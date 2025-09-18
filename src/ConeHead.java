import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//just a tankier normal Zombie
public class ConeHead extends Zombie
{
    private ImageIcon img1, img2, img3, img4, img5;
    public ConeHead(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        this.setHp(21);
        
        img1 = new ImageIcon(Lawn.class.getResource("ConeHead.png"));
        img2 = new ImageIcon(Lawn.class.getResource("ConeHead1.png"));
        img3 = new ImageIcon(Lawn.class.getResource("ConeHead2.png"));
        img4 = new ImageIcon(Lawn.class.getResource("Zombie.png"));
        img5 = new ImageIcon(Lawn.class.getResource("ZombieHalf.png"));
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.orange);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        
        if(this.getHp() >= 17)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 270, this.getY() - 90, this.getW() + 540, this.getH() + 190, null);
        }
        else if(this.getHp() >= 14)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX() - 210, this.getY() - 110, this.getW() + 400, this.getH() + 120, null);
        }
        else if(this.getHp() >= 11)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img3.getImage();
            g2d.drawImage(img, this.getX() - 290, this.getY() - 120, this.getW() + 580, this.getH() + 240, null);
        }
        else if(this.getHp() > 5)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img4.getImage();
            g2d.drawImage(img, this.getX() - 180, this.getY() - 50, this.getW() + 360, this.getH() + 120, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img5.getImage();
            g2d.drawImage(img, this.getX() - 170, this.getY() - 40, this.getW() + 360, this.getH() + 120, null);
        }
        
        
    }
}