package Minesweeper;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 *Üres mezőket(EmptyField) reprezentáló JButton-ök kinézetének beállítását végzi. 
 */
public class EmptyFieldGUI {
	
	/**
	 * Létrehoz egy gombot alapértelmezett kinézettel.
	 * @return a létrehozott gomb
	 */
	public static JButton init(EmptyField f) {
		JButton button = new JButton();
		button.setSize(40, 40);
		button.setBackground(Color.DARK_GRAY);
		return button;
		
	}
	
	/**
	 * Lekérdezi a paraméterként kapott EmptyField objektum tagváltozóinak értékeit és ezeknek megfeleően megváltoztatja a JButton kinézetét.
	 * 
	 * @param f az EmptyField, amelyhez tartozó JButton kinézetét változtatja
	 */
	public static void update(EmptyField f) {
		if(f.getMarked()) {
			f.getButton().setIcon(new ImageIcon("images/flag.png"));
		}
		else if(f.getClicked()) {
			if(f.getAdjacentMines() > 0) {
				f.getButton().setText(Integer.toString(f.getAdjacentMines()));
			}
			f.getButton().setBackground(Color.LIGHT_GRAY);
			f.getButton().setFont(new Font("Arial", Font.PLAIN, 17));
		}
		else if(f.getMarked() == false) {
			f.getButton().setIcon(null);
		}
	}
}
