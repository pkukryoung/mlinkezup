/*******************************************************************************
 * Copyright (c) 2006, 2009 Brad Reynolds and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Brad Reynolds - initial API and implementation
 *     Matthew Hall - bugs 118516, 213145, 194734, 195222
 ******************************************************************************/

package org.eclipse.jface.tests.internal.databinding.swt;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.databinding.conformance.ObservableDelegateTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.swt.SWTMutableObservableValueContractTest;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

/**
 * @since 3.2
 */
public class TableSingleSelectionObservableValueTest extends
		ObservableDelegateTest {
	private Delegate delegate;
	private IObservableValue observable;
	private Table table;

	public TableSingleSelectionObservableValueTest() {
		this(null);
	}

	public TableSingleSelectionObservableValueTest(String testName) {
		super(testName, new Delegate());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		observable = (IObservableValue) getObservable();
		delegate = (Delegate) getObservableContractDelegate();
		table = delegate.table;
	}

	@Override
	protected IObservable doCreateObservable() {
		Delegate delegate = (Delegate) getObservableContractDelegate();
		return delegate.createObservableValue(DisplayRealm.getRealm(Display
				.getDefault()));
	}

	@Test
	public void testSetValue() throws Exception {
		// preconditions
		assertEquals(-1, table.getSelectionIndex());
		assertEquals(-1, ((Integer) observable.getValue()).intValue());

		Integer value = Integer.valueOf(0);
		observable.setValue(value);
		assertEquals("table selection index", value.intValue(), table
				.getSelectionIndex());
		assertEquals("observable value", value, observable.getValue());
	}

	@Test
	public void testGetValue() throws Exception {
		int value = 1;
		table.setSelection(value);

		assertEquals("table selection index", value, table.getSelectionIndex());
		assertEquals("observable value", Integer.valueOf(value), observable
				.getValue());
	}

	public static void addConformanceTest(TestSuite suite) {
		suite.addTest(SWTMutableObservableValueContractTest.suite(new Delegate()));
	}

	/* package */static class Delegate extends
			AbstractObservableValueContractDelegate {
		private Shell shell;

		Table table;

		@Override
		public void setUp() {
			shell = new Shell();
			table = new Table(shell, SWT.NONE);
			new TableItem(table, SWT.NONE).setText("0");
			new TableItem(table, SWT.NONE).setText("1");
		}

		@Override
		public void tearDown() {
			shell.dispose();
		}

		@Override
		public IObservableValue createObservableValue(Realm realm) {
			return WidgetProperties.singleSelectionIndex()
					.observe(realm, table);
		}

		@Override
		public Object getValueType(IObservableValue observable) {
			return Integer.TYPE;
		}

		@Override
		public void change(IObservable observable) {
			int index = createIntegerValue((IObservableValue) observable)
					.intValue();
			table.select(index);

			table.notifyListeners(SWT.Selection, null);
		}

		@Override
		public Object createValue(IObservableValue observable) {
			return createIntegerValue(observable);
		}

		private Integer createIntegerValue(IObservableValue observable) {
			int value = ((Integer) observable.getValue()).intValue();
			switch (value) {
			case -1:
			case 1:
				return Integer.valueOf(0);
			case 0:
				return Integer.valueOf(1);
			}

			Assert.isTrue(false);
			return null;
		}
	}
}
