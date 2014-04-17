import greenfoot.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class BubbleWorld extends World
{   
    boolean ableToShoot = true;
    int nextLevel = 2;
    
    public BubbleWorld()
    {    
        super(432,661,1);
        addObject(new Counter(true), 57, 645);
        addObject(new Shooter(),240, 606); 
        addObject(new Counter(5000), 419, 645);
        world4();
        addObject(nextBubble(), 240,612);
    }
    public void world0()
    {
        addObject(randomBubble(), 27, 27);
    }
    
    public void world1()
    {
        String[] list1 ={"red","green", "red", "green","red","green","red","green"};
        String[] list2 ={"green", "red","green", "red", "green","red","green"};
        String[] list3 ={"red","green", "red", "green","red","green","red","green"};
        String[] list4 ={"green","red","green", "red", "green","red","green",};
        addRow(1,list1);
        addRow(2,list2);
        addRow(3,list3);
        addRow(4,list4);
    }
    
    public void world2()
    {
        String[] list1 = {"red","red","yellow","yellow","blue","blue","green","green"}; 
        String[] list2 = {"red","red","yellow","yellow","blue","blue","green"};
        String[] list3 = {"blue","blue","green","green","red","red","yellow","yellow"};
        String[] list4 = {"blue","green","green","red","red","yellow","yellow"};
        addRow(1,list1);
        addRow(2,list2);
        addRow(3,list3);
        addRow(4,list4);
    }
    
    public void world3()
    {
        String[] list1 = {"green","blue","red","blue","green","blue","blue","green"};
        String[] list2 = {"green","blue","blue","green","green","blue","green"};
        String[] list3 = {"purple","green","blue","green","purple","green","green","purple"};
        String[] list4 = {"purple","green","green","purple","purple","green","purple"};
        String[] list5 = {"purple","purple","green","purple","purple","purple","purple","purple"};
        addRow(1,list1);
        addRow(2,list2);
        addRow(3,list3);
        addRow(4,list4);
        addRow(5,list5);
    }
    
    public void world4()
    {
        String[] list1 = {"","yellow","yellow","yellow","red","red","red"};
        String[] list2 = {"yellow","","","","","","red"};
        String[] list3 = {"","yellow","","","","","red"};
        String[] list4 = {"yellow","blue","green","purple","blue","green","red"};
        String[] list5 = {"","green","purple","blue","green","purple","blue",};
        String[] list6 = {"","blue","green","purple","blue","green"};
        addRow(1,list1);
        addRow(2,list2);
        addRow(3,list3);
        addRow(4,list4);
        addRow(5,list5);
        addRow(6,list6);
    }
    
    public void world5()
    {
        String[] list1 = {"blue","red","green","yellow","yellow","green","red","blue"};
        String[] list2 = {"blue","red","green","yellow","green","red","blue"};
        String[] list3 = {"","blue","red","green","green","red","blue"};
        String[] list4 = {"","blue","red","green","red","blue"};
        String[] list5 = {"","","blue","red","red","blue"};
        String[] list6 = {"","","blue","red","blue"};
        String[] list7 = {"","","","blue","blue"};
        String[] list8 = {"","","","blue"};
        addRow(1,list1);
        addRow(2,list2);
        addRow(3,list3);
        addRow(4,list4);
        addRow(5,list5);
        addRow(6,list6);
        addRow(7,list7);
        addRow(8,list8);
    }
        
    public int theRow(int row){return (row-1)*47 + 27;}
    public int theCol(int col){return (col-1)*54 + 27;}
    public int theCol(int col,int odd){return (col-1)*54 + 0;}
    
    public Bubble randomBubble()
    {
        Random r = new Random();
        if(r.nextInt(4) == 0){return new RedBubble();}
        if(r.nextInt(4) == 1){return new YellowBubble();}
        if(r.nextInt(4) == 2){return new GreenBubble();}
        if(r.nextInt(4) == 3){return new BlueBubble();}
        else{return new PurpleBubble();}
    }
    
    public void newCounter()
    {
        List yo = getObjects(Counter.class);
        Counter yo2 = (Counter)yo.get(1);
        removeObject(yo2);
        addObject(new Counter(5000), 419, 645);
    }
        
    public void act()
    {
        List list = getObjects(Bubble.class);
        for(int a = 0; a< list.size(); a++)
        {
            Bubble bubby = (Bubble)list.get(a);
            if(((bubby.getX() <= 27) || (bubby.getX() >= 406))  && (bubby.getdY() < 0)){removeObject(bubby);}
        }
        
        if(getObjects(Bubble.class).size() <= 1)
        {
            //if(nextLevel == 6){world6(); nextLevel++;}
            //if(nextLevel == 5){world5(); nextLevel++;}
            if(nextLevel == 4){world4(); nextLevel++;}
            if(nextLevel == 3){world3(); nextLevel++;}
            if(nextLevel == 2){world2(); nextLevel++;}
            if(nextLevel == 1){world1(); nextLevel++;}
            if(nextLevel == 0){world0(); nextLevel++;}
            addObject(nextBubble(), 240,612);
            newCounter();
        }
            
        isFloating();
        
        for(int i = 0; i < list.size()-1; i++)
        {
            Bubble b = (Bubble)list.get(i);
            if(b.getdY() == 0){b.setLocation(b.getXCord(),((b.findRow()-1)*47 + 27));}
        }
                
        Bubble bubble = (Bubble)list.get(list.size()-1);
        
        if((ableToShoot == false) && (bubble.getdY() == 0) )
        {
            ableToShoot = true;
            bubble.setLocation(bubble.getXCord(),((bubble.findRow()-1)*47 + 27)); 
            bubble.connected();
            bubble.setLocation(bubble.getXCord(),((bubble.findRow()-1)*47 + 27));
            isFloating();
            addObject(nextBubble(), 240,612);
        }
    }
    
    public void addRandomRow(int y)
    {
        if((y-1)%2 == 0)
        {for(int i = 0; i<= 7; i++){addObject(randomBubble(), i*58 + 27, (y-1)*50 + 27);}}
        if((y-1)%2 == 1)
        {for(int i = 0; i<= 6; i++){addObject(randomBubble(), i*58 + 56, (y-1)*50 + 27);}}
    }
    
    public void addRow(int row, String[] list)
    {
        int col= (row-1)%2+1;
        for(int a = 0; a< list.length; a++)
        {
            if(!(list[a].equals("")))
            {
                Bubble bubble = bubbleColor(list[a]);
                if(row%2 == 1){addObject(bubble,theCol(col),theRow(row));}
                else{addObject(bubble,theCol(col,1),theRow(row));}
            }
            col++;
        }
    }
    
    public void shoot(int x, int y)
    { 
        if(ableToShoot == true)
        {
            ableToShoot = false;
            List list = getObjects(Bubble.class);
            Bubble bubble = (Bubble)list.get(list.size()-1);
            bubble.setdX(x);
            bubble.setdY(y);
        }
    }
    
    public Bubble nextBubble()
    {
        List list = getObjects(Bubble.class);
        int yellow = 0;int blue = 0;int green = 0;int red = 0;int purple = 0;
        
        for(int i = 0; i < list.size(); i++)
        {
            Bubble bub = (Bubble)list.get(i);
            if(bub.getColor().equals("yellow")){yellow++;}
            if(bub.getColor().equals("blue")){blue++;}
            if(bub.getColor().equals("red")){red++;}
            if(bub.getColor().equals("purple")){purple++;}
            if(bub.getColor().equals("green")){green++;}
        }
            Bubble returner = randomBubble();
            if(returner.getColor().equals("yellow") && (yellow != 0)){return new YellowBubble();}
            if(returner.getColor().equals("blue") && (blue != 0)){return new BlueBubble();}
            if(returner.getColor().equals("purple") && (purple != 0)){return new PurpleBubble();}
            if(returner.getColor().equals("red") && (red != 0)){return new RedBubble();}
            if(returner.getColor().equals("green") && (green != 0)){return new GreenBubble();}
            else return nextBubble();
        }
    
        ArrayList ALLBUBBLES = new ArrayList();
        ArrayList firstList = new ArrayList();
        ArrayList secondList = new ArrayList();
        ArrayList thirdList = new ArrayList();
        ArrayList fourthList = new ArrayList();
        ArrayList fifthList = new ArrayList();
        ArrayList sixthList = new ArrayList();
        ArrayList seventhList = new ArrayList();
        ArrayList eighthList = new ArrayList();
        ArrayList ninethList = new ArrayList();
        ArrayList tenethList = new ArrayList();
        
     public ArrayList findArray(int num)
     {
         if(num == 1){return firstList;}
         if(num == 2){return secondList;}
         if(num == 3){return thirdList;}
         if(num == 4){return fourthList;}
         if(num == 5){return fifthList;}
         if(num == 6){return sixthList;}
         if(num == 7){return seventhList;}
         if(num == 8){return eighthList;}
         if(num == 9){return ninethList;}
         else{return tenethList;}
        }
        
     public void isFloating()
     {
        ALLBUBBLES = new ArrayList();
        firstList = new ArrayList();
        secondList = new ArrayList();
        thirdList = new ArrayList();
        fourthList = new ArrayList();
        fifthList = new ArrayList();
        sixthList = new ArrayList();
        seventhList = new ArrayList();
        eighthList = new ArrayList();
        ninethList = new ArrayList();
        tenethList = new ArrayList();
        List alist = getObjects(Bubble.class);
        Bubble shooterbubble = (Bubble)alist.get(alist.size()-1);
        if(alist.size() > 1)
        {
            Bubble ndlastbubble = (Bubble)alist.get(alist.size()-2);
            if((shooterbubble.getX() == ndlastbubble.getX()) &&(shooterbubble.getY() == ndlastbubble.getY()))
            {
                Greenfoot.stop();
            }
        }
            
       
        
        shooterbubble.floating = false;
        ALLBUBBLES.add(shooterbubble);
        
        for(int a = 0; a < alist.size(); a++)
        {
            Bubble bubble1 = (Bubble)alist.get(a);
            if(bubble1.findRow() == 1)
            {
                firstList.add(bubble1);
                ALLBUBBLES.add(bubble1);
            }
        }
        
        for(int listCounter = 1; listCounter <=10; listCounter++)
        {
        for(int a = 0; a < findArray(listCounter).size(); a++)
        {
            Bubble bubble1 = (Bubble)findArray(listCounter).get(a);
            List neighbors1 = bubble1.getNeighbors(60);
            for(int b = 0; b< neighbors1.size(); b++)
            {
                Bubble bubble2 = (Bubble)neighbors1.get(b);
                if(ALLBUBBLES.contains(bubble2) == false)
                {
                    findArray(listCounter+1).add(bubble2);
                    ALLBUBBLES.add(bubble2);
                }
            }
        }
    }

        for(int a = 0; a < ALLBUBBLES.size(); a++)
        {
            Bubble bubble = (Bubble)ALLBUBBLES.get(a);
        }
        List anotherlist = getObjects(Bubble.class);
        for(int a = 0; a < anotherlist.size(); a++)
        {
            Bubble bubble = (Bubble)anotherlist.get(a);
            if(ALLBUBBLES.contains(bubble) == false && bubble.getY() >= 660){removeObject(bubble);}
            if(ALLBUBBLES.contains(bubble) == false) {bubble.fall();}
            if(ALLBUBBLES.contains(bubble) == true) {bubble.floating = false;}
        }  
    }
    public Bubble bubbleColor(String bubble)
    {
        if(bubble.equals("blue")){return new BlueBubble();}
        if(bubble.equals("yellow")){return new YellowBubble();}
        if(bubble.equals("red")){return new RedBubble();}
        if(bubble.equals("green")){return new GreenBubble();}
        else{return new PurpleBubble();}
    }
}



