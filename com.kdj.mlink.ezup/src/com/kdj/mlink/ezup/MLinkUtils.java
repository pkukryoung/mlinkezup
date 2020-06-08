package com.kdj.mlink.ezup;

import org.osgi.framework.Bundle;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class MLinkUtils {

	public static final String BUNDLE_ID = "com.kdj.mlink.ezup";

	public static void log(String message, Throwable exception) {
		Status status = new Status(IStatus.ERROR, BUNDLE_ID, 0, message, exception);
	}

	public static Image getImage(ImageRegistry reg, String key) {
		Image image = reg.get(key);
		if (image == null) {
			ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(BUNDLE_ID, key);
			reg.put(key, desc);
			image = reg.get(key);
		}
		return image;
	}
	
	public static void log(IStatus status) {
		Bundle b = Platform.getBundle(BUNDLE_ID);
		ILog log = Platform.getLog(b);
		log.log(status);
	}
	
}
