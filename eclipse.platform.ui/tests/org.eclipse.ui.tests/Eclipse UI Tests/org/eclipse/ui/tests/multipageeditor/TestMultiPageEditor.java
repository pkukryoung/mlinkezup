/*******************************************************************************
 * Copyright (c) 2000, 2017 IBM Corporation and others.
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
 *     Jeanderson Candido <http://jeandersonbc.github.io> - Bug 433603
 *******************************************************************************/
package org.eclipse.ui.tests.multipageeditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * A multi-page editor for testing key bindings while switching pages. This
 * creates two pages -- each with a different context. The first context binds
 * "Ctrl+Shift+4" -- the second binds "Ctrl+Shift+5" -- to the command
 * "org.eclipse.ui.tests.TestCommand".
 *
 * @since 3.0
 */
public final class TestMultiPageEditor extends MultiPageEditorPart {

	@Override
	protected void createPages() {
		try {
			IEditorPart part1 = new TestKeyBindingMultiPageEditorPart(0);
			addPage(part1, getEditorInput());

			IEditorPart part2 = new TestKeyBindingMultiPageEditorPart(1);
			addPage(part2, getEditorInput());
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do nothing.
	}

	@Override
	public void doSaveAs() {
		throw new UnsupportedOperationException("Not implemented in this test."); //$NON-NLS-1$
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Sets the active page.
	 *
	 * @param page
	 *            The page to activate; should be either <code>0</code> or
	 *            <code>1</code>.
	 */
	public void setPage(int page) {
		setActivePage(page);
	}

}
