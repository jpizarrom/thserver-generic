package com.jpizarro.th.server.generic.view.axis;

import java.io.Serializable;

import com.jpizarro.th.server.generic.model.persistence.util.exceptions.DuplicateInstanceException;
import com.jpizarro.th.server.generic.model.persistence.util.exceptions.InstanceNotFoundException;

public interface GenericService<E>{

	void create(E entity) throws DuplicateInstanceException;
		
	E find(Long id) throws InstanceNotFoundException;
	
	boolean exists(Long id);
	
	E update(E entity);

	void remove(Long id) throws InstanceNotFoundException;	
	
}