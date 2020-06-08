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

	String TITLE = "11���� �ΰ����� ����";
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
		group_1.setText("�� [KEY ����]");
		
		label = new Label(group_1, SWT.NONE);
		label.setText("�� ����");
		
		txt_title = new Text(group_1, SWT.BORDER);
		GridData gd_txt_title = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_title.widthHint = 700;
		txt_title.setLayoutData(gd_txt_title);
		
		lblNewLabel_37 = new Label(group_1, SWT.NONE);
		lblNewLabel_37.setText("   �޸�");
		
		txt_memo = new Text(group_1, SWT.BORDER);
		GridData gd_txt_memo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_memo.widthHint = 700;
		txt_memo.setLayoutData(gd_txt_memo);
		
		lblNewLabel_38 = new Label(group_1, SWT.NONE);
		GridData gd_lblNewLabel_38 = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 2, 1);
		gd_lblNewLabel_38.heightHint = 30;
		lblNewLabel_38.setLayoutData(gd_lblNewLabel_38);
		lblNewLabel_38.setText("* �������� ���ý� ������� ��������Ʈ���� �����˴ϴ�.");
		lblNewLabel_38.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		Label label_15 = new Label(group_1, SWT.NONE);
		label_15.setText("�� ���̵���");
		
		cb_idcheck = new Combo(group_1, SWT.NONE);
		GridData gd_cb_idcheck = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_idcheck.widthHint = 120;
		cb_idcheck.setLayoutData(gd_cb_idcheck);
		cb_idcheck.setItems(new String[] {"���̵���"});
		cb_idcheck.select(0);
		
		label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("�� �ǸŹ��");
		
		composite_3 = new Composite(group_1, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_3.setLayout(new GridLayout(5, false));
		
		btn_fixed = new Button(composite_3, SWT.RADIO);
		btn_fixed.setText("�������Ǹ�");
		
		btn_public = new Button(composite_3, SWT.RADIO);
		btn_public.setText("��������");
		
		btn_used = new Button(composite_3, SWT.RADIO);
		btn_used.setText("�߰��Ǹ�");
		
		btn_reser = new Button(composite_3, SWT.RADIO);
		btn_reser.setText("�����Ǹ�");
		
		lblNewLabel_39 = new Label(composite_3, SWT.NONE);
		lblNewLabel_39.setText("	* �������� �� ��ǰ������ �Ұ��մϴ�.");
		lblNewLabel_39.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		group_2 = new Group(container, SWT.NONE);
		group_2.setLayout(new GridLayout(2, false));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		group_2.setText("�� [�Ǹ���������]");
		
		label_6 = new Label(group_2, SWT.NONE);
		label_6.setText("�� ���񽺻�ǰ");
		
		composite_85 = new Composite(group_2, SWT.NONE);
		composite_85.setLayout(new GridLayout(2, false));
		composite_85.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_service = new Combo(composite_85, SWT.NONE);
		cb_service.setItems(new String[] {"�Ϲݹ�ۻ�ǰ", "��ȣ��ǰ", "��Ʈ��ǰ"});
		cb_service.select(0);
		
		lblNewLabel_144 = new Label(composite_85, SWT.NONE);
		lblNewLabel_144.setText("   �� PIN��ȣ ��ǰ�� �ֹ����� ������");
		lblNewLabel_144.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_153 = new Label(group_2, SWT.NONE);
		lblNewLabel_153.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_153.setText("* [���ϻ�ǰ]���񽺻�ǰ�� �Ϲݹ�ۻ�ǰ�� ����");
		lblNewLabel_153.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_145 = new Label(group_2, SWT.NONE);
		lblNewLabel_145.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_145.setText("* ��ǰȫ�������� �ִ� �ѱ� 20��, ����/���� 40�� �̳��� �Է��ϼž� �մϴ�.");
		lblNewLabel_145.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_146 = new Label(group_2, SWT.NONE);
		lblNewLabel_146.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_146.setText("* ��ǰȫ�������� 11���� ��ǰ�� �ϴܿ� ����Ǹ�, �˻���� ���Ե��� �ʽ��ϴ�.");
		lblNewLabel_146.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_147 = new Label(group_2, SWT.NONE);
		lblNewLabel_147.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_147.setText("* ��ǰȫ�������� ���� ��ǰ������ ����Ʈ�˻���� �ݿ����� �ʽ��ϴ�.");
		lblNewLabel_147.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_148 = new Label(group_2, SWT.NONE);
		lblNewLabel_148.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_148.setText("* ������ ��Ÿ ���ý� �߰������� �Է��� �ֽʽÿ�.");
		lblNewLabel_148.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_149 = new Label(group_2, SWT.NONE);
		lblNewLabel_149.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_149.setText("* �������� 1���� �̻��� ���, �������� �ٸ� ��ǰ ���� ��Ͽ� üũ�Ͽ� �ֽʽÿ�.");
		lblNewLabel_149.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_150 = new Label(group_2, SWT.NONE);
		lblNewLabel_150.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_150.setText("* �ɼ�/�߰����� ��ǰ�� �������� �ٸ� ���, �ݵ�� �󼼼��� �ش� ��ǰ�� ���� �������� ǥ���� �ֽñ� �ٶ��ϴ�.");
		lblNewLabel_150.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_151 = new Label(group_2, SWT.NONE);
		lblNewLabel_151.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_151.setText("* ����깰 ����ǰ�� ���, �������� �ƴ� ������ �������� ǥ���Ͽ��߸� �մϴ�.");
		lblNewLabel_151.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_152 = new Label(group_2, SWT.NONE);
		lblNewLabel_152.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_152.setText("* ������ �׸񿡼� ���� ��ǰ���� ����� �����ϴ� ����깰 ����ǰ�� ���, ��깰/���깰/����ǰ �׸� �� �ش��ϴ� �׸� �����Ͻñ� �ٶ��ϴ�.");
		lblNewLabel_152.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_143 = new Label(group_2, SWT.NONE);
		lblNewLabel_143.setText("   ��ǰȫ������");
		
		composite_84 = new Composite(group_2, SWT.NONE);
		composite_84.setLayout(new GridLayout(1, false));
		composite_84.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_prodpromotion = new Text(composite_84, SWT.BORDER);
		GridData gd_txt_prodpromotion = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodpromotion.widthHint = 700;
		txt_prodpromotion.setLayoutData(gd_txt_prodpromotion);
		
		lblNewLabel_141 = new Label(group_2, SWT.NONE);
		lblNewLabel_141.setText("   ������");
		
		composite_80 = new Composite(group_2, SWT.NONE);
		composite_80.setLayout(new GridLayout(1, false));
		composite_80.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		composite_81 = new Composite(composite_80, SWT.NONE);
		composite_81.setLayout(new GridLayout(5, false));
		composite_81.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btn_orgin1_1 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_1.setText("��깰");
		
		btn_orgin1_2 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_2.setText("���깰");
		
		btn_orgin1_3 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_3.setText("����ǰ");
		
		btn_orgin1_4 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_4.setText("������ �ǹ� ǥ�ô�� �ƴ�");
		
		btn_orgin1_5 = new Button(composite_81, SWT.RADIO);
		btn_orgin1_5.setText("��ǰ�� �������� �󼼼��� ����");
		
		Composite composite_82 = new Composite(composite_80, SWT.NONE);
		composite_82.setLayout(new GridLayout(5, false));
		composite_82.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btn_orgin2_1 = new Button(composite_82, SWT.RADIO);
		btn_orgin2_1.setText(" M-Link ��ǰ���� ���");
		
		btn_orgin2_2 = new Button(composite_82, SWT.RADIO);
		btn_orgin2_2.setText(" ��Ÿ ���");
		
		lblNewLabel_154 = new Label(composite_82, SWT.NONE);
		lblNewLabel_154.setText("	(��Ÿ ����̰ų� ��Ÿ�����϶� �߰����� :");
		
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
		btn_orgin3_1.setText("  �������� �ٸ� ��ǰ ���� ���");
		
		lblNewLabel_139 = new Label(group_2, SWT.NONE);
		lblNewLabel_139.setText("   ��ǰ��");
		
		composite_79 = new Composite(group_2, SWT.NONE);
		composite_79.setLayout(new GridLayout(4, false));
		composite_79.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_prodmd = new Text(composite_79, SWT.BORDER);
		GridData gd_txt_prodmd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodmd.widthHint = 500;
		txt_prodmd.setLayoutData(gd_txt_prodmd);
		
		btn_prodmdsearch = new Button(composite_79, SWT.NONE);
		btn_prodmdsearch.setText("�˻�");
		
		btn_prodmddel = new Button(composite_79, SWT.NONE);
		btn_prodmddel.setText("����");
		
		lblNewLabel_140 = new Label(composite_79, SWT.NONE);
		lblNewLabel_140.setText(" *�𵨸� ���Ե� �귣�尡 �켱�ݿ��˴ϴ�.");
		lblNewLabel_140.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_137 = new Label(group_2, SWT.NONE);
		lblNewLabel_137.setText("   ��ǰ�� ����");
		
		composite_78 = new Composite(group_2, SWT.NONE);
		composite_78.setLayout(new GridLayout(2, false));
		composite_78.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_prodedit = new Combo(composite_78, SWT.NONE);
		cb_prodedit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_prodedit.setItems(new String[] {"������", "��������"});
		cb_prodedit.select(0);
		
		lblNewLabel_138 = new Label(composite_78, SWT.NONE);
		lblNewLabel_138.setText("   �ػ�ǰ���� �ø� �����˴ϴ�.");
		lblNewLabel_138.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_135 = new Label(group_2, SWT.NONE);
		lblNewLabel_135.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_135.setText("* 11���� ��ǰ���� �� '�Ǹ��� ��ǰ�ڵ�'���� �Է��� ���� ���� ���� ��ǰ���� �� ������ �� �ֽ��ϴ�.\r\n" + 
				"   ��ǰ���� �۽� �� [������]�� ������ ��� ���� scm�� ��ϵ� ������ �����˴ϴ�.");
		lblNewLabel_135.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_134 = new Label(group_2, SWT.NONE);
		lblNewLabel_134.setText("�� �Ǹ��ڻ�ǰ�ڵ�");
		
		composite_77 = new Composite(group_2, SWT.NONE);
		composite_77.setLayout(new GridLayout(1, false));
		composite_77.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_sellerprod = new Combo(composite_77, SWT.NONE);
		cb_sellerprod.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_sellerprod.setItems(new String[] {"�۽Ź�ȣ", "��ü��ǰ�ڵ�", "��No","������","�𵨸�"});
		cb_sellerprod.select(0);
		
		lblNewLabel_132 = new Label(group_2, SWT.NONE);
		lblNewLabel_132.setText("   ����ĳ�� ���");
		
		composite_76 = new Composite(group_2, SWT.NONE);
		composite_76.setLayout(new GridLayout(2, false));
		composite_76.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_sellercash = new Button(composite_76, SWT.CHECK);
		btn_sellercash.setText("����ĳ�� ���");
		
		lblNewLabel_133 = new Label(composite_76, SWT.NONE);
		lblNewLabel_133.setText(" * ��������Ʈ�� �����ϸ� ����ĳ�ð� �����˴ϴ�.");
		
		label_8 = new Label(group_2, SWT.NONE);
		label_8.setText("�� ��ǰ����");
		
		composite_12 = new Composite(group_2, SWT.NONE);
		composite_12.setLayout(new GridLayout(9, false));
		
		btn_new = new Button(composite_12, SWT.RADIO);
		btn_new.setText("����ǰ");
		
		btn_stock = new Button(composite_12, SWT.RADIO);
		btn_stock.setText("����ǰ");
		
		btn_making = new Button(composite_12, SWT.RADIO);
		btn_making.setText("�ֹ����ۻ�ǰ");
		
		btn_usedprod = new Button(composite_12, SWT.RADIO);
		btn_usedprod.setText("�߰��ǰ");
		
		btn_reaper = new Button(composite_12, SWT.RADIO);
		btn_reaper.setText("���ۻ�ǰ");
		
		btn_exhibi = new Button(composite_12, SWT.RADIO);
		btn_exhibi.setText("����(����)��ǰ");
		
		btn_return = new Button(composite_12, SWT.RADIO);
		btn_return.setText("��ǰ��ǰ");
		
		btn_scratch = new Button(composite_12, SWT.RADIO);
		btn_scratch.setText("��ũ��ġ��ǰ");
		
		btn_rare = new Button(composite_12, SWT.RADIO);
		btn_rare.setText("��ͼ���ǰ");		

		lblNewLabel_130 = new Label(group_2, SWT.NONE);
		lblNewLabel_130.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_130.setText("* �̼����� ���źҰ��� �����Ͻø�, �̼����� ȸ������ ��ǰ�̹����� ������� ������ '19��'���� ǥ�õ˴ϴ�.");
		lblNewLabel_130.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_131 = new Label(group_2, SWT.NONE);
		lblNewLabel_131.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_131.setText("* ���źҰ� ��ǰ�� ���Ű������� ǥ���� ���, �Ǹű��� ó�� �� �� �ֽ��ϴ�.");
		lblNewLabel_130.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_22 = new Label(group_2, SWT.NONE);
		label_22.setText("�� �̼����� ���Ű���");
		
		composite_13 = new Composite(group_2, SWT.NONE);
		composite_13.setLayout(new GridLayout(2, false));
		
		btn_minoryes = new Button(composite_13, SWT.RADIO);
		btn_minoryes.setText("�̼����� ���Ű���");
		
		btn_minorno = new Button(composite_13, SWT.RADIO);
		btn_minorno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btn_minorno.setText("�̼����� ���źҰ�");	
		
		lblNewLabel_126 = new Label(group_2, SWT.NONE);
		lblNewLabel_126.setText("   ���� ����");
		
		composite_74 = new Composite(group_2, SWT.NONE);
		composite_74.setLayout(new GridLayout(2, false));
		composite_74.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_point = new Text(composite_74, SWT.BORDER);
		GridData gd_txt_point = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_point.widthHint = 700;
		txt_point.setLayoutData(gd_txt_point);
		
		btn_pointsearch = new Button(composite_74, SWT.NONE);
		btn_pointsearch.setText("�˻�");
		
		lblNewLabel_129 = new Label(composite_74, SWT.NONE);
		lblNewLabel_129.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_129.setText("* ī�װ� ��Ȱ�÷��� �� ��� ���� �����ʼ� �Դϴ�.");
		lblNewLabel_129.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_127 = new Label(group_2, SWT.NONE);
		lblNewLabel_127.setText("   ����� �ø��� ����");
		
		composite_75 = new Composite(group_2, SWT.NONE);
		composite_75.setLayout(new GridLayout(2, false));
		composite_75.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_disney = new Text(composite_75, SWT.BORDER);
		GridData gd_txt_disney = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_disney.widthHint = 700;
		txt_disney.setLayoutData(gd_txt_disney);
		
		btn_disneysearch = new Button(composite_75, SWT.NONE);
		btn_disneysearch.setText("�˻�");
		
		lblNewLabel_128 = new Label(composite_75, SWT.NONE);
		lblNewLabel_128.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_128.setText("* ī�װ� �ϱ�,�����ƿ�ǰ �� ��� ����� �ø��� �����ʼ� �Դϴ�.");
		lblNewLabel_128.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_125 = new Label(group_2, SWT.NONE);
		lblNewLabel_125.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_125.setText("* ������ ���̵� �г��Ӹ��� �������ΰ�� �ΰ��������� �г��Ӹ��� �����մϴ�.");
		lblNewLabel_125.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_7 = new Label(group_2, SWT.NONE);
		label_7.setText("�� �г���");
		
		composite_72 = new Composite(group_2, SWT.NONE);
		composite_72.setLayout(new GridLayout(3, false));
		composite_72.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_nicknm = new Text(composite_72, SWT.BORDER);
		GridData gd_txt_nicknm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_nicknm.widthHint = 500;
		txt_nicknm.setLayoutData(gd_txt_nicknm);
		
		btn_nicknmsearch = new Button(composite_72, SWT.NONE);
		btn_nicknmsearch.setText("�˻�");
		
		btn_nicknmdel = new Button(composite_72, SWT.NONE);
		btn_nicknmdel.setText("����");
		
		lblNewLabel_124 = new Label(group_2, SWT.NONE);
		lblNewLabel_124.setText("   �귣��");
		
		composite_73 = new Composite(group_2, SWT.NONE);
		composite_73.setLayout(new GridLayout(3, false));
		composite_73.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_brand = new Text(composite_73, SWT.BORDER);
		GridData gd_txt_brand = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_brand.widthHint = 500;
		txt_brand.setLayoutData(gd_txt_brand);
		
		btn_brandsearch = new Button(composite_73, SWT.NONE);
		btn_brandsearch.setText("�˻�");
		
		btn_branddel = new Button(composite_73, SWT.NONE);
		btn_branddel.setText("����");
		
		lblNewLabel_123 = new Label(group_2, SWT.NONE);
		lblNewLabel_123.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_123.setText("* ���Խ�û�� ���� URL �� �ڵ��� ����ī�װ����� ���밡���մϴ�.");
		lblNewLabel_123.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_122 = new Label(group_2, SWT.NONE);
		lblNewLabel_122.setText("   ���Խ�û�� ���� URL");
		
		composite_71 = new Composite(group_2, SWT.NONE);
		composite_71.setLayout(new GridLayout(1, false));
		composite_71.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_phoneurl = new Text(composite_71, SWT.BORDER);
		GridData gd_txt_phoneurl = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phoneurl.widthHint = 700;
		txt_phoneurl.setLayoutData(gd_txt_phoneurl);
		
		lblNewLabel_121 = new Label(group_2, SWT.NONE);
		lblNewLabel_121.setText("   �޴��� �����");
		
		composite_70 = new Composite(group_2, SWT.NONE);
		composite_70.setLayout(new GridLayout(4, false));
		composite_70.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_phone1 = new Button(composite_70, SWT.RADIO);
		btn_phone1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btn_phone1.setText(" �Ϲݾ��� �ܸ����� (�����Ⱓ ���� �� �����ϰ� ����Ǵ� �ܸ��� ����)");
		
		btn_phone2 = new Button(composite_70, SWT.RADIO);
		btn_phone2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btn_phone2.setText(" ����� ���� �ܸ����� (����� ���� �� ������� ���� �ܸ������� ����Ǵ� �ܸ��� ����)");
		new Label(composite_70, SWT.NONE);
		
		txt_phone = new Text(composite_70, SWT.BORDER);
		GridData gd_txt_phone = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phone.widthHint = 570;
		txt_phone.setLayoutData(gd_txt_phone);
		
		btn_phonesearch = new Button(composite_70, SWT.NONE);
		btn_phonesearch.setText("�˻�");
		
		btn_phonedel = new Button(composite_70, SWT.NONE);
		btn_phonedel.setText("����");
		
		lblNewLabel_119 = new Label(group_2, SWT.NONE);
		lblNewLabel_119.setText("   ��ȿ����");
		
		composite_69 = new Composite(group_2, SWT.NONE);
		composite_69.setLayout(new GridLayout(2, false));
		composite_69.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_effecdate = new Text(composite_69, SWT.BORDER);
		GridData gd_txt_effecdate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_effecdate.widthHint = 100;
		txt_effecdate.setLayoutData(gd_txt_effecdate);
		
		lblNewLabel_120 = new Label(composite_69, SWT.NONE);
		lblNewLabel_120.setText(" �Է¿�) 2008/01/31");
		
		lblNewLabel_118 = new Label(group_2, SWT.NONE);
		lblNewLabel_118.setText("   ���ݺ񱳻���Ʈ ���");
		
		composite_68 = new Composite(group_2, SWT.NONE);
		composite_68.setLayout(new GridLayout(2, false));
		composite_68.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_costcomparisY = new Button(composite_68, SWT.RADIO);
		btn_costcomparisY.setText("����	");
		
		btn_costcomparisN = new Button(composite_68, SWT.RADIO);
		btn_costcomparisN.setText("�������");
		
		lblNewLabel_117 = new Label(group_2, SWT.NONE);
		lblNewLabel_117.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_117.setText("*  �ǸűⰣ ���� ���� �������� ������ �ǸŽ�����(��ǰ�����)�� ��ϵǰ� �������� ��ϵ��� �ʽ��ϴ�.");
		lblNewLabel_117.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_24 = new Label(group_2, SWT.NONE);
		label_24.setText("�� �ǸűⰣ ����");
		
		composite_14 = new Composite(group_2, SWT.NONE);
		composite_14.setLayout(new GridLayout(2, false));
		
		btn_dateyes = new Button(composite_14, SWT.RADIO);
		btn_dateyes.setText("������");
		
		btn_dateno = new Button(composite_14, SWT.RADIO);
		btn_dateno.setText("��������");
		
		label_25 = new Label(group_2, SWT.NONE);
		label_25.setText("�� �ǸűⰣ ����");
		
		composite_11 = new Composite(group_2, SWT.NONE);
		composite_11.setLayout(new GridLayout(6, false));
		
		btn_7day = new Button(composite_11, SWT.RADIO);
		btn_7day.setText("7��");
		
		btn_15day = new Button(composite_11, SWT.RADIO);
		btn_15day.setText("15��");
		
		btn_30day = new Button(composite_11, SWT.RADIO);
		btn_30day.setText("30��");
		
		btn_60day = new Button(composite_11, SWT.RADIO);
		btn_60day.setText("60��");
		
		btn_90day = new Button(composite_11, SWT.RADIO);
		btn_90day.setText("90��");
		
		btn_120day = new Button(composite_11, SWT.RADIO);
		btn_120day.setText("120��");
		
		lblNewLabel_116 = new Label(group_2, SWT.NONE);
		lblNewLabel_116.setText("   1���� ��ǰ");
		
		composite_67 = new Composite(group_2, SWT.NONE);
		composite_67.setLayout(new GridLayout(1, false));
		composite_67.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_1wonphone = new Button(composite_67, SWT.CHECK);
		btn_1wonphone.setText("1���� ��ǰ ����ϱ�");
		
		lblNewLabel_115 = new Label(group_2, SWT.NONE);
		lblNewLabel_115.setText("   �ɼ� ����");
		
		composite_66 = new Composite(group_2, SWT.NONE);
		composite_66.setLayout(new GridLayout(1, false));
		composite_66.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_optsetting = new Combo(composite_66, SWT.NONE);
		cb_optsetting.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_optsetting.setItems(new String[] {"��ǰ�ɼ� + �߰���ǰ","��ǰ�ɼ�"});
		cb_optsetting.select(0);
		
		lblNewLabel_114 = new Label(group_2, SWT.NONE);
		lblNewLabel_114.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_114.setText("* 1�� �ɼ��� ������ ���, \"����:������\" �� ���� �ɼ��� 1������ ���յǾ� ��ϵ˴ϴ�.\r\n" + 
				"* 2�� �ɼ��� ������ ���, \"����\", \"������\" �� ���� �ɼ��� 2������ �и��Ǿ� ��ϵ˴ϴ�.\r\n" + 
				"* 3�� �ɼ� ������ ���ϴ� ��� '2�� �ɼ�'���� ������ �ֽñ� �ٶ��ϴ�.\r\n" + 
				"\r\n" + 
				"* [���ϻ�ǰ] ��� �� ���� ����\r\n" + 
				"- 2�� �ɼǱ��� ����� �����ϸ�, 3�� �ɼ��� ����� �Ұ��մϴ�.\r\n" + 
				"- �ɼǻ󼼸�Ī, �ɼ� ������ ������ �Ұ��մϴ�.\r\n" + 
				"  ex) �ɼ� ���� ��ǰ �� 1��, 1�� �� 2��, 2�� �� 1�� �� �ɼ� ���� ���� �Ұ�\r\n" + 
				"- �ɼ��� ���� ���´� �ɼ� ������ ���� ���� ���ɿ��ΰ� �ٸ��� �����˴ϴ�.\r\n" + 
				"  1�� �ɼ� : �ɼ��� ���� ���� ������ �����մϴ�.\r\n" + 
				"  2�� �ɼ� : �ɼ��� ���� ���� ������ �Ұ��մϴ�. (API ������)\r\n" + 
				"- �߰���ǰ(�߰��ɼ�)�� ����� �Ұ��մϴ�.\r\n" + 
				"- 2�� �ɼ� ���뿩�θ� '1�� �ɼ�(�����ɼ�)'���� ���� �� ���� ��ǰ�� �ɼǵ� 1�� �ɼ��̾�� �մϴ�.\r\n" + 
				"- 2�� �ɼ� ���뿩�θ� '2�� �ɼ�'���� ���� �� 1�ܰ� 2�� �ɼ����� ����� ���������� ��� ������ 999���� �ϰ� �ݿ��˴ϴ�. (2�� �ɼ��� ��� API���� �ɼǺ� ��� ���� ������)\r\n" + 
				"- 2�� �ɼ� ���뿩�θ� '2�� �ɼ�'���� ���� �� 1�ܰ� 2���� �ɼ��� ��� ���յǾ� ��ϵ˴ϴ�. (11���� ��å)\r\n" + 
				"- ������ �ۼ��� �ɼ��� 1������ ����� �����մϴ�. (11���� ��å)");
		lblNewLabel_114.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_113 = new Label(group_2, SWT.NONE);
		lblNewLabel_113.setText("   2�� �ɼ� ���뿩��");
		
		composite_65 = new Composite(group_2, SWT.NONE);
		composite_65.setLayout(new GridLayout(1, false));
		composite_65.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_2optiondefault = new Combo(composite_65, SWT.NONE);
		cb_2optiondefault.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_2optiondefault.setItems(new String[] {"1�� �ɼ�(�������)","2�� �ɼ�"});
		cb_2optiondefault.select(0);
		
		lblNewLabel_112 = new Label(group_2, SWT.NONE);
		lblNewLabel_112.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_112.setText("* ���������� �ɼ��� ��ϵ� ��� �ɼǰ� ���� ����� ��ϼ�, �Ǵ� �ɼǰ� �����ټ�, �ɼǰ� �����ٿ����� ������ �ֽñ� �ٶ��ϴ�.");
		lblNewLabel_112.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_111 = new Label(group_2, SWT.NONE);
		lblNewLabel_111.setText("   �ɼǰ� ������");
		
		composite_64 = new Composite(group_2, SWT.NONE);
		composite_64.setLayout(new GridLayout(5, false));
		composite_64.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_optval1 = new Button(composite_64, SWT.RADIO);
		btn_optval1.setText("��ϼ�");
		
		btn_optval2 = new Button(composite_64, SWT.RADIO);
		btn_optval2.setText("�ɼǰ� �����ټ�");
		
		btn_optval3 = new Button(composite_64, SWT.RADIO);
		btn_optval3.setText("�ɼǰ� �����ٿ���");
		
		btn_optval4 = new Button(composite_64, SWT.RADIO);
		btn_optval4.setText("�ɼǰ��� ������");
		
		btn_optval5 = new Button(composite_64, SWT.RADIO);
		btn_optval5.setText("�ɼǰ��� ������");
		
		lblNewLabel_100 = new Label(group_2, SWT.NONE);
		lblNewLabel_100.setText("   ������ �ۼ��� �ɼ�");
		
		composite_63 = new Composite(group_2, SWT.NONE);
		composite_63.setLayout(new GridLayout(4, false));
		composite_63.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_optionN = new Button(composite_63, SWT.RADIO);
		btn_optionN.setText("������");
		
		btn_optionY = new Button(composite_63, SWT.RADIO);
		btn_optionY.setText("�����");
		
		lblNewLabel_101 = new Label(composite_63, SWT.NONE);
		lblNewLabel_101.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_101.setText("   �ɼǸ�1 :  ");
		
		txt_option1 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option1.widthHint = 500;
		txt_option1.setLayoutData(gd_txt_option1);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_102 = new Label(composite_63, SWT.NONE);
		lblNewLabel_102.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_102.setText("   �ɼǸ�2 :  ");
		
		txt_option2 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option2.widthHint = 500;
		txt_option2.setLayoutData(gd_txt_option2);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_103 = new Label(composite_63, SWT.NONE);
		lblNewLabel_103.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_103.setText("   �ɼǸ�3 :  ");
		
		txt_option3 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option3 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option3.widthHint = 500;
		txt_option3.setLayoutData(gd_txt_option3);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_104 = new Label(composite_63, SWT.NONE);
		lblNewLabel_104.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_104.setText("   �ɼǸ�4 :  ");
		
		txt_option4 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option4 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option4.widthHint = 500;
		txt_option4.setLayoutData(gd_txt_option4);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_105 = new Label(composite_63, SWT.NONE);
		lblNewLabel_105.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_105.setText("   �ɼǸ�5 :  ");
		
		txt_option5 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option5 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option5.widthHint = 500;
		txt_option5.setLayoutData(gd_txt_option5);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_106 = new Label(composite_63, SWT.NONE);
		lblNewLabel_106.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_106.setText("   �ɼǸ�6 :  ");
		
		txt_option6 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option6 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option6.widthHint = 500;
		txt_option6.setLayoutData(gd_txt_option6);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_107 = new Label(composite_63, SWT.NONE);
		lblNewLabel_107.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_107.setText("   �ɼǸ�7 :  ");
		
		txt_option7 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option7 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option7.widthHint = 500;
		txt_option7.setLayoutData(gd_txt_option7);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_108 = new Label(composite_63, SWT.NONE);
		lblNewLabel_108.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_108.setText("   �ɼǸ�8 :  ");
		
		txt_option8 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option8 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option8.widthHint = 500;
		txt_option8.setLayoutData(gd_txt_option8);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_109 = new Label(composite_63, SWT.NONE);
		lblNewLabel_109.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_109.setText("   �ɼǸ�9 :  ");
		
		txt_option9 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option9 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option9.widthHint = 500;
		txt_option9.setLayoutData(gd_txt_option9);
		new Label(composite_63, SWT.NONE);
		new Label(composite_63, SWT.NONE);
		
		lblNewLabel_110 = new Label(composite_63, SWT.NONE);
		lblNewLabel_110.setText("   �ɼǸ�10 : ");
		
		txt_option10 = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_option10 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_option10.widthHint = 500;
		txt_option10.setLayoutData(gd_txt_option10);
		
		lblNewLabel_99 = new Label(group_2, SWT.NONE);
		lblNewLabel_99.setText("   ���޻� ��ǰ URL");
		
		composite_62 = new Composite(group_2, SWT.NONE);
		composite_62.setLayout(new GridLayout(1, false));
		composite_62.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_url = new Text(composite_62, SWT.BORDER);
		GridData gd_txt_url = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_url.widthHint = 700;
		txt_url.setLayoutData(gd_txt_url);
		
		lblNewLabel_96 = new Label(group_2, SWT.NONE);
		lblNewLabel_96.setText("   �����");
		
		composite_61 = new Composite(group_2, SWT.NONE);
		composite_61.setLayout(new GridLayout(5, false));
		composite_61.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_97 = new Label(composite_61, SWT.NONE);
		lblNewLabel_97.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_97.setText("���� �����");
		
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
		lblNewLabel_98.setText("	������ �����");
		
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
		btn_datedelete.setText("����");
		btn_datedelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_strdate.setText("");
				txt_enddate.setText("");
			}
		});
		
		lblNewLabel_92 = new Label(group_2, SWT.NONE);
		lblNewLabel_92.setText("   �����Ǿ� ���� ����");
		
		composite_60 = new Composite(group_2, SWT.NONE);
		composite_60.setLayout(new GridLayout(5, false));
		composite_60.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_benepia1 = new Button(composite_60, SWT.CHECK);
		btn_benepia1.setText(" ������");
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
		lblNewLabel_93.setText(" % ����");
		new Label(composite_60, SWT.NONE);
		
		btn_benepia2 = new Button(composite_60, SWT.CHECK);
		btn_benepia2.setText(" �Ⱓ����");
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
		lblNewLabel_95.setText(" * ��¥�� 2017/01/01 �������� �Է��Ͽ� �ּ���");
		
		lblNewLabel_90 = new Label(group_2, SWT.NONE);
		lblNewLabel_90.setText("   �ּұ��ż���");
		
		composite_59 = new Composite(group_2, SWT.NONE);
		composite_59.setLayout(new GridLayout(3, false));
		composite_59.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_minlimit1 = new Button(composite_59, SWT.RADIO);
		btn_minlimit1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		btn_minlimit1.setText(" ���Ѿ��� :�ּұ��ż��� ���Ѿ���");
		
		btn_minlimit2 = new Button(composite_59, SWT.RADIO);
		btn_minlimit2.setText(" 1ȸ ���� : ");
		
		txt_minlimit2_1 = new Text(composite_59, SWT.BORDER);
		GridData gd_txt_minlimit2_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_minlimit2_1.widthHint = 100;
		txt_minlimit2_1.setLayoutData(gd_txt_minlimit2_1);
		
		lblNewLabel_91 = new Label(composite_59, SWT.NONE);
		lblNewLabel_91.setText(" �� (1ȸ ���� ��, �ּ� ������ �� �ִ� ������ �����մϴ�.)");
		
		lblNewLabel_84 = new Label(group_2, SWT.NONE);
		lblNewLabel_84.setText("   �ִ뱸�ż���");
		
		composite_55 = new Composite(group_2, SWT.NONE);
		composite_55.setLayout(new GridLayout(7, false));
		composite_55.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		composite_56 = new Composite(composite_55, SWT.NONE);
		composite_56.setLayout(new GridLayout(1, false));
		composite_56.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit1 = new Button(composite_56, SWT.RADIO);
		btn_limit1.setText(" ���Ѿ��� : �ִ뱸�ż��� ���Ѿ���");
		
		composite_57 = new Composite(composite_55, SWT.NONE);
		composite_57.setLayout(new GridLayout(3, false));
		composite_57.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit2 = new Button(composite_57, SWT.RADIO);
		btn_limit2.setText(" 1ȸ ���� : ");
		
		txt_limit2_1 = new Text(composite_57, SWT.BORDER);
		GridData gd_txt_limit2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit2_1.widthHint = 100;
		txt_limit2_1.setLayoutData(gd_txt_limit2_1);
		
		lblNewLabel_85 = new Label(composite_57, SWT.NONE);
		lblNewLabel_85.setText(" �� (1ȸ ���� ��, �ִ� ������ �� �ִ� ������ �����մϴ�.)");
		
		composite_58 = new Composite(composite_55, SWT.NONE);
		composite_58.setLayout(new GridLayout(7, false));
		composite_58.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));
		
		btn_limit3 = new Button(composite_58, SWT.RADIO);
		btn_limit3.setText(" �Ⱓ ���� : �� ID��");
		
		txt_limit3_1 = new Text(composite_58, SWT.BORDER);
		GridData gd_txt_limit3_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit3_1.widthHint = 100;
		txt_limit3_1.setLayoutData(gd_txt_limit3_1);
		
		lblNewLabel_86 = new Label(composite_58, SWT.NONE);
		lblNewLabel_86.setText(" �� ���� �ִ�");
		
		txt_limit3_2 = new Text(composite_58, SWT.BORDER);
		GridData gd_txt_limit3_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_limit3_2.widthHint = 100;
		txt_limit3_2.setLayoutData(gd_txt_limit3_2);
		
		lblNewLabel_87 = new Label(composite_58, SWT.NONE);
		lblNewLabel_87.setText("�� ���� ���Ű����մϴ�. (�Ⱓ�� �ִ�");
		
		lblNewLabel_88 = new Label(composite_58, SWT.NONE);
		lblNewLabel_88.setText(" 30�ϱ��� ");
		lblNewLabel_88.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_89 = new Label(composite_58, SWT.NONE);
		lblNewLabel_89.setText("�̸�, ��ȸ���� �������� ���� ���� �����մϴ�. )");
		
		lblNewLabel_77 = new Label(group_2, SWT.NONE);
		lblNewLabel_77.setText("   ����ǰ ����");
		
		composite_54 = new Composite(group_2, SWT.NONE);
		composite_54.setLayout(new GridLayout(5, false));
		composite_54.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_78 = new Label(composite_54, SWT.NONE);
		lblNewLabel_78.setText("����ǰ��");
		
		txt_giftnm = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_giftnm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_txt_giftnm.widthHint = 300;
		txt_giftnm.setLayoutData(gd_txt_giftnm);
		
		lblNewLabel_79 = new Label(composite_54, SWT.NONE);
		lblNewLabel_79.setText("�Ⱓ����");
		
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
		btn_giftperioddel.setText("����");
		btn_giftperioddel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_giftperiodst.setText("");
				txt_giftperioden.setText("");
			}
		});
		
		lblNewLabel_80 = new Label(composite_54, SWT.NONE);
		lblNewLabel_80.setText("����ǰ ����");
		
		txt_gift = new Text(composite_54, SWT.BORDER | SWT.MULTI);
		GridData gd_txt_gift = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_txt_gift.heightHint = 50;
		gd_txt_gift.widthHint = 400;
		txt_gift.setLayoutData(gd_txt_gift);
		
		lblNewLabel_81 = new Label(composite_54, SWT.NONE);
		lblNewLabel_81.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1));
		lblNewLabel_81.setText("(�Ⱓ ������ ���� ����ǰ ���� �Ⱓ�� ������ �� �ֽ��ϴ�.)");
		
		lblNewLabel_82 = new Label(composite_54, SWT.NONE);
		lblNewLabel_82.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1));
		lblNewLabel_82.setText("(���� ����ǰ�� �ƴ� ����(������, ���Ϲ�� ��) ���� �� ��ǰ �Ǹ� ���� ��ġ ���� �������� ���� �� �ֽ��ϴ�.)");
		
		lblNewLabel_76 = new Label(group_2, SWT.NONE);
		lblNewLabel_76.setText("   ������� ���ø� ���");
		
		composite_53 = new Composite(group_2, SWT.NONE);
		composite_53.setLayout(new GridLayout(2, false));
		composite_53.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_exptemplate = new Text(composite_53, SWT.BORDER);
		GridData gd_txt_exptemplate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_exptemplate.widthHint = 600;
		txt_exptemplate.setLayoutData(gd_txt_exptemplate);
		
		btn_exptempsearch = new Button(composite_53, SWT.NONE);
		btn_exptempsearch.setText("�˻�");
		
		lblNewLabel_75 = new Label(group_2, SWT.NONE);
		lblNewLabel_75.setText("   ��۰�������");
		
		composite_52 = new Composite(group_2, SWT.NONE);
		composite_52.setLayout(new GridLayout(1, false));
		composite_52.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_exparea = new Combo(composite_52, SWT.NONE);
		cb_exparea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_exparea.setItems(new String[] {"���ݻ�ǰ���� ����", "����", "����(���� �����갣���� ����)", "����", "��õ","����", "�뱸", "����","�λ�", "���", "���","����", "�泲", "���","�泲", "���","����", "����", "����","����/���", 
				"����/���/����", "���/�泲","���/�泲","����/����", "�λ�/���","����/���/���ֵ����갣 ����","�Ϻ������Ұ�"});
		cb_exparea.select(0);
		
		lblNewLabel_74 = new Label(group_2, SWT.NONE);
		lblNewLabel_74.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_74.setText("* �湮���� �߰� �׸� üũ �� ��� �湮���� �ּҸ� �Է��� �ֽñ� �ٶ��ϴ�. �湮���� �߰� �׸� üũ�� �湮���� �ּҴ� �ʼ����Դϴ�.");
		lblNewLabel_74.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_73 = new Label(group_2, SWT.NONE);
		lblNewLabel_73.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_73.setText("* [���ϻ�ǰ]�湮���� ������(API �湮���� ������)");
		lblNewLabel_73.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		label_21 = new Label(group_2, SWT.NONE);
		label_21.setText("�� ��۹��");
		
		Composite composite_8 = new Composite(group_2, SWT.NONE);
		composite_8.setLayout(new GridLayout(2, false));
		
		cb_world = new Combo(composite_8, SWT.NONE);
		cb_world.setItems(new String[] {"�ù�", "����(���/����)", "��������", "������", "����ʿ����"});
		cb_world.select(0);
		
		btn_world = new Button(composite_8, SWT.CHECK);
		btn_world.setText("  �湮���� �߰�");
		
		label_2 = new Label(group_2, SWT.NONE);
		label_2.setText("   �߼��ù��");
		
		composite_51 = new Composite(group_2, SWT.NONE);
		composite_51.setLayout(new GridLayout(2, false));
		composite_51.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_exp = new Combo(composite_51, SWT.NONE);
		cb_exp.setItems(new String[] {"�����ϼ���"});
		cb_exp.select(0);
		scb_express = new ShopCombo(cb_exp);
		
		lblNewLabel_72 = new Label(composite_51, SWT.NONE);
		lblNewLabel_72.setText("	* ��۹���� �ù��� ��� �ʼ��� �����Ͽ� �ּ���");
		lblNewLabel_72.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_71 = new Label(group_2, SWT.NONE);
		lblNewLabel_71.setText("   �߼ۿ�����");
		
		composite_50 = new Composite(group_2, SWT.NONE);
		composite_50.setLayout(new GridLayout(3, false));
		composite_50.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_shipmentdate = new Text(composite_50, SWT.BORDER);
		GridData gd_txt_shipmentdate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_shipmentdate.widthHint = 600;
		txt_shipmentdate.setLayoutData(gd_txt_shipmentdate);
		
		btn_shipmentsearch = new Button(composite_50, SWT.NONE);
		btn_shipmentsearch.setText("�˻�");
		btn_shipmentsearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openShipmentdate();
			}
		});
		
		btn_shipmentdel = new Button(composite_50, SWT.NONE);
		btn_shipmentdel.setText("����");
		btn_shipmentdel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_shipmentdate.setText("");

			}
		});
		lblNewLabel_70 = new Label(group_2, SWT.NONE);
		lblNewLabel_70.setText("   �湮���� �ּ�");
		
		composite_49 = new Composite(group_2, SWT.NONE);
		composite_49.setLayout(new GridLayout(3, false));
		composite_49.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txt_visit = new Text(composite_49, SWT.BORDER);
		GridData gd_txt_visit = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_visit.widthHint = 600;
		txt_visit.setLayoutData(gd_txt_visit);
		
		btn_visitsearch = new Button(composite_49, SWT.NONE);
		btn_visitsearch.setText("�˻�");
		
		btn_visitdel = new Button(composite_49, SWT.NONE);
		btn_visitdel.setText("����");
		
		label_26 = new Label(group_2, SWT.NONE);
		label_26.setText("   ���������̿뿩��");
		
		composite_20 = new Composite(group_2, SWT.NONE);
		composite_20.setLayout(new GridLayout(2, false));
		
		btn_worldyes = new Button(composite_20, SWT.RADIO);
		btn_worldyes.setText("�����");
		
		btn_worldno = new Button(composite_20, SWT.RADIO);
		btn_worldno.setText("������");
		
		label_27 = new Label(group_2, SWT.NONE);
		label_27.setText("   �������۽û�ǰ����");
		
		composite_21 = new Composite(group_2, SWT.NONE);
		composite_21.setLayout(new GridLayout(2, false));
		
		btn_weight = new Button(composite_21, SWT.RADIO);
		btn_weight.setText("��ǰ�� �Ӽ�����");
		
		btn_weight300 = new Button(composite_21, SWT.RADIO);
		btn_weight300.setText("�⺻����(300g)");	
		
		composite_47 = new Composite(group_2, SWT.NONE);
		composite_47.setLayout(new GridLayout(1, false));
		composite_47.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		label_28 = new Label(composite_47, SWT.NONE);
		label_28.setText("   ��.�ΰ��� H.S�ڵ�");
		
		lblNewLabel_64 = new Label(composite_47, SWT.NONE);
		lblNewLabel_64.setText("   (�ؿ����� ī�װ�)");
		lblNewLabel_64.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		composite_48 = new Composite(group_2, SWT.NONE);
		composite_48.setLayout(new GridLayout(1, false));
		composite_48.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_hscode = new Combo(composite_48, SWT.NONE);
		cb_hscode.setItems(new String[] {"�����ϼ���","01��-������ǰ","3300��-ȭ��ǰ","3100��-���","34��-�񴩷�","39��-�ö�ƽ��ǰ��","40��-����ǰ��","42��-[EU:�����_�ռ�����(������ȭ���,��Ÿ)]","42��-[EU:������_�ռ�����(�����췹ź)]"
				,"42��-[EU��EU��:��Ʈ_����]","42��-[�̱�:����/����][EU:�����_����,õ,�ռ�����(�����췹ź)][EU��:�����_����,õ,�ռ�����]","42��-[�̱�:������ǰ][EU:������_����,õ,�ռ�����(������ȭ���,��Ÿ)][EU��:������_����,õ,�ռ�����]","42��-[EU��EU��:ŰȦ��_����������Ѽ���]"
				,"48��-������ǰ��","48��-������ǰ��","48��-ȭ����/�ֹ����","4900��-������ǰ","4910��-����/�μ⹰��","55��-������Ʈ��","61��-�縻��","61��-�Ƿ�","61��-�尩��","62��-[�̱�:�Ƿ��μ�ǰ][EU��EU��:�Ƿ�_�Ƿ�,��Ÿ��,��ī��]","62��-������ǰ��","63��-������ǰ"
				,"64��-[�̱�:�Ź߷�][EU��EU��:�Ź߷�_����,����]","65��-����","66��-���","71��-[�̱�:�־�][EU��EU��:�־�_�Ͱ���,����,�����]","73��-ö���ֹ��ǰ","84��-���� �� ����ǰ","85��-����/��ȭ�� CD��","87��-��������","87��-�ڵ�����ǰ","87��-������","90��-�Ȱ�"
				,"91��-�ð�","9400��-����/���׸���","9410��-ħ��/Ŀư/���/�Ҹ�ǰ","9420��-����/�������","94��-������","94��-���ڷ�","95��-�Ϸ�/���ÿ�ǰ","95��-��뱸","96��-������"});
		cb_hscode.select(0);
		
		lblNewLabel_65 = new Label(composite_48, SWT.NONE);
		lblNewLabel_65.setText("����û�� �Ű�Ǵ� H.S Code�Դϴ�.");
		
		lblNewLabel_66 = new Label(composite_48, SWT.NONE);
		lblNewLabel_66.setText("�ؿ����չ�ۻ�ǰ�� ���¸� ����ۻ�ǰ�� ��츸 ���� �ʿ�.");
		
		lblNewLabel_67 = new Label(composite_48, SWT.NONE);
		lblNewLabel_67.setText("��ǰ�űԵ�Ͻ�, �ش� ī�װ��� ���õ� HSCode�� ����Ʈ ���� �˴ϴ�.");
		
		lblNewLabel_68 = new Label(composite_48, SWT.NONE);
		lblNewLabel_68.setText("�׷��� �ش�ī�װ��� ���õ� HSCode�� ��ǰ�� ������ �ٸ� ���, ��ǰ�� �´� HSCode�� �����ϼž��ϸ�");
		
		lblNewLabel_69 = new Label(composite_48, SWT.NONE);
		lblNewLabel_69.setText("�߸� ����Ͽ� ������ �߻��� ��� �����в��� �ذ��ϼž� �մϴ�.(��: ����� ������, �����߰�¡����)");
		
		lblNewLabel_63 = new Label(group_2, SWT.NONE);
		lblNewLabel_63.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_63.setText("* ����� �� ��ȯ/��ǰ�� �ּҴ� ��ǰ�۽� ��, �ݵ�� 11���� SCM ��ǰ���ȭ�鿡�� 1�� �̻� ����ؾ��մϴ�.");
		lblNewLabel_63.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_62 = new Label(group_2, SWT.NONE);
		lblNewLabel_62.setText("   ����� �ּ� �ؿ� ����");
		
		composite_46 = new Composite(group_2, SWT.NONE);
		composite_46.setLayout(new GridLayout(2, false));
		composite_46.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_outaddr1 = new Button(composite_46, SWT.RADIO);
		btn_outaddr1.setText("����	");

		btn_outaddr2 = new Button(composite_46, SWT.RADIO);
		btn_outaddr2.setText("�ؿ�");
		
		label_29 = new Label(group_2, SWT.NONE);
		label_29.setText("�� ����� �ּ�");
		
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
		btn_factaddr.setText("�˻�");
		btn_factaddr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getFactoryAddress(true);
			}							
		});
		
		lblNewLabel_61 = new Label(group_2, SWT.NONE);
		lblNewLabel_61.setText("   ��ȯ/��ǰ�� �ּ� �ؿ� ����");
		
		composite_45 = new Composite(group_2, SWT.NONE);
		composite_45.setLayout(new GridLayout(2, false));
		composite_45.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_cngNretaddr1 = new Button(composite_45, SWT.RADIO);
		btn_cngNretaddr1.setText("����	");

		btn_cngNretaddr2 = new Button(composite_45, SWT.RADIO);
		btn_cngNretaddr2.setText("�ؿ�");
		
		label_30 = new Label(group_2, SWT.NONE);
		label_30.setText("�� ��ȯ/��ǰ�� �ּ�");
		
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
		btn_rtnaddr.setText("�˻�");
		btn_rtnaddr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getFactoryAddress(false);
			}							
		});
		
		lblNewLabel_60 = new Label(group_2, SWT.NONE);
		lblNewLabel_60.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_60.setText("* ��ۺ� ������ �Ǹ��� ���Ǻ� ���� ���ý�, 11���� SCM > �Ǹ��������� ȭ�鿡�� �Ǹ��� ���Ǻ� ���� ������ �����Ǿ� �ִ��� Ȯ���ϼž� �մϴ�.");
		lblNewLabel_60.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		Composite composite = new Composite(group_2, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 17, 1);
		gd_composite.heightHint = 294;
		composite.setLayoutData(gd_composite);
		
		lblNewLabel_59 = new Label(composite, SWT.NONE);
		lblNewLabel_59.setText("�� ��ۺ� ����");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		
		composite_2 = new Composite(composite_1, SWT.BORDER);
		composite_2.setBounds(0, 5, 192, 245);
		
		btn_free = new Button(composite_2, SWT.RADIO);
		btn_free.setText("����");
		btn_free.setBounds(5, 5, 50, 20);
		
		btn_condifree = new Button(composite_2, SWT.RADIO);
		btn_condifree.setBounds(5, 83, 140, 20);
		btn_condifree.setText("��ǰ ���Ǻ� ����");
		
		btn_fixing = new Button(composite_2, SWT.RADIO);
		btn_fixing.setBounds(5, 216, 130, 20);
		btn_fixing.setText("���� ��ۺ�");
		
		btn_seller = new Button(composite_2, SWT.RADIO);
		btn_seller.setText("�Ǹ��� ���Ǻ� ��ۺ�");
		btn_seller.setBounds(5, 29, 180, 20);
		
		btn_factory = new Button(composite_2, SWT.RADIO);
		btn_factory.setText("����� ���Ǻ� ��ۺ�");
		btn_factory.setBounds(5, 55, 180, 20);
		
		btn_differ = new Button(composite_2, SWT.RADIO);
		btn_differ.setText("������ ����");
		btn_differ.setBounds(5, 130, 130, 20);
		
		btn_one = new Button(composite_2, SWT.RADIO);
		btn_one.setText("1���� ��ۺ�");
		btn_one.setBounds(5, 185, 130, 20);
		
		composite_10 = new Composite(composite_1, SWT.BORDER);
		composite_10.setBounds(0, 248, 1154, 35);
		
		txt_jeju = new Text(composite_10, SWT.BORDER);
		txt_jeju.setBounds(50, 3, 159, 24);
		
		txt_island = new Text(composite_10, SWT.BORDER);
		txt_island.setBounds(370, 3, 159, 24);
		
		label_11 = new Label(composite_10, SWT.NONE);
		label_11.setText("����");
		label_11.setBounds(5, 5, 40, 20);
		
		label_12 = new Label(composite_10, SWT.NONE);
		label_12.setText("��");
		label_12.setBounds(212, 5, 20, 20);
		
		label_13 = new Label(composite_10, SWT.NONE);
		label_13.setText("�����갣");
		label_13.setBounds(290, 5, 70, 20);
		
		label_14 = new Label(composite_10, SWT.NONE);
		label_14.setText("��");
		label_14.setBounds(532, 5, 20, 20);
		
		Composite composite_9 = new Composite(composite_1, SWT.BORDER);
		composite_9.setBounds(190, 117, 964, 67);
		
		label_40 = new Label(composite_9, SWT.NONE);
		label_40.setBounds(5, 7, 50, 20);
		label_40.setText("1�� ~");
		
		txt_diffqty = new Text(composite_9, SWT.BORDER);
		txt_diffqty.setBounds(55, 5, 80, 24);
		
		label_41 = new Label(composite_9, SWT.NONE);
		label_41.setBounds(138, 7, 20, 20);
		label_41.setText("��");
		
		txt_diffprice = new Text(composite_9, SWT.BORDER);
		txt_diffprice.setBounds(158, 5, 150, 24);
		
		label_42 = new Label(composite_9, SWT.NONE);
		label_42.setBounds(311, 7, 20, 20);
		label_42.setText("��");
		
		label_44 = new Label(composite_9, SWT.NONE);
		label_44.setBounds(301, 36, 20, 20);
		label_44.setText("\uC6D0");
		
		txt_diffprice1 = new Text(composite_9, SWT.BORDER);
		txt_diffprice1.setBounds(148, 34, 150, 24);
		
		label_43 = new Label(composite_9, SWT.NONE);
		label_43.setBounds(88, 36, 60, 20);
		label_43.setText("�� �̻�");
		
		txt_diffqty1 = new Text(composite_9, SWT.BORDER);
		txt_diffqty1.setBounds(5, 34, 80, 24);
		
		Composite composite_22 = new Composite(composite_1, SWT.BORDER);
		composite_22.setBounds(190, 5, 964, 27);
		
		label_37 = new Label(composite_22, SWT.NONE);
		label_37.setBounds(5, 2, 100, 20);
		label_37.setText("0 ��");
		
		Label lblNewLabel = new Label(composite_22, SWT.NONE);
		lblNewLabel.setBounds(230, 2, 570, 20);
		lblNewLabel.setText("����/�ֹ��ݾ׿� ������� ������ ����");
		
		Composite composite_24 = new Composite(composite_1, SWT.BORDER);
		composite_24.setBounds(190, 31, 964, 26);
		
		label_38 = new Label(composite_24, SWT.NONE);
		label_38.setBounds(5, 2, 100, 20);
		label_38.setText("-- ��");
		
		lblNewLabel_1 = new Label(composite_24, SWT.NONE);
		lblNewLabel_1.setBounds(230, 1, 570, 20);
		lblNewLabel_1.setText("(11���� SCM > �Ǹ��������� > �⺻ ��ۺ� ��å ��������)");
		
		Composite composite_23 = new Composite(composite_1, SWT.BORDER);
		composite_23.setBounds(190, 56, 964, 26);
		
		label_39 = new Label(composite_23, SWT.NONE);
		label_39.setBounds(5, 2, 100, 20);
		label_39.setText("-- ��");
		
		label_46 = new Label(composite_23, SWT.NONE);
		label_46.setText("(11���� SCM > ��ǰ���ȭ�� > ����� ���Ǻ� ��ۺ� ��å ��������)");
		label_46.setBounds(230, 1, 570, 20);
		
		Composite composite_25 = new Composite(composite_1, SWT.BORDER);
		composite_25.setBounds(190, 79, 964, 40);
		
		label_9 = new Label(composite_25, SWT.NONE);
		label_9.setBounds(169, 7, 20, 20);
		label_9.setText("��");
		
		txt_condifree = new Text(composite_25, SWT.BORDER);
		txt_condifree.setBounds(5, 5, 159, 24);
		
		lblNewLabel_2 = new Label(composite_25, SWT.NONE);
		lblNewLabel_2.setBounds(230, 7, 80, 20);
		lblNewLabel_2.setText("������ǰ��");
		
		label_47 = new Label(composite_25, SWT.NONE);
		label_47.setText("�� �̻� �ֹ��� ����");
		label_47.setBounds(433, 7, 150, 20);
		
		txt_condifree1 = new Text(composite_25, SWT.BORDER);
		txt_condifree1.setBounds(310, 5, 120, 24);
		
		composite_26 = new Composite(composite_1, SWT.BORDER);
		composite_26.setBounds(190, 182, 964, 35);
		
		txt_oneprice = new Text(composite_26, SWT.BORDER);
		txt_oneprice.setBounds(5, 3, 159, 24);
		
		label_45 = new Label(composite_26, SWT.NONE);
		label_45.setText("��");
		label_45.setBounds(165, 5, 20, 20);
		
		lblNewLabel_3 = new Label(composite_26, SWT.NONE);
		lblNewLabel_3.setBounds(230, 5, 570, 20);
		lblNewLabel_3.setText("���� 1������ ��ۺ� �߰�");
		
		composite_27 = new Composite(composite_1, SWT.BORDER);
		composite_27.setBounds(190, 213, 964, 37);
		
		txt_fixing = new Text(composite_27, SWT.BORDER);
		txt_fixing.setBounds(5, 3, 159, 24);
		
		label_10 = new Label(composite_27, SWT.NONE);
		label_10.setBounds(165, 5, 20, 20);
		label_10.setText("��");
		
		lblNewLabel_4 = new Label(composite_27, SWT.NONE);
		lblNewLabel_4.setBounds(230, 5, 570, 20);
		lblNewLabel_4.setText("����/�ֹ��ݾ׿� ������� ���� ��ۺ�");

		
		label_16 = new Label(group_2, SWT.NONE);
		label_16.setBounds(5, 6, 160, 20);
		label_16.setText("�� ��ۺ� ����������");
		
		composite_16 = new Composite(group_2, SWT.NONE);
		composite_16.setBounds(3, 3, 570, 32);
		composite_16.setLayout(new GridLayout(1, false));
		
		cb_prepay = new Combo(composite_16, SWT.NONE);
		cb_prepay.setItems(new String[] {"����������", "�������Ұ�", "�������ʼ�"});
		cb_prepay.select(0);
		
		label_17 = new Label(group_2, SWT.NONE);
		label_17.setBounds(5, 6, 160, 20);
		label_17.setText("   �������");
		
		composite_17 = new Composite(group_2, SWT.NONE);
		composite_17.setBounds(3, 41, 570, 32);
		composite_17.setLayout(new GridLayout(2, false));
		
		btn_bundleyse = new Button(composite_17, SWT.RADIO);
		btn_bundleyse.setText("����");
		
		btn_bundleno = new Button(composite_17, SWT.RADIO);
		btn_bundleno.setText("�Ұ���");

		
		lblNewLabel_55 = new Label(group_2, SWT.NONE);
		lblNewLabel_55.setText("�� ��ȯ/��ǰ ��ۺ�");
		
		composite_44 = new Composite(group_2, SWT.NONE);
		composite_44.setLayout(new GridLayout(7, false));
		composite_44.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_56 = new Label(composite_44, SWT.NONE);
		lblNewLabel_56.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_56.setText("��ǰ ��ۺ� : �� ");
		
		txt_oneway = new Text(composite_44, SWT.BORDER);
		txt_oneway.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_57 = new Label(composite_44, SWT.NONE);
		lblNewLabel_57.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_57.setText("  �� /   ��ȯ ��ۺ� : �պ� ");
		
		txt_round = new Text(composite_44, SWT.BORDER);
		txt_round.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_58 = new Label(composite_44, SWT.NONE);
		lblNewLabel_58.setText("  ��   �� �ʱ��ۺ� ����� ��ǰ ��ۺ� �ΰ����  ");
		
		btn_roundyes = new Button(composite_44, SWT.RADIO);
		btn_roundyes.setText("�պ�(��*2)");
		
		btn_roundno = new Button(composite_44, SWT.RADIO);
		btn_roundno.setText("��");
		
		label_33 = new Label(group_2, SWT.NONE);
		label_33.setText("�� A/S�ȳ�");
		
		txt_as = new Text(group_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_txt_as = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_as.heightHint = 50;
		gd_txt_as.widthHint = 700;
		txt_as.setLayoutData(gd_txt_as);
		
		Label label_34 = new Label(group_2, SWT.NONE);
		label_34.setText("�� ��ȯ/��ǰ�ȳ�");
		
		txt_change = new Text(group_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_txt_change = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_change.heightHint = 50;
		gd_txt_change.widthHint = 700;
		txt_change.setLayoutData(gd_txt_change);
		
		composite_43 = new Composite(group_2, SWT.NONE);
		composite_43.setLayout(new GridLayout(2, false));
		composite_43.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_52 = new Label(composite_43, SWT.NONE);
		lblNewLabel_52.setText("  ������������");
		
		btn_multidiscnt = new Button(composite_43, SWT.CHECK);
		
		composite_42 = new Composite(group_2, SWT.NONE);
		composite_42.setLayout(new GridLayout(6, false));
		composite_42.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_multidiscntcnt = new Combo(composite_42, SWT.NONE);
		cb_multidiscntcnt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_multidiscntcnt.setItems(new String[] {"��������","�ݾױ���"});
		cb_multidiscntcnt.select(0);
		
		txt_multidiscntcnt = new Text(composite_42, SWT.BORDER);
		GridData gd_txt_multidiscntcnt = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_multidiscntcnt.widthHint = 100;
		txt_multidiscntcnt.setLayoutData(gd_txt_multidiscntcnt);
		
		lblNewLabel_53 = new Label(composite_42, SWT.NONE);
		lblNewLabel_53.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_53.setText("  �̻� ���Ž� �ǸŰ�(+�ɼǰ�)���� ����  ");
		
		txt_multidiscntwon = new Text(composite_42, SWT.BORDER);
		GridData gd_txt_multidiscntwon = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_multidiscntwon.widthHint = 100;
		txt_multidiscntwon.setLayoutData(gd_txt_multidiscntwon);
		
		cb_multidiscntwon = new Combo(composite_42, SWT.NONE);
		cb_multidiscntwon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_multidiscntwon.setItems(new String[] {"��","%"});
		cb_multidiscntwon.select(0);
		
		lblNewLabel_54 = new Label(composite_42, SWT.NONE);
		lblNewLabel_54.setText("����");
		
		lblNewLabel_51 = new Label(group_2, SWT.NONE);
		lblNewLabel_51.setText("   ����ó����");
		
		composite_41 = new Composite(group_2, SWT.NONE);
		composite_41.setLayout(new GridLayout(1, false));
		composite_41.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_buy = new Combo(composite_41, SWT.NONE);
		GridData gd_cb_buy = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cb_buy.widthHint = 340;
		cb_buy.setLayoutData(gd_cb_buy);
		cb_buy.setItems(new String[] {"������","A : �ش� �귣�� ���� ����","B : �ش� �귣�� ���� ��,�������� ����(��ȭ������)","C : �������� �ƿ﷿","D : ���� �¶��� ���θ�","E : A~D�� �ش���� �ʴ� ����ó(��� ��)"});
		cb_buy.select(0);
		
		lblNewLabel_50 = new Label(group_2, SWT.NONE);
		lblNewLabel_50.setText("   ��ǥ�̹��� ����");
		
		composite_40 = new Composite(group_2, SWT.NONE);
		composite_40.setLayout(new GridLayout(1, false));
		composite_40.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_repimg = new Combo(composite_40, SWT.NONE);
		cb_repimg.setItems(new String[] {"�����ϼ���","�ΰ��̹���6","�ΰ��̹���7","�ΰ��̹���8","�ΰ��̹���9","�ΰ��̹���10","�ΰ��̹���11","�ΰ��̹���12","�ΰ��̹���13","�ΰ��̹���14","�ΰ��̹���15","�ΰ��̹���16","�ΰ��̹���17",
				"�ΰ��̹���18","�ΰ��̹���19","�ΰ��̹���20","�ΰ��̹���21","�ΰ��̹���22"});
		cb_repimg.select(0);
		
		lblNewLabel_48 = new Label(group_2, SWT.NONE);
		lblNewLabel_48.setText("   ����� �󼼼��� �̹��� ����");
		
		composite_39 = new Composite(group_2, SWT.NONE);
		composite_39.setLayout(new GridLayout(2, false));
		composite_39.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_mobileimg = new Combo(composite_39, SWT.NONE);
		cb_mobileimg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_mobileimg.setItems(new String[] {"�����ϼ���","�ΰ��̹���6","�ΰ��̹���7","�ΰ��̹���8","�ΰ��̹���9","�ΰ��̹���10","�ΰ��̹���11","�ΰ��̹���12","�ΰ��̹���13","�ΰ��̹���14","�ΰ��̹���15","�ΰ��̹���16","�ΰ��̹���17",
				"�ΰ��̹���18","�ΰ��̹���19","�ΰ��̹���20","�ΰ��̹���21","�ΰ��̹���22"});
		cb_mobileimg.select(0);
		
		lblNewLabel_49 = new Label(composite_39, SWT.NONE);
		lblNewLabel_49.setText("   (����780pixel, 1.5MB �̸�)");
		
		lblNewLabel_46 = new Label(group_2, SWT.NONE);
		lblNewLabel_46.setText("   ����� ���� ���� ����");
		
		composite_8 = new Composite(group_2, SWT.NONE);
		composite_8.setLayout(new GridLayout(2, false));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_mobile = new Button(composite_8, SWT.CHECK);
		btn_mobile.setText("����� ����");
		
		lblNewLabel_47 = new Label(composite_8, SWT.NONE);
		lblNewLabel_47.setText("	(* üũ�� ��ǰ�������� ������ ����� ��Ǧ���� ���õ˴ϴ�.)");
		lblNewLabel_47.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_44 = new Label(group_2, SWT.NONE);
		lblNewLabel_44.setText("   �ؿܱ��Ŵ��� ��ǰ");
		
		composite = new Composite(group_2, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_overseasN = new Button(composite, SWT.RADIO);
		btn_overseasN.setText("�Ϲݻ�ǰ");
		
		btn_overseasY = new Button(composite, SWT.RADIO);
		btn_overseasY.setText("�ؿܱ��Ŵ����ǰ");
		
		lblNewLabel_45 = new Label(composite, SWT.NONE);
		lblNewLabel_45.setText("	* SCM���� �ؿܱ��Ŵ��� �׸��� ������ �� �ִ� ��ü�� ��� �����մϴ�.");
		lblNewLabel_45.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		lblNewLabel_43 = new Label(group_2, SWT.NONE);
		lblNewLabel_43.setText("   ��ǰ�� �߰� �չ���");
		
		txt_start = new Text(group_2, SWT.BORDER);
		GridData gd_txt_start = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_start.widthHint = 700;
		txt_start.setLayoutData(gd_txt_start);
		
		lblNewLabel_42 = new Label(group_2, SWT.NONE);
		lblNewLabel_42.setText("   ��ǰ�� �߰� �޹���");
		
		txt_end = new Text(group_2, SWT.BORDER);
		GridData gd_txt_end = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_end.widthHint = 700;
		txt_end.setLayoutData(gd_txt_end);
		
		lblNewLabel_41 = new Label(group_2, SWT.NONE);
		lblNewLabel_41.setText("   ��ǰ���� ����߰� ����");
		
		txt_top = new Text(group_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_top = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_top.heightHint = 70;
		gd_txt_top.widthHint = 700;
		txt_top.setLayoutData(gd_txt_top);
		
		lblNewLabel_40 = new Label(group_2, SWT.NONE);
		lblNewLabel_40.setText("   ��ǰ���� �ϴ��߰� ����");
		
		txt_bottom = new Text(group_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_bottom = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_bottom.heightHint = 70;
		gd_txt_bottom.widthHint = 700;
		txt_bottom.setLayoutData(gd_txt_bottom);
		
		group = new Group(container, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		group.setText("�� [���� �� �ΰ�����]");
		
		composite_36 = new Composite(group, SWT.NONE);
		composite_36.setLayout(new GridLayout(2, false));
		composite_36.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_33 = new Label(composite_36, SWT.NONE);
		lblNewLabel_33.setText("�⺻�������");
		
		btn_dasicdiscount = new Button(composite_36, SWT.CHECK);
		
		composite_35 = new Composite(group, SWT.NONE);
		composite_35.setLayout(new GridLayout(8, false));
		composite_35.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_35 = new Label(composite_35, SWT.NONE);
		lblNewLabel_35.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_35.setText("�ǸŰ�����");
		
		txt_dasicdiscount = new Text(composite_35, SWT.BORDER);
		GridData gd_txt_dasicdiscount = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_dasicdiscount.widthHint = 100;
		txt_dasicdiscount.setLayoutData(gd_txt_dasicdiscount);
		
		cb_dasicdiscount = new Combo(composite_35, SWT.NONE);
		cb_dasicdiscount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_dasicdiscount.setItems(new String[] {"��", "%"});
		cb_dasicdiscount.select(0);
		
		ghfdghfdh = new Label(composite_35, SWT.NONE);
		ghfdghfdh.setText("����  ");
		
		btn_dasicdiscountcoupone = new Button(composite_35, SWT.CHECK);
		btn_dasicdiscountcoupone.setText(" ���� ���ޱⰣ ����");
		
		lblNewLabel_36 = new Label(composite_35, SWT.NONE);
		lblNewLabel_36.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_36.setText("	���ú��� ~ ");
		
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
		btn_dasicdiscountdel.setText("����");
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
		lblNewLabel_8.setText("������ �Һ� ����");
		
		btn_interestfee = new Button(composite_22, SWT.CHECK);
		btn_interestfee.setText("		");
		
		composite_9 = new Composite(group, SWT.NONE);
		composite_9.setLayout(new GridLayout(4, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cb_interestfee = new Combo(composite_9, SWT.NONE);
		GridData gd_cb_interestfee = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_interestfee.widthHint = 80;
		cb_interestfee.setLayoutData(gd_cb_interestfee);
		cb_interestfee.setItems(new String[] {"2����", "3����","4����","5����","6����","7����","8����","9����","10����","11����","12����",});
		cb_interestfee.select(0);
		
		Label lblNewLabel_9 = new Label(composite_9, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText(" (Ƚ������ : ");
		
		txt_interestfee = new Text(composite_9, SWT.BORDER);
		txt_interestfee.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel_10 = new Label(composite_9, SWT.NONE);
		lblNewLabel_10.setText(" ȸ)");
		
		composite_23 = new Composite(group, SWT.NONE);
		composite_23.setLayout(new GridLayout(2, false));
		composite_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_6 = new Label(composite_23, SWT.NONE);
		lblNewLabel_6.setText("����Ŀ� ����");
		
		btn_support = new Button(composite_23, SWT.CHECK);
		btn_support.setText("		");
		
		composite_1 = new Composite(group, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_7 = new Label(composite_1, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("�ǸŰ�����");
		
		txt_support = new Text(composite_1, SWT.BORDER);
		GridData gd_txt_support = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_support.widthHint = 100;
		txt_support.setLayoutData(gd_txt_support);
		
		cb_support = new Combo(composite_1, SWT.NONE);
		GridData gd_cb_support = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_support.widthHint = 30;
		cb_support.setLayoutData(gd_cb_support);
		cb_support.setItems(new String[] {"��", "%"});
		cb_support.select(0);
		
		composite_24 = new Composite(group, SWT.NONE);
		composite_24.setLayout(new GridLayout(2, false));
		composite_24.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel = new Label(composite_24, SWT.NONE);
		lblNewLabel.setText("SK pay point ����");
		
		btn_skpay = new Button(composite_24, SWT.CHECK);
		btn_skpay.setText("		");
		
		Composite composite33 = new Composite(group, SWT.NONE);
		composite33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite33.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel_5 = new Label(composite33, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("�ǸŰ�����");
		
		txt_skpay = new Text(composite33, SWT.BORDER);
		GridData gd_txt_skpay = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_skpay.widthHint = 100;
		txt_skpay.setLayoutData(gd_txt_skpay);
		
		cb_skpay = new Combo(composite33, SWT.NONE);
		GridData gd_cb_skpay = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cb_skpay.widthHint = 30;
		cb_skpay.setLayoutData(gd_cb_skpay);
		cb_skpay.setItems(new String[] {"��", "%"});
		cb_skpay.select(0);
		
		composite_37 = new Composite(group, SWT.NONE);
		composite_37.setLayout(new GridLayout(1, false));
		composite_37.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_34 = new Label(composite_37, SWT.NONE);
		lblNewLabel_34.setText("��ǰ����");
		
		composite_38 = new Composite(group, SWT.NONE);
		composite_38.setLayout(new GridLayout(2, false));
		composite_38.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_prodreviewN = new Button(composite_38, SWT.RADIO);
		btn_prodreviewN.setText("�������");
		
		btn_prodreviewY = new Button(composite_38, SWT.RADIO);
		btn_prodreviewY.setText("������");
		
		label_51 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_51.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		lblNewLabel_27 = new Label(group, SWT.NONE);
		lblNewLabel_27.setText("�����ñ���\n�������̶�?");
		
		composite_34 = new Composite(group, SWT.NONE);
		composite_34.setLayout(new GridLayout(1, false));
		composite_34.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel_28 = new Label(composite_34, SWT.NONE);
		lblNewLabel_28.setText("- ��ǰ ������ �������� �� ��ǰ�� �������� �����ִ� ���ᱤ�� �������Դϴ�.");
		lblNewLabel_28.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_29 = new Label(composite_34, SWT.NONE);
		lblNewLabel_29.setText("- �����ñ��� �����ۿ��� �÷���, �÷���UP, GIF�̹���, ����ü, ������ 5���� ��ǰ�� �ֽ��ϴ�.");
		lblNewLabel_29.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_30 = new Label(composite_34, SWT.NONE);
		lblNewLabel_30.setText("1) ������ ��� ������ �����ִ� ������ : �÷���, �÷���UP");
		lblNewLabel_30.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_31 = new Label(composite_34, SWT.NONE);
		lblNewLabel_31.setText("2) �� ��ǰ�� ������ ������ ���� ��� �ٹ̱� ���� ������ : GIF�̹���, ����ü, ����");
		lblNewLabel_31.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		lblNewLabel_32 = new Label(composite_34, SWT.NONE);
		lblNewLabel_32.setText("�����۰� �Ⱓ�� ���� �����ϹǷ� ���ϴ� ������ ��ǰ�� �����Ͻ� �� �ֽ��ϴ�.");
		lblNewLabel_32.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		label_50 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_50.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_23 = new Label(group, SWT.NONE);
		lblNewLabel_23.setText("�÷���");
		
		composite_33 = new Composite(group, SWT.NONE);
		composite_33.setLayout(new GridLayout(1, false));
		composite_33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_plus = new Button(composite_33, SWT.CHECK);
		btn_plus.setText("�����");
		
		lblNewLabel_24 = new Label(composite_33, SWT.NONE);
		lblNewLabel_24.setText("- ��ǰ ����Ʈ ��ܿ� �켱 ����� �� �ֵ��� �����ִ� �������Դϴ�.");
		
		lblNewLabel_25 = new Label(composite_33, SWT.NONE);
		lblNewLabel_25.setText("- ��ǰ �̹��� ������ܿ� �÷��� �������� ����˴ϴ�.");
		
		lblNewLabel_26 = new Label(composite_33, SWT.NONE);
		lblNewLabel_26.setText("- �÷��� �������� ������ ��ǰ�� �÷���UP �������� �����Ͻ� �� �ֽ��ϴ�.");
		
		label_49 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_49.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_19 = new Label(group, SWT.NONE);
		lblNewLabel_19.setText("�÷��� UP");
		
		composite_32 = new Composite(group, SWT.NONE);
		composite_32.setLayout(new GridLayout(1, false));
		composite_32.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_plusup = new Button(composite_32, SWT.CHECK);
		btn_plusup.setText("�����");
		
		lblNewLabel_20 = new Label(composite_32, SWT.NONE);
		lblNewLabel_20.setText("- '11���� ��ŷ��' ���� �� �Ǹ������� 30% �������� �ο��Ǵ� �������Դϴ�.");
		
		lblNewLabel_21 = new Label(composite_32, SWT.NONE);
		lblNewLabel_21.setText("- ��ǰ�� ��ܿ� �÷���UP �������� ����˴ϴ�.");
		
		lblNewLabel_22 = new Label(composite_32, SWT.NONE);
		lblNewLabel_22.setText("- �÷��� �������� ������ ��ǰ�� �÷���UP �������� �����Ͻ� �� �ֽ��ϴ�.");
		
		label_48 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_48.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_15 = new Label(group, SWT.NONE);
		lblNewLabel_15.setText("GIF�̹���");
		
		composite_31 = new Composite(group, SWT.NONE);
		composite_31.setLayout(new GridLayout(1, false));
		composite_31.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_gifimg = new Button(composite_31, SWT.CHECK);
		btn_gifimg.setText("�����");
		
		lblNewLabel_16 = new Label(composite_31, SWT.NONE);
		lblNewLabel_16.setText("- ���� ���� ��ǰ �̹����� �����Ͽ� �� ��ǰ�� �ָ񵵸� ���� �� �ִ� �������Դϴ�.");
		
		lblNewLabel_17 = new Label(composite_31, SWT.NONE);
		lblNewLabel_17.setText("- �������ǽ����� ��ǰ �̹����� ���/������, GIF ������ �̹����� ����Ͻ� �� 'GIF�̹���' �������� �����Ͻø� �̿� �����մϴ�.");
		
		lblNewLabel_18 = new Label(composite_31, SWT.NONE);
		lblNewLabel_18.setText("- GIF�̹����� �ִ� 10����� ���� �����մϴ�.");
		
		label_34 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_34.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_13 = new Label(group, SWT.NONE);
		lblNewLabel_13.setText("����ü");
		
		composite_30 = new Composite(group, SWT.NONE);
		composite_30.setLayout(new GridLayout(1, false));
		composite_30.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_bold = new Button(composite_30, SWT.CHECK);
		btn_bold.setText("�����");
		
		lblNewLabel_14 = new Label(composite_30, SWT.NONE);
		lblNewLabel_14.setText("��ǰ���� ���� ǥ���ϰ� ��ǰ�� ���� �� �������� �ٿ� ��ǰ���� �����ϴ� �������Դϴ�.");
		
		label_1 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_11 = new Label(group, SWT.NONE);
		lblNewLabel_11.setText("����");
		
		composite_25 = new Composite(group, SWT.NONE);
		composite_25.setLayout(new GridLayout(1, false));
		composite_25.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btn_background = new Button(composite_25, SWT.CHECK);
		btn_background.setText("�����");
		
		lblNewLabel_12 = new Label(composite_25, SWT.NONE);
		lblNewLabel_12.setText("�ش� ��ǰ�� ������ ������ ����� ������ ���� ���� �����ϴ� �������Դϴ�.");
		
		label_35 = new Label(container, SWT.NONE);
		label_35.setText("�� ��뿩��");
		
		cb_whether = new Combo(container, SWT.NONE);
		cb_whether.setItems(new String[] {"��뿩��", "�����", "�̻��"});
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
	//�߼ۿ����ϰ˻�
	protected void openShipmentdate() {
		// TODO Auto-generated method stub
		
	}

	//�Ⱓ������ ȭ�����
	protected void setVisibleBenepiaPreiod() {
		if(btn_benepia2.getSelection()) {
			txt_benepiast.setEnabled(true);
			txt_benepiaen.setEnabled(true);
		}else {
			txt_benepiast.setEnabled(false);
			txt_benepiaen.setEnabled(false);
		}
		
	}

	//�����Ǿ����μ��� �����
	protected void setVisibleBenepia() {
		if(btn_benepia1.getSelection()) {
			txt_benepia1_1.setEnabled(true);
			btn_benepia2.setEnabled(true);
		}else {
			txt_benepia1_1.setEnabled(false);
			btn_benepia2.setEnabled(false);
		}
		
	}

	//���̵�üũ
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
			
			String id= "���θ�ID,";
			for(List<String> idche: shoplist) {
				id += idche.get(1)+",";
			}
			cb_idcheck.setItems(id.split(","));
			cb_idcheck.select(0);
		}catch (Exception e) {
			e.getStackTrace();
		}
			
		
		
	}

	//���/��ǰ�ּ��� ������ ����
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
			txt_title.setText(dto.getTitle());//����
			txt_memo.setText(dto.getMemo());//�޸�
			setidsetting(dto.getShopid());//���̵�
			switch(dto.getSelmthdcd()) { //�ǸŹ��	
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
			cb_service.select(0);//���񽺻�ǰ  �̷������ε�ϵǴ°� ���� 11������ ������ 01�� �����°� ����dto.setProdtypcd(cb_service.getSelectionIndex()==0?"01":""); 
			txt_prodpromotion.setText(dto.getMall_var_1());//��ǰȫ������
			switch(dto.getMall_var_2()) { //��������ǰ
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
			switch(dto.getMall_var_3()) { ////��������뿩��	
			case "01" : btn_orgin2_1.setSelection(true);
				break;
			case "02" : btn_orgin2_2.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_3().equals("02")) {
				dto.setMall_var_4(txt_orgin2_1.getText()); //��������뿩�ο���������
			}
			switch(dto.getMall_var_5()) {//���������ߵ�Ͽ���
			case "Y" : btn_orgin3_1.setSelection(true);
				break;
			case "N" : btn_orgin3_1.setSelection(false);
				break;
				default : break;
			}
			txt_prodmd.setText(dto.getMall_var_6());//��ǰ��
			cb_prodedit.select(Integer.parseInt(dto.getMall_var_7()));
			cb_sellerprod.select(Integer.parseInt(dto.getMall_var_8()));
			switch(dto.getMall_var_9()) {//����ĳ�û�뿩��
			case "Y" : btn_sellercash.setSelection(true);
				break;
			case "N" : btn_sellercash.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getProdstatcd()) {  //��ǰ����
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
			switch(dto.getMinorselcnyn()) {//�̼����ڱ��ſ���
			case "Y" : btn_minoryes.setSelection(true);
				break;
			case "N" : btn_minorno.setSelection(true);
				break;
				default : break;
			}
			txt_point.setText(dto.getMall_var_10());//��������
			txt_disney.setText(dto.getMall_var_11());//����Ͻø����
			txt_nicknm.setText(dto.getNicknm());//�г���
			txt_brand.setText(dto.getMall_var_12());//�귣��
			txt_phoneurl.setText(dto.getMall_var_13());//���Խ�ûURL
			switch(dto.getMall_var_94()) {//�޴���ȭ��������
			case "01" : btn_phone1.setSelection(true);
				break;
			case "02" : btn_phone2.setSelection(true);
				break;
				default : break;
			}
			txt_phone.setText(dto.getMall_var_14());//�޴��������
			txt_effecdate.setText(dto.getMall_var_15());//��ȿ����
			switch(dto.getMall_var_16()) {//���ݺ񱳵�Ͽ���
			case "01" : btn_costcomparisY.setSelection(true);
				break;
			case "02" : btn_costcomparisN.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getFpseltermyn()) {//�ǸűⰣ��������
			case "Y" : btn_dateyes.setSelection(true);
				break;
			case "N" : btn_dateno.setSelection(true);
				break;
				default : break;
			}
			if(dto.getFpseltermyn().equals("Y")) {
				switch(dto.getProdfpselcd()) { //�ǸűⰣ�ڵ�
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
			switch(dto.getMall_var_17()) {//1������ǰ����
			case "Y" : btn_1wonphone.setSelection(true);
				break;
			case "N" : btn_1wonphone.setSelection(false);
				break;
				default : break;
			}
			cb_optsetting.select(Integer.parseInt(dto.getMall_var_18()));//�ɼǼ�������
			cb_2optiondefault.select(Integer.parseInt(dto.getMall_var_19())); //2�ܿɼ����뿩��
			switch(dto.getMall_var_20()) {//�ɼǰ�������
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
			switch(dto.getMall_var_21()) {//�ۼ����ɼǿ���
			case "Y" : btn_optionY.setSelection(true);
				break;
			case "N" : btn_optionN.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_21().equals("Y")) {
				txt_option1.setText(dto.getMall_var_22()); //�ɼǸ�1
				txt_option2.setText(dto.getMall_var_23()); //�ɼǸ�2
				txt_option3.setText(dto.getMall_var_24());//�ɼǸ�3
				txt_option4.setText(dto.getMall_var_25()); //�ɼǸ�4
				txt_option5.setText(dto.getMall_var_26());//�ɼǸ�5
				txt_option6.setText(dto.getMall_var_27());//�ɼǸ�6
				txt_option7.setText(dto.getMall_var_28());//�ɼǸ�7
				txt_option8.setText(dto.getMall_var_29());//�ɼǸ�8
				txt_option9.setText(dto.getMall_var_30());//�ɼǸ�9
				txt_option10.setText(dto.getMall_var_31());//�ɼǸ�10
			}
			txt_url.setText(dto.getMall_var_32());//���ް���ǰURL
			txt_strdate.setText(dto.getMall_var_33());//���������
			txt_enddate.setText(dto.getMall_var_34());//�����������
			switch(dto.getMall_var_35()) {//�����Ǿ����ο���
			case "Y" : btn_benepia1.setSelection(true);
				break;
			case "N" : btn_benepia1.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_35().equals("Y")) {
				txt_benepia1_1.setText(dto.getMall_var_36());//�����Ǿ����αݾ׹���
				switch(dto.getMall_var_37()) {//�����ǾƱⰣ��������
				case "Y" : btn_benepia2.setSelection(true);
					break;
				case "N" : btn_benepia2.setSelection(false);
					break;
					default : break;
				}
				if(dto.getMall_var_37().equals("Y")) {
					txt_benepiast.setText(dto.getMall_var_38());//�����Ǿƽ�����
					txt_benepiaen.setText(dto.getMall_var_39());//�����Ǿ�������
				}			
			}
			switch(dto.getMall_var_40()) {//�ּұ��ż������ѿ���
			case "01" : btn_minlimit1.setSelection(true);
				break;
			case "02" : btn_minlimit2.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_40().equals("02")) {
				txt_minlimit2_1.setText(dto.getMall_var_41());//�ּұ������ѽü���
			}						
			switch(dto.getMall_var_42()) { //�ִ뱸�ż������ѿ���
			case "01" : btn_limit1.setSelection(true);
				break;
			case "02" : btn_limit2.setSelection(true);
				break;
			case "03" : btn_limit3.setSelection(true);
				break;
				default : break;
			}
			if(dto.getMall_var_42().equals("02")) {
				txt_limit2_1.setText(dto.getMall_var_43());//1ȸ���ѽü���
			}else if(dto.getMall_var_42().equals("03")) {
				txt_limit3_1.setText(dto.getMall_var_44());//�Ⱓ���ѽñⰣ
				txt_limit3_2.setText(dto.getMall_var_45());//�Ⱓ���ѽü���
			}
			txt_giftnm.setText(dto.getMall_var_46());//����ǰ��
			txt_giftperiodst.setText(dto.getMall_var_47());//����ǰ�Ⱓ������
			txt_giftperioden.setText(dto.getMall_var_49());//����ǰ�Ⱓ������
			txt_exptemplate.setText(dto.getMall_var_50());//����ǰ����
			txt_exptemplate.setText(dto.getMall_var_51());//����������ø�
			cb_exparea.select(Integer.parseInt(dto.getMall_var_52()));//��۰�������
			switch(dto.getExpwycd()) { //��۹��
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
			switch(dto.getMall_var_95()) { //�湮����
			case "Y" : btn_world.setSelection(true);
				break;
			case "N" :btn_world.setSelection(false);
				break;
				default : break;
			}
			scb_express.selectValue(dto.getSendexp());//�߼��ù��
			txt_shipmentdate.setText(dto.getMall_var_53());//�߼ۿ�����
			txt_visit.setText(dto.getMall_var_54());//�湮�����ּ�
			switch(dto.getGbldivyn()) { //�������ۿ���
			case "Y" : btn_worldyes.setSelection(true);
				break;
			case "N" :btn_worldno.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getGblwght()) { //�������۹���
			case "01" : btn_weight.setSelection(true);
				break;
			case "02" :btn_weight300.setSelection(true);
				break;
				default : break;
			}
			cb_hscode.select(Integer.parseInt(dto.getHscd()));//HS�ڵ�
			switch(dto.getMall_var_55()) { //������ּ��ؿܿ���
			case "01" : btn_outaddr1.setSelection(true);
				break;
			case "02" :btn_outaddr2.setSelection(true);
				break;
				default : break;
			}
			txt_factaddr.setText(dto.getAddrout());//����ּ�
			switch(dto.getMall_var_56()) {//��ȯ��ǰ�ּ��ؿܿ���
			case "01" : btn_cngNretaddr1.setSelection(true);
				break;
			case "02" :btn_cngNretaddr2.setSelection(true);
				break;
				default : break;
			}
			txt_chanaddr.setText(dto.getAddrin()); //��ȯ/��ǰ�ּ�
			switch(dto.getShiptypcd()) {//��ۺ�����
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
			if(dto.getShiptypcd().equals("03")) {//���Ǻ�
				txt_condifree.setText(dto.getShipprc());//�⺻��ۺ�
				txt_condifree1.setText(dto.getCondiprice());//��ǰ���Ǻ��� �ڿ��ݾ�
			}else if(dto.getShiptypcd().equals("05")) {//1����
				txt_oneprice.setText(dto.getShipprc());//�⺻��ۺ�
			}else if(dto.getShiptypcd().equals("02")) {//����
				txt_fixing.setText(dto.getShipprc());//�⺻��ۺ�
			}else if(dto.getShiptypcd().equals("04")) {//������
				txt_diffqty.setText(dto.getMall_var_57());//�����������1
				txt_diffqty1.setText(dto.getMall_var_58()); //�����������2
				txt_diffprice.setText(dto.getDiffprice1());//��������ݾ�1
				txt_diffprice1.setText(dto.getMall_var_59());//��������ݾ�2
			}
			txt_jeju.setText(dto.getJejuprc());//���ֹ�ۺ�
			txt_island.setText(dto.getIslandprc());//�����갣��ۺ�
			switch(dto.getPrctypcd()) { //����������
			case "01" : cb_prepay.select(0);
				break;
			case "02" : cb_prepay.select(1);
				break;
			case "03" : cb_prepay.select(2);
			break;
				default : break;
			}
			switch(dto.getBndyn()) { //������ۿ���
			case "Y" : btn_bundleyse.setSelection(true);
				break;
			case "N" : btn_bundleno.setSelection(true);
				break;
				default : break;
			}
			txt_oneway.setText(dto.getRetprc());//��ǰ��ۺ�
			txt_round.setText(dto.getExcprc());//��ȯ��ۺ�
			switch(dto.getRudcd()) { //�պ�������
			case "01" : btn_roundyes.setSelection(true);
				break;
			case "02" : btn_roundno.setSelection(true);
				break;
				default : break;
			}
			txt_as.setText(dto.getAsdtl());//A/S�ȳ�
			txt_change.setText(dto.getRtexcdtl());//��ȯ/��ǰ�ȳ�
			switch(dto.getMall_var_60()) { //�����������ο���
			case "01" : btn_multidiscnt.setSelection(true);
				break;
			case "02" : btn_multidiscnt.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_60().equals("Y")) {
				cb_multidiscntcnt.select(Integer.parseInt(dto.getMall_var_61()));//�������α���
				txt_multidiscntcnt.setText(dto.getMall_var_62());//�������αݾ׹װ���1
				txt_multidiscntwon.setText(dto.getMall_var_63());//�������αݾ׹װ���2
				cb_multidiscntwon.select(Integer.parseInt(dto.getMall_var_64()));//�������ι������
			}
			cb_buy.select(Integer.parseInt(dto.getMall_var_65()));//����ó����
			cb_repimg.select(Integer.parseInt(dto.getMall_var_66()));//��ǥ�̹���
			cb_mobileimg.select(Integer.parseInt(dto.getMall_var_67()));//����ϻ󼼼����̹�������
			switch(dto.getMall_var_68()) { //������������ÿ���
			case "Y" : btn_mobile.setSelection(true);
				break;
			case "N" : btn_mobile.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_69()) {//�ؿܱ��Ŵ����ǰ����
			case "01" : btn_overseasN.setSelection(true);
				break;
			case "02" : btn_overseasY.setSelection(false);
				break;
				default : break;
			}
			txt_start.setText(dto.getMall_var_70());//��ǰ����߰�����
			txt_end.setText(dto.getMall_var_71());//��ǰ����߰�����
			txt_top.setText(dto.getMall_var_72());//��ǰ�������߰�����
			txt_bottom.setText(dto.getMall_var_73());//��ǰ�����ϴ��߰�����
			switch(dto.getMall_var_74()) {//�⺻������ο���
			case "Y" : btn_dasicdiscount.setSelection(true);
				break;
			case "N" : btn_dasicdiscount.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_74().equals("Y")) {
				txt_dasicdiscount.setText(dto.getMall_var_75());//�⺻������αݾ�
				cb_dasicdiscount.select(Integer.parseInt(dto.getMall_var_76()));//�⺻������α���
			}
			switch(dto.getMall_var_77()) {//�������ޱⰣ��������
			case "Y" : btn_dasicdiscountcoupone.setSelection(true);
				break;
			case "N" : btn_dasicdiscountcoupone.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_77().equals("Y")) {
				txt_dasicdiscountdate.setText(dto.getMall_var_78());//�������ޱⰣ������
			}	
			switch(dto.getMall_var_79()) {//�������Һ���������
			case "Y" : btn_interestfee.setSelection(true);
				break;
			case "N" : btn_interestfee.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_79().equals("Y")) {
				cb_interestfee.select(Integer.parseInt(dto.getMall_var_80()));//�������Һΰ�����
				txt_interestfee.setText(dto.getMall_var_81());//�������Һ�ȸ������
			}	
			switch(dto.getMall_var_82()) {//������μ�������
			case "Y" : btn_support.setSelection(true);
				break;
			case "N" : btn_support.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_82().equals("Y")) {
				txt_support.setText(dto.getMall_var_83());//����Ŀ��ݾ�
				cb_support.select(Integer.parseInt(dto.getMall_var_84()));//����Ŀ����α���
			}	
			switch(dto.getMall_var_85()) {//SK����Ʈ���޿���
			case "Y" : btn_skpay.setSelection(true);
				break;
			case "N" : btn_skpay.setSelection(false);
				break;
				default : break;
			}
			if(dto.getMall_var_85().equals("Y")) {
				txt_skpay.setText(dto.getMall_var_86());//SK����Ʈ�ݾ�
				cb_skpay.select(Integer.parseInt(dto.getMall_var_87()));//SK����Ʈ���α���
			}
			switch(dto.getMall_var_88()) {//��ǰ���俩��
			case "Y" : btn_prodreviewY.setSelection(true);
				break;
			case "N" : btn_prodreviewN.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getMall_var_89()) {//�÷���������
			case "Y" : btn_plus.setSelection(true);
				break;
			case "N" : btn_plus.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_90()) {//�÷���UP������
			case "Y" : btn_plusup.setSelection(true);
				break;
			case "N" : btn_plusup.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_91()) {//GIF������
			case "Y" : btn_gifimg.setSelection(true);
				break;
			case "N" : btn_gifimg.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_92()) {//����ü������
			case "Y" : btn_bold.setSelection(true);
				break;
			case "N" : btn_bold.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getMall_var_93()) {//����������
			case "Y" : btn_background.setSelection(true);
				break;
			case "N" : btn_background.setSelection(false);
				break;
				default : break;
			}
			switch(dto.getUseyn()) {//��뿩��
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
			MessageDialog.openInformation(getShell(), TITLE, "������ �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(cb_idcheck.getText().equals("���̵���")) {
			MessageDialog.openInformation(getShell(), TITLE, "���̵� �����Ͻð� �����Ͻñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_public.getSelection()&&!btn_fixed.getSelection()&&!btn_used.getSelection()&&!btn_reser.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "�ǸŹ���� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_new.getSelection()&&!btn_stock.getSelection()&&!btn_making.getSelection()&&!btn_usedprod.getSelection()&&!btn_reaper.getSelection()&&!btn_exhibi.getSelection()&&!btn_return.getSelection()&&
				!btn_scratch.getSelection()&&!btn_rare.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "��ǰ���¸� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_minoryes.getSelection()&&!btn_minorno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "�̼����ڱ��Ű����� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(txt_nicknm.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "�г����� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_dateyes.getSelection()&&!btn_dateno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "�ǸűⰣ�� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_7day.getSelection()&&!btn_15day.getSelection()&&!btn_30day.getSelection()&&!btn_60day.getSelection()&&!btn_90day.getSelection()&&!btn_120day.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "�ǸűⰣ������ üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(cb_world.getSelectionIndex()==0) {
			if(cb_exp.getText().equals("�����ϼ���")) {
				MessageDialog.openInformation(getShell(), TITLE, "��۹���� �ù��ϰ�� �߼� �ù�縦 �ݵ�� ����ϰ� �����ϼžߵ˴ϴ�.");
				return;
			}
		}
		if(txt_factaddr.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "������ּҸ� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(txt_chanaddr.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "��ȯ/��ǰ���ּҸ� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_free.getSelection()&&!btn_condifree.getSelection()&&!btn_fixing.getSelection()&&!btn_seller.getSelection()&&!btn_factory.getSelection()&&!btn_differ.getSelection()&&!btn_one.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "��ۺ����� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(!btn_free.getSelection()&&btn_condifree.getSelection()||btn_fixing.getSelection()) {
			if(txt_condifree.getText().length()<1&&txt_fixing.getText().length()<1) {
				MessageDialog.openInformation(getShell(), TITLE, "��ۺ����� üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
				return;
			}			
		}
		if(!btn_roundyes.getSelection()&&!btn_roundno.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "�ʱ��ۺ� ����� ��ǰ��ۺ� �ΰ������ üũ�Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(txt_oneway.getText().trim().length()<1&&txt_round.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "��ǰ��ۺ�, ��ȯ��ۺ� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(txt_as.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "A/S�ȳ��� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(txt_change.getText().trim().length()<1) {
			MessageDialog.openInformation(getShell(), TITLE, "��ȯ / ��ǰ�ȳ��� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		if(cb_whether.getSelectionIndex()==0) {
			MessageDialog.openInformation(getShell(), TITLE, "��뿩�θ� �����Ͽ� �ֽñ� �ٶ��ϴ�.");
			return;
		}
		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			ShopProduct11stAdditionDto dto = new ShopProduct11stAdditionDto();

			dto.setTitle(txt_title.getText()); //����
			dto.setMemo(txt_memo.getText()); //�޸�
			dto.setShopid(cb_idcheck.getText()); //���̵�
			dto.setSelmthdcd(btn_fixed.getSelection()?"01":btn_used.getSelection()?"05":btn_reser.getSelection()?"04":"02"); //�ǸŹ��			
			dto.setProdtypcd(cb_service.getSelectionIndex()==0?"01":""); //���񽺻�ǰ
			dto.setMall_var_1(txt_prodpromotion.getText()); //��ǰȫ������
			dto.setMall_var_2(btn_orgin1_1.getSelection()?"01":btn_orgin1_2.getSelection()?"02":btn_orgin1_3.getSelection()?"03":btn_orgin1_4.getSelection()?"04":btn_orgin1_5.getSelection()?"05":""); //��������ǰ
			dto.setMall_var_3(btn_orgin2_1.getSelection()?"01":btn_orgin2_2.getSelection()?"02":""); //��������뿩��
			if(dto.getMall_var_3().equals("02")) {
				dto.setMall_var_4(txt_orgin2_1.getText()); //��������뿩�ο���������
			}
			dto.setMall_var_5(btn_orgin3_1.getSelection()?"Y":"N"); //���������ߵ�Ͽ���
			dto.setMall_var_6(txt_prodmd.getText()); //��ǰ��
			dto.setMall_var_7(String.valueOf(cb_prodedit.getSelectionIndex())); //��ǰ������
			dto.setMall_var_8(String.valueOf(cb_sellerprod.getSelectionIndex())); //�Ǹ��ڻ�ǰ�ڵ�
			dto.setMall_var_9(btn_sellercash.getSelection()?"Y":"N"); //����ĳ�û�뿩��
			dto.setProdstatcd(btn_new.getSelection()?"01":btn_usedprod.getSelection()?"02":btn_stock.getSelection()?"03":btn_reaper.getSelection()?"04":btn_exhibi.getSelection()?"05":btn_rare.getSelection()?"07":
				btn_return.getSelection()?"08":btn_scratch.getSelection()?"09":"10"); //��ǰ����
			dto.setMinorselcnyn(btn_minoryes.getSelection()?"Y":"N"); //�̼����ڱ��ſ���
			dto.setMall_var_10(txt_point.getText()); //��������
			dto.setMall_var_11(txt_disney.getText()); //����Ͻø����
			dto.setNicknm(txt_nicknm.getText()); //�г���
			dto.setMall_var_12(txt_brand.getText()); //�귣��
			dto.setMall_var_13(txt_phoneurl.getText()); //���Խ�ûURL
			dto.setMall_var_94(btn_phone1.getSelection()?"01":btn_phone2.getSelection()?"02":"");
			dto.setMall_var_14(txt_phone.getText()); //�޴��������
			dto.setMall_var_15(txt_effecdate.getText()); //��ȿ����
			dto.setMall_var_16(btn_costcomparisY.getSelection()?"01":btn_costcomparisN.getSelection()?"02":""); //���ݺ񱳵�Ͽ���
			dto.setFpseltermyn(btn_dateyes.getSelection()?"Y":"N"); //�ǸűⰣ��������
			if(dto.getFpseltermyn().equals("Y")) {
				dto.setProdfpselcd(btn_7day.getSelection()?"7:103":btn_15day.getSelection()?"15:104":btn_30day.getSelection()?"30:105":btn_60day.getSelection()?"60:106":btn_90day.getSelection()?"90:107":"120:108"); //�ǸűⰣ�ڵ�
			}			
			dto.setMall_var_17(btn_1wonphone.getSelection()?"Y":"N"); //1������ǰ����
			dto.setMall_var_18(String.valueOf(cb_optsetting.getSelectionIndex())); //�ɼǼ�������
			dto.setMall_var_19(String.valueOf(cb_2optiondefault.getSelectionIndex())); //2�ܿɼ����뿩��
			dto.setMall_var_20(btn_optval1.getSelection()?"01":btn_optval2.getSelection()?"02":btn_optval3.getSelection()?"03":btn_optval4.getSelection()?"04":btn_optval5.getSelection()?"05":""); //�ɼǰ�������
			dto.setMall_var_21(btn_optionN.getSelection()?"N":btn_optionY.getSelection()?"Y":""); //�ۼ����ɼǿ���
			if(dto.getMall_var_21().equals("Y")) {
				dto.setMall_var_22(txt_option1.getText()); //�ɼǸ�1
				dto.setMall_var_23(txt_option2.getText()); //�ɼǸ�2
				dto.setMall_var_24(txt_option3.getText()); //�ɼǸ�3
				dto.setMall_var_25(txt_option4.getText()); //�ɼǸ�4
				dto.setMall_var_26(txt_option5.getText()); //�ɼǸ�5
				dto.setMall_var_27(txt_option6.getText()); //�ɼǸ�6
				dto.setMall_var_28(txt_option7.getText()); //�ɼǸ�7
				dto.setMall_var_29(txt_option8.getText()); //�ɼǸ�8
				dto.setMall_var_30(txt_option9.getText()); //�ɼǸ�9
				dto.setMall_var_31(txt_option10.getText()); //�ɼǸ�10
			}		
			dto.setMall_var_32(txt_url.getText()); //���ް���ǰURL
			dto.setMall_var_33(txt_strdate.getText()); //���������
			dto.setMall_var_34(txt_enddate.getText()); //�����������
			dto.setMall_var_35(btn_benepia1.getSelection()?"Y":"N"); //�����Ǿ����ο���
			if(dto.getMall_var_35().equals("Y")) {
				dto.setMall_var_36(StringCountCheck(txt_benepia1_1.getText())); //�����Ǿ����αݾ׹���
				dto.setMall_var_37(btn_benepia2.getSelection()?"Y":"N"); //�����ǾƱⰣ��������
				if(dto.getMall_var_37().equals("Y")) {
					dto.setMall_var_38(txt_benepiast.getText()); //�����Ǿƽ�����
					dto.setMall_var_39(txt_benepiaen.getText()); //�����Ǿ�������
				}
			}
			dto.setMall_var_40(btn_minlimit1.getSelection()?"01":btn_minlimit2.getSelection()?"02":""); //�ּұ��ż������ѿ���
			if(dto.getMall_var_40().equals("02")) {
				dto.setMall_var_41(StringCountCheck(txt_minlimit2_1.getText())); //�ּұ������ѽü���
			}			
			dto.setMall_var_42(btn_limit1.getSelection()?"01":btn_limit2.getSelection()?"02":btn_limit3.getSelection()?"03":""); //�ִ뱸�ż������ѿ���
			if(dto.getMall_var_42().equals("02")) {
				dto.setMall_var_43(StringCountCheck(txt_limit2_1.getText())); //1ȸ���ѽü���
			}else if(dto.getMall_var_42().equals("03")) {
				dto.setMall_var_44(StringCountCheck(txt_limit3_1.getText())); //�Ⱓ���ѽñⰣ
				dto.setMall_var_45(StringCountCheck(txt_limit3_2.getText())); //�Ⱓ���ѽü���
			}
			dto.setMall_var_46(txt_giftnm.getText()); //����ǰ��
			dto.setMall_var_47(txt_giftperiodst.getText()); //����ǰ�Ⱓ������
			dto.setMall_var_49(txt_giftperioden.getText()); //����ǰ�Ⱓ������
			dto.setMall_var_50(txt_gift.getText()); //����ǰ����
			dto.setMall_var_51(txt_exptemplate.getText()); //����������ø�
			dto.setMall_var_52(String.valueOf(cb_exparea.getSelectionIndex())); //��۰�������
			dto.setExpwycd(cb_world.getSelectionIndex()==0?"01":cb_world.getSelectionIndex()==1?"02":cb_world.getSelectionIndex()==2?"03":cb_world.getSelectionIndex()==3?"04":"05"); //��۹��
			dto.setMall_var_95(btn_world.getSelection()?"Y":"N");//�湮����
			dto.setSendexp(scb_express.getSelectionValue()); //�߼��ù��
			dto.setMall_var_53(txt_shipmentdate.getText()); //�߼ۿ�����
			dto.setMall_var_54(txt_visit.getText()); //�湮�����ּ�
			dto.setGbldivyn(btn_worldyes.getSelection()?"Y":"N"); //�������ۿ���
			dto.setGblwght(btn_weight.getSelection()?"01":btn_weight300.getSelection()?"02":"0"); //�������۹���
			dto.setHscd(String.valueOf(cb_hscode.getSelectionIndex())); //HS�ڵ�
			dto.setMall_var_55(btn_outaddr1.getSelection()?"01":btn_outaddr2.getSelection()?"02":""); //������ּ��ؿܿ���
			dto.setAddrout(txt_factaddr.getText()); //����ּ�
			dto.setMall_var_56(btn_cngNretaddr1.getSelection()?"01":btn_cngNretaddr2.getSelection()?"02":""); //��ȯ��ǰ�ּ��ؿܿ���
			dto.setAddrin(txt_chanaddr.getText()); //��ȯ/��ǰ�ּ�
			dto.setShiptypcd(btn_free.getSelection()?"01":btn_fixing.getSelection()?"02":btn_seller.getSelection()?"07":btn_factory.getSelection()?"08":btn_condifree.getSelection()?"03":btn_one.getSelection()?"05":"04"); //��ۺ�����
			dto.setShipprc("0"); //�⺻��ۺ�
			dto.setCondiprice("0"); //��ǰ���Ǻ��� �ڿ��ݾ�
			dto.setMall_var_57("0"); //�����������1
			dto.setMall_var_58("0"); //�����������2
			dto.setDiffprice1("0"); //��������ݾ�1
			dto.setMall_var_59("0"); //��������ݾ�2
			if(dto.getShiptypcd().equals("03")) {//���Ǻ�
				dto.setShipprc(StringCountCheck(txt_condifree.getText())); //�⺻��ۺ�
				dto.setCondiprice(StringCountCheck(txt_condifree1.getText())); //��ǰ���Ǻ��� �ڿ��ݾ�
			}else if(dto.getShiptypcd().equals("05")) {//1����
				dto.setShipprc(StringCountCheck(txt_oneprice.getText())); //�⺻��ۺ�
			}else if(dto.getShiptypcd().equals("02")) {//����
				dto.setShipprc(StringCountCheck(txt_fixing.getText())); //�⺻��ۺ�
			}else if(dto.getShiptypcd().equals("04")) {//������
				dto.setMall_var_57(StringCountCheck(txt_diffqty.getText())); //�����������1
				dto.setMall_var_58(StringCountCheck(txt_diffqty1.getText())); //�����������2
				dto.setDiffprice1(StringCountCheck(txt_diffprice.getText())); //��������ݾ�1
				dto.setMall_var_59(StringCountCheck(txt_diffprice1.getText())); //��������ݾ�2
			}
			dto.setJejuprc(StringCountCheck(txt_jeju.getText())); //���ֹ�ۺ�
			dto.setIslandprc(StringCountCheck(txt_island.getText())); //�����갣��ۺ�
			dto.setPrctypcd(cb_prepay.getSelectionIndex()==0?"01":cb_prepay.getSelectionIndex()==1?"02":"03"); //����������
			dto.setBndyn(btn_bundleyse.getSelection()?"Y":"N"); //������ۿ���
			dto.setRetprc(StringCountCheck(txt_oneway.getText())); //��ǰ��ۺ�
			dto.setExcprc(StringCountCheck(txt_round.getText())); //��ȯ��ۺ�
			dto.setRudcd(btn_roundyes.getSelection()?"01":"02"); //�պ�������
			dto.setAsdtl(txt_as.getText()); //A/S�ȳ�
			dto.setRtexcdtl(txt_change.getText()); //��ȯ/��ǰ�ȳ�
			dto.setMall_var_60(btn_multidiscnt.getSelection()?"Y":"N"); //�����������ο���
			dto.setMall_var_62("0"); //�������αݾ׹װ���1
			dto.setMall_var_63("0");//�������αݾ׹װ���2
			if(dto.getMall_var_60().equals("Y")) {
				dto.setMall_var_61(String.valueOf(cb_multidiscntcnt.getSelectionIndex())); //�������α���
				dto.setMall_var_62(StringCountCheck(txt_multidiscntcnt.getText())); //�������αݾ׹װ���1
				dto.setMall_var_63(StringCountCheck(txt_multidiscntwon.getText())); //�������αݾ׹װ���2
				dto.setMall_var_64(String.valueOf(cb_multidiscntwon.getSelectionIndex())); //�������ι������
			}
			dto.setMall_var_65(String.valueOf(cb_buy.getSelectionIndex())); //����ó����
			dto.setMall_var_66(String.valueOf(cb_repimg.getSelectionIndex())); //��ǥ�̹���
			dto.setMall_var_67(String.valueOf(cb_mobileimg.getSelectionIndex())); //����ϻ󼼼����̹�������
			dto.setMall_var_68(btn_mobile.getSelection()?"Y":"N"); //������������ÿ���
			dto.setMall_var_69(btn_overseasN.getSelection()?"01":btn_overseasY.getSelection()?"02":""); //�ؿܱ��Ŵ����ǰ����
			dto.setMall_var_70(txt_start.getText()); //��ǰ����߰�����
			dto.setMall_var_71(txt_end.getText()); //��ǰ����߰�����
			dto.setMall_var_72(txt_top.getText()); //��ǰ�������߰�����
			dto.setMall_var_73(txt_bottom.getText()); //��ǰ�����ϴ��߰�����
			dto.setMall_var_74(btn_dasicdiscount.getSelection()?"Y":"N"); //�⺻������ο���
			dto.setMall_var_75("0"); //�⺻������αݾ�
			if(dto.getMall_var_74().equals("Y")) {
				dto.setMall_var_75(StringCountCheck(txt_dasicdiscount.getText())); //�⺻������αݾ�
				dto.setMall_var_76(String.valueOf(cb_dasicdiscount.getSelectionIndex())); //�⺻������α���
			}			
			dto.setMall_var_77(btn_dasicdiscountcoupone.getSelection()?"Y":"N"); //�������ޱⰣ��������
			if(dto.getMall_var_77().equals("Y")) {
				dto.setMall_var_78(txt_dasicdiscountdate.getText()); //�������ޱⰣ������
			}		
			dto.setMall_var_79(btn_interestfee.getSelection()?"Y":"N"); //�������Һ���������
			dto.setMall_var_81("0"); //�������Һ�ȸ������
			if(dto.getMall_var_79().equals("Y")) {
				dto.setMall_var_80(String.valueOf(cb_interestfee.getSelectionIndex())); //�������Һΰ�����
				dto.setMall_var_81(StringCountCheck(txt_interestfee.getText())); //�������Һ�ȸ������
			}			
			dto.setMall_var_82(btn_support.getSelection()?"Y":"N"); //������μ�������
			dto.setMall_var_83("0"); //����Ŀ��ݾ�
			if(dto.getMall_var_82().equals("Y")) {
				dto.setMall_var_83(StringCountCheck(txt_support.getText())); //����Ŀ��ݾ�
				dto.setMall_var_84(String.valueOf(cb_support.getSelectionIndex())); //����Ŀ����α���
			}		
			dto.setMall_var_85(btn_skpay.getSelection()?"Y":"N"); //SK����Ʈ���޿���
			dto.setMall_var_86("0"); //SK����Ʈ�ݾ�
			if(dto.getMall_var_85().equals("Y")) {
				dto.setMall_var_86(StringCountCheck(txt_skpay.getText())); //SK����Ʈ�ݾ�
				dto.setMall_var_87(String.valueOf(cb_skpay.getSelectionIndex())); //SK����Ʈ���α���
			}	
			dto.setMall_var_88(btn_prodreviewN.getSelection()?"N":btn_prodreviewY.getSelection()?"Y":""); //��ǰ���俩��
			dto.setMall_var_89(btn_plus.getSelection()?"Y":"N"); //�÷���������
			dto.setMall_var_90(btn_plusup.getSelection()?"Y":"N"); //�÷���UP������
			dto.setMall_var_91(btn_gifimg.getSelection()?"Y":"N"); //GIF������
			dto.setMall_var_92(btn_bold.getSelection()?"Y":"N"); //����ü������
			dto.setMall_var_93(btn_background.getSelection()?"Y":"N"); //����������
			dto.setUseyn(cb_whether.getSelectionIndex()==1?"Y":"N"); //��뿩��

			if(this.dto ==null) {
				int seq=0;
				if(list!=null) {
					seq = dao.getSeqNumber(list.get(0));
				}			
				int result = dao.ShopAddrDtlInsert(seq+1,dto,list);
				if(result!=0) {				
					MessageDialog.openInformation(getShell(), TITLE, "�ΰ������� �����Ͽ����ϴ�.");
					super.okPressed();
				}else {
					MessageDialog.openInformation(getShell(), TITLE, "�ΰ��������忡 �����Ͽ����ϴ�.");
				}
			}else {
				int result = dao.ShopAddrDtlUpdate(dto,this.dto,shopseq);
				if(result!=0) {				
					MessageDialog.openInformation(getShell(), TITLE, "�ΰ������� �����Ͽ����ϴ�.");
					opener.getSearchShoppingManager();
					super.okPressed();
				}else {
					MessageDialog.openInformation(getShell(), TITLE, "�ΰ����������� �����Ͽ����ϴ�.");
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
		btnOk = createButton(parent, IDialogConstants.OK_ID, "����", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "�ݱ�", false);
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
