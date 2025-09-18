//package LiJacob;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Tile
{
    private boolean occupied; //by a plant
    private ArrayList<Zombie> zombies;
    private Color col;
   
    private int x;
    private int y;
   
    private int w;
    private int h;
   
    private Plant currPlant;
    
    /*
    public Tile()
    {
        x = 50;
        y = 50;
        w = 125;
        h = 125;
    }
    */
    public Tile(int xCoor, int yCoor, Color color)
    {
        x = xCoor;
        y = yCoor;
        w = 108;
        h = 105;
        col = color;
       
        occupied = false;
        zombies = new ArrayList<Zombie>();
       
        currPlant = null;
    }
    public String toString()
    {
        return "A tile at (" + x + ", " + y + ")"; 
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getW()
    {
        return w;
    }
    public int getH()
    {
        return h;
    }
    public boolean getOccupied()
    {
        return occupied;
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, h);
       
        g.setColor(col);
        g.fillRect(x + 5, y + 5, w - 10, h - 10);
    }
    public void takeUp(Plant p, ArrayList<Plant> stuff)
    {
        if(occupied == false)
        {
            occupied = true;
            currPlant = p;
            stuff.add(p);
        }
    }
    public void freeUp(ArrayList<Plant> p)
    {
        occupied = false;
        if(currPlant != null)
        {
            for(int i = 0; i < p.size(); i++)
            {
                if(currPlant == p.get(i))
                {
                    p.get(i).takeDamage(10000);
                    currPlant = null;
                    return;
                }
            }
        }
    }
    public ArrayList<Zombie> countZombies(ArrayList<Zombie> zom)
    {
        ArrayList<Zombie> z = new ArrayList<Zombie>();
        
        for(int i = 0; i < zom.size(); i++)
        {
            int zX = zom.get(i).getX();
            int zW = zom.get(i).getW();
            int zY = zom.get(i).getY();
            int zH = zom.get(i).getH();
           
            if(y < zY && y + h > zY + zH)
            {
                if(x < zX && x + w > zX || x > zX && x < zX + zW || x + w > zX && x + w < zX + zW)
                //anything that's inside, but has a part of hitbox inside
                {
                    z.add(zom.get(i));
                }
            }
        }
        
        return z;
    }
    //SURIEL:  Added this method to check if the mouse x and y are inside of this tile.
    public boolean clickedInsideMe(int mx, int my)
    {
        if(mx >= x && mx <= x + w && my >= y && my <= y + h)
            return true;
        else
            return false;
    }
}