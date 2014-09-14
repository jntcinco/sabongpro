package com.tekusource.sabongpro.model;

public class BettingInfo {

	private String userName;
	private String side;
	private String odds;
	private double betAmount;
	private User user;
	
//	public String getUserName() {
//		return userName;
//	}
	
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
	
	public String getOdds() {
		return odds;
	}

	public void setOdds(String odds) {
		this.odds = odds;
	}
	
	public double getBetAmount() {
		return betAmount;
	}
	
	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}
}
