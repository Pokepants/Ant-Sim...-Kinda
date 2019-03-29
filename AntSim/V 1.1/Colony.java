import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

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

    Ant ants;
    double energy = 1000;
    double energyThresh = 3000;
    private double spawncost = 1;//TBD
    double health = 10000;
    int numAnts = 40;

    protected ColonyState state = ColonyState.IDLE;
    protected HealthBar chb;
    /**
     * Act - do whatever the Colony wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(chb == null){
            chb = new HealthBar(this,(int)health);
            getWorld().addObject(chb,this.getX(),this.getY()-getImage().getHeight()/2);

            chb.update();
        }
        if(state == ColonyState.IDLE){
            if (energy>=0){
                state = ColonyState.SPAWN;
            }

        }else if (state == ColonyState.SPAWN){
            List<Ant> nAnt = getObject(Ant.class);
            if(energy>=0 && nAnt.size() > numAnts){
                ants = new Ant();
                getWorld().addObject(ants, getX(), getY());
            }

            if (energy<=0){
                state = ColonyState.IDLE;
            }
        }else if(state == ColonyState.DEAD){
            getWorld().removeObject(this);
        }
        // Add your action code here.
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

    protected void loseNrg(double cost){
        energy -= cost;
    }
}
