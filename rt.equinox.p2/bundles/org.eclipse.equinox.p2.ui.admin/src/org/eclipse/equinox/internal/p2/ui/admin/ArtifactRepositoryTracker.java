/*******************************************************************************
 * Copyright (c) 2009, 2010 IBM Corporation and others.
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

package org.eclipse.equinox.internal.p2.ui.admin;

import java.net.URI;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.ui.ProvUI;
import org.eclipse.equinox.internal.provisional.p2.repository.RepositoryEvent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.RepositoryTracker;
import org.eclipse.equinox.p2.repository.IRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.ui.ProvisioningUI;

public class ArtifactRepositoryTracker extends RepositoryTracker {

	ProvisioningUI ui;

	public ArtifactRepositoryTracker(ProvisioningUI ui) {
		this.ui = ui;
	}

	@Override
	public URI[] getKnownRepositories(ProvisioningSession session) {
		return getArtifactRepositoryManager().getKnownRepositories(getArtifactRepositoryFlags());
	}

	@Override
	public void addRepository(URI repoLocation, String nickname, ProvisioningSession session) {
		ui.signalRepositoryOperationStart();
		try {
			getArtifactRepositoryManager().addRepository(repoLocation);
			if (nickname != null)
				getArtifactRepositoryManager().setRepositoryProperty(repoLocation, IRepository.PROP_NICKNAME, nickname);
		} finally {
			ui.signalRepositoryOperationComplete(
					new RepositoryEvent(repoLocation, IRepository.TYPE_ARTIFACT, RepositoryEvent.ADDED, true), true);
		}
	}

	@Override
	public void removeRepositories(URI[] repoLocations, ProvisioningSession session) {
		ui.signalRepositoryOperationStart();
		try {
			for (URI repoLocation : repoLocations) {
				getArtifactRepositoryManager().removeRepository(repoLocation);
			}
		} finally {
			ui.signalRepositoryOperationComplete(null, true);
		}
	}

	@Override
	public void refreshRepositories(URI[] locations, ProvisioningSession session, IProgressMonitor monitor) {
		ui.signalRepositoryOperationStart();
		SubMonitor mon = SubMonitor.convert(monitor, locations.length * 100);
		for (URI location : locations) {
			try {
				getArtifactRepositoryManager().refreshRepository(location, mon.newChild(100));
			}catch (ProvisionException e) {
				// ignore problematic repositories when refreshing
			}
		}
		// We have no idea how many repos may have been added/removed as a result of
		// refreshing these, this one, so we do not use a specific repository event to
		// represent it.
		ui.signalRepositoryOperationComplete(null, true);
	}

	IArtifactRepositoryManager getArtifactRepositoryManager() {
		return ProvUI.getArtifactRepositoryManager(ui.getSession());
	}

	@Override
	protected boolean contains(URI location, ProvisioningSession session) {
		return ProvUI.getArtifactRepositoryManager(session).contains(location);
	}
}