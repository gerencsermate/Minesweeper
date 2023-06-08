package Minesweeper;

import java.io.Serializable;



/**
 * EmptyField(üres mező) típusú mező.
 */
public class EmptyField extends Field implements Serializable{
	private static final long serialVersionUID = -4503098251689410881L;
	
	/**
	 * Szomszédos mezőkön elhelyezkedő bombák száma.
	 */
	private int adjacentMines;
	
	/**
	 * konstruktor
	 * @param t a Table, amely tartalmazza
	 * @param r melyik sorban helyezkedik el 
	 * @param c melyik oszlopban helyezkedik el
	 */
	public EmptyField(int n, Table t, int r, int c) {
		super(t, r, c);
		adjacentMines = n;
		marked = false;
		button = EmptyFieldGUI.init(this);
		button.addMouseListener(new ClickListener());
	};
	
	/**
	 * getter
	 * @return adjacentMines tagválzozó értéke
	 */
	public int getAdjacentMines() {
		return adjacentMines;
	}
	
	
	@Override
	/**
	 * Bal egérgomb megnyomását kezelő függvény. Ha a mező nincs megjelölve(marked == false), akkor clicked tagváltozó értékét igazra állítja.
	 * Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt.
	 * Ha az adjacentMines tagváltozó értéke 0, akkor meghívja a tartalmazó tábla clickNeighbours() függvényét.
	 */
	public void click() {
		if(enviroment.getPlaying()) {
			if(!marked) {
				clicked = true;
			}
			EmptyFieldGUI.update(this);
			if(adjacentMines == 0) {
				enviroment.clickNeighbours(this);
			}
		}
		
	}

	@Override
	/**
	 *Jobb egérgomb megnyomását kezelő függvény.
	 *Ha a mezőre még nem kattintottak rá(clickde == false) és nem jelölték meg(marked == false)
	 *és a tartalmazó tábla még nem érte el a kihelyeztt zászlók maximális számát(enviroment.flagLimitReached() == false), akkor
	 *a marked tagváltozó értéke igazra módosul, valamint meghívódik a tartalmazó tábla flagplaced() függvénye.
	 *Ha a mezőre még nem kattintottak rá(clicked == false), viszont a mező meg volt jelölve(marked == true), akkor
	 *a marked tagváltozó értéke hamisra módosul, valamint meghívódik a tartalmazó Table flagRemoved() függvénye.
	 *Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt.
	 */
	public void mark() {
		if(enviroment.getPlaying()) {
			if(!clicked ) {
				if(marked) {
					marked = false;
					enviroment.flagRemoved();
				}
				else if(!enviroment.flagLimitReached()){
					marked = true;
					enviroment.flagPlaced();
				}
			}
		}
		EmptyFieldGUI.update(this);
	}

}
