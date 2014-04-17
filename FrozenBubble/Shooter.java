import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Shooter extends Actor
{

    public void act() 
    {
        int r = getRotation();
        
        if(Greenfoot.isKeyDown("left"))
        {
            setRotation(r - 1);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            setRotation(r + 1);
        }
        if(Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up"))
        {
            BubbleWorld bubble = (BubbleWorld)getWorld();
            double x = 10* Math.tan(Math.toRadians(r));
            int y = 10;
            bubble.shoot((int)x,y);
            
        }
            
    } 

}
