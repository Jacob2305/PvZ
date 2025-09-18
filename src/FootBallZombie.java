import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FootBallZombie extends Zombie
{
    private boolean dashing;
    private boolean started;
    private ImageIcon img1, img2;
    
    public FootBallZombie(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        this.setHp(30);
        
        dashing = false;
        started = false;
        
        //his normal dashing speed will be 3x the normal zombie's
        this.setRunSpeed(this.getRunSpeed() / 3);
        this.setSlowRun(this.getSlowRun() / 3);
        
        img1 = new ImageIcon(Lawn.class.getResource("FootBall.png"));
        img2 = new ImageIcon(Lawn.class.getResource("FootBallDash.png"));
    }
    public void act(ArrayList<Zombie> z, ArrayList<Plant> p)
    {
        //starts off the dash
        if(dashing == false && started == false)
        {
            dashing = true;
            started = true;
        }
        
        //this is his movement when he is chilled or not
        if(this.getSlowed() == false)
        {
            if(System.currentTimeMillis() >= this.getOtherCurrTime() + this.getRunSpeed())
            {
                this.setX(this.getX() - this.getS());
                this.setOtherCurrTime(System.currentTimeMillis());
            }
        }
        else
        {
            if(System.currentTimeMillis() >= this.getOtherCurrTime() + this.getSlowRun())
            {
                this.setX(this.getX() - this.getS());
                this.setOtherCurrTime(System.currentTimeMillis());
            }
        }
        
        
        //this removes the zombie when it reaches 0 hp
        if(this.getHp() <= 0)
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
        
        couldEat(p);
        
    }
    public void eat(ArrayList<Plant> p)//attack
    {
        //the first plant will be instantly killed by a tackle
        if(dashing == true)
        {
            for(int i = 0; i < p.size(); i++)
            {
               if(p.get(i).getX() + p.get(i).getW() > this.getX() && p.get(i).getX()  + p.get(i).getW() < this.getX() + this.getW() && p.get(i).getY() > this.getY() && p.get(i).getY() < this.getY() + this.getH()) 
               {
                    //this reverts his speed back to a normal zombies
                    dashing = false;
                    this.setRunSpeed(this.getRunSpeed() * 3);
                    this.setSlowRun(this.getSlowRun() * 3);
                    p.get(i).takeDamage(200);
               }
            }
        }
        //this is a normal zombie
        else
        {
            for(int i = 0; i < p.size(); i++)
            {
               if(p.get(i).getX() + p.get(i).getW() > this.getX() && p.get(i).getX()  + p.get(i).getW() < this.getX() + this.getW() && p.get(i).getY() > this.getY() && p.get(i).getY() < this.getY() + this.getH()) 
               {
                   this.setS(0);
                   if(this.getSlowed() == false)
                   {
                        if(System.currentTimeMillis() >= this.getCurrTime() + this.getEatSpeed())
                        {
                            p.get(i).takeDamage(this.getDamage());
                            this.setCurrTime(System.currentTimeMillis());
                        }
                   }
                   else
                   {
                        if(System.currentTimeMillis() >= this.getCurrTime() + this.getSlowEat())
                        {
                            p.get(i).takeDamage(this.getDamage());
                            this.setCurrTime(System.currentTimeMillis());
                        }
                   }
               }
            }
        }
    }   
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.red);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        
        if(dashing == false)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 200, this.getY() - 80, this.getW() + 400, this.getH() + 150, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX() - 150, this.getY() - 20, this.getW() + 360, this.getH() + 120, null);
        }
    }
    
}