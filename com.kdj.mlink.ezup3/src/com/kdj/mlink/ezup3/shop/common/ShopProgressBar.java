package com.kdj.mlink.ezup3.shop.common;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;

import com.google.common.io.Resources;
import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.ui.CommandDialog;

public class ShopProgressBar {
	public static boolean IsTaskCancle = false;
	
	private final static int Search = 1000;
	private final static int Progress_Text = 2000;

	private static ShopProgressBar instance = new ShopProgressBar();
	JFrame frame = null;
	
	 JTextArea txtLog; // 로그..
		JProgressBar progressBar; // 프로그레스바..

	public static ShopProgressBar get() {
		return instance;
	}

	final JPanel ShopPanel = new JPanel() {
		public void paintComponent(Graphics g) {

			URL url = Resources.getResource("icons/loading.gif");
			ImageIcon icon = new ImageIcon(url);

			if (icon != null) {
				g.drawImage(icon.getImage(), 0, 0, this);
				setOpaque(false);
				super.paintComponent(g);
			}

			Graphics2D g2d = (Graphics2D) g.create();
			// 50% transparent Alpha
			// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			// 100% transparent Alpha
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));

			g2d.setColor(getBackground());
			g2d.fill(getBounds());
			g2d.dispose();
		}
	};

	public void start(Shell shell) {

		Monitor primary = shell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				instance.initialize(x, y);
			}
		});
	}

	
	public  void start(Shell shell,String title, int maximum  ){
		Monitor primary = shell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance.initialize_progress(x , y,title, maximum);
            }
        });
    }
	
	/*
	 * 조회 페이지...
	 */
	private void initialize(int x, int y) {

		frame = new JFrame("Wait ....");
		frame.setDefaultCloseOperation(JFrame.WAIT_CURSOR);
		frame.setUndecorated(true); // 이걸 해줘야 타이틀 바가 사라진다.
		frame.setBackground(new Color(0, 0, 0, 0));//

		frame.setContentPane(ShopPanel);
		frame.setSize(130, 130);
		frame.setVisible(true);

		frame.setLocation(x, y);
	}

	public void initialize_progress(int x, int y, String title, int maximum) {
		IsTaskCancle = false;
		
		JButton btnCancle; // 취소버튼..
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		int maxValue = 0;
		int width = (int) (screen.width / 1.8);
		int height = (int) (screen.height / 1.8); // 840/12*9;
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.setPreferredSize(new Dimension(width, height));
		panel.setMaximumSize(panel.getPreferredSize());
		panel.setMinimumSize(panel.getPreferredSize());

		frame.setContentPane(panel);

		JPanel panleTop = new JPanel();
		panleTop.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "처리상태..."));
		txtLog = new JTextArea();
		txtLog.setFont(new Font("Gulim", Font.BOLD, 20));
		txtLog.setForeground(Color.BLUE);
		// txtLog.setBounds(13, 13, 300, 400);
		JScrollPane scrollPane = new JScrollPane(txtLog);
		panleTop.setLayout(new GridLayout(1, 1));
		panleTop.setPreferredSize(new Dimension(width - 10, height - 150));
		panleTop.setMaximumSize(panleTop.getPreferredSize());
		panleTop.setMinimumSize(panleTop.getPreferredSize());
		panleTop.add(scrollPane);

		JPanel panelProgressBar = new JPanel();
		panelProgressBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "진행상태"));

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.white);
		// progressBar.setForeground(new Color(0, 126, 255,255));

		progressBar.setString("0%");
		progressBar.setMaximum(maximum);
		panelProgressBar.setLayout(new GridLayout(1, 1));
		panelProgressBar.setPreferredSize(new Dimension(width - 10, 50));
		panelProgressBar.setMaximumSize(panelProgressBar.getPreferredSize());
		panelProgressBar.setMinimumSize(panelProgressBar.getPreferredSize());
		panelProgressBar.add(progressBar);

		/* bottom 생성 */
		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));

		btnCancle = new JButton("취소");

		btnCancle.addActionListener(new AbstractAction() {
			
			// TaskCancleListener listener = this.cn;
			@Override
			public void actionPerformed(ActionEvent e) {
				IsTaskCancle = true;
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {

						frame.dispose();
					}
				});
				
				
			}
		});

		panelBottom.setBackground(Color.white);
		btnCancle.setSize(200, 100);
		panelBottom.setLayout(new BorderLayout());
		panelBottom.setPreferredSize(new Dimension(width - 40, 50));
		panelBottom.setMaximumSize(panelProgressBar.getPreferredSize());
		panelBottom.setMinimumSize(panelProgressBar.getPreferredSize());
		panelBottom.add(btnCancle, BorderLayout.EAST);

		panel.add(panleTop);
		panel.add(panelProgressBar);
		panel.add(panelBottom);
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setBackground(Color.white);
		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	
	public void setValue(String message, int value){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                txtLog.append(message + "\n");  // 로그 내용을 JTextArea 위에 붙여주고
                txtLog.setCaretPosition(txtLog.getDocument().getLength());  // 맨아래로 스크롤한다.
                progressBar.setValue(value);
                progressBar.setString(value+ "%");
            }
        });
    }
	
	public void finsh() {
		if (frame == null)
			return;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				frame.dispose();
			}
		});
	}

}
