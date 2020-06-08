/*******************************************************************************
 * Copyright (c) 2004, 2017 IBM Corporation and others.
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
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * The ForegroundColorDecorator is for testing the foreground enablement.
 */
public class ForegroundColorDecorator implements ILightweightLabelDecorator {


	public static final String ID = "org.eclipse.ui.tests.foregroundDecorator";

	public static Color color;

	@Override
	public void decorate(Object element, IDecoration decoration) {

		if(color == null){
			PlatformUI.getWorkbench().getDisplay().syncExec(() -> setUpColor());
		}
		decoration.setForegroundColor(color);

	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public static void setUpColor(){
		color = PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_YELLOW);
	}


}
