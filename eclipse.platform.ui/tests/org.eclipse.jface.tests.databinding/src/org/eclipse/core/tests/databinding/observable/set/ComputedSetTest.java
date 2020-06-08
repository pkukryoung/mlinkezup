/*******************************************************************************
 * Copyright (c) 2008, 2018 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 237703)
 ******************************************************************************/

package org.eclipse.core.tests.databinding.observable.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.databinding.observable.AbstractObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.set.ComputedSet;
import org.eclipse.jface.databinding.conformance.ObservableCollectionContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.SetChangeEventTracker;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

public class ComputedSetTest extends AbstractDefaultRealmTestCase {
	ComputedSetStub set;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		set = new ComputedSetStub();
		set.size(); // Force set to compute
	}

	@Test
	public void testDependency_Staleness() {
		assertFalse(set.isStale());
		set.dependency.fireStale();
		assertTrue(set.isStale());
	}

	@Test
	public void testDependency_FiresSetChange() {
		assertEquals(set.nextComputation, set);

		Object element = new Object();
		set.nextComputation.add(element);

		set.dependency.fireChange();

		assertEquals(Collections.singleton(element), set);
	}

	@Test
	public void testDependency_NoStaleEventIfAlreadyDirty() {
		set.dependency.fireChange();
		set.addStaleListener(staleEvent -> fail("Should not fire stale when set is already dirty"));
		set.dependency.fireStale();
	}

	@Test
	public void testDependency_SetChangeEventFiresOnlyWhenNotDirty() {
		SetChangeEventTracker tracker = SetChangeEventTracker.observe(set);

		set.dependency.fireChange();
		assertEquals(
				"ComputedSet should fire set change event when its dependency changes",
				1, tracker.count);

		set.dependency.fireChange();
		assertEquals(
				"ComputedSet should not fire set change events when dirty",
				1, tracker.count);

		set.size(); // Force set to recompute.
		set.dependency.fireChange();
		assertEquals(
				"ComputedSet should fire set change event when its dependency changes",
				2, tracker.count);
	}

	static class ComputedSetStub extends ComputedSet {
		Set nextComputation = new HashSet();
		ObservableStub dependency;

		ComputedSetStub() {
			this(Realm.getDefault());
		}

		ComputedSetStub(Realm realm) {
			super(realm);
			dependency = new ObservableStub(realm);
		}

		@Override
		protected Set calculate() {
			ObservableTracker.getterCalled(dependency);
			return new HashSet(nextComputation);
		}
	}

	static class ObservableStub extends AbstractObservable {
		public ObservableStub(Realm realm) {
			super(realm);
		}

		boolean stale;

		@Override
		public boolean isStale() {
			return stale;
		}

		@Override
		protected void fireStale() {
			stale = true;
			super.fireStale();
		}

		@Override
		protected void fireChange() {
			super.fireChange();
		}
	}

	public static void addConformanceTest(TestSuite suite) {
		suite.addTest(ObservableCollectionContractTest.suite(new Delegate()));
	}

	static class Delegate extends AbstractObservableCollectionContractDelegate {
		@Override
		public IObservableCollection createObservableCollection(Realm realm,
				int elementCount) {
			final ComputedSetStub set = new ComputedSetStub(realm);
			for (int i = 0; i < elementCount; i++)
				set.nextComputation.add(createElement(set));
			set.size(); // force set to compute
			return set;
		}

		@Override
		public void change(IObservable observable) {
			ComputedSetStub set = (ComputedSetStub) observable;
			set.nextComputation.add(new Object());
			set.dependency.fireChange();
		}

		@Override
		public Object createElement(IObservableCollection collection) {
			return new Object();
		}

		@Override
		public void setStale(IObservable observable, boolean stale) {
			if (stale)
				((ComputedSetStub) observable).dependency.fireStale();
			else {
				ComputedSetStub computedSet = (ComputedSetStub) observable;
				computedSet.dependency.stale = false;
				computedSet.dependency.fireChange();
			}
		}
	}
}