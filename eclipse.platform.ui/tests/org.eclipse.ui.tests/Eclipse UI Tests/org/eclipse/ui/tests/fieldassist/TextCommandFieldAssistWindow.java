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
package org.eclipse.ui.tests.fieldassist;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.tests.fieldassist.TextFieldAssistWindow;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

public class TextCommandFieldAssistWindow extends TextFieldAssistWindow {

	@Override
	protected ContentProposalAdapter createContentProposalAdapter(
			Control control) {
		return new ContentAssistCommandAdapter(control,
				getControlContentAdapter(), getContentProposalProvider(), null,
				getAutoActivationCharacters());
	}

}
