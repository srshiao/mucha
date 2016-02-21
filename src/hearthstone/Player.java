package hearthstone;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
	String name;
	String heroPower;
	//enum heroPower { HEAL, SNIPE, DRAW, SUMMON }
	int mana;
	int health;
	int fatigue;
	public ArrayList<Card> hand;
	private ArrayList<Card> deck;
	public ArrayList<Minion> board;
	
	//constructor with 2 params, initializes deck to have 30 random cards
	//20 random minions, 10 random spells, and then draws 4 into hand
	public Player(String name, String heroPower) {
		mana = 0;
		health = 15;
		this.name = name;
		this.heroPower = heroPower;
		
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		board = new ArrayList<Minion>();
		//20 random minions
		for(int pos = 0; pos < 20; pos++) {
			deck.add(newRandomMinion());
		}
		//10 random spells
		for(int pos = 0; pos < 10; pos++) {
			deck.add(newRandomSpell());
		}
		//shuffles
		shuffle();
		//draws 4 cards to start
		for(int pos = 0; pos < 4; pos++) {
			draw();
		}
		
		fatigue = 1;
	}
	
	//default constructor, initializes deck hand and board to be empty
	public Player() {
		mana = 0;
		health = 30;
		this.name = "Hero";
		this.heroPower = "None";
		
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		board=  new ArrayList<Minion>();
		fatigue = 1;
	}
	
	//returns the player's name
	public String getName() {
		return name;
	}
	
	//returns the player's health
	public int getHealth() {
		return health;
	}
	
	//takes a card from deck and puts it in hand
	//if no cards left, takes damage
	public void draw() {
		if(deck.size() > 0 && hand.size() < 7) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
		else if(deck.size() > 0 && hand.size() >= 7) {
			System.out.println("Too many cards.. discarding " + deck.get(0).toString());
			deck.remove(0);
		}
		else 
			health -= fatigue++;
	}
	
	//adds the card to the deck
	public void addToDeck(Card c) {
		deck.add(c);
	}
	
	//shuffles the deck
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	//returns a String version of the player, including hand, deck, and board
	public String toString() {
		String result = "Name: " + name + "\nHand:";	
		for(int pos = 0; pos < hand.size(); pos++) {
			result += ("\n" + hand.get(pos).toString() + "\n");
		}
		
		result += ("\nDeck: ");
		for(int pos = 0; pos < deck.size(); pos++) {
			result += ("\n" + deck.get(pos).toString() + "\n");
		}
		
		result += ("\nCurrent Board: ");
		for(int pos = 0; pos < board.size(); pos++) {
			result += ("\n" + board.get(pos).toString() + "\n");
		}
		
		return result + "\n";
	}
	
	//checks if the attack is valid
	public boolean validAttack(Minion m1, Minion m2) {
		if(!board.contains(m1) || board.contains(m2) || m1.getAttack() == 0)
			return false;
		return true;
	}
	
	//attacks the enemy minion
	public void attack(Minion m1, Player other, int pos) {
		if(validAttack(m1, other.board.get(pos))) {
			if(m1.damage(other.board.get(pos).getAttack()))
				board.remove(m1);
			if(other.board.get(pos).damage(m1.getAttack()))
				other.board.remove(pos);
		}
		else if(pos == other.board.size()){
			other.damage(m1.getAttack());
		}
	}
	
	//overloaded method, attacks a player instead
	public void attack(Minion m, Player p) {
		p.damage(m.getAttack());
	}
	
	//damages the player
	public void damage(int damage) {
		health -= damage;
		if(health < 1)
			lose();
	}
	
	//lose
	public void lose() {
		System.out.println(name + " loses!");
	}
	
	public boolean lost() {
		if(health < 1)
			return true;
		return false;
	}
	//checks if the card is valid to play
	public boolean validCard(Card c) { 
		if(!hand.contains(c) || mana < c.cost)
			return false;
		return true;
	}
	
	//summons the minion and takes away the mana
	public boolean summon(Minion m) {
		if(validCard(m) && board.size() < 6) {
			board.add(m);
			if(hand.contains(m))
				hand.remove(m);
			mana -= m.cost;
			return true;
		}
		return false;
	}
	
	//casts the spell
	public boolean cast(Spell s, Player p) {
		if(validCard(s)) {
			s.cast(this, p);
			hand.remove(s);
			mana -= s.cost;
			return true;
		}
		return false;
	}
	
	//returns a string of the player's hand, easily readable for playing
	public String showHand() {
		String result = "";
		for(Card c : hand) {
			if (c instanceof Minion)
				result += c.getCost() + " cost " + ((Minion) c).getAttack() + "/" +
						((Minion) c).getHealth() + " " + c.getName() + ", ";
			else if (c instanceof Spell)
				result += c.getCost() + " cost " + c.getName() + ", ";
		}
		result = result.substring(0, result.length() - 2);
		return result;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	//returns a new vanilla minion with attack 0-10 and health 1-10, and an according cost
	private Minion newRandomMinion(){
		return new Minion();
	}
	
	//returns a new random spell
	private Spell newRandomSpell(){
		return new Spell();
	}

}
