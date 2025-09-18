import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//most basic plant derived from Plant class
public class Peashooter extends Plant
{
    private ImageIcon img1;
    public Peashooter(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        img1 = new ImageIcon(Lawn.class.getResource("PeaShooter.png"));
    }

    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        
        if(this.getX() > 0 && this.getY() > 0)
        {
            checkRemove(pl, l);
            
            
            detectZombie(z, p, pl, l, s, e);
            /*
            for(int i = 0; i < z.size(); i++)
            {
                int zX = z.get(i).getX();
                int zW = z.get(i).getW();
                int zY = z.get(i).getY();
                int zH = z.get(i).getH();

                if(this.getY() > zY && this.getY() < zY + zH)
                {
                    if(this.getX() + this.getW() < zX + zY)
                    {
                        if(System.currentTimeMillis() >= this.getCurrTime() + this.getFireRate())
                        {
                            attack(p);
                            this.setCurrTime(System.currentTimeMillis());
                            return;
                        }
                    }
                }
            }
            */
        }
        
    }
    public void attack(ArrayList<Projectile> p)
    {
        p.add(new Pea(this.getX() + this.getW(), this.getY() + this.getH()/2 - 10));
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.blue);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        
        Graphics2D g2d;

        g2d = (Graphics2D) g;

        Image img = img1.getImage();

        g2d.drawImage(img, this.getX(), this.getY() - 5, this.getW(), this.getH() + 20, null);
    }    
}