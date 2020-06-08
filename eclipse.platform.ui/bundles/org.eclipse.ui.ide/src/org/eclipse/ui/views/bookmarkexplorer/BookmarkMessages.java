/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
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

package org.eclipse.ui.views.bookmarkexplorer;


/**
 * Utility class which helps managing messages
 *
 * @deprecated These messages are not API and should not be referenced outside
 *             of this plug-in.
 *
 * Marked for deletion, see Bug 550439
 *
 * @noreference
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
@Deprecated
class BookmarkMessages {

	private BookmarkMessages() {
		// prevent instantiation of class
	}

	/**
	 * Returns the formatted message for the given key in
	 * the resource bundle.
	 *
	 * @param key the resource name
	 * @param args the message arguments
	 * @return the string
	 */
	public static String format(String key, Object[] args) {
		return key;
	}

	/**
	 * Returns the resource object with the given key in
	 * the resource bundle. If there isn't any value under
	 * the given key, the key is returned.
	 *
	 * @param key the resource name
	 * @return the string
	 */
	public static String getString(String key) {
		return key;
	}
}
