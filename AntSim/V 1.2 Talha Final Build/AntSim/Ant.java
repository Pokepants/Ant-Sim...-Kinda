import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Ant extends AbstAnt
{

    protected double energyThreshold = 15;

    AbstAnt target;
    Food snak;
    Colony colony;

    DeadAnt dedaflmaoPressFinchat;

    public static int spawnTime = 1000;
    private static double decayRate = 0.1;

    public Ant(Colony c, int a, int h, int e){  
        colony = c;
        attack = a;
        health = h;
        energy = e;
    }

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
                List<Food> prospects = getObjectsInRange(sightRange, Food.class);
                //if there is food
                if (prospects.size() > 0){
                    for (int i = 0; i < prospects.size(); i++){
                        if (prospects.get(i).colony != this.colony){
                            snak = prospects.get(0);
                            state = AntState.FORAGE;
                            i = prospects.size();
                        }
                    }
                }
            }
            //else if look for an ant to attack
            List<AbstAnt> targets = getObjectsInRange(sightRange, AbstAnt.class);
            if (targets.size()>0 && energy > energyThreshold){
                for (int i = 0; i < targets.size(); i++){
                    if (targets.get(i).colony != this.colony){
                        target = targets.get(0);
                        state = AntState.ATTACK;
                        i = targets.size();
                    }
                }
            }
        }
        else if (state == AntState.ATTACK) {
            if (target.getWorld() == null){
                target = null;
                state = AntState.IDLE;
            }
            else if (energy < energyThreshold && getObjectsInRange(sightRange, BREADIT.class).size()>0 && snak!=null){
                state = AntState.IDLE;
            }
            else if (isTouching(AbstAnt.class) && getOneIntersectingObject(AbstAnt.class) == target){
                //calculate the damage done in the attack
                double damage = Greenfoot.getRandomNumber(attack);
                //call the target's takeDamage method
                target.takeDamage(damage);
                if (target.getHealth()> 0){
                    colony.tracker.enemyDef();
                    System.out.println(colony.tracker.cEnemyDef());
                }
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
            else if(!isTouching(Food.class)){
                turnTowards(snak.getX(),snak.getY());
                //move the ant
                move(speed);
                hb.update();
            }

            else if (isTouching(Food.class)){
                //Greenfoot.playSound("973571551897414.wav");
                double damage = Greenfoot.getRandomNumber(attack);
                snak.removeEnergy(damage);
                energy += damage;
            }

            else if (energy <= 100){
                state = AntState.IDLE;   
            }
        }
        else if (state == AntState.DEAD){
            dedaflmaoPressFinchat = new DeadAnt(colony);
            getWorld().addObject(dedaflmaoPressFinchat, getX(), getY());
            getWorld().removeObject(this);
            colony.tracker.deadAnt();
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
}