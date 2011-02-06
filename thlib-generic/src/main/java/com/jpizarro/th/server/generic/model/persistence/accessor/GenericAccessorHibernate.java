/*
 * Android Runner is a multiplayer GPS game fully written by Xurxo Mendez Perez
 * 
 * Copyright (C) 2009 Xurxo Mendez Perez
 *   
 * This file is part of Android Runner.
 * 
 * Android Runner is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Android Runner is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Android Runner.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpizarro.th.server.generic.model.persistence.accessor;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jpizarro.th.server.generic.model.persistence.util.exceptions.DuplicateInstanceException;
import com.jpizarro.th.server.generic.model.persistence.util.exceptions.InstanceNotFoundException;

/**
 * 
 * @author "Xurxo Mendez Perez"
 *
 */
public class GenericAccessorHibernate<E, PK extends Serializable> implements
		GenericAccessor<E, PK> {

	private SessionFactory sessionFactory;
	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public GenericAccessorHibernate() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().
			getGenericSuperclass()).getActualTypeArguments()[0];		
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void create(E entity) throws DuplicateInstanceException {
		getSession().persist(entity);
	}

	public boolean exists(PK id) {

		return getSession().createCriteria(entityClass).
			add(Restrictions.idEq(id)).
			setProjection(Projections.id()).
			uniqueResult() != null;

	}

	@SuppressWarnings("unchecked")
	public E find(PK id) throws InstanceNotFoundException {

		E entity = (E) getSession().get(entityClass, id);

		if (entity == null) {
			throw new InstanceNotFoundException(id, entityClass.getName());
        }

		return entity;

	}

	public void remove(PK id) throws InstanceNotFoundException {
		getSession().delete(find(id));
	}

	@SuppressWarnings("unchecked")
	public E update(E entity) {
		return (E) getSession().merge(entity);
	}

}
