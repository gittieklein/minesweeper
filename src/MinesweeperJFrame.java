

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class MinesweeperJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public MinesweeperJFrame(int rows, int cols, int bombs) 
	{
		setTitle("Minesweeper");	
		setSize(cols*50,rows*50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//window closes when click x
		setResizable(false);	//the user can't resize the screen - ensures the buttons stay squares
		setLocationRelativeTo(null);	//centers the window on the screen
		Container container = getContentPane();	//create container
		((JComponent) container).setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 20));
		container.setLayout(new BorderLayout());
		container.add(new GridComponent(rows,cols,bombs), BorderLayout.CENTER);	//add grid
		container.add(new BelowComponent(bombs), BorderLayout.SOUTH);	//add number of bombs remaining	
	}
	
//	public static void main(String[] args)
//	{
//		MinesweeperJFrame ms = new MinesweeperJFrame(10, 10, 5);
//	}

}
