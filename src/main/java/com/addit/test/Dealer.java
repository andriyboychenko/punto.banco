package com.addit.test;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.addit.test.entities.Card;

public class Dealer {

	private int CARD_LIMIT = 52;
	private int totalAnalysedCards = 0;
	private char sortedCards [] = new char[]{'A','K','Q','J','T','9','8','7','6','5','4','3','2'};
	private HashMap<Character,Card> cardsInGame;
	private HashMap<Character,Integer> cardsWeight;
	
	public Dealer(int decks){
		CARD_LIMIT = 52 * decks;
		cardsInGame = new HashMap<Character,Card> ();
		cardsWeight = new HashMap<Character, Integer>();
		cardsWeight.put('A', 1);
		cardsWeight.put('K', 0);
		cardsWeight.put('Q', 0);
		cardsWeight.put('J', 0);
		cardsWeight.put('T', 0);
		cardsWeight.put('9', 9);
		cardsWeight.put('8', 8);
		cardsWeight.put('7', 7);
		cardsWeight.put('6', 6);
		cardsWeight.put('5', 5);
		cardsWeight.put('4', 4);
		cardsWeight.put('3', 3);
		cardsWeight.put('2', 2);
	}
	
	/**
	 * Deals random card from the deck
	 * @return card from the deck
	 */
	public Card dealNextCard(){
		
		char card;
		
		do {
			
			card = sortedCards[randomCard(0, sortedCards.length-1)];
			
			if (isCardExistsInDeck( (CARD_LIMIT/sortedCards.length) , card )) {
				if (cardsInGame.get(card) == null){
					cardsInGame.put(card, new Card(card, cardsWeight.get(card), 1));
				} else {
					cardsInGame.get(card).incrementUsedCounter();
				}
				
				totalAnalysedCards ++;
				
				return cardsInGame.get(card);
			}
			
			
		} while( totalAnalysedCards < CARD_LIMIT );
		
		return null;
		
	}
	
	/**
	 * Returns the value of the hand
	 * @param playerHand cards on hand
	 * @return sum of player's hand
	 */
	public int calculateHandValue(List<Card> playerHand){
		
		int sum = 0;
		
		if (playerHand.size() == 2) {
			sum = calculateHandValueBasedOnWeigth(
					playerHand.get(0).getWeigth(),
					playerHand.get(1).getWeigth());
		}
		
		return sum;
	}
	
	public int calculateHandValueBasedOnWeigth(int w1, int w2){
		return (w1 + w2) >= 10 ? (w1 + w2) - 10 : (w1 + w2);
		
	}
	
	/**
	 * Generates random index of the card 
	 * @param min 0
	 * @param max 13
	 * @return random number
	 */
	private int randomCard(int min, int max) {
		
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	
	}
	
	/**
	 * 
	 * @param limit depends of number of decks. if we have 1 deck, we should
	 * use 4 as limit.
	 * @param card that we want to extract
	 * @return true or false
	 */
	private boolean isCardExistsInDeck(int limit, char card){

		if (cardsInGame.get(card) == null || cardsInGame.get(card).getUsedCounter() < limit) {
			return true;
		} else {
			return false;
		}
				
	}
	
	/**
	 * Used just for tests
	 * @param cardFace character
	 * @return card face weight
	 */
	public int getCardWeight(char cardFace){
		return cardsWeight.get(cardFace);
	}
	
	
}
