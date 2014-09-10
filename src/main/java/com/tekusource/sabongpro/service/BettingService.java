package com.tekusource.sabongpro.service;

import java.util.List;

import com.tekusource.sabongpro.model.BettingInfo;

public interface BettingService {

	public void addMeron(BettingInfo bettingInfo);
	
	public void removeLastBetMeron(int index);

	public BettingInfo getBettingInfoMeron(int index);
	
	public List<BettingInfo> getMeron();

	public void addWala(BettingInfo bettingInfo);
	
	public void removeLastBetWala(int index);
	
	public BettingInfo getBettingInfoWala(int index);

	public List<BettingInfo> getWala();
	
	public double getTotalBetBy(String side);
	
	public void lockBetting();
	
	public boolean isBettingLocked();
	
	public void declareWinner();
}
