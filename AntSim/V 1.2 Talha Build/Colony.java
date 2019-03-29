import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.lang.*;

/**
 * Write a description of class Colony here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colony extends AbstAnt

{enum ColonyState{
        DEAD,SPAWN,IDLE
    }
    
    SimpleTimer fSpawnTimer;
    AntArena world;
    Ant ant;
    double energy = 999999999;
    double eThresh = 1000;
    private double spawncost = 1;//TBD
    double health = 10000;
    int numAnts = 10;

    protected ColonyState state = ColonyState.IDLE;
    protected HealthBar chb;
    List <Ant> ants = new ArrayList<Ant>();
    /**
     * Act - do whatever the Colony wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public void act(){
        ants = getWorld().getObjects(Ant.class);
        if (fSpawnTimer == null){
            fSpawnTimer = new SimpleTimer();
        }
        if(chb == null){
            chb = new HealthBar(this,(int)health);
            getWorld().addObject(chb,this.getX(),this.getY()-getImage().getHeight()/2);
            chb.update();
        }   
        if(world == null){
            world = (AntArena)getWorld();
        }
        if(state == ColonyState.IDLE){
            if (energy >= eThresh && ants.size() <= numAnts){
                state = ColonyState.SPAWN;
            }
        }else if (state == ColonyState.SPAWN){
            if (energy <= eThresh){
                state = ColonyState.IDLE;
            }
            if(energy >= eThresh && ants.size() <= numAnts){
                if (ants.size() >= numAnts){
                    state = ColonyState.IDLE;
                }
                //System.out.println(fSpawnTimer.millisElapsed());
                if(fSpawnTimer.millisElapsed() >= Ant.spawnTime){
                    ant = new Ant(this, 5, 100, 100);
                    //System.out.println(ants.size());
                    energy -= AbstAnt.sEnergy;
                    getWorld().addObject(ant, getX(), getY());
                    fSpawnTimer.mark();
                }
            }
            }else if(state == ColonyState.DEAD){
                getWorld().removeObject(this);
        }
    }    
    
    public void takeDmg(double dmg){
        //remove the damage from the health
        health-= dmg;
        //if all the health is gone 
        if (health <= 0){
            //change it to dead
            state = ColonyState.DEAD;
        }

    }
}
