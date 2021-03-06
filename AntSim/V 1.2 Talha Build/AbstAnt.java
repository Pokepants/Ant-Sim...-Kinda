import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class AbstAnt extends Actor
{
    enum AntState{
        IDLE, HUNT, FEED, ATTACK, DEAD, TRACK, FOLLOW, FORAGE, RETURN
    }
    
    //Properties : what an ant has
    static double sEnergy = 100;
    int speed =5;
    double size = 30;
    int sightRange = 500;
    double energy = sEnergy;//the amount of energy, if < 0, it starves
    double health = 100;//if < 0, it dies
    int attack = 5;
    
    
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
}