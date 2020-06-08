/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
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
package org.eclipse.ui.tests.dialogs;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.ui.preferences.IWorkingCopyManager;
import org.eclipse.ui.preferences.WorkingCopyManager;

public class PreferenceDialogWrapper extends PreferenceDialog implements IWorkbenchPreferenceContainer{

	public PreferenceDialogWrapper(Shell parentShell, PreferenceManager manager) {
		super(parentShell, manager);
	}

	@Override
	public boolean showPage(IPreferenceNode node) {
		return super.showPage(node);
	}

	@Override
	public IPreferencePage getPage(IPreferenceNode node) {
		if (node == null) {
			return null;
		}

		// Create the page if nessessary
		if (node.getPage() == null) {
			node.createPage();
		}

		if (node.getPage() == null) {
			return null;
		}

		return node.getPage();
	}

	@Override
	public IWorkingCopyManager getWorkingCopyManager() {
		return new WorkingCopyManager();
	}

	@Override
	public boolean openPage(String preferencePageId, Object data) {
		return false;
	}

	@Override
	public void registerUpdateJob(Job job) {
		//Do nothing as we are not testing this.
	}


}
