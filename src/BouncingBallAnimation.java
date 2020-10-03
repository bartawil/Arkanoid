//ID 209215490

import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * A bouncing ball animation class.
 *
 * @author Bar Tawil
 */
public class BouncingBallAnimation {
    /**
     *
     * This function draws bunching ball animation with one ball.
     *
     * @param start - center point of the ball
     * @param dx - direction of the ball in x axis
     * @param dy - direction of the ball in  y axis
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy); // velocity by x y values
        //Velocity v = Velocity.fromAngleAndSpeed(dx, dy); // velocity by angle and speed
        //ball.setVelocity(v);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // moveOneStep will check limits between 0-200 (200 is the width or height)
            ball.moveOneStep(d.getWidth(), 0);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     *
     * @param args - start point and direction coordinates
     */
    public static void main(String[] args) {
        int x1 = Integer.parseInt(args[0]);
        int x2 = Integer.parseInt(args[1]);
        int x3 = Integer.parseInt(args[2]);
        int x4 = Integer.parseInt(args[3]);
        drawAnimation(new Point(x1, x2), x3, x4);
    }
}