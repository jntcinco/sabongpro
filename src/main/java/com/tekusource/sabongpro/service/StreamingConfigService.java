package com.tekusource.sabongpro.service;

import java.util.List;
import java.util.Map;

import com.tekusource.sabongpro.model.StreamingConfig;

public interface StreamingConfigService {

	public void save(StreamingConfig config);
	
	public void update(StreamingConfig config);
	
	public void remove(Long id);
	
	public StreamingConfig getStreamingConfigBy(Long id);
	
	public StreamingConfig getStreamingConfigBy(Map<String, Object> values);
	
	public List<StreamingConfig> getAllStreamingConfigs();
}
