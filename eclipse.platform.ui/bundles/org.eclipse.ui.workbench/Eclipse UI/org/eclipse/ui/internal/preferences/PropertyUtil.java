/*******************************************************************************
 * Copyright (c) 2004, 2019 IBM Corporation and others.
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
package org.eclipse.ui.internal.preferences;

import java.util.Set;

/**
 * @since 3.1
 */
public class PropertyUtil {
	private PropertyUtil() {
	}

	public static boolean isEqual(IPropertyMap map1, IPropertyMap map2) {
		Set<String> map1Keys = map1.keySet();
		Set<String> map2Keys = map2.keySet();

		if (!map1Keys.equals(map2Keys)) {
			return false;
		}

		for (String next : map1Keys) {
			if (!map1.getValue(next, Object.class).equals(map2.getValue(next, Object.class))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Copies all properties from the given source to the given destination
	 *
	 * @param destination
	 * @param source
	 * @since 3.1
	 */
	public static void copy(IPropertyMap destination, IPropertyMap source) {
		Set<String> keys = source.keySet();

		for (String key : keys) {
			destination.setValue(key, source.getValue(key, Object.class));
		}
	}

	/**
	 * Computes the union of a set property maps. The result will contain all
	 * properties from all of the contributing maps. If the same property had a
	 * different value in two or more maps, its value in the union will be null. If
	 * the same property
	 *
	 * Note that the result is a standalone object and will not be updated to
	 * reflect subsequent changes in the source maps.
	 *
	 * @param sources
	 * @return
	 * @since 3.1
	 */
	public static IPropertyMap union(IPropertyMap[] sources) {
		PropertyMapUnion result = new PropertyMapUnion();

		for (IPropertyMap map : sources) {
			result.addMap(map);
		}

		return result;
	}

	public static boolean get(IPropertyMap toRead, String propertyId, boolean defaultValue) {
		Boolean result = ((Boolean) toRead.getValue(propertyId, Boolean.class));

		if (result == null) {
			return defaultValue;
		}

		return result.booleanValue();
	}

	public static int get(IPropertyMap toRead, String propertyId, int defaultValue) {
		Integer result = ((Integer) toRead.getValue(propertyId, Integer.class));

		if (result == null) {
			return defaultValue;
		}

		return result.intValue();
	}
}
