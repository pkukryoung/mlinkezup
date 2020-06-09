/*******************************************************************************
 *  Copyright (c) 2007, 2010 IBM Corporation and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sonatype, Inc. - ongoing development
 *******************************************************************************/

package org.eclipse.equinox.internal.p2.ui.actions;

import java.util.Collection;
import org.eclipse.equinox.internal.p2.ui.ProvUI;
import org.eclipse.equinox.internal.p2.ui.ProvUIMessages;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.ProfileChangeOperation;
import org.eclipse.equinox.p2.operations.UninstallOperation;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.viewers.ISelectionProvider;

public class UninstallAction extends ExistingIUInProfileAction {

	public UninstallAction(ProvisioningUI ui, ISelectionProvider selectionProvider, String profileId) {
		super(ui, ProvUI.UNINSTALL_COMMAND_LABEL, selectionProvider, profileId);
		setToolTipText(ProvUI.UNINSTALL_COMMAND_TOOLTIP);
	}

	protected String getTaskName() {
		return ProvUIMessages.UninstallIUProgress;
	}

	@Override
	protected int getLockConstant() {
		return IProfile.LOCK_UNINSTALL;
	}

	@Override
	protected ProfileChangeOperation getProfileChangeOperation(Collection<IInstallableUnit> ius) {
		return ui.getUninstallOperation(ius, null);
	}

	@Override
	protected int performAction(ProfileChangeOperation operation, Collection<IInstallableUnit> ius) {
		return ui.openUninstallWizard(ius, (UninstallOperation) operation, null);
	}
}
