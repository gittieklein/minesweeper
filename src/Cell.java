import javax.swing.JButton;

public class Cell extends JButton
{
	private int xindex;
	private int yindex;
	
	public Cell(int x, int y)
	{
		xindex = x;
		yindex = y;
	}

	public int getXindex()
	{
		return xindex;
	}

	public void setXindex(int xindex)
	{
		this.xindex = xindex;
	}

	public int getYindex()
	{
		return yindex;
	}

	public void setYindex(int yindex)
	{
		this.yindex = yindex;
	}
}
