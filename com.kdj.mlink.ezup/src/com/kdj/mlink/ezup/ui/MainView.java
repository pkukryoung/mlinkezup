package com.kdj.mlink.ezup.ui;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.kdj.mlink.ezup.StatusLineContribution;
import com.kdj.mlink.ezup.StatusLineContribution2;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class MainView extends ViewPart {
	
	public static final String ID = "com.kdj.mlink.ezup.MainView.ID";
	public static IActionBars statusBar;
	int sashLeft = 10;
	int sashRight = 90;
	static String host;
	
	Composite parent;
	Composite composite_main;
	CTabFolder mainTabFolder;
	TreeViewer treeViewer;

	public MainView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {		
		this.parent = parent;
		parent.setLayout(new StackLayout());
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "com.kdj.mlink.ezup.MainView");

		composite_main = new Composite(parent, SWT.NONE);
		composite_main.setLayout(new GridLayout(1, false));

		SashForm sashForm = new SashForm(composite_main, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ScrolledComposite scrolledComposite = new ScrolledComposite(sashForm, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		treeViewer = new TreeViewer(scrolledComposite, SWT.BORDER);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (selection == null) {
					return;
				} else {
					MenuModel temp = (MenuModel) selection.getFirstElement();
					String menuID = temp.getId();
					if (menuID != null && menuID.length() != 0) {
						processCommand(menuID);
					}
				}
			}
		});

		Tree tree = treeViewer.getTree();
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		mainTabFolder = new CTabFolder(sashForm, SWT.BORDER | SWT.CLOSE);
		mainTabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			@Override
			public void close(CTabFolderEvent event) {
				CTabFolder sourceFolder = (CTabFolder) event.getSource();
				CTabItem[] items = sourceFolder.getItems();
				CTabItem sourceItem = sourceFolder.getItem(sourceFolder.getSelectionIndex());
				String title = sourceItem.getText();

			}

		});

		mainTabFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				statusBar.getStatusLineManager().setMessage("");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int sashwidth = sashForm.getSashWidth();
				int[] sashWeights = sashForm.getWeights();
				if (sashWeights[1] == 1000) {
					sashForm.setWeights(new int[] { sashLeft, sashRight });
				} else {
					sashForm.setWeights(new int[] { 0, 100 });
				}
			}
		});
		mainTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		mainTabFolder.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
//		CTabItem tbtmIntro = new CTabItem(mainTabFolder, SWT.NONE);
//		tbtmIntro.setText("intro");
		
		sashForm.setWeights(new int[] { 10, 90 });

		treeViewer.setLabelProvider(new MenuTreeLabelProvider());
		treeViewer.setContentProvider(new MenuTreeContentProvider());

		this.setMenuTree();
		treeViewer.expandAll();

		((StackLayout) parent.getLayout()).topControl = composite_main;
		parent.layout();
		
		statusBar = getViewSite().getActionBars();
//		IStatusLineManager statusLine = statusBar.getStatusLineManager();
//		statusLine.remove("1");
//		StatusLineContribution  statusItem = new StatusLineContribution("1");
//		statusItem.setText("bbb");
//		statusLine.add(statusItem);
//		statusLine.update(true);
		
	}
	
	public void setMenuTree() {

		MenuModel root = new MenuModel(null, "MLink", "");
		MenuModel product = new MenuModel(root, "상품관리", "");
		product.child.add(new MenuModel(product, "상품등록", "aaa"));
		product.child.add(new MenuModel(product, "상품조회", "bbb"));
		
		MenuModel order = new MenuModel(root, "주문관리", "");
		MenuModel order_list = new MenuModel(order, "주문업로드", "ddd");
		order.child.add(order_list);
		
		root.child.add(product);
		root.child.add(order);
		
		treeViewer.setInput(root);
		
	}

	public CTabFolder getMainTabFolder() {
		return mainTabFolder;
	}

	private void processCommand(String menuID) {
//		System.out.println("** command ID :" + menuID);
//
//		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//				.getActivePage().getActivePart().getSite().getService(IHandlerService.class);
//		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//				.getActivePage().getActivePart().getSite().getService(ICommandService.class);	
//		Command generateCodeCmd = commandService.getCommand(menuID);
//		ExecutionEvent executionEvent = handlerService.createExecutionEvent(generateCodeCmd, new Event());
//		
		try {
//			generateCodeCmd.executeWithChecks(executionEvent);
			statusBar.getStatusLineManager().setMessage("menuID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		if(parent != null) // NEW
			parent.setFocus(); // NEW

	}

}
