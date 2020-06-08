package com.kdj.mlink.ezup3.data.excel;

import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.kdj.mlink.ezup3.common.YDMAPluginUtils;

public class MyDataManagerFactory {

	private static MyDataManagerFactory factory;

	private MyDataManagerFactory() {
		factory = this;
	}

	public static MyDataManagerFactory getInstance() {

		if (factory == null) {
			new MyDataManagerFactory();
		}
		return factory;
	}

	public static MyExcelManager createExcelManager(String fileURI) throws FileNotFoundException {
		if (fileURI.contains(".xls")) {
			return new MyExcelManagerImple(fileURI);
		} else if (fileURI.contains(".xlsx")) {
			return new MyXLSXExcelManagerImple(fileURI);
		}
		return null;
	}

	public static FileDialog createExcelFileSaveDialog() {
		FileDialog dialog = new FileDialog(YDMAPluginUtils.getShell(), SWT.SAVE);
		String[] filters = { "*.xlsx", "*.xls" };
		dialog.setFilterExtensions(filters);
		return dialog;
	}

	public static FileDialog createExcelFileSelectionDialog() {
		FileDialog dialog = new FileDialog(YDMAPluginUtils.getShell(), SWT.OPEN);
		String[] filters = { "*.xlsx", "*.xls" };
		dialog.setFilterExtensions(filters);
		return dialog;
	}

}
