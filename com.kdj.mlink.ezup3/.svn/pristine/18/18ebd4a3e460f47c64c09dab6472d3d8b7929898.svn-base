package com.kdj.mlink.ezup3.ui;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class ProcdParseDialog extends CommandDialog {
	
	
	ProgressBar progressBar;
	List<List<String>> sheetContents;
	private Text txt_info;
	
	public ProcdParseDialog(Shell parentShell, List<List<String>> sheetContents) {
		super(parentShell);
		this.sheetContents = sheetContents;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("주문내역 분석중...");
		
		progressBar = new ProgressBar(container, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		txt_info = new Text(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		txt_info.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_info.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		txt_info.setVisible(false);
		
		startAnalizeThread();
		
		return container;
	}
	
	private void startAnalizeThread() {		
		ProdcdParserThread thread =	new ProdcdParserThread(this, progressBar, sheetContents);		
		thread.start();
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// --  No delete
	}

	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("주문업로드");
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(571, 175);
	}
}
