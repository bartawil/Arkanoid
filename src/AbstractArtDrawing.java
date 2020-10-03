import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Abstract Art class.
 *
 * @author Bar Tawil
 */
public class AbstractArtDrawing {
    /**
     * the func drawing random lines.
     * The middle point in each line is indicated in blue,
     * while the intersection points between the lines are indicated in red.
     */
    public void drawRandomLines() {
        // create a random-number generator
        Random rand = new Random();
        // Create a window with the title "Random Lines Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        // saves all the lines in array
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            // draws black lines
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            // creates a new line from the coordinates above
            Line line = new Line(x1, y1, x2, y2);
            lines[i] = line;
            // draws the blue middle points
            d.setColor(Color.BLUE);
            d.fillCircle((int) line.middle().getX(), (int) line.middle().getY(), 3);
            // searching for each intersection
            for (int j = 0; j < i; ++j) {
                if (line.isIntersecting(lines[j])) {
                    Point inter = line.intersectionWith(lines[j]);
                    // draws the red intersection points
                    d.setColor(Color.RED);
                    d.fillCircle((int) inter.getX(), (int) inter.getY(), 3);
                }
            }
        }
        gui.show(d);
    }

    /**
     * @param args - lines.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}