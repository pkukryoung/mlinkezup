/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
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
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The TestTableContentProvider is the content provider for the
 * tabel views in the decorator testing.
 */
public class TestTableContentProvider implements IStructuredContentProvider {
	@Override
	public Object[] getElements(Object inputElement) {
		TableElement[] elements = new TableElement[100];

		for (int i = 0; i < elements.length; i++) {
			elements[i] = new TableElement(i);
		}

		return elements;
	}
	@Override
	public void dispose() {
		// Do nothing by default

	}
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Do nothing by default

	}
}
