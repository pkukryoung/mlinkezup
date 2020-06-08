/*******************************************************************************
 * Copyright (c) 2006, 2012 IBM Corporation and others.
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
package org.eclipse.ui.tests.views.properties.tabbed.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class TestsView
	extends ViewPart
	implements ITabbedPropertySheetPageContributor {

	private TreeViewer viewer;

	protected TabbedPropertySheetPage tabbedPropertySheetPage;

	public static final String TESTS_VIEW_ID = "org.eclipse.ui.tests.views.properties.tabbed.views.TestsView"; //$NON-NLS-1$

	class ViewLabelProvider
		extends LabelProvider {

		@Override
		public String getText(Object obj) {
			Element element = (Element) ((TreeNode) obj).getValue();
			return element.getName();
		}

		@Override
		public Image getImage(Object obj) {
			Element element = (Element) ((TreeNode) obj).getValue();
			return element.getImage();
		}
	}

	/**
	 * The constructor.
	 */
	public TestsView() {
		//
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TestsViewContentProvider(this));
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(getViewSite());
		getSite().setSelectionProvider(viewer);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (adapter == IPropertySheetPage.class) {
			if (tabbedPropertySheetPage == null) {
				tabbedPropertySheetPage = new TabbedPropertySheetPage(this);
			}
			return adapter.cast(tabbedPropertySheetPage);
		}
		return super.getAdapter(adapter);
	}

	@Override
	public String getContributorId() {
		return TESTS_VIEW_ID;
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public TabbedPropertySheetPage getTabbedPropertySheetPage() {
		return tabbedPropertySheetPage;
	}
}