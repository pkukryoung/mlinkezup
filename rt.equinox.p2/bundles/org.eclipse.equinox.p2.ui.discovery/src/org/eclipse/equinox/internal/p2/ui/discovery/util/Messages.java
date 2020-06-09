/*******************************************************************************
 * Copyright (c) 2004, 2010 Tasktop Technologies and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.equinox.internal.p2.ui.discovery.util;

import org.eclipse.osgi.util.NLS;

/**
 * @author David Green
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.equinox.internal.p2.ui.discovery.util.messages"; //$NON-NLS-1$

	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String WorkbenchUtil_Browser_Initialization_Failed;

	public static String WorkbenchUtil_Invalid_URL_Error;

	public static String WorkbenchUtil_No_URL_Error;

	public static String WorkbenchUtil_Open_Location_Title;

}
