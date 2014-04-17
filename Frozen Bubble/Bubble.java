import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
import java.util.Random;

public class Bubble extends Actor
{
    private int dx = 0;
    private int dy = 0;
    public boolean floating = true;
    Random r = new Random();
    int randomx = r.nextInt(2);

    public Bubble()
    {
    }
    public Bubble(boolean yo)
    {
        floating = yo;
    }
    public void act() 
    {
        BubbleWorld bubble = (BubbleWorld)getWorld();
        if(getY() == 659)
        {
            setLocation(460,659);
            this.floating = false;
        }
        
        
        bubble = (BubbleWorld)getWorld();
        List list = bubble.getObjects(Bubble.class);
        
        Bubble lastBubble = (Bubble)list.get(list.size()-1);
    
        if(((getX() <= 27) || (getX() >= 405)) && getdY() != 0)
        {
            dx *= -1;
        }
        if((getY() <=27) && (dy > 0))
        {
            dy = 0;
            dx = 0;
            setLocation(getXCord(),bubble.theRow(findRow()));
        }
        
        if(lastBubble.getNeighbors().isEmpty() == false)
        {
            lastBubble.setdX(0);
            lastBubble.setdY(0);
            lastBubble.setLocation(lastBubble.getXCord(),bubble.theRow(lastBubble.findRow()));
        }
        setLocation(getX() + dx, getY() - dy);
    }   
    public void setdX(int x){dx = x;}
    public void setdY(int y){dy = y;}
    public int getdX(){return dx;}
    public int getdY(){return dy;}

    public int findRow()
    {
        int y = getY();
        int row;
        if((y-27)%47 <= 27) {row = (y-27)/47+1;}
        else{row = (y-27)/47+2;}
        return row;
    }
    
    public int findCol()
    {
        int row = findRow();
        int x = getX();
        int col;
        if(row % 2 == 1)
        {
            if((x-27) % 54 <= 26){col = (x-27)/54 + 1;}
            else{col = (x-27)/54 + 2;}
            return col;
        }
        else
        {
            if((x-41)% 54 <= 26){col = (x-41)/54 + 1;}
            else{col = (x-41)/54 + 2;}
            return col;
        }
    }
            
    public int getXCord()
    {
        int row = findRow();
        int col = findCol();
        if(row % 2 == 1){return (col-1)*54 + 27;}
        else{return (col-1)*54 + 54;}
    }
    
    public String getColor()
    {
        if(this instanceof GreenBubble){return "green";}
        if(this instanceof RedBubble){return "red";}
        if(this instanceof YellowBubble){return "yellow";}
        if(this instanceof BlueBubble){return "blue";}
        else{return "purple";}
    }
    
    public List getNeighbors(){return getObjectsInRange(57,Bubble.class);}
    public List getNeighbors(int d){return getObjectsInRange(d,Bubble.class);}
        
    public void connected()
    {
        BubbleWorld bubble = (BubbleWorld)getWorld();
        List alist = bubble.getObjects(Bubble.class);
        Bubble lastBubble = (Bubble)alist.get(alist.size()-1);
        List bubbleList = lastBubble.getNeighbors(60);
        ArrayList blist = new ArrayList();
        blist.add(lastBubble);
        
        for(int i = 0; i < bubbleList.size(); i++)
        {
            Bubble abub = (Bubble)bubbleList.get(i);
            if(abub.getColor().equals(lastBubble.getColor())){blist.add(abub);}
        }
        
        for(int i = 0; i < blist.size(); i++)
        {
            Bubble bbub = (Bubble)blist.get(i);
            List clist = bbub.getNeighbors(60);
            
            for(int k = 0; k< clist.size(); k++)
            {
                Bubble cbub = (Bubble)clist.get(k);
                if(cbub.getColor().equals(lastBubble.getColor()) && (blist.contains(cbub) == false)){blist.add(cbub);}
            }
        }
        if(blist.size() >= 3)
        {
            for(int j = 0; j < blist.size(); j++)
            {
                List yo = bubble.getObjects(Counter.class);
                Counter counter = (Counter)yo.get(0);
                Bubble bubby = (Bubble)blist.get(j);
                bubby.fall();
                counter.add(blist.size());
            }
            
        }
    }
        public void fall()
        {
            int omgthis;
            if(randomx == 1){omgthis = -10;}
            else{omgthis = 10;}
            setdX(omgthis);
            setdY(-10);
        }
        
        
            
                   
      
    }
 
    

        
    
    
 
