import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Enums.ButtonType;

public class Minesweeper {

	private HashMap<String, ImageIcon> images;

	private GameData gameData;

	// number of buttons so know when the player won
	private int countButtons;

	// used so that the user can't click on a bomb their first turn
	private boolean isFirst;

	// the grid of squares - they're buttons so you could click them
	private Cell squares[][];

	private GameTimer timer;

	public Minesweeper() {
		images = new HashMap<String, ImageIcon>();
		gameData = GameData.getInstance();
		squares = new Cell[gameData.getRows()][gameData.getColumns()];
		countButtons = 0;
		isFirst = true;
		timer = GameTimer.getInstance();

	}

	/**
	 * Method to resize and add all the games images to a HashMap.
	 */
	public void setImages() {
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
		images.put("flag", new ImageIcon("src/images/flag.png"));
		images.put("hit-mine", new ImageIcon("src/images/hit_bomb.png"));

		// resize all the images to fit the squares
		Image imgTemp;
		for (String key : images.keySet()) {
			if (images.get(key).equals(images.get("flag"))) {
				imgTemp = images.get(key).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH);
			} else {
				imgTemp = images.get(key).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			}
			images.put(key, new ImageIcon(imgTemp));
		}
	}

	/**
	 * Method to build a button with an even handler.
	 * @param x The row of the buttons location on the board
	 * @param y The column of the buttons location on the board.
	 * @return The new button that was created.
	 */
	public Cell buildButton(int x, int y) {
		squares[x][y] = new Cell(x, y);

		squares[x][y].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
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
				public void mouseExited(MouseEvent arg0) {
				}
	
				public void mousePressed(MouseEvent arg0) {
				}
	
				public void mouseReleased(MouseEvent arg0) {
				}
	
				public void mouseEntered(MouseEvent arg0) {
				}
			});

		return squares[x][y];
	}

	/**
	 * Method to put random bombs throughout the grid
	 * @param firstButton The first button that was clicked should never have a bomb.
	 */
	private void setMines(Cell firstButton) {
		Random rand = new Random();
		int x, y;
		for (int i = 0; i < gameData.getTotalMines();) {
			x = rand.nextInt(gameData.getRows());
			y = rand.nextInt(gameData.getColumns());
			if (squares[x][y].getButtonType() == ButtonType.BLANK && !squares[x][y].equals(firstButton)) {
				squares[x][y].setButtonType(ButtonType.MINE);
				squares[x][y].setImg(images.get("mine"));
				i++;
			}
		}
	}

	/**
	 * Method to count the number of bombs each cell is touching and set the image to that cell.
	 */
	private void setNumbers() {
		
		ButtonType type = ButtonType.BLANK;
		ImageIcon icon = null;
		Cell current;

		// if the cell is blank, check the cells around it
		for (int i = 0; i < gameData.getRows(); i++) {
			for (int j = 0; j < gameData.getColumns(); j++) {
				int numMines = 0;
				if (squares[i][j].getButtonType() != ButtonType.MINE) {
					
					// 1 cell up
					current = getActiveCell(i - 1, j);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell up to the left
					current = getActiveCell(i - 1, j - 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell up to the right
					current = getActiveCell(i - 1, j + 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell to the left
					current = getActiveCell(i, j - 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell down to the left
					current = getActiveCell(i + 1, j - 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell down
					current = getActiveCell(i + 1, j);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell down to the right
					current = getActiveCell(i + 1, j + 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// 1 cell to the right
					current = getActiveCell(i, j + 1);
					if (current != null && current.getButtonType() == ButtonType.MINE)
						numMines++;

					// set the button type and image of the cell based on the surrounding
					// number of bombs
					switch (numMines) {
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
	}

	/**
	 * Method to determine what should be done when a button is clicked with the left mouse button.
	 * @param button The button that was clicked.
	 */
	public void leftMouseClick(Cell button) {

		if (button.isEnabled()) {

			if (isFirst) {

				isFirst = false;
				timer.startTimer();
				setMines(button);
				setNumbers();
			}
			if (button.getButtonType() == ButtonType.MINE && !images.get("flag").equals(button.getIcon())) {
				try {
					// Open an audio input stream.
					File soundFile = new File("src/sound/mine.au"); // you could
																	// also get
																	// the sound
																	// file with
																	// an URL
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
					// Get a sound clip resource.
					Clip clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input
					// stream.
					clip.open(audioIn);
					clip.start();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				button.setImg(images.get("hit-mine"));

				timer.stopTimer();
				gameOver();
			}
			
			// if the button has a flag, ignore left click
			else if (!images.get("flag").equals(button.getIcon())) {
				if (button.getButtonType() == ButtonType.BLANK) {
					Stack<Cell> buttonStack = new Stack<Cell>();
					Cell currentButton;

					buttonStack.push(button);

					while (!buttonStack.isEmpty()) {
						currentButton = buttonStack.pop();

						if (currentButton != null && currentButton.isEnabled()) {
							// only flip the button if it is not a flag
							if (!images.get("flag").equals(currentButton.getIcon()))
								flipButton(currentButton);
							int i = currentButton.getXindex();
							int j = currentButton.getYindex();

							if (currentButton.getButtonType() == ButtonType.BLANK) {
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
							}
						}
					}
				} else {
					flipButton(button);
				}
			}
		}
		if (isFinished()) {
			timer.stopTimer();

			ImageIcon image = new ImageIcon("src/images/win.png");
			ImageIcon win = new ImageIcon(image.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH));

			int clicked = JOptionPane.showOptionDialog(null, "Congratulations you won!", "Game won",
					JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, win,
					new String[] { "New Game", "Cancel" }, null);
			if (clicked == 0) {
				MinesweeperJFrame.reset();

			}
		}

	}

	/**
	 * Method to see if the game was finished.
	 * @return If the game was finished.
	 */
	private boolean isFinished() {
		return countButtons == (gameData.getRows() * gameData.getColumns() - gameData.getTotalMines());
	}

	/**
	 * This method flips over all the mines and shows a dialog box to either
	 * restart or cancel the round.
	 */
	private void gameOver() {
		for (int i = 0; i < gameData.getRows(); i++) {
			for (int j = 0; j < gameData.getColumns(); j++) {
				squares[i][j].setEnabled(false);

				if (images.get("flag").equals(squares[i][j].getIcon())
						&& squares[i][j].getButtonType() != ButtonType.MINE) {
					squares[i][j].setDisabledIcon(images.get("xmine"));
					squares[i][j].setIcon(images.get("xmine"));

				}

				else if (images.get("flag").equals(squares[i][j].getIcon())
						&& squares[i][j].getButtonType() == ButtonType.MINE) {
					squares[i][j].setDisabledIcon(images.get("flag"));
					squares[i][j].setIcon(images.get("flag"));

				}

				else if (squares[i][j].getButtonType() == ButtonType.MINE) {
					squares[i][j].setDisabledIcon(squares[i][j].getImg());
					squares[i][j].setIcon(squares[i][j].getImg());
				}

			}
		}
		ImageIcon image = new ImageIcon("src/images/gameOver.png");
		ImageIcon Lose = new ImageIcon(image.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH));

		int clicked = JOptionPane.showOptionDialog(null, null, "Game Over", JOptionPane.CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, Lose, new String[] { "New Game", "Cancel" }, null);
		if (clicked == 0) {
			MinesweeperJFrame.reset();
		}
	}

	/**
	 * 
	 * @param button
	 */
	private void rightMouseClick(Cell button) {
		// only allow a button to be clicked if it was not disabled already on
		// left click.
		if (button.isEnabled()) {
			if (images.get("flag").equals(button.getIcon())) // have to compare
															// flag to
															// button so
															// don't get
															// null pointer
															// if button
															// icon is null
			{
				button.setIcon(null);
				BelowComponent.editMines(gameData.addRemainingMines(1));
			} else if (gameData.getRemainingMines() > 0) {
				button.setIcon(images.get("flag"));
				BelowComponent.editMines(gameData.addRemainingMines(-1));
			}
		}
	}

	/**
	 * Method to flip over a button.
	 * @param button The button to flip over.
	 */
	private void flipButton(Cell button) {
		countButtons++;
		// disable the button once it is flipped over
		button.setEnabled(false);
		// set the image of the button
		button.setIcon(button.getImg());
		// also set the disabled image so it is not grayed out.
		button.setDisabledIcon(button.getImg());

	}

	/**
	 * Get a cell if it is enabled. 
	 * @param x The row of the button
	 * @param y The column of the button
	 * @return The button that is enabled or null if it is disabled.
	 */
	private Cell getActiveCell(int x, int y) {
		if (x >= 0 && x < gameData.getRows() && y >= 0 && y < gameData.getColumns() && squares[x][y].isEnabled())
			return squares[x][y];
		// return null if the cell is not in the grid
		return null;
	}

	/**
	 * Method to reset the board
	 * @return True if the new board is the same size or false if it is different.
	 */
	public boolean reset() {
		countButtons = 0;
		isFirst = true;

		if (gameData.getRows() == squares.length && gameData.getColumns() == squares[0].length) {
			// go through the buttons and reset the images and button types
			for (int i = 0; i < squares.length; i++) {
				for (int j = 0; j < squares[i].length; j++) {
					squares[i][j].setButtonType(ButtonType.BLANK);
					squares[i][j].setIcon(null);
					squares[i][j].setEnabled(true);
				}
			}

			// reset the number of mines used.
			gameData.resetRemainingMines();

			return true;
		}
		return false;

	}

	/**
	 * Method to get the number of rows in the squares grid.
	 * @return The length of rows in the squares grid.
	 */
	public int getSquaresRows() {
		return squares.length;
	}

	/**
	 * Method to get the number of columns in the squares grid
	 * @param row The row to get the count of columns for.
	 * @return The length of columns in the squares grid.
	 */
	public int getSquaresColumns(int row) {
		return squares[row].length;
	}

	/**
	 * Method to reset the size of the squares grid
	 */
	public void resetSize() {
		squares = new Cell[gameData.getRows()][gameData.getColumns()];
	}

	/**
	 * Method to get a button
	 * @param x The row coordinate of the button
	 * @param y The column coordinate of the button
	 * @return The cell at the given coordinates. 
	 */
	public Cell getButton(int x, int y) {
		return squares[x][y];
	}
}
