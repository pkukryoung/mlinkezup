package com.kdj.mlink.ezup;

import org.eclipse.core.runtime.Plugin;
//import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {
	
	public PreferenceInitializer() {
		super();
	}
	
	//환경설정 화면에서 restore default button 크릭시 수행 
	public void initializeDefaultPreferences() {
	    
		//2가지 방법 
		IEclipsePreferences defs = new DefaultScope().getNode(Application.PLUGIN_ID);
	    defs.putBoolean(PreferenceConstants.AUTO_LOGIN, false);
	    defs.put(PreferenceConstants.DIR_PATH,"C:\\Program Files");
//	    
//	    ScopedPreferenceStore pref = new ScopedPreferenceStore(new ConfigurationScope(), Application.PLUGIN_ID);
//	    IPreferenceStore store = Plugin. .getDefault().getPreferenceStore();
//	    pref.setDefault(PreferenceConstants.DIR_PATH,"C:\\Program Files");
//	    pref.setDefault(PreferenceConstants.AUTO_LOGIN, false);
//	    boolean aa = pref.getBoolean(PreferenceConstants.AUTO_LOGIN);
	    
	  }
}
