/*******************************************************************************
 * Copyright (c) 2008, 2015 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 ******************************************************************************/

package org.eclipse.core.databinding.property;

/**
 * Marker interface for all property types in the properties framework.
 *
 * @since 1.2
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
 */
public interface IProperty {
}
