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
 * Level 1 class.
 *
 * @author Bar Tawil
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            double angle = (0);
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                for (int i = 0; i < 3; i++) {
                    d.setColor(Color.blue);
                    d.drawCircle(400, 200, 70 + i * 35);
                }
                d.drawLine(230, 200, 570, 200);
                d.drawLine(400, 15, 400, 355);
                d.setColor(Color.DARK_GRAY);
                d.setColor(new Color(68, 68, 68));
                d.drawLine(775, 600, 650, 450);
                d.drawLine(25, 600, 150, 450);
                d.drawLine(150, 450, 650, 450);
                d.drawLine(150, 0, 150, 450);
                d.drawLine(650, 0, 650, 450);
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
        List<Block> blocks = new LinkedList<>();
        Rectangle rectangle1 = new Rectangle(new Point(385, 185), 30, 30);
        Block block1 = new Block(rectangle1, Color.white);
        blocks.add(block1);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
