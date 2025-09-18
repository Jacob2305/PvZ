//package LiJacob;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Seed
{
    private int x;
    private int y;
    private int w;
    private int h;
    
    private int num; //which plant
    
    private boolean picked;
    
    private int recharge = 1000;
    private long curr;
    
    private boolean recharging;
    private ImageIcon img0, img1, img2, img3, img4, img5, img6, img7;
    
    public Seed(int xCoor, int yCoor, int p)
    {
        x = xCoor;
        y = yCoor;
        
        w = 120;
        h = 70;
        
        num = p;
        picked = false;
        
        curr = System.currentTimeMillis();
        recharging = false;
        
        img0 = new ImageIcon(Lawn.class.getResource("EmptySeed.PNG"));
        
        img1 = new ImageIcon(Lawn.class.getResource("PeaShooterSeed.PNG"));
        img2 = new ImageIcon(Lawn.class.getResource("WallNutSeed.PNG"));
        img3 = new ImageIcon(Lawn.class.getResource("PotatoMineSeed.PNG"));
        img4 = new ImageIcon(Lawn.class.getResource("CherryBombSeed.PNG"));
        img5 = new ImageIcon(Lawn.class.getResource("SnowPeaSeed.PNG"));
        img6 = new ImageIcon(Lawn.class.getResource("RepeaterSeed.PNG"));
        img7 = new ImageIcon(Lawn.class.getResource("SunFlowerSeed.PNG"));
        
        

    }
    public String toString()
    {
        if(num == 1)
            return "peashooter";
        else
            return null;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    private void setX(int newX)
    {
        x = newX;
    }
    private void setY(int newY)
    {
        y = newY;
    }
    public int getNum()
    {
        return num;
    }
    public void setNum(int n)
    {
        num = n;
    }
    public void toFalse()
    {
        picked = false;
    }
    public void toTrue()
    {
        picked = true;
    }
    public boolean isRecharging()
    {
        return recharging;   
    }
    public void setCharge()
    {
        recharging = true;
        curr = System.currentTimeMillis();
    }
    public void act(SeedSlot picks)
    {
        if(num == 1)//peashooter
            recharge = 5000; // 5 sec
        else if(num == 2)//wallnut
            recharge = 20000;
        else if(num == 3)//potato mine
            recharge = 20000; //30 sec
        else if(num == 4)//cherry bomb
            recharge = 30000;
        else if(num == 5)//snow pea
            recharge = 5000;
        else if(num == 6)//repeater but glitched
            recharge = 5000;
        else if(num == 7)//sunflower
            recharge = 5000;
        
        if(System.currentTimeMillis() > curr + recharge)
            recharging = false;
    }
    
    public void clicked(SeedSlot picks)
    {
        if(clickedInsideMe(this.getX(), this.getY()) && picked == false)
        {
            picked = true;

            int i = 0;
            boolean placed = false; //make sure it don't double place
            
            while(i < picks.getList().size() && placed == false)
            {
                if(picks.getList().get(i).getNum() == 0)
                {
                    picks.getList().get(i).setNum(this.getNum());
                    placed = true;
                }
                i++;
            }
    
        }
    }
    public void removeSeed(SeedSlot picks, SeedSlot choices)
    {
        if(this.getNum() != 0)
        {
            int i = 0;
            boolean found = false;
            while(i < choices.getList().size() && found == false)
            {
                if(choices.getList().get(i).getNum() == this.getNum())
                {
                    choices.getList().get(i).toFalse();
                    this.setNum(0);
                    found = true;
                }
                i++;
            }
            
            
            for(int j = 0; j < picks.getList().size() - 1; j++)
            {
                if(picks.getList().get(j).getNum() == 0)
                {
                   picks.getList().get(j).setNum(picks.getList().get(j + 1).getNum());
                   picks.getList().get(j + 1).setNum(0);
                }
            }
        }
        
        
    }
    public boolean clickedInsideMe(int mx, int my)
    {
        if(mx >= x && mx <= x + w && my >= y && my <= y + h)
            return true;
        else
            return false;
    }
    public void drawSelf(Graphics g, SunBank bank)
    {
        Graphics2D g2d;

        
        if(num == 0)
        {
            g.setColor(Color.black);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img0.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 1)
        {
            g.setColor(Color.red);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img1.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 2)
        {
            g.setColor(Color.blue);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img2.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 3)
        {
            g.setColor(Color.yellow);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img3.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 4)
        {
            g.setColor(Color.magenta);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img4.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 5)
        {
            g.setColor(Color.cyan);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img5.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 6)
        {
            g.setColor(Color.pink);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img6.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else if(num == 7)
        {
            g.setColor(Color.white);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img7.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        else
        {
            g.setColor(Color.green);
            g.fillRect(x, y, w, h);
            g2d = (Graphics2D) g;
            Image img = img0.getImage();
            g2d.drawImage(img, x, y, w, h, null);
        }
        
        
            
        
        Font f = new Font("Aerial", Font.BOLD, 30);
        g.setFont(f);
        
        
        g.setColor(Color.white);
        
        String cost;
        
        if(num == 1)//peashooter
        {
            cost = "" + 100;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 50, 25);
            if(bank.getAmount() < 100)
            {
                g.setColor(Color.red);
                
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 2)//wallnut
        {
            cost = "" + 50;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 35, 25);
            if(bank.getAmount() < 50)
            {
                g.setColor(Color.red);
                
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 3)//potato mine
        {
            cost = "" + 25;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 35, 25);
            if(bank.getAmount() < 25)
            {
                g.setColor(Color.red);
                
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 4)//cherry bomb
        {
            cost = "" + 200;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 50, 25);
            if(bank.getAmount() < 200)
            {
                g.setColor(Color.red);
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 5)//snow pea
        {
            cost = "" + 175;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 50, 25);
            if(bank.getAmount() < 175)
            {
                g.setColor(Color.red);
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 6)//repeater but glitched
        {
            cost = "" + 200;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 50, 25);
            if(bank.getAmount() < 200)
            {
                g.setColor(Color.red);
            }
            else
                g.setColor(Color.white);
        }
        else if(num == 7)//sunflower
        {
            cost = "" + 50;
            g.setColor(Color.black);
            g.fillRect(x + w - 40, y + h - 20, 35, 25);
            if(bank.getAmount() < 50)
            {
                g.setColor(Color.red);
            }
            else
                g.setColor(Color.white);
        }
        
        else
        {
            cost = "";
            
        }

        g.drawString(cost, x + w - 40, y + h + 3);
        
        f = new Font("Aerial", Font.BOLD, 35);
        g.setFont(f);
        g.setColor(Color.red);
        
        if(recharge > 0 && recharging == true)
        {
            g.setColor(Color.black);
            g.fillRect(x + 30, y + 20, 40, 40);
            
            g.setColor(Color.yellow);
            long rechargeTime = Math.abs((curr + recharge - System.currentTimeMillis())) / 1000;
            String time = "" + rechargeTime;
            if(rechargeTime > 9)
                g.drawString(time, x + 30, y + 50);
            else
                g.drawString(time, x + 40, y + 50);
            //System.out.println(time);
        }
    }
}
