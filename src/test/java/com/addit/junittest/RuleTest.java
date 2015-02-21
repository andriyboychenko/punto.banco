package com.addit.junittest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.addit.constants.Constants;
import com.addit.test.Dealer;
import com.addit.test.entities.HandValue;
import com.addit.test.entities.ThirdCard;


public class RuleTest {

	private KnowledgeBase kbase;
	
	@Before 
	public void initialize() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("com/addit/rules/ThirdCardRule.drl"), ResourceType.DRL);
		
		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	}
	
	@Test
	public void testThirdCardRule() {
		
				
		HandValue phand1 =  new HandValue(9, true);
		HandValue bhand1 =  new HandValue(0, false);
		ThirdCard ruleREsult1 = runRule(phand1,bhand1);
		assertEquals (ruleREsult1.getPlayerThirdCard(), -1);
		assertEquals (ruleREsult1.getBankerThirdCard(), -1);
		
		HandValue phand2 =  new HandValue(0, true);
		HandValue bhand2 =  new HandValue(8, false);
		ThirdCard ruleREsult2 = runRule(phand2,bhand2);
		assertEquals (ruleREsult2.getPlayerThirdCard(), -1);
		assertEquals (ruleREsult2.getBankerThirdCard(), -1);
		
		HandValue phand3 =  new HandValue(9, true);
		HandValue bhand3 =  new HandValue(9, false);
		ThirdCard ruleREsult3 = runRule(phand3,bhand3);
		assertEquals (ruleREsult3.getPlayerThirdCard(), -1);
		assertEquals (ruleREsult3.getBankerThirdCard(), -1);
		
		
		
		HandValue phand4 =  new HandValue(4, true);
		HandValue bhand4 =  new HandValue(7, false);
		ThirdCard ruleREsult4 = runRule(phand4,bhand4);
		int caompared1 = ((Integer)ruleREsult4.getPlayerThirdCard()).compareTo(-1);
		assertTrue("greater", caompared1 > 0);
		assertEquals (ruleREsult4.getBankerThirdCard(), -1);
		
		
		
		HandValue phand5 =  new HandValue(6, true);
		HandValue bhand5 =  new HandValue(1, false);
		ThirdCard ruleREsult5 = runRule(phand5,bhand5);
		int caompared2 = ((Integer)ruleREsult5.getBankerThirdCard()).compareTo(-1);
		assertTrue("greater", caompared2 > 0);
		assertEquals (ruleREsult5.getPlayerThirdCard(), -1);
		
		
		/* This test is impossible
		HandValue phand6 =  new HandValue(4, true);
		HandValue bhand6 =  new HandValue(6, false);
		ThirdCard ruleREsult6 = runRule(phand6,bhand6);
		int caompared3 = ((Integer)ruleREsult6.getPlayerThirdCard()).compareTo(-1);
		assertTrue("greater", caompared3 > 0);
		int caompared4 = ((Integer)ruleREsult6.getBankerThirdCard()).compareTo(-1);
		assertTrue("greater", caompared4 > 0);
 		*/
	}
	
	
	
	private ThirdCard runRule(HandValue phand, HandValue bhand){
		
		Dealer dealer = new Dealer(Constants.TOTAL_DEKS);
		
		StatefulKnowledgeSession ksession = null;
		
		ksession = kbase.newStatefulKnowledgeSession();
		ksession.insert(dealer);
		ksession.insert(phand);
		ksession.insert(bhand);
		ksession.fireAllRules();
	
		ThirdCard tcard = null;
		
		for (Object result : ksession.getObjects()) {
			if (result instanceof ThirdCard) {
				tcard = (ThirdCard) result;
			}
		}
		
		ksession.dispose();
		
		return tcard;
	}
}
