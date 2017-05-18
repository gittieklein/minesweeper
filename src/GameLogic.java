import java.util.Stack;

import Enums.ButtonType;

public class GameLogic
{
	private GameData gameData = GameData.getInstance();
	private boolean isFirst;
	
	public GameLogic()
	{
		isFirst = true;
	}
	
	/*
	 * left click: if mine - flip over all mines, if there's a flag that's not a mine show mine with X, end the game if not mine - flip over number, plus all adjacent zeros up until including the next number if it's a flag - it can't be clicked if
	 * all numbers are turned over - win
	 * 
	 * 
	 * right click: if blank button - flag, decrease number of mines if flag - blank, increase number of mines
	 */
	/*public void leftMouseClick(Cell button)
	{
		if(isFirst)
		{
			isFirst = false;
			
			setMines(button);
			setNumbers();
		}
		if (button.getButtonType() == ButtonType.MINE && !images.get("flag").equals(button.getIcon()))
		{
			flipButton(button);
			GameOver();
		}
		// if the button has a flag, ignore left click
		else if (!images.get("flag").equals(button.getIcon())) 
		{
			if (button.getButtonType() == ButtonType.BLANK)
			{
				Stack<Cell> buttonStack = new Stack<Cell>();
				Cell currentButton;

				buttonStack.push(button);

				while (!buttonStack.isEmpty())
				{
					currentButton = buttonStack.pop();

					if (currentButton != null && currentButton.isEnabled())
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
	
	private void GameOver() 
	{
		///NED WAS HERE
		
		for (int i = 0; i < gameData.getRows(); i++)
		{
			for (int j = 0; j < gameData.getColumns(); j++)
			{
				if(squares[i][j].getButtonType() == ButtonType.MINE)
				{
					squares[i][j].setEnabled(false);
					
					squares[i][j].setIcon(squares[i][j].getImg());
					
					squares[i][j].setDisabledIcon(squares[i][j].getImg());
				}
			}
		}
		
	}


	private void rightMouseClick(Cell button)
	{
		// only allow a button to be clicked if it was not disabled already on left click.
		if (button.isEnabled())
		{
			if (images.get("flag").equals(button.getIcon())) // have to compare flag to button so don't get  null pointer if button icon is null
			{
				button.setIcon(null);
				BelowComponent.editMines(gameData.addRemainingMines(1));
			}
			else if (gameData.getRemainingMines() > 0)
			{
				button.setIcon(images.get("flag"));
				BelowComponent.editMines(gameData.addRemainingMines(-1));
			}
		}
	}

	private void flipButton(Cell button)
	{
		countButtons++;
		// disable the button once it is flipped over
		button.setEnabled(false);
		// set the image of the button
		button.setIcon(button.getImg());
		// also set the disabled image so it is not grayed out.
		button.setDisabledIcon(button.getImg());
				
		System.out.println(countButtons);
		if(countButtons == (gameData.getRows() * gameData.getColumns()) - gameData.getTotalMines())
		{
			System.out.println("Game won");
		}
		System.out.println("flip button " + countButtons);
	}

	private Cell getActiveCell(int x, int y)
	{
		if (x >= 0 && x < gameData.getRows() && y >= 0 && y < gameData.getColumns() && squares[x][y].isEnabled())
			return squares[x][y];
		// return null if the cell is not in the grid
		return null;
	}
*/

}
