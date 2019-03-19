import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbstAnt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstAnt extends Actor
{
    enum AntState{
        IDLE, HUNT, FEED, ATTACK, DEAD, TRACK, FOLLOW, FORAGE, RETURN
    }
    
    //Properties : what an ant has
    int speed = 2;
    int sightEnemyRange = 200;
    int sightFoodRange = 200;
    double energy = 100;//the amount of energy, if < 0, it starves
    double health = 100;//if < 0, it dies
    int attack = 5;
    AntState state = AntState.IDLE;
    protected AHB Hb;
    //protected AEB Eb;
    /**
     * Act - do whatever the AbstAnt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Hb == null){
         Hb = new AHB(this,(int)health);
         getWorld().addObject(Hb,this.getX(),this.getY()-getImage().getHeight()/2);
        }/*if(Eb == null){
         Eb = new AEB(this,(int)energy);
         getWorld().addObject(Eb,this.getX(),this.getY()-getImage().getHeight()/2);
        }*/else{
            Hb.update();
            //Eb.update();
        }
        turn(Greenfoot.getRandomNumber(60)-30);
        move(Greenfoot.getRandomNumber(5)-2);
        
        
    }    
    
    //Methods : what the ant does
    protected abstract void eat(double energyGain);

    protected abstract void attack();

    protected abstract void track();

    protected abstract void collectFood();

    protected abstract void leaveTrail();

}