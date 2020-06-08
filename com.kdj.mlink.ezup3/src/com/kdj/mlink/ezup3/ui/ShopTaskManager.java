package com.kdj.mlink.ezup3.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMAGridColumn;
import com.kdj.mlink.ezup3.common.YDMAGridColumnList;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;
import com.kdj.mlink.ezup3.shop.common.CodeItem;
import com.kdj.mlink.ezup3.shop.common.ShopCombo;
import com.kdj.mlink.ezup3.shop.common.ShopErrorCode;
import com.kdj.mlink.ezup3.shop.common.WorkStatus;
import com.kdj.mlink.ezup3.shop.common.WorkTaskHelper;
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopDeliveryDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopPreferencesDto;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

/**
 * 엑셀의 내용을 체크하여 알려줌
 *
 * @author kdj01
 *
 */
public class ShopTaskManager extends Composite {
	public static String ID = "com.kdj.mlink.ezup3.command.ShopTaskManager.ID";

	/// 그리드 데이타
	public static List<ShopWorkDto> datasource = null;

	ShopOrderMstDto selectRowMst = null;
	List<List<String>> deleviryList = null; // 택배사 코드..

	int selectOrdSeq = 0;

	Image downloadImage = ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/download.png");

	YDMAGridColumnList col_list = new YDMAGridColumnList(); // 그리드 컬럼리스트 ..

	public static String TITLE = "작업관리";

	int selectedRowNoMst = -1;
	String orderListFullPath;
	String tabmenu = "";
	String orderDayStrFrom;
	String orderDayStrTo;
	Grid grid;
	private Grid gd_all;
	Composite composite_table;
	GridTableViewer tableViewer;
	private Tree tree;
	private Composite composite_5;
	static GridTableViewer gtv_all;
	private SashForm sashForm;
	private ScrolledComposite scrolledComposite;
	private CTabFolder tabFolder_1;
	TreeItem allitem;
	private static CTabItem tabItem_2;
	private Button btn_all;
	private Button btn_wait;
	private Button btn_progress;
	private Button btn_error;
	private Button btn_complete;
	private Button btn_cancel;
	private Button btn_fail;
	GridData gd_tabFolder_2;
	List<ShopDeliveryDto> list;
	List<List<String>> shopcheck = new ArrayList<>();

	ShopCombo scb_cmbOnUrl;
	private static CTabItem tabItem;
	private static CTabItem tabItem_1;
	private static CTabItem tabItem_3;
	private Composite composite_9;
	private Label label;
	private ToolBar toolBar;
	private ToolItem tb_refresh;
	private ToolItem tb_cancel;
	private ToolItem tb_delete;
	private Label label_1;
	private Composite composite_3;
	private Composite composite_4;
	private Composite composite_6;
	private ToolBar toolBar_2;
	private ToolBar toolBar_3;
	private ToolItem tb_dbdown;
	private ToolItem tb_dbup;
	private ToolItem tb_dbrefresh;
	private ToolItem tb_dbdown1;
	private ToolItem tb_dbup1;
	private ToolItem tb_dbrefresh1;
	private Label label_2;
	private Composite composite_7;
	private Label label_3;
	private Composite composite_8;
	private Label lb_pro2_1;
	private ProgressBar pro2_1;
	private Label lb_pro2_2;
	private Label label_4;
	private Composite composite_10;
	private Label lblNewLabel_4;
	private Spinner sp_thread;
	private Label lblNewLabel_5;
	private Label lb_power1;
	private Label lb_power;
	private Composite composite_11;
	private Label lb_refresh;
	private Label lb_cancel;
	private Label lb_delete;
	private ToolItem tb_log;
	private ToolItem tb_log1;
	private TabFolder tabFolder;
	private TabItem tabItem_4;
	private Grid gd_log;
	private GridTableViewer gridTableViewer_1;
	private GridTableViewer gtv_log;
	static GridTableViewer gtv_prod;
	Grid gd_prod;
	static GridTableViewer gtv_order;
	Grid gd_order;
	static GridTableViewer gtv_quest;
	Grid gd_quest;
	ProgressBar pro1_1;
	private ToolBar toolBar_1;
	private ToolItem tb_power;
	private TabItem tabItem_5;
	private Text txt_log;
	private Text txt_questlog;
	private Text txt_orderlog;
	private Text txt_alllog;
	private Text txt_prodlog;

	/*
	 * 그리드 컬럼을 설정한다. /*fileds.add("REG_DATE");//수집일자 //라벨부
	 */

	public void setGridColumns() {
		col_list.add(0, "WORKSEQ", "일련번호", 0);
		col_list.add(1, "SHOPCD", "쇼핑몰", 100);
		col_list.add(2, "USER_ID", "판매자ID", 120);
		col_list.add(3, "WORKCD", "작업#", 120);
		col_list.add(4, "WORKDETCD", "작업명", 100);
		col_list.add(5, "COMPNO", "추가정보", 200);
		col_list.add(6, "INSERTID", "작업자", 100);
		col_list.add(7, "WORKDATEFROM", "시작시간", 200);
		col_list.add(8, "WORKDATETO", "종료시간", 200);
		col_list.add(9, "WORKDLAPSED", "경과시간", 120);
		col_list.add(10, "WORKSTATS", "상태", 150);
		col_list.add(11, "WORKCURRRATE", "진행률", 200);
		col_list.add(12, "WORKMESSAGE", "메세지", 500);

	}

	/*
	 * 생성자.
	 */
	public ShopTaskManager(Composite parent, int style) {
		super(parent, style);
		setGridColumns(); // 그리드 컬럼설정.
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		composite.setLayout(gl_composite);

		composite_table = new Composite(composite, SWT.NONE);
		composite_table.setLayout(new GridLayout(3, false));
		composite_table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_table.setBounds(0, 0, 64, 64);

		Composite composite_1 = new Composite(composite_table, SWT.NONE);
		composite_1.setLayout(new GridLayout(11, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(5, false));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));

		btn_all = new Button(composite_3, SWT.NONE);
		btn_all.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));
		btn_all.setText("전체보기");
		btn_all.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.전체));
		btn_all.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TreeItem[] treeItem = tree.getSelection();

				if (treeItem.length == 0) {
					tree.select(tree.getItem(0));
					processCommand(tree.getItem(0), tabFolder_1.getSelectionIndex());
				} else {
					if (treeItem[0].equals(tree.getItem(0))) {
						tree.select(tree.getItem(0));
						processCommand(tree.getItem(0), tabFolder_1.getSelectionIndex());
					} else {

						if (!treeItem[0].getParent().equals(tree.getItem(0))) {
							tree.select(treeItem[0].getParentItem());
							processCommand(treeItem[0].getParentItem(), tabFolder_1.getSelectionIndex());
						}
					}
				}
			}
		});

		btn_wait = new Button(composite_3, SWT.NONE);
		btn_wait.setText("대기");
		btn_wait.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.대기));
		btn_wait.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.대기, tabFolder_1.getSelectionIndex());
			}
		});

		btn_progress = new Button(composite_3, SWT.NONE);
		btn_progress.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));
		btn_progress.setText("진행중");
		btn_progress.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.진행중));
		btn_progress.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.진행중, tabFolder_1.getSelectionIndex());
			}
		});

		btn_error = new Button(composite_3, SWT.NONE);
		btn_error.setText("오류");
		btn_error.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.오류));
		btn_error.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.오류, tabFolder_1.getSelectionIndex());
			}
		});

		btn_complete = new Button(composite_3, SWT.NONE);
		btn_complete.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));
		btn_complete.setText("완료");
		btn_complete.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.완료));
		btn_complete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.완료, tabFolder_1.getSelectionIndex());
			}
		});

		btn_cancel = new Button(composite_3, SWT.NONE);
		btn_cancel.setText("취소");
		btn_cancel.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.취소));
		btn_cancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.취소, tabFolder_1.getSelectionIndex());
			}
		});

		btn_fail = new Button(composite_3, SWT.NONE);
		btn_fail.setText("실패");
		btn_fail.setBackground(WorkStatus.getInstance().workfindColor(WorkStatus.실패));
		btn_fail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fiterStatusGrid(WorkStatus.실패, tabFolder_1.getSelectionIndex());
			}
		});

		label = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		toolBar = new ToolBar(composite_4, SWT.FLAT | SWT.RIGHT);

		tb_refresh = new ToolItem(toolBar, SWT.NONE);
		tb_refresh.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refreshtask.ico"));

		tb_cancel = new ToolItem(toolBar, SWT.NONE);
		tb_cancel.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/canceltask.ico"));

		tb_delete = new ToolItem(toolBar, SWT.NONE);
		tb_delete.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/deletetask.ico"));

		composite_11 = new Composite(composite_4, SWT.NONE);
		composite_11.setLayout(new GridLayout(3, false));
		composite_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lb_refresh = new Label(composite_11, SWT.NONE);
		lb_refresh.setAlignment(SWT.CENTER);
		lb_refresh.setText("새로고침");

		lb_cancel = new Label(composite_11, SWT.NONE);
		lb_cancel.setAlignment(SWT.CENTER);
		lb_cancel.setText("    취소    ");

		lb_delete = new Label(composite_11, SWT.NONE);
		lb_delete.setAlignment(SWT.CENTER);
		lb_delete.setText("    삭제    ");

		label_1 = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		composite_6 = new Composite(composite_1, SWT.NONE);
		composite_6.setLayout(new GridLayout(1, false));
		composite_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		toolBar_2 = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		tb_dbdown = new ToolItem(toolBar_2, SWT.NONE);
		tb_dbdown.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/dbdown.ico"));

		tb_dbup = new ToolItem(toolBar_2, SWT.NONE);
		tb_dbup.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/dbup.ico"));

		tb_dbrefresh = new ToolItem(toolBar_2, SWT.NONE);
		tb_dbrefresh.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/dbrefresh.ico"));

		tb_log = new ToolItem(toolBar_2, SWT.NONE);
		tb_log.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/log.ico"));

		toolBar_3 = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);

		tb_dbdown1 = new ToolItem(toolBar_3, SWT.NONE);
		tb_dbdown1.setText("DB 백업");

		tb_dbup1 = new ToolItem(toolBar_3, SWT.NONE);
		tb_dbup1.setText(" DB 복구 ");

		tb_dbrefresh1 = new ToolItem(toolBar_3, SWT.NONE);
		tb_dbrefresh1.setText("DB최적화");

		tb_log1 = new ToolItem(toolBar_3, SWT.NONE);
		tb_log1.setText("로그삭제");

		label_2 = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		composite_7 = new Composite(composite_1, SWT.NONE);
		composite_7.setLayout(new GridLayout(1, false));
		composite_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		Label lb_pro1_1 = new Label(composite_7, SWT.NONE);
		lb_pro1_1.setText("0 / 100 (잔여 : 1000)");

		pro1_1 = new ProgressBar(composite_7, SWT.NONE);
		pro1_1.setSelection(10);

		Label lb_pro1_2 = new Label(composite_7, SWT.NONE);
		lb_pro1_2.setText("추가요청  버전업그레이드");

		label_3 = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_3.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		composite_8 = new Composite(composite_1, SWT.NONE);
		composite_8.setLayout(new GridLayout(1, false));
		composite_8.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		lb_pro2_1 = new Label(composite_8, SWT.NONE);
		lb_pro2_1.setText("0 / 100 (잔여 : 1000)");

		pro2_1 = new ProgressBar(composite_8, SWT.NONE);
		pro2_1.setSelection(30);

		lb_pro2_2 = new Label(composite_8, SWT.NONE);
		lb_pro2_2.setText("주문무제한 적용하기");

		label_4 = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_4.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		composite_10 = new Composite(composite_1, SWT.NONE);
		composite_10.setLayout(new GridLayout(2, false));
		composite_10.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		lblNewLabel_4 = new Label(composite_10, SWT.NONE);
		lblNewLabel_4.setText("동시 실행 갯수");

		toolBar_1 = new ToolBar(composite_10, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		tb_power = new ToolItem(toolBar_1, SWT.NONE);
		tb_power.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/power.ico"));

		sp_thread = new Spinner(composite_10, SWT.BORDER);
		sp_thread.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		sp_thread.setMaximum(5);

		lblNewLabel_5 = new Label(composite_10, SWT.NONE);
		lblNewLabel_5.setText("동시실행수란?");

		lb_power1 = new Label(composite_10, SWT.NONE);
		lb_power1.setText("서버재시작");

		sashForm = new SashForm(composite_table, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		scrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		tree = new Tree(scrolledComposite, SWT.BORDER);
		tree.setHeaderForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tree.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		tree.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		tree.setBounds(10, 10, 680, 200);

		TreeColumn column1 = new TreeColumn(tree, SWT.CENTER);
		column1.setWidth(200);
		TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
		column2.setWidth(0);
		TreeColumn column3 = new TreeColumn(tree, SWT.CENTER);
		column3.setWidth(0);
		TreeColumn column4 = new TreeColumn(tree, SWT.CENTER);
		column4.setWidth(0);
		TreeColumn column5 = new TreeColumn(tree, SWT.CENTER);
		column4.setWidth(0);
		TreeColumn column6 = new TreeColumn(tree, SWT.CENTER);
		column6.setWidth(0);

		this.tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				TreeItem item = (TreeItem) e.item;
				processCommand(item, tabFolder_1.getSelectionIndex());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.item);
			}
		});

		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		tabFolder_1 = new CTabFolder(sashForm, SWT.BORDER);
		tabFolder_1.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		tabItem_2 = new CTabItem(tabFolder_1, SWT.NONE);
		tabItem_2.setText("전체작업(0)");

		composite_5 = new Composite(tabFolder_1, SWT.NONE);
		tabItem_2.setControl(composite_5);
		composite_5.setLayout(new GridLayout(2, false));
		gtv_all = new GridTableViewer(composite_5, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		gd_all = gtv_all.getGrid();
		gd_all.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		gtv_all.addSelectionChangedListener(event -> bindDetailSelection(tabFolder_1.getSelectionIndex()));

		gd_all = gtv_all.getGrid();

		Composite composite_2 = new Composite(composite_5, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite_2.heightHint = 200;
		composite_2.setLayoutData(gd_composite_2);

		tabFolder = new TabFolder(composite_2, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tabItem_5 = new TabItem(tabFolder, SWT.NONE);
		tabItem_5.setText("상세로그");

		txt_alllog = new Text(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		tabItem_5.setControl(txt_alllog);
		txt_alllog.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		txt_alllog.setFont(ResourceManager.getFont("맑은고딕", 20, SWT.BOLD));
		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();

		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		tabFolder_1.setSelection(tabItem_2);

		tabFolder_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int sashwidth = sashForm.getSashWidth();
				int[] sashWeights = sashForm.getWeights();
				if (sashWeights[1] == 1000) {
					sashForm.setWeights(new int[] { 7, 93 });
				} else {
					sashForm.setWeights(new int[] { 0, 100 });
				}
			}
		});
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder_1.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		tabItem = new CTabItem(tabFolder_1, SWT.NONE);
		tabItem.setText("상품작업(0)");

		composite_9 = new Composite(tabFolder_1, SWT.NONE);
		tabItem.setControl(composite_9);
		composite_9.setLayout(new GridLayout(1, false));

		gtv_prod = new GridTableViewer(composite_9, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_prod = gtv_prod.getGrid();
		gd_prod.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		gtv_prod.addSelectionChangedListener(event -> bindDetailSelection(tabFolder_1.getSelectionIndex()));

		Composite composite_10_1 = new Composite(composite_9, SWT.NONE);
		composite_10_1.setLayout(new GridLayout(1, false));
		GridData gd_composite_10_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite_10_1.heightHint = 200;
		composite_10_1.setLayoutData(gd_composite_10_1);

		tabFolder = new TabFolder(composite_10_1, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tabItem_5 = new TabItem(tabFolder, SWT.NONE);
		tabItem_5.setText("상세로그");

		txt_prodlog = new Text(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		tabItem_5.setControl(txt_prodlog);
		txt_prodlog.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		txt_prodlog.setFont(ResourceManager.getFont("맑은고딕", 20, SWT.BOLD));

		tabItem_1 = new CTabItem(tabFolder_1, SWT.NONE);
		tabItem_1.setText("주문작업(0)");

		Composite composite_13 = new Composite(tabFolder_1, SWT.NONE);
		tabItem_1.setControl(composite_13);
		composite_13.setLayout(new GridLayout(1, false));

		gtv_order = new GridTableViewer(composite_13, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_order = gtv_order.getGrid();
		gd_order.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		gtv_order.addSelectionChangedListener(event -> bindDetailSelection(tabFolder_1.getSelectionIndex()));

		Composite composite_10_2 = new Composite(composite_13, SWT.NONE);
		composite_10_2.setLayout(new GridLayout(1, false));
		GridData gd_composite_10_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite_10_2.heightHint = 200;
		composite_10_2.setLayoutData(gd_composite_10_2);

		tabFolder = new TabFolder(composite_10_2, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tabItem_5 = new TabItem(tabFolder, SWT.NONE);
		tabItem_5.setText("상세로그");

		txt_orderlog = new Text(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		tabItem_5.setControl(txt_orderlog);
		txt_orderlog.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		txt_orderlog.setFont(ResourceManager.getFont("맑은고딕", 20, SWT.BOLD));

		tabItem_3 = new CTabItem(tabFolder_1, SWT.NONE);
		tabItem_3.setText("문의작업(0)");

		Composite composite_14 = new Composite(tabFolder_1, SWT.NONE);
		tabItem_3.setControl(composite_14);
		composite_14.setLayout(new GridLayout(1, false));

		gtv_quest = new GridTableViewer(composite_14, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_quest = gtv_quest.getGrid();
		gd_quest.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		gtv_quest.addSelectionChangedListener(event -> bindDetailSelection(tabFolder_1.getSelectionIndex()));

		Composite composite_10_3 = new Composite(composite_14, SWT.NONE);
		composite_10_3.setLayout(new GridLayout(1, false));
		GridData gd_composite_10_3 = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite_10_3.heightHint = 200;
		composite_10_3.setLayoutData(gd_composite_10_3);

		tabFolder = new TabFolder(composite_10_3, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tabItem_5 = new TabItem(tabFolder, SWT.NONE);
		tabItem_5.setText("상세로그");

		txt_questlog = new Text(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		tabItem_5.setControl(txt_questlog);
		txt_questlog.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		txt_questlog.setFont(ResourceManager.getFont("맑은고딕", 20, SWT.BOLD));

		gd_all.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		gd_all.setLinesVisible(true);
		gd_all.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		gd_all.setHeaderVisible(true);

		gd_prod.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		gd_prod.setLinesVisible(true);
		gd_prod.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		gd_prod.setHeaderVisible(true);

		gd_order.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		gd_order.setLinesVisible(true);
		gd_order.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		gd_order.setHeaderVisible(true);

		gd_quest.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		gd_quest.setLinesVisible(true);
		gd_quest.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		gd_quest.setHeaderVisible(true);

		for (YDMAGridColumn col : col_list.getColumns()) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_all, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(col.getWidth());
			tableViewerColumn_x.setText(col.getText());

			MyCellRenderer myCellRenderer = new MyCellRenderer();
			if (col.getSeq() == 9 | col.getSeq() == 11) {
				myCellRenderer.setColumnAlign(SWT.RIGHT);
			} else {
				myCellRenderer.setColumnAlign(SWT.CENTER);
			}
			tableViewerColumn_x.setCellRenderer(myCellRenderer);
		}
		for (YDMAGridColumn col : col_list.getColumns()) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_prod, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(col.getWidth());
			tableViewerColumn_x.setText(col.getText());

			MyCellRenderer myCellRenderer = new MyCellRenderer();
			if (col.getSeq() == 9 | col.getSeq() == 11) {
				myCellRenderer.setColumnAlign(SWT.RIGHT);
			} else {
				myCellRenderer.setColumnAlign(SWT.CENTER);
			}
			tableViewerColumn_x.setCellRenderer(myCellRenderer);
		}
		for (YDMAGridColumn col : col_list.getColumns()) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_order, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(col.getWidth());
			tableViewerColumn_x.setText(col.getText());

			MyCellRenderer myCellRenderer = new MyCellRenderer();
			if (col.getSeq() == 9 | col.getSeq() == 11) {
				myCellRenderer.setColumnAlign(SWT.RIGHT);
			} else {
				myCellRenderer.setColumnAlign(SWT.CENTER);
			}
			tableViewerColumn_x.setCellRenderer(myCellRenderer);
		}
		for (YDMAGridColumn col : col_list.getColumns()) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_quest, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(col.getWidth());
			tableViewerColumn_x.setText(col.getText());

			MyCellRenderer myCellRenderer = new MyCellRenderer();
			if (col.getSeq() == 9 | col.getSeq() == 11) {
				myCellRenderer.setColumnAlign(SWT.RIGHT);
			} else {
				myCellRenderer.setColumnAlign(SWT.CENTER);
			}
			tableViewerColumn_x.setCellRenderer(myCellRenderer);
		}
		/* 컨텐트 프로 바이드 */
		gtv_all.setContentProvider(new MyContentProvider());
		gtv_all.setLabelProvider(new MyLableProvider());

		gtv_prod.setContentProvider(new MyContentProvider());
		gtv_prod.setLabelProvider(new MyLableProvider());

		gtv_order.setContentProvider(new MyContentProvider());
		gtv_order.setLabelProvider(new MyLableProvider());

		gtv_quest.setContentProvider(new MyContentProvider());
		gtv_quest.setLabelProvider(new MyLableProvider());

		sashForm.setWeights(new int[] { 7, 93 });
		// 쇼핑몰 이동 url 바인딩..
		init(parent, style);

		getSystemConf();
	}

	private void getSystemConf() {
		try {
			ShopPreferencesDto dto = ShopCommonDao.get().getSystemConf();
			if (dto.getCompno() != null) {
				sp_thread.setSelection(Integer.parseInt(dto.getSuggprocnt()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void bindDetailSelection(int tabno) {
		ShopWorkDto dto = null;
		GridItem[] item;

		StringBuilder sbMsg = new StringBuilder();
		switch (tabno) {
		case 0:
			item = gtv_all.getGrid().getSelection();
			if (item.length == 0)
				return;
			dto = (ShopWorkDto) item[0].getData();
			break;
		case 1:
			item = gtv_prod.getGrid().getSelection();
			if (item.length == 0)
				return;
			dto = (ShopWorkDto) item[0].getData();
			break;
		case 2:
			item = gtv_order.getGrid().getSelection();
			if (item.length == 0)
				return;
			dto = (ShopWorkDto) item[0].getData();
			break;
		case 3:
			item = gtv_quest.getGrid().getSelection();
			if (item.length == 0)
				return;
			dto = (ShopWorkDto) item[0].getData();
			break;
		}

		List<String> lstMsg = Arrays.asList(dto.getWorklog().split(","));
		List<String> lstSeq = Arrays.asList(dto.getProdseq().split(","));
		for (int i = 0; i < lstSeq.size(); ++i) {
			sbMsg.append(lstSeq.get(i) + " :\t" + ShopErrorCode.get().getMessage2(lstMsg.get(i)).getContent() + "\r\n");
		}

		switch (tabno) {
		case 0:
			txt_alllog.setText(sbMsg.toString());
			break;
		case 1:
			txt_prodlog.setText(sbMsg.toString());
			break;
		case 2:
			txt_orderlog.setText(sbMsg.toString());
			break;
		case 3:
			txt_questlog.setText(sbMsg.toString());
			break;
		}

	}

	/*
	 * 상태값으로 필터링한다.
	 */
	private void fiterStatusGrid(String status, int tabno) {
		switch (tabno) {
		case 1:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.상품등록))
					.collect(Collectors.toList());
			break;
		case 2:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.주문수집))
					.collect(Collectors.toList());
			break;
		case 3:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.문의수집))
					.collect(Collectors.toList());
			break;
		}

		if (datasource == null || datasource.size() == 0)
			return; // 데이타가 없으면 리턴..
		List<ShopWorkDto> list = datasource;
		String cmdID = "A";
		TreeItem[] item = tree.getSelection();
		if (item.length != 0) {
			cmdID = item[0].getText(1); // A :전체 , C : ShopCd 로 검색, U : 사용자로 검색..
		}
		List<ShopWorkDto> order_list = null;

		switch (cmdID) {
		case "A": // Compono 전체 검색.
			order_list = list.stream().filter(p -> p.getWorkdetcd().equals(status)).collect(Collectors.toList());
			break;
		case "C": // ShopCd 에 해당 하는것만 검색.
			order_list = datasource.stream()
					.filter(p -> p.getShopcd().equals(item[0].getText(2)) && p.getWorkdetcd().equals(status))
					.collect(Collectors.toList());
			break;
		case "U": // 쇼핑몰 사용자 아이디로 검색.
			order_list = datasource
					.stream().filter(p -> p.getShopcd().equals(item[0].getText(2))
							&& p.getUser_id().equals(item[0].getText(0)) && p.getWorkdetcd().equals(status))
					.collect(Collectors.toList());
			break;
		}
		switch (tabno) {
		case 0:
			gtv_all.setInput(order_list);
			gtv_all.refresh();
			break;
		case 1:
			gtv_prod.setInput(order_list);
			gtv_prod.refresh();
			break;
		case 2:
			gtv_order.setInput(order_list);
			gtv_order.refresh();
			break;
		case 3:
			gtv_quest.setInput(order_list);
			gtv_quest.refresh();
			break;
		}
	}

	private void processCommand(TreeItem item, int tabno) {

		String cmdID = item.getText(1); // A :전체 , C : ShopCd 로 검색, U : 사용자로 검색..
		switch (tabno) {
		case 1:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.상품등록))
					.collect(Collectors.toList());
			break;
		case 2:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.주문수집))
					.collect(Collectors.toList());
			break;
		case 3:
			datasource = datasource.stream().filter(f -> f.getWorkcd().equals(WorkStatus.문의수집))
					.collect(Collectors.toList());
			break;
		}
		System.out.println("** command ID :" + cmdID);

		if (datasource == null || datasource.size() == 0)
			return;

		List<ShopWorkDto> order_list = null;

		switch (cmdID) {
		case "A": // Compono 전체 검색.
			order_list = datasource;
			break;
		case "C": // ShopCd 에 해당 하는것만 검색.
			order_list = datasource.stream().filter(f -> f.getShopcd().equals(item.getText(2)))
					.collect(Collectors.toList());
			break;
		case "U": // 쇼핑몰 사용자 아이디로 검색.
			order_list = datasource.stream()
					.filter(f -> f.getShopcd().equals(item.getText(2)) && f.getUser_id().equals(item.getText(0)))
					.collect(Collectors.toList());
			break;
		}
		switch (tabno) {
		case 0:
			gtv_all.setInput(order_list);
			gtv_all.refresh();
			break;
		case 1:
			gtv_prod.setInput(order_list);
			gtv_prod.refresh();
			break;
		case 2:
			gtv_order.setInput(order_list);
			gtv_order.refresh();
			break;
		case 3:
			gtv_quest.setInput(order_list);
			gtv_quest.refresh();
			break;
		}

	}

	private void init(Composite parent, int style) {
		setDefaultDate();
		getShopnm();
		this.setMenuTree();

		WorkTaskHelper.get().start();
	}

	private void getShopnm() {
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			shopcheck = dao.getAllDealTradeList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] setDefaultDate() {
		String[] days = YDMATimeUtil.getZMonthDiffDay(-1, "yyyyMMdd");

		int yearCurrnet = Integer.valueOf(days[0].substring(0, 4));
		int monCurrnet = Integer.valueOf(days[0].substring(4, 6));
		int dayCurrnet = Integer.valueOf(days[0].substring(6, 8));

		int yearOff = Integer.valueOf(days[1].substring(0, 4));
		int monOff = Integer.valueOf(days[1].substring(4, 6));
		int dayOff = Integer.valueOf(days[1].substring(6, 8));

		Calendar calender_dateTime_from = Calendar.getInstance();
		calender_dateTime_from.add(Calendar.DAY_OF_MONTH, -3);

		Date date_from = calender_dateTime_from.getTime();
		String str_date_from = new SimpleDateFormat("yyyyMMdd").format(date_from);

		return days;

	}

	/*
	 * 메뉴 구성.
	 *
	 */
	public void setMenuTree() {
		List<CodeItem> urlItem = new ArrayList<>();
		TreeItem item = new TreeItem(tree, SWT.NONE);
		item.setText(new String[] { "전체", "A", "", "", "", "" });
		try {
			List<ShoppingMallDetailDto> list = ShopCommonDao.get().ShoppingMallDetailList();

			/* shopid , shoppingid 별로 그룹핑 한다. */
			Map<String, Map<String, List<ShoppingMallDetailDto>>> mapList = list.stream().collect(Collectors.groupingBy(
					ShoppingMallDetailDto::getShopcd, Collectors.groupingBy(ShoppingMallDetailDto::getShoppingid)));

			List<String> items = mapList.keySet().stream().collect(Collectors.toList());
			Collections.sort(items);

			for (String shopid : items) {
				Map<String, List<ShoppingMallDetailDto>> map = mapList.get(shopid);

				TreeItem subItem = new TreeItem(item, SWT.NONE);
				for (String shopUserID : map.keySet()) {
					List<ShoppingMallDetailDto> listdto = map.get(shopUserID);
					subItem.setText(
							new String[] { listdto.get(0).getShopnm(), "C", listdto.get(0).getShopcd(), "", "", "" });

					for (ShoppingMallDetailDto dto : listdto) {
						TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
						subsubItem.setText(new String[] { dto.getShoppingid(), "U", dto.getShopcd(), dto.getShopnm() });
						// 0 url 1 id 2 pw
						String code = String.format("%s,%s,%s", dto.getShopUrl(), dto.getShoppingid(),
								dto.getPassword());
						urlItem.add(new CodeItem(code, listdto.get(0).getShopnm() + "(" + dto.getShoppingid() + ")"));

					}
				}
			}

			for (TreeItem i : tree.getItems()) {
				for (TreeItem s : i.getItems()) {
					for (TreeItem g : s.getItems()) {
						i.setExpanded(true);
						s.setExpanded(true);
						g.setExpanded(true);
					}
				}
			}

			// scb_cmbOnUrl.setDataSource(urlItem);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	/*
	 * 프로바이더.
	 */
	class MyLableProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ShopWorkDto dto = (ShopWorkDto) element;
			String value = "";
			switch (columnIndex) {
			case 1:
				String shopnm = "";
				for (List<String> list : shopcheck) {
					if (dto.getShopcd().equals(list.get(1))) {
						shopnm = list.get(2);
					}
				}
				value = shopnm;
				break;
			case 3:
				value = WorkStatus.getInstance().findCode(dto.getWorkcd());
				break;
			case 4:
				value = WorkStatus.getInstance().findCode(dto.getWorkdetcd());
				break;
			case 5:
				value = "추가정보";
				break;
			default:
				value = col_list.getFieldValue(dto, col_list.getFieldName(columnIndex));
				break;
			}
			return value;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			// TODO Auto-generated method stub

			Color bgColor = null;
			ShopWorkDto dto = (ShopWorkDto) element;
//			int intcsColse = YDMAStringUtil.convertToInt(dto.getCsClose());
//			if (intcsColse > 0) {
//				bgColor = SWTResourceManager.getColor(255, 0, 0);
//			}

			return bgColor;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			Color bgColor = null;
			ShopWorkDto dto = (ShopWorkDto) element;

//			if (columnIndex == 3) {
//				bgColor = OrderStatus.getInstance().findColor(dto.getOrder_status());
//			} else {
//				int n = YDMAStringUtil.convertToInt(dto.getNo());
//				if (n % 2 == 0)
//					bgColor = SWTResourceManager.getColor(245, 245, 245);
//			}

			return bgColor;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static void setGridSetting(List<ShopWorkDto> list2, List<ShopWorkDto> gtv_prod2,
			List<ShopWorkDto> gtv_order2, List<ShopWorkDto> gtv_quest2) {
		Display.getDefault().asyncExec(() -> {
			datasource = list2;
			gtv_all.setInput(datasource);
			gtv_prod.setInput(gtv_prod2);
			gtv_order.setInput(gtv_order2);
			gtv_quest.setInput(gtv_quest2);
			tabItem_2.setText("전체작업(" + list2.size() + ")");
			tabItem.setText("상품작업(" + gtv_prod2.size() + ")");
			tabItem_1.setText("주문작업(" + gtv_order2.size() + ")");
			tabItem_3.setText("문의작업(" + gtv_quest2.size() + ")");
		});

	}

}
