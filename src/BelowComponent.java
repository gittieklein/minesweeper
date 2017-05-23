import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BelowComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private static JLabel mineLabel;
	private static JLabel timerLabel;
	
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
		
		 
		 timerLabel = new JLabel();
		 timerLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		 
		 //sets a margin to the right.
		 timerLabel.setBorder(new EmptyBorder(0,0,0,250));

		 this.add(timerLabel);
		
	}
	
	public static void startTimer()
	{
		
		
		 
		 class TimerListener implements ActionListener{
			    int elapsedSeconds = 0;

			    public void actionPerformed(ActionEvent evt){
			    	 
			    if(elapsedSeconds < 999)
		          { 
			    	elapsedSeconds++;
			        timerLabel.setText(elapsedSeconds + " ");	       
			        	
			        }
			    
			    }

			}
		 
		 Timer timer = new Timer(1000, new TimerListener());
		 timer.start();
			
	}
	public static void editMines(int mines)
	{
		mineLabel.setText(mines + " ");
	}
	
	
	
}


