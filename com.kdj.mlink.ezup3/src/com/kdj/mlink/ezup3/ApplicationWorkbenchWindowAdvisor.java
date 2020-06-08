package com.kdj.mlink.ezup3;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
//		configurer.setInitialSize(new Point(600, 400));
		configurer.setInitialSize(new Point(1024, 768));
		configurer.setShowCoolBar(true);
		// XXX Set the status line and progress indicator so that update
		// information can be shown there
		configurer.setShowStatusLine(true);
		configurer.setShowProgressIndicator(true);

	}

	@Override
	public void postWindowCreate() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.getWindow().getShell().setMaximized(true);
	}

}
