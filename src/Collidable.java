/**
 * This interface will be used by things that can be collided with.
 *
 * @author Bar Tawil
 */
public interface Collidable {

    /**
     * @return - the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  -the collision Point.
     * @param currentVelocity - a given velocity.
     * @return - the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}