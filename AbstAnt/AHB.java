import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AHB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AHB extends HealthBar
{ 
    protected AbstAnt owner;
    /**
     * Act - do whatever the AHB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public AHB(AbstAnt o, int maxHealth){
        //healthFill = new GreenfootImage(20,5);  
        setImage(temp);
        // color = Color(0, 255, 0); 
        this.maxHealth = (int)o.health;
        owner = o;
        size = owner.getImage().getHeight()/2;

    } 

    public void act(){
        if (owner == null){
            kys(); 
            return; 
        } 
        //check to see if the object is no longer in the world  
        //should not have to do this if the world specified the act order reasonably  
        //but... 
        else if (owner.getWorld() == null){
            kys(); 
        } 
        else{
            setLocation(owner.getX(),owner.getY()-size);   
        }
        // Add your action code here.
    } 

    private void kys(){ 
        getWorld().removeObject(this);
    }  

    public void update()  
    { 
        //update health    
        health = (int)owner.health;
        //move the bar to the right spot 

        //if we are full health or dead then no update required 
        if (health == maxHealth|| health <= 0){return;}
        //update the color 
        try{//not really needed anymore 
            red = (int)(120+134*(1. - ((double)health/(double)maxHealth)));
            green = (int)( 254 *((double)health/(double)maxHealth));
            if(visible){
                alpha = 255;
            }else{
                alpha = 0;
            }
            color = new Color(red,green, 80,alpha);
        } 
        catch(Exception e){
            //let me know if we divided by zero somehow 
            System.out.println(""+red+" , "+green);
        } 
        temp.setColor(color); 
        temp.fill();
        temp.scale(1+30*health/maxHealth, 3);
        setImage(temp); 
    }     
}
