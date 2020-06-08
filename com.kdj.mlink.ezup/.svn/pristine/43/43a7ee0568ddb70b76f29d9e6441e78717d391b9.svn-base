package com.kdj.mlink.ezup.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.kdj.mlink.ezup.model.IDataProviderPool;
import com.kdj.mlink.ezup.model.INxtService;

public class Shutdown {
	public static boolean execute(Shell shell, IEventBroker broker,
			  UISynchronize sync, final INxtService nxt, final IDataProviderPool pool) {

			if (!MessageDialog.openConfirm(shell, "Shutdown Offspring",
				"Are you sure you want to shutdown Offspring?")) {
			  return false;
			}

			final StartupDialog dialog = new StartupDialog(shell, sync);
			dialog.setBlockOnOpen(true);
			dialog.showOKButton(false);
			dialog.create();

			Job startupJob = new Job("Startup Job") {

			  volatile boolean DONE = false;

			  @Override
			  protected IStatus run(final IProgressMonitor monitor) {
				Thread cancelThread = new Thread(new Runnable() {

				  @Override
				  public void run() {
					while (!DONE) {
					  try {
						Thread.sleep(500);
						if (monitor.isCanceled()) {
						  System.exit(-1);
						  return;
						}
					  }
					  catch (InterruptedException e) {
						return;
					  }
					}
				  }

				});
				cancelThread.start();

				try {
				  monitor.beginTask("Shutting down dataprovider pools", IProgressMonitor.UNKNOWN);
				  pool.destroy();

				  monitor.setTaskName("Shutting down NXT");
				  nxt.shutdown();

				  monitor.done();
				}
				finally {
				  DONE = true;
				}
				return Status.OK_STATUS;
			  }
			};
			Job.getJobManager().setProgressProvider(new ProgressProvider() {

			  @Override
			  public IProgressMonitor createMonitor(Job job) {
				return dialog.getProgressMonitor();
			  }
			});
			startupJob.schedule();
			dialog.open();
			//
			//
			// BusyIndicator.showWhile(shell.getDisplay(), new Runnable() {
			//
			// @Override
			// public void run() {
			// nxt.shutdown();
			// pool.destroy();
			// }
			// });

			return true;
			}
}
