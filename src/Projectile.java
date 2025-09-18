import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Projectile 
{
    private int x;
    private int y;
    private int s;
    private int diam;
    private int dam;
    private int pierce;
    private Color col;
    
    
    public Projectile(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        s = 5;
        diam = 10;
        col = Color.BLUE;
        dam = 1;
        pierce = 1;

    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getS()
    {
        return s;
    }
    public int getDiam()
    {
        return diam;
    }
    public int getDam()
    {
        return dam;
    }
    public int getPierce()
    {
        return pierce;
    }
    public void setX(int newX)
    {
        x = newX;
    }
    public void setY(int newY)
    {
        y = newY;
    }
    public void setPierce(int newPierce)
    {
        pierce = newPierce;
    }
    public void act(ArrayList<Zombie> z, ArrayList<Projectile> p)
    {
        //travels foward
        x += s;
        
        //if it hits a zombie, deal damage to it
        for(int i = 0; i < z.size(); i++)
        {
            if(detectCollision(z.get(i)))
            {
                z.get(i).takeDamage(dam, false); 
                pierce--;
                if(pierce <= 0)
                {
                    for(int j = 0; j < p.size(); j++)
                    {
                        //disappears when it is out of pierce
                        if(p.get(j).getPierce() <= 0)
                        {
                            p.remove(j);
                            j--;
                        }
                            
                    }
                }
            }
        }
    }
    public boolean detectCollision(Zombie zombie)
    {
        boolean output = false;
        
        int zX = zombie.getX();
        int zW = zombie.getW();
        int zY = zombie.getY();
        int zH = zombie.getH();
        
        //detects if it contacts the dimensions of a zombie
        if(y > zY && y < zY + zH)
        {
            if(x + diam > zX && x + diam < zX + zW)
            {
                return true;
            }
        }
        return output;
    }
    private double distance(int x1, int y1, int x2, int y2)
    {
        double output;
        output = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return output;
    }

    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillOval(x, y, diam, diam);
    }
}