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

package org.eclipse.ui.tests.keys;

import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeySequenceText;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

/**
 * Tests Bug 43168
 *
 * @since 3.0
 */
public class Bug43168Test {

	/**
	 * Tests that a <code>StackOverflowError</code> does not occur when
	 * trying to set the key sequence in a key sequence entry widget.
	 *
	 * @throws ParseException
	 *             If "CTRL+" is not recognized as a key sequence.
	 */
	@Test
	public void testStackOverflow() throws ParseException {
		Display display = Display.getCurrent();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		Text text = new Text(shell, SWT.BORDER);
		KeySequenceText keySequenceText = new KeySequenceText(text);

		shell.pack();
		shell.open();
		keySequenceText.setKeySequence(KeySequence.getInstance("CTRL+")); //$NON-NLS-1$
		shell.close();
	}
}
