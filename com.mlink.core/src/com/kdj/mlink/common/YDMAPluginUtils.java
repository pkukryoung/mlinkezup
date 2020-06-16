package com.kdj.mlink.common;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;


public class YDMAPluginUtils {

	public static Shell getShell() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		return shell;
	}

	public static Shell getShell(ExecutionEvent event) {
		Shell shell = null;
		if (HandlerUtil.getActiveWorkbenchWindow(event) != null) {
			shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		} else {
			shell = getShell();
		}
		return shell;
	}

	public static IViewPart getView(String viewID) {
		IViewPart viewPart = null;
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			viewPart = page.showView(viewID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return viewPart;
	}
	
	public static int[] getMonitorSize() {
		int[] size = new int[2];
		Monitor[] mo = Display.getDefault().getMonitors();
		int width = 0;
		int height = 0;
		 for(int i=0; i<mo.length; i++) {
		   System.err.println("¸ð´ÏÅÍ["+i+"] - "+mo[i].getBounds());
		   width = (width >= mo[i].getBounds().width) ? width : mo[i].getBounds().width;
		   height = (height >= mo[i].getBounds().height) ? height : mo[i].getBounds().height;
		 }
		 size[0]=width;
		 size[1]=height;
		 return size;
	}

	
}
