package com.addit.test.entities;

public class Card {

	private char cardFace;
	private int weigth;
	private int usedCounter;
	
	public Card(char cardFace, int weigth, int usedCounter) {

		this.cardFace = cardFace;
		this.weigth = weigth;
		this.usedCounter = usedCounter;
	}

	public char getCardFace() {
		return cardFace;
	}

	public void setCardFace(char cardFace) {
		this.cardFace = cardFace;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}

	public int getUsedCounter() {
		return usedCounter;
	}

	public void incrementUsedCounter() {
		this.usedCounter ++;
	}
	
	
	
}
