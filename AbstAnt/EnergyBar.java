import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 
import java.io.*;//for the colors instead of java.AWT.* 
public class EnergyBar extends Actor 
{ 
    public static boolean visible = true;
    protected GreenfootImage temp = new GreenfootImage(1, 1); 
    protected greenfoot.Color color; 
    protected int maxEnergy;
    protected int size;
    protected int energy;
    protected int green = 0, blue = 255, alpha = 255;
    
    public void act(){ 
        //check to see if our owner is gone 
        
    } 
    private void kys(){ 
        getWorld().removeObject(this);
    } 
       
} 
