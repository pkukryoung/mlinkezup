/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
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

import static org.junit.Assert.assertEquals;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.Test;

/**
 * Tests Bug 43597
 *
 * @since 3.0
 */
public class Bug43597Test {

	private Font textFont;

	/**
	 * Tests that setting the text on a text widget to an empty string does not
	 * reset the font. This was a problem only on carbon.
	 */
	@Test
	public void testFontReset() {
		String metaCharacter = "\u2325X"; //$NON-NLS-1$

		// Set up a working environment.
		Display display = Display.getCurrent();
		Shell shell = new Shell(display);
		GridLayout gridLayout = new GridLayout();
		shell.setLayout(gridLayout);
		Text text = new Text(shell, SWT.LEFT);
		textFont = new Font(text.getDisplay(),
				"Lucida Grande", 13, SWT.NORMAL);
		text.setFont(textFont);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		shell.pack();
		shell.open();

		// Set the text once, and get the font.
		text.setText(metaCharacter);
		Font fontBefore = text.getFont();

		// Set the font again, and get the font afterward.
		text.setText(""); //$NON-NLS-1$
		text.setText(metaCharacter);
		Font fontAfter = text.getFont();

		// Test.
		assertEquals("Clearing text resets font.", fontBefore, fontAfter); //$NON-NLS-1$

		// Clean up after myself.
		shell.close();
		shell.dispose();
	}

	@After
	public void doTearDown() throws Exception {
		if (textFont != null) {
			textFont.dispose();
		}
	}
}
