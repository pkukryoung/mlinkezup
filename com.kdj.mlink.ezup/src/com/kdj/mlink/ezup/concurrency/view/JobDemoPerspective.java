package com.kdj.mlink.ezup.concurrency.view;

/*
 * "The Java Developer's Guide to Eclipse"
 *   by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. 
 * All Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * "CustomPerspective".
 * @see IPerspectiveFactory
 */
public class JobDemoPerspective implements IPerspectiveFactory {

	private static final String ID_JOBSVIEW =
		"com.kdj.mlink.ezup.concurrency.view.JobsView";
	private static final String ID_PROGRESSVIEW =
		"org.eclipse.ui.views.ProgressView";
	
	/**
	 * Implement the "CustomPerspective" constructor.
	 */
	public JobDemoPerspective() {
	}

	/**
	 * The initial layout of this perspective will have no editor area. 
	 * It will contain a folder in the top left area with the Resource Navigator 
	 * and Bookmarks view. It will have the Outline view below this folder. 
	 * It will also contain the Resource view under the editor area and a folder 
	 * with the Properties view and a place holder for the Tasks view below the 
	 * Resource view.
	 * 
	 * Shortcuts for the Custom project wizard, Java perspective, and the 
	 * Tasks view have also been defined. 
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {

		// Get the editor area
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		// Add shortcuts
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);

		// Top: Add Jobs View 
		layout.addView(ID_JOBSVIEW, IPageLayout.TOP, 0.6f, editorArea);
		
		// Bottom : Resource Navigator and Progress view 
		IFolderLayout bottom =
			layout.createFolder("topLeft", IPageLayout.BOTTOM, 0.3f, editorArea);
		bottom.addView(ID_PROGRESSVIEW);
		bottom.addView(IPageLayout.ID_RES_NAV);		
	}
}
