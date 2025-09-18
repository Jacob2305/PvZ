import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class SunFlower extends Plant
{
    int interval;
    private boolean placed;
    private ImageIcon img1;
    
    public SunFlower(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        interval = 8000;
        placed = false;
        this.setCost(50);
        img1 = new ImageIcon(Lawn.class.getResource("Sunflower.png"));
    }

    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        if(this.getX() > 0 && this.getY() > 0)
        {
            checkRemove(pl, l);
            
            
            if(placed == false)
            {
                placed = true;
                this.setCurrTime(System.currentTimeMillis());
            }
            
            if(System.currentTimeMillis() >= this.getCurrTime() + interval)
            {
                s.add(new Sun(this.getX(), this.getY(), false));
                this.setCurrTime(System.currentTimeMillis());
            }
        }          
    }
    public void attack(ArrayList<Projectile> p)
    {
        p.add(new Pea(this.getX() + this.getW(), this.getY() + this.getH()/2));
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.WHITE);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        
        Graphics2D g2d;

        g2d = (Graphics2D) g;

        Image img = img1.getImage();

        g2d.drawImage(img, this.getX(), this.getY() - 10, this.getW(), this.getH() + 30, null);
    }    
}