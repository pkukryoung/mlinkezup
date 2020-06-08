package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import com.kdj.mlink.ezup3.data.dao.ProductSubDao;
import com.kdj.mlink.ezup3.data.dao.ProductSubDto;

public class ProductSubDetail extends CommandDialog {

	String TITLE = "���ӻ�ǰ������";
	ProductManager opener;
	ProductSubDto dto;
	String prodcd;
	String prodnm;

	private Text txt_prodcd;
	private Text txt_proddtcd;
	private Text txt_proddtnm;
	private Text txt_specdtdes;
	private Button ck_flagset;
	private Text txt_qtyset;
	private Combo cb_setlevel;
	private Text text_exp;
	private Combo cb_useyn;
	private Combo cb_delyn;
	private Text txt_prodmn;

	/**
	 * @wbp.parser.constructor
	 */
	public ProductSubDetail(Shell parentShell, ProductManager opener, ProductSubDto dto) {
		super(parentShell);
		this.opener = opener;
		this.dto = dto;
	}

	public ProductSubDetail(Shell parentShell, ProductManager opener, String prodcd, String prodnm) {
		super(parentShell);
		this.opener = opener;
		this.prodcd = prodcd;
		this.prodnm = prodnm;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setBounds(0, 0, 71, 20);
		lblNewLabel.setText("��ǰ�ڵ�");

		txt_prodcd = new Text(composite, SWT.BORDER);
		txt_prodcd.setEditable(false);
		txt_prodcd.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.BOLD));
		txt_prodcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_txt_prodcd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_prodcd.widthHint = 200;
		txt_prodcd.setLayoutData(gd_txt_prodcd);

		Label lblNewLabel_10 = new Label(composite, SWT.NONE);
		lblNewLabel_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_10.setText("��ǰ��");

		txt_prodmn = new Text(composite, SWT.BORDER);
		txt_prodmn.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		txt_prodmn.setEditable(false);
		txt_prodmn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("���ӻ�ǰ�ڵ�");

		txt_proddtcd = new Text(composite, SWT.BORDER);
		txt_proddtcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		txt_proddtcd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt_proddtcd.setText(txt_proddtcd.getText().trim().toUpperCase());
			}

			@Override
			public void focusGained(FocusEvent e) {
				txt_proddtcd.getShell().setImeInputMode(SWT.ALPHA | SWT.NONE);
			}
		});

		txt_proddtcd.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.BOLD));
		txt_proddtcd.setTextLimit(20);
		GridData gd_txt_proddtcd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_proddtcd.widthHint = 200;
		txt_proddtcd.setLayoutData(gd_txt_proddtcd);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("���ӻ�ǰ��");

		txt_proddtnm = new Text(composite, SWT.BORDER);
		txt_proddtnm.setTextLimit(100);
		txt_proddtnm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_8 = new Label(composite, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_8.setText("�԰�");

		txt_specdtdes = new Text(composite, SWT.BORDER);
		txt_specdtdes.setTextLimit(100);
		GridData gd_txt_specdtdes = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_specdtdes.widthHint = 400;
		txt_specdtdes.setLayoutData(gd_txt_specdtdes);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("��Ʈ����");

		ck_flagset = new Button(composite, SWT.CHECK);
		ck_flagset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String proddtcd = txt_proddtcd.getText().trim();
				if (!checkPRODDTCD(proddtcd)) {
					ck_flagset.setSelection(false);
					if (proddtcd.length() != 0) {
						MessageDialog.openInformation(getShell(), TITLE, "��Ʈ������ �� ���� ��ǰ�Դϴ�. [" + proddtcd + "]");
					}
					return;
				}

				txt_qtyset.setEnabled(ck_flagset.getSelection());
				cb_setlevel.setEnabled(ck_flagset.getSelection());
			}
		});

		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("��Ʈ����");

		txt_qtyset = new Text(composite, SWT.BORDER);
		txt_qtyset.setEnabled(false);
		GridData gd_txt_qtyset = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt_qtyset.widthHint = 60;
		txt_qtyset.setLayoutData(gd_txt_qtyset);

		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("��Ʈ����");

//		text_levset = new Text(composite, SWT.BORDER);
//		text_levset.setTextLimit(1);
//		GridData gd_text_levset = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
//		gd_text_levset.widthHint = 60;
//		text_levset.setLayoutData(gd_text_levset);
//
//		Label lblNewLabel_10 = new Label(composite, SWT.NONE);
//		lblNewLabel_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblNewLabel_10.setText("New Label");

		cb_setlevel = new Combo(composite, SWT.READ_ONLY);
		cb_setlevel.setEnabled(false);
		cb_setlevel.setItems(new String[] { "1", "2" });
		GridData gd_cb_setlevel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_setlevel.widthHint = 40;
		cb_setlevel.setLayoutData(gd_cb_setlevel);

		Label lblNewLabel_9 = new Label(composite, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText("�ù��");

//		cb_expcd = new Combo(composite, SWT.READ_ONLY);
//		cb_expcd.setTextLimit(20);
//		cb_expcd.setItems(new String[] {"�Ե�"});
//		GridData gd_cb_expcd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_cb_expcd.widthHint = 120;
//		cb_expcd.setLayoutData(gd_cb_expcd);

		text_exp = new Text(composite, SWT.BORDER);
		GridData gd_text_exp = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_exp.widthHint = 120;
		text_exp.setLayoutData(gd_text_exp);

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("ǰ������");

		cb_useyn = new Combo(composite, SWT.READ_ONLY);
		cb_useyn.setItems(new String[] { "N", "Y" });
		GridData gd_cb_useyn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_useyn.widthHint = 40;
		cb_useyn.setLayoutData(gd_cb_useyn);
		cb_useyn.select(0);

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("��������");

		cb_delyn = new Combo(composite, SWT.READ_ONLY);
		cb_delyn.setItems(new String[] { "N", "Y" });
		GridData gd_cb_delyn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cb_delyn.widthHint = 40;
		cb_delyn.setLayoutData(gd_cb_delyn);
		cb_delyn.select(0);

		if (this.dto != null) { // �����ΰ��
			setProductSubeInfo(this.dto);
			// �����ΰ�� roddtcd �� ���� �Ұ�
			txt_proddtcd.setEditable(false);
			txt_proddtnm.setFocus();

			ProductMstDao dao = new ProductMstDao();
			try {
				boolean flag = dao.isExistProduct(dto.getProddtcd());
				if (flag) {
					txt_qtyset.setEnabled(true);
					cb_setlevel.setEnabled(true);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else { // �űԵ���ΰ��
			this.txt_prodcd.setText(this.prodcd);
			this.txt_prodmn.setText(this.prodnm);
			txt_proddtcd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			txt_proddtcd.setFocus();
		}

		return container;
	}

	public void setProductSubeInfo(ProductSubDto dto) {
		txt_prodcd.setText(dto.getProdcd());
		txt_proddtcd.setText(dto.getProddtcd());
		;
		txt_proddtnm.setText(dto.getProddtnm());

		txt_specdtdes.setText(dto.getSpecdtdes());

		ck_flagset.setSelection(dto.getFalgset().equals("1"));

		txt_qtyset.setText(dto.getQtyset());

		if (dto.getLevset().equals("1")) {
			cb_setlevel.select(0);
		} else if (dto.getLevset().equals("2")) {
			cb_setlevel.select(1);
		}

//		String expcd = dto.getExpdtcd();
//		if(expcd.equals("�Ե��ù�")) {
//			cb_expcd.select(0);
//		}else if(expcd.equals("KGB�ù�")){
//			cb_expcd.select(1);
//		}else if(expcd.equals("õĥ�ù�")){
//			cb_expcd.select(2);
//		}else if(expcd.equals("���")){
//			cb_expcd.select(3);
//		}else if(expcd.equals("����")){
//			cb_expcd.select(4);
//		}else {
//			cb_expcd.select(-1);
//		}
		text_exp.setText(dto.getExpdtcd());

		if (dto.getUseyn().equals("Y")) {
			this.cb_useyn.select(1);
		} else {
			this.cb_useyn.select(0);
		}
		if (dto.getDelyn().equals("Y")) {
			this.cb_delyn.select(1);
		} else {
			this.cb_delyn.select(0);
		}

	}

	/*
	 * //proddtcd �� ��ǰ����Ÿ(prodmst) ���̺� �ִ� �� üũ��
	 */
	private boolean checkPRODDTCD(String proddtcd) {
		boolean flag = false;
		try {
			if (proddtcd.length() == 0) {
				ck_flagset.setSelection(false);
				MessageDialog.openInformation(getShell(), TITLE, "���ӻ�ǰ�ڵ带 �Է��ϼ���.");
				return false;
			}

			ProductMstDao dao = new ProductMstDao();
			flag = dao.isExistProduct(proddtcd.toUpperCase());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	protected void okPressed() {

		// TODO -- ��ǰSub ����������
		String prodcd = txt_prodcd.getText().trim();
		String proddtcd = txt_proddtcd.getText().trim();
		String proddtnm = txt_proddtnm.getText().trim();
		String specdtdes = txt_specdtdes.getText().trim();

		String flagset = ck_flagset.getSelection() ? "1" : "0";

		String qtyset = "0";
		if (flagset.equals("1")) {
			qtyset = txt_qtyset.getText().trim();
		}
		String levset = "0";
		if (flagset.equals("1")) {
			levset = cb_setlevel.getText();
		}

		String expdtcd = text_exp.getText().trim(); // cb_expcd.getText();
		String useyn = cb_useyn.getText(); // cb_useyn.getItem(cb_useyn.getSelectionIndex());
		String delyn = cb_delyn.getText(); // cb_delyn.getItem(cb_delyn.getSelectionIndex());

		if (proddtcd.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "���ӻ�ǰ�ڵ带 �Է��ϼ���.");
			txt_proddtcd.setFocus();
			return;
		}
		if (proddtcd.length() < 5) {
			MessageDialog.openInformation(getShell(), TITLE, "���ӻ�ǰ�ڵ��� ���̴� 5���� �̻� �Է��Ͽ��� �մϴ�.");
			txt_proddtcd.setFocus();
			return;
		}
		if (proddtcd.equals(prodcd)) {
			boolean flag = MessageDialog.openQuestion(getShell(), TITLE, "��ǰ�ڵ�� ���ӻ�ǰ�ڵ尡 �����մϴ�.\n������ �ڵ带 ����Ͻðڽ��ϱ�?");
			if (!flag) {
				txt_proddtcd.setFocus();
				return;
			}
		}
		if (flagset.equals("1")) {
			if (qtyset.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "��Ʈ������ �Է��ϼ���.");
				txt_qtyset.setFocus();
				return;
			}
		}

		if (flagset.equals("1")) {
			if (levset.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "��Ʈ���� �Է��ϼ���.");
				cb_setlevel.setFocus();
				return;
			}
		}

		if (!checkPRODDTCD(proddtcd)) {
			if (ck_flagset.getSelection()) {
				MessageDialog.openInformation(getShell(), TITLE, "��Ʈ������ �� ���� ��ǰ�Դϴ�. [" + proddtcd + "]");
				return;
			}
		}

		ProductSubDao dao = new ProductSubDao();

		try {
			if (dto == null) { // �� ���ӻ�ǰ ���

//				if (dao.isExistProduct(proddtcd)) {
//					// �ɼǻ�ǰ�� prodmst �� ��������� ���⸦ üũ�ϸ� �ߺ����� �˼� ����
//					openInfoDialog(TITLE, "�̹� ��ϵ� ��ǰ�ڵ��Դϴ�.");
//					return;
//				}

				if (!flagset.equals("1") && dao.isExistOptProduct(proddtcd)) {
					// ��Ī��ǰ�ڵ� �� ���(��Ʈ��ǰ�ڵ� �ƴ�)�θ� 2�� �ϼ� ����
					openInfoDialog(TITLE, "�̹� ��ϵ� ���ӻ�ǰ�ڵ��Դϴ�.(��Ʈ��ǰ�� �ƴ�, ��Ī�ڵ��� ���� �ϳ��� ���ۻ�ǰ�ڵ尡 ���� ��ǥ��ǰ�ڵ忡 ��� �ɼ� ����)");
					return;
				}
				if (dao.getProdcutSubInfoByMsteAndSubCode(prodcd, proddtcd) != null) {//
					MessageDialog.openInformation(getShell(), TITLE, "������ ���ӻ�ǰ�ڵ尡 �����մϴ�.");
					return;
				}

				int result = dao.insertProdcutDtl(prodcd, proddtcd, proddtnm, specdtdes, flagset, qtyset, levset,
						expdtcd, useyn, delyn);

				List<List<String>> contents = (List<List<String>>) this.opener.tableViewer_sub.getInput();
				if (contents == null) {
					contents = new ArrayList<>();
					this.opener.tableViewer_sub.setInput(contents);
				}

				ProductSubDto dto_db = dao.getProdcutSubInfoByMsteAndSubCode(prodcd, proddtcd);

				List<String> list = new ArrayList<>();
				list.add("" + (contents.size() + 1));
				list.add(dto_db.getProddtcd());
				list.add(dto_db.getProddtnm());
				list.add(dto_db.getSpecdtdes());
				list.add(dto_db.getFalgset());
				list.add(dto_db.getQtyset());
				list.add(dto_db.getLevset());
				list.add(dto_db.getExpdtcd());
				list.add(dto_db.getUseyn());
				list.add(dto_db.getDelyn());
				list.add(dto_db.getInsertdt());
				list.add(dto_db.getInsertid());
				list.add(dto_db.getModifydt());
				list.add(dto_db.getModifyid());
				contents.add(list);

				this.opener.tableViewer_sub.refresh();
				this.opener.tableViewer_sub.getGrid().setSelection(contents.size() - 1);

				MessageDialog.openInformation(getShell(), TITLE, "���ӻ�ǰ�� �߰��Ͽ����ϴ�.");
				super.okPressed();

			} else {
				// TODO -- ���ӻ�ǰ��������
//				if (dao.isExistProduct(proddtcd)) {
//					// �ɼǻ�ǰ�� prodmst �� ��������� ���⸦ üũ�ϸ� �ߺ����� �˼� ����
//					openInfoDialog(TITLE, "�̹� ��ϵ� ��ǰ�ڵ��Դϴ�.");
//					return;
//				} 

				dao.updateProductSub(prodcd, proddtcd, proddtnm, specdtdes, flagset, qtyset, levset, expdtcd, useyn,
						delyn);
				ProductSubDto dto_db = dao.getProdcutSubInfoByMsteAndSubCode(prodcd, proddtcd);

				List<List<String>> contents = (List<List<String>>) this.opener.tableViewer_sub.getInput();
				List<String> list = contents.get(this.opener.tableViewer_sub.getGrid().getSelectionIndex());
				int idx = 0;
				list.set(idx++, list.get(0));
				list.set(idx++, dto_db.getProddtcd());
				list.set(idx++, dto_db.getProddtnm());
				list.set(idx++, dto_db.getSpecdtdes());
				list.set(idx++, dto_db.getFalgset());
				list.set(idx++, dto_db.getQtyset());
				list.set(idx++, dto_db.getLevset());
				list.set(idx++, dto_db.getExpdtcd());
				list.set(idx++, dto_db.getUseyn());
				list.set(idx++, dto_db.getDelyn());
				list.set(idx++, dto_db.getInsertdt());
				list.set(idx++, dto_db.getInsertid());
				list.set(idx++, dto_db.getModifydt());
				list.set(idx++, dto_db.getModifyid());

				this.opener.tableViewer_sub.refresh();

				MessageDialog.openInformation(getShell(), TITLE, "���ӻ�ǰ������ �����Ͽ����ϴ�.");
				super.okPressed();

			}

		} catch (Exception e) {
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "����", false);
		createButton(parent, IDialogConstants.CANCEL_ID, "�ݱ�", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(620, 506);
	}
}
