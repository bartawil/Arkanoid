package listeners;

/**
 * a Counter class.
 *
 * @author Bar Tawil
 */
public class Counter {
    private int num;

    /**
     * Construct a counter.
     *
     * @param num - number of blocks
     */
    public Counter(int num) {
        this.num = num;
    }

    /**
     * @param number - number we add to current count.
     */
    public void increase(int number) {
        this.num += number;
    }

    /**
     * @param number - number from current count.
     */
    public void decrease(int number) {
        this.num -= number;
    }

    /**
     * @return - current count.
     */
    public int getValue() {
        return this.num;
    }
}