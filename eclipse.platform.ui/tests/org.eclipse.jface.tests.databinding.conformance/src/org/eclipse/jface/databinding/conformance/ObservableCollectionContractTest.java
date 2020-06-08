/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
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
 *     Matthew Hall - bugs 213145, 274450
 *     Simon Scholz <simon.scholz@vogella.com> - Bug 444829
 *******************************************************************************/

package org.eclipse.jface.databinding.conformance;

import java.util.Arrays;

import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.jface.databinding.conformance.delegate.IObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.CurrentRealm;
import org.eclipse.jface.databinding.conformance.util.RealmTester;
import org.eclipse.jface.databinding.conformance.util.SuiteBuilder;

import junit.framework.Test;

/**
 * Tests for IObservableCollection that don't mutate the collection.
 * <p>
 * This class is experimental and can change at any time. It is recommended to
 * not subclass or assume the test names will not change. The only API that is
 * guaranteed to not change are the constructors. The tests will remain public
 * and not final in order to allow for consumers to turn off a test if needed by
 * subclassing.
 * </p>
 *
 * @since 3.2
 */
public class ObservableCollectionContractTest extends ObservableContractTest {
	private IObservableCollectionContractDelegate delegate;

	private IObservableCollection collection;

	public ObservableCollectionContractTest(
			IObservableCollectionContractDelegate delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public ObservableCollectionContractTest(String testName,
			IObservableCollectionContractDelegate delegate) {
		super(testName, delegate);
		this.delegate = delegate;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		collection = (IObservableCollection) getObservable();
	}

	public void testIterator_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.iterator(), "Collection.iterator()", collection);
	}

	public void testIterator_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.iterator(), (CurrentRealm) collection.getRealm());
	}

	public void testSize_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.size(), "Collection.size()", collection);
	}

	public void testSize_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.size(), (CurrentRealm) collection.getRealm());
	}

	public void testIsEmpty_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.isEmpty(), "Collection.isEmpty()", collection);
	}

	public void testIsEmpty_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.isEmpty(), (CurrentRealm) collection.getRealm());
	}

	public void testContains_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.contains(delegate.createElement(collection)), "Collection.contains(...)",
				collection);
	}

	public void testContains_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.contains(delegate.createElement(collection)),
				(CurrentRealm) collection.getRealm());
	}

	public void testContainsAll_GetterCalled() throws Exception {
		assertGetterCalled(
				() -> collection.containsAll(Arrays.asList(new Object[] { delegate.createElement(collection) })),
				"Collection.containsAll(Collection)", collection);
	}

	public void testContainsAll_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(
				() -> collection.containsAll(Arrays.asList(new Object[] { delegate.createElement(collection) })),
				(CurrentRealm) collection.getRealm());
	}

	public void testToArray_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.toArray(), "Collection.toArray()", collection);
	}

	public void testToArray_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.toArray(), (CurrentRealm) collection.getRealm());
	}

	public void testToArrayWithObjectArray_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.toArray(new Object[collection.size()]), "Collection.toArray(Object[])",
				collection);
	}

	public void testToArrayWithObjectArray_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.toArray(new Object[collection.size()]),
				(CurrentRealm) collection.getRealm());
	}

	public void testEquals_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.equals(collection), "Collection.equals(Object)", collection);
	}

	public void testEquals_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.equals(collection), (CurrentRealm) collection.getRealm());
	}

	public void testEquals_IdentityEquals() throws Exception {
		assertTrue(collection.equals(collection));
	}

	public void testHashCode_GetterCalled() throws Exception {
		assertGetterCalled(() -> collection.hashCode(), "Collection.hashCode()", collection);
	}

	public void testHashCode_RealmCheck() throws Exception {
		RealmTester.exerciseCurrent(() -> collection.hashCode(), (CurrentRealm) collection.getRealm());
	}

	public void testGetElementType_ReturnsType() throws Exception {
		assertEquals(
				"Element type of the collection should be returned from IObservableCollection.getElementType()",
				delegate.getElementType(collection),
				collection.getElementType());
	}

	public static Test suite(IObservableCollectionContractDelegate delegate) {
		return new SuiteBuilder().addObservableContractTest(
				ObservableCollectionContractTest.class, delegate).build();
	}
}
