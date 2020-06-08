/*******************************************************************************
 * Copyright (c) 2015, 2017 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jonas Helming - initial API and implementation
 *     Lars Vogel <Lars.Vogel@vogella.com> - Bug 468773
 ******************************************************************************/

package org.eclipse.e4.ui.tests.workbench;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.commands.CommandServiceAddon;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.bindings.BindingServiceAddon;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.internal.workbench.addons.CommandProcessingAddon;
import org.eclipse.e4.ui.internal.workbench.addons.HandlerProcessingAddon;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.commands.MHandlerContainer;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.ContextServiceAddon;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the activation of Handlers based on their Handler Container, e.g.
 * MWindow, MPerspective or MPart
 */
public class HandlerActivationTest {

	/**
	 * The ID for the test command
	 */
	private static final String COMMANDID = "handlerActivationTest";

	/**
	 * A Test Handler
	 */
	public interface TestHandler {
		public boolean isExecuted();
	}

	protected IEclipseContext appContext;
	protected E4Workbench wb;
	private MCommand command;
	private MWindow window;
	private EHandlerService handlerService;
	private ParameterizedCommand parameterizedCommand;
	private MPerspective perspectiveA;
	private MPart partA1;
	private MPerspective perspectiveB;
	private MPart partA2;
	private EPartService partService;
	private MPart partB1;
	private EModelService ems;

	@Before
	public void setUp() throws Exception {
		appContext = E4Application.createDefaultContext();
		ContextInjectionFactory.make(CommandServiceAddon.class, appContext);
		ContextInjectionFactory.make(ContextServiceAddon.class, appContext);
		ContextInjectionFactory.make(BindingServiceAddon.class, appContext);
		appContext.set(IWorkbench.PRESENTATION_URI_ARG, PartRenderingEngine.engineURI);
		ems = appContext.get(EModelService.class);
		createLayoutWithThreeContextLayers();
	}

	@After
	public void tearDown() throws Exception {
		if (wb != null) {
			wb.close();
		}
		appContext.dispose();
	}

	/**
	 * Creates an example application model with one window and two perspectives
	 */
	public void createLayoutWithThreeContextLayers() {
		window = ems.createModelElement(MWindow.class);
		MPerspectiveStack perspectiveStack = ems.createModelElement(MPerspectiveStack.class);
		window.getChildren().add(perspectiveStack);

		perspectiveA = ems.createModelElement(MPerspective.class);
		perspectiveB = ems.createModelElement(MPerspective.class);

		MPartStack stackA = ems.createModelElement(MPartStack.class);
		partA1 = ems.createModelElement(MPart.class);
		partA2 = ems.createModelElement(MPart.class);
		stackA.getChildren().add(partA1);
		stackA.getChildren().add(partA2);
		perspectiveA.getChildren().add(stackA);
		perspectiveStack.getChildren().add(perspectiveA);

		MPartStack stackB = ems.createModelElement(MPartStack.class);
		partB1 = ems.createModelElement(MPart.class);
		stackB.getChildren().add(partB1);
		stackB.setSelectedElement(partB1);
		perspectiveB.getChildren().add(stackB);
		perspectiveStack.getChildren().add(perspectiveB);

		perspectiveStack.setSelectedElement(perspectiveA);
		stackA.setSelectedElement(partA1);

		command = ems.createModelElement(MCommand.class);
		command.setElementId(COMMANDID);
		command.setCommandName("Test Handler Activation");

		MApplication application = ems.createModelElement(MApplication.class);
		application.getCommands().add(command);
		application.getChildren().add(window);
		application.setContext(appContext);
		appContext.set(MApplication.class, application);

		ContextInjectionFactory.make(CommandProcessingAddon.class, appContext);
		ContextInjectionFactory.make(HandlerProcessingAddon.class, appContext);

		wb = new E4Workbench(window, appContext);
		wb.createAndRunUI(window);


		ECommandService commandService = appContext.get(ECommandService.class);
		handlerService = appContext.get(EHandlerService.class);
		parameterizedCommand = commandService.createCommand(COMMANDID, null);
		partService = appContext.get(EPartService.class);

	}

	@Test
	public void testHandlerInWindowOnly() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(window);
		executeCommand();
		assertTrue(testHandler.isExecuted());
	}

	@Test
	public void testHandlerInActivePerspectiveOnly() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(perspectiveA);
		executeCommand();
		assertTrue(testHandler.isExecuted());
	}

	@Test
	public void testHandlerInActivePartOnly() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(partA1);
		executeCommand();
		assertTrue(testHandler.isExecuted());
	}

	@Test
	public void testHandlerInInactivePerspectiveOnly() {
		TestHandler testHandler = createTestHandlerInHandlerContainer(perspectiveB);
		executeCommand();
		assertFalse(testHandler.isExecuted());
	}

	@Test
	public void testHandlerInInactivePartOnly() {
		TestHandler testHandler = createTestHandlerInHandlerContainer(partA2);
		executeCommand();
		assertFalse(testHandler.isExecuted());
	}

	@Test
	public void testHandlerInActivePartAndPerspective() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(partA1);
		TestHandler testHandler2 = createTestHandlerInHandlerContainer(perspectiveA);
		executeCommand();
		assertTrue(testHandler.isExecuted());
		assertFalse(testHandler2.isExecuted());
	}

	@Test
	public void testHandlerInActivePartAndWindow() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(partA1);
		TestHandler testHandler2 = createTestHandlerInHandlerContainer(window);
		executeCommand();
		assertTrue(testHandler.isExecuted());
		assertFalse(testHandler2.isExecuted());
	}

	@Test
	public void testHandlerInActivePerspectiveAndWindow() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(perspectiveA);
		TestHandler testHandler2 = createTestHandlerInHandlerContainer(window);
		executeCommand();
		assertTrue(testHandler.isExecuted());
		assertFalse(testHandler2.isExecuted());
	}

	@Test
	public void testHandlerInActivePartAndPerspectiveAndWindow() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(partA1);
		TestHandler testHandler2 = createTestHandlerInHandlerContainer(perspectiveA);
		TestHandler testHandler3 = createTestHandlerInHandlerContainer(window);
		executeCommand();
		assertTrue(testHandler.isExecuted());
		assertFalse(testHandler2.isExecuted());
		assertFalse(testHandler3.isExecuted());
	}

	@Test
	public void testHandlerSwitchToInactivePart() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandler = createTestHandlerInHandlerContainer(partA2);
		executeCommand();
		assertFalse(testHandler.isExecuted());
		partService.activate(partA2);
		executeCommand();
		assertTrue(testHandler.isExecuted());
	}

	@Test
	public void testHandlerSwitchToInactivePerspective() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandlerA = createTestHandlerInHandlerContainer(perspectiveA);
		TestHandler testHandlerB = createTestHandlerInHandlerContainer(perspectiveB);
		partService.switchPerspective(perspectiveB);
		executeCommand();
		assertFalse(testHandlerA.isExecuted());
		assertTrue(testHandlerB.isExecuted());
	}

	@Test
	public void testHandlerSwitchToInactivePartInOtherPerspectiveWithPerspectiveHandlers() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandlerA = createTestHandlerInHandlerContainer(perspectiveA);
		TestHandler testHandlerB = createTestHandlerInHandlerContainer(perspectiveB);
		partService.switchPerspective(perspectiveB);
		partService.activate(partB1);
		executeCommand();
		assertFalse(testHandlerA.isExecuted());
		assertTrue(testHandlerB.isExecuted());
	}

	@Test
	public void testHandlerSwitchToInactivePartInOtherPerspectiveWithPartHandlers() {
		assumeFalse("Test fails on Mac: Bug 537639", Platform.OS_MACOSX.equals(Platform.getOS()));

		TestHandler testHandlerA = createTestHandlerInHandlerContainer(partA1);
		TestHandler testHandlerB = createTestHandlerInHandlerContainer(partB1);
		partService.switchPerspective(perspectiveB);
		partService.activate(partB1);
		executeCommand();
		assertFalse(testHandlerA.isExecuted());
		assertTrue(testHandlerB.isExecuted());
	}

	private TestHandler createTestHandlerInHandlerContainer(MHandlerContainer handlerContainer) {
		MHandler handler = ems.createModelElement(MHandler.class);
		handler.setCommand(command);
		TestHandler testHandler = new TestHandler() {


			private boolean executed;

			@Execute
			public void execute() {
				executed = true;
			}

			@Override
			public boolean isExecuted() {
				// TODO Auto-generated method stub
				return executed;
			}
		};
		handler.setObject(testHandler);

		handlerContainer.getHandlers().add(handler);
		return testHandler;
	}

	private void executeCommand() {
		handlerService.executeHandler(parameterizedCommand);

	}

}
