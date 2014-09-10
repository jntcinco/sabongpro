package com.tekusource.sabongpro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.model.BettingInfo;
import com.tekusource.sabongpro.service.BettingService;

@Service("bettingService")
@Scope("singleton")
@Transactional
public class BettingServiceImpl implements BettingService {

	private List<BettingInfo> meronInfos = new ArrayList<BettingInfo>();
	private List<BettingInfo> walaInfos = new ArrayList<BettingInfo>();
	
	private boolean isBettingLocked = false;
	
	public void addMeron(BettingInfo bettingInfo) {
		int count = 0;
		if(!walaInfos.isEmpty()) {
			for(BettingInfo info : walaInfos) {
				if(info.getUserName().equals(bettingInfo.getUserName())) {
					count = 1;
				}
			}
		}
		if(count == 0) {
			meronInfos.add(bettingInfo);
		}
	}
	
	public void removeLastBetMeron(int index) {
		meronInfos.remove(index);
	}
	
	public BettingInfo getBettingInfoMeron(int index) {
		return meronInfos.get(index);
	}
	
	public List<BettingInfo> getMeron() {
		return meronInfos;
	}
	
	public void addWala(BettingInfo bettingInfo) {
		int count = 0;
		if(!meronInfos.isEmpty()) {
			for(BettingInfo info : meronInfos) {
				if(info.getUserName().equals(bettingInfo.getUserName())) {
					count = 1;
				}
			}
		}
		if(count == 0) {
			walaInfos.add(bettingInfo);
		}
	}
	
	public void removeLastBetWala(int index) {
		walaInfos.remove(index);
	}
	
	public BettingInfo getBettingInfoWala(int index) {
		return walaInfos.get(index);
	}
	
	public List<BettingInfo> getWala() {
		return walaInfos;
	}
	
	public double getTotalBetBy(String side) {
		double totalBet = 0.0;
		if(side.equals("meron")) {
			for(BettingInfo info : meronInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else {
			for(BettingInfo info : walaInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		}
		return totalBet;
	}
	
	public void lockBetting() {
		this.isBettingLocked = true;
	}
	
	public boolean isBettingLocked() {
		return isBettingLocked;
	}
	
	public void declareWinner() {
		this.isBettingLocked = false;
		meronInfos.clear();
		walaInfos.clear();
	}
}
