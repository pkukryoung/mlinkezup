/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Cloudsmith Inc - initial API and implementation
 *******************************************************************************/

package org.eclipse.equinox.p2.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.repository.helpers.RepositoryHelper;
import org.junit.Test;

/**
 * Tests RepositoryHandler
 */
public class RepositoryHelperTest {
	@Test
	public void testURISyntaxChecker() throws URISyntaxException {
		URI location = new URI("http://somwhere.com/path");
		IStatus result = RepositoryHelper.checkRepositoryLocationSyntax(location);
		assertTrue("1.0 Valid URI should be ok", result.isOK());

		location = new URI("ftp://somwhere.com/path");
		result = RepositoryHelper.checkRepositoryLocationSyntax(location);
		assertTrue("2.0 Valid URI should be ok", result.isOK());

		location = new URI("https://somwhere.com/path");
		result = RepositoryHelper.checkRepositoryLocationSyntax(location);
		assertTrue("3.0 Valid URI should be ok", result.isOK());

		location = new URI("htp://somwhere.com/path");
		result = RepositoryHelper.checkRepositoryLocationSyntax(location);
		assertFalse("4.0 Invalid URI should not be ok", result.isOK());

		location = new URI("/somwhere.com/path");
		result = RepositoryHelper.checkRepositoryLocationSyntax(location);
		assertFalse("5.0 Invalid URI should not be ok", result.isOK());
	}
}
