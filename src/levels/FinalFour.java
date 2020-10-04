package levels;

import biuoop.DrawSurface;
import collidables.Block;

import geometry.Point;
import geometry.Rectangle;

import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Level 4 class.
 *
 * @author Bar Tawil
 */
public class FinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(127, 255, 212));
                d.fillRectangle(0, 0, 800, 600);
                // draw submarine
                d.setColor(new Color(255, 255, 92));
                d.fillOval(400, 400, 200, 100);
                d.fillOval(600, 400, 20, 50);
                d.fillOval(600, 450, 20, 50);
                d.fillRectangle(595, 445, 20, 10);
                d.fillRectangle(470, 390, 50, 50);
                d.fillRectangle(490, 350, 10, 40);
                d.fillRectangle(480, 350, 20, 10);
                d.setColor(new Color(102, 178, 255));
                d.fillOval(475, 348, 10, 13);
                for (int i = 0; i < 3; i++) {
                    d.setColor(new Color(102, 178, 255));
                    d.fillCircle(450 + i * 50, 450, 20);
                }
            }

            @Override
            public void timePassed() {
                return;
            }
        };
        return sprite;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();
        Color[] colors = new Color[7];
        colors[0] = new Color(144, 238, 144);
        colors[1] = new Color(152, 251, 152);
        colors[2] = new Color(143, 188, 143);
        colors[3] = new Color(0, 250, 154);
        colors[4] = new Color(0, 255, 127);
        colors[5] = new Color(46, 139, 87);
        colors[6] = new Color(102, 205, 170);
        // sets all blocks in the right place
        for (int i = 0; i < colors.length; i++) {
            for (int j = 1; j <= 15; j++) {
                // small blocks width
                int width = 50;
                // small blocks height
                int height = 25;
                Rectangle rectangle1 = new Rectangle(new Point(775 - width * j, 150 + height * i), width, height);
                Block block1 = new Block(rectangle1, colors[i]);
                blocks.add(block1);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
