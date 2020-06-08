/*******************************************************************************
 * Copyright (c) 2014, 2019 Daniel Rolka and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Daniel Rolka - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.tests.themes;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine.StylingPreferencesHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.junit.Test;
import org.osgi.service.event.Event;

public class StylingPreferencesHandlerTest {
	@Test
	public void testHandleEvent() throws Exception {
		//given
		IEclipsePreferences pref1 = mock(IEclipsePreferences.class);
		IEclipsePreferences pref2 = mock(IEclipsePreferences.class);

		StylingPreferencesHandlerTestable handler = spy(new StylingPreferencesHandlerTestable(mock(Display.class)));
		doReturn(new HashSet<>(Arrays.asList(pref1, pref2))).when(handler).getThemeRelatedPreferences();
		doReturn(Arrays.asList("pref1.prop1", "pref1.prop2")).when(handler).getOverriddenPropertyNames(pref1);
		doReturn(Arrays.asList("pref2.prop1")).when(handler).getOverriddenPropertyNames(pref2);

		IThemeEngine themeEngine = mock(IThemeEngine.class);

		Map<String, Object> eventParams = new HashMap<>();
		eventParams.put(IThemeEngine.Events.THEME_ENGINE, themeEngine);

		//when
		handler.handleEvent(new Event(IThemeEngine.Events.THEME_CHANGED, eventParams));

		//then
		verify(handler, times(1)).resetOverriddenPreferences();
		verify(themeEngine, times(2)).applyStyles(anyObject(), anyBoolean());
		verify(themeEngine, times(1)).applyStyles(pref1, false);
		verify(themeEngine, times(1)).applyStyles(pref2, false);
	}

	@Test
	public void testResetOverriddenPreferences() throws Exception {
		//given
		IEclipsePreferences pref1 = mock(IEclipsePreferences.class);
		IEclipsePreferences pref2 = mock(IEclipsePreferences.class);

		StylingPreferencesHandlerTestable handler = spy(new StylingPreferencesHandlerTestable(mock(Display.class)));
		doReturn(new HashSet<>(Arrays.asList(pref1, pref2))).when(handler).getThemeRelatedPreferences();
		doReturn(Arrays.asList("pref1.prop1", "pref1.prop2")).when(handler).getOverriddenPropertyNames(pref1);
		doReturn(Arrays.asList("pref2.prop1", "pref2.prop2")).when(handler).getOverriddenPropertyNames(pref2);

		//when
		handler.resetOverriddenPreferences();

		//then
		verify(handler, times(1)).resetOverriddenPreferences(pref1);
		verify(pref1, times(1)).remove("pref1.prop1");
		verify(pref1, times(1)).remove("pref1.prop2");
		verify(handler, times(1)).removeOverriddenPropertyNames(pref1);

		verify(handler, times(1)).resetOverriddenPreferences(pref2);
		verify(pref2, times(1)).remove("pref2.prop1");
		verify(pref2, times(1)).remove("pref2.prop2");
		verify(handler, times(1)).removeOverriddenPropertyNames(pref2);
	}

	@Test
	public void testGetPreferences() {
		Set<IEclipsePreferences> result = new StylingPreferencesHandler(mock(Display.class)) {
			@Override
			public Set<IEclipsePreferences> getThemeRelatedPreferences() {
				return super.getThemeRelatedPreferences();
			}
		}.getThemeRelatedPreferences();

		assertFalse(result.isEmpty());
	}

	@Test
	public void testAddOnDisplayDisposed() throws Exception {
		//given
		final Listener listener = mock(Listener.class);

		Display display = mock(Display.class);

		//when
		new StylingPreferencesHandler(display) {
			@Override
			protected Listener createOnDisplayDisposedListener() {
				return listener;
			}
		};

		//then
		verify(display, times(1)).addListener(SWT.Dispose, listener);
	}

	@Test
	public void testOnDisplayDisposedListener() throws Exception {
		//given
		StylingPreferencesHandlerTestable handler = spy(new StylingPreferencesHandlerTestable(mock(Display.class)));

		Listener listener = handler.createOnDisplayDisposedListener();

		//when
		listener.handleEvent(mock(org.eclipse.swt.widgets.Event.class));

		//then
		verify(handler, times(1)).resetOverriddenPreferences();
	}

	public static class StylingPreferencesHandlerTestable extends StylingPreferencesHandler {
		public StylingPreferencesHandlerTestable(Display display) {
			super(display);
		}

		@Override
		public Set<IEclipsePreferences> getThemeRelatedPreferences() {
			return Collections.emptySet();
		}

		@Override
		public void resetOverriddenPreferences() {
			super.resetOverriddenPreferences();
		}

		@Override
		public void resetOverriddenPreferences(IEclipsePreferences preferences) {
			super.resetOverriddenPreferences(preferences);
		}

		@Override
		public List<String> getOverriddenPropertyNames(
				IEclipsePreferences preferences) {
			return null;
		}

		@Override
		public void removeOverriddenPropertyNames(IEclipsePreferences preferences) {
			super.removeOverriddenPropertyNames(preferences);
		}

		@Override
		public Listener createOnDisplayDisposedListener() {
			return super.createOnDisplayDisposedListener();
		}
	}
}
