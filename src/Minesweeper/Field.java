package Minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JButton;


/**
 * Abstract osztály, a különböző típusú mezők származnak belőle.
 */
abstract public class Field implements Serializable{
	private static final long serialVersionUID = -3011181221916398290L;
	/**
	 * Igaz, ha a mező meg van jelölve.
	 */
	protected boolean marked;
	
	/**
	 * Igaz, ha a mezőre rákattintottak.
	 */
	protected boolean clicked;
	
	/**
	 * A Table, amelyben a mező található.
	 */
	protected Table enviroment;
	
	/**
	 * a tábla melyik sorában helyezkedik el a mező
	 */
	private int row;
	
	/**
	 * a tábla melyik oszlopában helyezkedik el a mező
	 */
	private int column;
	/**
	 * A mezőt reprezentáló gomb.
	 */
	protected JButton button;
	
	/**
	 * konstruktor
	 * @param t a Table, amely tartalmazza
	 * @param r melyik sorban helyezkedik el 
	 * @param c melyik oszlopban helyezkedik el
	 */
	protected Field(Table t, int r, int c) {
		clicked = false;
		marked = false;
		enviroment = t;
		row= r;
		column = c;
	}
	
	/**
	 * getter
	 * @return marked tagváltozó értéke
	 */
	public boolean getMarked() {
		return marked;
	}
	
	/**
	 * getter
	 * @return clicked tagváltozó értéke
	 */
	public boolean getClicked() {
		return clicked;
	}
	
	
	/**
	 * getter
	 * @return row tagváltozó értéke
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * getter
	 * @return column tagváltozó értéke
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * getter
	 * @return a mezőt reprezentáló JButton
	 */
	public JButton getButton() {
		return button;
	}
	
	/**
	 * Bal egérgomb megnyomását kezelő függvény.
	 */
	abstract public void click();
	
	
	/**
	 * Jobb egérgomb megnyomását kezelő függvény.
	 */
	abstract public void mark();
	
	
	/**
	 * Belső osztály, az egér gombjainak lenyomását kezeleő ActionListener.
	 *
	 */
	protected class ClickListener implements MouseListener, Serializable{

		private static final long serialVersionUID = -6727441662537488928L;

		@Override
		/**
		 * Egér eventek kezelésére szolgál. Eventek hatására a mező megfeleő eseméyneit kezelő függvényeit hívja meg.
		 * jobb egérgomb: mező megjelölése(zászló elhelyezése)
		 * bal egérgomb: mezőre kattintás
		 */
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				click();
			}
			else if(e.getButton() == MouseEvent.BUTTON3){
				mark();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
