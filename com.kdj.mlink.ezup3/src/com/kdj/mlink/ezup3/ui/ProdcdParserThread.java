package com.kdj.mlink.ezup3.ui;

import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;

public class ProdcdParserThread extends Thread {

	ProcdParseDialog opener;
	private ProgressBar progressBar;
	

	List<List<String>> origin_Contents;
	//List<String> prodcdList;

	public ProdcdParserThread(ProcdParseDialog opener, ProgressBar progressBar, List<List<String>> data) {
		this.progressBar = progressBar;
		this.opener = opener;
	
		// this.prodcdList = prodcdList;
		this.origin_Contents = data;
	}

	@Override
	public void run() {
		int x = 0;
		for (List<String> order : origin_Contents) {
			
			parse(order); // TODO -- 사방넷 옵션(코드)와 디비의 상품코드비교

			this.updateGUIInProgress(x++, origin_Contents.size());
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}

		this.updateGUIWhenFinish();
	}

	private void parse(List<String> order) {
		
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {

				String data_k = order.get(10);
				String data_r = order.get(18);
				String data_s = order.get(19);

				boolean flag = false;

				if (!flag && data_k.length() >= 5) {
					flag = analyze(data_k, order);
				}
				if (!flag && data_r.length() >= 5) {
					flag = analyze(data_r, order);
				}
				if (!flag && data_s.length() >= 5) {
					flag = analyze(data_s, order);
				}

			}
		});
		
	}

	private boolean analyze(String cellStr, List<String> order) {

		if (cellStr != null && cellStr.length() < 5) {
			return false;
		}
		if (!cellStr.contains("(") || !cellStr.contains(")")) {
			return false; //상품코드는 () 내에 존재하는 경우가 대부분임.
		}

		boolean flag = false;

		int beginIndex = cellStr.indexOf("(");
		int endIndex = cellStr.indexOf(")");

		if (((endIndex - beginIndex) - 1) >= 5) {
			String parsedStr = cellStr.substring(beginIndex + 1, endIndex);
			if (isEng(parsedStr)) {
				order.set(11, parsedStr);
				order.set(order.size()-1, "F"); //코드를 파싱했으나 찾았으나 검증안된 상태임으로 
				flag = true;
			} else {
				flag = analyze(cellStr.substring(endIndex + 1), order);
			}

		} else {
			flag = analyze(cellStr.substring(endIndex + 1), order);
		}

		return flag;
	}

	/**
	 * 문자에 영문 or 특수문자 아닌 문자  영문코드 아님으로 처리.
	 * @param str
	 * @return
	 */
	private boolean isEng(String str) {
		
		boolean isEng = false;
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
						
			if ((ch>='a' && ch<='z') ||  (ch >='A' && ch<='Z') || (ch>='0' && ch<='9')
					|| (ch=='~') || (ch=='!') || (ch=='@') || (ch=='#')
					|| (ch=='$') || (ch=='%') || (ch=='^') || (ch=='&')
					|| (ch=='*') || (ch=='-') || (ch=='+') || (ch=='-')
					|| (ch=='`') || (ch=='_') || (ch=='?') || (ch=='/')
					|| (ch=='{') || (ch=='}') || (ch=='[') || (ch==']')
					|| (ch=='|') || (ch=='=') || (ch=='\\') || (ch=='\'')
					|| (ch==':') || (ch==';') || (ch=='"') ) {
				isEng = true;
			}else {
				return false;
			}
			
			
		}

		return isEng;
	}

	private void updateGUIWhenFinish() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				progressBar.setSelection(0);
				progressBar.setMaximum(1);
				opener.close();
			}
		});
	}

	private void updateGUIInProgress(int value, int count) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				progressBar.setMaximum(count);
				progressBar.setSelection(value);
			}
		});
	}

}
