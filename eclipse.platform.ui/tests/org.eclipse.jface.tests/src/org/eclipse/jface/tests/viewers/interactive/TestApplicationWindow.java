/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
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
 *     Jeanderson Candido <http://jeandersonbc.github.io> - Bug 433608
 *******************************************************************************/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A simple application window used for testing various bugs. Insert code as
 * needed to test problems in a raw application window.
 *
 * Designed to be started as an SWT application.
 *
 * @since 3.2
 */

public class TestApplicationWindow extends ApplicationWindow {

	public static void main(String[] args) {
		new TestApplicationWindow().open();
	}

	public TestApplicationWindow() {
		super(null);
		setBlockOnOpen(true);
		addMenuBar();
	}

	/*
	 * Test initial location and size. See
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=96955
	 */
	@Override
	protected Point getInitialLocation(Point initialSize) {
		return new Point(30, 30);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(150, 150);
	}

	@Override
	protected Control createContents(Composite parent) {
		// create your contents here
		Control contents = super.createContents(parent);

		// fill the menu bar
		fillMenuBar();

		return contents;
	}

	private void fillMenuBar() {
		MenuManager menuManager = getMenuBarManager();
		MenuManager fileMenu = new MenuManager("&File");
		menuManager.add(fileMenu);

		Action loadAction = new Action("&Save") {
			@Override
			public void run() {
				// do nothing
			}
		};
		fileMenu.add(loadAction);
		menuManager.updateAll(false);
	}

}
