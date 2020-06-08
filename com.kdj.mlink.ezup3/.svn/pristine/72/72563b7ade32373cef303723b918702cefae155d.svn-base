package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
import com.kdj.mlink.ezup3.data.dao.ProductOptDao;

public class ProdcutOptDetail extends CommandDialog {

	String TITLE = "옵션상품";

	ProductMstDetail opener;
	List<String> opt;

	private Text text_optprodcd;
	private Text text_optprodnm;
	private Text text_optspecdes;
	private Text text_optea;
	private Button ck_optsale;
	private Button ck_optsaleout;
	private Button ck_optnotuse;
	private Text text_safestock;
	private Text text_vertstock;
	private Text text_addamt;
	private Combo cb_delyn;
	List<String> optlist;

	/**
	 * @param optlist
	 * @wbp.parser.constructor
	 */
	public ProdcutOptDetail(Shell parentShell, ProductMstDetail opener, List<String> opt, List<String> optlist) {
		super(parentShell);
		// setShellStyle(SWT.RESIZE | SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.MODELESS
		// |getDefaultOrientation());
		this.opener = opener;
		this.opt = opt;
		this.optlist = optlist;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("*옵션상품코드");

		text_optprodcd = new Text(composite, SWT.BORDER);
		text_optprodcd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_optprodcd.getShell().setImeInputMode(SWT.ALPHA | SWT.NONE);
			}

			@Override
			public void focusLost(FocusEvent e) {
				text_optprodcd.setText(text_optprodcd.getText().trim().toUpperCase());

			}
		});
		text_optprodcd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_optprodnm.setFocus();
				}
			}
		});

		GridData gd_text_optprodcd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_optprodcd.widthHint = 200;
		text_optprodcd.setLayoutData(gd_text_optprodcd);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("*옵션상세명");

		text_optprodnm = new Text(composite, SWT.BORDER);
		text_optprodnm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_optspecdes.setFocus();
				}
			}
		});
		GridData gd_text_optprodnm = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_optprodnm.widthHint = 400;
		text_optprodnm.setLayoutData(gd_text_optprodnm);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("규격");

		text_optspecdes = new Text(composite, SWT.BORDER);
		text_optspecdes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_optea.setFocus();
				}
			}
		});
		GridData gd_text_optspecdes = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_optspecdes.widthHint = 350;
		text_optspecdes.setLayoutData(gd_text_optspecdes);

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("EA");

		text_optea = new Text(composite, SWT.BORDER | SWT.RIGHT);
		text_optea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_safestock.setFocus();
				}
			}
		});
		text_optea.setText("0");
		GridData gd_text_optea = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_optea.widthHint = 40;
		text_optea.setLayoutData(gd_text_optea);

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("판매");

		ck_optsale = new Button(composite, SWT.CHECK);
		ck_optsale.setText("");

		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("품절");

		ck_optsaleout = new Button(composite, SWT.CHECK);
		ck_optsaleout.setText("");

		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("미사용");

		ck_optnotuse = new Button(composite, SWT.CHECK);
		ck_optnotuse.setText("");

		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("안전재고");

		text_safestock = new Text(composite, SWT.BORDER | SWT.RIGHT);
		text_safestock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_vertstock.setFocus();
				}
			}
		});
		text_safestock.setText("0");
		GridData gd_text_safestock = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_safestock.widthHint = 60;
		text_safestock.setLayoutData(gd_text_safestock);

		Label lblNewLabel_8 = new Label(composite, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_8.setText("가상재고");

		text_vertstock = new Text(composite, SWT.BORDER | SWT.RIGHT);
		text_vertstock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					text_addamt.setFocus();
				}
			}
		});
		text_vertstock.setText("0");
		GridData gd_text_vertstock = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_vertstock.widthHint = 60;
		text_vertstock.setLayoutData(gd_text_vertstock);

		Label lblNewLabel_9 = new Label(composite, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText("추가금액");

		text_addamt = new Text(composite, SWT.BORDER | SWT.RIGHT);
		text_addamt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					btn_ok.setFocus();
				}

			}
		});
		text_addamt.setText("0");
		GridData gd_text_addamt = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_addamt.widthHint = 60;
		text_addamt.setLayoutData(gd_text_addamt);

		Label lblNewLabel_11 = new Label(composite, SWT.NONE);
		lblNewLabel_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_11.setText("삭제");

		ComboViewer comboViewer_1 = new ComboViewer(composite, SWT.READ_ONLY);
		cb_delyn = comboViewer_1.getCombo();
		cb_delyn.setItems(new String[] { "N", "Y" });
		GridData gd_cb_delyn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_delyn.widthHint = 30;
		cb_delyn.setLayoutData(gd_cb_delyn);
		cb_delyn.select(0);

		if (opt != null) {
			if (!optlist.get(0).equals("0")) {
				// 복사하기 버튼으로 들어온경우
				this.setOpt(opt);
				MessageDialog.openInformation(getShell(), TITLE, "상품코드 변경시에는 삭제후 다시 진행하여 주시기 바랍니다");
				text_optprodcd.setEditable(true);
			} else {
				// 수정인경우
				this.setOpt(opt);
				MessageDialog.openInformation(getShell(), TITLE, "상품코드 변경시에는 삭제후 다시 진행하여 주시기 바랍니다");
				text_optprodcd.setEditable(false);
				text_optprodcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
			}
		} else {
			// 신규추가하는 경우 입력 편의를 위해 Prodcd 를 optprodcd에 입력해줌.
			text_optprodcd.setText(opener.txt_prodcd.getText().trim().toUpperCase());
			text_optprodcd.setSelection(text_optprodcd.getText().length());
			text_optea.setText(opener.txt_ea.getText().trim());
		}

		text_optprodcd.setFocus();

		return container;
	}

	private void setOpt(List<String> opt) {

		text_optprodcd.setText(opt.get(1));
		text_optprodnm.setText(opt.get(2));
		text_optspecdes.setText(opt.get(3));
		text_optea.setText(opt.get(4));
		ck_optsale.setSelection(opt.get(5).equals("1"));
		ck_optsaleout.setSelection(opt.get(6).equals("1"));
		ck_optnotuse.setSelection(opt.get(7).equals("1"));
		text_safestock.setText(opt.get(8));
		text_vertstock.setText(opt.get(9));
		text_addamt.setText(opt.get(10));
		cb_delyn.select(opt.get(11).equals("N") ? 0 : 1);

	}

	@Override
	protected void okPressed() {

		String optprodcd = text_optprodcd.getText().trim();
		String optprodnm = text_optprodnm.getText().trim();
		String optspecdes = text_optspecdes.getText().trim();
		String optea = text_optea.getText().trim();
		optea = optea.length() == 0 ? "0" : optea;
		String optsale = ck_optsale.getSelection() ? "1" : "0";
		String optsaleout = ck_optsaleout.getSelection() ? "1" : "0";
		String optnotuse = ck_optnotuse.getSelection() ? "1" : "0";
		String optsafestock = text_safestock.getText().trim();
		optsafestock = optsafestock.length() == 0 ? "0" : optsafestock;
		String optvertstock = text_vertstock.getText().trim();
		optvertstock = optvertstock.length() == 0 ? "0" : optvertstock;
		String optaddamt = text_addamt.getText().trim();
		optaddamt = optaddamt.length() == 0 ? "0" : optaddamt;
		String optdelyn = cb_delyn.getItem(cb_delyn.getSelectionIndex());

		if (optprodcd.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "옵션상품코드를 입력하세요");
			text_optprodcd.setFocus();
			return;
		}

		if (optprodcd.length() < 5) {
			MessageDialog.openInformation(getShell(), TITLE, "옵션상품코드의 길이는 5글자 이상 입력하여야 합니다.");
			text_optprodcd.setFocus();
			return;
		}

		if (optprodnm.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "옵션상세명를 입력하세요");
			text_optprodnm.setFocus();
			return;
		}

		try {
			if (this.opt == null) { // 옵션상품 추가인경우
				if (opener.txt_prodcd.getText().trim().toUpperCase().equals(optprodcd)) {
					MessageDialog.openInformation(getShell(), TITLE, "옵션상품의 코드가 상품코드와 동일합니다.");
					text_optprodcd.setFocus();
					return;
				}

				ProductMstDao dao_mst = new ProductMstDao();
				if (dao_mst.isExistProduct(optprodcd)) {
					// 옵션상품도 prodmst 에 저장됨으로 여기를 체크하면 중복여부 알수 있음
					openInfoDialog(TITLE, "이미 등록된 상품코드입니다.");
					return;
				}

				ProductOptDao dao = new ProductOptDao();
				dao.processProductOptInsert(opener.txt_prodcd.getText().trim().toUpperCase(),
						opener.txt_ecountcd.getText().trim().toUpperCase(), optprodcd, optprodnm, optspecdes, optea,
						optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt, optdelyn, false);

				List<List<String>> contents = (List<List<String>>) opener.tableViewer_opt.getInput();
				if (contents == null) {
					contents = new ArrayList<>();
					opener.tableViewer_opt.setInput(contents);
				}

				List<String> opt = dao.getProdcutOptInfo(opener.txt_prodcd.getText().trim().toUpperCase(), optprodcd);

				opt.set(0, String.valueOf(contents.size() + 1)); // 추가되는 opt 의 rowno를 테이블 인덱스는 테이블로갯수+1 로 바꿔준다.

				contents.add(opt);
				opener.tableViewer_opt.setInput(null);
				opener.tableViewer_opt.refresh();
				opener.tableViewer_opt.setInput(contents);
				opener.tableViewer_opt.refresh();
				opener.tableViewer_opt.getGrid().select(contents.size() - 1);

				MessageDialog.openInformation(getShell(), TITLE, "옵션상품을 추가하였습니다.");

				super.okPressed();

			} else { // 옵션상품 수정인 경우
				if (!optlist.get(0).equals("0")) {
					if (opener.txt_prodcd.getText().trim().toUpperCase().equals(optprodcd)) {
						MessageDialog.openInformation(getShell(), TITLE, "옵션상품의 코드가 상품코드와 동일합니다.");
						text_optprodcd.setFocus();
						return;
					}

					ProductMstDao dao_mst = new ProductMstDao();
					if (dao_mst.isExistProduct(optprodcd)) {
						// 옵션상품도 prodmst 에 저장됨으로 여기를 체크하면 중복여부 알수 있음
						openInfoDialog(TITLE, "이미 등록된 상품코드입니다.");
						return;
					}

					ProductOptDao dao = new ProductOptDao();
					dao.processProductOptInsert(opener.txt_prodcd.getText().trim().toUpperCase(),
							opener.txt_ecountcd.getText().trim().toUpperCase(), optprodcd, optprodnm, optspecdes, optea,
							optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt, optdelyn, false);

					List<List<String>> contents = (List<List<String>>) opener.tableViewer_opt.getInput();
					if (contents == null) {
						contents = new ArrayList<>();
						opener.tableViewer_opt.setInput(contents);
					}

					List<String> opt = dao.getProdcutOptInfo(opener.txt_prodcd.getText().trim().toUpperCase(),
							optprodcd);

					opt.set(0, String.valueOf(contents.size() + 1)); // 추가되는 opt 의 rowno를 테이블 인덱스는 테이블로갯수+1 로 바꿔준다.

					contents.add(opt);
					opener.tableViewer_opt.setInput(null);
					opener.tableViewer_opt.refresh();
					opener.tableViewer_opt.setInput(contents);
					opener.tableViewer_opt.refresh();
					opener.tableViewer_opt.getGrid().select(contents.size() - 1);

					MessageDialog.openInformation(getShell(), TITLE, "옵션상품을 추가하였습니다.");

					super.okPressed();
				} else {
					ProductMstDao mdao = new ProductMstDao();
					if (!mdao.isExistProduct(opener.txt_prodcd.getText().trim().toUpperCase())) {
						ProductOptDao dao = new ProductOptDao();
						dao.processProductOptInsert(opener.txt_prodcd.getText().trim().toUpperCase(),
								opener.txt_ecountcd.getText().trim().toUpperCase(), optprodcd, optprodnm, optspecdes,
								optea, optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt, optdelyn,
								true);

						super.okPressed();
					}
					ProductOptDao dao = new ProductOptDao();
					dao.processProductOptUpdate(opener.txt_prodcd.getText().trim().toUpperCase(), optprodcd, optprodnm,
							optspecdes, optea, optsale, optsaleout, optnotuse, optsafestock, optvertstock, optaddamt,
							optdelyn);
					int selecedIdx = opener.tableViewer_opt.getGrid().getSelectionIndex();

					List<String> opt = dao.getProdcutOptInfo(opener.txt_prodcd.getText().trim().toUpperCase(),
							optprodcd);

					List<List<String>> contents = (List<List<String>>) opener.tableViewer_opt.getInput();

					List<String> opt_orign = contents.get(selecedIdx);
					opt.set(0, opt_orign.get(0));

					contents.set(selecedIdx, opt);// contents 의 값을 opt 로 바꿔준다.
					opener.tableViewer_opt.refresh();
					opener.tableViewer_opt.getGrid().select(selecedIdx);

					MessageDialog.openInformation(getShell(), TITLE, "옵션상품을 수정하였습니다.");
					super.okPressed();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), TITLE, e.getMessage());
		}

	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	Button btn_ok = null;
	Button btn_cancel = null;

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		btn_ok = createButton(parent, IDialogConstants.OK_ID, "저장", false);
		btn_cancel = createButton(parent, IDialogConstants.CANCEL_ID, "닫기", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(673, 520);
	}
}
