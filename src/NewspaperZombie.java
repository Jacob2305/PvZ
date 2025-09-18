import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class NewspaperZombie extends Zombie
{
    private boolean rage;
    private ImageIcon img1, img2;
    public NewspaperZombie(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        this.setHp(25);
        
        rage = false;
        
        img1 = new ImageIcon(Lawn.class.getResource("Newspaper.png"));
        img2 = new ImageIcon(Lawn.class.getResource("NewspaperRage.png"));
    }
    public void act(ArrayList<Zombie> z, ArrayList<Plant> p)
    {
        //he gets twice as fast once he loses reaches a certain hp
        //this will happen when he loses his newpaper in game
        if(this.getHp() <= 15 && rage == false)
        {
            rage = true;
            this.setEatSpeed(this.getEatSpeed() / 2);
            this.setRunSpeed(this.getRunSpeed() / 2);
            this.setSlowEat(this.getSlowEat() / 2);
            this.setSlowRun(this.getSlowRun() / 2);
        }
        
        
        //this will be his movement
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
        
        
        //remove this zombie when he reaches 0 hp
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
        
        //see if the zombie can attack
        boolean canEat = false;
        for(int i = 0; i < p.size(); i++)
        {
           if(p.get(i).getX() + p.get(i).getW() > this.getX() && p.get(i).getX()  + p.get(i).getW() < this.getX() + this.getW() && p.get(i).getY() > this.getY() && p.get(i).getY() < this.getY() + this.getH()) 
           {
               canEat = true;
               //priorS = this.getS();
           }
        }
        if(canEat)
            eat(p);
        else
            this.setS(this.getPriorS());
        
    }
            
    public void drawSelf(Graphics g)
    {
        /*
        if(rage == false)
        {
            g.setColor(Color.gray);
            g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        }
        else
        {
            g.setColor(Color.black);
            g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        }
*/
        
        if(rage == false)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 200, this.getY() - 80, this.getW() + 350, this.getH() + 120, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX() - 220, this.getY() - 70, this.getW() + 440, this.getH() + 140, null);
        }
    }
    
}