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
 *******************************************************************************/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

/**
 * This is used to sort action sets in the perspective customization dialog.
 */
public class ActionSetComparator extends ViewerComparator {

	/**
	 * Creates a new sorter.
	 */
	public ActionSetComparator() {
	}

	/**
	 * Returns a negative, zero, or positive number depending on whether the first
	 * element is less than, equal to, or greater than the second element.
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof IActionSetDescriptor) {
			String str1 = LegacyActionTools.removeMnemonics(((IActionSetDescriptor) e1).getLabel());
			String str2 = LegacyActionTools.removeMnemonics(((IActionSetDescriptor) e2).getLabel());
			return getComparator().compare(str1, str2);
		}
		return 0;
	}
}
