import game.Game;

/**
 * a Game class.
 *
 * @author Bar Tawil
 */
public class Ass5Game {
    /**
     * run the game.
     * @param args - none
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}