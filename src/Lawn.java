//package LiJacob;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Lawn
{
    private ArrayList<Lane> lanes;
   
    private int x;
    private int y;
   
    private int w;
    private int h;
   
    private ImageIcon img1;
   
    //SURIEL:  Added an attribute that represents the lane number.  Not needed but I think it will help you with organization
    private int number;
    public Lawn(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        
        //5 lanes in the game
        lanes = new ArrayList<Lane>();
        for(int i = 0; i < 5; i++)
        {
            lanes.add(new Lane(x, y + 110 * i, i + 1));
        }
        
        img1 = new ImageIcon(Lawn.class.getResource("Capture.PNG"));
    }
    public String toString()
    {
        return "The lawn";
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
    public ArrayList<Lane> getLanes()
    {
        return lanes;
    }
    public void create(Graphics g)
    {
        Graphics2D g2d;
        g2d = (Graphics2D) g;
        Image img = img1.getImage();

        g2d.drawImage(img, x, y, w, 80, null);
        
        //creating the lanes that make up the lawn
        for(int i = 0; i < lanes.size(); i++)
        {
            lanes.get(i).create(g);
        }
       
    }
    public Lane locateLane(int xCoor, int yCoor)
    {
        //returns the lanne that's being clicked on
        for(int i = 0; i < lanes.size(); i++)
        {
            //SURIEL:  Wrote a helper method to check if the click happened inside of the Tile
            Lane curr = lanes.get(i);
            if(curr.clickedInsideMe(xCoor, yCoor))
            {
                System.out.println("Clicked on lane number " + number);
                return curr;
            }
            
        }
        return null;
    }
}