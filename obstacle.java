package edu.angelo.FinalProjectBlockyJumpHoward;



/**
 * Created by Matthew on 12/5/2016.
 */
public class obstacle {



    public static final int minX = 16;                      // farthest left the center of a target can go
    public static final int maxX = World.WORLD_WIDTH - 17;  // farthest right the center of a target can go
    public static final int minY = 80;                      // leaving room at the top of the screen for pause and score
    public static final int maxY = World.WORLD_HEIGHT + 20; // leaving room for the radius of the target

    public int locationX, locationY, velocityX, velocityY;
    public int type = 1; // 0 bottom 1 top 3 wall

    //moves the object left
    public void advance()
    {
        locationY -= 3;
    }


    //default constructor
    public  obstacle()
    {
        locationX = 20;
        locationY = maxY;
    }
}
