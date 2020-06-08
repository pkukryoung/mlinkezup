package com.kdj.mlink.ezup3.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.data.dao.CategoryDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.kdj.mlink.ezup3.data.dao.ShopProductDto;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDto;
import com.kdj.mlink.ezup3.ui.ProductMstDetail.MyContentProvider;
import com.kdj.mlink.ezup3.ui.ProductMstDetail.MyLableProvider;

import org.eclipse.swt.widgets.Button;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;


public class ShoppingMallCategoryMappingDetail extends CommandDialog{

	String TITLE = "쇼핑몰카테고리등록";
	ProductMstDetail opener;
	
	String[][] column_name_dt = { { "쇼핑몰", "120" }, { "아이디", "150" }, { "제목", "300" }, { "쇼핑몰카테고리", "250" }};
	String[][] column_name_ok = {{ "No.", "40" }, { "쇼핑몰", "100" }, { "마켓분류", "80" }, { "제목", "250" },{ "카테고리코드", "200" }, { "카테고리(쇼핑몰)", "250" }};
    
	Button btnOk;
	Button btnCancel;
	Composite composite_opt;
	String prodcd;
	private GridTableViewer gtv_shopdt;
	private GridTableViewer gtv_shopok;
	private Grid gd_mapOk;	
	private Grid gd_mapDt;
	private Composite composite;
	private Composite composite_1;
	private Label lblNewLabel;
	private Composite composite_6;
	private Composite composite_7;
	private Composite composite_8;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Combo cb_date;
	private DateTime dt_time_to;
	private DateTime dt_time_from;
	private Label label_3;
	private Button btn_today;
	private Button btn_week;
	private Button btn_month1;
	private Button btn_month3;
	private Combo cb_shopping;
	private Combo cb_yesorno;
	private Button btn_shopsearch;
	private Button btn_titlesearch;
	private Combo cb_searchtitle;
	private Text txt_searchnm;
	String largecateg;
	String midiumcateg;
	String smallcateg;
	String detailcateg;
	
	public ShoppingMallCategoryMappingDetail(Shell parentShell, ProductMstDetail opener, String prodcd, String largecateg, String midiumcateg, String smallcateg, String detailcateg) {
		super(parentShell);
		this.opener = opener;
		this.prodcd = prodcd;
		this.largecateg = largecateg;
		this.midiumcateg = midiumcateg;
		this.smallcateg = smallcateg;
		this.detailcateg = detailcateg;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));
		
		composite = new Composite(container, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(3, false));
		
		composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));
		
		lblNewLabel = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		gd_lblNewLabel.widthHint = 61;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("매핑현황");
		
		gtv_shopdt = new GridTableViewer(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		gd_mapDt = gtv_shopdt.getGrid();
		gd_mapDt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		gd_mapDt.setHeaderVisible(true);
		
				gtv_shopdt.setLabelProvider(new MyLableProvider());
				gtv_shopdt.setContentProvider(new MyContentProvider());
		
		Button btn_delete = new Button(composite, SWT.NONE);
		btn_delete.setText("선택삭제");
		
		composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		composite_6.setLayout(new GridLayout(9, false));
		
		label = new Label(composite_6, SWT.NONE);
		label.setText("일 자");
		label.setAlignment(SWT.CENTER);
		
		cb_date = new Combo(composite_6, SWT.NONE);
		cb_date.setItems(new String[] {"등록일", "수정일"});
		cb_date.select(0);
		
		dt_time_to = new DateTime(composite_6, SWT.BORDER);
		dt_time_to.setYear(2019);
		dt_time_to.setMonth(8);
		dt_time_to.setDay(19);
		
		label_3 = new Label(composite_6, SWT.NONE);
		label_3.setText("~");
		
		dt_time_from = new DateTime(composite_6, SWT.BORDER);
		dt_time_from.setYear(2019);
		dt_time_from.setMonth(8);
		dt_time_from.setDay(19);
		
		btn_today = new Button(composite_6, SWT.NONE);
		btn_today.setText("오늘");
		
		btn_week = new Button(composite_6, SWT.NONE);
		btn_week.setText("1주일");
		
		btn_month1 = new Button(composite_6, SWT.NONE);
		btn_month1.setText("한달");
		
		btn_month3 = new Button(composite_6, SWT.NONE);
		btn_month3.setText("3개월");
		
		composite_7 = new Composite(composite, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		composite_7.setLayout(new GridLayout(4, false));
		
		label_1 = new Label(composite_7, SWT.NONE);
		label_1.setText("선택사항");
		label_1.setAlignment(SWT.CENTER);
		
		cb_shopping = new Combo(composite_7, SWT.NONE);
		cb_shopping.setItems(new String[] {"쇼핑몰선택","11번가"});
		cb_shopping.select(0);
		
		cb_yesorno = new Combo(composite_7, SWT.NONE);
		cb_yesorno.setItems(new String[] {"사용여부", "사용중","미사용"});
		cb_yesorno.select(0);
		
		btn_shopsearch = new Button(composite_7, SWT.NONE);
		btn_shopsearch.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		btn_shopsearch.setText("검색");
		btn_shopsearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCategoryShoppingMall();
			}
		});
		
		composite_8 = new Composite(composite, SWT.NONE);
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		composite_8.setLayout(new GridLayout(4, false));
		
		label_2 = new Label(composite_8, SWT.NONE);
		label_2.setText("검색항목");
		label_2.setAlignment(SWT.CENTER);
		
		cb_searchtitle = new Combo(composite_8, SWT.NONE);
		cb_searchtitle.setItems(new String[] {"제목", "카테고리코드"});
		cb_searchtitle.select(0);
		
		txt_searchnm = new Text(composite_8, SWT.BORDER);
		GridData gd_txt_searchnm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_searchnm.widthHint = 400;
		txt_searchnm.setLayoutData(gd_txt_searchnm);
		
		btn_titlesearch = new Button(composite_8, SWT.NONE);
		btn_titlesearch.setText("검색");
		btn_titlesearch.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		
		gtv_shopok = new GridTableViewer(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		gd_mapOk = gtv_shopok.getGrid();
		gd_mapOk.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		gd_mapOk.setHeaderVisible(true);
		
		gtv_shopok.setLabelProvider(new MyLableProvider());
		gtv_shopok.setContentProvider(new MyContentProvider());
		
		Button btn_mapping = new Button(composite, SWT.NONE);
		btn_mapping.setText("선택매핑");
		//btn_mapping.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btn_mapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setProductMstShopCategoryUpdate();
			}
		});
		
		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (int i = 0; i < column_name_dt.length; i++) {
			String[] column = column_name_dt[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_mapDt, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		
		for (int i = 0; i < column_name_ok.length; i++) {
			String[] column = column_name_ok[i];
			GridColumn tableViewerColumn_x = new GridColumn(gd_mapOk, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		
		setCategory();
		
		return container;

	}	
	
	//처음시작시 마지막 카테고리
	private void setCategory() {
		if(detailcateg.equals("")) {
			txt_searchnm.setText(smallcateg);
		}else if(smallcateg.equals("")){
			txt_searchnm.setText(midiumcateg);
		}else {
			txt_searchnm.setText(detailcateg);
		}
		
	}
	
	List<List<String>> cate = new ArrayList<>();	
	List<List<String>> idcheck = new ArrayList<>();
	List<List<String>> shopcheck = new ArrayList<>();
	
	//검색클릭시 내용 가지고 오기
	protected void getCategoryShoppingMall() {
		ShoppingMallDao dao = new ShoppingMallDao();
		if(cb_shopping.getSelectionIndex()==0) {
			MessageDialog.openInformation(getShell(), TITLE, "쇼핑몰을 선택하신후에 진행하여 주시기 바랍니다.");
			return;
		}
		try {
			//쇼핑몰카테고리 가지고 오기
			shopcheck = dao.getShopCodeAllList();
			
			List<String> list = dao.getShopMstOneSelect(cb_shopping.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	//매핑클릭시
	protected void setProductMstShopCategoryUpdate() {
		List<List<String>> contents = new ArrayList<>();
		int[] selectedIdx = gd_mapOk.getSelectionIndices();
		if(selectedIdx.length<0) {
			MessageDialog.openInformation(getShell(), TITLE, "선택된 정보가 없습니다. 정보를 선택후 진행하여 주시기 바랍니다.");
			return;
		}
		for(int i= 0;i<selectedIdx.length;i++) {
			contents.add(((List<List<String>>)gtv_shopok.getInput()).get(selectedIdx[i]));
		}	 
		ShoppingMallDao dao = new ShoppingMallDao();
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
		btnOk= createButton(parent, IDialogConstants.OK_ID, "저장", false);
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
			ShoppingMallDto dto = (ShoppingMallDto) element;			
			String value = "";
			switch (columnIndex) {	
			case 0:
				value = dto.getRowno();
				break;
			case 1:
				value = dto.getShopcd();
				break;
			case 2:			
				value = "오픈마켓";
				break;
			case 3:
				value = dto.getShopcatnm();
				break;
			case 4:
				value = !dto.getShopdetcatcd().equals("")?dto.getShopdetcatcd():!dto.getShopsmlcatcd().equals("")?dto.getShopsmlcatcd():!dto.getShopmidcatcd().equals("")?
						dto.getShopmidcatcd():!dto.getShoplagcatcd().equals("")?dto.getShoplagcatcd():"";
				break;
			case 5:
				String lar ="";
				String mid ="";
				String sml ="";
				String det ="";
				for(List<String> list:cate) {
					if(!list.get(0).equals("1")) {
						if(list.get(2).equals(dto.getShoplagcatcd())) {
							lar = list.get(1);
						}
					}
					if(!list.get(0).equals("2")) {
						if(list.get(2).equals(dto.getShopmidcatcd())) {
							mid = list.get(1);
						}
					}
					if(!list.get(0).equals("3")) {
						if(list.get(2).equals(dto.getShopsmlcatcd())) {
							sml = list.get(1);
						}
					}
					if(!list.get(0).equals("4")) {
						if(list.get(2).equals(dto.getShopdetcatcd())) {
							det = list.get(1);
						}
					}				
				}
				value = !lar.equals("")?lar:!mid.equals("")?" > "+mid:!sml.equals("")?" > "+sml:!det.equals("")?" > "+det:"";				
				break;
			default:
				value = "";
			}

			return value;
		}

		public Color getBackground(Object element, int columnIndex) {

			List<String> list = (List<String>) element;
			String errflag = list.get(list.size() - 1);
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
