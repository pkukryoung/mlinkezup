package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridEditor;
import org.eclipse.nebula.widgets.grid.GridItem;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.data.dao.CategoryDao;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;

public class ShopCategoryMappingManager extends Composite {

	public static String ID = "com.kdj.mlink.ezup3.command.ShopCategoryMappingManager.ID";
	public static String TITLE = "카테고리매핑관리";

	String[][] column_name_opt = { { "분류코드", "60" }, { "순서", "45" }, { "분류명", "250" }, { "상태", "60" }, { "설명", "0" },
			{ "상위코드", "0" }, { "네이버코드", "0" }, { "수정", "70" }, { "매핑", "70" }, { "", "30" } };

	int selectedRowNo = -1;
	private Group group;
	private Grid grid;
	private Button btn_lrcate;
	private Group group_1;
	private Group group_2;
	private Group group_3;
	private GridTableViewer gtv_lgcate;
	private GridTableViewer gtv_mdcate;
	private GridTableViewer gtv_smcate;
	private GridTableViewer gtv_dtcate;
	private Grid gd_lgcate;
	private Grid gd_mdcate;
	private Grid gd_smcate;
	private Grid gd_dtcate;
	private Button btn_mdcate;
	private Button btn_smcate;
	private Button btn_dtcate;
	Button laredit;
	Button larmap;
	Button midedit;
	Button midmap;
	Button smledit;
	Button smlmap;
	Button detedit;
	Button detmap;
	private Button today;
	private Button week1;
	private Button month1;
	private Label label;
	private Label label_4;
	private Label label_1;
	private Combo combo;
	private Button btnCheckButton;
	private Label label_2;
	private Label label_3;
	private Label label_5;
	private Label label_6;

	public ShopCategoryMappingManager(Composite parent, int style) {
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
		composite_1.setLayout(new GridLayout(15, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group_0 = new Group(composite_1, SWT.NONE);
		group_0.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		group_0.setText("카테고리관리");
		group_0.setLayout(new GridLayout(11, false));

		Label lblNewLabel = new Label(group_0, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("선택사항");

		combo = new Combo(group_0, SWT.NONE);
		combo.setItems(new String[] { "\uC1FC\uD551\uBAB0\uC120\uD0DD" });
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 192;
		combo.setLayoutData(gd_combo);
		combo.select(0);

		btnCheckButton = new Button(group_0, SWT.CHECK);
		btnCheckButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnCheckButton.setText("체크시 매핑현황 다운도드");
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);

		label = new Label(group_0, SWT.NONE);
		label_4 = new Label(group_0, SWT.NONE);

		label_1 = new Label(group_0, SWT.NONE);
		label_1.setText("다운로드");

		today = new Button(group_0, SWT.NONE);
		GridData gd_today = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_today.widthHint = 154;
		today.setLayoutData(gd_today);
		today.setText("샘플파일");
//		today.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		today.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				setDefaultDate("오늘");
			}
		});

		week1 = new Button(group_0, SWT.NONE);
		GridData gd_week1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_week1.widthHint = 134;
		week1.setLayoutData(gd_week1);
		week1.setText("수정파일");
//		week1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		week1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				setDefaultDate("week1");
			}
		});

		month1 = new Button(group_0, SWT.NONE);
		GridData gd_month1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_month1.widthHint = 157;
		month1.setLayoutData(gd_month1);
		month1.setText("대량등록/수정");
//		month1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				setDefaultDate("month1");
			}
		});
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(group_0, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Composite composite_2 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(4, false);

		gl_composite_2.horizontalSpacing = 0;
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.verticalSpacing = 0;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setBounds(0, 0, 64, 64);

		group = new Group(composite_2, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("대분류관리");
		group.setLayout(new GridLayout(2, false));

		label_2 = new Label(group, SWT.CENTER);
//		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setFont(SWTResourceManager.getFont("맑은 고딕", 15, SWT.NORMAL));
		GridData gd_label_2 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_label_2.widthHint = 400;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("");

		btn_lrcate = new Button(group, SWT.CENTER);
		btn_lrcate.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 2, 1));
		btn_lrcate.setText("신규등록");
//		btn_lrcate.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btn_lrcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("1");

			}
		});

		gtv_lgcate = new GridTableViewer(group, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_lgcate = gtv_lgcate.getGrid();
		gd_lgcate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		gd_lgcate.setHeaderVisible(true);
		gd_lgcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategmidium();
			}
		});

		group_1 = new Group(composite_2, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_1.setText("중분류관리");
		group_1.setLayout(new GridLayout(2, false));

		label_3 = new Label(group_1, SWT.CENTER);
//		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setFont(SWTResourceManager.getFont("맑은 고딕", 15, SWT.NORMAL));
		GridData gd_label_3 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_label_3.widthHint = 400;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("");

		btn_mdcate = new Button(group_1, SWT.CENTER);
		btn_mdcate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		btn_mdcate.setText("신규등록");
//		btn_mdcate.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btn_mdcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("2");
			}
		});

		gtv_mdcate = new GridTableViewer(group_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_mdcate = gtv_mdcate.getGrid();
		gd_mdcate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		gd_mdcate.setHeaderVisible(true);
		gd_mdcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategSmall();
			}
		});

		group_2 = new Group(composite_2, SWT.NONE);
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_2.setText("소분류관리");
		group_2.setLayout(new GridLayout(2, false));

		label_5 = new Label(group_2, SWT.CENTER);
//		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setFont(SWTResourceManager.getFont("맑은 고딕", 15, SWT.NORMAL));
		GridData gd_label_5 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_label_5.widthHint = 400;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("");

		btn_smcate = new Button(group_2, SWT.CENTER);
		btn_smcate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		btn_smcate.setText("신규등록");
//		btn_smcate.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btn_smcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("3");
			}
		});

		gtv_smcate = new GridTableViewer(group_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_smcate = gtv_smcate.getGrid();
		gd_smcate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		gd_smcate.setHeaderVisible(true);
		gd_smcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategDetail();
			}
		});

		group_3 = new Group(composite_2, SWT.NONE);
		group_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_3.setText("세분류관리");
		group_3.setLayout(new GridLayout(2, false));

		label_6 = new Label(group_3, SWT.CENTER);
//		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setFont(SWTResourceManager.getFont("맑은 고딕", 15, SWT.NORMAL));
		GridData gd_label_6 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_label_6.widthHint = 400;
		label_6.setLayoutData(gd_label_6);
		label_6.setText("");

		btn_dtcate = new Button(group_3, SWT.CENTER);
		btn_dtcate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		btn_dtcate.setText("신규등록");
//		btn_dtcate.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btn_dtcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("4");
			}
		});
		gtv_dtcate = new GridTableViewer(group_3, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		gd_dtcate = gtv_dtcate.getGrid();
		gd_dtcate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		gd_dtcate.setHeaderVisible(true);
		gd_dtcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategDetailSelf();
			}
		});

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);
		MyCellRenderer myCellRenderer = new MyCellRenderer();

		for (int i = 0; i < column_name_opt.length; i++) {
			String[] column = column_name_opt[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_lgcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);
			if (i == 9) {
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}
		for (int i = 0; i < column_name_opt.length; i++) {
			String[] column = column_name_opt[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_mdcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);
			if (i == 9) {
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}
		for (int i = 0; i < column_name_opt.length; i++) {
			String[] column = column_name_opt[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_smcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);
			if (i == 9) {
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}
		for (int i = 0; i < column_name_opt.length; i++) {
			String[] column = column_name_opt[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_dtcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);
			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);
			if (i == 9) {
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}

		}

		gtv_lgcate.setContentProvider(new MyContentProvider());
		gtv_lgcate.setLabelProvider(new MyLableProvider());

		gtv_mdcate.setContentProvider(new MyContentProvider());
		gtv_mdcate.setLabelProvider(new MyLableProvider());

		gtv_smcate.setContentProvider(new MyContentProvider());
		gtv_smcate.setLabelProvider(new MyLableProvider());

		gtv_dtcate.setContentProvider(new MyContentProvider());
		gtv_dtcate.setLabelProvider(new MyLableProvider());

		setShoppingNameCheck();

		setCategory();

	}

	private void setShoppingNameCheck() {
		ShoppingMallDao dao = new ShoppingMallDao();
		String shop = "쇼핑몰선택,";
		try {
			List<List<String>> contents = dao.getShopDealTrade();
			for (List<String> list : contents) {
				shop += list.get(1) + ",";
			}

			combo.setItems(shop.split(","));
			combo.select(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 카테고리매핑
	protected void setCategoryMapping(String code, String value) {
		try {
			if (code.equals("1")) {
				gd_lgcate.setSelection(Integer.parseInt(value));
				if (gd_lgcate.getSelectionIndex() < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "파일을 선택하신후에 진행하여 주시기 바랍니다.");
					return;
				}

				List<List<String>> contents = (List<List<String>>) this.gtv_lgcate.getInput();
				List<String> list = contents.get(gtv_lgcate.getGrid().getSelectionIndex());

				CategoryDao dao = new CategoryDao();
				List<List<String>> midcontents = new ArrayList<>();
				midcontents = dao.midiumCategory(list.get(0));
				if (midcontents.size() != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "마지막 분류에서만 매핑이 가능합니다.");
				} else {
					ShoppingMallCategory11stMappingDetail d = new ShoppingMallCategory11stMappingDetail(getShell(),
							this, list, "1");
					d.open();
				}
			}
			if (code.equals("2")) {
				gd_mdcate.setSelection(Integer.parseInt(value));
				if (gd_mdcate.getSelectionIndex() < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "파일을 선택하신후에 진행하여 주시기 바랍니다.");
					return;
				}

				List<List<String>> contents = (List<List<String>>) this.gtv_mdcate.getInput();
				List<String> list = contents.get(gtv_mdcate.getGrid().getSelectionIndex());

				CategoryDao dao = new CategoryDao();
				List<List<String>> midcontents = new ArrayList<>();
				midcontents = dao.smallCategory(list.get(0));
				if (midcontents.size() != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "마지막 분류에서만 매핑이 가능합니다.");
				} else {
					ShoppingMallCategory11stMappingDetail d = new ShoppingMallCategory11stMappingDetail(getShell(),
							this, list, "2");
					d.open();
				}
			}
			if (code.equals("3")) {
				gd_smcate.setSelection(Integer.parseInt(value));
				if (gd_smcate.getSelectionIndex() < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "파일을 선택하신후에 진행하여 주시기 바랍니다.");
					return;
				}

				List<List<String>> contents = (List<List<String>>) this.gtv_smcate.getInput();
				List<String> list = contents.get(gtv_smcate.getGrid().getSelectionIndex());

				CategoryDao dao = new CategoryDao();
				List<List<String>> midcontents = new ArrayList<>();
				midcontents = dao.detailCategory(list.get(0));
				if (midcontents.size() != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "마지막 분류에서만 매핑이 가능합니다.");
				} else {
					ShoppingMallCategory11stMappingDetail d = new ShoppingMallCategory11stMappingDetail(getShell(),
							this, list, "3");
					d.open();
				}
			}
			if (code.equals("4")) {
				gd_dtcate.setSelection(Integer.parseInt(value));
				if (gd_dtcate.getSelectionIndex() < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "파일을 선택하신후에 진행하여 주시기 바랍니다.");
					return;
				}

				List<List<String>> contents = (List<List<String>>) this.gtv_dtcate.getInput();
				List<String> list = contents.get(gtv_dtcate.getGrid().getSelectionIndex());
				ShoppingMallCategory11stMappingDetail d = new ShoppingMallCategory11stMappingDetail(getShell(), this,
						list, "4");
				d.open();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 세팅하기
	private void setCategory() {
		CategoryDao dao = new CategoryDao();
		try {
			List<List<String>> lagcontents = dao.largeCategory();
			if (lagcontents.size() != 0) {
				this.gtv_lgcate.setInput(lagcontents);
				setGridrefresh("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}////////
		// 그리드변경??

	private void setGridrefresh(String code) {

		if (code.equals("1")) {
			Grid grid = gtv_lgcate.getGrid();
			GridItem[] items = grid.getItems();

			for (int i = 0; i < items.length; ++i) {
				GridEditor editor = new GridEditor(grid);
				GridItem item = items[i];
				// Button
				laredit = new Button(gd_lgcate, SWT.PUSH);
				laredit.setText("수정");
				laredit.setSize(20, 20);
				laredit.pack();
				editor.setEditor(laredit, item, 7);
				editor.horizontalAlignment = SWT.CENTER;
				editor.grabHorizontal = true;
				editor.minimumWidth = 50;

				GridEditor editor1 = new GridEditor(grid);
				GridItem item1 = items[i];
				larmap = new Button(gd_lgcate, SWT.PUSH);
				larmap.setText("매핑");
				larmap.setSize(20, 20);
				larmap.pack();
				editor1.setEditor(larmap, item1, 8);

				editor1.horizontalAlignment = SWT.CENTER;
				editor1.grabHorizontal = true;
				editor1.minimumWidth = 50;
				laredit.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
				larmap.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
				larmap.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						setCategoryMapping("1", this.value);
					}
				});
				laredit.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						categoryupdate("1", this.value);
					}

				});
			}

		}
		if (code.equals("2")) {
			Grid grid = gtv_mdcate.getGrid();
			GridItem[] items = grid.getItems();
			for (int i = 0; i < items.length; ++i) {
				GridEditor editor = new GridEditor(grid);
				GridItem item = items[i];
				// Button
				laredit = new Button(gd_mdcate, SWT.PUSH);
				laredit.setText("수정");
				laredit.setSize(20, 20);
				laredit.pack();
				editor.setEditor(laredit, item, 7);
				editor.horizontalAlignment = SWT.CENTER;
				editor.grabHorizontal = true;
				editor.minimumWidth = 50;

				GridEditor editor1 = new GridEditor(grid);
				GridItem item1 = items[i];
				larmap = new Button(gd_mdcate, SWT.PUSH);
				larmap.setText("매핑");
				larmap.setSize(20, 20);
				larmap.pack();
				editor1.setEditor(larmap, item1, 8);

				editor1.horizontalAlignment = SWT.CENTER;
				editor1.grabHorizontal = true;
				editor1.minimumWidth = 50;
				laredit.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
				larmap.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
				larmap.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						setCategoryMapping("2", this.value);
					}
				});
				laredit.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						categoryupdate("2", this.value);
					}

				});
			}
		}
		if (code.equals("3")) {
			Grid grid = gtv_smcate.getGrid();
			GridItem[] items = grid.getItems();
			for (int i = 0; i < items.length; ++i) {
				GridEditor editor = new GridEditor(grid);
				GridItem item = items[i];
				// Button
				laredit = new Button(gd_smcate, SWT.PUSH);
				laredit.setText("수정");
				laredit.setSize(20, 20);
				laredit.pack();
				editor.setEditor(laredit, item, 7);
				editor.horizontalAlignment = SWT.CENTER;
				editor.grabHorizontal = true;
				editor.minimumWidth = 50;

				GridEditor editor1 = new GridEditor(grid);
				GridItem item1 = items[i];
				larmap = new Button(gd_smcate, SWT.PUSH);
				larmap.setText("매핑");
				larmap.setSize(20, 20);
				larmap.pack();
				editor1.setEditor(larmap, item1, 8);

				editor1.horizontalAlignment = SWT.CENTER;
				editor1.grabHorizontal = true;
				editor1.minimumWidth = 50;
				laredit.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
				larmap.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
				larmap.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						setCategoryMapping("3", this.value);
					}
				});
				laredit.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						categoryupdate("3", this.value);
					}

				});
			}
		}
		if (code.equals("4")) {

			Grid grid = gtv_dtcate.getGrid();
			GridItem[] items = grid.getItems();

//			gd_dtcate.disposeAllItems();

			for (int i = 0; i < items.length; ++i) {
				GridItem item = items[i];

				GridEditor editor = new GridEditor(grid);
				// Button
				laredit = new Button(gd_dtcate, SWT.PUSH);
				laredit.setText("수정");
				laredit.setSize(20, 20);
				laredit.pack();
				laredit.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/changeOperation.gif"));
				editor.setEditor(laredit, item, 7);
				editor.horizontalAlignment = SWT.CENTER;
				editor.grabHorizontal = true;
				editor.minimumWidth = 50;

				GridEditor editor1 = new GridEditor(grid);
				GridItem item1 = items[i];
				larmap = new Button(gd_dtcate, SWT.PUSH);
				larmap.setText("매핑");
				larmap.setSize(20, 20);
				larmap.pack();
				larmap.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
				editor1.setEditor(larmap, item1, 8);
				editor1.horizontalAlignment = SWT.LEFT;
				editor1.grabHorizontal = true;
				editor1.minimumWidth = 50;

				larmap.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						setCategoryMapping("4", this.value);
					}
				});
				laredit.addSelectionListener(new CSelectionAdapter("M", String.valueOf(i)) {
					@Override
					public void widgetSelected(SelectionEvent e) {
						categoryupdate("4", this.value);
					}
				});
			}
		}
	}

	// 대분류선택시중분류가지고오기
	private void setCategmidium() {
		this.gtv_smcate.setInput(null);
		this.gtv_dtcate.setInput(null);
		List<List<String>> contents = (List<List<String>>) this.gtv_lgcate.getInput();
		List<String> list = contents.get(gtv_lgcate.getGrid().getSelectionIndex());

		label_2.setText(list.get(2));
		label_3.setText("");
		label_5.setText("");
		label_6.setText("");

		CategoryDao dao = new CategoryDao();

		List<List<String>> midcontents = new ArrayList<>();
		try {
			midcontents = dao.midiumCategory(list.get(0));
			if (midcontents.size() != 0) {
				this.gtv_mdcate.setInput(midcontents);

//				setGridrefresh("1");
				setGridrefresh("2");
//				setGridrefresh("3");
//				setGridrefresh("4");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}////////////
		// 중분류선택시소분류가지고오기

	private void setCategSmall() {
		this.gtv_dtcate.setInput(null);
		List<List<String>> contents = (List<List<String>>) this.gtv_mdcate.getInput();
		List<String> list = contents.get(gtv_mdcate.getGrid().getSelectionIndex());

		label_3.setText(list.get(2));
		label_5.setText("");
		label_6.setText("");

		CategoryDao dao = new CategoryDao();
		try {
			List<List<String>> smlcontents = dao.smallCategory(list.get(0));
			if (smlcontents.size() != 0) {
				this.gtv_smcate.setInput(smlcontents);
				setGridrefresh("3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}///////
		// 소분류선택시세분류가지고오기

	private void setCategDetail() {
		List<List<String>> contents = (List<List<String>>) this.gtv_smcate.getInput();
		List<String> list = contents.get(gtv_smcate.getGrid().getSelectionIndex());

		label_5.setText(list.get(2));
		label_6.setText("");

		CategoryDao dao = new CategoryDao();
		try {

			List<List<String>> detcontents = dao.detailCategory(list.get(0));
			if (detcontents.size() != 0) {
				this.gtv_dtcate.setInput(detcontents);
				setGridrefresh("4");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 소분류선택시세분류가지고오기
	private void setCategDetailSelf() {
		List<List<String>> contents = (List<List<String>>) this.gtv_dtcate.getInput();
		List<String> list = contents.get(gtv_dtcate.getGrid().getSelectionIndex());

		label_6.setText(list.get(2));
//		setGridrefresh("4");

	}

	private void openInsertCategory(String cate) {
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		try {
			if (cate.equals("2")) {
				int lagnum = gtv_lgcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "대분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_lgcate.getInput();
				list = contents_opt.get(gtv_lgcate.getGrid().getSelectionIndex());

			} else if (cate.equals("3")) {
				int lagnum = gtv_mdcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "중분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_mdcate.getInput();
				list = contents_opt.get(gtv_mdcate.getGrid().getSelectionIndex());

			} else if (cate.equals("4")) {
				int lagnum = gtv_smcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "소분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_smcate.getInput();
				list = contents_opt.get(gtv_smcate.getGrid().getSelectionIndex());

			}
			ProductCategoryManagerDialog d = new ProductCategoryManagerDialog(this.getShell(), this, cate, list, list1,
					true);
			d.open();

			setCategory();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 업데이트
	private void categoryupdate(String category, String value) {
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		List<List<String>> contents_opt = new ArrayList<>();
		List<List<String>> contents_opt1 = new ArrayList<>();
		int lagnum = gtv_lgcate.getGrid().getSelectionIndex();
		int midnum = gtv_mdcate.getGrid().getSelectionIndex();
		int smlnum = gtv_smcate.getGrid().getSelectionIndex();
		int detnum = gtv_dtcate.getGrid().getSelectionIndex();
		if (category.equals("1")) {
			gd_lgcate.setSelection(Integer.parseInt(value));
			contents_opt = (List<List<String>>) this.gtv_lgcate.getInput();
			list = contents_opt.get(gtv_lgcate.getGrid().getSelectionIndex());
		} else if (category.equals("2")) {
			gd_mdcate.setSelection(Integer.parseInt(value));
			contents_opt = (List<List<String>>) this.gtv_mdcate.getInput();
			list = contents_opt.get(gtv_mdcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_lgcate.getInput();
			list1 = contents_opt1.get(lagnum);
		}
		if (category.equals("3")) {
			gd_smcate.setSelection(Integer.parseInt(value));
			contents_opt = (List<List<String>>) this.gtv_smcate.getInput();
			list = contents_opt.get(gtv_smcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_mdcate.getInput();
			list1 = contents_opt1.get(midnum);
		}
		if (category.equals("4")) {
			gd_dtcate.setSelection(Integer.parseInt(value));
			contents_opt = (List<List<String>>) this.gtv_dtcate.getInput();
			list = contents_opt.get(gtv_dtcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_smcate.getInput();
			list1 = contents_opt1.get(smlnum);
		}

		ProductCategoryManagerDialog d = new ProductCategoryManagerDialog(this.getShell(), this, category, list, list1,
				false);
		d.open();

		setCategory();

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
