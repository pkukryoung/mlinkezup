package com.kdj.mlink.ezup3.ui;

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

public class SBNetOrderReceiveDialog extends CommandDialog {

	ProgressBar progressBar;
	String responseData;
	List<List<String>> sheetContents;
	char dateType;
	String typeOrderMsg = "사방넷 주문데이터를 가져오고 있습니다.";
	String typeClaimMsg = "사방넷 클레임데이터를 가져오고 있습니다.";

	public SBNetOrderReceiveDialog(Shell parentShell, String responseData, List<List<String>> sheetContents,
			char type) {
		super(parentShell);
		this.responseData = responseData;
		this.sheetContents = sheetContents;
		this.dateType = type;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));
		Label lblNewLabel = new Label(container, SWT.NONE);
		if (this.dateType == 'O') {
			lblNewLabel.setText(typeOrderMsg);
		} else if (this.dateType == 'C') {
			lblNewLabel.setText(typeClaimMsg);
		}

		progressBar = new ProgressBar(container, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		startAnalizeThread();

		return container;
	}

	private void startAnalizeThread() {

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// -- No delete
	}

	@Override
	protected void configureShell(Shell newShell) {
		if (this.dateType == 'O') {
			newShell.setText("사방넷 주문업로드");
		} else if (this.dateType == 'C') {
			newShell.setText("사방넷 클레임다운");
		}

		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(571, 175);
	}
}
