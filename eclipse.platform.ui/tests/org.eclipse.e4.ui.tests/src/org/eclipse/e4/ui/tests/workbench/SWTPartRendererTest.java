/*******************************************************************************
 * Copyright (c) 2013, 2018 IBM Corporation and others.
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
 ******************************************************************************/

package org.eclipse.e4.ui.tests.workbench;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.renderers.swt.SWTPartRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SWTPartRendererTest {
	private SWTPartRenderer renderer;
	private Shell shell;
	private MPart part;
	private IEclipseContext context;
	private Map<String, Object[]> stylingEngineExecutedMethods;

	@Before
	public void setUp() throws Exception {
		renderer = new SWTPartRenderer() {
			@Override
			public Object createWidget(MUIElement element, Object parent) {
				return null;
			}
		};

		shell = Display.getDefault().getActiveShell();
		stylingEngineExecutedMethods = new HashMap<>();

		context = EclipseContextFactory.create();
		context.set(IStylingEngine.class, (IStylingEngine) Proxy.newProxyInstance(getClass().getClassLoader(),
				new Class<?>[] { IStylingEngine.class }, (Object proxy, Method method, Object[] args) -> {
					stylingEngineExecutedMethods.put(method.getName(), args);
					return null;
				}));

		part = MBasicFactory.INSTANCE.createPart();
		part.setElementId("org.eclipse.elementId");
		part.setContext(context);

	}

	@Test
	public void testSetCSSInfo() throws Exception {
		Button button = new Button(shell, SWT.PUSH);

		renderer.setCSSInfo(part, button);
		Object[] setClassnameAndIdParams = stylingEngineExecutedMethods
				.get("setClassnameAndId");

		assertNotNull(setClassnameAndIdParams);
		assertEquals(3, setClassnameAndIdParams.length);
		assertEquals(button, setClassnameAndIdParams[0]);
		assertEquals("MPart", setClassnameAndIdParams[1].toString());
		assertEquals("org-eclipse-elementId",
				setClassnameAndIdParams[2].toString());
	}

	@Test
	public void testSetCSSInfoWhenUIElementWithTags() throws Exception {
		Button button = new Button(shell, SWT.PUSH);
		part.getTags().add("tag1");
		part.getTags().add("tag2");

		renderer.setCSSInfo(part, button);
		Object[] setClassnameAndIdParams = stylingEngineExecutedMethods
				.get("setClassnameAndId");

		assertNotNull(setClassnameAndIdParams);
		assertEquals(3, setClassnameAndIdParams.length);
		assertEquals(button, setClassnameAndIdParams[0]);
		assertEquals("MPart tag1 tag2", setClassnameAndIdParams[1].toString());
		assertEquals("org-eclipse-elementId",
				setClassnameAndIdParams[2].toString());
	}

	@Test
	public void testSetCSSInfoWhenNoCSSStylingEngineInContext()
			throws Exception {
		Button button = new Button(shell, SWT.PUSH);
		context.remove(IStylingEngine.class);

		renderer.setCSSInfo(part, button);
		Object[] setClassnameAndIdParams = stylingEngineExecutedMethods
				.get("setClassnameAndId");

		assertNull(setClassnameAndIdParams);
	}
}
