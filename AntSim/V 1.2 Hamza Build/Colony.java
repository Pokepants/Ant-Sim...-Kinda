import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Colony here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colony extends AbstAnt

{
    enum ColonyState{
        DEAD,SPAWN,IDLE
    }

    AntArena world;
    AbstAnt ant;
    double energy = 1000;
    double energyThresh = 100;
    private double spawncost = 1;//TBD
    double health = 1000;

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
        if(world == null){
            world = (AntArena)getWorld();
        }       
        if(state == ColonyState.IDLE){
            if (energy>energyThresh){
                state = ColonyState.SPAWN;
            }
        }else if (state == ColonyState.SPAWN){
            if(energy>energyThresh){
                energy -= spawncost;
                //where code to spawn ant goes
            }
            if (energy<energyThresh){
                state = ColonyState.IDLE;
            }
        }else if(energy<energyThresh){
            health--;
        }else if(health == 0){
            state = ColonyState.DEAD;
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
    /*protected void loseNrg(double cost){
        energy -= cost;
    }*/
}
