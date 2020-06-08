/*******************************************************************************
 * Copyright (c) 2009, 2016 Remy Chi Jian Suen and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Remy Chi Jian Suen <remy.suen@gmail.com> - initial API and implementation
 ******************************************************************************/

package org.eclipse.ui.tests.internal;

import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.tests.harness.util.UITestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PerspectiveSwitcherTest extends UITestCase {

	public PerspectiveSwitcherTest() {
		super(PerspectiveSwitcherTest.class.getSimpleName());
	}

	/**
	 * This test ensures that our workbench window's perspective bar can be
	 * docked at the other side of the window even if the 'Open Perspective'
	 * contribution item is not there.
	 */
	@Test
	public void testCreateBarManagerBug274486() {
		// we want to move the perspective bar to the other side so that it will
		// be recreated, TOP_RIGHT and TOP_LEFT should switch to LEFT, LEFT
		// should switch to TOP_RIGHT or TOP_LEFT
		IPreferenceStore apiPreferenceStore = PrefUtil.getAPIPreferenceStore();
		String originalPerspectiveBarPosition = apiPreferenceStore
				.getString(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR);
		String targetDockPosition = null;
		if (IWorkbenchPreferenceConstants.TOP_RIGHT.equals(originalPerspectiveBarPosition)
				|| IWorkbenchPreferenceConstants.TOP_LEFT.equals(originalPerspectiveBarPosition)) {
			targetDockPosition = IWorkbenchPreferenceConstants.LEFT;
		} else if (IWorkbenchPreferenceConstants.LEFT.equals(originalPerspectiveBarPosition)) {
			targetDockPosition = IWorkbenchPreferenceConstants.TOP_RIGHT;
		} else {
			throw new IllegalStateException("The current perspective bar position is unknown: " //$NON-NLS-1$
					+ originalPerspectiveBarPosition);
		}

		WorkbenchWindow window = (WorkbenchWindow) fWorkbench.getActiveWorkbenchWindow();
		assertNotNull("We should have a perspective bar in the beginning", //$NON-NLS-1$
				getPerspectiveSwitcher(window));

		// turn off the 'Open Perspective' item
		setPreference(apiPreferenceStore, IWorkbenchPreferenceConstants.SHOW_OPEN_ON_PERSPECTIVE_BAR, false);

		// now we dock the perspective bar on the other end
		setPreference(apiPreferenceStore, IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, targetDockPosition);

		// check that we have a perspective bar, the setValue(String, String)
		// method does not throw an exception because the perspective bar
		// creation code is wrapped around a SafeRunner so the exception does
		// not get propagated, hence, we need to check here
		assertNotNull("The perspective bar should have been created successfully", //$NON-NLS-1$
				getPerspectiveSwitcher(window));
	}

	private static Object getPerspectiveSwitcher(WorkbenchWindow window) {
		EModelService modelService = window.getService(EModelService.class);
		return modelService.find("PerspectiveSwitcher", window.getModel());
	}
}
