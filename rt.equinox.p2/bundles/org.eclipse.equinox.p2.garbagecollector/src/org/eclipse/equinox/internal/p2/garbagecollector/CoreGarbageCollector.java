/*******************************************************************************
 * Copyright (c) 2007, 2017 IBM Corporation and others.
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
package org.eclipse.equinox.internal.p2.garbagecollector;

import java.util.*;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.core.helpers.Tracing;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.query.*;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;

/**
 * Given a MarkSet, the CoreGarbageCollector removes any IArtifactDescriptors which
 * are not mapped to be an IArtifactKey in the MarkSet.
 */
public class CoreGarbageCollector {
	/**
	 * When set to true, information will be logged every time an artifact is removed
	 */
	private static boolean DEBUG = "true".equalsIgnoreCase(GarbageCollectorHelper.ID + "/debug"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Given a list of IArtifactKeys and an IArtifactRepository, removes all artifacts
	 * in aRepository that are not mapped to by an IArtifactKey in markSet
	 */
	public synchronized void clean(IArtifactKey[] markSet, final IArtifactRepository aRepository) {
		Set<IArtifactKey> set = new HashSet<>(Arrays.asList(markSet));
		//this query will match all artifact keys that are not in the given set
		IQuery<IArtifactKey> query = QueryUtil.createQuery(IArtifactKey.class, "unique($0)", set); //$NON-NLS-1$
		aRepository.executeBatch(monitor -> {
			for (IArtifactKey key : aRepository.query(query, null)) {
				aRepository.removeDescriptor(key, new NullProgressMonitor());
				if (DEBUG) {
					Tracing.debug("Key removed:" + key); //$NON-NLS-1$
				}
			}
		}, new NullProgressMonitor());
	}

}
