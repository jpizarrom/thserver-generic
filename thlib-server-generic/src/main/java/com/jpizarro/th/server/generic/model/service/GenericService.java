package com.jpizarro.th.server.generic.model.service;

import java.io.Serializable;

import com.jpizarro.th.server.generic.model.persistence.util.exceptions.DuplicateInstanceException;
import com.jpizarro.th.server.generic.model.persistence.util.exceptions.InstanceNotFoundException;

public interface GenericService<E, PK extends Serializable>{

	E create(E entity) throws DuplicateInstanceException;
		
	E find(PK id) throws InstanceNotFoundException;
	
	boolean exists(PK id);
	
	E update(E entity) throws InstanceNotFoundException;

	void remove(PK id) throws InstanceNotFoundException;	
	
}