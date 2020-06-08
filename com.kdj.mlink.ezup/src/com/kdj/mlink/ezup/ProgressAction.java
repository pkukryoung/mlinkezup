package com.kdj.mlink.ezup;

import java.awt.TextField;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.kdj.mlink.ezup.ui.DialogProgressProvider;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.UIJob;

public class ProgressAction extends Action {
	private static final int TASK_AMOUNT = 100; 
	
//	protected void createJobs() {
//	public void run() {
//		System.out.println("Preparing to run progress Service");
//		IProgressService progressService =
//				PlatformUI.getWorkbench().getProgressService();
//		try {
//			progressService.busyCursorWhile(new IRunnableWithProgress(){
//				public void run(IProgressMonitor monitor)throws InterruptedException {
//					System.out.println("Preparing a job");
//					final Job job = new Job("Online Export"){
//						@Override
//						protected IStatus run(IProgressMonitor monitor) {
//							int ticks = 200;
//							int sleep = 10;
//							try {
//								monitor.beginTask("BEGIN JOB", ticks);
//								monitor.setTaskName("Begin Job"); 
//								for (int i = 0; i < ticks; i++) { 
//									if (monitor.isCanceled()) 
//										return Status.CANCEL_STATUS; 
//									monitor.subTask("Processing tick #" + i);
//									monitor.worked(1);
//								}
//							} finally { monitor.done();
//							}
//							Display.getDefault().asyncExec(new Runnable() {
//								@Override public void run() { 
//									MessageDialog.openInformation(null, "데이터베이스 작업", "작업이 완료되었습니다."); 
//								} 
//							});
//
//							return Status.OK_STATUS;
//						}
//					};
//					job.setUser(true);
//					job.schedule();
//				}
//			});
//		} catch(InvocationTargetException e) {
//			e.printStackTrace();
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
//case 3
//	public void run() {
//		
//		try {
//            // 10 is the workload, so in your case the number of files to copy
//            IRunnableWithProgress op = new YourThread(10);
//            new ProgressMonitorDialog(new Shell()).run(true, true, op); 
//         } catch (InvocationTargetException ex) {
//             ex.printStackTrace();
//         } catch (InterruptedException ex) {
//             ex.printStackTrace();
//         }
//	}
//	
//	private static class YourThread implements IRunnableWithProgress {
//        private int workload;
//
//        public YourThread(int workload) {
//            this.workload = workload;
//        }
//
//        @Override
//        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
//        {
//            // Tell the user what you are doing
//            monitor.beginTask("Copying files", workload);
//
//            // Do your work
//            for(int i = 0; i < workload; i++)
//            {
//                // Optionally add subtasks
//                monitor.subTask("Copying file " + (i+1) + " of "+ workload + "...");
//
//                Thread.sleep(2000);
//
//                // Tell the monitor that you successfully finished one item of "workload"-many
//                monitor.worked(1);
//
//                // Check if the user pressed "cancel"
//                if(monitor.isCanceled())
//                {
//                    monitor.done();
//                    return;
//                }
//            }
//
//            // You are done
//            monitor.done();
//        }
//
//    }
	
// case2
//	public void run() {
//		Job job = new Job("Long Running Action:") {
//			 protected IStatus run(final IProgressMonitor monitor) {
//				 monitor.beginTask("Number counting", TASK_AMOUNT);
//				 for (int i = 0; i < TASK_AMOUNT; i++) {
//					 if (monitor.isCanceled()) {
//						 monitor.done();
//						 return Status.CANCEL_STATUS;
//					 }
//					 int done = i % TASK_AMOUNT;
//					 monitor.subTask("work done: (" + done + "%)");
//					 monitor.worked(1);
//					 try {
//						 Thread.sleep(200);
//					 } catch (InterruptedException e) {
//						 e.printStackTrace();
//					 }
//				 }
//				 monitor.done();
//				 return Status.OK_STATUS;
//			 }
//		 };
//		DialogProgressProvider aa = new DialogProgressProvider();
//		aa.createMonitor(job);
//
//	}
	
//case1
//	public void run() {
//		// job 은 pool에 보관하여 사용 하여 쓰래드보다 오버해드 적고, 진행상황 알림과 취소 가능
//		 Job job = new Job("Long Running Action:") {
//			 protected IStatus run(final IProgressMonitor monitor) {
//				 monitor.beginTask("Number counting", TASK_AMOUNT);
//				 
//				 // start 
//				 for (int i = 0; i < TASK_AMOUNT; i++) {
//					 if (monitor.isCanceled()) {
//						 monitor.done();
//						 return Status.CANCEL_STATUS;
//					 }
//					 int done = i % TASK_AMOUNT;
//					 monitor.subTask("work done: (" + done + "%)");
//					 monitor.worked(1);
//					 try {
//						 Thread.sleep(200);
//					 } catch (InterruptedException e) {
//						 e.printStackTrace();
//					 }
//				 }
//				 // end
//				 
//				 monitor.done();
//				 return Status.OK_STATUS;
//			 }
//		 };
//		 job.setName(job.getName() + " " + job.hashCode());
//		 job.setUser(true);	// 사용자작업임을 표시
//		 job.schedule();
//	}
//case0
	public void run() {
		Job job = new Job("Title") { 
			protected IStatus run(IProgressMonitor monitor) { 
					monitor.beginTask("TaskName", 20); 
					for(int i=0;i<20;i++){ 
						monitor.subTask("SubTaskName" + i); 
						monitor.worked(i+1); 
						if (monitor.isCanceled()) { 
							return Status.CANCEL_STATUS; 
						} 
						try { Thread.sleep(1000); } catch (Exception e) { } 
						Display.getDefault().asyncExec(new Runnable() { 
							public void run() { 
								System.out.println("작업중");
//								text.setText(text.getText() + "  " + 1); 
							} 
						}); 
					} 
					monitor.done(); 
					return Status.OK_STATUS; 
			}
		};
		job.setUser(true);	
		job.schedule();
	}
	
}
