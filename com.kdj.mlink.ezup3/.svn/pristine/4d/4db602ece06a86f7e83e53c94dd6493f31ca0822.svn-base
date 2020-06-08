package com.kdj.mlink.ezup3.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;

import com.kdj.mlink.ezup3.ui.MyCellRenderer;
import com.kdj.mlink.ezup3.ui.MyColumnHeaderRenderer;

public class YDMAGridUtil {
	private static YDMAGridUtil instance = new YDMAGridUtil();

	private YDMAGridUtil() {
		System.out.println("YDMAGridUtil Instance Created..");
	}

	public static YDMAGridUtil getInstance() {
		return instance;
	}

	public void addColumn(Grid grid, String filed, String text, int width, int headerstyle, int cellstyle) {
		MyColumnHeaderRenderer myHeaderRender = new MyColumnHeaderRenderer();
		myHeaderRender.setBGC(SWT.COLOR_DARK_CYAN);
		myHeaderRender.setFGC(SWT.COLOR_WHITE);

		GridColumn tableViewerColumn_x = new GridColumn(grid, headerstyle);
		tableViewerColumn_x.setHeaderRenderer(myHeaderRender);

		tableViewerColumn_x.setWidth(width);
		tableViewerColumn_x.setText(text);
		MyCellRenderer myCellRenderer = new MyCellRenderer();
		myCellRenderer.setColumnAlign(cellstyle);
		tableViewerColumn_x.setCellRenderer(myCellRenderer);
	}

	public static List<List<String>> covertToGridData(List<?> list, List<String> fileds)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<List<String>> contents = new ArrayList<>();
		for (Object obj : list) {
			List<String> data = new ArrayList<>();
			Class cl = obj.getClass();

			for (String f : fileds) {
				Field field = cl.getDeclaredField(f.toLowerCase());
				field.setAccessible(true);
				String val = field.get(obj) + "";
				data.add(val);
				System.out.println(val);
			}
			contents.add(data);
		}
		return contents;
	}
}
