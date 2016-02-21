package hearthstone;

public class Minion extends Card{

	private int maxHealth;
	private int health;
	private int attack;

	//constructor with 5 params
	public Minion(int cost, int attack, int health, String name, String description) {
		super(cost, name, description);
		this.attack = attack;
		this.health = health;
		maxHealth = health;
	}

	//copy constructor
	public Minion(Minion other) {
		super(other.cost, other.name, other.description);
		this.attack = other.attack;
		this.health = other.health;
		maxHealth = health;
	}
	
	//default constructor, generates a random minion with a random name
	public Minion() {
		super();
		health = (int)(Math.random() * 10) + 1;
		attack = (int)(Math.random() * 11);
		
		//sets cost based on the health/attack values
		switch(health + attack) {
		case 0:
		case 1:
		case 2:
		case 3:
			cost = 1;
			break;
		case 4:
		case 5:
			cost = 2;
			break;
		case 6:
		case 7:
			cost = 3;
			break;
		case 8:
		case 9: 
			cost = 4;
			break;
		case 10:
		case 11:
			cost = 5;
			break;
		case 12:
			cost = 6;
			break;
		case 13:
		case 14:
			cost = 7;
			break;
		case 15:
		case 16:
			cost = 8;
			break;
		case 17:
		case 18:
			cost = 9;
			break;
		case 19:
		case 20:
			cost = 10;
			break;
		default:
			cost = 5;
		}
		
		//sets name randomly out of 25 names
		switch((int)(Math.random() * 20)) {
		case 0:
			name = "Knight";
			break;
		case 1:
			name = "Moon";
			break;
		case 2:
			name = "Death";
			break;
		case 3: 
			name = "Fool";
			break;
		case 4:
			name = "Ice Queen";
			break;
		case 5:
			name = "Priestess";
			break;
		case 6:
			name = "Cupid";
			break;
		case 7:
			name = "Alchemist";
			break;
		case 8:
			name = "Nymph";
			break;
		case 9:
			name = "Mother Nature";
			break;
		case 10:
			name = "Lovers";
			break;
		case 11:
			name = "Magician";
			break;
		case 12:
			name = "Storm";
			break;
		case 13:
			name = "Lion";
			break;
		case 14:
			name = "Lady Night";
			break;
		case 15:
			name = "Justice";
			break;
		case 16:
			name = "Judgment";
			break;
		case 17:
			name = "Harpy";
			break;
		case 18:
			name = "Hanging Man";
			break;
		case 19:
			name = "Emperor";
			break;
		default:
			name = "Wisp";
		}
		
	}
	
	//returns the attack of the minion
	public int getAttack() {
		return attack;
	}
	
	//returns the current health of the minion
	public int getHealth() {
		return health;
	}
	
	//this minion takes this much damage.  returns true if the minion dies
	public boolean damage(int damage) {
		health -= damage;
		return health < 1;
	}
	
	//heals either the amount of heal or heals the minion to full, depending on the
	//heal and the maxHealth.
	public void heal(int heal) {
		if(health + heal <= maxHealth)
			health += heal;
		else
			health = maxHealth;
	}
	
	//toString method, shows cost, attack, and health.
	@Override
	public String toString() {
		if(description.equals(""))
			return "Name: " + name + "\nCost: " + cost + "\nAttack: " + attack +
					"\nCurrent Health: " + health;
		else
			return "Name: " + name + "\nCost: " + cost + "\nAttack: " + attack +
					"\nCurrent Health: " + health + "\nDescription: " + description;
	}

	public String toString2() {
		return attack + "/" + health + " " + name + "(" + cost + " mana)";
	}
}
