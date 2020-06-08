package com.kdj.mlink.ezup.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class CommandDialog extends Dialog {
	
	public CommandDialog(Shell parentShell) {
		super(parentShell);
	}

	protected void openErrDialog(String mesg) {

		MessageDialog.openError(getShell(), "Error", mesg);
	}

	
	protected void openErrDialog(String title, String mesg) {
		if (title == null && title.length() == 0) {
			this.openErrDialog(mesg);
		}

		MessageDialog.openError(getShell(), title, mesg);
	}

	protected void openInfoDialog(String mesg) {
		if (mesg.length() == 0) {
			return;
		}

		MessageDialog.openInformation(getShell(), "»Æ¿Œ", mesg);
	}

	
	protected void openInfoDialog(String title, String mesg) {

		if (title == null && title.length() == 0) {
			this.openInfoDialog(mesg);
		}

		MessageDialog.openInformation(getShell(), title, mesg);
	}

	protected void initializeBounds() {
		super.initializeBounds();
		Shell shell = this.getShell();
		Monitor primary = shell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
}
