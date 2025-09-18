import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Sun
{
    private int x, y, w, h;
    private boolean natural;
    
    private long curr;
    private int timer;
    
    private boolean faded;
    
    private int maxTravel;
    
    private ImageIcon img1;
    
    public Sun(int xCoor, int yCoor, boolean isNatural)
    {
        x = xCoor;
        y = yCoor;
        w = 70;
        h = 70;
        natural = isNatural;
        
        curr = System.currentTimeMillis();
        timer = 5000;
        faded = false;
        
        maxTravel = (int)(Math.random()*400) + 200;
        img1 = new ImageIcon(Lawn.class.getResource("Sun.png"));
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int newX)
    {
        x = newX;
    }
    public void act(ArrayList<Sun> s)
    {
        if(System.currentTimeMillis() > curr + timer)
        {
            for(int i = 0; i < s.size(); i++)
            {
                if(s.get(i) == this)
                    s.remove(i);
            }
        }
        
        if(natural == true && y <= maxTravel)
        {
            y++;
        }
    }
    public void clicked(SunBank bank, int xCoor, int yCoor, ArrayList<Sun> s)
    {
        if(clickedInsideMe(xCoor, yCoor))
        {
            
            bank.addSun();
            if(natural)
                bank.addSun();

            for(int i = 0; i < s.size(); i++)
            {
                if(s.get(i) == this)
                    s.remove(i);
            }
        }
    }
    public boolean clickedInsideMe(int mx, int my)
    {
        if(mx >= x && mx <= x + w && my >= y && my <= y + h)
            return true;
        else
            return false;
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.yellow);
        //g.fillRect(x, y, w, h);
        
        Graphics2D g2d;

        g2d = (Graphics2D) g;

        Image img = img1.getImage();

        g2d.drawImage(img, x - 145, y - 60, w + 280, h + 155, null);
    }  
}