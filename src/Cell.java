import javax.swing.JButton;

import Enums.ButtonType;

public class Cell extends JButton
{
	//the index of the button in the array
	private int xindex;
	private int yindex;
	
	//what the button has behind it
	private ButtonType type;
	
	public Cell(int x, int y)
	{
		xindex = x;
		yindex = y;
		type = ButtonType.BLANK;	//all squares should be initialized to blank
	}

	public int getXindex()
	{
		return xindex;
	}

	public int getYindex()
	{
		return yindex;
	}
	
	public ButtonType getButtonType()
	{
		return type;
	}
	
	public void setButtonType(ButtonType type)
	{
		this.type = type;
	}

}
