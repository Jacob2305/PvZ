import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Level1
{
    private long startTime;
    private boolean started;
    
    private boolean zom1, zom2, zom3, zom4, zom5, zom6, zom7, zom8, zom9, zom10, zom11, zom12, zom13, zom14, zom15, zom16, zom17, zom18, zom19, zom20;
    
    private boolean w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17, w18, w19, w20;

    //method added by Gemini
    public boolean isAllZombiesSpawned()
    {
        return zom20;
    }
    
    public Level1()
    {
        startTime = System.currentTimeMillis();
        started = false;
    }
    public void start()
    {
        //once this method is called, zombies will start filing out
        if(started == false)
        {
            startTime = System.currentTimeMillis();
            started = true;
        }
    }
     public long getStartTime()
    {
        return startTime + 201300;
    }
    public void act(ArrayList<Zombie> z, Lawn l)
    {
        start();
        
        //below are the zombies that will be sent out at tehir respective time
        if(System.currentTimeMillis() >= startTime + 18000 && zom1 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            zom1 = true;
            w1 = true;
        }
        if(System.currentTimeMillis() >= startTime + 32000 && zom2 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            zom2 = true;
            w2 = true;
        }
        
        if(System.currentTimeMillis() >= startTime + 50000 && zom3 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            zom3 = true;
            w3 = true;
        }
        if(System.currentTimeMillis() >= startTime + 55000 && zom4 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            zom4 = true;
            w4 = true;
        }
        
        if(System.currentTimeMillis() >= startTime + 65200 && zom5 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            zom5 = true;
            w5 = true;
        }
        if(System.currentTimeMillis() >= startTime + 72200 && zom6 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            zom6 = true;
            w6 = true;
        }
        
        if(System.currentTimeMillis() >= startTime + 77200 && zom7 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            zom7 = true;
            w7 = true;
        }
        
        //wave 1
        if(System.currentTimeMillis() >= startTime + 95200 && zom8 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            zom8 = true;
            w8 = true;
        }
        if(System.currentTimeMillis() >= startTime + 98200 && zom9 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            zom9 = true;
            w9 = true;
        }
        
        //past wave 1
        if(System.currentTimeMillis() >= startTime + 110200 && zom10 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            zom10 = true;
            w10 = true;
        }
        if(System.currentTimeMillis() >= startTime + 116200 && zom11 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 0);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom11 = true;
            w11 = true;
        }
        
        if(System.currentTimeMillis() >= startTime + 123200 && zom12 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            zom12 = true;
            w12 = true;
        }
        if(System.currentTimeMillis() >= startTime + 131200 && zom13 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom13 = true;
            w13 = true;
        }
        if(System.currentTimeMillis() >= startTime + 140200 && zom14 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom14 = true;
            w14 = true;
        }
        
        if(System.currentTimeMillis() >= startTime + 150200 && zom15 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom15 = true;
            w15 = true;
        }
        if(System.currentTimeMillis() >= startTime + 153200 && zom16 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom16 = true;
            w16 = true;
        }
        
        //FINAL WAVE
        if(System.currentTimeMillis() >= startTime + 182200 && zom17 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 1);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom17 = true;
            w17 = true;
        }
        if(System.currentTimeMillis() >= startTime + 185200 && zom18 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom18 = true;
            w18 = true;
        }
        if(System.currentTimeMillis() >= startTime + 189200 && zom19 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 2);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 3);
            zom19 = true;
            w19 = true;
        }
        if(System.currentTimeMillis() >= startTime + 192200 && zom20 == false)
        {
            int rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            rand = (int)(Math.random() * 5);
            l.getLanes().get(rand).spawnZombie(z, 4);
            zom20 = true;
            w20 = true;
        }

    }
    public void drawSelf(Graphics g)
    {
        if(System.currentTimeMillis() > startTime + 89200 && System.currentTimeMillis() < startTime + 94200)
        {
            Font f = new Font("Roboto", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("A HUGE WAVE OF ZOMBIES IS APPROACHING", 100, 400);
        }
        if(System.currentTimeMillis() > startTime + 172200 && System.currentTimeMillis() < startTime + 177200)
        {
            Font f = new Font("Roboto", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("A HUGE WAVE OF ZOMBIES IS APPROACHING", 100, 400);
        }
        if(System.currentTimeMillis() > startTime + 177200 && System.currentTimeMillis() < startTime + 181200)
        {
            Font f = new Font("Roboto", Font.BOLD, 80);
            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("FINAL WAVE", 500, 400);
        }
        
        
        g.setColor(Color.black);
        g.fillRect(300, 45, 610, 30);
        
        g.setColor(Color.lightGray);
        g.fillRect(305, 50, 600, 20);
        
        g.setColor(Color.black);
        g.fillRect(305 + 600/15 * 7, 10, 10, 40);
        
        g.setColor(Color.black);
        g.fillRect(300 + 600/15 * 15, 10, 10, 40);
        
        g.setColor(Color.red);
        g.fillRect(300 + 600/15 * 15, 10, 30, 20);
        
        g.setColor(Color.red);
        g.fillRect(305 + 600/15 * 7, 10, 30, 20);
        if(w1)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 1, 20);
        }
        if(w2)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 2, 20);
        }
        if(w3)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 3, 20);
        }
        if(w4)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 4, 20);
        }
        if(w5)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 5, 20);
        }
        if(w6)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 6, 20);
        }
        if(w7)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 7, 20);
        }
        if(w8)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 8, 20);
        }
        if(w9)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 9, 20);
        }
        if(w10)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 10, 20);
        }
        if(w11)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 11, 20);
        }
        if(w12)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 12, 20);
        }if(w13)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 13, 20);
        }
        if(w14)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 14, 20);
        }
        if(w15)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 15, 20);
        }
        /*
        if(w16)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 16, 20);
        }
        if(w17)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 17, 20);
        }
        if(w18)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 18, 20);
        }
        if(w19)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 19, 20);
        }
        if(w20)
        {
            g.setColor(Color.green);
            g.fillRect(305, 50, 600/15 * 20, 20);
        }
        */
        
        
        
    }
}