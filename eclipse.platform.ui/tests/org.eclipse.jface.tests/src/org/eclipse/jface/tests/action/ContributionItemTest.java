/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
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
package org.eclipse.jface.tests.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

/**
 * Tests for the [I]ContributionItem API.
 *
 * @since 3.1
 */
public class ContributionItemTest extends JFaceActionTest {

	/**
	 * Constructs a new test with the given name.
	 *
	 * @param name the name of the test
	 */
	public ContributionItemTest(String name) {
		super(name);
	}

	/**
	 * Tests that a contribution item's parent link is set when added to a
	 * contribution manager, and cleared when the item is removed. This is a
	 * regression test for: Bug 80569 [Contributions] Parent of contribution item
	 * not cleared when item removed from manager
	 */
	public void testParentLink() {
		IContributionManager mgr = new DummyContributionManager();
		ContributionItem item = new ActionContributionItem(new DummyAction());
		assertNull(item.getParent());
		mgr.add(item);
		assertEquals(mgr, item.getParent());
		mgr.remove(item);
		assertNull(item.getParent());
	}

	/**
	 * Tests that an action contribution item will display the text in a created SWT
	 * button when the MODE_FORCE_TEXT mode is set. This is a test for: Bug 187956
	 * [ActionSets] ActionContributionItem.MODE_FORCE_TEXT should apply to Buttons
	 * too
	 */
	public void testForceModeText() {
		Action action = new DummyAction();
		action.setImageDescriptor(ResourceLocator
				.imageDescriptorFromBundle("org.eclipse.jface.tests", "icons/anything.gif").orElse(null));
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(getShell());

		Control[] children = getShell().getChildren();
		Button button = (Button) children[0];
		assertEquals("", button.getText());
		action.setText("Text");
		assertEquals("", button.getText());
		item.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		assertEquals("Text", button.getText());
		action.setText(null);
		assertEquals("", button.getText());
	}
}
