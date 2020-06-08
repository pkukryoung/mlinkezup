package com.kdj.mlink.ezup3.shop.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.eclipse.wb.swt.ResourceManager;

import com.google.common.io.Resources;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class ShopRadioButton {
	ButtonGroup group = null;
	JPanel panel;
	List<CodeItem> items;
	List<JRadioButton> radios = new ArrayList<>();
	String code =""; 
	
	public ShopRadioButton(List<CodeItem> items, JPanel panel) {
		group = new ButtonGroup();
		this.items = items;
		
		for(CodeItem item : items) {
			JRadioButton radio = new JRadioButton(item.getName());
			radio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					radio_SelectChanged(e);
				}
			});
			
			String[] bounds =   item.getFieldName().split(",");
			
			radio.setBounds(YDMAStringUtil.convertToInt(bounds[0]), YDMAStringUtil.convertToInt(bounds[1]), YDMAStringUtil.convertToInt(bounds[2]), YDMAStringUtil.convertToInt(bounds[3]));

			radio.setIcon(new ImageIcon(Resources.getResource("icons/unchecked-radio.png")));
			radio.setSelectedIcon(new ImageIcon(Resources.getResource("icons/checked-radio.png")));
			radio.setDisabledSelectedIcon(new ImageIcon(Resources.getResource("icons/unchecked-radio.png")));
			
			radios.add(radio);
			panel.add(radio);
			group.add(radio);
		}
	}
	
	public void radio_SelectChanged(ActionEvent e) {
		String command = e.getActionCommand();
		code =  items.stream().filter(p->p.getName().equals(command)).findFirst().orElse(new CodeItem("", "")).getCode();
		
		for(JRadioButton radio  :  radios) {
			if(command.equals(radio.getText())) {
				radio.setSelected(true);
			}else {
				radio.setSelected(false);
			}
		}
	}
	
	
	
	/*
	 *  ÄÚµå .. 
	 */
	public String  getSelectCode() {
		return code;
	}
	
	
	
}
