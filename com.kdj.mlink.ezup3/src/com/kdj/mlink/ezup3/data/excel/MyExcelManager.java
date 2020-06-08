
package com.kdj.mlink.ezup3.data.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.jface.viewers.TableViewer;


public interface MyExcelManager extends MyDataManager {
	public int setSheetContents(TableViewer viewer);
	public int setSheetContents(int sheetNo, int startRowNo,List<List<String>> sheetContent);	
	public int setSheetContents(String sheetName, int startRowNo,List<List<String>> sheetContent);
	public int setSheetContentsWithHeaderRowContents(int sheetNo, List<List<String>> sheetContent);
	public void setSheetContentsWithHeaderRowContents(String sheetName, List<List<String>> voSheetContents);
	public List<List<String>> getSheetContents(int sheetNo, int startRowNo);	
	public List<List<String>> getSheetContents(String sheetName, int startRowNo);
	public List<String> getHeaderRowContents();
	public void setHeaderRowContents(Sheet sheet, List<String> headerRowContents);
	public Workbook getWkbook();
	public  Map<Integer, HashMap<String, String>> getSheetMapContents(int sheetNumber, int headerRowNumber);
}
