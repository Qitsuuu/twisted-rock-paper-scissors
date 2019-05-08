package gui.Opposite;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.Random;
import javax.swing.border.Border;
public class Playclass extends Homeclass{

JFrame fr1= new JFrame();
static String ans, butName1, butName2, butName3;
int pcount=1, ppause=1000, timerMax=5;
Timer timer3;
static int count= 100, TIMER_PAUSE=90, progbarLimit=0, counter=0, score;
static JLabel Score= new JLabel(" "+score);
static JLabel text= new JLabel("Score: ");
static JLabel Score2= new JLabel("");
static JProgressBar progressBar = new JProgressBar();
static Timer progressBarTimer;
static JPanel panQuest1= new JPanel();
static JPanel panQuest2= new JPanel();
JLabel[] picQ= new JLabel[108];
JPanel[] subPan= new JPanel[20];
static String butImg[]= {"rock1.png", "paper1.png", "scissors1.png"}; ///////////////ANSWER BUTTONS (Rock, Paper, Scissors)//////////
static String butHover[]= {"rock2.png", "paper2.png", "scissors2.png"};
static String butPressed[]= {"rock3.png", "paper3.png", "scissors3.png"};
static JButton but[]= new JButton[3];
static JButton but2[]= new JButton[3];
static JButton home= new JButton(new ImageIcon(locMain+"back1.png"));
String[]img={
		"p1.png", "p2.png", "p3.png","p4.png","p5.png","p6.png","p7.png","p8.png","p9.png","paper.png", //0-9
		"r1.png", "r2.png", "r3.png", "r4.png", "r5.png", "r6.png", "r7.png", "r8.png", "r9.png", "r10.png", "rock.png", //10-20
		"s1.png", "s2.png", "s3.png", "s4.png", "s5.png", "s6.png", "s7.png", "s8.png", "s9.png", "s10.png", "scissors.png", //21-31
		
		"rr1.png","rr2.png", "rr3.png", "rr4.png", "rr5.png", "rr6.png", //32-37
		"rp1.png", "rp2.png","rp3.png","rp4.png","rp5.png", //38-42
	    "rs1.png", "rs2.png", "rs3.png", "rs4.png", "rs5.png", //43-47
	  
	    "br1.png","br2.png", "br3.png", "br4.png", "br5.png", "br6.png", "br7.png",//48-54
		"bp1.png", "bp2.png","bp3.png","bp4.png","bp5.png", //55-59
		"bs1.png", "bs2.png", "bs3.png", "bs4.png", "bs5.png", //60-64
		
		"ir1.png", "ir2.png","ir3.png","ir4.png","ir5.gif","ir6.png","ir7.png","ir8.png","ir9.png", //65-73
		"ip1.png", "ip2.png","ip3.png","ip4.png","ip5.png","ip6.png","ip7.png","ip8.png","ip9.png", "ip10.png", //74-83
		"is1.png", "is2.png","is3.png","is4.png","is5.png","is6.png", //84-89
		
		"wr1.png", "wr2.png", "wr3.png", "wr4.png", "wr5.png", "wr6.png", //90-95
		"wp1.png", "wp2.png", "wp3.png", "wp4.png", "wp5.png", "wp6.png", //96-101
		"ws1.png", "ws2.png", "ws3.png", "ws4.png", "ws5.png", "ws6.png"}; //102-107
JLabel brainicon = new JLabel(new ImageIcon(locMain+"brain.png"));
JFrame transfr2= new JFrame();
JFrame popFrame= new JFrame();
JPanel butPan= new JPanel();
JPanel panel2 = new JPanel();
JPanel popcon = new JPanel();
JLabel label1= new JLabel(new ImageIcon(locMain+"stage1a.gif"));
JLabel label2= new JLabel(new ImageIcon(locMain+"stage2.gif"));;
JLabel label3= new JLabel(new ImageIcon(locMain+"stage3.gif"));;
JLabel label4= new JLabel(new ImageIcon(locMain+"stage4.gif"));;
JLabel label5= new JLabel(new ImageIcon(locMain+"stage5.gif"));;


	public Playclass()///////////////////////////////////////CONSTRUCTOR : PLAY BUTTON (Action)
	{
			mainbut[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
		  	ConA();
		  	extraBut();
		  		if (pressed==true)
					{
		  				
		  				popUpScreen();
		  				mainCard.show(mainpanel, "start");
						screenbg[1].add(panQuest1, BorderLayout.CENTER);
						startProgressBar();	
					}
					else
					{
						Playpressed=true; //to call the timer
						mainCard.show(mainpanel, "start");
						rpsMethod();
					}
		  		gameSound.stop();
			  	PlayST();
				}
			});
		}
	    ActionListener timerdoes = new ActionListener()///////////////////////G A M E  TIMER/LIFE  B A R//////////////////////////
	    {@Override
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        progressBar.setValue(count);
		        System.out.println(count);
		       
		        if(correct==true)
		        {
		        		score+=1;
		        		Score.setText(" "+score);
		            	count+=15;
		            	correct=false;
		         }
		        else if(wrong==true)
		        {
		    	   	count-=2;
		            wrong=false;
		         }
		        if (count>100)
		        {
		        	count=100;
		        }
		        else if (count<0)
		        {
		        	count=0;
		        }
		        if(count==40) {
		            progressBar.setForeground(Color.RED);
			     }
		        if(count==70)
			     {
		             progressBar.setForeground(Color.YELLOW);
			     }  
		        if(count==80)
			     {
		            progressBar.setForeground(Color.ORANGE);
			     }
		        if(count==96)
			     {
		            progressBar.setForeground(Color.GREEN);
			     }
		        if(count==100)
			     {
		            progressBar.setForeground(Color.MAGENTA);
			     }
		        if (progbarLimit==count && count==0)//////////////////////////ACTION WHEN  T I M E R = 0//////////////////////
		        {
		        		System.out.println("SCORE: "+score);
		            	progressBarTimer.stop();//stop the timer
		            	but[0].setEnabled(false);
		            	but[1].setEnabled(false);
		            	but[2].setEnabled(false);
		            	progressBar.setBackground(Color.pink);
		            	mainframe.setEnabled(false);
		            	panQuest1.removeAll();
		            	home.setVisible(true);
						actionbut[2].setVisible(true);
		            	if (score>=50)
		            	{
		            		failed=false;
		            		ResultST();
		            		playSound.stop();
		            		ResultScreen();
		            		Score2.setForeground(Color.GREEN);
		            		Score2.setFont(new Font("Arial", Font.BOLD,80));
		            		Score2.setText(""+score);
		            		screenbg[3].setLayout(new BorderLayout());
		            		screenbg[3].add(subPan[13], BorderLayout.SOUTH);
		            		subPan[13].add(subPan[14], BorderLayout.CENTER);
		            		subPan[14].setLayout(new GridLayout(2,0,0, 10));
		            		subPan[14].add(Score2);
		            		butPan.setBackground(Color.BLACK);
		            		popCard.show(subPan[18], "congrats");
		            	}
		            	else if (counter==0 || score<50)
		            	{
		            		failed=true;
		            		ResultST();
		            		playSound.stop();
		            		ResultScreen();
		            		Score2.setForeground(Color.CYAN);
			            	Score2.setFont(new Font("Arial", Font.BOLD,120));
			            	Score2.setText(""+score);
							screenbg[4].setLayout(new BorderLayout());
							screenbg[4].add(subPan[13], BorderLayout.SOUTH);
							subPan[13].add(subPan[14], BorderLayout.CENTER);
							subPan[14].setLayout(new GridLayout(1,0,0,0));
							subPan[14].add(Score2);
							butPan.setBackground(new Color(0.8f, 0.2f, 0.7f ,1f));
							popCard.show(subPan[18], "go");
							
		            	}
		            }
		            count--;
		        }
		    };
	 
	    ActionListener back = new ActionListener()//////////////////////////////////////H O M E  &  R E T R Y  B U T T O N////////////
	    { @Override
			public void actionPerformed(ActionEvent balik) {
			if(balik.getSource()==actionbut[3] || balik.getSource()==home)
			{
				ClickST();
				panQuest1.removeAll();
				score=0;
				questIcons();
				count=100;
				counter=0;
				pcount=1;
				 if(score==0 && pcount!=5)
				    {
					 	fr1.dispose();
				    }
				pressed= true;
				Score.setText(""+score);
				mainframe.setEnabled(true);
				mainframe.setOpacity(1.0f);
				popFrame.dispose();
				transfr2.dispose();
				homePanel[11].removeAll();
				homePanel[11].add(actionbut[4]);  homePanel[11].add(actionbut[5]); homePanel[11].add(extra[4]);
				progressBarTimer.stop();
				progressBar.setBackground(Color.GRAY);
				but[0].setEnabled(true);
				but[1].setEnabled(true);
				but[2].setEnabled(true);
				playSound.stop();
				gameSound.stop();
				try
				{
				if(failed==true || failed==false)
				{
					resultSound.stop();
				}
				}catch(Exception e){}
				GameST();
				mainCard.show(mainpanel, "home");
			}
			}	        	 
	    };
	    
	    void retryButton() ///////////////////////////////////////////R E T R Y  B U T T O N//////////////////////////////////////////
	    {
	    	actionbut[1].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ClickST();
					homePanel[11].removeAll();
					homePanel[11].add(actionbut[4]);  homePanel[11].add(actionbut[5]); homePanel[11].add(extra[4]);
					progressBarTimer.stop();
					progressBar.setBackground(Color.GRAY);
					mainframe.setEnabled(true);
					Playpressed=true;
					but[0].setEnabled(true);
					but[1].setEnabled(true);
					but[2].setEnabled(true);
					subPan[6].setVisible(true);
					count=100;
					counter=0;
					pcount=1;
					panQuest1.removeAll();
					score=0;
					questIcons();
					questCard.next(panQuest1);
					fr1.dispose();
					popUpScreen();
					stage.show(panel2, "s1");
					Score.setText(""+score);
					mainframe.setOpacity(1.0f);
					popFrame.dispose();
					transfr2.dispose();
					gameSound.stop();
					resultSound.stop();
					startProgressBar();
					PlayST();
					}
	        });
	    }
	    
	    public void rpsMethod() /////////////////////////////////////P L A Y  S C R E E N ////////////////////////////////////////
	    {
	    	homePanel[11].removeAll();
	    	ConA();
	    	playButs();
	    	questIcons();
	    	popUpScreen();
	    	stage.show(panel2, "s1");
	    	progressBar.setBackground(Color.GRAY);
	    	text.setForeground(Color.YELLOW);
	    	text.setFont(new Font("Arial", Font.BOLD,47));
	    	Score.setForeground(Color.CYAN);
	    	Score.setFont(new Font("Arial", Font.BOLD,65));
	    	startProgressBar();
	    	Border blue= BorderFactory.createLineBorder(Color.PINK, 3);
	    	progressBar.setBorder(blue);
	    	progressBar.setMaximum(100);
		    progressBar.setMinimum(progbarLimit);
		    progressBar.setForeground(Color.GREEN);
		    progressBar.setOrientation(0);
		    screenbg[5].setLayout(new GridLayout(1, 3, 50, 0));
		    screenbg[5].add(actionbut[3]);
		    screenbg[5].add(extra[3]);
		    screenbg[5].add(subPan[17]);
		    subPan[17].setLayout(new FlowLayout(FlowLayout.CENTER));
		    subPan[17].add(text); subPan[17].add(Score);
		    subPan[4].setLayout(new BorderLayout());
		    subPan[4].add(screenbg[5], BorderLayout.NORTH); 
		    subPan[4].add(progressBar);  subPan[4].add(brainicon, BorderLayout.WEST);
		    panRPS.setLayout(new BorderLayout());
    		panRPS.add(screenbg[1], BorderLayout.CENTER);
	    	screenbg[1].setLayout(new BorderLayout());
	    	panQuest1.setOpaque(false);
	    	screenbg[1].add(panQuest1, BorderLayout.CENTER);
	    	panRPS.add( subPan[4], BorderLayout.NORTH);
	    	screenbg[1].add(subPan[5], BorderLayout.SOUTH);
	    	subPan[5].add(subPan[6], BorderLayout.CENTER);
	        but[0].addActionListener(ansEasy);
	        but[1].addActionListener(ansEasy);
	        but[2].addActionListener(ansEasy);
	        actionbut[3].addActionListener(back);
	        retryButton();
	    }
	  	    
	   public void questIcons()
	    {
	    	panQuest1.setLayout(questCard);
	    	for(int x=0; x<1;x++)
	    	{
	    		if (score>=0 && score<15)
			    {
			        	RandomNum=rand.nextInt(32);	
			    }
	    		
	    		else if(score>=15 && score<23)
	    		{
	    			RandomNum=rand.nextInt(64);
	    			
	    			if (RandomNum<=32)
	    			{
	    				RandomNum+=32;
	    			}
	    		}
	    		else if(score>=23 && score<38)
	    		{
	    			RandomNum=rand.nextInt(89);	
	    			if (RandomNum<=24)
	    			{
	    				RandomNum+=65;
	    			}
	    			else if (RandomNum<=45 && RandomNum>=25)
	    			{
	    				{
		    				RandomNum+=41;
		    			}
	    			}
	    			else if (RandomNum<=65 && RandomNum>=46)
	    			{
	    				{
		    				RandomNum+=20;
		    			}
	    			}
	    		}
	    		else if(score>=38 && score<50)
	    		{
	    			RandomNum=rand.nextInt(107);	
	    			if (RandomNum<=17)
	    			{
	    				RandomNum+=90;
	    			}
	    			else if (RandomNum<=35 && RandomNum>=18)
	    			{
	    				{
		    				RandomNum+=72;
		    			}
	    			}
	    			else if (RandomNum<=53 && RandomNum>=36)
	    			{
	    				{
		    				RandomNum+=54;
		    			}
	    			}
	    			else if (RandomNum<=71 && RandomNum>=54)
	    			{
	    				{
		    				RandomNum+=36;
		    			}
	    			}
	    			else if (RandomNum<=89 && RandomNum>=72)
	    			{
	    				{
		    				RandomNum+=18;
		    			}
	    			}
	    		}
	    		else if(score>=50)
	    		{
	    			RandomNum=rand.nextInt(107);
	    		}
	    		picQ[RandomNum]= new JLabel(new ImageIcon(locAttachments+img[RandomNum]));
	    		panQuest1.add(picQ[RandomNum]);
	    
	    		if(score==15 && pcount!=5)
		    	{
	    			fr1.dispose();
	    			pcount=1;
	    			popUpScreen();
	    			stage.show(panel2, "s2");
		    	}
	    		else if(score==23 && pcount!=5)
		    	{
	    			fr1.dispose();
	    			pcount=1;
	    			popUpScreen();
	    			stage.show(panel2, "s3");
		    	}
	    		else if(score==38 && pcount!=5) //wala pa
		    	{
	    			fr1.dispose();
	    			pcount=1;
	    			popUpScreen();
	    			stage.show(panel2, "s4");
		    	}
	    		else if(score==50 && pcount!=5) //wala pa
		    	{
	    			fr1.dispose();
	    			pcount=1;
	    			popUpScreen();
	    			stage.show(panel2, "s5");
		    	}
	    		
	    		//STANDARD
	    		if (RandomNum>=0 && RandomNum<=9) //paper
	    		{
	    			picQ[RandomNum].setName("3");
	    		}
	    		else if (RandomNum>=10 && RandomNum<=20) //rock
	    		{
	    			picQ[RandomNum].setName("2");
	    		}
	    		else if (RandomNum>=21 && RandomNum<=31) //scissors
	    		{
	    			picQ[RandomNum].setName("1");
	    		}
	    		//OPPOSITE
	    		else if (RandomNum>=32 && RandomNum<=37)
	    		{
	    			picQ[RandomNum].setName("3");
	    		}
	    		else if (RandomNum>=38 && RandomNum<=42)
	    		{
	    			picQ[RandomNum].setName("1");
	    		}
	    		else if (RandomNum>=43 && RandomNum<=47)
	    		{
	    			picQ[RandomNum].setName("2");
	    		}
	    		//with Icons
	    		else if (RandomNum>=48 && RandomNum<=54)
	    		{
	    			picQ[RandomNum].setName("2");
	    		}
	    		else if (RandomNum>=55 && RandomNum<=59)
	    		{
	    			picQ[RandomNum].setName("3");
	    		}
	    		else if (RandomNum>=60 && RandomNum<=64)
	    		{
	    			picQ[RandomNum].setName("1");
	    		}
	    		else if (RandomNum>=65 && RandomNum<=73)
	    		{
	    			picQ[RandomNum].setName("2");
	    		}
	    		else if (RandomNum>=74 && RandomNum<=83)
	    		{
	    			picQ[RandomNum].setName("3");
	    		}
	    		else if (RandomNum>=84 && RandomNum<=89)
	    		{
	    			picQ[RandomNum].setName("1");
	    		}
	    		    		
	    		else if (RandomNum>=90 && RandomNum<=95) //rock
	    		{
	    			picQ[RandomNum].setName("2");
	    		}
	    		else if (RandomNum>=96 && RandomNum<=101) //paper
	    		{
	    			picQ[RandomNum].setName("3");
	    		}
	    		else if (RandomNum>=102 && RandomNum<=107) //scissors
	    		{
	    			picQ[RandomNum].setName("1");
	    		}
	    		System.out.println("RANDOM: "+RandomNum);
	    		ans=picQ[RandomNum].getName();
	    	}
	    }
	   
	    void ConA()
	    {
	    for(int x=0; x<subPan.length;x++)
    	{	
    		subPan[x]= new JPanel();
    		subPan[x].setOpaque(false);
    	}
	    }
	    void playButs()
	    {
	    	for(int x=0; x<but.length;x++)
	    	{	
	    		subPan[6].setLayout(new GridLayout(1, 3, 50, 0));
	    		but[x]= new JButton(new ImageIcon(locMain+butImg[x]));
	    		subPan[6].add(but[x]);
	    		but[x].setContentAreaFilled(false);
	    		but[x].setBorder(null);
	    		but[x].setName(""+(x+1));
	    		but[x].setRolloverIcon(new ImageIcon(locMain +butHover[x]));
	    		but[x].setPressedIcon(new ImageIcon(locMain +butPressed[x]));
	    	}
	    	but[0].setName("1"); //ROCK
	    	butName1= but[0].getName();
	    	but[1].setName("2"); //PAPER
	    	butName2= but[1].getName();
	    	but[2].setName("3");//SCISSORS
	    	butName3= but[2].getName();
	    }
	       
	   ActionListener ansEasy = new ActionListener(){@Override
		public void actionPerformed(ActionEvent answerbut) {
		  if(answerbut.getSource()==but[0] )
		  {	 
			  if(ans==butName1)
			  {
				//System.out.println("TAMATAMATMAAAAAAAAAAAAAAAAAAAAA");
				correct=true;
			  }
			 else
		     {
		        //System.out.println("MALIIIIIIIIIII");
		        wrong=true;
		     }
		  }
		  else if(answerbut.getSource()==but[1])
		  {
			if(ans==butName2)
		     {
				//System.out.println("TAMATAMATMAAAAAAAAAAAAAAAAAAAAA");
				correct=true;
		      }
			 else
		     {
		        System.out.println("MALIIIIIIIIIII");
		        wrong=true;
		     }
		  }
		  else if(answerbut.getSource()==but[2])
		  {
			if(ans==butName3)
		     {
				//System.out.println("TAMATAMATMAAAAAAAAAAAAAAAAAAAAA");
				correct=true;
		      }
			 else
		     {
		       // System.out.println("MALIIIIIIIIIII");
		        wrong=true;
		     }
		  }
		  ans="";
		  questIcons();
		 questCard.next(panQuest1);
		 counter+=1;
		System.out.println("COUNTER: "+counter);
	   }
  	 };
  	 
  	 	void startProgressBar() {
		   
			  	progressBarTimer = new Timer(TIMER_PAUSE, timerdoes);
			  	if (Playpressed==true)
				{
			  		progressBarTimer.start();
				}
			  	else if (Playpressed==false)
			  	{
			  		progressBarTimer.stop();
			  	}
		  }
		  
		  public void ResultScreen() //Instruction method // Score
		  {
				 {
					mainframe.setAlwaysOnTop(false);
					mainframe.setOpacity(0.4f);
				 	transparentfr2();
					popFrame.setUndecorated(true);
					popFrame.setVisible(true);
					popFrame.setShape(new RoundRectangle2D.Double(0,0, 605, 415, 60, 60)); 
					popFrame.setSize(605, 415);
					popFrame.setAlwaysOnTop(true);
					popFrame.setLocationRelativeTo(null);
					popFrame.getContentPane().setBackground(null);
					popFrame.add(popcon, BorderLayout.CENTER);
					subPan[18].setOpaque(false);
					popcon.setLayout(new BorderLayout());
					popcon.add(subPan[18], BorderLayout.CENTER);
					subPan[18].setOpaque(false);
					subPan[18].setLayout(popCard);
					subPan[18].add(screenbg[3], "congrats");
					subPan[18].add(screenbg[4], "go");
					popcon.add(butPan, BorderLayout.SOUTH);
					butPan.add(home, new FlowLayout(FlowLayout.CENTER));
					home.setContentAreaFilled(false);
					home.setBorder(null);
					home.setRolloverIcon(new ImageIcon(locMain+"back2.png"));
					home.setPressedIcon(new ImageIcon(locMain+"back3.png"));
					home.addActionListener(back);
					butPan.add(actionbut[1], new FlowLayout(FlowLayout.CENTER));
					//CustomCursor();
				 }
		  }
		  public void transparentfr2() ///////////////Semi-Transparent frame behind the MainFrame used as borderline
			{	
				w=620; h=425; e=60;
				transfr2.setUndecorated(true);
				transfr2.setShape(new RoundRectangle2D.Double(0,0, w, h, e, e));
				transfr2.setSize(w, h);
				transfr2.setLocationRelativeTo(null);
				transfr2.getContentPane().setBackground(Color.BLACK);
				transfr2.setOpacity(0.7f);
				transfr2.setVisible(true);
				transfr2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			private ActionListener TimerAction3 = new ActionListener()//////////////////////ACTION WHILE LOADING TIMER IS RUNNING///////////////////
					 {@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
						 System.out.println("count: " +pcount);
						   if (pcount==timerMax && pcount==5) {
							   timer3.stop();
							   fr1.dispose();//dispose of container
						      }
						        pcount++;
						     }
						   };
						   
			 private void popUpScreen()//////////////////////////////////////LOADING SCREEN/////////////////////////////
					 {
						fr1.getContentPane().setBackground(new Color(254, 228, 0, 70));
						fr1.setUndecorated(true);
						fr1.setAlwaysOnTop(true);
						fr1.pack();
						fr1.setBounds(200,320, 265, 100);
						fr1.add(panel2);
						fr1.setShape(new RoundRectangle2D.Double(0,0, 265, 100, 50, 50));
						label1.setFont(new Font("Arial", Font.BOLD, 50));
						panel2.setLayout(stage);
						panel2.setOpaque(false);
						panel2.add(label1, "s1"); panel2.add(label2, "s2");
						panel2.add(label3, "s3"); panel2.add(label4, "s4"); panel2.add(label5, "s5");
						fr1.setVisible(true);
						popTimer();
					 }
					void popTimer()//////////////////////////////LOADING TIMER/////////////////////////////
						{
						    timer3 = new Timer(ppause, TimerAction3);
						    timer3.start();
						}
}