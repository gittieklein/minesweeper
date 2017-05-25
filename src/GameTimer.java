import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer implements ActionListener{
	
			private int elapsedSeconds = 0;
			
			public void actionPerformed(ActionEvent evt)
			{
				if (elapsedSeconds < 999)
				{
					elapsedSeconds++;
					BelowComponent.timerLabel.setTimerLabel(String.format("%03d", elapsedSeconds) + " ");
				}
			}
		

		

//		timer = new Timer(1000, new TimerListener());
//		timer.start();		
	}

