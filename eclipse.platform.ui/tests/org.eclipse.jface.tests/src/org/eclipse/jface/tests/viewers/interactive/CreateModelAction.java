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

import org.eclipse.jface.tests.viewers.TestElement;

public class CreateModelAction extends TestBrowserAction {
	int fLevel;

	int fChildCount;

	public CreateModelAction(String label, TestBrowser browser, int level,
			int childCount) {
		super(label, browser);
		fLevel = level;
		fChildCount = childCount;
	}

	@Override
	public void run() {
		// Clear input since TestElement.equals does only
		// check the id, not the size of the TestElement.
		getBrowser().setInput(null);
		getBrowser().setInput(TestElement.createModel(fLevel, fChildCount));
	}
}
