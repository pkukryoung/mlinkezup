/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
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
package org.eclipse.ui.tests.views.properties.tabbed.override;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;
import org.eclipse.ui.tests.views.properties.tabbed.model.File;
import org.eclipse.ui.tests.views.properties.tabbed.model.Folder;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;

/**
 * The content provider for the override tests view.
 *
 * @author Anthony Hunter
 * @since 3.4
 */
public class OverrideTestsContentProvider implements IStructuredContentProvider {

	private Element[] elements;

	@Override
	public void dispose() {
		// not implemented
	}

	@Override
	public Object[] getElements(Object parent) {
		if (elements == null) {
			elements = new Element[] { new Information("Information"), //$NON-NLS-1$
					new Warning("Warning"), new Error("Error"), //$NON-NLS-1$ //$NON-NLS-2$
					new File("File"), new Folder("Folder") }; //$NON-NLS-1$//$NON-NLS-2$
		}
		return elements;
	}

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		// not implemented
	}
}