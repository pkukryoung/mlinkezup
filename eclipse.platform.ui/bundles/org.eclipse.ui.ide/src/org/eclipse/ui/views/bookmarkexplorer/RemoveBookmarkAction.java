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

package org.eclipse.ui.views.bookmarkexplorer;

import java.util.List;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.undo.DeleteMarkersOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.views.bookmarkexplorer.BookmarkMessages;

/**
 * Action to remove the selected bookmarks.
 *
 * Marked for deletion, see Bug 550439
 *
 * @noreference
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
@Deprecated
class RemoveBookmarkAction extends BookmarkAction {

	/**
	 * Create a new instance of this class.
	 *
	 * @param view the view
	 */
	public RemoveBookmarkAction(BookmarkNavigator view) {
		super(view, BookmarkMessages.RemoveBookmark_text);
		setToolTipText(BookmarkMessages.RemoveBookmark_toolTip);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
				IBookmarkHelpContextIds.REMOVE_BOOKMARK_ACTION);
		setEnabled(false);
	}

	/**
	 * Delete the marker selection.
	 */
	@Override
	public void run() {
		final IStructuredSelection sel = getStructuredSelection();
		if (sel.isEmpty()) {
			return;
		}
		List<IMarker> list = sel.toList();
		IMarker[] markers = new IMarker[list.size()];
		list.toArray(markers);
		IUndoableOperation op = new DeleteMarkersOperation(markers, BookmarkMessages.RemoveBookmark_undoText);
		execute(op, BookmarkMessages.RemoveBookmark_errorTitle, null,
				WorkspaceUndoUtil.getUIInfoAdapter(getView().getShell()));
	}

	@Override
	public void selectionChanged(IStructuredSelection sel) {
		setEnabled(!sel.isEmpty());
	}
}
