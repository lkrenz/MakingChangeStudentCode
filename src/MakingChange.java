import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author [YOUR NAME HERE]
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
        long[] paths = new long[target + 1];
        coins = sortArray(coins);
        recurseSolution(paths, coins, target, coins.length -1);
        return paths[target];
    }

    public static int[] sortArray(int[] coins) {
        Arrays.sort(coins);
        return coins;
    }

    public static int recurseSolution(long[] paths, int[] coins, int currVal, int lowestRemoved) {
        if (currVal == 0) {
            return 1;
        }
        if (currVal < 0) {
            return 0;
        }

        long numPaths = 0;

        for (int i = lowestRemoved; i > 0; i--) {
            int newTotal = currVal - coins[i];
            if (paths[newTotal] != 0) {
                numPaths += paths[newTotal];
            }
            else {
                paths[newTotal] = recurseSolution(paths, coins, newTotal, i);
            }
            numPaths += paths[newTotal];
        }
    }
}
