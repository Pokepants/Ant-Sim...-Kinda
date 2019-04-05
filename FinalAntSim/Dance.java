import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dance here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dance extends Actor
{
    GifImage[] gifImages = new GifImage[85];
    int imageNumber = 1;
    
    public Dance()
    {
        for(int i=1;i < gifImages.length;i++){
            gifImages[i] = new GifImage("f (" + i + ").gif");
            setImage(gifImages[imageNumber].getCurrentImage());
        }
    }
    
    public void act() 
    {
        animation();
    }    
    
    public void animation()
    {
        imageNumber = ( imageNumber + 1 ) % gifImages.length;
        if(imageNumber == 0){
            imageNumber = 1;
        }
        //System.out.println(imageNumber);
        setImage(gifImages[imageNumber].getCurrentImage());
    }
}
