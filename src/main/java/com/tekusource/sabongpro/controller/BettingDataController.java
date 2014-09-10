package com.tekusource.sabongpro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekusource.sabongpro.model.BettingInfo;
import com.tekusource.sabongpro.service.BettingService;

@Controller
@RequestMapping( value = "/bettingServices" )
public class BettingDataController {
	
	@Autowired
	private BettingService bettingService;

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
				if(side.equals("meron")) {
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
		boolean isNotEqualAmount = true;
		
		while(isNotEqualAmount) {
			double meronTotal = bettingService.getTotalBetBy("meron");
			double walaTotal = bettingService.getTotalBetBy("wala");
			int retVal = Double.compare(meronTotal, walaTotal);
			if(retVal > 0) {
				int index = bettingService.getMeron().size() - 1;
				if(index >= 0) {
					BettingInfo uBettingInfo = bettingService.getBettingInfoMeron(index);
					bettingService.removeLastBetMeron(index);
					meronTotal = bettingService.getTotalBetBy("meron");
					int newRetVal = Double.compare(meronTotal, walaTotal);
					if(newRetVal < 0) {
						double fitAmount = walaTotal - meronTotal;
						uBettingInfo.setBetAmount(fitAmount);
						bettingService.addMeron(uBettingInfo);
					}
				}
			} else if(retVal < 0) {
				int index = bettingService.getWala().size() - 1;
				if(index >= 0) {
					BettingInfo uBettingInfo = bettingService.getBettingInfoWala(index);
					bettingService.removeLastBetWala(index);
					walaTotal = bettingService.getTotalBetBy("wala");
					int newRetVal = Double.compare(walaTotal, meronTotal);
					if(newRetVal < 0) {
						double fitAmount = meronTotal - walaTotal;
						uBettingInfo.setBetAmount(fitAmount);
						bettingService.addWala(uBettingInfo);
					}
				}
			} else {
				isNotEqualAmount = false;
				bettingService.lockBetting();
			}
		}
		System.out.println("Meron size: "+bettingService.getMeron().size()+" Wala size: "+bettingService.getWala().size());
		return Collections.singletonMap("isBettingLocked", bettingService.isBettingLocked());
	}
	
	@RequestMapping(value="/betAmounts", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> betAmounts() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("meronTotal", bettingService.getTotalBetBy("meron"));
		map.put("walaTotal", bettingService.getTotalBetBy("wala"));
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
