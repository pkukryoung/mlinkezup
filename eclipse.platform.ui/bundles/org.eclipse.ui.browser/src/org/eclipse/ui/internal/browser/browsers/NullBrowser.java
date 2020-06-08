/*******************************************************************************
 * Copyright (c) 2003, 2015 IBM Corporation and others.
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
package org.eclipse.ui.internal.browser.browsers;

import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.AbstractWebBrowser;
import org.eclipse.ui.internal.browser.Messages;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;

public class NullBrowser extends AbstractWebBrowser {
	public NullBrowser(String id) {
		super(id);
	}

	@Override
	public void openURL(URL url) throws PartInitException {
		WebBrowserUIPlugin.logError(
			"There is no browser adapter configured to display " //$NON-NLS-1$
			+ url
			+ ".  Ensure that you have a required browser and adapter installed, and that the browser program is available on the system path.", //$NON-NLS-1$
			null);
		throw new PartInitException(Messages.errorNoBrowser);
	}
}