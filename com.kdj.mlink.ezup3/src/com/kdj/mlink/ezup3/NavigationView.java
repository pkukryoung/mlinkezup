package com.kdj.mlink.ezup3;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.kdj.mlink.ezup3.ui.ProductManager;

public class NavigationView extends ViewPart {
	public static final String ID = "com.kdj.mlink.ezup3.navigationView";
	private TreeViewer viewer;
	private int instanceNum = 1;

	class TreeObject {
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return getName();
		}
	}

	class TreeParent extends TreeObject {
		private ArrayList<TreeObject> children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList<>();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return children.toArray(new TreeObject[children.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	/**
	 * We will set up a dummy model to initialize tree heararchy. In real code, you
	 * will connect to a real model and expose its hierarchy.
	 */
	private TreeObject createDummyModel() {

		TreeObject to1 = new TreeObject("상품관리");
		TreeObject to2 = new TreeObject("창고관리");
		TreeObject to3 = new TreeObject("배송관리");
		TreeParent p1 = new TreeParent("상품관리");
		p1.addChild(to1);
		p1.addChild(to2);
		p1.addChild(to3);

		TreeObject to4 = new TreeObject("주문업로드");
		TreeParent p2 = new TreeParent("주문관리");
		p2.addChild(to4);

		TreeParent root = new TreeParent("");
		root.addChild(p1);
		root.addChild(p2);
		return root;
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.addDoubleClickListener(event -> {
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if (selection == null) {
				return;
			} else {
//				MenuModel temp = (MenuModel) selection.getFirstElement();
//				String menuID = temp.getId();
				String menuID = ProductManager.ID;
//				String menuID = View.ID;
				if (menuID != null && menuID.length() != 0) {
					processCommand(menuID);
				}
			}
		});
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createDummyModel());

//		MenuModel root = new MenuModel(null, "MLink", "");
//		MenuModel product = new MenuModel(root, "상품관리", "");
//		product.child.add(new MenuModel(product, "상품관리", ""));
//		product.child.add(new MenuModel(product, "창고관리", ""));
//		product.child.add(new MenuModel(product, "배송관리", ""));
//
//		MenuModel order = new MenuModel(root, "주문관리", "");
//		order.child.add(new MenuModel(order, "주문업로드", ""));
//		order.child.add(new MenuModel(order, "주문내역관리", ""));
//		order.child.add(new MenuModel(order, "랙별픽업리스트출력", ""));
//		order.child.add(new MenuModel(order, "택배사별출고리스트출력", ""));
//		order.child.add(new MenuModel(order, "택배사별출고리스트출력(화면)", ""));
//		order.child.add(new MenuModel(order, "기간판매현황", ""));
//
//		root.child.add(product);
//		root.child.add(order);
//		viewer.setInput(root);

	}

	private void processCommand(String menuID) {
		System.out.println("** command ID :" + menuID + "** instanceNum :" + Integer.toString(instanceNum));

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		String activeID = page.getActivePart().getSite().getId();
		IViewPart introView = window.getActivePage().findView("org.eclipse.ui.internal.introview");
		if (introView != null)
			window.getActivePage().hideView(introView);

//		IViewPart introView2 = window.getActivePage().findView("org.eclipse.ui.console.ConsoleView");
//		if (introView2 != null)
//			window.getActivePage().hideView(introView2);
//		window.getActivePage().activate(introView2);
		IWorkbenchPage page = window.getActivePage();
//		window.setActivePage(page);
		IPerspectiveDescriptor[] openper = page.getOpenPerspectives();
		IViewPart[] viewpart = window.getActivePage().getViews();
//		IPerspectiveDescriptor perspective = page.getPerspective();
//		final IWorkbenchPage page = getSite().getWorkbenchWindow().getActivePage();
//		page.getWorkingSet();

//		perspective.

		window.getActivePage().activate(viewpart[2]);
//		window.setActivePage(page);
//		final IWorkbenchPage activePage = window.getActivePage();
//		try {
//			final View view = (View) activePage.showView(menuID);
//			activePage.activate(view);
//		} catch (PartInitException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			window.getActivePage().showView(menuID, Integer.toString(instanceNum++), IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
		}

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}