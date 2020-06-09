/*******************************************************************************
 * Copyright (c) 2008, 2010 IBM Corporation and others.
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

package org.eclipse.equinox.internal.p2.ui.actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.equinox.internal.p2.ui.model.MetadataRepositoryElement;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.viewers.ISelectionProvider;

public abstract class ColocatedRepositoryAction extends ProvisioningAction {

	public ColocatedRepositoryAction(ProvisioningUI ui, String label, String tooltipText,
			ISelectionProvider selectionProvider) {
		super(ui, label, selectionProvider);
		setToolTipText(tooltipText);
		init();
	}

	protected URI[] getSelectedLocations(Object[] selectionArray) {
		List<URI> urls = new ArrayList<>();
		for (Object element : selectionArray) {
			if (element instanceof MetadataRepositoryElement) {
				urls.add(((MetadataRepositoryElement) element).getLocation());
			}
		}
		return urls.toArray(new URI[urls.size()]);
	}

	@Override
	protected void checkEnablement(Object[] selectionArray) {
		setEnabled(getSelectedLocations(selectionArray).length > 0);
	}
}
