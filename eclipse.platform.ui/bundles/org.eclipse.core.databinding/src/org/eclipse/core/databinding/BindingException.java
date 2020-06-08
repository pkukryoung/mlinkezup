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
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.databinding;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * An unchecked exception indicating a binding problem.
 *
 * @since 1.0
 */
public class BindingException extends RuntimeException {

	/*
	 * Needed because all Throwables are Serializable.
	 */
	private static final long serialVersionUID = -4092828452936724217L;
	private Throwable cause;

	/**
	 * Creates a new BindingException with the given message.
	 *
	 * @param message the exceptions message
	 */
	public BindingException(String message) {
		super(message);
	}

	/**
	 * Creates a new BindingException with the given message and cause.
	 *
	 * @param message the exceptions message
	 * @param cause   the cause for this exception; might be <code>null</code>
	 */
	public BindingException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	@Override
	public void printStackTrace(PrintStream err) {
		super.printStackTrace(err);
		if (cause != null) {
			err.println("caused by:"); //$NON-NLS-1$
			cause.printStackTrace(err);
		}
	}

	@Override
	public void printStackTrace(PrintWriter err) {
		super.printStackTrace(err);
		if (cause != null) {
			err.println("caused by:"); //$NON-NLS-1$
			cause.printStackTrace(err);
		}
	}
}
