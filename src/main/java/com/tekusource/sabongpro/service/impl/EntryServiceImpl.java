package com.tekusource.sabongpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.EntryDao;
import com.tekusource.sabongpro.model.Entry;
import com.tekusource.sabongpro.service.EntryService;

@Service("entryService")
@Transactional
public class EntryServiceImpl implements EntryService {

	private static final String FIGHT_NUMBER = "fightNumber";
	@Autowired
	private EntryDao entryDao;
	
	public void save(Entry entry) {
		if(entry != null) {
			entryDao.create(entry);
		}
	}
	
	public void update(Entry entry) {
		if(entry != null) {
			Entry updateEntry = (Entry) getEntryBy(entry.getId());
			if(updateEntry != null) {
				updateEntry.setEntryName(entry.getEntryName());
				updateEntry.setOwnerName(entry.getOwnerName());
				updateEntry.setSide(entry.getSide());
				entryDao.update(updateEntry);
			}
		}
	}
	
	public void remove(Long id) {
		if(entryDao.exist(id)) {
			entryDao.remove(id);
		}
	}
	
	public Entry getEntryBy(Long id) {
		return entryDao.get(id);
	}
	
	public Entry getEntryByFightNumber(String value) {
		return entryDao.getBy(FIGHT_NUMBER, value);
	}
	
	public List<Entry> getAllEntries() {
		return entryDao.getAll();
	}
}
