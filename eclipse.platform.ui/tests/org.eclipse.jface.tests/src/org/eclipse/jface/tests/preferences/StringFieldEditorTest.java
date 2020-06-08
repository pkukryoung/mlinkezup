/*******************************************************************************
 * Copyright (c) 2008, 2017 IBM Corporation and others.
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
 *     Matthew Bisson - <mrbisson@ca.ibm.com> Initial test implementation
 *     Pawel Pogorzelski - <Pawel.Pogorzelski@pl.ibm.com> - test for bug 289599
 ******************************************************************************/

package org.eclipse.jface.tests.preferences;

import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import junit.framework.TestCase;

public class StringFieldEditorTest extends TestCase {

	private Shell shell;
	private StringFieldEditor stringFieldEditor;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		shell = new Shell();

		stringFieldEditor = new StringFieldEditor("name", "label", shell);
	}

	public void testSetLabelText() {
		stringFieldEditor.setLabelText("label text");
		assertEquals("label text", stringFieldEditor.getLabelText());

		stringFieldEditor.setLabelText("label text");
		assertEquals("label text", stringFieldEditor.getLabelText());
	}

	public void testLoad() {
		PreferenceStore myPreferenceStore = new PreferenceStore();
		stringFieldEditor.setPreferenceName("name");
		stringFieldEditor.setPreferenceStore(myPreferenceStore);

		myPreferenceStore.setDefault("name", "foo");
		stringFieldEditor.load();
		assertEquals(stringFieldEditor.getStringValue(), "foo");

		myPreferenceStore.setDefault("name", "foo");
		myPreferenceStore.setValue("name", "bar");
		stringFieldEditor.load();
		assertEquals(stringFieldEditor.getStringValue(), "bar");
	}

	public void testLoadDefault() {
		PreferenceStore myPreferenceStore = new PreferenceStore();
		stringFieldEditor.setPreferenceName("name");
		stringFieldEditor.setPreferenceStore(myPreferenceStore);

		myPreferenceStore.setDefault("name", "foo");
		myPreferenceStore.setValue("name", "bar");
		stringFieldEditor.loadDefault();
		assertEquals(stringFieldEditor.getStringValue(), "foo");
	}

	public void testSetValueInWidget() {
		PreferenceStore myPreferenceStore = new PreferenceStore();
		stringFieldEditor.setPreferenceName("name");
		stringFieldEditor.setPreferenceStore(myPreferenceStore);

		myPreferenceStore.setValue("name", "foo");
		stringFieldEditor.load();
		assertEquals(stringFieldEditor.getStringValue(), "foo");

		Text text = stringFieldEditor.getTextControl(shell);
		text.setText("bar");
		assertEquals(stringFieldEditor.getStringValue(), "bar");
	}

	public void testSetValueInEditor() {
		PreferenceStore myPreferenceStore = new PreferenceStore();
		stringFieldEditor.setPreferenceName("name");
		stringFieldEditor.setPreferenceStore(myPreferenceStore);

		myPreferenceStore.setValue("name", "foo");
		stringFieldEditor.load();
		assertEquals(stringFieldEditor.getStringValue(), "foo");

		stringFieldEditor.setStringValue("bar");
		Text text = stringFieldEditor.getTextControl(shell);
		assertEquals(text.getText(), "bar");
		assertEquals(stringFieldEditor.getStringValue(), "bar");
	}

	public void testBug289599() {
		PreferenceStore store = new PreferenceStore();
		store.setDefault("foo", "bar");
		assertEquals("bar", store.getString("foo"));
		store.setValue("foo", "???");
		assertEquals("???", store.getString("foo"));
		IPropertyChangeListener listener = event -> {
			assertEquals("foo", event.getProperty());
			assertEquals("???", event.getOldValue());
			assertEquals("bar", event.getNewValue());
		};
		store.addPropertyChangeListener(listener);
		store.setToDefault("foo");
		store.removePropertyChangeListener(listener);
		assertEquals("bar", store.getString("foo"));
		IPropertyChangeListener failingListener = event -> fail("1.0");
		store.addPropertyChangeListener(failingListener);
		// We already called setToDefault, nothing should happen this time
		store.setToDefault("foo");
		store.removePropertyChangeListener(failingListener);
		assertEquals("bar", store.getString("foo"));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

