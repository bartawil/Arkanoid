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
 * Level 3 class.
 *
 * @author Bar Tawil
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        int halfBalls = numberOfBalls() / 2;
        for (int i = 1; i <= halfBalls; i++) {
            double angle = (i * 10);
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }
        for (int i = halfBalls + 1; i <= numberOfBalls(); i++) {
            double angle = ((i - halfBalls) * 10);
            velocities.add(Velocity.fromAngleAndSpeed(-angle, 5));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(192, 255, 128));
                d.fillRectangle(0, 0, 800, 600);
                // draw tower
                d.setColor(new Color(32, 32, 32));
                d.fillRectangle(40, 450, 90, 150);
                d.setColor(new Color(64, 64, 32));
                d.fillRectangle(70, 400, 30, 50);
                d.setColor(new Color(64, 64, 64));
                d.fillRectangle(80, 250, 10, 150);
                d.setColor(Color.orange);
                d.fillCircle(85, 245, 10);
                d.setColor(Color.red);
                d.fillCircle(85, 245, 8);
                d.setColor(Color.white);
                d.fillCircle(85, 245, 4);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        int limit = 6;
                        int height = 25;
                        int width = 10;
                        d.setColor(Color.white);
                        d.fillRectangle(40 + limit + (width + 7) * i, 450 + limit + (height + 7) * j, width, height);
                    }
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
        List<Block> blocks = new LinkedList<>();
        Color[] colors = new Color[5];
        colors[0] = new Color(128, 0, 0);
        colors[1] = new Color(255, 51, 51);
        colors[2] = new Color(255, 255, 77);
        colors[3] = new Color(26, 255, 26);
        colors[4] = new Color(245, 245, 245);
        // sets all blocks in the right place
        for (int i = 0; i < colors.length; i++) {
            for (int j = 1; j < 11 - i; j++) {
                // small blocks width
                int width = 40;
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
