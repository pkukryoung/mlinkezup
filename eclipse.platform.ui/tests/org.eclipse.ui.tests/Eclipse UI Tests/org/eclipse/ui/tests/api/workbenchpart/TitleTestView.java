/*******************************************************************************
 * Copyright (c) 2004, 2019 IBM Corporation and others.
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
package org.eclipse.ui.tests.api.workbenchpart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.layout.CellLayout;
import org.eclipse.ui.internal.layout.Row;
import org.eclipse.ui.part.ViewPart;

/**
 * @since 3.0
 */
public class TitleTestView extends ViewPart {

	Composite composite;

	Text title;

	Text name;

	Text contentDescription;

	Label titleLabel;

	Label nameLabel;

	Label cdLabel;

	@Override
	public void createPartControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		CellLayout layout = new CellLayout(2).setColumn(0, Row.fixed())
				.setColumn(1, Row.growing());
		composite.setLayout(layout);

		Label firstLabel = new Label(composite, SWT.NONE);
		firstLabel.setText("Title");
		title = new Text(composite, SWT.BORDER);
		title.setText(getTitle());

		title.addModifyListener(e -> setTitle(title.getText()));

		Label secondLabel = new Label(composite, SWT.NONE);
		secondLabel.setText("Name");
		name = new Text(composite, SWT.BORDER);
		name.setText(getPartName());
		name.addModifyListener(e -> setPartName(name.getText()));

		Label thirdLabel = new Label(composite, SWT.NONE);
		thirdLabel.setText("Content");
		contentDescription = new Text(composite, SWT.BORDER);
		contentDescription.setText(getContentDescription());
		contentDescription.addModifyListener(e -> setContentDescription(contentDescription.getText()));

		Label tlLabel = new Label(composite, SWT.NONE);
		tlLabel.setText("getTitle() = ");
		titleLabel = new Label(composite, SWT.NONE);

		Label nmLabel = new Label(composite, SWT.NONE);
		nmLabel.setText("getPartName() = ");
		nameLabel = new Label(composite, SWT.NONE);

		Label descLabel = new Label(composite, SWT.NONE);
		descLabel.setText("getContentDescription() = ");
		cdLabel = new Label(composite, SWT.NONE);

		updateLabels();

		addPropertyListener((source, propId) -> updateLabels());
	}

	private void updateLabels() {
		titleLabel.setText(getTitle());
		nameLabel.setText(getPartName());
		cdLabel.setText(getContentDescription());
	}

	@Override
	public void setFocus() {

	}

}
