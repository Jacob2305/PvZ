import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;


//just a normal zombie with more hp
public class BucketHead extends Zombie
{
    //use image to import images (ImageIcon)
    private ImageIcon img1, img2, img3, img4, img5;
    public BucketHead(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        this.setHp(40);
        
        img1 = new ImageIcon(Lawn.class.getResource("BucketHead.png"));
        img2 = new ImageIcon(Lawn.class.getResource("BucketHead1.png"));
        img3 = new ImageIcon(Lawn.class.getResource("BucketHead2.png"));
        img4 = new ImageIcon(Lawn.class.getResource("Zombie.png"));
        img5 = new ImageIcon(Lawn.class.getResource("ZombieHalf.png"));
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.black);
        //g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());

        if(this.getHp() >= 30)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX() - 250, this.getY() - 100, this.getW() + 500, this.getH() + 180, null);
        }
        else if(this.getHp() >= 20)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX() - 220, this.getY() - 80, this.getW() + 450, this.getH() + 150, null);
        }
        else if(this.getHp() >= 10)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img3.getImage();
            g2d.drawImage(img, this.getX() - 450, this.getY() - 220, this.getW() + 900, this.getH() + 400, null);
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