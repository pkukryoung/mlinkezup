package com.kdj.mlink.ezup3.shop.common;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.google.common.io.Resources;

public class ShopLoadingBar implements IShopProgress {
	
	JFrame frame = null;
	
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
	
	
	public ShopLoadingBar(Shell shell) {
		Monitor primary = shell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				initialize(x, y);
			}
		});
	}
	
	
	private void initialize(int x, int y) {

		frame = new JFrame("Wait ....");
		
		frame.setUndecorated(true); // 이걸 해줘야 타이틀 바가 사라진다.
		frame.setBackground(new Color(0, 0, 0, 0));//

		frame.setContentPane(ShopPanel);
		frame.setSize(130, 130);
		frame.setVisible(true);

		frame.setLocationRelativeTo(null); // setLocation(x, y);
	}
	
	@Override
	public void close() throws Exception {
		Thread.sleep(100);
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
