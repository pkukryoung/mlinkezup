package com.kdj.mlink.ezup.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;

import com.kdj.mlink.ezup.Application;
import com.kdj.mlink.ezup.PreferenceConstants;
import com.kdj.mlink.ezup.PreferencePage2;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class Login extends CommandDialog {

	public static String ID = "com.kdj.mlink.ezup.commands.Login.ID";
	private static final String LAST_USER = "last-user";
	private static final String PASSWORD = "password";
	private static final String SAVED = "saved-connections"; 
	private boolean auto_login;
	private ScopedPreferenceStore pref;

	String TITLE = "로그인";

	public Login(Shell parentShell, boolean auto_login) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM);
		this.auto_login = auto_login;
	}

	private Text txt_pw;
	private Text txt_id;
	static String host;
	private Button ck_saveLogin;
	private Button ck_autoLogin;

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblAccountDetails = new Label(composite, SWT.NONE);
		lblAccountDetails.setText("Account details");
		lblAccountDetails.setBounds(4, 3, 168, 20);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(60, 71, 58, 20);
		lblNewLabel.setText("User ID");

		txt_id = new Text(composite, SWT.BORDER);
		txt_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					txt_pw.setFocus();
				}
			}
		});
		txt_id.setBounds(124, 67, 271, 26);

		this.txt_id.setFocus();

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(48, 106, 71, 20);
		lblNewLabel_1.setText("Password");

		txt_pw = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		txt_pw.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_pw.getShell().setImeInputMode(SWT.ALPHA | SWT.PHONETIC);
			}
		});
		txt_pw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					okPressed();
				}
			}
		});
		txt_pw.setBounds(124, 102, 271, 26);

		ck_saveLogin = new Button(composite, SWT.CHECK);
		ck_saveLogin.setBounds(200, 144, 119, 16);
		ck_saveLogin.setText("아이디 저장");
		
	    
		ck_autoLogin = new Button(composite, SWT.CHECK);
		ck_autoLogin.setText("자동 로그인");
		ck_autoLogin.setSelection(false);
		ck_autoLogin.setBounds(200, 175, 119, 16);
	    ck_autoLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IEclipsePreferences prefs = new ConfigurationScope().getNode(Application.PLUGIN_ID);
				prefs.putBoolean(PreferenceConstants.AUTO_LOGIN, ck_autoLogin.getSelection());
				try {
					prefs.flush();
				} catch (BackingStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		ScopedPreferenceStore pref = new ScopedPreferenceStore(new ConfigurationScope(), Application.PLUGIN_ID);
	    boolean auto_login = pref.getBoolean(PreferenceConstants.AUTO_LOGIN);
	    ck_autoLogin.setSelection(auto_login);

		readAutoLogin();
		
		// 자동 로그인을 위한 처리 
		final Display display = Display.getCurrent();
		if(auto_login==true)  display.asyncExec(() -> close());

		return container;
	}

	private void readAutoLogin() {
		String id = "";
		String pw = "";
		
		ISecurePreferences root = SecurePreferencesFactory.getDefault();
		ISecurePreferences conn = root.node(SAVED);
		
		try {
			id = conn.get(LAST_USER, "");
			pw = conn.get(PASSWORD, ""); 
			txt_id.setText(id);
			txt_pw.setText(pw);
		} catch (StorageException e) {
			e.printStackTrace();
		}

		if (id.equals("") || id.equals("")) {
			return;
		}
		
//		if(auto_login=true) {
//			Login(id, pw);
//			super.okPressed();
//		}

	}

	private void setAutoLogin(String id, String pw) {
		
		ISecurePreferences root = SecurePreferencesFactory.getDefault();
		try { 
			ISecurePreferences conn = root.node(SAVED);
			conn.put(LAST_USER, id, false);
			conn.put(PASSWORD, pw, false);

		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			root.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void okPressed() {
		String id = this.txt_id.getText().trim();
		String pw = this.txt_pw.getText().trim();

		if (id.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "아이디를 입력하세요");
			this.txt_id.setFocus();
			return;
		}

		if (pw.length() == 0) {
			MessageDialog.openInformation(getShell(), TITLE, "비밀번호를 입력하세요");
			this.txt_pw.setFocus();
			return;
		}

		Login(id, pw);
		super.okPressed();
	
	}

	/*
	 * 일반 사용자 로그인..
	 */
	private void Login(String id, String pw) {
		
		setAutoLogin(id, pw);

	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
		System.exit(0);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		createButton(parent, IDialogConstants.OK_ID, "확인", false);
		createButton(parent, IDialogConstants.CANCEL_ID, "취소", false);

	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("M-Link Login");
		super.configureShell(newShell);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 311);
	}
}
