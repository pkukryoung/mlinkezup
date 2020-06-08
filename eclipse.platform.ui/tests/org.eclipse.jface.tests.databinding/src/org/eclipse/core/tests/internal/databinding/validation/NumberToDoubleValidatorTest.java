/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
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
 ******************************************************************************/

package org.eclipse.core.tests.internal.databinding.validation;

import java.math.BigDecimal;

import org.eclipse.core.internal.databinding.conversion.NumberToDoubleConverter;
import org.eclipse.core.internal.databinding.validation.NumberToDoubleValidator;
import org.eclipse.core.internal.databinding.validation.NumberToNumberValidator;

import java.text.NumberFormat;

/**
 * @since 1.1
 */
public class NumberToDoubleValidatorTest extends
		NumberToNumberValidatorTestHarness {

	@Override
	protected Number doGetOutOfRangeNumber() {
		return BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(Double.MAX_VALUE));
	}

	@Override
	protected NumberToNumberValidator doGetToBoxedTypeValidator(Class<?> fromType) {
		NumberToDoubleConverter converter = new NumberToDoubleConverter(NumberFormat.getInstance(), fromType, false);
		return new NumberToDoubleValidator(converter);
	}

	@Override
	protected NumberToNumberValidator doGetToPrimitiveValidator(Class<?> fromType) {
		NumberToDoubleConverter converter = new NumberToDoubleConverter(NumberFormat.getInstance(), fromType, true);
		return new NumberToDoubleValidator(converter);
	}
}
