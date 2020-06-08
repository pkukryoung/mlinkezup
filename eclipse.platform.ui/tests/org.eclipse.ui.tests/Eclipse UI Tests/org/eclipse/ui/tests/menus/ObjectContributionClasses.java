/*******************************************************************************
 * Copyright (c) 2000, 2017 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.ui.tests.menus;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.mapping.ModelProvider;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.IContributorResourceAdapter;
import org.eclipse.ui.ide.IContributorResourceAdapter2;

public class ObjectContributionClasses implements IAdapterFactory {

	public static final String PROJECT_NAME = "testContributorResourceAdapter";

	public static interface ICommon extends IAdaptable {
	}

	public static class Common implements ICommon {

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}
	}

	public static interface IA {
	}

	public static class A implements IA {
	}

	public static class A1 extends A {
	}

	public static class A11 extends A1 {
	}

	public static interface IB {
	}

	public static class B implements IB {
	}

	public static class B2 implements IB {
	}

	public static class D extends Common implements IA {
	}

	public static class E implements IAdaptable {

		@SuppressWarnings("unchecked")
		@Override
		public <T> T getAdapter(Class<T> adapter) {
			if (adapter == IF.class) {
				return (T) new F();
			}
			return null;
		}
	}

	public static interface IF extends IAdaptable {
	}

	public static class F implements IF {
		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}
	}

	public static class E1 extends E {
	}

	public static class C implements ICommon {

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}
	}

	public static class CResource implements IAdaptable {
		@SuppressWarnings("unchecked")
		@Override
		public <T> T getAdapter(Class<T> adapter) {
			if(adapter == IContributorResourceAdapter.class) {
				return (T) new ResourceAdapter();
			}
			return null;
		}
	}

	public static class CFile implements IAdaptable {
		@SuppressWarnings("unchecked")
		@Override
		public <T> T getAdapter(Class<T> adapter) {
			if(adapter == IContributorResourceAdapter.class) {
				return (T) new ResourceAdapter();
			}
			return null;
		}
	}

	// Returns a contribution adapter that doesn't handle ResourceMappings
	public static class CResourceOnly implements IAdaptable {
		@SuppressWarnings("unchecked")
		@Override
		public <T> T getAdapter(Class<T> adapter) {
			if(adapter == IContributorResourceAdapter.class) {
				return (T) new ResourceOnlyAdapter();
			}
			return null;
		}
	}

	public interface IModelElement {
	}

	public static class ModelElement extends PlatformObject implements IModelElement {
	}

	// Default contributor adapter

	public static class ResourceAdapter implements IContributorResourceAdapter2 {
		@Override
		public IResource getAdaptedResource(IAdaptable adaptable) {
			if(adaptable instanceof CResource) {
				return ResourcesPlugin.getWorkspace().getRoot();
			}
			if(adaptable instanceof CFile) {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME).getFile("dummy");
			}
			return null;
		}
		@Override
		public ResourceMapping getAdaptedResourceMapping(IAdaptable adaptable) {
			return getAdaptedResource(adaptable).getAdapter(ResourceMapping.class);
		}
	}

	// Contributor adapter that doesn't handle resource mappings

	public static class ResourceOnlyAdapter implements IContributorResourceAdapter {
		@Override
		public IResource getAdaptedResource(IAdaptable adaptable) {
			if(adaptable instanceof CResourceOnly) {
				return ResourcesPlugin.getWorkspace().getRoot();
			}
			return null;
		}
	}

	// Adapter methods

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(final Object adaptableObject, Class<T> adapterType) {
		if(adapterType == IContributorResourceAdapter.class) {
			return (T) new ResourceAdapter();
		}
		if(adaptableObject instanceof IA && adapterType == IA.class) {
			return (T) new A();
		}
		if(adapterType == IResource.class) {
			return (T) ResourcesPlugin.getWorkspace().getRoot();
		}
		if(adapterType == ICommon.class) {
			return (T) new Common();
		}
		if(adapterType == ResourceMapping.class) {
			return (T) new ResourceMapping() {
				@Override
				public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) {
					return new ResourceTraversal[] {
							new ResourceTraversal(new IResource[] {ResourcesPlugin.getWorkspace().getRoot()}, IResource.DEPTH_INFINITE, IResource.NONE)
					};
				}
				@Override
				public IProject[] getProjects() {
					return ResourcesPlugin.getWorkspace().getRoot().getProjects();
				}
				@Override
				public Object getModelObject() {
					return adaptableObject;
				}
				@Override
				public String getModelProviderId() {
					return ModelProvider.RESOURCE_MODEL_PROVIDER_ID;
				}
			};
		}

		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { ICommon.class, IResource.class, IFile.class, IContributorResourceAdapter.class, ResourceMapping.class};
	}
}
