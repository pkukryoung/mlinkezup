package com.kdj.mlink.ezup3.shop.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class ShopCombo  {
	
	Map<Integer, CodeItem> data =  new  HashMap<>();  //코드 정보.. 
	Combo combo = null;
	List<CodeItem> list = null;
	
	public ShopCombo(Combo combo) {
		this.combo = combo;
		
		// TODO Auto-generated constructor stub
	}

	public void add (String text, String value, int index) {
		data.put(index,  new CodeItem(value,text) );
		combo.add(text, index);
	}
	
	public void add (String text, String value, String fieldName, int index) {
		data.put(index,  new CodeItem(value,text, fieldName) );
		combo.add(text, index);
	}
	
	public String getSelectionValue() {
		Integer sel =	   combo.getSelectionIndex();
		if(sel == -1) return "";
		
		CodeItem item = data.get(combo.getSelectionIndex());
		if(item == null ) return "";
		
		return item.getCode();
	}
	public String getSelectionValue1() {
		Integer sel =	   combo.getSelectionIndex();
		if(sel == 0) return "";

		return data.get(combo.getSelectionIndex()).getCode();
	}
	public String getSelectionFieldName() {
		Integer sel =	   combo.getSelectionIndex();
		if(sel == -1) return "";

		return data.get(combo.getSelectionIndex()).getFieldName();
	}
	
	public CodeItem getSelectionItem() {
		Integer sel =	   combo.getSelectionIndex();
		if(sel == -1) return null;

		return data.get(combo.getSelectionIndex());
	}
	
	
	public void selectValue (String code) {
		int ret = -1;
		for(Integer key : data.keySet()) {
			CodeItem item = data.get(key);
			if(item.getCode().equals(code)) {
				ret = key;
				break;
			}
		}
		if(ret > -1)
		{
			combo.select(ret);
		}else {combo.select(0); }
	}
	
	
	public void init(String defaultValue) {
		data.clear();
		combo.removeAll();
		if(!defaultValue.isEmpty()) {
			combo.add(defaultValue, 0);
		}else {
			combo.add(defaultValue, 0);
		}
		
	}
	
	
	/*
	 *  리스트 코드 바인딩.. 
	 */
	public ShopCombo setCodeBind(List<List<String>> contents,  int codeIndex, int valueIndex)
	{
		return  setCodeBind(contents ,codeIndex, valueIndex, "" );
		
		
	}
	
	
	public ShopCombo setCodeBind(List<List<String>> contents,  int codeIndex, int valueIndex, String filedName)
	{
		if(list!=null) list.clear();
		list =
				contents.stream()
				  .map( p->new CodeItem(p.get(codeIndex), p.get(valueIndex),filedName ))
				 .collect(Collectors.toList());
		
		return this;
	}
	
	
	
	
	public void build( String firstText, boolean isFirstText) {
		int i = 0;
		data.clear();
		combo.removeAll();
		if(isFirstText) {
			combo.add(firstText, i);
			++i;
		}
		for(CodeItem item :  list) {
			data.put(i,  item );
			combo.add(item.getName() , i);
			++i;
		}
		
		combo.select(0);
	}
	
	
	public void build( String firstText) {
		build( firstText, true);
	}
	
	
	public void setDataSource(List<CodeItem> list,String defaultText) {
		
		int i = 0;
		data.clear();
		combo.removeAll();
		
		if(!defaultText.isEmpty()) {
			combo.add(defaultText, i);
			++i;
		}
		
		for(CodeItem item :  list) {
			data.put(i,  item );
			combo.add(item.getName() , i);
			++i;
		}
		
		combo.select(0);
		
	}
	public void setDataSource(List<CodeItem> list) {
		setDataSource(list, "선택해주세요");
	}
	
	public void setDataSource() {
		build("선택해주세요");
	}
	
	
	public void select(int index) {
		this.combo.select(index);
	}
	
}
