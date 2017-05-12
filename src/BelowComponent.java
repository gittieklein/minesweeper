import java.awt.*;
import javax.swing.*;

public class BelowComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private static JLabel mineLabel;
	
	public BelowComponent()
	{	
		setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		GameData gameData = GameData.getInstance();
		
		//scale the image so you could set the size
		mineLabel = new JLabel(gameData.getTotalMines() + " ");
		JLabel mineIcon = new JLabel();
		ImageIcon icon = new ImageIcon("src/images/mine.png");
		Image img = icon.getImage();
		Image mineimg = img.getScaledInstance(47, 47, Image.SCALE_SMOOTH);
		icon = new ImageIcon(mineimg);
		mineIcon.setIcon(icon);
	
		mineLabel.setFont(new Font("Calibri", Font.PLAIN, 32));	//set font and size of text

		//add number of mines and mine image to the component
		this.add(mineLabel);
		this.add(mineIcon);
	}
	
	public static void editMines(int mines)
	{
		mineLabel.setText(mines + " ");
	}
	
}


