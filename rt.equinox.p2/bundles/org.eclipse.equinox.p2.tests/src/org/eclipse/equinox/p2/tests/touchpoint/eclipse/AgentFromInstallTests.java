/*******************************************************************************
 * Copyright (c) 2013 Ericsson AB and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Ericsson AB - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.tests.touchpoint.eclipse;

import java.io.File;
import org.eclipse.equinox.internal.p2.touchpoint.eclipse.AgentFromInstall;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.tests.AbstractProvisioningTest;

public class AgentFromInstallTests extends AbstractProvisioningTest {

	public void testNormalEclipseFromInstallFolder() {
		File installFolder = getTestData("normalEclipse", "testData/configAreaToAgent/normalEclipse");
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), installFolder, null, null);
		assertNotNull(agent);
		IProfile profile = agent.getService(IProfileRegistry.class).getProfile(IProfileRegistry.SELF);
		assertNotNull(profile);
		assertEquals("SDKProfile", profile.getProfileId());
	}

	public void testNormalEclipseFromConfiguration() {
		File configurationFolder = getTestData("normalEclipse", "testData/configAreaToAgent/normalEclipse/configuration");
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), null, configurationFolder, null);
		assertNotNull(agent);
		IProfile profile = agent.getService(IProfileRegistry.class).getProfile(IProfileRegistry.SELF);
		assertNotNull(profile);
		assertEquals("SDKProfile", profile.getProfileId());
	}

	public void testSharedInstallWithoutBase() {
		File configurationFolder = getTestData("sharedWithoutBaseAvailable", "testData/configAreaToAgent/sharedWithoutBaseAvailable");
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), null, configurationFolder, null);
		assertNotNull(agent);
		IProfile profile = agent.getService(IProfileRegistry.class).getProfile(IProfileRegistry.SELF);
		assertNotNull(profile);
		assertEquals("SDKProfile", profile.getProfileId());
	}

	public void testMissingInstallFolder() {
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), new File("someRandomFile_that_does_not_exists"), null, null);
		assertNull(agent);
	}

	public void testTooManyProfiles() {
		File configurationFolder = getTestData("sharedWithoutBaseAvailable", "testData/configAreaToAgent/tooManyProfiles");
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), null, configurationFolder, null);
		assertNull(agent);
	}

	public void testTooManyProfilesWithProfileId() {
		File configurationFolder = getTestData("sharedWithoutBaseAvailable", "testData/configAreaToAgent/tooManyProfiles");
		IProvisioningAgent agent = AgentFromInstall.createAgentFrom(getAgentProvider(), null, configurationFolder, "OtherProfile");
		assertNotNull(agent);
	}
}
