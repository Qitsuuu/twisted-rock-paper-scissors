package gui.Opposite;

import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Homeclass extends JFrame implements ActionListener{
static //////////////////////////////////////////////////////L O C A T I O N S////////////////////////////////////////////////////
	//String loc="C:/Users/Qitty/Desktop/";

	String loc="C:/Users/Qitty/Desktop/Attachments/";
	static String locAttachments =loc + "attachments/";
	static String locMain =loc + "Main/";
	static String sound =loc + "Sounds/";
///////////////////////////////////////////////////////S O U N D S/////////////////////////////////////////////////////
	static Clip gameSound, playSound, resultSound, clickSound;
////////////////////////////////////////////////////////L A Y  O U T ////////////////////////////////////////////////////
	CardLayout stage=new CardLayout();
	CardLayout mainCard= new CardLayout();
	CardLayout questCard=new CardLayout();
	CardLayout popCard= new CardLayout();
	GridLayout menuGrid= new GridLayout(2, 3, 0, 0);
	GridLayout exitGrid= new GridLayout(2, 2, 30, 0);
	GridLayout playMenuGrid= new GridLayout(5, 2, 10, 10);
/////////////////////////////////////////////////////H O M E  S C R E E N//////////////////////////////////////////////////
	static JFrame mainframe= new JFrame();
	static JFrame transfr= new JFrame();
	static JPanel mainpanel= new JPanel();
	static JPanel panRPS= new JPanel();static JPanel panInstruction= new JPanel(); static JPanel panExit= new JPanel();
/////////////////////////////////////////////////////A R R A Y S//////////////////////////////////////////////////////////
	static JButton actionbut[]= new JButton[6]; //Play, Instruction, Exit
	JButton[] extra= new JButton[7];
	JPanel[] homePanel= new JPanel[12];
	JButton[] mainbut= new JButton[3];
	JLabel [] screenbg= new JLabel[8];
	String [] bg= {"titlefinal.gif", "Playbg.png", "Aboutbg.png", "Congratulations2.gif", "GameOver.gif", "banner.png", "Exitbg.png", "Playbg2.png"}; 
	String mainbutImg[]= {"Play.png", "About.png", "Exit.png"};
	String mainbutHover[]= {"Play2.png", "About2.png", "Exit2.png"};
	String mainbutPressed[]= {"Play3.png", "About3.png", "Exit3.png"};
	String butImgA[]= {"back1.png", "retry1.png", "ok1.png", "home.png", "yes1.png", "no1.png"};
	String butHoverA[]= {"back2.png", "retry2.png", "ok2.png", "home2.png", "yes2.png", "no2.png"};
	String butPressedA[]= {"back1.png", "retry3.png", "ok3.png", "home3.png", "yes3.png", "no3.png"};
	static Random rand= new Random();
	static boolean correct= false,  wrong= false, pressed= false, Playpressed= false, failed=false;
	int w, h, e, RandomNum;
	////////////////////////////////////////////LOADING SCREEN DECLARATIONS//////////////////////////////////////////////////
	int lcount= 1, pause=40, progbMax=100;
	JProgressBar progb1= new JProgressBar();
	JLabel label= new JLabel(new ImageIcon(locMain+"loadsc.gif"));
	Border cyan=BorderFactory.createLineBorder(Color.CYAN, 3);
	Timer progressbTimer;
	
	public Homeclass()
	{
		for(int x=0; x<actionbut.length;x++)
    	{	
			actionbut[x]= new JButton(new ImageIcon(locMain+butImgA[x]));
			actionbut[x].setRolloverIcon(new ImageIcon(locMain +butHoverA[x]));
			actionbut[x].setPressedIcon(new ImageIcon(locMain +butPressedA[x]));
    		actionbut[x].setContentAreaFilled(false);
    		actionbut[x].setBorder(null);
    		actionbut[x].setBorderPainted(false);
    		actionbut[x].addActionListener(this);
    	}
	
		for(int x=0; x<screenbg.length; x++)
		{
			screenbg[x]= new JLabel(new ImageIcon(locMain+bg[x]));
		}
		for(int x=0; x<homePanel.length;x++)
		{
			System.out.println(x);
			homePanel[x]= new JPanel(); homePanel[x].setOpaque(false);
		}
		e=60; w=960; h=660;
		
		transparentfr();
		CustomCursor();
		GameST();
		mainframe.setUndecorated(true);
		mainframe.setSize(w, h); 
		mainframe.setShape(new RoundRectangle2D.Double(0,0, w, h, e, e));
		mainframe.setAlwaysOnTop(true);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(new BorderLayout());
		mainframe.add(mainpanel, BorderLayout.CENTER);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
		mainpanel.setLayout(mainCard);
		mainpanel.setBackground(Color.ORANGE);
		mainpanel.add(screenbg[0],"home");
		screenbg[0].setLayout(new BorderLayout());
		screenbg[0].add(homePanel[0], BorderLayout.SOUTH);
		homePanel[0].add(homePanel[1], BorderLayout.CENTER);
		for(int x=0; x<mainbut.length;x++)
    	{	
    		homePanel[1].setLayout(menuGrid);
    		mainbut[x]= new JButton(new ImageIcon(locMain+mainbutImg[x]));
    		mainbut[x].setRolloverIcon(new ImageIcon(locMain +mainbutHover[x]));
    		mainbut[x].setPressedIcon(new ImageIcon(locMain +mainbutPressed[x]));
    		homePanel[1].add(mainbut[x]);
    		mainbut[x].setContentAreaFilled(false);
    		mainbut[x].setBorder(null);
    		mainbut[x].addActionListener(this);
    	}
		extraBut();
		homePanel[1].add(extra[0]); homePanel[1].add(extra[1]);
		mainpanel.add(panRPS, "start");
		mainpanel.add(panInstruction, "about");
		mainpanel.add(panExit, "exit");
	}
	
	void InstructionPanel() ////////////////////////////INSTRUCTION PANEL////////////////////////
	{
		panInstruction.setLayout(new BorderLayout());
		panInstruction.setBackground(Color.BLUE);
		homePanel[2].setLayout(new BorderLayout());
		panInstruction.add(screenbg[2], BorderLayout.CENTER);
		screenbg[2].setLayout(new BorderLayout());
		screenbg[2].add(homePanel[5], BorderLayout.SOUTH);
		homePanel[5].setLayout(new BorderLayout());
		homePanel[5].add(homePanel[6], BorderLayout.WEST);
		homePanel[6].add(actionbut[0]);
		actionbut[0].addActionListener(this);
		mainCard.show(mainpanel, "about");
	}
	 void ExitPanel() ////////////////////////////////////EXIT PANEL/////////////////////////////////
	 {
		panExit.setLayout(new BorderLayout());
		panExit.setBackground(Color.BLACK);
		panExit.add(homePanel[7], BorderLayout.SOUTH);
		homePanel[7].setLayout(new BorderLayout());
		homePanel[7].add(screenbg[6], BorderLayout.CENTER);
		homePanel[7].add(homePanel[10], BorderLayout.SOUTH);
		homePanel[10].add(homePanel[11]);
		homePanel[11].setLayout(exitGrid);
		homePanel[11].add(actionbut[4]);  homePanel[11].add(actionbut[5]); homePanel[11].add(extra[4]);
		mainCard.show(mainpanel, "exit");
	 }
	
	@Override
	public void actionPerformed(ActionEvent click)//////////////////////ACTION FOR MAIN BUTTONS///////////////////////////////
	{
		if(click.getSource()==mainbut[1])
		{
			ClickST();
			InstructionPanel();
		}
		if(click.getSource()==mainbut[2])
		{
			ClickST();
			ExitPanel();
		}
		if(click.getSource()==actionbut[4])
		{
			ClickST();
			 System.exit(0);
		}
		if(click.getSource()==actionbut[0] || click.getSource()==actionbut[5])
		{
			ClickST();
			mainCard.show(mainpanel, "home");
		}
	}
	
	void extraBut() //////////////////////////////////////////////extra buttons for spacing/////////////////////////////////
	{
		for(int x=0; x<extra.length;x++)
		{
			extra[x]= new JButton();
			extra[x].setContentAreaFilled(false);
			extra[x].setBorder(null);
			extra[x].setEnabled(false);
		}
	}
	
	public void transparentfr() ////////////////////////Semi-Transparent frame behind the MainFrame used as borderline
	{	
		int w= 980, h=680;
		transfr.setUndecorated(true);
		transfr.setShape(new RoundRectangle2D.Double(0,0, w, h, e, e));
		transfr.setSize(980, 680);
		transfr.setLocationRelativeTo(null);
		transfr.getContentPane().setBackground(Color.ORANGE);
		transfr.setOpacity(0.7f);
		transfr.setVisible(true);
		transfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	public void GameST() //////////////////////////////////////////////////SOUND: Game Main/////////////////////////////
	 {
		 try
			{
			File soundFile = new File(sound + "chubbycat.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			gameSound = AudioSystem.getClip();
			gameSound.open(audioIn);
			gameSound.start();
			gameSound.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch (UnsupportedAudioFileException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
	 }
	
	public void PlayST() /////////////////////////////////////////////////////SOUND : PLAY SOUND//////////////////////////////
	 {
		String soundclip[]= {"codegeek.wav","clockworktale.wav","happyinn.wav", "castle.wav","cyber.wav", "find.wav","happyinn.wav", "krab.wav","macaron.wav"};
		RandomNum=rand.nextInt(9);
		 try
			{
			File soundFile = new File(sound+soundclip[RandomNum]);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			playSound = AudioSystem.getClip();
			playSound.open(audioIn);
			playSound.start();
			playSound.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch (UnsupportedAudioFileException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
	 }
	public void ResultST() ////////////////////////////////////////////SOUND: RESULTS Sound (either sad or happy)///////////////////////
	 {
		 try
			{
			if( failed==true)
			 {
				 File soundFile = new File(sound + "Sad.wav");
				 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
					resultSound = AudioSystem.getClip();
					resultSound.open(audioIn);
					resultSound.start();
					resultSound.loop(Clip.LOOP_CONTINUOUSLY);
			 }
			else if( failed==false)
			 {
				 File soundFile = new File(sound + "icy.wav");
				 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
					resultSound = AudioSystem.getClip();
					resultSound.open(audioIn);
					resultSound.start();
					resultSound.loop(Clip.LOOP_CONTINUOUSLY);
			 }
			
			}
			catch (UnsupportedAudioFileException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
	 }
	
	public void ClickST() ///////////////////////////////////////////////////SOUND: when Clicking a button////////////////////////
	{
		try
		{
		File soundFile = new File(sound + "MarioJump.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clickSound = AudioSystem.getClip();
		clickSound.open(audioIn);
		clickSound.start();
		}
		catch (UnsupportedAudioFileException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (LineUnavailableException e1)
		{
			e1.printStackTrace();
		}
	}
	 
	public void CustomCursor()/////////////////////////////////////////////////CURSOR//////////////////////////////////////////
	{
		try
		{
		mainframe.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(locMain+"cursor.png").getImage(), new Point(0,0), "custom cursor"));
		}
		catch(Exception e){}}

	private ActionListener loading = new ActionListener()//////////////////////ACTION WHILE LOADING TIMER IS RUNNING///////////////////
	 {@Override
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		 progb1.setValue(lcount);
		 System.out.println(lcount);
			if (lcount==1)
			{
			 progb1.setForeground(Color.RED);
			}
			if (lcount==15)
			{
				 progb1.setForeground(Color.ORANGE);
			}
			if (lcount==30)
			{
				 progb1.setForeground(Color.YELLOW);
			}
			if (lcount==45)
			{
				 progb1.setForeground(Color.GREEN);
			}
			if (lcount==60)
			{
				 progb1.setForeground(Color.BLUE);
			}
			if (lcount==75)
			{
				 progb1.setForeground((new Color(0.3f, 0.1f, 0.9f,1f)));
			}
			if (lcount==90)
			{
				 progb1.setForeground((new Color(0.8f, 0.2f, 0.7f ,1f)));
			}
		   if (progbMax==lcount && lcount==100) {
		       dispose();//dispose of container
		       progressbTimer.stop();
		       transfr.setOpacity(0.7f);
		       mainframe.setOpacity(1f);
		      }
		        lcount++;
		     }
		   };
		   void loadingScreen()//////////////////////////////////////LOADING SCREEN/////////////////////////////
		    {
		        Container container = getContentPane();
		        JPanel panel = new JPanel();
		        panel.setBackground(Color.BLACK);
		        container.add(panel, BorderLayout.CENTER);
		        panel.add(label);
		       	container.add(progb1, BorderLayout.SOUTH);
		        progb1.setOrientation(0);
		    	progb1.setBorder(cyan);
		        progb1.setMaximum(progbMax);
		        setUndecorated(true);
		        transfr.setOpacity(0f);
		        mainframe.setOpacity(0f);
		        pack();
		        setLocationRelativeTo(null);
		        setVisible(true);
		        startProgressBar2();
		    }
		  private void startProgressBar2()//////////////////////////////LOADING TIMER/////////////////////////////
		     {
		    	 progressbTimer = new Timer(pause, loading);
		    	 progressbTimer.start();
		     }
}
