import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BelowComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private static JLabel mineLabel;
	private static JLabel timerLabel;
	private int amount;
	private Label space;
	private GameData gameData = GameData.getInstance();

	public BelowComponent()
	{
		setLayout(new FlowLayout(FlowLayout.CENTER));

		//timer image
		JLabel timeIcon = new JLabel();
		ImageIcon ticon = new ImageIcon("src/images/timer.png");
		Image timg = ticon.getImage();
		Image timeimg = timg.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
		ticon = new ImageIcon(timeimg);
		timeIcon.setIcon(ticon);

		timerLabel = new JLabel("000");
		timerLabel.setFont(new Font("Calibri", Font.PLAIN, 32));

		this.add(timeIcon);
		this.add(timerLabel);

		amount = (gameData.getColumns() * 43) / 9;
		space = new Label(String.format("%" + (amount) + "s", ""));

		this.add(space);

		// set up mines and mine image
		mineLabel = new JLabel(gameData.getTotalMines() + " ");
		JLabel mineIcon = new JLabel();
		ImageIcon icon = new ImageIcon("src/images/mine.png");
		Image img = icon.getImage();
		Image mineimg = img.getScaledInstance(47, 47, Image.SCALE_SMOOTH);
		icon = new ImageIcon(mineimg);
		mineIcon.setIcon(icon);

		mineLabel.setFont(new Font("Calibri", Font.PLAIN, 32)); // set font and size of text

		// add number of mines and mine image to the component
		this.add(mineLabel);
		this.add(mineIcon);
	}

	public void reset()
	{
		amount = (gameData.getColumns() * 43) / 9;
		space.setText(String.format("%" + (amount) + "s", ""));
	}

	public static void setTimerLabel(String text)
	{
		BelowComponent.timerLabel.setText(text);
	}

	public static void editMines(int mines)
	{
		mineLabel.setText(mines + " ");
	}

}
