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

/**
 * Used to write trace messages to the console; can disable all messages.
 */
public class TraceUtility {

  /**
   * Reference for trace messages
   */
  public static final String VIEW = "Jobs View";  
  
  /**
   * Reference for trace messages
   */
  public static final String JOB = "Job";
  /**
   * Reference for trace messages
   */
  public static final String UIJOB = "UIJob";
  /**
   * Reference for trace messages
   */
  public static final String LOCK = "Lock";
  /**
   * Reference for trace messages
   */
  public static final String RULE = "SchedulingRule";

  /**
   * Write trace statements. System.out.println with prefix tagging used for
   * simplicity. Can be turned off if want a quiet console.
   * 
   * @param key
   * @param msgSource
   * @param msg
   */
  public static void traceMsg(String key, String msgSource, String msg) {
    // make it true if you want console logging
    if (1 == 2)
      System.out.println(key + "-: " + msgSource + "-: " + msg);
  }
}