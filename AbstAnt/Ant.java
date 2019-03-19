import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Ant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ant extends AbstAnt
{
    public static long spawnTime = 5000;
    protected double energyThreshold = 90;
    int deathCount = 0;

    GreenfootSound attackSound;
    
    
    Food prospect;
    Spider target;
    Colony clny;

    /**
     * Act - do whatever the Ant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(attackSound == null){
            attackSound = ((AntArena)getWorld()).attackSound();
        }

        if (state == AntState.IDLE){
            super.act();

            if(1000<3000){
                if(getObjectsInRange(sightFoodRange,Food.class).size()>0){
                    //look for some food
                    List<Food> prospects = getObjectsInRange(sightFoodRange,Food.class);
                    //if there is food
                    if (prospects.size() > 0){
                        //set to foraging
                        prospect = prospects.get(0);
                        state = AntState.HUNT;
                    }
                }   
            }
            //if energy is below a threshhold value
            if(getObjectsInRange(sightFoodRange,Food.class).size()>0){
                //look for some food
                List<Food> prospects = getObjectsInRange(sightFoodRange,Food.class);
                //if there is food
                if (prospects.size() > 0){
                    //set to foraging
                    prospect = prospects.get(0);
                    state = AntState.FORAGE;
                }
            }  
            //else if look for an ant to attack
            List<Spider> targets = getObjectsInRange(sightEnemyRange, Spider.class);
            if (targets.size()>0){ //if the list is not empty
                target = targets.get(0);
                state = AntState.ATTACK;
            }
        }
        else if(state == AntState.HUNT){
            if(prospect.getWorld() == null){
                prospect = null;
                state = AntState.IDLE;  
            }
            else if (isTouching(Food.class)){
                prospect.carry(this);
            }else{
                //turn toward ant ==> turntowards
                turnTowards(prospect.getX(), prospect.getY());
                //move the ant
                move(speed);
            }
        }
        else if (state == AntState.FORAGE) {
            if (prospect.getWorld() == null){
                prospect = null;
                state = AntState.IDLE;
            }
            else if (isTouching(Food.class)){
                double gain = 20;
                prospect.removeEnergy(gain);
                eat(gain);
                attackSound.play();
            }
            else{
                //turn towards the ant ==> turntowards
                turnTowards(prospect.getX(),prospect.getY());
                //move the ant
                move(speed);
            }
        }
        else if (state == AntState.ATTACK) {
            if (target.getWorld() == null){
                target = null;
                state = AntState.IDLE;
            }
            else if (isTouching(Spider.class)){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                //call the target's takeDamage method
                target.takeDamage(damage);

            }
            else{
                //turn towards the ant ==> turntowards
                turnTowards(target.getX(),target.getY());
                //move the ant
                move(speed);
            }
        }
        else if(state == AntState.DEAD){
            int death = 1;
            
            attack = 0;
            int dead = 0;
            move(dead);
            getWorld().removeObject(Hb);
        }
    } 

    //Methods : what the ant does
    //override the inherited move method to take off energy
    public void move(int distance){
        super.move(distance);
        takeEnergy(distance);
    }

    public void eat(double energyGain){
        energy += energyGain;

        if(energy >= 100)
        {
            state = AntState.IDLE;
        }
    }

    protected void attack(){
    }

    protected void track(){

    }

    protected void collectFood(){

    }

    protected void leaveTrail(){

    }

    public void takeDamage(double damage) {
        //removes the damage from the health
        health -= damage;
        attackSound.play();
        //if all the health is gone
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        } 
    }

    public void takeEnergy(double removedEnergy) {
        double tired = removedEnergy*0.5;
        energy -= tired;

        if(energy <= 0){
            health -= 2;
            energy = 0;
        }
        if(health <= 0){
            
            health = 0;
            state = AntState.DEAD;
        }

    }
}