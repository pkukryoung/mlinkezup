package com.kdj.mlink.ezup;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;


public class StatusLineContribution2 extends ContributionItem {
	
	public StatusLineContribution2(String id) {
	    super(id);
	}

	@Override
	public void fill(Composite parent) {
	
		Label label=new Label(parent,SWT.NONE);
	    label.setText("LABEL");
	    Text text=new Text(parent, SWT.BORDER);
	    text.setText("TEXT");
	    Combo combo=new Combo(parent,SWT.NONE);
	    combo.setText("COMBO");
		
    }

}
