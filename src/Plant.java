import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Plant
{
    private int x;
    private int y;
    private int w;
    private int h;
    private int hp;
    
    private long fireRate;
    private long currTime;
    
    private Color col;
    
    private boolean settled;
    private Lane lane;
    private Tile tile;
    
    private int cost;
    
    public Plant(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        w = 80;
        h = 60;
        hp = 10;
        col = Color.ORANGE;
        
        fireRate = 1000;
        currTime = 0;
        
        cost = 100;
    }
    public String toString()
    {
        return "A PLANT.";
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
    public int getHp()
    {
        return hp;
    }
    public void setX(int newX)
    {
        x = newX;
        settled = true;
    }
    public void setY(int newY)
    {
        y = newY;
        settled = true;
    }
    public void setW(int newW)
    {
        w = newW;
    }
    public void setH(int newH)
    {
        h = newH;
    }
    public void setHp(int newHp)
    {
        hp = newHp;
    }
    public void setCol(Color c)
    {
        col = c;
    }
    public long getFireRate()
    {
        return fireRate;
    }
    public void setFireRate(long newFireRate)
    {
        fireRate = newFireRate;
    }
    public long getCurrTime()
    {
        return currTime;
    }
    public void setCurrTime(long newCurrTime)
    {
        currTime = newCurrTime;
    }
    public int getCost()
    {
        return cost;
    }
    public void setCost(int c)
    {
        cost = c;
    }
    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
   
        checkRemove(pl, l);
        
        detectZombie(z, p, pl, l, s, e);
        
        
        //checks to see if this plant has reached 0hp
        //if so, remove this plant from the lawn
        if(hp <= 0)
        {
            for(int i = 0; i < pl.size(); i++)
            {
                if(pl.get(i).getHp() <= 0)
                {
                    pl.remove(i);
                    i--;
                }
            }
        }
    }
    public void detectZombie(ArrayList<Zombie> z, ArrayList<Projectile> p, ArrayList<Plant> pl, Lawn l, ArrayList<Sun> s, ArrayList<Explosions> e)
    {
        for(int i = 0; i < z.size(); i++)
        {
            //gets the zombie's location and dimensions
            int zX = z.get(i).getX();
            int zW = z.get(i).getW();
            int zY = z.get(i).getY();
            int zH = z.get(i).getH();
            
            //checks to see if the zombie is within the plants row through the plant's y coor
            if(this.getY() > zY && this.getY() < zY + zH)
            {
                if(this.getX() + this.getW() < zX + zY)
                {
                    if(System.currentTimeMillis() >= currTime + fireRate)
                    {
                        //attack the zombie if it can
                        attack(p);
                        currTime = System.currentTimeMillis();
                        return;
                    }
                }
            }
            
            
        }
    }
    public void attack(ArrayList<Projectile> p)
    {
        p.add(new Pea(this.getX() + this.getW(), this.getY() + this.getH()/2 - 20));
    }
    public void takeDamage(int d)
    {
        hp -= d;
    }
    public Lane getLane(Lawn lawn)
    {
        Lane whichLane = lawn.locateLane(x, y);
        lane = whichLane;
        return whichLane;
    }
    public Tile getTile(Lawn lawn)
    {
        Lane whichLane = this.getLane(lawn);
        Tile whichTile = whichLane.locateTile(x, y);
        tile = whichTile;
        return whichTile;
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.orange);
        g.fillRect(x, y, w, h);
    }    
    public void checkRemove(ArrayList<Plant> pl, Lawn l)
    {
        //this makes sure if a plant dies, it gets removed from the map
        if(hp <= 0)
        {
            for(int i = 0; i < pl.size(); i++)
            {
                if(pl.get(i).getHp() <= 0)
                {
                    //this allows the tile to be plantable
                    pl.get(i).getTile(l).freeUp(pl);
                    
                    //this removes the plant from the lawn
                    pl.remove(i);
                    
                    i--;
                }
            }
        }
    }
}
