package com.kdj.mlink.ezup3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class DebugConsole extends MessageConsole {

	public static final String ID_CONSOLE_VIEW = "com.kdj.mlink.ezup3.IConsoleConstants.ID";

	private MessageConsoleStream outMessageStream;
	private MessageConsoleStream inMessageStream;

	public DebugConsole() {
		super("XMPP Debug", null);
		outMessageStream = newMessageStream();
		outMessageStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
		inMessageStream = newMessageStream();
		inMessageStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

	}

	@Override
	protected void dispose() {
	}
}
