/*******************************************************************************
 * Copyright (c) 2009, 2010 Cloudsmith Inc. and others.
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
package org.eclipse.equinox.p2.metadata;

/**
 * Exception thrown when parsing Omni Version formats.
 * @noextend This class is not intended to be subclassed by clients.
 * @since 2.0
 */
public class VersionFormatException extends Exception {

	private static final long serialVersionUID = -867104101610941043L;

	public VersionFormatException(String message) {
		super(message);
	}
}
