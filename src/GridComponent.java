
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

import Enums.ButtonType;

public class GridComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	private Cell squares[][]; // the grid of squares - they're buttons so you
								// could click them
	private int countButtons;
	private final int ROWS;
	private final int COLS;
	private int bombs;
	private HashMap<String, ImageIcon> images;

	public GridComponent(int r, int c, int bomb)
	{
		countButtons = 0;
		ROWS = r;
		COLS = c;
		bombs = bomb;
		squares = new Cell[ROWS][COLS];
		setLayout(new GridLayout(ROWS, COLS));

		images = new HashMap<String, ImageIcon>();

		setImages();
		buildButtons();
		setBombs();
		setNumbers();
	}

	private void setImages()
	{
		// create array of images
		images.put("zero", new ImageIcon("src/images/zero.png"));
		images.put("one", new ImageIcon("src/images/one.png"));
		images.put("two", new ImageIcon("src/images/two.png"));
		images.put("three", new ImageIcon("src/images/three.png"));
		images.put("four", new ImageIcon("src/images/four.png"));
		images.put("five", new ImageIcon("src/images/five.png"));
		images.put("six", new ImageIcon("src/images/six.png"));
		images.put("seven", new ImageIcon("src/images/seven.png"));
		images.put("eight", new ImageIcon("src/images/eight.png"));
		images.put("mine", new ImageIcon("src/images/mineboard.png"));
		images.put("xmine", new ImageIcon("src/images/xmine.png"));
		images.put("button", new ImageIcon("src/images/button.png"));
		images.put("button-flag", new ImageIcon("src/images/button-flag.png"));
		images.put("flag", new ImageIcon("src/images/flag.png"));

		Image imgTemp;
		for (String key : images.keySet())
		{
			imgTemp = images.get(key).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			images.put(key, new ImageIcon(imgTemp));
		}
	}

	private void buildButtons()
	{
		// loop through the 2D array
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLS; j++)
			{
				squares[i][j] = new Cell(i, j); // create a new button
				squares[i][j].putClientProperty("index1", i); // add property of row location
				squares[i][j].putClientProperty("index2", j); // add property of column location
				squares[i][j].setPreferredSize(new Dimension(40, 40)); // set the size of each button to be a square
				// squares[i][j].setBackground(Color.lightGray); //set the
				// buttons to gray

				// set button image to all buttons
				// squares[i][j].setIcon(icon);

				// TODO generate mouse listener
				squares[i][j].addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Cell button = (Cell) e.getSource();

						// checks if the button was clicked with right or left
						// and calls appropriate method
						if (SwingUtilities.isLeftMouseButton(e))
							leftMouseClick(button);
						if (SwingUtilities.isRightMouseButton(e))
							rightMouseClick(button);
					}

					// methods needed for interface that don't want to do
					// anything
					public void mouseExited(MouseEvent arg0)
					{
					}

					public void mousePressed(MouseEvent arg0)
					{
					}

					public void mouseReleased(MouseEvent arg0)
					{
					}

					public void mouseEntered(MouseEvent arg0)
					{
					}

				});

				this.add(squares[i][j]); // add the button to the grid layout
			}
		}
	}

	private void setBombs()
	{
		Random rand = new Random();
		int x, y;
		for (int i = 0; i < bombs;)
		{
			x = rand.nextInt(ROWS);
			y = rand.nextInt(COLS);
			if (squares[x][y].getButtonType() == ButtonType.BLANK)
			{
				squares[x][y].setButtonType(ButtonType.BOMB);
				squares[x][y].setImg(images.get("mine"));
				i++;
			}
		}
	}

	private void setNumbers()
	{
		ButtonType type = ButtonType.BLANK;
		ImageIcon icon = null;

		// now that all bombs are in place, set the numbers around them
		// check every square, if it's blank, check the cells around it
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLS; j++)
			{
				int numBombs = 0;
				if (squares[i][j].getButtonType() != ButtonType.BOMB)
				{
					// count the bombs around it in all directions

					// 1 cell up
					if (getActiveCell(i - 1, j) != null)
					{
						if (getActiveCell(i - 1, j).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell up to the left
					if (getActiveCell(i - 1, j - 1) != null)
					{
						if (getActiveCell(i - 1, j - 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell up to the right
					if (getActiveCell(i - 1, j + 1) != null)
					{
						if (getActiveCell(i - 1, j + 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell to the left
					if (getActiveCell(i, j - 1) != null)
					{
						if (getActiveCell(i, j - 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell down to the left
					if (getActiveCell(i + 1, j - 1) != null)
					{
						if (getActiveCell(i + 1, j - 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell down
					if (getActiveCell(i + 1, j) != null)
					{
						if (getActiveCell(i + 1, j).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell down to the right
					if (getActiveCell(i + 1, j + 1) != null)
					{
						if (getActiveCell(i + 1, j + 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}

					// 1 cell to the right
					if (getActiveCell(i, j + 1) != null)
					{
						if (getActiveCell(i, j + 1).getButtonType() == ButtonType.BOMB)
							numBombs++;
					}
				}
				else
				{
					numBombs = -1;
				}

				switch (numBombs)
				{
					case -1:
						type = ButtonType.BOMB;
						icon = images.get("mine");
						break;
					case 1:
						icon = images.get("one");
						type = ButtonType.ONE;
						break;
					case 2:
						icon = images.get("two");
						type = ButtonType.TWO;
						break;
					case 3:
						icon = images.get("three");
						type = ButtonType.THREE;
						break;
					case 4:
						icon = images.get("four");
						type = ButtonType.FOUR;
						break;
					case 5:
						icon = images.get("five");
						type = ButtonType.FIVE;
						break;
					case 6:
						icon = images.get("six");
						type = ButtonType.SIX;
						break;
					case 7:
						icon = images.get("seven");
						type = ButtonType.SEVEN;
						break;
					case 8:
						icon = images.get("eight");
						type = ButtonType.EIGHT;
						break;
					default:
						icon = images.get("zero");
						type = ButtonType.BLANK;

				}
				squares[i][j].setButtonType(type);
				squares[i][j].setImg(icon);
			}
		}
	}

	/*
	 * left click: if bomb - flip over all bombs, if there's a flag that's not a bomb show bomb with X, end the game if not bomb - flip over number, plus all adjacent zeros up until including the next number if it's a flag - it can't be clicked if
	 * all numbers are turned over - win
	 * 
	 * 
	 * right click: if blank button - flag, decrease number of bombs if flag - blank, increase number of bombs
	 */
	private void leftMouseClick(Cell button)
	{
		if (button.getButtonType() == ButtonType.BOMB && !images.get("flag").equals(button.getIcon()))
		{
			flipButton(button);
		}
		// if the button has a flag, ignore left click
		else if (!images.get("flag").equals(button.getIcon())) // have to
																// compare flag
																// to button so
																// don't get
																// null pointer
																// if button
																// icon is null
		{

			if (button.getButtonType() == ButtonType.BLANK)
			{
				Stack<Cell> buttonStack = new Stack<Cell>();
				Cell currentButton;

				buttonStack.push(button);

				while (!buttonStack.isEmpty())
				{
					currentButton = buttonStack.pop();

					if (currentButton != null)
					{
						flipButton(currentButton);
						int i = currentButton.getXindex();
						int j = currentButton.getYindex();

						if (currentButton.getButtonType() == ButtonType.BLANK)
						{
							// left
							buttonStack.push(getActiveCell(i - 1, j));

							// left, up
							buttonStack.push(getActiveCell(i - 1, j - 1));

							// left, down
							buttonStack.push(getActiveCell(i - 1, j + 1));

							// up
							buttonStack.push(getActiveCell(i, j - 1));

							// right, up
							buttonStack.push(getActiveCell(i + 1, j - 1));

							// right
							buttonStack.push(getActiveCell(i + 1, j));

							// right, down
							buttonStack.push(getActiveCell(i + 1, j + 1));

							// down
							buttonStack.push(getActiveCell(i, j + 1));
							System.out.println("added to stack");
						}
					}
				}
			}

			else
			{
				flipButton(button);
			}
		}
	}

	private void rightMouseClick(Cell button)
	{
		// only allow a button to be clicked if it was not disabled already on
		// left click.
		if (button.isEnabled())
		{
			if (images.get("flag").equals(button.getIcon())) // have to compare
																// flag to
																// button so
																// don't get
																// null pointer
																// if button
																// icon is null
			{
				button.setIcon(null);
				BelowComponent.editBombs(++bombs);
			}
			else if (bombs > 0)
			{
				// JLabel flagIcon = new JLabel();
				// add number of bombs and bomb image to the component
				// button.setIcon(new ImageIcon("src/images/flag.png"));
				// need to disable left button click.

				button.setIcon(images.get("flag"));
				BelowComponent.editBombs(--bombs);
			}
		}
	}

	private void flipButton(Cell button)
	{
		countButtons++;
		// disable the button once it is flipped over.
		button.setEnabled(false);
		// set the image of the button
		button.setIcon(button.getImg());
		// also set the disabled image so it is not grayed out.
		button.setDisabledIcon(button.getImg());
				
		System.out.println(countButtons);
		if(countButtons == (ROWS * COLS) - bombs)
		{
			System.out.println("Game won");
		}
	}

	private Cell getActiveCell(int x, int y)
	{
		if (x >= 0 && x < ROWS && y >= 0 && y < COLS && squares[x][y].isEnabled())
			return squares[x][y];
		// return null if the cell is not in the grid
		return null;
	}

}
