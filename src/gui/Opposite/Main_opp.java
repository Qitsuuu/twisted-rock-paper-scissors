package gui.Opposite;
import javax.swing.SwingUtilities;

public class Main_opp extends Homeclass{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				Playclass display= new Playclass();
				display.loadingScreen();
			}
			});
	}
}
