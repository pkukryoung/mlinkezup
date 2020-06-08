/*******************************************************************************
 * Copyright (c) 2010 Ovidio Mallo and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Ovidio Mallo - initial API and implementation (bug 299619)
 *     Ovidio Mallo - bug 301370
 ******************************************************************************/

package org.eclipse.core.tests.internal.databinding.property.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.eclipse.core.databinding.observable.map.WritableMap;
import org.eclipse.core.internal.databinding.property.value.MapSimpleValueObservableMap;
import org.eclipse.core.internal.databinding.property.value.SelfValueProperty;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;
import org.junit.Test;

public class MapSimpleValueObservableMapTest extends
		AbstractDefaultRealmTestCase {

	@Test
	public void testGetKeyValueType() {
		WritableMap masterMap = new WritableMap(String.class, Integer.class);
		SelfValueProperty detailProperty = new SelfValueProperty(Object.class);

		MapSimpleValueObservableMap detailMap = new MapSimpleValueObservableMap(
				masterMap, detailProperty);

		assertEquals(masterMap.getKeyType(), detailMap.getKeyType());
		assertEquals(detailProperty.getValueType(), detailMap.getValueType());
	}

	@Test
	public void testPut_ReplacedOldValue() {
		// Create any simple master map and detail property.
		WritableMap masterMap = new WritableMap(String.class, Integer.class);
		SelfValueProperty detailProperty = new SelfValueProperty(Integer.class);

		MapSimpleValueObservableMap detailMap = new MapSimpleValueObservableMap(
				masterMap, detailProperty);

		// Our common key.
		String key = "key";

		// Add an entry on the master map for our key.
		Integer oldValue = Integer.valueOf(111);
		masterMap.put(key, oldValue);

		// Replace the entry on the detail map for our key.
		Integer newValue = Integer.valueOf(777);
		Object returnedOldValue = detailMap.put(key, newValue);

		// Check that the replaced old value is our original value.
		assertSame(oldValue, returnedOldValue);
	}
}
