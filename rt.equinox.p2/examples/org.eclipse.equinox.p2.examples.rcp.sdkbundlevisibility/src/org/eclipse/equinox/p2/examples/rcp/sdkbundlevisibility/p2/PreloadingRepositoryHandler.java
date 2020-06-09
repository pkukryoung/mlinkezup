/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.examples.rcp.sdkbundlevisibility.p2;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.equinox.p2.ui.LoadMetadataRepositoryJob;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * PreloadingRepositoryHandler provides background loading of repositories
 * before executing the provisioning handler.
 *
 * @since 3.5
 */
abstract class PreloadingRepositoryHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public PreloadingRepositoryHandler() {
		// constructor
	}

	/**
	 * Execute the command.
	 */
	@Override
	public Object execute(ExecutionEvent event) {
		doExecuteAndLoad();
		return null;
	}

	void doExecuteAndLoad() {
		if (preloadRepositories()) {
			// cancel any load that is already running
			Job.getJobManager().cancel(LoadMetadataRepositoryJob.LOAD_FAMILY);
			final LoadMetadataRepositoryJob loadJob = new LoadMetadataRepositoryJob(getProvisioningUI());
			setLoadJobProperties(loadJob);
			if (waitForPreload()) {
				loadJob.addJobChangeListener(new JobChangeAdapter() {
					@Override
					public void done(IJobChangeEvent event) {
						if (PlatformUI.isWorkbenchRunning())
							if (event.getResult().isOK()) {
								PlatformUI.getWorkbench().getDisplay().asyncExec(() -> doExecute(loadJob));
							}
					}
				});
				loadJob.setUser(true);
				loadJob.schedule();

			} else {
				loadJob.setSystem(true);
				loadJob.setUser(false);
				loadJob.schedule();
				doExecute(null);
			}
		} else {
			doExecute(null);
		}
	}

	protected abstract void doExecute(LoadMetadataRepositoryJob job);

	protected boolean preloadRepositories() {
		return true;
	}

	protected boolean waitForPreload() {
		return true;
	}

	protected void setLoadJobProperties(Job loadJob) {
		loadJob.setProperty(LoadMetadataRepositoryJob.ACCUMULATE_LOAD_ERRORS, Boolean.toString(true));
	}

	protected ProvisioningUI getProvisioningUI() {
		return ProvisioningUI.getDefaultUI();
	}

	/**
	 * Return a shell appropriate for parenting dialogs of this handler.
	 * 
	 * @return a Shell
	 */
	protected Shell getShell() {
		return PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
	}
}
