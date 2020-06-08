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
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsAdvancedSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
 * A tab descriptor for the dynamic tests view.
 *
 * @author Anthony Hunter
 */
public class DynamicTestsAdvancedTabDescriptor extends AbstractTabDescriptor {

	public DynamicTestsAdvancedTabDescriptor() {
		super();
		getSectionDescriptors().add(
				new DynamicTestsAdvancedSectionDescriptor(
						new DynamicTestsTypeMapper()));
	}

	@Override
	public String getCategory() {
		return "advanced"; //$NON-NLS-1$
	}

	@Override
	public String getId() {
		return "AdvancedTab"; //$NON-NLS-1$
	}

	@Override
	public String getLabel() {
		return "Advanced"; //$NON-NLS-1$
	}

}
