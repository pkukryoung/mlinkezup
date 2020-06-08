/*******************************************************************************
 * Copyright (c) 2004, 2011 IBM Corporation and others.
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
package org.eclipse.ui.tests.commands;

import static org.junit.Assert.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ExecutionException;
import org.eclipse.ui.commands.ICommand;
import org.eclipse.ui.commands.IWorkbenchCommandSupport;
import org.eclipse.ui.commands.NotHandledException;
import org.eclipse.ui.internal.Workbench;
import org.junit.After;
import org.junit.Test;

/**
 * A test for whether the select all handler will send a selection event.
 *
 * @since 3.1
 */
public final class Bug74982Test {

	/**
	 * Whether the selection event has been fired.
	 */
	private boolean selectionEventFired = false;

	private Shell dialog;

	@After
	public void doTearDown() throws Exception {
		if (dialog != null) {
			dialog.dispose();
		}
	}

	/**
	 * Tests that the <code>SelectAllHandler</code> triggers a selection
	 * event. Creates a dialog with a text widget, gives the text widget focus,
	 * and then calls the select all command. This should then call the
	 * <code>SelectAllHandler</code> and trigger a selection event.
	 *
	 * @throws ExecutionException
	 *             If the <code>SelectAllHandler</code> is broken in some way.
	 * @throws NotHandledException
	 *             If the dialog does not have focus, or if the
	 *             <code>WorkbenchCommandSupport</code> class is broken in
	 *             some way.
	 */
	@Test
	public final void testSelectAllHandlerSendsSelectionEvent()
			throws ExecutionException, NotHandledException {
		// Create a dialog with a text widget.
		IWorkbench fWorkbench = PlatformUI.getWorkbench();
		dialog = new Shell(fWorkbench.getActiveWorkbenchWindow().getShell());
		dialog.setLayout(new GridLayout());
		final Text text = new Text(dialog, SWT.SINGLE);
		text.setText("Mooooooooooooooooooooooooooooo");
		text.setLayoutData(new GridData());
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectionEventFired = true;
			}
		});

		// Open the dialog and give the text widget focus.
		dialog.pack();
		dialog.open();
		text.setFocus();

		// Spin the event loop to make sure focus is set-up properly.
		final Display display = fWorkbench.getDisplay();
		while (display.readAndDispatch()) {
			((Workbench)fWorkbench).getContext().processWaiting();
		}

		// Get the select all command and execute it.
		final IWorkbenchCommandSupport commandSupport = fWorkbench
				.getCommandSupport();
		final ICommand selectAllCommand = commandSupport.getCommandManager()
				.getCommand("org.eclipse.ui.edit.selectAll");
		selectAllCommand.execute(null);

		// Check to see if the selection event has been fired.
		assertTrue(
				"The selection event was not fired when the SelectAllHandler was used.",
				selectionEventFired);
	}
}
