import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class CherryBomb extends Plant
{
    private int timer;
    private long currTime; 
    private boolean placed;
    
    private ImageIcon img1, img2;
    
    public CherryBomb(int xCoor, int yCoor)
    {
        super(xCoor, yCoor);
        timer = 1500;
        currTime = System.currentTimeMillis();
        placed = false;
        this.setCost(200);
        this.setHp(500);
        
        img1 = new ImageIcon(Lawn.class.getResource("CherryBomb.png"));
        img2 = new ImageIcon(Lawn.class.getResource("CherryBomb2.png"));
    }

    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        if(this.getX() > 0 && this.getY() > 0)
        {
            if(placed == false)
            {
                placed = true;
                currTime = System.currentTimeMillis();
            }
            
            //remove this plant after it "dies" from exploding
            checkRemove(pl, l);

            //this allows the Cherry bomb to explod and "die" thus allowing checkRemove above
            if(System.currentTimeMillis() >= timer + currTime)
                this.explode(z, l, e);
        }
    }
    public void explode(ArrayList<Zombie> z, Lawn l, ArrayList<Explosions> e)
    {
        //gets the dimension and position of the Tile it is placed in
        Tile t = this.getTile(l);
        
        int leftMost = t.getX() - t.getW();
        int rightMost = t.getX() + t.getW() * 2;
        int upMost = t.getY() - t.getH() - 5;
        int downMost = t.getY() + t.getH() * 2 - 5;
        
        for(int i = 0; i < z.size(); i++)
        {
            //any zombie within a whole tile radius will suffer damage
            if(z.get(i).getX() >= leftMost && z.get(i).getX() <= rightMost && z.get(i).getY() >= upMost && z.get(i).getY() <= downMost)
            {
                z.get(i).takeDamage(90, false);
            }
        }
        
        //this kills itself so it gets removed from the map
        this.takeDamage(999);
        
        //draws out the explosion (for imaging purposes)
        e.add(new Explosions(this.getX() - 450, this.getY() - 350, 1));
    }
    public void drawSelf(Graphics g)
    {
        //g.setColor(Color.red);
        
       // g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        if(System.currentTimeMillis() >= timer + currTime - 500)
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, this.getX()- 90, this.getY() - 50, this.getW() + 180, this.getH() + 70, null);
        }
        else
        {
            Graphics2D g2d;
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, this.getX()- 50, this.getY() - 10, this.getW() + 100, this.getH() + 30, null);
        }
    }   
}