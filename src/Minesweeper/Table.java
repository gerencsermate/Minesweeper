package Minesweeper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class Table implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7670949479403529986L;
	
	/**
	 * a mezőket tartalmzó tömb
	 */
	private Field[][] fields;
	
	/**
	 * a bombák külön eltárolva
	 */
	private ArrayList<Mine> mines;
	
	/**
	 * a bombák száma
	 */
	private int numberOfMines;
	
	/**
	 * bónusz mezők száma
	 */
	private int numberOfBonusFields;
	
	/**
	 * a tábla mérete
	 */
	private int size;
	
	/**
	 * jelöletlen bombák száma
	 */
	private int remainingMines;
	
	/**
	 * kihelyezett zászlók száma
	 */
	private int flags;
	
	/**
	 * a játék, amely a táblát tartalmazza
	 */
	private Game game;
	
	/**
	 * igaz, ha a játék folyamatban van
	 */
	private boolean playing;
	
	/**
	 * igaz, ha a játékos vesztett
	 */
	private boolean lost;

	/**
	 * konstruktor
	 * Inicializálja a játékmezőt. Elhelyezi a bombékat, létrehozza a mezőket és bónuszmezőket.
	 * @param s a tábla mérete
	 * @param m a bomák száma
	 * @param bonusFields a bónusz mezők száma 
	 * @param g a játék, amely tartalmazza a táblát
	 */
	public Table(int s, int m, int bonusFields, Game g) {
		game = g;
		size = s;
		numberOfMines = m;
		numberOfBonusFields = bonusFields;
		remainingMines = m;
		flags = 0;
		playing = true;
		lost = false;
		
		
		fields = new Field[size][size];
		mines = new ArrayList<Mine>();
		int adjacentMines[][] = new int[size][size];
		
		Random r = new Random();
		for(int i = 0; i < numberOfMines; i++) {
			int x = r.nextInt(size);
			int y = r.nextInt(size);
			while(fields[y][x] != null) {
				x = r.nextInt(size);
				y = r.nextInt(size);
			}
			fields[y][x] = new Mine(this, y, x);
			mines.add((Mine)fields[y][x]);
			if(y-1 >= 0 && x-1 >= 0) {
				adjacentMines[y-1][x-1]++;
			}
			if(y-1 >= 0) {
				adjacentMines[y-1][x]++;
			}
			if(y-1 >= 0 && x+1 < size) {
				adjacentMines[y-1][x+1]++;
			}
			if(x-1 >= 0) {
				adjacentMines[y][x-1]++;
			}
			if(x+1 < size) {
				adjacentMines[y][x+1]++;
			}
			if(y+1 < size && x-1 >=0) {
				adjacentMines[y+1][x-1]++;
			}
			if(y+1 < size) {
				adjacentMines[y+1][x]++;
			}
			if(y+1 < size && x+1 < size) {
				adjacentMines[y+1][x+1]++;
			}
		}
		for(int i = 0; i < numberOfBonusFields; i++) {
			int x = r.nextInt(size);
			int y = r.nextInt(size);
			while(fields[y][x] != null) {
				x = r.nextInt(size);
				y = r.nextInt(size);
			}
			fields[y][x] = new BonusField(this, y, x);
		}
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(fields[i][j] == null) {
					fields[i][j] = new EmptyField(adjacentMines[i][j], this, i, j);
				}
			}
		}
	}
	
	/**
	 * getter
	 * @return game tagváltozó értéke
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * getter
	 *@return a bombákat tartalmazó ArrayList
	 */
	public ArrayList<Mine> getMines(){
		return mines;
	}
	
	/**
	 * getter
	 * @param row  sor
	 * @param column oszlop
	 * @return a megadott sorban és oszlopban elhelyzkedő mező
	 */
	public Field getField(int row, int column) {
		return fields[row][column];
	}
	
	/**
	 * getter
	 * @return remainingMines tagváltozó értéke
	 */
	public int getRemainingMines(){
		return remainingMines;
	}
	
	/**
	 * getter
	 * @return igaz ha a játékos bombát talált
	 */
	public boolean getPlaying() {
		return playing;
	}
	
	
	/**
	 * gette
	 * @return  lost tagváltozó értéke
	 */
	public boolean getLost() {
		return lost;
	}
	
	/**
	 * eggyel megnöveli a flags mező értékét
	 */
	public void flagPlaced() {
		flags++;
	}
	
	/**
	 * eggyel csökkenti a flags mező értékét
	 */
	public void flagRemoved() {
		flags--;
	}
	
	/**
	 * meghívja a game gameLost() függvényét
	 */
	public void mineFound() {
		lost = true;
		for(Mine i : mines) {
			if(i.getClicked() == false) {
				i.click();
			}
		}
		playing = false;
		game.gameLost();
	}
	
	/**
	 * eggyel növeli flags változó értékét, valmaint eggyel csökkenti a reaminingMines változó értékét
	 * Ha a reaminingMines változó értéke 0, akkor a játékos nyert, meghívódik a game gameWon() függvénye  
	 */
	public void mineSpotted(){
		flags++;
		remainingMines--;
		if(remainingMines == 0) {
			playing = false;
			game.gameWon();
		}
	}
	
	/**
	 * eggyel csökkenti flags változó értékét, valmaint eggyel növeli a reaminingMines változó értékét
	 */
	public void mineUnspotted() {
		remainingMines++;
		flags--;
	}
	
	/**
	 * jelzi ha nem helyezhető el több zászló
	 * @return igaz ha kihelyeztt zászlók száma elérte a maximumot
	 */
	public boolean flagLimitReached() {
		return flags == numberOfMines;
	}
	
	/**
	 * ha a mező körül nem helyezkedik el egy bomba sem, meghívja a szomszédai click() függvényét
	 * @param f a mező, amelyet vizsgál
	 */
	public void clickNeighbours(Field f) {
		if(numberOfBonusFields == 0) {
			int r = f.getRow();
			int c = f.getColumn();
			if(r-1 >= 0 && c-1 >= 0 && fields[r-1][c-1].getClicked() == false) {
				fields[r-1][c-1].click();
			}
			if(r-1 >= 0 && fields[r-1][c].getClicked() == false) {
				fields[r-1][c].click();
			}
			if(r-1 >= 0 && c+1 < size && fields[r-1][c+1].getClicked() == false) {
				fields[r-1][c+1].click();
			}
			if(c-1 >= 0 && fields[r][c-1].getClicked() == false) {
				fields[r][c-1].click();
			}
			if(c+1 < size && fields[r][c+1].getClicked() == false) {
				fields[r][c+1].click();
			}
			if(r+1 < size && c-1 >= 0 && fields[r+1][c-1].getClicked() == false) {
				fields[r+1][c-1].click();
			}
			if(r+1 < size && fields[r+1][c].getClicked() == false) {
				fields[r+1][c].click();
			}
			if(r+1 < size && c+1 < size && fields[r+1][c+1].getClicked() == false) {
				fields[r+1][c+1].click();
			}
		}
	}

	/**
	 * felfed egy bombát
	 */
	public void revealMine() {
		for(Mine i : mines) {
			if(i.getClicked() == false && i.getMarked() == false && i.getRevealed() == false) {
				i.reveal();
				break;
			}
		}
	}
	
	/**
	 * getter
	 * @return sorok száma
	 */
	public int getRowCount() {
		return size;
	}


	/**
	 * getter
	 * @return oszlopok száma
	 */
	public int getColumnCount() {
		return size;
	}


	/**
	 * A megjelenítéshez kell, megadott mezőt reprezentáló JButton-t adja vissza.
	 * @param rowIndex sor 
	 * @param columnIndex oszlop
	 * @return az adott mezőt reprezentáló JButton
	 */
	public JButton getValueAt(int rowIndex, int columnIndex) {
		return fields[rowIndex][columnIndex].getButton();
	}
}
