/*******************************************************************************
 * Copyright (c) 2012, 2017 SAP AG and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.tests.publisher.actions;

import org.eclipse.core.runtime.IStatus;
import org.hamcrest.*;

public class StatusMatchers {

	public static Matcher<IStatus> errorStatus() {
		return new TypeSafeMatcher<IStatus>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("a status with severity ERROR");
			}

			@Override
			public boolean matchesSafely(IStatus item) {
				return item.matches(IStatus.ERROR);
			}
		};
	}

	public static Matcher<IStatus> okStatus() {
		return new TypeSafeMatcher<IStatus>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("a status with severity OK");
			}

			@Override
			public boolean matchesSafely(IStatus item) {
				return item.isOK();
			}
		};
	}

	public static Matcher<IStatus> statusWithMessageWhich(final Matcher<String> messageMatcher) {
		return new TypeSafeMatcher<IStatus>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("a status with a message which is ");
				description.appendDescriptionOf(messageMatcher);
			}

			@Override
			public boolean matchesSafely(IStatus item) {
				return messageMatcher.matches(item.getMessage());
			}
		};
	}

}
