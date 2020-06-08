package com.kdj.mlink.ezup.ui;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


class MenuTreeLabelProvider  extends LabelProvider {
    
    @Override
    public String getText(Object element) {
        return super.getText(element);
    }
    
   
    @Override
    public Image getImage(Object element) {
    	String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
    	MenuModel MenuID = (MenuModel)element;
    	if (MenuID.getId().equals(""))
			imageKey = ISharedImages.IMG_OBJ_FOLDER;
    	return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//        return super.getImage(element);
    }
}