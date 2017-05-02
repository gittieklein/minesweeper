

import java.awt.*;

import javax.swing.*;

public class BelowComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private static JLabel bombLabel;
	
	public BelowComponent(int bombs)
	{	
		setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		//scale the image so you could set the size
		bombLabel = new JLabel(bombs + " ");
		JLabel bombIcon = new JLabel();
		ImageIcon icon = new ImageIcon("src/images/mine.png");
		Image img = icon.getImage();
		Image bombimg = img.getScaledInstance(47, 47, Image.SCALE_SMOOTH);
		icon = new ImageIcon(bombimg);
		bombIcon.setIcon(icon);
	
		bombLabel.setFont(new Font("Calibri", Font.PLAIN, 32));	//set font and size of text

		//add number of bombs and bomb image to the component
		this.add(bombLabel);
		this.add(bombIcon);
	}
	
	public static void editBombs(int bombs)
	{
		bombLabel.setText(bombs + " ");
	}
	
}


