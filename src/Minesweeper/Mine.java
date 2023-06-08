package Minesweeper;

import java.io.Serializable;


/**
 * Mine(bomba) típusú mező.
 */
public class Mine extends Field implements Serializable{
	private static final long serialVersionUID = 956966675657823004L;
	/**
	 * Igaz, ha egy bónuszmező felfedte.
	 */
	private boolean revealed;
	
	
	/**
	 * konstruktor
	 * @param t a Table, amely tartalmazza
	 * @param r melyik sorban helyezkedik el 
	 * @param c melyik oszlopban helyezkedik el
	 */
	public Mine(Table t, int r, int c){
		super(t, r, c);
		marked = false;
		revealed = false;
		button = MineGUI.init(this);
		button.addMouseListener(new ClickListener());
	}
	
	
	
	
	@Override
	/**
	 * Bal egérgomb megnyomását kezelő függvény. Ha a bombát egy bónuszmező nem fedte fel(revealed == false) és nincs megejölve(marked == false),
	 * akkor a clicked tagváltozó értéke igazra módosul, valamint ha játékos még nem vesztett, akkor a tartalmazó Table mineFound() függvénye hívódik.
	 * Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt. 
	 */
	public void click() {
		if(enviroment.getPlaying()) {
			if(!marked && !revealed) {
				clicked = true;
				if(enviroment.getLost() == false) {
					enviroment.mineFound();
				}
			}
		}
		MineGUI.update(this);
	}

	@Override
	/**
	 *Jobb egérgomb megnyomását kezelő függvény.
	 *Ha a bombára még nem kattintottak rá(clickde == false) és nem jelölték meg(marked == false)
	 *és nem is fedték fel(revealed == false) és a tartalmazó tábla még nem érte el a kihelyeztt zászlók maximális számát(enviroment.flagLimitReached() == false), akkor
	 *akkor a marked tagváltozó értéke igazra módosul, valamint meghívódik a tartalmazó tábla minespotted() függvénye.
	 *Ha a mezőre még nem kattintottak rá(clicked == false) és bónuszmező sem fedte fel(revealed == false), viszont a mező meg volt jelölve(marked == true), akkor
	 *a marked tagváltozó értéke hamisra módosul, valamint meghívódik a tartalmazó Table mineUnspotted függvénye.
	 *Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt.
	 */
	public void mark() {
		if(enviroment.getPlaying()) {
			if(!clicked && !marked && !revealed && !enviroment.flagLimitReached()) {
				marked = true;
				enviroment.mineSpotted();
			}
			else if(!clicked && !revealed && marked) {
				marked = false;
				enviroment.mineUnspotted();
			}
		}
		MineGUI.update(this);
	}
	
	/**
	 * Akkor hívódik meg, ha egy bónuszmező felfedi a bombát.
	 * Ha a mezőre még nem kattintottak rá(clicked == false) és nincs is megjelölve (marked == false), akkor a revealed tagváltozó értéke igazra módosul,
	 * valamint meghívódik a tartalmezó Table mineSpotted függvénye.
	 * Tagváltozók beállítása után meghívja a MineGUI.update(Mine m) függvényt.
	 */
	public void reveal() {
		if(!clicked && !marked) {
			revealed = true;
			enviroment.mineSpotted();
		}
		MineGUI.update(this);
	}
	
	/**
	 * getter
	 * @return revealed tagváltozó értéke
	 */
	public boolean getRevealed() {
		return revealed;
	}
}
