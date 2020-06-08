package com.kdj.mlink.ezup3.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;

public class ShopAdditionalInforMationManager extends Composite {

	public static String ID = "com.kdj.mlink.ezup3.command.ShopAdditionalInforMationManager.ID";
	public static String TITLE = "부가정보관리";

	String[][] column_name = { { "코드", "100" }, { "쇼핑몰명", "150" }, { "마켓분류", "150" }, { "제목", "500" }, { "형태", "100" },
			{ "사용여부", "100" }, { "최초등록일", "250" }, { "최종수정일", "250" }, { "쇼핑몰코드", "0" } };

	int selectedRowNo = -1;

	private Text txt_search_condition;
	private Grid table;
	private Button btn_addition;
	GridTableViewer tableViewer;
	private Combo cb_shopping;
	private Label label_2;
	private Label label_3;
	private Label label;
	private Label label_4;
	private Combo cb_shopnm;
	private DateTime dateTime_to;
	private DateTime dateTime_from;
	private Label label_5;
	private Button today;
	private Button week1;
	private Button month1;
	private Button month3;
	private Button month6;
	private Label label_1;
	private Combo cb_select;
	private Combo cb_inerlock;
	private Combo cb_useyn;
	Composite composite_2;
	Combo cb_senddt;

	public ShopAdditionalInforMationManager(Composite parent, int style) {
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
		composite_1.setLayout(null);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_1.heightHint = 159;
		composite_1.setLayoutData(gd_composite_1);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(5, 9, 60, 20);
		lblNewLabel.setBounds(5, 9, 70, 20);
		lblNewLabel.setText("일 자");

		dateTime_from = new DateTime(composite_1, SWT.BORDER);
		dateTime_from.setBounds(177, 5, 102, 28);

		dateTime_to = new DateTime(composite_1, SWT.BORDER);
		dateTime_to.setBounds(296, 5, 102, 28);

		label = new Label(composite_1, SWT.NONE);
		label.setBounds(759, 9, 0, 20);
		label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBounds(794, 9, 0, 20);

		label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(5, 85, 70, 20);
		label_2.setText("검색항목");

		cb_shopping = new Combo(composite_1, SWT.NONE);
		cb_shopping.setBounds(80, 81, 108, 28);
		cb_shopping.setItems(new String[] { "조건선택", "제목", "부가정보코드" });
		cb_shopping.select(1);

		txt_search_condition = new Text(composite_1, SWT.BORDER);
		txt_search_condition.setBounds(193, 81, 259, 28);

		Button btn_search = new Button(composite_1, SWT.NONE);
		btn_search.setBounds(457, 81, 66, 28);
		btn_search.setToolTipText("찾기");
		btn_search.setText("검색");
		btn_search.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		// btn_search.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getSearchShoppingManager();
			}
		});

		label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(5, 122, 70, 20);
		label_3.setText("신규등록");

		cb_shopnm = new Combo(composite_1, SWT.NONE);
		cb_shopnm.setBounds(80, 119, 150, 28);

		btn_addition = new Button(composite_1, SWT.NONE);
		btn_addition.setBounds(235, 119, 102, 28);
		btn_addition.setText("신규등록");
		btn_addition.setToolTipText("신규등록하기");
		btn_addition.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/upload.gif"));
		// btn_addition.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btn_addition.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAdditionalInformation();
			}
		});

		label_5 = new Label(composite_1, SWT.NONE);
		label_5.setText("~");
		label_5.setBounds(282, 8, 11, 20);

		today = new Button(composite_1, SWT.NONE);
		today.setText("오늘");
		// today.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		today.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("오늘");
			}
		});
		today.setBounds(403, 6, 50, 26);

		week1 = new Button(composite_1, SWT.NONE);
		week1.setText("1주일");
		// week1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		week1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("week1");
			}
		});
		week1.setBounds(458, 6, 50, 26);

		month1 = new Button(composite_1, SWT.NONE);
		month1.setText("1개월");
		// month1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month1");
			}
		});
		month1.setBounds(513, 6, 50, 26);

		month3 = new Button(composite_1, SWT.NONE);
		month3.setText("3개월");
		// month3.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month3");
			}
		});
		month3.setBounds(568, 6, 50, 26);

		month6 = new Button(composite_1, SWT.NONE);
		month6.setText("6개월");
		// month6.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month6");
			}
		});
		month6.setBounds(623, 6, 50, 26);

		cb_senddt = new Combo(composite_1, SWT.NONE);
		cb_senddt.setBounds(80, 5, 92, 28);
		cb_senddt.setItems(new String[] { "등록일", "수정일" });
		cb_senddt.select(0);

		label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("선택사항");
		label_1.setBounds(5, 47, 70, 20);

		cb_select = new Combo(composite_1, SWT.NONE);
		cb_select.setItems(new String[] { "쇼핑몰선택" });
		cb_select.setBounds(80, 43, 120, 28);
		cb_select.select(0);

		cb_inerlock = new Combo(composite_1, SWT.NONE);
		cb_inerlock.setItems(new String[] { "연동판매형태", "고정가판매/오픈마켓", "공동구매" });
		cb_inerlock.setBounds(205, 43, 150, 28);
		cb_inerlock.select(0);

		cb_useyn = new Combo(composite_1, SWT.NONE);
		cb_useyn.setItems(new String[] { "사용여부", "사용중", "미사용" });
		cb_useyn.setBounds(360, 43, 110, 28);
		cb_useyn.select(1);

		composite_2 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.horizontalSpacing = 0;
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.verticalSpacing = 0;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setBounds(0, 0, 64, 64);

		tableViewer = new GridTableViewer(composite_2, SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		tableViewer.addDoubleClickListener(event -> opendetail());

		table = tableViewer.getGrid();
		table.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		MyCellRenderer myCellRenderer = new MyCellRenderer();

		for (String[] column : column_name) {
			GridColumn tableViewerColumn_x = new GridColumn(table, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
//		GridItem[] items =   table.getItems();
//		table.setItemHeight(50);
//
//		for(int i = 0; i<items.length; ++i) {
//			GridEditor editor = new GridEditor(table);
//			GridItem item = items[i];
//			String text = String.format("%s\n%s\n%s", item.getText(0), item.getText(1),item.getText(2));
//			String string = "This is sample text with a link and some other link \n here.";
//			final StyledText widget = new StyledText (table, SWT.MULTI | SWT.None);
//			widget.setText(text);
//
//			String link1 = item.getText(0);
//			String link2 = item.getText(2);
//			StyleRange style1 = new StyleRange();
//			style1.underline = true;
//			style1.underlineStyle = SWT.UNDERLINE_LINK;
//
//			int[] ranges = {text.indexOf(link1), link1.length(), text.indexOf(link2), link2.length()};
//			StyleRange[] styles = {style1, style1};
//			widget.setStyleRanges(ranges, styles);
//
//			//StyledText widget = new StyledText(grid, SWT.NONE);
//			//widget.setText("This is \n the StyledText \n widget.");
//			widget.setEnabled(false);
//			widget.pack();
//			//Object  lbl =  new JLabel("<html>잘생긴승훈이 멋졌어<br>그렇게 멋진녀석이...<br></html>");
//			//Label	lb_qty = new Label( grid, SWT.NONE);
//			//GridData gd_lb_qty = new GridData(SWT.LEFT, SWT.MULTI, false, false, 1, 1);
//			//gd_lb_qty.widthHint = 50;
//			//lb_qty.setLayoutData(gd_lb_qty);
//			//lb_qty.setText("-\ndsfsfs\nsdfsf\nsdsf");
//
//			/*JPanel	controlPanel = new JPanel();
//
//			controlPanel.setLayout(new FlowLayout());*/
//
//			//)
//			//b.setText("<html><body>This <br>is Unicode HTML content from memory</body></html>");
////lb_qty.pack();
//			editor.horizontalAlignment = SWT.RIGHT;
//		    editor.grabHorizontal = true;
//		    editor.minimumWidth = 50;
//			editor.setEditor(widget, item, 1);
//			//Button
//
////			editor.setEditor((Control)lbl, item, 1);
////
////			Button button = new Button(table, SWT.PUSH);
////
////		    button.setText("A");
////		    button.setSize(16,16);
////		    button.pack();
////
////		    editor.horizontalAlignment = SWT.RIGHT;
////		    editor.grabHorizontal = true;
////		    editor.minimumWidth = 50;
////		    editor.setEditor(button, item, 1);
//		}
		setDefaultDate("week1");

		setShopCode();

		tableViewer.setLabelProvider(new MyLableProvider());
		tableViewer.setContentProvider(new MyContentProvider());
		// tableViewer.setRowHeaderLabelProvider(new M);

	}

	public String[] setDefaultDate(String data) {
		String[] days = YDMATimeUtil.getZMonthDiffDay(-1, "yyyyMMdd");

		int yearCurrnet = Integer.valueOf(days[0].substring(0, 4));
		int monCurrnet = Integer.valueOf(days[0].substring(4, 6));
		int dayCurrnet = Integer.valueOf(days[0].substring(6, 8));

		dateTime_to.setYear(yearCurrnet);
		dateTime_to.setMonth(monCurrnet - 1);
		dateTime_to.setDay(dayCurrnet);

		int yearOff = Integer.valueOf(days[1].substring(0, 4));
		int monOff = Integer.valueOf(days[1].substring(4, 6));
		int dayOff = Integer.valueOf(days[1].substring(6, 8));

		dateTime_from.setYear(yearOff);
		dateTime_from.setMonth(monOff);
		dateTime_from.setDay(dayOff);

		switch (data) {
		case "오늘":
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date();
			String mTime = mSimpleDateFormat.format(currentTime);
			dateTime_from.setYear(Integer.parseInt(mTime.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(mTime.substring(4, 6)) - 1);
			dateTime_from.setDay(Integer.parseInt(mTime.substring(6, 8)));
			break;
		case "week1":
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date date = calendar.getTime();
			String aa = new SimpleDateFormat("yyyyMMdd").format(date);
			dateTime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
			dateTime_from.setDay(Integer.parseInt(aa.substring(6, 8)));
			break;
		case "month1":
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			date = calendar.getTime();
			aa = new SimpleDateFormat("yyyyMMdd").format(date);
			dateTime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
			dateTime_from.setDay(Integer.parseInt(aa.substring(6, 8)));
			break;
		case "month3":
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -3);
			date = calendar.getTime();
			aa = new SimpleDateFormat("yyyyMMdd").format(date);
			dateTime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
			dateTime_from.setDay(Integer.parseInt(aa.substring(6, 8)));
			break;
		case "month6":
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			date = calendar.getTime();
			aa = new SimpleDateFormat("yyyyMMdd").format(date);
			dateTime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
			dateTime_from.setDay(Integer.parseInt(aa.substring(6, 8)));
			break;
		default:
			break;
		}

		return days;

	}

	// 쇼핑몰선택 세팅하기
	private void setShopCode() {
		ShoppingMallDao dao = new ShoppingMallDao();
		String shop = "쇼핑몰선택,";
		try {
			List<List<String>> contents = dao.getShopDealTrade();
			for (List<String> list : contents) {
				shop += list.get(1) + ",";
			}
			cb_shopnm.setItems(shop.split(","));
			cb_shopnm.select(0);
			cb_select.setItems(shop.split(","));
			cb_select.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 검색
	protected void getSearchShoppingManager() {
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			List<String> list = new ArrayList<>();
			// 쇼핑몰선택0번 때문에
			if (cb_useyn.getSelectionIndex() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "사용여부를 체크후에 진행하여 주시기 바랍니다.");
				return;
			}
			if (cb_shopping.getSelectionIndex() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "검색항목을 체크후에 진행하여 주시기 바랍니다.");
				return;
			}
			int senddt = cb_senddt.getSelectionIndex();
			String prodFrom = YDMATimeUtil.getRoRddtDate(dateTime_from) + " 00:00:00";
			String prodTo = YDMATimeUtil.getRoRddtDate(dateTime_to) + " 23:59:59";
			int select = cb_select.getSelectionIndex();
			if (select != 0) {
				list = dao.getShopMstOneSelect(cb_select.getText());
			} else {
				list.add("0");
			}

			int interlock = cb_inerlock.getSelectionIndex();
			String useyn = cb_useyn.getText().equals("사용중") ? "Y" : "N";
			int result = cb_shopping.getSelectionIndex();
			String search = txt_search_condition.getText().trim();

			List<List<String>> contents = dao.getAdditionalInforMation(senddt, prodFrom, prodTo, select, interlock,
					useyn, result, search, list.get(0));
			if (contents.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "검색된 정보가 존재하지 않습니다.");
				tableViewer.setInput(null);
				return;
			}
			for (List<String> list2 : contents) {
				if (list2.get(4).equals("01")) {
					list2.set(4, "고정가판매");
				} else if (list2.get(4).equals("02")) {
					list2.set(4, "공동구매");
				} else {
					list2.set(4, "");
				}
			}
			tableViewer.setInput(contents);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 부가정보등록
	protected void openAdditionalInformation() {
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			List<String> list = dao.getShopMstOneSelect(cb_shopnm.getText());
			if (cb_shopnm.getSelectionIndex() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "선택된 정보가 없습니다. 정보를 선택후 진행하여 주시기 바랍니다.");
				return;
			}
			openAdditionSearchBean(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 쇼핑몰 분류
	private void openAdditionSearchBean(List<String> list) {

	}

	// 수정할때
	protected void opendetail() {
		ShoppingMallDao dao = new ShoppingMallDao();

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

	class MyLableProvider extends StyledCellLabelProvider implements ITableLabelProvider, ITableColorProvider {
		@Override
		public String getColumnText(Object element, int columnIndex) {
			String value = "";
			List<String> rowContent = (List<String>) element;
			value = rowContent.get(columnIndex);
			return value;
		}

		@Override
		public Color getBackground(java.lang.Object element, int columnIndex) {
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
}
