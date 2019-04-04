import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AntArena extends World
{
    Ant ant;
    BREADIT food;
    Spider spider;
    SimpleTimer fSpawnTimer = new SimpleTimer();
    AntArena antArena;
    SpiderWeb web;
    Colony swarm;
    LadyBug ladybug;
    LabelTwo label; 
    
    int numAnts = 10;
    int numFood = 10;
    int numLadybug = 60;
    int numSpider = 10;
    int numColony = 2;
    int initNumWebs = 10;

    private GreenfootSound gameSound1= new GreenfootSound("C418 - Key - Minecraft Volume Alpha.wav");

    public AntArena()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 600, 1); 

        label = new LabelTwo("Press Down To Go Back. Press Right To Make Health Visible, Left To Do The Opposite.", 25);
        addObject(label, 550, 550);
        
        for (int i = 0; i < initNumWebs; i++){
            web = new SpiderWeb();
            addObject(web, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < numLadybug; i++){
            ladybug = new LadyBug();
            addObject(ladybug,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        //contruct the food timer project
        for (int i = 0; i < numSpider; i++){
            spider = new Spider();
            addObject(spider, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

        for (int i = 0; i < numFood; i++){ 
            food = new BREADIT();
            addObject(food, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

        for (int i = 0; i < numColony; i++){
            swarm = new Colony();
            addObject(swarm,Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()));
        }
    }

    public void act(){

        gameSound1.playLoop();

        if(fSpawnTimer.millisElapsed() > Food.spawnTime){
            //then spawn in another food
            BREADIT temp = new BREADIT();
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