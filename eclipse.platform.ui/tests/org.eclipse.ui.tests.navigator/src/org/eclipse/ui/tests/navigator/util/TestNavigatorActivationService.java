/*******************************************************************************
 * Copyright (c) 2015 Google Inc. and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     C. Sean Young <csyoung@google.com> - Bug 436645
 ******************************************************************************/
package org.eclipse.ui.tests.navigator.util;

import org.eclipse.ui.navigator.IExtensionActivationListener;
import org.eclipse.ui.navigator.INavigatorActivationService;
import org.eclipse.ui.navigator.INavigatorContentDescriptor;

/**
 * A "mock" NavigatorActivationService.
 */
public class TestNavigatorActivationService implements INavigatorActivationService {
	@Override
	public INavigatorContentDescriptor[] activateExtensions(String[] extensionIds,
			boolean toDeactivateAllOthers) {
		return null;
	}

	@Override
	public INavigatorContentDescriptor[] deactivateExtensions(String[] extensionIds,
			boolean toActivateAllOthers) {
		return null;
	}

	@Override
	public boolean isNavigatorExtensionActive(String aNavigatorExtensionId) {
		return false;
	}

	@Override
	public void persistExtensionActivations() {
	}

	@Override
	public void addExtensionActivationListener(IExtensionActivationListener aListener) {
	}

	@Override
	public void removeExtensionActivationListener(IExtensionActivationListener aListener) {
	}
}
