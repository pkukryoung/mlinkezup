/*******************************************************************************
 * Copyright (c) 2009, 2018 Cloudsmith Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.equinox.p2.testserver.servlets;

import java.net.URI;
import java.net.URLConnection;

public class ContentLengthLier extends BasicResourceDelivery {

	double keepFactor;

	/**
	 * The ContentLengthLier sets the content length to a percentage of the original
	 * length. Values between 0 and 200 can be used (to lie about files being both
	 * smaller < 100, or larger > 100).
	 *
	 * @param theAlias
	 * @param thePath
	 * @param keepPercent - how much to lie between 0 and 200 (inclusive)
	 */
	public ContentLengthLier(String theAlias, URI thePath, int keepPercent) {
		super(theAlias, thePath);
		if (keepPercent < 0 || keepPercent > 200)
			throw new IllegalArgumentException(
					"keepPercent must be between 0 and 200 - was:" + Integer.valueOf(keepPercent)); //$NON-NLS-1$
		keepFactor = keepPercent / 100.0;
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected int getContentLength(URLConnection conn) {
		int contentLength = conn.getContentLength();
		return (contentLength >= 0) ? (int) (contentLength * keepFactor) : contentLength;

	}

}
