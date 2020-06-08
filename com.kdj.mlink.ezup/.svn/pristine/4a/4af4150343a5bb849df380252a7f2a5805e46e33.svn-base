package com.kdj.mlink.ezup.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class NodeHandler extends AbstractHandler implements IHandler {
	
	protected Shell getShell(ExecutionEvent event) {
		Shell shell = null;
		if (HandlerUtil.getActiveWorkbenchWindow(event) != null) {
			shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		} else {
			shell = Display.getCurrent().getActiveShell();
		}
		return shell;
	}
}
