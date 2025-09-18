import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Zombie
{
    private int x;
    private int y;
    private int w;
    private int h;
    private int hp;
    private int s;
    private Color col;
    
    private boolean isEating;
    
    private long currTime;
    private long otherCurrTime;
    private int eatSpeed;//attack speed
    private int runSpeed;
    
    private boolean slowed;
    private int slowEat;
    private int slowRun;
    
    private int d;
    
    private int priorS;
    
    private ImageIcon img1, img2;
    public Zombie(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        w = 50;
        h = 95;
        hp = 10;
        s = 1;
        col = Color.MAGENTA;
        
        isEating = false;
        
        currTime = 0;
        otherCurrTime = 0;
        
        eatSpeed = 250;
        runSpeed = 25;
        
        slowed = false;
        slowEat = eatSpeed * 2;
        slowRun = runSpeed * 2;
        
        
        d = 1;
        
        img1 = new ImageIcon(Lawn.class.getResource("Zombie.png"));
        img2 = new ImageIcon(Lawn.class.getResource("ZombieHalf.png"));
        
        priorS = 1; //will retain speed back after eating
    }
    public String toString()
    {
        return "A normal zombie with a health of " + hp;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getW()
    {
        return w;
    }
    public int getH()
    {
        return h;
    }
    public int getHp()
    {
        return hp;
    }
    public int getS()
    {
        return s;
    }
    public boolean getIsEating()
    {
        return isEating;
    }
    public long getCurrTime()
    {
        return currTime;
    }
    public long getOtherCurrTime()
    {
        return otherCurrTime;
    }
    public int getEatSpeed()
    {
        return eatSpeed;
    }
    public int getRunSpeed()
    {
        return runSpeed;
    }
    public boolean getSlowed()
    {
        return slowed;
    }
    public int getPriorS()
    {
        return priorS;
    }
    public int getDamage()
    {
        return d;
    }
    public int getSlowEat()
    {
        return slowEat;
    }
    public int getSlowRun()
    {
        return slowRun;
    }
    public void setEatSpeed(int newEatSpeed)
    {
        eatSpeed = newEatSpeed;
    }
    public void setRunSpeed(int newRunSpeed)
    {
        runSpeed = newRunSpeed;
    }
    public void setSlowEat(int newSlowEat)
    {
        slowEat = newSlowEat;
    }
    public void setSlowRun(int newSlowRun)
    {
        slowRun = newSlowRun;
    }
    public void setDamage(int newD)
    {
        d = newD;
    }
    public void setX(int newX)
    {
        x = newX;
    }
    public void setS(int newS)
    {
        s = newS;
    }
    public void setCurrTime(long newTime)
    {
        currTime = newTime;
    }
    public void setOtherCurrTime(long newTime)
    {
        otherCurrTime = newTime;
    }
    public void setHp(int newHp)
    {
        hp = newHp;
    }
    public void act(ArrayList<Zombie> z, ArrayList<Plant> p)
    {
        //this if else controls the speed at which the zombie moves
        //if the zombie is chilled, it will move at a slower rate
        if(slowed == false)
        {
            if(System.currentTimeMillis() >= otherCurrTime + runSpeed)
            {
                x = x - s;
                otherCurrTime = System.currentTimeMillis();
            }
        }
        else
        {
            if(System.currentTimeMillis() >= otherCurrTime + slowRun)
            {
                x = x - s;
                otherCurrTime = System.currentTimeMillis();
            }
        }
        
        
        //this allows the zombie to be removed once it has reached 0 hp
        if(hp <= 0)
        {
            for(int i = 0; i < z.size(); i++)
            {
                if(z.get(i).getHp() <= 0)
                {
                    z.remove(i);
                    i--;
                }
            }
        }
        
        //this will always be active to see if the zombie intercepts a plant
        couldEat(p);
        
    }
    public void couldEat(ArrayList<Plant> p)
    {
        //this boolean controls if there's a plant in directly in front of it
        boolean canEat = false;
        
        //this loop goes to see if a plant exists on the tile the zombie is on
        for(int i = 0; i < p.size(); i++)
        {
           if(p.get(i).getX() + p.get(i).getW() > x && p.get(i).getX() < x) 
           {
               if(p.get(i).getY() > y && p.get(i).getY() < y + h)
               {
                   canEat = true;
               //priorS = this.getS();
               }
           }
        }
        
        //if there's a plant, start eating it (attack), if not, keep moving
        if(canEat)
            eat(p);
        else
            s = priorS;
    }
    public void eat(ArrayList<Plant> p)//attack
    {
        //this loop makes sure there's a plant in front of it to start eating
        //the loop aspect of it may be redundant due to the code up in couldEat() but whatever
        for(int i = 0; i < p.size(); i++)
        {
           if(p.get(i).getX() + p.get(i).getW() > x && p.get(i).getX() < x && p.get(i).getY() > y && p.get(i).getY() < y + h) 
           {
               //can't move when it eats
               //will eat slower if chilled
               s = 0;
               if(slowed == false)
               {
                    if(System.currentTimeMillis() >= currTime + eatSpeed)
                    {
                        p.get(i).takeDamage(d);
                        currTime = System.currentTimeMillis();
                    }
               }
               else
               {
                    if(System.currentTimeMillis() >= currTime + slowEat)
                    {
                        p.get(i).takeDamage(d);
                        currTime = System.currentTimeMillis();
                    }
               }
           }
        }
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(col);
        //g.fillRect(x, y, w, h);
        
        //basic drawing
        if(hp > 5)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 180, this.getY() - 50, this.getW() + 360, this.getH() + 120, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX() - 170, this.getY() - 40, this.getW() + 360, this.getH() + 120, null);
        }
    }
    public void takeDamage(int dam, boolean slow)
    {
        //if a snowpea hits it or something, gain chill effect (slow)
        hp -= dam;
        if(slow)
            this.slow();
    }
    public void slow()
    {
        slowed = true;
    }
}