package com.addit.test.entities;

public class ThirdCard {

	private int playerThirdCard;
	private int bankerThirdCard;
	
	public ThirdCard(int payerThirdCard, int bankerThirdCard) {
		this.playerThirdCard = payerThirdCard;
		this.bankerThirdCard = bankerThirdCard;
	}

	public int getPlayerThirdCard() {
		return playerThirdCard;
	}

	public void setPlayerThirdCard(int playerThirdCard) {
		this.playerThirdCard = playerThirdCard;
	}

	public int getBankerThirdCard() {
		return bankerThirdCard;
	}

	public void setBankerThirdCard(int bankerThirdCard) {
		this.bankerThirdCard = bankerThirdCard;
	}
	
	
	
}
