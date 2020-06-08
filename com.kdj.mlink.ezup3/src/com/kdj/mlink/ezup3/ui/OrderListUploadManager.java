package com.kdj.mlink.ezup3.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMACommonCode;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.ClaimDao;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.OrderDao;
import com.kdj.mlink.ezup3.data.dao.SabangNetDao;
import com.kdj.mlink.ezup3.data.dao.UserDao;
import com.kdj.mlink.ezup3.data.dao.PostDao;
import com.kdj.mlink.ezup3.data.excel.MyExcelManager;
import com.kdj.mlink.ezup3.data.excel.MyDataManagerFactory;

/**
 * ������ ������ üũ�Ͽ� �˷���
 * 
 * @author kdj01
 *
 */
public class OrderListUploadManager extends Composite {

	public static String ID = "com.kdj.mlink.ezup3.command.OrderListUpload.ID";
	
	//Image searchImage = ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png");
	Image downloadImage = ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/download.png");

	String[][] column_name = {{ "No.", "65" }, { "�ֹ��Ͻ�", "160" }, { "�����θ�", "150" }, { "�����ȣ", "90" }, { "�ּ�", "450" },
			{ "�ڵ���", "150" }, { "�Ϲ���ȭ", "150" }, { "����", "60" }, { "��ۺ�", "80" }, { "�ſ�", "150" }, { "�ɼ�(����)", "400" },
			{ "��ǰ�ڵ�", "200" }, { "�޽���", "260" }, { "���屸��", "100" }, { "��۱���", "100" }, { "�����ֹ���ȣ", "150" },
			{ "���θ����̵�", "250" }, { "�ֹ��ڸ�(������)", "150" }, { "��Ÿ�޽���", "250" }, { "�ڻ���ڵ�", "300" }, { "�ֹ��հ�", "100" } };

	public static String TITLE = "�ֹ����ε�";
	int selectedRowNoMst = -1;
	String orderListFullPath;

	String orderDayStrFrom;
	String orderDayStrTo;

	Composite composite_table;
	private Grid table;
	GridTableViewer tableViewer;
	DateTime dt_orddt;
	
	Button btn_search; 
	
	Combo cb_ordseq;
	Combo combo_source;

	Composite composite_stack;
	Composite composite_date;
	Composite composite_txt;
	DateTime dateTime_from;
	DateTime dateTime_to;
	
	
	private Text text_filename;
	private Text text_total;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Button button;
	private Button btn_delete;
	private Button today;
	private Button week1;
	private Button month1;
	private Button month3;

	
	public OrderListUploadManager(Composite parent, int style) {
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
		GridLayout gl_composite_1 = new GridLayout(18, false);
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("�ֹ�����");

		ComboViewer comboViewer_source = new ComboViewer(composite_1, SWT.READ_ONLY);
		comboViewer_source.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (combo_source.getSelectionIndex() == 0) {
					((StackLayout) composite_stack.getLayout()).topControl = composite_date;
					YDMATimeUtil.setOrddtDate(dt_orddt);
					dt_orddt.setEnabled(false);					
				} else {
					((StackLayout) composite_stack.getLayout()).topControl = composite_txt;	
					dt_orddt.setEnabled(true);					
				}
				composite_stack.layout();
			}
		});
		combo_source = comboViewer_source.getCombo();
		combo_source.setItems(new String[] { "����", "�ֹ���" });
		GridData gd_combo_source = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_combo_source.widthHint = 100;
		combo_source.setLayoutData(gd_combo_source);
		combo_source.select(0);

		composite_stack = new Composite(composite_1, SWT.NONE);
		composite_stack.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		composite_stack.setLayout(new StackLayout());

		composite_date = new Composite(composite_stack, SWT.NONE);
		GridLayout gl_composite_date = new GridLayout(3, false);
		gl_composite_date.horizontalSpacing = 0;
		gl_composite_date.verticalSpacing = 0;
		gl_composite_date.marginWidth = 0;
		gl_composite_date.marginHeight = 0;
		composite_date.setLayout(gl_composite_date);

		dateTime_from = new DateTime(composite_date, SWT.BORDER);
		dateTime_from.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		dateTime_from.setBounds(0, 0, 102, 28);

		Label lblNewLabel_3 = new Label(composite_date, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));
		lblNewLabel_3.setBounds(0, 0, 71, 20);
		lblNewLabel_3.setText("~");

		dateTime_to = new DateTime(composite_date, SWT.BORDER);
		dateTime_to.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		dateTime_to.setBounds(0, 0, 102, 28);

		composite_txt = new Composite(composite_stack, SWT.NONE);
		GridLayout gl_composite_filename = new GridLayout(1, false);
		gl_composite_filename.verticalSpacing = 0;
		gl_composite_filename.horizontalSpacing = 0;
		gl_composite_filename.marginWidth = 0;
		gl_composite_filename.marginHeight = 0;
		composite_txt.setLayout(gl_composite_filename);

		text_filename = new Text(composite_txt, SWT.BORDER);
		text_filename.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text_filename.setText("");
		text_filename.setBounds(0, 0, 72, 26);
		
		today = new Button(composite_1, SWT.NONE);
		today.setText("����");
		today.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		today.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("����");
			}
		});
		week1 = new Button(composite_1, SWT.NONE);
		week1.setText("1����");
		week1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		week1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("week1");
			}
		});
		
		month1 = new Button(composite_1, SWT.NONE);
		month1.setText("1����");
		month1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month1");
			}
		});
		
		month3 = new Button(composite_1, SWT.NONE);
		month3.setText("3����");
		month3.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		month3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultDate("month3");
			}
		});
		
		btn_search = new Button(composite_1, SWT.NONE);
		btn_search.setToolTipText("�ֹ�������������");
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo_source.getSelectionIndex() == 0) {// ����
					getOrderListFromSabangNet();
				} else {
					getOrderListFromExcel();
				}
			}
		});
		btn_search.setImage(downloadImage);
		//btn_search.setText("��������");


		Button btn_refresh = new Button(composite_1, SWT.NONE);
		GridData gd_btn_refresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btn_refresh.widthHint = 85;
		btn_refresh.setLayoutData(gd_btn_refresh);
		btn_refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshOrder();
			}
		});
		btn_refresh.setToolTipText("����");
		btn_refresh.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/refresh.png"));
		btn_refresh.setText("����");
		
		
		lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setText("�ֹ��Ǽ�");

		text_total = new Text(composite_1, SWT.BORDER | SWT.RIGHT);
		text_total.setText("0");
		GridData gd_text_total = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_text_total.widthHint = 50;
		text_total.setLayoutData(gd_text_total);
		
		lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setText("��");

		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("  *���ε� ����");

		dt_orddt = new DateTime(composite_1, SWT.BORDER);
		dt_orddt.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.horizontalIndent = 10;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("����");

		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.READ_ONLY);
		cb_ordseq = comboViewer.getCombo();
		GridData gd_cb_ordseq = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_ordseq.widthHint = 40;
		cb_ordseq.setLayoutData(gd_cb_ordseq);
		cb_ordseq.setItems(new String[] { "1", "2" });
		cb_ordseq.select(0);

//		Button btn_add = new Button(composite_1, SWT.NONE);
//		btn_add.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				openOrderDetail(true);
//			}
//		});		
//		btn_add.setText(" �߰� ");

//		Button btn_modify = new Button(composite_1, SWT.NONE);
//		btn_modify.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseUp(MouseEvent e) {
//				if(selectedRowNoMst < 0) {
//					MessageDialog.openInformation(getShell(), TITLE,   "�ֹ������� �����ϼ���." );
//				}else {
//					openOrderDetail(false);	
//				}
//			}
//		});
//		btn_modify.setText(" ���� ");
//		
//		Button btn_del = new Button(composite_1, SWT.NONE);
//		btn_del.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if(selectedRowNoMst < 0) {
//					MessageDialog.openInformation(getShell(), TITLE,   "�ֹ������� �����ϼ���." );
//				}else {
//					deleteOrder(selectedRowNoMst);
//				}
//			}
//		});		
//		btn_del.setText(" ���� ");	

		Button btn_upload = new Button(composite_1, SWT.NONE);
		GridData gd_btn_upload = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btn_upload.widthHint = 85;
		btn_upload.setLayoutData(gd_btn_upload);
		btn_upload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				uploadOrder();
			}
		});
		btn_upload.setText("���ε� ");
		btn_upload.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/upload.gif"));
		btn_upload.setToolTipText("���ε�");

//		Button btn_uploadShow = new Button(composite_1, SWT.NONE);
//		btn_uploadShow.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				showUploadOrderList();
//			}
//		});
//		btn_uploadShow.setText("�ֹ���������");

		composite_table = new Composite(composite, SWT.NONE);
		composite_table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridLayout gl_composite_table = new GridLayout(1, false);
		gl_composite_table.verticalSpacing = 0;
		gl_composite_table.marginWidth = 0;
		gl_composite_table.marginHeight = 0;
		composite_table.setLayout(gl_composite_table);
		composite_table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_table.setBounds(0, 0, 64, 64);

		tableViewer = new GridTableViewer(composite_table,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				openOrderDetail(false);
			}
		});
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				selectedRowNoMst = table.getSelectionIndex();
			}
		});
		table = tableViewer.getGrid();
		table.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setLineColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);

//		TableViewerColumn hidden_colmn = new TableViewerColumn(tableViewer, SWT.NONE);
//		TableColumn tblclmnNewColumn_hidden = hidden_colmn.getColumn();
//		tblclmnNewColumn_hidden.setWidth(0);
//		tblclmnNewColumn_hidden.setResizable(false);
//
//		for (int i = 0; i < column_name.length; i++) {
//			TableViewerColumn tableViewerColumnX = new TableViewerColumn(tableViewer, SWT.NONE);
//			TableColumn tblclmnNewColumnX = tableViewerColumnX.getColumn();
//			tblclmnNewColumnX.setAlignment(SWT.CENTER);
//			tblclmnNewColumnX.setWidth(Integer.parseInt(column_name[i][1]));
//			tblclmnNewColumnX.setText(column_name[i][0]);
//		}

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (int i = 0; i < column_name.length; i++) {
			String[] column = column_name[i];
			GridColumn tableViewerColumn_x = new GridColumn(table, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

			if (i == 4 || i == 5 || i == 6 || i == 10 || i == 11 || i == 12 || i == 16|| i == 18|| i == 19) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		
			if (i == 7 || i == 8 || i == 20) { // ��ۺ�, �ֹ��հ�
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.RIGHT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}
		btn_delete = new Button(composite_1, SWT.NONE);
		GridData gd_btn_delete = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btn_delete.widthHint = 85;
		btn_delete.setLayoutData(gd_btn_delete);
		btn_delete.setText("����");
		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				deleteOrder();
			}
		});
		btn_delete.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/delete.png"));
		btn_delete.setToolTipText("�����ϱ�");
		btn_delete.setVisible(false);
		((StackLayout) composite_stack.getLayout()).topControl = composite_date;
				
		
		tableViewer.setContentProvider(new MyContentProvider());
		tableViewer.setLabelProvider(new MyLableProvider());
		btnDeleteVisible();
		setDefaultDate("����");
		table.setVisible(false);

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
		case "����":
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			dateTime_from.setYear(Integer.parseInt(mTime.substring(0, 4)));
			dateTime_from.setMonth(Integer.parseInt(mTime.substring(4, 6))-1);
			dateTime_from.setDay(Integer.parseInt(mTime.substring(6, 8)));
			break;
			case "week1": 
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date date = calendar.getTime();
				String aa = new SimpleDateFormat("yyyyMMdd").format(date);
				dateTime_from.setYear(Integer.parseInt(aa.substring(0, 4)));
				dateTime_from.setMonth(Integer.parseInt(aa.substring(4, 6))-1);
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
			
			default: break;
		}
		
		return days;
		
	}

	private void btnDeleteVisible() {			
		try {
			UserDao dao = new UserDao();
			boolean isNew = dao.checkExistUser("admin");
			if(isNew) {
				btn_delete.setVisible(true);
			} else {
				btn_delete.setVisible(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public String getOrderListFullPath() {
		return orderListFullPath;
	}
	//�����ϱ�
	protected void deleteOrder() {
		List<List<String>> contents = (List<List<String>> )tableViewer.getInput();		
		int[] selectedIdx = tableViewer.getGrid().getSelectionIndices();
		if(selectedIdx.length==0) {
			MessageDialog.openInformation(getShell(), TITLE, "������ �ֹ������� �����ϼ���.");
		}else {						
			List<List<String>> contents_del = new ArrayList<List<String>>();
			for(int i=0; i<selectedIdx.length; i++ )
			{
				List<String> list = contents.get(selectedIdx[i]);
				contents_del.add(list);
			}
			boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "��㳻���� �����Ͻðڽ��ϱ�?\n");
			if(flag) {				
				contents.removeAll(contents_del);
				tableViewer.refresh();						
			}			
			
		}
		
	}
	
	public void showSource(int soucreIdx) {
		combo_source.select(soucreIdx);

		if (soucreIdx == 0) {
			((StackLayout) composite_stack.getLayout()).topControl = composite_date;
			YDMATimeUtil.setOrddtDate(dt_orddt);
			dt_orddt.setEnabled(false);
			
		} else {
			((StackLayout) composite_stack.getLayout()).topControl = composite_txt;
			dt_orddt.setEnabled(true);
		}
		composite_stack.layout();
		
		
	}

	public void setOrderListFullPath(String orderListFullPath) {
		this.orderListFullPath = orderListFullPath;
		String orderfilename = orderListFullPath.substring(orderListFullPath.lastIndexOf(File.separator) + 1,
				orderListFullPath.length());
		this.text_filename.setText(orderfilename);
	}

	public void getOrderListFromSabangNet() {
				
		try {
			long dayOffset = YDMATimeUtil.getDayOffset(YDMATimeUtil.getRoRddtDate(dateTime_from), YDMATimeUtil.getRoRddtDate(dateTime_to));	
			if(dayOffset>5 || dayOffset<0) {
				MessageDialog.openInformation(getShell(), TITLE, "��¥�Է��� �ùٸ��� �ʽ��ϴ�.  "+ dayOffset +"\n�ִ� 5�ϰ��� �ֹ������� ������ �� �ֽ��ϴ�.");
				return;
			}
						
			
			String dateFrom = YDMATimeUtil.getOrddtDate(dateTime_from);
			String dateTo = YDMATimeUtil.getOrddtDate(dateTime_to);
			
			boolean flag = MessageDialog.openQuestion(getShell(), TITLE,
					"������ �Ⱓ�� ���� �ֹ������͸� �����ñ��?\n" + dateFrom + " ~ " + dateTo);

			if (flag) {				

				/////////////////////////////////////////
//				System.gc();
//				Thread.sleep(50);
				////////////////////////////////////////
				CompInfoDao compdao = new CompInfoDao();
				List<String> complist = compdao.getCompNoImage();
				SabangNetDao dao = new SabangNetDao();
				List<List<String>> xmlContents = dao.getSabangNetOrderList(dateFrom, dateTo);
				
				List<List<String>> idcheck = new ArrayList<List<String>>();			
				List<List<String>> checkContents = new ArrayList<List<String>>();
				List<List<String>> computeContents = new ArrayList<List<String>>();
				OrderDao oddao = new OrderDao();
				//�ߺ��ֹ����ε� üũ..
				List<String> uploadedOrderSabCd = dao.getSabangNetOrderIdx(dateFrom, dateTo);
				for (List<String> list : xmlContents) {
					//�̹� �ֹ����ε��� ���� �����ڵ带 ��ȸ�Ѵ�.(�����ֹ��ҷ����� ���� ����, dateFrom~dateTo)
					//�̹� �ֹ����ε��� �ֹ��� �ٽ� �ֹ������� ���Խ�Ű�� �ʱ� ����.
					//�޴�����ȣ�� �����ÿ� �Ϲݹ�ȣ�� ��ü					
					if(oddao.isPhonenumber(list.get(5))) {
						list.set(5, list.get(6));
					} 
					if(uploadedOrderSabCd.contains(list.get(14))) {
						continue;//dateFrom~dateTo ������ ���� �ֹ����ε��� �Ͱ� �ߺ��Ǵ� �� ����
					}					
					checkContents.add(list);	
				}
				
				//�ڽ����� ���..
				for (List<String> list : checkContents) {					
					String saleCntStr =  (list.get(7).length()==0) ? "0" : list.get(7);					
					int saleCnt = Integer.parseInt(saleCntStr);					
					String boxeaStr = (list.get(20).length()==0) ? "0" : list.get(20);	//BOX_EA
					int boxEa = Integer.parseInt(boxeaStr);					
					if(boxEa == 0) {
						boxEa = 1;
					}					
					list.set(7, String.valueOf(saleCnt*boxEa));
					list.remove(20); //BOX_EA �� ����� �����ؾ�  UI�׸��� ��½� �ε����� ����.
										
					computeContents.add(list);				
				}
				//üũ�غ��ߵ�
				for(List<String> idlist : computeContents) {
//					if(complist.get(0).equals("3")) {
//						//idcheck.add(idlist);
//						if(idlist.get(18).contains("SKY") || idlist.get(18).contains("SCH")) {
//							idcheck.add(idlist);
//						}	
//					}else if(complist.get(0).equals("2")) {
//						if(!idlist.get(18).contains("SKY") && !idlist.get(18).contains("SCH")) {
//							idcheck.add(idlist);
//						}
//					}else {
						idcheck.add(idlist);
//					}					
				}				
				this.showOrderList(idcheck);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}
	}

	public void getOrderListFromExcel() {
		
		FileDialog fileSelectionDialog = MyDataManagerFactory.createExcelFileSelectionDialog();
		fileSelectionDialog.setText("�ֹ�������");

		String filePath = fileSelectionDialog.open();
		if (filePath == null) {
			return;
		}

		this.orderListFullPath = filePath;

		//this.table.removeAll();

		try {

			String filename = orderListFullPath.substring(orderListFullPath.lastIndexOf(File.separator) + 1,
					orderListFullPath.length());
			this.text_filename.setText(filename);
			MyExcelManager myExcelManager = null;

			myExcelManager = MyDataManagerFactory.createExcelManager(orderListFullPath);
			myExcelManager.loadData();
			OrderDao oddao = new OrderDao();
			List<List<String>> sheetContents = myExcelManager.getSheetContents(0, 1);
			List<List<String>> contents = new ArrayList<List<String>>();
			for(List<String> list : sheetContents) {
				if(oddao.isPhonenumber(list.get(5))) {
					list.set(5, list.get(6));
				}  				
				contents.add(list);
			}
			
			this.showOrderList(contents);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	public void loadOrderList() {

		try {

			if (this.combo_source.getSelectionIndex() == 0) { // ���� ������ ���

				getOrderListFromSabangNet();

			} else if (this.combo_source.getSelectionIndex() == 1) { // �ֹ��� ������ ���

				getOrderListFromExcel();

			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}



	void deeepCompare(List<String> order, List<String> prodcdList) throws Exception {

		boolean flag = false;
		for (String prodcd : prodcdList) {			
			if (order.get(10).contains(prodcd.trim())) {
				order.set(11, prodcd);
				order.set(order.size()-1, "T");
				flag = true;
				//break;
			}			
		}
		
		if(flag) {
			return;
		}
		
		for (String prodcd : prodcdList) {			
			if (order.get(18).contains(prodcd.trim())) {
				order.set(11, prodcd);
				order.set(order.size()-1, "T");
				flag = true;
				//break;
			}
		}

		if(flag) {
			return;
		}
		
		if (!order.get(order.size()-1).equals("T")) {
			for (String prodcd : prodcdList) {
				if (order.get(19).contains(prodcd)) {
					order.set(11, prodcd);
					order.set(order.size()-1, "T");
					flag = true;
					//break;
				}
			}
		}

	}

	void compareProdcdToMaster(List<List<String>> origin_Contents) throws Exception {
		// �ֹ������� ��ǰ�ڵ带 ã�Ƴ��� ���� ���� ���������� ������ ����Ÿ�� ���Ѵ�.
		
		List<String> prodcdList = YDMACommonCode.getAllProductCode();
		for (List<String> order : origin_Contents) {
			if (order.get(11).trim().length() == 0) {
				//�ڵ带  ã�� ����  ��� �ֹ������  prodcd ���ؼ� ã�´�.
				deeepCompare(order, prodcdList);
			} else {
				//Ư���ڵ带 ã�� ����  ��� ��ϵ� prodcd ���� Ȯ���Ѵ�.				
				for(String prodcd : prodcdList) {
					if(order.get(11).equals(prodcd)) {
						//11��°�� �ִ� �ڵ�� ����� prodcd �� ��ġ�ϸ�..
						order.set(order.size()-1, "T"); //21��°�� T�� ����
						break;
					}
				}
			}

		}

		//�ڵ尡 �����ϵ� (T)�� �߿��� '������'�� ���� �� ã��
		List<String> contents_rack = YDMACommonCode.getAllRackProductCode();
		for (List<String> order : origin_Contents) {
			if (order.get(21).equals("T")) {				
				if (!contents_rack.contains(order.get(11))) {
					order.set(order.size()-1, "B");// �ù������ ���� �Ͱ� �Բ� -B�� ����
				}
			}
		}

		// �ڵ尡 �����ΰ� �߿��� '�ù������'�� ���� �� ã��
		List<String> contents_exp = YDMACommonCode.getAllExpProductCode();
		for (List<String> order : origin_Contents) {
			if (order.get(21).equals("T")) {
				if (!contents_exp.contains(order.get(11))) {
					order.set(order.size()-1, "B");// ������ ���� �Ͱ� �Բ� -B�� ����
				}
			}
		}
		
		// �ڵ尡 �����ΰ� �߿��� '�����Ȼ�ǰ' ã��
		List<String> contents_delyn = YDMACommonCode.getAllProductCodeFoDelyn();
		for (List<String> order : origin_Contents) {
			if (order.get(21).equals("T")) {
				if (!contents_delyn.contains(order.get(11))) {
					order.set(order.size()-1, "B");// ������ ���� �Ͱ� �Բ� -B�� ����
				}
			}
		}

	}

	void optionProductOrderZeroAmount(List<List<String>> origin_Contents) throws Exception {
		// ���������� �ֹ��� �ɼ� ���� ��ǰ�� ��� �ֹ��ݾ��� 0������ ���εǴ� ��찡 �ִ�.
		// �ֹ��ݾ��� 1������ ���� �Է�ó�� ��.
		for (List<String> order : origin_Contents) {
			if (order.get(20).equals("0") ) {
				if (order.get(10).contains("[G") || order.get(10).contains("G") ) {
					order.set(20, "1");	// 1�� ���� ��
				}
			}
		}
	}
	
	private void showOrderList(List<List<String>> origin_Contents) throws Exception {

		if(origin_Contents==null || origin_Contents.size()==0 ) {
			tableViewer.setInput(null);			
			tableViewer.refresh();
			if (!table.isVisible()) {
				table.setVisible(true);
			}
			MessageDialog.openInformation(getShell(), TITLE, "���ο� �ֹ������� �����ϴ�.");		
			return;
		}
		
		if(origin_Contents.get(0).size() != 20) {
			MessageDialog.openError(getShell(), TITLE, "�ֹ��������� ������ ���� �ʽ��ϴ�.");		
			return;
		}
		
		// ������Ʈ���� ������ ����Ÿ�� 11��° ��ġ�� prodcd �ڸ�, �� �������� ���±��� �ڸ��� ���Խ�Ŵ
		for (List<String> list : origin_Contents) {
			list.add(11, ""); // 11��° ��ġ�� prodcd �ڸ� �߰���, list �� ��� ���� 1�� �þ (20-->21)
			list.add(list.size(), "X"); // ������ ��ġ�� ����flag�� ���� �ڸ��� �߰��� - �⺻��F(:�ڵ����)(21-->22)
		}

		//-- ���α׷����� �ε� ..��  YDWMExcelOrderListParserThread ���� �ֹ��м��Ͽ� �ֹ��ڵ带 ������.
		ProcdParseDialog d = new ProcdParseDialog(this.getShell(), origin_Contents);
		d.open();


		this.compareProdcdToMaster(origin_Contents);
		
		this.optionProductOrderZeroAmount(origin_Contents);	// �ɼǻ�ǰ �ֹ��ݾ� �ȵ����°�� �ֿ� ó��
		
		
		origin_Contents  = rearrangeContentsZeroAmount("0", origin_Contents);	

		origin_Contents = this.rearrangeContents("B",
				this.rearrangeContents("F", this.rearrangeContents("X", origin_Contents)));
		
		// -- �׸��� ���
		tableViewer.setInput(null);
		tableViewer.setInput(origin_Contents);
		tableViewer.refresh();
		if (!table.isVisible()) {
			table.setVisible(true);
		}
		
		text_total.setText(""+origin_Contents.size());
		
/////////////////////////////////////////
//		System.gc();
//		Thread.sleep(50);
/////////////////////////////////////////
			
		MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� �����Խ��ϴ�.");		
	}

	private List<List<String>> rearrangeContents(String flag, List<List<String>> contents) {
		List<List<String>> topContents = new ArrayList<List<String>>();
		for (List<String> list : contents) {
			if (flag.equals(list.get(list.size() - 1))) {
				topContents.add(0, list);
			} else {
				topContents.add(list);
			}
		}

		return topContents;
	}
	private List<List<String>> rearrangeContentsZeroAmount(String flag, List<List<String>> contents) {
		List<List<String>> topContents = new ArrayList<List<String>>();
		for (List<String> list : contents) {
			if (flag.equals(list.get(20))) {
				topContents.add(0, list);
			} else {
				topContents.add(list);
			}
		}

		return topContents;
	}

	/**
	 * UI �׸��� ����Ÿ�� ���� �����ϴ� �Լ�
	 * 
	 * @param sheetContents
	 */
	public void uploadOrder() {
		// TODO -- �׸��� ����Ÿ�� ���� �����Ѵ�.
		
		try {

			List<List<String>> orderList = (List<List<String>>) this.tableViewer.getInput();
			
			if (orderList == null || orderList.size() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "���ε��� �ֹ������� �����ϴ�.");
				return;
			}
		
			for (List<String> order : orderList) {
				if("0".equals(order.get(20))) {
					continue;
				}
				if (order.get(11).length() == 0) {
					MessageDialog.openInformation(getShell(), TITLE, "��ǰ�ڵ尡 ���� �ֹ��� �ֽ��ϴ�.\n�ֹ���ȣ - " + order.get(0));
					return;
				}		
			}

			List<String> prodcdList = YDMACommonCode.getAllProductCode();
			for (List<String> order : orderList) {
				if("0".equals(order.get(20))) {
					continue;
				}
				String prodcd = order.get(11);
				if (!prodcdList.contains(prodcd)) {
					MessageDialog.openInformation(getShell(), TITLE, "��ǰ�ڵ尡 ��ϵ��� ���� ��ǰ�� �ֹ��� �ֽ��ϴ�.\n�ֹ���ȣ - " + order.get(0));
					return;
				}
			}

			for (List<String> order : orderList) {
				if("0".equals(order.get(20))) {
					continue;
				}
				String errFlag = order.get(order.size() - 1);
				if (errFlag.equals("B")) {
					MessageDialog.openInformation(getShell(), TITLE,
							"â����� Ȥ�� ��۰����� ��ϵ��� �ʴ� ��ǰ��  �ֹ��� �ֽ��ϴ�.\n�ֹ���ȣ - " + order.get(0));
					return;
				}
			}
			for(List<String> order : orderList) {
				if(order.get(12).getBytes().length>300) {
					byte[] strByte = order.get(12).getBytes();
					String newTitle = new String(strByte,0,100);
					order.set(12, newTitle);
				} 				
			}
			//�ڻ���ڵ�50�ڷ�
			for(List<String> order : orderList) {
				if(order.get(19).getBytes().length>50) {
					byte[] strByte = order.get(19).getBytes();
					String newTitle = new String(strByte,0,50);
					order.set(19, newTitle);
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
			String ordseq = cb_ordseq.getItem(cb_ordseq.getSelectionIndex());

			String msg = "�ֹ������� ���ε��Ͻðڽ��ϱ�?\n" + "���ε�����: " + orddt + "\n����: " + ordseq;
			
			if (MessageDialog.openQuestion(getShell(), TITLE, msg)) {
				OrderDao dao = new OrderDao();
				if (dao.existOrdmst(orddt, ordseq)) { // �ߺ� ���ε� üũ
					MessageDialog.openInformation(getShell(), TITLE, "������ ���ε����ڿ� ������ �̹� �ֹ������� �����ϹǷ� ���� ���ε带 �Ͻ� �� �����ϴ�.");
					return;
					
//					String dateStr = dt_orddt.getYear() + "-" + monthStr + "-" + dayStr;
//					msg = "������ ���ڿ� ������ �̹� �ֹ������� �����մϴ�. [" + dateStr + ", " + ordseq + "]\n"
//							+ "���ο� �ֹ��������� ��ü�Ͻðڽ��ϱ�?\n*������ �ֹ������� �����˴ϴ�.";
//					boolean flag = MessageDialog.openQuestion(getShell(), TITLE, msg);
//					if (flag) {
//
//						int count = dao.getEcountSentOrderNum(orddt, ordseq);
//						if (count > 0) {
//							msg = "�̹� ��ī��Ʈ�� ��������� �ֹ������� �־� ��ü�� �� �����ϴ�.\n" + "[" + dateStr + ", " + ordseq + "]";
//							MessageDialog.openInformation(getShell(), TITLE, msg);
//						} else {
//							// orddt, ordseq �� ��ġ�ϴ� orddtl �� ����Ÿ ������ ���ο� �ֹ��������� ���ε��Ŵ
//							dao.updateloadOrderList(orddt, ordseq, orderList);
//							tableViewer.setInput(null);
//							MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� �߰��Ͽ����ϴ�.");
//						}
//
//					} else {
//						return;
//					}

				} else {
					dao.insertOrderList(orddt, ordseq, orderList);//�ֹ��� �ε���=[22], ������ [21]
					tableViewer.setInput(null);
					MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� ���ε��Ͽ����ϴ�.");
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	void refreshOrder() {
		try {
			List<List<String>> orderList = (List<List<String>>) this.tableViewer.getInput();
			if (orderList == null || orderList.size() == 0) {
				return;
			}
			compareProdcdToMaster(orderList);
			// table.clearItems();
			tableViewer.setInput(null);
			tableViewer.setInput(orderList);
			table.setSelection(-1);
			tableViewer.refresh();
			this.text_total.setText(String.valueOf(orderList.size()));
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	public void setOrderDate(String from, String to) {
		orderDayStrFrom = from;
		orderDayStrTo = to;
		// TODO ���� ����
		// -- �ֹ� ���� , ���� ��¥�� ���ø��� ����
		// -- ���ø� ������ ftp ����
		// -- ����api ȣ��
	}

	
//	private void close() {
//		CTabFolder mainTabFolder = ((MainView)YDMAPluginUtils.getView(MainView.ID)).getMainTabFolder();
//		CTabItem [] items = mainTabFolder.getItems();
//		for(int i=0; i<items.length; i++) {
//			CTabItem item = items[i];
//			if(item.getControl() instanceof YDWMOrderListUpload) {
//				mainTabFolder.getItem(i).getControl().dispose();
//				mainTabFolder.getItem(i).dispose();
//			}
//		}
//	}



//	/**
//	 * �׸��忡 �ε��ÿ� üũ
//	 * 
//	 * @param parsedStr
//	 * @param order
//	 * @param prodcdList
//	 */
//	private void compareWithOrderData(String parsedStr, List<String> order, List<String> prodcdList) {
//		for (String prodcd : prodcdList) {
//			if (parsedStr.contains(prodcd)) {
//				order.set(order.size() - 1, "T");
//				return;
//			}
//		}
//	}

	void openOrderDetail(boolean isNew) {

		if (!isNew) { // --����
			List<List<String>> list = (List<List<String>>) this.tableViewer.getInput();
			List<String> order = (List<String>) list.get(selectedRowNoMst);

			OrderDetailDialog d = new OrderDetailDialog(this.getShell(), order, this);
			d.open();

		}

	}

	void deleteOrder(int rowNo) {

		boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "������ �ֹ������� �����Ͻðڽ��ϱ�?");
		if (flag) {
			List<List<String>> contents = (List<List<String>>) this.tableViewer.getInput();
			Object obj = contents.remove(rowNo);
			if (obj != null) {
				this.tableViewer.refresh();
				MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� �����Ǿ����ϴ�.");
			}

		}

	}

	class MyContentProvider extends ArrayContentProvider implements IStructuredContentProvider {
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

		public String getColumnText(Object element, int columnIndex) {

			String value = "";

			List<String> rowContent = (List<String>) element;
			value = (String) rowContent.get(columnIndex);

			return value;
		}

		public Color getBackground(Object element, int columnIndex) {

			List<String> list = (List<String>) element;
			String errflag = list.get(list.size() - 1);

			if (errflag.equals("X")) {// �ֹ����� ��ǰ�ڵ尡 ����ʴ�(no eXist) ���
				if(!"0".equals(list.get(20))){
					return SWTResourceManager.getColor(SWT.COLOR_GREEN);	
				}
				
			} else if (errflag.equals("F")) {// �ֹ������� ��ǰ�� ��ǰ����Ÿ�� ��ġ ����(Fault) ���
				return SWTResourceManager.getColor(SWT.COLOR_RED);
			} else if (errflag.equals("B")) {// ��, �ù�� �Ѵ� ���� ���
				// return SWTResourceManager.getColor(SWT.COLOR_MAGENTA);
				return SWTResourceManager.getColor(255, 140, 0);
			}

//			else if (errflag.equals("R")) {//��ǰ�ڵ�� �����̳� Rack ������ ��� ���� ���
//				return SWTResourceManager.getColor(255, 140, 0);
//			}
//			else if (errflag.equals("E")) {//��ǰ�ڵ�� �����̳� Exp ������ ��� ���� ���
//				return SWTResourceManager.getColor(SWT.COLOR_MAGENTA);
//			}

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
