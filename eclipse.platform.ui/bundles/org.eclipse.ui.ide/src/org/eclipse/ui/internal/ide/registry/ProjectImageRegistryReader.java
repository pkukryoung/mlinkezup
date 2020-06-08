/*******************************************************************************
 * Copyright (c) 2000, 2019 IBM Corporation and others.
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
 *     Alexander Fedorov <alexander.fedorov@arsysop.ru> - Bug 548799
 *******************************************************************************/
package org.eclipse.ui.internal.ide.registry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
 * A strategy to project nature image extensions from the registry.
 */
public class ProjectImageRegistryReader extends IDERegistryReader {
	private static final String TAG_IMAGE = "image";//$NON-NLS-1$

	private static final String ATT_ID = "id";//$NON-NLS-1$

	private static final String ATT_NATURE_ID = "natureId";//$NON-NLS-1$

	private static final String ATT_ICON = "icon";//$NON-NLS-1$

	private ProjectImageRegistry registry;

	/**
	 * Reads the contents of the given element
	 */
	@Override
	protected boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(TAG_IMAGE)) {
			return false;
		}

		String id = element.getAttribute(ATT_ID);
		if (id == null) {
			logMissingAttribute(element, ATT_ID);
			return true;
		}

		String natureId = element.getAttribute(ATT_NATURE_ID);
		if (natureId == null) {
			logMissingAttribute(element, ATT_NATURE_ID);
			return true;
		}

		String icon = element.getAttribute(ATT_ICON);
		if (icon == null) {
			logMissingAttribute(element, ATT_ICON);
			return true;
		}
		String extendingPluginId = element.getContributor().getName();
		ResourceLocator.imageDescriptorFromBundle(extendingPluginId, icon)
				.ifPresent(d -> registry.setNatureImage(natureId, d));
		return true;
	}

	/**
	 * Read the project nature images within a registry.
	 */
	public void readProjectNatureImages(IExtensionRegistry in,
			ProjectImageRegistry out) {
		registry = out;
		readRegistry(in, IDEWorkbenchPlugin.IDE_WORKBENCH,
				IDEWorkbenchPlugin.PL_PROJECT_NATURE_IMAGES);
	}
}
