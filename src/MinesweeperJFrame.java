import java.awt.*;
import javax.swing.*;

public class MinesweeperJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public MinesweeperJFrame() 
	{
		GameData gameData = GameData.getInstance();
		
		setTitle("Minesweeper");	
		setSize(1330, 840);
		
		//window closes when click x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//the user can't resize the screen - ensures the buttons stay squares
		setResizable(false);
		
		//centers the window on the screen
		setLocationRelativeTo(null);	
		
		//set icon image
		ImageIcon image = new ImageIcon("src/images/mine.png");
		setIconImage(image.getImage());
		
		//create container
		Container container = getContentPane();	
		((JComponent) container).setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 20));
		container.setLayout(new BorderLayout());
		
		container.add(new GridComponent(), BorderLayout.CENTER);	//add grid
		container.add(new BelowComponent(), BorderLayout.SOUTH);	//add number of mines remaining	
	}
	
	
}
