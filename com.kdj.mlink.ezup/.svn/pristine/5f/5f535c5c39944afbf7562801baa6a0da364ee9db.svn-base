package com.kdj.mlink.ezup;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.kdj.mlink.ezup.ui.MainView;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
//		layout.addView(MainView.ID, IPageLayout.LEFT, 0.33f, layout.getEditorArea());
		layout.addStandaloneView(MainView.ID, false, IPageLayout.LEFT,  1.0f, layout.getEditorArea()); 
//		layout.addStandaloneView(MainView.ID, false, IPageLayout.LEFT,  0.33f, layout.getEditorArea());
//		layout.addView("com.kdj.mlink.ezup.ui.ProgressView", IPageLayout.BOTTOM, 0.22f, layout.getEditorArea());
	}
}
