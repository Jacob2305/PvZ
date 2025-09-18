import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FrozenPea extends Projectile 
{
    private ImageIcon img1;
    public FrozenPea(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        img1 = new ImageIcon(Lawn.class.getResource("SnowPeaBullet.png"));
    }
    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p)
    {
        //same thing as the parent class (projectile), just changed the slow effect to true
        this.setX(this.getX() + this.getS());
        
        for(int i = 0; i < z.size(); i++)
        {
            if(detectCollision(z.get(i)))
            {
                z.get(i).takeDamage(this.getDam(), true); 
                this.setPierce(this.getPierce() - 1);
                if(this.getPierce() <= 0)
                {
                    for(int j = 0; j < p.size(); j++)
                    {
                        if(p.get(j).getPierce() <= 0)
                        {
                            p.remove(j);
                            j--;
                        }
                            
                    }
                }
            }
        }
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillOval(this.getX(), this.getY(), this.getDiam(), this.getDiam());
        
        Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 300, this.getY() - 100, this.getDiam() + 400, this.getDiam() + 270, null);
    }
}