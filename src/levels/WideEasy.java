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
 * Level 2 class.
 *
 * @author Bar Tawil
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
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
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                // draw rainbow
                for (int i = 0; i < 100; i++) {
                    d.setColor(new Color(255, 255, 153));
                    d.drawLine(100, 120, 780 - 8 * i, 250);
                }
                // draw sun
                d.setColor(new Color(255, 255, 153));
                d.fillCircle(100, 120, 70);
                d.setColor(new Color(255, 255, 102));
                d.fillCircle(100, 120, 60);
                d.setColor(new Color(255, 255, 0));
                d.fillCircle(100, 120, 50);
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
        double numOfBlocks = 15;
        Color[] colors = new Color[7];
        colors[0] = new Color(153, 0, 61);
        colors[1] = new Color(255, 51, 51);
        colors[2] = new Color(255, 255, 77);
        colors[3] = new Color(26, 255, 26);
        colors[4] = new Color(0, 250, 153);
        colors[5] = new Color(6, 132, 122);
        colors[6] = new Color(0, 0, 153);
        // sets all blocks in the right place
        for (int i = 0; i < colors.length; i++) {
            int stopSecondLoop = 2;
            if (i == 3) {
                stopSecondLoop = 3;
            }
            for (int j = 1; j <= stopSecondLoop; j++) {
                // small blocks width
                double width = (800 - 2 * 25) / numOfBlocks;
                // small blocks height
                double height = 25;
                double startX = 775 - width - width * blocks.size();
                Rectangle rectangle = new Rectangle(new Point(startX, 250), width, height);
                Block block = new Block(rectangle, colors[i]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
