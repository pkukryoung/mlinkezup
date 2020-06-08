package com.kdj.mlink.ezup3.ui;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

import com.kdj.mlink.ezup3.data.dao.OrderDao;

public class OrderListStockCheckThread extends Thread {

	OrderListStockChekDialog opener;
	private ProgressBar progressBar;

	Hashtable<String, List<List<String>>> h_pordcd;
	String orddt;

	public OrderListStockCheckThread(OrderListStockChekDialog opener, ProgressBar progressBar,
			Hashtable<String, List<List<String>>> h_pordcd, String orddt) {
		this.opener = opener;
		this.progressBar = progressBar;
		this.h_pordcd = h_pordcd;

		this.orddt = orddt;
	}

	@Override
	public void run() {

		try {

			int totalCount = h_pordcd.size();

			Enumeration enumKey = h_pordcd.keys();

			OrderDao dao = new OrderDao();

			int idx = 0;

			while (enumKey.hasMoreElements()) {

				// 특정상품별로 재고수량을 추출하여 같은상품의 재고수량을 추가 한다.
				String prodcdKey = (String) enumKey.nextElement();
				int sotckCnt = dao.checkStock1(orddt, prodcdKey);

				List<List<String>> listGroup = h_pordcd.get(prodcdKey);

				for (List<String> list : listGroup) {
					list.add("" + sotckCnt); // 재고수량 추가
				}

				this.updateGUIInProgress(idx++, totalCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.updateGUIWhenFinish();
	}

	private void updateGUIWhenFinish() {
		Display.getDefault().asyncExec(() -> {
			progressBar.setSelection(0);
			progressBar.setMaximum(1);
			opener.close();
		});
	}

	private void updateGUIInProgress(int value, int count) {
		Display.getDefault().asyncExec(() -> {
			progressBar.setMaximum(count);
			progressBar.setSelection(value);
		});
	}
}
