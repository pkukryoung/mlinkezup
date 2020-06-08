package com.kdj.mlink.ezup;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class DebugConsole extends MessageConsole {
	
	public static final String ID_CONSOLE_VIEW = "com.kdj.mlink.ezup.IConsoleConstants.ID";
	
	private MessageConsoleStream outMessageStream;
	private MessageConsoleStream inMessageStream;
	
//	private PacketListener outListener = new PacketListener() {
//		public void processPacket(Packet arg0) {
//		outMessageStream.println(arg0.toXML());
//		}
//	};
//	
//	private PacketListener inListener = new PacketListener() {
//		public void processPacket(Packet arg0) {
//			inMessageStream.println(arg0.toXML());
//		}
//	};
	public DebugConsole() {
		super("XMPP Debug", null);
		outMessageStream = newMessageStream();
		outMessageStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
		inMessageStream = newMessageStream();
		inMessageStream.setColor(Display.getCurrent().getSystemColor( SWT.COLOR_RED));
//		Session.getInstance().getConnection().
//		addPacketWriterListener(outListener, null);
//		Session.getInstance().getConnection().
//		addPacketListener(inListener, null);
	}
	protected void dispose() {
//		Session.getInstance().getConnection().removePacketWriterListener( outListener);
//		Session.getInstance().getConnection().removePacketListener(inListener);
	}
}