package com.tekusource.sabongpro.service;

import java.util.List;

import com.tekusource.sabongpro.model.BettingInfo;

public interface BettingService {
	
	public static final String MERON = "MERON";
	public static final String WALA = "WALA";

	public void addMeron(BettingInfo bettingInfo);
	
	public void removeLastBetMeron(String odds, int index);

	public BettingInfo getBettingInfoMeron(String odds, int index);
	
	public List<BettingInfo> getMeron(String odds);

	public void addWala(BettingInfo bettingInfo);
	
	public void removeLastBetWala(String odds, int index);
	
	public BettingInfo getBettingInfoWala(String odds, int index);

	public List<BettingInfo> getWala(String odds);
	
	public double getTotalBetBy(String odds, String side);
	
	public void lockBetting();
	
	public void unLockBetting();
	
	public boolean isBettingLocked();
	
	public void declareWinner();
}
