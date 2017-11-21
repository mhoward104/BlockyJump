package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.Random;

/**
 * Created by Matthew on 11/17/2016.
 */
public class Target {

    private static Random random = new Random();
    public static final int minX = 16;                      // farthest left the center of a target can go
    public static final int maxX = World.WORLD_WIDTH - 17;  // farthest right the center of a target can go
    public static final int minY = 80;                      // leaving room at the top of the screen for pause and score
    public static final int maxY = World.WORLD_HEIGHT - 17; // leaving room for the radius of the target

    public int locationX, locationY, velocityX, velocityY;

    //sets the lcoationa and velocity
    public void advance()
    {

        if((locationX + velocityX > minX) && (locationX + velocityX < maxX))
        {
            locationX += velocityX;
        }
        else if(locationX + velocityX > maxX)
        {
            locationX = maxX - (velocityX - (maxX - locationX));
            velocityX *= -1;
        }
        else
        {

            locationX = minX - (velocityX - (minX - locationX));
            velocityX *= -1;
        }


        if((locationY + velocityY > minY) && (locationY + velocityY < maxY))
        {
            locationY += velocityY;
        }

        else if(locationY + velocityY > maxY)
        {
            locationY =  maxY - (velocityY - (maxY - locationY));
            velocityY *= -1;
        }

        else
        {

            locationY =  minY - (velocityY - (minY - locationY));
            velocityY *= -1;
        }


}

    //randomizes teh velocity and location
    public void randomize()
    {

        locationX = random.nextInt(maxX + 1 - minX) + minX;
        locationY = random.nextInt(maxY + 1 - minY) + minY;
        // max int = 10 min int = -10
        velocityX = random.nextInt(10 + 1 + 10) - 10;
        velocityY = random.nextInt(10 + 1 + 10) - 10;

    }

    //constructor to call randomize
    public Target()
    {
        randomize();

    }

}
