import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class SunBank
{
    int amt;
    private ImageIcon img1;
    public SunBank(int amount)
    {
        amt = amount;
        img1 = new ImageIcon(Lawn.class.getResource("SunBank.png"));
    }
    public int getAmount()
    {
        return amt;
    }
    public void setAmount(int amount)
    {
        amt = amount;
    }
    public void addSun()
    {
        amt += 25;
    }
    public void useSun(int amount)
    {
        amt -= amount;
    }
    public void drawSelf(Graphics g)
    {
        Graphics2D g2d;
        g2d = (Graphics2D) g;
        Image img = img1.getImage();
        g2d.drawImage(img, 10, 5, 110, 130,  null);
        
        Font f = new Font("Aerial", Font.BOLD, 30);
        g.setFont(f);
        g.setColor(Color.black);
        String money = "" + this.getAmount();
        
        if(getAmount() >= 1000)
            g.drawString(money, 30, 125);
        else if(getAmount() >= 100)
            g.drawString(money, 40, 125);
        else if(getAmount() > 0)
            g.drawString(money, 50, 125);
        else
            g.drawString(money, 60, 125);
    }
}