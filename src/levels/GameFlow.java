package levels;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import animations.WinScreen;
import biuoop.KeyboardSensor;
import game.GameLevel;
import listeners.Counter;
import listeners.ScoreTrackingListener;

import java.util.List;

/**
 * GameFlow class.
 *
 * @author Bar Tawil
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreCounter;

    /**
     * Construct a GameFlow.
     *
     * @param ar - the runner.
     * @param ks - the keyboard/=.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreCounter = new Counter(0);
    }

    /**
     * moving from one level to the next.
     *
     * @param levels - list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(scoreCounter);
        int levelNum = 1;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, scoreTracking);

            level.initialize();

            while (level.getBallsCounter().getValue() != 0 && level.getBlocksCounterCounter().getValue() != 0) {
                level.run();
            }

            if (level.getBallsCounter().getValue() == 0) {
                this.animationRunner
                        .run(new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                                new EndScreen(this.scoreCounter)));
                this.animationRunner.getGui().close();
            }

            if (level.getBallsCounter().getValue() != 0 && level.getBlocksCounterCounter().getValue() == 0
                    && levelNum == levels.size()) {
                this.animationRunner
                        .run(new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                                new WinScreen(this.scoreCounter)));
                this.animationRunner.getGui().close();
            }
            levelNum++;
        }
    }
}
