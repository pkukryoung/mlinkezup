package com.kdj.mlink.ezup3.ui;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

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
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kdj.mlink.ezup3.common.FtpUtil;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.data.dao.AutomationDao;
import com.kdj.mlink.ezup3.data.dao.CategoryDao;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.ExpDao;
import com.kdj.mlink.ezup3.data.dao.ProductIforDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.kdj.mlink.ezup3.data.dao.ProductOptDao;
import com.kdj.mlink.ezup3.data.dao.RackDao;
import com.kdj.mlink.ezup3.data.dao.SabangNetDao;
import com.kdj.mlink.ezup3.shop.common.ChromeExtention;
import com.kdj.mlink.ezup3.shop.common.ChromeScript;

public class ProductMstDetail extends CommandDialog {

	String[][] column_name_opt = { { "No.", "40" }, { "상품코드", "200" }, { "상품명", "300" }, { "규격", "140" },
			{ "EA", "50" }, { "판매", "70" }, { "품절", "70" }, { "미사용", "70" }, { "안전재고", "90" }, { "가상재고", "90" },
			{ "추가금액", "90" }, { "삭제", "70" } };

	String TITLE = "상품관리";
	ProductManager opener;
	ProductMstDto dto;
	static String host;

	public ProductMstDto getDto() {
		return dto;
	}

	Button btnOk;
	Button btnCancel;
	Button btn_Copy;
	Button btn_11st;

	private Label label;
	private Label label_1;
	private Button button;
	private Button button_1;
	private ViewForm viewForm;
	private Button button_2;
	private Button button_3;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Text txt_mainimg;
	private Text txt_subimg1;
	private Text txt_subimg2;
	private Button button_4;
	private Button button_5;
	private Button button_6;
	private Button button_7;
	private Button button_8;
	private Button button_9;
	private Label lblNewLabel_16;
	private Button basicIF;
	private Button propertyIF;
	private Combo cb_infornum;
	private Combo cb_propertynum;
	private Label label_5;
	private Label label_6;
	private Label label_7;
	private Text txt_searchwd;
	private Button btn_sabang;

	public Text txt_prodcd;
	private Text txt_prodnm;
	private Text txt_spec;
	public Text txt_ecountcd;
	private Text txt_price;
	private Button ck_flagset;
	private Button ck_flagplus;
	private Button ck_flagout;
	private Combo cb_delyn;
	private Combo cb_useyn;
	private Text txt_tagprice;
	private Text txt_cusprice;
	private Text txt_aproinvt;
	private Text txt_remark;
	private Text txt_sabcd;
	public Text txt_ea;
//	private Button btn_delopt;
	private Button btn_addopt;
	JFrame frame;
	java.awt.FileDialog fd;

	Composite composite_opt;
	public GridTableViewer tableViewer_opt;
	private String fname;
	private String fdirectory;
	private String fname2;
	private String fdirectory2;
	private String fname3;
	private String fdirectory3;
	private Button btn_11steidt;
	private Button btn_11stDiscontinued;
	Label lbImage1;
	JLabel lb;
	String imgTargetPath;
	String imgTargetPath2;
	String imgTargetPath3;
	String Prodprodcd;
//	int selectedIdxOpt = -1;

	public ProductMstDetail(Shell parentShell, ProductMstDto dto, ProductManager opener, String Prodprodcd) {
		super(parentShell);
		this.opener = opener;
		this.dto = dto;
		this.Prodprodcd = Prodprodcd;

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(null);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite.heightHint = 696;
		composite.setLayoutData(gd_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(54, 9, 66, 20);
		lblNewLabel.setBounds(5, 10, 115, 20);
		lblNewLabel.setText("*상품코드");

		txt_prodcd = new Text(composite, SWT.BORDER);
		txt_prodcd.setBounds(125, 5, 159, 30);
		txt_prodcd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_prodnm.setFocus();
				}
			}
		});
		txt_prodcd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				txt_prodcd.setText(txt_prodcd.getText().trim().toUpperCase());
			}

			@Override
			public void focusGained(FocusEvent e) {
				txt_spec.getShell().setImeInputMode(SWT.NONE);
			}
		});
		txt_prodcd.setTextLimit(20);
		txt_prodcd.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.BOLD));

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(5, 43, 115, 20);
		lblNewLabel_1.setText("*상품명");

		txt_prodnm = new Text(composite, SWT.BORDER);
		txt_prodnm.setBounds(125, 38, 520, 26);
		txt_prodnm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_prodnm.getShell().setImeInputMode(SWT.ALPHA | SWT.PHONETIC);
			}
		});
		txt_prodnm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String prodnm = txt_prodnm.getText();
				int byteview = prodnm.getBytes().length;
				txt_byte.setText(String.valueOf(byteview));
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_spec.setFocus();
				}
			}
		});

		txt_prodnm.setTextLimit(200);

		button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(641, 231, 70, 26);
		button_1.setText("파일선택");
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getImageFileMain();
			}
		});

		viewForm = new ViewForm(composite, SWT.NONE);
		viewForm.setBounds(1155, 186, 0, 0);

		Label lblNewLabel_8 = new Label(composite, SWT.NONE);
		lblNewLabel_8.setAlignment(SWT.RIGHT);
		lblNewLabel_8.setBounds(5, 72, 115, 26);
		lblNewLabel_8.setText("규격");

		txt_spec = new Text(composite, SWT.BORDER);
		txt_spec.setBounds(125, 67, 360, 26);
		txt_spec.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_spec.getShell().setImeInputMode(SWT.ALPHA | SWT.PHONETIC);
			}
		});
		txt_spec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_price.setFocus();
				}
			}
		});
		txt_spec.setTextLimit(100);

		Label lblNewLabel_11 = new Label(composite, SWT.NONE);
		lblNewLabel_11.setAlignment(SWT.RIGHT);
		lblNewLabel_11.setBounds(5, 101, 115, 29);
		lblNewLabel_11.setText("ERP상품코드");

		txt_ecountcd = new Text(composite, SWT.BORDER);
		txt_ecountcd.setBounds(125, 96, 160, 29);
		txt_ecountcd.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.BOLD));
		txt_ecountcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setBounds(144, 128, 80, 20);
		lblNewLabel_7.setText("세트구분");

		ck_flagset = new Button(composite, SWT.CHECK);
		ck_flagset.setBounds(125, 128, 16, 20);
		// ck_flagset.setText("(세트 상품일 경우 V 체크)");

		Label lblNewLabel_3 = new Label(composite, SWT.RIGHT);
		lblNewLabel_3.setBounds(243, 128, 110, 20);
		lblNewLabel_3.setText("세트플러스구분");

		ck_flagplus = new Button(composite, SWT.CHECK);
		ck_flagplus.setBounds(227, 128, 16, 20);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(395, 128, 90, 20);
		lblNewLabel_2.setText("외주코드구분");

		ck_flagout = new Button(composite, SWT.CHECK);
		ck_flagout.setBounds(376, 128, 16, 20);

		Label lblNewLabel_10 = new Label(composite, SWT.NONE);
		lblNewLabel_10.setAlignment(SWT.RIGHT);
		lblNewLabel_10.setBounds(5, 153, 115, 26);
		lblNewLabel_10.setText("*판매가");

		txt_price = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_price.setBounds(125, 151, 70, 25);
		txt_price.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_tagprice.setFocus();
				}
			}
		});
		txt_price.setText("0");

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setAlignment(SWT.RIGHT);
		lblNewLabel_4.setBounds(5, 182, 115, 26);
		lblNewLabel_4.setText("택가(소비자가)");

		txt_tagprice = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_tagprice.setBounds(125, 179, 70, 25);
		txt_tagprice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_remark.setFocus();
				}
			}
		});
		txt_tagprice.setText("0");

		label = new Label(composite, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		label.setBounds(5, 211, 115, 26);
		label.setText("사업자공급가");

		txt_cusprice = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_cusprice.setBounds(125, 207, 70, 25);
		txt_cusprice.setText("0");

		label_1 = new Label(composite, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(5, 266, 115, 26);
		label_1.setText("적정재고");

		txt_aproinvt = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_aproinvt.setBounds(125, 263, 70, 25);
		txt_aproinvt.setText("0");

		Label lblNewLabel_9 = new Label(composite, SWT.NONE);
		lblNewLabel_9.setAlignment(SWT.RIGHT);
		lblNewLabel_9.setBounds(5, 324, 115, 20);
		lblNewLabel_9.setText("상세설명");

		txt_remark = new Text(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txt_remark.setBounds(125, 319, 850, 70);
		txt_remark.setText("<center>\r\n<img src=\" \"><br>\r\n \r\n\r\n</center>");
		txt_remark.setTextLimit(10000);

		button = new Button(composite, SWT.NONE);
		button.setBounds(978, 319, 303, 70);
		button.setText("이미지 상세보기");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getImageload(txt_remark.getText().trim());

			}
		});

		Label lblNewLabel_13 = new Label(composite, SWT.NONE);
		lblNewLabel_13.setAlignment(SWT.RIGHT);
		lblNewLabel_13.setBounds(5, 292, 115, 26);
		lblNewLabel_13.setText("EA");

		txt_ea = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_ea.setBounds(125, 291, 70, 25);
		txt_ea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					btnOk.setFocus();
				}
			}
		});
		txt_ea.setText("0");

		Label lblNewLabel_12 = new Label(composite, SWT.NONE);
		lblNewLabel_12.setAlignment(SWT.RIGHT);
		lblNewLabel_12.setBounds(5, 399, 115, 29);
		lblNewLabel_12.setText("사방넷코드");

		txt_sabcd = new Text(composite, SWT.BORDER);
		txt_sabcd.setBounds(125, 394, 159, 29);
		txt_sabcd.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.BOLD));
		txt_sabcd.setEditable(false);
		txt_sabcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setAlignment(SWT.RIGHT);
		lblNewLabel_6.setBounds(5, 429, 115, 28);
		lblNewLabel_6.setText("품절");

		cb_useyn = new Combo(composite, SWT.READ_ONLY);
		cb_useyn.setBounds(125, 426, 62, 28);
		cb_useyn.setItems(new String[] { "N", "Y" });
		cb_useyn.select(0);

		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setBounds(200, 429, 40, 28);
		lblNewLabel_5.setText("삭제");

		cb_delyn = new Combo(composite, SWT.READ_ONLY);
		cb_delyn.setBounds(243, 426, 62, 28);
		cb_delyn.setItems(new String[] { "N", "Y" });
		cb_delyn.select(0);

		button_2 = new Button(composite, SWT.NONE);
		button_2.setBounds(641, 260, 70, 26);
		button_2.setText("파일선택");
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getImageFileSub1();
			}
		});

		button_3 = new Button(composite, SWT.NONE);
		button_3.setBounds(641, 289, 70, 26);
		button_3.setText("파일선택");
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getImageFileSub2();
			}
		});

		label_2 = new Label(composite, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(555, 236, 83, 26);
		label_2.setText("대표이미지");

		label_3 = new Label(composite, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setBounds(555, 265, 83, 26);
		label_3.setText("부가이미지1");

		label_4 = new Label(composite, SWT.NONE);
		label_4.setAlignment(SWT.RIGHT);
		label_4.setBounds(555, 292, 83, 26);
		label_4.setText("부가이미지2");

		txt_mainimg = new Text(composite, SWT.BORDER);
		txt_mainimg.setBounds(714, 231, 110, 26);
		txt_mainimg.setTextLimit(100);
		txt_mainimg.setText("");

		txt_subimg1 = new Text(composite, SWT.BORDER);
		txt_subimg1.setBounds(714, 260, 110, 26);
		txt_subimg1.setTextLimit(100);
		txt_subimg1.setText("");

		txt_subimg2 = new Text(composite, SWT.BORDER);
		txt_subimg2.setBounds(714, 289, 110, 26);
		txt_subimg2.setTextLimit(100);
		txt_subimg2.setText("");

		button_4 = new Button(composite, SWT.NONE);
		button_4.setBounds(827, 231, 100, 26);
		button_4.setText("이미지보기");
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getOpenImageMain();
			}
		});

		button_5 = new Button(composite, SWT.NONE);
		button_5.setBounds(827, 260, 100, 26);
		button_5.setText("이미지보기");
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getOpenImageSub1();
			}
		});

		button_6 = new Button(composite, SWT.NONE);
		button_6.setBounds(827, 289, 100, 26);
		button_6.setText("이미지보기");
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getOpenImageSub2();
			}
		});

		button_7 = new Button(composite, SWT.NONE);
		button_7.setBounds(930, 231, 45, 26);
		button_7.setText("Clear");
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getClearMain();
			}
		});

		button_8 = new Button(composite, SWT.NONE);
		button_8.setBounds(930, 260, 45, 26);
		button_8.setText("Clear");
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getClearSub1();
			}
		});

		button_9 = new Button(composite, SWT.NONE);
		button_9.setBounds(930, 289, 45, 26);
		button_9.setText("Clear");
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getClearSub2();
			}
		});

		lblNewLabel_16 = new Label(composite, SWT.NONE);
		lblNewLabel_16.setBounds(980, 10, 300, 300);

		basicIF = new Button(composite, SWT.NONE);
		basicIF.setBounds(1182, 458, 100, 23);
		basicIF.setText("상품기본정보");
		basicIF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getBasicInformation();
			}
		});

		propertyIF = new Button(composite, SWT.NONE);
		propertyIF.setBounds(1182, 487, 100, 23);
		propertyIF.setText("상품속성정보");
		propertyIF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getPrepertyInformation();
			}
		});

		cb_infornum = new Combo(composite, SWT.READ_ONLY);
		cb_infornum.setBounds(1079, 458, 100, 23);
		cb_infornum.setItems();

		cb_propertynum = new Combo(composite, SWT.READ_ONLY);
		cb_propertynum.setBounds(1079, 487, 100, 23);
		cb_propertynum.setItems();

		label_5 = new Label(composite, SWT.NONE);
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(986, 460, 90, 23);
		label_5.setText("상품기본정보");

		label_6 = new Label(composite, SWT.NONE);
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(986, 489, 90, 23);
		label_6.setText("상품속성정보");

		label_7 = new Label(composite, SWT.NONE);
		label_7.setBounds(5, 491, 115, 29);
		label_7.setAlignment(SWT.RIGHT);
		label_7.setText("사이트검색어");

		txt_searchwd = new Text(composite, SWT.BORDER);
		txt_searchwd.setBounds(125, 489, 640, 26);
		txt_searchwd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(124, 519, 1157, 171);

		tableViewer_opt = new GridTableViewer(composite_1,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		Grid table_opt = tableViewer_opt.getGrid();
		table_opt.setBounds(3, 35, 1148, 129);
		tableViewer_opt.addDoubleClickListener(event -> {
			if (tableViewer_opt.getGrid().getSelectionIndex() >= 0) {
				openOptProduct(false);
			}
		});
		tableViewer_opt.addSelectionChangedListener(event -> {

		});

		table_opt.setRowHeaderVisible(true);
		table_opt.setHeaderVisible(true);

		btn_addopt = new Button(composite_1, SWT.NONE);
		btn_addopt.setEnabled(false);
		btn_addopt.setBounds(3, 3, 93, 30);
		btn_addopt.setText("옵션상품추가");
		btn_addopt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openOptProduct(true);
			}
		});

		lb_category = new Label(composite, SWT.NONE);
		lb_category.setText("카테고리분류");
		lb_category.setAlignment(SWT.RIGHT);
		lb_category.setBounds(5, 461, 115, 29);

		cb_categlagcd = new Combo(composite, SWT.READ_ONLY);
		cb_categlagcd.setItems(new String[] { "대분류선택", "" });
		cb_categlagcd.setBounds(125, 458, 130, 28);
		cb_categlagcd.select(0);
		cb_categlagcd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getcategmidcd();
			}
		});

		cb_categmidcd = new Combo(composite, SWT.READ_ONLY);
		cb_categmidcd.setItems(new String[] { "중분류선택", "" });
		cb_categmidcd.setBounds(260, 458, 130, 28);
		cb_categmidcd.select(0);
		cb_categmidcd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getSmallCategory();
			}
		});

		cb_categsmlcd = new Combo(composite, SWT.READ_ONLY);
		cb_categsmlcd.setItems(new String[] { "소분류선택", "" });
		cb_categsmlcd.setBounds(395, 458, 130, 28);
		cb_categsmlcd.select(0);
		cb_categsmlcd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getDetailCategory();
			}
		});

		cb_categdetcd = new Combo(composite, SWT.READ_ONLY);
		cb_categdetcd.setItems(new String[] { "세분류선택", "" });
		cb_categdetcd.setBounds(530, 458, 130, 28);
		cb_categdetcd.select(0);

		btn_Category = new Button(composite, SWT.NONE);
		btn_Category.setText("카테고리등록");
		btn_Category.setBounds(665, 458, 100, 28);
		btn_Category.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openCategory();
			}
		});

		btn_proposal = new Button(composite, SWT.NONE);
		btn_proposal.setText("상품명제안");
		btn_proposal.setBounds(650, 38, 100, 26);
		btn_proposal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				webCrawling(false);
			}
		});

		btn_bestsc = new Button(composite, SWT.NONE);
		btn_bestsc.setText("인기검색어");
		btn_bestsc.setBounds(770, 489, 100, 26);

		txt_byte = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_byte.setText("");
		txt_byte.setBounds(754, 38, 50, 26);

		label_8 = new Label(composite, SWT.NONE);
		label_8.setText("Byte");
		label_8.setBounds(807, 40, 40, 26);

		label_9 = new Label(composite, SWT.NONE);
		label_9.setText("발주단가");
		label_9.setAlignment(SWT.RIGHT);
		label_9.setBounds(5, 239, 115, 26);

		txt_odprice = new Text(composite, SWT.BORDER | SWT.RIGHT);
		txt_odprice.setText("0");
		txt_odprice.setBounds(125, 235, 70, 25);

		btn_bestsc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				webCrawling(true);
			}
		});

		btn_categmap = new Button(composite, SWT.NONE);
		btn_categmap.setText("카테고리매핑");
		btn_categmap.setBounds(770, 458, 100, 28);
		btn_categmap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openShoppingMallCategoryMapping();
			}
		});

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		for (int i = 0; i < column_name_opt.length; i++) {
			String[] column = column_name_opt[i];
			GridColumn tableViewerColumn_x = new GridColumn(table_opt, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

			if (i == 1 || i == 2 || i == 3) {
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.LEFT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}

			if (i == 8 || i == 9 || i == 10) { // 배송비, 주문합게
				MyCellRenderer myCellRenderer = new MyCellRenderer();
				myCellRenderer.setColumnAlign(SWT.RIGHT);
				tableViewerColumn_x.setCellRenderer(myCellRenderer);
			}
		}

		tableViewer_opt.setLabelProvider(new MyLableProvider());
		tableViewer_opt.setContentProvider(new MyContentProvider());

		// 상품 일반정보 세팅
		if (this.dto != null) {
			setProductInfo(this.dto);
			// 수정인 procd 편집불가로 한다.
			txt_prodcd.setEditable(false);
			txt_prodcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
			// txt_prodnm.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
			// 수정인 경우 첫 포커스
			txt_prodnm.setFocus();
		}

		// 신규상품등록인 경우는 opt 추가 못함 제외.
		// 일단 신규상품등록되고 나서 수정모드에서 opt 추가 가능하게 함
		if (this.dto == null) {
			if (!Prodprodcd.equals("0")) {
				setCopyNInsert();
				setOptList();
			} else {
				getDefault();
				getDefault2();
				cb_infornum.select(0);
				cb_propertynum.select(0);
				btn_addopt.setEnabled(false);
			}

		} else {
			btn_addopt.setEnabled(true);
			setOptList(); // 옵션테이블 세팅
		}

		getCategoryList();

		return container;

	}

	private void setCopyNInsert() {
		btn_addopt.setEnabled(false);
		ProductMstDao dao = new ProductMstDao();
		try {
			List<String> list = dao.getProductMstList(Prodprodcd);
			txt_prodcd.setText(list.get(0));
			txt_prodnm.setText(list.get(1));
			txt_spec.setText(list.get(2));
			txt_ecountcd.setText("");
			if (list.get(4).equals("1")) {
				ck_flagset.setSelection(true);
			} else {
				ck_flagset.setSelection(false);
			}
			if (list.get(5).equals("1")) {
				ck_flagplus.setSelection(true);
			} else {
				ck_flagplus.setSelection(false);
			}
			if (list.get(6).equals("1")) {
				ck_flagout.setSelection(true);
			} else {
				ck_flagout.setSelection(false);
			}
			txt_price.setText(list.get(7));
			txt_tagprice.setText(list.get(8));
			txt_sabcd.setText("");
			txt_remark.setText(list.get(10));
			txt_ea.setText(list.get(11));
			if (list.get(12).equals("Y")) {
				cb_useyn.select(1);
			} else {
				cb_useyn.select(0);
			}
			if (list.get(13).equals("Y")) {
				cb_delyn.select(1);
			} else {
				cb_delyn.select(0);
			}
			txt_cusprice.setText(list.get(19));
			txt_aproinvt.setText(list.get(20));
			txt_mainimg.setText(list.get(21));
			txt_subimg1.setText(list.get(22));
			txt_subimg2.setText(list.get(23));
			if (txt_mainimg.getText().length() != 0) {
				getOpenImageMain();
			} else if (txt_mainimg.getText().length() == 0 && txt_subimg1.getText().length() != 0) {
				getOpenImageSub1();
			} else {
				getOpenImageSub2();
			}
			// ERP코드
			txt_ecountcd.setText(list.get(3));
			getDefault();
			getDefault2();
			setinfo(list.get(24));
			setattrval(list.get(25));
			txt_searchwd.setText(list.get(26));
			txt_odprice.setText(list.get(31));
			String prodnm = txt_prodnm.getText();
			int byteview = prodnm.getBytes().length;
			txt_byte.setText(String.valueOf(byteview));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getDefault() {
		String strappend = "";
		String[] strsplit;
		try {
			ProductIforDao dao = new ProductIforDao();
			List<List<String>> contents = dao.getProdInforName();
			for (List<String> list : contents) {
				strappend += list.get(0) + "_" + list.get(1) + ",";
			}
			strsplit = strappend.split(",");
			cb_infornum.setItems(strsplit);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getDefault2() {
		String strappend = "";
		String[] strsplit;
		try {
			ProductIforDao dao = new ProductIforDao();
			List<List<String>> contents = dao.getPropername();
			for (List<String> list : contents) {
				strappend += list.get(0) + "_" + list.get(1) + ",";
			}
			strsplit = strappend.split(",");
			cb_propertynum.setItems(strsplit);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 시작시 전체 카테고리 가져오기
	private void getCategoryList() {
		getLargeCategory();
		ProductMstDao dao = new ProductMstDao();
		String prodcd = txt_prodcd.getText();
		try {
			List<String> list = dao.getCategoryList(prodcd);
			if (list.size() > 0) {
				if (list.get(0) != null) {
					setcateglagcd(list.get(0));
				}
				getcategmidcd();
				if (list.get(1) != null) {
					setcategmidcd(list.get(1));
				}
				getSmallCategory();
				if (list.get(2) != null) {
					setcategsmlcd(list.get(2));
				}
				getDetailCategory();
				if (list.get(3) != null) {
					setcategdetcd(list.get(3));
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void setcateglagcd(String info) {
		for (int i = 0; i < this.cb_categlagcd.getItems().length; i++) {
			String type = this.cb_categlagcd.getItems()[i];
			String cate = type.split("[(]")[1].split("[)]")[0];
			if (cate.equals(info)) {
				this.cb_categlagcd.setText(type);
				break;
			}
		}
	}

	void setcategmidcd(String info) {
		for (int i = 0; i < this.cb_categmidcd.getItems().length; i++) {
			String type = this.cb_categmidcd.getItems()[i];
			String cate = type.split("[(]")[1].split("[)]")[0];
			if (cate.equals(info)) {
				this.cb_categmidcd.setText(type);
				break;
			}
		}
	}

	void setcategsmlcd(String info) {
		for (int i = 0; i < this.cb_categsmlcd.getItems().length; i++) {
			String type = this.cb_categsmlcd.getItems()[i];
			String cate = type.split("[(]")[1].split("[)]")[0];
			if (cate.equals(info)) {
				this.cb_categsmlcd.setText(type);
				break;
			}
		}
	}

	void setcategdetcd(String info) {
		for (int i = 0; i < this.cb_categdetcd.getItems().length; i++) {
			String type = this.cb_categdetcd.getItems()[i];
			String cate = type.split("[(]")[1].split("[)]")[0];
			if (cate.equals(info)) {
				this.cb_categdetcd.setText(type);
				break;
			}
		}
	}

	// 대분류 가져오기
	private void getLargeCategory() {
		String category = "대분류선택(0),";
		cb_categsmlcd.setItems("소분류선택(0)");
		cb_categsmlcd.select(0);
		cb_categdetcd.setItems("세분류선택(0)");
		cb_categdetcd.select(0);
		cb_categmidcd.setItems("중분류선택(0)");
		cb_categmidcd.select(0);
		cb_categlagcd.setItems("대분류선택(0)");
		cb_categlagcd.select(0);
		// 대분류

		try {
			CategoryDao dao = new CategoryDao();
			List<List<String>> contents = dao.getLargeCategory();
			if (contents.size() > 0) {
				for (int i = 0; i < contents.size(); i++) {
					category += contents.get(i).get(1) + "(" + contents.get(i).get(0) + "),";
				}
				cb_categlagcd.setItems(category.split(","));
				cb_categlagcd.select(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 중분류가져오기
	private void getcategmidcd() {
		cb_categsmlcd.setItems("소분류선택(0)");
		cb_categsmlcd.select(0);
		cb_categdetcd.setItems("세분류선택(0)");
		cb_categdetcd.select(0);
		cb_categmidcd.setItems("중분류선택(0)");
		cb_categmidcd.select(0);
		List<List<String>> contents;

		String code = cb_categlagcd.getText().split("[(]")[1].split("[)]")[0];

		String category = "중분류선택(0),";
		try {
			if (!code.equals("0")) {
				CategoryDao dao = new CategoryDao();
				contents = dao.getMidiumCategory(code);
				if (contents.size() > 0) {
					for (int i = 0; i < contents.size(); i++) {
						category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
					}
					cb_categmidcd.setItems(category.split(","));
					cb_categmidcd.select(0);
				} else {
					cb_categmidcd.select(0);
				}
			} else {
				cb_categmidcd.select(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 소분류가져오기
	private void getSmallCategory() {
		cb_categdetcd.setItems("세분류선택(0)");
		cb_categdetcd.select(0);
		cb_categsmlcd.setItems("소분류선택(0)");
		List<List<String>> contents;

		String code = cb_categmidcd.getText().split("[(]")[1].split("[)]")[0];
		String category = "소분류선택(0),";
		try {
			if (!code.equals("0")) {
				CategoryDao dao = new CategoryDao();
				contents = dao.getSmallCategory(code);
				if (contents.size() > 0) {
					for (int i = 0; i < contents.size(); i++) {
						category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
					}
					cb_categsmlcd.setItems(category.split(","));
					cb_categsmlcd.select(0);
				} else {
					cb_categsmlcd.select(0);
				}
			} else {
				cb_categsmlcd.select(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 세분류가져오기
	private void getDetailCategory() {
		cb_categdetcd.setItems("세분류선택(0)");
		List<List<String>> contents;

		String code = cb_categsmlcd.getText().split("[(]")[1].split("[)]")[0];
		String category = "세분류선택(0),";
		try {
			if (!code.equals("0")) {
				CategoryDao dao = new CategoryDao();
				contents = dao.getDetailCategory(code);
				if (contents.size() > 0) {
					for (int i = 0; i < contents.size(); i++) {
						category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
					}
					cb_categdetcd.setItems(category.split(","));
					cb_categdetcd.select(0);
				} else {
					cb_categdetcd.select(0);
				}
			} else {
				cb_categdetcd.select(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setProductInfo(ProductMstDto dto) {

		try {
			txt_prodcd.setText(dto.getProdcd());
			txt_prodnm.setText(dto.getProdnm());
			txt_spec.setText(dto.getSpecdes());
			txt_ecountcd.setText(dto.getEcountcd());

			ck_flagset.setSelection(dto.getFlagset().equals("1"));
			ck_flagplus.setSelection(dto.getFlagplus().equals("1"));
			ck_flagout.setSelection(dto.getFlagout().equals("1"));

			txt_price.setText(dto.getPrice());
			txt_tagprice.setText(dto.getTagprice());
			txt_cusprice.setText(dto.getCusprice());
			txt_aproinvt.setText(dto.getAproinvt());
			txt_sabcd.setText(dto.getSabcd());
			txt_remark.setText(dto.getRemark());
			txt_ea.setText(dto.getEa());
			txt_mainimg.setText(dto.getRepstt());
			txt_subimg1.setText(dto.getAddtn1());
			txt_subimg2.setText(dto.getAddtn2());
			txt_searchwd.setText(dto.getSearchwd());
			String prodnm = txt_prodnm.getText();
			int byteview = prodnm.getBytes().length;
			txt_byte.setText(String.valueOf(byteview));
			if (dto.getOrderprice() != null) {
				txt_odprice.setText(dto.getOrderprice());
			}

			if (txt_mainimg.getText().length() != 0) {
				getOpenImageMain();
			} else if (txt_mainimg.getText().length() == 0 && txt_subimg1.getText().length() != 0) {
				getOpenImageSub1();
			} else {
				getOpenImageSub2();
			}
			if (dto.getUseyn().equals("Y")) {
				cb_useyn.select(1);
			} else {
				cb_useyn.select(0);
			}
			if (dto.getDelyn().equals("Y")) {
				cb_delyn.select(1);
			} else {
				cb_delyn.select(0);
			}
			getDefault();
			getDefault2();
			getLargeCategory();

			List<String> list = getInfornumber(txt_prodcd.getText().trim());
			if (list.get(0) != null && list.get(1) != null) {
				setinfo(list.get(0));
				setattrval(list.get(1));
			} else {
				cb_infornum.select(0);
				cb_propertynum.select(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	void setinfo(String info) {
		for (int i = 0; i < this.cb_infornum.getItems().length; i++) {
			String type = this.cb_infornum.getItems()[i];
			if (type.contains(info)) {
				this.cb_infornum.setText(type);
				break;
			}
		}
	}

	void setattrval(String attrval) {
		for (int i = 0; i < this.cb_propertynum.getItems().length; i++) {
			String mall = this.cb_propertynum.getItems()[i];
			if (mall.contains(attrval)) {
				this.cb_propertynum.setText(mall);
				break;
			}
		}
	}

	private List<String> getInfornumber(String prodcd) {
		ProductMstDao dao = new ProductMstDao();
		List<String> list = new ArrayList<>();
		try {
			list = dao.getNumberInforMationProperty(prodcd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void setOptList() {
		try {
			ProductOptDao dao = new ProductOptDao();
			if (!Prodprodcd.equals("0")) {
				List<List<String>> contents = dao.getProdcutOptList(Prodprodcd);
				tableViewer_opt.setInput(contents);
			} else {
				List<List<String>> contents = dao.getProdcutOptList(dto.getProdcd());
				tableViewer_opt.setInput(contents);
			}

		} catch (Exception e) {
			e.printStackTrace();
			openErrDialog(TITLE, e.getMessage());
		}

	}

	public void deleteOptProduct() {
		try {

			ProductOptDao dao = new ProductOptDao();

		} catch (Exception e) {
			e.printStackTrace();
			openErrDialog(TITLE, e.getMessage());
		}

	}

	public void openOptProduct(boolean isNew) {

		try {

			if (!isNew) { // 옵션상품을 수정하는 경우
				List<String> imsi = new ArrayList<>();
				List<List<String>> contents_opt = (List<List<String>>) this.tableViewer_opt.getInput();
				List<String> list = contents_opt.get(tableViewer_opt.getGrid().getSelectionIndex());
				imsi.add(Prodprodcd);
				ProdcutOptDetail d = new ProdcutOptDetail(this.getShell(), this, list, imsi);
				d.open();

			} else { // 옵션상품을 추가하는 경우
				List<String> imsi = new ArrayList<>();
				ProdcutOptDetail d = new ProdcutOptDetail(this.getShell(), this, null, imsi);
				d.open();
			}

		} catch (Exception e) {
			e.printStackTrace();
			openErrDialog(TITLE, e.getMessage());
		}

	}

	// 이미지 파일선택
	private void getImageFileMain() {
		fd = new FileDialog(frame, "파일열기", 0);
		fd.setDirectory("");
		fd.setVisible(true);
		fdirectory = fd.getDirectory();
		fname = fd.getFile();
		if (fd.getFile() != null) {
			txt_mainimg.setText(fd.getDirectory() + File.separator + fd.getFile());
		}

	}

	// 이미지 파일선택
	private void getImageFileSub1() {
		fd = new FileDialog(frame, "파일열기", 0);
		fd.setDirectory("");
		fd.setVisible(true);
		fdirectory2 = fd.getDirectory();
		fname2 = fd.getFile();
		if (fd.getFile() != null) {
			txt_subimg1.setText(fd.getDirectory() + File.separator + fd.getFile());
		}

	}

	// 이미지 파일선택
	private void getImageFileSub2() {
		fd = new FileDialog(frame, "파일열기", 0);
		fd.setDirectory("");
		fd.setVisible(true);
		fdirectory3 = fd.getDirectory();
		fname3 = fd.getFile();
		if (fd.getFile() != null) {
			txt_subimg2.setText(fd.getDirectory() + File.separator + fd.getFile());
		}

	}

	// 이미지보이기
	private void getOpenImageMain() {
		String imagename = txt_mainimg.getText().trim();
		String SRC_FILE = "";

		BufferedImage img = null;
		URL url = null;
		java.awt.Image resizeImage = null;
		BufferedImage newImage = null;
		Graphics g = null;

		if (imagename.length() > 0) {
			SRC_FILE = "tmp1";
			try {
				if (imagename.contains("http")) {
					url = new URL(imagename);
					img = ImageIO.read(url);
				} else {
					img = ImageIO.read(new File(imagename));
				}

				resizeImage = img.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
				newImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
				g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				ImageIO.write(newImage, "jpg", new File(SRC_FILE));
				// adResourceManager.dispose();
				ResourceManager.disposeImages();
				// lblNewLabel_16.setImage(null);
				lblNewLabel_16.setImage(ResourceManager.getImage(SRC_FILE));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 이미지보이기
	private void getOpenImageSub1() {

		String imagename = txt_subimg1.getText().trim();
		String SRC_FILE = "";

		BufferedImage img = null;
		URL url = null;
		java.awt.Image resizeImage = null;
		BufferedImage newImage = null;
		Graphics g = null;

		if (imagename.length() > 0) {
			SRC_FILE = "tmp1";
			try {
				if (imagename.contains("http")) {
					url = new URL(imagename);
					img = ImageIO.read(url);
				} else {
					img = ImageIO.read(new File(imagename));
				}

				resizeImage = img.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
				newImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
				g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				ImageIO.write(newImage, "jpg", new File(SRC_FILE));
				// adResourceManager.dispose();
				ResourceManager.disposeImages();
				lblNewLabel_16.setImage(ResourceManager.getImage(SRC_FILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 이미지보이기
	private void getOpenImageSub2() {
		String imagename = txt_subimg2.getText().trim();
		String SRC_FILE = "";

		BufferedImage img = null;
		URL url = null;
		java.awt.Image resizeImage = null;
		BufferedImage newImage = null;
		Graphics g = null;

		if (imagename.length() > 0) {
			SRC_FILE = "tmp1";
			try {
				if (imagename.contains("http")) {
					url = new URL(imagename);
					img = ImageIO.read(url);
				} else {
					img = ImageIO.read(new File(imagename));
				}
				resizeImage = img.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
				newImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
				g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				ImageIO.write(newImage, "jpg", new File(SRC_FILE));
				// adResourceManager.dispose();
				ResourceManager.disposeImages();
				lblNewLabel_16.setImage(ResourceManager.getImage(SRC_FILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 클리어
	private void getClearMain() {
		txt_mainimg.setText("");
		lblNewLabel_16.setImage(null);

	}

	// 클리어
	private void getClearSub1() {
		txt_subimg1.setText("");
		lblNewLabel_16.setImage(null);

	}

	// 클리어
	private void getClearSub2() {
		txt_subimg2.setText("");
		lblNewLabel_16.setImage(null);

	}

	// 이미지 불러오기
	private void getImageload(String txt_remark) {

		String imagePath;
		try {
			imagePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("image.productPath"));
			String imageFile = YDMAProperties.getInstance().getAppProperty("image.productFile");
			String imageFullpath = imagePath + imageFile;

			BufferedWriter out = new BufferedWriter(new FileWriter(imageFullpath));
			StringBuffer sb = new StringBuffer(txt_remark);
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

	// 상품기본정보
	private void getBasicInformation() {
		try {

			List<ProductMstDto> list = (List<ProductMstDto>) this.tableViewer_opt.getInput();
			String prodcd = txt_prodcd.getText().trim();
			// int infornumber = cb_infornum.getSelectionIndex()+1;
			String infornumber = cb_infornum.getText().trim();
			List<List<String>> contents_opt = (List<List<String>>) this.tableViewer_opt.getInput();
			ProductBasicDetail d = new ProductBasicDetail(this.getShell(), this, prodcd, infornumber, contents_opt);
			d.open();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 상품속성정보
	private void getPrepertyInformation() {
		int propertynum = cb_propertynum.getSelectionIndex();
		try {
			List<ProductMstDto> list = (List<ProductMstDto>) this.tableViewer_opt.getInput();
			ProductPropertyDetail d = new ProductPropertyDetail(this.getShell(), this, propertynum);
			d.open();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	int sendFailCnt = 0;
	private Composite composite_1;
	private Label lb_category;
	private Combo cb_categlagcd;
	private Combo cb_categmidcd;
	private Combo cb_categsmlcd;
	private Combo cb_categdetcd;
	private Button btn_Category;
	private Button btn_proposal;
	private Button btn_bestsc;

	void sendToSabangNet() {
		String prodcd = txt_prodcd.getText().trim();

		try {
			host = YDMAProperties.getInstance().getAppProperty("ftp.host");
			String jdbcurl = YDMAProperties.getInstance().getAppProperty("jdbc.url");
			if (!jdbcurl.contains(host)) {
				boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "테스트환경에서는 사방넷 전송을 금지합니다.\n전송하시겠습니까?");
				if (!flag) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			AutomationDao dao = new AutomationDao();
			List<String> apiInfo = dao.checkEcountStockApi("YDWMSABProductApi");
			if (apiInfo.get(0).equals("1")) {
				MessageDialog.openInformation(getShell(), TITLE, "사방넷전송기능이 자동으로 설정되어 있습니다.\n자동화설정에서 수동으로 변경후 실행해주세요.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ProductMstDao mdao = new ProductMstDao();
			List<List<String>> contents = mdao.getProdMstList(prodcd);
			sendProduct300(contents);

			String msg = "사방넷에 상품목록을 등록하였습니다.";
			if (sendFailCnt > 0) {
				msg = "\n(사방넷 상품등록에 실패한 상품이  [ " + sendFailCnt + " ] 건 있습니다.)";
			}
			MessageDialog.openInformation(getShell(), TITLE, msg);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}
	}

	public void sendProduct300(List<List<String>> contents) throws Exception {
		System.out.println(" #############   보내는 상품  : " + contents.size());
		if (contents.size() > 0) {

			for (List<String> list : contents) { // Log
				System.out.println(list.get(1));
			}
			SabangNetDao dao = new SabangNetDao();
			List<List<String>> sendResult = dao.sendProductListToSabangNet(contents, true, this.getShell());

			if (sendResult.size() > 0) {
				System.out.println(" #############   받은 결과 상품  : " + sendResult.size());
				for (List<String> list : sendResult) { // Log
					System.out.println(list);

					if (list.get(0).equals("FAIL")) {
						sendFailCnt++;
					}
				}
				// 수신데이타 처리
				dao.processSabangNetProductLog(sendResult);

			} else {
				sendFailCnt++;
			}
		}
	}

	// 카테고리
	private void openCategory() {
		try {
			MessageDialog.openInformation(getShell(), "카테고리관리", "카테고리 등록시 \n'( )'괄호문장과 ','꼼마문장은 \n사용하시면 적용되지 않습니다.");
			ProductCategoryList d = new ProductCategoryList(this.getShell(), this);
			d.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bureader;
	private StringBuffer strbutter;
	JFrame frame1;
	JProgressBar bar;
	Container container;
	Toolkit tk;
	Dimension screen;
	private Text txt_byte;
	private Label label_8;
	private Label label_9;
	private Text txt_odprice;
	private Button btn_categmap;

	// 크롤링
	public void webCrawling(boolean isNew) {
		String categlagcd = cb_categlagcd.getText().trim().split("[(]")[1].split("[)]")[0].equals("0") ? ""
				: cb_categlagcd.getText().trim().split("[(]")[1].split("[)]")[0];
		String categmidcd = cb_categmidcd.getText().trim().split("[(]")[1].split("[)]")[0].equals("0") ? ""
				: cb_categmidcd.getText().trim().split("[(]")[1].split("[)]")[0];
		String categsmlcd = cb_categsmlcd.getText().trim().split("[(]")[1].split("[)]")[0].equals("0") ? ""
				: cb_categsmlcd.getText().trim().split("[(]")[1].split("[)]")[0];
		String categdetcd = cb_categdetcd.getText().trim().split("[(]")[1].split("[)]")[0].equals("0") ? ""
				: cb_categdetcd.getText().trim().split("[(]")[1].split("[)]")[0];
		CategoryDao ladao = new CategoryDao();
		List<String> larlist = new ArrayList<>();
		List<String> midlist = new ArrayList<>();
		List<String> smllist = new ArrayList<>();
		List<String> detlist = new ArrayList<>();
		try {
			CompInfoDao compdao = new CompInfoDao();
			List<String> compId = compdao.getCompNoImage();
			if (!categlagcd.equals("")) {
				larlist = ladao.naverLargeCategory(categlagcd);
				if (!larlist.get(5).equals("")) {
					categlagcd = Integer.toString(Integer.parseInt(larlist.get(5)));
				} else {
					categlagcd = "";
				}
			}
			if (!categmidcd.equals("")) {
				midlist = ladao.naverMidiumCategory(categmidcd);
				if (!midlist.get(6).equals("")) {
					categmidcd = Integer.toString(Integer
							.parseInt(midlist.get(6).substring(midlist.get(6).length() - 3, midlist.get(6).length())));
				} else {
					categmidcd = "";
				}
			}
			if (!categsmlcd.equals("")) {
				smllist = ladao.naverSmallCategory(categsmlcd);
				if (!smllist.get(6).equals("")) {
					categsmlcd = Integer.toString(Integer
							.parseInt(smllist.get(6).substring(smllist.get(6).length() - 3, smllist.get(6).length())));
				} else {
					categsmlcd = "";
				}
			}
			if (!categdetcd.equals("")) {
				detlist = ladao.naverDetailCategory(categdetcd);
				if (!detlist.get(6).equals("")) {
					categdetcd = Integer.toString(Integer
							.parseInt(detlist.get(6).substring(detlist.get(6).length() - 3, detlist.get(6).length())));
				} else {
					categdetcd = "";
				}
			}
			List<List<String>> contents = getCrawllag(categlagcd, categmidcd, categsmlcd, categdetcd, compId.get(10));

			PopularSearch search = new PopularSearch(this.getShell(), this, isNew, contents);
			search.open();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	ChromeDriver driver = null;

	// 인기검색어
	private List<List<String>> getCrawllag(String categlagcd, String categmidcd, String categsmlcd, String categdetcd,
			String compId) {
		List<List<String>> contents = new ArrayList<>();
		String URL = "https://datalab.naver.com/shoppingInsight/sCategory.naver";
		ChromeExtention chrome = ChromeExtention.getInstace();

		try {

			driver = chrome.setFileDown(false).setHeadlessMode(true).getDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			driver.get(URL);
//			Thread.sleep(2000);
			if (!categlagcd.equals("") && categmidcd.equals("") && categsmlcd.equals("") && categdetcd.equals("")) {
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(1) > ul > li:nth-child("
								+ categlagcd + ") > a').click()",
						"");
			} else if (!categlagcd.equals("") && !categmidcd.equals("") && categsmlcd.equals("")
					&& categdetcd.equals("")) {
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(1) > ul > li:nth-child("
								+ categlagcd + ") > a').click()",
						"");
				ChromeScript.get().addScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(2) > ul > li:nth-child("
								+ categmidcd + ") > a').click()")
						.waitTiem(1000).executeScripter(driver);
			} else if (!categlagcd.equals("") && !categmidcd.equals("") && !categsmlcd.equals("")
					&& categdetcd.equals("")) {
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(1) > ul > li:nth-child("
								+ categlagcd + ") > a').click()",
						"");
				Thread.sleep(1000);
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(2) > ul > li:nth-child("
								+ categmidcd + ") > a').click()",
						"");
				Thread.sleep(1000);
//				.waitTiem(1000).executeScripter(driver) ;
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(3) > ul > li:nth-child("
								+ categsmlcd + ") > a').click()",
						"");
				Thread.sleep(1000);
//				.waitTiem(1000).executeScripter(driver) ;
			} else if (!categlagcd.equals("") && !categmidcd.equals("") && !categsmlcd.equals("")
					&& !categdetcd.equals("")) {
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(1) > ul > li:nth-child("
								+ categlagcd + ") > a').click()",
						"");
				Thread.sleep(1000);
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(2) > ul > li:nth-child("
								+ categmidcd + ") > a').click()",
						"");
				Thread.sleep(1000);
//				.waitTiem(2000).executeScripter(driver) ;
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(3) > ul > li:nth-child("
								+ categsmlcd + ") > a').click()",
						"");
				Thread.sleep(1000);
//				.waitTiem(2000).executeScripter(driver) ;
				driver.executeScript(
						"document.querySelector('div.section.insite_inquiry > div > div > div:nth-child(1) > div > div:nth-child(4) > ul > li:nth-child("
								+ categdetcd + ") > a').click()",
						"");
				Thread.sleep(1000);
//				.waitTiem(2000).executeScripter(driver) ;

			} else {
				MessageDialog.openInformation(getShell(), "인기검색어", "카테고리중 1개 이상은 선택하셔야 됩니다.");
				return null;
			}
			driver.executeScript(
					"document.querySelector('#content > div.section_instie_area.space_top > div > div.section.insite_inquiry > div > a').click()",
					"");
			Thread.sleep(1000);
			String search = (String) driver.executeScript(
					"return document.querySelector('#content > div.section_instie_area.space_top > div > div:nth-child(2) > div.section_insite_sub > div > div > div.rank_top1000_scroll > ul').innerText",
					"");

			String[] aa = search.split("\n");
			for (int i = 0; i < aa.length; i++) {
				if (i % 2 != 0) {
					List<String> list = new ArrayList<>();
					list.add(aa[i]);
					contents.add(list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contents;

	}

	// PopularSearch다이얼로그에서 값가져오기
	public void setSearchNM(List<List<String>> contents_search, boolean isNew) {
		String searchnm = "";
		String site = txt_searchwd.getText();
		String prodnm = txt_prodnm.getText();
		for (List<String> search : contents_search) {
			searchnm += search.get(0).trim() + ",";
		}
		if (isNew) {
			if (searchnm.contains(site)) {
				txt_searchwd.setText(searchnm);
				txt_searchwd.setFocus();
				txt_searchwd.setSelection(txt_searchwd.getText().length());
			} else {
				txt_searchwd.setText(searchnm + txt_searchwd.getText());
				txt_searchwd.setFocus();
				txt_searchwd.setSelection(txt_searchwd.getText().length());
			}
		} else {
			if (searchnm.contains(prodnm)) {
				txt_prodnm.setText(searchnm);
				txt_prodnm.setFocus();
				txt_prodnm.setSelection(txt_prodnm.getText().length());
			} else {
				txt_prodnm.setText(searchnm + txt_prodnm.getText());
				txt_prodnm.setFocus();
				txt_prodnm.setSelection(txt_prodnm.getText().length());
			}
		}

	}

	// 11번가
	protected void sendTo11st() {
		String prodcd = txt_prodcd.getText().trim();

		try {
			host = YDMAProperties.getInstance().getAppProperty("ftp.host");
			String jdbcurl = YDMAProperties.getInstance().getAppProperty("jdbc.url");
			if (!jdbcurl.contains(host)) {
				boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "테스트환경에서는 11번가 전송을 금지합니다.\n전송하시겠습니까?");
				if (!flag) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ProductMstDao mdao = new ProductMstDao();
			List<List<String>> contents = mdao.getProdMstList(prodcd);
			send11stProduct(contents, prodcd);

			String msg = "11번가에 상품을  등록하였습니다.";
			if (sendFailCnt > 0) {
				msg = "\n(11번가 상품등록에 실패한 상품이  [ " + sendFailCnt + " ] 건 있습니다.)";
			}
			MessageDialog.openInformation(getShell(), TITLE, msg);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	// 11번가
	public void send11stProduct(List<List<String>> contents, String prodcd) throws Exception {
//		System.out.println(" #############   보내는 상품  : " + contents.size());
//		if (contents.size() > 0) {
//			ProductMstDao mstdao = new ProductMstDao();
//			for (List<String> list : contents) { // Log
//				System.out.println(list.get(1));
//			}
//
//			SabangNetDao dao = new SabangNetDao();
//			List<List<String>> sendResult = dao.sendProductListTo11st(contents,this.getShell());
//
//			if (sendResult.size() > 0) {
//				System.out.println(" #############   받은 결과 상품  : " + sendResult.size());
//				for (List<String> list : sendResult) { // Log
//					System.out.println(list);
//
//					if (list.get(1).contains("실패")) {
//						sendFailCnt++;
//					}
//				}
//				//수신데이터넣는곳 11번가 코드 수정해야됨
//				mstdao.setProductMstShopcode(sendResult);
//			}
//		}

	}

	// 카테고리매핑
	protected void openShoppingMallCategoryMapping() {

	}

	@Override
	protected void okPressed() {

		String prodcd = txt_prodcd.getText().trim();
		String prodnm = txt_prodnm.getText().trim();
		if (prodnm.contains(",") || prodnm.contains("*")) {
			MessageDialog.openInformation(getShell(), TITLE, "선택하신 내용중 상품명에 (',','*')의 내용이 있어 등록할수 없습니다.");
			return;
		}
		String specdes = txt_spec.getText().trim();
		String flagset = ck_flagset.getSelection() ? "1" : "0";
		String flagplus = ck_flagplus.getSelection() ? "1" : "0";
		String flagout = ck_flagout.getSelection() ? "1" : "0";
		String price = txt_price.getText().trim();
		String tagprice = txt_tagprice.getText().trim();
		tagprice = tagprice.length() == 0 ? "0" : tagprice;
		String cusprice = txt_cusprice.getText().trim();
		cusprice = cusprice.length() == 0 ? "0" : cusprice;
		String aproinvt = txt_aproinvt.getText().trim();
		aproinvt = aproinvt.length() == 0 ? "0" : aproinvt;
		String ecountcd = txt_ecountcd.getText().trim().toUpperCase().length() < 0 ? ""
				: txt_ecountcd.getText().trim().toUpperCase();
		String mainimg = txt_mainimg.getText().trim();
		String subimg1 = txt_subimg1.getText().trim();
		String subimg2 = txt_subimg2.getText().trim();
		String mainpath = fdirectory;
		String subpath1 = fdirectory2;
		String subpath2 = fdirectory3;
		String orderprice = txt_odprice.getText().trim();
		orderprice = orderprice.length() == 0 ? "0" : orderprice;

		String infornum = cb_infornum.getText();
		if (infornum.length() > 0) {
			int i = infornum.indexOf("_");
			infornum = infornum.substring(0, i);
		}
		String propertynum = cb_propertynum.getText();
		if (propertynum.length() > 0) {
			int k = propertynum.indexOf("_");
			propertynum = propertynum.substring(0, k);
		}
		String searchwd = txt_searchwd.getText();

		String remark = txt_remark.getText().trim();
		String ea = txt_ea.getText().trim();
		ea = ea.length() == 0 ? "0" : ea;

		String sabcd = txt_sabcd.getText().trim();
		String useyn = cb_useyn.getItem(cb_useyn.getSelectionIndex());
		String delyn = cb_delyn.getItem(cb_delyn.getSelectionIndex());

		String categlagcd = cb_categlagcd.getText();
		if (categlagcd.length() > 0) {
			categlagcd = categlagcd.split("[(]")[1].split("[)]")[0];
		}
		String categmidcd = cb_categmidcd.getText();
		if (categmidcd.length() > 0) {
			categmidcd = categmidcd.split("[(]")[1].split("[)]")[0];
		}
		String categsmlcd = cb_categsmlcd.getText();
		if (categsmlcd.length() > 0) {
			categsmlcd = categsmlcd.split("[(]")[1].split("[)]")[0];
		}
		String categdetcd = cb_categdetcd.getText();
		if (categdetcd.length() > 0) {
			categdetcd = categdetcd.split("[(]")[1].split("[)]")[0];
		}

		if (prodcd.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "상품코드를 입력하세요.");
			txt_prodcd.setFocus();
			return;
		}

		if (prodcd.length() < 5) {
			MessageDialog.openInformation(getShell(), TITLE, "상품코드의 길이는 5글자 이상 입력하여야 합니다.");
			txt_prodcd.setFocus();
			return;
		}

		if (prodnm.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "상품명을 입력하세요.");
			txt_prodnm.setFocus();
			return;
		}
		if (price.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "단가을 입력하세요.");
			txt_price.setFocus();
			return;
		}
		if (remark.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "상세설명을 입력하세요.");
			txt_remark.setFocus();
			return;
		}

		if (price.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "단가를 입력하세요.");
			txt_price.setFocus();
			return;
		}

		ProductMstDao dao = new ProductMstDao();
		CompInfoDao cdao = new CompInfoDao();

		try {
			List<String> comlist = cdao.getCompno();

			if (dto == null) { // 새 상품마스터 코드 등록

				if (dao.isExistProduct(prodcd)) {//
					MessageDialog.openInformation(getShell(), TITLE, "동일한 상품코드가 존재합니다.");
					return;
				}
				if (!Prodprodcd.equals("0")) {
					if (mainimg.length() > 0) {
						if (!mainimg.contains("http:")) {
							int result = FtpUtil.uploadImage(prodcd, mainimg, 1, comlist);
							FtpUtil.uploadLocalImage(prodcd, mainimg, 1, comlist);
							if (result == 0) {
								MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
								return;
							} else {
								mainimg = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "01.jpg";
							}
						}
					}
					if (subimg1.length() > 0) {
						if (!subimg1.contains("http:")) {
							int result = FtpUtil.uploadImage(prodcd, subimg1, 2, comlist);
							if (result == 0) {
								MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
								return;
							} else {
								subimg1 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "02.jpg";
							}
						}
					}
					if (subimg2.length() > 0) {
						if (!subimg2.contains("http:")) {
							int result = FtpUtil.uploadImage(prodcd, subimg2, 3, comlist);
							if (result == 0) {
								MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
								return;
							} else {
								subimg2 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "03.jpg";
							}
						}
					}

				} else {
					// 서버에 사진 폴더생성하기 및 사진전송
					if (mainimg.length() > 0) {
						if (!mainimg.contains("http:")) {
							int result = FtpUtil.uploadImage(prodcd, mainimg, 1, comlist);
							FtpUtil.uploadLocalImage(prodcd, mainimg, 1, comlist);
							if (result == 0) {
								MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
								return;
							} else {
								mainimg = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "01.jpg";
							}
						}
					}
					if (subimg1.length() > 0) {
						int result = FtpUtil.uploadImage(prodcd, subimg1, 2, comlist);
						if (result == 0) {
							MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
							return;
						} else {
							subimg1 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "02.jpg";
						}
					}
					if (subimg2.length() > 0) {
						int result = FtpUtil.uploadImage(prodcd, subimg2, 3, comlist);
						if (result == 0) {
							MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다");
							return;
						} else {
							subimg2 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "03.jpg";
						}
					}
				}
				// 복사하기시 창고관리와 배송관리인설트하기
				if (!Prodprodcd.equals("0")) {
					RackDao rackdao = new RackDao();
					List<String> list = rackdao.selectRackInfo(Prodprodcd);
					String prodcdname = txt_prodcd.getText().trim().toUpperCase();
					if (list.size() > 0) {
						rackdao.insertRackInfo(prodcdname, list.get(2), list.get(3), list.get(4));
					}

					ExpDao expdao = new ExpDao();
					List<String> expInfo = expdao.getExpMstInfo(Prodprodcd);
					List<List<String>> qtyInfo = expdao.getExpQtyInfo(Prodprodcd);
					List<List<String>> qtyContents = new ArrayList<>();
					for (int i = 0; i < qtyInfo.size(); i++) {
						List<String> qtylist1 = new ArrayList<>();
						qtylist1.add(String.valueOf(i + 1));
						qtylist1.add(qtyInfo.get(i).get(0));

						qtyContents.add(qtylist1);
					}
					List<List<String>> costInfo = expdao.getExpCostInfo(Prodprodcd);
					List<List<String>> costInfoContents = new ArrayList<>();
					for (int i = 0; i < costInfo.size(); i++) {
						List<String> costInfo1 = new ArrayList<>();
						costInfo1.add(String.valueOf(i + 1));
						costInfo1.add(costInfo.get(i).get(0));

						costInfoContents.add(costInfo1);
					}
					List<String> contentsExpInfo = new ArrayList<>();
					contentsExpInfo.add(0, expInfo.get(4));
					contentsExpInfo.add(1, expInfo.get(5));
					contentsExpInfo.add(2, expInfo.get(6));
					contentsExpInfo.add(3, expInfo.get(7));
					contentsExpInfo.add(4, expInfo.get(8));
					contentsExpInfo.add(5, expInfo.get(9));
					contentsExpInfo.add(6, expInfo.get(10));
					contentsExpInfo.add(7, expInfo.get(3)); // expout
					contentsExpInfo.add(8, expInfo.get(11)); // flagrack
					contentsExpInfo.add(9, expInfo.get(12)); // rackconts
					contentsExpInfo.add(10, expInfo.get(13)); // expinner
					contentsExpInfo.add(11, expInfo.get(14)); // expcostnm
					contentsExpInfo.add(12, expInfo.get(15));

					expdao.processUpdateExpInfo(prodcdname, contentsExpInfo, qtyContents, costInfoContents);

				}
				dao.processProductMstInsert(prodcd, prodnm, specdes, flagset, flagplus, flagout, price, tagprice, sabcd,
						remark, ea, useyn, delyn, cusprice, aproinvt, mainimg, subimg1, subimg2, infornum, propertynum,
						searchwd, categlagcd, categmidcd, categsmlcd, categdetcd, orderprice, ecountcd);

				List<ProductMstDto> list = (List<ProductMstDto>) this.opener.tableViewer_mst.getInput();
				if (list == null) {
					list = new ArrayList<>();
					this.opener.tableViewer_mst.setInput(list);
				}

				ProductMstDto dto_db = dao.getProdcutInfoByProdcd(prodcd);
				dto_db.setRowno(String.valueOf(list.size() + 1));
				list.add(0, dto_db);

				this.opener.tableViewer_mst.setInput(null);
				this.opener.tableViewer_mst.refresh();
				this.opener.tableViewer_mst.setInput(list);
				this.opener.tableViewer_mst.refresh();
				this.opener.tableViewer_mst.getGrid().select(0);

				if (prodcd.startsWith("YWD") || prodcd.startsWith("YWC") || prodcd.startsWith("YWT")
						|| prodcd.startsWith("YWS") || prodcd.startsWith("YWL") || prodcd.startsWith("YWH")
						|| prodcd.startsWith("YWM") || prodcd.startsWith("YWR")) {
//					YWD -> YYWD , WWD
//					YWC -> YYWC , WWC
//					YWT -> YYWT , WWT
//					YWS -> YYWS , WWS
//					YWL -> YYWL , WWL
//					YWH -> YYWH , WWH
//					YWM -> YYWM , WWM
//					YWR -> YYWR , WWR
					Display.getDefault().asyncExec(() -> opener.getProdcutSubListByProductCode());
				}

				opener.setTotalCount();
				if (Prodprodcd.equals("0")) {
					List<String> imsi = new ArrayList<>();
					boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "상품을  등록하였습니다.\n옵션상품을 추가하시겠습니까?");
					if (flag) {
						txt_prodcd.setEditable(false);
						btn_addopt.setEnabled(true);

						ProdcutOptDetail d = new ProdcutOptDetail(this.getShell(), this, null, imsi);
						d.open();
					} else {
						super.okPressed();
					}
				} else {
					List<List<String>> contents = (List<List<String>>) tableViewer_opt.getInput();
					if (contents.size() > 0) {
						for (List<String> optlist : contents) {
							int idx = 1;
							String optprodcd = optlist.get(idx++).trim();
							String optprodnm = optlist.get(idx++).trim();
							String optspecdes = optlist.get(idx++).trim();
							String optea = optlist.get(idx++).trim();
							String optsale = optlist.get(idx++).trim();
							String optsaleout = optlist.get(idx++).trim();
							String optnotuse = optlist.get(idx++).trim();
							String optsafestock = optlist.get(idx++).trim();
							String optvertstock = optlist.get(idx++).trim();
							String optaddamt = optlist.get(idx++).trim();
							String optdelyn = optlist.get(idx++).trim();
							ProductOptDao opt_dao = new ProductOptDao();

							if (!opt_dao.isExistProductNProdcd(optlist.get(1))) {
								opt_dao.processProductOptInsert(txt_prodcd.getText().trim().toUpperCase(),
										txt_ecountcd.getText().trim().toUpperCase(), optprodcd, optprodnm, optspecdes,
										optea, optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt,
										optdelyn, true);
							} else {
								MessageDialog.openInformation(getShell(), TITLE,
										"상품을 등록하였습니다.\n옵션상품은 동일한 코드가 있어 저장하지 못하였습니다. 옵션상품은 다시 등록하여 주시기 바랍니다.");
								return;
//								opt_dao.processProductOptUpdate(txt_prodcd.getText().trim().toUpperCase(), optprodcd, optprodnm,
//										optspecdes, optea, optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt,
//										optdelyn);
							}
						}
					} else {
						List<String> imsi = new ArrayList<>();
						boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "상품을  등록하였습니다.\n옵션상품을 추가하시겠습니까?");
						if (flag) {
							txt_prodcd.setEditable(false);
							btn_addopt.setEnabled(true);

							ProdcutOptDetail d = new ProdcutOptDetail(this.getShell(), this, null, imsi);
							d.open();
						}
					}
				}
			} else {// 상품정보수정
				// 서버에 사진 폴더생성하기 및 사진전송
				if (mainimg.length() > 0 && !mainimg.contains("http")) {
					int result = FtpUtil.uploadImage(prodcd, mainimg, 1, comlist);
					FtpUtil.uploadLocalImage(prodcd, mainimg, 1, comlist);
					if (result == 0) {
						MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다 JPG파일로 다시 등록하여주십시요");
						return;
					} else {
						mainimg = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "01.jpg";
					}
				}
				if (subimg1.length() > 0 && !subimg1.contains("http")) {
					int result = FtpUtil.uploadImage(prodcd, subimg1, 2, comlist);
					if (result == 0) {
						MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다 JPG파일로 다시 등록하여주십시요");
						return;
					} else {
						subimg1 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "02.jpg";
					}
				}
				if (subimg2.length() > 0 && !subimg2.contains("http")) {
					int result = FtpUtil.uploadImage(prodcd, subimg2, 3, comlist);
					if (result == 0) {
						MessageDialog.openInformation(getShell(), TITLE, "파일을 저장하지 못하였습니다 JPG파일로 다시 등록하여주십시요");
						return;
					} else {
						subimg2 = "http://itsm.kdjsystem.com/" + comlist.get(1) + "/" + prodcd + "03.jpg";
					}
				}

				dao.processProductUpdate(prodcd, prodnm, specdes, flagset, flagplus, flagout, price, tagprice, sabcd,
						remark, ea, useyn, delyn, cusprice, aproinvt, mainimg, subimg1, subimg2, infornum, propertynum,
						searchwd, categlagcd, categmidcd, categsmlcd, categdetcd, orderprice, ecountcd);

				// 수정된 값을 디비에 조회하여 테이블에 반영한다.
				ProductMstDto dot_db = dao.getProdcutInfoByProdcd(prodcd);
				List<ProductMstDto> contents = (List<ProductMstDto>) this.opener.tableViewer_mst.getInput();
				int selectedIdx = this.opener.tableViewer_mst.getGrid().getSelectionIndex();
				ProductMstDto dot_orign = contents.get(selectedIdx);
				dot_db.setRowno(dot_orign.getRowno());
				dot_db.setImg(dot_orign.getImg());
				contents.set(selectedIdx, dot_db);
				this.opener.tableViewer_mst.refresh();
				this.opener.tableViewer_mst.getGrid().select(selectedIdx);

				MessageDialog.openInformation(getShell(), TITLE, "상품정보를 수정하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
		if (driver != null) {
			driver.quit();
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		btn_11st=createButton(parent, IDialogConstants.FINISH_ID, "11번가전송", false);
//		btn_11st.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				sendTo11st();
//
//			}
//		});
//		btn_11steidt=createButton(parent, IDialogConstants.FINISH_ID, "11번가수정", false);
//		btn_11steidt.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				sendTo11stEdit();
//
//			}
//		});
//		btn_11stDiscontinued=createButton(parent, IDialogConstants.FINISH_ID, "11번가판매중지", false);
//		btn_11stDiscontinued.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				sendTo11stDiscontinued();
//
//			}
//		});
		btn_sabang = createButton(parent, IDialogConstants.FINISH_ID, "사방넷전송", false);
		btn_sabang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendToSabangNet();

			}
		});

		btnOk = createButton(parent, IDialogConstants.OK_ID, "저장", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "닫기", false);
	}

	// 판매중지
	protected void sendTo11stDiscontinued() {
		String prodcd = txt_prodcd.getText().trim();

		try {
			host = YDMAProperties.getInstance().getAppProperty("ftp.host");
			String jdbcurl = YDMAProperties.getInstance().getAppProperty("jdbc.url");
			if (!jdbcurl.contains(host)) {
				boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "테스트환경에서는 11번가 전송을 금지합니다.\n전송하시겠습니까?");
				if (!flag) {
					return;
				}
			}
			ProductMstDao mdao = new ProductMstDao();
			List<List<String>> contents = mdao.getProdMstList(prodcd);
			send11stProductDiscontinued(contents, prodcd);

			String msg = "11번가 판매중지 처리가 정상적으로 등록 되었습니다.";
			if (sendFailCnt > 0) {
				msg = "\n(11번가에 판매중지처리에 실패한 상품이  [ " + sendFailCnt + " ] 건 있습니다.)";
			}
			MessageDialog.openInformation(getShell(), TITLE, msg);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	// 11번가 판매중지
	private void send11stProductDiscontinued(List<List<String>> contents, String prodcd) throws Exception {
		System.out.println(" #############   보내는 상품  : " + contents.size());
		if (contents.size() > 0) {

			for (List<String> list : contents) { // Log
				System.out.println(list.get(1));
			}

			SabangNetDao dao = new SabangNetDao();
			List<List<String>> sendResult = dao.sendProductListTo11stDiscontinued(contents, true, this.getShell(),
					prodcd);

			if (sendResult.size() > 0) {
				System.out.println(" #############   받은 결과 상품  : " + sendResult.size());
				for (List<String> list : sendResult) { // Log
					System.out.println(list);
					if (!list.get(3).contains("200")) {
						sendFailCnt++;
					}
				}
			} /// if
		} /// 맨처음if
	}

//	//11번가수정
//	protected void sendTo11stEdit() {
//		String prodcd = txt_prodcd.getText().trim();
//
//		try {
//			host = YDMAProperties.getInstance().getAppProperty("ftp.host");
//			String jdbcurl = YDMAProperties.getInstance().getAppProperty("jdbc.url");
//			if (!jdbcurl.contains(host)) {
//				boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "테스트환경에서는 11번가 전송을 금지합니다.\n전송하시겠습니까?");
//				if(!flag) {
//					return;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			ProductMstDao mdao = new ProductMstDao();
//			List<List<String>> contents = mdao.getProdMstList(prodcd);
//			send11stProductEdit(contents);
//
//			String msg = "11번가에 상품목록을 수정하였습니다.";
//			if (sendFailCnt > 0) {
//				msg = "\n(11번가 상품수정에 실패한 상품이  [ " + sendFailCnt + " ] 건 있습니다.)";
//			}
//			MessageDialog.openInformation(getShell(), TITLE, msg);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			MessageDialog.openError(getShell(), TITLE, e.getMessage());
//		}
//
//	}

//	//11번가수정
//	public void send11stProductEdit(List<List<String>> contents) throws Exception {
//		System.out.println(" #############   보내는 상품  : " + contents.size());
//		if (contents.size() > 0) {
//
//			for (List<String> list : contents) { // Log
//				System.out.println(list.get(1));
//			}
//
//			SabangNetDao dao = new SabangNetDao();
//			List<List<String>> sendResult = dao.sendProductListTo11stEdit(contents,true,this.getShell());
//
//			if (sendResult.size() > 0) {
//				System.out.println(" #############   받은 결과 상품  : " + sendResult.size());
//				for (List<String> list : sendResult) { // Log
//					System.out.println(list);
//
//					if (list.get(0).contains("실패")) {
//						sendFailCnt++;
//					}
//				}
//
//
//			}
//		}
//
//	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(1307, 805);
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

			List<String> rowContent = (List<String>) element;
			value = rowContent.get(columnIndex);

			if (columnIndex == 5) {
				value = value.equals("1") ? "o" : "";
			} else if (columnIndex == 6) { // 품절
				value = value.equals("1") ? "o" : "";
			} else if (columnIndex == 7) { // 미사용
				value = value.equals("1") ? "o" : "";
			}

			return value;
		}

		@Override
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
