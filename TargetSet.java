package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 11/17/2016.
 */
public class TargetSet {

    public List<Target> targets = new ArrayList<Target>();


    public void TargetSet(int numTargets)
    {
        for(int i = 0; i < numTargets; i++)
        {
            targets.add(new Target());
        }
    }

    public TargetSet()
    {
        TargetSet(10);
    }

    public void advance()
    {
        for(int i = 0; i < targets.size(); i++) {

            targets.get(i).advance();
        }
    }
}
