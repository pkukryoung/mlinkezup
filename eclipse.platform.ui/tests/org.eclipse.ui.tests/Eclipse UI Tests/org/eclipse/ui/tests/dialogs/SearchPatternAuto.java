/*******************************************************************************
 * Copyright (c) 2006, 2017 IBM Corporation and others.
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

package org.eclipse.ui.tests.dialogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.ui.dialogs.SearchPattern;
import org.junit.Test;

/**
 * Test case for tests SearchPattern match functionality
 *
 * @since 3.3
 *
 */
public class SearchPatternAuto {

	private static List<String> resources = new ArrayList<>();

	static {
		generateRescourcesTestCases('A', 'C', 8, "");
		generateRescourcesTestCases('A', 'C', 4, "");
	}

	/**
	 * Generates strings data for match test cases.
	 *
	 * @param startChar
	 * @param endChar
	 * @param lenght
	 * @param resource
	 */
	private static void generateRescourcesTestCases(char startChar, char endChar, int lenght, String resource){
		for (char ch = startChar; ch <= endChar; ch++) {
			String res = resource + ch;
			if (lenght == res.length()) {
				resources.add(res);
			} else if ((res.trim().length() % 2) == 0) {
				generateRescourcesTestCases(Character.toUpperCase((char)(startChar + 1)), Character.toUpperCase((char)(endChar + 1)), lenght, res);
			} else {
				generateRescourcesTestCases(Character.toLowerCase((char)(startChar + 1)), Character.toLowerCase((char)(endChar + 1)), lenght, res);
			}
		}
	}

	/**
	 * Tests exact match functionality. If we camelCase rule is enable, Pattern should starts with lowerCase character.
	 * Result for "abcd " pattern should be similar to regexp pattern "abcd" with case insensitive.
	 */
	@Test
	public void testExactMatch1() {
		Pattern pattern = Pattern.compile("abcd", Pattern.CASE_INSENSITIVE);
		assertMatches("abcd ", SearchPattern.RULE_EXACT_MATCH, pattern);
	}

	/**
	 * Tests exact match functionality. If we camelCase rule is enable, Pattern should starts with lowerCase character.
	 * Result for "abcdefgh&lt;" pattern should be similar to regexp pattern "abcdefgh" with case insensitive.
	 */
	@Test
	public void testExactMatch2() {
		Pattern pattern = Pattern.compile("abcdefgh", Pattern.CASE_INSENSITIVE);
		assertMatches("abcdefgh<", SearchPattern.RULE_EXACT_MATCH, pattern);
	}

	/**
	 * Tests prefix match functionality. If we camelCase rule is enable, Pattern should starts with lowerCase character.
	 * Result for "ab" pattern should be similar to regexp pattern "ab.*" with case insensitive.
	 */
	@Test
	public void testPrefixMatch() {
		Pattern pattern = Pattern.compile("ab.*", Pattern.CASE_INSENSITIVE);
		assertMatches("ab", SearchPattern.RULE_PREFIX_MATCH, pattern);
	}

	/**
	 * Tests pattern match functionality. It's similar to regexp patterns.
	 * Result for "**cDe" pattern should be similar to regexp pattern ".*cde.*" with case insensitive.
	 */
	@Test
	public void testPatternMatch1() {
		Pattern pattern = Pattern.compile(".*cde.*", Pattern.CASE_INSENSITIVE);
		assertMatches("**cDe", SearchPattern.RULE_PATTERN_MATCH, pattern);
	}

	/**
	 * Tests pattern match functionality. It's similar to regexp patterns.
	 * Result for "**c*e*i" pattern should be similar to regexp pattern ".*c.*e.*i.*" with case insensitive.
	 */
	@Test
	public void testPatternMatch2() {
		Pattern pattern = Pattern.compile(".*c.*e.*i.*", Pattern.CASE_INSENSITIVE);
		assertMatches("**c*e*i", SearchPattern.RULE_PATTERN_MATCH, pattern);
	}

	/**
	 * Tests camelCase match functionality.
	 * Every string starts with upperCase characters should be recognize as camelCase pattern match rule.
	 * Result for "CD" SearchPattern should be similar to regexp pattern "C[^A-Z]*D.*" or "CD.*"
	 * If pattern contains only upperCase characters result contains all prefix match elements.
	 */
	@Test
	public void testCamelCaseMatch1() {
		Pattern pattern = Pattern.compile("C[^A-Z]*D.*");
		Pattern pattern2 = Pattern.compile("CD.*", Pattern.CASE_INSENSITIVE);
		assertMatches("CD", SearchPattern.RULE_CAMELCASE_MATCH, pattern, pattern2);
	}

	/**
	 * Tests camelCase match functionality.
	 * Every string starts with upperCase characters should be recognize as camelCase pattern match rule.
	 * Result for "AbCd " SearchPattern should be similar to regexp pattern "C[^A-Z]*D.*" or "CD.*"
	 */
	@Test
	public void testCamelCaseMatch2() {
		Pattern pattern = Pattern.compile("Ab[^A-Z]*Cd[^A-Z]*");
		assertMatches("AbCd ", SearchPattern.RULE_CAMELCASE_MATCH, pattern);
	}

	/**
	 * Tests camelCase match functionality.
	 * Every string starts with upperCase characters should be recognize as camelCase pattern match rule.
	 * Result for "AbCdE&lt;" SearchPattern should be similar to regexp pattern "Ab[^A-Z]*Cd[^A-Z]*E[^A-Z]*"
	 */
	@Test
	public void testCamelCaseMatch3() {
		Pattern pattern = Pattern.compile("Ab[^A-Z]*Cd[^A-Z]*E[^A-Z]*");
		assertMatches("AbCdE<", SearchPattern.RULE_CAMELCASE_MATCH, pattern);
	}

	/**
	 * Tests blank match functionality.
	 * Blank string should be recognize as blank pattern match rule.
	 * It should match with all resources.
	 * Result for SearchPattern should be similar to regexp pattern ".*"
	 */
	@Test
	public void testBlankMatch() {
		Pattern pattern = Pattern.compile(".*", Pattern.CASE_INSENSITIVE);
		assertMatches("", SearchPattern.RULE_BLANK_MATCH, pattern);
	}

	private void assertMatches(String patternText, int searchPattern, Pattern... patterns) {
		SearchPattern patternMatcher = new SearchPattern();
		patternMatcher.setPattern(patternText);
		assertEquals(patternMatcher.getMatchRule(), searchPattern);
		for (String res : resources) {
			boolean anyMatches = anyMatches(res, patterns);
			boolean patternMatches = patternMatcher.matches(res);
			if (patternMatches) {
				assertTrue("Pattern '" + patternText + "' matches '" + res + "' but other patterns don't.", anyMatches);
			} else {
				assertFalse("Pattern '" + patternText + "' doesn't match '" + res + "' but other patterns do.",
						anyMatches);
			}
		}
	}

	private static boolean anyMatches(String res, Pattern... patterns) {
		boolean result = false;
		for (Pattern pattern : patterns) {
			result |= pattern.matcher(res).matches();
		}
		return result;
	}

}
