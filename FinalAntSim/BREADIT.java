import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BREADIT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BREADIT extends Food
{
    Ant ant;
    boolean carried = false;

    public void collected(boolean carry) {
        carried = carry;
    } 

}
