package Minesweeper;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *bónusz mezőket (BonusField) reprezentáló JButton-ök kinézetének beállítását végzi. 
 */
public class BonusFieldGUI {
	
	/**
	 * Létrehoz egy gombot alapértelmezett kinézettel.
	 * @return a létrehozott gomb
	 */
	public static JButton init() {
		JButton button = new JButton();
		button.setSize(40, 40);
		button.setBackground(Color.DARK_GRAY);
		return button;
	}
	
	
	/**
	 * Lekérdezi a paraméterként kapott BonusField objektum tagváltozóinak értékeit és ezeknek megfeleően megváltoztatja a JButton kinézetét.
	 * 
	 * @param b a BonusFiled, amelyhez tartozó JButton kinézetét változtatja
	 */
	public static void update(BonusField b) {
		if(b.getMarked()) {
			b.getButton().setIcon(new ImageIcon("images/flag.png"));
		}
		else if(b.getClicked()) {
			b.getButton().setBackground(Color.LIGHT_GRAY);
			b.getButton().setIcon(new ImageIcon("images/bonus.png"));
		}
		else if(b.getMarked() == false) {
			b.getButton().setIcon(null);
		}
	}
}
