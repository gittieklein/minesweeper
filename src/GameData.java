
public class GameData
{
	private static GameData singelton = null;
	private final int ROWS;
	private final int COLS;
	private final int TOTAL_MINES;
	private int remaining_mines;
	
	/**
	 * Private constructor can only be called from inside the class
	 * makes sure only one instance of game data
	 */
	
	private GameData(int r, int c, int m)
	{
		ROWS = r;
		COLS= c;
		TOTAL_MINES = remaining_mines = m;
	}
	
	public static GameData getInstance() 
	{
		return singelton;
	}
	
	public static GameData getInstance(int r, int c, int m)
	{
		if(singelton == null)
		{
			//protect from multi-threading
			synchronized (GameData.class)
			{
				if(singelton == null)
				{
					singelton = new GameData(r, c, m);
				}
			}
		}		
		return singelton;
	}
	
	/**
	 * getters and setters
	 */
	public int getColumns()
	{
		return COLS;
	}
	
	public int getRows()
	{
		return ROWS;
	}
	
	public int getRemainingMines()
	{
		return remaining_mines;
	}
	
	public int getTotalMines()
	{
		return TOTAL_MINES;
	}
	
	/**
	 * increases or decreases remaining mines and returns the new number
	 * @param m 1 or -1 (add or remove)
	 * @return
	 */
	public int addRemainingMines(int m)
	{
		remaining_mines += m;
		return remaining_mines;
	}
	
}
