package com.kdj.mlink.ezup.ui;

import org.eclipse.core.runtime.ProgressMonitorWrapper;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.widgets.Display;

public class AccumulatingProgressMonitor extends ProgressMonitorWrapper {
	private Display display;
	private Collector collector;
	private String currentTask = "";
	
	private class Collector implements Runnable {
        private String subTask;
        private double worked;
        private IProgressMonitor monitor;

        public Collector(String subTask, double work, IProgressMonitor monitor) {
            this.subTask = subTask;
            this.worked = work;
            this.monitor = monitor;
        }

        public void worked(double workedIncrement) {
            this.worked = this.worked + workedIncrement;
        }

        public void subTask(String subTaskName) {
            this.subTask = subTaskName;
        }

        public void run() {
            clearCollector(this);
            if (subTask != null) {
				monitor.subTask(subTask);
			}
            if (worked > 0) {
				monitor.internalWorked(worked);
			}
        }
    }

    public AccumulatingProgressMonitor(IProgressMonitor monitor, Display display) {
        super(monitor);
        Assert.isNotNull(display);
        this.display = display;
    }

    public void beginTask(final String name, final int totalWork) {
        synchronized (this) {
            collector = null;
        }
        display.asyncExec(new Runnable() {
            public void run() {
                currentTask = name;
                getWrappedProgressMonitor().beginTask(name, totalWork);
            }
        });
    }

    private synchronized void clearCollector(Collector collectorToClear) {
        // Check if the accumulator is still using the given collector.
        // If not, don't clear it.
        if (this.collector == collectorToClear) {
			this.collector = null;
		}
    }

    private void createCollector(String subTask, double work) {
        collector = new Collector(subTask, work, getWrappedProgressMonitor());
        display.asyncExec(collector);
    }

    public void done() {
        synchronized (this) {
            collector = null;
        }
        display.asyncExec(new Runnable() {
            public void run() {
                getWrappedProgressMonitor().done();
            }
        });
    }

    public synchronized void internalWorked(final double work) {
        if (collector == null) {
            createCollector(null, work);
        } else {
            collector.worked(work);
        }
    }

    public void setTaskName(final String name) {
        synchronized (this) {
            collector = null;
        }
        display.asyncExec(new Runnable() {
            public void run() {
                currentTask = name;
                getWrappedProgressMonitor().setTaskName(name);
            }
        });
    }

    public synchronized void subTask(final String name) {
        if (collector == null) {
            createCollector(name, 0);
        } else {
            collector.subTask(name);
        }
    }

    public synchronized void worked(int work) {
        internalWorked(work);
    }

    public void clearBlocked() {
        final IProgressMonitor pm = getWrappedProgressMonitor();
        if (!(pm instanceof IProgressMonitorWithBlocking)) {
			return;
		}

        display.asyncExec(new Runnable() {
            public void run() {
                ((IProgressMonitorWithBlocking) pm).clearBlocked();
                Dialog.getBlockedHandler().clearBlocked();
            }
        });
    }

    public void setBlocked(final IStatus reason) {
        final IProgressMonitor pm = getWrappedProgressMonitor();
        if (!(pm instanceof IProgressMonitorWithBlocking)) {
			return;
		}

        display.asyncExec(new Runnable() {
            public void run() {
                ((IProgressMonitorWithBlocking) pm).setBlocked(reason);
                Dialog.getBlockedHandler().showBlocked(pm, reason, currentTask);
            }
        });
    }
    
}
