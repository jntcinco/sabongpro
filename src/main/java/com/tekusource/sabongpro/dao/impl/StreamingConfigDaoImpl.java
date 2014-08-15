package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.StreamingConfigDao;
import com.tekusource.sabongpro.model.StreamingConfig;

@Repository("streamingConfigDao")
public class StreamingConfigDaoImpl extends GenericDaoImpl<StreamingConfig, Long> implements StreamingConfigDao {

	public StreamingConfigDaoImpl() {
		super(StreamingConfig.class);
	}
}
