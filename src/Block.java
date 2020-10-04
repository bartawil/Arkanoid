import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A Block class implementing the collidable and sprite interface.
 *
 * @author Bar Tawil
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private Color color;

    /**
     * Construct a new block with rectangle and color.
     *
     * @param rect  - gets the block size from the rectangle class.
     * @param color - color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * @return - the collision object
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notify the object that we collided with it at 'collisionPoint' with a given velocity.
     *
     * @param collisionPoint  - collision point
     * @param currentVelocity - current hit velocity
     * @return - the new velocity expected after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * @param surface - draw the block on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.white);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() { }

    /**
     * @param g - add the sprite and the 'collidable' to the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
