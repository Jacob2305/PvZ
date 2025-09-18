//package LiJacob;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Lane
{
    private ArrayList<Tile> tiles;
   
    private int x;
    private int y;
   
    private int w;
    private int h;
   
   
    //SURIEL:  Added an attribute that represents the lane number.  Not needed but I think it will help you with organization
    private int number;
    public Lane(int xCoor, int yCoor, int n)
    {
        x = xCoor;
        y = yCoor;
        
        //9 tiles per lane
        tiles = new ArrayList<Tile>();
        for(int i = 0; i < 9; i++)
        {
            tiles.add(new Tile(x + 108 * i, y, Color.GREEN));
        }
        
        w = 900;
        h = 100;
        
        number = n;
    }
    public String toString()
    {
        return "A lane at " + x + ", " + y;
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
    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }
    public void create(Graphics g)
    {
        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).drawSelf(g);
        }
       
    }
    /*
    this will return what tile is being clicked on
    it will help with spawning plants in the correct lane and tile
    */
    public Tile locateTile(int xCoor, int yCoor)
    {
        for(int i = 0; i < tiles.size(); i++)
        {
            //SURIEL:  Wrote a helper method to check if the click happened inside of the Tile
            Tile curr = tiles.get(i);
            if(curr.clickedInsideMe(xCoor, yCoor))
            {
                System.out.println("Clicked on tile " + i + " in lane number " + number);
                return curr;
            }
            
        }
        return null;
    }
    public void spawnZombie(ArrayList<Zombie> z, int i)
    {
        if(i == 0)
            z.add(new Zombie(tiles.get(8).getX() + tiles.get(8).getW(), y + 5));
        else if(i == 1)
            z.add(new ConeHead(tiles.get(8).getX() + tiles.get(8).getW(), y + 5));
        else if(i == 2)
            z.add(new BucketHead(tiles.get(8).getX() + tiles.get(8).getW(), y + 5));
        else if(i == 3)
            z.add(new NewspaperZombie(tiles.get(8).getX() + tiles.get(8).getW(), y + 5));
        else if(i == 4)
            z.add(new FootBallZombie(tiles.get(8).getX() + tiles.get(8).getW(), y + 5));
    }
    public boolean clickedInsideMe(int mx, int my)
    {
        if(mx >= x && mx <= x + w && my >= y && my <= y + h)
            return true;
        else
            return false;
    }
}