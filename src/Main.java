//package LiJacob;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.util.ArrayList;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.applet.*;


public class Main extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //sees if a plant is selected from the seed
    private boolean plantChosen;
    
    //dimensions of the game
    private int WIDTH;
    private int HEIGHT;
   
    private ArrayList<Zombie> zombies;
    private ArrayList<Plant> plants;
    private ArrayList<Projectile> projectiles;
    
   //zombies at the plant selection
    private ArrayList<Zombie> preview, sorted; //what is sorted even for??
   
    private Lawn theLawn;
    
    private Lane lane1;
    private Lane lane2;
    private Lane lane3;
    private Lane lane4;
    private Lane lane5;
    
    private SeedSlot picks; // what you picked to rock and roll
    private SeedSlot choices; //your seedslot
    
    private Seed selected;
    
    private Plant pHolder;
    
    //SURIEL ADDED
    private int mx, my; //To show you where the user clicked.  Will be deleted when project is done.
    
    private SunBank bank;
    private ArrayList<Sun> s; //for all the sun on the screen 
    private long sunTime;
    
    private Shovel shovel;
    
    private ArrayList<Explosions> explosion;

    // Added by Gemini to track zombie count for debugging
    private int lastZombieCount = 0;
    
    //STAGES OF THE GAME
    private boolean intro;
    private boolean help;
    private boolean seedSelection;
    private boolean canStart;
    private boolean startRound;
    private boolean gameOver;
    private boolean victory;
    private boolean congrats;
    
    private boolean goodbye; //if you quit like a noob lol
    
    //lists of the level
    private Level1 lvl1;
    
    //PICS!!!
    private ImageIcon img1, img2, img3, img4, img5, img6, img7, img8;
    
    private int randSong;
    private long startMusic;
    private long song1Len;
    private long song2Len;
    private boolean newMusic;
    private boolean startNewMusic;
    private AudioClip introSong, selectionSong, startSong1, startSong2, latterSong1, latterSong2, victorySong, loseSong;
    private boolean start1, start2, start3, start4, start5, start6, start7, start8, start9, start10;
    
    //Default Constructor
    public Main()
    {
        //initializing instance variables
        WIDTH = 1250;
        HEIGHT = 700;
       
        //int lane = (int)(Math.random() * 5); //lane wasn't used
       
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        projectiles = new ArrayList<Projectile>();
        
        preview = new ArrayList<Zombie>();
        
        startMusic = System.currentTimeMillis();
        randSong = (int)(Math.random() * 2 + 1);
        song1Len = 192000;
        song2Len = 123000;
        newMusic = false;
        startNewMusic = false;
        
        introSong = Applet.newAudioClip(this.getClass().getResource("TitleSong.wav"));
        selectionSong = Applet.newAudioClip(this.getClass().getResource("SeedSelectionSong.wav"));
        startSong1 = Applet.newAudioClip(this.getClass().getResource("startSong1.wav"));
        startSong2 = Applet.newAudioClip(this.getClass().getResource("startSong2.wav"));
        //latterSong1 = Applet.newAudioClip(this.getClass().getResource("LatterSong1.wav"));
        //latterSong2 = Applet.newAudioClip(this.getClass().getResource("LatterSong2.wav"));
        loseSong = Applet.newAudioClip(this.getClass().getResource("LoseSong.wav"));
        victorySong = Applet.newAudioClip(this.getClass().getResource("VictorySong.wav"));
        
        
        start1 = false;
        start2 = false;
        start3 = false;
        start4 = false;
        start5 = false;
        start6 = false;
        start7 = false;
        start8 = false;
        start9 = false;
        start10 = false;
        
        //creating the setup for zombies
        for(int i = 0; i < 7; i++)//7 zombies for view (arbitrary number)
        {
            int randX = (int)(Math.random() * 200 + 970);
            int randY = (int)(Math.random() * 450 + 50);
            
            if(i < 1)//first zombie will be a normal one within those coor
                preview.add(new Zombie(randX, randY));
            else if(i < 2)
            {
                boolean failed = false;
                while(failed == false)
                {
                    failed = true;
                    for(int j = 0; j < preview.size(); j++)
                    {
                        int dis = Math.abs(preview.get(j).getY() - randY);
                        while(dis < 100 && failed == true)
                        {
                            randY = (int)(Math.random() * 450 + 50);
                            dis = Math.abs(preview.get(j).getY() - randY);
                        }
                    }
                }
                
                preview.add(new Zombie(randX, randY));
                /*
                boolean failed = false;
                for(int j = 0; j < preview.size(); j++)
                {
                    int dis = Math.abs(preview.get(j).getY() - randY);
                    while(dis < 100 && failed == true)
                    {
                        randY = (int)(Math.random() * 450 + 50);
                        dis = Math.abs(preview.get(j).getY() - randY);
                    }
                }
                preview.add(new Zombie(randX, randY));
                */
            }
            else if(i < 4)
            {
                boolean failed = false;
                for(int j = 0; j < preview.size(); j++)
                {
                    int dis = Math.abs(preview.get(j).getY() - randY);
                    while(dis < 100 && failed == true)
                    {
                        randY = (int)(Math.random() * 450 + 50);
                        dis = Math.abs(preview.get(j).getY() - randY);
                    }
                }
                preview.add(new ConeHead(randX, randY));
            }
            else if(i < 5)
            {
                boolean failed = false;
                for(int j = 0; j < preview.size(); j++)
                {
                    int dis = Math.abs(preview.get(j).getY() - randY);
                    while(dis < 100 && failed == true)
                    {
                        randY = (int)(Math.random() * 450 + 50);
                        dis = Math.abs(preview.get(j).getY() - randY);
                    }
                }
                preview.add(new BucketHead(randX, randY));
            }
            else if(i < 6)
            {
                boolean failed = false;
                for(int j = 0; j < preview.size(); j++)
                {
                    int dis = Math.abs(preview.get(j).getY() - randY);
                    while(dis < 100 && failed == true)
                    {
                        randY = (int)(Math.random() * 450 + 50);
                        dis = Math.abs(preview.get(j).getY() - randY);
                    }
                }
                preview.add(new NewspaperZombie(randX, randY));
            }
            else
            {
                boolean failed = false;
                for(int j = 0; j < preview.size(); j++)
                {
                    int dis = Math.abs(preview.get(j).getY() - randY);
                    while(dis < 100 && failed == true)
                    {
                        randY = (int)(Math.random() * 450 + 50);
                        dis = Math.abs(preview.get(j).getY() - randY);
                    }
                }
                preview.add(new FootBallZombie(randX, randY));
            }
        }
        
        
        int startingX = 250;
        int startingY = 100;
        
        theLawn = new Lawn(startingX, startingY);
        lane1 = theLawn.getLanes().get(0);
        lane2 = theLawn.getLanes().get(1);
        lane3 = theLawn.getLanes().get(2);
        lane4 = theLawn.getLanes().get(3);
        lane5 = theLawn.getLanes().get(4);
        
        picks = new SeedSlot(10, 140, 7, 1, true);
        choices = new SeedSlot(250, 70, 7, 5, false);
        
        selected = null;
        
        bank = new SunBank(1000);
        s = new ArrayList<Sun>();
        sunTime = 0;
        
        shovel = new Shovel(WIDTH - 200, 20);
        
        mx = -10;//initial values, will be changed later
        my = -10;
       
        intro = true;
        help = false;
        seedSelection = false;
        canStart = false;
        startRound = false;
        gameOver = false;
        victory = false;
        congrats = false;
        
        goodbye = false;
        
        lvl1 = new Level1();
        
        explosion = new ArrayList<Explosions>();
        
        img1 = new ImageIcon(Lawn.class.getResource("Capture.PNG"));
        img2 = new ImageIcon(Lawn.class.getResource("Shovel.PNG"));
        img3 = new ImageIcon(Lawn.class.getResource("Capture.PNG"));
        img4 = new ImageIcon(Lawn.class.getResource("SeedSelection.png"));
        img5 = new ImageIcon(Lawn.class.getResource("LoadingScreen.png"));
        img6 = new ImageIcon(Lawn.class.getResource("gg.png"));
        img7 = new ImageIcon(Lawn.class.getResource("victory.png"));
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Plants vs Zombies but bad :)"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

         /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
          */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves

    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        //getting the key pressed
        int key = e.getKeyCode();
        //System.out.println("key = " + key);

       
    }  
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        if(intro)
        {
            Graphics2D g2d = (Graphics2D) g;
            Image imgBackground = img5.getImage();
            g2d.drawImage(imgBackground, 0, 0, WIDTH, HEIGHT, null);
            
            g.setColor(Color.black);
            g.fillRect(500, 120, 275, 70);
            
            Font f = new Font("Comic Sans", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("But bad :D", 510, 170);
            
            g.setColor(Color.red);
            g.fillRect(350, 550, 200, 100);
            f = new Font("Comic Sans", Font.BOLD, 75);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("PLAY", 350, 630);
            
            g.setColor(Color.blue);
            g.fillRect(650, 550, 200, 100);
            f = new Font("Comic Sans", Font.BOLD, 75);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("HELP", 650, 630);
            
            if(help)
            {
                g.setColor(Color.blue);
                g.fillRect(200, 200, 800, 500);
                
                g.setColor(Color.red);
                g.fillRect(1050, 600, 200, 100);
                
                f = new Font("Comic Sans", Font.BOLD, 75);
                g.setFont(f);
                g.setColor(Color.yellow);
                g.drawString("EXIT", 1060, 670);
                
                f = new Font("Comic Sans", Font.BOLD, 42);
                g.setFont(f);
                g.setColor(Color.green);
                g.drawString("Can you survive the zombie onslaught?", 200, 240);
 
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.RED);
                g.drawString("You must first endure the absolutely horrible graphics", 210, 280);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Obtain sun to spend on your defense and hold off", 210, 310);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("Sun falling from sky = 50, sunflower sun = 25", 210, 340);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Attackers: Peashooter, Snow Pea (slows), Repeater", 210, 370);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("Wallnut: tank; Cherry Bomb: self-explanatory (not broken)", 210, 400);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Potato mine takes time to arm before exploding on contact", 210, 430);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("Use shovel to remove your unnecessary plants (no refund)", 210, 460);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Zombies: conehead, buckethead, newspaper, football", 210, 490);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.cyan);
                g.drawString("Without his newspaper, Newspaper zombie moves faster", 210, 520);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Upon entering lawn, football zombie will dash and destroy", 210, 550);
                
                f = new Font("Comic Sans", Font.BOLD, 27);
                g.setFont(f);
                g.setColor(Color.lightGray);
                g.drawString("the first plant he encounters. After, he becomes normal", 210, 580);
                
                f = new Font("Comic Sans", Font.BOLD, 45);
                g.setFont(f);
                g.setColor(Color.green);
                g.drawString("I know dis hard to look at", 310, 640);
                
                f = new Font("Comic Sans", Font.BOLD, 35);
                g.setFont(f);
                g.setColor(Color.pink);
                g.drawString("(but plz have fun lol)", 400, 680);
                /*
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Obtain sun and spend them on plants", 200, 320);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("Sun falling from the sky are worth 50 sun", 200, 360);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Sun produced from Sunflowers are only 25", 200, 400);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("Potato Mines requires time to arm first", 200, 440);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.orange);
                g.drawString("Use Shovel to remove unnecessary plants", 200, 480);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.magenta);
                g.drawString("CHERRY BOMB IS NOT BROKEN", 200, 520);
                
                f = new Font("Comic Sans", Font.BOLD, 38);
                g.setFont(f);
                g.setColor(Color.green);
                g.drawString("I bet you can't beat this in less than 5 tries", 210, 560);
                
                f = new Font("Comic Sans", Font.BOLD, 100);
                g.setFont(f);
                g.setColor(Color.PINK);
                g.drawString("HAVE FUN!!!", 270, 670);
                */
            }
        }
        //this will be the loadout selection
        if(seedSelection == true && gameOver == false)
        {
            Graphics2D g2d = (Graphics2D) g;
            Image imgBackground = img3.getImage();
            g2d.drawImage(imgBackground, -1200, -210, WIDTH + 2000, HEIGHT + 350, null);
            
            /*
            g.setColor(Color.magenta);
            g.fillRect(225, 10, 710, HEIGHT);
*/
            Graphics2D g2d2 = (Graphics2D) g;
            Image imgBackground2 = img4.getImage();
            g2d2.drawImage(imgBackground2, 225, 10, 710, HEIGHT, null);
            
            if(canStart == false)
                g.setColor(Color.blue);
            else
                g.setColor(Color.cyan);
            
            g.fillRect(WIDTH - 250, HEIGHT - 65, 225, 60);

            Font f = new Font("Aerial", Font.BOLD, 35);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("LET'S ROCK!", WIDTH - 245, HEIGHT - 25);
            
            for(int i = 0; i < preview.size(); i++)
            {
                preview.get(i).drawSelf(g);
            }
            
            picks.create(g, bank);
            choices.create(g, bank);
            
        }
        
        
        if(startRound == true)
        {
            
            Graphics2D g2d = (Graphics2D) g;
            Image imgBackground = img1.getImage();
            g2d.drawImage(imgBackground, -500, -210, WIDTH + 1450, HEIGHT + 350, null);
            
            if(gameOver)
            {
                g2d = (Graphics2D) g;
                imgBackground = img1.getImage();
                g2d.drawImage(imgBackground, 0, -210, WIDTH + 1450, HEIGHT + 350, null);
            }
            
            //theLawn.create(g);
            
            Font f = new Font("Aerial", Font.BOLD, 35);
            g.setFont(f);
            g.setColor(Color.white);
            String money = "" + bank.getAmount();
            g.drawString(money, 10, 30);
            
            picks.create(g, bank);
            
            Graphics2D g2d2 = (Graphics2D) g;
            Image imgBackground2 = img2.getImage();
            g2d2.drawImage(imgBackground2, WIDTH - 200, 2, 80, 80, null);
            
            //shovel.drawSelf(g);

            for(int i = 0; i < plants.size(); i++)
            {
                plants.get(i).drawSelf(g);
            }

            for(int i = 0; i < zombies.size(); i++)
            {
                zombies.get(i).drawSelf(g);
            }

            for(int i = 0; i < projectiles.size(); i++)
            {
                projectiles.get(i).drawSelf(g);
            }
            
            for(int i = 0; i < picks.getList().size(); i++)
            {
                picks.getList().get(i).drawSelf(g, bank);
            }
            
            for(int i = 0; i < explosion.size(); i++)
            {
                explosion.get(i).drawSelf(g);
            }
            bank.drawSelf(g);
            lvl1.drawSelf(g);
            
            for(int i = 0; i < s.size(); i++)
            {
                s.get(i).drawSelf(g);
            }
        }
        
        
        
        if(gameOver)
        {
            Font f = new Font("Aerial", Font.BOLD, 100);
            g.setFont(f);
            g.setColor(Color.yellow);
            g.drawString("YOU LOSE!!!", 300, 200);
            
            g.setColor(Color.green);
            g.fillRect(200, 400, 200, 100);
            
            f = new Font("Aerial", Font.BOLD, 70);
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString("Retry", 210, 465);
            
            g.setColor(Color.red);
            g.fillRect(800, 400, 200, 100);
            f = new Font("Aerial", Font.BOLD, 70);
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString("Quit", 827, 475);
            
            if(goodbye)
            {
                Graphics g2d = (Graphics2D) g;
                Image imgBackground = img6.getImage();
                g2d.drawImage(imgBackground, 0, 0, WIDTH, HEIGHT, null);
            }
        }
        
        
        if(congrats)
        {
            Graphics2D g2d = (Graphics2D) g;
            Image imgBackground = img7.getImage();
            g2d.drawImage(imgBackground, 0, 0, WIDTH, HEIGHT, null);
            
            Font f = new Font("Aerial", Font.BOLD, 100);
            g.setFont(f);
            g.setColor(Color.yellow);
            g.drawString("YOU WIN!!!", 400, 100);
            f = new Font("Aerial", Font.BOLD, 80);
            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("You made the panda happy :D", 50, 670);
        }
        
        if(victory && congrats == false)
        {
            g.setColor(Color.red);
            g.fillRect(500, 350, 300, 90);
            Font f = new Font("Aerial", Font.BOLD, 35);
            g.setFont(f);
            g.setColor(Color.green);
            g.drawString("Don't click here :P", 500, 400);
        }
        //SURIEL: Drawing a yellow where the user clicked
        g.setColor(Color.yellow);
        g.fillOval(mx, my, 10, 10);
        
       
    }
    public void loop()
    {
        if(intro == true && start1 == false)
        {
            introSong.loop();
            start1 = true;
        }
        else if(seedSelection && gameOver == false && start2 == false)
        {
            introSong.stop();
            loseSong.stop();
            selectionSong.loop();
            start2 = true;
        }
        /*
        if(randSong == 1 && start3 == true)
        {
            if(System.currentTimeMillis() > startMusic + song1Len && startNewMusic == false);
            {
                startSong2.play();
                startNewMusic = true;
            }
        }
        else
        {
            if(System.currentTimeMillis() > startMusic + song2Len && startNewMusic == false);
            {
                startSong1.play();
                startNewMusic = true;
            }
        }
        */
        else if(startRound == true && gameOver == false && congrats == false && start3 == false)
        {
            if(randSong == 1)
            {
                selectionSong.stop();
                startSong1.loop();
                start3 = true;
                //startMusic = System.currentTimeMillis();
            }
            else
            {
                selectionSong.stop();
                startSong2.loop();
                start3 = true;
                //startMusic = System.currentTimeMillis();
            }
        }
        
        else if(gameOver == true && start4 == false)
        {
            startSong1.stop();
            startSong2.stop();
            loseSong.play();
            start4 = true;
        }
        else if(congrats == true && start5 == false)
        {
            startSong1.stop();
            startSong2.stop();
            victorySong.play();
            start5 = true;
        }
        if(startRound == true && gameOver == false && congrats == false)
        {
            for(int i = 0; i < plants.size(); i++)
            {
                plants.get(i).act(zombies, projectiles, plants, theLawn, s, explosion);
            }

            for(int i = 0; i < zombies.size(); i++)
            {
                zombies.get(i).act(zombies, plants);
            }
            for(int i = 0; i < projectiles.size(); i++)
            {
                projectiles.get(i).act(zombies, projectiles);
            }
            for(int i = 0; i < s.size(); i++)
            {
                s.get(i).act(s);
            }
            for(int i = 0; i < picks.getList().size(); i++)
            {
                picks.getList().get(i).act(picks);
            }
            for(int i = 0; i < explosion.size(); i++)
            {
                explosion.get(i).act(explosion);
            }
            if(System.currentTimeMillis() > sunTime + 10000)
            {
                int randX = (int)(Math.random()*800) + 250;
                s.add(new Sun(randX, 0, true));
                sunTime = System.currentTimeMillis();
            }
            
            lvl1.act(zombies, theLawn);
            
            //if(System.currentTimeMillis() > lvl1.getStartTime())
            //{
            //    if(zombies.size() == 0)
            //        victory = true;
            //}

            //code added by Gemini
            if(System.currentTimeMillis() > lvl1.getStartTime() && lvl1.isAllZombiesSpawned())
            {
                if(zombies.size() == 0)
                    victory = true;
            }
            
            //lose screen shift
            for(int i = 0; i < zombies.size(); i++)
            {
                if(zombies.get(i).getX() < 100)
                {
                    for(int j = 0; j < zombies.size(); j++)
                    {
                        zombies.get(j).setX(zombies.get(j).getX() + 500);
                    }
                    for(int j = 0; j < plants.size(); j++)
                    {
                        plants.get(j).setX(plants.get(j).getX() + 500);
                    }
                    for(int j = 0; j < s.size(); j++)
                    {
                        s.get(j).setX(s.get(j).getX() + 500);
                    }
                    for(int j = 0; j < projectiles.size(); j++)
                    {
                        projectiles.get(j).setX(projectiles.get(j).getX() + 500);
                    }
                    gameOver = true;
                }
            }

            // Added by Gemini to print zombie count on change for debugging
            if (zombies.size() != lastZombieCount) {
                System.out.println("ZOMBIE COUNT CHANGED: " + zombies.size());
                lastZombieCount = zombies.size();
            }
        }
        
        //Do not write below this
        repaint();
    }
   
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
        int xCoor = e.getX() - 3;//SURIEL:  This method has a strange glitch where the click happens a bit to the right and a bit below where the click is, this is to fix that
        int yCoor = e.getY() - 32;
        
        mx = xCoor;
        my = yCoor;
        
        if(intro)
        {
            if(xCoor >= 650 && xCoor <= 850 && yCoor >= 550 && yCoor <= 650)
                help = true;
            
            if(help)
            {
                if(xCoor >= 1050 && xCoor <= 1250 && yCoor >= 600 && yCoor <= 700)
                    help = false;
            }

            if(xCoor >= 350 && xCoor <= 550 && yCoor >= 550 && yCoor <= 650 && help == false)
            {
                intro = false;
                seedSelection = true;
            }
            
            
        }
        if(startRound == false && intro == false)
        {
            
            for(int i = 0; i < choices.getList().size(); i++)
            {
                if(choices.getList().get(i).clickedInsideMe(xCoor, yCoor) == true)
                {
                    choices.getList().get(i).clicked(picks);
                    if(picks.getList().get(picks.getList().size() - 1).getNum() > 0)
                        canStart = true;
                    else
                        canStart = false;
                }
            }

            
            for(int i = 0; i < picks.getList().size(); i++)
            {
                if(picks.getList().get(i).clickedInsideMe(xCoor, yCoor) == true)
                {
                    picks.getList().get(i).removeSeed(picks, choices);
                    if(picks.getList().get(picks.getList().size() - 1).getNum() > 0)
                        canStart = true;
                    else
                        canStart = false;
                }
            }
            
            if(xCoor >= WIDTH - 250 && xCoor <= WIDTH - 25 && yCoor >= HEIGHT - 60 && yCoor <= HEIGHT - 5 && canStart == true)
            {
                startRound = true;
                bank.setAmount(50);
            }
        }
        
        if(startRound == true && gameOver == false && intro == false && congrats == false)
        {
            Lane lane = theLawn.locateLane(xCoor, yCoor);
            
            Tile l1T = lane1.locateTile(xCoor, yCoor);
            Tile l2T = lane2.locateTile(xCoor, yCoor);
            Tile l3T = lane3.locateTile(xCoor, yCoor);
            Tile l4T = lane4.locateTile(xCoor, yCoor);
            Tile l5T = lane5.locateTile(xCoor, yCoor);
            
            /*
            System.out.println(lane);
            System.out.println(l1T);    
            System.out.println(l2T); 
            System.out.println(l3T); 
            System.out.println(l4T); 
            System.out.println(l5T); 
                */
            
            shovel.act(xCoor, yCoor, theLawn, plants);
            
            if(plantChosen == true)
            {
                Plant newP = pHolder;
                if(l1T != null)
                {
                    if(l1T.getOccupied() == false)
                    {
                        int currX = l1T.getX();
                        int currY = l1T.getY();
                        newP.setX(currX + 20);
                        newP.setY(currY + 10);
                        l1T.takeUp(newP, plants);
                        bank.useSun(pHolder.getCost());
                        plantChosen = false;

                        selected.setCharge();
                    }
                }
                else if(l2T != null)
                {
                    if(l2T.getOccupied() == false)
                    {
                        int currX = l2T.getX();
                        int currY = l2T.getY();
                        newP.setX(currX + 20);
                        newP.setY(currY + 10);
                        l2T.takeUp(newP, plants);
                        bank.useSun(pHolder.getCost());
                        plantChosen = false;

                        selected.setCharge();
                    }
                }
                else if(l3T != null)
                {
                    if(l3T.getOccupied() == false)
                    {
                        int currX = l3T.getX();
                        int currY = l3T.getY();
                        newP.setX(currX + 20);
                        newP.setY(currY + 15);
                        l3T.takeUp(newP, plants);
                        bank.useSun(pHolder.getCost());
                        plantChosen = false;

                        selected.setCharge();
                    }
                }
                else if(l4T != null)
                {
                    if(l4T.getOccupied() == false)
                    {
                        int currX = l4T.getX();
                        int currY = l4T.getY();
                        newP.setX(currX + 20);
                        newP.setY(currY + 20);
                        l4T.takeUp(newP, plants);
                        bank.useSun(pHolder.getCost());
                        plantChosen = false;

                        selected.setCharge();
                    }
                }
                else if(l5T != null)
                {
                    if(l5T.getOccupied() == false)
                    {
                        int currX = l5T.getX();
                        int currY = l5T.getY();
                        newP.setX(currX + 20);
                        newP.setY(currY + 20);
                        l5T.takeUp(newP, plants);
                        bank.useSun(pHolder.getCost());
                        plantChosen = false;
                        
                        selected.setCharge();
                    }
                }
            }

            Seed seedClicked = picks.whateverMrSurielSaid(xCoor, yCoor);
            //System.out.println("seeds clicked: " + seedClicked);

            if(seedClicked != null)
            {
                if(seedClicked.isRecharging() == false)
                {
                    if(seedClicked.getNum() == 1)
                    {
                        pHolder = new Peashooter(-50, -50);

                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 2)
                    {
                        pHolder = new WallNut(-50, -50);

                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 3)
                    {
                        pHolder = new PotatoMine(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 4)
                    {
                        pHolder = new CherryBomb(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 5)
                    {
                        pHolder = new SnowPea(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 6)
                    {
                        pHolder = new Repeater(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else if(seedClicked.getNum() == 7)
                    {
                        pHolder = new SunFlower(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                    else
                    {
                        pHolder = new Plant(-50, -50);
                        if(pHolder.getCost() <= bank.getAmount())
                            plantChosen = true;
                        else
                            pHolder = null;
                    }
                  
                    //seedClicked.setCharge();
                    selected = seedClicked;
                    
                    
                }
            }
            for(int i = 0; i < s.size(); i++)
            {
                s.get(i).clicked(bank, xCoor, yCoor, s);
            }
            
            if(System.currentTimeMillis() > lvl1.getStartTime())
            {
                if(xCoor >= 500 && xCoor <= 800 && yCoor >= 350 && yCoor <= 440)
                    congrats = true;
            }
        }
        
        if(gameOver && goodbye == false)
        {
            if(xCoor >= 200 && xCoor <= 400 && yCoor >= 400 && yCoor <= 500)
            {
                //initializing instance variables
                WIDTH = 1250;
                HEIGHT = 700;

                int lane = (int)(Math.random() * 5);

                plants = new ArrayList<Plant>();
                zombies = new ArrayList<Zombie>();
                projectiles = new ArrayList<Projectile>();


                preview = new ArrayList<Zombie>();

                startMusic = System.currentTimeMillis();
                randSong = (int)(Math.random() * 2 + 1);
                //song1Len = 192000;
                //song2Len = 123000;
                //newMusic = false;
                //startNewMusic = false;


                







                for(int i = 0; i < 7; i++)
                {
                    int randX = (int)(Math.random() * 200 + 970);
                    int randY = (int)(Math.random() * 450 + 50);

                    if(i < 1)
                        preview.add(new Zombie(randX, randY));
                    else if(i < 2)
                    {
                        boolean failed = false;
                        for(int j = 0; j < preview.size(); j++)
                        {
                            int dis = Math.abs(preview.get(j).getY() - randY);
                            while(dis < 100 && failed == true)
                            {
                                randY = (int)(Math.random() * 450 + 50);
                                dis = Math.abs(preview.get(j).getY() - randY);
                            }
                        }
                        preview.add(new Zombie(randX, randY));
                    }
                    else if(i < 4)
                    {
                        boolean failed = false;
                        for(int j = 0; j < preview.size(); j++)
                        {
                            int dis = Math.abs(preview.get(j).getY() - randY);
                            while(dis < 100 && failed == true)
                            {
                                randY = (int)(Math.random() * 450 + 50);
                                dis = Math.abs(preview.get(j).getY() - randY);
                            }
                        }
                        preview.add(new ConeHead(randX, randY));
                    }
                    else if(i < 5)
                    {
                        boolean failed = false;
                        for(int j = 0; j < preview.size(); j++)
                        {
                            int dis = Math.abs(preview.get(j).getY() - randY);
                            while(dis < 100 && failed == true)
                            {
                                randY = (int)(Math.random() * 450 + 50);
                                dis = Math.abs(preview.get(j).getY() - randY);
                            }
                        }
                        preview.add(new BucketHead(randX, randY));
                    }
                    else if(i < 6)
                    {
                        boolean failed = false;
                        for(int j = 0; j < preview.size(); j++)
                        {
                            int dis = Math.abs(preview.get(j).getY() - randY);
                            while(dis < 100 && failed == true)
                            {
                                randY = (int)(Math.random() * 450 + 50);
                                dis = Math.abs(preview.get(j).getY() - randY);
                            }
                        }
                        preview.add(new NewspaperZombie(randX, randY));
                    }
                    else
                    {
                        boolean failed = false;
                        for(int j = 0; j < preview.size(); j++)
                        {
                            int dis = Math.abs(preview.get(j).getY() - randY);
                            while(dis < 100 && failed == true)
                            {
                                randY = (int)(Math.random() * 450 + 50);
                                dis = Math.abs(preview.get(j).getY() - randY);
                            }
                        }
                        preview.add(new FootBallZombie(randX, randY));
                    }
                }


                int startingX = 250;
                int startingY = 100;

                theLawn = new Lawn(startingX, startingY);
                lane1 = theLawn.getLanes().get(0);
                lane2 = theLawn.getLanes().get(1);
                lane3 = theLawn.getLanes().get(2);
                lane4 = theLawn.getLanes().get(3);
                lane5 = theLawn.getLanes().get(4);

                picks = new SeedSlot(10, 140, 7, 1, true);
                choices = new SeedSlot(250, 70, 7, 5, false);

                selected = null;

                bank = new SunBank(1000);
                s = new ArrayList<Sun>();
                sunTime = 0;

                shovel = new Shovel(WIDTH - 200, 20);

                mx = -10;//initial values, will be changed later
                my = -10;

                intro = false;
                seedSelection = true;
                canStart = false;
                startRound = false;
                gameOver = false;
                victory = false;
                congrats = false;

                goodbye = false;

                start1 = false;
                start2 = false;
                start3 = false;
                start4 = false;
                start5 = false;
                start6 = false;
                start7 = false;
                start8 = false;
                start9 = false;
                start10 = false;
                
                lvl1 = new Level1();

                explosion = new ArrayList<Explosions>();

                // Added by Gemini to reset zombie count for debugging
                lastZombieCount = 0;
            }
            else if(xCoor >= 800 && xCoor <= 1000 && yCoor >= 400 && yCoor <= 500)
            {
                goodbye = true;
            }
            
        }
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public static void main(String[] args)
    {
        
        Main g = new Main();
        g.start(60);
        
        
    }
}
