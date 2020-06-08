package com.kdj.mlink.ezup3.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.poi.ss.usermodel.HorizontalAlignment;


public class YDMAProgressBar {
	private static  YDMAProgressBar instance = null;
	int key = 0;
	JFrame frame = null;
	JProgressBar bar = null;
	Container container = null;
	int milliseconds;
	String title = "";
	Map<Integer,String> list = new HashMap<Integer,String>();
	List<String> titles = new ArrayList<String>();
	boolean visiableStatus = false; 
	JLabel label = null;
	JTextArea txtLog  = null;
	/**
	 * @wbp.parser.entryPoint
	 */
	private YDMAProgressBar(){}
	
	/*
	 *. 인스턴스 생성..  
	 */
	public static YDMAProgressBar get(){	
		if(instance == null)
			instance = new YDMAProgressBar();
		return instance;
	}

    
	/**
	 * @wbp.parser.entryPoint
	 */
	public void start() {
		start("로딩중...");
	}
	
	
	/*
	 *   프로그래스 바 초기값 100 으로 셋팅 함.. 
	 */
	public void start(String title) {
		start(title, 100);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void start(String title, int maximum) {
		start(title,maximum, 10, false);
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void start(String title, int maximum, int milliseconds,boolean visiableStatus) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bar = new JProgressBar();		
	    container = frame.getContentPane();
		container.add(bar,BorderLayout.NORTH);
		frame.setSize(570, 80);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		frame.setLocation(screen.width/2-285,screen.height/2-40);
		bar.setMaximum(maximum);
		this.milliseconds = milliseconds;
		this.title = title;
		this.visiableStatus = visiableStatus;
		titles.clear();
		if(visiableStatus) {
			label = new JLabel();
			container.add(label,BorderLayout.CENTER);
			
		
			
		    txtLog = new JTextArea();
		    txtLog.setFont(new Font("Gulim", Font.BOLD, 20));
		    txtLog.setForeground(Color.BLUE);
			JScrollPane scrollPane = new JScrollPane(txtLog);
			container.add(scrollPane);

			frame.setSize(570, frame.getHeight() + 60);
			
		}
	} 
	
	
	/*
	 * value 값만 변경.. 
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setValue(int value) {
		setValue(this.title, value, true);
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setValue(String title, int value) {
		setValue(title, value, true);
	}
	
	/*
	 *    텍스트 변경.. 
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setValue(String title, int value, boolean isSucess) {
		try {
			Thread.sleep(milliseconds);
			bar.setValue(value);
			frame.setTitle(title);
			bar.setStringPainted(true);	
			frame.setVisible(true);
			
			
			if(visiableStatus)
			{
				//label.setText(getHtml(title, isSucess));
				  txtLog.append(title + "\n");  // 로그 내용을 JTextArea 위에 붙여주고

				  txtLog.setCaretPosition(txtLog.getDocument().getLength());  // 맨아래로 스크롤한다.
				
				//frame.setSize(570, frame.getHeight() + 25);
				  frame.setLocation(10, 10);
				  frame.setSize(1100, 600);
			}
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public String getHtml(String currentText, boolean isSucess) {
		
		TreeMap<Integer,String> tm = new TreeMap<Integer,String>(list);
		Iterator<Integer> it = tm.keySet().iterator();  
		
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<div style=\"border:0px solid; padding:10px;\">");
		html.append("<ul>");
		html.append(String.format("%s%s%s%s","<li style=\" border: 0px solid #efefef; font-size: 12px; color:white; font-family:Gulim; font-weight:bold; \">" ,  currentText,"-> 진행중",  "</li>"));
		while(it.hasNext()) {
			Integer key = it.next();
			if(isSucess)
				html.append(String.format("%s%s%s%s","<li style=\" border: 0px solid #efefef; font-size: 12px; font-family:Gulim; color:GREEN \">" ,  list.get(key).toString(), "-> 완료",  "</li>"));
			else
				html.append(String.format("%s%s%s%s","<li style=\" border: 0px solid #efefef; font-size: 12px; font-family:Gulim; color:RED \">" ,  list.get(key).toString(), "-> 실패",  "</li>"));
			  System.out.println(key+","+tm.get(key));
	    }
		
		
		html.append("</ul>");
		html.append("</div>");
		html.append("</html>");
		list.put(key++, currentText);
		return html.toString();
	}
	
	
	public void end() {
		frame.dispose();
		instance = null;	
	}
}
