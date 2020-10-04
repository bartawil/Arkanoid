package collidables;

import biuoop.DrawSurface;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import listeners.HitListener;
import listeners.HitNotifier;

import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * A Block class implementing the collidable and sprite interface.
 *
 * @author Bar Tawil
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Construct a new block with rectangle and color.
     *
     * @param rect  - gets the block size from the rectangle class
     * @param color - color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return - the collision object
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - collision point
     * @param currentVelocity - current hit velocity
     * @param hitter          - the ball that hit
     * @return - the new velocity expected after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * @param surface - draw the block on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(new Color(187, 202, 182));
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() { }

    /**
     * @param g - add the sprite and the collidable to the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the block from the game.
     * @param game - this game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this method will be called whenever a hit() occurs,
     * and will notify all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter - the ball that his the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
