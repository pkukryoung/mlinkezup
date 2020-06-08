/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Andrey Loskutov <loskutov@gmx.de> - Bug 41431, 462760
 *******************************************************************************/

package org.eclipse.ui.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
 * The abstract superclass for resource-based actions that listen to selection
 * change events. This implementation tracks the current selection (see
 * <code>getStructuredSelection</code>) and provides a convenient place to
 * monitor selection changes that could affect the availability of the action.
 * <p>
 * Subclasses must implement the following <code>IAction</code> method:
 * </p>
 * <ul>
 * <li><code>run</code> - to do the action's work</li>
 * </ul>
 * <p>
 * Subclasses may extend the <code>updateSelection</code> method to update the
 * action determine its availability based on the current selection.
 * </p>
 * <p>
 * The object instantiating the subclass is responsible for registering the
 * instance with a selection provider. Alternatively, the object can notify the
 * subclass instance directly of a selection change using the methods:
 * </p>
 * <ul>
 * <li><code>selectionChanged(IStructuredSelection)</code> - passing the
 * selection</li>
 * <li><code>selectionChanged(ISelectionChangedEvent)</code> - passing the
 * selection change event</li>
 * </ul>
 */
public abstract class SelectionListenerAction extends BaseSelectionListenerAction {

	/**
	 * Indicates whether the selection has changes since <code>resources</code>
	 * and <code>nonResources</code> were computed.
	 */
	private boolean selectionDirty = true;

	/**
	 * The list of resource elements in the current selection (element type:
	 * <code>IResource</code>); meaningful only when
	 * <code>selectionDirty == false</code>.
	 */
	private List<IResource> resources;

	/**
	 * The list of non-resource elements in the current selection (element type:
	 * <code>Object</code>); meaningful only when
	 * <code>selectionDirty == false</code>.
	 */
	private List<Object> nonResources;

	/**
	 * Creates a new action with the given text.
	 *
	 * @param text
	 *            the string used as the text for the action, or
	 *            <code>null</code> if there is no text
	 */
	protected SelectionListenerAction(String text) {
		super(text);
	}

	/**
	 * The <code>SelectionListenerAction</code> implementation of this
	 * <code>BaseSelectionListenerAction</code> method clears the cached
	 * resources and non-resources.
	 */
	@Override
	protected void clearCache() {
		selectionDirty = true;
		// clear out the lists in case computeResources does not get called
		// immediately
		resources = null;
		nonResources = null;
	}

	/**
	 * Extracts <code>IResource</code>s from the current selection and adds
	 * them to the resources list, and the rest into the non-resources list.
	 */
	private final void computeResources() {
		resources = null;
		nonResources = null;

		IStructuredSelection structuredSelection = getStructuredSelection();
		// assume selection contains mostly resources most times
		List<IResource> resourcesTmp = new ArrayList<>(structuredSelection.size());
		List<Object> nonResourcesTmp = new ArrayList<>();

		for (Iterator<?> e = structuredSelection.iterator(); e.hasNext();) {
			Object next = e.next();

			IResource resource = Adapters.adapt(next, IResource.class);

			if (resource != null) {
				resourcesTmp.add(resource);
				continue;
			}

			boolean resourcesFoundForThisSelection = false;
			ResourceMapping mapping = Adapters.adapt(next, ResourceMapping.class);

			if (mapping != null) {
				ResourceTraversal[] traversals = null;
				try {
					traversals = mapping.getTraversals(ResourceMappingContext.LOCAL_CONTEXT, new NullProgressMonitor());
				} catch (CoreException exception) {
					IDEWorkbenchPlugin.log(exception.getLocalizedMessage(), exception.getStatus());
				}

				if (traversals != null) {
					for (ResourceTraversal traversal : traversals) {
						IResource[] traversalResources = traversal.getResources();
						if (traversalResources != null) {
							resourcesFoundForThisSelection = true;
							resourcesTmp.addAll(Arrays.asList(traversalResources));
						}
					}
				}
			}

			if (resourcesFoundForThisSelection) {
				continue;
			}

			nonResourcesTmp.add(next);
		}

		resources = resourcesTmp.isEmpty() ? null : resourcesTmp;
		nonResources = nonResourcesTmp.isEmpty() ? null : nonResourcesTmp;
	}

	/**
	 * Returns the elements in the current selection that are not
	 * <code>IResource</code>s.
	 *
	 * @return list of elements (element type: <code>Object</code>)
	 */
	protected List<?> getSelectedNonResources() {
		// recompute if selection has changed.
		if (selectionDirty) {
			computeResources();
			selectionDirty = false;
		}

		List<Object> list = nonResources;
		if (list == null) {
			return Collections.emptyList();
		}

		return list;
	}

	/**
	 * Returns the elements in the current selection that are
	 * <code>IResource</code>s.
	 *
	 * @return list of resource elements (element type: <code>IResource</code>)
	 */
	protected List<? extends IResource> getSelectedResources() {
		// recompute if selection has changed.
		if (selectionDirty) {
			computeResources();
			selectionDirty = false;
		}

		List<IResource> list = resources;
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}

	/**
	 * Returns whether the type of the given resource is among those in the
	 * given resource type mask.
	 *
	 * @param resource
	 *            the resource
	 * @param resourceMask
	 *            a bitwise OR of resource types: <code>IResource</code>.{<code>FILE</code>,
	 *            <code>FOLDER</code>, <code>PROJECT</code>,
	 *            <code>ROOT</code>}
	 * @return <code>true</code> if the resource type matches, and
	 *         <code>false</code> otherwise
	 * @see IResource
	 */
	protected boolean resourceIsType(IResource resource, int resourceMask) {
		return (resource.getType() & resourceMask) != 0;
	}

	/**
	 * Returns whether the current selection consists entirely of resources
	 * whose types are among those in the given resource type mask.
	 *
	 * @param resourceMask
	 *            a bitwise OR of resource types: <code>IResource</code>.{<code>FILE</code>,
	 *            <code>FOLDER</code>, <code>PROJECT</code>,
	 *            <code>ROOT</code>}
	 * @return <code>true</code> if all resources in the current selection are
	 *         of the specified types or if the current selection is empty, and
	 *         <code>false</code> if some elements are resources of a
	 *         different type or not resources
	 * @see IResource
	 */
	protected boolean selectionIsOfType(int resourceMask) {
		if (getSelectedNonResources().size() > 0) {
			return false;
		}

		for (IResource next : getSelectedResources()) {
			if (!resourceIsType(next, resourceMask)) {
				return false;
			}
		}
		return true;
	}

}
