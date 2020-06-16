package com.kdj.mlink.ezup;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.kdj.mlink.ezup.ui.Login;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {
	
	public static final String PLUGIN_ID = "com.kdj.mlink.ezup";

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		
		//login 
		ExecutionEvent event = null;
		
		// 환경설정에서 자동 Login 설정인지 확인
//		Preferences pref = (Preferences) new ConfigurationScope().getNode(Application.PLUGIN_ID);
//		boolean auto_login = pref.getBoolean(PreferenceConstants.AUTO_LOGIN, true);
		
	    ScopedPreferenceStore pref = new ScopedPreferenceStore(new ConfigurationScope(), Application.PLUGIN_ID);
	    boolean auto_login = pref.getBoolean(PreferenceConstants.AUTO_LOGIN);
		
		if (!openLoginDialog(event,auto_login)) return IApplication.EXIT_OK;
		
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}
	
	private boolean openLoginDialog(ExecutionEvent event, Boolean auto_login) {
		try {
			Login d = new Login(getShell(event),auto_login);
			d.open();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private Shell getShell(ExecutionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
