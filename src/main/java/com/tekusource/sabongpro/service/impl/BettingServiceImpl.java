package com.tekusource.sabongpro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.BetHistoryDao;
import com.tekusource.sabongpro.model.BetHistory;
import com.tekusource.sabongpro.model.BettingInfo;
import com.tekusource.sabongpro.model.OddsType;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.BettingService;
import com.tekusource.sabongpro.service.UserService;

@Service("bettingService")
@Scope("singleton")
@Transactional
public class BettingServiceImpl implements BettingService {
	
	@Autowired
	private BetHistoryDao betHistoryDao;
	
	@Autowired
	private UserService userService;

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
		String userName = bettingInfo.getUser().getUserName();
		
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
		String userName = bettingInfo.getUser().getUserName();
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
			totalBet = computeTotalBet(meronInfos);
		} else if(side.equals(meron) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			totalBet = computeTotalBet(nineTenMeron);
		} else if(side.equals(meron) && odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			totalBet = computeTotalBet(eightTenMeron);
		} else if(side.equals(meron) && odds.equals(OddsType.TEN_NINE.getDescription())) {
			totalBet = computeTotalBet(tenNineMeron);
		} else if(side.equals(meron) && odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			totalBet = computeTotalBet(tenEightMeron);
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_TEN.getDescription())) {
			totalBet = computeTotalBet(walaInfos);
		} else if(side.equals(wala) && odds.equals(OddsType.NINE_TEN.getDescription())) {
			totalBet = computeTotalBet(nineTenWala);
		} else if(side.equals(wala) && odds.equals(OddsType.EIGHT_TEN.getDescription())) {
			totalBet = computeTotalBet(eightTenWala);
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_NINE.getDescription())) {
			totalBet = computeTotalBet(tenNineWala);
		} else if(side.equals(wala) && odds.equals(OddsType.TEN_EIGHT.getDescription())) {
			totalBet = computeTotalBet(tenEightWala);
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
	
	public void declareWinner(String winner) {
		insertBetHistory(meronInfos, winner);
		insertBetHistory(nineTenMeron, winner);
		insertBetHistory(eightTenMeron, winner);
		insertBetHistory(tenNineMeron, winner);
		insertBetHistory(tenEightMeron, winner);

		insertBetHistory(walaInfos, winner);
		insertBetHistory(nineTenWala, winner);
		insertBetHistory(eightTenWala, winner);
		insertBetHistory(tenNineWala, winner);
		insertBetHistory(tenEightWala, winner);
		
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
				if(info.getUser().getUserName().equals(userName)) {
					isBet = true;
				}
			}
		}
		return isBet;
	}
	
	private void insertBetHistory(List<BettingInfo> infos, String winner) {
		for(BettingInfo info : infos) {
			User user = (User) userService.getUserByUserName(info.getUser().getUserName());
			String side = info.getSide();
			BetHistory betHistory = new BetHistory();
			betHistory.setResult(winner);
			betHistory.setSide(side);
			betHistory.setUser(user);
			String odds = info.getOdds();
			double virtualPoints = 0;
			if(odds.equals(OddsType.TEN_TEN.getDescription())) {
				double betAmount = info.getBetAmount();
				virtualPoints = computeVirtualPoints(winner, side, user.getVirtualPoints(), betAmount, betAmount);
			} 
			else if(odds.equals(OddsType.NINE_TEN.getDescription())) {
				double discountedAmount = (info.getBetAmount() * .9);
				double normalAmount = info.getBetAmount();
				if(side.equals("MERON")) {
					virtualPoints = computeVirtualPoints(winner, side, user.getVirtualPoints(), discountedAmount, normalAmount);
				} else {
					virtualPoints = computeVirtualPoints(winner, side, user.getVirtualPoints(), normalAmount, discountedAmount);
				}
			}
			user.setVirtualPoints(virtualPoints);
			userService.update(user);
			betHistoryDao.create(betHistory);
		}
	}
	
	private double computeTotalBet(List<BettingInfo> infos) {
		double totalBet = 0;
		for(BettingInfo info : infos) {
			totalBet = totalBet + info.getBetAmount();
		}
		return totalBet;
	}
	
	private double computeVirtualPoints(String winner, String side, double userVirtualPoints, 
										double winnerAmount, double losserAmount) {
		double virtualPoints = 0;
		if(winner.equals(side)) {
			virtualPoints = userVirtualPoints + winnerAmount;
		} else {
			virtualPoints = userVirtualPoints - losserAmount;
		}
		return virtualPoints;
	}
}
