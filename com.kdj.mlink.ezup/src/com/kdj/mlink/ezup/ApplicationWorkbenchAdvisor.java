package com.kdj.mlink.ezup;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.kdj.mlink.ezup.ui.DialogProgressProvider;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

//	private static final String PERSPECTIVE_ID = "com.kdj.mlink.ezup.perspective.ID"; //$NON-NLS-1$ 
//	private static final String PERSPECTIVE_ID = "com.kdj.mlink.ezup.perspectiveDebug.ID"; //$NON-NLS-1$ 
//	private static final String PERSPECTIVE_ID = "com.kdj.mlink.ezup.perspectiveFreeMoving.ID"; //$NON-NLS-1$ 
	private static final String PERSPECTIVE_ID = "com.kdj.mlink.ezup.concurrency.view.JobDemoPerspective.ID"; //$NON-NLS-1$ 

	@Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer); 
    }
    
    @Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
    
    @Override
    public void initialize(IWorkbenchConfigurer configurer) {
    	// TODO Auto-generated method stub
    	super.initialize(configurer);
    	configurer.setSaveAndRestore(true);
    	ConsolePlugin.getDefault().getConsoleManager().addConsoles(
    			 new IConsole[] { new DebugConsole() });

    }
    
//    @Override
//    public void preStartup() {
//    	Platform.getJobManager().setProgressProvider(new DialogProgressProvider());
//    }
}
