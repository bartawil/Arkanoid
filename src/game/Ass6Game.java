package game;

import animations.AnimationRunner;
import biuoop.KeyboardSensor;
import levels.DirectHit;
import levels.FinalFour;
import levels.GameFlow;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

import java.util.LinkedList;
import java.util.List;

/**
 * a Game class.
 *
 * @author Bar Tawil
 */
public class Ass6Game {
    /**
     * run the game.
     *
     * @param args - none
     */
    public static void main(String[] args) {
        // list representing levels information
        List<LevelInformation> levelNumber = new LinkedList<>();
        levelNumber.add(new DirectHit());
        levelNumber.add(new WideEasy());
        levelNumber.add(new Green3());
        levelNumber.add(new FinalFour());
        // create real list
        List<LevelInformation> levels = new LinkedList<>();
        for (String arg : args) {
            if (arg.length() == 1 && arg.matches("[1-4]+")) {
                int levelNum = Integer.parseInt(arg);
                levels.add(levelNumber.get(levelNum - 1));
            }
        }
        // if we have no args
        if (levels.size() == 0) {
            levels.addAll(levelNumber);
        }
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keyboard = runner.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboard);
        gameFlow.runLevels(levels);
    }
}