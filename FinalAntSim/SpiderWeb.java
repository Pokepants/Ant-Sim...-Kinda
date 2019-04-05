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

    /**
     * Act - do whatever the SpiderWeb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        List<Ant> preys = getObjectsInRange(20,Ant.class);
        if(preys.size()>0){
            if (isTouching(Ant.class)){
                int stuck = 0;
                for(int i = 0; i < preys.size(); i++){
                    preys.get(i).speed = stuck;
                    preys.get(i).attack = stuck;
                }
            }
        }
    }    
}
