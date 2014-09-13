package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekusource.sabongpro.model.BettingInfo;
import com.tekusource.sabongpro.model.OddsType;
import com.tekusource.sabongpro.service.BettingService;

@Controller
@RequestMapping( value = "/bettingServices" )
public class BettingDataController {
	
	@Autowired
	private BettingService bettingService;
	
	private static List<String> oddsList;
	
	static {
		oddsList = new ArrayList<String>();
		oddsList.add(OddsType.TEN_TEN.getDescription());
		oddsList.add(OddsType.NINE_TEN.getDescription());
		oddsList.add(OddsType.EIGHT_TEN.getDescription());
		oddsList.add(OddsType.TEN_NINE.getDescription());
		oddsList.add(OddsType.TEN_EIGHT.getDescription());
	}

	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public boolean betting(@RequestParam("userName") String userName, @RequestParam("side") String side, 
						   @RequestParam("odds") String odds, @RequestParam("betAmount") double betAmount) {
		boolean isLocked = bettingService.isBettingLocked();
		if(!side.isEmpty() && side.length() > 0) {
			BettingInfo info = new BettingInfo();
			info.setUserName(userName);
			info.setSide(side);
			info.setOdds(odds);
			info.setBetAmount(betAmount);
			if(!isLocked) {
				if(side.equals(BettingService.MERON)) {
					bettingService.addMeron(info);
				} else {
					bettingService.addWala(info);
				}
			}
		}
		return isLocked;
	}
	
	@RequestMapping(value="/closeBetting", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> closeBetting() {
		for(String odds : oddsList) {
			boolean isNotEqualAmount = true;
			while(isNotEqualAmount) {
				double meronTotal = bettingService.getTotalBetBy(odds, BettingService.MERON);
				double walaTotal = bettingService.getTotalBetBy(odds, BettingService.WALA);
				int retVal = Double.compare(meronTotal, walaTotal);
				if(retVal > 0) {
					int index = bettingService.getMeron(odds).size() - 1;
					if(index >= 0) {
						BettingInfo uBettingInfo = bettingService.getBettingInfoMeron(odds, index);
						bettingService.removeLastBetMeron(odds, index);
						meronTotal = bettingService.getTotalBetBy(odds, BettingService.MERON);
						int newRetVal = Double.compare(meronTotal, walaTotal);
						if(newRetVal < 0) {
							double fitAmount = walaTotal - meronTotal;
							uBettingInfo.setBetAmount(fitAmount);
							bettingService.addMeron(uBettingInfo);
						}
					}
				} else if(retVal < 0) {
					int index = bettingService.getWala(odds).size() - 1;
					if(index >= 0) {
						BettingInfo uBettingInfo = bettingService.getBettingInfoWala(odds, index);
						bettingService.removeLastBetWala(odds, index);
						walaTotal = bettingService.getTotalBetBy(odds, BettingService.WALA);
						int newRetVal = Double.compare(walaTotal, meronTotal);
						if(newRetVal < 0) {
							double fitAmount = meronTotal - walaTotal;
							uBettingInfo.setBetAmount(fitAmount);
							bettingService.addWala(uBettingInfo);
						}
					}
				} else {
					isNotEqualAmount = false;
				}
			}
		}
		bettingService.lockBetting();
		return Collections.singletonMap("isBettingLocked", bettingService.isBettingLocked());
	}
	
	@RequestMapping(value="/betAmounts", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> betAmounts() {
		double nineTenTotal = bettingService.getTotalBetBy(OddsType.NINE_TEN.getDescription(), BettingService.MERON) * .9;
		double eightTenTotal = bettingService.getTotalBetBy(OddsType.EIGHT_TEN.getDescription(), BettingService.MERON) * .8;
		double tenNineTotal = bettingService.getTotalBetBy(OddsType.TEN_NINE.getDescription(), BettingService.WALA) * .9;
		double tenEightTotal = bettingService.getTotalBetBy(OddsType.TEN_EIGHT.getDescription(), BettingService.WALA) * .8;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("meronTotal", bettingService.getTotalBetBy(OddsType.TEN_TEN.getDescription(), BettingService.MERON));
		map.put("nineTenMeronTotal", nineTenTotal);
		map.put("eightTenMeronTotal", eightTenTotal);
		map.put("tenNineMeronTotal", bettingService.getTotalBetBy(OddsType.TEN_NINE.getDescription(), BettingService.MERON));
		map.put("tenEightMeronTotal", bettingService.getTotalBetBy(OddsType.TEN_EIGHT.getDescription(), BettingService.MERON));
		
		map.put("walaTotal", bettingService.getTotalBetBy(OddsType.TEN_TEN.getDescription(), BettingService.WALA));
		map.put("nineTenWalaTotal", bettingService.getTotalBetBy(OddsType.NINE_TEN.getDescription(), BettingService.WALA));
		map.put("eightTenWalaTotal", bettingService.getTotalBetBy(OddsType.EIGHT_TEN.getDescription(), BettingService.WALA));
		map.put("tenNineWalaTotal", tenNineTotal);
		map.put("tenEightWalaTotal", tenEightTotal);
		
		map.put("isLocked", bettingService.isBettingLocked());
		return map;
	}
	
	@RequestMapping(value="/declareWinner", method=RequestMethod.GET)
	@ResponseBody
	public String declareWinner() {
		bettingService.declareWinner();
		return "";
	}
}
