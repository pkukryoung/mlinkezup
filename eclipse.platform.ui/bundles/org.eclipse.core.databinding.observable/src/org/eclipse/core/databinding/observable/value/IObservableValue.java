/*******************************************************************************
 * Copyright (c) 2006, 2015 IBM Corporation and others.
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
 *     Matthew Hall - bug 237718
 *     Stefan Xenos <sxenos@gmail.com> - Bug 335792
 *******************************************************************************/

package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;

/**
 * A value whose changes can be tracked by value change listeners.
 *
 * @param <T>
 *            type of the value of the property
 *
 * @noimplement This interface is not intended to be implemented by clients.
 *              Clients should instead subclass one of the classes that
 *              implement this interface.
 *              <p>
 *              Authors of extensions to the databinding framework may extend
 *              this interface and indirectly implement it, but if doing so must
 *              also extend one of the framework classes. (Use an API problem
 *              filter to suppress the resulting warning.)
 *              <p>
 *              Direct implementers of this interface outside of the framework
 *              will be broken in future releases when methods are added to this
 *              interface.
 *
 * @see AbstractObservableValue
 *
 * @since 1.0
 */
public interface IObservableValue<T> extends IObservable {

	/**
	 * The value type of this observable value, or <code>null</code> if this
	 * observable value is untyped.
	 *
	 * @return the value type, or <code>null</code>
	 */
	public Object getValueType();

	/**
	 * Returns the value. Must be invoked in the {@link Realm} of the
	 * observable.
	 *
	 * @return the current value
	 * @TrackedGetter
	 */
	public T getValue();

	/**
	 * Sets the value. Must be invoked in the {@link Realm} of the observable.
	 *
	 * @param value
	 *            the value to set
	 * @throws UnsupportedOperationException
	 *             if this observable value cannot be set.
	 */
	public void setValue(T value);

	/**
	 *
	 * @param listener the change listener to add; not <code>null</code>
	 */
	public void addValueChangeListener(IValueChangeListener<? super T> listener);

	/**
	 * @param listener the change listener to remove; not <code>null</code>
	 */
	public void removeValueChangeListener(IValueChangeListener<? super T> listener);
}
