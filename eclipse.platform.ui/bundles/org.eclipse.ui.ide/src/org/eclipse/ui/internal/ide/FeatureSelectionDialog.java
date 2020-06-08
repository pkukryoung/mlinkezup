/*******************************************************************************
 * Copyright (c) 2000, 2019 IBM Corporation and others.
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
 *     Simon Scholz <simon.scholz@vogella.com> - Bug 448060
 *     Alexander Fedorov <alexander.fedorov@arsysop.ru> - Bug 460381
 *******************************************************************************/

package org.eclipse.ui.internal.ide;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Dialog to allow the user to select a feature from a list.
 */
public class FeatureSelectionDialog extends AbstractSelectionDialog<AboutInfo> {
	/**
	 * List width in characters.
	 */
	private static final int LIST_WIDTH = 60;

	/**
	 * List height in characters.
	 */
	private static final int LIST_HEIGHT = 10;

	/**
	 * The feature about infos.
	 */
	private AboutInfo[] features;

	/**
	 * List to display the resolutions.
	 */
	private ListViewer listViewer;

	/**
	 * The help context id
	 */
	private String helpContextId;

	/**
	 * Creates an instance of this dialog to display
	 * the given features.
	 * <p>
	 * There must be at least one feature.
	 * </p>
	 *
	 * @param shell  the parent shell
	 * @param features  the features to display
	 * @param primaryFeatureId  the id of the primary feature or null if none
	 * @param shellTitle  shell title
	 * @param shellMessage  shell message
	 * @param helpContextId  help context id
	 */
	public FeatureSelectionDialog(Shell shell, AboutInfo[] features,
			String primaryFeatureId, String shellTitle, String shellMessage,
			String helpContextId) {

		super(shell);
		if (features == null || features.length == 0) {
			throw new IllegalArgumentException();
		}
		this.features = features;
		this.helpContextId = helpContextId;
		setTitle(shellTitle);
		setMessage(shellMessage);

		// Sort ascending
		Arrays.sort(features, new Comparator<AboutInfo>() {
			Collator coll = Collator.getInstance(Locale.getDefault());

			@Override
			public int compare(AboutInfo i1, AboutInfo i2) {
				String name1, name2;
				name1 = i1.getFeatureLabel();
				name2 = i2.getFeatureLabel();
				if (name1 == null) {
					name1 = ""; //$NON-NLS-1$
				}
				if (name2 == null) {
					name2 = ""; //$NON-NLS-1$
				}
				return coll.compare(name1, name2);
			}
		});

		// Find primary feature
		for (AboutInfo feature : features) {
			if (feature.getFeatureId().equals(primaryFeatureId)) {
				setInitialSelection(feature);
				return;
			}
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(newShell,
				helpContextId);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		// Create label
		createMessageArea(composite);
		// Create list viewer
		listViewer = new ListViewer(composite, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = convertHeightInCharsToPixels(LIST_HEIGHT);
		data.widthHint = convertWidthInCharsToPixels(LIST_WIDTH);
		listViewer.getList().setLayoutData(data);
		listViewer.getList().setFont(parent.getFont());
		// Set the label provider
		listViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				// Return the features's label.
				return element == null ? "" : ((AboutInfo) element).getFeatureLabel(); //$NON-NLS-1$
			}
		});

		// Set the content provider
		listViewer.setContentProvider(ArrayContentProvider.getInstance());
		listViewer.setInput(features);

		// Add a selection change listener
		listViewer.addSelectionChangedListener(
				event -> getButton(IDialogConstants.OK_ID).setEnabled(!event.getSelection().isEmpty()));

		// Add double-click listener
		listViewer.addDoubleClickListener(event -> okPressed());
		return composite;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		// Set the initial selection
		listViewer.setSelection(new StructuredSelection(getInitialSelection()), true);
		return contents;
	}

	@Override
	protected void okPressed() {
		setResult(listViewer.getStructuredSelection(), AboutInfo.class);
		super.okPressed();
	}
}
