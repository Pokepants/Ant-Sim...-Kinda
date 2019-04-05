import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class LadyBug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LadyBug extends AbstAnt
{

    double health = 100;
    double energy = 100;
    AntState state = AntState.IDLE;

    Food food;

    /**
     * Act - do whatever the LadyBug wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (state == AntState.IDLE){
            super.act();
            turn(Greenfoot.getRandomNumber(60)-30);
            move(Greenfoot.getRandomNumber(5)-2);

        }
        else if(state == AntState.DEAD){
            getWorld().removeObject(this);
        }
    }

    public void takeDamage(double damage) {
        //removes the damage from the health
        health -= damage;
        //if all the health is gone
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        } 
    }

    public double findDistanceBetween(Food a1, LadyBug a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
}
