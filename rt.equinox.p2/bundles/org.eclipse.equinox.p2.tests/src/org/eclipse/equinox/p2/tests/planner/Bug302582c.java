/*******************************************************************************
 * Copyright (c) 2010, 2017 IBM Corporation and others.
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
package org.eclipse.equinox.p2.tests.planner;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;

public class Bug302582c extends Bug302582 {
	@Override
	protected String getProfileId() {
		return "PlatformProfile";
	}

	@Override
	protected String getTestDataPath() {
		return "testData/bug302582c";
	}

	/*
	 * Profile already contains a single optional singleton IU. Try to install 3 versions of that IU into it. (including the lower
	 * version which is already installed)
	 */
	@Override
	public void testInstall() {
		IPlanner planner = createPlanner();

		// create the actual plan - install everything in the repo as optional (mimic the dropins folder)
		IQueryResult<IInstallableUnit> allIUs = repo.query(QueryUtil.createIUAnyQuery(), new NullProgressMonitor());
		//		IQueryResult allIUs = repo.query(new InstallableUnitQuery("aaa", new VersionRange("[1.0.2, 1.0.3]")), new NullProgressMonitor());

		IProfileChangeRequest actualChangeRequest = createProfileChangeRequest(allIUs.toSet(), null, null);
		IProvisioningPlan actualPlan = planner.getProvisioningPlan(actualChangeRequest, null, new NullProgressMonitor());

		// this is the plan that we expect - highest version only
		IQueryResult<IInstallableUnit> queryResult = repo.query(QueryUtil.createIUQuery("aaa", Version.createOSGi(1, 0, 3)), new NullProgressMonitor());
		IProfileChangeRequest expectedChangeRequest = createProfileChangeRequest(queryResult.toSet(), null, null);
		IProvisioningPlan expectedPlan = planner.getProvisioningPlan(expectedChangeRequest, null, new NullProgressMonitor());

		assertContains("1.0", expectedPlan, actualPlan);
	}
}
