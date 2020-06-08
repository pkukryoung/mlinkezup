/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
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
 *     Patrik Suzzi <psuzzi@gmail.com> - Bug 490700
 *******************************************************************************/
package org.eclipse.ui.tests.dialogs;

import java.util.ResourceBundle;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.tests.harness.util.DialogCheck;
import org.junit.Test;

public class TextMessageDialogsAuto {

	private Shell getShell() {
		return DialogCheck.getShell();
	}

	/*
	 * Convenience method simliar to org.eclipse.jface.dialogs.MessageDialog::openConfirm.
	 * The method will return the dialog instead of opening.
	 * @param title the dialog's title, or <code>null</code> if none.
	 * @param message the message.
	 * @return Dialog the confirm dialog.
	 */
	private MessageDialog getConfirmDialog(String title, String message) {
		return new MessageDialog(getShell(), title, null, message,
				MessageDialog.QUESTION, 0,
				IDialogConstants.OK_LABEL,
				IDialogConstants.CANCEL_LABEL);
	}

	/*
	 * Convenience method simliar to org.eclipse.jface.dialogs.MessageDialog::openQuestion.
	 * The method will return the dialog instead of opening.
	 * @param title the dialog's title, or <code>null</code> if none.
	 * @param message the message.
	 * @return MessageDialog the question dialog.
	 */
	private MessageDialog getQuestionDialog(String title, String message) {
		return new MessageDialog(getShell(), title, null, message,
				MessageDialog.QUESTION, 0,
				IDialogConstants.YES_LABEL,
				IDialogConstants.NO_LABEL);
	}

	@Test
	public void testCloseFileDeleted() {
		Dialog dialog = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("org.eclipse.ui.texteditor.EditorMessages");
		if (bundle != null) {
			dialog = getConfirmDialog(
					bundle
							.getString("Editor_error_activated_deleted_close_title"),
					bundle
							.getString("Editor_error_activated_deleted_close_message"));
		}
		DialogCheck.assertDialogTexts(dialog);
	}

	@Test
	public void testErrorClosing() {
		Dialog dialog = getQuestionDialog(WorkbenchMessages.Error,
				WorkbenchMessages.ErrorClosingNoArg);
		DialogCheck.assertDialogTexts(dialog);
	}

	@Test
	public void testFileChanged() {
		MessageDialog dialog = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("org.eclipse.ui.texteditor.EditorMessages");
		if (bundle != null) {
			dialog = getQuestionDialog(
					bundle.getString("Editor_error_activated_outofsync_title"),
					bundle
							.getString("Editor_error_activated_outofsync_message"));
		}
		DialogCheck.assertDialogTexts(dialog);
	}
	@Test
	public void testSaveFileDeleted() {
		MessageDialog dialog = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("org.eclipse.ui.texteditor.EditorMessages");
		if (bundle != null) {
			dialog = new MessageDialog(getShell(), bundle.getString("Editor_error_activated_deleted_save_title"), null,
					bundle.getString("Editor_error_activated_deleted_save_message"), MessageDialog.QUESTION, 0,
					bundle.getString("Editor_error_activated_deleted_save_button_save"),
					bundle.getString("Editor_error_activated_deleted_save_button_close"));
		}
		DialogCheck.assertDialogTexts(dialog);
	}

	@Test
	public void testUpdateConflict() {
		MessageDialog dialog = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("org.eclipse.ui.texteditor.EditorMessages");
		if (bundle != null) {
			dialog = getQuestionDialog(bundle
					.getString("Editor_error_save_outofsync_title"), bundle
					.getString("Editor_error_save_outofsync_message"));
		}
		DialogCheck.assertDialogTexts(dialog);
	}

}

