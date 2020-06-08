package com.kdj.mlink.ezup3.ui;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.data.dao.ProductIforDao;

public class ProductBasicDetail extends CommandDialog {
	ProductMstDetail opener;
	Button btnOk;
	Button btnCancel;
	Button btnInsert;
	Button btnCopy;
	String TITLE = "상품기본정보";
	String[] originArea = { "원산지", "America", "Australia", "Belgium", "Brazil", "Chile", "CHINA", "ITALY", "KOREA",
			"Mexico", "Norway", "Thailand", "과테말라", "국내산", "그루지아(조지아)", "그리스", "기타국가", "나이지리아", "남아프리카공화국", "네델란드",
			"네팔", "노르웨이", "뉴질랜드", "니카라과", "대만", "대한민국", "덴마크", "도미니카", "도미니카공화국", "독일", "라오스", "라트비아", "러시아", "러시아공화국",
			"레바논", "루마니아", "룩셈부르크", "리비아", "리투아니아", "마다가스카르", "마카오", "마케도니아", "말레이시아", "말레이지아", "멕시코", "모나코", "모로코",
			"모리셔스", "몰다비아", "몰도바", "몰타", "몽골", "미국", "미국/일본", "미국OEM", "미얀마", "바레인", "방글라데시", "베네수엘라", "베트남", "벨기에",
			"벨로루시", "보스니아", "복합원산지", "볼리비아", "북한", "불가리아", "브라질", "사우디아라비아", "세네갈", "세르비아", "수입산", "스리랑카", "스웨덴", "스위스",
			"스코틀랜드", "스페인", "슬로바키아", "슬로베니아", "싱가폴", "아랍에미레이트", "아랍에미리트", "아르메니아", "아르헨티나", "아이티", "아일랜드", "아프리카",
			"알바니아", "에스토니아", "에콰도르", "엘살바도르", "영국", "오스트레일리아", "오스트리아", "온두라스", "외국산", "요르단", "우간다", "우루과이", "우즈베키스탄",
			"우크라이나", "원양산", "유럽연합(EU)", "이디오피아", "이라크", "이란", "이스라엘", "이집트", "이탈리아", "이태리", "인도", "인도네시아", "인도네시아OEM",
			"인디아", "일본", "일본/태국", "중국", "중국/대만", "중국/말레이시아", "중국/미얀마", "중국/베트남", "중국/인도", "중국/인도네시아", "중국/일본", "중국/태국",
			"중국/필리핀", "중국OEM", "중국개성", "중국외복수국가", "지부티", "체코", "칠레", "카타르", "캄보디아", "캐나다", "케냐", "켈리포니아", "콜롬비아",
			"쿠웨이트", "크로아티아", "타이완", "타일랜드", "태국", "터키", "튀니지", "파라과이", "파키스탄", "페루", "포르투칼", "폴란드", "푸에르토리코", "프랑스",
			"프랑스/미국", "프랑스/중국", "피지", "핀란드", "필리핀", "한국/베트남", "한국/중국", "한국/중국/미국", "헝가리", "호주", "홍콩" };

	private Composite composite;
	private Combo cb_season;
	private Label brandnm;
	private Text txt_brandnm;
	private Combo cb_prodgubun;
	private Label prodCate;
	private Label jejo;
	private Label origin;
	private Label season;
	private Text txt_expcost;
	private Text txt_manfnm;
	private Label status;
	private Label salesarea;
	private Label shipping;
	private Label shippingfee;
	private Label taxbreak;
	private Combo cb_orgnm;
	private Combo cb_prodstat;
	private Combo cb_locarea;
	private Combo cb_expgubun;
	private Combo cb_taxgubun;
	private Label number;
	private Combo cb_number;
	private Button btn_flagopt;
	private Label option1;
	private Label option2;
	private Label option3;
	private Label option4;
	private Button btn_flaginvt1;
	private Button btn_flaginvt2;
	private Button btn_flagoptchg1;
	private Button btn_flagoptchg2;
	private Button btn_flagoptset;
	private Label label;
	private Label label_1;
	private Label label_3;

	String infornumber;
	String prodcd;
	private Label label_2;
	private Text txt_infornm;
	private Button btn_insert;
	private Button btn_copy;
	private Text txt_orgnm;
	private Label label_4;
	private Text txt_optnm1;
	private Text txt_optnm2;
	private Label lb_mfgubun;
	private Combo cb_mfgubun;
	List<List<String>> list_opt;
	private Label label_5;
	private Label label_6;
	private Text txt_supplier;
	private Text txt_businessno;
	private Label label_7;

	public ProductBasicDetail(Shell parentShell, ProductMstDetail opener, String prodcd, String infornumber,
			List<List<String>> list_opt) {
		super(parentShell);
		this.opener = opener;
		this.prodcd = prodcd;
		this.infornumber = infornumber;
		this.list_opt = list_opt;

	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(2, false));

		composite = new Composite(container, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 586;
		gd_composite.widthHint = 1275;
		composite.setLayoutData(gd_composite);
		composite.setLayout(null);

		cb_season = new Combo(composite, SWT.NONE);
		cb_season.setItems(new String[] { "시즌", "봄", "여름", "가을", "겨울", "FW", "SS", "기타" });
		cb_season.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_season.setBounds(135, 194, 150, 31);

		brandnm = new Label(composite, SWT.NONE);
		brandnm.setText("브랜드명");
		brandnm.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		brandnm.setBounds(5, 71, 120, 23);

		txt_brandnm = new Text(composite, SWT.BORDER);
		txt_brandnm.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_brandnm.setBounds(135, 71, 250, 23);

		cb_prodgubun = new Combo(composite, SWT.NONE);
		cb_prodgubun.setItems(new String[] { "상품구분", "위탁", "제조", "사입", "직영", "자료없음" });
		cb_prodgubun.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_prodgubun.setBounds(135, 100, 150, 23);

		prodCate = new Label(composite, SWT.NONE);
		prodCate.setText("* 상품구분");
		prodCate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		prodCate.setBounds(5, 100, 120, 31);

		jejo = new Label(composite, SWT.NONE);
		jejo.setText("* 제조사");
		jejo.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		jejo.setBounds(5, 134, 120, 23);

		origin = new Label(composite, SWT.NONE);
		origin.setText("* 원산지");
		origin.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		origin.setBounds(5, 160, 120, 31);

		season = new Label(composite, SWT.NONE);
		season.setText("시즌");
		season.setBounds(5, 194, 120, 31);

		txt_expcost = new Text(composite, SWT.BORDER);
		txt_expcost.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_expcost.setBounds(135, 363, 250, 23);

		txt_manfnm = new Text(composite, SWT.BORDER);
		txt_manfnm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_manfnm.setBounds(135, 134, 250, 23);

		status = new Label(composite, SWT.NONE);
		status.setText("* 상품상태");
		status.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		status.setBounds(5, 261, 120, 31);

		salesarea = new Label(composite, SWT.NONE);
		salesarea.setText("판매지역");
		salesarea.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		salesarea.setBounds(5, 295, 120, 31);

		shipping = new Label(composite, SWT.NONE);
		shipping.setText("* 배송비구분");
		shipping.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		shipping.setBounds(5, 329, 120, 31);

		shippingfee = new Label(composite, SWT.NONE);
		shippingfee.setText("배송비");
		shippingfee.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		shippingfee.setBounds(5, 363, 120, 23);

		taxbreak = new Label(composite, SWT.NONE);
		taxbreak.setText("세금구분");
		taxbreak.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		taxbreak.setBounds(5, 389, 120, 31);

		cb_orgnm = new Combo(composite, SWT.NONE);
		cb_orgnm.setItems(originArea);
		cb_orgnm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_orgnm.setBounds(135, 160, 150, 31);
		cb_orgnm.select(0);
		cb_orgnm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				settxtorgnm();
			}
		});

		cb_prodstat = new Combo(composite, SWT.NONE);
		cb_prodstat.setItems(new String[] { "상품상태", "대기중", "공급중", "일시중지", "완전품절", "미사용", "삭제" });
		cb_prodstat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_prodstat.setBounds(135, 261, 150, 31);

		cb_locarea = new Combo(composite, SWT.NONE);
		cb_locarea.setItems(new String[] { "판매지역", "전국", "전국(도서제외)", "수도권", "기타", "자료없음" });
		cb_locarea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_locarea.setBounds(135, 295, 150, 31);

		cb_expgubun = new Combo(composite, SWT.NONE);
		cb_expgubun.setItems(new String[] { "배송비구분", "무료", "착불", "선결제", "착불/선결제" });
		cb_expgubun.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_expgubun.setBounds(135, 329, 150, 31);

		cb_taxgubun = new Combo(composite, SWT.NONE);
		cb_taxgubun.setItems(new String[] { "세금구분", "과세", "면세", "자료없음", "비과세" });
		cb_taxgubun.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_taxgubun.setBounds(135, 389, 150, 31);

		number = new Label(composite, SWT.NONE);
		number.setText("기본정보번호");
		number.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		number.setBounds(5, 0, 120, 31);

		cb_number = new Combo(composite, SWT.NONE);
		cb_number.setItems(new String[] {});
		cb_number.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_number.setBounds(135, 0, 250, 28);
		cb_number.setEnabled(false);
		cb_number.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		btn_flagopt = new Button(composite, SWT.CHECK);
		btn_flagopt.setText("옵션없음");
		btn_flagopt.setBounds(506, 484, 90, 23);

		option1 = new Label(composite, SWT.NONE);
		option1.setText("* 옵션등록상태");
		option1.setBounds(394, 484, 100, 23);

		option2 = new Label(composite, SWT.NONE);
		option2.setText("* 재고관리사용여부");
		option2.setBounds(5, 511, 150, 23);

		option3 = new Label(composite, SWT.NONE);
		option3.setText("* 옵션수정여부");
		option3.setBounds(5, 535, 150, 23);

		option4 = new Label(composite, SWT.NONE);
		option4.setText("* 옵션세팅방식");
		option4.setBounds(5, 559, 150, 23);

		btn_flaginvt1 = new Button(composite, SWT.CHECK);
		btn_flaginvt1.setText("사용안함");
		btn_flaginvt1.setBounds(160, 511, 90, 23);
		btn_flaginvt1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCheck1();
			}
		});

		btn_flaginvt2 = new Button(composite, SWT.CHECK);
		btn_flaginvt2.setText("사용함");
		btn_flaginvt2.setBounds(260, 511, 90, 23);
		btn_flaginvt2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCheck2();
			}
		});

		btn_flagoptchg1 = new Button(composite, SWT.CHECK);
		btn_flagoptchg1.setText("수정가능");
		btn_flagoptchg1.setBounds(160, 535, 90, 23);
		btn_flagoptchg1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCheck3();
			}
		});

		btn_flagoptchg2 = new Button(composite, SWT.CHECK);
		btn_flagoptchg2.setText("수정불가능");
		btn_flagoptchg2.setBounds(260, 535, 100, 23);
		btn_flagoptchg2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCheck4();
			}
		});

		btn_flagoptset = new Button(composite, SWT.CHECK);
		btn_flagoptset.setText("멀티옵션");
		btn_flagoptset.setBounds(160, 559, 90, 23);
		btn_flagoptset.setSelection(true);

		label = new Label(composite, SWT.NONE);
		label.setText(" (선택 시 단품 상품으로 등록 / 옵션 상품은 옵션없음의 선택을 해제)");
		label.setBounds(606, 484, 470, 20);

		label_1 = new Label(composite, SWT.NONE);
		label_1.setText(" (재고관리 메뉴 사용시 재고관리사용여부에 사용함을 선택/모음전과 세트 상품 등록시 재고롼리사용여부에 사용안함을 선택)");
		label_1.setBounds(360, 511, 850, 20);

		label_3 = new Label(composite, SWT.NONE);
		label_3.setText(" (일반옵션, 세트Ⅱ, 모음전 동시 등록 가능)");
		label_3.setBounds(260, 559, 500, 20);

		label_2 = new Label(composite, SWT.NONE);
		label_2.setText("기본정보명");
		label_2.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_2.setBounds(5, 32, 120, 31);

		txt_infornm = new Text(composite, SWT.BORDER);
		txt_infornm.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_infornm.setBounds(135, 32, 250, 31);

		btn_insert = new Button(composite, SWT.NONE);
		btn_insert.setText("신규");
		btn_insert.setBounds(1059, 537, 100, 25);
		btn_insert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getInsert();
			}
		});

		btn_copy = new Button(composite, SWT.NONE);
		btn_copy.setText("복사");
		btn_copy.setBounds(1165, 537, 100, 25);

		txt_orgnm = new Text(composite, SWT.BORDER);
		txt_orgnm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_orgnm.setBounds(290, 160, 150, 28);

		label_4 = new Label(composite, SWT.NONE);
		label_4.setText("옵션제목");
		label_4.setBounds(5, 484, 120, 26);

		txt_optnm1 = new Text(composite, SWT.BORDER);
		txt_optnm1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_optnm1.setBounds(135, 484, 120, 26);
		txt_optnm1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				getoption();
			}
		});

		txt_optnm2 = new Text(composite, SWT.BORDER);
		txt_optnm2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_optnm2.setBounds(258, 484, 120, 26);

		lb_mfgubun = new Label(composite, SWT.NONE);
		lb_mfgubun.setText("남녀구분");
		lb_mfgubun.setBounds(5, 227, 120, 31);

		cb_mfgubun = new Combo(composite, SWT.NONE);
		cb_mfgubun.setItems(new String[] { "남녀구분", "남성용", "여성용", "공용", "자료없음", "주니어", "남아용", "여아용" });
		cb_mfgubun.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_mfgubun.setBounds(135, 228, 150, 28);

		label_5 = new Label(composite, SWT.NONE);
		label_5.setText("공급업체");
		label_5.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_5.setBounds(5, 426, 120, 26);

		label_6 = new Label(composite, SWT.NONE);
		label_6.setText("사업자번호");
		label_6.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_6.setBounds(5, 455, 120, 26);

		txt_supplier = new Text(composite, SWT.BORDER);
		txt_supplier.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_supplier.setBounds(135, 422, 250, 26);

		txt_businessno = new Text(composite, SWT.BORDER);
		txt_businessno.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_businessno.setBounds(135, 453, 250, 26);

		label_7 = new Label(composite, SWT.NONE);
		label_7.setText("(사업자번호는 숫자로만 입력하여 주시기 바랍니다.)");
		label_7.setBounds(400, 456, 470, 20);
		new Label(container, SWT.NONE);
		btn_copy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCopyNinsert();
			}

		});

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		getSelect();

		return container;
	}

	private void settxtorgnm() {
		String orgnm = cb_orgnm.getText();
		txt_orgnm.setText(orgnm);
	}

	private void getSelect() {
		try {
			ProductIforDao dao = new ProductIforDao();
			String infornum = infornumber;
			int idx = infornum.indexOf("_");
			String seq = infornum.substring(0, idx);
			List<String> list = dao.getOpen(seq);
			if (list != null) {
				if (list.get(0).length() > 0) {
					cb_number.setItems(String.valueOf(list.get(0)));
					cb_number.select(0);
				}
				txt_infornm.setText(list.get(1));
				txt_brandnm.setText(list.get(2));
				cb_prodgubun.select(Integer.parseInt(list.get(3)));
				txt_manfnm.setText(list.get(4));
				cb_orgnm.select(Integer.parseInt(list.get(5)));
				cb_season.select(Integer.parseInt(list.get(6)));
				cb_mfgubun.select(Integer.parseInt(list.get(7)));
				cb_prodstat.select(Integer.parseInt(list.get(8)));
				cb_locarea.select(Integer.parseInt(list.get(9)));
				cb_expgubun.select(Integer.parseInt(list.get(10)));
				txt_expcost.setText(list.get(11));
				cb_taxgubun.select(Integer.parseInt(list.get(12)));
				if (list.get(13).equals("1")) {
					btn_flagopt.setSelection(true);
				} else {
					if (list.get(13).equals("0")) {
						btn_flagopt.setSelection(false);
						txt_optnm1.setText("");
						txt_optnm2.setText("");
					} else {
						btn_flagopt.setSelection(true);
						txt_optnm1.setText("단품");
						txt_optnm2.setText("");
					}
				}
				txt_optnm1.setText(list.get(14));
				txt_optnm2.setText(list.get(15));
				if (list.get(16).equals("1")) {
					btn_flaginvt1.setSelection(true);
					btn_flaginvt2.setSelection(false);
				} else if (list.get(16).equals("2")) {
					btn_flaginvt1.setSelection(false);
					btn_flaginvt2.setSelection(true);
				} else {
					btn_flaginvt1.setSelection(false);
					btn_flaginvt2.setSelection(false);
				}
				if (list.get(17).equals("1")) {
					btn_flagoptchg1.setSelection(true);
					btn_flagoptchg2.setSelection(false);
				} else if (list.get(17).equals("2")) {
					btn_flagoptchg1.setSelection(false);
					btn_flagoptchg2.setSelection(true);
				} else {
					btn_flagoptchg1.setSelection(false);
					btn_flagoptchg2.setSelection(false);
				}
				if (list.get(18).equals("1")) {
					btn_flagoptset.setSelection(true);
				} else {
					btn_flagoptset.setSelection(false);
				}
				txt_orgnm.setText(list.get(19));

				txt_supplier.setText(list.get(20));
				txt_businessno.setText(list.get(21));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 옵션있음없음선택시
	private void getoption() {
		String optnm1 = txt_optnm1.getText();
		if (optnm1.equals("단품")) {
			btn_flagopt.setSelection(true);
		} else {
			btn_flagopt.setSelection(false);
		}
	}

	// 옵션상태체크
	private void getCheck1() {
		if (btn_flaginvt1.getSelection() == false) {
			btn_flaginvt2.setSelection(true);
		} else {
			btn_flaginvt2.setSelection(false);
		}

	}

	// 옵션상태체크
	private void getCheck2() {
		if (btn_flaginvt2.getSelection() == false) {
			btn_flaginvt1.setSelection(true);
		} else {
			btn_flaginvt1.setSelection(false);
		}
	}

	// 옵션상태체크
	private void getCheck3() {
		if (btn_flagoptchg1.getSelection() == false) {
			btn_flagoptchg2.setSelection(true);
		} else {
			btn_flagoptchg2.setSelection(false);
		}

	}

	// 옵션상태체크
	private void getCheck4() {
		if (btn_flagoptchg2.getSelection() == false) {
			btn_flagoptchg1.setSelection(true);
		} else {
			btn_flagoptchg1.setSelection(false);
		}

	}

	// 신규버튼 클릭시
	private void getInsert() {
		try {
			ProductBasicDetail2 d = new ProductBasicDetail2(this.getShell(), this, list_opt);
			super.okPressed();
			d.open();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 카피버튼누를시
	private void getCopyNinsert() {
		// 등록하기
		ProductIforDao dao = new ProductIforDao();
		try {
			int infornum = dao.getInfornum();
			infornum = infornum + 1;
			String seq = String.valueOf(infornum);
			String infornm = txt_infornm.getText();
			String brandnm = txt_brandnm.getText();
			String prodgubun = String.valueOf(cb_prodgubun.getSelectionIndex());
			String manfnm = txt_manfnm.getText();
			String orgnm = String.valueOf(cb_orgnm.getSelectionIndex());
			String season = String.valueOf(cb_season.getSelectionIndex());
			String mfgubun = String.valueOf(cb_mfgubun.getSelectionIndex());
			String prodstat = String.valueOf(cb_prodstat.getSelectionIndex());
			String locarea = String.valueOf(cb_locarea.getSelectionIndex());
			String expgubun = String.valueOf(cb_expgubun.getSelectionIndex());
			String expcost = txt_expcost.getText();
			String taxgubun = String.valueOf(cb_taxgubun.getSelectionIndex());
			String flagopt = "";
			String optnm1 = txt_optnm1.getText();
			String optnm2 = txt_optnm2.getText();
			String supplier = txt_supplier.getText();
			String businessno = txt_businessno.getText();
			if (btn_flagopt.getSelection()) {
				flagopt = "1";
			} else {
				flagopt = "0";
			}
			String flaginvt = "";
			if (btn_flaginvt1.getSelection() == true) {
				flaginvt = "1";
			} else if (btn_flaginvt2.getSelection() == true) {
				flaginvt = "2";
			} else {
				flaginvt = "0";
			}
			String flagoptchg = "";
			if (btn_flagoptchg1.getSelection() == true) {
				flagoptchg = "1";
			} else if (btn_flagoptchg2.getSelection() == true) {
				flagoptchg = "2";
			} else {
				flagoptchg = "0";
			}
			String flagoptset = "";
			if (btn_flagoptset.getSelection()) {
				flagoptset = "1";
			} else {
				flagoptset = "0";
			}
			String orgname = txt_orgnm.getText();
			int num = dao.getInfornum();
			dao.insertProdIFM(num + 1, infornm, brandnm, prodgubun, manfnm, orgnm, season, mfgubun, prodstat, locarea,
					expgubun, expcost, taxgubun, flagopt, optnm1, optnm2, flaginvt, flagoptchg, flagoptset, orgname,
					supplier, businessno);
			List<String> list = dao.getOpen(seq);
			if (list != null) {
				cb_number.setItems(String.valueOf(list.get(0)));
				cb_number.select(0);
				txt_infornm.setText(list.get(1));
				txt_brandnm.setText(list.get(2));
				cb_prodgubun.select(Integer.parseInt(list.get(3)));
				txt_manfnm.setText(list.get(4));
				cb_orgnm.select(Integer.parseInt(list.get(5)));
				cb_season.select(Integer.parseInt(list.get(6)));
				cb_mfgubun.select(Integer.parseInt(list.get(7)));
				cb_prodstat.select(Integer.parseInt(list.get(8)));
				cb_locarea.select(Integer.parseInt(list.get(9)));
				cb_expgubun.select(Integer.parseInt(list.get(10)));
				txt_expcost.setText(list.get(11));
				cb_taxgubun.select(Integer.parseInt(list.get(12)));
				if (list.get(13).equals("1")) {
					btn_flagopt.setSelection(true);
				} else {
					btn_flagopt.setSelection(false);
				}
				txt_optnm1.setText(list.get(14));
				txt_optnm2.setText(list.get(15));
				if (list.get(16).equals("1")) {
					btn_flaginvt1.setSelection(true);
					btn_flaginvt2.setSelection(false);
				} else if (list.get(16).equals("2")) {
					btn_flaginvt1.setSelection(false);
					btn_flaginvt2.setSelection(true);
				} else {
					btn_flaginvt1.setSelection(false);
					btn_flaginvt2.setSelection(false);
				}
				if (list.get(17).equals("1")) {
					btn_flagoptchg1.setSelection(true);
					btn_flagoptchg2.setSelection(false);
				} else if (list.get(17).equals("2")) {
					btn_flagoptchg1.setSelection(false);
					btn_flagoptchg2.setSelection(true);
				} else {
					btn_flagoptchg1.setSelection(false);
					btn_flagoptchg2.setSelection(false);
				}
				if (list.get(18).equals("1")) {
					btn_flagoptset.setSelection(true);
				} else {
					btn_flagoptset.setSelection(false);
				}
				txt_orgnm.setText(list.get(19));
				txt_supplier.setText(list.get(20));
				txt_businessno.setText(list.get(21));

			} else {
				MessageDialog.openInformation(getShell(), TITLE, "등록된 글이 없습니다");
				cb_number.setFocus();
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void okPressed() {
		//
		if (cb_prodgubun.getText().equals("상품구분")) {
			MessageDialog.openInformation(getShell(), TITLE, "상품구분을 선택하세요");
			cb_prodgubun.setFocus();
			return;
		}
		if (txt_manfnm.getText().trim().length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "제조사를 작성하세요");
			txt_manfnm.setFocus();
			return;
		}
		if (cb_orgnm.getText().equals("원산지")) {
			MessageDialog.openInformation(getShell(), TITLE, "원산지를 선택하세요");
			cb_orgnm.setFocus();
			return;
		}
		if (cb_prodstat.getText().equals("상품상태")) {
			MessageDialog.openInformation(getShell(), TITLE, "상품상태를 선택하세요");
			cb_prodstat.setFocus();
			return;
		}
		if (cb_expgubun.getText().equals("배송지구분")) {
			MessageDialog.openInformation(getShell(), TITLE, "배송지구분을 선택하세요");
			cb_expgubun.setFocus();
			return;
		}
		if (btn_flaginvt1.getSelection() == false && btn_flaginvt2.getSelection() == false) {
			MessageDialog.openInformation(getShell(), TITLE, "재고관리사용여부를 체크하세요");
			btn_flaginvt1.setFocus();
			return;
		}
		if (btn_flagoptchg1.getSelection() == false && btn_flagoptchg2.getSelection() == false) {
			MessageDialog.openInformation(getShell(), TITLE, "옵션수정여부를 체크하세요");
			btn_flagoptchg1.setFocus();
			return;
		}
		if (txt_optnm1.getText().length() == 0 && txt_optnm2.getText().length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "옵션제목에 1개이상 작성하세요");
			btn_flagoptchg1.setFocus();
			return;
		}

		// 등록하기
		String seq = cb_number.getText();
		String infonm = txt_infornm.getText();
		String brandnm = txt_brandnm.getText();
		String prodgubun = String.valueOf(cb_prodgubun.getSelectionIndex());
		String manfnm = txt_manfnm.getText();
		String orgnm = String.valueOf(cb_orgnm.getSelectionIndex());
		String season = String.valueOf(cb_season.getSelectionIndex());
		String mfgubun = String.valueOf(cb_mfgubun.getSelectionIndex());
		String prodstat = String.valueOf(cb_prodstat.getSelectionIndex());
		String locarea = String.valueOf(cb_locarea.getSelectionIndex());
		String expgubun = String.valueOf(cb_expgubun.getSelectionIndex());
		String expcost = txt_expcost.getText();
		String taxgubun = String.valueOf(cb_taxgubun.getSelectionIndex());
		String flagopt = "";
		String optnm1 = txt_optnm1.getText();
		String optnm2 = txt_optnm2.getText();
		String supplier = txt_supplier.getText();
		String businessno = txt_businessno.getText();
		if (btn_flagopt.getSelection()) {
			flagopt = "1";
		} else {
			flagopt = "0";
		}
		String flaginvt = "";
		if (btn_flaginvt1.getSelection() == true) {
			flaginvt = "1";
		} else if (btn_flaginvt2.getSelection() == true) {
			flaginvt = "2";
		} else {
			flaginvt = "0";
		}
		String flagoptchg = "";
		if (btn_flagoptchg1.getSelection() == true) {
			flagoptchg = "1";
		} else if (btn_flagoptchg2.getSelection() == true) {
			flagoptchg = "2";
		} else {
			flagoptchg = "0";
		}
		String flagoptset = "";
		if (btn_flagoptset.getSelection()) {
			flagoptset = "1";
		} else {
			flagoptset = "0";
		}
		String orgname = txt_orgnm.getText();
		ProductIforDao dao = new ProductIforDao();
		try {
			dao.updateProdIFM(seq, infonm, brandnm, prodgubun, manfnm, orgnm, season, mfgubun, prodstat, locarea,
					expgubun, expcost, taxgubun, flagopt, optnm1, optnm2, flaginvt, flagoptchg, flagoptset, orgname,
					supplier, businessno);
			MessageDialog.openInformation(getShell(), TITLE, "상품기본정보를 수정하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.okPressed();

	}/////

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
		return new Point(1300, 700);
	}
}////
