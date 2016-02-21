package hearthstone;

public class Spell extends Card{

	//boolean dmgSpell = false;
	//boolean effSpell = false;
	boolean allySpell;
	
	//constructor, 3 params, calls super constructor
	public Spell(int cost, String name, String description) {
		super(cost, name, description);
		allySpell = false;
	}
	
	//default constructor, creates a random spell
	public Spell() {
		super();
		
		switch((int)(Math.random() * 6)){
		case 0:
			name = "Snipe";
			description = "Deal 6 damage to a random minion";
			cost = 4;
			allySpell = false;
			break;
		case 1:
			name = "Draw";
			description = "Draw 2 cards";
			cost = 3;
			allySpell = true;
			break;
		case 2:
			name = "Heal";
			description = "Heal 5 health to a random minion";
			cost = 1;
			allySpell = true;
			break;
		case 3:
			name = "Summon";
			description = "Summon a 2/2 Wisp";
			cost = 2;
			allySpell = true;
			break;
		case 4:
			name = "Shuffle";
			description = "Shuffle your opponent's deck";
			cost = 4;
			allySpell = false;
			break;
		case 5:
			name = "Explosion";
			description = "Destroys a random enemy minion";
			cost = 6;
			allySpell = false;
		default:
		}
	}
	
	//casts the spell
	public void cast(Player p1, Player p2) {
		switch(name) {
		case "Snipe":
			int target = (int)Math.random() * p2.board.size();
			if(p2.board.size() > 0) {
				p2.board.get(target).damage(6);
				p2.board.remove(target);
			}
			break;
		case "Draw":
			p1.draw();
			p1.draw();
			break;
		case "Heal":
			if(p1.board.size() > 0)
				p1.board.get((int)Math.random() * p1.board.size()).heal(5);
			break;
		case "Summon":
			p1.board.add(new Minion(0, 2, 2, "Wisp", ""));
			break;
		case "Shuffle":
			p2.shuffle();
		case "Explosion":
			int target2 = (int)Math.random() * p2.board.size();
			if(p2.board.size() > 0) {
				p2.board.remove(target2);
			}
		default:
			p1.summon(new Minion(0, 1, 1, "Error Minion", "How did this get here?"));
		
			
		}
	}
	
	//toString for spells, all spells have descriptions
	@Override
	public String toString() {
		return "Name: " + name + "\nCost: " + cost + "\nDescription: " + description;
	}
	
	
	public String toString2() {
		return "Name: " + name + "(" + cost + " mana)";
	}
}
