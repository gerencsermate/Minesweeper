package Minesweeper;

import java.io.Serializable;


/**
 * BonusField(bónusz mező) típusú mező.
 */
public class BonusField extends Field implements Serializable{
	private static final long serialVersionUID = 1329886815102094326L;
	
	
	/**
	 * konstruktor
	 * @param t a Table, amely tartalmazza
	 * @param r melyik sorban helyezkedik el 
	 * @param c melyik oszlopban helyezkedik el
	 */
	public BonusField(Table t, int r, int c) {
		super(t, r, c);
		enviroment = t;
		button = BonusFieldGUI.init();
		button.addMouseListener(new ClickListener());
	};
	
	@Override
	/**
	 * Bal egérgomb megnyomását kezelő függvény. Ha a mező nincs megjelölve(marked == false), akkor clicked tagváltozó értékét igazra állítja, 
	 * valamint meghívja a tartalmazó Table revealMine() függvényét.
	 * Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt.
	 * 
	 */
	public void click() {
		if(enviroment.getPlaying()) {
			if(!marked) {
				clicked = true;
				enviroment.revealMine();
			}
		}
		BonusFieldGUI.update(this);
		
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
			if(!marked && !clicked && !enviroment.flagLimitReached()) {
				marked = true;
				enviroment.flagPlaced();
			}
			else if(!clicked) {
				marked = false;
				enviroment.flagRemoved();
			}
		}
		BonusFieldGUI.update(this);
		
	}
}
