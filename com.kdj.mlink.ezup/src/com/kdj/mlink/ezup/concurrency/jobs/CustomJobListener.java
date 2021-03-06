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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.progress.WorkbenchJob;

import com.kdj.mlink.ezup.concurrency.ConcurrencyPlugin;
import com.kdj.mlink.ezup.concurrency.TraceUtility;

/**
 * Writes job event details to the console and the list in the Jobs Demo view.
 * JDG2E: 1C2 Job Change Listener Definition
 */

public class CustomJobListener implements IJobChangeListener {

  private List list = null;

  /**
   * Constructor
   */
  public CustomJobListener() {
    super();
  }

  /**
   * Constructor that will accept a list; this is used later 
   * to  append messages for display in view.
   * 
   * @param list
   */
  public CustomJobListener(List list) {
    super();
    this.list = list;
  }

  /**
   * JDG2E: 1C3 Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void aboutToRun(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB,
        "JobChangeEvent: aboutToRun", "for Job: " + event.getJob()
            + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));
    addToList(TraceUtility.JOB, "JobChangeEvent: aboutToRun",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));

  }

  /**
   * Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void awake(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB, "JobChangeEvent: awake",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));
    addToList(TraceUtility.JOB, "JobChangeEvent: awake", "for Job: "
        + event.getJob() + " job state: "
        + CustomJobWork.getStateValue(event.getJob()));

  }

  /**
   * Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void done(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB, "JobChangeEvent: done",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob())
            + " event status: " + event.getResult());
    addToList(TraceUtility.JOB, "JobChangeEvent: done", "for Job: "
        + event.getJob() + " job state: "
        + CustomJobWork.getStateValue(event.getJob())
        + " event status: " + getCodeValue(event.getResult().getCode()));

  }

  /**
   * Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#running(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void running(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB, "JobChangeEvent: running",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));
    addToList(TraceUtility.JOB, "JobChangeEvent: running", "for Job: "
        + event.getJob() + " job state: "
        + CustomJobWork.getStateValue(event.getJob()));

  }

  /**
   * Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#scheduled(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void scheduled(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB,
        "JobChangeEvent: scheduled", "for Job: " + event.getJob()
            + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));
    addToList(TraceUtility.JOB, "JobChangeEvent: scheduled",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));

  }

  /**
   * Writes messages as events occur
   * 
   * @see org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse.core.runtime.jobs.IJobChangeEvent)
   */
  public void sleeping(IJobChangeEvent event) {
    TraceUtility.traceMsg(TraceUtility.JOB, "JobChangeEvent: sleeping",
        "for Job: " + event.getJob() + " job state: "
            + CustomJobWork.getStateValue(event.getJob()));
    addToList(TraceUtility.JOB, "JobChangeEvent: sleeping", "for Job: "
        + event.getJob() + " job state: "
        + CustomJobWork.getStateValue(event.getJob()));

  }

  /**
   * @param string
   * @param string2
   * @param string3
   */
  private void addToList(final String key, final String context,
      final String msg) {
    if (list != null) {
      // Another example of a syncExec or asyncExec being 
      // replaced by a UI/Workbench Job
      //         list.getDisplay().syncExec(new Runnable() {
      //
      //            public void run() {
      //               list.add(key + " : " + context + " : " + msg);
      //               list.setSelection(list.getItemCount() - 1);
      //            }
      //         });

      WorkbenchJob job = new WorkbenchJob("updateList") {
        public IStatus runInUIThread(IProgressMonitor monitor) {
          if (list !=null && !list.isDisposed()) {
            list.add(key + " : " + context + " : " + msg);
            list.setSelection(list.getItemCount() - 1);
          }
          return new Status(IStatus.OK, ConcurrencyPlugin.PLUGIN_ID,
              IStatus.OK, "added to list", null);
        }
      };
      job.schedule();
    }
  }

  /**
   * @param list
   *          The list to set.
   */
  public void setList(List list) {
    this.list = list;
  }

  /**
   * @param code An <code>int</code> value from a Job status.
   * @return a readable representation of the status code for a job.
   */
  protected String getCodeValue(int code) {
    switch (code) {
      case Job.NONE :
        return "IStatus.OK";
      case Job.SLEEPING :
        return "IStatus.INFO";
      case Job.WAITING :
        return "IStatus.WARNING";
      case Job.RUNNING :
        return "IStatus.ERROR";
      default :
        return "IStatus.Unknown_Code";
    }
  }

}

