/*******************************************************************************
 * Copyright (c) 2018, 2019 SAP SE and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP SE - initial version
 *******************************************************************************/
package org.eclipse.jface.tests.widgets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestUnitControlFactory.class, TestUnitButtonFactory.class, TestUnitLabelFactory.class,
		TestUnitCompositeFactory.class, TestUnitSpinnerFactory.class, TestUnitTextFactory.class,
		TestUnitTableFactory.class, TestUnitItemFactory.class, TestUnitTableColumnFactory.class })
public class AllWidgetTests {

}
