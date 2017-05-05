import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Enums.ButtonType;

public class Cell extends JButton
{
	//the index of the button in the array
	private int xindex;
	private int yindex;
	
	//what the button has behind it
	private ButtonType type;
	private ImageIcon img;
	
	//know if the button was pressed - meaning the number is showing
	private boolean isPressed;
	
	public boolean isPressed()
	{
		return isPressed;
	}

	public void pressed()
	{
		this.isPressed = true;
	}

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
	
	public ImageIcon getImg()
	{
		return img;
	}
	
	public void setImg(ImageIcon img)
	{
		this.img = img;
	}

}
