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
 *******************************************************************************/

package org.eclipse.ui.views.bookmarkexplorer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.bookmarkexplorer.BookmarkMessages;

/**
 * Action to select all bookmarks.
 *
 * Marked for deletion, see Bug 550439
 *
 * @noreference
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
@Deprecated
class SelectAllAction extends BookmarkAction {

	/**
	 * Create a new instance of this class.
	 *
	 * @param view the view
	 */
	public SelectAllAction(BookmarkNavigator view) {
		super(view, BookmarkMessages.SelectAll_text);
		setToolTipText(BookmarkMessages.SelectAll_toolTip);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
				IBookmarkHelpContextIds.SELECT_ALL_BOOKMARK_ACTION);
		setEnabled(true);
	}

	@Override
	public void run() {
		Viewer viewer = getView().getViewer();
		Control control = viewer.getControl();
		if (control instanceof Table) {
			((Table) control).selectAll();
			viewer.setSelection(viewer.getSelection(), false);
		}
	}
}
