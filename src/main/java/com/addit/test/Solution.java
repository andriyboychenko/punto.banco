package com.addit.test;

import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.addit.constants.Constants;
import com.addit.stats.Statistics;
import com.addit.test.entities.Card;
import com.addit.test.entities.HandValue;
import com.addit.test.entities.ThirdCard;

public class Solution {
	
	private static KnowledgeBase kbase;
	
	
	public static void main(String [] args) {
		
		setupDrools();
		
		Statistics stats = new Statistics(Constants.TOTAL_EXPERIMENTS);
		
		for (int i = 0; i < stats.getTotalExpriments(); i++) {
		
			// Dealer has 8 decks
			Dealer dealer = new Dealer(Constants.TOTAL_DEKS);
			
			List<Card> playerHand = new ArrayList<Card>();
			List<Card> bankerHand = new ArrayList<Card>();
			List<Card> tailHand = new ArrayList<Card>();
			
			playerHand.add(dealer.dealNextCard());
			bankerHand.add(dealer.dealNextCard());
			tailHand.add(dealer.dealNextCard());
			playerHand.add(dealer.dealNextCard());
			bankerHand.add(dealer.dealNextCard());
			tailHand.add(dealer.dealNextCard());
			
			
			HandValue phand =  new HandValue(dealer.calculateHandValue(playerHand), true);
			HandValue bhand =  new HandValue(dealer.calculateHandValue(bankerHand), false);
			int playerFinalScore = phand.getHandValue();
			int bankerFinalScore = bhand.getHandValue();
			
			/*
			 * Decision of third hand 
			 */
			StatefulKnowledgeSession ksession = null;
			try {
				
			
				ksession = kbase.newStatefulKnowledgeSession();
				ksession.insert(dealer);
				ksession.insert(phand);
				ksession.insert(bhand);
				ksession.fireAllRules();
			
				for (Object result : ksession.getObjects()) { //always return 1 ThirdCard object
					if (result instanceof ThirdCard) { 
						int pThird = ((ThirdCard)result).getPlayerThirdCard();
						int bThird = ((ThirdCard)result).getBankerThirdCard();
						
						if (pThird != -1){
							playerFinalScore = dealer.calculateHandValueBasedOnWeigth(playerFinalScore, pThird);
						}
						if (bThird != -1){
							bankerFinalScore = dealer.calculateHandValueBasedOnWeigth(bankerFinalScore, bThird);
						}
						
						break;
					}
				}
				
			} catch (Exception e) {
				System.out.println("Was unable to obtain third card due to : " + e.getMessage());
			} finally {
				if(ksession != null) {
					ksession.dispose();
				}
			}
			
			
			int diffWithNinePlayer = 9-playerFinalScore;
			int diffWithNineBanker = 9-bankerFinalScore;
						
			if (diffWithNinePlayer < diffWithNineBanker) {
				stats.incrementPlayerVinnerCount();
			} else if (diffWithNinePlayer > diffWithNineBanker) {
				stats.incrementBankerVinnerCount();
			} else {
				stats.incrementTieVinnerCount();
			}
			
		}
		
		stats.showResults();
		
		
	}
	
	private static void setupDrools(){
		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("com/addit/rules/ThirdCardRule.drl"), ResourceType.DRL);
		
		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	}
}
