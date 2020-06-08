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
 *******************************************************************************/
package org.eclipse.ui.internal.ide;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

/**
 * Utility class to create status objects.
 *
 * PRIVATE This class is an internal implementation class and should not be
 * referenced or sub-classed outside of the workbench
 *
 * @since 3.0
 */
public class StatusUtil {

	/**
	 * Answer a flat collection of the passed status and its recursive children
	 */
	protected static List<IStatus> flatten(IStatus aStatus) {
		List<IStatus> result = new ArrayList<>();

		if (aStatus.isMultiStatus()) {
			for (IStatus currentChild : aStatus.getChildren()) {
				if (currentChild.isMultiStatus()) {
					Iterator<IStatus> childStatiiEnum = flatten(currentChild).iterator();
					while (childStatiiEnum.hasNext()) {
						result.add(childStatiiEnum.next());
					}
				} else {
					result.add(currentChild);
				}
			}
		} else {
			result.add(aStatus);
		}

		return result;
	}

	/**
	 * This method must not be called outside the workbench.
	 *
	 * Utility method for creating status.
	 */
	protected static IStatus newStatus(IStatus[] stati, String message,
			Throwable exception) {

		if (message == null || message.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		return new MultiStatus(IDEWorkbenchPlugin.IDE_WORKBENCH, IStatus.ERROR,
				stati, message, exception);
	}


	/**
	 * This method must not be called outside the workbench.
	 *
	 * Utility method for creating status.
	 *
	 * @param severity  severity of the status
	 * @param message   status message
	 * @param exception an exception to add; must not be <code>null</code> if
	 *                  message is <code>null</code> or empty
	 * @return {@link IStatus}
	 */
	public static IStatus newStatus(int severity, String message, Throwable exception) {

		String statusMessage = message;
		if (message == null || message.trim().length() == 0) {
			if (exception == null) {
				throw new IllegalArgumentException();
			} else if (exception.getMessage() == null) {
				statusMessage = exception.toString();
			} else {
				statusMessage = exception.getMessage();
			}
		}

		return new Status(severity, IDEWorkbenchPlugin.IDE_WORKBENCH, severity,
				statusMessage, exception);
	}

	/**
	 * This method must not be called outside the workbench.
	 *
	 * Utility method for creating error status messages delegating to
	 * <code>StatusUtil{@link #newStatus(int, String, Throwable)}</code>.
	 *
	 * @param exception an exception to add to the error status
	 * @return {@link IStatus}
	 */
	public static IStatus newError(Throwable exception) {
		return newStatus(IStatus.ERROR, exception.getLocalizedMessage(), exception);
	}

	/**
	 * This method must not be called outside the workbench.
	 *
	 * Utility method for creating status.
	 *
	 * @param children  list of statuses; might contain nested multi-statuses
	 * @param message   status message
	 * @param exception optional status exception
	 * @return {@link IStatus}
	 */
	public static IStatus newStatus(List<IStatus> children, String message,
			Throwable exception) {

		List<IStatus> flatStatusCollection = new ArrayList<>();
		Iterator<IStatus> iter = children.iterator();
		while (iter.hasNext()) {
			IStatus currentStatus = iter.next();
			Iterator<IStatus> childrenIter = flatten(currentStatus).iterator();
			while (childrenIter.hasNext()) {
				flatStatusCollection.add(childrenIter.next());
			}
		}

		IStatus[] stati = new IStatus[flatStatusCollection.size()];
		flatStatusCollection.toArray(stati);
		return newStatus(stati, message, exception);
	}

}
