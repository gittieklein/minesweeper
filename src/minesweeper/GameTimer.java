package minesweeper;

import minesweeper.BottomStrip;
import java.awt.event.*;
import javax.swing.Timer;

public class GameTimer
{
	private static GameTimer gameTimer = null;
	private int elapsedSeconds;
	private Timer timer;

	private GameTimer()
	{
		elapsedSeconds = 0;
		timer = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (elapsedSeconds < 999)
				{
					elapsedSeconds++;
					BottomStrip.setTimerLabel(String.format("%03d", elapsedSeconds) + " ");
				}
			}
		});
	}
	
	public static GameTimer getInstance()
	{
		if(gameTimer == null)
		{
			gameTimer = new GameTimer();
		}
		
		return gameTimer;	
	}

	public void resetTimer()
	{
		stopTimer();
		//elapsedSeconds = 0;
		BottomStrip.setTimerLabel("000");
	}

	public void startTimer()
	{
		elapsedSeconds = 0;
		timer.start();
	}

	public void stopTimer()
	{
		timer.stop();
	}

}
