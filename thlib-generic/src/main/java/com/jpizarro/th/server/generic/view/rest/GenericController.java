package com.jpizarro.th.server.generic.view.rest;

import java.util.List;

public interface GenericController <E,PK>{
	public E getEntity(PK id);
	public List<E> getEntities();
	public E addEntity(E body);
	public E updateEntity(String body);
	public Object removeEntity(PK id);

}
