package com.kdj.mlink.ezup.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;

public class DialogProgressProvider extends ProgressProvider {
	
	public IProgressMonitor createMonitor(final Job job) {
		final IProgressMonitor[] m = new IProgressMonitor[] {
				new NullProgressMonitor() 
		};
		Display.getDefault().syncExec( new Runnable() {
			@Override
			public void run() {
				final ProgressMonitorDialog dialog = new ProgressMonitorDialog( Display.getDefault().getActiveShell() );
				dialog.setBlockOnOpen(true);
				dialog.setCancelable(true);
				dialog.open();
				job.addJobChangeListener( new JobChangeAdapter() {
					@Override
					public void done(IJobChangeEvent event) {
						// 취소시 처리
						close(dialog);
					}
				});
//				m[0] = new AccumulatingProgressMonitor(	dialog.getProgressMonitor(), Display.getDefault() );
			}
		});
		return m[0];
	}
	
	private void close(final ProgressMonitorDialog dialog) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				if( dialog != null && ! dialog.getShell().isDisposed() )
					dialog.close();
			}
		});
	}
}
