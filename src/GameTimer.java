import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer{
	
			private int elapsedSeconds ;
			private Timer timer; 
			public GameTimer()
			{
				elapsedSeconds = 0;
				timer = new Timer(0, new ActionListener(){
					public void actionPerformed(ActionEvent evt)
					{
						if (elapsedSeconds < 999)
						{
							elapsedSeconds++;
							BelowComponent.setTimerLabel(String.format("%03d", elapsedSeconds) + " ");
						}
					}
				});
				
			}
			
		
			public void restartTimer(){
				stopTimer();
				startTimer();
			}
			public void startTimer(){
				timer.start();
			}
			public void stopTimer()
			{
				timer.stop();
			}
		

		
	}

