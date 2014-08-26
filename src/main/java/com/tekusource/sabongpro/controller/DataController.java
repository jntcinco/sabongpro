package com.tekusource.sabongpro.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.service.StreamingConfigService;

@Controller
@RequestMapping( value = "/services" )
public class DataController {

	@Autowired
	private StreamingConfigService streamingConfigService;
	
	@RequestMapping(value="/streamUrl", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> streamUrl() {
		String streamUrl = "";
		List<StreamingConfig> configs = (List<StreamingConfig>) streamingConfigService.getStreamingConfigBy(true);
        StreamingConfig config = null;
		if (!configs.isEmpty()) {
			if(config == null) {
				config = (StreamingConfig) configs.get(0);
				streamUrl = config.getUrl();
			}
        }
		return Collections.singletonMap("streamUrl", streamUrl);
	}
	
	@RequestMapping(value="/getStreamDetails", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> getStreamDetails(@RequestParam("configId") Long configId ) {
		StreamingConfig config = (StreamingConfig) streamingConfigService.getStreamingConfigBy(configId);
		if(config == null) {
			config = new StreamingConfig();
		}
		return Collections.singletonMap("config", config);
	}
}
