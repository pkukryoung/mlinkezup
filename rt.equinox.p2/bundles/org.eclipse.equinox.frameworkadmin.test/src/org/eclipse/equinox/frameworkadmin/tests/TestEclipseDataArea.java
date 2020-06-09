/*******************************************************************************
 * Copyright (c) 2008, 2017 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.equinox.frameworkadmin.tests;

import java.io.File;
import java.io.IOException;
import org.eclipse.equinox.internal.provisional.frameworkadmin.Manipulator;
import org.junit.After;
import org.junit.Test;

public class TestEclipseDataArea extends FwkAdminAndSimpleConfiguratorTest {
	Manipulator m = null;

	@Override
	@After
	public void setUp() throws Exception {
		super.setUp();
		m = createMinimalConfiguration(TestEclipseDataArea.class.getName());
	}

	@Test
	public void testp2DataArea() throws IOException {
		m.getConfigData().setProperty("eclipse.p2.data.area", "@config.dir/../p2");
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/../p2");
		m.load();
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/../p2");

		m.getConfigData().setProperty("eclipse.p2.data.area",
				new File(getConfigurationFolder(), "p2").getAbsoluteFile().toURI().toString());
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/p2");
		m.load();
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/p2");

		m.getConfigData().setProperty("eclipse.p2.data.area",
				new File(getConfigurationFolder(), "../p2").getAbsoluteFile().toURI().toString());
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/../p2");
		m.load();
		m.save(false);
		assertContent(getConfigIni(), "@config.dir/../p2");

		m.getConfigData().setProperty("eclipse.p2.data.area", "file:/d:/tmp/fo%20o/bar/p2");
		m.save(false);
		assertContent(getConfigIni(), "/tmp/fo o/bar/p2");
		assertNotContent(getConfigIni(), "@config.dir");
		m.load();
		m.save(false);
		assertContent(getConfigIni(), "/tmp/fo o/bar/p2");
		assertNotContent(getConfigIni(), "@config.dir");

	}
}
