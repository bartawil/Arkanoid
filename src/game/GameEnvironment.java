package game;

import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 * GameEnvironment class.
 *
 * @author Bar Tawil
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Construct a game environment by list of objects.
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c - new object in the environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * return the information about the closest collision that is going to occur.
     *
     * @param trajectory - line
     * @return - null/closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> allPointsList = new LinkedList<>();
        if (!collidables.isEmpty()) {
            for (Collidable c : collidables) {
                Rectangle r = c.getCollisionRectangle();
                if (!r.intersectionPoints(trajectory).isEmpty()) {
                    allPointsList.add(new CollisionInfo(trajectory.closestIntersectionToStartOfLine(r), c));
                }
            }
        } else {
            return null;
        }
        if (!allPointsList.isEmpty()) {
            int listSize = allPointsList.size();
            int min = 0;
            for (int i = 1; i < listSize; i++) {
                if (trajectory.start().distance(allPointsList.get(min).collisionPoint())
                        > trajectory.start().distance(allPointsList.get(i).collisionPoint())) {
                    min++;
                }
            }
            return allPointsList.get(min);
        } else {
            return null;
        }
    }

    /**
     *
     * @return - the list of Collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}