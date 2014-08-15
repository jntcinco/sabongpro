package com.tekusource.sabongpro.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tekusource.sabongpro.dao.GenericDao;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> persistentClass;
	
	@PersistenceContext(unitName="persitenceUnit")
	protected EntityManager entityManager; 
	
	public GenericDaoImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public void create(T object) {
		entityManager.persist(object);
	}
	
	public void update(T object) {
		entityManager.merge(object);
	}
	
	public void remove(PK id) {
		entityManager.remove(get(id));
		entityManager.flush();
	}
	
	public T get(PK id) {
		T entity = entityManager.find(persistentClass, id);
		if(entity == null) {
			String msg = persistentClass + " object with id '" + id + "' not found.";
			throw new EntityNotFoundException(msg);
		}
		return entity;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<T> getAll() {
		return entityManager.createQuery( "from " + persistentClass.getName() ).getResultList();
	}
	
	public boolean exist(PK id) {
		 T entity = (T) entityManager.find( this.persistentClass, id );
	     return entity != null;
	}
	
	/**
     * {@inheritDoc}
     * @return object associated with the input name and value; null if not found
     */
    @SuppressWarnings( "unchecked" )
    public T getBy( String name, Object value )
    {
        Query query =
            entityManager.createQuery( "select obj from " + persistentClass.getName() + " obj where " + name + " = :"
                + name.toString() );
        query.setParameter( name, value );
                    
        try
        {
            return (T) query.getSingleResult();
        }
        catch ( NoResultException e )
        {
            return null;
        }
    }
	
	/**
     * Retrives a single object whose property/column names and values matches the input criteria (<code>values</code>).
     * Result is sorted according to the input sort order. <br/><br/>
     * <b>Note:</b><br/>
     * This will only return a single object, if a list is expected, used the <code>findBy</code> operations instead.
     * 
     * @param values column name/value criteria where key is the property/column name and value is:
     * <ul>
     *  <li>if value is null, column value is matched to NULL
     *  <li>if value is a collection, column value to any value in the collection,
     *  <li>otherwise, column value is match to the String equivalent of the input value
     * </ul>
     * @param orders sort order where key is the property/column name and the value is the sort order: true if ascending, false otherwise
     * 
     * @return an object that matches the input criteria, sorted according to the the input sort order. If multiple objects matches the 
     * input criteria, the first object based on the sort order will be returned. If no object is matched, null is returned
     */
    @SuppressWarnings( "unchecked" )
    public T getBy( Map<String, Object> values, Map<String, Boolean> orders )
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "select obj from " );
        sb.append( persistentClass.getName() );
        sb.append( " obj where " );

        for ( Map.Entry<String, Object> entry : values.entrySet() )
        {
            sb.append( entry.getKey() );

            if ( entry.getValue() == null )
            {
                sb.append( " IS NULL" );
            }
            else if ( entry.getValue() instanceof Collection )
            {
                sb.append( " IN (:value_" + entry.getKey() + ")" );
            }
            else
            {
                sb.append( " = :value_" + entry.getKey() );
            }
            sb.append( " AND " );
        }
        // remove last AND
        sb.delete( sb.length() - " AND ".length(), sb.length() );

        // sort
        if ( orders != null && orders.size() > 0 )
        {
            sb.append( " ORDER BY " );
            for ( Map.Entry<String, Boolean> entry : orders.entrySet() )
            {
                sb.append( entry.getKey() );
                if ( entry.getValue() )
                {
                    sb.append( " ASC, " );
                }
                else
                {
                    sb.append( " DESC, " );
                }
            }
            // remove last comma and space
            sb.delete( sb.length() - 2, sb.length() );
        }

        Query query = entityManager.createQuery( sb.toString() );

        for ( Map.Entry<String, Object> entry : values.entrySet() )
        {
            if ( entry.getValue() != null )
            {
                query.setParameter( "value_" + entry.getKey(), entry.getValue() );
            }
        }

        try
        {
            return (T) query.getSingleResult();
        }
        catch ( NoResultException e )
        {
            String errorMessage = "GenericDao's getSingleResult() found nothing.";
//            logger.debug( errorMessage );

            return null;
        }
    }
}
