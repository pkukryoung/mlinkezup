package com.kdj.mlink.ezup3;

import java.io.PrintStream;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Perspective implements IPerspectiveFactory {

	public static final String ID = "com.kdj.mlink.ezup3.perspective";

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

		layout.addView(NavigationView.ID, IPageLayout.LEFT, 0.10f, editorArea);
//		layout.addStandaloneView(NavigationView.ID, false, IPageLayout.LEFT, 0.10f, editorArea);
		IFolderLayout folder = layout.createFolder("Process", IPageLayout.TOP, 0.90f, editorArea);
		folder.addPlaceholder(View.ID + ":*");
		folder.addView(View.ID);
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM, 0.10f, NavigationView.ID);
//		IFolderLayout folder2 = layout.createFolder("messages", IPageLayout.BOTTOM, 0.20f, editorArea);
//		folder2.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW + ":*");
//		folder2.addView(IConsoleConstants.ID_CONSOLE_VIEW);

		layout.getViewLayout(NavigationView.ID).setCloseable(false);
		layout.getViewLayout(View.ID).setCloseable(false);
		layout.getViewLayout(IConsoleConstants.ID_CONSOLE_VIEW).setCloseable(false);

//		layout.addStandaloneView(NavigationView.ID, false, IPageLayout.LEFT, 0.33f, layout.getEditorArea());
//		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM, 0.70f, layout.getEditorArea());

	}
}
