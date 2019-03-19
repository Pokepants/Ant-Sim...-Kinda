import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    public static long spawnTime = 2000;
    
    private static double decayRate = 0.1;//lets try this to make the food lose energy over time
    private double energy = 100;
    private double size = 30;
    private double weight = 10;
    
    public Food() {
        setEnergy();
    }
    
    public Food(double e){
        setEnergy(energy);
    }

    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(energy > 0){
            //make the energy decay
            removeEnergy(decayRate);
        }
        else{
            getWorld().removeObject(this);
        }
    }

    public void removeEnergy(double amount){
        //remove energy
        energy -= amount;
        if(energy <= 0){
            energy = 0;
        }
        //change the energy and weight based on the new energy
        setEnergy();
    }
    
    public void setEnergy(){
        size = 10 + energy*0.2;
        weight = 5 + energy*1;
        getImage().scale((int)size,(int)size);
    }
    
    public void setEnergy(double energy){
        this.energy = energy;
        setEnergy();
    }

    public boolean stillAvailable(){
        return getWorld() != null;
    }
    
    public void carry(Ant ant){
    setLocation(ant.getX()/2,ant.getY()/2);
    }
}