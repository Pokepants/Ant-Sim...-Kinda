import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Spider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider extends Actor
{
    enum SpiderState{
        IDLE, ATTACK, DEAD, OBJECTIVE
    }

    Ant food;
    Colony goal;

    //Properties : what an spider has
    int speed = 1;
    double health = 3000;//if < 0, it dies
    double maxHealth = 3000;
    int attack = 5;
    SpiderState state = SpiderState.IDLE;
    protected SpiderHealth Hb;

    /**
     * Act - do whatever the Spider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Hb == null){
            Hb = new SpiderHealth(this,(int)health);
            getWorld().addObject(Hb,this.getX(),this.getY()-getImage().getHeight()/2);
        }/*if(Eb == null){
        //Eb = new EnergyBar(this,(int)Nrg);
        getWorld().addObject(Eb,this.getX(),this.getY()-getImage().getHeight()/2);
        }*/else{
            Hb.update();
            //Eb.update();
        }

        if (state == SpiderState.IDLE){
            super.act();
            turn(Greenfoot.getRandomNumber(60)-30);
            move(Greenfoot.getRandomNumber(5)-2);
            List<Ant> foods = getObjectsInRange(100,Ant.class);
            if (getObjectsInRange(50,Colony.class).size()>0){
                state = SpiderState.OBJECTIVE;
            }
            //if there is food
            else if (foods.size() > 0){
                //set to foraging
                food = foods.get(0);
                state = SpiderState.ATTACK;
            } 
        }
        else if(state == SpiderState.OBJECTIVE){
            if (goal == null){
                goal = null;
                state = SpiderState.IDLE;
            }
            else if (isTouching(Colony.class)){
                //calculate the damage done in the attack
                double damage = attack*2;
                //call the target's takeDamage method
                goal.takeDmg(damage);

            }
            else{
                //turn towards the ant ==> turntowards
                turnTowards(goal.getX(),goal.getY());
                //move the ant
                move(speed);
            }
        }
        else if (state == SpiderState.ATTACK) {
            if (food.getWorld() == null){
                food = null;
                state = SpiderState.IDLE;
            }
            else if (intersects(food) && getObjectsInRange(50,Ant.class).size()>0){
                //calculate the damage done in the attack
                double damage = attack;
                //List<Ant> foods = getObjectsInRange(20,Ant.class);
                //food = foods.get(0);
                //call the target's takeDamage method
                food.takeDamage(damage);
                if(food.health <= 0){
                    getWorld().removeObject(food);
                    if(health <= maxHealth){
                        health += 20;
                    }
                }
            }
            else{
                //turn towards the ant ==> turntowards
                turnTowards(food.getX(),food.getY());
                //move the ant
                move(speed);
                if(getObjectsInRange(50,Ant.class).size()>0){
                    List<Ant> foods = getObjectsInRange(100,Ant.class);
                    food = foods.get(0);
                }
            }
        }
        else if(state == SpiderState.DEAD){
            getWorld().removeObject(this);
        }
    }

    public void takeDamage(double damage) {
        //removes the damage from the health
        health -= damage;
        //if all the health is gone
        if (health <= 0){
            //change it to dead
            state = SpiderState.DEAD;
        } 
    }
}
