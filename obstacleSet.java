package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Matthew on 12/5/2016.
 */
public class obstacleSet {
    private static Random random = new Random();
    public List<obstacle> obstacles = new ArrayList<obstacle>();
    public int NumOf;

    /**
     * loops through number of obstacles and adds how many should be in
     * @param numObstacles how many objects are in the list
     */
    public void obstacleSet(int numObstacles)
    {
        for(int i = 0; i < numObstacles; i++)
        {
            obstacles.add(new obstacle());
        }
    }

    /**
     * starts the list with one object in it
     */
    public obstacleSet()
    {
        obstacleSet(1);
    }

    /**
     * calls teh advance method from the obstacle class on all obstaces in the list
     */
    public void advance()
    {
        for(int i = 0; i < obstacles.size(); i++) {

            obstacles.get(i).advance();
        }
    }
}
