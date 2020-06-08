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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class PopularSearch extends CommandDialog {
	ProductMstDetail opener;
	Button btnOk;
	Button btnCancel;
	Button btnInsert;
	Button btnCopy;
	String TITLE = "인기검색어";
	String[][] column_name = { { "인기검색어", "350" } };

	GridTableViewer search_Table;
	Grid grid;
	boolean isNew;
	String categlagcd;
	String categmidcd;
	String categsmlcd;
	String categdetcd;
	List<List<String>> contents;

	public PopularSearch(Shell parentShell, ProductMstDetail opener, boolean isNew, List<List<String>> contents) {// String
																													// categlagcd,
																													// String
																													// categmidcd,
																													// String
																													// categsmlcd,
																													// String
																													// categdetcd)
																													// {
		super(parentShell);
		this.opener = opener;
		this.isNew = isNew;
		this.contents = contents;
		this.categmidcd = categmidcd;
		this.categsmlcd = categsmlcd;
		this.categdetcd = categdetcd;

	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		search_Table = new GridTableViewer(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		grid = search_Table.getGrid();
		GridData gd_grid = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grid.heightHint = 346;
		grid.setLayoutData(gd_grid);
		grid.setRowHeaderVisible(true);
		grid.setHeaderVisible(true);

		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);
		for (String[] column : column_name) {
			GridColumn tableViewerColumn_x = new GridColumn(grid, SWT.LEFT);
			tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

			tableViewerColumn_x.setWidth(Integer.parseInt(column[1]));
			tableViewerColumn_x.setText(column[0]);

		}
		search_Table.setContentProvider(new MyContentProvider());
		search_Table.setLabelProvider(new MyLableProvider());

		setPopularSearch();

		return container;
	}

	private void setPopularSearch() {
		search_Table.setInput(contents);

	}

	@Override
	protected void okPressed() {
		int[] selectedIdx = search_Table.getGrid().getSelectionIndices();
		if (selectedIdx.length < 0 || selectedIdx.length > 10) {
			MessageDialog.openInformation(getShell(), "인기검색어", "선택은 1건에서 최대 10건까지 가능합니다");
			return;
		}
		List<List<String>> contents_search = new ArrayList<>();
		List<List<String>> contents = (List<List<String>>) search_Table.getInput();
		for (int element : selectedIdx) {
			List<String> list = new ArrayList<>();
			list = contents.get(element);
			contents_search.add(list);
		}
		opener.setSearchNM(contents_search, isNew);
		this.close();

	}/////

	@Override
	protected void cancelPressed() {
		super.cancelPressed();

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		btnCancel = createButton(parent, IDialogConstants.OK_ID, "선택", false);
		btnCancel = createButton(parent, IDialogConstants.CANCEL_ID, "닫기", false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(TITLE);
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(300, 500);
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
