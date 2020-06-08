/*******************************************************************************
 * Copyright (c) 2004, 2015 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.ui.internal.preferences;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.runtime.Preferences;

/**
 * @since 3.1
 */
public final class PreferencesAdapter extends PropertyMapAdapter {
	private Preferences store;

	private Preferences.IPropertyChangeListener listener = event -> firePropertyChange(event.getProperty());

	public PreferencesAdapter(Preferences toConvert) {
		this.store = toConvert;
	}

	@Override
	protected void attachListener() {
		store.addPropertyChangeListener(listener);
	}

	@Override
	protected void detachListener() {
		store.removePropertyChangeListener(listener);
	}

	@Override
	public Set keySet() {
		Set result = new HashSet();

		java.util.Collections.addAll(result, store.propertyNames());

		return result;
	}

	@Override
	public Object getValue(String propertyId, Class propertyType) {
		if (propertyType.isAssignableFrom(String.class)) {
			return store.getString(propertyId);
		}

		if (propertyType == Boolean.class) {
			return store.getBoolean(propertyId) ? Boolean.TRUE : Boolean.FALSE;
		}

		if (propertyType == Double.class) {
			return Double.valueOf(store.getDouble(propertyId));
		}

		if (propertyType == Float.class) {
			return Float.valueOf(store.getFloat(propertyId));
		}

		if (propertyType == Integer.class) {
			return Integer.valueOf(store.getInt(propertyId));
		}

		if (propertyType == Long.class) {
			return Long.valueOf(store.getLong(propertyId));
		}

		return null;
	}

	@Override
	public boolean propertyExists(String propertyId) {
		return store.contains(propertyId);
	}

	@Override
	public void setValue(String propertyId, Object newValue) {
		if (newValue instanceof String) {
			store.setValue(propertyId, (String) newValue);
		} else if (newValue instanceof Integer) {
			store.setValue(propertyId, ((Integer) newValue).intValue());
		} else if (newValue instanceof Boolean) {
			store.setValue(propertyId, ((Boolean) newValue).booleanValue());
		} else if (newValue instanceof Double) {
			store.setValue(propertyId, ((Double) newValue).doubleValue());
		} else if (newValue instanceof Float) {
			store.setValue(propertyId, ((Float) newValue).floatValue());
		} else if (newValue instanceof Integer) {
			store.setValue(propertyId, ((Integer) newValue).intValue());
		} else if (newValue instanceof Long) {
			store.setValue(propertyId, ((Long) newValue).longValue());
		}
	}

}
