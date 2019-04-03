import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class AbstAnt extends Actor
{
    enum AntState{
        IDLE, HUNT, FEED, ATTACK, DEAD, TRACK, FOLLOW, FORAGE, RETURN, PROTECT, DEVOUR
    }
    enum AntBit{
        COLLECTER, INVADER, REAPER, SENTRY
    }

    //Properties : what an ant has
    protected static double sEnergy = 999;
    protected int speed =5;
    protected double size = 30;
    protected int sightRange = 500;
    protected double energy = sEnergy;//the amount of energy, if < 0, it starves
    protected double health = 100;//if < 0, it dies
    protected int attack = 5;
    protected Colony colony;

    AntState state = AntState.IDLE;
    HealthBar hb;
    HealthBar shb;

    public void act(){
        if (hb == null){
            hb = new HealthBar(this,(int)health);
            getWorld().addObject(hb,getX(),getY()-getImage().getHeight()/2);
        }
        else{
            hb.update();
        }
        turn(Greenfoot.getRandomNumber(60)-30);
        move(Greenfoot.getRandomNumber(5)-2);
    }
    //Methods : what the ant does
    public void takeDamage(double damage) {
        //removes the damage from the health
        health -= damage;
        //if all the health is gone
        if (health <= 0){
            //change it to dead
            state = AntState.DEAD;
        }
    }
    public double getHealth(){
        return health;
    }
}