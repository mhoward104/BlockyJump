package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.Random;

/**
 * Created by Matthew on 11/24/2016.
 */
public class player {


    private static Random random = new Random();
    public static final int minX = 16;                      // farthest left the center of a target can go
    public static final int maxX = World.WORLD_WIDTH - 17;  // farthest right the center of a target can go
    public static final int minY = 80;                      // leaving room at the top of the screen for pause and score
    public static final int maxY = World.WORLD_HEIGHT - 17; // leaving room for the radius of the target

    public int locationX = 20, locationY = 100;
    public int gravityX = 20;
    public boolean jump = false, fall = true, slide = false;
    public int floor = 20;
    public int charcterHeight = 60, characterWidth = 30;
    public int count  = 0;


    /**
     * advances the player character if one of the movement options is true changes the location and width/height of the character.
     */
    public void advance()
    {
        if(jump)
        {
            gravityX = gravityX + 5;
           // locationX += gravityX;
            locationX = gravityX;
            if(locationX >= 120)
            {
                jump = false;
                fall = true;
            }
        }
        if(fall)
        {
            gravityX -= 5;
            locationX = gravityX;
            if(gravityX <= floor)
            {
                fall = false;
            }
            /*
            while(gravityX < 0)
            {
                gravityX = gravityX - 2;
            }
            locationX -= gravityX;
           if(locationX <= 20)
            {
                fall = false;
                jump = false;
            }*/
        }
        if(slide)
        {
                characterWidth = 60;
                charcterHeight = 30;
                count += 1;

           if(count >= 45)
            {
                charcterHeight = 60;
                characterWidth = 30;
                count = 0;
                slide = false;
            }

        }



    }

    /**
     * default constructor
     */
    public player()
    {

    }
}
