/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
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
 ******************************************************************************/

package org.eclipse.ui.tests.internal;

import static org.junit.Assert.assertEquals;

import org.eclipse.ui.internal.util.Util;
import org.junit.Test;

/**
 * @since 3.5
 */
public class UtilTest {

	@Test
	public void testBasicSplit() {
		final String field = "field1";
		String[] result = Util.split(field, ',');
		assertEquals(1, result.length);
		assertEquals(field, result[0]);
	}

	@Test
	public void testBasic2Split() {
		final String field = "field1,field2";
		String[] result = Util.split(field, ',');
		assertEquals(2, result.length);
		assertEquals("field1", result[0]);
		assertEquals("field2", result[1]);
	}

	@Test
	public void testBasic3Split() {
		final String field = "field1,field3,field2";
		String[] result = Util.split(field, ',');
		assertEquals(3, result.length);
		assertEquals("field1", result[0]);
		assertEquals("field3", result[1]);
		assertEquals("field2", result[2]);
	}

	@Test
	public void testNothingSplit() {
		final String field = "";
		String[] result = Util.split(field, ',');
		assertEquals(1, result.length);
		assertEquals(0, result[0].length());
	}

	@Test
	public void testNothingUsefulSplit() {
		final String field = ",";
		String[] result = Util.split(field, ',');
		assertEquals(0, result.length);
	}

	@Test
	public void testNothingUseful2Split() {
		final String field = ",,";
		String[] result = Util.split(field, ',');
		assertEquals(0, result.length);
	}

	@Test
	public void testNothingUsefulSpaceSplit() {
		final String field = " ,";
		String[] result = Util.split(field, ',');
		assertEquals(1, result.length);
		assertEquals(" ", result[0]);
	}

	@Test
	public void testNothingUsefulSpaceSplit2() {
		final String field = ", ";
		String[] result = Util.split(field, ',');
		assertEquals(2, result.length);
		assertEquals(0, result[0].length());
		assertEquals(" ", result[1]);
	}

	@Test
	public void testNothingUsefulSpaceSplit3() {
		final String field = " , ";
		String[] result = Util.split(field, ',');
		assertEquals(2, result.length);
		assertEquals(" ", result[0]);
		assertEquals(" ", result[1]);
	}

	@Test
	public void test2Delimiters() {
		final String field = "field1,,field3,field2";
		String[] result = Util.split(field, ',');
		assertEquals(4, result.length);
		assertEquals("field1", result[0]);
		assertEquals(0, result[1].length());
		assertEquals("field3", result[2]);
		assertEquals("field2", result[3]);
	}

	@Test
	public void test3Delimiters() {
		final String field = "field1,,,field3,field2";
		String[] result = Util.split(field, ',');
		assertEquals(5, result.length);
		assertEquals("field1", result[0]);
		assertEquals(0, result[1].length());
		assertEquals(0, result[2].length());
		assertEquals("field3", result[3]);
		assertEquals("field2", result[4]);
	}
}
