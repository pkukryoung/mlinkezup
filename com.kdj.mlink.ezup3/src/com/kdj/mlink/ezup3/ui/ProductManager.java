package com.kdj.mlink.ezup3.ui;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.layout.Row;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMAPluginUtils;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.data.dao.CategoryDao;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.kdj.mlink.ezup3.data.dao.ProductSubDao;
import com.kdj.mlink.ezup3.data.dao.ProductSubDto;
import com.kdj.mlink.ezup3.data.excel.MyDataManagerFactory;
import com.kdj.mlink.ezup3.shop.common.ShopCombo;

public class ProductManager extends ViewPart {

	public static final String ID = "com.kdj.mlink.ezup3.ui.ProductManager";

//public class ProductManager extends Composite {
//
//	public static String ID = "com.kdj.mlink.ezup3.command.ProductManager.ID";
	public static String TITLE_MST = "상품관리";
	public static String TITLE_SUB = "종속상품관리";

//	String[][] column_name_mst = {
//			{"No.\n\n","50"}, {"상품코드\nERP상품코드\n사방넷코드","160"}, { "I", "30" }, {"상품명\n규격","395"}, {"세트구분\n세트플러스구분\n외주코드구분", "120"},{"단가\n택가\nEA","70"}
//			, {"상품상세", "350"},{"품절여부\n삭제여부","80"}, {"등록일\n등록자","160"},{"수정일\n수정자","160"}, {"수정자","70"}, {"사업자단가\n적정재고","100"}
//			, {"대표이미지","0"}, {"부가이미지1","0"}, {"부가이미지2","0"}, {"사이트검색어","0"},{"발주단가","0"}
////			{"No.","50"}, {"상품코드","160"}, { "I", "30" }, {"상품명","395"}, {"규격","90"}, {"ERP상품코드", "130"}
////			, {"세트구분", "80"}, {"세트플러스구분", "80"}, {"외주코드구분", "80"},{"단가","70"}, {"택가(룸에오피스)","80"}
////			, {"상품상세", "350"}, {"EA", "50"}, {"사방넷코드", "110"},  {"품절여부","80"}, {"삭제여부","80"}
////			, {"등록일","160"}, {"등록자","70"}, {"수정일","160"}, {"수정자","70"}, {"사업자단가","100"}, {"적정재고","80"}
////			, {"대표이미지","0"}, {"부가이미지1","0"}, {"부가이미지2","0"}, {"사이트검색어","0"},{"발주단가","0"}//,{"대분류","10"},{"중분류","10"},{"소분류","0"},{"사이트검색어","0"}
//	};
	String[][] column_name_mst = { { "\nNo.\n", "50" }, { "\n자체상품코드\n", "150" }, { "\n상품명-규격\n", "400" },
			{ "세트구분\n세트플러스구분\n외주코드구분", "140" }, { "단가\n사업단가\n발주단가", "100" }, { "\n랙정보\n", "70" },
			{ "택배사\n송장수량\n번호/택배비", "120" }, { "대표이미지\n상세이미지설명\n", "500" }, { "품절여부\n공급여부\n", "100" },
			{ "공급업체\n상품속성\n", "120" }, { "\n검색어\n", "400" }, { "등록일시\n수정일시\n", "200" } };
	String[][] column_name_sub = { { "No.", "65" }, { "종속상품코드", "180" }, { "종속상품명", "400" }, { "규격", "300" },
			{ "세트구분", "90" }, { "세트수량", "90" }, { "세트레벨", "90" }, { "택배사", "150" }, { "품절여부", "90" }, { "삭제여부", "90" }
			// , {"등록일","180"}, {"등록자","100"}, {"수정일","180"}, {"수정자","100"}
	};
	// {"택배사파일","150"}, {"랙위치","70"}, {"층","50"}, {"셀","50"},

//	int selectedRowNoMst = -1;
//	int selectedRowNoSub = -1;
	List<List<String>> cate = new ArrayList<>();
	GridTableViewer tableViewer_mst;
	GridTableViewer tableViewer_sub;
	Grid table_prodmst;
	Grid table_prodsub;
	ShopCombo scb_categlagcd;
	ShopCombo scb_categmidcd;
	ShopCombo scb_categsmlcd;
	ShopCombo scb_categdetcd;
	Text txt_search_condition;
	Link link;
	Combo cb_option;
	public Text txt_prodcnt;

	private String img_path = ""; // image path

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

//		Composite top = new Composite(parent, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.marginHeight = 0;
//		layout.marginWidth = 0;
//		top.setLayout(layout);
//		// top banner
//		Composite banner = new Composite(top, SWT.NONE);
//		banner.setLayoutData(
//				new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
//		layout = new GridLayout();
//		layout.marginHeight = 5;
//		layout.marginWidth = 10;
//		layout.numColumns = 2;
//		banner.setLayout(layout);

		GridLayout gl_composite = new GridLayout();
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		composite.setLayout(gl_composite);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(12, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.READ_ONLY);
		cb_option = comboViewer.getCombo();
		cb_option.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		cb_option.setItems(new String[] { "상품코드", "상품명" });
		cb_option.select(1);

		txt_search_condition = new Text(composite_1, SWT.BORDER);
		txt_search_condition.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_search_condition.getShell().setImeInputMode(SWT.ALPHA | SWT.PHONETIC);
			}
		});
		txt_search_condition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					int opt = cb_option.getSelectionIndex();
					getProdcutListByOptionSearch(opt, txt_search_condition.getText().trim());
				}
			}
		});
		GridData gd_txt_search_condition = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
		gd_txt_search_condition.widthHint = 250;
		txt_search_condition.setLayoutData(gd_txt_search_condition);
		txt_search_condition.setBounds(0, 0, 73, 26);

		Button btn_search = new Button(composite_1, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int opt = cb_option.getSelectionIndex();
				getProdcutListByOptionSearch(opt, txt_search_condition.getText().trim());
				// tableViewer_prod.getTable().setSelection(((List<UserDto>)tableViewer_prod.getInput()).size()-1);
			}
		});
		btn_search.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		btn_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

			}
		});
		btn_search.setBounds(0, 0, 93, 30);
		btn_search
				.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));

		txt_prodcnt = new Text(composite_1, SWT.BORDER);
		GridData gd_txt_prodcnt = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodcnt.widthHint = 40;
		txt_prodcnt.setLayoutData(gd_txt_prodcnt);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setText("개");

		Button btn_refresh = new Button(composite_1, SWT.NONE);
		btn_refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getProdcutListByOptionSearch(cb_option.getSelectionIndex(), "");
			}
		});

		btn_refresh.setToolTipText("전체보기");
		btn_refresh.setImage(
				org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
		btn_refresh.setText("전체보기");

		Button btn_add_mst = new Button(composite_1, SWT.NONE);
		btn_add_mst
				.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/import.gif"));
		btn_add_mst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDetailProductMst(true, "0");
			}
		});
		btn_add_mst.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		btn_add_mst.setText("상품등록");
		btn_add_mst.setToolTipText("상품등록");
//		Button btn_del = new Button(composite_1, SWT.NONE);
//		btn_del.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//
//				if(table_prodmst.getSelectionIndex()<0) {
//					MessageDialog.openInformation(getShell(), TITLE_MST, "삭제할 상품을 선택하세요.");
//					return;
//				}else {
//					deleteProductMst();
//				}
//
//			}
//		});
//		btn_del.setText("상품삭제");

		Button btn_excell = new Button(composite_1, SWT.NONE);
		btn_excell.setEnabled(false);
		btn_excell.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getShell(), TITLE_MST, "준비중입니다. ㅠ.ㅠ");

			}
		});
		btn_excell.setText("엑셀등록");
		btn_excell
				.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/excel.jpg"));
		btn_excell.setToolTipText("엑셀등록");
		Button btn_copy = new Button(composite_1, SWT.NONE);
		btn_copy.setText("복사하기");
		btn_copy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDetailProductMst(true, "1");
			}
		});
		btn_copy.setToolTipText("복사하기");
		btn_copy.setImage(
				org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));

		Button excel = new Button(composite_1, SWT.NONE);
		excel.setText("엑셀저장");
		excel.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/excel.jpg"));

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setText("미리보기");
		new Label(composite_1, SWT.NONE);

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPreview();
			}
		});
		excel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SaveExcel();
			}
		});
//		SashForm sashForm = new SashForm(composite, SWT.NONE);
//		sashForm.setOrientation(SWT.VERTICAL);
//		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_mst = new Composite(composite, SWT.BORDER);
		GridLayout gl_composite_mst = new GridLayout(1, false);
		gl_composite_mst.verticalSpacing = 0;
		gl_composite_mst.marginHeight = 0;
		gl_composite_mst.marginWidth = 0;
		gl_composite_mst.horizontalSpacing = 0;
		composite_mst.setLayout(gl_composite_mst);
		composite_mst.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_mst.setBounds(0, 0, 64, 64);

		tableViewer_mst = new GridTableViewer(composite_mst,
				SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer_mst.addSelectionChangedListener(event -> getProdcutSubListByProductCode());

		tableViewer_mst.addDoubleClickListener(event -> openDetailProductMst(false, "0"));
		table_prodmst = tableViewer_mst.getGrid();
		table_prodmst.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		table_prodmst.setLinesVisible(true);
		table_prodmst.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		table_prodmst.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_prodmst.setHeaderVisible(true);
		table_prodmst.setWordWrapHeader(true);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (String[] column : column_name_mst) {
			GridColumn tableViewerColumn_x = new GridColumn(table_prodmst, SWT.LEFT);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

			MyCellRenderer myCellRenderer = new MyCellRenderer();
			myCellRenderer.setWordWrap(true);
			tableViewerColumn_x.setCellRenderer(myCellRenderer);
			tableViewerColumn_x.setHeaderWordWrap(true);
//			if(i==1||i==2|| i==3 ||i==10 ) {
//				MyCellRenderer myCellRenderer =  new MyCellRenderer();
//
//				myCellRenderer.setColumnAlign(SWT.LEFT);
//				tableViewerColumn_x.setCellRenderer(myCellRenderer);
//
//			}

		}

		Composite composite_sub = new Composite(composite, SWT.BORDER);
		GridLayout gl_composite_sub = new GridLayout(1, false);
		gl_composite_sub.verticalSpacing = 0;
		gl_composite_sub.marginWidth = 0;
		gl_composite_sub.marginHeight = 0;
		gl_composite_sub.horizontalSpacing = 0;
		composite_sub.setLayout(gl_composite_sub);
		GridData gd_composite_sub = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_sub.heightHint = 220;
		gd_composite_sub.widthHint = 200;
		composite_sub.setLayoutData(gd_composite_sub);

		Composite composite_sub_1 = new Composite(composite_sub, SWT.NONE);
		composite_sub_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_sub_1.setLayout(new GridLayout(1, false));

		Button btn_add_sub = new Button(composite_sub_1, SWT.NONE);
		btn_add_sub.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDetailProductSub(true);
			}
		});
		btn_add_sub.setText("종속등록");
		btn_add_sub
				.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/import.gif"));
		btn_add_sub.setToolTipText("종속상품등록");
		tableViewer_sub = new GridTableViewer(composite_sub,
				SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer_sub.addDoubleClickListener(event -> openDetailProductSub(false));
		tableViewer_sub.addSelectionChangedListener(event -> {
			// selectedRowNoSub = table_prodsub.getSelectionIndex();
		});
		table_prodsub = tableViewer_sub.getGrid();
		table_prodsub.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		table_prodsub.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_prodsub.setLinesVisible(true);
		table_prodsub.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		table_prodsub.setHeaderVisible(true);

		MyColumnHeaderRenderer myHeaderRender2 = new MyColumnHeaderRenderer();
		myHeaderRender2.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender2.setFGC(SWT.COLOR_WHITE);

		for (int i = 0; i < column_name_sub.length; i++) {
			String[] column = column_name_sub[i];
			GridColumn tableViewerColumn_x = new GridColumn(table_prodsub, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender2);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

			if (i == 2 || i == 3 || i == 10 || i == 4) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}

			if (i == 0 || i == 1 || i == 5 || i == 6 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.CENTER);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
			if (i == 7 || i == 8) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.RIGHT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}

//		sashForm.setWeights(new int[] {75, 25});

		tableViewer_mst.setContentProvider(new ProdMstContentProvider());
		tableViewer_mst.setLabelProvider(new ProdMstLableProvider());

		tableViewer_sub.setContentProvider(new ProdSubContentProvider());
		tableViewer_sub.setLabelProvider(new ProdSubLableProvider());

		getProdcutListByOptionSearch(cb_option.getSelectionIndex(), ""); // 상품명으로 전체검색

	}

	protected Shell getShell() {
		// TODO Auto-generated method stub
		return null;
	}

	// 미리보기
	protected void setPreview() {
		int[] selectedRowNoMst = table_prodmst.getSelectionIndices();
		if (selectedRowNoMst.length == 0) {
			MessageDialog.openInformation(getShell(), TITLE_MST, "상품을 선택하시기 바랍니다.");
			return;
		}
		if (selectedRowNoMst.length > 1) {
			MessageDialog.openInformation(getShell(), TITLE_MST, "1개의 상품만 선택하여 주시기 바랍니다.");
			return;
		}
		ProductMstDto dto = new ProductMstDto();
		List<ProductMstDto> contents = (List<ProductMstDto>) tableViewer_mst.getInput();

		dto = contents.get(selectedRowNoMst[0]);

		String imagePath;
		try {
			imagePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("image.productPath"));
			String imageFile = YDMAProperties.getInstance().getAppProperty("image.productFile");
			String imageFullpath = imagePath + imageFile;

			BufferedWriter out = new BufferedWriter(new FileWriter(imageFullpath));
			StringBuffer sb = new StringBuffer(dto.getRemark().trim());
			out.write(sb.toString());
			out.close();

//			String url = imageFullpath.replace("\\", "/");
			String url = imageFullpath;
			File newFile = new File(url);

			String myOS = System.getProperty("os.name").toLowerCase();
			if (myOS.contains("windows")) {
				// if (Desktop.isDesktopSupported()) {
//		        Desktop.getDesktop().browse(new URI(url));
				Desktop.getDesktop().open(newFile);
			} else {
				Runtime runtime = Runtime.getRuntime();
				if (myOS.contains("mac")) {
					runtime.exec("open " + url);
				} else if (myOS.contains("nix") || myOS.contains("nux")) {
					runtime.exec("xdg-open " + url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void SaveExcel() {
		if (tableViewer_mst.getInput() == null || ((List<List<String>>) tableViewer_mst.getInput()).size() == 0) {
			MessageDialog.openInformation(getShell(), TITLE_MST, "엑셀파일로 저장할 주문내역이 없습니다.");
			return;
		}
		FileDialog fileSelectionDialog = MyDataManagerFactory.createExcelFileSaveDialog();
		String fileSavePath = fileSelectionDialog.open();
		if (fileSavePath == null) {
			return;
		}

		try {

			printExpPickupList(fileSavePath);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE_MST, e.getMessage());
		}

	}

	// 엑셀저장
	private int printExpPickupList(String fileSavePath) throws Exception {
		CompInfoDao compdao = new CompInfoDao();
		List<String> complist = compdao.getCompNoImage();
		ProductMstDao dao = new ProductMstDao();
		List<ProductMstDto> contents = dao.getProdcutListByOptionSearch(cb_option.getSelectionIndex(), "");
		String[][] headerColumns = { { "No.", "60" }, { "상품코드", "160" }, { "상품명", "395" }, { "규격", "150" },
				{ "ERP코드", "150" }, { "카테고리", "500" }, { "칼라(대표코드)", "300" } };

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

		Sheet sheet = workbook.createSheet("전체상품");
		if (sheet == null) {
			return -1;
		}
		Row row_head = (Row) sheet.createRow(0);
		for (int i = 0; i < headerColumns.length; i++) {
			Cell cell = ((org.apache.poi.ss.usermodel.Row) row_head).createCell(i);
			cell.setCellValue(headerColumns[i][0]);
			sheet.setColumnWidth(i, Integer.parseInt(headerColumns[i][1]) * 10 * 3);
			cell.setCellStyle(cs_center);
		}
		int rowIdx = 1;

		for (ProductMstDto dto : contents) {
			Row row = (Row) sheet.createRow(rowIdx);

			for (int i = 0; i < headerColumns.length; i++) {
				Cell cell = ((org.apache.poi.ss.usermodel.Row) row).createCell(i);
				String value = "";
				if (i == 0) {
					value = dto.getRowno();
				}
				if (i == 1) {
					value = dto.getProdcd();
				}
				if (i == 2) {
					value = dto.getProdnm();
				}
				if (i == 3) {
					value = dto.getSpecdes();
				}
				if (i == 4) {
					value = dto.getEcountcd();
				}
				cell.setCellValue(value);
			}

			rowIdx++;

		}

		FileOutputStream fileOut = new FileOutputStream(newfile);
		workbook.write(fileOut);
		fileOut.close();

		System.out.println(newfile + " 상품내역 생성 종료");

		MessageDialog.openInformation(getShell(), TITLE_MST, "상품내역을 엑셀파일로 저장하였습니다.\n" + fileSavePath);

		return 0;
	}

	public void setTotalCount() {
		List<ProductMstDto> contents = (List<ProductMstDto>) tableViewer_mst.getInput();
		txt_prodcnt.setText(String.valueOf(contents.size()));
	}

	List<String> optprodcd;
	List<List<String>> prodinfor;
	List<List<String>> prodattr;

	private void getProdcutListByOptionSearch(int opt, String optStr) {

		if (optStr.length() != 0 && optStr.length() < 2) {
			// 검색어가 있는데 2보다 작은 경우
			MessageDialog.openInformation(getShell(), TITLE_MST, "검색어를 2글자이상 입력해주세요");
			return;
		}
		try {
			img_path = String.format("%s\\%s",
					YDMASessonUtil
							.getAppPath(YDMAProperties.getInstance().getAppProperty("Product.image.productImage")),
					YDMASessonUtil.getImageFolderName());
			CategoryDao catedao = new CategoryDao();
			cate = catedao.getAllCategoryList();
			ProductMstDao dao = new ProductMstDao();
			List<ProductMstDto> contents = dao.getProdcutListByOptionSearch(opt, optStr);
			optprodcd = dao.getoptProdcd();
			prodinfor = dao.getProdInfor();
			prodattr = dao.getAttrName();
			this.tableViewer_mst.setInput(contents);
			this.tableViewer_mst.refresh();
			table_prodmst.setItemHeight(70);
			// setGridrefresh("");
			// txt_prodcnt.setText(String.valueOf(contents.size()));
			setTotalCount();

			this.tableViewer_sub.setInput(null);

			if (contents == null || contents.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE_MST, "조건에 맞는  상품이 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE_MST, e.getMessage());
		}
	}

	public void openDetailProductMst(boolean isNew, String number) {
		try {
			if (!isNew) {// 수정
				List<ProductMstDto> list = (List<ProductMstDto>) this.tableViewer_mst.getInput();
				ProductMstDto dot_orgin = list.get(table_prodmst.getSelectionIndex());
				ProductMstDetail d = new ProductMstDetail(this.getShell(), dot_orgin, this, "0");
				d.open();

			} else { // 추가
				if (number.equals("0")) {
					ProductMstDetail d = new ProductMstDetail(this.getShell(), null, this, "0");
					d.open();
				} else {
					if (table_prodmst.getSelectionIndex() < 0) {
						MessageDialog.openInformation(getShell(), TITLE_MST, "상품을 선택하세요");
						return;
					}
					List<ProductMstDto> list = (List<ProductMstDto>) this.tableViewer_mst.getInput();
					ProductMstDto dot_orgin = list.get(table_prodmst.getSelectionIndex());

					ProductMstDetail d = new ProductMstDetail(this.getShell(), null, this, dot_orgin.getProdcd());
					d.open();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE_MST, e.getMessage());
		}

	}

//	public void deleteProductMst() {
//		try {
//			List<ProductMstDto> list = (List<ProductMstDto>) tableViewer_mst.getInput();
//			ProductMstDto dto = (ProductMstDto) list.get(table_prodmst.getSelectionIndex());
//			ProductMstDao dao = new ProductMstDao();
//			dao.deleteProductMst(dto.getProdcd());
//			dto.setDelyn("Y");
//
//			this.tableViewer_mst.refresh();
//		} catch (Exception e) {
//			e.printStackTrace();
//			MessageDialog.openInformation(getShell(), TITLE_MST, e.getMessage());
//		}
//
//	}

	public void deleteProductSub(String falg) {
		try {
			List<ProductSubDto> list = (List<ProductSubDto>) tableViewer_sub.getInput();
			ProductSubDto dto = list.get(table_prodsub.getSelectionIndex());
			ProductSubDao dao = new ProductSubDao();
			dao.deleteProductSub(dto.getProdcd(), dto.getProddtcd(), falg);
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE_SUB, e.getMessage());
		}

	}

	public void getProdcutSubListByProductCode() {

		if (table_prodmst.getSelectionIndex() < 0) {
			return;
		}

		List<ProductMstDto> list = (List<ProductMstDto>) tableViewer_mst.getInput();
		ProductMstDto temp = list.get(table_prodmst.getSelectionIndex());

		try {
			ProductSubDao dao = new ProductSubDao();
			List<List<String>> productSubList = dao.getProdcutSubListByProductCode(temp.getProdcd());
			this.tableViewer_sub.setInput(productSubList);
			this.tableViewer_sub.refresh();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE_MST, e.getMessage());
		}

	}

	public void openDetailProductSub(boolean isNew) {

		try {
			if (!isNew) { // 수정인경우

				List<List<String>> list_sub = (List<List<String>>) tableViewer_sub.getInput();
				List<String> rowList_sub = list_sub.get(table_prodsub.getSelectionIndex());
				String proddtcd = rowList_sub.get(1);

				List<ProductMstDto> list_mst = (List<ProductMstDto>) tableViewer_mst.getInput();
				ProductMstDto rowList_mst = list_mst.get(table_prodmst.getSelectionIndex());
				String prodcd = rowList_mst.getProdcd();

				ProductSubDao dao = new ProductSubDao();
				ProductSubDto dto = dao.getProdcutSubInfoByMsteAndSubCode(prodcd, proddtcd);
				ProductSubDetail d = new ProductSubDetail(YDMAPluginUtils.getShell(), this, dto);
				d.open();

			} else { // 등록인경우

				if (table_prodmst.getSelectionIndex() < 0) {
					MessageDialog.openInformation(getShell(), TITLE_SUB, "대표상품을 먼저 선택하세요");
					return;
				}

				List<List<String>> list_mst = (List<List<String>>) tableViewer_mst.getInput();

				ProductMstDto dto = (ProductMstDto) list_mst.get(table_prodmst.getSelectionIndex());
				String prodcd = dto.getProdcd();
				String prodnm = dto.getProdnm();

				ProductSubDetail d = new ProductSubDetail(YDMAPluginUtils.getShell(), this, prodcd, prodnm);
				d.open();

			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE_MST, e.getMessage());
		}

	}

//	//그리드변경??
//	private void setGridrefresh(String code) {
//			Grid grid =  tableViewer_mst.getGrid();
//			GridItem[] items =   grid.getItems();
//			int i=0;
//			for(i = 0; i<items.length; ++i) {
//				GridEditor editor = new GridEditor(grid);
//				GridItem item = items[i];
//				//Button
//				link = new Link(table_prodmst, SWT.NONE);
//				link.setText("<a>dddddddddd</a>");
//				link.setSize(20,20);
//				link.pack();
//			    editor.setEditor(link, item, 2);
//			    editor.horizontalAlignment = SWT.CENTER;
//			    editor.grabHorizontal = true;
//			    editor.minimumWidth = 50;
//
//			}
//
//
//
//		//btnClickevent();
//	}
	class ProdMstContentProvider extends ArrayContentProvider implements IStructuredContentProvider {
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

	class ProdMstLableProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {
		@Override
		public String getColumnText(Object element, int columnIndex) {

			ProductMstDto dto = (ProductMstDto) element;
			String value = "";
			switch (columnIndex) {
			case 0:
//			{"\nNo.\n","50"}
				value = String.format("\n%s\n", dto.getRowno());
				break;
			case 1:
				// {"\n자체상품코드\n","200"}
				String prodcd = "[대표]";
//				String optchk = optprodcd.stream()
//							.filter(opt -> dto.getProdcd().equals(opt.getBytes()))
//							.findAny()
//							.orElse(null);
				for (int i = 0; i < optprodcd.size(); i++) {
					if (dto.getProdcd().equals(optprodcd.get(i))) {
						prodcd = "[옵션]";
						break;
					}
				}
				value = String.format("\n%s\n%s", dto.getProdcd(), prodcd);
				break;
			case 2:
				// {"\n상품명-규격\n","400"}
				String lar = "";
				String mid = "";
				String sml = "";
				String det = "";
				for (List<String> list : cate) {
					if (!list.get(0).equals("")) {
						if (list.get(0).equals(dto.getCateglagcd())) {
							lar = list.get(1);
						}
					}
					if (!list.get(2).equals("")) {
						if (list.get(2).equals(dto.getCategmidcd())) {
							mid = " > " + list.get(4);
						}
					}
					if (!list.get(5).equals("")) {
						if (list.get(5).equals(dto.getCategsmlcd())) {
							sml = " > " + list.get(7);
						}
					}
					if (!list.get(8).equals("")) {
						if (list.get(8).equals(dto.getCategdetcd())) {
							det = " > " + list.get(10);
						}
					}
				}

				value = String.format("\n%s%s\n%s", dto.getProdnm(),
						dto.getSpecdes().equals("") ? "" : " - " + dto.getSpecdes(), lar + mid + sml + det);
				break;
			case 3:
				// {"세트구분\n세트플러스구분2\n외주코드구분", "140"}
				value = String.format("%s\n%s\n%s", dto.getFlagset().equals("1") ? "세트" : "-",
						dto.getFlagplus().equals("1") ? "세트" : "-", dto.getFlagout().equals("1") ? "세트" : "-");
				break;
			case 4:
				// {"판매가\n사업단가\n발주단가","100"}
				value = String.format("%s\n%s\n%s", dto.getPrice(), dto.getCusprice(), dto.getOrderprice());
				break;
			case 5:
				// {"\n랙정보\n","100"}
				value = String.format("%s\n%s\n%s", dto.getNeccd1().equals("") ? "-" : dto.getNeccd1(),
						dto.getNeccd2().equals("") ? "-" : dto.getNeccd2(),
						dto.getNeccd3().equals("") ? "-" : dto.getNeccd3());
				break;
			case 6:
				// {"택배사\n송장수량\n택배비","100"}
				value = String.format("%s\n%s\n%s", dto.getExpfile(), dto.getExpinvqty(),
						dto.getDellivtabno() + "/" + dto.getDelivamt());
				break;
			case 7:
				// {"대표이미지\n상세이미지설명\n", "200"}
				String aa = "";
				String bb = "";
				if (dto.getRemark().contains("<")) {
					String remark = dto.getRemark().replaceAll("\n", "");
					aa = remark.substring(remark.indexOf(">") + 1, remark.length());
					bb = remark.substring(0, remark.indexOf(">") + 1);
				}

				value = String.format("%s\n%s\n%s", dto.getRepstt(), bb, aa);
				break;
			case 8:
				// {"품절여부\n공급여부\n","150"}
				String stat = "";
				for (List<String> list : prodinfor) {
					if (list.get(0).equals(dto.getInfornum())) {
						stat = list.get(1);
					}
				}
				String gunum = stat.equals("0") ? "-"
						: stat.equals("1") ? "대기중"
								: stat.equals("2") ? "공급중"
										: stat.equals("3") ? "일시중지"
												: stat.equals("4") ? "완전품절" : stat.equals("5") ? "미사용" : "삭제";
				value = String.format("%s\n%s", dto.getUseyn().equals("") ? "-" : dto.getUseyn(), gunum);
				break;
			case 9:
				// {"상품기본정보\n상품속성정보\n","100"}
				String prodinnm = "";
				for (List<String> list : prodinfor) {
					if (list.get(0).equals(dto.getInfornum())) {
						prodinnm = list.get(2);
					}
				}
				String prodattrnm = "";
				for (List<String> list : prodattr) {
					if (list.get(0).equals(dto.getPropertynum())) {
						prodattrnm = list.get(1);
					}
				}
				value = String.format("%s\n%s", prodinnm, prodattrnm);
				break;
			case 10:
				// {"\n검색어\n","150"}
				value = String.format("\n%s\n", dto.getSearchwd());
				break;
			case 11:
				// 등록일시 수정일시
				value = String.format("%s\n%s", dto.getInsertdt().equals("") ? "-" : dto.getInsertdt(),
						dto.getModifydt().equals("") ? "-" : dto.getModifydt());
				break;
			default:
				value = "";
			}
			return value;
		}

		@Override
		public Color getBackground(java.lang.Object element, int columnIndex) {
			Color bgColor = null;
			if (columnIndex == 1) {
				ProductMstDto dto = (ProductMstDto) element;
				if (dto.getUseyn().equals("Y")) { // 품절
					bgColor = SWTResourceManager.getColor(SWT.COLOR_YELLOW);
				}
				if (dto.getDelyn().equals("Y")) { // 삭제
					bgColor = SWTResourceManager.getColor(255, 140, 0);
				}

			}
			return bgColor;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {

//			if(columnIndex==1) {
//				ProductMstDto dto = (ProductMstDto) element;
//				if (dto.getDelyn().equals("Y")) {
//					return SWTResourceManager.getColor(SWT.COLOR_RED);
//				}
//			}

			return null;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			Image imgValue = null;
//
//			if (columnIndex==2) {
//				//List<String> rowContent = (List<String>) element;
//				ProductMstDto dto = (ProductMstDto) element;
//				String SRC_FILE =  String.format("%s\\%s%s" , img_path , dto.getImg() , ".jpg");
//				try {
//					imgValue = YMDAResourceManager.getImage(SRC_FILE.trim());
//					YMDAResourceManager.dispose();
//				}
//				catch (NullPointerException e) {
//					imgValue = null;
//				}
//			}
			return imgValue;
		}
	}

	class ProdSubContentProvider extends ArrayContentProvider implements IStructuredContentProvider {
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

	class ProdSubLableProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {
		@Override
		public String getColumnText(Object element, int columnIndex) {

			String value = "";

//			if(columnIndex==0) {
//				value ="";
//			}else {
//				List<String> rowContent = (List<String>) element;
//				value = (String)rowContent.get(columnIndex-1);
//			}
//			return value;

			List<String> rowContent = (List<String>) element;
			value = rowContent.get(columnIndex);

			return value;
		}

		@Override
		public Color getBackground(java.lang.Object element, int columnIndex) {

			List<ProductSubDto> object = (List<ProductSubDto>) tableViewer_sub.getInput();

			for (int i = 0; i < object.size(); i++) {
				if (object.get(i) == element) {
//					if (i % 2 == 0) { // 현재의 element 가 뷰 리스트상의 특정 Row 의 element 면
//						return SWTResourceManager.getColor(SWT.COLOR_WHITE);
//					} else {
//						return SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
//					}
				}
			}

			return null;
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

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
