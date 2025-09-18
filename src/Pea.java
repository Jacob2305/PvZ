import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//the base projectile
public class Pea extends Projectile 
{
    private ImageIcon img1;
    public Pea(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        img1 = new ImageIcon(Lawn.class.getResource("Pea.png"));
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(this.getX(), this.getY(), this.getDiam(), this.getDiam());
        
        Graphics2D g2d;

        g2d = (Graphics2D) g;

        Image img = img1.getImage();

        g2d.drawImage(img, this.getX() - 50, this.getY() - 30, this.getDiam() + 100, this.getDiam() + 50, null);
    }
}