package com.kdj.mlink.ezup.concurrency.jobs;

/*
 * "The Java Developer's Guide to Eclipse"
 *   by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. 
 * All Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.IProgressConstants;

import com.kdj.mlink.ezup.concurrency.ConcurrencyPlugin;
import com.kdj.mlink.ezup.concurrency.TraceUtility;

/**
 * An implementation of job processing that is used to demonstrate the function and 
 * capability of a job run using the Eclipse job framework.
 */
public class CustomJob extends Job {

  private ILock accessLock = null;
  private int result = IStatus.OK;
  private boolean fail = false;
  private boolean useLimitedRule = false;
  private boolean rescheduleJob;
  private int rescheduleDelay;

  /**
   * @param name
   */
  public CustomJob(String name) {
    super(name);
  }

  /**
   * Values passed to this constructor are used to control behavior.
   * 
   * @param name - the initial job name, this value is changed dynamically.
   * @param fail - Request that this job fail by throwing an exception.
   * @param result - Define the expected job result as defined in the status
   * @param useLimitedRule - Request that a scheduling rule be used dynamically in job logic.
   */
  // JDG2E: 1A2 CustomJob - values passed to customize logic
  public CustomJob(String name, boolean fail, int result,
      boolean useLimitedRule) {
    super(name);
    this.fail = fail;
    this.result = result;
    this.useLimitedRule = useLimitedRule;
  }

  /**
   * This method is used to demonstrate job behavior. No real processing is performed,
   * the method uses calls to routines that include <code>Thread.sleep</code> requests.
   * 
   * If required, the job is forced to fail, acquires a lock, uses a rule, checks for 
   * cancelation, and reschedules itself.  The requested status is returned at the end. 
   * 
   */
  // JDG2E: 1A3 CustomJob - run method contains logic
  protected IStatus run(IProgressMonitor monitor) {
    setName(getName() + " : " + Thread.currentThread());
    printJobDetails(this, monitor);

    // JDG2E: 2B Fail a running Job
    if (fail)
      throw new RuntimeException("My Message");

    // JDG2E: 5B1 Job includes logic to acquire lock when appropriate
    if (accessLock != null) {
      accessLock.acquire();
      TraceUtility.traceMsg(TraceUtility.LOCK, this.getName(),
          "Using Lock: " + accessLock);
    }
    monitor.beginTask("Task: " + getName(),
        CustomJobWork.PASS_TASK_VALUE * 2);

    SubProgressMonitor subMonitor = new SubProgressMonitor(monitor,
        CustomJobWork.PASS_TASK_VALUE);

    // JDG2E: 3B1 Rule associated with running job
    if (useLimitedRule) {
      IJobManager jm = Platform.getJobManager();
      jm.beginRule(ResourcesPlugin.getWorkspace().getRoot(), monitor);

    }

    // Do the Part 1 work
    setName(getName() + " --> Part 1");
    CustomJobWork.doWork(subMonitor, getName(), TraceUtility.JOB,
        CustomJobWork.PASS1);
    subMonitor.done();

    // JDG2E: 3B2 Rule association removed
    if (useLimitedRule) {
      IJobManager jm = Platform.getJobManager();
      jm.endRule(ResourcesPlugin.getWorkspace().getRoot());
    }

    // JDG2E: 5B2 Job includes logic to release lock when appropriate
    if (accessLock != null)
      accessLock.release();

    // JDG2E: 1E3 Job includes logic to check if cancel request is received
    if (monitor.isCanceled()) {
      TraceUtility.traceMsg(TraceUtility.JOB, this.getName(),
          "Cancel Request Received");

      // JDG2E: 1E4 Status returned reflects a cancel request processed
      return new Status(IStatus.CANCEL, ConcurrencyPlugin.PLUGIN_ID,
          IStatus.CANCEL, "Job was canceled", null);
    }

    SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor,
        CustomJobWork.PASS_TASK_VALUE);

    // Do the Part 2 work
    setName(getName() + "  --> Part 2");
    CustomJobWork.doWork(subMonitor2, getName(), TraceUtility.JOB,
        CustomJobWork.PASS2);
    subMonitor2.done();

    if (rescheduleJob)
      this.schedule(rescheduleDelay);

    monitor.done();

    switch (result) {

      case IStatus.OK :
        return new Status(IStatus.OK, ConcurrencyPlugin.PLUGIN_ID,
            IStatus.OK, "Job Completed Fine", null);

      case IStatus.INFO :
        setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);

        return new Status(IStatus.INFO, ConcurrencyPlugin.PLUGIN_ID,
            IStatus.INFO, "Job Completed with Information", null);

      case IStatus.WARNING :

        return new Status(IStatus.WARNING, ConcurrencyPlugin.PLUGIN_ID,
            IStatus.WARNING, "Job Completed with Warning",
            new RuntimeException("Warning Exception Message"));

      case IStatus.ERROR :

        return new Status(IStatus.ERROR, ConcurrencyPlugin.PLUGIN_ID,
            IStatus.ERROR, "Job Completed with Error",
            new RuntimeException("Error Exception Message"));

      case IStatus.CANCEL :

        return new Status(IStatus.CANCEL, ConcurrencyPlugin.PLUGIN_ID,
            IStatus.CANCEL, "Job Ended with Cancel", null);

      default :
        return Status.OK_STATUS;
    }

  }

  /**
   * Identifies the lock, if any, that will be used by this job.
   * 
   * @param accessLock
   */
  // JDG2E: 5A2c Lock for Job identified
  public void setAccessLock(ILock accessLock) {
    this.accessLock = accessLock;
  }

  /**
   * Identifies that any job that is of this same type is a job in the same 
   * family.  This supports the use of a family image.
   * 
   * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
   */
  public boolean belongsTo(Object family) {
    // JDG2E Say yes to any instance of CustomJob as family
    
    return (family == CustomJob.class || family instanceof CustomJob);
  }

  /**
   *  Trace messages
   */
  private void printJobDetails(Job aJob, IProgressMonitor aMonitor) {
    TraceUtility.traceMsg(TraceUtility.JOB, aJob.getName(),
        "\n\tJob Details: " + "\n\t\tState:"
            + CustomJobWork.getStateValue(this) + " Job Priority: "
            + aJob.getPriority() + "\n\t\tThread: "
            + Thread.currentThread() + " at priority: "
            + Thread.currentThread().getPriority() + "\n\t\tMonitor: "
            + aMonitor);
  }

  /**
   * Tells job to reschedule itself.
   * @param rescheduleJob
   */
  public void setRescheduleJob(boolean rescheduleJob) {
    this.rescheduleJob = rescheduleJob;
  }

  /**
   * Identifies the delay that should be used for the schedule request when this is
   * a repeating job.
   * 
   * @param rescheduleDelay
   */
  public void setRescheduleDelay(int rescheduleDelay) {
    this.rescheduleDelay = rescheduleDelay;
  }

}