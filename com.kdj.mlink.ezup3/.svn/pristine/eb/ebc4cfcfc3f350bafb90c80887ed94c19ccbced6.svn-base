package com.kdj.mlink.ezup3.data.excel;

import java.io.FileNotFoundException;
import java.io.IOException;



public abstract class AbstractDataManager {
		

	protected String uriStr ;	

	
	public AbstractDataManager(String uri) throws FileNotFoundException{
		this.uriStr = uri;
	}

	public abstract void loadData() throws FileNotFoundException;	
	public abstract void saveData() throws IOException;
	
	
}
