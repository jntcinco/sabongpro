package com.tekusource.sabongpro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.model.BettingInfo;
import com.tekusource.sabongpro.model.OddsType;
import com.tekusource.sabongpro.service.BettingService;

@Service("bettingService")
@Scope("singleton")
@Transactional
public class BettingServiceImpl implements BettingService {

	private List<BettingInfo> meronInfos = new ArrayList<BettingInfo>();
	private List<BettingInfo> nineTenMeron = new ArrayList<BettingInfo>();
	
	private List<BettingInfo> walaInfos = new ArrayList<BettingInfo>();
	private List<BettingInfo> nineTenWala = new ArrayList<BettingInfo>();
	
	private boolean isBettingLocked = false;
	
	public void addMeron(BettingInfo bettingInfo) {
		if(bettingInfo.getOdds().equals(OddsType.TEN_TEN.getDescription())) {
			if(!isUserBet(bettingInfo.getUserName(), walaInfos)) {
				meronInfos.add(bettingInfo);
			}
		} else if(bettingInfo.getOdds().equals(OddsType.NINE_TEN.getDescription())) {
			if(!isUserBet(bettingInfo.getUserName(), nineTenWala)) {
				nineTenMeron.add(bettingInfo);
			}
		}
	}
	
	public void removeLastBetMeron(String odds, int index) {
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			meronInfos.remove(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			nineTenMeron.remove(index);
		}
	}
	
	public BettingInfo getBettingInfoMeron(String odds, int index) {
		BettingInfo info = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			info = (BettingInfo) meronInfos.get(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			info = (BettingInfo) nineTenMeron.get(index);
		}
		return info;
	}
	
	public List<BettingInfo> getMeron(String odds) {
		List<BettingInfo> infos = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			infos = meronInfos;
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			infos = nineTenMeron;
		}
		return infos;
	}
	
	public void addWala(BettingInfo bettingInfo) {
		if(bettingInfo.getOdds().equals(OddsType.TEN_TEN.getDescription())) {
			if(!isUserBet(bettingInfo.getUserName(), meronInfos)) {
				walaInfos.add(bettingInfo);
			}
		} else if(bettingInfo.getOdds().equals(OddsType.NINE_TEN.getDescription())) {
			if(!isUserBet(bettingInfo.getUserName(), nineTenMeron)) {
				nineTenWala.add(bettingInfo);
			}
		}
	}
	
	public void removeLastBetWala(String odds, int index) {
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			walaInfos.remove(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			nineTenWala.remove(index);
		}
	}
	
	public BettingInfo getBettingInfoWala(String odds, int index) {
		BettingInfo info = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			info = (BettingInfo) walaInfos.get(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			info = (BettingInfo) nineTenWala.get(index);
		}
		return info;
	}
	
	public List<BettingInfo> getWala(String odds) {
		List<BettingInfo> infos = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			infos = walaInfos;
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			infos = nineTenWala;
		}
		return infos;
	}
	
	public double getTotalBetBy(String odds, String side) {
		double totalBet = 0.0;
		if(side.equals(BettingService.MERON) && odds.equals(OddsType.TEN_TEN.getDescription())) {
			for(BettingInfo info : meronInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(BettingService.MERON) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			for(BettingInfo info : nineTenMeron) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(BettingService.WALA) && odds.equals(OddsType.TEN_TEN.getDescription())) {
			for(BettingInfo info : walaInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		}  else if(side.equals(BettingService.WALA) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			for(BettingInfo info : nineTenWala) {
				totalBet = totalBet + info.getBetAmount();
			}
		} 
		return totalBet;
	}
	
	public void lockBetting() {
		this.isBettingLocked = true;
	}
	
	public void unLockBetting() {
		this.isBettingLocked = false;
	}
	
	public boolean isBettingLocked() {
		return isBettingLocked;
	}
	
	public void declareWinner() {
		this.isBettingLocked = false;
		meronInfos.clear();
		nineTenMeron.clear();
		walaInfos.clear();
		nineTenWala.clear();
	}
	
	private boolean isUserBet(String userName, List<BettingInfo> infos) {
		boolean isBet = false;
		if(!infos.isEmpty()) {
			for(BettingInfo info : infos) {
				if(info.getUserName().equals(userName)) {
					isBet = true;
				}
			}
		}
		return isBet;
	}
}
