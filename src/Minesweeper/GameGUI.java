package Minesweeper;



import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * A játékot megjelenítő JFrame
 *
 */
public class GameGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 646874620553911114L;
	
	/**
	 * A GameController osztály, amely a játékot irányítja.
	 */
	private GameController game;
	
	/**
	 * menüsáv
	 */
	private JMenuBar menuBar;
	/**
	 * játékmenettel kapcsolatos menüpont
	 */
	private JMenu gameMenu;
	
	/**
	 * új klasszikus játékmód
	 */
	private JMenu newSimpleGame;
	
	/**
	 * új bónusz mezős játék
	 */
	private JMenu newBonusGame;
	
	/**
	 * új 7x7-es klasszikus játék 
	 */
	private JMenuItem newGame7;
	/**
	 * új 11x11-es klasszikus játék 
	 */
	private JMenuItem newGame11;
	/**
	 * új 15x15-ös klasszikus játék 
	 */
	private JMenuItem newGame15;
	/**
	 * új 7x7-es bónusz mezős játék 
	 */
	private JMenuItem newBonusGame7;
	/**
	 * új 11x11-es bónusz mezős játék 
	 */
	private JMenuItem newBonusGame11;
	/**
	 * új 15x15-ös bónusz mezős játék 
	 */
	private JMenuItem newBonusGame15;
	/**
	 * játék betöltése menüpont
	 */
	private JMenuItem loadGame;
	/**
	 * játék mentése menüpont
	 */
	private JMenuItem saveGame;
	/**
	 * toplista menüpont
	 */
	private JMenuItem scorelineMenu;
	/**
	 * játékmező
	 */
	private JInternalFrame board;
	/**
	 * toplista
	 */
	private JTable sc;
	/**
	 * toplistát megjelenítő frame
	 */
	private JInternalFrame scScreen;
	/**
	 * eltelt időt megjelenítő frame
	 */
	private JTextField timeField;
	/**
	 * eltelt időt megjelenítő frame frissítését végző timer
	 */
	private Timer timer;
	
	/**
	 * konstruktor
	 * inicializálja a framet
	 */
	public GameGUI(GameController g) {
		game = g;
		
		
		setTitle("Minesweeper");
		setSize(800, 800);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new SavingWindowListener());
		setLayout(new BorderLayout());
		
		JLabel jl1 = new JLabel("Minesweeper", JLabel.CENTER);
		jl1.setFont(new Font("Arial", Font.BOLD, 50));
		add(jl1, BorderLayout.NORTH);
		
		ImageIcon img = new ImageIcon("images/logo.png");
		setIconImage(img.getImage());
		add(new JLabel(img), BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		
		gameMenu = new JMenu("Game");
		
		newSimpleGame = new JMenu("New simple game");
		newBonusGame = new JMenu("New game with bonus fields");
		
		newGame7 = new JMenuItem("7x7 (7 mines)");
		newGame7.addActionListener(new newSimpleGame7ActionListener());
		newGame11 = new JMenuItem("11x11 (25 mines)");
		newGame11.addActionListener(new newSimpleGame11ActionListener());
		newGame15 = new JMenuItem("15x15 (35 mines)");
		newGame15.addActionListener(new newSimpleGame15ActionListener());
		
		newSimpleGame.add(newGame7);
		newSimpleGame.add(newGame11);
		newSimpleGame.add(newGame15);
		
		gameMenu.add(newSimpleGame);
		
		newBonusGame7 = new JMenuItem("7x7 (7 mines)");
		newBonusGame7.addActionListener(new newBonusGame7ActionListener());
		newBonusGame11 = new JMenuItem("11x11 (25 mines)");
		newBonusGame11.addActionListener(new newBonusGame11ActionListener());
		newBonusGame15 = new JMenuItem("15x15 (35 mines)");
		newBonusGame15.addActionListener(new newBonusGame15ActionListener());
		
		newBonusGame.add(newBonusGame7);
		newBonusGame.add(newBonusGame11);
		newBonusGame.add(newBonusGame15);
		
		gameMenu.add(newBonusGame);
		
		saveGame = new JMenuItem("Save game");
		saveGame.addActionListener(new saveGameActionListener());
		gameMenu.add(saveGame);
		
		loadGame = new JMenuItem("Load game");
		loadGame.addActionListener(new loadGameActionListener());
		
		gameMenu.add(loadGame);
		
		
		sc = new JTable();
		sc.setModel(game.getScoreline());
		
		scScreen = new JInternalFrame("Scoreline", false, true);
		scScreen.setLayout(new GridLayout(1,0));
		scScreen.setSize(200, 200);
		scScreen.setVisible(false);
		scScreen.add(sc);
		add(scScreen);
		
		scorelineMenu = new JMenuItem("Scoreline");
		scorelineMenu.addActionListener(new ShowScorelineActionListener());
		
		timeField = new JTextField(10);
		timeField.setEditable(false);
		timer = new Timer(1000, new refreshTimeActionListener());
		
		
		
		menuBar.add(gameMenu);
		menuBar.add(scorelineMenu);
		menuBar.add(timeField);
		
		setJMenuBar(menuBar);
		
		
		
	}
	
	/**
	 * Kirajzolja a játékmezőt. Lekérdezi az aktuális játék táblájából a mezőket reprezentáló JButton objektumokat és ezekből építi fel a pályát.
	 * @param t
	 */
	public void drawGame(Table t) {
		if(board != null) {
			remove(board);
		}
		board = new JInternalFrame();
		board.setLayout(new GridLayout(t.getColumnCount(), t.getRowCount()));
		for(int i = 0; i < t.getRowCount(); i++) {
			for(int j = 0; j < t.getColumnCount(); j++) {
				board.add(t.getValueAt(i, j));
			}
		}
		board.setVisible(true);
		timer.start();
		add(board);
	}
	
	/**
	 * Felugró ablakot jelenít meg, ha a játékos nyert, valamint a név megadása után hozzáadja a kátékost a toplistához.
	 */
	public void win() {
		timer.stop();
		game.addPlayerToScoreline(JOptionPane.showInputDialog("You won!\nPlease type your name:", null));
	}
	
	/**
	 * Felugró ablakot jelenít meg, ha a játékos veszített.
	 */
	public void lose() {
		timer.stop();
		JOptionPane.showMessageDialog(board, "Game over");
	}

	/**
	 * 7x7-es klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newSimpleGame7ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(7, 7, 0);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * 11x11-es klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newSimpleGame11ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(11, 20, 0);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * 15x15-ös klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newSimpleGame15ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(15, 40, 0);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * 7x7-es klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newBonusGame7ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(7, 7, 3);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * 11x11-es klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newBonusGame11ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(11, 20, 8);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * 15x15-ös klasszikus játékot létrehozó menüponthoz tartozó ActionListener. Kattintásra létrehozza a játékot és meghívja a GamGUI.drawGame(Table t) függvényt.
	 */
	class newBonusGame15ActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.newGame(15, 35, 20);
			drawGame(game.getBoard());
		}
	}
	
	/**
	 * savegame menüponthoz tartozó ActionListener. Kattintásra elmenti az aktuális játékot.
	 * Meghívja a GameController.saveGame(String filename) függvéynt.
	 *
	 */
	class saveGameActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(board != null) {
				game.saveGame(JOptionPane.showInputDialog("Filename:"));
				timer.stop();
			}
		}
	}
	
	/**
	 * loadgame menüponthoz tartozó ActionListener. Kattintásra betölti a megadott.
	 * Meghívja a GameController.loadGame(String filename) függvéynt.
	 *
	 */
	class loadGameActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			game.loadGame(JOptionPane.showInputDialog("Filename:"));
			GameGUI.this.drawGame(game.getBoard());
		}
	}
	
	/**
	 * A játék bezárásakor hívódik. Leállás előtt elmenti a toplistát.
	 */
	class SavingWindowListener extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
			game.saveScoreline();
			e.getWindow().dispose();
			game.stopPlaying();
		}
	}
	
	/**
	 *A scoreline menüponthoz tartozó ActionListener. Kattintásra megjeleníti a toplistát.
	 */
	class ShowScorelineActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			scScreen.setVisible(true);
		}
		
	}
	
	/**
	 * Másodpercenként frissíti a timeField mezőt.
	 */
	class refreshTimeActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			timeField.setText("Time: " + game.getCurrentGame().getCurrentTime() + "(m : s)");
		}
	}
	
}
