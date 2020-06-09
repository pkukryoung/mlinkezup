/*******************************************************************************
 *  Copyright (c) 2008, 2017 IBM Corporation and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *   IBM - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.tests.publisher.actions;

import static org.easymock.EasyMock.anyBoolean;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.eclipse.equinox.p2.tests.publisher.actions.StatusMatchers.okStatus;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.Collections;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ProductFile;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.actions.RootIUAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.IExecutableAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.ProductAction;
import org.eclipse.equinox.p2.publisher.eclipse.ProductFileAdvice;
import org.eclipse.equinox.p2.tests.TestData;

/**
 * Tests for {@link ProductAction} specific to Mac.
 */
public class ProductActionTestMac extends ActionTest {

	private File executablesFeatureLocation = null;
	private Capture<RootIUAdvice> rootIUAdviceCapture;
	private Capture<ProductFileAdvice> productFileAdviceCapture;
	private String source = "";

	@Override
	protected IPublisherInfo createPublisherInfoMock() {
		//override to create a nice mock, because we don't care about other method calls.
		return createNiceMock(IPublisherInfo.class);
	}

	@Override
	protected void insertPublisherInfoBehavior() {
		publisherInfo.addAdvice(EasyMock.and(EasyMock.isA(RootIUAdvice.class), EasyMock.capture(rootIUAdviceCapture)));
		publisherInfo.addAdvice(EasyMock.and(EasyMock.isA(ProductFileAdvice.class), EasyMock.capture(productFileAdviceCapture)));
		//Return an empty list every time getAdvice is called
		expect(publisherInfo.getAdvice((String) anyObject(), anyBoolean(), (String) anyObject(), (Version) anyObject(), (Class<IPublisherAdvice>) anyObject())).andReturn(Collections.emptyList());
		expectLastCall().anyTimes();
	}

	@Override
	public void setUp() throws Exception {
		configSpec = AbstractPublisherAction.createConfigSpec("carbon", "macosx", "x86");
		rootIUAdviceCapture = new Capture<>();
		productFileAdviceCapture = new Capture<>();
		setupPublisherInfo();
		setupPublisherResult();
	}

	/**
	 * Tests that correct advice is created for the org.eclipse.platform product.
	 */
	public void testPlatformProduct() throws Exception {
		ProductFile productFile = new ProductFile(TestData.getFile("ProductActionTest", "platform.product").toString());
		addContextIU("org.eclipse.platform.feature.group", "3.8.3");
		testAction = new ProductAction(source, productFile, flavorArg, executablesFeatureLocation);
		IStatus status = testAction.perform(publisherInfo, publisherResult, null);
		assertThat(status, is(okStatus()));

		IExecutableAdvice launchAdvice = productFileAdviceCapture.getValue();
		assertEquals("1.0", "eclipse", launchAdvice.getExecutableName());

		String[] programArgs = launchAdvice.getProgramArguments();
		assertEquals("2.0", 2, programArgs.length);
		assertEquals("2.1", "-showsplash", programArgs[0]);
		assertEquals("2.2", "org.eclipse.platform", programArgs[1]);

		String[] vmArgs = launchAdvice.getVMArguments();
		assertEquals("3.0", 6, vmArgs.length);
		assertEquals("3.1", "-Xdock:icon=../Resources/Eclipse.icns", vmArgs[0]);
		assertEquals("3.2", "-XstartOnFirstThread", vmArgs[1]);
		assertEquals("3.3", "-Xms40m", vmArgs[2]);
		assertEquals("3.4", "-Xmx256m", vmArgs[3]);
		assertEquals("3.5", "-XX:MaxPermSize=256m", vmArgs[4]);
		assertEquals("3.6", "-Dorg.eclipse.swt.internal.carbon.smallFonts", vmArgs[5]);
	}
}
