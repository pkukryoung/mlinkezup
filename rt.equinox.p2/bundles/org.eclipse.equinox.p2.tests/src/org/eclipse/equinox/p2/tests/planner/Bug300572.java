/*******************************************************************************
 *  Copyright (c) 2010 Sonatype, Inc and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     Sonatype, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.tests.planner;

import java.util.Arrays;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.planner.ProfileInclusionRules;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.tests.AbstractProvisioningTest;

//This test verify that one patch can replace another one.
public class Bug300572 extends AbstractProvisioningTest {
	public void testInstallSecondPatch() throws ProvisionException {
		IProvisioningAgentProvider provider = getAgentProvider();
		IProvisioningAgent agent = provider.createAgent(getTestData("Bug300572 data", "testData/bug300572/p2").toURI());
		IMetadataRepositoryManager repoMgr = agent.getService(IMetadataRepositoryManager.class);
		//The following repo contains the second patch to be installed
		IMetadataRepository repo = repoMgr.loadRepository(getTestData("bug300572 data", "testData/bug300572/repo/").toURI(), new NullProgressMonitor());
		IInstallableUnit[] ius = repo.query(QueryUtil.createIUQuery("hellopatch.feature.group"), null).toArray(IInstallableUnit.class);
		System.out.println(Arrays.toString(ius));

		IPlanner planner = agent.getService(IPlanner.class);
		//The profile already contains a a feature (hellofeature) and a patch for it (hellopatch).
		IProfile sdkProfile = agent.getService(IProfileRegistry.class).getProfile("SDKProfile");
		assertFalse(sdkProfile.query(QueryUtil.createIUQuery("hellopatch.feature.group"), null).isEmpty());
		assertFalse(sdkProfile.query(QueryUtil.createIUQuery("hellofeature.feature.group"), null).isEmpty());

		//Try to optionally install the second patch. We install all the IUs from the repo since it contains only the patch and its necessary IUs.
		IProfileChangeRequest request = planner.createChangeRequest(sdkProfile);
		IQueryResult<IInstallableUnit> allIUs = repo.query(QueryUtil.ALL_UNITS, null);
		request.addAll(allIUs.toUnmodifiableSet());
		for (IInstallableUnit iu : allIUs) {
			request.setInstallableUnitInclusionRules(iu, ProfileInclusionRules.createOptionalInclusionRule(iu));
		}

		ProvisioningContext pc = new ProvisioningContext(agent);

		IProvisioningPlan plan = planner.getProvisioningPlan(request, pc, new NullProgressMonitor());
		assertOK("plan is not ok", plan.getStatus());
		assertFalse("hellopatch.feature.group not found", plan.getAdditions().query(QueryUtil.createIUQuery("hellopatch.feature.group", Version.create("1.0.2.201001211536")), null).isEmpty());
	}
}
