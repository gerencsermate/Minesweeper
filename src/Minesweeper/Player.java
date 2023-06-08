package Minesweeper;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 *Egy játékos adatait tárolja(név, mennyi ideig tartott a játék).
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 8546379502026586058L;
	/**
	 * játékos neve
	 */
	private String name;
	/**
	 * játék ideje
	 */
	private long time;
	
	/**
	 * konstruktor
	 * @param n név
	 * @param t idő (milliseconds)
	 */
	public Player(String n, long t) {
		name = n;
		time = t;
	}
	
	/**
	 * getter
	 * @return time tagváltozó értéke
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * getter
	 * @return a name tagváltozó értéke
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Az idő tagváltozó értékét adja vissza Stringként, "mm : ss" formában.
	 */
	public String getTimeAsString() {
		return Long.toString(TimeUnit.MILLISECONDS.toSeconds(time) / 60) + " : " + Long.toString(TimeUnit.MILLISECONDS.toSeconds(time) % 60);
	}
}
