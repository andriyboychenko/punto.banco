package com.addit.stats;

import java.text.DecimalFormat;

public class Statistics {
	
	private int totalExpriments;
	private int playerVinnerCount;
	private int bankerVinnerCount;
	private int tieVinnerCount;
	
	public Statistics(int totalExpriments) {
		this.totalExpriments = totalExpriments;
		this.playerVinnerCount = 0;
		this.bankerVinnerCount = 0;
		this.tieVinnerCount = 0;
	}

	
	public int getTotalExpriments() {
		return totalExpriments;
	}


	public int getPlayerVinnerCount() {
		return playerVinnerCount;
	}

	public int getBankerVinnerCount() {
		return bankerVinnerCount;
	}

	public int getTieVinnerCount() {
		return tieVinnerCount;
	}

	public void setTotalExpriments(int totalExpriments) {
		this.totalExpriments = totalExpriments;
	}
	
	public void incrementPlayerVinnerCount() {
		this.playerVinnerCount ++;
	}

	public void incrementBankerVinnerCount() {
		this.bankerVinnerCount ++;
	}

	public void incrementTieVinnerCount() {
		this.tieVinnerCount ++;
	}
	
	public void showResults(){
				
		System.out.println("banker wins:" + new DecimalFormat("##.#").format( ((double)bankerVinnerCount/(double)totalExpriments)*100 ) + "%");
		System.out.println("player wins:" + new DecimalFormat("##.#").format( ((double)playerVinnerCount/(double)totalExpriments)*100 ) + "%");
		System.out.println("tie        :" + new DecimalFormat("##.#").format( ((double)tieVinnerCount/(double)totalExpriments)*100 ) + "%");
		
	}
	
}
