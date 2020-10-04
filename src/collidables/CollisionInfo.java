package collidables;

import geometry.Point;

/**
 * A CollisionInfo class.
 *
 * @author Bar Tawil
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Construct the collisionInfo by given 'collidable' object and collision point.
     *
     * @param collisionPoint - collision point
     * @param collidable     - block
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * @return - the point where the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return - the 'collidable' object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}