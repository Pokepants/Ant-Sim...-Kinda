import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AntArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AntArena extends World
{
    SimpleTimer fSpawnTimer;
    SimpleTimer antSpawnT;
    Ant ant;
    Food food;
    Spider spider;
    SpiderWeb web;
    Colony swarm;

    public GreenfootSound backgroundMusic = new GreenfootSound("Sandstorm.mp3");
    public GreenfootSound attackSound = new GreenfootSound("OOF.mp3");

    int numAnts = 80;
    int numFood = 20;
    int numSpiders = 3;
    int numColony = 1;
    int initNumWebs = 10;
    double size = 30;

    /**
     * Constructor for objects of class AntArena.
     * 
     */ 

    public GreenfootSound attackSound(){
        attackSound.play();
        return attackSound;
    }

    public AntArena()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 600, 1); 
        backgroundMusic.playLoop();

        //contruct the food timer project
        fSpawnTimer = new SimpleTimer();
        antSpawnT = new SimpleTimer();

        for (int i = 0; i < initNumWebs; i++){
            web = new SpiderWeb();
            addObject(web, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < numAnts; i++){
            ant = new Ant();
            addObject(ant, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < numColony; i++){
            swarm = new Colony();
            addObject(swarm,getWidth()/2,getHeight()/2);
        }
        for (int i = 0; i < numSpiders; i++){
            spider = new Spider();
            addObject(spider, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < numFood; i++){ 
            addObject(new Food(80), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

    }

    public void act(){
        //if the food spawntimer has enough timer
        if(fSpawnTimer.millisElapsed() > Food.spawnTime){
            //then spawn in another food
            Food temp = new Food();
            //adding object to the world
            addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            fSpawnTimer.mark();
            numFood++;
        }
        if(fSpawnTimer.millisElapsed() > SpiderWeb.spawnTime){
            //then spawn in another food
            SpiderWeb temp = new SpiderWeb();
            //adding object to the world
            addObject(temp,spider.getX(), spider.getY());
            fSpawnTimer.mark();
        }
        if(antSpawnT.millisElapsed()>ant.spawnTime){
            Ant temp = new Ant();
            addObject(temp,getWidth()/2,getHeight()/2);
            //Colony.loseNrg((ant.Nrg+ant.health)/2);
            antSpawnT.mark();
            numAnts++;
        }
        showText("# of Ants spawn total "+numAnts,getWidth()/5,getHeight()/20);
        showText("# of Food total "+numFood,getWidth()/5*3,getHeight()/20);
    }
}