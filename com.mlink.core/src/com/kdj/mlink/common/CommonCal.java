package com.kdj.mlink.common;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CommonCal extends CommandDialog {
	
	String TITLE = "날짜선택";
	
	Text opener;
	
	public CommonCal(Text opener) {
		super(opener.getShell());
		this.opener = opener;
	
		
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));
		
		DateTime dateTime = new DateTime(container, SWT.CALENDAR);
		dateTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String dateStr = YDMATimeUtil.getRoRddtDate(dateTime);
				opener.setText(dateStr);
				cancelPressed();
			}
		});
		
		return container;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {

					
	}
	
	@Override
	protected void configureShell(Shell newShell) {	
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(262, 266);
	}
}
