package com.addit.junittest;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.addit.constants.Constants;
import com.addit.test.Dealer;
import com.addit.test.entities.Card;

public class DealerTest {


	private static Character sortedCards [] = new Character []{'A','K','Q','J','T','9','8','7','6','5','4','3','2'};
	
	
	@Test
	public void testDealer() {
 
		Dealer dealer = new Dealer(Constants.TOTAL_DEKS);
		Assert.assertEquals(0,dealer.calculateHandValueBasedOnWeigth(0,0));
		Assert.assertEquals(9,dealer.calculateHandValueBasedOnWeigth(0,9));
		Assert.assertEquals(4,dealer.calculateHandValueBasedOnWeigth(9,5));
				
		Assert.assertEquals(0,dealer.calculateHandValue(
				Arrays.asList(
							new Card('K', dealer.getCardWeight('K'), 0),new Card('Q', dealer.getCardWeight('Q'), 0))
						)
				);
		
		Assert.assertEquals(9,dealer.calculateHandValue(
				Arrays.asList(
							new Card('T', dealer.getCardWeight('T'), 0),new Card('9', dealer.getCardWeight('9'), 0))
						)
				);
		
		Assert.assertEquals(4,dealer.calculateHandValue(
				Arrays.asList(
							new Card('9', dealer.getCardWeight('9'), 0),new Card('5', dealer.getCardWeight('5'), 0))
						)
				);
		
		List<Character> charList = Arrays.asList(sortedCards);
		boolean cardExits = charList.contains(dealer.dealNextCard().getCardFace());
		Assert.assertTrue(cardExits);
 
	}
	
}