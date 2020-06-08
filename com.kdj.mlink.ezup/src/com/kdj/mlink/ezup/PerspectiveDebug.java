package com.kdj.mlink.ezup;

import java.io.PrintStream;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.kdj.mlink.ezup.ui.MainView;

public class PerspectiveDebug implements IPerspectiveFactory {
	
	private static final String ID_PROGRESSVIEW = "org.eclipse.ui.views.ProgressView";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		
		MessageConsole console = new MessageConsole("Console", null);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
		MessageConsoleStream stream = console.newMessageStream();

		PrintStream myS = new PrintStream(stream);
		System.setOut(myS);
		System.setErr(myS);

		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);
		
		layout.addStandaloneView(MainView.ID, false,IPageLayout.LEFT, 1.00f, layout.getEditorArea());
//		layout.addView(MainView.ID, IPageLayout.LEFT, 1.00f, layout.getEditorArea());
//		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.33f, layout.getEditorArea());
//		folder.addPlaceholder(MainView.ID + ":*");
//		folder.addView(MainView.ID);
		
//		IFolderLayout folder2 = layout.createFolder("messages", IPageLayout.BOTTOM, 0.20f, layout.getEditorArea());
//		folder2.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW + ":*");
//		folder2.addView(IConsoleConstants.ID_CONSOLE_VIEW);
//		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW,IPageLayout.BOTTOM, 0.67f, MainView.ID);
//		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM, 0.90f, MainView.ID);
		IFolderLayout bottom = layout.createFolder("topLeft", IPageLayout.BOTTOM, 0.90f, MainView.ID);
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		bottom.addView(ID_PROGRESSVIEW);

	}

}
