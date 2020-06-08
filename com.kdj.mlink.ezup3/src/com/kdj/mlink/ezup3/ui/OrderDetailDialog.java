package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.common.YDMACommonCode;
import com.kdj.mlink.ezup3.data.dao.OrderDao;
//import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
//import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.kdj.mlink.ezup3.data.dao.VProductsDao;

public class OrderDetailDialog extends CommandDialog {

	String TITLE = "�ֹ���������";

	OrderListUploadManager opener_upload;
	OrderListManager opener_manage;
	List<String> order;
	String[] keys; // [orddt][ordseq]

	private Text text_seq;
//	private Text text_rorddt;
	private Text text_rcvnam;
	private Text text_pstno;
	private Text text_addr;
	private Text text_clpno;
	private Text text_telno;
	private Text text_qty;
	private Text text_shpfee;
	private Text text_credit;
	private Text text_optdesc;
	private Text text_prodcd;
	private Text text_prodnm;
	private Text text_prodesc;
	private Text text_messge;
	private Text text_pkgclss;
	private Text text_shipcls;
	private Text text_sabordno;
	private Text text_shopid;
	private Text text_ordnm;
	private Text text_etcmsg;
	private Text text_mallcd;
	private Text text_ordmat;

	DateTime dateTime_day;
	DateTime dateTime_time;

	/**
	 * @wbp.parser.constructor
	 */
	public OrderDetailDialog(Shell parentShell, List<String> order, OrderListUploadManager opener) {
		super(parentShell);
		this.opener_upload = opener;
		this.order = order;
	}

	public OrderDetailDialog(Shell parentShell, String[] keys, List<String> order, OrderListManager opener) {
		super(parentShell);
		this.opener_manage = opener;
		this.order = order;
		this.keys = keys;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("No.");

		text_seq = new Text(container, SWT.BORDER);
		GridData gd_text_seq = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_seq.widthHint = 70;
		text_seq.setLayoutData(gd_text_seq);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("�ֹ��Ͻ�");

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		dateTime_day = new DateTime(composite, SWT.BORDER);
		dateTime_time = new DateTime(composite, SWT.BORDER | SWT.TIME | SWT.SHORT);

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("*�����θ�");

		text_rcvnam = new Text(container, SWT.BORDER);
		GridData gd_text_rcvnam = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_rcvnam.widthHint = 195;
		text_rcvnam.setLayoutData(gd_text_rcvnam);

		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("*�����ȣ");

		Composite composite_2 = new Composite(container, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);

		text_pstno = new Text(composite_2, SWT.BORDER);
		text_pstno.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));
		GridData gd_text_pstno = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_text_pstno.widthHint = 107;
		text_pstno.setLayoutData(gd_text_pstno);

		Button btnpost_search = new Button(composite_2, SWT.NONE);
		btnpost_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openZipcodeListDialog();
			}
		});
		btnpost_search.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		btnpost_search.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		btnpost_search.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));

		Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("*�ּ�");

		text_addr = new Text(container, SWT.BORDER);
		GridData gd_text_addr = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_addr.widthHint = 500;
		text_addr.setLayoutData(gd_text_addr);

		Label lblNewLabel_5 = new Label(container, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("*�ڵ���");

		text_clpno = new Text(container, SWT.BORDER);
		GridData gd_text_clpno = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_clpno.widthHint = 150;
		text_clpno.setLayoutData(gd_text_clpno);

		Label lblNewLabel_6 = new Label(container, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("�Ϲ���ȭ");

		text_telno = new Text(container, SWT.BORDER);
		GridData gd_text_telno = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_telno.widthHint = 150;
		text_telno.setLayoutData(gd_text_telno);

		Label lblNewLabel_7 = new Label(container, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("*����");

		text_qty = new Text(container, SWT.BORDER);
		GridData gd_text_qty = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_qty.widthHint = 100;
		text_qty.setLayoutData(gd_text_qty);

		Label lblNewLabel_8 = new Label(container, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_8.setText("��ۺ�");

		text_shpfee = new Text(container, SWT.BORDER);
		GridData gd_text_shpfee = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_shpfee.widthHint = 100;
		text_shpfee.setLayoutData(gd_text_shpfee);

		Label lblNewLabel_9 = new Label(container, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_9.setText("�ſ�");

		text_credit = new Text(container, SWT.BORDER);
		GridData gd_text_credit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_credit.widthHint = 200;
		text_credit.setLayoutData(gd_text_credit);

		Label lblNewLabel_10 = new Label(container, SWT.NONE);
		lblNewLabel_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_10.setText("�ɼ�");

		text_optdesc = new Text(container, SWT.BORDER);
		GridData gd_text_optdesc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_optdesc.widthHint = 500;
		text_optdesc.setLayoutData(gd_text_optdesc);

		Label lblNewLabel_11 = new Label(container, SWT.NONE);
		lblNewLabel_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_11.setText("*��ǰ�ڵ�");

		Composite composite_1 = new Composite(container, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(4, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);

		text_prodcd = new Text(composite_1, SWT.BORDER);
		text_prodcd.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));
		GridData gd_text_prodcd = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_text_prodcd.widthHint = 107;
		text_prodcd.setLayoutData(gd_text_prodcd);

		Button btn_search = new Button(composite_1, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openProdcutListDialog();
			}
		});
		btn_search.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		btn_search.setImage(ResourceManager.getPluginImage("com.kdj.mlink.ezup3", "icons/search.png"));
		btn_search.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));

		text_prodnm = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_prodnm.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));
		GridData gd_text_prodnm = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_text_prodnm.widthHint = 301;
		text_prodnm.setLayoutData(gd_text_prodnm);

		text_prodesc = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_prodesc.setFont(SWTResourceManager.getFont("���� ���", 10, SWT.NORMAL));
		GridData gd_text_prodesc = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_text_prodesc.widthHint = 86;
		text_prodesc.setLayoutData(gd_text_prodesc);

		Label lblNewLabel_12 = new Label(container, SWT.NONE);
		lblNewLabel_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_12.setText("�޽���");

		text_messge = new Text(container, SWT.BORDER);
		GridData gd_text_messge = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_messge.widthHint = 500;
		text_messge.setLayoutData(gd_text_messge);

		Label lblNewLabel_13 = new Label(container, SWT.NONE);
		lblNewLabel_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_13.setText("���屸��");

		text_pkgclss = new Text(container, SWT.BORDER);
		GridData gd_text_pkgclss = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_pkgclss.widthHint = 100;
		text_pkgclss.setLayoutData(gd_text_pkgclss);

		Label lblNewLabel_14 = new Label(container, SWT.NONE);
		lblNewLabel_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_14.setText("��۱���");

		text_shipcls = new Text(container, SWT.BORDER);
		GridData gd_text_shipcls = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_shipcls.widthHint = 100;
		text_shipcls.setLayoutData(gd_text_shipcls);

		Label lblNewLabel_15 = new Label(container, SWT.NONE);
		lblNewLabel_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_15.setText("�����ֹ���ȣ");

		text_sabordno = new Text(container, SWT.BORDER);
		GridData gd_text_sabordno = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_sabordno.widthHint = 150;
		text_sabordno.setLayoutData(gd_text_sabordno);

		Label lblNewLabel_16 = new Label(container, SWT.NONE);
		lblNewLabel_16.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_16.setText("���θ����̵�");

		text_shopid = new Text(container, SWT.BORDER);
		GridData gd_text_shopid = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_shopid.widthHint = 350;
		text_shopid.setLayoutData(gd_text_shopid);

		Label lblNewLabel_17 = new Label(container, SWT.NONE);
		lblNewLabel_17.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_17.setText("     �ֹ��ڸ�(������)");

		text_ordnm = new Text(container, SWT.BORDER);
		GridData gd_text_ordnm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_ordnm.widthHint = 150;
		text_ordnm.setLayoutData(gd_text_ordnm);

		Label lblNewLabel_18 = new Label(container, SWT.NONE);
		lblNewLabel_18.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_18.setText("��Ÿ�޽���");

		text_etcmsg = new Text(container, SWT.BORDER);
		GridData gd_text_etcmsg = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_etcmsg.widthHint = 400;
		text_etcmsg.setLayoutData(gd_text_etcmsg);

		Label lblNewLabel_19 = new Label(container, SWT.NONE);
		lblNewLabel_19.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_19.setText("�ڻ���ڵ�");

		text_mallcd = new Text(container, SWT.BORDER);
		GridData gd_text_mallcd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_mallcd.widthHint = 300;
		text_mallcd.setLayoutData(gd_text_mallcd);

		Label lblNewLabel_20 = new Label(container, SWT.NONE);
		lblNewLabel_20.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_20.setText("*�ֹ��հ�");

		text_ordmat = new Text(container, SWT.BORDER);
		GridData gd_text_ordmat = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_ordmat.widthHint = 100;
		text_ordmat.setLayoutData(gd_text_ordmat);

		if (order != null) {
			// --- ����
			setOrderInfo(order);

		} else {
			// �ű� �ֹ� �߰�
			text_seq.setEditable(false);

			// List<List<String>> contents =
			// (List<List<String>>)opener_manage.tableViewer.getInput();

			text_rcvnam.setFocus();
		}

		return container;
	}

	void setRorddt(String orddtStr) {
		StringTokenizer token = new StringTokenizer(orddtStr);
		String dateStr = token.nextToken();
		String timeStr = token.nextToken();

		StringTokenizer token_day = new StringTokenizer(dateStr, "-");
		String yearStr = token_day.nextToken();
		String monthStr = token_day.nextToken();
		String dayStr = token_day.nextToken();

		dateTime_day.setYear(Integer.parseInt(yearStr));
		dateTime_day.setMonth(Integer.parseInt(monthStr) - 1);
		dateTime_day.setDay(Integer.parseInt(dayStr));

		StringTokenizer token_time = new StringTokenizer(timeStr, ":");
		String hourStr = token_time.nextToken();
		String minutehStr = token_time.nextToken();

		dateTime_time.setHours(Integer.parseInt(hourStr));
		dateTime_time.setMinutes(Integer.parseInt(minutehStr));
	}

	void setOrderInfo(List<String> order) {

		int i = 0; // order�� �ε���
		text_seq.setText(order.get(i++));

		String orddtStr = order.get(i++);// 2019-01-18 10:34 , 20190217155600

		if (this.opener_upload != null) { // �ֹ����ε��� ���
			if (orddtStr != null) {
				this.setRorddt(orddtStr);
			}

		} else if (this.opener_manage != null) { // �ֹ������� ���

			if (order.get(21).equals("Y")) {// ��ī��Ʈ ������

				// text_rorddt.setText(orddtStr);
			} else { // ��ī��Ʈ ������
				this.setRorddt(orddtStr);
			}
		}

		text_rcvnam.setText(order.get(i++));
		text_pstno.setText(order.get(i++));
		text_addr.setText(order.get(i++));
		text_clpno.setText(order.get(i++));
		text_telno.setText(order.get(i++));
		text_qty.setText(order.get(i++));
		text_shpfee.setText(order.get(i++));
		text_credit.setText(order.get(i++));
		text_optdesc.setText(order.get(i++));
		text_prodcd.setText(order.get(i++));

		try {
			VProductsDao dao = new VProductsDao();
			List<String> list = dao.getVProdcutInfoByProdcd(order.get(i - 1));
			if (list != null) {
				if (list.size() > 0) {
					text_prodnm.setText(list.get(1));
					text_prodesc.setText(list.get(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

		text_messge.setText(order.get(i++));
		text_pkgclss.setText(order.get(i++));
		text_shipcls.setText(order.get(i++));
		text_sabordno.setText(order.get(i++));
		text_shopid.setText(order.get(i++));
		text_ordnm.setText(order.get(i++));
		text_etcmsg.setText(order.get(i++));
		text_mallcd.setText(order.get(i++));
		text_ordmat.setText(order.get(i++));

		if (this.opener_upload != null) {
			// -- �ֹ� ���ε� ���������� ���½ô� ��� �����Ұ��ϰ� ��
			text_seq.setEditable(false);
			text_rcvnam.setEditable(false);
			text_pstno.setEditable(false);
//			text_rorddt.setEditable(false);

			dateTime_time.setEnabled(false);
			dateTime_day.setEnabled(false);

			text_addr.setEditable(false);
			text_clpno.setEditable(false);
			text_telno.setEditable(false);
			text_qty.setEditable(false);
			text_shpfee.setEditable(false);
			text_credit.setEditable(false);
			text_optdesc.setEditable(false);
			text_messge.setEditable(false);
			text_pkgclss.setEditable(false);
			text_shipcls.setEditable(false);
			text_sabordno.setEditable(false);
			text_shopid.setEditable(false);
			text_ordnm.setEditable(false);
			text_etcmsg.setEditable(false);
			text_mallcd.setEditable(false);
			text_ordmat.setEditable(false);

			text_prodcd.setEditable(true); // ��ǰ�ڵ常 ��������
			text_prodcd.setFocus();

		} else if (this.opener_manage != null) {
			// �ֹ������������� �����Ѱ��
			if (order.get(order.size() - 1).equals("Y")) {
				// ��ī��Ʈ�� ���۵� �ֹ� �̸�..��� �׸� �����Ұ�
				text_seq.setEditable(false);
				text_rcvnam.setEditable(false);
				text_pstno.setEditable(false);
				// text_rorddt.setEditable(false);

				dateTime_time.setEnabled(false);
				dateTime_day.setEnabled(false);

				text_addr.setEditable(false);
				text_clpno.setEditable(false);
				text_telno.setEditable(false);
				text_qty.setEditable(false);
				text_shpfee.setEditable(false);
				text_credit.setEditable(false);
				text_prodcd.setEditable(false);
				text_optdesc.setEditable(false);
				text_messge.setEditable(false);
				text_pkgclss.setEditable(false);
				text_shipcls.setEditable(false);
				text_sabordno.setEditable(false);
				text_shopid.setEditable(false);
				text_ordnm.setEditable(false);
				text_etcmsg.setEditable(false);
				text_mallcd.setEditable(false);
				text_ordmat.setEditable(false);
			} else {
				// ��ī��Ʈ�� ���۵��� ���� �ֹ����� �̸�
				text_seq.setEditable(false); // �ֹ���ȣ�� Key �����Ұ�
				text_rcvnam.setEditable(true);
				text_pstno.setEditable(true);
//				text_rorddt.setEditable(true);

				dateTime_time.setEnabled(true);
				dateTime_day.setEnabled(true);

				text_addr.setEditable(true);
				text_clpno.setEditable(true);
				text_telno.setEditable(true);
				text_qty.setEditable(true);
				text_shpfee.setEditable(true);
				text_credit.setEditable(true);
				text_optdesc.setEditable(true);
				text_messge.setEditable(true);
				text_pkgclss.setEditable(true);
				text_shipcls.setEditable(true);
				text_sabordno.setEditable(true);
				text_shopid.setEditable(true);
				text_ordnm.setEditable(true);
				text_etcmsg.setEditable(true);
				text_mallcd.setEditable(true);
				text_ordmat.setEditable(true);
				text_prodcd.setEditable(true);
				text_prodcd.setFocus();
			}
		}

	}

	void openProdcutListDialog() {
	}

	public void setProdcdInfo(String prodcd, String prodnm, String prodesc) {
		text_prodcd.setText(prodcd);
		text_prodnm.setText(prodnm);
		text_prodesc.setText(prodesc);
	}

	void openZipcodeListDialog() {
		String postno = text_pstno.getText();
		String addr = text_addr.getText();

	}

	public void setZipcodeInfo(String zipcode, String addr) {
		text_pstno.setText(zipcode);
		text_addr.setText(addr);

	}

	@Override
	protected void okPressed() {

		String rcvnam = text_rcvnam.getText().trim();
		String pstno = text_pstno.getText().trim();
		String addr = text_addr.getText().trim();
		String clpno = text_clpno.getText().trim();
		String telno = text_telno.getText().trim();
		String qty = text_qty.getText().trim();
		String shpfee = text_shpfee.getText().trim();
		String credit = text_credit.getText().trim();
		String optdesc = text_optdesc.getText().trim();
		String prodcd = text_prodcd.getText().trim();
		String messge = text_messge.getText().trim();
		String pkgclss = text_pkgclss.getText().trim();
		String shipcls = text_shipcls.getText().trim();
		String etcmsg = text_etcmsg.getText().trim();
		String mallcd = text_mallcd.getText().trim();
		String ordmat = text_ordmat.getText().trim();
		String sabordno = text_sabordno.getText().trim();
		String shopid = text_shopid.getText().trim();
		String ordnm = text_ordnm.getText().trim();

		try {

			if (rcvnam.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�����θ��� �Է��ϼ���");
				text_rcvnam.setFocus();
				return;
			}

			if (pstno.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�����ȣ �Է��ϼ���");
				text_pstno.setFocus();
				return;
			}
			if (addr.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�ּҸ� �Է��ϼ���");
				text_addr.setFocus();
				return;
			}
			if (clpno.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�ڵ�����ȣ�� �Է��ϼ���");
				text_clpno.setFocus();
				return;
			}

			if (qty.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� �Է��ϼ���");
				text_qty.setFocus();
				return;
			}

			if (prodcd.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "��ǰ�ڵ带 �Է��ϼ���");
				text_prodcd.setFocus();
				return;
			}

			// ---------- �ڵ� ��ȿ�� üũ...
			if (!YDMACommonCode.getAllProductCode().contains(prodcd)) {
				text_prodcd.setFocus();
				MessageDialog.openInformation(getShell(), TITLE, "��ϵ��� ���� ��ǰ�ڵ��Դϴ�.");
				return;
			}
			if (!YDMACommonCode.getAllRackProductCode().contains(prodcd)) {
				MessageDialog.openInformation(getShell(), TITLE, "â������� ��ϵ��� ���� ��ǰ�ڵ��Դϴ�.");
				text_prodcd.setFocus();
				return;
			}
			if (!YDMACommonCode.getAllExpProductCode().contains(prodcd)) {
				MessageDialog.openInformation(getShell(), TITLE, "��۰����� ��ϵ��� ���� ��ǰ�ڵ��Դϴ�.");
				text_prodcd.setFocus();
				return;
			}
			if (!YDMACommonCode.getAllProductCodeFoDelyn().contains(prodcd)) {
				MessageDialog.openInformation(getShell(), TITLE, "������ ��ǰ�ڵ��Դϴ�.");
				text_prodcd.setFocus();
				return;
			}

//			if(shipcls.length()==0) {
//				MessageDialog.openInformation(getShell(), TITLE, "��۱����� �Է��ϼ���");
//				text_shipcls.setFocus();
//				return;
//			}
//			if(ordnm.length()==0) {
//				MessageDialog.openInformation(getShell(), TITLE, "�ֹ��ڸ��� �Է��ϼ���");
//				text_ordnm.setFocus();
//				return;
//			}

			if (ordmat.length() == 0) {
				MessageDialog.openInformation(getShell(), TITLE, "�ֹ��հ踦 �Է��ϼ���");
				text_ordmat.setFocus();
				return;
			}

			int month = dateTime_day.getMonth() + 1;
			String monthStr = Integer.toString(month);
			if (month < 10) {
				monthStr = "0" + monthStr;
			}

			int day = dateTime_day.getDay();
			String dayStr = Integer.toString(day);
			if (day < 10) {
				dayStr = "0" + dayStr;
			}

			int hour = dateTime_time.getHours();
			String hourStr = Integer.toString(hour);
			if (hour < 10) {
				hourStr = "0" + hourStr;
			}
			int min = dateTime_time.getMinutes();
			String minStr = Integer.toString(min);
			if (min < 10) {
				minStr = "0" + minStr;
			}

			String rorddt_day = dateTime_day.getYear() + "-" + monthStr + "-" + dayStr;
			// rorddt_day �� ���ε� ���� YDMATimeUtil.getOrddtDate(dateTime_day) �� ������ �ٸ���.
			String rorddt_time = hourStr + ":" + minStr; // +":"+secStr;
			String rorddtStr = rorddt_day + " " + rorddt_time;

			// �ֹ������� ������ orddt, ordseq, seq �� key�� �Ѵ�.

			if (this.order != null) {// �����϶�

				if (this.opener_manage != null) { // �ֹ��������� �϶�

					if (!order.get(order.size() - 1).equals("Y")) {
						// Y(��ī��Ʈ ������ ���), "N" �̳� NULL(��ī��Ʈ ���� ��)
						// ��ǰ�ڵ� �������������� ����� ������-�ù����� ���� üũ���� �˸�.

						List<String> updatedOrder = new ArrayList<>();
						updatedOrder.add(rorddtStr);
						updatedOrder.add(rcvnam);
						updatedOrder.add(pstno);
						updatedOrder.add(addr);
						updatedOrder.add(clpno);
						updatedOrder.add(telno);
						updatedOrder.add(qty);
						updatedOrder.add(shpfee);
						updatedOrder.add(credit);
						updatedOrder.add(optdesc);
						updatedOrder.add(prodcd);
						updatedOrder.add(messge);
						updatedOrder.add(pkgclss);
						updatedOrder.add(shipcls);
						updatedOrder.add(sabordno);
						updatedOrder.add(shopid);
						updatedOrder.add(ordnm);
						updatedOrder.add(etcmsg);
						updatedOrder.add(mallcd);
						updatedOrder.add(ordmat);

						String seqStr = order.get(0);

						OrderDao dao = new OrderDao();
						updatedOrder = dao.updateOrderDetail(keys[0], keys[1], Integer.parseInt(seqStr), updatedOrder);

						int selectionIdx = opener_manage.table.getSelectionIndex();
						((List<List<String>>) opener_manage.tableViewer.getInput()).remove(selectionIdx);
						((List<List<String>>) opener_manage.tableViewer.getInput()).add(selectionIdx, updatedOrder);

						opener_manage.tableViewer.refresh();
						opener_manage.table.setSelection(opener_manage.selectedIdx);

						MessageDialog.openInformation(getShell(), TITLE, "[�ֹ�����]�ֹ������� �����Ͽ����ϴ�.");
						super.okPressed();
					}

				} else if (this.opener_upload != null) { // �ֹ����ε忡�� ������
					// ��ǰ�ڵ常 ���� ����..

					order.set(11, prodcd);
					order.set(order.size() - 1, "T");
					// opener_upload.tableViewer.refresh(); //ȭ�������� ���ø��� ��ư������ �ϰ� ó����

					MessageDialog.openInformation(getShell(), TITLE, "[�ֹ����ε�]�ֹ������� �����Ǿ����ϴ�.");
					super.okPressed();

				}

			} else { // �ֹ��������� �ű� �ֹ� �����
				// �ű������� ����� ������-�ù����� ���� üũ���� �˸�.

				List<String> insertedOrder = new ArrayList();
				insertedOrder.add(rorddtStr);
				insertedOrder.add(rcvnam);
				insertedOrder.add(pstno);
				insertedOrder.add(addr);
				insertedOrder.add(clpno);
				insertedOrder.add(telno);
				insertedOrder.add(qty);
				insertedOrder.add(shpfee);
				insertedOrder.add(credit);
				insertedOrder.add(optdesc);
				insertedOrder.add(prodcd);
				insertedOrder.add(messge);
				insertedOrder.add(pkgclss);
				insertedOrder.add(shipcls);
				insertedOrder.add(sabordno);
				insertedOrder.add(shopid);
				insertedOrder.add(ordnm);
				insertedOrder.add(etcmsg);
				insertedOrder.add(mallcd);
				insertedOrder.add(ordmat);
				// insertedNewOrder.add("T");

				boolean flag = MessageDialog.openQuestion(getShell(), TITLE,
						keys[0] + ", " + keys[1] + "�� �ֹ������� ���ֹ��� �߰��Ͻðڽ��ϱ�?");
				if (!flag) {
					return;
				}

				OrderDao dao = new OrderDao();
				if (dao.existOrdmst(keys[0], keys[1])) {
					// ORDMST �� �����ϸ� ORDDTL �� �μ�Ʈ ��
					insertedOrder = dao.insertOrddtl(keys[0], keys[1], insertedOrder);

				} else {
					// �μ�Ʈ - ordmst, orddtl
					insertedOrder = dao.insertOrdmst_Orddtl(keys[0], keys[1], insertedOrder);
				}

				List<List<String>> contents = (List<List<String>>) opener_manage.tableViewer.getInput();
				contents.add(0, insertedOrder);

				opener_manage.table.setSelection(-1);
				opener_manage.tableViewer.setInput(null);
				opener_manage.tableViewer.setInput(contents);
				opener_manage.tableViewer.refresh();
				opener_manage.table.setSelection(0);
				MessageDialog.openInformation(getShell(), TITLE, "�ֹ������� �߰� �Ͽ����ϴ�.");
				super.okPressed();

			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}
	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		if (this.order != null) { // �����̸�
			if (order.get(order.size() - 1).equals("Y")) { // ��ī��Ʈ ��� ������ �Ÿ�.
				createButton(parent, IDialogConstants.CANCEL_ID, "�ݱ�", false);
			} else {
				createButton(parent, IDialogConstants.OK_ID, "����", false);
				createButton(parent, IDialogConstants.CANCEL_ID, "�ݱ�", false);
			}
		} else { // �űԸ�
			createButton(parent, IDialogConstants.OK_ID, "����", false);
			createButton(parent, IDialogConstants.CANCEL_ID, "�ݱ�", false);
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(700, 779);
	}
}
