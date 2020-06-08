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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.shop.common.CodeItem;
import com.kdj.mlink.ezup3.shop.common.ShopCombo;
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopDeliveryDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProduct11stAdditionDto;
import com.kdj.mlink.ezup3.common.CommonCalander;
import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDao;
import com.kdj.mlink.ezup3.ui.CommandDialog;
import com.kdj.mlink.ezup3.ui.ShopAdditionalInforMationManager;

import org.eclipse.swt.widgets.Button;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Group;


public class ShopAdditionalInformationDetail extends CommandDialog{

	String TITLE = "11번가 부가정보 관리";
	ShopAdditionalInforMationManager opener;
	static String host;
    
	Button btnOk;
	Button btnCancel;

	JFrame frame;
	java.awt.FileDialog fd;

	Composite composite_opt;
	public GridTableViewer tableViewer_opt;
	Label lbImage1;
	JLabel lb;
	String imgTargetPath;
	String imgTargetPath2;
	String imgTargetPath3;
	String Prodprodcd;
	private Label label;
	private Text txt_title;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private Label label_7;
	private Text txt_nicknm;
	private Composite composite_4;
	private Composite composite_5;
	private Composite composite_6;
	private Composite composite_7;
	private Label label_23;
	List<String> list;
	private Combo cb_service;
	private Label label_8;
	private Button btn_new;
	private Button btn_stock;
	private Button btn_making;
	private Button btn_usedprod;
	private Button btn_reaper;
	private Button btn_exhibi;
	private Button btn_return;
	private Button btn_scratch;
	private Button btn_rare;
	private Label label_22;
	private Button btn_minoryes;
	private Button btn_minorno;
	private Label label_24;
	private Button btn_dateyes;
	private Button btn_dateno;
	private Label label_25;
	private Button btn_7day;
	private Button btn_15day;
	private Button btn_30day;
	private Button btn_60day;
	private Button btn_90day;
	private Button btn_120day;
	private Label label_21;
	private Combo cb_world;
	private Label label_26;
	private Button btn_worldyes;
	private Button btn_worldno;
	private Label label_27;
	private Button btn_weight;
	private Button btn_weight300;
	private Label label_28;
	private Combo cb_hscode;
	private Label label_29;
	private Text txt_factaddr;
	private Label label_30;
	private Text txt_chanaddr;
	private Label label_33;
	private Text txt_as;
	private Text txt_change;
	private Label label_35;
	private Combo cb_whether;
	private Label label_16;
	private Combo cb_prepay;
	private Label label_17;
	private Button btn_bundleyse;
	private Button btn_bundleno;
	private Label label_18;
	private Button btn_roundyes;
	private Button btn_roundno;
	private Label label_19;
	private Label label_20;
	private Text txt_oneway;
	private Label label_31;
	private Label label_32;
	private Text txt_round;
	private Label label_36;
	private Composite composite_2;
	private Composite composite_10;
	private Button btn_free;
	private Button btn_condifree;
	private Button btn_fixing;
	private Text txt_condifree;
	private Text txt_fixing;
	private Text txt_jeju;
	private Text txt_island;
	private Label label_9;
	private Label label_10;
	private Label label_11;
	private Label label_12;
	private Label label_13;
	private Label label_14;
	private Composite composite_15;
	private Composite composite_16;
	private Composite composite_17;
	private Composite composite_18;
	private Composite composite_19;
	private Composite composite_3;
	private Button btn_reser;
	private Button btn_used;
	private Button btn_public;
	private Button btn_fixed;
	private Composite composite_11;
	private Composite composite_12;
	private Composite composite_13;
	private Composite composite_14;
	private Composite composite_20;
	private Composite composite_21;
	private Button btn_factaddr;
	private Button btn_rtnaddr;
	ShopProduct11stAdditionDto dto;
	private Label label_2;
	private Combo cb_exp;
	private Button btn_seller;
	private Button btn_factory;
	private Button btn_differ;
	private Button btn_one;
	private ScrolledComposite scrolledComposite;
	private Label label_37;
	private Label label_38;
	private Label label_39;
	private Text txt_diffqty;
	private Text txt_diffprice;
	private Text txt_diffqty1;
	private Text txt_diffprice1;
	private Label label_40;
	private Label label_41;
	private Label label_42;
	private Label label_43;
	private Label label_44;
	private Composite composite_26;
	private Composite composite_27;
	private Text txt_oneprice;
	private Label label_45;
	private Label lblNewLabel_1;
	private Label label_46;
	private Label lblNewLabel_2;
	private Label label_47;
	private Text txt_condifree1;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Composite composite_28;
	private Composite composite_29;
	Combo cb_idcheck;
	String shopseq;
	private List<ShopDeliveryDto> datasource = null;
	private Label lblNewLabel;
	private Text txt_skpay;
	private Button btn_skpay;
	Combo cb_skpay;
	private Group group;
	private Label lblNewLabel_6;
	private Composite composite_1;
	private Label lblNewLabel_7;
	private Button btn_support;
	private Text txt_support;
	private Combo cb_support;
	private Label lblNewLabel_8;
	private Composite composite_9;
	private Combo cb_interestfee;
	private Text txt_interestfee;
	private Button btn_interestfee;
	private Composite composite_22;
	private Composite composite_23;
	private Composite composite_24;
	private Label lblNewLabel_11;
	private Composite composite_25;
	private Button btn_background;
	private Label lblNewLabel_12;
	private Label lblNewLabel_13;
	private Composite composite_30;
	private Button btn_bold;
	private Label lblNewLabel_14;
	private Label lblNewLabel_15;
	private Composite composite_31;
	private Button btn_gifimg;
	private Label lblNewLabel_16;
	private Label lblNewLabel_17;
	private Label lblNewLabel_18;
	private Label label_1;
	private Label label_34;
	private Label label_48;
	private Label lblNewLabel_19;
	private Composite composite_32;
	private Button btn_plusup;
	private Label lblNewLabel_20;
	private Label lblNewLabel_21;
	private Label lblNewLabel_22;
	private Label label_49;
	private Label lblNewLabel_23;
	private Composite composite_33;
	private Button btn_plus;
	private Label lblNewLabel_24;
	private Label lblNewLabel_25;
	private Label lblNewLabel_26;
	private Label label_50;
	private Label lblNewLabel_27;
	private Composite composite_34;
	private Label lblNewLabel_28;
	private Label lblNewLabel_29;
	private Label lblNewLabel_30;
	private Label lblNewLabel_31;
	private Label lblNewLabel_32;
	private Label label_51;
	private Composite composite_35;
	private Composite composite_36;
	private Label lblNewLabel_33;
	private Button btn_dasicdiscount;
	private Composite composite_37;
	private Composite composite_38;
	private Label lblNewLabel_34;
	private Button btn_prodreviewN;
	private Button btn_prodreviewY;
	private Label lblNewLabel_35;
	private Text txt_dasicdiscount;
	private Combo cb_dasicdiscount;
	private Label ghfdghfdh;
	private Button btn_dasicdiscountcoupone;
	private Text txt_dasicdiscountdate;
	private Button btn_dasicdiscountdel;
	private Label lblNewLabel_36;
	private Group group_1;
	private Label lblNewLabel_37;
	private Text txt_memo;
	private Label lblNewLabel_38;
	private Label lblNewLabel_39;
	private Group group_2;
	private Label lblNewLabel_40;
	private Text txt_bottom;
	private Label lblNewLabel_41;
	private Text txt_top;
	private Label lblNewLabel_42;
	private Text txt_end;
	private Label lblNewLabel_43;
	private Text txt_start;
	private Label lblNewLabel_44;
	private Composite composite;
	private Button btn_overseasN;
	private Button btn_overseasY;
	private Label lblNewLabel_45;
	private Label lblNewLabel_46;
	private Composite composite_8;
	private Button btn_mobile;
	private Label lblNewLabel_47;
	private Label lblNewLabel_48;
	private Composite composite_39;
	private Combo cb_mobileimg;
	private Label lblNewLabel_49;
	private Label lblNewLabel_50;
	private Combo cb_repimg;
	private Composite composite_40;
	private Label lblNewLabel_51;
	private Composite composite_41;
	private Combo cb_buy;
	private Label lblNewLabel_52;
	private Composite composite_42;
	private Combo cb_multidiscntcnt;
	private Text txt_multidiscntcnt;
	private Label lblNewLabel_53;
	private Text txt_multidiscntwon;
	private Combo cb_multidiscntwon;
	private Label lblNewLabel_54;
	private Composite composite_43;
	private Button btn_multidiscnt;
	private Label lblNewLabel_55;
	private Composite composite_44;
	private Label lblNewLabel_56;
	private Text text_1;
	private Label lblNewLabel_57;
	private Text text_2;
	private Label lblNewLabel_58;
	private Button btnRadioButton;
	private Button btnRadioButton_1;
	private Label lblNewLabel_59;
	private Label lblNewLabel_60;
	private Label lblNewLabel_61;
	private Label lblNewLabel_62;
	private Composite composite_45;
	private Composite composite_46;
	Button btn_outaddr1;
	Button btn_outaddr2;
	Button btn_cngNretaddr1;
	Button btn_cngNretaddr2;
	private Label lblNewLabel_63;
	private Composite composite_47;
	private Label lblNewLabel_64;
	private Composite composite_48;
	private Label lblNewLabel_65;
	private Label lblNewLabel_66;
	private Label lblNewLabel_67;
	private Label lblNewLabel_68;
	private Label lblNewLabel_69;
	private Label lblNewLabel_70;
	private Label lblNewLabel_71;
	private Composite composite_49;
	private Composite composite_50;
	private Text txt_visit;
	private Button btn_visitsearch;
	private Button btn_visitdel;
	private Text txt_shipmentdate;
	private Button btn_shipmentsearch;
	private Button btn_shipmentdel;
	private Composite composite_51;
	private Label lblNewLabel_72;
	private Button btn_world;
	private Label lblNewLabel_73;
	private Label lblNewLabel_74;
	private Label lblNewLabel_75;
	private Composite composite_52;
	private Combo cb_exparea;
	private Label lblNewLabel_76;
	private Composite composite_53;
	private Text txt_exptemplate;
	private Button btn_exptempsearch;
	private Label lblNewLabel_77;
	private Composite composite_54;
	private Label lblNewLabel_78;
	private Label lblNewLabel_79;
	private Label lblNewLabel_80;
	private Label lblNewLabel_81;
	private Label lblNewLabel_82;
	private Text txt_giftnm;
	private Text txt_giftperiodst;
	private Label lblNewLabel_83;
	private Text txt_giftperioden;
	private Button btn_giftperioddel;
	private Text txt_gift;
	private Label lblNewLabel_84;
	private Composite composite_55;
	private Button btn_limit1;
	private Button btn_limit2;
	private Text txt_limit2_1;
	private Label lblNewLabel_85;
	private Button btn_limit3;
	private Text txt_limit3_1;
	private Label lblNewLabel_86;
	private Text txt_limit3_2;
	private Label lblNewLabel_87;
	private Label lblNewLabel_88;
	private Label lblNewLabel_89;
	private Composite composite_56;
	private Composite composite_57;
	private Composite composite_58;
	private Label lblNewLabel_90;
	private Composite composite_59;
	private Button btn_minlimit1;
	private Button btn_minlimit2;
	private Text txt_minlimit2_1;
	private Label lblNewLabel_91;
	private Label lblNewLabel_92;
	private Composite composite_60;
	private Button btn_benepia1;
	private Button btn_benepia2;
	private Text txt_benepia1_1;
	private Text txt_benepiast;
	private Label lblNewLabel_93;
	private Label lblNewLabel_94;
	private Text txt_benepiaen;
	private Label lblNewLabel_95;
	private Label lblNewLabel_96;
	private Composite composite_61;
	private Label lblNewLabel_97;
	private Text txt_strdate;
	private Label lblNewLabel_98;
	private Text txt_enddate;
	private Button btn_datedelete;
	private Label lblNewLabel_99;
	private Composite composite_62;
	private Text txt_url;
	private Label lblNewLabel_100;
	private Composite composite_63;
	private Button btn_optionN;
	private Button btn_optionY;
	private Label lblNewLabel_101;
	private Text txt_option1;
	private Label lblNewLabel_102;
	private Label lblNewLabel_103;
	private Label lblNewLabel_104;
	private Label lblNewLabel_105;
	private Label lblNewLabel_106;
	private Label lblNewLabel_107;
	private Label lblNewLabel_108;
	private Label lblNewLabel_109;
	private Label lblNewLabel_110;
	private Text txt_option2;
	private Text txt_option3;
	private Text txt_option4;
	private Text txt_option5;
	private Text txt_option6;
	private Text txt_option7;
	private Text txt_option8;
	private Text txt_option9;
	private Text txt_option10;
	private Label lblNewLabel_111;
	private Composite composite_64;
	private Button btn_optval1;
	private Button btn_optval2;
	private Button btn_optval3;
	private Button btn_optval4;
	private Button btn_optval5;
	private Label lblNewLabel_112;
	private Label lblNewLabel_113;
	private Composite composite_65;
	private Combo cb_2optiondefault;
	private Label lblNewLabel_114;
	private Label lblNewLabel_115;
	private Composite composite_66;
	private Combo cb_optsetting;
	private Label lblNewLabel_116;
	private Composite composite_67;
	private Button btn_1wonphone;
	private Label lblNewLabel_117;
	private Label lblNewLabel_118;
	private Composite composite_68;
	private Button btn_costcomparisY;
	private Button btn_costcomparisN;
	private Label lblNewLabel_119;
	private Composite composite_69;
	private Text txt_effecdate;
	private Label lblNewLabel_120;
	private Label lblNewLabel_121;
	private Composite composite_70;
	private Button btn_phone1;
	private Button btn_phone2;
	private Text txt_phone;
	private Button btn_phonesearch;
	private Button btn_phonedel;
	private Label lblNewLabel_122;
	private Composite composite_71;
	private Text txt_phoneurl;
	private Label lblNewLabel_123;
	private Composite composite_72;
	private Button btn_nicknmsearch;
	private Button btn_nicknmdel;
	private Label lblNewLabel_124;
	private Composite composite_73;
	private Text txt_brand;
	private Button btn_brandsearch;
	private Button btn_branddel;
	private Label lblNewLabel_125;
	private Label lblNewLabel_126;
	private Label lblNewLabel_127;
	private Composite composite_74;
	private Composite composite_75;
	private Text txt_disney;
	private Button btn_disneysearch;
	private Label lblNewLabel_128;
	private Text txt_point;
	private Button btn_pointsearch;
	private Label lblNewLabel_129;
	private Label lblNewLabel_130;
	private Label lblNewLabel_131;
	private Label lblNewLabel_132;
	private Composite composite_76;
	private Button btn_sellercash;
	private Label lblNewLabel_133;
	private Label lblNewLabel_134;
	private Composite composite_77;
	private Combo cb_sellerprod;
	private Label lblNewLabel_135;
	private Label lblNewLabel_136;
	private Label lblNewLabel_137;
	private Composite composite_78;
	private Combo cb_prodedit;
	private Label lblNewLabel_138;
	private Label lblNewLabel_139;
	private Composite composite_79;
	private Text txt_prodmd;
	private Button btn_prodmdsearch;
	private Button btn_prodmddel;
	private Label lblNewLabel_140;
	private Label lblNewLabel_141;
	private Composite composite_80;
	private Composite composite_81;
	private Button btn_orgin2_1;
	private Button btn_orgin2_2;
	private Text txt_orgin2_1;
	private Label lblNewLabel_142;
	private Button btn_orgin3_1;
	Button btn_orgin1_1;
	Button btn_orgin1_2;
	Button btn_orgin1_3;
	Button btn_orgin1_4;
	Button btn_orgin1_5;
	private Label lblNewLabel_143;
	private Composite composite_84;
	private Text txt_prodpromotion;
	private Composite composite_85;
	private Label lblNewLabel_144;
	private Label lblNewLabel_145;
	private Label lblNewLabel_146;
	private Label lblNewLabel_147;
	private Label lblNewLabel_148;
	private Label lblNewLabel_149;
	private Label lblNewLabel_150;
	private Label lblNewLabel_151;
	private Label lblNewLabel_152;
	private Label lblNewLabel_153;
	private ShopCombo scb_express;
	private Label lblNewLabel_154;
	
	public ShopAdditionalInformationDetail(Shell parentShell, ShopAdditionalInforMationManager opener, List<String> list, ShopProduct11stAdditionDto dto, String shopseq) {
//		getShell(),this,null,joinlist,list.get(0)
		super(parentShell);
		this.opener = opener;
		this.list = list;
		this.dto = dto;
		this.shopseq = shopseq;
		try {
			if(list==null) {
				datasource   = ShopCommonDao.get().getExpress(dto.getShopcd());
			}else {
				datasource   = ShopCommonDao.get().getExpress(list.get(0));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {		
		scrolledComposite = new ScrolledComposite( parent, SWT.H_SCROLL | SWT.V_SCROLL );
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite container = new Composite(scrolledComposite, SWT.NONE );
		container.setLayout(new GridLayout(2, false));
		
		group_1 = new Group(container, SWT.NONE);
		group_1.setLayout(new GridLayout(2, false));
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		group_1.setText("■ [KEY 정보]");
		
		label = new Label(group_1, SWT.NONE);
		label.setText("※ 제목");
		
		txt_title = new Text(group_1, SWT.BORDER);
		GridData gd_txt_title = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_title.widthHint = 700;
		txt_title.setLayoutData(gd_txt_title);
		
		lblNewLabel_37 = new Label(group_1, SWT.NONE);
		lblNewLabel_37.setText("   메모");
		
		txt_memo = new Text(group_1, SWT.BORDER);
		GridData gd_txt_memo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_memo.widthHint = 700;
		txt_memo.setLayoutData(gd_txt_memo);
		
		lblNewLabel_38 = new Label(group_1, SWT.NONE);
		GridData gd_lblNewLabel_38 = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 2, 1);
		gd_lblNewLabel_38.heightHint = 30;
		lblNewLabel_38.setLayoutData(gd_lblNewLabel_38);
		lblNewLabel_38.setText("* 공동구매 선택시 수수료는 셀러포인트에서 차감됩니다.");
		lblNewLabel_38.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		Label label_15 = new Label(group_1, SWT.NONE);
		label_15.setText("※ 아이디선택");
		
		cb_idcheck = new Combo(group_1, SWT.NONE);
		GridData gd_cb_idcheck = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_idcheck.widthHint = 120;
		cb_idcheck.setLayoutData(gd_cb_idcheck);
		cb_idcheck.setItems(new String[] {"아이디선택"});
		cb_idcheck.select(0);
		
		label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("※ 판매방식");
		
		composite_3 = new Composite(group_1, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_3.setLayout(new GridLayout(5, false));
		
		btn_fixed = new Button(composite_3, SWT.RADIO);
		btn_fixed.setText("고정가판매");
		
		btn_public = new Button(composite_3, SWT.RADIO);
		btn_public.setText("공동구매");
		
		btn_used = new Button(composite_3, SWT.RADIO);
		btn_used.setText("중고판매");
		
		btn_reser = new Button(composite_3, SWT.RADIO);
		btn_reser.setText("예약판매");
		
		lblNewLabel_39 = new Label(composite_3, SWT.NONE);
		lblNewLabel_39.setText("	* 공동구매 로 상품연동은 불가합니다.");
		lblNewLabel_39.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		group_2 = new Group(container, SWT.NONE);
		group_2.setLayout(new GridLayout(2, false));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		group_2.setText("■ [판매조건정보]");
		
		label_6 = new Label(group_2, SWT.NONE);
		label_6.setText("※ 서비스상품");
		
		composite_85 = new Composite(group_2, SWT.NONE);
		composite_85.setLayout(new GridLayout(2, false));
		composite_85.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_service = new Combo(composite_85, SWT.NONE);
		cb_service.setItems(new String[] {"일반배송상품", "소호상품", "마트상품"});
		cb_service.select(0);
		
		lblNewLabel_144 = new Label(composite_85, SWT.NONE);
		lblNewLabel_144.setText("   ※ PIN번호 상품은 주문수집 미지원");
		lblNewLabel_144.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_153 = new Label(group_2, SWT.NONE);
		lblNewLabel_153.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_153.setText("* [단일상품]서비스상품은 일반배송상품만 가능");
		lblNewLabel_153.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_145 = new Label(group_2, SWT.NONE);
		lblNewLabel_145.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_145.setText("* 상품홍보문구는 최대 한글 20자, 영문/숫자 40자 이내로 입력하셔야 합니다.");
		lblNewLabel_145.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_146 = new Label(group_2, SWT.NONE);
		lblNewLabel_146.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_146.setText("* 상품홍보문구는 11번가 상품명 하단에 노출되며, 검색대상에 포함되지 않습니다.");
		lblNewLabel_146.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_147 = new Label(group_2, SWT.NONE);
		lblNewLabel_147.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_147.setText("* 상품홍보문구는 사방넷 상품정보의 사이트검색어로 반영되지 않습니다.");
		lblNewLabel_147.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_148 = new Label(group_2, SWT.NONE);
		lblNewLabel_148.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_148.setText("* 원산지 기타 선택시 추가설명을 입력해 주십시오.");
		lblNewLabel_148.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_149 = new Label(group_2, SWT.NONE);
		lblNewLabel_149.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_149.setText("* 원산지가 1군데 이상인 경우, 원산지가 다른 상품 같이 등록에 체크하여 주십시오.");
		lblNewLabel_149.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_150 = new Label(group_2, SWT.NONE);
		lblNewLabel_150.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_150.setText("* 옵션/추가구성 상품의 원산지가 다른 경우, 반드시 상세설명에 해당 상품별 별도 원산지를 표기해 주시기 바랍니다.");
		lblNewLabel_150.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_151 = new Label(group_2, SWT.NONE);
		lblNewLabel_151.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_151.setText("* 농수산물 가공품의 경우, 제조국이 아닌 원료의 원산지를 표기하여야만 합니다.");
		lblNewLabel_151.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_152 = new Label(group_2, SWT.NONE);
		lblNewLabel_152.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_152.setText("* 원산지 항목에서 사방넷 상품정보 사용을 선택하는 농수산물 가공품의 경우, 농산물/수산물/가공품 항목 중 해당하는 항목도 선택하시기 바랍니다.");
		lblNewLabel_152.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_143 = new Label(group_2, SWT.NONE);
		lblNewLabel_143.setText("   상품홍보문구");
		
		composite_84 = new Composite(group_2, SWT.NONE);
		composite_84.setLayout(new GridLayout(1, false));
		composite_84.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_prodpromotion = new Text(composite_84, SWT.BORDER);
		GridData gd_txt_prodpromotion = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodpromotion.widthHint = 700;
		txt_prodpromotion.setLayoutData(gd_txt_prodpromotion);
		
		lblNewLabel_141 = new Label(group_2, SWT.NONE);
		lblNewLabel_141.setText("   원산지");
		
		composite_80 = new Composite(group_2, SWT.NONE);
		composite_80.setLayout(new GridLayout(1, false));
		composite_80.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		composite_81 = new Composite(composite_80, SWT.NONE);
		composite_81.setLayout(new GridLayout(5, false));
		composite_81.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btn_orgin1_1 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_1.setText("농산물");
		
		btn_orgin1_2 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_2.setText("수산물");
		
		btn_orgin1_3 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_3.setText("가공품");
		
		btn_orgin1_4 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_4.setText("원산지 의무 표시대상 아님");
		
		btn_orgin1_5 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_5.setText("상품별 원산지는 상세설명 참조");
		
		Composite composite_82 = new Composite(composite_80, SWT.NONE);
		composite_82.setLayout(new GridLayout(5, false));
		composite_82.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btn_orgin2_1 = new Button(composite_82, SWT.RADIO);
		btn_orgin2_1.setText(" M-Link 상품정보 사용");
		
		btn_orgin2_2 = new Button(composite_82, SWT.RADIO);
		btn_orgin2_2.setText(" 기타 사용");
		
		lblNewLabel_154 = new Label(composite_82, SWT.NONE);
		lblNewLabel_154.setText("	(기타 사용이거나 기타국가일때 추가설명 :");
		
		txt_orgin2_1 = new Text(composite_82, SWT.BORDER);
		GridData gd_txt_orgin2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_orgin2_1.widthHint = 200;
		txt_orgin2_1.setLayoutData(gd_txt_orgin2_1);
		
		lblNewLabel_142 = new Label(composite_82, SWT.NONE);
		lblNewLabel_142.setText(")");
		
		Composite composite_83 = new Composite(composite_80, SWT.NONE);
		composite_83.setLayout(new GridLayout(1, false));
		composite_83.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_orgin3_1 = new Button(composite_83, SWT.CHECK);
		btn_orgin3_1.setText("  원산지가 다른 상품 같이 등록");
		
		lblNewLabel_139 = new Label(group_2, SWT.NONE);
		lblNewLabel_139.setText("   상품모델");
		
		composite_79 = new Composite(group_2, SWT.NONE);
		composite_79.setLayout(new GridLayout(4, false));
		composite_79.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_prodmd = new Text(composite_79, SWT.BORDER);
		GridData gd_txt_prodmd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodmd.widthHint = 500;
		txt_prodmd.setLayoutData(gd_txt_prodmd);
		
		btn_prodmdsearch = new Button(composite_79, SWT.NONE);
		btn_prodmdsearch.setText("검색");
		
		btn_prodmddel = new Button(composite_79, SWT.NONE);
		btn_prodmddel.setText("삭제");
		
		lblNewLabel_140 = new Label(composite_79, SWT.NONE);
		lblNewLabel_140.setText(" *모델명에 포함된 브랜드가 우선반영됩니다.");
		lblNewLabel_140.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_137 = new Label(group_2, SWT.NONE);
		lblNewLabel_137.setText("   상품모델 수정");
		
		composite_78 = new Composite(group_2, SWT.NONE);
		composite_78.setLayout(new GridLayout(2, false));
		composite_78.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_prodedit = new Combo(composite_78, SWT.NONE);
		cb_prodedit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_prodedit.setItems(new String[] {"수정함", "수정안함"});
		cb_prodedit.select(0);
		
		lblNewLabel_138 = new Label(composite_78, SWT.NONE);
		lblNewLabel_138.setText("   ※상품수정 시만 지원됩니다.");
		lblNewLabel_138.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_135 = new Label(group_2, SWT.NONE);
		lblNewLabel_135.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_135.setText("* 11번가 상품연동 시 '판매자 상품코드'란에 입력할 값에 대해 사방넷 상품정보 중 선택할 수 있습니다.\r\n" + 
				"   상품수정 송신 시 [사용안함]을 선택한 경우 기존 scm에 등록된 정보가 삭제됩니다.");
		lblNewLabel_135.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_134 = new Label(group_2, SWT.NONE);
		lblNewLabel_134.setText("※ 판매자상품코드");
		
		composite_77 = new Composite(group_2, SWT.NONE);
		composite_77.setLayout(new GridLayout(1, false));
		composite_77.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_sellerprod = new Combo(composite_77, SWT.NONE);
		cb_sellerprod.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_sellerprod.setItems(new String[] {"송신번호", "자체상품코드", "모델No","사용안함","모델명"});
		cb_sellerprod.select(0);
		
		lblNewLabel_132 = new Label(group_2, SWT.NONE);
		lblNewLabel_132.setText("   셀러캐시 사용");
		
		composite_76 = new Composite(group_2, SWT.NONE);
		composite_76.setLayout(new GridLayout(2, false));
		composite_76.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_sellercash = new Button(composite_76, SWT.CHECK);
		btn_sellercash.setText("셀러캐시 사용");
		
		lblNewLabel_133 = new Label(composite_76, SWT.NONE);
		lblNewLabel_133.setText(" * 셀러포인트가 부족하면 셀러캐시가 차감됩니다.");
		
		label_8 = new Label(group_2, SWT.NONE);
		label_8.setText("※ 상품상태");
		
		composite_12 = new Composite(group_2, SWT.NONE);
		composite_12.setLayout(new GridLayout(9, false));
		
		btn_new = new Button(composite_12, SWT.RADIO);
		btn_new.setText("새상품");
		
		btn_stock = new Button(composite_12, SWT.RADIO);
		btn_stock.setText("재고상품");
		
		btn_making = new Button(composite_12, SWT.RADIO);
		btn_making.setText("주문제작상품");
		
		btn_usedprod = new Button(composite_12, SWT.RADIO);
		btn_usedprod.setText("중고상품");
		
		btn_reaper = new Button(composite_12, SWT.RADIO);
		btn_reaper.setText("리퍼상품");
		
		btn_exhibi = new Button(composite_12, SWT.RADIO);
		btn_exhibi.setText("전시(진열)상품");
		
		btn_return = new Button(composite_12, SWT.RADIO);
		btn_return.setText("반품상품");
		
		btn_scratch = new Button(composite_12, SWT.RADIO);
		btn_scratch.setText("스크래치상품");
		
		btn_rare = new Button(composite_12, SWT.RADIO);
		btn_rare.setText("희귀소장품");		

		lblNewLabel_130 = new Label(group_2, SWT.NONE);
		lblNewLabel_130.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_130.setText("* 미성년자 구매불가를 선택하시면, 미성년자 회원에게 상품이미지가 노출되지 않으며 '19금'으로 표시됩니다.");
		lblNewLabel_130.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_131 = new Label(group_2, SWT.NONE);
		lblNewLabel_131.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_131.setText("* 구매불가 상품을 구매가능으로 표시한 경우, 판매금지 처리 될 수 있습니다.");
		lblNewLabel_130.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_22 = new Label(group_2, SWT.NONE);
		label_22.setText("※ 미성년자 구매가능");
		
		composite_13 = new Composite(group_2, SWT.NONE);
		composite_13.setLayout(new GridLayout(2, false));
		
		btn_minoryes = new Button(composite_13, SWT.RADIO);
		btn_minoryes.setText("미성년자 구매가능");
		
		btn_minorno = new Button(composite_13, SWT.RADIO);
		btn_minorno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btn_minorno.setText("미성년자 구매불가");	
		
		lblNewLabel_126 = new Label(group_2, SWT.NONE);
		lblNewLabel_126.setText("   지점 선택");
		
		composite_74 = new Composite(group_2, SWT.NONE);
		composite_74.setLayout(new GridLayout(2, false));
		composite_74.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_point = new Text(composite_74, SWT.BORDER);
		GridData gd_txt_point = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_point.widthHint = 700;
		txt_point.setLayoutData(gd_txt_point);
		
		btn_pointsearch = new Button(composite_74, SWT.NONE);
		btn_pointsearch.setText("검색");
		
		lblNewLabel_129 = new Label(composite_74, SWT.NONE);
		lblNewLabel_129.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_129.setText("* 카테고리 생활플러스 일 경우 지점 선택필수 입니다.");
		lblNewLabel_129.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_127 = new Label(group_2, SWT.NONE);
		lblNewLabel_127.setText("   디즈니 시리즈 선택");
		
		composite_75 = new Composite(group_2, SWT.NONE);
		composite_75.setLayout(new GridLayout(2, false));
		composite_75.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_disney = new Text(composite_75, SWT.BORDER);
		GridData gd_txt_disney = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_disney.widthHint = 700;
		txt_disney.setLayoutData(gd_txt_disney);
		
		btn_disneysearch = new Button(composite_75, SWT.NONE);
		btn_disneysearch.setText("검색");
		
		lblNewLabel_128 = new Label(composite_75, SWT.NONE);
		lblNewLabel_128.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_128.setText("* 카테고리 완구,영유아용품 일 경우 디즈니 시리즈 선택필수 입니다.");
		lblNewLabel_128.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_125 = new Label(group_2, SWT.NONE);
		lblNewLabel_125.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_125.setText("* 동일한 아이디에 닉네임명이 여러개인경우 부가정보에서 닉네임명을 선택합니다.");
		lblNewLabel_125.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_7 = new Label(group_2, SWT.NONE);
		label_7.setText("※ 닉네임");
		
		composite_72 = new Composite(group_2, SWT.NONE);
		composite_72.setLayout(new GridLayout(3, false));
		composite_72.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_nicknm = new Text(composite_72, SWT.BORDER);
		GridData gd_txt_nicknm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_nicknm.widthHint = 500;
		txt_nicknm.setLayoutData(gd_txt_nicknm);
		
		btn_nicknmsearch = new Button(composite_72, SWT.NONE);
		btn_nicknmsearch.setText("검색");
		
		btn_nicknmdel = new Button(composite_72, SWT.NONE);
		btn_nicknmdel.setText("삭제");
		
		lblNewLabel_124 = new Label(group_2, SWT.NONE);
		lblNewLabel_124.setText("   브랜드");
		
		composite_73 = new Composite(group_2, SWT.NONE);
		composite_73.setLayout(new GridLayout(3, false));
		composite_73.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_brand = new Text(composite_73, SWT.BORDER);
		GridData gd_txt_brand = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_brand.widthHint = 500;
		txt_brand.setLayoutData(gd_txt_brand);
		
		btn_brandsearch = new Button(composite_73, SWT.NONE);
		btn_brandsearch.setText("검색");
		
		btn_branddel = new Button(composite_73, SWT.NONE);
		btn_branddel.setText("삭제");
		
		lblNewLabel_123 = new Label(group_2, SWT.NONE);
		lblNewLabel_123.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_123.setText("* 가입신청서 연결 URL 은 핸드폰 관련카테고리에만 적용가능합니다.");
		lblNewLabel_123.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_122 = new Label(group_2, SWT.NONE);
		lblNewLabel_122.setText("   가입신청서 연결 URL");
		
		composite_71 = new Composite(group_2, SWT.NONE);
		composite_71.setLayout(new GridLayout(1, false));
		composite_71.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_phoneurl = new Text(composite_71, SWT.BORDER);
		GridData gd_txt_phoneurl = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phoneurl.widthHint = 700;
		txt_phoneurl.setLayoutData(gd_txt_phoneurl);
		
		lblNewLabel_121 = new Label(group_2, SWT.NONE);
		lblNewLabel_121.setText("   휴대폰 요금제");
		
		composite_70 = new Composite(group_2, SWT.NONE);
		composite_70.setLayout(new GridLayout(4, false));
		composite_70.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_phone1 = new Button(composite_70, SWT.RADIO);
		btn_phone1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btn_phone1.setText(" 일반약정 단말가격 (일정기간 약정 시 동일하게 적용되는 단말기 가격)");
		
		btn_phone2 = new Button(composite_70, SWT.RADIO);
		btn_phone2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btn_phone2.setText(" 요금제 약정 단말가격 (요금제 약정 시 요금제에 따라 단말할인이 적용되는 단말기 가격)");
		new Label(composite_70, SWT.NONE);
		
		txt_phone = new Text(composite_70, SWT.BORDER);
		GridData gd_txt_phone = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phone.widthHint = 570;
		txt_phone.setLayoutData(gd_txt_phone);
		
		btn_phonesearch = new Button(composite_70, SWT.NONE);
		btn_phonesearch.setText("검색");
		
		btn_phonedel = new Button(composite_70, SWT.NONE);
		btn_phonedel.setText("삭제");
		
		lblNewLabel_119 = new Label(group_2, SWT.NONE);
		lblNewLabel_119.setText("   유효일자");
		
		composite_69 = new Composite(group_2, SWT.NONE);
		composite_69.setLayout(new GridLayout(2, false));
		composite_69.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_effecdate = new Text(composite_69, SWT.BORDER);
		GridData gd_txt_effecdate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_effecdate.widthHint = 100;
		txt_effecdate.setLayoutData(gd_txt_effecdate);
		
		lblNewLabel_120 = new Label(composite_69, SWT.NONE);
		lblNewLabel_120.setText(" 입력예) 2008/01/31");
		
		lblNewLabel_118 = new Label(group_2, SWT.NONE);
		lblNewLabel_118.setText("   가격비교사이트 등록");
		
		composite_68 = new Composite(group_2, SWT.NONE);
		composite_68.setLayout(new GridLayout(2, false));
		composite_68.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_costcomparisY = new Button(composite_68, SWT.RADIO);
		btn_costcomparisY.setText("적용	");
		
		btn_costcomparisN = new Button(composite_68, SWT.RADIO);
		btn_costcomparisN.setText("적용안함");
		
		lblNewLabel_117 = new Label(group_2, SWT.NONE);
		lblNewLabel_117.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_117.setText("*  판매기간 설정 값중 설정안함 설정시 판매시작일(상품등록일)만 등록되고 종료일은 등록되지 않습니다.");
		lblNewLabel_117.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_24 = new Label(group_2, SWT.NONE);
		label_24.setText("※ 판매기간 설정");
		
		composite_14 = new Composite(group_2, SWT.NONE);
		composite_14.setLayout(new GridLayout(2, false));
		
		btn_dateyes = new Button(composite_14, SWT.RADIO);
		btn_dateyes.setText("설정함");
		
		btn_dateno = new Button(composite_14, SWT.RADIO);
		btn_dateno.setText("설정안함");
		
		label_25 = new Label(group_2, SWT.NONE);
		label_25.setText("※ 판매기간 단위");
		
		composite_11 = new Composite(group_2, SWT.NONE);
		composite_11.setLayout(new GridLayout(6, false));
		
		btn_7day = new Button(composite_11, SWT.RADIO);
		btn_7day.setText("7일");
		
		btn_15day = new Button(composite_11, SWT.RADIO);
		btn_15day.setText("15일");
		
		btn_30day = new Button(composite_11, SWT.RADIO);
		btn_30day.setText("30일");
		
		btn_60day = new Button(composite_11, SWT.RADIO);
		btn_60day.setText("60일");
		
		btn_90day = new Button(composite_11, SWT.RADIO);
		btn_90day.setText("90일");
		
		btn_120day = new Button(composite_11, SWT.RADIO);
		btn_120day.setText("120일");
		
		lblNewLabel_116 = new Label(group_2, SWT.NONE);
		lblNewLabel_116.setText("   1원폰 상품");
		
		composite_67 = new Composite(group_2, SWT.NONE);
		composite_67.setLayout(new GridLayout(1, false));
		composite_67.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_1wonphone = new Button(composite_67, SWT.CHECK);
		btn_1wonphone.setText("1원폰 상품 등록하기");
		
		lblNewLabel_115 = new Label(group_2, SWT.NONE);
		lblNewLabel_115.setText("   옵션 설정");
		
		composite_66 = new Composite(group_2, SWT.NONE);
		composite_66.setLayout(new GridLayout(1, false));
		composite_66.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_optsetting = new Combo(composite_66, SWT.NONE);
		cb_optsetting.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_optsetting.setItems(new String[] {"상품옵션 + 추가상품","상품옵션"});
		cb_optsetting.select(0);
		
		lblNewLabel_114 = new Label(group_2, SWT.NONE);
		lblNewLabel_114.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_114.setText("* 1단 옵션을 선택할 경우, \"색상:사이즈\" 와 같이 옵션이 1단으로 조합되어 등록됩니다.\r\n" + 
				"* 2단 옵션을 선택할 경우, \"색상\", \"사이즈\" 와 같이 옵션이 2단으로 분리되어 등록됩니다.\r\n" + 
				"* 3단 옵션 적용을 원하는 경우 '2단 옵션'으로 선택해 주시기 바랍니다.\r\n" + 
				"\r\n" + 
				"* [단일상품] 등록 시 유의 사항\r\n" + 
				"- 2단 옵션까지 등록이 가능하며, 3단 옵션은 등록이 불가합니다.\r\n" + 
				"- 옵션상세명칭, 옵션 구조는 수정이 불가합니다.\r\n" + 
				"  ex) 옵션 없는 단품 → 1단, 1단 → 2단, 2단 → 1단 등 옵션 구조 수정 불가\r\n" + 
				"- 옵션의 재고와 상태는 옵션 구조에 따라 수정 가능여부가 다르게 지원됩니다.\r\n" + 
				"  1단 옵션 : 옵션의 재고와 상태 수정이 가능합니다.\r\n" + 
				"  2단 옵션 : 옵션의 재고와 상태 수정이 불가합니다. (API 미지원)\r\n" + 
				"- 추가상품(추가옵션)은 등록이 불가합니다.\r\n" + 
				"- 2단 옵션 적용여부를 '1단 옵션(기존옵션)'으로 선택 시 사방넷 상품의 옵션도 1단 옵션이어야 합니다.\r\n" + 
				"- 2단 옵션 적용여부를 '2단 옵션'으로 선택 시 1단과 2단 옵션으로 등록이 가능하지만 재고 수량은 999개로 일괄 반영됩니다. (2단 옵션인 경우 API에서 옵션별 재고 수정 미지원)\r\n" + 
				"- 2단 옵션 적용여부를 '2단 옵션'으로 선택 시 1단과 2단의 옵션이 모두 조합되어 등록됩니다. (11번가 정책)\r\n" + 
				"- 구매자 작성형 옵션은 1개까지 등록이 가능합니다. (11번가 정책)");
		lblNewLabel_114.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_113 = new Label(group_2, SWT.NONE);
		lblNewLabel_113.setText("   2단 옵션 적용여부");
		
		composite_65 = new Composite(group_2, SWT.NONE);
		composite_65.setLayout(new GridLayout(1, false));
		composite_65.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_2optiondefault = new Combo(composite_65, SWT.NONE);
		cb_2optiondefault.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_2optiondefault.setItems(new String[] {"1단 옵션(기존방식)","2단 옵션"});
		cb_2optiondefault.select(0);
		
		lblNewLabel_112 = new Label(group_2, SWT.NONE);
		lblNewLabel_112.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_112.setText("* 독립형으로 옵션이 등록될 경우 옵션값 노출 방식을 등록순, 또는 옵션값 가나다순, 옵션값 가나다역순을 선택해 주시기 바랍니다.");
		lblNewLabel_112.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_111 = new Label(group_2, SWT.NONE);
		lblNewLabel_111.setText("   옵션값 노출방식");
		
		composite_64 = new Composite(group_2, SWT.NONE);
		composite_64.setLayout(new GridLayout(5, false));
		composite_64.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_optval1 = new Button(composite_64, SWT.RADIO);
		btn_optval1.setText("등록순");
		
		btn_optval2 = new Button(composite_64, SWT.RADIO);
		btn_optval2.setText("옵션값 가나다순");
		
		btn_optval3 = new Button(composite_64, SWT.RADIO);
		btn_optval3.setText("옵션값 가나다역순");
		
		btn_optval4 = new Button(composite_64, SWT.RADIO);
		btn_optval4.setText("옵션가격 낮은순");
		
		btn_optval5 = new Button(composite_64, SWT.RADIO);
		btn_optval5.setText("옵션가격 높은순");
		
		lblNewLabel_100 = new Label(group_2, SWT.NONE);
		lblNewLabel_100.setText("   구매자 작성형 옵션");
		
		composite_63 = new Composite(group_2, SWT.NONE);
		composite_63.setLayout(new GridLayout(4, false));
		composite_63.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_optionN = new Button(composite_63, SWT.RADIO);
		btn_optionN.setText("사용안함");
		
		btn_optionY = new Button(composite_63, SWT.RADIO);
		btn_optionY.setText("사용함");
		
		lblNewLabel_101 = new Label(composite_63, SWT.NONE);
		lblNewLabel_101.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_101.setText("   옵션명1 :  ");
		
		txt_option1 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option1.widthHint = 500;
		txt_option1.setLayoutData(gd_txt_option1);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_102 = new Label(composite_63, SWT.NONE);
		lblNewLabel_102.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_102.setText("   옵션명2 :  ");
		
		txt_option2 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option2.widthHint = 500;
		txt_option2.setLayoutData(gd_txt_option2);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_103 = new Label(composite_63, SWT.NONE);
		lblNewLabel_103.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_103.setText("   옵션명3 :  ");
		
		txt_option3 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option3 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option3.widthHint = 500;
		txt_option3.setLayoutData(gd_txt_option3);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_104 = new Label(composite_63, SWT.NONE);
		lblNewLabel_104.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_104.setText("   옵션명4 :  ");
		
		txt_option4 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option4 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option4.widthHint = 500;
		txt_option4.setLayoutData(gd_txt_option4);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_105 = new Label(composite_63, SWT.NONE);
		lblNewLabel_105.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_105.setText("   옵션명5 :  ");
		
		txt_option5 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option5 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option5.widthHint = 500;
		txt_option5.setLayoutData(gd_txt_option5);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_106 = new Label(composite_63, SWT.NONE);
		lblNewLabel_106.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_106.setText("   옵션명6 :  ");
		
		txt_option6 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option6 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option6.widthHint = 500;
		txt_option6.setLayoutData(gd_txt_option6);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_107 = new Label(composite_63, SWT.NONE);
		lblNewLabel_107.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_107.setText("   옵션명7 :  ");
		
		txt_option7 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option7 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option7.widthHint = 500;
		txt_option7.setLayoutData(gd_txt_option7);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_108 = new Label(composite_63, SWT.NONE);
		lblNewLabel_108.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_108.setText("   옵션명8 :  ");
		
		txt_option8 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option8 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option8.widthHint = 500;
		txt_option8.setLayoutData(gd_txt_option8);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_109 = new Label(composite_63, SWT.NONE);
		lblNewLabel_109.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_109.setText("   옵션명9 :  ");
		
		txt_option9 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option9 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option9.widthHint = 500;
		txt_option9.setLayoutData(gd_txt_option9);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_110 = new Label(composite_63, SWT.NONE);
		lblNewLabel_110.setText("   옵션명10 : ");
		
		txt_option10 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option10 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option10.widthHint = 500;
		txt_option10.setLayoutData(gd_txt_option10);
		
		lblNewLabel_99 = new Label(group_2, SWT.NONE);
		lblNewLabel_99.setText("   제휴사 상품 URL");
		
		composite_62 = new Composite(group_2, SWT.NONE);
		composite_62.setLayout(new GridLayout(1, false));
		composite_62.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_url = new Text(composite_62, SWT.BORDER);
		GridData gd_txt_url = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_url.widthHint = 700;
		txt_url.setLayoutData(gd_txt_url);
		
		lblNewLabel_96 = new Label(group_2, SWT.NONE);
		lblNewLabel_96.setText("   출발일");
		
		composite_61 = new Composite(group_2, SWT.NONE);
		composite_61.setLayout(new GridLayout(5, false));
		composite_61.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_97 = new Label(composite_61, SWT.NONE);
		lblNewLabel_97.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_97.setText("최초 출발일");
		
		txt_strdate = new Text(composite_61, SWT.BORDER);
		GridData gd_txt_strdate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_strdate.widthHint = 100;
		txt_strdate.setLayoutData(gd_txt_strdate);
		txt_strdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_giftperiodst);
				calander.open();
			}
		});
		
		lblNewLabel_98 = new Label(composite_61, SWT.NONE);
		lblNewLabel_98.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_98.setText("	마지막 출발일");
		
		txt_enddate = new Text(composite_61, SWT.BORDER);
		GridData gd_txt_enddate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_enddate.widthHint = 100;
		txt_enddate.setLayoutData(gd_txt_enddate);
		txt_enddate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_enddate);
				calander.open();
			}
		});
		
		btn_datedelete = new Button(composite_61, SWT.NONE);
		btn_datedelete.setText("삭제");
		btn_datedelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_strdate.setText("");
				txt_enddate.setText("");
			}
		});
		
		lblNewLabel_92 = new Label(group_2, SWT.NONE);
		lblNewLabel_92.setText("   베네피아 할인 설정");
		
		composite_60 = new Composite(group_2, SWT.NONE);
		composite_60.setLayout(new GridLayout(5, false));
		composite_60.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_benepia1 = new Button(composite_60, SWT.CHECK);
		btn_benepia1.setText(" 동의함");
		btn_benepia1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisibleBenepia();
			}
		});
		txt_benepia1_1 = new Text(composite_60, SWT.BORDER);
		GridData gd_txt_benepia1_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_benepia1_1.widthHint = 120;
		txt_benepia1_1.setLayoutData(gd_txt_benepia1_1);
		
		lblNewLabel_93 = new Label(composite_60, SWT.NONE);
		lblNewLabel_93.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_93.setText(" % 할인");
		new Label(composite_60, SWT.NONE);
		
		btn_benepia2 = new Button(composite_60, SWT.CHECK);
		btn_benepia2.setText(" 기간설정");
		btn_benepia2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisibleBenepiaPreiod();
			}
		});
		txt_benepiast = new Text(composite_60, SWT.BORDER);
		GridData gd_txt_benepiast = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_benepiast.widthHint = 120;
		txt_benepiast.setLayoutData(gd_txt_benepiast);
		
		lblNewLabel_94 = new Label(composite_60, SWT.NONE);
		lblNewLabel_94.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_94.setText(" ~ ");
		
		txt_benepiaen = new Text(composite_60, SWT.BORDER);
		GridData gd_txt_benepiaen = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_benepiaen.widthHint = 120;
		txt_benepiaen.setLayoutData(gd_txt_benepiaen);
		
		lblNewLabel_95 = new Label(composite_60, SWT.NONE);
		lblNewLabel_95.setText(" * 날짜는 2017/01/01 형식으로 입력하여 주세요");
		
		lblNewLabel_90 = new Label(group_2, SWT.NONE);
		lblNewLabel_90.setText("   최소구매수량");
		
		composite_59 = new Composite(group_2, SWT.NONE);
		composite_59.setLayout(new GridLayout(3, false));
		composite_59.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_minlimit1 = new Button(composite_59, SWT.RADIO);
		btn_minlimit1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		btn_minlimit1.setText(" 제한안함 :최소구매수량 제한안함");
		
		btn_minlimit2 = new Button(composite_59, SWT.RADIO);
		btn_minlimit2.setText(" 1회 제한 : ");
		
		txt_minlimit2_1 = new Text(composite_59, SWT.BORDER);
		GridData gd_txt_minlimit2_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_minlimit2_1.widthHint = 100;
		txt_minlimit2_1.setLayoutData(gd_txt_minlimit2_1);
		
		lblNewLabel_91 = new Label(composite_59, SWT.NONE);
		lblNewLabel_91.setText(" 개 (1회 구매 시, 최소 구매할 수 있는 수량을 제한합니다.)");
		
		lblNewLabel_84 = new Label(group_2, SWT.NONE);
		lblNewLabel_84.setText("   최대구매수량");
		
		composite_55 = new Composite(group_2, SWT.NONE);
		composite_55.setLayout(new GridLayout(7, false));
		composite_55.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		composite_56 = new Composite(composite_55, SWT.NONE);
		composite_56.setLayout(new GridLayout(1, false));
		composite_56.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit1 = new Button(composite_56, SWT.RADIO);
		btn_limit1.setText(" 제한안함 : 최대구매수량 제한안함");
		
		composite_57 = new Composite(composite_55, SWT.NONE);
		composite_57.setLayout(new GridLayout(3, false));
		composite_57.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit2 = new Button(composite_57, SWT.RADIO);
		btn_limit2.setText(" 1회 제한 : ");
		
		txt_limit2_1 = new Text(composite_57, SWT.BORDER);
		GridData gd_txt_limit2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit2_1.widthHint = 100;
		txt_limit2_1.setLayoutData(gd_txt_limit2_1);
		
		lblNewLabel_85 = new Label(composite_57, SWT.NONE);
		lblNewLabel_85.setText(" 개 (1회 구매 시, 최대 구매할 수 있는 수량을 제한합니다.)");
		
		composite_58 = new Composite(composite_55, SWT.NONE);
		composite_58.setLayout(new GridLayout(7, false));
		composite_58.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit3 = new Button(composite_58, SWT.RADIO);
		btn_limit3.setText(" 기간 제한 : 한 ID당");
		
		txt_limit3_1 = new Text(composite_58, SWT.BORDER);
		GridData gd_txt_limit3_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit3_1.widthHint = 100;
		txt_limit3_1.setLayoutData(gd_txt_limit3_1);
		
		lblNewLabel_86 = new Label(composite_58, SWT.NONE);
		lblNewLabel_86.setText(" 일 동안 최대");
		
		txt_limit3_2 = new Text(composite_58, SWT.BORDER);
		GridData gd_txt_limit3_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit3_2.widthHint = 100;
		txt_limit3_2.setLayoutData(gd_txt_limit3_2);
		
		lblNewLabel_87 = new Label(composite_58, SWT.NONE);
		lblNewLabel_87.setText("개 까지 구매가능합니다. (기간은 최대");
		
		lblNewLabel_88 = new Label(composite_58, SWT.NONE);
		lblNewLabel_88.setText(" 30일까지 ");
		lblNewLabel_88.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_89 = new Label(composite_58, SWT.NONE);
		lblNewLabel_89.setText("이며, 비회원은 본인인증 값에 따라 제한합니다. )");
		
		lblNewLabel_77 = new Label(group_2, SWT.NONE);
		lblNewLabel_77.setText("   사은품 정보");
		
		composite_54 = new Composite(group_2, SWT.NONE);
		composite_54.setLayout(new GridLayout(5, false));
		composite_54.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_78 = new Label(composite_54, SWT.NONE);
		lblNewLabel_78.setText("사은품명");
		
		txt_giftnm = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_giftnm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_txt_giftnm.widthHint = 300;
		txt_giftnm.setLayoutData(gd_txt_giftnm);
		
		lblNewLabel_79 = new Label(composite_54, SWT.NONE);
		lblNewLabel_79.setText("기간선택");
		
		txt_giftperiodst = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_giftperiodst = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_giftperiodst.widthHint = 100;
		txt_giftperiodst.setLayoutData(gd_txt_giftperiodst);
		txt_giftperiodst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_giftperiodst);
				calander.open();
			}
		});
		
		lblNewLabel_83 = new Label(composite_54, SWT.NONE);
		lblNewLabel_83.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_83.setText(" ~ ");
		
		txt_giftperioden = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_giftperioden = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_giftperioden.widthHint = 100;
		txt_giftperioden.setLayoutData(gd_txt_giftperioden);
		txt_giftperioden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_giftperioden);
				calander.open();
			}
		});
		
		btn_giftperioddel = new Button(composite_54, SWT.NONE);
		btn_giftperioddel.setText("삭제");
		btn_giftperioddel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_giftperiodst.setText("");
				txt_giftperioden.setText("");
			}
		});
		
		lblNewLabel_80 = new Label(composite_54, SWT.NONE);
		lblNewLabel_80.setText("사은품 정보");
		
		txt_gift = new Text(composite_54, SWT.BORDER | SWT.MULTI);
		GridData gd_txt_gift = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_txt_gift.heightHint = 50;
		gd_txt_gift.widthHint = 400;
		txt_gift.setLayoutData(gd_txt_gift);
		
		lblNewLabel_81 = new Label(composite_54, SWT.NONE);
		lblNewLabel_81.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1));
		lblNewLabel_81.setText("(기간 선택을 통해 사은품 제공 기간을 설정할 수 있습니다.)");
		
		lblNewLabel_82 = new Label(composite_54, SWT.NONE);
		lblNewLabel_82.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1));
		lblNewLabel_82.setText("(실제 사은품이 아닌 내용(무료배송, 당일배송 등) 적발 시 상품 판매 금지 조치 등의 불이익을 받을 수 있습니다.)");
		
		lblNewLabel_76 = new Label(group_2, SWT.NONE);
		lblNewLabel_76.setText("   배송정보 템플릿 목록");
		
		composite_53 = new Composite(group_2, SWT.NONE);
		composite_53.setLayout(new GridLayout(2, false));
		composite_53.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_exptemplate = new Text(composite_53, SWT.BORDER);
		GridData gd_txt_exptemplate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_exptemplate.widthHint = 600;
		txt_exptemplate.setLayoutData(gd_txt_exptemplate);
		
		btn_exptempsearch = new Button(composite_53, SWT.NONE);
		btn_exptempsearch.setText("검색");
		
		lblNewLabel_75 = new Label(group_2, SWT.NONE);
		lblNewLabel_75.setText("   배송가능지역");
		
		composite_52 = new Composite(group_2, SWT.NONE);
		composite_52.setLayout(new GridLayout(1, false));
		composite_52.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_exparea = new Combo(composite_52, SWT.NONE);
		cb_exparea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_exparea.setItems(new String[] {"사방넷상품정보 참조", "전국", "전국(제주 도서산간지역 제외)", "서울", "인천","광주", "대구", "대전","부산", "울산", "경기","강원", "충남", "충북","경남", "경북","전남", "전북", "제주","서울/경기", 
				"서울/경기/대전", "충북/충남","경북/경남","전북/전남", "부산/울산","서울/경기/제주도서산간 제외","일부지역불가"});
		cb_exparea.select(0);
		
		lblNewLabel_74 = new Label(group_2, SWT.NONE);
		lblNewLabel_74.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_74.setText("* 방문수령 추가 항목에 체크 할 경우 방문수령 주소를 입력해 주시기 바랍니다. 방문수령 추가 항목 체크시 방문수령 주소는 필수값입니다.");
		lblNewLabel_74.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_73 = new Label(group_2, SWT.NONE);
		lblNewLabel_73.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_73.setText("* [단일상품]방문수령 미지원(API 방문수령 미지원)");
		lblNewLabel_73.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_21 = new Label(group_2, SWT.NONE);
		label_21.setText("※ 배송방법");
		
		Composite composite_8 = new Composite(group_2, SWT.NONE);
		composite_8.setLayout(new GridLayout(2, false));
		
		cb_world = new Combo(composite_8, SWT.NONE);
		cb_world.setItems(new String[] {"택배", "우편(등기/소포)", "직접전달", "퀵서비스", "배송필요없음"});
		cb_world.select(0);
		
		btn_world = new Button(composite_8, SWT.CHECK);
		btn_world.setText("  방문수령 추가");
		
		label_2 = new Label(group_2, SWT.NONE);
		label_2.setText("   발송택배사");
		
		composite_51 = new Composite(group_2, SWT.NONE);
		composite_51.setLayout(new GridLayout(2, false));
		composite_51.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_exp = new Combo(composite_51, SWT.NONE);
		cb_exp.setItems(new String[] {"선택하세요"});
		cb_exp.select(0);
		scb_express = new ShopCombo(cb_exp);
		
		lblNewLabel_72 = new Label(composite_51, SWT.NONE);
		lblNewLabel_72.setText("	* 배송방법이 택배일 경우 필수로 선택하여 주세요");
		lblNewLabel_72.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_71 = new Label(group_2, SWT.NONE);
		lblNewLabel_71.setText("   발송예정일");
		
		composite_50 = new Composite(group_2, SWT.NONE);
		composite_50.setLayout(new GridLayout(3, false));
		composite_50.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_shipmentdate = new Text(composite_50, SWT.BORDER);
		GridData gd_txt_shipmentdate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_shipmentdate.widthHint = 600;
		txt_shipmentdate.setLayoutData(gd_txt_shipmentdate);
		
		btn_shipmentsearch = new Button(composite_50, SWT.NONE);
		btn_shipmentsearch.setText("검색");
		btn_shipmentsearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openShipmentdate();
			}
		});
		
		btn_shipmentdel = new Button(composite_50, SWT.NONE);
		btn_shipmentdel.setText("삭제");
		btn_shipmentdel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_shipmentdate.setText("");

			}
		});
		lblNewLabel_70 = new Label(group_2, SWT.NONE);
		lblNewLabel_70.setText("   방문수령 주소");
		
		composite_49 = new Composite(group_2, SWT.NONE);
		composite_49.setLayout(new GridLayout(3, false));
		composite_49.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_visit = new Text(composite_49, SWT.BORDER);
		GridData gd_txt_visit = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_visit.widthHint = 600;
		txt_visit.setLayoutData(gd_txt_visit);
		
		btn_visitsearch = new Button(composite_49, SWT.NONE);
		btn_visitsearch.setText("검색");
		
		btn_visitdel = new Button(composite_49, SWT.NONE);
		btn_visitdel.setText("삭제");
		
		label_26 = new Label(group_2, SWT.NONE);
		label_26.setText("   전세계배송이용여부");
		
		composite_20 = new Composite(group_2, SWT.NONE);
		composite_20.setLayout(new GridLayout(2, false));
		
		btn_worldyes = new Button(composite_20, SWT.RADIO);
		btn_worldyes.setText("사용함");
		
		btn_worldno = new Button(composite_20, SWT.RADIO);
		btn_worldno.setText("사용안함");
		
		label_27 = new Label(group_2, SWT.NONE);
		label_27.setText("   전세계배송시상품무게");
		
		composite_21 = new Composite(group_2, SWT.NONE);
		composite_21.setLayout(new GridLayout(2, false));
		
		btn_weight = new Button(composite_21, SWT.RADIO);
		btn_weight.setText("상품의 속성정보");
		
		btn_weight300 = new Button(composite_21, SWT.RADIO);
		btn_weight300.setText("기본무게(300g)");	
		
		composite_47 = new Composite(group_2, SWT.NONE);
		composite_47.setLayout(new GridLayout(1, false));
		composite_47.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		label_28 = new Label(composite_47, SWT.NONE);
		label_28.setText("   관.부가세 H.S코드");
		
		lblNewLabel_64 = new Label(composite_47, SWT.NONE);
		lblNewLabel_64.setText("   (해외직구 카테고리)");
		lblNewLabel_64.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		composite_48 = new Composite(group_2, SWT.NONE);
		composite_48.setLayout(new GridLayout(1, false));
		composite_48.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_hscode = new Combo(composite_48, SWT.NONE);
		cb_hscode.setItems(new String[] {"선택하세요","01류-각종식품","3300류-화장품","3100류-향수","34류-비누류","39류-플라스틱제품류","40류-고무제품류","42류-[EU:가방류_합성피혁(폴리염화비닐,기타)]","42류-[EU:지갑류_합성피혁(폴리우레탄)]"
				,"42류-[EU및EU외:벨트_가죽]","42류-[미국:가방/지갑][EU:가방류_가죽,천,합성피혁(폴리우레탄)][EU외:가방류_가죽,천,합성피혁]","42류-[미국:가죽제품][EU:지값류_가죽,천,합성피혁(폴리염화비닐,기타)][EU외:지갑류_가죽,천,합성피혁]","42류-[EU및EU외:키홀더_지갑과비슷한소재]"
				,"48류-위생용품류","48류-지제제품류","48류-화장지/주방용기류","4900류-도서용품","4910류-서적/인쇄물류","55류-직물매트류","61류-양말류","61류-의류","61류-장갑류","62류-[미국:의류부속품][EU및EU외:의류_의류,넥타이,스카프]","62류-직물제품류","63류-직물제품"
				,"64류-[미국:신발류][EU및EU외:신발류_가죽,섬유]","65류-모자","66류-우산","71류-[미국:주얼리][EU및EU외:주얼리_귀걸이,반지,목걸이]","73류-철제주방용품","84류-가전 및 기계용품","85류-음악/영화용 CD류","87류-유모차류","87류-자동차용품","87류-자전거","90류-안경"
				,"91류-시계","9400류-가구/인테리어","9410류-침구/커튼/장식/소모품","9420류-가구/조명기기류","94류-보행기류","94류-의자류","95류-완류/낚시용품","95류-운동용구","96류-라이터"});
		cb_hscode.select(0);
		
		lblNewLabel_65 = new Label(composite_48, SWT.NONE);
		lblNewLabel_65.setText("관세청에 신고되는 H.S Code입니다.");
		
		lblNewLabel_66 = new Label(composite_48, SWT.NONE);
		lblNewLabel_66.setText("해외통합배송상품과 이태리 직배송상품인 경우만 설정 필요.");
		
		lblNewLabel_67 = new Label(composite_48, SWT.NONE);
		lblNewLabel_67.setText("상품신규등록시, 해당 카테고리에 셋팅된 HSCode가 디폴트 세팅 됩니다.");
		
		lblNewLabel_68 = new Label(composite_48, SWT.NONE);
		lblNewLabel_68.setText("그러나 해당카테고리에 셋팅된 HSCode와 상품의 성격이 다른 경우, 상품에 맞는 HSCode를 선택하셔야하며");
		
		lblNewLabel_69 = new Label(composite_48, SWT.NONE);
		lblNewLabel_69.setText("잘못 등록하여 문제가 발생할 경우 셀러분께서 해결하셔야 합니다.(예: 통관이 지연됨, 관세추가징수등)");
		
		lblNewLabel_63 = new Label(group_2, SWT.NONE);
		lblNewLabel_63.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_63.setText("* 출고지 및 교환/반품지 주소는 상품송신 전, 반드시 11번가 SCM 상품등록화면에서 1개 이상 등록해야합니다.");
		lblNewLabel_63.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_62 = new Label(group_2, SWT.NONE);
		lblNewLabel_62.setText("   출고지 주소 해외 여부");
		
		composite_46 = new Composite(group_2, SWT.NONE);
		composite_46.setLayout(new GridLayout(2, false));
		composite_46.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_outaddr1 = new Button(composite_46, SWT.RADIO);
		btn_outaddr1.setText("국내	");

		btn_outaddr2 = new Button(composite_46, SWT.RADIO);
		btn_outaddr2.setText("해외");
		
		label_29 = new Label(group_2, SWT.NONE);
		label_29.setText("※ 출고지 주소");
		
		composite_28 = new Composite(group_2, SWT.NONE);
		composite_28.setLayout(new GridLayout(2, false));
		GridData gd_composite_28 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_28.widthHint = 679;
		gd_composite_28.heightHint = 35;
		composite_28.setLayoutData(gd_composite_28);
		
		txt_factaddr = new Text(composite_28, SWT.BORDER);
		GridData gd_txt_factaddr = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_factaddr.widthHint = 600;
		txt_factaddr.setLayoutData(gd_txt_factaddr);
		
		btn_factaddr = new Button(composite_28, SWT.NONE);
		btn_factaddr.setText("검색");
		btn_factaddr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getFactoryAddress(true);
			}							
		});
		
		lblNewLabel_61 = new Label(group_2, SWT.NONE);
		lblNewLabel_61.setText("   교환/반품지 주소 해외 여부");
		
		composite_45 = new Composite(group_2, SWT.NONE);
		composite_45.setLayout(new GridLayout(2, false));
		composite_45.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_cngNretaddr1 = new Button(composite_45, SWT.RADIO);
		btn_cngNretaddr1.setText("국내	");

		btn_cngNretaddr2 = new Button(composite_45, SWT.RADIO);
		btn_cngNretaddr2.setText("해외");
		
		label_30 = new Label(group_2, SWT.NONE);
		label_30.setText("※ 교환/반품지 주소");
		
		composite_29 = new Composite(group_2, SWT.NONE);
		composite_29.setLayout(new GridLayout(2, false));
		GridData gd_composite_29 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_29.heightHint = 35;
		composite_29.setLayoutData(gd_composite_29);
		
		txt_chanaddr = new Text(composite_29, SWT.BORDER);
		GridData gd_txt_chanaddr = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_chanaddr.widthHint = 600;
		txt_chanaddr.setLayoutData(gd_txt_chanaddr);
		btn_rtnaddr = new Button(composite_29, SWT.NONE);
		btn_rtnaddr.setText("검색");
		btn_rtnaddr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getFactoryAddress(false);
			}							
		});
		
		lblNewLabel_60 = new Label(group_2, SWT.NONE);
		lblNewLabel_60.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_60.setText("* 배송비 설정중 판매자 조건부 무료 선택시, 11번가 SCM > 판매정보설정 화면에서 판매자 조건부 무료 내용이 설정되어 있는지 확인하셔야 합니다.");
		lblNewLabel_60.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		Composite composite = new Composite(group_2, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 17, 1);
		gd_composite.heightHint = 294;
		composite.setLayoutData(gd_composite);
		
		lblNewLabel_59 = new Label(composite, SWT.NONE);
		lblNewLabel_59.setText("※ 배송비 설정");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		
		composite_2 = new Composite(composite_1, SWT.BORDER);
		composite_2.setBounds(0, 5, 192, 245);
		
		btn_free = new Button(composite_2, SWT.RADIO);
		btn_free.setText("무료");
		btn_free.setBounds(5, 5, 50, 20);
		
		btn_condifree = new Button(composite_2, SWT.RADIO);
		btn_condifree.setBounds(5, 83, 140, 20);
		btn_condifree.setText("상품 조건부 무료");
		
		btn_fixing = new Button(composite_2, SWT.RADIO);
		btn_fixing.setBounds(5, 216, 130, 20);
		btn_fixing.setText("고정 배송비");
		
		btn_seller = new Button(composite_2, SWT.RADIO);
		btn_seller.setText("판매자 조건부 배송비");
		btn_seller.setBounds(5, 29, 180, 20);
		
		btn_factory = new Button(composite_2, SWT.RADIO);
		btn_factory.setText("출고지 조건부 배송비");
		btn_factory.setBounds(5, 55, 180, 20);
		
		btn_differ = new Button(composite_2, SWT.RADIO);
		btn_differ.setText("수량별 차등");
		btn_differ.setBounds(5, 130, 130, 20);
		
		btn_one = new Button(composite_2, SWT.RADIO);
		btn_one.setText("1개당 배송비");
		btn_one.setBounds(5, 185, 130, 20);
		
		composite_10 = new Composite(composite_1, SWT.BORDER);
		composite_10.setBounds(0, 248, 1154, 35);
		
		txt_jeju = new Text(composite_10, SWT.BORDER);
		txt_jeju.setBounds(50, 3, 159, 24);
		
		txt_island = new Text(composite_10, SWT.BORDER);
		txt_island.setBounds(370, 3, 159, 24);
		
		label_11 = new Label(composite_10, SWT.NONE);
		label_11.setText("제주");
		label_11.setBounds(5, 5, 40, 20);
		
		label_12 = new Label(composite_10, SWT.NONE);
		label_12.setText("원");
		label_12.setBounds(212, 5, 20, 20);
		
		label_13 = new Label(composite_10, SWT.NONE);
		label_13.setText("도서산간");
		label_13.setBounds(290, 5, 70, 20);
		
		label_14 = new Label(composite_10, SWT.NONE);
		label_14.setText("원");
		label_14.setBounds(532, 5, 20, 20);
		
		Composite composite_9 = new Composite(composite_1, SWT.BORDER);
		composite_9.setBounds(190, 117, 964, 67);
		
		label_40 = new Label(composite_9, SWT.NONE);
		label_40.setBounds(5, 7, 50, 20);
		label_40.setText("1개 ~");
		
		txt_diffqty = new Text(composite_9, SWT.BORDER);
		txt_diffqty.setBounds(55, 5, 80, 24);
		
		label_41 = new Label(composite_9, SWT.NONE);
		label_41.setBounds(138, 7, 20, 20);
		label_41.setText("개");
		
		txt_diffprice = new Text(composite_9, SWT.BORDER);
		txt_diffprice.setBounds(158, 5, 150, 24);
		
		label_42 = new Label(composite_9, SWT.NONE);
		label_42.setBounds(311, 7, 20, 20);
		label_42.setText("원");
		
		label_44 = new Label(composite_9, SWT.NONE);
		label_44.setBounds(301, 36, 20, 20);
		label_44.setText("\uC6D0");
		
		txt_diffprice1 = new Text(composite_9, SWT.BORDER);
		txt_diffprice1.setBounds(148, 34, 150, 24);
		
		label_43 = new Label(composite_9, SWT.NONE);
		label_43.setBounds(88, 36, 60, 20);
		label_43.setText("개 이상");
		
		txt_diffqty1 = new Text(composite_9, SWT.BORDER);
		txt_diffqty1.setBounds(5, 34, 80, 24);
		
		Composite composite_22 = new Composite(composite_1, SWT.BORDER);
		composite_22.setBounds(190, 5, 964, 27);
		
		label_37 = new Label(composite_22, SWT.NONE);
		label_37.setBounds(5, 2, 100, 20);
		label_37.setText("0 원");
		
		Label lblNewLabel = new Label(composite_22, SWT.NONE);
		lblNewLabel.setBounds(230, 2, 570, 20);
		lblNewLabel.setText("수량/주문금액에 상관없이 무조건 무료");
		
		Composite composite_24 = new Composite(composite_1, SWT.BORDER);
		composite_24.setBounds(190, 31, 964, 26);
		
		label_38 = new Label(composite_24, SWT.NONE);
		label_38.setBounds(5, 2, 100, 20);
		label_38.setText("-- 원");
		
		lblNewLabel_1 = new Label(composite_24, SWT.NONE);
		lblNewLabel_1.setBounds(230, 1, 570, 20);
		lblNewLabel_1.setText("(11번가 SCM > 판매정보설정 > 기본 배송비 정책 설정내용)");
		
		Composite composite_23 = new Composite(composite_1, SWT.BORDER);
		composite_23.setBounds(190, 56, 964, 26);
		
		label_39 = new Label(composite_23, SWT.NONE);
		label_39.setBounds(5, 2, 100, 20);
		label_39.setText("-- 원");
		
		label_46 = new Label(composite_23, SWT.NONE);
		label_46.setText("(11번가 SCM > 상품등록화면 > 출고지 조건부 배송비 정책 설정내용)");
		label_46.setBounds(230, 1, 570, 20);
		
		Composite composite_25 = new Composite(composite_1, SWT.BORDER);
		composite_25.setBounds(190, 79, 964, 40);
		
		label_9 = new Label(composite_25, SWT.NONE);
		label_9.setBounds(169, 7, 20, 20);
		label_9.setText("원");
		
		txt_condifree = new Text(composite_25, SWT.BORDER);
		txt_condifree.setBounds(5, 5, 159, 24);
		
		lblNewLabel_2 = new Label(composite_25, SWT.NONE);
		lblNewLabel_2.setBounds(230, 7, 80, 20);
		lblNewLabel_2.setText("개별상품을");
		
		label_47 = new Label(composite_25, SWT.NONE);
		label_47.setText("원 이상 주문시 무료");
		label_47.setBounds(433, 7, 150, 20);
		
		txt_condifree1 = new Text(composite_25, SWT.BORDER);
		txt_condifree1.setBounds(310, 5, 120, 24);
		
		composite_26 = new Composite(composite_1, SWT.BORDER);
		composite_26.setBounds(190, 182, 964, 35);
		
		txt_oneprice = new Text(composite_26, SWT.BORDER);
		txt_oneprice.setBounds(5, 3, 159, 24);
		
		label_45 = new Label(composite_26, SWT.NONE);
		label_45.setText("원");
		label_45.setBounds(165, 5, 20, 20);
		
		lblNewLabel_3 = new Label(composite_26, SWT.NONE);
		lblNewLabel_3.setBounds(230, 5, 570, 20);
		lblNewLabel_3.setText("수량 1개마다 배송비 추가");
		
		composite_27 = new Composite(composite_1, SWT.BORDER);
		composite_27.setBounds(190, 213, 964, 37);
		
		txt_fixing = new Text(composite_27, SWT.BORDER);
		txt_fixing.setBounds(5, 3, 159, 24);
		
		label_10 = new Label(composite_27, SWT.NONE);
		label_10.setBounds(165, 5, 20, 20);
		label_10.setText("원");
		
		lblNewLabel_4 = new Label(composite_27, SWT.NONE);
		lblNewLabel_4.setBounds(230, 5, 570, 20);
		lblNewLabel_4.setText("수량/주문금액에 상관없이 고정 배송비");

		
		label_16 = new Label(group_2, SWT.NONE);
		label_16.setBounds(5, 6, 160, 20);
		label_16.setText("※ 배송비 선결제여부");
		
		composite_16 = new Composite(group_2, SWT.NONE);
		composite_16.setBounds(3, 3, 570, 32);
		composite_16.setLayout(new GridLayout(1, false));
		
		cb_prepay = new Combo(composite_16, SWT.NONE);
		cb_prepay.setItems(new String[] {"선결제가능", "선결제불가", "선결제필수"});
		cb_prepay.select(0);
		
		label_17 = new Label(group_2, SWT.NONE);
		label_17.setBounds(5, 6, 160, 20);
		label_17.setText("   묶음배송");
		
		composite_17 = new Composite(group_2, SWT.NONE);
		composite_17.setBounds(3, 41, 570, 32);
		composite_17.setLayout(new GridLayout(2, false));
		
		btn_bundleyse = new Button(composite_17, SWT.RADIO);
		btn_bundleyse.setText("가능");
		
		btn_bundleno = new Button(composite_17, SWT.RADIO);
		btn_bundleno.setText("불가능");

		
		lblNewLabel_55 = new Label(group_2, SWT.NONE);
		lblNewLabel_55.setText("※ 교환/반품 배송비");
		
		composite_44 = new Composite(group_2, SWT.NONE);
		composite_44.setLayout(new GridLayout(7, false));
		composite_44.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_56 = new Label(composite_44, SWT.NONE);
		lblNewLabel_56.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_56.setText("반품 배송비 : 편도 ");
		
		txt_oneway = new Text(composite_44, SWT.BORDER);
		txt_oneway.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_57 = new Label(composite_44, SWT.NONE);
		lblNewLabel_57.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_57.setText("  원 /   교환 배송비 : 왕복 ");
		
		txt_round = new Text(composite_44, SWT.BORDER);
		txt_round.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_58 = new Label(composite_44, SWT.NONE);
		lblNewLabel_58.setText("  원   ▶ 초기배송비 무료시 반품 배송비 부과방법  ");
		
		btn_roundyes = new Button(composite_44, SWT.RADIO);
		btn_roundyes.setText("왕복(편도*2)");
		
		btn_roundno = new Button(composite_44, SWT.RADIO);
		btn_roundno.setText("편도");
		
		label_33 = new Label(group_2, SWT.NONE);
		label_33.setText("※ A/S안내");
		
		txt_as = new Text(group_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_txt_as = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_as.heightHint = 50;
		gd_txt_as.widthHint = 700;
		txt_as.setLayoutData(gd_txt_as);
		
		Label label_34 = new Label(group_2, SWT.NONE);
		label_34.setText("※ 교환/반품안내");
		
		txt_change = new Text(group_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_txt_change = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_change.heightHint = 50;
		gd_txt_change.widthHint = 700;
		txt_change.setLayoutData(gd_txt_change);
		
		composite_43 = new Composite(group_2, SWT.NONE);
		composite_43.setLayout(new GridLayout(2, false));
		composite_43.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_52 = new Label(composite_43, SWT.NONE);
		lblNewLabel_52.setText("  복수구매할인");
		
		btn_multidiscnt = new Button(composite_43, SWT.CHECK);
		
		composite_42 = new Composite(group_2, SWT.NONE);
		composite_42.setLayout(new GridLayout(6, false));
		composite_42.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_multidiscntcnt = new Combo(composite_42, SWT.NONE);
		cb_multidiscntcnt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_multidiscntcnt.setItems(new String[] {"수량기준","금액기준"});
		cb_multidiscntcnt.select(0);
		
		txt_multidiscntcnt = new Text(composite_42, SWT.BORDER);
		GridData gd_txt_multidiscntcnt = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_multidiscntcnt.widthHint = 100;
		txt_multidiscntcnt.setLayoutData(gd_txt_multidiscntcnt);
		
		lblNewLabel_53 = new Label(composite_42, SWT.NONE);
		lblNewLabel_53.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_53.setText("  이상 구매시 판매가(+옵션가)에서 개당  ");
		
		txt_multidiscntwon = new Text(composite_42, SWT.BORDER);
		GridData gd_txt_multidiscntwon = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_multidiscntwon.widthHint = 100;
		txt_multidiscntwon.setLayoutData(gd_txt_multidiscntwon);
		
		cb_multidiscntwon = new Combo(composite_42, SWT.NONE);
		cb_multidiscntwon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_multidiscntwon.setItems(new String[] {"원","%"});
		cb_multidiscntwon.select(0);
		
		lblNewLabel_54 = new Label(composite_42, SWT.NONE);
		lblNewLabel_54.setText("할인");
		
		lblNewLabel_51 = new Label(group_2, SWT.NONE);
		lblNewLabel_51.setText("   구입처선택");
		
		composite_41 = new Composite(group_2, SWT.NONE);
		composite_41.setLayout(new GridLayout(1, false));
		composite_41.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_buy = new Combo(composite_41, SWT.NONE);
		GridData gd_cb_buy = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cb_buy.widthHint = 340;
		cb_buy.setLayoutData(gd_cb_buy);
		cb_buy.setItems(new String[] {"사용안함","A : 해당 브랜드 정식 도매","B : 해당 브랜드 직영 온,오프라인 매장(백화점포함)","C : 오프라인 아울렛","D : 현지 온라인 쇼핑몰","E : A~D에 해당되지 않는 구입처(경매 등)"});
		cb_buy.select(0);
		
		lblNewLabel_50 = new Label(group_2, SWT.NONE);
		lblNewLabel_50.setText("   대표이미지 선택");
		
		composite_40 = new Composite(group_2, SWT.NONE);
		composite_40.setLayout(new GridLayout(1, false));
		composite_40.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_repimg = new Combo(composite_40, SWT.NONE);
		cb_repimg.setItems(new String[] {"선택하세요","부가이미지6","부가이미지7","부가이미지8","부가이미지9","부가이미지10","부가이미지11","부가이미지12","부가이미지13","부가이미지14","부가이미지15","부가이미지16","부가이미지17",
				"부가이미지18","부가이미지19","부가이미지20","부가이미지21","부가이미지22"});
		cb_repimg.select(0);
		
		lblNewLabel_48 = new Label(group_2, SWT.NONE);
		lblNewLabel_48.setText("   모바일 상세설명 이미지 선택");
		
		composite_39 = new Composite(group_2, SWT.NONE);
		composite_39.setLayout(new GridLayout(2, false));
		composite_39.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_mobileimg = new Combo(composite_39, SWT.NONE);
		cb_mobileimg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_mobileimg.setItems(new String[] {"선택하세요","부가이미지6","부가이미지7","부가이미지8","부가이미지9","부가이미지10","부가이미지11","부가이미지12","부가이미지13","부가이미지14","부가이미지15","부가이미지16","부가이미지17",
				"부가이미지18","부가이미지19","부가이미지20","부가이미지21","부가이미지22"});
		cb_mobileimg.select(0);
		
		lblNewLabel_49 = new Label(composite_39, SWT.NONE);
		lblNewLabel_49.setText("   (가로780pixel, 1.5MB 미만)");
		
		lblNewLabel_46 = new Label(group_2, SWT.NONE);
		lblNewLabel_46.setText("   모바일 쿠폰 선택 여부");
		
		composite_8 = new Composite(group_2, SWT.NONE);
		composite_8.setLayout(new GridLayout(2, false));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_mobile = new Button(composite_8, SWT.CHECK);
		btn_mobile.setText("모바일 쿠폰");
		
		lblNewLabel_47 = new Label(composite_8, SWT.NONE);
		lblNewLabel_47.setText("	(* 체크시 상품정보제공 유형이 모바일 쿠푠으로 선택됩니다.)");
		lblNewLabel_47.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_44 = new Label(group_2, SWT.NONE);
		lblNewLabel_44.setText("   해외구매대행 상품");
		
		composite = new Composite(group_2, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_overseasN = new Button(composite, SWT.RADIO);
		btn_overseasN.setText("일반상품");
		
		btn_overseasY = new Button(composite, SWT.RADIO);
		btn_overseasY.setText("해외구매대행상품");
		
		lblNewLabel_45 = new Label(composite, SWT.NONE);
		lblNewLabel_45.setText("	* SCM에서 해외구매대행 항목을 선택할 수 있는 업체만 사용 가능합니다.");
		lblNewLabel_45.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_43 = new Label(group_2, SWT.NONE);
		lblNewLabel_43.setText("   상품명 추가 앞문구");
		
		txt_start = new Text(group_2, SWT.BORDER);
		GridData gd_txt_start = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_start.widthHint = 700;
		txt_start.setLayoutData(gd_txt_start);
		
		lblNewLabel_42 = new Label(group_2, SWT.NONE);
		lblNewLabel_42.setText("   상품명 추가 뒷문구");
		
		txt_end = new Text(group_2, SWT.BORDER);
		GridData gd_txt_end = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_end.widthHint = 700;
		txt_end.setLayoutData(gd_txt_end);
		
		lblNewLabel_41 = new Label(group_2, SWT.NONE);
		lblNewLabel_41.setText("   상품설명 상단추가 문구");
		
		txt_top = new Text(group_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_top = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_top.heightHint = 70;
		gd_txt_top.widthHint = 700;
		txt_top.setLayoutData(gd_txt_top);
		
		lblNewLabel_40 = new Label(group_2, SWT.NONE);
		lblNewLabel_40.setText("   상품설명 하단추가 문구");
		
		txt_bottom = new Text(group_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_bottom = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_bottom.heightHint = 70;
		gd_txt_bottom.widthHint = 700;
		txt_bottom.setLayoutData(gd_txt_bottom);
		
		group = new Group(container, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		group.setText("■ [유료 및 부가서비스]");
		
		composite_36 = new Composite(group, SWT.NONE);
		composite_36.setLayout(new GridLayout(2, false));
		composite_36.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_33 = new Label(composite_36, SWT.NONE);
		lblNewLabel_33.setText("기본즉시할인");
		
		btn_dasicdiscount = new Button(composite_36, SWT.CHECK);
		
		composite_35 = new Composite(group, SWT.NONE);
		composite_35.setLayout(new GridLayout(8, false));
		composite_35.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_35 = new Label(composite_35, SWT.NONE);
		lblNewLabel_35.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_35.setText("판매가에서");
		
		txt_dasicdiscount = new Text(composite_35, SWT.BORDER);
		GridData gd_txt_dasicdiscount = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_dasicdiscount.widthHint = 100;
		txt_dasicdiscount.setLayoutData(gd_txt_dasicdiscount);
		
		cb_dasicdiscount = new Combo(composite_35, SWT.NONE);
		cb_dasicdiscount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_dasicdiscount.setItems(new String[] {"원", "%"});
		cb_dasicdiscount.select(0);
		
		ghfdghfdh = new Label(composite_35, SWT.NONE);
		ghfdghfdh.setText("할인  ");
		
		btn_dasicdiscountcoupone = new Button(composite_35, SWT.CHECK);
		btn_dasicdiscountcoupone.setText(" 쿠폰 지급기간 설정");
		
		lblNewLabel_36 = new Label(composite_35, SWT.NONE);
		lblNewLabel_36.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_36.setText("	오늘부터 ~ ");
		
		txt_dasicdiscountdate = new Text(composite_35, SWT.BORDER);
		GridData gd_txt_dasicdiscountdate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_dasicdiscountdate.widthHint = 100;
		txt_dasicdiscountdate.setLayoutData(gd_txt_dasicdiscountdate);
		txt_dasicdiscountdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_dasicdiscountdate);
				calander.open();
			}
		});
		
		btn_dasicdiscountdel = new Button(composite_35, SWT.NONE);
		btn_dasicdiscountdel.setText("삭제");
		btn_dasicdiscountdel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_dasicdiscountdate.setText("");
			}
		});
		
		composite_22 = new Composite(group, SWT.NONE);
		composite_22.setLayout(new GridLayout(2, false));
		composite_22.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_8 = new Label(composite_22, SWT.NONE);
		lblNewLabel_8.setText("무이자 할부 제공");
		
		btn_interestfee = new Button(composite_22, SWT.CHECK);
		btn_interestfee.setText("		");
		
		composite_9 = new Composite(group, SWT.NONE);
		composite_9.setLayout(new GridLayout(4, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_interestfee = new Combo(composite_9, SWT.NONE);
		GridData gd_cb_interestfee = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_interestfee.widthHint = 80;
		cb_interestfee.setLayoutData(gd_cb_interestfee);
		cb_interestfee.setItems(new String[] {"2개월", "3개월","4개월","5개월","6개월","7개월","8개월","9개월","10개월","11개월","12개월",});
		cb_interestfee.select(0);
		
		Label lblNewLabel_9 = new Label(composite_9, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText(" (횟수제한 : ");
		
		txt_interestfee = new Text(composite_9, SWT.BORDER);
		txt_interestfee.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel_10 = new Label(composite_9, SWT.NONE);
		lblNewLabel_10.setText(" 회)");
		
		composite_23 = new Composite(group, SWT.NONE);
		composite_23.setLayout(new GridLayout(2, false));
		composite_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_6 = new Label(composite_23, SWT.NONE);
		lblNewLabel_6.setText("희망후원 설정");
		
		btn_support = new Button(composite_23, SWT.CHECK);
		btn_support.setText("		");
		
		composite_1 = new Composite(group, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_7 = new Label(composite_1, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("판매가에서");
		
		txt_support = new Text(composite_1, SWT.BORDER);
		GridData gd_txt_support = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_support.widthHint = 100;
		txt_support.setLayoutData(gd_txt_support);
		
		cb_support = new Combo(composite_1, SWT.NONE);
		GridData gd_cb_support = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_support.widthHint = 30;
		cb_support.setLayoutData(gd_cb_support);
		cb_support.setItems(new String[] {"원", "%"});
		cb_support.select(0);
		
		composite_24 = new Composite(group, SWT.NONE);
		composite_24.setLayout(new GridLayout(2, false));
		composite_24.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel = new Label(composite_24, SWT.NONE);
		lblNewLabel.setText("SK pay point 지급");
		
		btn_skpay = new Button(composite_24, SWT.CHECK);
		btn_skpay.setText("		");
		
		Composite composite33 = new Composite(group, SWT.NONE);
		composite33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite33.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel_5 = new Label(composite33, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("판매가에서");
		
		txt_skpay = new Text(composite33, SWT.BORDER);
		GridData gd_txt_skpay = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_skpay.widthHint = 100;
		txt_skpay.setLayoutData(gd_txt_skpay);
		
		cb_skpay = new Combo(composite33, SWT.NONE);
		GridData gd_cb_skpay = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cb_skpay.widthHint = 30;
		cb_skpay.setLayoutData(gd_cb_skpay);
		cb_skpay.setItems(new String[] {"원", "%"});
		cb_skpay.select(0);
		
		composite_37 = new Composite(group, SWT.NONE);
		composite_37.setLayout(new GridLayout(1, false));
		composite_37.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_34 = new Label(composite_37, SWT.NONE);
		lblNewLabel_34.setText("상품리뷰");
		
		composite_38 = new Composite(group, SWT.NONE);
		composite_38.setLayout(new GridLayout(2, false));
		composite_38.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_prodreviewN = new Button(composite_38, SWT.RADIO);
		btn_prodreviewN.setText("노출안함");
		
		btn_prodreviewY = new Button(composite_38, SWT.RADIO);
		btn_prodreviewY.setText("노출함");
		
		label_51 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_51.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		lblNewLabel_27 = new Label(group, SWT.NONE);
		lblNewLabel_27.setText("리스팅광고\n아이템이란?");
		
		composite_34 = new Composite(group, SWT.NONE);
		composite_34.setLayout(new GridLayout(1, false));
		composite_34.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_28 = new Label(composite_34, SWT.NONE);
		lblNewLabel_28.setText("- 상품 리스팅 영역에서 내 상품의 가독성을 높여주는 유료광고 아이템입니다.");
		lblNewLabel_28.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_29 = new Label(composite_34, SWT.NONE);
		lblNewLabel_29.setText("- 리스팅광고 아이템에는 플러스, 플러스UP, GIF이미지, 볼드체, 배경색의 5가지 상품이 있습니다.");
		lblNewLabel_29.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_30 = new Label(composite_34, SWT.NONE);
		lblNewLabel_30.setText("1) 리스팅 상단 노출을 도와주는 아이템 : 플러스, 플러스UP");
		lblNewLabel_30.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_31 = new Label(composite_34, SWT.NONE);
		lblNewLabel_31.setText("2) 내 상품의 리스팅 영역을 눈에 띄게 꾸미기 위한 아이템 : GIF이미지, 볼드체, 배경색");
		lblNewLabel_31.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_32 = new Label(composite_34, SWT.NONE);
		lblNewLabel_32.setText("아이템과 기간을 직접 선택하므로 원하는 구성의 상품을 구매하실 수 있습니다.");
		lblNewLabel_32.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		label_50 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_50.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_23 = new Label(group, SWT.NONE);
		lblNewLabel_23.setText("플러스");
		
		composite_33 = new Composite(group, SWT.NONE);
		composite_33.setLayout(new GridLayout(1, false));
		composite_33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_plus = new Button(composite_33, SWT.CHECK);
		btn_plus.setText("사용함");
		
		lblNewLabel_24 = new Label(composite_33, SWT.NONE);
		lblNewLabel_24.setText("- 상품 리스트 상단에 우선 노출될 수 있도록 도와주는 아이템입니다.");
		
		lblNewLabel_25 = new Label(composite_33, SWT.NONE);
		lblNewLabel_25.setText("- 상품 이미지 좌측상단에 플러스 아이폰이 노출됩니다.");
		
		lblNewLabel_26 = new Label(composite_33, SWT.NONE);
		lblNewLabel_26.setText("- 플러스 아이템을 적용한 상품만 플러스UP 아이템을 구매하실 수 있습니다.");
		
		label_49 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_49.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_19 = new Label(group, SWT.NONE);
		lblNewLabel_19.setText("플러스 UP");
		
		composite_32 = new Composite(group, SWT.NONE);
		composite_32.setLayout(new GridLayout(1, false));
		composite_32.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_plusup = new Button(composite_32, SWT.CHECK);
		btn_plusup.setText("사용함");
		
		lblNewLabel_20 = new Label(composite_32, SWT.NONE);
		lblNewLabel_20.setText("- '11번가 랭킹순' 점수 중 판매점수에 30% 가산점이 부여되는 아이템입니다.");
		
		lblNewLabel_21 = new Label(composite_32, SWT.NONE);
		lblNewLabel_21.setText("- 상품명 상단에 플러스UP 아이콘이 노출됩니다.");
		
		lblNewLabel_22 = new Label(composite_32, SWT.NONE);
		lblNewLabel_22.setText("- 플러스 아이템을 적용한 상품만 플러스UP 아이템을 구매하실 수 있습니다.");
		
		label_48 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_48.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_15 = new Label(group, SWT.NONE);
		lblNewLabel_15.setText("GIF이미지");
		
		composite_31 = new Composite(group, SWT.NONE);
		composite_31.setLayout(new GridLayout(1, false));
		composite_31.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_gifimg = new Button(composite_31, SWT.CHECK);
		btn_gifimg.setText("사용함");
		
		lblNewLabel_16 = new Label(composite_31, SWT.NONE);
		lblNewLabel_16.setText("- 여러 장의 상품 이미지를 노출하여 내 상품의 주목도를 높일 수 있는 아이템입니다.");
		
		lblNewLabel_17 = new Label(composite_31, SWT.NONE);
		lblNewLabel_17.setText("- 셀러오피스에서 상품 이미지의 등록/수정시, GIF 형식의 이미지를 등록하신 후 'GIF이미지' 아이템을 구매하시면 이용 가능합니다.");
		
		lblNewLabel_18 = new Label(composite_31, SWT.NONE);
		lblNewLabel_18.setText("- GIF이미지는 최대 10장까지 전시 가능합니다.");
		
		label_34 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_34.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_13 = new Label(group, SWT.NONE);
		lblNewLabel_13.setText("볼드체");
		
		composite_30 = new Composite(group, SWT.NONE);
		composite_30.setLayout(new GridLayout(1, false));
		composite_30.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_bold = new Button(composite_30, SWT.CHECK);
		btn_bold.setText("사용함");
		
		lblNewLabel_14 = new Label(composite_30, SWT.NONE);
		lblNewLabel_14.setText("상품명을 굵게 표시하고 상품명 끝에 √ 아이콘을 붙여 상품명을 강조하는 아이템입니다.");
		
		label_1 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_11 = new Label(group, SWT.NONE);
		lblNewLabel_11.setText("배경색");
		
		composite_25 = new Composite(group, SWT.NONE);
		composite_25.setLayout(new GridLayout(1, false));
		composite_25.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_background = new Button(composite_25, SWT.CHECK);
		btn_background.setText("사용함");
		
		lblNewLabel_12 = new Label(composite_25, SWT.NONE);
		lblNewLabel_12.setText("해당 상품의 리스팅 영역에 노란색 배경색을 입혀 행을 강조하는 아이템입니다.");
		
		label_35 = new Label(container, SWT.NONE);
		label_35.setText("※ 사용여부");
		
		cb_whether = new Combo(container, SWT.NONE);
		cb_whether.setItems(new String[] {"사용여부", "사용중", "미사용"});
		cb_whether.select(0);
		
		//container.setSize( 1300, 2000 );
		scrolledComposite.setContent(container);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setMinSize( 1467, 1200 );
		
		scrolledComposite.addListener( SWT.Resize, event -> {
			  int width = scrolledComposite.getClientArea().width;
			  scrolledComposite.setMinSize( parent.computeSize( width, SWT.DEFAULT ));
		});
		
		setIdcheck();
		
		setDetailSetting();
		
		return scrolledComposite;
	}
	//발송예정일검색
	protected void openShipmentdate() {
		// TODO Auto-generated method stub
		
	}

	//기간설정시 화면숨김
	protected void setVisibleBenepiaPreiod() {
		if(btn_benepia2.getSelection()) {
			txt_benepiast.setEnabled(true);
			txt_benepiaen.setEnabled(true);
		}else {
			txt_benepiast.setEnabled(false);
			txt_benepiaen.setEnabled(false);
		}
		
	}

	//베네피아할인설정 숨기기
	protected void setVisibleBenepia() {
		if(btn_benepia1.getSelection()) {
			txt_benepia1_1.setEnabled(true);
			btn_benepia2.setEnabled(true);
		}else {
			txt_benepia1_1.setEnabled(false);
			btn_benepia2.setEnabled(false);
		}
		
	}

	//아이디체크
	private void setIdcheck() {
		List<CodeItem> datas =
				  datasource.stream()
				  .map( p->new CodeItem(p.getDlvID(), p.getDivNM()))
				  .collect(Collectors.toList());
			
		scb_express.setDataSource(datas);
		
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			List<List<String>> shoplist = new ArrayList<List<String>>();
			if(dto==null) {
				shoplist = dao.getShopidListCheck(list.get(0));
			}else {
				shoplist = dao.getShopidListCheck(dto.getShopcd());
			}
			
			String id= "쇼핑몰ID,";
			for(List<String> idche: shoplist) {
				id += idche.get(1)+",";
			}
			cb_idcheck.setItems(id.split(","));
			cb_idcheck.select(0);
		}catch (Exception e) {
			e.getStackTrace();
		}
			
		
		
	}

	//출고/반품주소지 가지고 오기
	protected void getFactoryAddress(boolean flag) {
		ShoppingMallDao dao = new ShoppingMallDao();
		List<List<String>> contents = dao.getFactoryAddress(flag);
		for(List<String>list : contents) {
			if(flag) {
				txt_factaddr.setText(list.get(0));
			} else {
				txt_chanaddr.setText(list.get(0));
			}			
		}		
	}
	void setidsetting(String info) {
		for (int i = 0; i < this.cb_idcheck.getItems().length; i++) {
			String type = this.cb_idcheck.getItems()[i];
			if (type.equals(info)) {
				this.cb_idcheck.setText(type);
				break;
			} 
		}
	}
	//
	private void setDetailSetting() {
		if(dto!=null) {
			txt_title.setText(dto.getTitle());//제목
			txt_memo.setText(dto.getMemo());//메모
			setidsetting(dto.getShopid());//아이디
			switch(dto.getSelmthdcd()) { //판매방식	
			case "01" : btn_fixed.setSelection(true);
				break;
			case "02" : btn_public.setSelection(true);
				break;
			case "04" : btn_reser.setSelection(true);
				break;
			case "05" : btn_used.setSelection(true);
				break;
				default : break;
			}
			cb_service.select(0);//서비스상품  이런식으로등록되는거 보니 11번가에 무조건 01로 보내는거 같음dto.setProdtypcd(cb_service.getSelectionIndex()==0?"01":""); 
			txt_prodpromotion.setText(dto.getMall_var_1());//상품홍보문구
			switch(dto.getMall_var_2()) { //원산지물품
			case "01" : btn_orgin1_1.setSelection(true);
				break;
			case "02" : btn_orgin1_2.setSelection(true);
				break;
			case "03" : btn_orgin1_3.setSelection(true);
				break;
			case "04" : btn_orgin1_4.setSelection(true);
				break;
			case "05" : btn_orgin1_5.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getMall_var_3()) { ////원산지사용여부	
			case "01" : btn_orgin2_1.setSelection(true);
				break;
			case "02" : btn_orgin2_2.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_3().equals("02")) {
				dto.setMall_var_4(txt_orgin2_1.getText()); //원산지사용여부에따른내용
			}
			switch(dto.getMall_var_5()) {//원산지다중등록여부
			case "Y" : btn_orgin3_1.setSelection(true);
				break;
			case "N" : btn_orgin3_1.setSelection(false);
				break;
				default : break;
			}
			txt_prodmd.setText(dto.getMall_var_6());//상품모델
			cb_prodedit.select(Integer.parseInt(dto.getMall_var_7()));
			cb_sellerprod.select(Integer.parseInt(dto.getMall_var_8()));
			switch(dto.getMall_var_9()) {//셀러캐시사용여부
			case "Y" : btn_sellercash.setSelection(true);
				break;
			case "N" : btn_sellercash.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getProdstatcd()) {  //상품상태
			case "01" : btn_new.setSelection(true);
				break;
			case "02" : btn_usedprod.setSelection(true);
				break;
			case "03" : btn_stock.setSelection(true);
				break;
			case "04" : btn_reaper.setSelection(true);
				break;
			case "05" : btn_exhibi.setSelection(true);
				break;
			case "07" : btn_rare.setSelection(true);
				break;
			case "08" : btn_return.setSelection(true);
				break;
			case "09" : btn_scratch.setSelection(true);
				break;
			case "10" : btn_exhibi.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getMinorselcnyn()) {//미성년자구매여부
			case "Y" : btn_minoryes.setSelection(true);
				break;
			case "N" : btn_minorno.setSelection(true);
				break;
				default : break;
			}
			txt_point.setText(dto.getMall_var_10());//지점선택
			txt_disney.setText(dto.getMall_var_11());//디즈니시리즈선택
			txt_nicknm.setText(dto.getNicknm());//닉네임
			txt_brand.setText(dto.getMall_var_12());//브랜드
			txt_phoneurl.setText(dto.getMall_var_13());//가입신청URL
			switch(dto.getMall_var_94()) {//휴대전화약정여부
			case "01" : btn_phone1.setSelection(true);
				break;
			case "02" : btn_phone2.setSelection(true);
				break;
				default : break;
			}
			txt_phone.setText(dto.getMall_var_14());//휴대폰요금제
			txt_effecdate.setText(dto.getMall_var_15());//유효일자
			switch(dto.getMall_var_16()) {//가격비교등록여부
			case "01" : btn_costcomparisY.setSelection(true);
				break;
			case "02" : btn_costcomparisN.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getFpseltermyn()) {//판매기간설정여부
			case "Y" : btn_dateyes.setSelection(true);
				break;
			case "N" : btn_dateno.setSelection(true);
				break;
				default : break;
			}
			if(dto.getFpseltermyn().equals("Y")) {
				switch(dto.getProdfpselcd()) { //판매기간코드
				case "7:103" : btn_7day.setSelection(true);
					break;
				case "15:104" : btn_15day.setSelection(true);
					break;
				case "30:105" : btn_30day.setSelection(true);
					break;
				case "60:106" : btn_60day.setSelection(true);
					break;
				case "90:107" : btn_90day.setSelection(true);
					break;
				case "120:108" : btn_120day.setSelection(true);
					break;
					default : break;
				}
			}		
			switch(dto.getMall_var_17()) {//1원폰상품여부
			case "Y" : btn_1wonphone.setSelection(true);
				break;
			case "N" : btn_1wonphone.setSelection(false);
				break;
				default : break;
			}
			cb_optsetting.select(Integer.parseInt(dto.getMall_var_18()));//옵션설정여부
			cb_2optiondefault.select(Integer.parseInt(dto.getMall_var_19())); //2단옵션적용여부
			switch(dto.getMall_var_20()) {//옵션값노출방식
			case "01" : btn_optval1.setSelection(true);
				break;
			case "02" : btn_optval2.setSelection(false);
				break;
			case "03" : btn_optval3.setSelection(false);
				break;
			case "04" : btn_optval4.setSelection(false);
				break;
			case "05" : btn_optval5.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_21()) {//작성형옵션여부
			case "Y" : btn_optionY.setSelection(true);
				break;
			case "N" : btn_optionN.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_21().equals("Y")) {
				txt_option1.setText(dto.getMall_var_22()); //옵션명1
				txt_option2.setText(dto.getMall_var_23()); //옵션명2
				txt_option3.setText(dto.getMall_var_24());//옵션명3
				txt_option4.setText(dto.getMall_var_25()); //옵션명4
				txt_option5.setText(dto.getMall_var_26());//옵션명5
				txt_option6.setText(dto.getMall_var_27());//옵션명6
				txt_option7.setText(dto.getMall_var_28());//옵션명7
				txt_option8.setText(dto.getMall_var_29());//옵션명8
				txt_option9.setText(dto.getMall_var_30());//옵션명9
				txt_option10.setText(dto.getMall_var_31());//옵션명10
			}
			txt_url.setText(dto.getMall_var_32());//제휴가상품URL
			txt_strdate.setText(dto.getMall_var_33());//최초출발일
			txt_enddate.setText(dto.getMall_var_34());//마지막출발일
			switch(dto.getMall_var_35()) {//베네피아할인여부
			case "Y" : btn_benepia1.setSelection(true);
				break;
			case "N" : btn_benepia1.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_35().equals("Y")) {
				txt_benepia1_1.setText(dto.getMall_var_36());//베네피아할인금액및율
				switch(dto.getMall_var_37()) {//베네피아기간설정여부
				case "Y" : btn_benepia2.setSelection(true);
					break;
				case "N" : btn_benepia2.setSelection(false);
					break;
					default : break;
				}
				if(dto.getMall_var_37().equals("Y")) {
					txt_benepiast.setText(dto.getMall_var_38());//베네피아시작일
					txt_benepiaen.setText(dto.getMall_var_39());//베네피아종료일
				}			
			}
			switch(dto.getMall_var_40()) {//최소구매수량제한여부
			case "01" : btn_minlimit1.setSelection(true);
				break;
			case "02" : btn_minlimit2.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_40().equals("02")) {
				txt_minlimit2_1.setText(dto.getMall_var_41());//최소구매제한시수량
			}						
			switch(dto.getMall_var_42()) { //최대구매수량제한여부
			case "01" : btn_limit1.setSelection(true);
				break;
			case "02" : btn_limit2.setSelection(true);
				break;
			case "03" : btn_limit3.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_42().equals("02")) {
				txt_limit2_1.setText(dto.getMall_var_43());//1회제한시수량
			}else if(dto.getMall_var_42().equals("03")) {
				txt_limit3_1.setText(dto.getMall_var_44());//기간제한시기간
				txt_limit3_2.setText(dto.getMall_var_45());//기간제한시수량
			}
			txt_giftnm.setText(dto.getMall_var_46());//사은품명
			txt_giftperiodst.setText(dto.getMall_var_47());//사은품기간시작일
			txt_giftperioden.setText(dto.getMall_var_49());//사은품기간종료일
			txt_exptemplate.setText(dto.getMall_var_50());//사은품정보
			txt_exptemplate.setText(dto.getMall_var_51());//배송정보템플릿
			cb_exparea.select(Integer.parseInt(dto.getMall_var_52()));//배송가능지역
			switch(dto.getExpwycd()) { //배송방법
			case "01" : cb_world.select(0);
				break;
			case "02" :cb_world.select(1);
				break;
			case "03" : cb_world.select(2);
				break;
			case "04" : cb_world.select(3);
				break;
			case "05" : cb_world.select(4);
				break;
				default : break;
			}
			switch(dto.getMall_var_95()) { //방문수령
			case "Y" : btn_world.setSelection(true);
				break;
			case "N" :btn_world.setSelection(false);
				break;
				default : break;
			}
			scb_express.selectValue(dto.getSendexp());//발송택배사
			txt_shipmentdate.setText(dto.getMall_var_53());//발송예정일
			txt_visit.setText(dto.getMall_var_54());//방문수령주소
			switch(dto.getGbldivyn()) { //전세계배송여부
			case "Y" : btn_worldyes.setSelection(true);
				break;
			case "N" :btn_worldno.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getGblwght()) { //전세계배송무게
			case "01" : btn_weight.setSelection(true);
				break;
			case "02" :btn_weight300.setSelection(true);
				break;
				default : break;
			}
			cb_hscode.select(Integer.parseInt(dto.getHscd()));//HS코드
			switch(dto.getMall_var_55()) { //출고지주소해외여부
			case "01" : btn_outaddr1.setSelection(true);
				break;
			case "02" :btn_outaddr2.setSelection(true);
				break;
				default : break;
			}
			txt_factaddr.setText(dto.getAddrout());//출고주소
			switch(dto.getMall_var_56()) {//교환반품주소해외여부
			case "01" : btn_cngNretaddr1.setSelection(true);
				break;
			case "02" :btn_cngNretaddr2.setSelection(true);
				break;
				default : break;
			}
			txt_chanaddr.setText(dto.getAddrin()); //교환/반품주소
			switch(dto.getShiptypcd()) {//배송비조건
			case "01" : btn_free.setSelection(true);
				break;
			case "02" :btn_fixing.setSelection(true);
				break;
			case "03" :btn_condifree.setSelection(true);
			break;
			case "04" :btn_differ.setSelection(true);
			break;
			case "05" :btn_one.setSelection(true);
			break;
			case "07" :btn_seller.setSelection(true);
			break;
			case "08" :btn_factory.setSelection(true);
			break;
				default : break;
			}
			if(dto.getShiptypcd().equals("03")) {//조건부
				txt_condifree.setText(dto.getShipprc());//기본배송비
				txt_condifree1.setText(dto.getCondiprice());//상품조건별시 뒤에금액
			}else if(dto.getShiptypcd().equals("05")) {//1개당
				txt_oneprice.setText(dto.getShipprc());//기본배송비
			}else if(dto.getShiptypcd().equals("02")) {//고정
				txt_fixing.setText(dto.getShipprc());//기본배송비
			}else if(dto.getShiptypcd().equals("04")) {//수량별
				txt_diffqty.setText(dto.getMall_var_57());//수량차등별수량1
				txt_diffqty1.setText(dto.getMall_var_58()); //수량차등별수량2
				txt_diffprice.setText(dto.getDiffprice1());//수량차등별금액1
				txt_diffprice1.setText(dto.getMall_var_59());//수량차등별금액2
			}
			txt_jeju.setText(dto.getJejuprc());//제주배송비
			txt_island.setText(dto.getIslandprc());//도서산간배송비
			switch(dto.getPrctypcd()) { //선결제여부
			case "01" : cb_prepay.select(0);
				break;
			case "02" : cb_prepay.select(1);
				break;
			case "03" : cb_prepay.select(2);
			break;
				default : break;
			}
			switch(dto.getBndyn()) { //묶음배송여부
			case "Y" : btn_bundleyse.setSelection(true);
				break;
			case "N" : btn_bundleno.setSelection(true);
				break;
				default : break;
			}
			txt_oneway.setText(dto.getRetprc());//반품배송비
			txt_round.setText(dto.getExcprc());//교환배송비
			switch(dto.getRudcd()) { //왕복편도여부
			case "01" : btn_roundyes.setSelection(true);
				break;
			case "02" : btn_roundno.setSelection(true);
				break;
				default : break;
			}
			txt_as.setText(dto.getAsdtl());//A/S안내
			txt_change.setText(dto.getRtexcdtl());//교환/반품안내
			switch(dto.getMall_var_60()) { //복수구매할인여부
			case "01" : btn_multidiscnt.setSelection(true);
				break;
			case "02" : btn_multidiscnt.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_60().equals("Y")) {
				cb_multidiscntcnt.select(Integer.parseInt(dto.getMall_var_61()));//복수할인기준
				txt_multidiscntcnt.setText(dto.getMall_var_62());//복수할인금액및갯수1
				txt_multidiscntwon.setText(dto.getMall_var_63());//복수할인금액및갯수2
				cb_multidiscntwon.select(Integer.parseInt(dto.getMall_var_64()));//복수할인방법기준
			}
			cb_buy.select(Integer.parseInt(dto.getMall_var_65()));//구입처선택
			cb_repimg.select(Integer.parseInt(dto.getMall_var_66()));//대표이미지
			cb_mobileimg.select(Integer.parseInt(dto.getMall_var_67()));//모바일상세설명이미지선택
			switch(dto.getMall_var_68()) { //모바일쿠폰선택여부
			case "Y" : btn_mobile.setSelection(true);
				break;
			case "N" : btn_mobile.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_69()) {//해외구매대행상품여부
			case "01" : btn_overseasN.setSelection(true);
				break;
			case "02" : btn_overseasY.setSelection(false);
				break;
				default : break;
			}
			txt_start.setText(dto.getMall_var_70());//상품명앞추가문구
			txt_end.setText(dto.getMall_var_71());//상품명뒷추가문구
			txt_top.setText(dto.getMall_var_72());//상품설명상단추가문구
			txt_bottom.setText(dto.getMall_var_73());//상품설명하단추가문구
			switch(dto.getMall_var_74()) {//기본즉시할인여부
			case "Y" : btn_dasicdiscount.setSelection(true);
				break;
			case "N" : btn_dasicdiscount.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_74().equals("Y")) {
				txt_dasicdiscount.setText(dto.getMall_var_75());//기본즉시할인금액
				cb_dasicdiscount.select(Integer.parseInt(dto.getMall_var_76()));//기본즉시할인기준
			}
			switch(dto.getMall_var_77()) {//쿠폰지급기간설정여부
			case "Y" : btn_dasicdiscountcoupone.setSelection(true);
				break;
			case "N" : btn_dasicdiscountcoupone.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_77().equals("Y")) {
				txt_dasicdiscountdate.setText(dto.getMall_var_78());//쿠폰지급기간종료일
			}	
			switch(dto.getMall_var_79()) {//무이자할부제공여부
			case "Y" : btn_interestfee.setSelection(true);
				break;
			case "N" : btn_interestfee.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_79().equals("Y")) {
				cb_interestfee.select(Integer.parseInt(dto.getMall_var_80()));//무이자할부개월수
				txt_interestfee.setText(dto.getMall_var_81());//무이자할부회수제한
			}	
			switch(dto.getMall_var_82()) {//희망할인설정여부
			case "Y" : btn_support.setSelection(true);
				break;
			case "N" : btn_support.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_82().equals("Y")) {
				txt_support.setText(dto.getMall_var_83());//희망후원금액
				cb_support.select(Integer.parseInt(dto.getMall_var_84()));//희망후원할인기준
			}	
			switch(dto.getMall_var_85()) {//SK포인트지급여부
			case "Y" : btn_skpay.setSelection(true);
				break;
			case "N" : btn_skpay.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_85().equals("Y")) {
				txt_skpay.setText(dto.getMall_var_86());//SK포인트금액
				cb_skpay.select(Integer.parseInt(dto.getMall_var_87()));//SK포인트할인기준
			}
			switch(dto.getMall_var_88()) {//상품리뷰여부
			case "Y" : btn_prodreviewY.setSelection(true);
				break;
			case "N" : btn_prodreviewN.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getMall_var_89()) {//플러스광고여부
			case "Y" : btn_plus.setSelection(true);
				break;
			case "N" : btn_plus.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_90()) {//플러스UP광고여부
			case "Y" : btn_plusup.setSelection(true);
				break;
			case "N" : btn_plusup.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_91()) {//GIF광고여부
			case "Y" : btn_gifimg.setSelection(true);
				break;
			case "N" : btn_gifimg.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_92()) {//볼드체광고여부
			case "Y" : btn_bold.setSelection(true);
				break;
			case "N" : btn_bold.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_93()) {//배경색광고여부
			case "Y" : btn_background.setSelection(true);
				break;
			case "N" : btn_background.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getUseyn()) {//사용여부
			case "Y" : cb_whether.select(1);
				break;
			case "N" : cb_whether.select(2);
				break;
				default : break;
			}
		}else {
			btn_fixed.setSelection(true);
			btn_orgin2_1.setSelection(true);
			btn_new.setSelection(true);
			btn_minoryes.setSelection(true);
			btn_costcomparisY.setSelection(true);
			btn_dateyes.setSelection(true);
			btn_30day.setSelection(true);
			btn_optval1.setSelection(true);
			btn_optionN.setSelection(true);
			btn_minlimit1.setSelection(true);
			btn_limit1.setSelection(true);
			btn_outaddr1.setSelection(true);
			btn_cngNretaddr1.setSelection(true);
			btn_bundleyse.setSelection(true);
			btn_roundyes.setSelection(true);
			btn_overseasN.setSelection(true);
			
		}
		
	}
	void setcateglagcd(String info) {
		for (int i = 0; i < this.cb_exp.getItems().length; i++) {
			String type = this.cb_exp.getItems()[i];
			String cate = type;
			if (cate.equals(info)) {
				this.cb_exp.setText(type);
				break;
			} 
		}
	}
	@Override
	protected void okPressed() {
		if(txt_title.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "제목을 입력하여 주시기 바랍니다.");
			return;
		}
		if(cb_idcheck.getText().equals("아이디선택")) {
			MessageDialog.openInformation(getShell(), TITLE, "아이디를 선택하시고 진행하시기 바랍니다.");
			return;
		}
		if(!btn_public.getSelection()&&!btn_fixed.getSelection()&&!btn_used.getSelection()&&!btn_reser.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "판매방식을 체크하여 주시기 바랍니다.");
			return;
		}
		if(!btn_new.getSelection()&&!btn_stock.getSelection()&&!btn_making.getSelection()&&!btn_usedprod.getSelection()&&!btn_reaper.getSelection()&&!btn_exhibi.getSelection()&&!btn_return.getSelection()&&
				!btn_scratch.getSelection()&&!btn_rare.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "상품상태를 체크하여 주시기 바랍니다.");
			return;
		}
		if(!btn_minoryes.getSelection()&&!btn_minorno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "미성년자구매가능을 체크하여 주시기 바랍니다.");
			return;
		}
		if(txt_nicknm.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "닉네임을 입력하여 주시기 바랍니다.");
			return;
		}
		if(!btn_dateyes.getSelection()&&!btn_dateno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "판매기간을 체크하여 주시기 바랍니다.");
			return;
		}
		if(!btn_7day.getSelection()&&!btn_15day.getSelection()&&!btn_30day.getSelection()&&!btn_60day.getSelection()&&!btn_90day.getSelection()&&!btn_120day.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "판매기간단위를 체크하여 주시기 바랍니다.");
			return;
		}
		if(cb_world.getSelectionIndex()==0) {
			if(cb_exp.getText().equals("선택하세요")) {
				MessageDialog.openInformation(getShell(), TITLE, "배송방법이 택배일경우 발송 택배사를 반드시 등록하고 저장하셔야됩니다.");
				return;
			}
		}
		if(txt_factaddr.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "출고지주소를 입력하여 주시기 바랍니다.");
			return;
		}
		if(txt_chanaddr.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "교환/반품지주소를 입력하여 주시기 바랍니다.");
			return;
		}
		if(!btn_free.getSelection()&&!btn_condifree.getSelection()&&!btn_fixing.getSelection()&&!btn_seller.getSelection()&&!btn_factory.getSelection()&&!btn_differ.getSelection()&&!btn_one.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "배송비설정을 체크하여 주시기 바랍니다.");
			return;
		}
		if(!btn_free.getSelection()&&btn_condifree.getSelection()||btn_fixing.getSelection()) {
			if(txt_condifree.getText().length()<1&&txt_fixing.getText().length()<1) {
				MessageDialog.openInformation(getShell(), TITLE, "배송비설정을 체크하여 주시기 바랍니다.");
				return;
			}			
		}
		if(!btn_roundyes.getSelection()&&!btn_roundno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "초기배송비 무료시 반품배송비 부과방법을 체크하여 주시기 바랍니다.");
			return;
		}
		if(txt_oneway.getText().trim().length()<1&&txt_round.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "반품배송비, 교환배송비를 입력하여 주시기 바랍니다.");
			return;
		}
		if(txt_as.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "A/S안내를 입력하여 주시기 바랍니다.");
			return;
		}
		if(txt_change.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "교환 / 반품안내를 입력하여 주시기 바랍니다.");
			return;
		}
		if(cb_whether.getSelectionIndex()==0) {
			MessageDialog.openInformation(getShell(), TITLE, "사용여부를 선택하여 주시기 바랍니다.");
			return;
		}
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			ShopProduct11stAdditionDto dto = new ShopProduct11stAdditionDto();

			dto.setTitle(txt_title.getText()); //제목
			dto.setMemo(txt_memo.getText()); //메모
			dto.setShopid(cb_idcheck.getText()); //아이디
			dto.setSelmthdcd(btn_fixed.getSelection()?"01":btn_used.getSelection()?"05":btn_reser.getSelection()?"04":"02"); //판매방식			
			dto.setProdtypcd(cb_service.getSelectionIndex()==0?"01":""); //서비스상품
			dto.setMall_var_1(txt_prodpromotion.getText()); //상품홍보문구
			dto.setMall_var_2(btn_orgin1_1.getSelection()?"01":btn_orgin1_2.getSelection()?"02":btn_orgin1_3.getSelection()?"03":btn_orgin1_4.getSelection()?"04":btn_orgin1_5.getSelection()?"05":""); //원산지물품
			dto.setMall_var_3(btn_orgin2_1.getSelection()?"01":btn_orgin2_2.getSelection()?"02":""); //원산지사용여부
			if(dto.getMall_var_3().equals("02")) {
				dto.setMall_var_4(txt_orgin2_1.getText()); //원산지사용여부에따른내용
			}
			dto.setMall_var_5(btn_orgin3_1.getSelection()?"Y":"N"); //원산지다중등록여부
			dto.setMall_var_6(txt_prodmd.getText()); //상품모델
			dto.setMall_var_7(String.valueOf(cb_prodedit.getSelectionIndex())); //상품수정모델
			dto.setMall_var_8(String.valueOf(cb_sellerprod.getSelectionIndex())); //판매자상품코드
			dto.setMall_var_9(btn_sellercash.getSelection()?"Y":"N"); //셀러캐시사용여부
			dto.setProdstatcd(btn_new.getSelection()?"01":btn_usedprod.getSelection()?"02":btn_stock.getSelection()?"03":btn_reaper.getSelection()?"04":btn_exhibi.getSelection()?"05":btn_rare.getSelection()?"07":
				btn_return.getSelection()?"08":btn_scratch.getSelection()?"09":"10"); //상품상태
			dto.setMinorselcnyn(btn_minoryes.getSelection()?"Y":"N"); //미성년자구매여부
			dto.setMall_var_10(txt_point.getText()); //지점선택
			dto.setMall_var_11(txt_disney.getText()); //디즈니시리즈선택
			dto.setNicknm(txt_nicknm.getText()); //닉네임
			dto.setMall_var_12(txt_brand.getText()); //브랜드
			dto.setMall_var_13(txt_phoneurl.getText()); //가입신청URL
			dto.setMall_var_94(btn_phone1.getSelection()?"01":btn_phone2.getSelection()?"02":"");
			dto.setMall_var_14(txt_phone.getText()); //휴대폰요금제
			dto.setMall_var_15(txt_effecdate.getText()); //유효일자
			dto.setMall_var_16(btn_costcomparisY.getSelection()?"01":btn_costcomparisN.getSelection()?"02":""); //가격비교등록여부
			dto.setFpseltermyn(btn_dateyes.getSelection()?"Y":"N"); //판매기간설정여부
			if(dto.getFpseltermyn().equals("Y")) {
				dto.setProdfpselcd(btn_7day.getSelection()?"7:103":btn_15day.getSelection()?"15:104":btn_30day.getSelection()?"30:105":btn_60day.getSelection()?"60:106":btn_90day.getSelection()?"90:107":"120:108"); //판매기간코드
			}			
			dto.setMall_var_17(btn_1wonphone.getSelection()?"Y":"N"); //1원폰상품여부
			dto.setMall_var_18(String.valueOf(cb_optsetting.getSelectionIndex())); //옵션설정여부
			dto.setMall_var_19(String.valueOf(cb_2optiondefault.getSelectionIndex())); //2단옵션적용여부
			dto.setMall_var_20(btn_optval1.getSelection()?"01":btn_optval2.getSelection()?"02":btn_optval3.getSelection()?"03":btn_optval4.getSelection()?"04":btn_optval5.getSelection()?"05":""); //옵션값노출방식
			dto.setMall_var_21(btn_optionN.getSelection()?"N":btn_optionY.getSelection()?"Y":""); //작성형옵션여부
			if(dto.getMall_var_21().equals("Y")) {
				dto.setMall_var_22(txt_option1.getText()); //옵션명1
				dto.setMall_var_23(txt_option2.getText()); //옵션명2
				dto.setMall_var_24(txt_option3.getText()); //옵션명3
				dto.setMall_var_25(txt_option4.getText()); //옵션명4
				dto.setMall_var_26(txt_option5.getText()); //옵션명5
				dto.setMall_var_27(txt_option6.getText()); //옵션명6
				dto.setMall_var_28(txt_option7.getText()); //옵션명7
				dto.setMall_var_29(txt_option8.getText()); //옵션명8
				dto.setMall_var_30(txt_option9.getText()); //옵션명9
				dto.setMall_var_31(txt_option10.getText()); //옵션명10
			}		
			dto.setMall_var_32(txt_url.getText()); //제휴가상품URL
			dto.setMall_var_33(txt_strdate.getText()); //최초출발일
			dto.setMall_var_34(txt_enddate.getText()); //마지막출발일
			dto.setMall_var_35(btn_benepia1.getSelection()?"Y":"N"); //베네피아할인여부
			if(dto.getMall_var_35().equals("Y")) {
				dto.setMall_var_36(StringCountCheck(txt_benepia1_1.getText())); //베네피아할인금액및율
				dto.setMall_var_37(btn_benepia2.getSelection()?"Y":"N"); //베네피아기간설정여부
				if(dto.getMall_var_37().equals("Y")) {
					dto.setMall_var_38(txt_benepiast.getText()); //베네피아시작일
					dto.setMall_var_39(txt_benepiaen.getText()); //베네피아종료일
				}
			}
			dto.setMall_var_40(btn_minlimit1.getSelection()?"01":btn_minlimit2.getSelection()?"02":""); //최소구매수량제한여부
			if(dto.getMall_var_40().equals("02")) {
				dto.setMall_var_41(StringCountCheck(txt_minlimit2_1.getText())); //최소구매제한시수량
			}			
			dto.setMall_var_42(btn_limit1.getSelection()?"01":btn_limit2.getSelection()?"02":btn_limit3.getSelection()?"03":""); //최대구매수량제한여부
			if(dto.getMall_var_42().equals("02")) {
				dto.setMall_var_43(StringCountCheck(txt_limit2_1.getText())); //1회제한시수량
			}else if(dto.getMall_var_42().equals("03")) {
				dto.setMall_var_44(StringCountCheck(txt_limit3_1.getText())); //기간제한시기간
				dto.setMall_var_45(StringCountCheck(txt_limit3_2.getText())); //기간제한시수량
			}
			dto.setMall_var_46(txt_giftnm.getText()); //사은품명
			dto.setMall_var_47(txt_giftperiodst.getText()); //사은품기간시작일
			dto.setMall_var_49(txt_giftperioden.getText()); //사은품기간종료일
			dto.setMall_var_50(txt_gift.getText()); //사은품정보
			dto.setMall_var_51(txt_exptemplate.getText()); //배송정보템플릿
			dto.setMall_var_52(String.valueOf(cb_exparea.getSelectionIndex())); //배송가능지역
			dto.setExpwycd(cb_world.getSelectionIndex()==0?"01":cb_world.getSelectionIndex()==1?"02":cb_world.getSelectionIndex()==2?"03":cb_world.getSelectionIndex()==3?"04":"05"); //배송방법
			dto.setMall_var_95(btn_world.getSelection()?"Y":"N");//방문수령
			dto.setSendexp(scb_express.getSelectionValue()); //발송택배사
			dto.setMall_var_53(txt_shipmentdate.getText()); //발송예정일
			dto.setMall_var_54(txt_visit.getText()); //방문수령주소
			dto.setGbldivyn(btn_worldyes.getSelection()?"Y":"N"); //전세계배송여부
			dto.setGblwght(btn_weight.getSelection()?"01":btn_weight300.getSelection()?"02":"0"); //전세계배송무게
			dto.setHscd(String.valueOf(cb_hscode.getSelectionIndex())); //HS코드
			dto.setMall_var_55(btn_outaddr1.getSelection()?"01":btn_outaddr2.getSelection()?"02":""); //출고지주소해외여부
			dto.setAddrout(txt_factaddr.getText()); //출고주소
			dto.setMall_var_56(btn_cngNretaddr1.getSelection()?"01":btn_cngNretaddr2.getSelection()?"02":""); //교환반품주소해외여부
			dto.setAddrin(txt_chanaddr.getText()); //교환/반품주소
			dto.setShiptypcd(btn_free.getSelection()?"01":btn_fixing.getSelection()?"02":btn_seller.getSelection()?"07":btn_factory.getSelection()?"08":btn_condifree.getSelection()?"03":btn_one.getSelection()?"05":"04"); //배송비조건
			dto.setShipprc("0"); //기본배송비
			dto.setCondiprice("0"); //상품조건별시 뒤에금액
			dto.setMall_var_57("0"); //수량차등별수량1
			dto.setMall_var_58("0"); //수량차등별수량2
			dto.setDiffprice1("0"); //수량차등별금액1
			dto.setMall_var_59("0"); //수량차등별금액2
			if(dto.getShiptypcd().equals("03")) {//조건부
				dto.setShipprc(StringCountCheck(txt_condifree.getText())); //기본배송비
				dto.setCondiprice(StringCountCheck(txt_condifree1.getText())); //상품조건별시 뒤에금액
			}else if(dto.getShiptypcd().equals("05")) {//1개당
				dto.setShipprc(StringCountCheck(txt_oneprice.getText())); //기본배송비
			}else if(dto.getShiptypcd().equals("02")) {//고정
				dto.setShipprc(StringCountCheck(txt_fixing.getText())); //기본배송비
			}else if(dto.getShiptypcd().equals("04")) {//수량별
				dto.setMall_var_57(StringCountCheck(txt_diffqty.getText())); //수량차등별수량1
				dto.setMall_var_58(StringCountCheck(txt_diffqty1.getText())); //수량차등별수량2
				dto.setDiffprice1(StringCountCheck(txt_diffprice.getText())); //수량차등별금액1
				dto.setMall_var_59(StringCountCheck(txt_diffprice1.getText())); //수량차등별금액2
			}
			dto.setJejuprc(StringCountCheck(txt_jeju.getText())); //제주배송비
			dto.setIslandprc(StringCountCheck(txt_island.getText())); //도서산간배송비
			dto.setPrctypcd(cb_prepay.getSelectionIndex()==0?"01":cb_prepay.getSelectionIndex()==1?"02":"03"); //선결제여부
			dto.setBndyn(btn_bundleyse.getSelection()?"Y":"N"); //묶음배송여부
			dto.setRetprc(StringCountCheck(txt_oneway.getText())); //반품배송비
			dto.setExcprc(StringCountCheck(txt_round.getText())); //교환배송비
			dto.setRudcd(btn_roundyes.getSelection()?"01":"02"); //왕복편도여부
			dto.setAsdtl(txt_as.getText()); //A/S안내
			dto.setRtexcdtl(txt_change.getText()); //교환/반품안내
			dto.setMall_var_60(btn_multidiscnt.getSelection()?"Y":"N"); //복수구매할인여부
			dto.setMall_var_62("0"); //복수할인금액및갯수1
			dto.setMall_var_63("0");//복수할인금액및갯수2
			if(dto.getMall_var_60().equals("Y")) {
				dto.setMall_var_61(String.valueOf(cb_multidiscntcnt.getSelectionIndex())); //복수할인기준
				dto.setMall_var_62(StringCountCheck(txt_multidiscntcnt.getText())); //복수할인금액및갯수1
				dto.setMall_var_63(StringCountCheck(txt_multidiscntwon.getText())); //복수할인금액및갯수2
				dto.setMall_var_64(String.valueOf(cb_multidiscntwon.getSelectionIndex())); //복수할인방법기준
			}
			dto.setMall_var_65(String.valueOf(cb_buy.getSelectionIndex())); //구입처선택
			dto.setMall_var_66(String.valueOf(cb_repimg.getSelectionIndex())); //대표이미지
			dto.setMall_var_67(String.valueOf(cb_mobileimg.getSelectionIndex())); //모바일상세설명이미지선택
			dto.setMall_var_68(btn_mobile.getSelection()?"Y":"N"); //모바일쿠폰선택여부
			dto.setMall_var_69(btn_overseasN.getSelection()?"01":btn_overseasY.getSelection()?"02":""); //해외구매대행상품여부
			dto.setMall_var_70(txt_start.getText()); //상품명앞추가문구
			dto.setMall_var_71(txt_end.getText()); //상품명뒷추가문구
			dto.setMall_var_72(txt_top.getText()); //상품설명상단추가문구
			dto.setMall_var_73(txt_bottom.getText()); //상품설명하단추가문구
			dto.setMall_var_74(btn_dasicdiscount.getSelection()?"Y":"N"); //기본즉시할인여부
			dto.setMall_var_75("0"); //기본즉시할인금액
			if(dto.getMall_var_74().equals("Y")) {
				dto.setMall_var_75(StringCountCheck(txt_dasicdiscount.getText())); //기본즉시할인금액
				dto.setMall_var_76(String.valueOf(cb_dasicdiscount.getSelectionIndex())); //기본즉시할인기준
			}			
			dto.setMall_var_77(btn_dasicdiscountcoupone.getSelection()?"Y":"N"); //쿠폰지급기간설정여부
			if(dto.getMall_var_77().equals("Y")) {
				dto.setMall_var_78(txt_dasicdiscountdate.getText()); //쿠폰지급기간종료일
			}		
			dto.setMall_var_79(btn_interestfee.getSelection()?"Y":"N"); //무이자할부제공여부
			dto.setMall_var_81("0"); //무이자할부회수제한
			if(dto.getMall_var_79().equals("Y")) {
				dto.setMall_var_80(String.valueOf(cb_interestfee.getSelectionIndex())); //무이자할부개월수
				dto.setMall_var_81(StringCountCheck(txt_interestfee.getText())); //무이자할부회수제한
			}			
			dto.setMall_var_82(btn_support.getSelection()?"Y":"N"); //희망할인설정여부
			dto.setMall_var_83("0"); //희망후원금액
			if(dto.getMall_var_82().equals("Y")) {
				dto.setMall_var_83(StringCountCheck(txt_support.getText())); //희망후원금액
				dto.setMall_var_84(String.valueOf(cb_support.getSelectionIndex())); //희망후원할인기준
			}		
			dto.setMall_var_85(btn_skpay.getSelection()?"Y":"N"); //SK포인트지급여부
			dto.setMall_var_86("0"); //SK포인트금액
			if(dto.getMall_var_85().equals("Y")) {
				dto.setMall_var_86(StringCountCheck(txt_skpay.getText())); //SK포인트금액
				dto.setMall_var_87(String.valueOf(cb_skpay.getSelectionIndex())); //SK포인트할인기준
			}	
			dto.setMall_var_88(btn_prodreviewN.getSelection()?"N":btn_prodreviewY.getSelection()?"Y":""); //상품리뷰여부
			dto.setMall_var_89(btn_plus.getSelection()?"Y":"N"); //플러스광고여부
			dto.setMall_var_90(btn_plusup.getSelection()?"Y":"N"); //플러스UP광고여부
			dto.setMall_var_91(btn_gifimg.getSelection()?"Y":"N"); //GIF광고여부
			dto.setMall_var_92(btn_bold.getSelection()?"Y":"N"); //볼드체광고여부
			dto.setMall_var_93(btn_background.getSelection()?"Y":"N"); //배경색광고여부
			dto.setUseyn(cb_whether.getSelectionIndex()==1?"Y":"N"); //사용여부

			if(this.dto ==null) {
				int seq=0;
				if(list!=null) {
					seq = dao.getSeqNumber(list.get(0));
				}			
				int result = dao.ShopAddrDtlInsert(seq+1,dto,list);
				if(result!=0) {				
					MessageDialog.openInformation(getShell(), TITLE, "부가정보를 저장하였습니다.");
					super.okPressed();
				}else {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보저장에 실패하였습니다.");
				}
			}else {
				int result = dao.ShopAddrDtlUpdate(dto,this.dto,shopseq);
				if(result!=0) {				
					MessageDialog.openInformation(getShell(), TITLE, "부가정보를 수정하였습니다.");
					opener.getSearchShoppingManager();
					super.okPressed();
				}else {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보수정에 실패하였습니다.");
				}
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		

	}


	private String StringCountCheck(String text) {
		String value = text.trim().length()==0?"0":text;
		return value;
	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();

	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		btnOk = createButton(parent, IDialogConstants.OK_ID, "저장", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "닫기", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(1470,1067);
	}
}
