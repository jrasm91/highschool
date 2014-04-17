import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeWorld  extends World
{
    public ArrayList list;
    public String direction = "down";
    public Greenfoot greenfoot = new Greenfoot();
    public Random random = new Random();
    public String cheat = "off";
   
    public SnakeWorld()
    {    
        super(30, 30, 19); 
        world1();
    }
    
    public void act()
    {
        body first = (body)list.get(0);
        if(cheat.equals("off"))
        {
            first.selfcollide();
            collide();
        }
        keyEvent();
        move();
    }
    
    public void collide()
    {
        Actor first = (Actor)list.get(0);
        body last = (body)list.get(list.size()-1);
        if(greenfoot.isKeyDown("up") && (direction == "down")) {greenfoot.stop();}
        if(greenfoot.isKeyDown("down") && (direction == "up")) {greenfoot.stop();}
        if(greenfoot.isKeyDown("left") && (direction == "right")) {greenfoot.stop();}
        if(greenfoot.isKeyDown("right") && (direction == "left")) {greenfoot.stop();}
    }
    
    public void keyEvent()
    {
        if(greenfoot.isKeyDown("o")){cheat = "on";}
        if(greenfoot.isKeyDown("f")){cheat = "off";}
        if(greenfoot.isKeyDown("up")){direction = "up";}
        if(greenfoot.isKeyDown("down")){direction = "down";}
        if(greenfoot.isKeyDown("left")){direction = "left";}
        if(greenfoot.isKeyDown("right")){direction = "right";}
    }
    
    public void move()
    {    
        body first = (body)list.get(0);
        body last = (body)list.get(list.size()-1);
        body newbody = new body();
        if(direction.equals("up"))
        {
            addObject(newbody,first.getX(), first.getY()-1);
            list.add(0,newbody);
        }
        if(direction.equals("down"))
        {
            addObject(newbody,first.getX(), first.getY()+1);
            list.add(0,newbody);
        }
        if(direction.equals("left"))
        {
            addObject(newbody,first.getX()-1, first.getY());
            list.add(0,newbody);
        }
        if(direction.equals("right"))
        {
            addObject(newbody,first.getX()+1, first.getY());
            list.add(0,newbody);
        }
        if((first.eat() == false) && (greenfoot.isKeyDown("=") == false))
        {
            removeObject((body)list.get(list.size()-1));
            list.remove(last);
        }
        if((greenfoot.isKeyDown("-") == true) && (list.size() > 3))
        {
            removeObject((body)list.get(list.size()-1));
            list.remove(list.size()-1);
        }
        if(first.eat() == true)
        {
            food foodz = (food)getObjects(food.class).get(0);
            removeObject(foodz);
            addFood();
        }
    }
  
    public void addFood()
    {   
        int x = random.nextInt(30);
        int y = random.nextInt(30);
        addObject(new food(), x,y);
    }
    
    public void world1()
    {
        body body1 = new body();
        body body2 = new body();
        body body3 = new body();  
        addObject(body1, 5, 5);
        addObject(body2, 6, 5);
        addObject(body3, 7, 5);
        list = new ArrayList();
        list.add(body1);
        list.add(body2);
        list.add(body3);
        addFood();
    }
}