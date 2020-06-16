package com.kdj.mlink.ezup;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.IHandlerService;
import org.osgi.framework.Version;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	Control toolbar;
	Control page;
	Control statusline;
	QuickSearchPanel searchPanel;
	
	private org.eclipse.swt.graphics.Image statusImage;
	private IWorkbenchWindow window;
    private TrayItem trayItem;
    private org.eclipse.swt.graphics.Image trayImage;  
    
    private ImageRegistry images = new ImageRegistry();
    private ApplicationActionBarAdvisor actionBarAdvisor;

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }
    
    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    @Override
    public void preWindowOpen() {
    	
    	Version version = Platform.getProduct().getDefiningBundle().getVersion(); 
		int major = version.getMajor();
		int minor = version.getMinor();
		int micro = version.getMicro();
		
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
//        configurer.setInitialSize(new Point(400, 300));
        configurer.setInitialSize(new Point(1024, 768));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowMenuBar(true);
        configurer.setTitle("Mall Link Ver " + major + "." + minor + "." + micro);
        configurer.setShowProgressIndicator(true);

    }
    
    @Override
    public void postWindowOpen() {

    	initStatusLine();
    	window = getWindowConfigurer().getWindow();
    	trayItem = initTaskItem(window);
    	if(trayItem != null) {
    		hooPopupMenu(window);
    		hooMinimize(window);
    	}
    	
    }
    
    private void initStatusLine() {
    	statusImage = AbstractUIPlugin.imageDescriptorFromPlugin("com.kdj.mlink.ezup", IImageKeys.ONLINE ).createImage();
    	IStatusLineManager statusline = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
    	statusline.setMessage(statusImage, "Online"); 
	
    }
    
    private TrayItem initTaskItem(IWorkbenchWindow window) {
    	final Tray tray = window.getShell().getDisplay().getSystemTray();
    	if(tray == null) return null;
    	TrayItem trayItem = new TrayItem(tray, SWT.NONE);
    	trayImage = AbstractUIPlugin.imageDescriptorFromPlugin("com.kdj.mlink.ezup", IImageKeys.ONLINE).createImage();
    	trayItem.setImage(trayImage);
    	trayItem.setToolTipText("M-link");
    	return trayItem;
    }
    private void hooPopupMenu(final IWorkbenchWindow window) {
    	trayItem.addListener(SWT.MenuDetect, new Listener() {
        	public void handleEvent(Event event) {
        		Menu menu = new Menu(window.getShell(), SWT.POP_UP);
        		// Creates a new menu item that terminates the program
        		// when selected
        		MenuItem exit = new MenuItem(menu, SWT.NONE); 
        		exit.setText("Exit!");
        		exit.addListener(SWT.Selection, new Listener() {
        				public void handleEvent(Event event) {
        					// Lets call our command
        					IHandlerService handlerService = (IHandlerService) window
        							.getService(IHandlerService.class);
        					try {
        						handlerService.executeCommand(ICommandIds.CMD_EXIT , null);
        					} catch (Exception ex) {
        						throw new RuntimeException(ICommandIds.CMD_EXIT );
        					}
        				}
        		});
                // We need to make the menu visible
                menu.setVisible(true);
                }
            });
    }
    private void hooMinimize(final IWorkbenchWindow window) {
    	window.getShell().addShellListener(new ShellAdapter() {
        	// If the window is minimized hide the window
        	public void shellIconified(ShellEvent e) {
        		window.getShell().setVisible(false);
        	}
        });
        // If user double-clicks on the tray icons the application will be
        // visible again
        trayItem.addListener(SWT.DefaultSelection, new Listener() {
        	public void handleEvent(Event event) {
        		Shell shell = window.getShell();
        		if (!shell.isVisible()) {
        			shell.setVisible(true);
        			window.getShell().setMinimized(false);
        		}
        	}
        });
    }
    
    @Override
    public void dispose() {
    	if(statusImage != null) statusImage.dispose();
    	if(trayImage != null) trayImage.dispose();
    	if(trayItem != null) trayItem.dispose();
    }
    
    @Override
	public void createWindowContents(Shell shell) {
		 IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		 Menu menu = configurer.createMenuBar();
		 shell.setMenuBar(menu);
		 FormLayout layout = new FormLayout();
		 layout.marginWidth = 0;
		 layout.marginHeight = 0;
		 shell.setLayout(layout);
		 Control toolbar = configurer.createCoolBarControl(shell);
		 Control page = configurer.createPageComposite(shell);
		 Control statusline = configurer.createStatusLineControl(shell);
		 // The layout method does the work of connecting the
		 // controls together.
		 layoutNormal();
		}
	
	private void layoutNormal() {
		 // Toolbar
		 FormData data = new FormData();
		 data.top = new FormAttachment(0, 0);
		 data.left = new FormAttachment(0, 0);
		 data.right = new FormAttachment(100, 0);
		 toolbar.setLayoutData(data);
		 // Status line
		 data = new FormData();
		 data.bottom = new FormAttachment(100, 0);
		 data.left = new FormAttachment(0, 0);
		 data.right = new FormAttachment(100, 0);
		 statusline.setLayoutData(data);
		 // page contents
		 data = new FormData();
		 data.top = new FormAttachment(toolbar);
		 data.left = new FormAttachment(0, 0);
		 data.right = new FormAttachment(100, 0);
		 data.bottom = new FormAttachment(statusline);
		 page.setLayoutData(data);
		 getWindowConfigurer().getWindow().getShell().layout(true);
		}
	
	public void setShowToolbar(boolean visible) {
		 if (visible) {
			 if (toolbar.isVisible())
				 return;
			 FormData data = (FormData) page.getLayoutData();
			 data.top = new FormAttachment(toolbar, 0);
			 page.setLayoutData(data);
			 toolbar.setVisible(true);
		 } else {
			 if (!toolbar.isVisible())
				 return;
			 FormData data = (FormData) page.getLayoutData();
			 data.top = new FormAttachment(0, 0);
			 page.setLayoutData(data);
			 toolbar.setVisible(false);
		 }
		 getWindowConfigurer().getWindow().getShell().layout(true);
	}
	
	public void setShowSearchPanel(boolean visible) {
		 if (visible) {
			 if (searchPanel != null)
				 return;
			 searchPanel = new QuickSearchPanel( getWindowConfigurer().getWindow().getShell(), null);
			 FormData data = (FormData) page.getLayoutData();
			 data.bottom = new FormAttachment(searchPanel, 0);
			 page.setLayoutData(data);
			 data = new FormData();
			 data.left = new FormAttachment(0, 0);
			 data.right = new FormAttachment(100, 0);
			 if (statusline.isVisible()) {
				 data.bottom = new FormAttachment(statusline, 0);
			 } else {
				 data.bottom = new FormAttachment(100, 0);
			 }
			 searchPanel.setLayoutData(data);
		 } else {
		 }
	}

}
