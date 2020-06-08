
package com.kdj.mlink.ezup3.data.excel;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface MyDataManager {
	public abstract void saveData() throws IOException;	
	public abstract void loadData() throws FileNotFoundException;
}
