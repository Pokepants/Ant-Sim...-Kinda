import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class TitleScreen extends World
{
    //AntArena antArena;
    TitleScreen titleScreen;
    Label label;
    Label how2;
    //int on = 1;

    private GreenfootSound gameSound1= new GreenfootSound("C418 - Key - Minecraft Volume Alpha.wav");

    public TitleScreen()
    {    
        super(1100, 600, 1); 

        label = new Label("Ant Simulator", 200);
        how2 = new Label("Press up to start the simulator", 50);
        addObject(label, 550, 100);
        addObject(how2, 550, 400);
    }

    public void act(){
        /*MouseInfo mouse = Greenfoot.getMouseInfo();
        //use this to get the x position of the mouse.
        mouse.getX();
        //use this to get the y position of the mouse.
        mouse.getY();
        int x = mouse.getX();
        int y = mouse.getY();*/

        if(Greenfoot.isKeyDown("up")){
            Greenfoot.setWorld(new AntArena());
        }

        /*if(on == 1){
        Greenfoot.playSound("C418 - Key - Minecraft Volume Alpha.wav");
        on = 0;
        }*/

        gameSound1.playLoop();
    }
}
