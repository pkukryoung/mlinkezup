/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
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
package org.eclipse.e4.ui.workbench.addons.dndaddon;

import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

class DragAndDropUtil {
	public static final String IGNORE_AS_DROP_TARGET = "ignore_as_drop_target"; //$NON-NLS-1$

	/**
	 * Shorthand method. Returns the bounding rectangle for the given control,
	 * in display coordinates.
	 *
	 * @param boundsControl
	 *            the control whose bounds are to be computed
	 * @return the bounds of the given control in display coordinates
	 */
	public static Rectangle getDisplayBounds(Control boundsControl) {
		Control parent = boundsControl.getParent();
		if (parent == null || boundsControl instanceof Shell) {
			return boundsControl.getBounds();
		}

		return Geometry.toDisplay(parent, boundsControl.getBounds());
	}

	/**
	 * Finds and returns the most specific SWT control at the given location.
	 * (Note: this does a DFS on the SWT widget hierarchy, which can be slow).
	 * Any invisible control or control tagged with IGNORE_AS_DROP_TARGET will
	 * be ignored by this method.
	 *
	 * @param displayToSearch
	 *            the display to search for potential controls
	 * @param locationToFind
	 *            the position, in display coordinates, to be located
	 * @return the most specific SWT control at the given location
	 */
	public static Control findControl(Display displayToSearch, Point locationToFind) {
		Shell[] shells = displayToSearch.getShells();
		fixShellOrder(displayToSearch, shells);
		return findControl(shells, locationToFind);
	}

	/**
	 * Finds the active shell and moves it to the end of the given array, so that
	 * findControl() will find the controls from the active shell first.
	 */
	private static void fixShellOrder(Display display, Shell[] shells) {
		if (shells.length <= 1) {
			return;
		}
		Shell activeShell = display.getActiveShell();
		int lastIndex = shells.length - 1;
		if (activeShell == null || shells[lastIndex] == activeShell) {
			return;
		}
		// Find the index of the active shell and exchange last one with active
		for (int i = 0; i < shells.length; i++) {
			if (shells[i] == activeShell) {
				Shell toMove = shells[lastIndex];
				shells[i] = toMove;
				shells[lastIndex] = activeShell;
				break;
			}
		}
	}

	/**
	 * Searches the given list of controls for a control containing the given
	 * point. If the array contains any composites, those composites will be
	 * recursively searched to find the most specific child that contains the
	 * point. Any invisible control or control tagged with IGNORE_AS_DROP_TARGET
	 * will be ignored by this method.
	 *
	 * @param toSearch
	 *            an array of controls to be searched for potential matches
	 * @param locationToFind
	 *            a point (in display coordinates)
	 * @return the most specific Control that overlaps the given point, or null
	 *         if none
	 */
	private static Control findControl(Control[] toSearch, Point locationToFind) {
		for (int idx = toSearch.length - 1; idx >= 0; idx--) {
			Control next = toSearch[idx];

			if (next.getData(IGNORE_AS_DROP_TARGET) != null) {
				continue;
			}

			if (!next.isDisposed() && next.isVisible()) {
				Rectangle bounds = getDisplayBounds(next);

				if (bounds.contains(locationToFind)) {
					if (next instanceof Composite) {
						Control result = findControl((Composite) next, locationToFind);

						if (result != null) {
							return result;
						}
					}

					return next;
				}
			}
		}

		return null;
	}

	/**
	 * Finds the control at the given location. Any invisible control or control
	 * tagged with IGNORE_AS_DROP_TARGET will be ignored by this method.
	 *
	 * @param toSearch
	 *            the composite to be searched for potential matches.
	 * @param locationToFind
	 *            location (in display coordinates)
	 * @return the control at the given location
	 */
	private static Control findControl(Composite toSearch, Point locationToFind) {
		Control[] children = toSearch.getChildren();

		return findControl(children, locationToFind);
	}
}
