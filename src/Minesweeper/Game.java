package Minesweeper;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Az aktuális játékot vezérlő osztály.
 *
 */
public class Game implements Serializable{
	
	private static final long serialVersionUID = -100867446339686008L;
	
	/**
	 * A GameController, amely létrehozta.
	 */
	transient private GameController controller;
	
	/**
	 * A játékhoz tartozó TimeCounter. Ez az objektum felelős a játék idejének számolásáért.
	 */
	private TimeCounter timer;

	/**
	 * konstruktor
	 * @param g A GameController, amelyhez tartozik.
	 */
	public Game(GameController g) {
		controller = g;
		timer = new TimeCounter();
	}
	
	/**
	 * setter
	 * @param gc erre állítja controller tagváltozó értékét
	 */
	public void setController(GameController gc) {
		controller = gc;
	}
	
	/**
	 * Játék elindításákor hívódik.
	 * Meghívja a timer tagváltozó start()	függvényét 
	 */
	public void startGame() {
		timer.start();
	}
	
	
	/**
	 * Akkor hívódik, ha a játékos megnyerte a játékot.
	 * Meghívja a timer stop(), illetve a controller gameWon() függvényét.
	 */
	public void gameWon() {
		controller.gameWon();
	}
	
	/**
	 * Akkor hívódik, ha a játékos elvesztette a játékot.
	 * Meghívja a timer stop(), illetve a controller gameLost() függvényét.
	 */
	public void gameLost() {
		controller.gameLost();
		
	}
	
	/**
	 * Szünteteltei a timer-t
	 * Meghívja a timer Pause() függvényét
	 */
	public void pauseTimer() {
		timer.pause();
	}
	
	/**
	 * Szüneteltetés után újra elindítja a timer-t
	 * Meghívja a timer resume( függvényét
	 */
	public void restartTimer() {
		timer.Resume();
	}
	
	/**
	 * Lekérdezi a timertől az eltelt időt.
	 * @return az eltelt idő
	 */
	public long getTime() {
		return timer.getCurrentTime();
	}
	
	/**
	 * Lekérdezi a timertől az adott  pillantban eltelt időt és Stringként adja vissza 
	 * @return az eltelt idő Stringként
	 */
	public String getCurrentTime() {
		long t = timer.getCurrentTime();
		return Long.toString(TimeUnit.MILLISECONDS.toSeconds(t) / 60) + " : " + Long.toString(TimeUnit.MILLISECONDS.toSeconds(t) % 60);
	}
	
}
