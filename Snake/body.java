import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class body extends Actor
{
    private SnakeWorld world = (SnakeWorld)getWorld();
    private Greenfoot greenfoot = new Greenfoot();
    private Random random = new Random();
    
    public void selfcollide() 
    {
        
        SnakeWorld world = (SnakeWorld)getWorld();
        if(this.getIntersectingObjects(body.class).isEmpty() == false){greenfoot.stop();}
    }
    
    public boolean eat()
    {
        if(this.getIntersectingObjects(food.class).isEmpty() == false)
        {
            return true;
        }
        else return false;
    }
}
