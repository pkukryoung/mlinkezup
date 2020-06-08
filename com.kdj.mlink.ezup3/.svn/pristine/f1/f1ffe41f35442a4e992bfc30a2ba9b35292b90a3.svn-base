package com.kdj.mlink.ezup3.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class YDMAGridColumnList {
	ArrayList<YDMAGridColumn> columns;
	ArrayList<String> list;
	public YDMAGridColumnList() {
		columns = new ArrayList<YDMAGridColumn>();
		list = new ArrayList<String>();
	}
	
	public void add(int  seq, String filed, String text, int width) {
		YDMAGridColumn column = new YDMAGridColumn(seq, filed, text, width);
		columns.add(column);
		list.add(filed);
	}
	
	public ArrayList<String> getFields() {
		return list;
	}
	
	public ArrayList<YDMAGridColumn> getColumns() {
		return columns;
	}
	
	
	public String getFieldName(int index) {
		String ret = "";
		for(YDMAGridColumn item :  columns)
    	{
    		if(item.getSeq() == index) {
    			ret = item.getFiledName();
    			break;
    		}
    	}
		return ret;
	}
	
    public String getFieldValue(Object obj,  String fieldName) {
    	String ret = "";
    	try{
	      
    		Class cl = obj.getClass();
			Field field =  cl.getDeclaredField(fieldName.toLowerCase()); 	
			field.setAccessible(true);
			ret = field.get(obj) +"";
	    }catch (Exception e){
	    	System.out.println(fieldName);
	        e.printStackTrace();
	    }
    	
    	return ret;
    }

}
