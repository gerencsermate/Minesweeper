package Minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class GameController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3966683446126817504L;

	/**
	 * az aktuális játék
	 */
	private Game currentGame;
	
	/**
	 * a tábla, amelyen a játék folyik
	 */
	private Table board;
	
	/**
	 * a megjelenítő
	 */
	private GameGUI frame;
	
	/**
	 * a toplista
	 */
	private Scoreline scoreline;
	
	/**
	 * új játék létrehozása
	 * @param s tábla mérete(s*s méretű)
	 * @param m bombák száma
	 * @param bonusFields bónusz mezők száma
	 */
	public void newGame(int s, int m, int bonusFields) {
		currentGame = new Game(this);
		board = new Table(s, m, bonusFields, currentGame);
		currentGame.startGame();
	}
	
	/**
	 * getter
	 * @return board tagváltozó értéke
	 */
	public Table getBoard() {
		return board;
	}
	
	/**
	 * getter
	 * @return scoreline tagváltozó értéke
	 */
	public Scoreline getScoreline() {
		return scoreline;
	}
	
	/**
	 * getter
	 * @return current tagváltozó értéke
	 */
	public Game getCurrentGame() {
		return currentGame;
	}
	
	/**
	 * Ezzel a függvénnyel indítható el az alkalmazás.
	 * Az inicializálást végzi.
	 */
	public void play() {
		scoreline = new Scoreline();
		scoreline.load("scoreline_save");
		frame = new GameGUI(this);
		frame.setVisible(true);
	}
	
	/**
	 * Aktuális játék mentése.
	 * Szerializálja a játékmezőt.
	 * @param saveName ide szerializál
	 */
	public void saveGame(String saveName){
		currentGame.pauseTimer();
		try {
			FileOutputStream f = new FileOutputStream(saveName);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(board);
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Egy elmentett játék betöltése.
	 * @param filename ezt a fájlt tölti be
	 */
	public void loadGame(String filename) {
		try {
			FileInputStream f = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(f);
			board = (Table)in.readObject();
			currentGame = board.getGame();
			currentGame.setController(this);
			currentGame.restartTimer();
			//frame = currentGame.getController().getFrame();
			f.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Meghívja a megjelenítő (frame) win() függvényét
	 */
	public void gameWon() {
		frame.win();
		
	}
	
	/**
	 * Meghívja a megjelenítő (frame) win() függvényét
	 */
	public void gameLost() {
		frame.lose();
	}
	
	/**
	 * Hozzáad egy játékost a toplistához
	 * @param n a hozzáadandó játékos neve
	 */
	public void addPlayerToScoreline(String n) {
		scoreline.addPlayer(new Player(n, currentGame.getTime()));
	}
	
	/**
	 * Elmenteni a toplistát.
	 * Meghívja a scoreline save() függvényét
	 */
	public void saveScoreline() {
		scoreline.save("scoreline_save");
	}
	
	public void stopPlaying() {
		System.exit(0);
	}
}
