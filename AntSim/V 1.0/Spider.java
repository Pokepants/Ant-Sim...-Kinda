import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Spider extends AbstAnt
{
    
   protected double energyThreshold = 15;
    
   Ant target; 
   Ant snak; 
    
   public void act() {
        if (state == AntState.IDLE){
            super.act();
            //if energy is below a threshhold value
            if(health < 100 && energy > energyThreshold){
                health++;
                energy -=5;
            }
            //look for some Ant
            if(energy < energyThreshold){
                List<Ant> prospects = getObjectsInRange(sightRange,Ant.class);
                //if there is Ant
                if (prospects.size() > 0){
                    //set to foraging
                    snak = prospects.get(0);
                    if (snak.health <= 0){
                        state = AntState.FORAGE;
                    }
                }   
            }
            //else if look for an ant to attack
            List<Ant> targets = getObjectsInRange(sightRange,Ant.class);
            if (targets.size()>0 && energy > energyThreshold){ //if the list is not empty
                target = targets.get(0);
                if (target.health >0){ 
                    state = AntState.ATTACK;
                }
            }
        }
        else if (state == AntState.ATTACK) {
            if (target.getWorld() == null){
                target = null;
                state = AntState.IDLE;
            }
            else if (target.health <= 0){
                state = AntState.IDLE;
                target = null;
            }
            else if (energy < energyThreshold && getObjectsInRange(sightRange, Ant.class).size()>0 && target!=null){
                state = AntState.IDLE;
            }
            else if (isTouching(Ant.class)){
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
            if (snak.getWorld() == null){
                snak = null;
                state = AntState.IDLE;
            }
            else if (isTouching(Ant.class)){
                double damage = Greenfoot.getRandomNumber(attack);
                snak.eat(damage);
                energy +=10;
                hb.update();
            }
            else if(!isTouching(Ant.class)){
                turnTowards(snak.getX(),snak.getY());
                //move the ant
                move(speed);
                hb.update();
            }
            
            else if (energy <= 100){
                state = AntState.IDLE;
            } 
        }
        else if(state == AntState.DEAD){
            getWorld().removeObject(this);
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
    
   protected void eat(){  
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
