import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CEB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CEB extends EnergyBar
{
    protected Colony owner;
    /**
     * Act - do whatever the AEB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public CEB(Colony o, int maxEnergy){
        //energyFill = new GreenfootImage(20,5);  
        setImage(temp);
        // color = Color(0, 255, 0); 
        this.maxEnergy = (int)o.energy;
        owner = o;
        size = owner.getImage().getHeight()/2;

    } 
    public void act() 
    {if (owner == null){
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
         setLocation(owner.getX(),owner.getY()+size);   
        }
        // Add your action code here.
    }    
    private void kys(){ 
        getWorld().removeObject(this);
    } 
    public void update()  
    { 
        
        //update energy    
        energy = (int)owner.energy;
        //move the bar to the right spot 
        
        //if we are full energy or dead then no update required 
        //if (energy == maxEnergy|| energy <= 0){return;}
        //update the color 
        try{//not really needed anymore 
            green = (int)(120+134*(1. - ((double)energy/(double)maxEnergy)));
            blue = (int)( 254 *((double)energy/(double)maxEnergy));
            if(visible == true){
                alpha = 255;
            }else{
                alpha = 0;
            }
            color = new Color(0,green, blue,alpha);
        } 
        catch(Exception e){
            //let me know if we divided by zero somehow 
            System.out.println(""+green+" , "+blue);
        } 
        temp.setColor(color); 
        temp.fill();
        temp.scale(1+100*energy/maxEnergy, 5);
        setImage(temp); 
    }  
}
