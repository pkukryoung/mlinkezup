/*******************************************************************************
 * Copyright (c) 2004, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.browser;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
/**
 * Action to open the Web broswer.
 */
public class OpenBrowserWorkbenchAction implements IWorkbenchWindowActionDelegate {
	/**
	 * OpenBrowserWorkbenchAction constructor comment.
	 */
	public OpenBrowserWorkbenchAction() {
		super();
	}

	/**
	 * Disposes this action delegate.  The implementor should unhook any references
	 * to itself so that garbage collection can occur.
	 */
	@Override
	public void dispose() {
		// do nothing
	}

	/**
	 * Initializes this action delegate with the workbench window it will work in.
	 *
	 * @param window the window that provides the context for this delegate
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		// do nothing
	}

	/**
	 * Performs this action.
	 * <p>
	 * This method is called when the delegating action has been triggered.
	 * Implement this method to do the actual work.
	 * </p>
	 *
	 * @param action the action proxy that handles the presentation portion of the
	 *   action
	 */
	@Override
	public void run(IAction action) {
		try {
			IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
			IWebBrowser browser = browserSupport.createBrowser(IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.NAVIGATION_BAR, null, null, null);
			browser.openURL(null);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Error opening browser", e); //$NON-NLS-1$
		}
	}

	/**
	 * Notifies this action delegate that the selection in the workbench has changed.
	 * <p>
	 * Implementers can use this opportunity to change the availability of the
	 * action or to modify other presentation properties.
	 * </p>
	 *
	 * @param action the action proxy that handles presentation portion of the action
	 * @param selection the current selection in the workbench
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// do nothing
	}
}
