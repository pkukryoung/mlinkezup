/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
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
package org.eclipse.jface.tests.action;

import junit.framework.TestCase;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Abstract superclass of all JFace action tests.
 *
 * @since 3.1
 */
public abstract class JFaceActionTest extends TestCase {

	private Display display;
	private Shell shell;

	/**
	 * Constructs a new test with the given name
	 *
	 * @param name the name of the test
	 */
	protected JFaceActionTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		display = Display.getCurrent();
		if (display == null) {
			display = new Display();
		}
		shell = new Shell(display);
		shell.setSize(500, 500);
		shell.setLayout(new FillLayout());
		shell.open();
	}

	@Override
	protected void tearDown() throws Exception {
		shell.dispose();
	}

	protected Display getDisplay() {
		return display;
	}

	protected Shell getShell() {
		return shell;
	}

}
