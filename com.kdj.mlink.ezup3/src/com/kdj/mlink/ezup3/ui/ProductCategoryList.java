package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.data.dao.CategoryDao;

public class ProductCategoryList extends CommandDialog {
	ProductMstDetail opener;
	Button btnOk;
	Button btnCancel;
	Button btnInsert;
	Button btnCopy;
	String TITLE = "카테고리관리";
	String[][] column_name_opt = { { "분류코드", "75" }, { "순서", "45" }, { "분류명", "151" }, { "상태", "60" }, { "설명", "0" },
			{ "상위코드", "0" }, { "네이버코드", "50" } };

	private Composite composite;
	private Label lb_lrcate;

	String infornumber;
	String prodcd;
	List<List<String>> list_opt;
	private Label lb_mdcate;
	private Label lb_smcate;
	private Label lb_dtcate;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	private Composite composite_4;
	private Button btn_lrcate;
	private Button btn_mdcate;
	private Button btn_smcate;
	private Button btn_dtcate;
	private GridTableViewer gtv_lgcate;
	private GridTableViewer gtv_mdcate;
	private GridTableViewer gtv_smcate;
	private GridTableViewer gtv_dtcate;
	private Grid gd_lgcate;
	private Grid gd_mdcate;
	private Grid gd_smcate;
	private Grid gd_dtcate;

	public ProductCategoryList(Shell parentShell, ProductMstDetail opener) {
		super(parentShell);
		this.opener = opener;

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

		composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(0, 0, 315, 576);

		lb_lrcate = new Label(composite_1, SWT.NONE);
		lb_lrcate.setBounds(3, 3, 306, 26);
		lb_lrcate.setAlignment(SWT.CENTER);
		lb_lrcate.setText("대분류관리");
		lb_lrcate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));

		btn_lrcate = new Button(composite_1, SWT.NONE);
		btn_lrcate.setText("신규등록");
		btn_lrcate.setBounds(209, 541, 100, 28);
		btn_lrcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("1");
			}
		});

		gtv_lgcate = new GridTableViewer(composite_1,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_lgcate = gtv_lgcate.getGrid();
		gd_lgcate.setBounds(3, 37, 306, 500);
		gd_lgcate.setHeaderVisible(true);
		gtv_lgcate.addDoubleClickListener(event -> categoryupdate("1"));
		gd_lgcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategmidium();
			}
		});

		composite_2 = new Composite(composite, SWT.BORDER);
		composite_2.setBounds(318, 0, 315, 576);

		lb_mdcate = new Label(composite_2, SWT.NONE);
		lb_mdcate.setBounds(3, 3, 306, 26);
		lb_mdcate.setAlignment(SWT.CENTER);
		lb_mdcate.setText("중분류관리");
		lb_mdcate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));

		btn_mdcate = new Button(composite_2, SWT.NONE);
		btn_mdcate.setText("신규등록");
		btn_mdcate.setBounds(209, 541, 100, 28);
		btn_mdcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("2");
			}
		});

		gtv_mdcate = new GridTableViewer(composite_2,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_mdcate = gtv_mdcate.getGrid();
		gd_mdcate.setBounds(3, 37, 306, 500);
		gd_mdcate.setHeaderVisible(true);
		composite_4 = new Composite(composite, SWT.BORDER);
		composite_4.setBounds(954, 0, 315, 576);
		gtv_mdcate.addDoubleClickListener(event -> categoryupdate("2"));
		gd_mdcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategSmall();
			}
		});

		lb_dtcate = new Label(composite_4, SWT.NONE);
		lb_dtcate.setBounds(3, 3, 306, 26);
		lb_dtcate.setAlignment(SWT.CENTER);
		lb_dtcate.setText("세분류관리");
		lb_dtcate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));

		btn_dtcate = new Button(composite_4, SWT.NONE);
		btn_dtcate.setText("신규등록");
		btn_dtcate.setBounds(209, 541, 100, 28);
		btn_dtcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("4");
			}
		});

		gtv_dtcate = new GridTableViewer(composite_4,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_dtcate = gtv_dtcate.getGrid();
		gd_dtcate.setBounds(3, 37, 306, 500);
		gd_dtcate.setHeaderVisible(true);
		gtv_dtcate.addDoubleClickListener(event -> categoryupdate("4"));

		composite_3 = new Composite(composite, SWT.BORDER);
		composite_3.setBounds(636, 0, 315, 576);

		lb_smcate = new Label(composite_3, SWT.NONE);
		lb_smcate.setBounds(3, 3, 306, 26);
		lb_smcate.setAlignment(SWT.CENTER);
		lb_smcate.setText("소분류관리");
		lb_smcate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));

		btn_smcate = new Button(composite_3, SWT.NONE);
		btn_smcate.setText("신규등록");
		btn_smcate.setBounds(209, 541, 100, 28);
		btn_smcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openInsertCategory("3");
			}
		});

		gtv_smcate = new GridTableViewer(composite_3,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		gd_smcate = gtv_smcate.getGrid();
		gd_smcate.setBounds(3, 37, 306, 500);
		gd_smcate.setHeaderVisible(true);
		gtv_smcate.addDoubleClickListener(event -> categoryupdate("3"));
		gd_smcate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCategDetail();
			}
		});

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);
		for (String[] column : column_name_opt) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_lgcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		for (String[] column : column_name_opt) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_mdcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		for (String[] column : column_name_opt) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_smcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		for (String[] column : column_name_opt) {
			GridColumn tableViewerColumn_x = new GridColumn(gd_dtcate, SWT.CENTER);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		gtv_lgcate.setContentProvider(new MyContentProvider());
		gtv_lgcate.setLabelProvider(new MyLableProvider());

		gtv_mdcate.setLabelProvider(new MyLableProvider());
		gtv_mdcate.setContentProvider(new MyContentProvider());

		gtv_smcate.setLabelProvider(new MyLableProvider());
		gtv_smcate.setContentProvider(new MyContentProvider());

		gtv_dtcate.setLabelProvider(new MyLableProvider());
		gtv_dtcate.setContentProvider(new MyContentProvider());

		setCategory();

		return container;
	}

	private void setCategory() {
		CategoryDao dao = new CategoryDao();
		try {
			List<List<String>> lagcontents = dao.largeCategory();
			if (lagcontents.size() != 0) {
				this.gtv_lgcate.setInput(lagcontents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}////////
		// 대분류선택시중분류가지고오기

	private void setCategmidium() {
		this.gtv_smcate.setInput(null);
		this.gtv_dtcate.setInput(null);
		List<List<String>> contents = (List<List<String>>) this.gtv_lgcate.getInput();
		List<String> list = contents.get(gtv_lgcate.getGrid().getSelectionIndex());

		CategoryDao dao = new CategoryDao();
		List<List<String>> midcontents = new ArrayList<>();
		try {
			midcontents = dao.midiumCategory(list.get(0));
			if (midcontents.size() != 0) {
				this.gtv_mdcate.setInput(midcontents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}////////////
		// 중분류선택시소분류가지고오기

	private void setCategSmall() {
		this.gtv_dtcate.setInput(null);
		List<List<String>> contents = (List<List<String>>) this.gtv_mdcate.getInput();
		List<String> list = contents.get(gtv_mdcate.getGrid().getSelectionIndex());

		CategoryDao dao = new CategoryDao();
		try {
			List<List<String>> smlcontents = dao.smallCategory(list.get(0));
			if (smlcontents.size() != 0) {
				this.gtv_smcate.setInput(smlcontents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}///////
		// 소분류선택시세분류가지고오기

	private void setCategDetail() {
		List<List<String>> contents = (List<List<String>>) this.gtv_smcate.getInput();
		List<String> list = contents.get(gtv_smcate.getGrid().getSelectionIndex());

		CategoryDao dao = new CategoryDao();
		try {
			List<List<String>> detcontents = dao.detailCategory(list.get(0));
			if (detcontents.size() != 0) {
				this.gtv_dtcate.setInput(detcontents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/////////////

	private void openInsertCategory(String cate) {
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		try {
			if (cate.equals("2")) {
				int lagnum = gtv_lgcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "대분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_lgcate.getInput();
				list = contents_opt.get(gtv_lgcate.getGrid().getSelectionIndex());

			} else if (cate.equals("3")) {
				int lagnum = gtv_mdcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "중분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_mdcate.getInput();
				list = contents_opt.get(gtv_mdcate.getGrid().getSelectionIndex());

			} else if (cate.equals("4")) {
				int lagnum = gtv_smcate.getGrid().getSelectionIndex();
				if (lagnum < 0) {
					MessageDialog.openInformation(getShell(), TITLE, "소분류를 선택하세요");
					return;
				}
				List<List<String>> contents_opt = (List<List<String>>) this.gtv_smcate.getInput();
				list = contents_opt.get(gtv_smcate.getGrid().getSelectionIndex());

			}
			ProductCategoryManagerDialog d = new ProductCategoryManagerDialog(this.getShell(), this, cate, list, list1,
					true);
			d.open();

			setCategory();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 업데이트
	private void categoryupdate(String category) {
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		List<List<String>> contents_opt = new ArrayList<>();
		List<List<String>> contents_opt1 = new ArrayList<>();
		int lagnum = gtv_lgcate.getGrid().getSelectionIndex();
		int midnum = gtv_mdcate.getGrid().getSelectionIndex();
		int smlnum = gtv_smcate.getGrid().getSelectionIndex();
		int detnum = gtv_dtcate.getGrid().getSelectionIndex();
		if (category.equals("1")) {
			contents_opt = (List<List<String>>) this.gtv_lgcate.getInput();
			list = contents_opt.get(gtv_lgcate.getGrid().getSelectionIndex());
		} else if (category.equals("2")) {
			contents_opt = (List<List<String>>) this.gtv_mdcate.getInput();
			list = contents_opt.get(gtv_mdcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_lgcate.getInput();
			list1 = contents_opt1.get(lagnum);
		}
		if (category.equals("3")) {
			contents_opt = (List<List<String>>) this.gtv_smcate.getInput();
			list = contents_opt.get(gtv_smcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_mdcate.getInput();
			list1 = contents_opt1.get(midnum);
		}
		if (category.equals("4")) {
			contents_opt = (List<List<String>>) this.gtv_dtcate.getInput();
			list = contents_opt.get(gtv_dtcate.getGrid().getSelectionIndex());
			contents_opt1 = (List<List<String>>) this.gtv_smcate.getInput();
			list1 = contents_opt1.get(smlnum);
		}

		ProductCategoryManagerDialog d = new ProductCategoryManagerDialog(this.getShell(), this, category, list, list1,
				false);
		d.open();

		setCategory();

	}

	@Override
	protected void okPressed() {

	}/////

	@Override
	protected void cancelPressed() {
		super.cancelPressed();

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
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

			return value;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {

			return null;
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
}////
