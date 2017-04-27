

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

public class GridComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	private JButton squares[][];	//the grid of squares - they're buttons so you could click them
	private final int ROWS;
	private final int COLS;
	private final int BOMBS;
	
	public GridComponent(int r, int c, int bomb)
	{
		ROWS = r;
		COLS = c;
		BOMBS = bomb;
		squares = new JButton[ROWS][COLS];
		setLayout(new GridLayout(ROWS,COLS));
		
		buildButtons();
		setBombs();
	}

	private void buildButtons()
	{
		//loop through the 2D array
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				squares[i][j] = new JButton();	//create a new button
				squares[i][j].putClientProperty("index1", i);	//add property of row location
				squares[i][j].putClientProperty("index2", j);	//add property of column location
				squares[i][j].setPreferredSize(new Dimension(40, 40)); //set the size of each button to be a square
				
				//TODO generate mouse listener
				squares[i][j].addMouseListener(new MouseListener() 
				{ 
					
					@Override
					public void mouseClicked(MouseEvent e)
					{
						JButton button = (JButton) e.getSource();
						
						//checks if the button was clicked with right or left and calls appropriate method
						if(SwingUtilities.isLeftMouseButton(e)) leftMouseClick(button);
						if(SwingUtilities.isRightMouseButton(e)) rightMouseClick(button);	
					}

					//methods needed for interface that don't want to do anything
					public void mouseExited(MouseEvent arg0){}
					public void mousePressed(MouseEvent arg0){}
					public void mouseReleased(MouseEvent arg0){}
					public void mouseEntered(MouseEvent arg0){}
					
				} );
				
				this.add(squares[i][j]);	//add the button to the grid layout
			}
		}
	}
	
	private void setBombs()
	{
		Random rand = new Random();
		//TODO set the bombs based on random number of row and column
	}
	
	/*
	 * left click:
	 * if bomb - flip over all bombs, if there's a flag that's not a bomb show bomb with X, end the game
	 * if not bomb - flip over number, plus all adjacent zeros up until including the next number
	 * if it's a flag - it can't be clicked
	 * if all numbers are turned over - win
	 * 
	 * 
	 * right click:
	 * if blank button - flag, decrease number of bombs
	 * if flag - blank, increase number of bombs
	 */
	private void leftMouseClick(JButton button)
	{
		
	}
	
	private void rightMouseClick(JButton button)
	{
		
	}

}
