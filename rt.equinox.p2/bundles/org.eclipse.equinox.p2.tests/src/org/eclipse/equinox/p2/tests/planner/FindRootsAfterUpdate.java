/*******************************************************************************
 * Copyright (c) 2011, 2018 Sonatype, Inc. and others.
 *
 * This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License 2.0 which accompanies this distribution, and is
 * available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Sonatype, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.equinox.p2.tests.planner;

import java.util.Arrays;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.tests.AbstractProvisioningTest;

public class FindRootsAfterUpdate extends AbstractProvisioningTest {
	IInstallableUnit a1;
	IInstallableUnit b1;
	IInstallableUnit b2;
	IInstallableUnit b3;
	IInstallableUnit x1;

	IProfile profile;
	IPlanner planner;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		a1 = createIU("A", Version.create("1.0.0"), createRequiredCapabilities(IInstallableUnit.NAMESPACE_IU_ID, "B", new VersionRange("[1.0.0, 4.0.0)")));

		b1 = createIU("B", Version.create("1.0.0"), true);

		b2 = createIU("B", Version.create("2.0.0"), true);

		b3 = createIU("B", Version.create("3.0.0"), true);

		x1 = createIU("X", Version.createOSGi(2, 0, 0), createRequiredCapabilities(IInstallableUnit.NAMESPACE_IU_ID, "B", new VersionRange("[2.0.0, 2.0.0]")));

		createTestMetdataRepository(new IInstallableUnit[] {a1, b1, b2, b3, x1});

		profile = createProfile("TestProfile." + getName());
		planner = createPlanner();

	}

	public void testInstallB3() {
		ProfileChangeRequest req = new ProfileChangeRequest(profile);
		ProvisioningContext ctx = new ProvisioningContext(getAgent());
		IRequirement[] reqs = createRequiredCapabilities(IInstallableUnit.NAMESPACE_IU_ID, "B", new VersionRange("[2.0.0, 4.0.0]"));
		req.addExtraRequirements(Arrays.asList(reqs[0]));
		IProvisioningPlan plan = planner.getProvisioningPlan(req, ctx, null);
		assertEquals(IStatus.OK, plan.getStatus().getSeverity());
		assertInstallOperand(plan, b3);
		assertContains(plan.getFutureState().query(QueryUtil.createMatchQuery(reqs[0].getMatches()), null), b3);
	}

	public void testConflictThroughExtraRequirements() {
		ProfileChangeRequest req = new ProfileChangeRequest(profile);
		ProvisioningContext ctx = new ProvisioningContext(getAgent());
		IRequirement reqOnB = createRequiredCapabilities(IInstallableUnit.NAMESPACE_IU_ID, "B", new VersionRange("[3.0.0, 4.0.0]"))[0];
		IRequirement reqOnX = createRequiredCapabilities(IInstallableUnit.NAMESPACE_IU_ID, "X", new VersionRange("[2.0.0, 2.0.0]"))[0];
		IRequirement[] extraReqs = new IRequirement[] {reqOnB, reqOnX};
		req.addExtraRequirements(Arrays.asList(extraReqs));
		IProvisioningPlan plan = planner.getProvisioningPlan(req, ctx, null);
		assertNotOK(plan.getStatus());
	}
}
