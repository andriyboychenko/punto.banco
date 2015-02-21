package com.addit.test.entities;

public class HandValue {

	private int handValue;
	private boolean playerHand;
	private boolean analysed;
	
	public HandValue(int handValue, boolean playerHand) {
		this.handValue = handValue;
		this.playerHand = playerHand;
		this.analysed = false;
	}

	public int getHandValue() {
		return handValue;
	}

	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}

	public boolean isPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(boolean playerHand) {
		this.playerHand = playerHand;
	}

	public boolean isAnalysed() {
		return analysed;
	}

	public void setAnalysed(boolean analysed) {
		this.analysed = analysed;
	}
	
	
	
}
