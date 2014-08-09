package com.tekusource.sabongpro.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T, PK extends Serializable> {

	public void create(T object);
	
	public void update(T object);
	
	public void remove(PK id);
	
	public T get(PK id);
	
	public List<T> getAll();
	
	public boolean exist(PK id);
	
	public T getBy( String name, Object value );
	
	public T getBy( Map<String, Object> values, Map<String, Boolean> orders );
}
