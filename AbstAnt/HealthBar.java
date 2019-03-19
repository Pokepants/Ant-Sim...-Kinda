import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 
import java.io.*;//for the colors instead of java.AWT.* 
public class HealthBar extends Actor 
{ 
    public static boolean visible = true;
    protected GreenfootImage temp = new GreenfootImage(1, 1); 
    protected greenfoot.Color color; 
    protected int maxHealth;
    protected int size;
    protected int health;
    protected int red = 0, green = 255, alpha = 255;



    public void act(){ 

    } 

    private void kys(){ 
        getWorld().removeObject(this);
    } 
  
} 