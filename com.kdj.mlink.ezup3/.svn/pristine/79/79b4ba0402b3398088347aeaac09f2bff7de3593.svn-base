package com.kdj.mlink.ezup3.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDto;

public class ShoppingMallCategory11stMappingDetail extends CommandDialog {

	String TITLE = "쇼핑몰카테고리등록";
	ShopCategoryMappingManager opener;

	String[][] column_name_dt = { { "쇼핑몰", "120" }, { "아이디", "150" }, { "제목", "300" }, { "쇼핑몰카테고리", "280" } };
	String[][] column_name_ok = { { "No.", "40" }, { "쇼핑몰코드", "100" }, { "쇼핑몰", "80" }, { "제목", "250" },
			{ "쇼핑몰카테고리", "150" }, { "카테고리(쇼핑몰)", "300" } };

	Button btnOk;
	Button btnCancel;
	Composite composite_opt;
	String prodcd;
	private GridTableViewer gtv_shopdt;
	private GridTableViewer gtv_shopok;
	private Grid gd_mapOk;
	private Grid gd_mapDt;
	private Composite composite;
	private Label lblNewLabel;
	private Composite composite_6;
	private Composite composite_7;
	private Composite composite_8;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Combo cb_date;
	private DateTime datetime_from;
	private DateTime datetime_to;
	private Label label_3;
	private Button btn_today;
	private Button btn_week;
	private Button btn_month1;
	private Button btn_month3;
	private Combo cb_shopping;
	private Combo cb_yesorno;
	private Button btn_shopsearch;
	private Combo cb_searchtitle;
	private Text txt_searchnm;
	String largecateg;
	String midiumcateg;
	String smallcateg;
	String detailcateg;
	List<String> list;
	String managercode;

	public ShoppingMallCategory11stMappingDetail(Shell shell, ShopCategoryMappingManager opener, List<String> list,
			String managercode) {
		super(shell);
		this.opener = opener;
		this.list = list;
		this.managercode = managercode;
//		this.midiumcateg = midiumcateg;
//		this.smallcateg = smallcateg;
//		this.detailcateg = detailcateg;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		composite = new Composite(container, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(3, false));

		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("매핑현황");

		gtv_shopdt = new GridTableViewer(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_mapDt = gtv_shopdt.getGrid();
		gd_mapDt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		gd_mapDt.setHeaderVisible(true);

		gtv_shopdt.setLabelProvider(new DetailMyLableProvider());
		gtv_shopdt.setContentProvider(new DetailMyContentProvider());

		Button btn_delete = new Button(composite, SWT.NONE);
		btn_delete.setText("선택삭제");
		// btn_delete.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDeleteCategMapping();
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("일 자");
		label.setAlignment(SWT.CENTER);

		composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		composite_6.setLayout(new GridLayout(8, false));

		cb_date = new Combo(composite_6, SWT.NONE);
		cb_date.setItems(new String[] { "등록일", "수정일" });
		cb_date.select(0);

		datetime_from = new DateTime(composite_6, SWT.BORDER);
		datetime_from.setYear(2019);
		datetime_from.setMonth(8);
		datetime_from.setDay(19);

		label_3 = new Label(composite_6, SWT.NONE);
		label_3.setText("~");

		datetime_to = new DateTime(composite_6, SWT.BORDER);
		datetime_to.setYear(2019);
		datetime_to.setMonth(8);
		datetime_to.setDay(19);

		btn_today = new Button(composite_6, SWT.NONE);
		btn_today.setText("오늘");
		btn_today.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("오늘");
			}
		});

		btn_week = new Button(composite_6, SWT.NONE);
		btn_week.setText("1주일");
		btn_week.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("week1");
			}
		});

		btn_month1 = new Button(composite_6, SWT.NONE);
		btn_month1.setText("한달");
		btn_month1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month1");
			}
		});

		btn_month3 = new Button(composite_6, SWT.NONE);
		btn_month3.setText("3개월");
		btn_month3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month3");
			}
		});

		label_2 = new Label(composite, SWT.NONE);
		label_2.setText("검색항목");
		label_2.setAlignment(SWT.CENTER);

		composite_7 = new Composite(composite, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		composite_7.setLayout(new GridLayout(2, false));

		cb_shopping = new Combo(composite_7, SWT.NONE);
		cb_shopping.setItems(new String[] { "쇼핑몰선택" });
		cb_shopping.select(0);

		cb_yesorno = new Combo(composite_7, SWT.NONE);
		cb_yesorno.setItems(new String[] { "사용여부", "사용중", "미사용" });
		cb_yesorno.select(0);

		label_1 = new Label(composite, SWT.NONE);
		label_1.setText("선택사항");
		label_1.setAlignment(SWT.CENTER);

		composite_8 = new Composite(composite, SWT.NONE);
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		composite_8.setLayout(new GridLayout(3, false));

		cb_searchtitle = new Combo(composite_8, SWT.NONE);
		cb_searchtitle.setItems(new String[] { "제목", "카테고리코드" });
		cb_searchtitle.select(0);

		txt_searchnm = new Text(composite_8, SWT.BORDER);
		GridData gd_txt_searchnm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_searchnm.widthHint = 400;
		txt_searchnm.setLayoutData(gd_txt_searchnm);

		btn_shopsearch = new Button(composite_8, SWT.NONE);
		btn_shopsearch.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		btn_shopsearch.setText("검색");
		btn_shopsearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCategoryShoppingMall();
			}
		});

		gtv_shopok = new GridTableViewer(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_mapOk = gtv_shopok.getGrid();
		gd_mapOk.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		gd_mapOk.setHeaderVisible(true);

		gtv_shopok.setLabelProvider(new MyLableProvider());
		gtv_shopok.setContentProvider(new MyContentProvider());

		Button btn_mapping = new Button(composite, SWT.NONE);
		btn_mapping.setText("선택매핑");
		// btn_mapping.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btn_mapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setProductMstShopCategoryUpdate();
			}
		});

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (String[] column : column_name_dt) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_mapDt, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}

		for (String[] column : column_name_ok) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_mapOk, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}

		setCategory();
		setDefaultDate();
		setShopnm();
		return container;

	}

	// 쇼핑몰이름선택하기
	private void setShopnm() {
		ShoppingMallDao dao = new ShoppingMallDao();
		String shop = "쇼핑몰선택,";
		try {
			List<List<String>> contents = dao.getShopDealTrade();
			for (List<String> list : contents) {
				shop += list.get(1) + ",";
			}
			cb_shopping.setItems(shop.split(","));
			cb_shopping.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 카테고리매핑삭제
	protected void setDeleteCategMapping() {
		if (gd_mapDt.getSelectionIndices().length == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "파일 선택후에 진행하여 주시기 바랍니다.");
			return;
		}
		List<ShoppingMallDto> contents = (List<ShoppingMallDto>) this.gtv_shopdt.getInput();
		ShoppingMallDto catelist = contents.get(gtv_shopdt.getGrid().getSelectionIndex());
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			int result = dao.CategMappingDelete(catelist, managercode);
			if (result != 0) {
				MessageDialog.openInformation(getShell(), TITLE, "정상적으로 삭제완료 되었습니다.");
				super.okPressed();
			} else {
				MessageDialog.openInformation(getShell(), TITLE, "삭제하는데 오류가 발생하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] setDefaultDate() {

		String[] days = YDMATimeUtil.getZMonthDiffDay(-1, "yyyyMMdd");

		int yearCurrnet = Integer.valueOf(days[0].substring(0, 4));
		int monCurrnet = Integer.valueOf(days[0].substring(4, 6));
		int dayCurrnet = Integer.valueOf(days[0].substring(6, 8));

		datetime_to.setYear(yearCurrnet);
		datetime_to.setMonth(monCurrnet - 1);
		datetime_to.setDay(dayCurrnet);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date date = calendar.getTime();
		String aa = new SimpleDateFormat("yyyyMMdd").format(date);
		datetime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
		datetime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
		datetime_from.setDay(Integer.parseInt(aa.substring(6, 8)));

		return days;
	}

	public String[] setDefaultDate(String data) {
		String[] days = YDMATimeUtil.getZMonthDiffDay(-1, "yyyyMMdd");

		int yearCurrnet = Integer.valueOf(days[0].substring(0, 4));
		int monCurrnet = Integer.valueOf(days[0].substring(4, 6));
		int dayCurrnet = Integer.valueOf(days[0].substring(6, 8));

		datetime_to.setYear(yearCurrnet);
		datetime_to.setMonth(monCurrnet - 1);
		datetime_to.setDay(dayCurrnet);

		int yearOff = Integer.valueOf(days[1].substring(0, 4));
		int monOff = Integer.valueOf(days[1].substring(4, 6));
		int dayOff = Integer.valueOf(days[1].substring(6, 8));

		datetime_from.setYear(yearOff);
		datetime_from.setMonth(monOff);
		datetime_from.setDay(dayOff);

		switch (data) {
		case "week1":
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date date = calendar.getTime();
			String aa = new SimpleDateFormat("yyyyMMdd").format(date);
			datetime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
			datetime_from.setMonth(Integer.parseInt(aa.substring(4, 6)) - 1);
			datetime_from.setDay(Integer.parseInt(aa.substring(6, 8)));
			break;
		case "month1":
			datetime_from.setMonth(monOff - 1);
			break;
		case "month3":
			datetime_from.setMonth(monOff - 3);
			break;
		case "month6":
			datetime_from.setMonth(monOff - 6);
			break;
		default:
			break;
		}

		return days;

	}

	// 처음시작시 마지막 카테고리
	private void setCategory() {
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			shopcheck = dao.getShopCodeAllList();
			List<List<String>> contents = dao.getCategMapping(list.get(0), managercode);
			List<ShoppingMallDto> list = dao.getShopCategoryCheck(contents);
			gtv_shopdt.setInput(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	List<List<String>> cate = new ArrayList<>();
	List<List<String>> idcheck = new ArrayList<>();
	List<List<String>> shopcheck = new ArrayList<>();

	// 검색클릭시 내용 가지고 오기
	protected void getCategoryShoppingMall() {
		ShoppingMallDao dao = new ShoppingMallDao();
		int data = cb_date.getSelectionIndex();
		String prodFrom = YDMATimeUtil.getRoRddtDate(datetime_from) + " 00:00:00";
		String prodTo = YDMATimeUtil.getRoRddtDate(datetime_to) + " 23:59:59";
		String use_yn = "";
		if (cb_yesorno.getSelectionIndex() != 0) {
			use_yn = cb_yesorno.getText().equals("사용중") ? "Y" : "N";
		}
		int searchcode = cb_searchtitle.getSelectionIndex();
		String serchnm = txt_searchnm.getText();
		if (cb_shopping.getSelectionIndex() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "쇼핑몰을 선택하신후에 진행하여 주시기 바랍니다.");
			return;
		}
		try {
			// 쇼핑몰카테고리 가지고 오기
//			cate = dao.getLargeCategory();
			shopcheck = dao.getShopCodeAllList();

			List<String> list = dao.getShopMstOneSelect(cb_shopping.getText());
			List<ShoppingMallDto> dto = dao.getCategoryList(list.get(0), data, prodFrom, prodTo, use_yn, searchcode,
					serchnm);
			if (dto.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "검색된 내용이 없습니다.");
				return;
			}
			gtv_shopok.setInput(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 매핑클릭시
	protected void setProductMstShopCategoryUpdate() {
		if (gd_mapOk.getSelectionIndex() < 0) {
			MessageDialog.openInformation(getShell(), TITLE, "선택된 정보가 없습니다. 정보를 선택후 진행하여 주시기 바랍니다.");
			return;
		}
		List<ShoppingMallDto> contents = (List<ShoppingMallDto>) this.gtv_shopok.getInput();
		ShoppingMallDto catelist = contents.get(gtv_shopok.getGrid().getSelectionIndex());
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			if (!dao.isExistCategoryMap(list, catelist, managercode)) {
				int result = dao.setProductMstShopCategoryUpdate(list, catelist, managercode);
				if (result != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "정상적으로 매핑이 완료 되었습니다.");
					super.okPressed();
				} else {
					MessageDialog.openInformation(getShell(), TITLE, "매핑저장시 오류가 발생하였습니다.");
				}
			} else {
				MessageDialog.openInformation(getShell(), TITLE, "이미 저장되어 있는 카테고리입니다.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void okPressed() {

	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// btnOk= createButton(parent, IDialogConstants.OK_ID, "저장", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "닫기", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(1030, 800);
	}

	class DetailMyContentProvider extends ArrayContentProvider implements IStructuredContentProvider {
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

	class DetailMyLableProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ShoppingMallDto dto = (ShoppingMallDto) element;
			String value = "";
			switch (columnIndex) {
			case 0:
				String shopnm = "";
				for (List<String> check : shopcheck) {
					if (check.get(1).equals(dto.getShopcd())) {
						shopnm = check.get(2);
					}
				}
				value = shopnm;
				break;
			case 1:
				value = "전체아이디";
				break;
			case 2:
				value = dto.getShopcatnm();
				break;
			case 3:
				value = !dto.getShopdetcatcd().equals("") ? dto.getShopdetcatcd()
						: !dto.getShopsmlcatcd().equals("") ? dto.getShopsmlcatcd()
								: !dto.getShopmidcatcd().equals("") ? dto.getShopmidcatcd()
										: !dto.getShoplagcatcd().equals("") ? dto.getShoplagcatcd() : "";
				break;
			default:
				value = "";
			}

			return value;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
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
			ShoppingMallDto dto = (ShoppingMallDto) element;
			String value = "";
			switch (columnIndex) {
			case 0:
				value = dto.getRowno();
				break;
			case 1:
				String shopnm = "";
				for (List<String> check : shopcheck) {
					if (check.get(1).equals(dto.getShopcd())) {
						shopnm = check.get(2);
					}
				}
				value = shopnm;
				break;
			case 2:
				value = dto.getMargetcateg();
				break;
			case 3:
				value = dto.getShopcatnm();
				break;
			case 4:
				value = !dto.getShopdetcatcd().equals("") ? dto.getShopdetcatcd()
						: !dto.getShopsmlcatcd().equals("") ? dto.getShopsmlcatcd()
								: !dto.getShopmidcatcd().equals("") ? dto.getShopmidcatcd()
										: !dto.getShoplagcatcd().equals("") ? dto.getShoplagcatcd() : "";
				break;
			case 5:
				String lar = "";
				String mid = "";
				String sml = "";
				String det = "";
				for (List<String> list : cate) {
					if (list.get(0).equals("1")) {
						if (list.get(2).equals(dto.getShoplagcatcd())) {
							lar = list.get(1);
						}
					}
					if (list.get(0).equals("2")) {
						if (list.get(2).equals(dto.getShopmidcatcd())) {
							mid = list.get(1);
						}
					}
					if (list.get(0).equals("3")) {
						if (list.get(2).equals(dto.getShopsmlcatcd())) {
							sml = list.get(1);
						}
					}
					if (list.get(0).equals("4")) {
						if (list.get(2).equals(dto.getShopdetcatcd())) {
							det = list.get(1);
						}
					}
				}
				value = String.format("%s%s%s%s", lar.equals("") ? "" : lar, mid.equals("") ? "" : " > " + mid,
						sml.equals("") ? "" : " > " + sml, det.equals("") ? "" : " > " + det);
				break;
			default:
				value = "";
			}

			return value;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
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
