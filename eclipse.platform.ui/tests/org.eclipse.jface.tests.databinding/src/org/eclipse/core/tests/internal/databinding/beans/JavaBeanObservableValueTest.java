/*******************************************************************************
 * Copyright (c) 2006, 2018 Brad Reynolds and others.
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
 *     Brad Reynolds - bug 171616
 *     Katarzyna Marszalek - test case for bug 198519
 *     Matthew Hall - bug 213145, 246103, 194734, 268688
 ******************************************************************************/

package org.eclipse.core.tests.internal.databinding.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.beans.PropertyDescriptor;

import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.beans.IBeanProperty;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.conformance.MutableObservableValueContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.util.ChangeEventTracker;
import org.eclipse.jface.databinding.conformance.util.CurrentRealm;
import org.eclipse.jface.databinding.conformance.util.ValueChangeEventTracker;
import org.eclipse.jface.examples.databinding.model.SimplePerson;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

/**
 * @since 3.2
 */
public class JavaBeanObservableValueTest extends AbstractDefaultRealmTestCase {
	private Bean bean;
	private IObservableValue observableValue;
	private IBeanObservable beanObservable;
	private PropertyDescriptor propertyDescriptor;
	private String propertyName;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		bean = new Bean();
		propertyName = "value";
		propertyDescriptor = ((IBeanProperty) BeanProperties.value(Bean.class,
				propertyName)).getPropertyDescriptor();
		observableValue = BeansObservables.observeValue(bean, propertyName);
		beanObservable = (IBeanObservable) observableValue;
	}

	@Test
	public void testGetObserved() throws Exception {
		assertEquals(bean, beanObservable.getObserved());
	}

	@Test
	public void testGetPropertyDescriptor() throws Exception {
		assertEquals(propertyDescriptor, beanObservable.getPropertyDescriptor());
	}

	@Test
	public void testSetValueThrowsExceptionThrownByBean() throws Exception {
		ThrowsSetException temp = new ThrowsSetException();
		IObservableValue observable = BeansObservables.observeValue(temp,
				"value");

		try {
			observable.setValue("");
			fail("exception should have been thrown");
		} catch (RuntimeException e) {
			assertEquals(temp.thrownException, e.getCause());
		}
	}

	@Test
	public void testGetValueThrowsExceptionThrownByBean() throws Exception {
		ThrowsGetException temp = new ThrowsGetException();
		IObservableValue observable = BeansObservables.observeValue(temp,
				"value");

		try {
			observable.getValue();
			fail("exception should have been thrown");
		} catch (RuntimeException e) {
			assertEquals(temp.thrownException, e.getCause());
		}
	}

	@Test
	public void testBug198519() {
		final SimplePerson person = new SimplePerson();
		final ComputedValue cv = new ComputedValue() {
			final IObservableValue name = BeansObservables.observeValue(person,
					"name"); //$NON-NLS-1$

			@Override
			protected Object calculate() {
				return Boolean.valueOf(name.getValue() != null);
			}
		};
		cv.addChangeListener(event -> cv.getValue());
		person.setName("foo");
	}

	@Test
	public void testConstructor_RegistersListeners() throws Exception {
		IObservableValue observable = BeansObservables.observeValue(bean,
				propertyName);
		ChangeEventTracker.observe(observable);

		assertTrue(bean.hasListeners(propertyName));
	}

	@Test
	public void testConstructor_SkipRegisterListeners() throws Exception {
		IObservableValue observable = PojoObservables.observeValue(bean,
				propertyName);
		ChangeEventTracker.observe(observable);

		assertFalse(bean.hasListeners(propertyName));
	}

	@Test
	public void testSetBeanProperty_CorrectForNullOldAndNewValues() {
		// The java bean spec allows the old and new values in a
		// PropertyChangeEvent to
		// be null, which indicates that an unknown change occured.

		// This test ensures that JavaBeanObservableValue fires the correct
		// value diff
		// even if the bean implementor is lazy :-P

		Bean bean = new AnnoyingBean();
		bean.setValue("old");
		IObservableValue observable = BeansObservables.observeValue(bean,
				"value");
		ValueChangeEventTracker tracker = ValueChangeEventTracker
				.observe(observable);
		bean.setValue("new");
		assertEquals(1, tracker.count);
		assertEquals("old", tracker.event.diff.getOldValue());
		assertEquals("new", tracker.event.diff.getNewValue());
	}

	@Test
	public void testSetBeanPropertyOutsideRealm_FiresEventInsideRealm() {
		Bean bean = new Bean("old");
		CurrentRealm realm = new CurrentRealm(true);
		IObservableValue observable = BeansObservables.observeValue(realm,
				bean, "value");
		ValueChangeEventTracker tracker = ValueChangeEventTracker
				.observe(observable);

		realm.setCurrent(false);
		bean.setValue("new");
		assertEquals(0, tracker.count);

		realm.setCurrent(true);
		assertEquals(1, tracker.count);
		assertEquals(Diffs.createValueDiff("old", "new"), tracker.event.diff);
	}

	public static void addConformanceTest(TestSuite suite) {
		suite.addTest(MutableObservableValueContractTest.suite(new Delegate()));
	}

	/* package */static class Delegate extends
			AbstractObservableValueContractDelegate {
		private Bean bean;

		@Override
		public void setUp() {
			super.setUp();

			bean = new Bean("");
		}

		@Override
		public IObservableValue createObservableValue(Realm realm) {
			return BeansObservables.observeValue(realm, bean, "value");
		}

		@Override
		public void change(IObservable observable) {
			IObservableValue observableValue = (IObservableValue) observable;
			observableValue.setValue(createValue(observableValue));
		}

		@Override
		public Object getValueType(IObservableValue observable) {
			return String.class;
		}

		@Override
		public Object createValue(IObservableValue observable) {
			return observable.getValue() + "a";
		}
	}

	/**
	 * Throws an exception when the value is set.
	 *
	 * @since 3.2
	 */
	/* package */class ThrowsSetException {
		private String value;

		/* package */NullPointerException thrownException;

		public void setValue(String value) {
			throw thrownException = new NullPointerException();
		}

		public String getValue() {
			return value;
		}
	}

	/* package */class ThrowsGetException {
		public String value;

		/* package */NullPointerException thrownException;

		public String getValue() {
			throw thrownException = new NullPointerException();
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
