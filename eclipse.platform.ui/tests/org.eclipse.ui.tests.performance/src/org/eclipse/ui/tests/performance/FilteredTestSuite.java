/*******************************************************************************
 * Copyright (c) 2009, 2017 IBM Corporation and others.
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
package org.eclipse.ui.tests.performance;

import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Only includes tests that match filter. Filter is specified in the Java system variable
 * <code>org.eclipse.ui.tests.filter</code>. The expected filter format is:
 * <pre>
 * 		test_class_name[#test_name]
 * </pre>
 * where both test_class_name and test_name can be a regular expression.
 * <p>
 * For instance:
 * </p>
 * <pre>
 * 		OpenCloseEditorTest#testOpenAndCloseEditors:perf_outline()
 * </pre>
 * The variable can be specified as a VM argument for the Eclipse launcher:
 * <pre>
 * 		"-Dorg.eclipse.ui.tests.filter=OpenCloseEditorTest#testOpenAndCloseEditors:perf_outline()"
 * </pre>
 * or
 * <pre>
 * 		"-Dorg.eclipse.ui.tests.filter=OpenCloseEditorTest#testOpenAndCloseEditors.*"
 * </pre>
 * @since 3.5
 */
public class FilteredTestSuite extends TestSuite {

	static final public String FILTER_TEST_NAME = "org.eclipse.ui.tests.filter";

	private String filterTestClassName;
	private String filterTestName;

	public FilteredTestSuite() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		BundleContext context = bundle != null ? bundle.getBundleContext() : null;
		if (context == null) { // most likely run in a wrong launch mode
			System.err.println("UIPerformanceTestSuite was unable to retirieve bundle context; test filtering is disabled");
			return;
		}
		String filterString = context.getProperty(FILTER_TEST_NAME);
		if (filterString == null)
			return;
		if (filterString.endsWith("()"))
			filterString = filterString.substring(0, filterString.length() - 2);

		// Expected format: filterTestClassName#filterTestName
		int methodSeparator = filterString.indexOf('#');
		if (methodSeparator != -1) {

			if (methodSeparator == 0)
				filterTestClassName = null;
			else
				filterTestClassName = filterString.substring(0, methodSeparator);

			if (methodSeparator + 1 < filterString.length())
				filterTestName = filterString.substring(methodSeparator + 1);
			else
				filterTestName = null;
		} else {
			filterTestClassName = filterString;
			filterTestName = null;
		}
	}

	@Override
	public void addTest(Test test) {
		if ((filterTestClassName != null) || (filterTestName != null)) {
			if (test instanceof TestSuite) {
				addFilteredTestSuite((TestSuite)test);
				return;
			} else if (test instanceof TestCase) {
				addFilteredTestCase((TestCase)test);
				return;
			}
		}
		// default processing: no filter or unknown test type
		super.addTest(test);
	}

	private void addFilteredTestSuite(TestSuite testSuite) {
		for (Enumeration<Test> allTests = testSuite.tests(); allTests.hasMoreElements();) {
			Object subTest = allTests.nextElement();

			if (subTest instanceof TestSuite) {
				addFilteredTestSuite((TestSuite)subTest);
				continue;
			}

			if (!(subTest instanceof TestCase))
				continue;

			if (filterTestClassName != null) {
				Class<?> testClass = subTest.getClass();
				String subTestQualName = testClass.getName(); // qualified class name
				if (subTestQualName == null)
					subTestQualName = "";
				int index = subTestQualName.lastIndexOf('.');
				String subTestName = ""; // short class name
				if ((index != -1) && ((index +1) < subTestQualName.length()))
					subTestName = subTestQualName.substring(index+1);
				if (!subTestName.matches(filterTestClassName) && !subTestQualName.matches(filterTestClassName))
					continue;
			}
			addFilteredTestCase((TestCase)subTest);
		}
	}

	private void addFilteredTestCase(TestCase testCase) {
		if (filterTestName == null) {
			super.addTest(testCase);
			return;
		}
		String testCaseName = testCase.getName();
		if (testCaseName == null)
			return;
		if (testCaseName.matches(filterTestName))
			super.addTest(testCase);
	}
}
