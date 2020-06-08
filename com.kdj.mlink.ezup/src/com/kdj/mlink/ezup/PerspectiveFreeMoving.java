package com.kdj.mlink.ezup;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.kdj.mlink.ezup.ui.MainView;

public class PerspectiveFreeMoving implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.addView(MainView.ID, IPageLayout.LEFT, 0.33f,
				 layout.getEditorArea());
		layout.getViewLayout(MainView.ID).setCloseable(false);

	}

}
