/*******************************************************************************
 * Copyright (c) 2004, 2016 IBM Corporation and others.
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
 *     Patrik Suzzi <psuzzi@gmail.com> - Bug 489250
 *******************************************************************************/
package org.eclipse.ui.tests.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.ExternalActionManager;
import org.eclipse.jface.util.Util;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.tests.statushandlers.TestStatusHandler;
import org.junit.Before;
import org.junit.Test;

/**
 * A tests whether is active will log an exception if the command is not
 * defined.
 *
 * @since 3.1
 */
public final class Bug73756Test {

	private static String CMD_ID = "a command that is not defined";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(ExternalActionManager.class.getName());

	private static int SEVERITY = IStatus.ERROR;

	private static String MESSAGE = MessageFormat.format(
			Util.translateString(RESOURCE_BUNDLE, "undefinedCommand.WarningMessage", null), //$NON-NLS-1$
			CMD_ID);

	private static String PLUGIN_ID = "org.eclipse.jface";

	@Before
	public void doTearDown() throws Exception {
		TestStatusHandler.uninstall();
	}

	/**
	 * Tests that calling <code>isActive()</code> on an undefined command causes a
	 * log message to be written. This simple calls <code>isActive()</code> for a
	 * bogus command identifier. A log listener flips a boolean flag if a log
	 * message is written.
	 */
	@Test
	public final void testUndefinedCommandIsActiveLogged() throws Exception {
		TestStatusHandler.install();

		// Check if a bogus command is active.
		ExternalActionManager.getInstance().getCallback().isActive(CMD_ID);

		// Check if a correct status is logged
		assertEquals(TestStatusHandler.getLastHandledStyle(), StatusManager.LOG);
		assertStatusAdapter(TestStatusHandler.getLastHandledStatusAdapter());
	}

	/**
	 * Checks whether the last handled status is correct
	 */
	private void assertStatusAdapter(StatusAdapter statusAdapter) {
		assertNotNull("A warning should have been logged.", statusAdapter);
		IStatus status = statusAdapter.getStatus();
		assertEquals(status.getSeverity(), SEVERITY);
		assertEquals(status.getPlugin(), PLUGIN_ID);
		assertEquals(status.getMessage(), MESSAGE);
	}
}
