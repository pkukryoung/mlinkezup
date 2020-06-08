/*******************************************************************************
 * Copyright (c) 2009, 2015 IBM Corporation and others.
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
 *     Matthew Hall - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.databinding.typed;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.databinding.AdaptedValueProperty;
import org.eclipse.ui.internal.databinding.MultiSelectionProperty;
import org.eclipse.ui.internal.databinding.SingleSelectionProperty;

/**
 * Factory methods for creating properties for the Workbench.
 *
 * <p>
 * Examples:
 * </p>
 *
 * <pre>
 * WorkbenchProperties.singleSelection().observe(getSite().getService(ISelectionService.class))
 * </pre>
 *
 * <p>
 * This class is a new version of the deprecated class with the same name in the
 * parent package. The difference is that this class returns typed property
 * objects. This class is located in its own package to be able to coexist with
 * the old version while having the same name.
 *
 * @since 3.117
 */
public class WorkbenchProperties {
	/**
	 * Returns a value property which observes the source object as the adapted
	 * type, using the platform adapter manager. If the source is of the target
	 * type, or can be adapted to the target type, this is used as the value of
	 * property, otherwise <code>null</code>.
	 *
	 * @param adapter the adapter class
	 * @return a value property which observes the source object as the adapted
	 *         type.
	 */
	public static <S, T> IValueProperty<S, T> adaptedValue(Class<T> adapter) {
		return adaptedValue(adapter, Platform.getAdapterManager());
	}

	/**
	 * Returns a value property which observes the source object as the adapted
	 * type. If the source object is of the target type, or can be adapted to the
	 * target type, this is used as the value of property, otherwise
	 * <code>null</code>.
	 *
	 * @param adapter        the adapter class
	 * @param adapterManager the adapter manager used to adapt source objects
	 * @return a value property which observes the source object as the adapted
	 *         type.
	 */
	public static <S, T> IValueProperty<S, T> adaptedValue(Class<T> adapter,
			final IAdapterManager adapterManager) {
		return new AdaptedValueProperty<>(adapter, adapterManager);
	}

	/**
	 * Returns a property for observing the first element of a structured selection
	 * as exposed by {@link ISelectionService}.
	 *
	 * @return an observable value
	 */
	public static <S extends ISelectionService, T> IValueProperty<S, T> singleSelection() {
		return singleSelection(null, false);
	}

	/**
	 * Returns a property for observing the first element of a structured selection
	 * as exposed by {@link ISelectionService}.
	 *
	 * @param partId        the part id, or <code>null</code> if the selection can
	 *                      be from any part
	 * @param postSelection <code>true</code> if the selection should be delayed for
	 *                      keyboard-triggered selections
	 *
	 * @return an observable value
	 */
	@SuppressWarnings("unchecked")
	public static <S extends ISelectionService, T> IValueProperty<S, T> singleSelection(String partId,
			boolean postSelection) {
		return (IValueProperty<S, T>) singleSelection(partId, postSelection, Object.class);
	}

	/**
	 * Returns a property for observing the first element of a structured selection
	 * as exposed by {@link ISelectionService}.
	 *
	 * @param partId        the part id, or <code>null</code> if the selection can
	 *                      be from any part
	 * @param postSelection <code>true</code> if the selection should be delayed for
	 *                      keyboard-triggered selections
	 * @param valueType     value type of the selection
	 *
	 * @return an observable value
	 */
	public static <S extends ISelectionService, T> IValueProperty<S, T> singleSelection(String partId,
			boolean postSelection, Class<T> valueType) {
		return new SingleSelectionProperty<>(partId, postSelection, valueType);
	}

	/**
	 * Returns a property for observing the elements of a structured selection as
	 * exposed by {@link ISelectionService}.
	 *
	 * @return an observable value
	 */
	@SuppressWarnings("unchecked")
	public static <S extends ISelectionService, E> IListProperty<S, E> multipleSelection() {
		return (IListProperty<S, E>) multipleSelection(Object.class);
	}

	/**
	 * Returns a property for observing the elements of a structured selection as
	 * exposed by {@link ISelectionService}.
	 *
	 * @param elementType element type of the selection
	 *
	 * @return an observable value
	 */
	public static <S extends ISelectionService, E> IListProperty<S, E> multipleSelection(Class<E> elementType) {
		return multipleSelection(null, false, elementType);
	}


	/**
	 * Returns a property for observing the elements of a structured selection as
	 * exposed by {@link ISelectionService}.
	 *
	 * @param partId        the part id, or <code>null</code> if the selection can
	 *                      be from any part
	 * @param postSelection <code>true</code> if the selection should be delayed for
	 *                      keyboard-triggered selections
	 *
	 * @return an observable value
	 */
	@SuppressWarnings("unchecked")
	public static <S extends ISelectionService, E> IListProperty<S, E> multipleSelection(String partId,
			boolean postSelection) {
		return (IListProperty<S, E>) multipleSelection(partId, postSelection, Object.class);
	}

	/**
	 * Returns a property for observing the elements of a structured selection as
	 * exposed by {@link ISelectionService}.
	 *
	 * @param partId        the part id, or <code>null</code> if the selection can
	 *                      be from any part
	 * @param postSelection <code>true</code> if the selection should be delayed for
	 *                      keyboard-triggered selections
	 * @param elementType   type of selection elements
	 *
	 * @return an observable value
	 */
	public static <S extends ISelectionService, E> IListProperty<S, E> multipleSelection(String partId,
			boolean postSelection, Class<E> elementType) {
		return new MultiSelectionProperty<>(partId, postSelection, elementType);
	}

}
