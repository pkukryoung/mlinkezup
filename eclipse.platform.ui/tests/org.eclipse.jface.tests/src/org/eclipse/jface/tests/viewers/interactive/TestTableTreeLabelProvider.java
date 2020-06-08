/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
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
 *     Jeanderson Candido <http://jeandersonbc.github.io> - Bug 433608
 *******************************************************************************/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TestTableTreeLabelProvider extends TestLabelProvider implements
		ITableLabelProvider {
	/**
	 * Returns the label image for a given column.
	 *
	 * @param element
	 *            the object representing the entire row. Can be
	 *            <code>null</code> indicating that no input object is set in
	 *            the viewer.
	 * @param columnIndex
	 *            the index of the column in which the label appears. Numbering
	 *            is zero based.
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return getImage();
	}

	/**
	 * Returns the label text for a given column.
	 *
	 * @param element
	 *            the object representing the entire row. Can be
	 *            <code>null</code> indicating that no input object is set in
	 *            the viewer.
	 * @param columnIndex
	 *            the index of the column in which the label appears. Numbering
	 *            is zero based.
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element != null) {
			return element + " column " + columnIndex;
		}
		return null;
	}
}
