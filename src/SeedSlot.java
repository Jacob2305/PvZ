//package LiJacob;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class SeedSlot
{
    private int maxSlot;//how many slots there will be
    private int perRow;//how it will be displayed
    private ArrayList<Seed> loadout;
    
    private int x;
    private int y;
    
    private boolean forLoadout;
    
    public SeedSlot(int xCoor, int yCoor, int max, int r, boolean l)
    {
         x = xCoor;
         y = yCoor;
         
         maxSlot = max;
         perRow = r;
         
         if(l == true)
         {
            loadout = new ArrayList<Seed>();
            int count = 0;//this will help with the row col thingy
            int reset = 0;//start a new col
            for(int i = 0; i < max; i++)
            {
                loadout.add(new Seed(x + ((120 + 10)* count) , y + ((70 + 10) * reset), 0));
                count++;
                if(count == perRow)
                {
                    count = 0;
                    reset++;
                }
            }
         }
         else
         {
            loadout = new ArrayList<Seed>();
            int count = 0;//this will help with the row col thingy
            int reset = 0;//start a new col
            int num = 1;
            for(int i = 0; i < max; i++)
            {
                loadout.add(new Seed(x + ((120 + 10)* count) , y + ((70 + 20) * reset), num));
                count++;
                if(count == perRow)
                {
                    count = 0;
                    reset++;
                }
                num++;
            }
         }
    }

    public ArrayList<Seed> getList()
    {
        return loadout;
    }
    public Seed whateverMrSurielSaid(int xCoor, int yCoor)
    {
        for(int i = 0; i < loadout.size(); i++)
        {
            Seed curr = loadout.get(i);
            if(curr.clickedInsideMe(xCoor, yCoor))
            {
                System.out.println("Clicked on seed #" + (i + 1));
                return curr;
            }
            
        }
        return null;
    }
    public void create(Graphics g, SunBank bank)
    {
        for(int i = 0; i < loadout.size(); i++)
        {
            loadout.get(i).drawSelf(g, bank);
        }
    }
    
}