package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.EntryDao;
import com.tekusource.sabongpro.model.Entry;

@Repository("entryDao")
public class EntryDaoImpl extends GenericDaoImpl<Entry, Long> implements EntryDao {

	public EntryDaoImpl() {
		super(Entry.class);
	}
}
