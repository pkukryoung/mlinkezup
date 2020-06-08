/*******************************************************************************
 * Copyright (c) 2005, 2017 IBM Corporation and others.
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
package org.eclipse.ui.tests.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.ui.IMemento;

public class TestMemento implements IMemento {

	String typeName;
	String id;
	HashSet<IMemento> children = new HashSet<>();
	Hashtable<String, Object> values = new Hashtable<>();
	String textData;

	public TestMemento(String type, String id){
		typeName = type;
		this.id = id;
	}

	@Override
	public IMemento createChild(String type) {
		return createChild(type, null);
	}

	@Override
	public IMemento createChild(String type, String id) {
		IMemento child  = new TestMemento(typeName,id);
		children.add(child);
		return child;
	}

	@Override
	public IMemento getChild(String type) {
		Iterator<IMemento> iterator = children.iterator();
		while(iterator.hasNext()){
			TestMemento next = (TestMemento) iterator.next();
			if(next.typeName.equals(type)) {
				return next;
			}
		}
		return null;
	}

	@Override
	public IMemento[] getChildren() {
		IMemento[] returnValue = new IMemento[children.size()];
		children.toArray(returnValue);
		return returnValue;
	}

	@Override
	public IMemento[] getChildren(String type) {
		Iterator<IMemento> iterator = children.iterator();
		Collection<IMemento> matches = new HashSet<>();
		while(iterator.hasNext()){
			TestMemento next = (TestMemento) iterator.next();
			if(next.typeName.equals(type)){
				matches.add(next);
			}
		}

		IMemento[] returnValue = new IMemento[matches.size()];
		matches.toArray(returnValue);
		return returnValue;
	}

	@Override
	public Float getFloat(String key) {
		if(values.containsKey(key)) {
			return (Float) values.get(key);
		}
		return null;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public Integer getInteger(String key) {
		if(values.containsKey(key)) {
			return (Integer) values.get(key);
		}
		return null;
	}

	@Override
	public String getString(String key) {
		if(values.containsKey(key)) {
			return (String) values.get(key);
		}
		return null;
	}

	@Override
	public String getTextData() {
		return textData;
	}

	@Override
	public void putFloat(String key, float value) {
		values.put(key,Float.valueOf(value));
	}

	@Override
	public void putInteger(String key, int value) {
		values.put(key,Integer.valueOf(value));

	}

	@Override
	public void putMemento(IMemento memento) {
		TestMemento newMemento = (TestMemento) memento;
		typeName = newMemento.typeName;
		id =  newMemento.id;
		children =  newMemento.children;
		values =  newMemento.values;
		textData =  newMemento.textData;
	}

	@Override
	public void putString(String key, String value) {
		values.put(key,value);

	}

	@Override
	public void putTextData(String data) {
		textData = data;

	}

	@Override
	public String[] getAttributeKeys() {
		Set<String> keySet = values.keySet();
		return keySet.toArray(new String[keySet.size()]);
	}

	@Override
	public Boolean getBoolean(String key) {
		if(values.containsKey(key)) {
			return (Boolean) values.get(key);
		}
		return null;
	}

	@Override
	public String getType() {
		return typeName;
	}

	@Override
	public void putBoolean(String key, boolean value) {
		values.put(key, value?Boolean.TRUE:Boolean.FALSE);
	}

}
