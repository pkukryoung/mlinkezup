package com.kdj.mlink.ezup.concurrency.jobs;

/*
 * "The Java Developer's Guide to Eclipse" by D'Anjou, Fairbrother, Kehn,
 * Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. All
 * Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;

import com.kdj.mlink.ezup.concurrency.TraceUtility;

/**
 *  Implements a looping construct to emulate real work being performed by a job.
 */
public class CustomJobWork {

  /**
   * Value for work to be performed
   */
  static final int PASS_TASK_VALUE = 2500;
  /**
   * Literal for monitor feedback.
   */
  static final String PASS1 = "task_one";
  /**
   * Literal for monitor feedback.
   */
  static final String PASS2 = "task_two";

  /**
   *  
   */
  public CustomJobWork() {
    super();
  }

  /**
   * Do silly work loop.
   * 
   * @param progressMonitor
   * @param jobName
   * @param jobType
   * @param pass
   */
  static void doWork(IProgressMonitor progressMonitor, String jobName,
      String jobType, String pass) {

    TraceUtility.traceMsg(jobType, pass, "monitor in use: "
        + progressMonitor);

    progressMonitor.beginTask("Running: " + jobName, PASS_TASK_VALUE);

    progressMonitor.setTaskName(pass);
    for (int j = 0; j < 80; j++) {
      progressMonitor.worked(PASS_TASK_VALUE / 80);
      pause(j);
    }
  }

  /**
   * @param job
   * @return State of <code>Job</code> for use in trace messages.
   */
  static String getStateValue(Job job) {
    switch (job.getState()) {
      case Job.NONE :
        return "Job.NONE";
      case Job.SLEEPING :
        return "Job.SLEEPING";
      case Job.WAITING :
        return "Job.WAITING";
      case Job.RUNNING :
        return "Job.RUNNING";
      default :
        return "Job.Unknown_State";
    }
  }

  /**
   * @param time
   */
  public static void pause(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
    }
  }

}