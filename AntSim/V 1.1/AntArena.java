 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 
public class AntArena extends World
{
    Ant ant;
    Food food;
    Spider spider;
    SimpleTimer fSpawnTimer;
    AntArena antArena;
    Colony swarm;
    LabelTwo label;
    
    int numAnts = 40;
    int numFood = 10;
    int numSpider = 40;
    int numColony = 1;
    
    private GreenfootSound gameSound1= new GreenfootSound("C418 - Key - Minecraft Volume Alpha.wav");
    
    public AntArena()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 600, 1); 
        
        fSpawnTimer = new SimpleTimer();
        
        label = new LabelTwo("Press Down To Go Back. Press Right To Make Health Visible, Left To Do The Opposite.", 25);
        addObject(label, 550, 550);
        
        //contruct the food timer project
        for (int i = 0; i < numSpider; i++){
            spider = new Spider();
            addObject(spider, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        for (int i = 0; i < numAnts; i++){
            ant = new Ant();
            addObject(ant, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        for (int i = 0; i < numFood; i++){ 
            addObject(new Food(800), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        for (int i = 0; i < numColony; i++){
            swarm = new Colony();
            addObject(swarm,getWidth()/2,getHeight()/2);
        }
    }
    
    public void act(){
        
        gameSound1.playLoop();
        
        if(fSpawnTimer.millisElapsed() > Food.spawnTime){
            //then spawn in another food
            Food temp = new Food();
            //adding object to the world
            addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            fSpawnTimer.mark();
        }
        
        if(Greenfoot.isKeyDown("down")){
            Greenfoot.setWorld(new TitleScreen());
        }
        
        if(Greenfoot.isKeyDown("right"))
        {
            HealthBar.visible = true;
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            HealthBar.visible = false;
        }
    }
}