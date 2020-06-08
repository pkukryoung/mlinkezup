/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
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
package org.eclipse.ui.tests.views.properties.tabbed.views;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;

/**
 * A filter for Information messages that have the work "Two" in them.
 *
 * @author Anthony Hunter
 */
public class InformationTwoFilter
	implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof TreeNode) {
			Element element = (Element) ((TreeNode) object).getValue();
			if (element instanceof Information) {
				Information information = (Information) element;
				if (information.getName().contains("Two")) {//$NON-NLS-1$
					return true;
				}
			}
		}
		return false;
	}

}
