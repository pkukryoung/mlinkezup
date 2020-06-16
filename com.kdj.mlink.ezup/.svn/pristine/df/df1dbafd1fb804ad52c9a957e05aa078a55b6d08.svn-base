package com.kdj.mlink.ezup;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.jface.action.Action;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction preferenceAtion;
	private IWorkbenchAction helpAtion;
	private IWorkbenchAction searchHelpAction;
	private IWorkbenchAction dynamicHelpAction;
	private StatusLineContribution statusContribution;
	private ProgressAction progressAction;
	private Action toggleToolbar;
	private IWorkbenchWindowConfigurer windowAdvisor;
	private IAction toggleStatusLine;
	private IAction toggleQuickSearch;
	private ApplicationWorkbenchWindowAdvisor aa;

	public ApplicationActionBarAdvisor (IActionBarConfigurer configurer) {
		super(configurer);
		windowAdvisor = configurer.getWindowConfigurer(); 
		aa = new ApplicationWorkbenchWindowAdvisor(windowAdvisor);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		
		toggleToolbar = new Action("Toolbar", IAction.AS_CHECK_BOX) {
	    	 public void run() {
	    		 windowAdvisor.setShowCoolBar(!windowAdvisor.getShowCoolBar()); 
	    	 }
	    };
	    
	    toggleQuickSearch = new Action("Search Panel", IAction.AS_CHECK_BOX) {
	    	 public void run() {
	    		 aa.setShowSearchPanel(true);
//	    	 	 windowAdvisor.setShowSearchPanel(!windowAdvisor.getShowSearchPanel());
	    		 updateEnablements();
	    	 }
	    };
		
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		preferenceAtion = ActionFactory.PREFERENCES.create(window);
		register(preferenceAtion);
		helpAtion = ActionFactory.HELP_CONTENTS.create(window);
		register(helpAtion);
		searchHelpAction = ActionFactory.HELP_SEARCH.create(window); // NEW
	    register(searchHelpAction); 
	    dynamicHelpAction = ActionFactory.DYNAMIC_HELP.create(window); // NEW
	    register(dynamicHelpAction); 
	    
	    progressAction = new ProgressAction();
	    progressAction.setText("Count me!");
	    progressAction.setId("progress.action");
	    register(progressAction);
	    
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {

		MenuManager mlinkMenu = new MenuManager("&Mlink","mlink");
		mlinkMenu.add(exitAction);
		MenuManager helpMenu = new MenuManager("&Help","help");
		helpMenu.add(helpAtion);
		helpMenu.add(aboutAction);
		helpMenu.add(searchHelpAction); // NEW
		helpMenu.add(dynamicHelpAction); // NEW
		MenuManager prefMenu = new MenuManager("&Preference","preference");
		prefMenu.add(preferenceAtion);

		menuBar.add(mlinkMenu);
		menuBar.add(helpMenu);
		menuBar.add(prefMenu);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
//		super.fillCoolBar(coolBar);
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar);
		toolbar.add(exitAction);
		toolbar.add(helpAtion);
		toolbar.add(aboutAction);
		toolbar.add(searchHelpAction);
		toolbar.add(dynamicHelpAction);
		toolbar.add(preferenceAtion);
		
		IContributionItem comboCI = new ControlContribution(null) {
			@Override
			protected Control createControl(Composite parent) {
				Combo c = new Combo(parent, SWT.READ_ONLY);
//				c.setBounds(565, 96, 250, 28);
				c.setBounds(574, 1, 250, 28);
//				c.setLayoutData(new GridData(SWT.FILL, SWT.RIGHT, true, false, 1, 1));
				c.add("one");
				c.add("two");
				c.add("three");
				return c;
			}
		};
		toolbar.add(comboCI);
		
		toolbar.add(progressAction);
	}
	
	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {	
		StatusLineContribution  statusItem = new StatusLineContribution("1");
		statusItem.setText("aaaa");
		statusLine.add(statusItem);
	}
	
	protected void fillTrayItem(IMenuManager trayItem) {
		trayItem.add(aboutAction);
		trayItem.add(exitAction);
	}

	private void updateEnablements() {
		 toggleToolbar.setChecked(windowAdvisor.getShowCoolBar());
		 toggleStatusLine.setChecked(windowAdvisor.getShowStatusLine());
		 toggleQuickSearch.setChecked(windowAdvisor.getShowFastViewBars());
		}

}

