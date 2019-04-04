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
    public statTracker tracker;
    protected SimpleTimer fSpawnTimer;
    protected AntArena world;
    protected Ant ant; 
    protected ColonyState state = ColonyState.IDLE;
    protected HealthBar chb;

    protected double energy = 2000;
    protected double eThresh = 1000;
    double eMaxThresh = 2000;
    protected double spawncost = 100;
    protected double health = 10000;
    protected int numAnts = 10;
    List <Ant> ants = new ArrayList<Ant>();
    /**
     * Act - do whatever the Colony wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act(){
        if (fSpawnTimer == null){
            fSpawnTimer = new SimpleTimer();
        }
        if (tracker == null){
            tracker = new statTracker(this);
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
            if (energy >= eThresh && tracker.cAliveAnt() <= numAnts){
                state = ColonyState.SPAWN;
            }
        }else if (state == ColonyState.SPAWN){
            if (energy <= eThresh){
                state = ColonyState.IDLE;
            }
            if(energy >= eThresh && tracker.cAliveAnt() <= numAnts){
                if (tracker.cAliveAnt() >= numAnts){
                    state = ColonyState.IDLE;
                }
                //System.out.println(fSpawnTimer.millisElapsed());
                if(fSpawnTimer.millisElapsed() >= Ant.spawnTime){
                    ant = new Ant(this, 5, 100, 100);
                    energy -= AbstAnt.sEnergy;
                    getWorld().addObject(ant, getX(), getY());
                    tracker.aliveAnt();
                    //System.out.println(tracker.cAliveAnt());
                    fSpawnTimer.mark();
                }
            }
        }else if(state == ColonyState.DEAD){
            getWorld().removeObject(this);
        }
    }
    
    public void gainFd(double gain){
        //gain energy from food
        energy += gain;
    }

}
