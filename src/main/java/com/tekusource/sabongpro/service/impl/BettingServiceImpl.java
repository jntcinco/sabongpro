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
	private List<BettingInfo> eightTenMeron = new ArrayList<BettingInfo>();
	private List<BettingInfo> tenNineMeron = new ArrayList<BettingInfo>();
	private List<BettingInfo> tenEightMeron = new ArrayList<BettingInfo>();
	
	private List<BettingInfo> walaInfos = new ArrayList<BettingInfo>();
	private List<BettingInfo> nineTenWala = new ArrayList<BettingInfo>();
	private List<BettingInfo> eightTenWala = new ArrayList<BettingInfo>();
	private List<BettingInfo> tenNineWala = new ArrayList<BettingInfo>();
	private List<BettingInfo> tenEightWala = new ArrayList<BettingInfo>();
	
	private boolean isBettingLocked = false;
	
	public void addMeron(BettingInfo bettingInfo) {
		String odds = bettingInfo.getOdds();
		String userName = bettingInfo.getUserName();
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			if(!isUserBet(userName, walaInfos)) {
				meronInfos.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			if(!isUserBet(userName, nineTenWala)) {
				nineTenMeron.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			if(!isUserBet(userName, eightTenWala)) {
				eightTenMeron.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			if(!isUserBet(userName, tenNineWala)) {
				tenNineMeron.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			if(!isUserBet(userName, tenEightWala)) {
				tenEightMeron.add(bettingInfo);
			}
		}
	}
	
	public void removeLastBetMeron(String odds, int index) {
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			meronInfos.remove(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			nineTenMeron.remove(index);
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			eightTenMeron.remove(index);
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			tenNineMeron.remove(index);
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			tenEightMeron.remove(index);
		}
	}
	
	public BettingInfo getBettingInfoMeron(String odds, int index) {
		BettingInfo info = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			info = (BettingInfo) meronInfos.get(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			info = (BettingInfo) nineTenMeron.get(index);
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			info = (BettingInfo) eightTenMeron.get(index);
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			info = (BettingInfo) tenNineMeron.get(index);
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			info = (BettingInfo) tenEightMeron.get(index);
		}
		return info;
	}
	
	public List<BettingInfo> getMeron(String odds) {
		List<BettingInfo> infos = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			infos = meronInfos;
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			infos = nineTenMeron;
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			infos = eightTenMeron;
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			infos = tenNineMeron;
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			infos = tenEightMeron;
		}
		return infos;
	}
	
	public void addWala(BettingInfo bettingInfo) {
		String odds = bettingInfo.getOdds();
		String userName = bettingInfo.getUserName();
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			if(!isUserBet(userName, meronInfos)) {
				walaInfos.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			if(!isUserBet(userName, nineTenMeron)) {
				nineTenWala.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			if(!isUserBet(userName, eightTenMeron)) {
				eightTenWala.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			if(!isUserBet(userName, tenNineMeron)) {
				tenNineWala.add(bettingInfo);
			}
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			if(!isUserBet(userName, tenEightMeron)) {
				tenEightWala.add(bettingInfo);
			}
		}
	}
	
	public void removeLastBetWala(String odds, int index) {
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			walaInfos.remove(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			nineTenWala.remove(index);
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			eightTenWala.remove(index);
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			tenNineWala.remove(index);
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			tenEightWala.remove(index);
		}
	}
	
	public BettingInfo getBettingInfoWala(String odds, int index) {
		BettingInfo info = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			info = (BettingInfo) walaInfos.get(index);
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			info = (BettingInfo) nineTenWala.get(index);
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			info = (BettingInfo) eightTenWala.get(index);
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			info = (BettingInfo) tenNineWala.get(index);
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			info = (BettingInfo) tenEightWala.get(index);
		}
		return info;
	}
	
	public List<BettingInfo> getWala(String odds) {
		List<BettingInfo> infos = null;
		if(odds.equals(OddsType.TEN_TEN.getDescription())) {
			infos = walaInfos;
		} else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
			infos = nineTenWala;
		} else if(odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			infos = eightTenWala;
		} else if(odds.equals(OddsType.TEN_NINE.getDescription())) {
			infos = tenNineWala;
		} else if(odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			infos = tenEightWala;
		}
		return infos;
	}
	
	public double getTotalBetBy(String odds, String side) {
		double totalBet = 0.0;
		String meron = BettingService.MERON;
		String wala = BettingService.WALA;
		
		if(side.equals(meron) && odds.equals(OddsType.TEN_TEN.getDescription())) {
			for(BettingInfo info : meronInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(meron) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			for(BettingInfo info : nineTenMeron) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(meron) && odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			for(BettingInfo info : eightTenMeron) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(meron) && odds.equals(OddsType.TEN_NINE.getDescription())) {
			for(BettingInfo info : tenNineMeron) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(meron) && odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			for(BettingInfo info : tenEightMeron) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_TEN.getDescription())) {
			for(BettingInfo info : walaInfos) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(wala) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			for(BettingInfo info : nineTenWala) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(wala) && odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			for(BettingInfo info : eightTenWala) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_NINE.getDescription())) {
			for(BettingInfo info : tenNineWala) {
				totalBet = totalBet + info.getBetAmount();
			}
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			for(BettingInfo info : tenEightWala) {
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
		eightTenMeron.clear();
		tenNineMeron.clear();
		tenEightMeron.clear();
		
		walaInfos.clear();
		nineTenWala.clear();
		eightTenWala.clear();
		tenNineWala.clear();
		tenEightWala.clear();
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
