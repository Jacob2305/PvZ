import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class WallNut extends Plant
{
    private ImageIcon img1;
    public WallNut(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        this.setHp(100);
        this.setCost(50);
        
        img1 = new ImageIcon(Lawn.class.getResource("WallNutCos.png"));
    }

    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        checkRemove(pl, l);
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.black);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        
        Graphics2D g2d;

        g2d = (Graphics2D) g;

        Image img = img1.getImage();

        g2d.drawImage(img, this.getX(), this.getY() - 30, this.getW(), this.getH() + 40, null);
    }   
}