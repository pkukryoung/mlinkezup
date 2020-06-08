package com.kdj.mlink.ezup3.data.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;



public class MyXLSXExcelManagerImple extends AbstractDataManager implements MyExcelManager {

	private Workbook wkbook;

	public Workbook getWkbook() {
		return wkbook;
	}

	public MyXLSXExcelManagerImple(String fileURI) throws FileNotFoundException {
		super(fileURI);
	}

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
			for (Cell cell : row) {
				String cellContent = "";
				switch (cell.getCellType()) {				
				case Cell.CELL_TYPE_BOOLEAN:
					cellContent = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellContent = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_STRING:
					cellContent = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						cellContent = sFormat.format(cell.getDateCellValue());
					} else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellContent = cell.getRichStringCellValue().toString();
					}
					break;					
				default:
					
				}
				rowContent.add(cellContent);
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
			wkbook = new XSSFWorkbook(inStream);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	@Override
	public void saveData() throws IOException {
		this.fitColumnToContent();
		this.fitSheetToOnePage();
		FileOutputStream fileOut = new FileOutputStream(uriStr);
		wkbook.write(fileOut);
		fileOut.close();
	}
	

	@Override
	public List<String> getHeaderRowContents() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public void setHeaderRowContents(Sheet sheet, List<String> headerRowContents) {
		if (wkbook == null) {
			this.createWorkbook();
		}

	
		Row headerRow = sheet.createRow(0);
		CellStyle headerCellStyle = this.getHeaderCellStyle();
		int cellNo = 0;

		for (String cellContent : headerRowContents) {
			Cell cell = headerRow.createCell(cellNo);
			cell.setCellValue(cellContent);
			cell.setCellStyle(headerCellStyle);
			cellNo++;
		}

	}

	
	@Override
	public int setSheetContents(int sheetNo, int startRowNo, List<List<String>> sheetContent) {
		Sheet sheet = this.createSheet("sheet-" + sheetNo);
		return this.setSheetContents(sheet, startRowNo, sheetContent);
	}

	private int setSheetContents(Sheet sheet, int startRowNo, List<List<String>> sheetContent) {
		CellStyle headerCellStyle = this.getHeaderCellStyle();
		CellStyle contentCellStyle = this.getContentCellStyle();
		int rowNo = startRowNo;
		for (List<String> rowContents : sheetContent) {			
			Row row = sheet.createRow(rowNo);
			int cellNo = 0;
			for (String cellContents : rowContents) {
				Cell cell = row.createCell(cellNo);
				cell.setCellValue(cellContents);
				if (rowNo == 0) {
					cell.setCellStyle(headerCellStyle);
				} else {
					cell.setCellStyle(contentCellStyle);
				}
				cellNo++;
			}
			rowNo++;
		}

		return rowNo;
	}

	private Sheet createSheet(String sheetName) {

		if (wkbook == null)
			this.createWorkbook();

		Sheet sheet = null;

		try {
			sheet = wkbook.createSheet(sheetName);
		} catch (IllegalArgumentException e) {
			sheet = wkbook.getSheet(sheetName);
		}

		return sheet;
	}

	private void createWorkbook() {
		wkbook = new XSSFWorkbook();

	}

	@Override
	public int setSheetContents(String sheetName, int startRowNo, List<List<String>> sheetContent) {
		Sheet sheet = this.createSheet(sheetName);
		return this.setSheetContents(sheet, startRowNo, sheetContent);
	}

	
	@Override
	public int setSheetContentsWithHeaderRowContents(int sheetNo, List<List<String>> sheetContent) {
		return this.setSheetContents(sheetNo, 0, sheetContent);
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

		for (int i = 0; i < col; i++) {
			String columnHeaderName = viewer.getTable().getColumn(i).getText();
			columnHeaderNames.add(columnHeaderName);
		}

		this.setHeaderRowContents(sheet, columnHeaderNames);

		CellStyle contentsCellStyle = this.getContentCellStyle();
		for (int i = 0; i < items.length; i++) {
			TableItem item = items[i];
			Row row = sheet.createRow((short) i + 1);
			for (int j = 0; j < col; j++) {
				String b = item.getText(j);
				Cell cell = row.createCell((short) j);
				cell.setCellValue(b);
				cell.setCellStyle(contentsCellStyle);
			}

		}
		return items.length+1;
	}

	private CellStyle getHeaderCellStyle() {
		CellStyle cellStyle = wkbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(cellStyle.SOLID_FOREGROUND);
		Font font = wkbook.createFont();
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		font.setFontName("맑은 고딕");
		cellStyle.setFont(font);
		return cellStyle;
	}

	private CellStyle getContentCellStyle() {
		CellStyle cellStyle = wkbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setFillPattern(cellStyle.SOLID_FOREGROUND);
		cellStyle.setWrapText(true);
		return cellStyle;
	}

	@Override
	public void setSheetContentsWithHeaderRowContents(String sheetName, List<List<String>> sheetContent) {
		this.setSheetContents(sheetName, 0, sheetContent);
	}

	
	@Override
	public List<List<String>> getSheetContents(String sheetName, int startRowNo) {
		return this.getSheetContents(wkbook.getSheetIndex(sheetName), startRowNo);
	}

	private void fitColumnToContent() {
		for (int i = 0; i < this.wkbook.getNumberOfSheets(); i++) {
			Sheet sheet = this.wkbook.getSheetAt(i);
			if (sheet != null) {
				Row firstRow = sheet.getRow(0);
				for (Iterator iterator = firstRow.iterator(); iterator.hasNext();) {
					Cell cell = (Cell) iterator.next();
					sheet.autoSizeColumn(cell.getColumnIndex());
				}
			}

		}

	}

	private void fitSheetToOnePage() {
		for (int i = 0; i < this.wkbook.getNumberOfSheets(); i++) {
			Sheet sheet = this.wkbook.getSheetAt(i);
			PrintSetup ps = sheet.getPrintSetup();
			sheet.setAutobreaks(true);
			ps.setFitHeight((short) 1);
			ps.setFitWidth((short) 1);
			ps.setLandscape(true);
		}
	}
}
