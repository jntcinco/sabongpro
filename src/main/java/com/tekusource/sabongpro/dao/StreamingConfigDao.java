package com.tekusource.sabongpro.dao;

import java.util.List;

import com.tekusource.sabongpro.model.StreamingConfig;

public interface StreamingConfigDao extends GenericDao<StreamingConfig, Long> {

	public List<StreamingConfig> getStreamingConfigBy(boolean isOnline);
}
