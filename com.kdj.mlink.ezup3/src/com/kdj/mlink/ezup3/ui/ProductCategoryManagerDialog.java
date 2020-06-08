package com.kdj.mlink.ezup3.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
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

import com.kdj.mlink.ezup3.data.dao.CategoryDao;

public class ProductCategoryManagerDialog extends CommandDialog {
	ProductCategoryList opener;
	ShopCategoryMappingManager opener1;

	Button btnOk;
	Button btnCancel;
	Button btnInsert;
	Button btnCopy;
	String TITLE = "카테고리등록";

	private Composite composite;
	private Label lb_lrcate;

	String infornumber;
	String prodcd;
	List<List<String>> list_opt;
	private Composite composite_1;
	String cate;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Text txt_code;
	private Text txt_category;
	private Text txt_disp;
	private Text txt_comment;
	private Combo cb_useyn;
	List<String> list;
	List<String> list1;
	boolean isNew;
	private Text lb_differ;
	private Label lb_naver;
	private Button btn_search;
	private Combo cb_category;
	private Text txt_mycode;

	public ProductCategoryManagerDialog(Shell parentShell, ProductCategoryList opener, String cate, List<String> list,
			List<String> list1, boolean isNew) {
		super(parentShell);
		this.opener = opener;
		this.cate = cate;
		this.list = list;
		this.isNew = isNew;
		this.list1 = list1;
	}

	public ProductCategoryManagerDialog(Shell shell, ShopCategoryMappingManager opener1, String cate2,
			List<String> list2, List<String> list12, boolean isNew2) {
		super(shell);
		this.opener1 = opener1;
		this.cate = cate2;
		this.list = list2;
		this.isNew = isNew2;
		this.list1 = list12;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(2, false));

		composite = new Composite(container, SWT.NONE);
		composite.setLayout(null);

		composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(5, 5, 615, 265);

		lb_lrcate = new Label(composite_1, SWT.NONE);
		lb_lrcate.setBounds(5, 5, 140, 26);
		lb_lrcate.setText("※ 상위분류");
		lb_lrcate.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));

		label = new Label(composite_1, SWT.NONE);
		label.setText("※ 분류코드");
		label.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label.setBounds(5, 41, 140, 26);

		label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("※ 분류명");
		label_1.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_1.setBounds(5, 77, 140, 26);

		label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("※ 사용여부");
		label_2.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_2.setBounds(5, 113, 140, 26);

		label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("   전시순서");
		label_3.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_3.setBounds(5, 149, 140, 26);

		label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("   설 명");
		label_4.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		label_4.setBounds(5, 185, 140, 26);

		txt_code = new Text(composite_1, SWT.BORDER);
		txt_code.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_code.setBounds(150, 41, 100, 26);
		txt_code.setEnabled(false);

		txt_category = new Text(composite_1, SWT.BORDER);
		txt_category.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_category.setBounds(150, 77, 400, 26);

		txt_disp = new Text(composite_1, SWT.BORDER);
		txt_disp.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_disp.setBounds(150, 149, 100, 26);

		txt_comment = new Text(composite_1, SWT.BORDER);
		txt_comment.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_comment.setBounds(150, 185, 400, 26);

		cb_useyn = new Combo(composite_1, SWT.READ_ONLY);
		cb_useyn.setItems(new String[] { "사용여부", "사용중", "미사용" });
		cb_useyn.setBounds(150, 113, 200, 28);
		cb_useyn.select(1);

		lb_differ = new Text(composite_1, SWT.BORDER);
		lb_differ.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		lb_differ.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lb_differ.setEnabled(false);
		lb_differ.setBounds(151, 2, 200, 26);

		lb_naver = new Label(composite_1, SWT.NONE);
		lb_naver.setText("   네이버검색순서");
		lb_naver.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		lb_naver.setBounds(5, 221, 140, 26);

		cb_category = new Combo(composite_1, SWT.READ_ONLY);
		cb_category.setBounds(150, 221, 200, 28);

		txt_mycode = new Text(composite_1, SWT.BORDER);
		txt_mycode.setFont(SWTResourceManager.getFont("맑은 고딕", 10, SWT.NORMAL));
		txt_mycode.setBounds(250, 41, 150, 26);

		new Label(container, SWT.NONE);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		setCategory(isNew);

		return container;
	}

	// 처음시작시 분류별로 띄워주는거 틀리게
	private void setCategory(boolean isNew) {
		if (isNew) {
			if (cate.equals("1")) {
				lb_differ.setText("없음");
				setCategoryDefault();
				setNVCateglag();
			} else if (cate.equals("2")) {
				lb_differ.setText(list.get(2));
				setCategoryDefault();
				setNVCategmid();
			} else if (cate.equals("3")) {
				lb_differ.setText(list.get(2));
				setCategoryDefault();
				setNVCategsml();
			} else if (cate.equals("4")) {
				lb_differ.setText(list.get(2));
				setCategoryDefault();
				setNVCategdet();
			}
		} else {
			if (cate.equals("1")) {
				setNVCateglag();
				setCategoryList();
			} else if (cate.equals("2")) {
				setNVCategmid();
				setCategoryList();
			} else if (cate.equals("3")) {
				setNVCategsml();
				setCategoryList();
			} else if (cate.equals("4")) {
				setNVCategdet();
				setCategoryList();
			}
		}

	}

	// 글초기화
	private void setCategoryDefault() {
		if (list.size() > 0) {
			txt_code.setText(list.get(0));
			txt_mycode.setFocus();
			txt_code.setEnabled(false);
		} else {
			txt_code.setText("");
		}
		txt_category.setText("");
		txt_disp.setText("");
		txt_comment.setText("");
		cb_useyn.select(1);
	}

	// 수정할때
	private void setCategoryList() {
		if (!cate.equals("1")) {
			int i = 0;
			lb_differ.setEnabled(true);
			txt_code.setVisible(false);
			txt_mycode.setText(list.get(i++));
			txt_mycode.setBounds(150, 41, 200, 26);
			txt_mycode.setEnabled(false);
			txt_disp.setText(list.get(i++));
			txt_category.setText(list.get(i++));
			if (list.get(i++).equals("사용중")) {
				cb_useyn.select(1);
			} else {
				cb_useyn.select(2);
			}
			txt_comment.setText(list.get(i++));
			lb_differ.setText(list.get(i++));
			String type = list.get(6);
			setcateglagcd(type);

		} else {
			int i = 0;
			lb_differ.setText("없음");
			txt_code.setVisible(false);
			txt_mycode.setText(list.get(i++));
			txt_mycode.setBounds(150, 41, 200, 26);
			txt_mycode.setEnabled(false);
			txt_disp.setText(list.get(i++));
			txt_category.setText(list.get(i++));
			if (list.get(i++).equals("사용중")) {
				cb_useyn.select(1);
			} else {
				cb_useyn.select(2);
			}
			txt_comment.setText(list.get(i++));
			String type = list.get(6);
			setcateglagcd(type);
		}
	}

	void setcateglagcd(String info) {
		for (int i = 0; i < this.cb_category.getItems().length; i++) {
			String type = this.cb_category.getItems()[i];
			String cate = type.split("[(]")[1].split("[)]")[0];
			if (cate.equals(info)) {
				this.cb_category.setText(type);
				break;
			} else {
				cb_category.select(0);
			}
		}
	}

	private void setNVCateglag() {
		String category = "1분류선택(0),";
		// 인설트대분류 가져오기
		try {
			CategoryDao dao = new CategoryDao();
			List<List<String>> contents = dao.getNaverLargeCategory();
			if (contents.size() > 0) {
				for (int i = 0; i < contents.size(); i++) {
					category += contents.get(i).get(1) + "(" + contents.get(i).get(0) + "),";
				}
				cb_category.setItems(category.split(","));
				if (isNew) {
					cb_category.select(0);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}////////
		// 인설트2분류

	private void setNVCategmid() {
		String category = "2분류선택(0),";
		try {
			CategoryDao dao = new CategoryDao();
			List<List<String>> contents = new ArrayList<>();
			if (isNew) {
				contents = dao.getNaverMidiumCategory(list.get(6));
			} else {
				if (list.get(6).equals("")) {
					contents = dao.getNaverMidiumCategory(list1.get(6));
				} else {
					contents = dao.getNaverMidiumCategory(list.get(6).substring(0, list.get(6).length() - 3));
				}
			}
			if (contents.size() > 0) {
				for (int i = 0; i < contents.size(); i++) {
					category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
				}
				cb_category.setItems(category.split(","));
				if (isNew) {
					cb_category.select(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}////////
		// 3분류

	private void setNVCategsml() {
		String category = "3분류선택(0),";
		try {

			CategoryDao dao = new CategoryDao();
			List<List<String>> contents = new ArrayList<>();
			if (isNew) {
				contents = dao.getNaverSmallCategory(list.get(6));
			} else {
				if (list.get(6).equals("")) {
					contents = dao.getNaverSmallCategory(list1.get(6));
				} else {
					contents = dao.getNaverSmallCategory(list.get(6).substring(0, list.get(6).length() - 3));
				}

			}
			if (contents.size() > 0) {
				for (int i = 0; i < contents.size(); i++) {
					category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
				}
				cb_category.setItems(category.split(","));
				if (isNew) {
					cb_category.select(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}////////
		// 4분류

	private void setNVCategdet() {
		String category = "4분류선택(0),";
		try {
			CategoryDao dao = new CategoryDao();
			List<List<String>> contents = new ArrayList<>();
			if (isNew) {
				contents = dao.getNaverDetailCategory(list.get(6));
			} else {
				if (list.get(6).equals("")) {
					contents = dao.getNaverDetailCategory(list1.get(6));
				} else {
					contents = dao.getNaverDetailCategory(list.get(6).substring(0, list.get(6).length() - 3));
				}

			}
			if (contents.size() > 0) {
				for (int i = 0; i < contents.size(); i++) {
					category += contents.get(i).get(2) + "(" + contents.get(i).get(0) + "),";
				}
				cb_category.setItems(category.split(","));
				if (isNew) {
					cb_category.select(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}////////

	@Override
	protected void okPressed() {
		if (isNew) {
			if (txt_mycode.getText().length() != 3) {
				MessageDialog.openInformation(getShell(), TITLE, "분류코드를 3자리 숫자로 입력하세요");
				txt_code.setFocus();
				return;
			}
			if (txt_category.getText().length() < 0) {
				MessageDialog.openInformation(getShell(), TITLE, "분류명을 입력하세요");
				txt_category.setFocus();
				return;
			}
			if (cb_useyn.getText().equals("사용여부")) {
				MessageDialog.openInformation(getShell(), TITLE, "사용여부를 체크하세요");
				cb_useyn.setFocus();
				return;
			}
		} else {
			if (txt_category.getText().length() < 0) {
				MessageDialog.openInformation(getShell(), TITLE, "분류명을 입력하세요");
				txt_category.setFocus();
				return;
			}
			if (cb_useyn.getText().equals("사용여부")) {
				MessageDialog.openInformation(getShell(), TITLE, "사용여부를 체크하세요");
				cb_useyn.setFocus();
				return;
			}
		}

		String mycode = txt_code.getText().trim() + txt_mycode.getText().trim();
		String differcode = lb_differ.getText() == "" ? "" : lb_differ.getText();
		String code = txt_code.getText().trim();
		String category = txt_category.getText();
		String disp = txt_disp.getText().trim() == "" ? "0" : txt_disp.getText().trim();
		String use_yn = cb_useyn.getText().equals("사용중") ? "1" : "0";
		String comment = txt_comment.getText();
		String nvcategory = "";
		if (cb_category.getText().length() > 0) {
			nvcategory = cb_category.getText().split("[(]")[1].split("[)]")[0].equals("0") ? ""
					: cb_category.getText().split("[(]")[1].split("[)]")[0];
		}

		CategoryDao dao = new CategoryDao();
		try {
			if (isNew) {
				if (this.cate.equals("1")) {
					if (dao.isExistLarge(mycode)) {//
						MessageDialog.openInformation(getShell(), TITLE, "동일한 코드가 존재합니다.");
						return;
					}
					dao.categoryLargeInsert(mycode, category, disp, use_yn, comment, nvcategory);
				} else if (this.cate.equals("2")) {
					if (dao.isExistMidium(mycode)) {//
						MessageDialog.openInformation(getShell(), TITLE, "동일한 코드가 존재합니다.");
						return;
					}
					dao.categoryMidiumInsert(list.get(0), mycode, category, disp, use_yn, comment, nvcategory);
				} else if (this.cate.equals("3")) {
					if (dao.isExistSmall(mycode)) {//
						MessageDialog.openInformation(getShell(), TITLE, "동일한 코드가 존재합니다.");
						return;
					}
					dao.categorySmallInsert(list.get(0), mycode, category, disp, use_yn, comment, nvcategory);
				} else {
					if (dao.isExistDetail(mycode)) {//
						MessageDialog.openInformation(getShell(), TITLE, "동일한 코드가 존재합니다.");
						return;
					}
					dao.categoryDetailInsert(list.get(0), mycode, category, disp, use_yn, comment, nvcategory);
				}
			} else {
				if (this.cate.equals("1")) {
					dao.categoryLargeUpdate(mycode, category, disp, use_yn, comment, nvcategory);
				} else if (this.cate.equals("2")) {
					dao.categoryMidiumUpdate(differcode, mycode, category, disp, use_yn, comment, nvcategory);
				} else if (this.cate.equals("3")) {
					dao.categorySmallUpdate(differcode, mycode, category, disp, use_yn, comment, nvcategory);
				} else {
					dao.categoryDetailUpdate(differcode, mycode, category, disp, use_yn, comment, nvcategory);
				}
			}
			super.okPressed();

		} catch (Exception e) {
			e.printStackTrace();
		}

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
		return new Point(645, 390);
	}

}////
