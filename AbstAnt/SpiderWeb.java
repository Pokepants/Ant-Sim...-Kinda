import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class SpiderWeb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiderWeb extends Actor
{
    Ant prey;
    Spider trapper;
    SpiderWeb web;

    private double size = 30;
    public static long spawnTime = 2000;
    int sightRange = 30;

    /**
     * Act - do whatever the SpiderWeb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getObjectsInRange(30,Ant.class).size()>0){
            List<Ant> preys = getObjectsInRange(30,Ant.class);
            for(int i = 0; i < preys.size(); i++)
            {
                if (intersects(preys.get(i))){
                    int stuck = 0;
                    preys.get(i).speed = stuck;
                }
            }
        }
    }    
}
