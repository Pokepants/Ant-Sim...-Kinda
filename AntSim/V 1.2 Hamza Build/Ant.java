import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Ant extends AbstAnt
{
    
    protected double energyThreshold = 15;
    GreenfootImage leemon = new GreenfootImage("lemon.png");
    
    Spider target;
    Food snak;
    int powFood;
    
    private static double decayRate = 0.1;
    
    public void act() {
        if (state == AntState.IDLE){
            super.act();
            //if energy is below a threshhold value
            if(health < 100 && energy > energyThreshold){
                health++;
                energy -=5;
            }
            if(energy < energyThreshold){
                //look for some food
                List<Food> prospects = getObjectsInRange(sightRange,Food.class);
                //if there is food
                if (prospects.size() > 0){
                    //set to foraging
                    snak = prospects.get(0);                    
                    state = AntState.FORAGE;
                }
            }
            //else if look for an ant to attack
            List<Spider> targets = getObjectsInRange(sightRange, Spider.class);
            if (targets.size()>0 && energy > energyThreshold){ //if the list is not empty
                target = targets.get(0);
                state = AntState.ATTACK;
            }
        }
        else if (state == AntState.ATTACK) {
            if (target.getWorld() == null){
                target = null;
                state = AntState.IDLE;
            }
            else if (energy < energyThreshold && getObjectsInRange(sightRange, Food.class).size()>0 && snak!=null){
                state = AntState.IDLE;
            }
            else if (isTouching(Spider.class)){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                //call the target's takeDamage method
                target.takeDamage(damage);
                energy -= (damage*0.1);
                hb.update();
            }
            else{
                //turn towards the ant ==> turntowards
                turnTowards(target.getX(),target.getY());
                //move the ant
                move(speed);                
            }
        }
        else if (state == AntState.FORAGE){
            powFood = 1;
            if (snak.getWorld() == null){
                snak = null;
                state = AntState.IDLE;
            }
            else if(!isTouching(Food.class)){
                turnTowards(snak.getX(),snak.getY());
                //move the ant
                move(speed);
                hb.update();
            }
            else if(isTouching(Food.class)){
                Greenfoot.playSound("973571551897414.wav");
                double damage = Greenfoot.getRandomNumber(attack);
                snak.removeEnergy(damage);
                energy += damage;
            }
            else if (energy <= 100){
                state = AntState.IDLE;   
            }
            if(powFood == 1){
                if(isTouching(Food.class)){
                    attack = 21;
                    snak.setImage(leemon);
                }
            }
        }       
    } 
    //Methods : what the ant does
     //override the inherited move method to take off energy
    public void move(int distance){
        energy -= 1 ;  
        super.move(distance);
        if (energy <=0){
        health--;
        energy = 0;
        }
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        }
    }
    
    protected void eat(double e){  
        energy -= e;
        if (energy < 0){
            getWorld().removeObject(this); 
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
        //if all the health is gone
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        }
    }
}