import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Multi bouncing balls animation class.
 *
 * @author Bar Tawil
 */
public class MultipleBouncingBallsAnimation {
    /**
     * This function draws multi bunching balls animation.
     *
     * @param ball - gets array of balls
     */
    private static void drawAnimation(Ball[] ball) {
        GUI gui = new GUI("title", 600, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // draws each ball from the array on the surface
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < ball.length; i++) {
                // moveOneStep will check limits between 0-600 (600 is the width or height)
                ball[i].moveOneStep(d.getWidth(), 0);
                ball[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    /**
     * @param args - size of the balls
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Ball[] ball = new Ball[args.length];
        // create balls from the args
        for (int i = 0; i < args.length; ++i) {
            // gets radius size from the args array
            int r = Integer.parseInt(args[i]);
            // gets random x,y coordinates
            int x = rand.nextInt(600); // get integer in range 0-600
            int y = rand.nextInt(600); // get integer in range 0-600
            Color ran = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            ball[i] = new Ball(x, y, r, ran);
            // larger balls will be slower
            int vel = (int) Math.sqrt(51 - r) + 5;
            if (r >= 50) {
                vel = 5;
            }
            ball[i].setVelocity(vel, vel);
        }
        drawAnimation(ball);
    }
}
