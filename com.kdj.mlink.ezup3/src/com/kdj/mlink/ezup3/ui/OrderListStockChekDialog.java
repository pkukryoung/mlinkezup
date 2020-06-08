package com.kdj.mlink.ezup3.ui;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class OrderListStockChekDialog extends CommandDialog {
	
	ProgressBar progressBar;		
	Hashtable<String, List<List<String>>> h_pordcd; 
	//List<List<String>> sheetContents;
	String orddt;
	char dateType;
	int orderCount = 0;
	
	
	public OrderListStockChekDialog(Shell parentShell, Hashtable<String, List<List<String>>> h_pordcd,
			int orderCount, String orddt, char type) {
		super(parentShell);
		this.h_pordcd = h_pordcd;
		this.orderCount = orderCount;
		this.orddt = orddt;
		this.dateType = type;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));
		Label lblNewLabel = new Label(container, SWT.NONE);
		
		if(this.dateType=='S'){
			int prodcdSize = h_pordcd.size();
			//String typeStockMsg =" "+ orderCount + " 주문에 대한 " + prodcdSize+"개 상품의 재고를 체크하고 있습니다.";
			String typeStockMsg = " 주문상품의 재고를 파악하고 있습니다.";
			lblNewLabel.setText(typeStockMsg);
		}
		
		progressBar = new ProgressBar(container, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		startAnalizeThread();
		
		return container;
	}
	
	private void startAnalizeThread() {		
		if(this.dateType=='S') {//상품재고
			OrderListStockCheckThread thread = new OrderListStockCheckThread(this,  progressBar, h_pordcd,  orddt);		
			thread.start();
		}
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// --  No delete
	}

	
	@Override
	protected void configureShell(Shell newShell) {
		if(this.dateType=='S') {
			newShell.setText("상품재고파악");
		}
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(571, 175);
	}
}
