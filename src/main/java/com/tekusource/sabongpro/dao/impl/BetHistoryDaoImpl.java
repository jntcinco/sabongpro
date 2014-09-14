package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.BetHistoryDao;
import com.tekusource.sabongpro.model.BetHistory;

@Repository("betHistoryDao")
public class BetHistoryDaoImpl extends GenericDaoImpl<BetHistory, Long> implements BetHistoryDao {

	public BetHistoryDaoImpl() {
		super(BetHistory.class);
	}
}
