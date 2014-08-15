package com.tekusource.sabongpro.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.StreamingConfigDao;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.service.StreamingConfigService;

@Service("streamingConfigService")
@Transactional
public class StreamingConfigServiceImpl implements StreamingConfigService {

	@Autowired
	private StreamingConfigDao streamingConfigDao;
	
	public void save(StreamingConfig config) {
		if(config != null) {
			streamingConfigDao.create(config);
		}
	}

	public void update(StreamingConfig config) {
		if(config != null) {
			StreamingConfig updateConfig = (StreamingConfig) getStreamingConfigBy(config.getId());
			if(updateConfig != null) {
				updateConfig.setDescription(config.getDescription());
				updateConfig.setUrl(config.getUrl());
				updateConfig.setStatus(config.getStatus());
				updateConfig.setDateLastUpdated(Calendar.getInstance());
				streamingConfigDao.update(updateConfig);
			}
		}
	}

	public void remove(Long id) {
		if(streamingConfigDao.exist(id)) {
			streamingConfigDao.remove(id);
		}
	}

	public StreamingConfig getStreamingConfigBy(Long id) {
		return streamingConfigDao.get(id);
	}

	public StreamingConfig getStreamingConfigBy(Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		return (StreamingConfig) streamingConfigDao.getBy(values, orders);
	}

	public List<StreamingConfig> getAllStreamingConfigs() {
		return streamingConfigDao.getAll();
	}
	
	public List<StreamingConfig> getStreamingConfigBy(String status) {
		return streamingConfigDao.getStreamingConfigBy(status);
	}
}
