/*******************************************************************************
 * Copyright (c) 2005, 2015 IBM Corporation and others.
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
 *     Simon Scholz <simon.scholz@vogella.com> - Bug 460405
 *******************************************************************************/
package org.eclipse.ui.tests.navigator.extension;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;

public class TestExtensionAdapterFactory implements IAdapterFactory {

	private static final Class IRESOURCE_TYPE = IResource.class;
	private static final Class IFILE_TYPE = IFile.class;

	private static final Class[] ADAPTED_TYPES = new Class[] { IRESOURCE_TYPE, IFILE_TYPE };

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if(IRESOURCE_TYPE == adapterType || IFILE_TYPE == adapterType) {
			TestExtensionTreeData data = (TestExtensionTreeData) adaptableObject;
			return adapterType.cast(data.getFile());
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTED_TYPES;
	}

}
