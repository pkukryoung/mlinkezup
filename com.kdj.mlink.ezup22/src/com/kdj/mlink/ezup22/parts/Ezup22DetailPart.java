package com.kdj.mlink.ezup22.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite; 

public class Ezup22DetailPart {

	@PostConstruct
	public void createControls(Composite parent) {
	    System.out.println(this.getClass().getSimpleName()
	    + " @PostConstruct method called.");
	} 
}
