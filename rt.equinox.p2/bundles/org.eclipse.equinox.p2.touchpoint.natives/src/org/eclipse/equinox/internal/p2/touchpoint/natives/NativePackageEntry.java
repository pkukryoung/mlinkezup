/*******************************************************************************
 * Copyright (c) 2014 Rapicorp, Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Rapicorp, Inc - prompt to install debian package
 *******************************************************************************/
package org.eclipse.equinox.internal.p2.touchpoint.natives;

public class NativePackageEntry {
	public String name;
	public String version;
	public String comparator;
	public String downloadLink;

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public NativePackageEntry(String name, String version, String comparator) {
		this.name = name;
		this.version = version;
		this.comparator = comparator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	@Override
	public String toString() {
		return name + ' ' + version;
	}

}
