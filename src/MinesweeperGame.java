import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MinesweeperGame
{	
	public static void main(String args[]) 
	{
		//create a game data object - the same object will be used through out so that all classes have access to the same info
		GameData gameData = GameData.getInstance(16, 16, 40);
		MinesweeperJFrame frame = new MinesweeperJFrame();
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
		JMenu menuNew, menuExit, selectLevel;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//New Game for menu
		menuNew = new JMenu("New Game");
		menuNew.setMnemonic(KeyEvent.VK_N); //ALT-N will start a new game
		
		//listener to start a new game when new game is clicked
		menuNew.addMenuListener(new MenuListener()
		{
			//methods needed for the interface that are not needed here
			public void menuCanceled(MenuEvent arg0){}
			public void menuDeselected(MenuEvent arg0){}

			public void menuSelected(MenuEvent arg0)
			{
				new MinesweeperGame();	//not sure if this works
			}
		});
		menuBar.add(menuNew);
	
		selectLevel = new JMenu("Select Level");
		JMenuItem beginner, intermediate, advanced;
		beginner = new JMenuItem("Beginner");
		intermediate = new JMenuItem("Intermediate");
		advanced = new JMenuItem("Advanced");
		selectLevel.add(beginner);
		selectLevel.add(intermediate);
		selectLevel.add(advanced);
		
		LevelActionListener listener = new MinesweeperGame.LevelActionListener();
		beginner.addActionListener(listener);
		intermediate.addActionListener(listener);
		advanced.addActionListener(listener);
		
		menuBar.add(selectLevel);
		
		//Exit game for menu
		menuExit = new JMenu("Exit");
		menuExit.setMnemonic(KeyEvent.VK_X); //ALT-X will close game
		
		//listener to close the game when exit is clicked
		menuExit.addMenuListener(new MenuListener()
		{
			//methods needed for the interface that are not needed here
			public void menuCanceled(MenuEvent arg0){}
			public void menuDeselected(MenuEvent arg0){}
			public void menuSelected(MenuEvent arg0)
			{
				System.exit(0);	
			}
		});	
		
		menuBar.add(menuExit);
		
		return menuBar;
	}

	public static class LevelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			 JMenuItem menuItem = (JMenuItem)e.getSource();
		     String level = menuItem.getText();		
		     
		     if(level.equals("Beginner"))
		     {
		    	 GameData gameData = GameData.getInstance();
		    	 gameData.changeLevel(9, 9, 10);
		     }
		     else if(level.equals("Intermediate"))
		     {
		    	 GameData gameData = GameData.getInstance();
		    	 gameData.changeLevel(16, 16, 40);
		     }
		     else
		     {
		    	 GameData gameData = GameData.getInstance();
		    	 gameData.changeLevel(16, 30, 99);
		     }
		    	 
		}
	}
}
