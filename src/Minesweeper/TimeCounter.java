package Minesweeper;

import java.io.Serializable;

/**
 * Játék idejének mérésére szolgál.
 *
 */
public class TimeCounter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5454106112015291380L;
	/**
	 * eltelt idő
	 */
	private long elapsed;
	/**
	 * indítás pillanata
	 */
	private long start;
	/**
	 * szünet megkezdésének pillanata
	 */
	private long paused;
	/**
	 * utolsó lekérdezés pillanata
	 */
	private long get;
	
	/**
	 * konstruktor
	 * Inicializálja a tagváltozókat.
	 */
	public TimeCounter() {
		elapsed = 0;
		paused = 0;
		elapsed = 0;
		get = 0;
	}
	
	/**
	 * Elindítja a számlálót. (start tagváltozót a jelenlegi időre állítja)
	 */
	public void start() {
		start = System.currentTimeMillis();
		get = start;
	}
	
	public long getElapsed() {
		return elapsed;
	}
	
	/**
	 * Megadja a az indítás óta eltelt idő, tovább számol, elapsed értékét nem állítja be.
	 * @return az eltelt idő
	 */
	public long getCurrentTime() {
		elapsed += System.currentTimeMillis() - get;
		get = System.currentTimeMillis();
		return elapsed;
	}
	
	/**
	 * Szünet az idő számolásában.
	 */
	public void pause() {
		paused = System.currentTimeMillis();
	}
	
	/**
	 * Szünet megkezdésétől eltelt időt levonja az elapsed tagváltozó értékéből.
	 */
	public void Resume() {
		elapsed = elapsed - (System.currentTimeMillis() - paused); 
	}
}
