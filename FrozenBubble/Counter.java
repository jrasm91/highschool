import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Font;

/**
 * Counter that displays a number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private int countdown = 5000;
    private int value = 0;
    private String text;
    private int stringLength;
    boolean yo;

    public Counter()
    {
        this("");
        yo = false;
    }
    
    public Counter(int count)
    {
        this("");
        countdown = count;
        
    }
    
    public Counter(boolean val)
    {
        this("");
        yo = val;
    }

    public Counter(String prefix)
    {
        text = prefix;
        stringLength = 100;

        setImage(new GreenfootImage(stringLength, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F));  // use larger font
        
        updateImage();
    }
    
    public void act() 
    {
        countdown--;
        updateImage();
    }
    
   
 

    public void add(int score)
    {
        value += score;
    }

    public void subtract(int score)
    {
        value -= score;
    }

    public int getValue()
    {
        return value;
    }

    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        if(yo == true)
        image.drawString(text +  value, 1, 18);
        if(yo == false)
        image.drawString(text +  countdown, 1, 18);
        
    }
}
