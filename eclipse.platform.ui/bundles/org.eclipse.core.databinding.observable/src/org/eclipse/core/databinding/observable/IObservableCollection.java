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
 *     Stefan Xenos <sxenos@gmail.com> - Bug 335792
 *******************************************************************************/

package org.eclipse.core.databinding.observable;

import java.util.Collection;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;

/**
 * Interface for observable collections. Only general change listeners can be
 * added to an observable collection. Listeners interested in incremental
 * changes have to be added using more concrete subtypes such as
 * {@link IObservableList} or {@link IObservableSet}.
 *
 * @param <E>
 *            type of the elements in the collection
 *
 * @noextend This interface is not intended to be extended by clients.
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
 * @since 1.0
 */
public interface IObservableCollection<E> extends IObservable, Collection<E> {

	/**
	 * Returns the element type of this observable collection, or
	 * <code>null</code> if this observable collection is untyped.
	 *
	 * @return the element type of this observable collection, or
	 *         <code>null</code> if this observable collection is untyped.
	 */
	Object getElementType();

}
