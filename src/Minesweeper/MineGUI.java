package Minesweeper;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *Bombákat(Mine) reprezentáló JButton-ök kinézetének beállítását végzi. 
 */
public class MineGUI {
	
	/**
	 * Létrehoz egy gombot alapértelmezett kinézettel.
	 * @return a létrehozott gomb
	 */
	public static JButton init(Mine m) {
		JButton button = new JButton();
		button.setSize(40, 40);
		button.setBackground(Color.DARK_GRAY);
		return button;
	}
	
	
	/**
	 * Lekérdezi a paraméterként kapott Mine objektum tagváltozóinak értékeit és ezeknek megfeleően megváltoztatja a JButton kinézetét.
	 * 
	 * @param m a Mine, amelyhez tartozó JButton kinézetét változtatja
	 */
	public static void update(Mine m) {
		if(m.getRevealed()) {
			m.getButton().setBackground(Color.GREEN);
			m.getButton().setIcon(new ImageIcon("images/flag.png"));
		}
		else if(m.getMarked()) {
			m.getButton().setIcon(new ImageIcon("images/flag.png"));
		}
		else if(m.getClicked()) {
			m.getButton().setBackground(Color.RED);
			m.getButton().setIcon(new ImageIcon("images/mine.png"));
		}
		else if(m.getMarked() == false) {
			m.getButton().setIcon(null);
		}
		
	}
}
