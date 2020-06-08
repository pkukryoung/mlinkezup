/*******************************************************************************
 * Copyright (c) 2009 Remy Chi Jian Suen and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Remy Chi Jian Suen <remy.suen@gmail.com> - initial API and implementation
 *     IBM - ongoing development
******************************************************************************/
package org.eclipse.jface.tests.fieldassist;

import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ComboFieldAssistWindow extends AbstractFieldAssistWindow {

	@Override
	protected IControlContentAdapter getControlContentAdapter() {
		return new ComboContentAdapter();
	}

	@Override
	protected Control createFieldAssistControl(Composite parent) {
		return new Combo(parent, SWT.DROP_DOWN);
	}

}
