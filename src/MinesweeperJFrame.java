import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.*;

//import MinesweeperGame.LevelActionListener;


public class MinesweeperJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static GameData gameData = GameData.getInstance(9,9,10);
	private static Container container;
	private static int bottom;
	private static int side;
	private static GridComponent grid;
	private static BelowComponent below;
	
	
	public static void main(String[] args)
	{		
		grid = new GridComponent();
		below = new BelowComponent();
		MinesweeperJFrame frame = new MinesweeperJFrame();
		frame.setJMenuBar(createMenu());
		frame.setVisible(true);
	}
	
	public MinesweeperJFrame() 
	{		
		setTitle("Minesweeper");	
		setSize(1330, 826);
		
		//window closes when click x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//the user can't resize the screen - ensures the buttons stay squares
		setResizable(false);
		
		//centers the window on the screen
		setLocationRelativeTo(null);	
		
		//set icon image
		ImageIcon image = new ImageIcon("src/images/icon.png");
		setIconImage(image.getImage());
		
		//create container
		container = getContentPane();	
		
		//set the boarder for dif amounts based on the amount of buttons
		bottom = 688 - (gameData.getRows() * 43);
		side = 1330 - (gameData.getColumns() * 43);
		
		((JComponent) container).setBorder(BorderFactory.createEmptyBorder(40, side/2, bottom, side/2));
		container.setLayout(new BorderLayout());
		
		container.add(grid, BorderLayout.CENTER);	//add grid
		container.add(below, BorderLayout.SOUTH);	//add number of mines remaining	
		
		try
		{
			// Open an audio input stream.
			File soundFile = new File("src/sound/background.au"); // you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.loop(Integer.MAX_VALUE);
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}		
	}	
	
	public static void reset()
	{
		container.remove(grid);
		grid.reset();
		//below = new BelowComponent();
		gameData = GameData.getInstance();
		
		//container.removeAll();
		gameData.changeLevel(gameData.getRows(), gameData.getColumns(), gameData.getTotalMines());
		bottom = 688 - (gameData.getRows() * 43);
		side = 1330 - (gameData.getColumns() * 43);
		((JComponent) container).setBorder(BorderFactory.createEmptyBorder(40, side/2, bottom, side/2));
		container.add(grid, BorderLayout.CENTER);	//add grid
		//container.add(below, BorderLayout.SOUTH);	//add number of mines remaining	
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
				reset();		
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
		
		LevelActionListener listener = new MinesweeperJFrame.LevelActionListener();
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

	/**
	 * action listener for levels
	 */
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
		    	 reset();
		     }
		     else if(level.equals("Intermediate"))
		     {
		    	 GameData gameData = GameData.getInstance();
		    	 gameData.changeLevel(16, 16, 40);
		    	 reset();
		     }
		     else
		     {
		    	 GameData gameData = GameData.getInstance();
		    	 gameData.changeLevel(16, 30, 99);
		    	 reset();
		     }		    	 
		}
	}
}
