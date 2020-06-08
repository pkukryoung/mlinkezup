package com.kdj.mlink.ezup3.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
//import java.util.Calendar;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.HousingDao;
import com.kdj.mlink.ezup3.data.dao.OrderDao;
import com.kdj.mlink.ezup3.data.dao.PostDao;
import com.kdj.mlink.ezup3.data.excel.MyDataManagerFactory;

/**
 * 엑셀의 내용을 체크하여 알려줌
 *
 * @author kdj01 2020-01-31 update by Mac
 *
 */
public class OrderListManager extends Composite {

	public static String ID = "com.kdj.mlink.ezup3.command.OrderListManage.ID";

	String[][] column_name = { { "No.", "50" }, { "주문일시", "150" }, { "수취인명", "80" }, { "우편번호", "80" }, { "주소", "450" },
			{ "핸드폰", "130" }, { "일반전화", "130" }, { "수량", "50" }, { "배송비", "70" }, { "신용", "50" }, { "옵션(수집)", "400" },
			{ "상품코드", "120" }, { "메시지", "260" }, { "포장구분", "80" }, { "배송구분", "80" }, { "사방넷주문번호", "130" },
			{ "쇼핑몰아이디", "180" }, { "주문자명(예금주)", "150" }, { "기타메시지", "150" }, { "자사몰코드", "150" }, { "주문합계", "80" },
			{ "이카운트전송", "110" } };

	public static String TITLE = "주문내역관리";

	public String key_orddt;
	public String key_seq;
	public int selectedIdx = -1;
	String orderListFullPath;

	Composite composite_table;
	public Grid table;
	GridTableViewer tableViewer;
	public DateTime dt_orddt;
	public Combo cb_ordseq;

	Text txt_search_condition;
	Combo cb_option;
	int Excelcount = 0;
	JFrame frame;
	JProgressBar bar;
	Container container;
	Toolkit tk;
	Dimension screen;

	public OrderListManager(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		composite.setLayout(gl_composite);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(19, false);
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("업로드일자");

		dt_orddt = new DateTime(composite_1, SWT.BORDER);
		dt_orddt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.horizontalIndent = 10;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("차수");

		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.READ_ONLY);
		cb_ordseq = comboViewer.getCombo();
		GridData gd_cb_ordseq = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cb_ordseq.widthHint = 25;
		cb_ordseq.setLayoutData(gd_cb_ordseq);
		cb_ordseq.setItems(new String[] { "1", "2", "3", "4", "5", "전체" });
		cb_ordseq.select(0);

		Button btn_search = new Button(composite_1, SWT.NONE);
		btn_search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btn_search.setToolTipText("주문내역가져오기");
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getOrderList();
			}
		});

		btn_search.setBounds(0, 0, 93, 30);
		btn_search.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/checkout.gif"));

		ComboViewer comboViewer_1 = new ComboViewer(composite_1, SWT.READ_ONLY);
		cb_option = comboViewer_1.getCombo();
		cb_option.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		cb_option.setItems(new String[] { "상품코드", "상품명", "수취인명", "주문자명" });
		cb_option.select(0);

		txt_search_condition = new Text(composite_1, SWT.BORDER);
		txt_search_condition.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				// boolean isKor = txt_search_condition.getShell().getImeInputMode() ==
				// SWT.NATIVE;
				txt_search_condition.getShell().setImeInputMode(SWT.ALPHA | SWT.PHONETIC);
			}
		});
		txt_search_condition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					int opt = cb_option.getSelectionIndex();
					getOrderListByOption(opt, txt_search_condition.getText().trim());
				}
			}
		});
		GridData gd_txt_search_condition = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_txt_search_condition.widthHint = 200;
		txt_search_condition.setLayoutData(gd_txt_search_condition);
		txt_search_condition.setBounds(0, 0, 73, 26);

		Button btn_search_order = new Button(composite_1, SWT.NONE);
		btn_search_order.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int opt = cb_option.getSelectionIndex();
				getOrderListByOption(opt, txt_search_condition.getText().trim());

			}
		});
		btn_search_order.setToolTipText("검색하기");
		btn_search_order.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		btn_search_order.setBounds(0, 0, 93, 30);
		btn_search_order.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btn_add = new Button(composite_1, SWT.NONE);
		GridData gd_btn_add = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btn_add.widthHint = 95;
		btn_add.setLayoutData(gd_btn_add);
		btn_add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openOrderDetail(true);
			}
		});
		btn_add.setText(" 주문추가 ");
		btn_add.setToolTipText("주문추가");
		btn_add.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/testable.png"));

		Button btn_del = new Button(composite_1, SWT.NONE);
		GridData gd_btn_del = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btn_del.widthHint = 95;
		btn_del.setLayoutData(gd_btn_del);
		btn_del.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				deleteOrder();
			}
		});
		btn_del.setText(" 주문삭제 ");
		btn_del.setToolTipText("주문삭제");
		btn_del.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/delete.png"));

		Button btn_excell = new Button(composite_1, SWT.NONE);
		GridData gd_btn_excell = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btn_excell.widthHint = 95;
		btn_excell.setLayoutData(gd_btn_excell);
		btn_excell.setText("엑셀저장");
		btn_excell.setToolTipText("엑셀저장");
		btn_excell.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsExcel();
			}
		});
		btn_excell.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/excel.jpg"));

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 95;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkStock();
			}
		});
		btnNewButton.setText("재고확인");
		btnNewButton.setToolTipText("재고확인");
		btnNewButton.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/console_view.gif"));

		Button btn_excelIN = new Button(composite_1, SWT.NONE);
		GridData gd_btn_excelIN = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btn_excelIN.widthHint = 95;
		btn_excelIN.setLayoutData(gd_btn_excelIN);
		btn_excelIN.setText("인수출력");
		btn_excelIN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsExcelInsu();
			}
		});
		btn_excelIN.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/excel.jpg"));
		btn_excelIN.setToolTipText("인수증출력하기");

		Button btnPost = new Button(composite_1, SWT.NONE);
		GridData gd_btnPost = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnPost.widthHint = 95;
		btnPost.setLayoutData(gd_btnPost);
		btnPost.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sumerizePost();
			}
		});
		btnPost.setText("우편모음");
		btnPost.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/upload.gif"));
		btnPost.setToolTipText("우편번호모으기");
		Button btnPostMod = new Button(composite_1, SWT.NONE);
		GridData gd_btnPostMod = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnPostMod.widthHint = 95;
		btnPostMod.setLayoutData(gd_btnPostMod);
		btnPostMod.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modifyPost();
			}
		});
		btnPostMod.setText("우편변경");
		btnPostMod.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
		btnPostMod.setToolTipText("우편번호 자동변경");

		Button btnTransOrd1 = new Button(composite_1, SWT.NONE);
		GridData gd_btnTransOrd1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnTransOrd1.widthHint = 95;
		btnTransOrd1.setLayoutData(gd_btnTransOrd1);
		btnTransOrd1.setText("1일이월");
		btnTransOrd1.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
		btnTransOrd1.setToolTipText("1일후로 이월");

		btnTransOrd1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				transferOrder("1");
			}
		});

		Button btnTransOrd2 = new Button(composite_1, SWT.NONE);
		GridData gd_btnTransOrd2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnTransOrd2.widthHint = 95;
		btnTransOrd2.setLayoutData(gd_btnTransOrd2);
		btnTransOrd2.setText("2일이월");
		btnTransOrd2.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
		btnTransOrd2.setToolTipText("2일후로 이월");
		btnTransOrd2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				transferOrder("2");
			}
		});

		Button btnTransOrd = new Button(composite_1, SWT.NONE);
		GridData gd_btnTransOrd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnTransOrd.widthHint = 95;
		btnTransOrd.setLayoutData(gd_btnTransOrd);
		btnTransOrd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				transferOrder("3");
			}
		});
		btnTransOrd.setText("3일이월");
		btnTransOrd.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
		btnTransOrd.setToolTipText("3일후로 이월");

//		Button btn_upload = new Button(composite_1, SWT.NONE);
//		btn_upload.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				upLoadOrder();
//			}
//		});
//		btn_upload.setText(" 저장 ");

		composite_table = new Composite(composite, SWT.NONE);
		composite_table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridLayout gl_composite_table = new GridLayout(1, false);
		gl_composite_table.verticalSpacing = 0;
		gl_composite_table.marginWidth = 0;
		gl_composite_table.marginHeight = 0;
		composite_table.setLayout(gl_composite_table);
		composite_table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_table.setBounds(0, 0, 64, 64);

		tableViewer = new GridTableViewer(composite_table, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		tableViewer.addDoubleClickListener(event -> openOrderDetail(false));
		tableViewer.addSelectionChangedListener(event -> {
			// selectedRowNoMst = table.getSelectionIndex();

		});
		table = tableViewer.getGrid();
		table.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (int i = 0; i < column_name.length; i++) {
			String[] column = column_name[i];
			GridColumn tableViewerColumn_x = new GridColumn(table, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

			if (i == 4 || i == 5 || i == 6 || i == 10 || i == 11 || i == 12 || i == 16 || i == 18 || i == 19) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}

		}

		tableViewer.setContentProvider(new MyContentProvider());
		tableViewer.setLabelProvider(new MyLableProvider());

		table.setVisible(false);

	}

	private void saveAsExcel() {

		if (tableViewer.getInput() == null || ((List<List<String>>) tableViewer.getInput()).size() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "엑셀파일로 저장할 주문내역이 없습니다.");
			return;
		}

		FileDialog fileSelectionDialog = MyDataManagerFactory.createExcelFileSaveDialog();
		String fileSavePath = fileSelectionDialog.open();
		if (fileSavePath == null) {
			return;
		}

		try {
			printExpPickupList(key_orddt, key_seq, fileSavePath);
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	private int printExpPickupList(String dateStr, String seq, String fileSavePath) throws Exception {

		List<List<String>> sheetContents = (List<List<String>>) tableViewer.getInput();

//		String newfile = YDMASessonUtil.getAppPath()+"\\YDwmsData\\" + expfilecd + ".xlsx";
//		newfile = newfile.replace(".xlsx", "_" + dateStr + "_" + seq + ".xlsx");

		String newfile = fileSavePath;

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFColor color_fail = new XSSFColor();
		color_fail.setIndexed(IndexedColors.RED.getIndex());

		XSSFColor color_pass = new XSSFColor();
		color_pass.setIndexed(IndexedColors.BLUE.getIndex());

		XSSFColor color_normal = new XSSFColor();
		color_normal.setIndexed(IndexedColors.WHITE.getIndex());

		XSSFCellStyle cs_left = workbook.createCellStyle();
		cs_left.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		cs_left.setFillForegroundColor(color_normal);
		cs_left.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle cs_right = workbook.createCellStyle();
		cs_right.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		cs_right.setFillForegroundColor(color_normal);
		cs_right.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle cs_center = workbook.createCellStyle();
		cs_center.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_center.setFillForegroundColor(color_normal);
		cs_center.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		Sheet sheet = workbook.createSheet("주문내역");

		if (sheet == null) {
			return -1;
		}

		Row row_head = sheet.createRow(0);

		for (int i = 0; i < column_name.length; i++) {
			Cell cell = row_head.createCell(i);
			cell.setCellValue(column_name[i][0]);
			sheet.setColumnWidth(i, Integer.parseInt(column_name[i][1]) * 10 * 3);
			cell.setCellStyle(cs_center);
		}

		int rowIdx = 1;
		OrderDao oddao = new OrderDao();
		for (List<String> rowContents : sheetContents) {
			Row row = sheet.createRow(rowIdx);
			for (int i = 0; i < column_name.length; i++) {
				Cell cell = row.createCell(i);
				if (oddao.isPhonenumber(rowContents.get(5))) {

					String value = rowContents.get(6);
					cell.setCellValue(value);
				}
				String value = rowContents.get(i);
				cell.setCellValue(value);

				if (i == 7 || i == 8 || i == 19) {
					cell.setCellStyle(cs_right);
				} else if (i == 2 || i == 4 || i == 10 || i == 13 || i == 16 || i == 17 || i == 18 || i == 19
						|| i == 20) {
					cell.setCellStyle(cs_left);
				} else {
					cell.setCellStyle(cs_center); // default
				}
			}

			rowIdx++;
		}

		FileOutputStream fileOut = new FileOutputStream(newfile);
		workbook.write(fileOut);
		fileOut.close();

		System.out.println(newfile + " 주문내역 생성 종료");

		MessageDialog.openInformation(getShell(), TITLE, "주문내역을 엑셀파일로 저장하였습니다.\n" + fileSavePath);

		return 0;

	}

	private void getOrderListByOption(int opt, String optStr) {

		if (optStr.length() != 0 && optStr.length() < 2) {
			// 검색어가 있는데 2보다 작은 경우
			MessageDialog.openInformation(getShell(), TITLE, "검색어를 2글자이상 입력해주세요");
			return;
		}
		try {
			List<List<String>> contents = new ArrayList<>();
			key_orddt = YDMATimeUtil.getOrddtDate(dt_orddt);
			key_seq = cb_ordseq.getItem(cb_ordseq.getSelectionIndex());
			OrderDao dao = new OrderDao();
			if (key_seq.equals("전체")) {
				List<List<String>> contents1 = dao.getOrderListForOrderManageByCondition(key_orddt, "1", opt, optStr);
				List<List<String>> contents2 = dao.getOrderListForOrderManageByCondition(key_orddt, "2", opt, optStr);
				List<List<String>> contents3 = dao.getOrderListForOrderManageByCondition(key_orddt, "3", opt, optStr);
				for (List<String> list1 : contents1) {
					contents.add(list1);
				}
				for (List<String> list2 : contents2) {
					contents.add(list2);
				}
				for (List<String> list3 : contents3) {
					contents.add(list3);
				}
			} else {
				contents = dao.getOrderListForOrderManageByCondition(key_orddt, key_seq, opt, optStr);
			}

			this.tableViewer.setInput(null);
			this.tableViewer.setInput(contents);
			this.tableViewer.refresh();
			if (!this.table.isVisible()) {
				this.table.setVisible(true);
			}
			this.selectedIdx = -1; // Row 선택 초기화

			if (contents == null || contents.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "조건에 맞는  주문이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}
	}

	public void getOrderList() {

		try {
			List<List<String>> contents = new ArrayList<>();
			key_orddt = YDMATimeUtil.getOrddtDate(dt_orddt);
			key_seq = cb_ordseq.getItem(cb_ordseq.getSelectionIndex());

			OrderDao dao = new OrderDao();
			if (key_seq.equals("전체")) {
				List<List<String>> contents1 = dao.getOrderListForOrderManage(key_orddt, "1");
				List<List<String>> contents2 = dao.getOrderListForOrderManage(key_orddt, "2");
				List<List<String>> contents3 = dao.getOrderListForOrderManage(key_orddt, "3");
				for (List<String> list1 : contents1) {
					contents.add(list1);
				}
				for (List<String> list2 : contents2) {
					contents.add(list2);
				}
				for (List<String> list3 : contents3) {
					contents.add(list3);
				}
				if (!this.table.isVisible()) {
					this.table.setVisible(true);
				}
			} else {
				contents = dao.getOrderListForOrderManage(key_orddt, key_seq);
				if (!this.table.isVisible()) {
					this.table.setVisible(true);
				}
			}

			tableViewer.setInput(null);
			tableViewer.setInput(contents);
			table.setSelection(-1);
			tableViewer.refresh();

			if (contents.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "조건에 일치하는 주문내역이 없습니다.\n");
			} else {
				for (List<String> list : contents) {
					String ecountsendyn = list.get(21);
					if (ecountsendyn.equals("Y")) {

					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	public void setUploadDate(int year, int month, int day) {
		this.dt_orddt.setYear(year);
		this.dt_orddt.setMonth(month);
		this.dt_orddt.setDay(day);
	}

	public void setUploadSeq(int seq) {
		this.cb_ordseq.select(seq);
	}

	public void sumerizePost() {
		// TODO -- 그리드 데이타에서 우편번호 6자리를 5자리로 변경한다..

		try {

			List<List<String>> orderList = (List<List<String>>) this.tableViewer.getInput();

			if (orderList == null || orderList.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "우편번호 수정할 주문내역이 없습니다.");
				return;
			}

			int post6cnt = 0;
			List<List<String>> topContents = new ArrayList<>();
			for (List<String> list : orderList) {
				if (list.get(20).equals("0")) {
					topContents.add(list);
					continue;
				}

				if ("Y".equals(list.get(21)) == false // && ("천일택배".equals(list.get(13))||"건영택배".equals(list.get(13)))
				) {
					String postNo = list.get(3).replace("-", "");
					if (postNo.length() >= 6) {
						topContents.add(0, list);
						post6cnt++;
					} else {
						topContents.add(list);
					}
				} else {
					topContents.add(list);
				}

			}

			tableViewer.setInput(null);
			tableViewer.setInput(topContents);
			tableViewer.refresh();
			MessageDialog.openInformation(getShell(), TITLE,
					"수정이 가능한 주문내역중,  6자리 우편번호  총" + post6cnt + "건을 앞쪽으로 이동 하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	public void modifyPost() {
		// TODO -- 그리드 데이타에서 우편번호 6자리를 5자리로 변경한다..

		try {

			List<List<String>> orderList = (List<List<String>>) this.tableViewer.getInput();

			if (orderList == null || orderList.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "우편번호 수정할 주문내역이 없습니다.");
				return;
			}

			int post6cnt = 0;
			List<List<String>> topContents = new ArrayList<>();
			for (List<String> list : orderList) {
				if (list.get(20).equals("0")) {
					topContents.add(list);
					continue;
				}

				if ("Y".equals(list.get(21)) == false // && "천일택배".equals(list.get(13)) || "롯데택배".equals(list.get(13))
				) {
					String postNo = list.get(3).replace("-", "");
					if (postNo.length() == 6) {
						topContents.add(0, list);
						post6cnt++;
						System.out.println(list);
					} else {
						topContents.add(list);
					}
				} else {
					topContents.add(list);
				}

			}

			int month = dt_orddt.getMonth() + 1;
			String monthStr = Integer.toString(month);
			if (month < 10) {
				monthStr = "0" + monthStr;
			}
			int day = dt_orddt.getDay();
			String dayStr = Integer.toString(day);
			if (day < 10) {
				dayStr = "0" + dayStr;
			}
			String orddt = dt_orddt.getYear() + monthStr + dayStr;
			String ordseq = cb_ordseq.getText();

			PostDao dao = new PostDao();
			int post6val = 0;
			for (int i = 0; i < post6cnt; i++) {
				List<String> list = topContents.get(i);
				String postNo = list.get(3).replace("-", "");
				if (postNo.length() == 6) {
					String zipCode = dao.getPostNoChange(postNo, list.get(4));
					if (zipCode != "") {
						if (ordseq.equals("전체")) {
							ordseq = list.get(22);
						}
						int seqs = Integer.parseInt(list.get(0));
						dao.updateOrderPost(orddt, Integer.parseInt(ordseq), seqs, zipCode);
						list.set(3, zipCode); // 우편번호는은 3번째이 있음
						topContents.set(i, list);
						post6val++;
					}
				}
			}

			tableViewer.setInput(null);
			tableViewer.setInput(topContents);
			tableViewer.refresh();
			MessageDialog.openInformation(getShell(), TITLE,
					"수정이 가능한 주문내역중,  6자리 우편번호  총" + post6cnt + "건중 , " + post6val + "건을 수정하고 앞쪽으로 이동 하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	List<List<String>> orderList = null;

	void checkStock() {

		try {

			String orddt = YDMATimeUtil.getOrddtDate(dt_orddt);
			String ordseq = cb_ordseq.getItem(cb_ordseq.getSelectionIndex());
			List<List<String>> contents = new ArrayList<>();
			// 프로그래스바를 위한 리스트
			orderList = (List<List<String>>) this.tableViewer.getInput();
			// contents = (List<List<String>>) this.tableViewer.getInput();

			OrderDao dao = new OrderDao();
			if (ordseq.equals("전체")) {
				List<List<String>> contents1 = dao.getOrderListForOrderManage(key_orddt, "1");
				List<List<String>> contents2 = dao.getOrderListForOrderManage(key_orddt, "2");
				List<List<String>> contents3 = dao.getOrderListForOrderManage(key_orddt, "3");
				for (List<String> list1 : contents1) {
					contents.add(list1);
				}
				for (List<String> list2 : contents2) {
					contents.add(list2);
				}
				for (List<String> list3 : contents3) {
					contents.add(list3);
				}
				if (!this.table.isVisible()) {
					this.table.setVisible(true);
				}
			} else {
				contents = dao.getOrderListForOrderManage(orddt, ordseq);
			}

//			if(contents== null || contents.size()==0) {
//				MessageDialog.openInformation(getShell(), TITLE, "재고를 확인할 주문내역을 가져와야 합니다.");
//				return;
//			}

			if (!table.isVisible()) {
				table.setVisible(true);
			}

			if (contents.size() == 0) {

				tableViewer.setInput(null);
				tableViewer.setInput(contents);
				tableViewer.refresh();

				MessageDialog.openInformation(getShell(), TITLE, "조건에 일치하는 주문내역이 없습니다.\n");
				return;

			} else {
				System.out.println("contents-1: " + contents.size());
				// List<List<String>> checkContent = new ArrayList<List<String>>();

				Hashtable<String, List<List<String>>> h_pordcd = new Hashtable<>();

				for (List<String> list : contents) {

					if (list.get(20).equals("0") || (list.get(11).trim().length() == 0)) {
						continue;
					}
					// 주문금액의 합계가 0 이 아닌 것만 골라냄.
					// 상품코드별로 묶음.
					String prodcd = list.get(11);
					if (h_pordcd.get(prodcd) == null) {
						h_pordcd.put(prodcd, new ArrayList<List<String>>());
					}

					// 동일 상품코드에 대한 seq 을 묶음 -- 사방넷 주문번호가 좋으나 사방넷 주문번호 없는 경우 있음.

					h_pordcd.get(prodcd).add(list);
				}

				System.out.println("h_pordcd key 갯수: " + h_pordcd.size());

//				Enumeration enumKey = h_pordcd.keys();
//
//				while (enumKey.hasMoreElements()) {
//
//					String prodcdKey = (String) enumKey.nextElement();
//					int sotckCnt = dao.checkStock1(orddt, prodcdKey);
//
//					List<List<String>> listGroup = (List<List<String>>) h_pordcd.get(prodcdKey);
//
//					for (List<String> list : listGroup) {
//						list.add(""+sotckCnt);
//					}
//
//				}

				frame = new JFrame("상품코드로 3일이후 입고예정재고를 확인중...");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				bar = new JProgressBar();
				container = frame.getContentPane();
				container.add(bar, BorderLayout.CENTER);
				frame.setSize(570, 80);
				tk = Toolkit.getDefaultToolkit();
				screen = tk.getScreenSize();
				frame.setLocation(screen.width / 2 - 285, screen.height / 2 - 40);

				tableViewer.setInput(null);
				tableViewer.setInput(contents);
				tableViewer.refresh();

				frame.setVisible(false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	void openOrderDetail(boolean isNew) {

		if (tableViewer.getInput() == null) {
			// 주문을 추가할 테이블 UI 가 있어야 하므로
			MessageDialog.openInformation(getShell(), TITLE, "주문을 추가할 업로드 일자/차수의 주문 내역을 조회하세요.");
			return;
		}

		// 주문내역관리에서 상세창을 오픈시 주문내역을 조회한 일자와 차수(key_orddt, key_seq) 를 넘겨줘야 한다.

		String[] keys = { key_orddt, key_seq };
		if (!isNew) {
			// --수정
			selectedIdx = table.getSelectionIndex();
			List<List<String>> list = (List<List<String>>) this.tableViewer.getInput();
			List<String> order = list.get(selectedIdx);
			if (key_seq.equals("전체")) {
				keys[1] = order.get(22);
			}

			OrderDetailDialog d = new OrderDetailDialog(this.getShell(), keys, order, this);
			d.open();
		}

		else {
			// 신규입력
			selectedIdx = -1;
			if (key_seq.equals("전체")) {
				keys[1] = "3";
			}
			OrderDetailDialog d = new OrderDetailDialog(this.getShell(), keys, null, this);
			d.open();
		}

	}

	void deleteOrder() {

	}

	void saveAsExcelInsu() {

		int[] selectedRowNoMst = table.getSelectionIndices();
		if (selectedRowNoMst.length == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "엑셀파일로 출력할 주문을 선택하세요.");
			return;
		}

		FileDialog fileSelectionDialog = MyDataManagerFactory.createExcelFileSaveDialog();
		String fileSavePath = fileSelectionDialog.open();
		if (fileSavePath == null) {
			return;
		}

		try {

			List<List<String>> contents_excel = new ArrayList<>();
			List<List<String>> contents = (List<List<String>>) this.tableViewer.getInput();

			int[] seqs = new int[selectedRowNoMst.length];

			for (int i = 0; i < selectedRowNoMst.length; i++) {
				List<String> list = contents.get(selectedRowNoMst[i]);
				seqs[i] = Integer.parseInt(list.get(0));
				contents_excel.add(list);
			}

			printInsuList(contents_excel, fileSavePath);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}
	}

	private int printInsuList(List<List<String>> sheetContents, String fileSavePath) throws Exception {

		// templete file
		String insutempPath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("wareh.insuTempatePath"));
		String insutempFile = YDMAProperties.getInstance().getAppProperty("wareh.insuTempateFile");
		String templatefile = insutempPath + File.separator + insutempFile;

		// String templatefile =
		// YDMASessonUtil.getAppPath()+"\\YDwmsData\\04.WarehouseList\\template\\인수증.xlsx";

		// ------------------ 복사본 생성 -------------
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(templatefile));
		FileOutputStream fileOut = new FileOutputStream(fileSavePath);
		wb.write(fileOut);
		fileOut.close();
		// ------------------ 복사본 생성 완료!!-------------

		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileSavePath));
		Sheet sheet = workbook.getSheetAt(0);

		XSSFColor color_fail = new XSSFColor();
		color_fail.setIndexed(IndexedColors.RED.getIndex());

		XSSFColor color_pass = new XSSFColor();
		color_pass.setIndexed(IndexedColors.BLUE.getIndex());

		XSSFColor color_normal = new XSSFColor();
		color_normal.setIndexed(IndexedColors.WHITE.getIndex());

		XSSFCellStyle cs_left = workbook.createCellStyle();
		cs_left.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		cs_left.setFillForegroundColor(color_normal);
		cs_left.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle cs_right = workbook.createCellStyle();
		cs_right.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		cs_right.setFillForegroundColor(color_normal);
		cs_right.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle cs_center = workbook.createCellStyle();
		cs_center.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_center.setFillForegroundColor(color_normal);
		cs_center.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		String orddt = YDMATimeUtil.getOrddtDate(dt_orddt);
		String yymm = orddt.substring(4, 6) + "/" + orddt.substring(6);

		int rowIdx = 9;

		for (List<String> rowContents : sheetContents) {
			if (rowIdx == 9) {
				Row row = sheet.getRow(1);
				String cellValue = orddt.concat("-");
				Cell cell = row.getCell(9);
				cell.setCellValue(cellValue.concat(rowContents.get(0)));

				row = sheet.getRow(3);
				cellValue = rowContents.get(2);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(4);
				cellValue = rowContents.get(5);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(5);
				cellValue = rowContents.get(4);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(21);
				cellValue = rowContents.get(12);
				cell = row.getCell(3);
				cell.setCellValue(cellValue);
			}

			Row row = sheet.getRow(rowIdx);

			String cellValue = yymm;
			Cell cell = row.getCell(1);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(1);
				cell.setCellValue(cellValue);
			}

			cellValue = rowContents.get(10);
			cell = row.getCell(2);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(2);
				cell.setCellValue(cellValue);
			}

			cellValue = rowContents.get(7);
			cell = row.getCell(8);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(8);
				cell.setCellValue(cellValue);
			}

			rowIdx++;
		}

		rowIdx = 37;

		for (List<String> rowContents : sheetContents) {
			if (rowIdx == 37) {
				Row row = sheet.getRow(29);
				String cellValue = orddt.concat("-");
				Cell cell = row.getCell(9);
				cell.setCellValue(cellValue.concat(rowContents.get(0)));

				row = sheet.getRow(31);
				cellValue = rowContents.get(2);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(32);
				cellValue = rowContents.get(5);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(33);
				cellValue = rowContents.get(4);
				cell = row.getCell(1);
				cell.setCellValue(cellValue);

				row = sheet.getRow(49);
				cellValue = rowContents.get(12);
				cell = row.getCell(3);
				cell.setCellValue(cellValue);
			}

			Row row = sheet.getRow(rowIdx);

			String cellValue = yymm;
			Cell cell = row.getCell(1);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(1);
				cell.setCellValue(cellValue);
			}

			cellValue = rowContents.get(10);
			cell = row.getCell(2);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(2);
				cell.setCellValue(cellValue);
			}

			cellValue = rowContents.get(7);
			cell = row.getCell(8);
			if (cell != null) {
				cell.setCellValue(cellValue);
			} else {
				cell = row.createCell(8);
				cell.setCellValue(cellValue);
			}

			rowIdx++;
		}

		fileOut = new FileOutputStream(fileSavePath);
		workbook.write(fileOut);
		fileOut.close();

		System.out.println(fileSavePath + " 인수증 생성 종료");

		MessageDialog.openInformation(getShell(), TITLE, "인수증을 엑셀파일로 저장하였습니다.\n" + fileSavePath);

		return 0;

	}

	void transferOrder(String February) {

		int[] selectedRowNoMst = table.getSelectionIndices();
		if (selectedRowNoMst.length == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "이월할 주문을 선택하세요.");
			return;
		}
		if (cb_ordseq.getText().equals("전체")) {
			MessageDialog.openInformation(getShell(), TITLE, "전체에서는 상품이월을 할수 없습니다.");
			return;
		}
		int month = dt_orddt.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + monthStr;
		}
		int day = dt_orddt.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + dayStr;
		}
		String orddt = dt_orddt.getYear() + monthStr + dayStr;
		String ordseq = cb_ordseq.getText();

		boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "선택한 주문내역을 이월후 삭제 합니다. 계속하시겠습니까?");

		if (!flag) {
			return;
		}

		try {

			List<List<String>> contents_transf = new ArrayList<>();
			List<List<String>> contents = (List<List<String>>) this.tableViewer.getInput();

			int[] seqs = new int[selectedRowNoMst.length];

			for (int i = 0; i < selectedRowNoMst.length; i++) {
				List<String> list = contents.get(selectedRowNoMst[i]);
				seqs[i] = Integer.parseInt(list.get(0));
				contents_transf.add(list);
			}
			String transforddt = "";
			String transfordseq = "";
			if (February.equals("1")) {
				transforddt = YDMATimeUtil.getNetworkDay(orddt, 1);
				transfordseq = Integer.toString(Integer.parseInt(ordseq) + 1);
			} else if (February.equals("2")) {
				transforddt = YDMATimeUtil.getNetworkDay(orddt, 2);
				transfordseq = Integer.toString(Integer.parseInt(ordseq) + 2);
			} else {
				// 3일 후로 주문일자 설정
				transforddt = YDMATimeUtil.getNetworkDay(orddt, 3);
				// 차수는 1차->4차, 2차->5차 로 변경
				transfordseq = Integer.toString(Integer.parseInt(ordseq) + 3);
			}
			for (List<String> list : contents_transf) {
				if (list.size() == 23) {
					list.remove(list.size() - 1);
				} else if (list.size() == 24) {
					list.remove(list.size() - 1);
					list.remove(list.size() - 2);
				}
			}
			OrderDao dao = new OrderDao();
			int result1 = dao.insertOrderList(transforddt, transfordseq, contents_transf);
			int[] result2 = dao.processDeleteOrder(orddt, Integer.parseInt(ordseq), seqs);

			contents.removeAll(contents_transf);

			this.tableViewer.refresh();

			MessageDialog.openInformation(getShell(), TITLE, result2.length + " 건의 주문내역이 이월후 삭제 되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}
	}

	class MyContentProvider extends ArrayContentProvider implements IStructuredContentProvider {
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Object[]) {
				return (Object[]) inputElement;
			}

			if (inputElement instanceof Collection) {
				return ((Collection) inputElement).toArray();
			}
			return new Object[0];
		}
	};

	class MyLableProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

		@Override
		public String getColumnText(Object element, int columnIndex) {

			String value = "";

//			if (columnIndex == 0) {
//				value = "";
//			} else {
//				List<String> rowContent = (List<String>) element;
//				value = (String) rowContent.get(columnIndex - 1);
//			}

			List<String> rowContent = (List<String>) element;
			value = rowContent.get(columnIndex);
			return value;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			List<String> list = (List<String>) element;
			if (list.size() > 23) {
				String qty = list.get(7);
				String stockStr = list.get(23);

				int qtyCnt = Integer.parseInt(qty);
				int stockCnt = Integer.parseInt(stockStr);

				// System.out.println("주문수량 " + qtyCnt);
				// System.out.println("재고수량 " + stockCnt);

				if ((stockCnt < qtyCnt)) {
					// 상품코드로 3일이후 입고예정재고를 확인..있다면 노란색으로 표시
					try {
						HousingDao dao = new HousingDao();
						int result = dao.get3DayInventoryCnt(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"),
								list.get(11));
						// 프로그래스바
						if (orderList.size() > Integer.parseInt(list.get(0))) {
							bar.setMaximum(orderList.size());
							bar.setValue(Integer.parseInt(list.get(0)));
							bar.setStringPainted(true);
							frame.setVisible(true);
						} else {
							frame.setVisible(false);
						}
						if (result > 0 && result <= 1) {
							return SWTResourceManager.getColor(SWT.COLOR_GREEN);
						} else if (result > 1 && result <= 2) {
							return SWTResourceManager.getColor(SWT.COLOR_BLUE);
						} else if (result > 2 && result <= 3) {
							return SWTResourceManager.getColor(SWT.COLOR_YELLOW);
						} else {
							return SWTResourceManager.getColor(SWT.COLOR_RED);
						}
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
					}
				}
			}

			// return SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
			return SWTResourceManager.getColor(SWT.COLOR_WHITE);
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {

			return null;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

	}
}
