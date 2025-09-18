import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Shovel
{
    private int x, y, w, h;
    private boolean selected;
    public Shovel(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        w = 80;
        h = 80;
        
        selected = false;
    }
    public void setFalse()
    {
        selected = false;
    }
    public void setTrue()
    {
        selected = true;
    }
    public boolean getSelected()
    {
        return selected;
    }
    public void act(int xCoor, int yCoor, Lawn l, ArrayList<Plant> p )
    {
        if(clickedInsideMe(xCoor, yCoor))
        {
            selected = true;
            return;
        }
        
        if(selected == true)
        {
            
            Lane lane = l.locateLane(xCoor, yCoor);
            if(lane != null)
            {
                Tile tile = lane.locateTile(xCoor, yCoor);
                tile.freeUp(p);
            }
            selected = false;
            /*
            for(int i = 0; i < 5; i++)
            {
                
                
                ArrayList<Lane> lanes = l.getLanes();
                Lane lane = lanes.get(i);
                ArrayList<Tile> tiles = lane.getTiles();
                
                    
                
                for(int j = 0; j < 5; i++)
                {
                    
                    //boolean clicked = tiles.get(j).clickedInsideMe(xCoor, yCoor);
                    //Tile clicked = tiles.get(j);
                    //if(clicked)
                    {
                        //tiles.get(j).freeUp(p);
                    }

                }
                
            }
*/
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
        g.setColor(Color.blue);
        g.fillRect(x, y, w, h);
    }
}