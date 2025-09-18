import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//this is only for imaging purposes 
public class Explosions
{
    private int x, y;
    private int type;
    private long curr;
    
    private boolean appeared;
    
    private ImageIcon img1, img2;
    
    public Explosions(int xCoor, int yCoor, int i)
    {
        x = xCoor;
        y = yCoor;
        type = i;
        curr = System.currentTimeMillis();
        
        appeared = false;
        
        img1 = new ImageIcon(Lawn.class.getResource("CherryBomb3.png"));
        img2 = new ImageIcon(Lawn.class.getResource("PotatoMine3.png"));
    }
    public void act(ArrayList<Explosions> e)
    {
        //once this thing is added to the lawn, it will display itself
        //after 1 second, it will remove itself
        if(appeared == false)
        {
            curr = System.currentTimeMillis();
            appeared = true;
        }
        if(System.currentTimeMillis() > curr + 1000)
        {
            for(int i = 0; i < e.size(); i++)
            {
                if(this == e.get(i))
                    e.remove(i);
            }
        }
    }
    public void drawSelf(Graphics g)
    {
        if(type == 1)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, x, y, 1000, 600, null);
        }
        else if(type == 2)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, x - 100, y - 90, 300, 200, null);
        }
    }
    
}