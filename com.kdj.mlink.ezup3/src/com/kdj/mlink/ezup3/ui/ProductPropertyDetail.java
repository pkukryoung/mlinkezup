package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
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

import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.data.dao.ProductIforDao;

public class ProductPropertyDetail extends CommandDialog {
	ProductMstDetail opener;
	Button btnOk;
	Button btnCancel;
	String TITLE = "»óÇ°¼Ó¼ºÁ¤º¸";

	private Composite composite;
	private Label lb_attrnm1;
	private Label lb_attrnm2;
	private Text txt_attrvl8;
	private Label lb_attrnm3;
	private Text txt_attrvl5;
	private Label lb_attrnm7;
	private Label lb_attrnm8;
	private Label lb_attrnm4;
	private Label lb_attrnm5;
	private Label lb_attrnm6;
	private Label lb_attrnm9;
	private Text txt_attrvl4;
	private Label lb_attrnm10;
	private Text txt_attrvl9;
	private Text txt_attrvl3;
	private Text txt_attrvl6;
	private Text txt_attrvl7;
	private Text txt_attrvl1;
	private Label lb_attrnm11;
	private Label num1;
	private Label num2;
	private Label num3;
	private Label num4;
	private Label num5;
	private Label num6;
	private Label num7;
	private Label num8;
	private Label num9;
	private Label num10;
	private Label num11;
	private Label ification;
	private Text txt_attrvl2;
	private Text txt_attrvl10;
	private Text txt_attrvl11;
	private Label num15;
	private Label lb_attrnm15;
	private Text txt_attrvl15;
	private Text txt_attrvl16;
	private Label lb_attrnm16;
	private Label num16;
	private Label num17;
	private Label num18;
	private Label num19;
	private Label num20;
	private Label num21;
	private Label num22;
	private Label num23;
	private Label num24;
	private Label num25;
	private Label lb_attrnm25;
	private Label lb_attrnm24;
	private Label lb_attrnm23;
	private Label lb_attrnm22;
	private Label lb_attrnm21;
	private Label lb_attrnm20;
	private Label lb_attrnm19;
	private Label lb_attrnm18;
	private Label lb_attrnm17;
	private Text txt_attrvl17;
	private Text txt_attrvl18;
	private Text txt_attrvl19;
	private Text txt_attrvl20;
	private Text txt_attrvl21;
	private Text txt_attrvl22;
	private Text txt_attrvl23;
	private Text txt_attrvl24;
	private Text txt_attrvl25;
	private Combo cb_ification;

	int propertynum;
	private Text txt_infonm;
	private Label label;
	private Text txt_all;
	private Button button;
	private Text txt_attrvl12;
	private Text txt_attrvl13;
	private Text txt_attrvl14;
	private Label lb_attrnm12;
	private Label lb_attrnm13;
	private Label lb_attrnm14;
	private Label num12;
	private Label num13;
	private Label num14;
	private Text txt_attrvl28;
	private Label lb_attrnm28;
	private Label num28;
	private Label num27;
	private Label lb_attrnm27;
	private Text txt_attrvl27;
	private Text txt_attrvl26;
	private Label lb_attrnm26;
	private Label num26;

	public ProductPropertyDetail(Shell parentShell, ProductMstDetail opener, int propertynum) {
		super(parentShell);
		this.opener = opener;
		this.propertynum = propertynum;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(2, false));

		composite = new Composite(container, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 759;
		gd_composite.widthHint = 1133;
		composite.setLayoutData(gd_composite);
		composite.setLayout(null);

		lb_attrnm1 = new Label(composite, SWT.NONE);
		lb_attrnm1.setBounds(25, 126, 120, 40);

		lb_attrnm2 = new Label(composite, SWT.NONE);
		lb_attrnm2.setBounds(25, 171, 120, 40);

		txt_attrvl8 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl8.setBounds(150, 436, 350, 40);

		lb_attrnm3 = new Label(composite, SWT.NONE);
		lb_attrnm3.setBounds(25, 216, 120, 40);

		txt_attrvl5 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl5.setBounds(150, 301, 350, 40);

		lb_attrnm7 = new Label(composite, SWT.NONE);
		lb_attrnm7.setBounds(25, 396, 120, 40);

		lb_attrnm8 = new Label(composite, SWT.NONE);
		lb_attrnm8.setBounds(25, 441, 120, 40);

		lb_attrnm4 = new Label(composite, SWT.NONE);
		lb_attrnm4.setBounds(25, 261, 120, 40);

		lb_attrnm5 = new Label(composite, SWT.NONE);
		lb_attrnm5.setBounds(25, 306, 120, 40);

		lb_attrnm6 = new Label(composite, SWT.NONE);
		lb_attrnm6.setBounds(25, 351, 120, 40);

		lb_attrnm9 = new Label(composite, SWT.NONE);
		lb_attrnm9.setBounds(25, 486, 120, 40);

		txt_attrvl4 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl4.setBounds(150, 256, 350, 40);

		lb_attrnm10 = new Label(composite, SWT.NONE);
		lb_attrnm10.setBounds(25, 531, 120, 40);

		txt_attrvl9 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl9.setBounds(150, 481, 350, 40);

		txt_attrvl3 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl3.setBounds(150, 211, 350, 40);

		txt_attrvl6 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl6.setBounds(150, 346, 350, 40);

		txt_attrvl7 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl7.setBounds(150, 391, 350, 40);

		txt_attrvl1 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl1.setBounds(150, 121, 350, 40);

		lb_attrnm11 = new Label(composite, SWT.NONE);
		lb_attrnm11.setBounds(25, 576, 120, 40);

		num1 = new Label(composite, SWT.NONE);
		num1.setText("1");
		num1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num1.setBounds(5, 121, 20, 40);

		num2 = new Label(composite, SWT.NONE);
		num2.setText("2");
		num2.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num2.setBounds(5, 166, 20, 40);

		num3 = new Label(composite, SWT.NONE);
		num3.setText("3");
		num3.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num3.setBounds(5, 211, 20, 40);

		num4 = new Label(composite, SWT.NONE);
		num4.setText("4");
		num4.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num4.setBounds(5, 256, 20, 40);

		num5 = new Label(composite, SWT.NONE);
		num5.setText("5");
		num5.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num5.setBounds(5, 301, 20, 40);

		num6 = new Label(composite, SWT.NONE);
		num6.setText("6");
		num6.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num6.setBounds(5, 346, 20, 40);

		num7 = new Label(composite, SWT.NONE);
		num7.setText("7");
		num7.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num7.setBounds(5, 391, 20, 40);

		num8 = new Label(composite, SWT.NONE);
		num8.setText("8");
		num8.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num8.setBounds(5, 436, 20, 40);

		num9 = new Label(composite, SWT.NONE);
		num9.setText("9");
		num9.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num9.setBounds(5, 481, 20, 40);

		num10 = new Label(composite, SWT.NONE);
		num10.setText("10");
		num10.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num10.setBounds(5, 526, 20, 40);

		num11 = new Label(composite, SWT.NONE);
		num11.setText("11");
		num11.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num11.setBounds(5, 571, 20, 40);

		ification = new Label(composite, SWT.NONE);
		ification.setAlignment(SWT.CENTER);
		ification.setText("¼Ó¼º ºÐ·ù");
		ification.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 11, SWT.NORMAL));
		ification.setBounds(5, 6, 145, 31);

		txt_attrvl2 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl2.setBounds(150, 166, 350, 40);

		txt_attrvl10 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl10.setBounds(150, 526, 350, 40);

		txt_attrvl11 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl11.setBounds(150, 571, 350, 40);

		num15 = new Label(composite, SWT.NONE);
		num15.setText("15");
		num15.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num15.setBounds(588, 121, 20, 40);

		lb_attrnm15 = new Label(composite, SWT.NONE);
		lb_attrnm15.setBounds(608, 126, 120, 40);

		txt_attrvl15 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl15.setBounds(733, 121, 350, 40);

		txt_attrvl16 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl16.setBounds(733, 166, 350, 40);

		lb_attrnm16 = new Label(composite, SWT.NONE);
		lb_attrnm16.setBounds(608, 171, 120, 40);

		num16 = new Label(composite, SWT.NONE);
		num16.setText("16");
		num16.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num16.setBounds(588, 166, 20, 40);

		num17 = new Label(composite, SWT.NONE);
		num17.setText("17");
		num17.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num17.setBounds(588, 211, 20, 40);

		num18 = new Label(composite, SWT.NONE);
		num18.setText("18");
		num18.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num18.setBounds(588, 256, 20, 40);

		num19 = new Label(composite, SWT.NONE);
		num19.setText("19");
		num19.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num19.setBounds(588, 301, 20, 40);

		num20 = new Label(composite, SWT.NONE);
		num20.setText("20");
		num20.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num20.setBounds(588, 346, 20, 40);

		num21 = new Label(composite, SWT.NONE);
		num21.setText("21");
		num21.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num21.setBounds(588, 391, 20, 40);

		num22 = new Label(composite, SWT.NONE);
		num22.setText("22");
		num22.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num22.setBounds(588, 436, 20, 40);

		num23 = new Label(composite, SWT.NONE);
		num23.setText("23");
		num23.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num23.setBounds(588, 481, 20, 40);

		num24 = new Label(composite, SWT.NONE);
		num24.setText("24");
		num24.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num24.setBounds(588, 526, 20, 40);

		num25 = new Label(composite, SWT.NONE);
		num25.setText("25");
		num25.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num25.setBounds(588, 571, 20, 40);

		lb_attrnm25 = new Label(composite, SWT.NONE);
		lb_attrnm25.setBounds(608, 576, 120, 40);

		lb_attrnm24 = new Label(composite, SWT.NONE);
		lb_attrnm24.setBounds(608, 531, 120, 40);

		lb_attrnm23 = new Label(composite, SWT.NONE);
		lb_attrnm23.setBounds(608, 486, 120, 40);

		lb_attrnm22 = new Label(composite, SWT.NONE);
		lb_attrnm22.setBounds(608, 441, 120, 40);

		lb_attrnm21 = new Label(composite, SWT.NONE);
		lb_attrnm21.setBounds(608, 396, 120, 40);

		lb_attrnm20 = new Label(composite, SWT.NONE);
		lb_attrnm20.setBounds(608, 351, 120, 40);

		lb_attrnm19 = new Label(composite, SWT.NONE);
		lb_attrnm19.setBounds(608, 306, 120, 40);

		lb_attrnm18 = new Label(composite, SWT.NONE);
		lb_attrnm18.setBounds(608, 261, 120, 40);

		lb_attrnm17 = new Label(composite, SWT.NONE);
		lb_attrnm17.setBounds(608, 216, 120, 40);

		txt_attrvl17 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl17.setBounds(733, 211, 350, 40);

		txt_attrvl18 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl18.setBounds(733, 256, 350, 40);

		txt_attrvl19 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl19.setBounds(733, 301, 350, 40);

		txt_attrvl20 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl20.setBounds(733, 346, 350, 40);

		txt_attrvl21 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl21.setBounds(733, 391, 350, 40);

		txt_attrvl22 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl22.setBounds(733, 436, 350, 40);

		txt_attrvl23 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl23.setBounds(733, 481, 350, 40);

		txt_attrvl24 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl24.setBounds(733, 526, 350, 40);

		txt_attrvl25 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl25.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl25.setBounds(733, 571, 350, 40);

		cb_ification = new Combo(composite, SWT.NONE);
		cb_ification.setItems();
		cb_ification.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		cb_ification.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cb_ification.setBounds(150, 6, 430, 31);
		cb_ification.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getSelect();
			}
		});

		txt_infonm = new Text(composite, SWT.BORDER);
		txt_infonm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_infonm.setBounds(588, 6, 350, 31);

		label = new Label(composite, SWT.NONE);
		label.setText("ÀÚ·áÀÏ°ý¼öÁ¤");
		label.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 11, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(5, 43, 145, 50);

		txt_all = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_all.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_all.setBounds(150, 45, 430, 50);

		button = new Button(composite, SWT.NONE);
		button.setText("È®ÀÎ");
		button.setBounds(590, 45, 100, 50);

		txt_attrvl12 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl12.setText("");
		txt_attrvl12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl12.setBounds(150, 616, 350, 40);

		txt_attrvl13 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl13.setText("");
		txt_attrvl13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl13.setBounds(150, 661, 350, 40);

		txt_attrvl14 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl14.setText("");
		txt_attrvl14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl14.setBounds(150, 706, 350, 40);

		lb_attrnm12 = new Label(composite, SWT.NONE);
		lb_attrnm12.setBounds(25, 623, 120, 40);

		lb_attrnm13 = new Label(composite, SWT.NONE);
		lb_attrnm13.setBounds(25, 666, 120, 40);

		lb_attrnm14 = new Label(composite, SWT.NONE);
		lb_attrnm14.setBounds(25, 711, 120, 40);

		num12 = new Label(composite, SWT.NONE);
		num12.setText("12");
		num12.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num12.setBounds(5, 617, 20, 40);

		num13 = new Label(composite, SWT.NONE);
		num13.setText("13");
		num13.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num13.setBounds(5, 661, 20, 40);

		num14 = new Label(composite, SWT.NONE);
		num14.setText("14");
		num14.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num14.setBounds(5, 706, 20, 40);

		txt_attrvl28 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl28.setText("");
		txt_attrvl28.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl28.setBounds(733, 707, 350, 40);

		lb_attrnm28 = new Label(composite, SWT.NONE);
		lb_attrnm28.setBounds(608, 711, 120, 40);

		num28 = new Label(composite, SWT.NONE);
		num28.setText("28");
		num28.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num28.setBounds(588, 707, 20, 40);

		num27 = new Label(composite, SWT.NONE);
		num27.setText("27");
		num27.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num27.setBounds(588, 662, 20, 40);

		lb_attrnm27 = new Label(composite, SWT.NONE);
		lb_attrnm27.setBounds(608, 666, 120, 40);

		txt_attrvl27 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl27.setText("");
		txt_attrvl27.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl27.setBounds(733, 662, 350, 40);

		txt_attrvl26 = new Text(composite, SWT.BORDER | SWT.MULTI);
		txt_attrvl26.setText("");
		txt_attrvl26.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txt_attrvl26.setBounds(733, 617, 350, 40);

		lb_attrnm26 = new Label(composite, SWT.NONE);
		lb_attrnm26.setBounds(608, 623, 120, 40);

		num26 = new Label(composite, SWT.NONE);
		num26.setText("26");
		num26.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		num26.setBounds(588, 618, 20, 40);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setAllok();
			}
		});
		new Label(container, SWT.NONE);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		getSeasonItem();

		return container;
	}

	private void getSeasonItem() {
		String a = "";
		;
		String[] temp;
		try {
			ProductIforDao dao = new ProductIforDao();
			List<List<String>> contents = dao.getSeasonItems();
			if (contents != null) {
				for (List<String> list : contents) {
					a += list.get(1) + ",";
				}
				temp = a.split(",");
				cb_ification.setItems(temp);
				cb_ification.select(propertynum);
			}

			getSelect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// È­¸é»Ñ¸®±â
	private void getSelect() {
		int num = cb_ification.getSelectionIndex();
		String attrcd = "";
		if (num < 9) {
			attrcd = "00" + String.valueOf(num + 1);
		} else {
			attrcd = "0" + String.valueOf(num + 1);
		}
		try {
			ProductIforDao dao = new ProductIforDao();
			List<String> name = dao.getInfonm(attrcd);
			txt_infonm.setText(name.get(0));
			List<List<String>> contents = dao.getSelectList(attrcd);
			lbdefault();
			numdefault();
			textdefault();
			txtsetText();
			if (contents != null) {
				String aaa = "";
				for (List<String> list : contents) {
					aaa = list.get(2);
					if (aaa.length() > 8) {
						aaa = aaa.substring(0, 8) + "\n" + aaa.substring(8, aaa.length());
					}
					if (1 == Integer.parseInt(list.get(1))) {
						lb_attrnm1.setText(aaa);
						lb_attrnm1.setVisible(true);
						num1.setVisible(true);
						txt_attrvl1.setVisible(true);
					} else if (2 == Integer.parseInt(list.get(1))) {
						lb_attrnm2.setText(aaa);
						lb_attrnm2.setVisible(true);
						num2.setVisible(true);
						txt_attrvl2.setVisible(true);
					} else if (3 == Integer.parseInt(list.get(1))) {
						lb_attrnm3.setText(aaa);
						lb_attrnm3.setVisible(true);
						num3.setVisible(true);
						txt_attrvl3.setVisible(true);
					} else if (4 == Integer.parseInt(list.get(1))) {
						lb_attrnm4.setText(aaa);
						lb_attrnm4.setVisible(true);
						num4.setVisible(true);
						txt_attrvl4.setVisible(true);
					} else if (5 == Integer.parseInt(list.get(1))) {
						lb_attrnm5.setText(aaa);
						lb_attrnm5.setVisible(true);
						num5.setVisible(true);
						txt_attrvl5.setVisible(true);
					} else if (6 == Integer.parseInt(list.get(1))) {
						lb_attrnm6.setText(aaa);
						lb_attrnm6.setVisible(true);
						num6.setVisible(true);
						txt_attrvl6.setVisible(true);
					} else if (7 == Integer.parseInt(list.get(1))) {
						lb_attrnm7.setText(aaa);
						lb_attrnm7.setVisible(true);
						num7.setVisible(true);
						txt_attrvl7.setVisible(true);
					} else if (8 == Integer.parseInt(list.get(1))) {
						lb_attrnm8.setText(aaa);
						lb_attrnm8.setVisible(true);
						num8.setVisible(true);
						txt_attrvl8.setVisible(true);
					} else if (9 == Integer.parseInt(list.get(1))) {
						lb_attrnm9.setText(aaa);
						lb_attrnm9.setVisible(true);
						num9.setVisible(true);
						txt_attrvl9.setVisible(true);
					} else if (10 == Integer.parseInt(list.get(1))) {
						lb_attrnm10.setText(aaa);
						lb_attrnm10.setVisible(true);
						num10.setVisible(true);
						txt_attrvl10.setVisible(true);
					} else if (11 == Integer.parseInt(list.get(1))) {
						lb_attrnm11.setText(aaa);
						lb_attrnm11.setVisible(true);
						num11.setVisible(true);
						txt_attrvl11.setVisible(true);
					} else if (12 == Integer.parseInt(list.get(1))) {
						lb_attrnm12.setText(aaa);
						lb_attrnm12.setVisible(true);
						num12.setVisible(true);
						txt_attrvl12.setVisible(true);
					} else if (13 == Integer.parseInt(list.get(1))) {
						lb_attrnm13.setText(aaa);
						lb_attrnm13.setVisible(true);
						num13.setVisible(true);
						txt_attrvl13.setVisible(true);
					} else if (14 == Integer.parseInt(list.get(1))) {
						lb_attrnm14.setText(aaa);
						lb_attrnm14.setVisible(true);
						num14.setVisible(true);
						txt_attrvl14.setVisible(true);
					} else if (15 == Integer.parseInt(list.get(1))) {
						lb_attrnm15.setText(aaa);
						lb_attrnm15.setVisible(true);
						num15.setVisible(true);
						txt_attrvl15.setVisible(true);
					} else if (16 == Integer.parseInt(list.get(1))) {
						lb_attrnm16.setText(aaa);
						lb_attrnm16.setVisible(true);
						num16.setVisible(true);
						txt_attrvl16.setVisible(true);
					} else if (17 == Integer.parseInt(list.get(1))) {
						lb_attrnm17.setText(aaa);
						lb_attrnm17.setVisible(true);
						num17.setVisible(true);
						txt_attrvl17.setVisible(true);
					} else if (18 == Integer.parseInt(list.get(1))) {
						lb_attrnm18.setText(aaa);
						lb_attrnm18.setVisible(true);
						num18.setVisible(true);
						txt_attrvl18.setVisible(true);
					} else if (19 == Integer.parseInt(list.get(1))) {
						lb_attrnm19.setText(aaa);
						lb_attrnm19.setVisible(true);
						num19.setVisible(true);
						txt_attrvl19.setVisible(true);
					} else if (20 == Integer.parseInt(list.get(1))) {
						lb_attrnm20.setText(aaa);
						lb_attrnm20.setVisible(true);
						num20.setVisible(true);
						txt_attrvl20.setVisible(true);
					} else if (21 == Integer.parseInt(list.get(1))) {
						lb_attrnm21.setText(aaa);
						lb_attrnm21.setVisible(true);
						num21.setVisible(true);
						txt_attrvl21.setVisible(true);
					} else if (22 == Integer.parseInt(list.get(1))) {
						lb_attrnm22.setText(aaa);
						lb_attrnm22.setVisible(true);
						num22.setVisible(true);
						txt_attrvl22.setVisible(true);
					} else if (23 == Integer.parseInt(list.get(1))) {
						lb_attrnm23.setText(aaa);
						lb_attrnm23.setVisible(true);
						num23.setVisible(true);
						txt_attrvl23.setVisible(true);
					} else if (24 == Integer.parseInt(list.get(1))) {
						lb_attrnm24.setText(aaa);
						lb_attrnm24.setVisible(true);
						num24.setVisible(true);
						txt_attrvl24.setVisible(true);
					} else if (25 == Integer.parseInt(list.get(1))) {
						lb_attrnm25.setText(aaa);
						lb_attrnm25.setVisible(true);
						num25.setVisible(true);
						txt_attrvl25.setVisible(true);
					} else if (26 == Integer.parseInt(list.get(1))) {
						lb_attrnm26.setText(aaa);
						lb_attrnm26.setVisible(true);
						num26.setVisible(true);
						txt_attrvl26.setVisible(true);
					} else if (27 == Integer.parseInt(list.get(1))) {
						lb_attrnm27.setText(aaa);
						lb_attrnm27.setVisible(true);
						num27.setVisible(true);
						txt_attrvl27.setVisible(true);
					} else if (28 == Integer.parseInt(list.get(1))) {
						lb_attrnm28.setText(aaa);
						lb_attrnm28.setVisible(true);
						num28.setVisible(true);
						txt_attrvl28.setVisible(true);
					}
				}
			}
			List<List<String>> contents2 = dao.getSelectViewList(attrcd);
			if (contents2 != null) {
				for (List<String> list1 : contents2) {
					if (1 == Integer.parseInt(list1.get(1))) {
						txt_attrvl1.setText(list1.get(2));
					} else if (2 == Integer.parseInt(list1.get(1))) {
						txt_attrvl2.setText(list1.get(2));
					} else if (3 == Integer.parseInt(list1.get(1))) {
						txt_attrvl3.setText(list1.get(2));
					} else if (4 == Integer.parseInt(list1.get(1))) {
						txt_attrvl4.setText(list1.get(2));
					} else if (5 == Integer.parseInt(list1.get(1))) {
						txt_attrvl5.setText(list1.get(2));
					} else if (6 == Integer.parseInt(list1.get(1))) {
						txt_attrvl6.setText(list1.get(2));
					} else if (7 == Integer.parseInt(list1.get(1))) {
						txt_attrvl7.setText(list1.get(2));
					} else if (8 == Integer.parseInt(list1.get(1))) {
						txt_attrvl8.setText(list1.get(2));
					} else if (9 == Integer.parseInt(list1.get(1))) {
						txt_attrvl9.setText(list1.get(2));
					} else if (10 == Integer.parseInt(list1.get(1))) {
						txt_attrvl10.setText(list1.get(2));
					} else if (11 == Integer.parseInt(list1.get(1))) {
						txt_attrvl11.setText(list1.get(2));
					} else if (12 == Integer.parseInt(list1.get(1))) {
						txt_attrvl12.setText(list1.get(2));
					} else if (13 == Integer.parseInt(list1.get(1))) {
						txt_attrvl13.setText(list1.get(2));
					} else if (14 == Integer.parseInt(list1.get(1))) {
						txt_attrvl14.setText(list1.get(2));
					} else if (15 == Integer.parseInt(list1.get(1))) {
						txt_attrvl15.setText(list1.get(2));
					} else if (16 == Integer.parseInt(list1.get(1))) {
						txt_attrvl16.setText(list1.get(2));
					} else if (17 == Integer.parseInt(list1.get(1))) {
						txt_attrvl17.setText(list1.get(2));
					} else if (18 == Integer.parseInt(list1.get(1))) {
						txt_attrvl18.setText(list1.get(2));
					} else if (19 == Integer.parseInt(list1.get(1))) {
						txt_attrvl19.setText(list1.get(2));
					} else if (20 == Integer.parseInt(list1.get(1))) {
						txt_attrvl20.setText(list1.get(2));
					} else if (21 == Integer.parseInt(list1.get(1))) {
						txt_attrvl21.setText(list1.get(2));
					} else if (22 == Integer.parseInt(list1.get(1))) {
						txt_attrvl22.setText(list1.get(2));
					} else if (23 == Integer.parseInt(list1.get(1))) {
						txt_attrvl23.setText(list1.get(2));
					} else if (24 == Integer.parseInt(list1.get(1))) {
						txt_attrvl24.setText(list1.get(2));
					} else if (25 == Integer.parseInt(list1.get(1))) {
						txt_attrvl25.setText(list1.get(2));
					} else if (26 == Integer.parseInt(list1.get(1))) {
						txt_attrvl26.setText(list1.get(2));
					} else if (27 == Integer.parseInt(list1.get(1))) {
						txt_attrvl27.setText(list1.get(2));
					} else if (28 == Integer.parseInt(list1.get(1))) {
						txt_attrvl28.setText(list1.get(2));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void okPressed() {
		String attrcd = txt_infonm.getText();
		String attrvl1 = txt_attrvl1.getText();
		String attrvl2 = txt_attrvl2.getText();
		String attrvl3 = txt_attrvl3.getText();
		String attrvl4 = txt_attrvl4.getText();
		String attrvl5 = txt_attrvl5.getText();
		String attrvl6 = txt_attrvl6.getText();
		String attrvl7 = txt_attrvl7.getText();
		String attrvl8 = txt_attrvl8.getText();
		String attrvl9 = txt_attrvl9.getText();
		String attrvl10 = txt_attrvl10.getText();
		String attrvl11 = txt_attrvl11.getText();
		String attrvl12 = txt_attrvl12.getText();
		String attrvl13 = txt_attrvl13.getText();
		String attrvl14 = txt_attrvl14.getText();
		String attrvl15 = txt_attrvl15.getText();
		String attrvl16 = txt_attrvl16.getText();
		String attrvl17 = txt_attrvl17.getText();
		String attrvl18 = txt_attrvl18.getText();
		String attrvl19 = txt_attrvl19.getText();
		String attrvl20 = txt_attrvl20.getText();
		String attrvl21 = txt_attrvl21.getText();
		String attrvl22 = txt_attrvl22.getText();
		String attrvl23 = txt_attrvl23.getText();
		String attrvl24 = txt_attrvl24.getText();
		String attrvl25 = txt_attrvl25.getText();
		String attrvl26 = txt_attrvl26.getText();
		String attrvl27 = txt_attrvl27.getText();
		String attrvl28 = txt_attrvl28.getText();

		List<String> list = new ArrayList<>();
		list = setListadd(attrcd, attrvl1, attrvl2, attrvl3, attrvl4, attrvl5, attrvl6, attrvl7, attrvl8, attrvl9,
				attrvl10, attrvl11, attrvl12, attrvl13, attrvl14, attrvl15, attrvl16, attrvl17, attrvl18, attrvl19,
				attrvl20, attrvl21, attrvl22, attrvl23, attrvl24, attrvl25, attrvl26, attrvl27, attrvl28);

		for (int i = 0; i < list.size(); i++) {
			if (YDMAStringUtil.length(list.get(i)) > 500) {
				MessageDialog.openInformation(getShell(), TITLE, "500¹®ÀÚ ÀÌ³»·Î¸¸ ÀÔ·ÂÀÌ °¡´ÉÇÕ´Ï´Ù.");
				return;
			}
		}

		ProductIforDao dao = new ProductIforDao();

		try {
			List<String> bigu = dao.InsertNupdate(attrcd);
			String num = txt_infonm.getText();
			int seq = dao.getProdAttrNm(num);
			if (bigu.size() <= 0) {
//				list = setListadd(attrcd,attrvl1,attrvl2,attrvl3,attrvl4,attrvl5,attrvl6,attrvl7,attrvl8,attrvl9,attrvl10,attrvl11,attrvl12,
//						attrvl13,attrvl14,attrvl15,attrvl16,attrvl17,attrvl18,attrvl19,attrvl20,attrvl21,attrvl22,attrvl23,attrvl24,attrvl25,attrvl26,attrvl27,attrvl28);

				dao.prodAttrvalInsert(list, seq);
				MessageDialog.openInformation(getShell(), TITLE, "»óÇ°¼Ó¼ºÁ¤º¸¸¦ ÀúÀåµÇ¾ú½À´Ï´Ù");
				cb_ification.setFocus();

			} else {
//				list = setListadd(attrcd,attrvl1,attrvl2,attrvl3,attrvl4,attrvl5,attrvl6,attrvl7,attrvl8,attrvl9,attrvl10,attrvl11,attrvl12,
//						attrvl13,attrvl14,attrvl15,attrvl16,attrvl17,attrvl18,attrvl19,attrvl20,attrvl21,attrvl22,attrvl23,attrvl24,attrvl25,attrvl26,attrvl27,attrvl28);

				dao.prodAttrvalUpdate(list, seq);
				MessageDialog.openInformation(getShell(), TITLE, "»óÇ°¼Ó¼ºÁ¤º¸¸¦ ¼öÁ¤ÇÏ¿´½À´Ï´Ù");
				cb_ification.setFocus();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.okPressed();
	}

	private void txtsetText() {
		txt_attrvl1.setText("");
		txt_attrvl2.setText("");
		txt_attrvl3.setText("");
		txt_attrvl4.setText("");
		txt_attrvl5.setText("");
		txt_attrvl6.setText("");
		txt_attrvl7.setText("");
		txt_attrvl8.setText("");
		txt_attrvl9.setText("");
		txt_attrvl10.setText("");
		txt_attrvl11.setText("");
		txt_attrvl12.setText("");
		txt_attrvl13.setText("");
		txt_attrvl14.setText("");
		txt_attrvl15.setText("");
		txt_attrvl16.setText("");
		txt_attrvl17.setText("");
		txt_attrvl18.setText("");
		txt_attrvl19.setText("");
		txt_attrvl20.setText("");
		txt_attrvl21.setText("");
		txt_attrvl22.setText("");
		txt_attrvl23.setText("");
		txt_attrvl24.setText("");
		txt_attrvl25.setText("");
		txt_attrvl26.setText("");
		txt_attrvl27.setText("");
		txt_attrvl28.setText("");
	}

	private void textdefault() {
		txt_attrvl1.setVisible(false);
		txt_attrvl2.setVisible(false);
		txt_attrvl3.setVisible(false);
		txt_attrvl4.setVisible(false);
		txt_attrvl5.setVisible(false);
		txt_attrvl6.setVisible(false);
		txt_attrvl7.setVisible(false);
		txt_attrvl8.setVisible(false);
		txt_attrvl9.setVisible(false);
		txt_attrvl10.setVisible(false);
		txt_attrvl11.setVisible(false);
		txt_attrvl12.setVisible(false);
		txt_attrvl13.setVisible(false);
		txt_attrvl14.setVisible(false);
		txt_attrvl15.setVisible(false);
		txt_attrvl16.setVisible(false);
		txt_attrvl17.setVisible(false);
		txt_attrvl18.setVisible(false);
		txt_attrvl19.setVisible(false);
		txt_attrvl20.setVisible(false);
		txt_attrvl21.setVisible(false);
		txt_attrvl22.setVisible(false);
		txt_attrvl23.setVisible(false);
		txt_attrvl24.setVisible(false);
		txt_attrvl25.setVisible(false);
		txt_attrvl26.setVisible(false);
		txt_attrvl27.setVisible(false);
		txt_attrvl28.setVisible(false);
	}

	private void numdefault() {
		num1.setVisible(false);
		num2.setVisible(false);
		num3.setVisible(false);
		num4.setVisible(false);
		num5.setVisible(false);
		num6.setVisible(false);
		num7.setVisible(false);
		num8.setVisible(false);
		num9.setVisible(false);
		num10.setVisible(false);
		num11.setVisible(false);
		num12.setVisible(false);
		num13.setVisible(false);
		num14.setVisible(false);
		num15.setVisible(false);
		num16.setVisible(false);
		num17.setVisible(false);
		num18.setVisible(false);
		num19.setVisible(false);
		num20.setVisible(false);
		num21.setVisible(false);
		num22.setVisible(false);
		num23.setVisible(false);
		num24.setVisible(false);
		num25.setVisible(false);
		num26.setVisible(false);
		num27.setVisible(false);
		num28.setVisible(false);
	}

	private void lbdefault() {
		lb_attrnm1.setText("");
		lb_attrnm2.setText("");
		lb_attrnm3.setText("");
		lb_attrnm4.setText("");
		lb_attrnm5.setText("");
		lb_attrnm6.setText("");
		lb_attrnm7.setText("");
		lb_attrnm8.setText("");
		lb_attrnm9.setText("");
		lb_attrnm10.setText("");
		lb_attrnm11.setText("");
		lb_attrnm12.setText("");
		lb_attrnm13.setText("");
		lb_attrnm14.setText("");
		lb_attrnm15.setText("");
		lb_attrnm16.setText("");
		lb_attrnm17.setText("");
		lb_attrnm18.setText("");
		lb_attrnm19.setText("");
		lb_attrnm20.setText("");
		lb_attrnm21.setText("");
		lb_attrnm22.setText("");
		lb_attrnm23.setText("");
		lb_attrnm24.setText("");
		lb_attrnm25.setText("");
		lb_attrnm26.setText("");
		lb_attrnm27.setText("");
		lb_attrnm28.setText("");
	}

	// ÀÚ·á ÀÏ°ýÀû¿ë
	private void setAllok() {
		String all = txt_all.getText();
		txt_attrvl1.setText(all);
		txt_attrvl2.setText(all);
		txt_attrvl3.setText(all);
		txt_attrvl4.setText(all);
		txt_attrvl5.setText(all);
		txt_attrvl6.setText(all);
		txt_attrvl7.setText(all);
		txt_attrvl8.setText(all);
		txt_attrvl9.setText(all);
		txt_attrvl10.setText(all);
		txt_attrvl11.setText(all);
		txt_attrvl12.setText(all);
		txt_attrvl13.setText(all);
		txt_attrvl14.setText(all);
		txt_attrvl15.setText(all);
		txt_attrvl16.setText(all);
		txt_attrvl17.setText(all);
		txt_attrvl18.setText(all);
		txt_attrvl19.setText(all);
		txt_attrvl20.setText(all);
		txt_attrvl21.setText(all);
		txt_attrvl22.setText(all);
		txt_attrvl23.setText(all);
		txt_attrvl24.setText(all);
		txt_attrvl25.setText(all);
		txt_attrvl26.setText(all);
		txt_attrvl27.setText(all);
		txt_attrvl28.setText(all);
	}

	private List<String> setListadd(String attrcd, String attrvl1, String attrvl2, String attrvl3, String attrvl4,
			String attrvl5, String attrvl6, String attrvl7, String attrvl8, String attrvl9, String attrvl10,
			String attrvl11, String attrvl12, String attrvl13, String attrvl14, String attrvl15, String attrvl16,
			String attrvl17, String attrvl18, String attrvl19, String attrvl20, String attrvl21, String attrvl22,
			String attrvl23, String attrvl24, String attrvl25, String attrvl26, String attrvl27, String attrvl28) {
		List<String> list = new ArrayList<>();
		list.add(attrcd);
		list.add(attrvl1);
		list.add(attrvl2);
		list.add(attrvl3);
		list.add(attrvl4);
		list.add(attrvl5);
		list.add(attrvl6);
		list.add(attrvl7);
		list.add(attrvl8);
		list.add(attrvl9);
		list.add(attrvl10);
		list.add(attrvl11);
		list.add(attrvl12);
		list.add(attrvl13);
		list.add(attrvl14);
		list.add(attrvl15);
		list.add(attrvl16);
		list.add(attrvl17);
		list.add(attrvl18);
		list.add(attrvl19);
		list.add(attrvl20);
		list.add(attrvl21);
		list.add(attrvl22);
		list.add(attrvl23);
		list.add(attrvl24);
		list.add(attrvl25);
		list.add(attrvl26);
		list.add(attrvl27);
		list.add(attrvl28);
		return list;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		btnOk = createButton(parent, IDialogConstants.OK_ID, "ÀúÀå", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "´Ý±â", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(1150, 870);
	}
}/////////////////////
