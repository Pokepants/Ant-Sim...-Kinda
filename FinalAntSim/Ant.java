import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Ant extends AbstAnt
{

    protected double energyThreshold = 15;

    AbstAnt target;
    BREADIT snak;
    LadyBug extrafd;
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
            if(snak == null){
                List<BREADIT> cFoods = getObjectsInRange(sightRange, BREADIT.class);
                snak = getSnak(cFoods);
            }

            if(snak != null){
                if(colony.energy <= colony.eThresh ){
                    state = AntState.COLLECT;
                    return;
                }
                else if(energy < energyThreshold){
                    //set to foraging
                    state = AntState.FORAGE;
                    return;

                }
                else if(getObjectsInRange(sightRange,LadyBug.class).size()>0){
                    List<LadyBug> extrafds = getObjectsInRange(sightRange,LadyBug.class);
                    //set to foraging
                    int fdsize = extrafds.size();
                    extrafd = extrafds.get(0);
                    state = AntState.FORAGE;
                }
            }
            //else if look for an ant to attack
            List<AbstAnt> targets = getObjectsInRange(sightRange, AbstAnt.class);
            if (targets.size()>0 && energy > energyThreshold){
                for (int i = 0; i < targets.size(); i++){
                    if (targets.get(i).colony != this.colony){
                        target = targets.get(0);
                        state = AntState.ATTACK;
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
        else if (state == AntState.COLLECT) {
            //make sure that colony is still below energy threshold
            if (colony.energy >= colony.eMaxThresh){
                //after Colony is full, go back to idle state
                snak.carried = false;
                state = AntState.IDLE;
            }

            else if(snak.getWorld() == null){
                snak.carried = false;
                snak = null;
                state = AntState.IDLE;
                return;
            }
            //find all nearby food (only bread) that can be collected

            if (intersects(snak)){
                //bring it to the Colony
                System.out.println(snak);
                snak.setLocation(this.getX(),this.getY());
                //move the ant towards the Colony
                turnTowards(colony.getX(),colony.getY());
                move(speed);
                hb.update();
                if (intersects(colony)){
                    snak.carried = false;
                    getWorld().removeObject(snak);
                    snak = null;
                    colony.gainFd(cEnergy);
                    state = AntState.IDLE;
                    return;
                }
            }
            //tell this ant to move to it
            else {
                turnTowards(snak.getX(),snak.getY());
                //move the ant
                move(speed);
                hb.update();
            }
        }
        else if (state == AntState.FORAGE){
            if (snak != null && getObjectsInRange(100,BREADIT.class).size()>0){
                if (snak.getWorld() == null){
                    snak = null;
                    state = AntState.IDLE;
                }
                else if (energy <= 100){
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
            }else if (extrafd != null){
                if (extrafd.getWorld() == null){
                    extrafd = null;
                    state = AntState.IDLE;
                }
                else if (energy <= 100){
                    state = AntState.IDLE;   
                }
                else if (isTouching(LadyBug.class)){
                    double gain = 10;
                    double damage = Greenfoot.getRandomNumber(attack);
                    extrafd.takeDamage(attack*2);
                    energy += damage;
                }
                else{
                    //turn towards the ant ==> turntowards
                    turnTowards(extrafd.getX(),extrafd.getY());
                    //move the ant
                    move(speed);
                }
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
            List <BREADIT> snaks = getObjectsInRange(sightRange,BREADIT.class);
            for(int i = 0; i < snaks.size(); i++){
                if(intersects(snaks.get(i))){
                    snaks.get(i).carried = false;
                }
            }
            //change it to dead
            state = AntState.DEAD;
        }
    }
    
    protected BREADIT getSnak(List <BREADIT> items){
        for(BREADIT item : items){
            if (item.carried == false){
                item.carried = true;
                return item;
            }
        }
        return null;
    }
    
}