package com.tekusource.sabongpro.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.StreamingConfigDao;
import com.tekusource.sabongpro.model.StreamingConfig;

@Repository("streamingConfigDao")
public class StreamingConfigDaoImpl extends GenericDaoImpl<StreamingConfig, Long> implements StreamingConfigDao {

	public StreamingConfigDaoImpl() {
		super(StreamingConfig.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<StreamingConfig> getStreamingConfigBy(boolean isOnline) {
		Query query = entityManager.createQuery("select obj from " + persistentClass.getName() + " obj where isStreamOnline = :isStreamOnline");
	    query.setParameter( "isStreamOnline", isOnline );
		return query.getResultList();
	}
}
