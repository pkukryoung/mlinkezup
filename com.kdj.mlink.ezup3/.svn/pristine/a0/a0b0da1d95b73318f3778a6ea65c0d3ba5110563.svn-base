package com.kdj.mlink.ezup3.shop.domesin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.shop.common.CodeItem;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class PanelDeliveryView {

	JPanel panel;
	List<ShopOrderMstDto> items;

	List<JPanel> panels = new ArrayList<>();

	JPanel panel_19;
	String code = "";
	JPanel panel_3;
	JPanel panel_4;
	private String img_path = "";
	JLabel lblNewLabel_6;
	
	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	int oldY = 69;

	private void removeComponent(JPanel parent, JPanel child) {
		Component[] componentList = parent.getComponents();

		// Loop through the components
		for (Component c : componentList) {

			// Find the components you want to remove
			if (c.equals(child)) {

				// Remove it
				parent.remove(c);
			}
		}

		// IMPORTANT
		parent.revalidate();
		parent.repaint();
	}

	private void removeComponent(JPanel p) {
		Component[] componentList = panel.getComponents();

		// Loop through the components
		for (Component c : componentList) {

			// Find the components you want to remove
			if (c.equals(p)) {

				// Remove it
				panel.remove(c);
			}
		}

		// IMPORTANT
		panel.revalidate();
		panel.repaint();
	}

	private int getComponetSize() {
		Component[] componentList = panel.getComponents();

		int ret = Arrays.asList(componentList).stream().filter(p -> p.getName() != null && p.getName().equals("1"))
				.mapToInt(p -> p.getBounds().height).sum();

		System.out.println("총사이즈 :" + ret);
		return ret;

	}

	private void removeComponent_pane2(JPanel parent, JPanel child, String panelName) {
		Component[] componentList = parent.getComponents();

		Rectangle ret_rectangle = null;
		Rectangle temp_rectangle = null;
		boolean isFind = false;

		// Loop through the components
		for (Component c : componentList) {

			// Find the components you want to remove
			if (c.equals(child)) {
				// Remove it
				ret_rectangle = new Rectangle(c.getBounds());
				parent.remove(c);
				isFind = true;
				continue;
			}

			if (isFind && c.getName() != null && c.getName().equals(panelName)) {
				temp_rectangle = new Rectangle(c.getBounds());
				c.setBounds(ret_rectangle);
				ret_rectangle = temp_rectangle;
			}
		}

		child.revalidate();
		child.repaint();

		// IMPORTANT
		parent.revalidate();
		parent.repaint();
	}

	private int getComponetCountLast(JPanel panel, String panel_name) {
		Component[] componentList = panel.getComponents();
		System.out.println();
		Component list = Arrays.asList(componentList).stream()
				.filter(p -> p.getName() != null && p.getName().equals("1"))
				.reduce((a, b) -> (a.getBounds().y > b.getBounds().y) ? a : b).orElse(null);

		if (list == null)
			return 69;

		int ret = Arrays.asList(list).stream().filter(p -> p.getName() != null && p.getName().equals(panel_name))
				.mapToInt(p -> p.getBounds().y).max().orElse(0);

		return ret + 32;
	}

	JPanel center;

	/*
	 * 
	 */
	public PanelDeliveryView(List<ShopOrderMstDto> items, JPanel panel, int oldY, List<ShopOrderMstDto> list) {

		this.items = items;
		this.panel = panel;

		int saleCost = 0;

		panel_19 = new JPanel();
		int y_size = 130 + ((items.size()) * 105);
		panel_19.setSize(1200, y_size);
		panel_19.setPreferredSize(new Dimension(1200, y_size));
		panel_19.setName("1");
		panel.add(panel_19);
		panel_19.setLayout(null);
		TitledBorder tb = new TitledBorder(new LineBorder(java.awt.Color.black));
//
//		List<Color> list_color = Arrays.asList(Color.RED, Color.BLACK, Color.BLUE, Color.WHITE, Color.PINK,
//				Color.YELLOW);
//
//		int idx = (int) (Math.random() * 5);

		panel_19.setBackground(Color.WHITE);
		// panel_19.set(Color.RED);
		JPanel panel_1 = new JPanel();

		panel_1.setBorder(tb);
		panel_1.setBounds(5, 5, 1039, 65);
		panel_19.add(panel_1);
		panel_1.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
		panel_1.setPreferredSize(new Dimension(1039, 65));
		panel_1.revalidate();

		JLabel lblNewLabel_4 = new JLabel(String.format(
				"<html>수취인 : <span style = \" font-size:1.2em; \">%s</span><br>휴대폰 : %s | 전화번호 : %s | (%s) %s<br>배송요청 : <span style = \"color : #04B404\">%s</span></html>",
				items.get(0).getReceive_name(), items.get(0).getReceive_cel(), items.get(0).getReceive_tel(),
				items.get(0).getReceive_zipcode(), items.get(0).getReceive_addr(), items.get(0).getDelv_msg()));
		panel_1.add(lblNewLabel_4);

		for (ShopOrderMstDto item : items) {
			try {
				img_path = String.format("%s\\%s",
						YDMASessonUtil
								.getAppPath(YDMAProperties.getInstance().getAppProperty("Product.image.productImage2")),
						YDMASessonUtil.getImageFolderName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JPanel panel_2 = new JPanel(new BoxLayout(panel_19, BoxLayout.Y_AXIS));
			panel_2.setName("panel_2");

			// panel_2.setSize(1039, 103);
			panel_2.setBounds(5, oldY, 1039, 103);
			panel_19.add(panel_2);
			panel_2.setLayout(null);

			panels.add(panel_2);
			JPanel panel_9 = new JPanel();
			panel_9.setName("panel_9");
			panel_9.setBounds(1046, oldY + 15, 64, 38);
			panel_19.add(panel_9);
			panel_9.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

			JButton btnDelete = new JButton("삭제");

			panel_9.add(btnDelete);

			oldY = panel_2.getBounds().y + 103;

			JPanel panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setBounds(0, 0, 109, 101);
			panel_2.add(panel_5);
			panel_5.setBorder(tb);
			ImageIcon image1 = new ImageIcon(String.format("%s\\%s%s", img_path, item.getCompayny_goods_cd(), ".jpg")); // 이미지
			// 경로
			panel_5.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			JLabel lblNewLabel_10 = new JLabel(image1, SwingConstants.CENTER);
			// JLabel lblNewLabel_10 = new JLabel("<html><img src =
			// "+String.format("%s\\%s%s", img_path, item.getCompayny_goods_cd(), ".jpg")+"
			// width = \"100\" height = \"100\"</html>");
			panel_5.add(lblNewLabel_10);

			JPanel panel_6 = new JPanel();
			panel_6.setBounds(108, 0, 413, 70);
			panel_6.setBorder(tb);
			panel_2.add(panel_6);
			panel_6.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_6.setBackground(Color.WHITE);

			JLabel lblNewLabel_5 = new JLabel(String.format(
					"<html><p style = \"color:red\">%s</p><p style = \"color : gray\">[상품코드 : %s] [업체코드 : %s]</p>%s</html>",
					item.getDosinstatus() == null ? "" : item.getDosinstatus(), item.getCompayny_goods_cd(), "TV1001",
					item.getP_product_name()));
			panel_6.add(lblNewLabel_5);

			JPanel panel_7 = new JPanel();
			panel_7.setBounds(520, 0, 519, 70);
			panel_7.setBorder(tb);
			panel_2.add(panel_7);
			panel_7.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_7.setBackground(Color.YELLOW);
			lblNewLabel_6 = new JLabel(String.format(
					"<html><span style = \"color : #58FA58\">주문관리메모 :</span> <span style = \"color : #2E64FE\">%s</span><br>"
							+ "<span style = \"color : #58FA58\">솔루션주문번호 :</span> <span style = \"color : #2E64FE\">%s</span>"
							+ "<br><span style = \"color : #58FA58\">옵션내용 :</span> <span style = \"color : #2E64FE\">%s</span></html>",
					item.getShop_userid(), item.getOrder_id(), item.getP_sku_value()));
			panel_7.add(lblNewLabel_6);

			JPanel panel_8 = new JPanel();
			panel_8.setBounds(520, 69, 519, 32);
			panel_2.add(panel_8);
			panel_8.setLayout(null);

			JPanel panel_10 = new JPanel();
			panel_10.setBounds(0, 0, 130, 32);
			panel_10.setBorder(tb);
			panel_8.add(panel_10);
			panel_10.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_10.setBackground(Color.WHITE);
			JLabel lblNewLabel_9 = new JLabel(item.getSale_cost());
			panel_10.add(lblNewLabel_9);
			lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel_11 = new JPanel();
			panel_11.setBounds(387, 0, 132, 32);
			panel_11.setBorder(tb);
			panel_8.add(panel_11);
			panel_11.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_11.setBackground(Color.WHITE);

			saleCost += Integer.parseInt(item.getSale_cost()) * Integer.parseInt(item.getSale_cnt());

			JLabel lblNewLabel_13 = new JLabel(
					String.valueOf(Integer.parseInt(item.getSale_cost()) * Integer.parseInt(item.getSale_cnt())));
			panel_11.add(lblNewLabel_13);
			lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel_12 = new JPanel();
			panel_12.setBounds(129, 0, 130, 32);
			panel_12.setBorder(tb);
			panel_8.add(panel_12);
			panel_12.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_12.setBackground(Color.WHITE);

			JLabel lblNewLabel_11 = new JLabel(item.getSale_cnt());
			panel_12.add(lblNewLabel_11);
			lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel_13 = new JPanel();
			panel_13.setBounds(258, 0, 130, 32);
			panel_13.setBorder(tb);
			panel_8.add(panel_13);
			panel_13.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_13.setBackground(Color.WHITE);

			JLabel lblNewLabel_12 = new JLabel(item.getDelivery_method_str2());
			panel_13.add(lblNewLabel_12);
			lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel_18 = new JPanel();
			panel_18.setBackground(Color.WHITE);
			panel_18.setBounds(108, 69, 413, 32);
			panel_2.add(panel_18);

			String[] CBmenu_1 = item.getDosinoption().split(",");
			JComboBox comboBox = new JComboBox(CBmenu_1);
			comboBox.setPreferredSize(new Dimension(350, 23));
			panel_18.setBorder(tb);
			panel_18.setLayout(new java.awt.GridLayout(0, 1, 0, 0));
			panel_18.add(comboBox);
			if (item.getDosinoption() != null) {
				if (!item.getP_sku_value().equals("")) {
					if (!item.getDosinoption().contains(item.getP_sku_value())) {
						comboBox.setEnabled(true);
					} else {
						comboBox.setEnabled(false);
					}
				} else {
					comboBox.setEnabled(false);
				}
			} else {
				comboBox.setEnabled(false);
			}
			comboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!comboBox.getSelectedItem().equals("옵션선택")) {
						for(ShopOrderMstDto dto : list) {
							if(dto.getOrder_id().equals(item.getOrder_id())) {
								dto.setP_sku_value(comboBox.getSelectedItem().toString());
							}
						}
					}

				}
			});

			btnDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							removeComponent_pane2(panel_19, panel_9, "panel_9");
							removeComponent_pane2(panel_19, panel_2, "panel_2");

							panel.revalidate();
							panel.repaint();
							Rectangle r = null;
							int find = 0;
							for (int i = 0; i < panels.size(); ++i) {
								if (panels.get(i).equals(panel_2)) {

									find = i;

									break;
								}
							}

							panels.remove(find);
							if (panels.isEmpty()) {
								removeComponent(panel, panel_19);
								panel.setPreferredSize(new Dimension(1100, getComponetSize()));
								panel.revalidate();
								panel.repaint();
							}
							list.remove(item);// 리스트 삭제하기
						}
					});

				}
			});
		}

		panel_3 = new JPanel();
		panel_3.setBounds(5, oldY, 1039, 32);
		panel_19.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_14 = new JPanel();
		panel_14.setBounds(0, 1, 908, 32);
		panel_14.setBorder(tb);
		panel_3.add(panel_14);
		panel_14.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_7 = new JLabel("<html><B style = \"color : #31B404\">TV100036</B> 배송비계</html>");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_14.add(lblNewLabel_7);

		JPanel panel_15 = new JPanel();
		panel_15.setBounds(907, 2, 132, 32);
		panel_15.setBorder(tb);
		panel_3.add(panel_15);
		panel_15.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_8 = new JLabel(
				"<html><span style = \"font-size : bold\">" + items.get(0).getDelv_cost() + "</span><html>");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_15.add(lblNewLabel_8);

		oldY = panel_3.getBounds().y + panel_3.getBounds().height;

		panel_4 = new JPanel();
		panel_4.setBounds(5, oldY, 1039, 32);
		panel_4.setName("panel_4");
		panel_19.add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_16 = new JPanel();
		panel_16.setBounds(0, 0, 908, 32);
		panel_16.setBorder(tb);
		panel_4.add(panel_16);
		panel_16.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_14 = new JLabel(
				"<html><span style = \"color : #31B404; font-size : bold\">TV100036</span> 업체소계<html>");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_16.add(lblNewLabel_14);

		JPanel panel_17 = new JPanel();
		panel_17.setBounds(907, 0, 132, 32);
		panel_17.setBorder(tb);
		panel_4.add(panel_17);
		panel_17.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_15 = new JLabel("<html><span style = \"font-size : bold\">" + saleCost + "</span><html>");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel_17.add(lblNewLabel_15);

		this.oldY = oldY;

		getComponetSize();
		// System.out.println("패널19 사이즈:" + panel_19.getBounds().height);

	}

	protected void setOptionCheck() {
		// TODO Auto-generated method stub

	}

	/*
	 * 코드 ..
	 */
	public String getSelectCode() {
		return code;
	}

}
