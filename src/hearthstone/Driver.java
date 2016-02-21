package hearthstone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Driver {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//JFrame frame = new JFrame();
		//frame.setLayout(null);
		//frame.setSize(1600, 900);
		//frame.getContentPane().setBackground(new Color(51, 204, 255));
		//frame.add(new JLabel(new ImageIcon("mooncard.jpg")));
		int turn = 1;
		Player p1 = new Player("Rexxar", "True Shot");
		Player p2 = new Player("Jaina", "Fire Blast");
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 900);
		frame.getContentPane().setBackground(new Color(51, 204, 255));
		frame.setVisible(true);
		
		while(!p1.lost() && !p2.lost()) {
		
			frame.setVisible(false);
			frame.getContentPane().setLayout(new BorderLayout());
			frame.repaint();
			frame.validate();
			frame.setSize(1600, 900);
			frame.getContentPane().setBackground(new Color(51, 204, 255));
			frame.setVisible(true);
			boolean p1Turn = true;

///////////////////////////////////////////////////////////////////////
//////////////////PLAYER 1 TURN
				
			int x, y;
			boolean attacked = false;
			
			BufferedImage hand = new BufferedImage(1, 1, 1);
			BufferedImage board = new BufferedImage(1, 1, 1);
			BufferedImage cardimg = new BufferedImage(1, 1, 1);
			BufferedImage health = new BufferedImage(1, 1, 1);
			BufferedImage attack = new BufferedImage(1, 1, 1);
			BufferedImage cost = new BufferedImage(1, 1, 1);
			
			JLabel pHealth = new JLabel(p1.getName() + " Health: " + p1.getHealth() + 
					"\n\n " + p2.getName() + " Health: " + p2.getHealth());
			JLabel boardLabel = new JLabel();
			JLabel boardLabel2 = new JLabel();
			//turn = sc.nextInt();
			//System.out.println("Turn #" + turn + ": ");
			if(turn > 9) {
				p1.mana = 10;
				p2.mana = 10;
			}
			else {
				p1.mana = turn;
				p2.mana = turn;
			}
			p1.draw();
			pHealth = new JLabel(p1.getName() + "'s turn! (Turn #: " + turn + ") \n" + p1.getName() + " Health: " + p1.getHealth() + 
					"\n\n " + p2.getName() + " Health: " + p2.getHealth());
			//draws the hand
			if(p1.hand.size() > 0) {
			
				
				hand = new BufferedImage(200 * p1.hand.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics g = hand.getGraphics();
				x = 0;
				y = 0;
					for (Card c : p1.hand) {
						//System.out.println(c.getName());
						cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
					
						g.drawImage(cardimg, x, y, null);
						cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
						g.drawImage(cost, x, y, null);
						if(c instanceof Minion) {
							
							attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
							g.drawImage(attack, x, y + 234, null);
							health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
							g.drawImage(health, x + 136, y + 234, null);
						}
						x += 175;
						BufferedImage empty = ImageIO.read(new File("empty.png"));
						g.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
						x += 25;
					}
					
		
					ImageIcon handIMG = new ImageIcon(hand);
					frame.add(new JLabel(handIMG), BorderLayout.SOUTH);
			}
		
		
		
			//draws the board for p1
			if(p1.board.size() > 0) {
				attacked = true;
				board = new BufferedImage(200 * p1.board.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics gr = board.getGraphics();
				x = 0;
				y = 0;
				for (Card c : p1.board) {
					cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
	
					gr.drawImage(cardimg, x, y, null);
					cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
					gr.drawImage(cost, x, y, null);
					if(c instanceof Minion) {
						
						attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
						gr.drawImage(attack, x, y + 234, null);
						health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
						gr.drawImage(health, x + 136, y + 234, null);
					}
					x += 175;
					BufferedImage empty = ImageIO.read(new File("empty.png"));
					//g.drawImage(empty, x, y, null);
					gr.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
					x += 25;
					

					boardLabel = new JLabel(new ImageIcon(board));
					frame.add(boardLabel, BorderLayout.CENTER);
				}
				
			}
			
			frame.repaint();
			frame.revalidate();
			
			//draws the board for p2
			if(p2.board.size() > 0) {
				board = new BufferedImage(200 * p2.board.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics gr = board.getGraphics();
				x = 0;
				y = 0;
				for (Card c : p2.board) {
					//System.out.println(c.getName());
					cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
	
					gr.drawImage(cardimg, x, y, null);
					cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
					gr.drawImage(cost, x, y, null);
					if(c instanceof Minion) {
						
						attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
						gr.drawImage(attack, x, y + 234, null);
						//System.out.println(c.getName() + " current health: " +((Minion) c).getHealth());
						health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
						gr.drawImage(health, x + 136, y + 234, null);
					}
					x += 175;
					BufferedImage empty = ImageIO.read(new File("empty.png"));
					//g.drawImage(empty, x, y, null);
					gr.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
					x += 25;
					
					boardLabel2 = new JLabel(new ImageIcon(board));
					frame.add(boardLabel2, BorderLayout.NORTH);
					frame.repaint();
				}
				
				
			}
			
			frame.add(pHealth, BorderLayout.EAST);

			String[] cardNames = new String[p1.hand.size() + 2];
			int count = 1;
			cardNames[0] = "Select a card to use or to not play a card. You have " + p1.mana + " mana.";
			cardNames[cardNames.length - 1] = "Do not play a card";
			JComboBox<?> cards = null;
			for(Card c : p1.hand) {
				if (c instanceof Minion)
					cardNames[count] = ((Minion)c).toString2();
				else if (c instanceof Spell)
					cardNames[count] = ((Spell)c).toString2();
				count++;
				cards = new JComboBox<String>(cardNames);
				cards.setSelectedIndex(0);
			}
			
			frame.add(cards, BorderLayout.WEST);
			
			while(cards.getSelectedItem().equals("Select a card to use or to not play a card. You have " + p1.mana + " mana.")) {
				frame.repaint();
				frame.revalidate();
				Thread.sleep(200);
			}
			
			if(cards.getSelectedIndex() == cards.getItemCount() - 1){
				
			}
			else if (p1.hand.get(cards.getSelectedIndex() - 1).getClass() == new Minion().getClass()){
				if(p1.summon((Minion)p1.hand.get(cards.getSelectedIndex() - 1))) {
					cards.removeItemAt(cards.getSelectedIndex() - 1);
					if(p1.board.size() > 1) {
						frame.remove(boardLabel);
						frame.add(boardLabel);
					}
					if(p2.board.size() > 1) {
						frame.add(boardLabel2);
						frame.remove(boardLabel2);
					}
					frame.add(cards, BorderLayout.CENTER);
					frame.repaint();
					frame.revalidate();
				}
				//System.out.println("hello");
			}
			else if (p1.hand.get(cards.getSelectedIndex() - 1) instanceof Spell) {
				if(p1.cast((Spell)p1.hand.get(cards.getSelectedIndex() - 1), p2)){
					cards.removeItemAt(cards.getSelectedIndex() - 1);
					frame.repaint();
					frame.revalidate();
				}
			}
			
			frame.remove(cards);
			
			
			if(attacked) {
				//finds the selected minion
				Minion attacker = null;
				String selected = "";
				Minion[] boardMinions = new Minion[p1.board.size()];
				String[] boardNames = new String[p1.board.size() + 2];
				boardNames[0] = "Select a minion to attack with";
				boardNames[boardNames.length - 1] = "Do not attack";
				count = 1;
				JComboBox<?> minions = null;
				JComboBox<?> minions2 = null;
				for(Minion m : p1.board) {
					boardMinions[count - 1] = m;
					boardNames[count] = m.toString2();
					count++;
					minions = new JComboBox<Object>(boardNames);
					minions.setSelectedIndex(0);
					
					frame.add(minions, BorderLayout.WEST);
					
				}
				while(minions.getSelectedItem().equals("Select a minion to attack with")) {
					frame.repaint();
					frame.revalidate();
					Thread.sleep(200);
				}
				
				//selects the attacker and store it in the variable
				selected = (String) minions.getSelectedItem();
				for(Minion m : boardMinions) {
					if(m.toString2().equals(selected))
						attacker = m;
				}
				
				frame.remove(minions);
				frame.repaint();
				frame.revalidate();
				
				if(attacker != null) {
					Minion[] board2Minions = new Minion[p2.board.size()];
					String[] board2Names = new String[p2.board.size() + 3];
					board2Names[0] = "Select a target to attack";
					board2Names[board2Names.length - 2] = p2.getName();
					board2Names[board2Names.length - 1] = "Do not attack a target";
					int count2 = 1;
					for(Minion m : p2.board) {
						board2Minions[count2 - 1] = m;
						board2Names[count2] = m.toString2();
						count2++;
						//Create the combo box, select item at index 4.
						//Indices start at 0, so 4 specifies the pig.
						
					}
					minions2 = new JComboBox<Object>(board2Names);
					minions2.setSelectedIndex(0);
					
					frame.add(minions2, BorderLayout.WEST);
					while(minions2.getSelectedItem().equals("Select a target to attack")) {
						frame.repaint();
						frame.revalidate();
						Thread.sleep(200);
					}
					
					//selects the attacker and store it in the variable
					selected = (String) minions2.getSelectedItem();
					selected = selected.trim();
					
					if(selected.equals(p2.getName())){
						p1.attack(attacker, p2);
						if(p2.lost()) {
							BufferedImage victory = ImageIO.read(new File("victoryscreen.jpg"));
							Graphics gr = victory.getGraphics();
							gr.drawImage(victory, 0, 0, null);
							gr.drawImage(victory, 0, 0, 1600, 900, null);
							frame.repaint();
							frame.revalidate();
							//frame.setVisible(false);
							System.out.println("):");
						}
						else if(p1.lost()) {
							BufferedImage defeat = ImageIO.read(new File("defeatscreen.jpg"));
							Graphics gr = defeat.getGraphics();
							gr.drawImage(defeat, 0, 0, null);
							frame.repaint();
							frame.revalidate();
							//frame.setVisible(false);
						}
					}
					else {
						for(int pos = 1; pos < board2Names.length - 1; pos++) {
							if(board2Names[pos].equals(selected))
							p1.attack(attacker, p2, pos - 1);
						}
					}
				frame.remove(pHealth);
				frame.revalidate();
				pHealth = new JLabel(p1.getName() + "'s turn! (Turn #: " + turn + ") \n" + p1.getName() + " Health: " + p1.getHealth() + 
						"\n\n " + p2.getName() + " Health: " + p2.getHealth());
				frame.add(pHealth, BorderLayout.EAST);
				//System.out.println(p1.board + " 2: " + p2.board);
				frame.remove(minions2);
				frame.repaint();
				frame.revalidate();
				
				}
			}
			
			
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.revalidate();
				
			p2.draw();
			//draws the hand
			if(p2.hand.size() > 0) {
			
				
				hand = new BufferedImage(200 * p2.hand.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics g = hand.getGraphics();
				x = 0;
				y = 0;
					for (Card c : p2.hand) {
						//System.out.println(c.getName());
						cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
						//for flipping
						//g2.drawImage(image, x + width, y, -width, height, null);
						g.drawImage(cardimg, x, y, null);
						cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
						g.drawImage(cost, x, y, null);
						if(c instanceof Minion) {
							
							attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
							g.drawImage(attack, x, y + 234, null);
							health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
							g.drawImage(health, x + 136, y + 234, null);
						}
						x += 175;
						BufferedImage empty = ImageIO.read(new File("empty.png"));
						//g.drawImage(empty, x, y, null);
						g.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
						x += 25;
					}
					
		
					ImageIcon handIMG = new ImageIcon(hand);
					frame.add(new JLabel(handIMG), BorderLayout.SOUTH);
			}
		
		
		
			//draws the board for p2
			attacked = false;
			if(p2.board.size() > 0) {
				attacked = true;
				board = new BufferedImage(200 * p2.board.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics gr = board.getGraphics();
				x = 0;
				y = 0;
				for (Card c : p2.board) {
					//System.out.println(c.getName());
					cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
	
					gr.drawImage(cardimg, x, y, null);
					cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
					gr.drawImage(cost, x, y, null);
					if(c instanceof Minion) {
						
						attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
						gr.drawImage(attack, x, y + 234, null);
						health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
						gr.drawImage(health, x + 136, y + 234, null);
					}
					x += 175;
					BufferedImage empty = ImageIO.read(new File("empty.png"));
					//g.drawImage(empty, x, y, null);
					gr.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
					x += 25;
					

					boardLabel = new JLabel(new ImageIcon(board));
					frame.add(boardLabel, BorderLayout.CENTER);
				}
				
			}
			
			frame.repaint();
			frame.revalidate();
			
			//draws the board for p1
			if(p1.board.size() > 0) {
				board = new BufferedImage(200 * p1.board.size(), 274, BufferedImage.TYPE_INT_RGB);
				Graphics gr = board.getGraphics();
				x = 0;
				y = 0;
				for (Card c : p1.board) {
					//System.out.println(c.getName());
					cardimg = ImageIO.read(new File(c.getName() + ".jpg"));
	
					gr.drawImage(cardimg, x, y, null);
					cost = ImageIO.read(new File("stats/" + c.getCost() + "cost.jpg"));
					gr.drawImage(cost, x, y, null);
					if(c instanceof Minion) {
						
						attack = ImageIO.read(new File(("stats/" + ((Minion) c).getAttack() + "attack.jpg")));
						gr.drawImage(attack, x, y + 234, null);
						//System.out.println(c.getName() + " current health: " +((Minion) c).getHealth());
						health = ImageIO.read(new File(("stats/" + ((Minion) c).getHealth() + "health.jpg")));
						gr.drawImage(health, x + 136, y + 234, null);
					}
					x += 175;
					BufferedImage empty = ImageIO.read(new File("empty.png"));
					//g.drawImage(empty, x, y, null);
					gr.drawImage(empty, x, y, 25, 274, frame.getContentPane().getBackground(), null);
					x += 25;
					
					boardLabel2 = new JLabel(new ImageIcon(board));
					frame.add(boardLabel2, BorderLayout.NORTH);
					frame.repaint();
				}
				
				
			}
			
			frame.add(pHealth, BorderLayout.EAST);

			String[] cardNames2 = new String[p2.hand.size() + 2];
			count = 1;
			cardNames2[0] = "Select a card to use or to not play a card. You have " + p2.mana + " mana.";
			cardNames2[cardNames2.length - 1] = "Do not play a card";
			JComboBox<?> cards2 = null;
			for(Card c : p2.hand) {
				if (c instanceof Minion)
					cardNames2[count] = ((Minion)c).toString2();
				else if (c instanceof Spell)
					cardNames2[count] = ((Spell)c).toString2();
				count++;
				cards2 = new JComboBox<String>(cardNames2);
				cards2.setSelectedIndex(0);
			}
			
			frame.add(cards2, BorderLayout.WEST);
			
			while(cards2.getSelectedItem().equals("Select a card to use or to not play a card. You have " + p2.mana + " mana.")) {
				frame.repaint();
				frame.revalidate();
				Thread.sleep(200);
			}
			
			if(cards2.getSelectedIndex() == cards2.getItemCount() - 1){
				
			}
			else if (p2.hand.get(cards2.getSelectedIndex() - 1).getClass() == new Minion().getClass()){
				if(p2.summon((Minion)p2.hand.get(cards2.getSelectedIndex() - 1))) {
					cards2.removeItemAt(cards2.getSelectedIndex() - 1);
					if(p2.board.size() > 1) {
						frame.remove(boardLabel);
						frame.add(boardLabel);
					}
					if(p1.board.size() > 1) {
						frame.add(boardLabel2);
						frame.remove(boardLabel2);
					}
					frame.add(cards2, BorderLayout.CENTER);
					frame.repaint();
					frame.revalidate();
				}
			}
			else if (p2.hand.get(cards2.getSelectedIndex() - 1).getClass() == new Spell().getClass()){
				if(p2.cast((Spell)p2.hand.get(cards2.getSelectedIndex() - 1), p1)){
					cards2.removeItemAt(cards2.getSelectedIndex() - 1);
					frame.repaint();
					frame.revalidate();
				}
			}
			
			frame.remove(cards2);
			
			
			if(attacked) {
				//finds the selected minion
				Minion attacker = null;
				String selected = "";
				Minion[] boardMinions = new Minion[p2.board.size()];
				String[] boardNames = new String[p2.board.size() + 2];
				boardNames[0] = "Select a minion to attack with";
				boardNames[boardNames.length - 1] = "Do not attack";
				count = 1;
				JComboBox<?> minions = null;
				JComboBox<?> minions2 = null;
				for(Minion m : p2.board) {
					boardMinions[count - 1] = m;
					boardNames[count] = m.toString2();
					count++;
					minions = new JComboBox<Object>(boardNames);
					minions.setSelectedIndex(0);
					
					frame.add(minions, BorderLayout.WEST);
					
				}
				while(minions.getSelectedItem().equals("Select a minion to attack with")) {
					frame.repaint();
					frame.revalidate();
					Thread.sleep(200);
				}
				
				//selects the attacker and store it in the variable
				selected = (String) minions.getSelectedItem();
				for(Minion m : boardMinions) {
					if(m.toString2().equals(selected))
						attacker = m;
				}
				
				frame.remove(minions);
				frame.repaint();
				frame.revalidate();
				
				if(attacker != null) {
					Minion[] board2Minions = new Minion[p1.board.size()];
					String[] board2Names = new String[p1.board.size() + 3];
					board2Names[0] = "Select a target to attack";
					board2Names[board2Names.length - 2] = p1.getName();
					board2Names[board2Names.length - 1] = "Do not attack a target";
					int count2 = 1;
					for(Minion m : p1.board) {
						board2Minions[count2 - 1] = m;
						board2Names[count2] = m.toString2();
						count2++;
						//Create the combo box, select item at index 4.
						//Indices start at 0, so 4 specifies the pig.
						
					}
					minions2 = new JComboBox<Object>(board2Names);
					minions2.setSelectedIndex(0);
					
					frame.add(minions2, BorderLayout.WEST);
					while(minions2.getSelectedItem().equals("Select a target to attack")) {
						frame.repaint();
						frame.revalidate();
						Thread.sleep(200);
					}
					
					//selects the attacker and store it in the variable
					selected = (String) minions2.getSelectedItem();
					selected = selected.trim();
					
					if(selected.equals(p1.getName())){
						p2.attack(attacker, p1);
						if(p1.lost()) {
							frame.getContentPane().removeAll();
							frame.add(new JLabel(p1.getName() + " wins!"));
						}
						else if(p2.lost()) {
							System.out.println(p2.getName() + " loses!");
						}
					}
					else {
						for(int pos = 1; pos < board2Names.length - 1; pos++) {
							if(board2Names[pos].equals(selected))
							p2.attack(attacker, p1, pos - 1);
						}
					}
				frame.remove(pHealth);
				frame.revalidate();
				pHealth = new JLabel(p2.getName() + "'s turn! (Turn #: " + turn + ")\n" + p2.getName() + " Health: " + p2.getHealth() + 
						"\n\n " + p1.getName() + " Health: " + p1.getHealth());
				frame.add(pHealth, BorderLayout.EAST);
				//System.out.println(p2.board + " 2: " + p1.board);
				frame.remove(minions2);
				frame.repaint();
				frame.revalidate();
				
				}
			}
			
			
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.revalidate();
		
		turn++;
		
		}
		if(p2.lost()) {
			BufferedImage victory = ImageIO.read(new File("victoryscreen.jpg"));
			Graphics gr = victory.getGraphics();
			gr.drawImage(victory, 0, 0, null);
			//frame.repaint();
			//frame.revalidate();
			System.out.println("):");
			
		}
		else if(p1.lost()) {
			BufferedImage defeat = ImageIO.read(new File("defeatscreen.jpg"));
			Graphics gr = defeat.getGraphics();
			gr.drawImage(defeat, 0, 0, null);
			frame.repaint();
			frame.revalidate();
		}
		
		System.out.println("hello");
	}
	
/*	public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
 
        addAButton("Button 1", pane);
        addAButton("Button 2", pane);
        addAButton("Button 3", pane);
        addAButton("Long-Named Button 4", pane);
        addAButton("5", pane);
    }
 
    private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }
 
    *//**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     *//*
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BoxLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }*/
 
}
