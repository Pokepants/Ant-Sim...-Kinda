import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 

//import java.io.;//for the colors instead of java.AWT.* 
/** 
 * A pretty cool health bar with a low computational footprint, and autoupdating 
 * spawn it in and then forget it 
 *
 * @author Nathan Rowbottom 
 * @version Feb 26, 2019 
 */ 
public class HealthBar extends Actor 
{ 
    public static boolean visible = true;
    protected GreenfootImage temp = new GreenfootImage(1, 1); 
    protected greenfoot.Color color; 
    protected AbstAnt owner;
    protected int maxHealth;
    protected int size;
    protected int health;
    protected int red = 0, green = 255, alpha = 255;

    public HealthBar(AbstAnt o, int maxHeath){
        //healthFill = new GreenfootImage(20,5);
        setImage(temp);
        // color = Color(0, 255, 0); 
        this.maxHealth = (int)o.health;
        owner = o;
        size = owner.getImage().getHeight()/2;

    } 

    public void act(){ 
        //check to see if our owner is gone 
        if (owner == null){
            kys(); 
            return; 
        }
        //check to see if the object is no longer in the world
        //should not have to do this if the world specified the act order reasonably
        //but... 
        else if (owner.getWorld() == null){
            kys(); 
        } 
        else{
            setLocation(owner.getX(), owner.getY()-size);
        }
        
    } 

    private void kys(){ 
        getWorld().removeObject(this);
    } 

    public void update()
    { 

        //update health
        health = (int)owner.health;
        //move the bar to the right spot 

        //if we are full health or dead then no update required 
        if (health == maxHealth|| health <= 0){return;}
        //update the color 
        try{//not really needed anymore 
            red = (int)(120+134*(1. - ((double)health/(double)maxHealth)));
            green = (int)( 254*((double)health/(double)maxHealth));
            if (visible){
                alpha = 255;
                /*if(Greenfoot.isKeyDown("right"))
                {
                    alpha+=100;
                }
                if(Greenfoot.isKeyDown("left"))
                {
                    alpha-=100;
                }*/
            }
            else {
                alpha = 0;
            }
            color = new Color(red,green, 80, alpha);
        } 
        catch(Exception e){
            //let me know if we divided by zero somehow 
            System.out.println(""+red+" , "+green);
        } 
        temp.setColor(color); 
        temp.fill();
        temp.scale(1+20*health/maxHealth, 3);
        temp.setTransparency(alpha);
        setImage(temp); 
    }
}