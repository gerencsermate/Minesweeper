package Minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


/**
 * A toplista adatainak tárolásáért felelős.
 */
public class Scoreline extends AbstractTableModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -521245700579598786L;
	private ArrayList<Player> players;
	
	/**
	 * konstruktor
	 */
	public Scoreline() {
		players = new ArrayList<Player>();
	}
	
	/**
	 * Egy játékost hozzáad a toplistához.
	 * @param a hozzáadandó játékos
	 */
	public void addPlayer(Player p) {
		players.add(p);
		players.sort((a, b) -> {return a.getTime() > b.getTime() ? 1 : -1;});
	}
	
	/**
	 * A megadott fájlba szerializálja az adatokat tartalmazó ArrayList-et.
	 * @param filename ide szerializál
	 */
	public void save(String filename) {
		try {
			FileOutputStream f = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(players);
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A megadott nevű fájlból visszatölti az adatokat és a beolvasott ArrayList-et állítja be a players tagváltozó értékének.
	 * @param filename innen tölti be az adatokat
	 */
	@SuppressWarnings("unchecked")
	public void load(String filename) {
		try {
			FileInputStream f = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(f);
			players = (ArrayList<Player>)in.readObject();
			f.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return players.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return players.get(rowIndex).getName();
		}else {
			return players.get(rowIndex).getTimeAsString();
		}
	}
}
