//209215490

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Multi frames bouncing balls animation class.
 *
 * @author Bar Tawil
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * This function creates two frames and draws multi bunching balls in them.
     *
     * @param ball - gets array of balls
     */
    private static void drawAnimation(Ball[] ball) {
        GUI gui = new GUI("title", 800, 800);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            // draws each ball from the array on the surfaces
            for (int i = 0; i < ball.length; i++) {
                // first half of the balls
                if (i < ball.length / 2) {
                    ball[i].moveOneStep(500, 50);
                } else {
                    ball[i].moveOneStep(600, 450);
                }
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
        int x;
        int y;
        // create balls from the args
        for (int i = 0; i < args.length; ++i) {
            int r = Integer.parseInt(args[i]);
            Color ran = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            // first half of the balls
            if (i < args.length / 2) {
                x = rand.nextInt(450) + 50; // get integer in range 50 - 500
                y = rand.nextInt(450) + 50; // get integer in range 50 - 500
            } else {
                x = rand.nextInt(150) + 450; // get integer in range 450 - 600
                y = rand.nextInt(150) + 450; // get integer in range 450 - 600
            }
            ball[i] = new Ball(x, y, r, ran);
            // larger balls will be slower
            int vel = (int) Math.sqrt(51 - r) + 5;
            if (r >= 50) {
                vel = 5;
            }
            Velocity v = Velocity.fromAngleAndSpeed(vel, vel);
            ball[i].setVelocity(v);
        }
        drawAnimation(ball);
    }

}
