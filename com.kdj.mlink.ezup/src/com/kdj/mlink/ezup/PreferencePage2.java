package com.kdj.mlink.ezup;

import java.io.IOException;
import java.util.prefs.Preferences;

//import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class PreferencePage2 extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private ScopedPreferenceStore pref;

	public PreferencePage2() {
		super(GRID);
		pref = new ScopedPreferenceStore(new ConfigurationScope(), Application.PLUGIN_ID);
		setPreferenceStore(pref);
	}

	public PreferencePage2(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public PreferencePage2(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public PreferencePage2(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		BooleanFieldEditor boolEditor = new BooleanFieldEditor(PreferenceConstants.AUTO_LOGIN, "Login automatically at startup", getFieldEditorParent());
		addField(boolEditor);
		
		String[] filterExtension = { "*.file extension" };
		addField(new DirectoryFieldEditor(PreferenceConstants.DIR_PATH,"&Directory preference:", getFieldEditorParent()));
		FileFieldEditor filePathPrefEditor = new FileFieldEditor(PreferenceConstants.FILE_PATH, "&File Path preference:", getFieldEditorParent());
		filePathPrefEditor.setFileExtensions(filterExtension);
		addField(filePathPrefEditor);
		
	    addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,  "&An example of a boolean preference",  getFieldEditorParent()));
	    
	    addField(new RadioGroupFieldEditor( PreferenceConstants.P_CHOICE, "An example of a multiple-choice preference",  1, 
	    		new String[][] { { "&Choice 1", "choice1" }, {"C&hoice 2", "choice2" }  }, getFieldEditorParent()));
	    addField(new StringFieldEditor(PreferenceConstants.P_STRING, "A &text preference:", getFieldEditorParent()));
		
	}
	
	@Override
	public boolean performOk() {
		try {
			pref.save();
		} catch (IOException e) {
			MLinkUtils.log("Unable to save general preference page preferences", e);
		}
		return super.performOk();
	}

}
