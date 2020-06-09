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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.tests.AbstractProvisioningTest;

public class Bug306279f extends AbstractProvisioningTest {
	public void testInstallBabel() throws ProvisionException {
		IProvisioningAgentProvider provider = getAgentProvider();
		IProvisioningAgent agent = provider.createAgent(getTestData("bug306279f data", "testData/bug306279f/p2").toURI());
		IMetadataRepositoryManager repoMgr = agent.getService(IMetadataRepositoryManager.class);
		repoMgr.addRepository(getTestData("bug306279f data", "testData/bug306279f/repo/helios/").toURI());
		repoMgr.addRepository(getTestData("bug306279f data", "testData/bug306279f/repo/babel").toURI());

		IPlanner planner = agent.getService(IPlanner.class);
		IProfile sdkProfile = agent.getService(IProfileRegistry.class).getProfile("SDKProfile");
		IProfileChangeRequest request = planner.createChangeRequest(sdkProfile);
		request.add(repoMgr.query(QueryUtil.createIUQuery("org.eclipse.babel.nls_rt.rap_en_AA.feature.group"), null).iterator().next());

		ProvisioningContext pc = new ProvisioningContext(agent);

		IProvisioningPlan plan = planner.getProvisioningPlan(request, pc, new NullProgressMonitor());
		assertOK("plan is not ok", plan.getStatus());
		assertTrue("should not contain rap.jface", plan.getAdditions().query(QueryUtil.createIUQuery("org.eclipse.rap.jface"), null).isEmpty());
	}
}
