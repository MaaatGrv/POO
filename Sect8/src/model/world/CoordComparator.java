package model.world;

import java.util.Comparator;

import model.agents.Agent;

public class CoordComparator implements Comparator<Agent> {
    // First Compare X, then Y, then ID
    @Override
    public int compare(Agent a1, Agent a2) {
        if (a1.getCoord().getX() > a2.getCoord().getX()) {
            return 1;
        } else if (a1.getCoord().getX() < a2.getCoord().getX()) {
            return -1;
        } else {
            if (a1.getCoord().getY() > a2.getCoord().getY()) {
                return 1;
            } else if (a1.getCoord().getY() < a2.getCoord().getY()) {
                return -1;
            } else {
                if (a1.getId() > a2.getId()) {
                    return 1;
                } else if (a1.getId() < a2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
