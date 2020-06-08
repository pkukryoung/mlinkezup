/*******************************************************************************
 * Copyright (c) 2014-2016 Red Hat Inc., and others
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mickael Istria (Red Hat Inc.) - initial API and implementation
 ******************************************************************************/
package org.eclipse.ui.internal.wizards.datatransfer.expressions;

import java.io.File;

import org.eclipse.core.expressions.ElementHandler;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * Adds support for a set of additional expressions to use in plugin.xml to
 * decide enablement depending of file/resource structure. Expressions provided
 * by this {@link ElementHandler} only applies on {@link IContainer} and
 * {@link File}.
 *
 * @since 3.12
 */
public class FileExpressionHandler extends ElementHandler {

	@Override
	public Expression create(ExpressionConverter converter, IConfigurationElement element) {
		String name = element.getName();
		if (name == null) {
			return null;
		}

		switch (name) {
		case HasFileExpression.TAG:
			return new HasFileExpression(element);
		case HasFileRecursivelyExpression.TAG:
			return new HasFileRecursivelyExpression(element);
		case HasFileWithSuffixRecursivelyExpression.TAG:
			return new HasFileWithSuffixRecursivelyExpression(element);
		case HasFolderExpression.TAG:
			return new HasFolderExpression(element);
		default:
			break;
		}
		return null;
	}
}
