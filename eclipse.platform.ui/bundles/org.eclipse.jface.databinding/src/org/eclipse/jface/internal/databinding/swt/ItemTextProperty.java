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

package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Item;

/**
 * @since 3.3
 *
 */
public class ItemTextProperty extends WidgetStringValueProperty<Item> {
	@Override
	protected String doGetStringValue(Item source) {
		return source.getText();
	}

	@Override
	protected void doSetStringValue(Item source, String value) {
		source.setText(value == null ? "" : value); //$NON-NLS-1$
	}

	@Override
	public String toString() {
		return "Item.text <String>"; //$NON-NLS-1$
	}
}
