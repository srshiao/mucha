package hearthstone;

public class Card {

	int cost;
	String name;
	//maybe no description
	String description;
	
	//constructor 3 params
	public Card(int cost, String name, String description){
		this.cost = cost;
		this.name = name;
		this.description = description;
	}
	
	//constructor 2 params
	public Card(int cost, String name){
		this.cost = cost;
		this.name = name;
		this.description = "";
	}
	
	//copy constructor
	public Card(Card other){
		this.cost = other.cost;
		this.name = other.name;
		this.description = other.description;
	}
	
	//default constructor, initalizes default variables to 0 or an empty String
	public Card() {
		cost = 0;
		name = "";
		description = "";
	}
	
	//returns name
	public String getName() {
		return name;
	}
	
	//returns cost
	public int getCost() {
		return cost;
	}
	
	//default toString, should never be called
	public String toString() {
		if(description.equals(""))
			return "Name: " + name + "\nCost: " + cost;
		else
			return "Name: " + name + "\nCost: " + cost + "\nDescription: " + description;
	}
	
	
}
