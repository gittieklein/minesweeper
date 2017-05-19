import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;


public class MinesweeperJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public MinesweeperJFrame() 
	{
		GameData gameData = GameData.getInstance();
		
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
		Container container = getContentPane();	
		
		//set the boarder for dif amounts based on the amount of buttons
		int side, bottom;
		bottom = 688 - (gameData.getRows() * 43);
		side = 1330 - (gameData.getColumns() * 43);
		
		((JComponent) container).setBorder(BorderFactory.createEmptyBorder(40, side/2, bottom, side/2));
		container.setLayout(new BorderLayout());
		
		container.add(new GridComponent(), BorderLayout.CENTER);	//add grid
		container.add(new BelowComponent(), BorderLayout.SOUTH);	//add number of mines remaining	
		
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
	
	
}
