package com.kdj.mlink.ezup.concurrency;

/*
 * "The Java Developer's Guide to Eclipse"
 *   by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. 
 * All Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.kdj.mlink.ezup.concurrency.jobs.CustomJobListener;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConcurrencyPlugin extends AbstractUIPlugin {
  /**
   *  
   */
  public ConcurrencyPlugin() {
    super();
    plugin = this;
    try {
      resourceBundle = ResourceBundle
          .getBundle("com.ibm.jdg2e.concurrency.ConcurrencyPluginResources");
    } catch (MissingResourceException x) {
      resourceBundle = null;
    }
  }
  //The shared instance.
  private static ConcurrencyPlugin plugin;
  /**
   * Convenience string; avoids calls to bundle and symbolic name.
   */
  public static final String PLUGIN_ID = "com.ibm.jdg2e.concurrency";
  //Resource bundle.
  private ResourceBundle resourceBundle;
  private IJobChangeListener jcl;

  /**
   * @return Returns the shared instance.
   */
  public static ConcurrencyPlugin getDefault() {
    return plugin;
  }

  /**
   * @param key
   * @return the string from the plugin's resource bundle, 
   * or 'key' if not found.
   */
  public static String getResourceString(String key) {
    ResourceBundle bundle = ConcurrencyPlugin.getDefault().getResourceBundle();
    try {
      return (bundle != null ? bundle.getString(key) : key);
    } catch (MissingResourceException e) {
      return key;
    }
  }

  /**
   * @return the plugin's resource bundle.
   */
  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

  /**
   * @return the shared <code>IJobChangeListener</code> used by code in this plug-in.
   */
  public IJobChangeListener getJCL() {

    if (jcl == null)
      jcl = new CustomJobListener();

    return jcl;
  }

}