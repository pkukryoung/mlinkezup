package com.kdj.mlink.ezup.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class MenuTreeContentProvider implements ITreeContentProvider {
	 
    
    @Override
    public Object[] getElements(Object inputElement) {
        return ((MenuModel)inputElement).child.toArray();
    }
    

    @Override
    public Object[] getChildren(Object parentElement) {
        return getElements(parentElement);
    }

    @Override
    public Object getParent(Object element) {
        if( element == null) return null;
        return ((MenuModel)element).parent;
    }

    
    @Override
    public boolean hasChildren(Object element) {
        return ((MenuModel)element).child.size() > 0;
    }

   
    @Override
    public void dispose() {

    }


	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

   
}