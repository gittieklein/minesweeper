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
	private static Timer timer;
	
	
	public BelowComponent()
	{	
		setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		GameData gameData = GameData.getInstance();
	
		//set up timer and timer image
		JLabel timeIcon = new JLabel();
		ImageIcon ticon = new ImageIcon("src/images/timer.png");
		Image timg = ticon.getImage();
		Image timeimg = timg.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
		ticon = new ImageIcon(timeimg);
		timeIcon.setIcon(ticon);
		
		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		 
		this.add(timeIcon);
		this.add(timerLabel);
		
		//set up mines and mine image
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
		 
		 timer = new Timer(1000, new TimerListener());
		 timer.start();
		
	}
	
	public void stopTimer(){
		timer.stop();
	}
	public static void editMines(int mines)
	{
		mineLabel.setText(mines + " ");
	}
	
	
	
}


