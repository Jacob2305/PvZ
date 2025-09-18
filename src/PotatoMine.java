import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PotatoMine extends Plant
{
    private boolean armed; 
    private long start;
    private boolean placed;
    private ImageIcon img1, img2;
    
    public PotatoMine(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        start = System.currentTimeMillis();
        armed = false;
        placed = false;
        this.setCost(25);
        img1 = new ImageIcon(Lawn.class.getResource("PotatoMine.png"));
        img2 = new ImageIcon(Lawn.class.getResource("BabyMine.png"));
    }

    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        if(this.getX() > 0 && this.getY() > 0)
        {
            if(placed == false)
            {
                placed = true;
                start = System.currentTimeMillis();
            }
        
            checkRemove(pl, l);

            //mine becomes armed and explodable after 10 seconds
            if(System.currentTimeMillis() > start + 10000)
                armed = true;
            
            //This loop watches for zombies making contact with the mine when it's armed
             boolean exploded = false;
             int i = 0;
             while(i < z.size() && exploded == false && armed == true)
             {
                 if(this.getX() + this.getW() >= z.get(i).getX() 
                         && this.getX() + this.getW() <= z.get(i).getX() + z.get(i).getW() 
                         && this.getY() >= z.get(i).getY() 
                         && this.getY() <= z.get(i).getY() + z.get(i).getH())
                 {
                     //when a zombie makes contact, this will explode when it's armed
                     this.explode(z, l, e);
                     exploded = true;
                     e.add(new Explosions(this.getX(), this.getY(), 2));
                 }
                 i++;
             }
        }
    }
    public void explode(ArrayList<Zombie> zom, Lawn l, ArrayList<Explosions> e)
    {
        //destroy zombies that are in the same tile
        Tile t = this.getTile(l);
        ArrayList<Zombie> z = t.countZombies(zom);
        
        for(int i = 0; i < z.size(); i++)
        {
            for(int j = 0; j < zom.size(); j++)
            {
                if(z.get(i) == zom.get(j))
                    zom.get(j).takeDamage(70, false);
            }
        }
        
        this.takeDamage(999);
    }
    public void drawSelf(Graphics g)
    {
        /*
        if(armed == false)
        {
            g.setColor(Color.magenta);
        }
        else
        {
            g.setColor(Color.YELLOW);
        }
        
        g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
`*/
        
        if(armed == true)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX(), this.getY(), this.getW(), this.getH() + 10, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX()- 60, this.getY() - 20, this.getW() + 130, this.getH() + 70, null);
        }
    }   
}