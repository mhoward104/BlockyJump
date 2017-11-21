package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.Random;

public class World {
    static final int WORLD_WIDTH = 320;
    static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 10;
    //changed it so the snake starts a lot faster. Its mroe fo a challenge this way.
    static final float TICK_INITIAL = .02f;
    static final float TICK_DECREMENT = 1.5f;
    private static Random rand = new Random();

    //targetSet of type TargetSet
    public obstacleSet obstacleSet = new obstacleSet();
    public player character = new player();


    public boolean gameOver = false;;
    public int score = 0;
    public int randAdd = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    float tick = TICK_INITIAL;
    public int numFrames = 50;
    public int counter = 0;

//method to randomly add obstacles to the oabstacleset as well as erases them

    /**
     * Technique for increasing efficiency and reducing power- when an object exits the screen on the left it is automatically erased from the list of objects
     * Principle of good game design- Randomly spawns the object between 50 - 100 ticks.  SO that the game is always different. Chance
     *
     * Method to spawn and de-spawn objects as well as set which type of object it is(bottom object or Top Object
     */
    public void spawn()
    {
        if(counter == numFrames)
        {
            numFrames = random.nextInt(101) + 50;
            counter = 0;
            randAdd = random.nextInt(3);
           /* if(randAdd == 1 || randAdd == 0 )
            {*/
            obstacleSet.obstacles.add(new obstacle());
            obstacleSet.obstacles.get(obstacleSet.obstacles.size() - 1).type = random.nextInt(2);

            if(obstacleSet.obstacles.get(obstacleSet.obstacles.size() - 1).type == 0)
            {
                obstacleSet.obstacles.get(obstacleSet.obstacles.size() - 1).locationX = 60;
            }
           /* }*/
        }
        counter++;

        for(int i = 0; i <obstacleSet.obstacles.size(); i++)
        {
            if(obstacleSet.obstacles.get(i).locationY <= 0)
            {
                obstacleSet.obstacles.remove(i);
            }
        }
    }

    /**
     * Method to check if the player/character has collided with an object
     */
    public void hit()
    {
        for(int i = 0; i < obstacleSet.obstacles.size(); i++)
        {

            if((obstacleSet.obstacles.get(i)).type == 1 && character.slide == false)
            {
                if((Math.abs(obstacleSet.obstacles.get(i).locationY - character.locationY) <= 25) && (Math.abs((obstacleSet.obstacles.get(i).locationX) - character.locationX) <= 30)) // made the hit smaller to be more fair
                {
                    gameOver = true;
                }
            }

            if((obstacleSet.obstacles.get(i)).type == 1 && character.slide == true)
            {
                if((Math.abs(obstacleSet.obstacles.get(i).locationY - character.locationY) <= 60) && (Math.abs((obstacleSet.obstacles.get(i).locationX) - character.locationX) <= 30)) // made the hit smaller to be more fair
                {
                    gameOver = true;
                }
            }

            if((obstacleSet.obstacles.get(i)).type == 0 && character.slide == false)
            {
                if((Math.abs(obstacleSet.obstacles.get(i).locationY - character.locationY) <= 25))
                {
                    gameOver = true;
                }

            }


        }
    }

    /**
     * if the Y of the Character and object are equal within 5 pixles add a point to the score
     */
    public void scoring()
    {

        for(int i = 0; i < obstacleSet.obstacles.size(); i++)
        {
            if (character.locationY - obstacleSet.obstacles.get(i).locationY == 5)
            {
                score += 1;
                if(tick - TICK_DECREMENT > 0) {
                    tick -= TICK_DECREMENT;
                }
            }
        }

    }

    public void update(float deltaTime) {
        if (gameOver)
            return;

        tickTime += deltaTime;
    //added functioncalls to update()
        while (tickTime > tick) {
            tickTime -= tick;
            hit();
            spawn();
            scoring();
            obstacleSet.advance();


            if(character.jump || character.fall || character.slide )

                character.advance();





        }
    }
}
