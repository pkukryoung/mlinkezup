/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
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
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.views;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsElement;

/**
 * Label provider for the title bar for the tabbed property view.
 *
 * @author Anthony Hunter
 */
public class DynamicTestsTitleBarLabelProvider extends
		org.eclipse.jface.viewers.LabelProvider {

	@Override
	public Image getImage(Object obj) {
		Assert.isTrue(obj instanceof IStructuredSelection);
		IStructuredSelection structuredSelection = (IStructuredSelection) obj;
		if (structuredSelection.equals(StructuredSelection.EMPTY) ||
				structuredSelection.size() > 1) {
			return null;
		}
		DynamicTestsElement dynamicTestsElement = (DynamicTestsElement) ((DynamicTestsTreeNode) structuredSelection
				.getFirstElement()).getValue();
		return dynamicTestsElement.getImage();
	}

	@Override
	public String getText(Object obj) {
		Assert.isTrue(obj instanceof IStructuredSelection);
		IStructuredSelection structuredSelection = (IStructuredSelection) obj;
		if (structuredSelection.equals(StructuredSelection.EMPTY)) {
			return null;
		}
		if (structuredSelection.size() > 1) {
			return structuredSelection.size() + " items selected";//$NON-NLS-1$
		}
		DynamicTestsElement dynamicTestsElement = (DynamicTestsElement) ((TreeNode) structuredSelection
				.getFirstElement()).getValue();
		return dynamicTestsElement.getName();
	}
}