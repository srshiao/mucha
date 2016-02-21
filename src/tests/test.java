package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hearthstone.Minion;
import hearthstone.Spell;
import hearthstone.Player;

public class test {

	@Test
	public void test1() {
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.addToDeck(new Minion(0, 2, 2, "Wisp", ""));
		p2.addToDeck(new Minion(0, 3, 3, "Wisp", ""));
		p1.draw();
		p2.draw();
		p1.summon((Minion)p1.hand.get(0));
		p2.summon((Minion)p2.hand.get(0));

		p1.attack(p1.board.get(0), p2, 0);	
		
		assertEquals(0, p1.board.size());
	}
	
	@Test
	public void test2() {
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.addToDeck(new Minion(0, 2, 2, "Wisp", ""));
		p2.addToDeck(new Minion(0, 3, 3, "Wisp", ""));
		p1.draw();
		p2.draw();
		p1.summon((Minion)p1.hand.get(0));
		p2.summon((Minion)p2.hand.get(0));
		p1.addToDeck(new Spell(0, "Snipe", ""));
		p1.draw();
		
		p1.attack(p1.board.get(0), p2, 0);	
		p1.cast((Spell)p1.hand.get(0), p2);
		
		//System.out.println(p1.toString());
		//System.out.println(p2.toString());
		assertEquals(0, p2.board.size());
	}
	
	@Test
	public void test3() {
		Player p1 = new Player();
		Player p2 = new Player();
		p1.addToDeck(new Minion(0, 10, 10, "Wisp", ""));
		p2.addToDeck(new Minion(0, 6, 6, "Wisp", ""));
		p1.draw();
		p2.draw();
		p1.summon((Minion)p1.hand.get(0));
		p2.summon((Minion)p2.hand.get(0));
		p1.attack(p1.board.get(0), p2, 0);
		
		System.out.println(p1.toString());
		
		p1.addToDeck(new Spell(2, "Heal", ""));
		p1.draw();
		
		p1.setMana(10);
		p1.cast((Spell)p1.hand.get(0), p2);
		System.out.println(p1.toString());
	}
	
	@Test
	public void test4() {
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.addToDeck(new Spell(2, "Summon", ""));
		p1.draw();
		
		p1.setMana(10);
		p1.cast((Spell)p1.hand.get(0), p2);
		
		System.out.println(p1.toString());
	}
}
