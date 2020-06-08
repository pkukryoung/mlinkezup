package com.kdj.mlink.ezup.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import com.kdj.mlink.ezup.ui.Login;
import com.kdj.mlink.ezup.commands.NodeHandler;

public class OpenDialogHandler extends NodeHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		openLoginDialog(event);
		return null;
	}

	private void openLoginDialog(ExecutionEvent event) {
		Login d = new Login(getShell(event),false);
		d.open();
	}
		
}
