/*******************************************************************************
 * Copyright (c) 2007, 2010 IBM Corporation and others.
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
package org.eclipse.equinox.internal.p2.ui;

import java.net.URI;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryManager;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.RepositoryTracker;
import org.eclipse.equinox.p2.repository.IRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.ui.ProvisioningUI;

/**
 * An object that queries a particular set of metadata repositories.
 */
public class QueryableMetadataRepositoryManager extends QueryableRepositoryManager<IInstallableUnit> {

	public QueryableMetadataRepositoryManager(ProvisioningUI ui, boolean includeDisabledRepos) {
		super(ui, includeDisabledRepos);
	}

	@Override
	protected IMetadataRepository getRepository(IRepositoryManager<IInstallableUnit> manager, URI location) {
		// note the use of MetadataRepositoryManager (the concrete implementation).
		if (manager instanceof MetadataRepositoryManager) {
			return ((MetadataRepositoryManager) manager).getRepository(location);
		}
		return null;
	}

	@Override
	protected IMetadataRepositoryManager getRepositoryManager() {
		return ProvUI.getMetadataRepositoryManager(getSession());
	}

	@Override
	protected IMetadataRepository doLoadRepository(IRepositoryManager<IInstallableUnit> manager, URI location,
			IProgressMonitor monitor) throws ProvisionException {
		return ui.loadMetadataRepository(location, false, monitor);
	}

	@Override
	protected int getRepositoryFlags(RepositoryTracker repositoryManipulator) {
		return repositoryManipulator.getMetadataRepositoryFlags();
	}
}
