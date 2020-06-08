/*******************************************************************************
 * Copyright (c) 2007, 2018 Brad Reynolds and others.
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
 *     Matthew Hall - bugs 221351, 213145, 244098, 246103, 194734, 268688,
 *                    301774
 ******************************************************************************/

package org.eclipse.core.tests.internal.databinding.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.beans.IBeanProperty;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.jface.databinding.conformance.MutableObservableListContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.CurrentRealm;
import org.eclipse.jface.databinding.conformance.util.ListChangeEventTracker;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;
import org.eclipse.swt.widgets.Display;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

/**
 * @since 1.1
 */
public class JavaBeanObservableArrayBasedListTest extends
		AbstractDefaultRealmTestCase {
	private IObservableList list;
	private IBeanObservable beanObservable;

	private PropertyDescriptor propertyDescriptor;

	private Bean bean;

	private String propertyName;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		propertyName = "array";
		propertyDescriptor = ((IBeanProperty) BeanProperties.list(Bean.class,
				propertyName)).getPropertyDescriptor();
		bean = new Bean(new Object[0]);

		list = BeansObservables.observeList(DisplayRealm.getRealm(Display
				.getDefault()), bean, propertyName);
		beanObservable = (IBeanObservable) list;
	}

	@Test
	public void testGetObserved() throws Exception {
		assertSame(bean, beanObservable.getObserved());
	}

	@Test
	public void testGetPropertyDescriptor() throws Exception {
		assertEquals(propertyDescriptor, beanObservable.getPropertyDescriptor());
	}

	@Test
	public void testRegistersListenerAfterFirstListenerIsAdded()
			throws Exception {
		assertFalse(bean.changeSupport.hasListeners(propertyName));
		list.addListChangeListener(new ListChangeEventTracker());
		assertTrue(bean.changeSupport.hasListeners(propertyName));
	}

	@Test
	public void testRemovesListenerAfterLastListenerIsRemoved()
			throws Exception {
		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertTrue(bean.changeSupport.hasListeners(propertyName));
		list.removeListChangeListener(listener);
		assertFalse(bean.changeSupport.hasListeners(propertyName));
	}

	@Test
	public void testFiresListChangeEvents() throws Exception {
		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);
		bean.setArray(new Bean[] { new Bean() });
		assertEquals(1, listener.count);
	}

	@Test
	public void testAddAddsElement() throws Exception {
		int count = list.size();
		String element = "1";

		assertEquals(0, count);
		list.add(element);
		assertEquals(count + 1, list.size());
		assertEquals(element, bean.getArray()[count]);
	}

	@Test
	public void testAddListChangeEvent() throws Exception {
		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);
		String element = "1";

		list.add(element);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;

		assertSame(list, event.getObservableList());
		assertDiff(event.diff, Collections.EMPTY_LIST, Collections
				.singletonList("1"));
	}

	@Test
	public void testAdd_FiresPropertyChangeEvent() throws Exception {
		assertPropertyChangeEvent(bean, () -> list.add("0"));
	}

	@Test
	public void testAddWithIndex() throws Exception {
		String element = "1";
		assertEquals(0, list.size());

		list.add(0, element);
		assertEquals(element, bean.getArray()[0]);
	}

	@Test
	public void testAddAtIndexListChangeEvent() throws Exception {
		String element = "1";
		assertEquals(0, list.size());

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		list.add(0, element);

		ListChangeEvent event = listener.event;
		assertDiff(event.diff, Collections.EMPTY_LIST, Collections
				.singletonList("1"));
	}

	@Test
	public void testAddAtIndexPropertyChangeEvent() throws Exception {
		assertPropertyChangeEvent(bean, () -> list.add(0, "0"));
	}

	@Test
	public void testRemove() throws Exception {
		String element = "1";
		list.add(element);

		assertEquals(1, bean.getArray().length);
		list.remove(element);
		assertEquals(0, bean.getArray().length);
	}

	@Test
	public void testRemoveListChangeEvent() throws Exception {
		String element = "1";
		list.add(element);

		assertEquals(1, list.size());
		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		list.remove(element);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Collections.singletonList("1"),
				Collections.EMPTY_LIST);
	}

	@Test
	public void testRemovePropertyChangeEvent() throws Exception {
		list.add("0");

		assertPropertyChangeEvent(bean, () -> list.remove("0"));
	}

	@Test
	public void testRemoveAtIndex() throws Exception {
		String element = "1";
		list.add(element);

		assertEquals(element, bean.getArray()[0]);

		list.remove(0);
		assertEquals(0, bean.getArray().length);
	}

	@Test
	public void testRemoveAtIndexListChangeEvent() throws Exception {
		String element = "1";
		list.add(element);

		assertEquals(1, list.size());
		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		list.remove(0);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Collections.singletonList(element),
				Collections.EMPTY_LIST);
	}

	@Test
	public void testRemoveAtIndexPropertyChangeEvent() throws Exception {
		list.add("0");
		assertPropertyChangeEvent(bean, () -> list.remove(0));
	}

	@Test
	public void testAddAll() throws Exception {
		Collection elements = Arrays.asList(new String[] { "1", "2" });
		assertEquals(0, list.size());

		list.addAll(elements);

		assertEquals(2, bean.getArray().length);
	}

	@Test
	public void testAddAllListChangEvent() throws Exception {
		List elements = Arrays.asList(new String[] { "1", "2" });
		assertEquals(0, list.size());

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);
		assertEquals(0, listener.count);

		list.addAll(elements);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Collections.EMPTY_LIST, Arrays
				.asList(new String[] { "1", "2" }));
	}

	@Test
	public void testAddAllPropertyChangeEvent() throws Exception {
		assertPropertyChangeEvent(bean, () -> list.addAll(Arrays.asList(new String[] { "0", "1" })));
	}

	@Test
	public void testAddAllAtIndex() throws Exception {
		List elements = Arrays.asList(new String[] { "1", "2" });
		list.addAll(elements);

		assertEquals(2, list.size());

		list.addAll(2, elements);

		assertEquals(4, bean.getArray().length);
		assertEquals(elements.get(0), bean.getArray()[0]);
		assertEquals(elements.get(1), bean.getArray()[1]);
	}

	@Test
	public void testAddAllAtIndexListChangeEvent() throws Exception {
		List elements = Arrays.asList(new String[] { "1", "2" });
		list.addAll(elements);

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);

		list.addAll(2, elements);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Arrays.asList(new Object[] { "1", "2" }), Arrays
				.asList(new Object[] { "1", "2", "1", "2" }));
	}

	@Test
	public void testAddAllAtIndexPropertyChangeEvent() throws Exception {
		assertPropertyChangeEvent(bean, () -> list.addAll(0, Arrays.asList(new String[] { "1", "2" })));
	}

	@Test
	public void testRemoveAll() throws Exception {
		list.addAll(Arrays.asList(new String[] { "1", "2", "3", "4" }));
		assertEquals(4, bean.getArray().length);

		list.removeAll(Arrays.asList(new String[] { "2", "4" }));

		assertEquals(2, bean.getArray().length);
		assertEquals("1", bean.getArray()[0]);
		assertEquals("3", bean.getArray()[1]);
	}

	@Test
	public void testRemoveAllListChangeEvent() throws Exception {
		List elements = Arrays.asList(new String[] { "1", "2" });
		list.addAll(elements);
		list.addAll(elements);

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);
		list.removeAll(elements);

		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Arrays
				.asList(new Object[] { "1", "2", "1", "2" }),
				Collections.EMPTY_LIST);
	}

	@Test
	public void testRemoveAllPropertyChangeEvent() throws Exception {
		list.add("0");
		assertPropertyChangeEvent(bean, () -> list.removeAll(Arrays.asList(new String[] { "0" })));
	}

	@Test
	public void testRetainAll() throws Exception {
		List elements = Arrays.asList(new String[] { "0", "1", "2", "3" });
		list.addAll(elements);

		assertEquals(4, bean.getArray().length);

		list.retainAll(elements.subList(0, 2));
		assertEquals(2, bean.getArray().length);

		assertEquals(elements.get(0), bean.getArray()[0]);
		assertEquals(elements.get(1), bean.getArray()[1]);
	}

	@Test
	public void testRetainAllListChangeEvent() throws Exception {
		List elements = Arrays.asList(new String[] { "0", "1", "2", "3" });
		list.addAll(elements);

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);
		list.retainAll(elements.subList(0, 2));

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Arrays
				.asList(new Object[] { "0", "1", "2", "3" }), Arrays
				.asList(new Object[] { "0", "1" }));
	}

	@Test
	public void testRetainAllPropertyChangeEvent() throws Exception {
		list.addAll(Arrays.asList(new String[] { "0", "1" }));

		assertPropertyChangeEvent(bean, () -> list.retainAll(Arrays.asList(new String[] { "0" })));
	}

	@Test
	public void testSet() throws Exception {
		String oldElement = "old";
		String newElement = "new";
		list.add(oldElement);

		assertEquals(oldElement, bean.getArray()[0]);

		list.set(0, newElement);
		assertEquals(newElement, bean.getArray()[0]);
	}

	@Test
	public void testMove() throws Exception {
		String element0 = "element0";
		String element1 = "element1";
		list.add(element0);
		list.add(element1);

		assertEquals(2, bean.getArray().length);
		assertEquals(element0, bean.getArray()[0]);
		assertEquals(element1, bean.getArray()[1]);

		list.move(0, 1);

		assertEquals(2, bean.getArray().length);
		assertEquals(element1, bean.getArray()[0]);
		assertEquals(element0, bean.getArray()[1]);
	}

	@Test
	public void testSetListChangeEvent() throws Exception {
		String oldElement = "old";
		String newElement = "new";
		list.add(oldElement);

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);
		assertEquals(0, listener.count);

		list.set(0, newElement);

		assertEquals(1, listener.count);
		ListChangeEvent event = listener.event;
		assertSame(list, event.getObservableList());

		assertDiff(event.diff, Collections.singletonList(oldElement),
				Collections.singletonList(newElement));
	}

	@Test
	public void testSetPropertyChangeEvent() throws Exception {
		list.add("0");
		assertPropertyChangeEvent(bean, () -> list.set(0, "1"));
	}

	@Test
	public void testListChangeEventFiresWhenNewListIsSet() throws Exception {
		Bean[] elements = new Bean[] { new Bean(), new Bean() };

		ListChangeEventTracker listener = new ListChangeEventTracker();
		list.addListChangeListener(listener);

		assertEquals(0, listener.count);
		bean.setArray(elements);
		assertEquals(1, listener.count);
	}

	@Test
	public void testSetBeanProperty_CorrectForNullOldAndNewValues() {
		// The java bean spec allows the old and new values in a
		// PropertyChangeEvent to be null, which indicates that an unknown
		// change occured.

		// This test ensures that JavaBeanObservableValue fires the correct
		// value diff even if the bean implementor is lazy :-P

		Bean bean = new AnnoyingBean();
		bean.setArray(new Object[] { "old" });
		IObservableList observable = BeansObservables.observeList(
				new CurrentRealm(true), bean, "array");
		ListChangeEventTracker tracker = ListChangeEventTracker
				.observe(observable);
		bean.setArray(new Object[] { "new" });
		assertEquals(1, tracker.count);

		List list = new ArrayList();
		list.add("old");
		tracker.event.diff.applyTo(list);
		assertEquals(Collections.singletonList("new"), list);
	}

	@Test
	public void testModifyObservableList_FiresListChange() {
		Bean bean = new Bean(new Object[] { "old" });
		IObservableList observable = BeansObservables
				.observeList(bean, "array");
		ListChangeEventTracker tracker = ListChangeEventTracker
				.observe(observable);

		observable.set(0, "new");

		assertEquals(1, tracker.count);
		assertDiff(tracker.event.diff, Collections.singletonList("old"),
				Collections.singletonList("new"));
	}

	@Test
	public void testSetBeanPropertyOutsideRealm_FiresEventInsideRealm() {
		Bean bean = new Bean(new Object[0]);
		CurrentRealm realm = new CurrentRealm(true);
		IObservableList observable = BeansObservables.observeList(realm, bean,
				"array");
		ListChangeEventTracker tracker = ListChangeEventTracker
				.observe(observable);

		realm.setCurrent(false);
		bean.setArray(new Object[] { "element" });
		assertEquals(0, tracker.count);

		realm.setCurrent(true);
		assertEquals(1, tracker.count);
		assertDiff(tracker.event.diff, Collections.EMPTY_LIST, Collections
				.singletonList("element"));
	}

	private static void assertDiff(ListDiff diff, List oldList, List newList) {
		oldList = new ArrayList(oldList); // defensive copy in case arg is
		// unmodifiable
		diff.applyTo(oldList);
		assertEquals("applying diff to list did not produce expected result",
				newList, oldList);
	}

	private static void assertPropertyChangeEvent(Bean bean, Runnable runnable) {
		PropertyChangeTracker listener = new PropertyChangeTracker();
		bean.addPropertyChangeListener(listener);

		Object[] old = bean.getArray();
		assertEquals(0, listener.count);

		runnable.run();

		PropertyChangeEvent event = listener.evt;
		assertEquals("event did not fire", 1, listener.count);
		assertEquals("array", event.getPropertyName());
		assertTrue("old value", Arrays.equals(old, (Object[]) event
				.getOldValue()));
		assertTrue("new value", Arrays.equals(bean.getArray(), (Object[]) event
				.getNewValue()));
		assertFalse("lists are equal", Arrays.equals(bean.getArray(), old));
	}

	private static class PropertyChangeTracker implements
			PropertyChangeListener {
		int count;

		PropertyChangeEvent evt;

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			count++;
			this.evt = evt;
		}
	}

	public static void addConformanceTest(TestSuite suite) {
		suite.addTest(MutableObservableListContractTest.suite(new Delegate()));
	}

	static class Delegate extends AbstractObservableCollectionContractDelegate {
		@Override
		public IObservableCollection createObservableCollection(Realm realm,
				int elementCount) {
			String propertyName = "array";
			Object bean = new Bean(new Object[0]);

			IObservableList list = BeansObservables.observeList(realm, bean,
					propertyName, String.class);
			for (int i = 0; i < elementCount; i++)
				list.add(createElement(list));
			return list;
		}

		@Override
		public Object createElement(IObservableCollection collection) {
			return new Object();
		}

		@Override
		public Object getElementType(IObservableCollection collection) {
			return String.class;
		}

		@Override
		public void change(IObservable observable) {
			IObservableList list = (IObservableList) observable;
			list.add(createElement(list));
		}
	}
}
