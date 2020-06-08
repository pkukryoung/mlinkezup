
package com.kdj.mlink.ezup3.data.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

public class MyExcelManagerImple extends AbstractDataManager implements MyExcelManager {

	private Workbook wkbook;

	public Workbook getWkbook() {
		return wkbook;
	}

	public MyExcelManagerImple(String fileURI) throws FileNotFoundException {
		super(fileURI);
	}

	private void createWorkbook() {
		wkbook = new HSSFWorkbook();

	}
	
	
	private List<String> getHeader(Sheet sheet, int headerIndex){
		List<String> headers = new ArrayList<>();
		Row row = sheet.getRow(headerIndex);
		
		for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++) {
			Cell cell = row.getCell(cellNumber, Row.CREATE_NULL_AS_BLANK);
			String cellValue = "";
			switch (cell.getCellType()) {				
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					cellValue = sFormat.format(cell.getDateCellValue());
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getRichStringCellValue().toString();
				}
				break;					
			default:
			}
			headers.add(cellValue);
		}
		return headers;
	}
		

   
	public  Map<Integer, HashMap<String, String>> getSheetMapContents(int sheetNumber, int headerRowNumber) {

		Sheet sheet = wkbook.getSheetAt(sheetNumber);
		//List<List<String>> sheetContents = new ArrayList<List<String>>();
		 Map<Integer, HashMap<String, String>> sheetContents = new HashMap<Integer, HashMap<String, String>>();
		if (sheet == null) {
			return null;
		}
		
		//  헤더 셋팅.. 
	   List<String> headers =getHeader(sheet, headerRowNumber);  // 헤더를 가져온다. 
	    Integer cnt = 0;
		for (Row row : sheet) {
			if (row.getRowNum() <= headerRowNumber) {
				continue;
			}
			
			Map<String, String>  rowContent =   new HashMap<String, String>();
			for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++) {
				Cell cell = row.getCell(cellNumber, Row.CREATE_NULL_AS_BLANK);
				String cellValue = "";
				switch (cell.getCellType()) {				
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						cellValue = sFormat.format(cell.getDateCellValue());
					} else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.getRichStringCellValue().toString();
					}
					break;					
				default:
				}
				rowContent.put(headers.get(cellNumber), cellValue);
			}
			sheetContents.put(cnt, (HashMap<String, String>) rowContent);
			cnt++;
		}
		
		return sheetContents;
	}
	
	
	
	@Override
	public List<List<String>> getSheetContents(int sheetNumber, int startRowNumber) {

		Sheet sheet = wkbook.getSheetAt(sheetNumber);
		List<List<String>> sheetContents = new ArrayList<List<String>>();

		if (sheet == null) {
			return null;
		}

		for (Row row : sheet) {
			if (row.getRowNum() < startRowNumber) {
				continue;
			}
			
			List<String> rowContent = new ArrayList<String>();
			
			for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++) {
				Cell cell = row.getCell(cellNumber, Row.CREATE_NULL_AS_BLANK);
				String cellValue = "";
				switch (cell.getCellType()) {				
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						cellValue = sFormat.format(cell.getDateCellValue());
					} else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.getRichStringCellValue().toString();
					}
					break;					
				default:
				}
				rowContent.add(cellValue);
			}
			sheetContents.add(rowContent);
		}
		
		return sheetContents;
	}
	
	@Override
	public void loadData() throws FileNotFoundException {
		String fileName = super.uriStr;
		InputStream inStream = new FileInputStream(fileName);
		try {
			wkbook = WorkbookFactory.create(inStream);
		} catch (IOException e) {
			wkbook = null;
			e.printStackTrace();
		} catch (Exception e) {
			wkbook = null;
			e.printStackTrace();
		}
	}

	@Override
	public void saveData() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(uriStr);
		wkbook.write(fileOut);
		fileOut.close();
	}

	@Override
	public List<String> getHeaderRowContents() {		
		return null;
	}

	@Override
	public void setHeaderRowContents(Sheet sheet, List<String> headerRowContents) {

	}

	@Override
	public int setSheetContents(int sheetNo, int startRowNo, List<List<String>> sheetContent) {

		Sheet sheet = null;
		
		if (wkbook == null) {
			this.createWorkbook();
		}		

		try {
			sheet = wkbook.getSheetAt(sheetNo);
		} catch (IllegalArgumentException e) {
			sheet = wkbook.createSheet("sheet-1");
		}

		int rowNo = startRowNo;
		for (List<String> rowContents : sheetContent) {
			Row row = sheet.createRow((short) startRowNo);
			short cellNumber = 0;
			for (String cellValue : rowContents) {
				Cell cell = row.createCell(cellNumber);
				cell.setCellValue(cellValue);
				cellNumber++;
			}
			rowNo++;
		}

		return rowNo;
	}

	
	@Override
	public void setSheetContentsWithHeaderRowContents(String sheetName, List<List<String>> voSheetContents) {		

	}

	@Override
	public List<List<String>> getSheetContents(String sheetName, int startRowNo) {		
		return null;
	}

	@Override
	public int setSheetContents(String sheetName, int startRowNo, List<List<String>> sheetContent) {		
		return 0;
	}

	@Override
	public int setSheetContentsWithHeaderRowContents(int sheetNo, List<List<String>> sheetContent) {		
		return 0;
	}

	@Override
	public int setSheetContents(TableViewer viewer) {

		if (wkbook == null)
			this.createWorkbook();

		Sheet sheet;
		try {
			sheet = wkbook.createSheet("sheet-1");
		} catch (IllegalArgumentException e) {
			sheet = wkbook.getSheet("sheet-1");
		}

		TableItem[] items = viewer.getTable().getItems();
		int col = viewer.getTable().getColumnCount();

		List<String> columnHeaderNames = new ArrayList<String>();

		for (int i = 1; i < col; i++) {
			String columnHeaderName = viewer.getTable().getColumn(i).getText();
			columnHeaderNames.add(columnHeaderName);
		}

		this.setHeaderRowContents(sheet, columnHeaderNames);

		for (int i = 0; i < items.length; i++) {
			TableItem item = items[i];
			Row row = sheet.createRow((short) i);
			for (int j = 1; j < col; j++) {
				String b = item.getText(j);
				Cell cell = row.createCell((short) j);
				cell.setCellValue(b);
			}

		}

		return items.length + 1;
	}

	private Cell createCell(String cellContent) {

		return null;
	}

	

}
