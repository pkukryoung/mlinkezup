package com.kdj.mlink.ezup3.ui;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class CSelectionAdapter implements SelectionListener {
	
	public String type = "";
	public String value = "";
	
	public  CSelectionAdapter(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		e.data  = String.format("%s|%s", type, value ); 
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
