package com.tekusource.sabongpro.service;

import java.util.List;
import java.util.Map;

import com.tekusource.sabongpro.model.Entry;

public interface EntryService {

	public void save(Entry entry);
	
	public void update(Entry entry);
	
	public void remove(Long id);
	
	public Entry getEntryBy(Long id);
	
//	public Entry getEntryBy(Map<String, Object> values);
	
	public List<Entry> getAllEntries();
}
