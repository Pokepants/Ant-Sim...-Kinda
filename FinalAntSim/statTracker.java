/**
 * Write a description of class statTracker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class statTracker  
{
    // instance variables - replace the example below with your own
    private int aliveAnt;
    private int spawnedAnt;
    private int aliveSpider;
    private int enemyDef;
    private Colony colony;

    /**
     * Constructor for objects of class statTracker
     */
    public statTracker(Colony c){
        colony =  c;
        aliveAnt = 0;
        spawnedAnt = 0;
        aliveSpider = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void deadAnt(){
        aliveAnt--;
    }

    public void aliveAnt(){
        aliveAnt++;
        spawnedAnt();
    }

    public void enemyDef(){
        enemyDef++;
    }

    public void aliveSpider(){
        aliveSpider++;
    }

    private void spawnedAnt(){
        spawnedAnt++;
    }

    public int cEnemyDef(){
        return enemyDef;
    }

    public int cAliveAnt(){
        return aliveAnt;
    }

    public int cSpawnedAnt(){
        return spawnedAnt;
    }

    public int cAliveSpider(){
        return aliveSpider;
    }
}
