package com.kdj.mlink.ezup22.handlers;

import org.eclipse.e4.core.di.annotations.Execute; 

public class RemoveTodoHandler {
	@Execute
	public void execute() {
	      System.out.println((this.getClass().getSimpleName() + " called"));
	    } 
	
}
