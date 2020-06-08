package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ResourceManager;
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
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopDeliveryDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductInterParkAdditionDto;
import com.kdj.mlink.ezup3.common.CommonCalander;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
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

public class ShopInterparkAdditionalInformationDetail extends CommandDialog {

	String TITLE = "인터파크 부가정보 관리";
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

	private Text txt_retNcngcost;
	private Button btn_delvtype1;
	private Button btn_delvtype2;
	private Button btn_delvtype3;
	private Button btn_delvtype4;
	private Button btn_delvtype5;
	private Label lblNewLabel_11;
	private Label lblNewLabel_12;
	private Label lblNewLabel_13;
	private Label lblNewLabel_21;
	private Button btn_freechk;
	private Text txt_delvcost1;
	private Text txt_delvcost2;
	private Text txt_delvcost2_1;
	private Text txt_delvcost3;
	private Button btn_onechk;
	private Text txt_delvcost2_2;
	private Composite composite_13;
	private Text txt_qty1_1;
	private Text txt_qty2_1;
	private Label lblNewLabel_42;
	private Text txt_qty1_2;
	private Label lblNewLabel_33;
	private Text txt_qty1_3;
	private Text txt_qty2_2;
	private Text txt_qty1_4;
	private Text txt_qty1_5;
	private Text txt_qty2_3;
	private Text txt_qty1_6;
	private Text txt_qty1_7;
	private Text txt_qty2_4;
	private Label lblNewLabel_46;
	private Label lblNewLabel_47;
	private Label lblNewLabel_48;
	private Composite composite_14;
	private Composite composite_15;
	private Label label_2;
	private Label label_6;
	private Label label_7;
	private Label label_9;
	private Label label_10;
	private Label label_11;

	String imgTargetPath;
	String imgTargetPath2;
	String imgTargetPath3;
	String Prodprodcd;
	private Label label;
	private Text txt_title;
	private Label label_3;
	private Label label_4;
	private Composite composite_4;
	private Composite composite_5;
	private Composite composite_6;
	private Composite composite_7;
	private Label label_23;
	List<String> list;
	private Label label_8;
	private Button btn_new;
	private Button btn_return;
	private Button btn_used;
	private Button btn_used_good;
	private Button btn_used_normal;
	private Label label_30;
	private Text txt_returnaddr;
	private Label label_35;
	private Combo cb_whether;
	private Button btn_fixed;
	private Composite composite_12;
	private Button txt_returnaddrsearch;
	ShopProductInterParkAdditionDto dto;
	private ScrolledComposite scrolledComposite;
	private Composite composite_29;
	String shopseq;
	private Label lblNewLabel_5;
	private Composite composite_3;
	private Composite composite_31;
	private Label lblNewLabel_57;
	private Text txt_memo;
	private Label label_1;
	private Label lblNewLabel;
	private Composite composite_1;
	private Text txt_jejucharge;
	private Text txt_islandcharge;
	private Label lblNewLabel_17;
	private Text txt_shipuniquene;
	private Text txt_defaultship;
	private Text txt_byte;
	private Label lblNewLabel_18;
	private Button btnNewButton;
	private Button btnNewButton_1;
	private Label lblNewLabel_19;
	private Composite composite_9;
	private Button btn_express;
	private Button btn_post;
	private Button btn_freight;
	private Button btn_notexpress;
	private Label lblNewLabel_20;
	private Composite composite_10;
	private Button btn_quantityN;
	private Button btn_quantityY;
	private Text txt_quantity;
	private Label lblNewLabel_23;
	private Composite composite_11;
	private Composite composite_exp;
	private Combo cb_salesperiodA;
	private Label lblNewLabel_24;
	private Combo cb_salesperiodB;
	private Label lblNewLabel_25;
	private Button btn_salesperiodN;
	private Button btn_salesperiodY;
	private Label lblNewLabel_26;
	private Composite composite_19;
	private Button btn_uniqueneN;
	private Button btn_uniqueneY;
	private Label lblNewLabel_27;
	private Text txt_asdtl;
	private Label lblNewLabel_28;
	private Label lblNewLabel_29;
	private Label lblNewLabel_30;
	private Label lblNewLabel_31;
	private Composite composite_21;
	private Button btn_minorY;
	private Button btn_minorN;
	private Label lblNewLabel_32;
	private Composite composite_22;
	private Button btn_overseasY;
	private Button btn_overseasN;
	private Label lblNewLabel_34;
	private Label lblNewLabel_35;
	private Composite composite_23;
	private Combo cb_prodno;
	private Label lblNewLabel_36;
	private Label lblNewLabel_37;
	private Composite composite_24;
	private Text txt_search1;
	private Text txt_search2;
	private Text txt_search3;
	private Text txt_search4;
	private Text txt_search5;
	private Label lblNewLabel_38;
	private Label lblNewLabel_39;
	private Label lblNewLabel_40;
	private Label lblNewLabel_41;
	private Button btn_instant;
	private Button btn_reservation;
	private Button btn_open;
	private Button btn_md;
	private Button btn_direct;
	private Button btn_defaultdelvcharge;
	private Button btn_exceptiondelvcharge;
	private Text txt_calfrom;
	private Label lb_cal;
	private Text txt_calto;
	private Button btn_delvchargedefault;
	private Button btn_delvchargeexception;
	private Label lblNewLabel_7;
	private Text txt_uniquene;
	private Button btn_notcertified;
	private Button btn_healthfood;
	private Button btn_proddescription;
	private Label lblNewLabel_8;
	private Text txt_salesreporting;
	private Label lblNewLabel_9;
	private Text txt_dealerreg;
	private Button btn_adreviewY;
	private Button btn_adreviewN;
	private Label lblNewLabel_10;
	private Text txt_adreviewno;
	private Composite composite_2;
	Label lblNewLabel_81;
	Label lblNewLabel_71;
	Label lblNewLabel_101;
	Label lblNewLabel_111;
	Label lblNewLabel_121;
	Label label_61;
	Label lblNewLabel_131;
	Label lblNewLabel_91;
	Label lblNewLabel_231;
	Label lblNewLabel_241;
	Label lblNewLabel_11l;
	Label lblNewLabel_171;
	Combo cb_prepayment;
	Label lblNewLabel_341;
	Label lblNewLabel_381;
	Label lblNewLabel_391;
	Label lblNewLabel_401;
	Label lblNewLabel_411;
	Label lblNewLabel_431;
	Label lblNewLabel_441;
	Label lblNewLabel_451;
	Label lblNewLabel_711;
	Label lblNewLabel_811;
	private Group grpZ;
	private Group group;
	private Label lblNewLabel_1;
	private Label lblNewLabel_43;
	private Group group_1;
	private Composite composite;
	private Label lblNewLabel_2;
	private Composite composite_8;
	private Button btn_interestfeeN;
	private Button btn_interestfeeY;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_6;
	private Composite composite_16;
	private Combo cb_ipoint;
	private Text txt_ipoint;
	private Label lblNewLabel_14;
	private Label lblNewLabel_15;
	private Label lblNewLabel_16;
	private Label lblNewLabel_22;
	private Composite composite_17;
	private Button btn_sellerdis1;
	private Button btn_sellerdis2;
	private Button btn_sellerdis1_1;
	private Button btn_sellerdis1_2;
	private Text txt_sellerdis1_1;
	private Label lb_sellerdis1_1;
	private Text txt_sellerdis2_1;
	private Label lb_sellerdis2_1;
	private Text txt_sellerdis2_2;
	private Button btn_sellerdis2_1del;
	private Label lb_sellerdis2_2;
	private Composite composite_18;
	private Composite composite_20;
	private Label lblNewLabel_44;
	private Composite composite_25;
	private Text txt_bottom;
	private Label lblNewLabel_45;
	private Composite composite_26;
	private Text txt_top;
	private Label lblNewLabel_49;
	private Composite composite_27;
	private Label lblNewLabel_50;
	private Label lblNewLabel_51;
	private Label lblNewLabel_52;
	private Button btn_toysex1;
	private Button btn_toysex2;
	private Button btn_toysex3;
	private Composite composite_28;
	private Button btn_toyage1;
	private Button btn_toyage2;
	private Button btn_toyage3;
	private Composite composite_30;
	private Text txt_toyage2_1;
	private Label lb_toyage2_1;
	private Text txt_toyage2_2;
	private Combo cb_toyage2_1;
	private Text txt_toyage3_1;
	private Combo cb_toyage3_1;
	private Combo cb_toyage3_2;
	private Label lblNewLabel_53;
	private Composite composite_32;
	private Text txt_usesale;
	private Label lblNewLabel_54;
	private Label lblNewLabel_55;
	private Label lblNewLabel_56;
	private Composite composite_33;
	private Label lblNewLabel_58;
	private Label lblNewLabel_59;
	private Text txt_phoneurl1;
	private Text txt_phoneurl2;
	private Combo cb_phoneurl1;
	private Combo cb_phoneurl2;
	private Label lblNewLabel_60;
	private Composite composite_34;
	private Button btn_usim1;
	private Button btn_usim2;
	private Label lblNewLabel_61;
	private Composite composite_35;
	private Button btn_activa1;
	private Button btn_activa2;
	private Label lblNewLabel_62;
	private Composite composite_36;
	private Button btn_terminalprice1;
	private Button btn_terminalprice2;
	private Label lblNewLabel_63;
	private Label lblNewLabel_64;
	private Label lblNewLabel_65;
	private Label lblNewLabel_66;
	private Composite composite_37;
	private Button btn_pricecompar1;
	private Button btn_pricecompar2;
	private Label lblNewLabel_67;
	private Composite composite_38;
	private Combo cb_2opt;
	private Composite composite_39;
	private Label lblNewLabel_68;
	private Label lblNewLabel_69;
	private Label lblNewLabel_70;
	private Composite composite_40;
	Button btn_optdesc1;
	Button btn_optdesc2;
	Button btn_optdesc3;
	Button btn_optdesc4;
	private Label lblNewLabel_72;
	private Composite composite_41;
	private Button btn_inputopt1;
	private Button btn_inputopt2;
	private Text txt_inputopt1;
	private Label lblNewLabel_73;
	private Label lblNewLabel_74;
	private Composite composite_42;
	private Label lblNewLabel_75;
	private Label lblNewLabel_76;
	private Label lblNewLabel_77;
	private Label lblNewLabel_78;
	private Label lblNewLabel_79;
	private Label lblNewLabel_80;
	private Composite composite_43;
	private Button btn_rental5_1;
	private Button btn_rental5_2;
	private Text txt_rental5_1;
	private Label lblNewLabel_82;
	private Composite composite_44;
	private Button btn_rental4_1;
	private Button btn_rental4_2;
	private Text txt_rental4_1;
	private Label lblNewLabel_83;
	private Button btn_rental4_3;
	private Composite composite_45;
	private Button btn_rental3_1;
	private Button btn_rental3_2;
	private Button btn_rental3_3;
	private Text txt_rental3_1;
	private Label lblNewLabel_84;
	private Button btn_rental3_4;
	private Composite composite_46;
	private Text txt_rental2;
	private Label lblNewLabel_85;
	private Composite composite_47;
	private Label lblNewLabel_86;
	private Label lblNewLabel_87;
	private Composite composite_48;
	private Combo cb_consumcost;
	private Label lblNewLabel_88;
	private Label lblNewLabel_89;
	private Label lblNewLabel_90;
	private Label lblNewLabel_92;
	private Composite composite_49;
	private Label lblNewLabel_93;
	private Label lblNewLabel_94;
	private Label lblNewLabel_95;
	private Label lblNewLabel_96;
	private Label lblNewLabel_97;
	private Label lblNewLabel_98;
	private Label lblNewLabel_99;
	private Label lblNewLabel_100;
	private Label lblNewLabel_102;
	private Composite composite_50;
	private Combo cb_isbn;
	private Label lblNewLabel_103;
	private Composite composite_51;
	private Text txt_usepreiod;
	private Label lblNewLabel_104;
	private Label lblNewLabel_105;
	private Label lblNewLabel_106;
	private Composite composite_52;
	private Text txt_importno;
	private Label lblNewLabel_107;
	private Label lblNewLabel_108;
	private Label lblNewLabel_109;
	private Composite composite_53;
	private Combo cb_modelno;
	private Label lblNewLabel_110;
	private Composite composite_54;
	private Text txt_brand1;
	private Text txt_brand2;
	private Button btn_brandsearch;
	private Button btn_branddel;
	private Label lblNewLabel_112;
	private Label lblNewLabel_113;
	private Label lblNewLabel_114;
	private Composite composite_55;
	private Composite composite_56;
	private Text txt_end;
	private Text txt_start;
	private Label lblNewLabel_115;
	private Label lblNewLabel_116;
	private Composite composite_57;
	private Combo cb_storeimg;
	private Label lblNewLabel_117;
	private Label lblNewLabel_118;
	private Label lblNewLabel_119;
	private Composite composite_58;
	private Combo cb_repimg;
	private Label lblNewLabel_120;
	private Label lblNewLabel_122;
	private Label lblNewLabel_123;
	private Composite composite_59;
	private Button btn_overarea1;
	private Button btn_overarea2;
	private Label lblNewLabel_124;
	private Label lblNewLabel_125;
	private Composite composite_60;
	private Text txt_categ2;
	private Button btn_categ2del;
	private Label lblNewLabel_126;
	private Composite composite_61;
	private Text txt_categ1;
	private Button btn_categ1del;
	private Label lblNewLabel_127;
	private Composite composite_62;
	private Text txt_brandcateg;
	private Button btn_brandcategdel;
	private Label lblNewLabel_128;
	private Composite composite_63;
	private Text txt_categfee;
	private Label lblNewLabel_129;
	private Label lblNewLabel_130;
	Combo cb_idcheck;
	Label newLabel;
	private List<ShopDeliveryDto> datasource = null;

	public ShopInterparkAdditionalInformationDetail(Shell parentShell, ShopAdditionalInforMationManager opener,
			List<String> list, ShopProductInterParkAdditionDto dto, String shopseq) {
		super(parentShell);
		this.opener = opener;
		this.list = list;
		this.dto = dto;
		this.shopseq = shopseq;
		try {
			if (list == null) {
				 datasource = ShopCommonDao.get().getExpress(dto.getShopcd());
			} else {
				datasource = ShopCommonDao.get().getExpress(list.get(0));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite container = new Composite(scrolledComposite, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		grpZ = new Group(container, SWT.NONE);
		grpZ.setLayout(new GridLayout(2, false));
		grpZ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		grpZ.setText("■ [KEY 정보]");

		label = new Label(grpZ, SWT.NONE);
		label.setText("※ 제 목");

		txt_title = new Text(grpZ, SWT.BORDER);
		GridData gd_txt_title = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_title.widthHint = 700;
		txt_title.setLayoutData(gd_txt_title);

		lblNewLabel_57 = new Label(grpZ, SWT.NONE);
		lblNewLabel_57.setText("   메 모");

		txt_memo = new Text(grpZ, SWT.BORDER);
		GridData gd_txt_memo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_memo.widthHint = 700;
		txt_memo.setLayoutData(gd_txt_memo);

		lblNewLabel_130 = new Label(grpZ, SWT.NONE);
		lblNewLabel_130.setText("※ 아이디 선택");

		cb_idcheck = new Combo(grpZ, SWT.NONE);
		GridData gd_cb_idcheck = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_idcheck.widthHint = 120;
		cb_idcheck.setLayoutData(gd_cb_idcheck);
		cb_idcheck.setItems(new String[] { "아이디선택" });
		cb_idcheck.select(0);

		Label label_15 = new Label(grpZ, SWT.NONE);
		GridData gd_label_15 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_15.widthHint = 150;
		label_15.setLayoutData(gd_label_15);
		label_15.setText("※ 형태구분");

		btn_fixed = new Button(grpZ, SWT.RADIO);
		btn_fixed.setText("고정가판매");
		btn_fixed.setSelection(true);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		group = new Group(container, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		group.setText("■ [판매조건정보]");

		lblNewLabel_5 = new Label(group, SWT.NONE);
		lblNewLabel_5.setText("※ 계약유형");

		composite_3 = new Composite(group, SWT.NONE);
		composite_3.setLayout(new GridLayout(3, false));

		btn_open = new Button(composite_3, SWT.RADIO);
		btn_open.setText("오픈마켓");

		btn_md = new Button(composite_3, SWT.RADIO);
		btn_md.setText("MD");

		btn_direct = new Button(composite_3, SWT.RADIO);
		btn_direct.setText("직매입");

		Label lblNewLabel_6 = new Label(group, SWT.NONE);
		lblNewLabel_6.setText("※ 판매방식");

		composite_31 = new Composite(group, SWT.NONE);
		composite_31.setLayout(new GridLayout(2, false));

		btn_instant = new Button(composite_31, SWT.RADIO);
		btn_instant.setText("즉시판매");

		btn_reservation = new Button(composite_31, SWT.RADIO);
		btn_reservation.setText("예약판매");

		label_8 = new Label(group, SWT.NONE);
		label_8.setText("※ 상품상태");

		composite_12 = new Composite(group, SWT.NONE);
		composite_12.setLayout(new GridLayout(5, false));

		btn_new = new Button(composite_12, SWT.RADIO);
		btn_new.setText("새상품");

		btn_used = new Button(composite_12, SWT.RADIO);
		btn_used.setText("중고상품");

		btn_return = new Button(composite_12, SWT.RADIO);
		btn_return.setText("반품상품");
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);

		lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_1.setText("* 사방넷은 카테고리 선택시, 표준 수수료가 조회됩니다.");

		lblNewLabel_43 = new Label(group, SWT.NONE);
		lblNewLabel_43.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_43.setText("* 계약 수수료가 설정되어 있거나, 인터파크 SCM의 수수료와 일치하지 않는 경우에는 수정입력하시기 바랍니다.");
		lblNewLabel_43.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_128 = new Label(group, SWT.NONE);
		lblNewLabel_128.setText("※ 카테고리 수수료율");

		composite_63 = new Composite(group, SWT.NONE);
		composite_63.setLayout(new GridLayout(2, false));
		composite_63.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_categfee = new Text(composite_63, SWT.BORDER);
		GridData gd_txt_categfee = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_categfee.widthHint = 70;
		txt_categfee.setLayoutData(gd_txt_categfee);

		lblNewLabel_129 = new Label(composite_63, SWT.NONE);
		lblNewLabel_129.setText(" %");

		lblNewLabel_127 = new Label(group, SWT.NONE);
		lblNewLabel_127.setText("   브랜드 카테고리");

		composite_62 = new Composite(group, SWT.NONE);
		composite_62.setLayout(new GridLayout(2, false));
		composite_62.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_brandcateg = new Text(composite_62, SWT.BORDER);
		GridData gd_txt_brandcateg = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_brandcateg.widthHint = 700;
		txt_brandcateg.setLayoutData(gd_txt_brandcateg);

		btn_brandcategdel = new Button(composite_62, SWT.NONE);
		btn_brandcategdel.setText("삭제");

		lblNewLabel_126 = new Label(group, SWT.NONE);
		lblNewLabel_126.setText("   테마 카테고리1");

		composite_61 = new Composite(group, SWT.NONE);
		composite_61.setLayout(new GridLayout(2, false));
		composite_61.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_categ1 = new Text(composite_61, SWT.BORDER);
		GridData gd_txt_categ1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_categ1.widthHint = 700;
		txt_categ1.setLayoutData(gd_txt_categ1);

		btn_categ1del = new Button(composite_61, SWT.NONE);
		btn_categ1del.setText("삭제");

		lblNewLabel_125 = new Label(group, SWT.NONE);
		lblNewLabel_125.setText("   테마 카테고리2");

		composite_60 = new Composite(group, SWT.NONE);
		composite_60.setLayout(new GridLayout(2, false));
		composite_60.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_categ2 = new Text(composite_60, SWT.BORDER);
		GridData gd_txt_categ2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_categ2.widthHint = 700;
		txt_categ2.setLayoutData(gd_txt_categ2);

		btn_categ2del = new Button(composite_60, SWT.NONE);
		btn_categ2del.setText("삭제");

		lblNewLabel_123 = new Label(group, SWT.NONE);
		lblNewLabel_123.setText("   해외배송여부");

		composite_59 = new Composite(group, SWT.NONE);
		composite_59.setLayout(new GridLayout(2, false));
		composite_59.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_overarea1 = new Button(composite_59, SWT.CHECK);
		btn_overarea1.setText("해외판매를 진행");
		new Label(composite_59, SWT.NONE);

		btn_overarea2 = new Button(composite_59, SWT.CHECK);
		btn_overarea2.setText("상품최소무게(0.1kg)");

		lblNewLabel_124 = new Label(composite_59, SWT.NONE);
		lblNewLabel_124.setText(
				"  * 최소 무게 0.1kg로 등록하는 경우 체크합니다. 그 외 상품 무게는 상품정보고시 내 '무게' 항목이 kg(단위)으로 치환되어 등록됩니다. 예)190 -> 0.19(kg)으로 전송");
		lblNewLabel_124.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_122 = new Label(group, SWT.NONE);
		lblNewLabel_122.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_122.setText("* 인터파크 대표이미지를 특정 부가이미지로 지정할 경우에만 선택하시기 바랍니다.");
		lblNewLabel_122.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_120 = new Label(group, SWT.NONE);
		lblNewLabel_120.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_120.setText("* 선택한 부가이미지에 파일이 없는 경우에는 대표이미지가 우선 사용됩니다.");
		lblNewLabel_120.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_119 = new Label(group, SWT.NONE);
		lblNewLabel_119.setText("   대표이미지 설정");

		composite_58 = new Composite(group, SWT.NONE);
		composite_58.setLayout(new GridLayout(1, false));
		composite_58.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_repimg = new Combo(composite_58, SWT.NONE);
		cb_repimg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_repimg.setItems(new String[] { "사용안함", "부가이미지6", "부가이미지7", "부가이미지8", "부가이미지9", "부가이미지10", "부가이미지11",
				"부가이미지12", "부가이미지13", "부가이미지14", "부가이미지15", "부가이미지16", "부가이미지17", "부가이미지18", "부가이미지19", "부가이미지20",
				"부가이미지21", "부가이미지22" });
		cb_repimg.select(0);

		lblNewLabel_118 = new Label(group, SWT.NONE);
		lblNewLabel_118.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_118.setText("* 인터파크 스토어디 상품으로 등록시 추가이미지1(350X500)로 업로드할 부가이미지를 선택합니다.");
		lblNewLabel_118.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_117 = new Label(group, SWT.NONE);
		lblNewLabel_117.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_117.setText("* 단, 일반몰 등록인 경우에는 반드시 사용안함으로 선택하시기 바랍니다.");
		lblNewLabel_117.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_116 = new Label(group, SWT.NONE);
		lblNewLabel_116.setText("   스토어디 추가이미지1");

		composite_57 = new Composite(group, SWT.NONE);
		composite_57.setLayout(new GridLayout(1, false));
		composite_57.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_storeimg = new Combo(composite_57, SWT.NONE);
		cb_storeimg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_storeimg.setItems(new String[] { "사용안함", "부가이미지18", "부가이미지19", "부가이미지20", "부가이미지21", "부가이미지22" });
		cb_storeimg.select(0);

		lblNewLabel_115 = new Label(group, SWT.NONE);
		lblNewLabel_115.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_115.setText("* 홍보문구는 상품명 뒤에 노출되며 검색대상에 포함되지 않습니다.");
		lblNewLabel_115.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_114 = new Label(group, SWT.NONE);
		lblNewLabel_114.setText("   상품명 추가 앞문구");

		composite_56 = new Composite(group, SWT.NONE);
		composite_56.setLayout(new GridLayout(1, false));
		composite_56.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_start = new Text(composite_56, SWT.BORDER);
		GridData gd_txt_start = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_start.widthHint = 700;
		txt_start.setLayoutData(gd_txt_start);

		lblNewLabel_113 = new Label(group, SWT.NONE);
		lblNewLabel_113.setText("   상품명 추가 뒷문구");

		composite_55 = new Composite(group, SWT.NONE);
		composite_55.setLayout(new GridLayout(1, false));
		composite_55.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_end = new Text(composite_55, SWT.BORDER);
		GridData gd_txt_end = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_end.widthHint = 700;
		txt_end.setLayoutData(gd_txt_end);

		lblNewLabel_110 = new Label(group, SWT.NONE);
		lblNewLabel_110.setText("   브랜드");

		composite_54 = new Composite(group, SWT.NONE);
		composite_54.setLayout(new GridLayout(5, false));
		composite_54.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_brand1 = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_brand1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_brand1.widthHint = 150;
		txt_brand1.setLayoutData(gd_txt_brand1);

		txt_brand2 = new Text(composite_54, SWT.BORDER);
		GridData gd_txt_brand2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_brand2.widthHint = 250;
		txt_brand2.setLayoutData(gd_txt_brand2);

		btn_brandsearch = new Button(composite_54, SWT.NONE);
		btn_brandsearch.setText("검색");

		btn_branddel = new Button(composite_54, SWT.NONE);
		btn_branddel.setText("삭제");

		lblNewLabel_112 = new Label(composite_54, SWT.NONE);
		lblNewLabel_112.setText("* 카테고리 변경시 브랜드를 다시 선택해주세요.");
		lblNewLabel_112.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_109 = new Label(group, SWT.NONE);
		lblNewLabel_109.setText("   모델명/품번 선택");

		composite_53 = new Composite(group, SWT.NONE);
		composite_53.setLayout(new GridLayout(1, false));
		composite_53.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_modelno = new Combo(composite_53, SWT.NONE);
		cb_modelno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_modelno.setItems(new String[] { "M-Link 품번 모델NO", "M-Link 품번 모델명" });
		cb_modelno.select(0);

		lblNewLabel_106 = new Label(group, SWT.NONE);
		lblNewLabel_106.setText("   수입신고번호");

		composite_52 = new Composite(group, SWT.NONE);
		composite_52.setLayout(new GridLayout(3, false));
		composite_52.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_importno = new Text(composite_52, SWT.BORDER);
		GridData gd_txt_importno = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_importno.widthHint = 400;
		txt_importno.setLayoutData(gd_txt_importno);

		lblNewLabel_107 = new Label(composite_52, SWT.NONE);
		lblNewLabel_107.setText("* 설정시, 부가정보의 수입신고번호가 우선 사용됩니다. ");
		lblNewLabel_107.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_108 = new Label(composite_52, SWT.NONE);
		lblNewLabel_108.setText(" 예) 12345-12-1234567");

		lblNewLabel_38 = new Label(group, SWT.NONE);
		lblNewLabel_38.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_38.setText("* 검색대상 키워드는 상품 검색 대상에 포함되는 키워드로,상품명 외에 등록하실 상품을 설명하는부가 키워드로 활용하실 수 있습니다.(한/영 10자 이내)");
		lblNewLabel_38.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_39 = new Label(group, SWT.NONE);
		lblNewLabel_39.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_39.setText("* 예)상품명 : 신라면블랙 40입/신라면/농심/컵라면, 검색대상 키워드:싸이라면,블랙신컵");
		lblNewLabel_39.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_40 = new Label(group, SWT.NONE);
		lblNewLabel_40.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_40.setText("* 상품과 관련이 없거나 부적절한 내용은 검색, 구매활동 저해의 원인이 되어 제재를 받을 수있으므로 주의하시기 바랍니다.");
		lblNewLabel_40.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_41 = new Label(group, SWT.NONE);
		lblNewLabel_41.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_41.setText("* 부가정보의 검색 대상 키워드를 입력하지 않는 경우 M-Link 상품 정보의 사이트검색어 항목으로 전송됩니다.");
		lblNewLabel_41.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_37 = new Label(group, SWT.NONE);
		lblNewLabel_37.setText("   검색 대상 키워드");

		composite_24 = new Composite(group, SWT.NONE);
		composite_24.setLayout(new GridLayout(5, false));
		composite_24.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_search1 = new Text(composite_24, SWT.BORDER);
		GridData gd_txt_search1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_search1.widthHint = 200;
		txt_search1.setLayoutData(gd_txt_search1);

		txt_search2 = new Text(composite_24, SWT.BORDER);
		GridData gd_txt_search2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_search2.widthHint = 200;
		txt_search2.setLayoutData(gd_txt_search2);

		txt_search3 = new Text(composite_24, SWT.BORDER);
		GridData gd_txt_search3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_search3.widthHint = 200;
		txt_search3.setLayoutData(gd_txt_search3);

		txt_search4 = new Text(composite_24, SWT.BORDER);
		GridData gd_txt_search4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_search4.widthHint = 200;
		txt_search4.setLayoutData(gd_txt_search4);

		txt_search5 = new Text(composite_24, SWT.BORDER);
		GridData gd_txt_search5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_search5.widthHint = 200;
		txt_search5.setLayoutData(gd_txt_search5);

		lblNewLabel_35 = new Label(group, SWT.NONE);
		lblNewLabel_35.setText("   판매자상품번호");

		composite_23 = new Composite(group, SWT.NONE);
		composite_23.setLayout(new GridLayout(2, false));
		composite_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_prodno = new Combo(composite_23, SWT.NONE);
		cb_prodno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_prodno.setItems(new String[] { "M-Link 상품코드 사용", "자체 상품코드 사용", "M-Link 품번모델NO" });
		cb_prodno.select(0);

		lblNewLabel_36 = new Label(composite_23, SWT.NONE);
		lblNewLabel_36.setText("* M-Link 상품코드 사용 : 연동송신NO 입니다");

		lblNewLabel_33 = new Label(group, SWT.NONE);
		lblNewLabel_33.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_33.setText("* 해외구매대행 상품 설정은 상품 등록송신에만 반영되며, 수정송신시에는 기존 정보를 유지합니다.");
		lblNewLabel_33.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_34 = new Label(group, SWT.NONE);
		lblNewLabel_34.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_34.setText("* 단, M-Link는 주문수집시, 주민등록번호를 수집하지 않습니다.");
		lblNewLabel_34.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_32 = new Label(group, SWT.NONE);
		lblNewLabel_32.setText("   해외구매대행 상품");

		composite_22 = new Composite(group, SWT.NONE);
		composite_22.setLayout(new GridLayout(2, false));
		composite_22.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_overseasY = new Button(composite_22, SWT.RADIO);
		btn_overseasY.setText("예");

		btn_overseasN = new Button(composite_22, SWT.RADIO);
		btn_overseasN.setText("아니오");

		lblNewLabel_31 = new Label(group, SWT.NONE);
		lblNewLabel_31.setText("※ 미성년자구매가능");

		composite_21 = new Composite(group, SWT.NONE);
		composite_21.setLayout(new GridLayout(2, false));
		composite_21.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_minorY = new Button(composite_21, SWT.RADIO);
		btn_minorY.setText("예");

		btn_minorN = new Button(composite_21, SWT.RADIO);
		btn_minorN.setText("아니오");

		lblNewLabel_105 = new Label(group, SWT.NONE);
		lblNewLabel_105.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_105.setText("* 상품상태를 중고상품으로 선택한 경우 사용개월수 입력은 필수입니다.");
		lblNewLabel_105.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_103 = new Label(group, SWT.NONE);
		lblNewLabel_103.setText("   사용개월수");

		composite_51 = new Composite(group, SWT.NONE);
		composite_51.setLayout(new GridLayout(2, false));
		composite_51.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_usepreiod = new Text(composite_51, SWT.BORDER);
		GridData gd_txt_usepreiod = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_usepreiod.widthHint = 70;
		txt_usepreiod.setLayoutData(gd_txt_usepreiod);

		lblNewLabel_104 = new Label(composite_51, SWT.NONE);
		lblNewLabel_104.setText(" 개월");

		lblNewLabel_102 = new Label(group, SWT.NONE);
		lblNewLabel_102.setText("   도서상품 ISBN");

		composite_50 = new Composite(group, SWT.NONE);
		composite_50.setLayout(new GridLayout(1, false));
		composite_50.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_isbn = new Combo(composite_50, SWT.NONE);
		cb_isbn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_isbn.setItems(new String[] { "사용안함", "모델No.를 ISBN으로 입력함" });
		cb_isbn.select(0);

		lblNewLabel_92 = new Label(group, SWT.NONE);
		lblNewLabel_92.setText("   생활용품/전기용품/어린이제품\r\n" + "   인증정보");

		composite_49 = new Composite(group, SWT.NONE);
		composite_49.setLayout(new GridLayout(3, false));
		composite_49.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_93 = new Label(composite_49, SWT.NONE);
		lblNewLabel_93.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_93.setText("상품정보의 인증분야, 인증기관, 인증번호를 이용하여 등록합니다.");

		lblNewLabel_94 = new Label(composite_49, SWT.NONE);
		lblNewLabel_94.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_94.setText("인증분야 입력 예시)");

		lblNewLabel_95 = new Label(composite_49, SWT.NONE);
		lblNewLabel_95.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_95.setText("생활용품/안전인증, 생활용품/자율안전확인, 생활용품/어린이보호포장, 생활용품/안전품질표시");

		lblNewLabel_96 = new Label(composite_49, SWT.NONE);
		lblNewLabel_96.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_96.setText("전기용품/안전인증, 전기용품/자율안전확인");

		lblNewLabel_97 = new Label(composite_49, SWT.NONE);
		lblNewLabel_97.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_97.setText("어린이제품/안전인증, 어린이제품/자율안전확인, 어린이제품/공급자적합성확인");

		lblNewLabel_98 = new Label(composite_49, SWT.NONE);
		lblNewLabel_98.setText("**상품설명 내 별도 표기 선택조건** 인증번호 : ");

		lblNewLabel_99 = new Label(composite_49, SWT.NONE);
		lblNewLabel_99.setText("상세정보참조 ");
		lblNewLabel_99.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		lblNewLabel_100 = new Label(composite_49, SWT.NONE);
		lblNewLabel_100.setText("입력");

		lblNewLabel_30 = new Label(group, SWT.NONE);
		lblNewLabel_30.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_30.setText("* 카테고리를 건강식품 선택시 입력해 주세요.");
		lblNewLabel_30.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_28 = new Label(group, SWT.NONE);
		lblNewLabel_28.setText("건강기능식품 허가사항");

		Composite composite_20 = new Composite(group, SWT.NONE);
		composite_20.setLayout(new GridLayout(5, false));
		composite_20.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_healthfood = new Button(composite_20, SWT.RADIO);
		btn_healthfood.setText("건강기능식품 대상품");
		btn_healthfood.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setHealthFood();
			}
		});
		btn_proddescription = new Button(composite_20, SWT.RADIO);
		btn_proddescription.setText("상품설명 내 표기");
		btn_proddescription.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setHealthFood();
			}
		});
		btn_notcertified = new Button(composite_20, SWT.RADIO);
		btn_notcertified.setText("인증대상아님");
		new Label(composite_20, SWT.NONE);
		new Label(composite_20, SWT.NONE);

		lblNewLabel_8 = new Label(composite_20, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_8.setText(" 판매업 신고기관");

		txt_salesreporting = new Text(composite_20, SWT.BORDER);
		GridData gd_txt_salesreporting = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_txt_salesreporting.widthHint = 350;
		txt_salesreporting.setLayoutData(gd_txt_salesreporting);

		lblNewLabel_9 = new Label(composite_20, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText(" 판매업 신고번호");

		txt_dealerreg = new Text(composite_20, SWT.BORDER);
		GridData gd_txt_dealerreg = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_dealerreg.widthHint = 300;
		txt_dealerreg.setLayoutData(gd_txt_dealerreg);

		composite_2 = new Composite(composite_20, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 5, 1));

		btn_adreviewY = new Button(composite_2, SWT.RADIO);
		btn_adreviewY.setText("사전광고심의 대상");

		btn_adreviewN = new Button(composite_2, SWT.RADIO);
		btn_adreviewN.setText("사전광고심의 대상아님");
		btn_adreviewY.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setAdreview();
			}
		});

		lblNewLabel_10 = new Label(composite_20, SWT.NONE);
		lblNewLabel_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_10.setText(" 광고심의번호");

		txt_adreviewno = new Text(composite_20, SWT.BORDER);
		GridData gd_txt_adreviewno = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_txt_adreviewno.widthHint = 400;
		txt_adreviewno.setLayoutData(gd_txt_adreviewno);
		new Label(composite_20, SWT.NONE);

		lblNewLabel_29 = new Label(group, SWT.NONE);
		lblNewLabel_29.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_29.setText("* 상품의 A/S 가능여부와 절차 및 가능기준을 입력해주세요");
		lblNewLabel_29.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_27 = new Label(group, SWT.NONE);
		lblNewLabel_27.setText("※ A/S가능여부");

		txt_asdtl = new Text(group, SWT.BORDER);
		GridData gd_txt_asdtl = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_asdtl.widthHint = 700;
		txt_asdtl.setLayoutData(gd_txt_asdtl);

		lblNewLabel_26 = new Label(group, SWT.NONE);
		lblNewLabel_26.setText("   특이사항");

		composite_19 = new Composite(group, SWT.NONE);
		composite_19.setLayout(new GridLayout(2, false));
		composite_19.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_uniqueneN = new Button(composite_19, SWT.RADIO);
		btn_uniqueneN.setText("특이 사항 없음");

		btn_uniqueneY = new Button(composite_19, SWT.RADIO);
		btn_uniqueneY.setText("특이 사항 있음");
		btn_uniqueneY.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setUniQueness();
			}
		});
		lblNewLabel_7 = new Label(composite_19, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_7.setText("* 단, 영문 250자(한글 120자)까지만 입력 가능합니다.");
		lblNewLabel_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_7.setEnabled(false);

		txt_uniquene = new Text(composite_19, SWT.BORDER);
		GridData gd_txt_uniquene = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_txt_uniquene.widthHint = 700;
		txt_uniquene.setLayoutData(gd_txt_uniquene);
		txt_uniquene.setEnabled(false);

		lblNewLabel_90 = new Label(group, SWT.NONE);
		lblNewLabel_90.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_90.setText("* 소비자가 적용은 인터파크에 소비자가 입력기능이 열려있는 업체에 대해서만 반영됩니다.");
		lblNewLabel_90.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_89 = new Label(group, SWT.NONE);
		lblNewLabel_89.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_89.setText("* 소비자가 적용은 도서카테고리는 필수 선택입니다.");
		lblNewLabel_89.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_88 = new Label(group, SWT.NONE);
		lblNewLabel_88.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_88.setText("* 소비자가 적용여부 선택 시 렌탈 카테고리의 '월렌탈료'로 반영됩니다.");
		lblNewLabel_88.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_87 = new Label(group, SWT.NONE);
		lblNewLabel_87.setText("   소비자가 적용여부");

		composite_48 = new Composite(group, SWT.NONE);
		composite_48.setLayout(new GridLayout(1, false));
		composite_48.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_consumcost = new Combo(composite_48, SWT.NONE);
		cb_consumcost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_consumcost.setItems(new String[] { "사용안함", "M-Link TAG가로 전송" });
		cb_consumcost.select(0);

		lblNewLabel_74 = new Label(group, SWT.NONE);
		lblNewLabel_74.setText("   렌탈 카테고리 추가사항");

		composite_42 = new Composite(group, SWT.NONE);
		composite_42.setLayout(new GridLayout(2, false));
		composite_42.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_75 = new Label(composite_42, SWT.NONE);
		lblNewLabel_75.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_75.setText("* 카테고리가 렌탈일 경우 세팅하여 주세요.");

		lblNewLabel_76 = new Label(composite_42, SWT.NONE);
		GridData gd_lblNewLabel_76 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_76.widthHint = 150;
		lblNewLabel_76.setLayoutData(gd_lblNewLabel_76);
		lblNewLabel_76.setText("▣ 월렌탈료");

		composite_47 = new Composite(composite_42, SWT.NONE);
		composite_47.setLayout(new GridLayout(1, false));
		composite_47.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_86 = new Label(composite_47, SWT.NONE);
		lblNewLabel_86.setText("소비자가 적용여부를 '사방넷 TAG가로 전송' 을 선택해주세요. 렌탈 카테고리의 '월렌탈료'로 반영됩니다.");
		lblNewLabel_86.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_77 = new Label(composite_42, SWT.NONE);
		GridData gd_lblNewLabel_77 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_77.widthHint = 150;
		lblNewLabel_77.setLayoutData(gd_lblNewLabel_77);
		lblNewLabel_77.setText("▣ 의무사용기간");

		composite_46 = new Composite(composite_42, SWT.NONE);
		composite_46.setLayout(new GridLayout(2, false));
		composite_46.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_rental2 = new Text(composite_46, SWT.BORDER);
		GridData gd_txt_rental2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_rental2.widthHint = 100;
		txt_rental2.setLayoutData(gd_txt_rental2);

		lblNewLabel_85 = new Label(composite_46, SWT.NONE);
		lblNewLabel_85.setText("개월");

		lblNewLabel_78 = new Label(composite_42, SWT.NONE);
		GridData gd_lblNewLabel_78 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_78.widthHint = 150;
		lblNewLabel_78.setLayoutData(gd_lblNewLabel_78);
		lblNewLabel_78.setText("▣ 설치비");

		composite_45 = new Composite(composite_42, SWT.NONE);
		composite_45.setLayout(new GridLayout(6, false));
		composite_45.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_rental3_1 = new Button(composite_45, SWT.RADIO);
		btn_rental3_1.setText("해당사항 없음");

		btn_rental3_2 = new Button(composite_45, SWT.RADIO);
		btn_rental3_2.setText("무료");

		btn_rental3_3 = new Button(composite_45, SWT.RADIO);
		btn_rental3_3.setText("유료");

		txt_rental3_1 = new Text(composite_45, SWT.BORDER);
		GridData gd_txt_rental3_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_rental3_1.widthHint = 100;
		txt_rental3_1.setLayoutData(gd_txt_rental3_1);

		lblNewLabel_84 = new Label(composite_45, SWT.NONE);
		lblNewLabel_84.setText("원 ");

		btn_rental3_4 = new Button(composite_45, SWT.RADIO);
		btn_rental3_4.setText("상세설명참조");

		lblNewLabel_79 = new Label(composite_42, SWT.NONE);
		GridData gd_lblNewLabel_79 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_79.widthHint = 150;
		lblNewLabel_79.setLayoutData(gd_lblNewLabel_79);
		lblNewLabel_79.setText("▣ 등록비");

		composite_44 = new Composite(composite_42, SWT.NONE);
		composite_44.setLayout(new GridLayout(5, false));
		composite_44.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_rental4_1 = new Button(composite_44, SWT.RADIO);
		btn_rental4_1.setText("면제");

		btn_rental4_2 = new Button(composite_44, SWT.RADIO);
		btn_rental4_2.setText("유료");

		txt_rental4_1 = new Text(composite_44, SWT.BORDER);
		GridData gd_txt_rental4_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_rental4_1.widthHint = 100;
		txt_rental4_1.setLayoutData(gd_txt_rental4_1);

		lblNewLabel_83 = new Label(composite_44, SWT.NONE);
		lblNewLabel_83.setText("원 ");

		btn_rental4_3 = new Button(composite_44, SWT.RADIO);
		btn_rental4_3.setText("상세설명참조");

		lblNewLabel_80 = new Label(composite_42, SWT.NONE);
		GridData gd_lblNewLabel_80 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_80.widthHint = 150;
		lblNewLabel_80.setLayoutData(gd_lblNewLabel_80);
		lblNewLabel_80.setText("▣ 권장소비자가");

		composite_43 = new Composite(composite_42, SWT.NONE);
		composite_43.setLayout(new GridLayout(4, false));
		composite_43.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_rental5_1 = new Button(composite_43, SWT.RADIO);
		btn_rental5_1.setText("없음");

		btn_rental5_2 = new Button(composite_43, SWT.RADIO);
		btn_rental5_2.setText("있음");

		txt_rental5_1 = new Text(composite_43, SWT.BORDER);
		GridData gd_txt_rental5_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_rental5_1.widthHint = 100;
		txt_rental5_1.setLayoutData(gd_txt_rental5_1);

		lblNewLabel_82 = new Label(composite_43, SWT.NONE);
		lblNewLabel_82.setText("원");

		lblNewLabel_25 = new Label(group, SWT.NONE);
		lblNewLabel_25.setText("※ 판매기간 설정");

		composite_14 = new Composite(group, SWT.NONE);
		composite_14.setLayout(new GridLayout(2, false));
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_salesperiodN = new Button(composite_14, SWT.RADIO);
		btn_salesperiodN.setText("설정안함");
		btn_salesperiodN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSalesCalendar();
			}
		});
		btn_salesperiodY = new Button(composite_14, SWT.RADIO);
		btn_salesperiodY.setText("설정함");

		lblNewLabel_23 = new Label(group, SWT.NONE);
		lblNewLabel_23.setText("※ 판매기간");

		composite_11 = new Composite(group, SWT.NONE);
		composite_11.setLayout(null);
		composite_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_salesperiodA = new Combo(composite_11, SWT.NONE);
		cb_salesperiodA.setBounds(5, 5, 126, 28);
		cb_salesperiodA.setItems(new String[] { "기간으로 지정", "마감일로 지정" });
		cb_salesperiodA.select(0);
		cb_salesperiodA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Salesperiod();
			}
		});

		lblNewLabel_24 = new Label(composite_11, SWT.NONE);
		lblNewLabel_24.setBounds(136, 9, 146, 20);
		lblNewLabel_24.setText("상품 등록일로부터 ~ ");

		cb_salesperiodB = new Combo(composite_11, SWT.NONE);
		cb_salesperiodB.setBounds(287, 5, 85, 28);
		cb_salesperiodB.setItems(new String[] { "7일간", "15일간", "30일간", "60일간", "90일간", "180일간", "360일간" });
		cb_salesperiodB.select(4);

		txt_calfrom = new Text(composite_11, SWT.BORDER);
		txt_calfrom.setBounds(140, 6, 89, 26);
		txt_calfrom.setVisible(false);
		txt_calfrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_calfrom);
				calander.open();
			}
		});
		lb_cal = new Label(composite_11, SWT.NONE);
		lb_cal.setBounds(234, 9, 11, 20);
		lb_cal.setText("~");

		txt_calto = new Text(composite_11, SWT.BORDER);
		txt_calto.setBounds(250, 6, 89, 26);
		txt_calto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_calto);
				calander.open();
			}
		});

		lblNewLabel_72 = new Label(group, SWT.NONE);
		lblNewLabel_72.setText("   입력형 옵션");

		composite_41 = new Composite(group, SWT.NONE);
		composite_41.setLayout(new GridLayout(4, false));
		composite_41.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_inputopt1 = new Button(composite_41, SWT.RADIO);
		btn_inputopt1.setText("사용안함");

		btn_inputopt2 = new Button(composite_41, SWT.RADIO);
		btn_inputopt2.setText("사용함");

		lblNewLabel_73 = new Label(composite_41, SWT.NONE);
		lblNewLabel_73.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_73.setText("	입력형 : ");

		txt_inputopt1 = new Text(composite_41, SWT.BORDER);
		GridData gd_txt_inputopt1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_inputopt1.widthHint = 500;
		txt_inputopt1.setLayoutData(gd_txt_inputopt1);

		lblNewLabel_70 = new Label(group, SWT.NONE);
		lblNewLabel_70.setText("   옵션 정렬방법");

		composite_40 = new Composite(group, SWT.NONE);
		composite_40.setLayout(new GridLayout(4, false));
		composite_40.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_optdesc1 = new Button(composite_40, SWT.RADIO);
		btn_optdesc1.setText("미설정(SCM의 정렬값 유지)");

		btn_optdesc2 = new Button(composite_40, SWT.RADIO);
		btn_optdesc2.setText("등록순");

		btn_optdesc3 = new Button(composite_40, SWT.RADIO);
		btn_optdesc3.setText("가다나순");

		btn_optdesc4 = new Button(composite_40, SWT.RADIO);
		btn_optdesc4.setText("추가금액오름차순");

		composite_39 = new Composite(group, SWT.NONE);
		composite_39.setLayout(null);
		GridData gd_composite_39 = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_composite_39.heightHint = 80;
		composite_39.setLayoutData(gd_composite_39);

		lblNewLabel_68 = new Label(composite_39, SWT.NONE);
		lblNewLabel_68.setBounds(5, 19, 578, 20);
		lblNewLabel_68.setText("* 1단 옵션을 선택할 경우, \"색상:사이즈\" 와 같이 옵션이 1단으로 조합되어 등록됩니다.");
		lblNewLabel_68.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_69 = new Label(composite_39, SWT.NONE);
		lblNewLabel_69.setBounds(5, 44, 595, 20);
		lblNewLabel_69.setText("* 2단 옵션을 선택할 경우, \"색상\", \"사이즈\" 와 같이 옵션이 2단으로 분리되어 등록됩니다.");
		lblNewLabel_69.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_67 = new Label(group, SWT.NONE);
		lblNewLabel_67.setText("   2단 옵션 적용여부");

		composite_38 = new Composite(group, SWT.NONE);
		composite_38.setLayout(new GridLayout(1, false));
		composite_38.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_2opt = new Combo(composite_38, SWT.NONE);
		cb_2opt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_2opt.setItems(new String[] { "1단 옵션(기존 방식)", "2단 옵션" });
		cb_2opt.select(0);

		lblNewLabel_20 = new Label(group, SWT.NONE);
		lblNewLabel_20.setText("   주문1회당 판매수량제한");

		composite_10 = new Composite(group, SWT.NONE);
		composite_10.setLayout(new GridLayout(4, false));
		composite_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_quantityN = new Button(composite_10, SWT.RADIO);
		btn_quantityN.setText("제한없음");
		btn_quantityN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				setQuantity();
			}
		});

		btn_quantityY = new Button(composite_10, SWT.RADIO);
		btn_quantityY.setText("제한있음");
		btn_quantityY.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				setQuantity();
			}
		});

		txt_quantity = new Text(composite_10, SWT.BORDER);
		GridData gd_txt_quantity = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_quantity.widthHint = 80;
		txt_quantity.setLayoutData(gd_txt_quantity);

		Label lblNewLabel_22 = new Label(composite_10, SWT.NONE);
		lblNewLabel_22.setText("개");

		lblNewLabel_19 = new Label(group, SWT.NONE);
		lblNewLabel_19.setText("   배송방법");

		composite_9 = new Composite(group, SWT.NONE);
		composite_9.setLayout(new GridLayout(4, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_express = new Button(composite_9, SWT.RADIO);
		btn_express.setText("택배");

		btn_post = new Button(composite_9, SWT.RADIO);
		btn_post.setText("우편(소포/등기)");

		btn_freight = new Button(composite_9, SWT.RADIO);
		btn_freight.setText("화물배달(가국직배송)");

		btn_notexpress = new Button(composite_9, SWT.RADIO);
		btn_notexpress.setText("배송필요없음");

		lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("※ 배송비 설정");

		composite_1 = new Composite(group, SWT.BORDER);
		composite_1.setLayout(new GridLayout(8, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_delvchargedefault = new Button(composite_1, SWT.RADIO);
		btn_delvchargedefault.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btn_delvchargedefault.setText("판매자 기본 배송비 적용");
		btn_delvchargedefault.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setExpressDelvCost();
			}
		});

		btn_delvchargeexception = new Button(composite_1, SWT.RADIO);
		btn_delvchargeexception.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btn_delvchargeexception.setText("이 상품만 별도 배송비 적용");
		btn_delvchargeexception.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setExpressDelvCost();
			}
		});
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		lblNewLabel_11l = new Label(composite_1, SWT.NONE);
		lblNewLabel_11l.setText("묶음배송설정");

		lblNewLabel_171 = new Label(composite_1, SWT.NONE);
		lblNewLabel_171.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_171.setText("기본 배송 적용됨");

		txt_defaultship = new Text(composite_1, SWT.BORDER);
		txt_defaultship.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setText("검색");

		btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnNewButton_1.setText("삭제");
		new Label(composite_1, SWT.NONE);

		composite_exp = new Composite(composite_1, SWT.BORDER);
		composite_exp.setLayout(new GridLayout(9, false));
		composite_exp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 8, 1));

		lblNewLabel_91 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_91.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_91.setText("배송비 종류");

		lblNewLabel_101 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_101.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_101.setText("배송비");

		lblNewLabel_111 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_111.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 4, 1));
		lblNewLabel_111.setText("조건");
		new Label(composite_exp, SWT.NONE);

		lblNewLabel_121 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_121.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_121.setText("결제방법");

		label_61 = new Label(composite_exp, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_61.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 9, 1));

		btn_delvtype1 = new Button(composite_exp, SWT.RADIO);
		btn_delvtype1.setText("무료");
		btn_delvtype1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDelvType();
			}
		});
		lblNewLabel_131 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_131.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_131.setAlignment(SWT.CENTER);
		lblNewLabel_131.setText("0원");
		new Label(composite_exp, SWT.NONE);

		composite_15 = new Composite(composite_exp, SWT.NONE);
		composite_15.setLayout(new GridLayout(2, false));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));

		lblNewLabel_21 = new Label(composite_15, SWT.NONE);
		lblNewLabel_21.setText("무료배송 (");

		btn_freechk = new Button(composite_15, SWT.CHECK);
		btn_freechk.setText(" 일부지역 유료)");
		new Label(composite_exp, SWT.NONE);

		lblNewLabel_231 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_231.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_231.setText("-");

		label_2 = new Label(composite_exp, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 9, 1));

		btn_delvtype2 = new Button(composite_exp, SWT.RADIO);
		btn_delvtype2.setText("정액");
		btn_delvtype2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDelvType();
			}
		});
		txt_delvcost1 = new Text(composite_exp, SWT.BORDER);
		txt_delvcost1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_48 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_48.setText("원");

		lblNewLabel_241 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_241.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblNewLabel_241.setText("주문 수량/주문금액 관계없이 고정 배송비");

		label_11 = new Label(composite_exp, SWT.SEPARATOR | SWT.VERTICAL);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 7));

		cb_prepayment = new Combo(composite_exp, SWT.NONE);
		GridData gd_cb_prepayment = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 8);
		gd_cb_prepayment.widthHint = 100;
		cb_prepayment.setLayoutData(gd_cb_prepayment);
		cb_prepayment.setItems(new String[] { "착불/선불 선택가능", "선불", "착불" });
		cb_prepayment.select(0);

		label_7 = new Label(composite_exp, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));

		btn_delvtype3 = new Button(composite_exp, SWT.RADIO);
		btn_delvtype3.setText("수량 조건부 무료");
		btn_delvtype3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDelvType();
			}
		});
		txt_delvcost2 = new Text(composite_exp, SWT.BORDER);
		txt_delvcost2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_47 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_47.setText("원");

		composite_14 = new Composite(composite_exp, SWT.NONE);
		composite_14.setLayout(new GridLayout(3, false));
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));

		lblNewLabel_25 = new Label(composite_14, SWT.NONE);
		lblNewLabel_25.setText("해당 상품을 ");

		txt_delvcost2_1 = new Text(composite_14, SWT.BORDER);
		GridData gd_txt_delvcost2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_delvcost2_1.widthHint = 100;
		txt_delvcost2_1.setLayoutData(gd_txt_delvcost2_1);

		lblNewLabel_26 = new Label(composite_14, SWT.NONE);
		lblNewLabel_26.setText("개 이상 구매시 무료");

		label_9 = new Label(composite_exp, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));

		btn_delvtype4 = new Button(composite_exp, SWT.RADIO);
		btn_delvtype4.setText("1개당 배송비");
		btn_delvtype4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDelvType();
			}
		});
		txt_delvcost3 = new Text(composite_exp, SWT.BORDER);
		GridData gd_txt_delvcost3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_delvcost3.widthHint = 100;
		txt_delvcost3.setLayoutData(gd_txt_delvcost3);

		lblNewLabel_46 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_46.setText("원");

		lblNewLabel_27 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_27.setText("배송비 = 주문수량 x1개당 배송비 (");

		btn_onechk = new Button(composite_exp, SWT.CHECK);
		btn_onechk.setText("단, 해당상품을 ");

		txt_delvcost2_2 = new Text(composite_exp, SWT.BORDER);
		GridData gd_txt_delvcost2_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_delvcost2_2.widthHint = 80;
		txt_delvcost2_2.setLayoutData(gd_txt_delvcost2_2);

		lblNewLabel_28 = new Label(composite_exp, SWT.NONE);
		lblNewLabel_28.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_28.setText("개 이상 구매시 무료)");

		label_10 = new Label(composite_exp, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));

		btn_delvtype5 = new Button(composite_exp, SWT.RADIO);
		btn_delvtype5.setText("n개당 배송비");
		btn_delvtype5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDelvType();
			}
		});

		composite_13 = new Composite(composite_exp, SWT.NONE);
		composite_13.setLayout(new GridLayout(6, false));
		composite_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));

		txt_qty1_1 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
		gd_txt_qty1_1.widthHint = 40;
		txt_qty1_1.setLayoutData(gd_txt_qty1_1);

		lblNewLabel_29 = new Label(composite_13, SWT.NONE);
		lblNewLabel_29.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_29.setText("개 마다 배송비 ");

		txt_qty2_1 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty2_1.widthHint = 90;
		txt_qty2_1.setLayoutData(gd_txt_qty2_1);

		lblNewLabel_42 = new Label(composite_13, SWT.NONE);
		lblNewLabel_42.setText("원 반복 부과");

		txt_qty1_2 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_2.widthHint = 40;
		txt_qty1_2.setLayoutData(gd_txt_qty1_2);

		lblNewLabel_33 = new Label(composite_13, SWT.NONE);
		lblNewLabel_33.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_33.setText("개 ");

		txt_qty1_3 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_3.widthHint = 40;
		txt_qty1_3.setLayoutData(gd_txt_qty1_3);

		lblNewLabel_341 = new Label(composite_13, SWT.NONE);
		lblNewLabel_341.setText("개 배송비 ");

		txt_qty2_2 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty2_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty2_2.widthHint = 90;
		txt_qty2_2.setLayoutData(gd_txt_qty2_2);

		lblNewLabel_381 = new Label(composite_13, SWT.NONE);
		lblNewLabel_381.setText("원");

		txt_qty1_4 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_4.widthHint = 40;
		txt_qty1_4.setLayoutData(gd_txt_qty1_4);

		lblNewLabel_391 = new Label(composite_13, SWT.NONE);
		lblNewLabel_391.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_391.setText("개 ");

		txt_qty1_5 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_5.widthHint = 40;
		txt_qty1_5.setLayoutData(gd_txt_qty1_5);

		lblNewLabel_401 = new Label(composite_13, SWT.NONE);
		lblNewLabel_401.setText("개 배송비 ");

		txt_qty2_3 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty2_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty2_3.widthHint = 90;
		txt_qty2_3.setLayoutData(gd_txt_qty2_3);

		lblNewLabel_411 = new Label(composite_13, SWT.NONE);
		lblNewLabel_411.setText("원");

		txt_qty1_6 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_6.widthHint = 40;
		txt_qty1_6.setLayoutData(gd_txt_qty1_6);

		lblNewLabel_431 = new Label(composite_13, SWT.NONE);
		lblNewLabel_431.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_431.setText("개 ");

		txt_qty1_7 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty1_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty1_7.widthHint = 40;
		txt_qty1_7.setLayoutData(gd_txt_qty1_7);

		lblNewLabel_441 = new Label(composite_13, SWT.NONE);
		lblNewLabel_441.setText("개 배송비 ");

		txt_qty2_4 = new Text(composite_13, SWT.BORDER);
		GridData gd_txt_qty2_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qty2_4.widthHint = 90;
		txt_qty2_4.setLayoutData(gd_txt_qty2_4);

		lblNewLabel_451 = new Label(composite_13, SWT.NONE);
		lblNewLabel_451.setText("원");
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);
		new Label(composite_exp, SWT.NONE);

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setText("배송비 특이사항 : ");

		txt_shipuniquene = new Text(composite_1, SWT.BORDER);
		txt_shipuniquene.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 5, 1));
		txt_shipuniquene.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String prodnm = txt_shipuniquene.getText();
				int byteview = prodnm.getBytes().length;
				txt_byte.setText(String.valueOf(byteview));

			}
		});
		txt_byte = new Text(composite_1, SWT.BORDER);

		lblNewLabel_18 = new Label(composite_1, SWT.NONE);
		lblNewLabel_18.setText("Byte");

		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblNewLabel_3.setText("＊ 배송비 관련 특이사항이 있는 경우 기술하며, 상품최종 배송비 아래 노출됩니다.");
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label lblNewLabel_4 = new Label(group, SWT.NONE);
		lblNewLabel_4.setText("   제주 및 도서산간 추가배송비 설정");

		Composite composite_8 = new Composite(group, SWT.NONE);
		composite_8.setLayout(new GridLayout(5, false));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblNewLabel_14 = new Label(composite_8, SWT.NONE);
		lblNewLabel_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_14.setText("제주");

		txt_jejucharge = new Text(composite_8, SWT.BORDER);
		GridData gd_txt_jejucharge = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_jejucharge.widthHint = 80;
		txt_jejucharge.setLayoutData(gd_txt_jejucharge);

		Label lblNewLabel_15 = new Label(composite_8, SWT.NONE);
		lblNewLabel_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_15.setText(" 도서산간 : ");

		txt_islandcharge = new Text(composite_8, SWT.BORDER);
		GridData gd_txt_islandcharge = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_islandcharge.widthHint = 80;
		txt_islandcharge.setLayoutData(gd_txt_islandcharge);

		Label lblNewLabel_16 = new Label(composite_8, SWT.NONE);
		lblNewLabel_16.setText("숫자만 입력");

		label_30 = new Label(group, SWT.NONE);
		label_30.setText("   반품배송지 주소");

		composite_29 = new Composite(group, SWT.NONE);
		composite_29.setLayout(new GridLayout(3, false));
		composite_29.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		txt_returnaddr = new Text(composite_29, SWT.BORDER);
		GridData gd_txt_returnaddr = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_returnaddr.widthHint = 700;
		txt_returnaddr.setLayoutData(gd_txt_returnaddr);
		txt_returnaddrsearch = new Button(composite_29, SWT.NONE);
		txt_returnaddrsearch.setText("검색");
		txt_returnaddrsearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});

		Button btn_returnaddrdel = new Button(composite_29, SWT.NONE);
		btn_returnaddrdel.setText("삭제");

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label_3.setText(
				"* 반품/교환 배송비는 구매자가 반품/교환 접수시 무료반품교환쿠폰 사용기준이 되며, 정보를 변경하시면 판매자 기본 반품/교환 배송비로 설정되어 있는 상품에 동일하게 적용됩니다.");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label_4.setText("* 상품별 배송비는 상품등록/수정 화면에서 관리하실수 있습니다.");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label_5.setText("* 반품/교환(편도기준) 배송비가 2,500원 이하의 경우에만 반품/교환 접수시 무료반품교환쿠폰 사용이가능합니다.");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		label_1 = new Label(group, SWT.NONE);
		label_1.setText("※ 반품/교환 배송비");

		Composite composite = new Composite(group, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		btn_defaultdelvcharge = new Button(composite, SWT.RADIO);
		btn_defaultdelvcharge.setText("판매자 기본 반품/교환 배송비 적용");
		btn_defaultdelvcharge.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				setRetNCngCost();
			}
		});

		btn_exceptiondelvcharge = new Button(composite, SWT.RADIO);
		btn_exceptiondelvcharge.setText("이 상품만 별도 배송비 적용");
		btn_exceptiondelvcharge.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				setRetNCngCost();
			}
		});

		composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(3, false));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		lblNewLabel_711 = new Label(composite_2, SWT.NONE);
		lblNewLabel_711.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_711.setText("반품/교환 배송비(편도기준) : ");

		txt_retNcngcost = new Text(composite_2, SWT.BORDER);
		GridData gd_txt_retNcngcost = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_retNcngcost.widthHint = 70;
		txt_retNcngcost.setLayoutData(gd_txt_retNcngcost);

		lblNewLabel_811 = new Label(composite_2, SWT.NONE);
		lblNewLabel_811.setText("원");

		lblNewLabel_66 = new Label(group, SWT.NONE);
		lblNewLabel_66.setText("   가격비교등록");

		composite_37 = new Composite(group, SWT.NONE);
		composite_37.setLayout(new GridLayout(2, false));
		composite_37.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_pricecompar1 = new Button(composite_37, SWT.RADIO);
		btn_pricecompar1.setText("등록안함");

		btn_pricecompar2 = new Button(composite_37, SWT.RADIO);
		btn_pricecompar2.setText("등록함");

		lblNewLabel_63 = new Label(group, SWT.NONE);
		lblNewLabel_63.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_63.setText("* 단말가격 결제방법, 핸드폰 가입비, 유심비, 가입신청, 판매권 사용 항목은 핸드폰 카테고리 선택시 적용되는 항목입니다.");
		lblNewLabel_63.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_64 = new Label(group, SWT.NONE);
		lblNewLabel_64.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_64.setText("* 판매권 입력시 판매하고자 하는 상품의 수량만큼 판매권을 사용가능하며 판매권을 보유하고 있어야 사용가능합니다.");
		lblNewLabel_64.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_65 = new Label(group, SWT.NONE);
		lblNewLabel_65.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_65.setText("* 판매권 미입력시 0개로 반영됩니다.");
		lblNewLabel_65.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_62 = new Label(group, SWT.NONE);
		lblNewLabel_62.setText("※ 단말가격 결제방법");

		composite_36 = new Composite(group, SWT.NONE);
		composite_36.setLayout(new GridLayout(2, false));
		composite_36.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_terminalprice1 = new Button(composite_36, SWT.RADIO);
		btn_terminalprice1.setText("할부");

		btn_terminalprice2 = new Button(composite_36, SWT.RADIO);
		btn_terminalprice2.setText("완납");

		lblNewLabel_61 = new Label(group, SWT.NONE);
		lblNewLabel_61.setText("※ 핸드폰 가입비");

		composite_35 = new Composite(group, SWT.NONE);
		composite_35.setLayout(new GridLayout(2, false));
		composite_35.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_activa1 = new Button(composite_35, SWT.RADIO);
		btn_activa1.setText("면제");

		btn_activa2 = new Button(composite_35, SWT.RADIO);
		btn_activa2.setText("분납");

		lblNewLabel_60 = new Label(group, SWT.NONE);
		lblNewLabel_60.setText("※ 핸드폰 유심비");

		composite_34 = new Composite(group, SWT.NONE);
		composite_34.setLayout(new GridLayout(2, false));
		composite_34.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_usim1 = new Button(composite_34, SWT.RADIO);
		btn_usim1.setText("면제");

		btn_usim2 = new Button(composite_34, SWT.RADIO);
		btn_usim2.setText("후납");

		lblNewLabel_56 = new Label(group, SWT.NONE);
		lblNewLabel_56.setText("※ 핸드폰 가입신청");

		composite_33 = new Composite(group, SWT.NONE);
		composite_33.setLayout(new GridLayout(3, false));
		composite_33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_58 = new Label(composite_33, SWT.NONE);
		lblNewLabel_58.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_58.setText("* 신청서 확인하기 페이지 URL 주소 ");

		txt_phoneurl1 = new Text(composite_33, SWT.BORDER);
		GridData gd_txt_phoneurl1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phoneurl1.widthHint = 500;
		txt_phoneurl1.setLayoutData(gd_txt_phoneurl1);

		cb_phoneurl1 = new Combo(composite_33, SWT.NONE);
		cb_phoneurl1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_phoneurl1.setItems(new String[] { "부가정보 내용 적용", "추가 상품상세설명1 적용", "추가 상품상세설명2 적용" });
		cb_phoneurl1.select(0);

		lblNewLabel_59 = new Label(composite_33, SWT.NONE);
		lblNewLabel_59.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_59.setText("* 신청서 확인하기 페이지 URL 주소 ");

		txt_phoneurl2 = new Text(composite_33, SWT.BORDER);
		GridData gd_txt_phoneurl2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_phoneurl2.widthHint = 500;
		txt_phoneurl2.setLayoutData(gd_txt_phoneurl2);

		cb_phoneurl2 = new Combo(composite_33, SWT.NONE);
		cb_phoneurl2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_phoneurl2.setItems(new String[] { "부가정보 내용 적용", "추가 상품상세설명1 적용", "추가 상품상세설명2 적용" });
		cb_phoneurl2.select(0);

		lblNewLabel_53 = new Label(group, SWT.NONE);
		lblNewLabel_53.setText("※ 판매권 사용");

		composite_32 = new Composite(group, SWT.NONE);
		composite_32.setLayout(new GridLayout(3, false));
		composite_32.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_usesale = new Text(composite_32, SWT.BORDER);
		GridData gd_txt_usesale = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_usesale.widthHint = 70;
		txt_usesale.setLayoutData(gd_txt_usesale);

		lblNewLabel_54 = new Label(composite_32, SWT.NONE);
		lblNewLabel_54.setText(" 개 ");

		lblNewLabel_55 = new Label(composite_32, SWT.NONE);
		lblNewLabel_55.setText("* 판매권은 판매가가 1원일 경우 사용 가능합니다.");
		lblNewLabel_55.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_49 = new Label(group, SWT.NONE);
		lblNewLabel_49.setText("   완구 카테고리 추가사항");

		composite_27 = new Composite(group, SWT.NONE);
		composite_27.setLayout(new GridLayout(4, false));
		composite_27.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblNewLabel_50 = new Label(composite_27, SWT.NONE);
		lblNewLabel_50.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblNewLabel_50.setText("* 카테고리가 완구일 경우 세팅하여 주세요 ");

		lblNewLabel_51 = new Label(composite_27, SWT.NONE);
		lblNewLabel_51.setText(" 사용자 성별");

		composite_28 = new Composite(composite_27, SWT.NONE);
		composite_28.setLayout(new GridLayout(3, false));
		composite_28.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		btn_toysex1 = new Button(composite_28, SWT.RADIO);
		btn_toysex1.setText("전체사용가능");

		btn_toysex2 = new Button(composite_28, SWT.RADIO);
		btn_toysex2.setText("남아용");

		btn_toysex3 = new Button(composite_28, SWT.RADIO);
		btn_toysex3.setText("여아용");

		lblNewLabel_52 = new Label(composite_27, SWT.NONE);
		lblNewLabel_52.setText(" 사용자 연령");

		composite_30 = new Composite(composite_27, SWT.NONE);
		composite_30.setLayout(new GridLayout(6, false));
		composite_30.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		btn_toyage1 = new Button(composite_30, SWT.RADIO);
		btn_toyage1.setText("전체사용가능");
		new Label(composite_30, SWT.NONE);
		new Label(composite_30, SWT.NONE);
		new Label(composite_30, SWT.NONE);
		new Label(composite_30, SWT.NONE);
		new Label(composite_30, SWT.NONE);

		btn_toyage2 = new Button(composite_30, SWT.RADIO);
		btn_toyage2.setText("일부 연령만 사용");

		txt_toyage2_1 = new Text(composite_30, SWT.BORDER);
		GridData gd_txt_toyage2_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_toyage2_1.widthHint = 70;
		txt_toyage2_1.setLayoutData(gd_txt_toyage2_1);

		lb_toyage2_1 = new Label(composite_30, SWT.NONE);
		lb_toyage2_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lb_toyage2_1.setText(" ~ ");

		txt_toyage2_2 = new Text(composite_30, SWT.BORDER);
		GridData gd_txt_toyage2_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_txt_toyage2_2.widthHint = 70;
		txt_toyage2_2.setLayoutData(gd_txt_toyage2_2);

		cb_toyage2_1 = new Combo(composite_30, SWT.NONE);
		cb_toyage2_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_toyage2_1.setItems(new String[] { "개월", "세" });
		cb_toyage2_1.select(0);

		btn_toyage3 = new Button(composite_30, SWT.RADIO);
		btn_toyage3.setText("특정 연령만 사용");

		txt_toyage3_1 = new Text(composite_30, SWT.BORDER);
		GridData gd_txt_toyage3_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txt_toyage3_1.widthHint = 70;
		txt_toyage3_1.setLayoutData(gd_txt_toyage3_1);

		cb_toyage3_1 = new Combo(composite_30, SWT.NONE);
		GridData gd_cb_toyage3_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_cb_toyage3_1.widthHint = 40;
		cb_toyage3_1.setLayoutData(gd_cb_toyage3_1);
		cb_toyage3_1.setItems(new String[] { "개월", "세" });
		cb_toyage3_1.select(0);

		cb_toyage3_2 = new Combo(composite_30, SWT.NONE);
		GridData gd_cb_toyage3_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_cb_toyage3_2.widthHint = 40;
		cb_toyage3_2.setLayoutData(gd_cb_toyage3_2);
		cb_toyage3_2.setItems(new String[] { "이상", "이하" });
		cb_toyage3_2.select(0);

		lblNewLabel_45 = new Label(group, SWT.NONE);
		lblNewLabel_45.setText("   상품설명 상단 추가문구");

		composite_26 = new Composite(group, SWT.NONE);
		composite_26.setLayout(new GridLayout(1, false));
		composite_26.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_top = new Text(composite_26, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_top = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_top.widthHint = 700;
		gd_txt_top.heightHint = 70;
		txt_top.setLayoutData(gd_txt_top);

		lblNewLabel_44 = new Label(group, SWT.NONE);
		lblNewLabel_44.setText("   상품설명 하단 추가문구");

		composite_25 = new Composite(group, SWT.NONE);
		composite_25.setLayout(new GridLayout(1, false));
		composite_25.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		txt_bottom = new Text(composite_25, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_txt_bottom = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_bottom.heightHint = 70;
		gd_txt_bottom.widthHint = 700;
		txt_bottom.setLayoutData(gd_txt_bottom);

		group_1 = new Group(container, SWT.NONE);
		group_1.setLayout(new GridLayout(2, false));
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		group_1.setText("■ [유료 및 부가서비스]");

		lblNewLabel_22 = new Label(group_1, SWT.NONE);
		lblNewLabel_22.setText("   판매자부담 즉시할인");

		composite_17 = new Composite(group_1, SWT.NONE);
		composite_17.setLayout(new GridLayout(6, false));
		composite_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		composite_18 = new Composite(composite_17, SWT.NONE);
		composite_18.setLayout(new GridLayout(5, false));
		composite_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));

		btn_sellerdis1 = new Button(composite_18, SWT.CHECK);
		GridData gd_btn_sellerdis1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btn_sellerdis1.widthHint = 150;
		btn_sellerdis1.setLayoutData(gd_btn_sellerdis1);
		btn_sellerdis1.setText("설정함");
		btn_sellerdis1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSellerDiscount();
			}
		});
		
		btn_sellerdis1_1 = new Button(composite_18, SWT.RADIO);
		btn_sellerdis1_1.setText("정률");
		btn_sellerdis1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSellerDiscountRate();
			}
		});
		btn_sellerdis1_2 = new Button(composite_18, SWT.RADIO);
		btn_sellerdis1_2.setText("정액");
		btn_sellerdis1_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSellerDiscountRate();
			}
		});
		txt_sellerdis1_1 = new Text(composite_18, SWT.BORDER);
		GridData gd_txt_sellerdis1_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_sellerdis1_1.widthHint = 100;
		txt_sellerdis1_1.setLayoutData(gd_txt_sellerdis1_1);

		lb_sellerdis1_1 = new Label(composite_18, SWT.NONE);
		lb_sellerdis1_1.setText(" % 할인");

		composite_20 = new Composite(composite_17, SWT.NONE);
		composite_20.setLayout(new GridLayout(6, false));
		composite_20.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));

		btn_sellerdis2 = new Button(composite_20, SWT.CHECK);
		GridData gd_btn_sellerdis2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btn_sellerdis2.widthHint = 150;
		btn_sellerdis2.setLayoutData(gd_btn_sellerdis2);
		btn_sellerdis2.setText("할인기간설정");
		btn_sellerdis2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSellerDiscountPreiod();
			}
		});
		txt_sellerdis2_1 = new Text(composite_20, SWT.BORDER);
		GridData gd_txt_sellerdis2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_sellerdis2_1.widthHint = 100;
		txt_sellerdis2_1.setLayoutData(gd_txt_sellerdis2_1);
		txt_sellerdis2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_sellerdis2_1);
				calander.open();
			}
		});
		
		lb_sellerdis2_1 = new Label(composite_20, SWT.NONE);
		lb_sellerdis2_1.setText(" ~ ");

		txt_sellerdis2_2 = new Text(composite_20, SWT.BORDER);
		GridData gd_txt_sellerdis2_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_sellerdis2_2.widthHint = 100;
		txt_sellerdis2_2.setLayoutData(gd_txt_sellerdis2_2);
		txt_sellerdis2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				CommonCalander calander = new CommonCalander(txt_sellerdis2_2);
				calander.open();
			}
		});
		
		btn_sellerdis2_1del = new Button(composite_20, SWT.NONE);
		btn_sellerdis2_1del.setText("삭제");
		btn_sellerdis2_1del.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_sellerdis2_1.setText("");
				txt_sellerdis2_2.setText("");
			}
		});
		lb_sellerdis2_2 = new Label(composite_20, SWT.NONE);
		lb_sellerdis2_2.setText(" * 할인기간 시작일은 상품 등록일보다 과거일 수 없습니다.");
		lb_sellerdis2_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_16 = new Label(group_1, SWT.NONE);
		lblNewLabel_16.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_16.setText("* 판매자가 구매자에게 부여하는 것으로 구매확정시 부여한 포인트는 정산시 공제됩니다.");
		lblNewLabel_16.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_15 = new Label(group_1, SWT.NONE);
		lblNewLabel_15.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_15.setText("* '고정 포인트로 부여'는 인터파크 어드민에서 없어진 항목입니다.'판매가의 일정 %로 부여'로 선택하세요.");
		lblNewLabel_15.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_6 = new Label(group_1, SWT.NONE);
		lblNewLabel_6.setText("   i-포인트 부여");

		composite_16 = new Composite(group_1, SWT.NONE);
		composite_16.setLayout(new GridLayout(3, false));
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_ipoint = new Combo(composite_16, SWT.NONE);
		cb_ipoint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cb_ipoint.setItems(new String[] { "판매가의 일정 %로 부여", "고정 포인트로 부여" });
		cb_ipoint.select(0);
		cb_ipoint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setIpoint();
			}
		});

		txt_ipoint = new Text(composite_16, SWT.BORDER);
		GridData gd_txt_ipoint = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txt_ipoint.widthHint = 70;
		txt_ipoint.setLayoutData(gd_txt_ipoint);

		newLabel = new Label(composite_16, SWT.NONE);
		newLabel.setText(" %");

		lblNewLabel_4 = new Label(group_1, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_4.setText("* 선택한 할부개월수 이하 개월수도 무이자 할부가 적용됩니다.");
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_3 = new Label(group_1, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_3.setText("* 구매금액 기준 무이자할부 수수료율 : 2~3개월(2.5%),4~6개월(4.5%),7~10개월(7.5%),11~12개월(10%)");
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

		lblNewLabel_2 = new Label(group_1, SWT.NONE);
		lblNewLabel_2.setText("   무이자할부");

		composite_8 = new Composite(group_1, SWT.NONE);
		composite_8.setLayout(new GridLayout(2, false));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btn_interestfeeN = new Button(composite_8, SWT.RADIO);
		btn_interestfeeN.setText("적용안함");

		btn_interestfeeY = new Button(composite_8, SWT.RADIO);
		btn_interestfeeY.setText("적용함");
		label_35 = new Label(group_1, SWT.NONE);
		label_35.setText("※ 사용여부");

		composite = new Composite(group_1, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cb_whether = new Combo(composite, SWT.NONE);
		cb_whether.setItems(new String[] { "사용여부", "사용중", "미사용" });
		cb_whether.select(0);

		// container.setSize( 1300, 2000 );
		scrolledComposite.setContent(container);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setAlwaysShowScrollBars(true);
		// scrolledComposite.setMinSize( 1300, 800 );

		scrolledComposite.addListener(SWT.Resize, event -> {
			int width = scrolledComposite.getClientArea().width;
			scrolledComposite.setMinSize(parent.computeSize(width, SWT.DEFAULT));
		});
		//
		setIdcheck();
		init();

		setSalesCalendar();
		setUniQueness();
		setHealthFood();
		setAdreview();
		setQuantity();
		setRetNCngCost();
		Salesperiod();
		setExpressDelvCost();
		setDelvType();
		setIpoint();
		setSellerDiscount();
		setSellerDiscountPreiod();
		setSellerDiscountRate();
		return scrolledComposite;
	}
	//판매자부담할인 요율글자 변경
	protected void setSellerDiscountRate() {
		if(btn_sellerdis1_1.getSelection()) {
			lb_sellerdis1_1.setText("% 할인");
		}else {
			lb_sellerdis1_1.setText("원 할인");
		}
		
	}

	//판매자부담할인 기간설정 보이게 하기
	protected void setSellerDiscountPreiod() {
		if(btn_sellerdis2.getSelection()) {
			btn_sellerdis1_1.setVisible(true);
			btn_sellerdis1_2.setVisible(true);
			txt_sellerdis1_1.setVisible(true);
			lb_sellerdis1_1.setVisible(true);
			txt_sellerdis2_1.setVisible(true);
			lb_sellerdis2_1.setVisible(true);
			txt_sellerdis2_2.setVisible(true);
			btn_sellerdis2_1del.setVisible(true);
			lb_sellerdis2_2.setVisible(true);
		}else {
			txt_sellerdis2_1.setVisible(false);
			lb_sellerdis2_1.setVisible(false);
			txt_sellerdis2_2.setVisible(false);
			btn_sellerdis2_1del.setVisible(false);
			lb_sellerdis2_2.setVisible(false);
		}
		
	}

	//판매자부담즉시할인보이게하기
	protected void setSellerDiscount() {
		if(btn_sellerdis1.getSelection()) {
			btn_sellerdis1_1.setVisible(true);
			btn_sellerdis1_2.setVisible(true);
			txt_sellerdis1_1.setVisible(true);
			lb_sellerdis1_1.setVisible(true);
		}else {
			btn_sellerdis1_1.setVisible(false);
			btn_sellerdis1_2.setVisible(false);
			txt_sellerdis1_1.setVisible(false);
			lb_sellerdis1_1.setVisible(false);
		}
		
	}
	//아이포인트 글자변경하기
	protected void setIpoint() {
		if(cb_ipoint.getSelectionIndex()==0) {
			newLabel.setText(" %");
		}else {
			newLabel.setText(" P");
		}
		
		
	}

	// 아이디체크
	private void setIdcheck() {
		List<CodeItem> datas = datasource.stream().map(p -> new CodeItem(p.getDlvID(), p.getDivNM()))
				.collect(Collectors.toList());

		// scb_express.setDataSource(datas);

		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			List<List<String>> shoplist = new ArrayList<List<String>>();
			if(dto==null) {
				shoplist = dao.getShopidListCheck(list.get(0));
			}else {
				shoplist = dao.getShopidListCheck(dto.getShopcd());
			}

			String id = "쇼핑몰ID,";
			for (List<String> idche : shoplist) {
				id += idche.get(1) + ",";
			}
			cb_idcheck.setItems(id.split(","));
			cb_idcheck.select(0);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	protected void setDelvType() {
		if (btn_delvtype1.getSelection() == true) {
			btn_freechk.setEnabled(true);
			txt_delvcost1.setEnabled(false);
			txt_delvcost2.setEnabled(false);
			txt_delvcost2_1.setEnabled(false);
			txt_delvcost3.setEnabled(false);
			btn_onechk.setEnabled(false);
			txt_delvcost2_2.setEnabled(false);
			txt_qty1_1.setEnabled(false);
			txt_qty1_2.setEnabled(false);
			txt_qty1_3.setEnabled(false);
			txt_qty1_4.setEnabled(false);
			txt_qty1_5.setEnabled(false);
			txt_qty1_6.setEnabled(false);
			txt_qty1_7.setEnabled(false);
			txt_qty2_1.setEnabled(false);
			txt_qty2_2.setEnabled(false);
			txt_qty2_3.setEnabled(false);
			txt_qty2_4.setEnabled(false);
		} else if (btn_delvtype2.getSelection() == true) {
			btn_freechk.setEnabled(false);
			txt_delvcost1.setEnabled(true);
			txt_delvcost2.setEnabled(false);
			txt_delvcost2_1.setEnabled(false);
			txt_delvcost3.setEnabled(false);
			btn_onechk.setEnabled(false);
			txt_delvcost2_2.setEnabled(false);
			txt_qty1_1.setEnabled(false);
			txt_qty1_2.setEnabled(false);
			txt_qty1_3.setEnabled(false);
			txt_qty1_4.setEnabled(false);
			txt_qty1_5.setEnabled(false);
			txt_qty1_6.setEnabled(false);
			txt_qty1_7.setEnabled(false);
			txt_qty2_1.setEnabled(false);
			txt_qty2_2.setEnabled(false);
			txt_qty2_3.setEnabled(false);
			txt_qty2_4.setEnabled(false);
		} else if (btn_delvtype3.getSelection() == true) {
			btn_freechk.setEnabled(false);
			txt_delvcost1.setEnabled(false);
			txt_delvcost2.setEnabled(true);
			txt_delvcost2_1.setEnabled(true);
			txt_delvcost3.setEnabled(false);
			btn_onechk.setEnabled(false);
			txt_delvcost2_2.setEnabled(false);
			txt_qty1_1.setEnabled(false);
			txt_qty1_2.setEnabled(false);
			txt_qty1_3.setEnabled(false);
			txt_qty1_4.setEnabled(false);
			txt_qty1_5.setEnabled(false);
			txt_qty1_6.setEnabled(false);
			txt_qty1_7.setEnabled(false);
			txt_qty2_1.setEnabled(false);
			txt_qty2_2.setEnabled(false);
			txt_qty2_3.setEnabled(false);
			txt_qty2_4.setEnabled(false);
		} else if (btn_delvtype4.getSelection() == true) {
			btn_freechk.setEnabled(false);
			txt_delvcost1.setEnabled(false);
			txt_delvcost2.setEnabled(false);
			txt_delvcost2_1.setEnabled(false);
			txt_delvcost3.setEnabled(true);
			btn_onechk.setEnabled(true);
			txt_delvcost2_2.setEnabled(true);
			txt_qty1_1.setEnabled(false);
			txt_qty1_2.setEnabled(false);
			txt_qty1_3.setEnabled(false);
			txt_qty1_4.setEnabled(false);
			txt_qty1_5.setEnabled(false);
			txt_qty1_6.setEnabled(false);
			txt_qty1_7.setEnabled(false);
			txt_qty2_1.setEnabled(false);
			txt_qty2_2.setEnabled(false);
			txt_qty2_3.setEnabled(false);
			txt_qty2_4.setEnabled(false);
		} else if (btn_delvtype5.getSelection() == true) {
			btn_freechk.setEnabled(false);
			txt_delvcost1.setEnabled(false);
			txt_delvcost2.setEnabled(false);
			txt_delvcost2_1.setEnabled(false);
			txt_delvcost3.setEnabled(false);
			btn_onechk.setEnabled(false);
			txt_delvcost2_2.setEnabled(false);
			txt_qty1_1.setEnabled(true);
			txt_qty1_2.setEnabled(true);
			txt_qty1_3.setEnabled(true);
			txt_qty1_4.setEnabled(true);
			txt_qty1_5.setEnabled(true);
			txt_qty1_6.setEnabled(true);
			txt_qty1_7.setEnabled(true);
			txt_qty2_1.setEnabled(true);
			txt_qty2_2.setEnabled(true);
			txt_qty2_3.setEnabled(true);
			txt_qty2_4.setEnabled(true);
		}

	}

	protected void setExpressDelvCost() {
		if (btn_delvchargedefault.getSelection() == true) {
			lblNewLabel_11l.setEnabled(true);
			lblNewLabel_171.setEnabled(true);
			txt_defaultship.setEnabled(true);
			btnNewButton.setEnabled(true);
			btnNewButton_1.setEnabled(true);

			lblNewLabel_91.setEnabled(false);
			lblNewLabel_101.setEnabled(false);
			lblNewLabel_111.setEnabled(false);
			lblNewLabel_121.setEnabled(false);
			label_61.setEnabled(false);
			btn_delvtype1.setEnabled(false);
			lblNewLabel_131.setEnabled(false);
			lblNewLabel_21.setEnabled(false);
			btn_freechk.setEnabled(false);
			lblNewLabel_231.setEnabled(false);
			label_2.setEnabled(false);
			btn_delvtype2.setEnabled(false);
			txt_delvcost1.setEnabled(false);
			lblNewLabel_48.setEnabled(false);
			lblNewLabel_241.setEnabled(false);
			label_11.setEnabled(false);
			cb_prepayment.setEnabled(false);
			label_7.setEnabled(false);
			btn_delvtype3.setEnabled(false);
			txt_delvcost2.setEnabled(false);
			lblNewLabel_47.setEnabled(false);
			lblNewLabel_25.setEnabled(false);
			txt_delvcost2_1.setEnabled(false);
			lblNewLabel_26.setEnabled(false);
			label_9.setEnabled(false);
			btn_delvtype4.setEnabled(false);
			txt_delvcost3.setEnabled(false);
			lblNewLabel_46.setEnabled(false);
			lblNewLabel_27.setEnabled(false);
			btn_onechk.setEnabled(false);
			txt_delvcost2_2.setEnabled(false);
			lblNewLabel_28.setEnabled(false);
			label_10.setEnabled(false);
			btn_delvtype5.setEnabled(false);
			txt_qty1_1.setEnabled(false);
			lblNewLabel_29.setEnabled(false);
			txt_qty2_1.setEnabled(false);
			lblNewLabel_42.setEnabled(false);
			txt_qty1_2.setEnabled(false);
			lblNewLabel_33.setEnabled(false);
			txt_qty1_3.setEnabled(false);
			lblNewLabel_341.setEnabled(false);
			txt_qty2_2.setEnabled(false);
			lblNewLabel_381.setEnabled(false);
			txt_qty1_4.setEnabled(false);
			lblNewLabel_391.setEnabled(false);
			txt_qty1_5.setEnabled(false);
			lblNewLabel_401.setEnabled(false);
			txt_qty2_3.setEnabled(false);
			lblNewLabel_411.setEnabled(false);
			txt_qty1_6.setEnabled(false);
			lblNewLabel_431.setEnabled(false);
			txt_qty1_7.setEnabled(false);
			lblNewLabel_441.setEnabled(false);
			txt_qty2_4.setEnabled(false);
			lblNewLabel_451.setEnabled(false);

		} else {
			lblNewLabel_11l.setEnabled(false);
			lblNewLabel_171.setEnabled(false);
			txt_defaultship.setEnabled(false);
			btnNewButton.setEnabled(false);
			btnNewButton_1.setEnabled(false);

			lblNewLabel_91.setEnabled(true);
			lblNewLabel_101.setEnabled(true);
			lblNewLabel_111.setEnabled(true);
			lblNewLabel_121.setEnabled(true);
			label_61.setEnabled(true);
			btn_delvtype1.setEnabled(true);
			lblNewLabel_131.setEnabled(true);
			lblNewLabel_21.setEnabled(true);
			btn_freechk.setEnabled(true);
			lblNewLabel_231.setEnabled(true);
			label_2.setEnabled(true);
			btn_delvtype2.setEnabled(true);
			txt_delvcost1.setEnabled(true);
			lblNewLabel_48.setEnabled(true);
			lblNewLabel_241.setEnabled(true);
			label_11.setEnabled(true);
			cb_prepayment.setEnabled(true);
			label_7.setEnabled(true);
			btn_delvtype3.setEnabled(true);
			txt_delvcost2.setEnabled(true);
			lblNewLabel_47.setEnabled(true);
			lblNewLabel_25.setEnabled(true);
			txt_delvcost2_1.setEnabled(true);
			lblNewLabel_26.setEnabled(true);
			label_9.setEnabled(true);
			btn_delvtype4.setEnabled(true);
			txt_delvcost3.setEnabled(true);
			lblNewLabel_46.setEnabled(true);
			lblNewLabel_27.setEnabled(true);
			btn_onechk.setEnabled(true);
			txt_delvcost2_2.setEnabled(true);
			lblNewLabel_28.setEnabled(true);
			label_10.setEnabled(true);
			btn_delvtype5.setEnabled(true);
			txt_qty1_1.setEnabled(true);
			lblNewLabel_29.setEnabled(true);
			txt_qty2_1.setEnabled(true);
			lblNewLabel_42.setEnabled(true);
			txt_qty1_2.setEnabled(true);
			lblNewLabel_33.setEnabled(true);
			txt_qty1_3.setEnabled(true);
			lblNewLabel_341.setEnabled(true);
			txt_qty2_2.setEnabled(true);
			lblNewLabel_381.setEnabled(true);
			txt_qty1_4.setEnabled(true);
			lblNewLabel_391.setEnabled(true);
			txt_qty1_5.setEnabled(true);
			lblNewLabel_401.setEnabled(true);
			txt_qty2_3.setEnabled(true);
			lblNewLabel_411.setEnabled(true);
			txt_qty1_6.setEnabled(true);
			lblNewLabel_431.setEnabled(true);
			txt_qty1_7.setEnabled(true);
			lblNewLabel_441.setEnabled(true);
			txt_qty2_4.setEnabled(true);
			lblNewLabel_451.setEnabled(true);
		}

	}

	protected void setRetNCngCost() {
		if (btn_defaultdelvcharge.getSelection() == true) {
			lblNewLabel_711.setEnabled(false);
			txt_retNcngcost.setEnabled(false);
			lblNewLabel_811.setEnabled(false);
		} else {
			lblNewLabel_711.setEnabled(true);
			txt_retNcngcost.setEnabled(true);
			lblNewLabel_811.setEnabled(true);
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
	private void init() {
		if (dto != null) {
			txt_title.setText(dto.getTitle());//제목
			txt_memo.setText(dto.getMemo());//메모
			setidsetting(dto.getShopid());//샵아이디
			btn_fixed.setSelection(dto.getSelmthdcd().equals("01") ? true : false);//형태구분
			switch(dto.getContracttypcd()) {//계약유형
			case "01":btn_open.setSelection(true);
				break;
			case "02":btn_md.setSelection(true);
				break;
			case "03":btn_direct.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getProdtypcd()) {//판매방식
			case "01":btn_instant.setSelection(true);
				break;
			case "02":btn_reservation.setSelection(true);
				break;

				default : break;
			}
			switch(dto.getProdstatcd()) {//상품상태
			case "01":btn_new.setSelection(true);
				break;
			case "02":btn_used.setSelection(true);
				break;
			case "03":btn_return.setSelection(true);
				break;
				default : break;
			}
			txt_categfee.setText(dto.getValue2());//카테고리수수료율
			txt_brandcateg.setText(dto.getValue3());//브랜드카테고리
			txt_categ1.setText(dto.getValue4());//테마카테고리1
			txt_categ2.setText(dto.getValue5());//테마카테고리2
			switch(dto.getValue6()) {//해외배송여부
			case "01":btn_overarea1.setSelection(true);
				break;
			case "02":btn_overarea2.setSelection(true);
				break;
			case "03":btn_overarea1.setSelection(true);
			btn_overarea2.setSelection(true);
				break;
				default : break;
			}
			cb_repimg.select(Integer.parseInt(dto.getValue7()));//대표이미지
			cb_storeimg.select(Integer.parseInt(dto.getValue8()));//스토어디추가이미지
			txt_start.setText(dto.getValue9());//상품명추가앞문구
			txt_end.setText(dto.getValue10());//상품명추가뒷문구
			txt_brand1.setText(dto.getValue11());//브랜드1
			txt_brand2.setText(dto.getValue12());//브랜드2
			cb_modelno.select(Integer.parseInt(dto.getValue13()));//모델명/품번선택
			txt_importno.setText(dto.getValue14());//수입신고번호
			if (!dto.getKeyword().equals("")) {//검색키워드
				if (dto.getKeyword().contains(",")) {
					String[] search = dto.getKeyword().split(",");
					switch (search.length) {
					case 1:
						txt_search1.setText(search[0]);
						break;
					case 2:
						txt_search1.setText(search[0]);
						txt_search2.setText(search[1]);
						break;
					case 3:
						txt_search1.setText(search[0]);
						txt_search2.setText(search[1]);
						txt_search3.setText(search[2]);
						break;
					case 4:
						txt_search1.setText(search[0]);
						txt_search2.setText(search[1]);
						txt_search3.setText(search[2]);
						txt_search4.setText(search[3]);
						break;
					case 5:
						txt_search1.setText(search[0]);
						txt_search2.setText(search[1]);
						txt_search3.setText(search[2]);
						txt_search4.setText(search[3]);
						txt_search5.setText(search[4]);
						break;
					default:
						break;
					}
				} else {
					txt_search1.setText(dto.getKeyword());
				}
			}
			cb_prodno.select(Integer.parseInt(dto.getProdno()));//해외구매대형여부
			switch(dto.getOverseaspurchased()) {//해외배송여부
			case "01":btn_overseasY.setSelection(true);
				break;
			case "02":btn_overseasN.setSelection(true);
				break;
				default : break;
			}
			switch(dto.getMinorselcnyn()) {//미성년자구매가능여부
			case "01":btn_minorY.setSelection(true);
				break;
			case "02":btn_minorN.setSelection(true);
				break;
				default : break;
			}
			txt_usepreiod.setText(dto.getValue15());//상품사용개월수
			cb_isbn.select(Integer.parseInt(dto.getValue16()));//도서상품ISBN
			switch (dto.getHealthfuncfood()) {//건강기능식품여부
			case "01":
				btn_healthfood.setSelection(true);
				break;
			case "02":
				btn_proddescription.setSelection(true);
				break;
			case "03":
				btn_notcertified.setSelection(true);
				break;
			default:
				break;
			}
			if (dto.getHealthfuncfood().equals("01")) {
				txt_salesreporting.setText(dto.getSalesreporting());//판매업신고기관
				txt_dealerreg.setText(dto.getSalesreportingno());//판매업신고번호
				switch (dto.getAdreview()) {//사전광고심의여부
				case "01":
					btn_adreviewY.setSelection(true);
					break;
				case "02":
					btn_adreviewN.setSelection(true);
					break;
				default:
					break;
				}
				if (dto.getAdreview().equals("01")) {
					txt_adreviewno.setText(dto.getAdreviewno());//광고심의번호
				}
			}
			txt_asdtl.setText(dto.getAsdtl());//A/S가능여부
			switch (dto.getUniquenessyn()) {//특이사항여부
			case "01":
				btn_uniqueneY.setSelection(true);
				break;
			case "02":
				btn_uniqueneN.setSelection(true);
				break;
			default:
				break;
			}
			if (dto.getUniquenessyn().equals("01")) {
				txt_uniquene.setText(dto.getUniquenessval());//특이사항내용

			}	
			cb_consumcost.select(Integer.parseInt(dto.getValue66()));//소비자가적용여부
			txt_rental2.setText(dto.getValue17());//렌탈의무사용기간
			switch (dto.getValue18()) {//렌탈설치비여부
			case "01":
				btn_rental3_1.setSelection(true);
				break;
			case "02":
				btn_rental3_2.setSelection(true);
				break;
			case "03":
				btn_rental3_3.setSelection(true);
				break;
			case "04":
				btn_rental3_4.setSelection(true);
				break;
			default:
				break;
			}
			if(dto.getValue18().equals("03")) {
				txt_rental3_1.setText(dto.getValue19());//렌탈설치비유료금액
			}	
			switch (dto.getValue20()) { //렌탈등록비여부
			case "01":
				btn_rental4_1.setSelection(true);
				break;
			case "02":
				btn_rental4_2.setSelection(true);
				break;
			case "03":
				btn_rental4_3.setSelection(true);
				break;
			default:
				break;
			}
			if(dto.getValue20().equals("02")) {
				txt_rental4_1.setText(dto.getValue21());//렌탈등록비유료금액
			}		
			switch (dto.getValue22()) { //렌탈소비자가여부
			case "01":
				btn_rental5_1.setSelection(true);
				break;
			case "02":
				btn_rental5_2.setSelection(true);
				break;
			default:
				break;
			}
			if(dto.getValue22().equals("02")) {
				txt_rental5_1.setText(dto.getValue23());//렌탈소비자가유료금액
			}	
			switch (dto.getSalesperiodyn()) {//판매기간설정여부
			case "01":
				btn_salesperiodY.setSelection(true);
				break;
			case "02":
				btn_salesperiodN.setSelection(true);
				break;
			default:
				break;
			}
			if (dto.getSalesperiodyn().equals("01")) {
				cb_salesperiodA.select(Integer.parseInt(dto.getSalesperiodtyp()));//기간설정타입
				if (dto.getSalesperiodtyp().equals("0")) {
					cb_salesperiodB.setText(dto.getSalesperiodto());//판매기간종료일
				} else {
					txt_calfrom.setText(dto.getSalesperiodfrom());//판매기간시작일
					txt_calto.setText(dto.getSalesperiodto());//판매기간종료일
				}
			}
			switch (dto.getValue24()) {//입력형옵션사용여부
			case "01":
				btn_inputopt1.setSelection(true);
				break;
			case "02":
				btn_inputopt2.setSelection(true);
				break;
			default:
				break;
			}
			if(dto.getValue24().equals("02")) {
				txt_inputopt1.setText(dto.getValue25());//입력형옵셩내용
			}	
			switch (dto.getValue65()) {//옵션정렬방법
			case "01":
				btn_optdesc1.setSelection(true);
				break;
			case "02":
				btn_optdesc2.setSelection(true);
				break;
			case "03":
				btn_optdesc3.setSelection(true);
				break;
			case "04":
				btn_optdesc4.setSelection(true);
				break;
			default:
				break;
			}
			cb_2opt.select(Integer.parseInt(dto.getValue26()));//2단옵션적용여부
			switch (dto.getQuantityyn()) {//주문당판매수량제한여부
			case "01":
				btn_quantityY.setSelection(true);
				break;
			case "02":
				btn_quantityN.setSelection(true);
				break;
			default:
				break;
			}		
			if (dto.getQuantityyn().equals("01")) {
				txt_quantity.setText(dto.getQuantitycnt());//판매제한수량
			}	
			switch (dto.getExpresstyp()) {//배송방법타입
			case "01":
				btn_express.setSelection(true);
				break;
			case "02":
				btn_post.setSelection(true);
				break;
			case "03":
				btn_freight.setSelection(true);
				break;
			case "04":
				btn_notexpress.setSelection(true);
				break;
			default:
				break;
			}	
			switch (dto.getDelvtype()) {//배송비설정여부
			case "01":
				btn_delvchargedefault.setSelection(true);
				break;
			case "02":
				btn_exceptiondelvcharge.setSelection(true);
				break;
			default:
				break;
			}	
			if(dto.getDelvtype().equals("01")) {
				txt_defaultship.setText(dto.getValue27());//배송비기본배송비시금액
			}else {
				switch (dto.getDelvmethod()) {//배송비종류
				case "01":
					btn_delvtype1.setSelection(true);
					break;
				case "02":
					btn_delvtype2.setSelection(true);
					break;
				case "03":
					btn_delvtype3.setSelection(true);
					break;
				case "04":
					btn_delvtype4.setSelection(true);
					break;
				case "05":
					btn_delvtype5.setSelection(true);
					break;
				default:
					break;
				}	
				if (dto.getDelvmethod().equals("01")) {
					switch (dto.getDelvfreechk()) {//배송비안에 무료및조건부무료체크여부
					case "Y":
						btn_freechk.setSelection(true);
						break;
					case "N":
						btn_freechk.setSelection(false);
						break;
					default:
						break;
					}	
				} else if (dto.getDelvmethod().equals("02")) {
					txt_delvcost1.setText(dto.getShipprc());//기본배송비
				} else if (dto.getDelvmethod().equals("03")) {
					txt_delvcost2.setText(dto.getShipprc());//기본배송비
					txt_delvcost2_1.setText(dto.getShipprc2());//기본배송비2
				} else if (dto.getDelvmethod().equals("04")) {
					switch (dto.getValue28()) {//배송비개당시무료여부
					case "Y":
						btn_onechk.setSelection(true);
						break;
					case "N":
						btn_onechk.setSelection(false);
						break;
					default:
						break;
					}
					txt_delvcost2.setText(dto.getShipprc());//기본배송비
					txt_delvcost2_2.setText(dto.getShipprc2());//기본배송비2
				} else if (dto.getDelvmethod().equals("05")) {
					txt_qty1_1.setText(dto.getDelvqty());//수량별체크시수량들1
					txt_qty1_2.setText(dto.getValue29());//수량2
					txt_qty1_3.setText(dto.getValue30());//수량3
					txt_qty1_4.setText(dto.getValue31());//수량4
					txt_qty1_5.setText(dto.getValue32());//수량5
					txt_qty1_6.setText(dto.getValue33());//수량6
					txt_qty1_7.setText(dto.getValue34());//수량7
					txt_qty2_1.setText(dto.getDelvqtycost());//수량별체크시금액들
					txt_qty2_2.setText(dto.getValue35());//금액2
					txt_qty2_3.setText(dto.getValue36());//금액3
					txt_qty2_4.setText(dto.getValue37());//금액4
				}
				cb_prepayment.select(Integer.parseInt(dto.getPrepayment()));//착불,선불결제여부
			}
			txt_shipuniquene.setText(dto.getShipuniquene());//배송비특이사항
			txt_jejucharge.setText(dto.getJejuprc());//제주추가배송비
			txt_islandcharge.setText(dto.getIslandprc());//도서산간추가배송비
			txt_returnaddr.setText(dto.getAddrin());//반품배송지주소
			switch (dto.getReturnshippingyn()) {//반품교환배송비타입
			case "01":
				btn_defaultdelvcharge.setSelection(true);
				break;
			case "02":
				btn_exceptiondelvcharge.setSelection(false);
				break;
			default:
				break;
			}
			if (dto.getReturnshippingyn().equals("02")) {
				txt_retNcngcost.setText(dto.getRetncngprc());//반품교환배송비
			}	
			switch (dto.getValue38()) {//가격비교등록여부
			case "01":
				btn_pricecompar1.setSelection(true);
				break;
			case "02":
				btn_pricecompar2.setSelection(false);
				break;
			default:
				break;
			}
			switch (dto.getValue39()) {//단말기할부여부
			case "01":
				btn_terminalprice1.setSelection(true);
				break;
			case "02":
				btn_terminalprice2.setSelection(false);
				break;
			default:
				break;
			}
			switch (dto.getValue40()) {//핸드폰가입비여부
			case "01":
				btn_activa1.setSelection(true);
				break;
			case "02":
				btn_activa2.setSelection(false);
				break;
			default:
				break;
			}
			switch (dto.getValue41()) {//핸드폰유심비여부
			case "01":
				btn_usim1.setSelection(true);
				break;
			case "02":
				btn_usim2.setSelection(false);
				break;
			default:
				break;
			}	
			txt_phoneurl1.setText(dto.getValue42());//핸드폰가입신청URL1
			cb_phoneurl1.select(Integer.parseInt(dto.getValue43()));//핸드폰가입신청내용적용1
			txt_phoneurl2.setText(dto.getValue44());//핸드폰가입신청URL2
			cb_phoneurl2.select(Integer.parseInt(dto.getValue45()));//핸드폰가입신청내용적용2
			txt_usesale.setText(dto.getValue46());//판매권사용갯수
			switch (dto.getValue47()) {//완구사용자성별여부
			case "01":
				btn_toysex1.setSelection(true);
				break;
			case "02":
				btn_toysex2.setSelection(false);
				break;
			case "03":
				btn_toysex3.setSelection(false);
				break;
			default:
				break;
			}
			switch (dto.getValue48()) {//완구사용자연령여부
			case "01":
				btn_toyage1.setSelection(true);
				break;
			case "02":
				btn_toyage2.setSelection(false);
				break;
			case "03":
				btn_toyage3.setSelection(false);
				break;
			default:
				break;
			}
			if(dto.getValue48().equals("02")) {
				txt_toyage2_1.setText(dto.getValue49());//완구일부연령시작나이
				txt_toyage2_2.setText(dto.getValue50());//완구일부연령종료나이
				cb_toyage2_1.select(Integer.parseInt(dto.getValue51()));//완구일부연령개월및나이선택
			}else if(dto.getValue48().equals("03")) {
				txt_toyage3_1.setText(dto.getValue52());//완구특정연령시작나이
				cb_toyage3_1.select(Integer.parseInt(dto.getValue53()));//완구특정연령개월및나이선택
				cb_toyage3_2.select(Integer.parseInt(dto.getValue54()));//완구특정연령이상및이하선택
			}
			txt_top.setText(dto.getValue55());//상품설명상단추가문구
			txt_bottom.setText(dto.getValue56());//상품설명하단추가문구
			switch (dto.getValue57()) {//판매자부담즉시할인여부
			case "01":
				btn_sellerdis1.setSelection(true);
				break;
			case "02":
				btn_sellerdis2.setSelection(true);
				break;
			case "03":
				btn_sellerdis1.setSelection(true);
				btn_sellerdis2.setSelection(true);
				break;
			default:
				break;
			}
			if(dto.getValue57().equals("01")) {
				switch (dto.getValue58()) {//판매자부담정액정률여부
				case "01":
					btn_sellerdis1_1.setSelection(true);
					break;
				case "02":
					btn_sellerdis1_2.setSelection(true);
					break;
				default:
					break;
				}
				if(!dto.getValue58().equals("")) {
					txt_sellerdis1_1.setText(dto.getValue59());//판매자부담할인금액
				}
			}else if(dto.getValue57().equals("03")) {
				switch (dto.getValue58()) {//판매자부담정액정률여부
				case "01":
					btn_sellerdis1_1.setSelection(true);
					break;
				case "02":
					btn_sellerdis1_2.setSelection(true);
					break;
				default:
					break;
				}
				if(!dto.getValue58().equals("")) {
					txt_sellerdis1_1.setText(dto.getValue59());//판매자부담할인금액
				}	
				txt_sellerdis2_1.setText(dto.getValue60());//할인기간시작일
				txt_sellerdis2_2.setText(dto.getValue61());//할인기간종료일
			}	
			cb_ipoint.select(Integer.parseInt(dto.getValue62()));//I-포인트선택여부
			txt_ipoint.setText(dto.getValue63());
			switch (dto.getValue64()) {//무이자할부여부
			case "N":
				btn_interestfeeN.setSelection(true);
				break;
			case "Y":
				btn_interestfeeY.setSelection(true);
				break;
			default:
				break;
			}
			switch (dto.getUseyn()) {
			case "Y":
				cb_whether.select(1);
				break;
			case "N":
				cb_whether.select(2);
				break;
			default:
				break;
			}
		}
	}

	void setSalessetting(String info) {
		for (int i = 0; i < this.cb_salesperiodB.getItems().length; i++) {
			String type = this.cb_salesperiodB.getItems()[i];
			if (type.equals(info)) {
				this.cb_salesperiodB.setText(type);
				break;
			}
		}
	}

	protected void setQuantity() {
		if (btn_quantityY.getSelection()) {
			txt_quantity.setEnabled(true);
		} else {
			txt_quantity.setEnabled(false);
		}

	}

	protected void setAdreview() {
		if (btn_adreviewY.getSelection()) {
			lblNewLabel_10.setEnabled(true);
			txt_adreviewno.setEnabled(true);
		} else {
			lblNewLabel_10.setEnabled(false);
			txt_adreviewno.setEnabled(false);
		}

	}

	protected void setHealthFood() {
		if (btn_healthfood.getSelection()) {
			lblNewLabel_8.setEnabled(true);
			txt_salesreporting.setEnabled(true);
			lblNewLabel_9.setEnabled(true);
			txt_dealerreg.setEnabled(true);
			btn_adreviewY.setEnabled(true);
			btn_adreviewN.setEnabled(true);
			if (btn_adreviewY.getSelection()) {
				lblNewLabel_10.setEnabled(true);
				txt_adreviewno.setEnabled(true);
			} else {
				lblNewLabel_10.setEnabled(false);
				txt_adreviewno.setEnabled(false);
			}
		} else {
			lblNewLabel_8.setEnabled(false);
			txt_salesreporting.setEnabled(false);
			lblNewLabel_9.setEnabled(false);
			txt_dealerreg.setEnabled(false);
			btn_adreviewY.setEnabled(false);
			btn_adreviewN.setEnabled(false);
			lblNewLabel_10.setEnabled(false);
			txt_adreviewno.setEnabled(false);
		}

	}

	protected void setUniQueness() {
		if (btn_uniqueneY.getSelection()) {
			lblNewLabel_7.setEnabled(true);
			txt_uniquene.setEnabled(true);
		} else {
			lblNewLabel_7.setEnabled(false);
			txt_uniquene.setEnabled(false);
		}
	}

	protected void setSalesCalendar() {
		if (btn_salesperiodN.getSelection()) {
			composite_11.setVisible(false);
			lblNewLabel_23.setEnabled(false);
		} else {
			composite_11.setVisible(true);
			lblNewLabel_23.setEnabled(true);
		}

	}

	protected void Salesperiod() {
		if (cb_salesperiodA.getSelectionIndex() == 0) {
			lblNewLabel_24.setVisible(true);
			cb_salesperiodB.setVisible(true);
			txt_calfrom.setVisible(false);
			txt_calto.setVisible(false);
			lb_cal.setVisible(false);
		} else {
			lblNewLabel_24.setVisible(false);
			cb_salesperiodB.setVisible(false);
			txt_calfrom.setVisible(true);
			txt_calto.setVisible(true);
			lb_cal.setVisible(true);
		}

	}
	private String setNumberFormat(String text) {
		String value = "";
		value = text.trim().length()==0?"0":text;
		return value;
	}
	@Override
	protected void okPressed() {
		if (txt_title.getText().trim().length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "제목을 입력하여 주시기 바랍니다.");
			txt_title.setFocus();
			return;
		}
		if (!btn_open.getSelection() && !btn_md.getSelection() && !btn_direct.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "계약유형을 선택하여 주시기 바랍니다.");
			btn_open.setFocus();
			return;
		}
		if (!btn_instant.getSelection() && !btn_reservation.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "판매방식을 선택하여 주시기 바랍니다.");
			btn_instant.setFocus();
			return;
		}
		if (!btn_new.getSelection() && !btn_used.getSelection() && !btn_return.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "상품상태를 선택하여 주시기 바랍니다.");
			btn_new.setFocus();
			return;
		}
		if (!btn_minorY.getSelection() && !btn_minorN.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "미성년자구매가능을 선택하여 주시기 바랍니다.");
			btn_minorY.setFocus();
			return;
		}
		if (txt_asdtl.getText().trim().length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "A/S가능여부를 입력하여 주시기 바랍니다.");
			txt_asdtl.setFocus();
			return;
		}
		if (!btn_salesperiodY.getSelection() && !btn_salesperiodN.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "판매기간설정을 선택하여 주시기 바랍니다.");
			btn_salesperiodY.setFocus();
			return;
		}
		if (!btn_delvchargedefault.getSelection() && !btn_delvchargeexception.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "배송비설정을의 배송비적용을 선택하여 주시기 바랍니다.");
			btn_delvchargedefault.setFocus();
			return;
		}
		if (!btn_defaultdelvcharge.getSelection() && !btn_exceptiondelvcharge.getSelection()) {
			MessageDialog.openInformation(getShell(), TITLE, "반품/교환배송비를 선택하여 주시기 바랍니다.");
			btn_defaultdelvcharge.setFocus();
			return;
		}
		if (cb_whether.getSelectionIndex() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "사용여부를 선택하여 주시기 바랍니다.");
			cb_whether.setFocus();
			return;
		}
		
		ShopProductInterParkAdditionDto dto = new ShopProductInterParkAdditionDto();
		
		dto.setTitle(txt_title.getText()); //제목
		dto.setMemo(txt_memo.getText()); //메모
		dto.setValue1(cb_idcheck.getText()); //쇼핑몰아이디
		dto.setSelmthdcd(btn_fixed.getSelection()? "01" : ""); //형태구분
		dto.setContracttypcd(btn_open.getSelection()? "01" : btn_md.getSelection()? "02" : "03"); //계약유형
		dto.setProdtypcd(btn_instant.getSelection()? "01" : "02"); //판매방식
		dto.setProdstatcd(btn_new.getSelection()? "01" : btn_used.getSelection()? "02" : "03"); //상품상태
		dto.setValue2(setNumberFormat(txt_categfee.getText())); //카테고리수수료율
		dto.setValue3(txt_brandcateg.getText()); //브랜드카테고리
		dto.setValue4(txt_categ1.getText()); //테마카테고리1
		dto.setValue5(txt_categ2.getText()); //테마카테고리2
		dto.setValue6(btn_overarea1.getSelection()?"01":btn_overarea2.getSelection()?"02":btn_overarea1.getSelection()&&btn_overarea2.getSelection()?"03":""); //해외배송여부
		dto.setValue7(String.valueOf(cb_repimg.getSelectionIndex())); //대표이미지
		dto.setValue8(String.valueOf(cb_storeimg.getSelectionIndex())); //스토어디추가이미지
		dto.setValue9(txt_start.getText()); //상품명추가앞문구
		dto.setValue10(txt_end.getText()); //상품명추가뒷문구
		dto.setValue11(txt_brand1.getText()); //브랜드1
		dto.setValue12(txt_brand2.getText()); //브랜드2
		dto.setValue13(String.valueOf(cb_modelno.getSelectionIndex())); //모델명/품번선택
		dto.setValue14(txt_importno.getText()); //수입신고번호
		String sear1 = txt_search1.getText().trim().length() == 0 ? "" : txt_search1.getText();
		String sear2 = txt_search2.getText().trim().length() == 0 ? "" : "," + txt_search2.getText();
		String sear3 = txt_search3.getText().trim().length() == 0 ? "" : "," + txt_search3.getText();
		String sear4 = txt_search4.getText().trim().length() == 0 ? "" : "," + txt_search4.getText();
		String sear5 = txt_search5.getText().trim().length() == 0 ? "" : "," + txt_search5.getText();
		String keyword = sear1 + sear2 + sear3 + sear4 + sear5;
		dto.setKeyword(keyword); //검색키워드
		dto.setProdno(String.valueOf(cb_prodno.getSelectionIndex())); //판매자상품번호
		dto.setOverseaspurchased(btn_overseasY.getSelection()? "01":btn_overseasN.getSelection()? "02":""); //해외구매대형여부
		dto.setMinorselcnyn(btn_minorY.getSelection()? "01" : "02"); //미성년자구매가능여부
		dto.setValue15(setNumberFormat(txt_usepreiod.getText())); //상품사용개월수
		dto.setValue16(String.valueOf(cb_isbn.getSelectionIndex())); //도서상품ISBN
		dto.setHealthfuncfood(btn_healthfood.getSelection()? "01": btn_proddescription.getSelection()? "02": btn_notcertified.getSelection()? "03" : ""); //건강기능식품여부
		if (dto.getHealthfuncfood().equals("01")) {
			dto.setSalesreporting(txt_salesreporting.getText()); //판매업신고기관
			dto.setSalesreportingno(txt_dealerreg.getText()); //판매업신고번호
			dto.setAdreview(btn_adreviewY.getSelection()? "01" : btn_adreviewN.getSelection()? "02" : ""); //사전광고심의여부
			if (dto.getAdreview().equals("01")) {
				dto.setAdreviewno(txt_adreviewno.getText()); //광고심의번호
			}
		}
		dto.setAsdtl(txt_asdtl.getText()); //A/S가능여부
		dto.setUniquenessyn(btn_uniqueneY.getSelection()? "01": btn_uniqueneN.getSelection()? "02" : ""); //특이사항여부
		if (dto.getUniquenessyn().equals("01")) {
			dto.setUniquenessval(txt_uniquene.getText()); //특이사항내용
		}		
		dto.setValue66(String.valueOf(cb_consumcost.getSelectionIndex()));//소비자가적용여부
		dto.setValue17(setNumberFormat(txt_rental2.getText())); //렌탈의무사용기간
		dto.setValue18(btn_rental3_1.getSelection()?"01":btn_rental3_2.getSelection()?"02":btn_rental3_3.getSelection()?"03":btn_rental3_4.getSelection()?"04":""); //렌탈설치비여부
		if(dto.getValue18().equals("03")) {
			dto.setValue19(setNumberFormat(txt_rental3_1.getText())); //렌탈설치비유료금액
		}		
		dto.setValue20(btn_rental4_1.getSelection()?"01":btn_rental4_2.getSelection()?"02":btn_rental4_3.getSelection()?"03":""); //렌탈등록비여부
		if(dto.getValue20().equals("02")) {
			dto.setValue21(setNumberFormat(txt_rental4_1.getText())); //렌탈등록비유료금액	
		}		
		dto.setValue22(btn_rental5_1.getSelection()?"01":btn_rental5_2.getSelection()?"02":""); //렌탈소비자가여부
		if(dto.getValue22().equals("02")) {
			dto.setValue23(setNumberFormat(txt_rental5_1.getText())); //렌탈소비자가유료금액
		}	
		dto.setSalesperiodyn(btn_salesperiodY.getSelection()? "01" : btn_salesperiodN.getSelection()? "02" :""); //판매기간설정여부
		if (dto.getSalesperiodyn().equals("01")) {
			dto.setSalesperiodtyp(String.valueOf(cb_salesperiodA.getSelectionIndex())); //기간설정타입
			if (dto.getSalesperiodtyp().equals("0")) {
				dto.setSalesperiodfrom(YDMATimeUtil.getCurrentDateHanjin()); //판매기간시작일
				dto.setSalesperiodto(cb_salesperiodB.getText()); //판매기간종료일
			} else {
				dto.setSalesperiodfrom(txt_calfrom.getText()); //판매기간시작일
				dto.setSalesperiodto(txt_calto.getText()); //판매기간종료일
			}
		}		
		dto.setValue24(btn_inputopt1.getSelection()?"01":btn_inputopt2.getSelection()?"02":""); //입력형옵션사용여부
		if(dto.getValue24().equals("02")) {
			dto.setValue25(txt_inputopt1.getText()); //입력형옵셩내용
		}	
		dto.setValue65(btn_optdesc1.getSelection()?"01":btn_optdesc2.getSelection()?"02":btn_optdesc3.getSelection()?"03":btn_optdesc4.getSelection()?"04":""); //옵션정렬방법
		dto.setValue26(String.valueOf(cb_2opt.getSelectionIndex())); //2단옵션적용여부
		dto.setQuantityyn(btn_quantityY.getSelection()? "01": btn_quantityN.getSelection()? "02" : ""); //주문당판매수량제한여부
		if (dto.getQuantityyn().equals("01")) {
			dto.setQuantitycnt(setNumberFormat(txt_quantity.getText())); //판매제한수량
		}		
		dto.setExpresstyp(btn_express.getSelection()? "01": btn_post.getSelection()? "02": btn_freight.getSelection()? "03" : btn_notexpress.getSelection()? "04" : ""); //배송방법타입
		dto.setDelvtype(btn_delvchargedefault.getSelection()? "01" : "02"); //배송비설정여부
		dto.setShipprc("0"); //기본배송비
		dto.setShipprc2("0"); //기본배송비2
		dto.setDelvqty("0"); //수량별체크시수량들1
		dto.setValue29("0"); //수량2
		dto.setValue30("0"); //수량3
		dto.setValue31("0"); //수량4
		dto.setValue32("0"); //수량5
		dto.setValue33("0"); //수량6
		dto.setValue34("0"); //수량7
		dto.setDelvqtycost("0"); //수량별체크시금액들
		dto.setValue35("0"); //금액2
		dto.setValue36("0"); //금액3
		dto.setValue37("0"); //금액4
		if(dto.getDelvtype().equals("01")) {
			dto.setValue27(txt_defaultship.getText()); //배송비기본배송비시금액
		}else {
			dto.setDelvmethod(btn_delvtype1.getSelection() ? "01": btn_delvtype2.getSelection() ? "02": btn_delvtype3.getSelection() ? "03": btn_delvtype4.getSelection() ? "04" : btn_delvtype5.getSelection() ? "05" : ""); //배송비종류
			if (dto.getDelvmethod().equals("01")) {
				dto.setDelvfreechk(btn_freechk.getSelection() ? "Y" : "N"); //배송비안에 무료및조건부무료체크여부
			} else if (dto.getDelvmethod().equals("02")) {
				dto.setShipprc(setNumberFormat(txt_delvcost1.getText())); //기본배송비
			} else if (dto.getDelvmethod().equals("03")) {
				dto.setShipprc(setNumberFormat(txt_delvcost2.getText())); //기본배송비
				dto.setShipprc2(setNumberFormat(txt_delvcost2_1.getText())); //기본배송비2
			} else if (dto.getDelvmethod().equals("04")) {
				dto.setValue28(btn_onechk.getSelection() ? "Y" : "N"); //배송비개당시무료여부
				dto.setShipprc(setNumberFormat(txt_delvcost2.getText())); //기본배송비
				dto.setShipprc2(setNumberFormat(txt_delvcost2_2.getText())); //기본배송비2
			} else if (dto.getDelvmethod().equals("05")) {
				dto.setDelvqty(setNumberFormat(txt_qty1_1.getText())); //수량별체크시수량들1
				dto.setValue29(setNumberFormat(txt_qty1_2.getText())); //수량2
				dto.setValue30(setNumberFormat(txt_qty1_3.getText())); //수량3
				dto.setValue31(setNumberFormat(txt_qty1_4.getText())); //수량4
				dto.setValue32(setNumberFormat(txt_qty1_5.getText())); //수량5
				dto.setValue33(setNumberFormat(txt_qty1_6.getText())); //수량6
				dto.setValue34(setNumberFormat(txt_qty1_7.getText())); //수량7
				dto.setDelvqtycost(setNumberFormat(txt_qty2_1.getText())); //수량별체크시금액들
				dto.setValue35(setNumberFormat(txt_qty2_2.getText())); //금액2
				dto.setValue36(setNumberFormat(txt_qty2_3.getText())); //금액3
				dto.setValue37(setNumberFormat(txt_qty2_4.getText())); //금액4
			}
			dto.setPrepayment(String.valueOf(cb_prepayment.getSelectionIndex())); //착불,선불결제여부
		}	
		dto.setShipuniquene(txt_shipuniquene.getText()); //배송비특이사항
		dto.setJejuprc(setNumberFormat(txt_jejucharge.getText())); //제주추가배송비
		dto.setIslandprc(setNumberFormat(txt_islandcharge.getText())); //도서산간추가배송비
		dto.setAddrin(txt_returnaddr.getText()); //반품배송지주소
		dto.setReturnshippingyn(btn_defaultdelvcharge.getSelection()? "01" : btn_exceptiondelvcharge.getSelection()?"02":""); //반품교환배송비타입
		if (dto.getReturnshippingyn().equals("02")) {
			dto.setRetncngprc(setNumberFormat(txt_retNcngcost.getText())); //반품교환배송비
		}		
		dto.setValue38(btn_pricecompar1.getSelection()?"01":btn_pricecompar2.getSelection()?"02":""); //가격비교등록여부
		dto.setValue39(btn_terminalprice1.getSelection()?"01":btn_terminalprice2.getSelection()?"02":""); //단말기할부여부
		dto.setValue40(btn_activa1.getSelection()?"01":btn_activa2.getSelection()?"02":""); //핸드폰가입비여부
		dto.setValue41(btn_usim1.getSelection()?"01":btn_usim2.getSelection()?"02":""); //핸드폰유심비여부
		dto.setValue42(txt_phoneurl1.getText()); //핸드폰가입신청URL1
		dto.setValue43(String.valueOf(cb_phoneurl1.getSelectionIndex())); //핸드폰가입신청내용적용1
		dto.setValue44(txt_phoneurl2.getText()); //핸드폰가입신청URL2
		dto.setValue45(String.valueOf(cb_phoneurl2.getSelectionIndex())); //핸드폰가입신청내용적용2
		dto.setValue46(setNumberFormat(txt_usesale.getText())); //판매권사용갯수
		dto.setValue47(btn_toysex1.getSelection()?"01":btn_toysex2.getSelection()?"02":btn_toysex3.getSelection()?"03":""); //완구사용자성별여부
		dto.setValue48(btn_toyage1.getSelection()?"01":btn_toyage2.getSelection()?"02":btn_toyage3.getSelection()?"03":""); //완구사용자연령여부
		dto.setValue49(setNumberFormat(txt_toyage2_1.getText())); //완구일부연령시작나이
		dto.setValue50(setNumberFormat(txt_toyage2_2.getText())); //완구일부연령종료나이
		dto.setValue51(String.valueOf(cb_toyage2_1.getSelectionIndex())); //완구일부연령개월및나이선택
		dto.setValue52(setNumberFormat(txt_toyage3_1.getText())); //완구특정연령시작나이
		dto.setValue53(String.valueOf(cb_toyage3_1.getSelectionIndex())); //완구특정연령개월및나이선택
		dto.setValue54(String.valueOf(cb_toyage3_2.getSelectionIndex())); //완구특정연령이상및이하선택
		dto.setValue55(txt_top.getText()); //상품설명상단추가문구
		dto.setValue56(txt_bottom.getText()); //상품설명하단추가문구
		dto.setValue57(btn_sellerdis1.getSelection()?"01":btn_sellerdis2.getSelection()?"02":btn_sellerdis1.getSelection()&&btn_sellerdis2.getSelection()?"03":""); //판매자부담즉시할인여부
		if(dto.getValue57().equals("01")) {
			dto.setValue58(btn_sellerdis1_1.getSelection()?"01":btn_sellerdis1_2.getSelection()?"02":""); //판매자부담정액정률여부
			if(!dto.getValue58().equals("")) {
				dto.setValue59(setNumberFormat(txt_sellerdis1_1.getText())); //판매자부담할인금액
			}
		}else if(dto.getValue57().equals("03")) {
			dto.setValue58(btn_sellerdis1_1.getSelection()?"01":btn_sellerdis1_2.getSelection()?"02":""); //판매자부담정액정률여부
			if(!dto.getValue58().equals("")) {
				dto.setValue59(setNumberFormat(txt_sellerdis1_1.getText())); //판매자부담할인금액
			}			
			dto.setValue60(txt_sellerdis2_1.getText()); //할인기간시작일
			dto.setValue61(txt_sellerdis2_2.getText()); //할인기간종료일
		}	
		dto.setValue62(String.valueOf(cb_ipoint.getSelectionIndex())); //I-포인트선택여부
		dto.setValue63(setNumberFormat(txt_ipoint.getText())); //I-포인트할인
		dto.setValue64(btn_interestfeeN.getSelection()?"N":btn_interestfeeY.getSelection()?"Y":""); //무이자할부여부
		dto.setUseyn(cb_whether.getSelectionIndex() == 1 ? "Y" : "N"); //사용여부

		ShoppingMallDao dao = new ShoppingMallDao();
		try {
			if (this.dto == null) {
				int seq = dao.getSeqNumber(list.get(0));
				int result = dao.ShopAddrDtlInterparkInsert(list.get(0),dto,seq + 1);
				if (result != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보를 저장하였습니다.");
					super.okPressed();
				} else {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보저장에 실패하였습니다.");
				}
			} else {
				int result = dao.ShopAddrDtlInterparkUpdate(this.dto.getShopcd(), dto,this.dto.getSeq());
				if (result != 0) {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보를 수정하였습니다.");
					super.okPressed();
				} else {
					MessageDialog.openInformation(getShell(), TITLE, "부가정보수정에 실패하였습니다.");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		return new Point(1580, 1467);
	}
}
