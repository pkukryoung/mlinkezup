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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.tests.harness.util.DialogCheck;
import org.junit.Test;

public class UIErrorDialogs {

	private Shell getShell() {
		return DialogCheck.getShell();
	}

	/*
	 * Get an example ErrorDialog with a status and a couple of
	 * child statuses.
	 */
	private ErrorDialog getMultiStatusErrorDialog() {

		IStatus[] childStatuses = new IStatus[2];
		childStatuses[0] = new Status(IStatus.ERROR, "org.eclipse.ui.tests",
				IStatus.ERROR, "Error message 1", new Throwable());
		childStatuses[1] = new Status(IStatus.ERROR, "org.eclipse.ui.tests",
				IStatus.ERROR, "Error message 2", new Throwable());
		MultiStatus mainStatus = new MultiStatus("org.eclipse.ui.tests",
				IStatus.ERROR, childStatuses, "Main error", new Throwable());

		return new ErrorDialog(getShell(), "Error Test", "Error message",
				mainStatus, IStatus.ERROR);
	}

	@Test
	public void testErrorClipboard() {
		Dialog dialog = getMultiStatusErrorDialog();
		DialogCheck.assertDialog(dialog);
	}

}
