

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

import Enums.ButtonType;

public class GridComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	private Cell squares[][];	//the grid of squares - they're buttons so you could click them
	private final int ROWS;
	private final int COLS;
	private final int BOMBS;
	
	public GridComponent(int r, int c, int bomb)
	{
		ROWS = r;
		COLS = c;
		BOMBS = bomb;
		squares = new Cell[ROWS][COLS];
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
				squares[i][j] = new Cell(i, j);	//create a new button
				squares[i][j].putClientProperty("index1", i);	//add property of row location
				squares[i][j].putClientProperty("index2", j);	//add property of column location
				squares[i][j].setPreferredSize(new Dimension(40, 40)); //set the size of each button to be a square
				squares[i][j].setBackground(Color.lightGray);	//set the buttons to gray
						
				//TODO generate mouse listener
				squares[i][j].addMouseListener(new MouseListener() 
				{ 
					
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Cell button = (Cell) e.getSource();
						
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
		int x, y;
		for(int i = 0; i < BOMBS;)
		{
			x = rand.nextInt(ROWS);
			y = rand.nextInt(COLS);
			if(squares[x][y].getButtonType() == ButtonType.BLANK)
			{
				squares[x][y].setButtonType(ButtonType.BOMB);
				i++;
			}
		}
		
		//now that all bombs are in place, set the numbers around them
		//check every sqaure, if it's blank, check the cells around it 
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				int numBombs = 0;
				if(squares[i][j].getButtonType() == ButtonType.BLANK)
				{
					//count the bombs around it in all directions
					if(i > 0)
					{
						//1 cell up
						if(squares[i-1][j].getButtonType() == ButtonType.BOMB) numBombs++;
						
						if(j > 0)
						{
							//1 cell up to the left
							if(squares[i-1][j-1].getButtonType() == ButtonType.BOMB) numBombs++;
						}
						
						if(j < COLS - 1)
						{
							//1 cell up to the right
							if(squares[i-1][j+1].getButtonType() == ButtonType.BOMB) numBombs++;
						}
					}
					
					if(j > 0)
					{
						//1 cell to the left
						if(squares[i][j-1].getButtonType() == ButtonType.BOMB) numBombs++;
						
						if(i < ROWS - 1)
						{
							//1 cell down to the left
							if(squares[i+1][j-1].getButtonType() == ButtonType.BOMB) numBombs++;
						}
					}
					
					if(i < ROWS - 1)
					{
						//1 cell down
						if(squares[i+1][j].getButtonType() == ButtonType.BOMB) numBombs++;
						
						if(j < COLS - 1)
						{
							//1 cell down to the right
							if(squares[i+1][j+1].getButtonType() == ButtonType.BOMB) numBombs++;
						}
					}
					
					//1 cell to the right
					if(j < COLS - 1)
					{
						if(squares[i][j+1].getButtonType() == ButtonType.BOMB) numBombs++;
					}
					
					ButtonType type = ButtonType.BLANK;
					switch (numBombs)
					{
						case 1:
							type = ButtonType.ONE;
							break;
						case 2:
							type = ButtonType.TWO;
							break;
						case 3: 
							type = ButtonType.THREE;
							break;
						case 4: 
							type = ButtonType.FOUR;
							break;
						case 5:
							type = ButtonType.FIVE;
							break;
						case 6:
							type = ButtonType.SIX;
							break;
						case 7:
							type = ButtonType.SEVEN;
							break;
						case 8:
							type = ButtonType.EIGHT;
							break;
						default:
							type = ButtonType.BLANK;		
					}
					squares[i][j].setButtonType(type);
				}
			}
		}
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
	private void leftMouseClick(Cell button)
	{
		System.out.println(button.getYindex());
		System.out.println(button.getButtonType());
	}
	
	private void rightMouseClick(Cell button)
	{
		if(button.getModel().isPressed())
		{
		 //this doesnt work	button.setIcon(null);
		}
		else
		{
			JLabel flagIcon = new JLabel();
			//add number of bombs and bomb image to the component
			button.setIcon(new ImageIcon("src/images/flag.png"));
			//need to disable left button click.
		}		
	}

}
