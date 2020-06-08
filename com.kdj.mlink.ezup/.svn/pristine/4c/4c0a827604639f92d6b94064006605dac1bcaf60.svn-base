package com.kdj.mlink.ezup.ui;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
	public MenuModel parent; 
    
    public List<MenuModel> child = new ArrayList<>(); 
        
    String name;
    String id;
    
    public MenuModel(MenuModel parent, String name, String cmdId) {
        this.parent = parent;
        this.name =name;
        this.id = cmdId;
    }
 
    public String getId() {
    	return id;
    }
    @Override
    public String toString() {
        return name;
    }

}
