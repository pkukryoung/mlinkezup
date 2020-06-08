/**
 * Copyright (c) 2008, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      IBM Corporation - initial API and implementation
 */
package org.eclipse.e4.ui.model.application.ui.advanced.impl;

import org.eclipse.e4.ui.model.application.ui.advanced.MAdvancedFactory;
import org.eclipse.e4.ui.model.application.ui.advanced.MArea;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdvancedFactoryImpl extends EFactoryImpl implements MAdvancedFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AdvancedFactoryImpl eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AdvancedFactoryImpl init() {
		try {
			AdvancedFactoryImpl theAdvancedFactory = (AdvancedFactoryImpl) EPackage.Registry.INSTANCE
					.getEFactory(AdvancedPackageImpl.eNS_URI);
			if (theAdvancedFactory != null) {
				return theAdvancedFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AdvancedFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdvancedFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case AdvancedPackageImpl.PLACEHOLDER:
			return (EObject) createPlaceholder();
		case AdvancedPackageImpl.PERSPECTIVE:
			return (EObject) createPerspective();
		case AdvancedPackageImpl.PERSPECTIVE_STACK:
			return (EObject) createPerspectiveStack();
		case AdvancedPackageImpl.AREA:
			return (EObject) createArea();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 1.0
	 * @generated
	 */
	@Override
	public MPlaceholder createPlaceholder() {
		return new PlaceholderImpl();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 1.0
	 * @generated
	 */
	@Override
	public MPerspective createPerspective() {
		return new PerspectiveImpl();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 1.0
	 * @generated
	 */
	@Override
	public MPerspectiveStack createPerspectiveStack() {
		return new PerspectiveStackImpl();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 1.0
	 * @generated
	 */
	@Override
	public MArea createArea() {
		return new AreaImpl();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdvancedPackageImpl getAdvancedPackage() {
		return (AdvancedPackageImpl) getEPackage();
	}

} //AdvancedFactoryImpl
