/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
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
package org.eclipse.e4.demo.cssbridge.ui;

import org.eclipse.e4.demo.cssbridge.ui.views.FolderPreviewView;
import org.eclipse.e4.demo.cssbridge.ui.views.FoldersView;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {
	public static final String ID = "org.eclipse.e4.demo.cssbridge.ui.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		layout.addStandaloneView(FoldersView.ID, false, IPageLayout.LEFT,
				0.25f, editorArea);
		layout.addStandaloneView(FolderPreviewView.ID, false,
				IPageLayout.RIGHT, 0.5f, editorArea);
		layout.getViewLayout(FoldersView.ID).setCloseable(false);
		layout.getViewLayout(FolderPreviewView.ID).setCloseable(false);
	}
}
