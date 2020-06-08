/*******************************************************************************
 * Copyright (c) 2005, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.browser;

import java.net.URL;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
/**
 * An instance of a running Web browser.
 */
public class InternalBrowserViewInstance extends InternalBrowserInstance {
	public InternalBrowserViewInstance(String id, int style, String name, String tooltip) {
		super(WebBrowserUtil.encodeStyle(id, style), style, name, tooltip);
	}

	@Override
	public void openURL(URL url) throws PartInitException {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		final IWorkbenchPage page = workbenchWindow.getActivePage();
		WebBrowserView view = (WebBrowserView)part;
		if (view == null) {
			try {
				view = (WebBrowserView)page.showView(WebBrowserView.WEB_BROWSER_VIEW_ID, getId(), IWorkbenchPage.VIEW_CREATE);
				if (tooltip != null && tooltip.length() > 0) {
					view.setBrowserViewTooltip(tooltip);
				}
				if (name != null && name.length() > 0) {
					view.setBrowserViewName(name);
				}
				hookPart(page, view);

			} catch (Exception e) {
				Trace.trace(Trace.SEVERE, "Error opening Web browser", e); //$NON-NLS-1$
			}
		}
		if (view!=null) {
			page.bringToTop(view);
			view.setURL(url.toExternalForm());
		}
	}

	@Override
	public boolean close() {
		return ((WebBrowserView)part).close();
	}
}