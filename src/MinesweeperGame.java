import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import GUI.MinesweeperJFrame;

public class MinesweeperGame
{

	public static void main(String args[]) 
	{
		int rows = 16;
		int cols = 30;
		int bombs = 130;
		MinesweeperJFrame frame = new MinesweeperJFrame(rows, cols, bombs);
		frame.setJMenuBar(createMenu());
		frame.setVisible(true);
	}
	

	/**
	 * create a menu bar to be added to the frame
	 * @return the menu bar
	 */
	private static JMenuBar createMenu()
	{
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuNew, menuExit;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		//a group of JMenuItems
		menuNew = new JMenuItem("New Game", KeyEvent.VK_T);
		menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));	//CTRL-N will start a new game
		menu.add(menuNew);

		menuExit = new JMenuItem("Exit");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));	//CTRL-ESC will close game
		menu.add(menuExit);
		
		return menuBar;
	}
	
}
