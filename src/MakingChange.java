import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Liam Krenz
 */

public class MakingChange {

    public static long countWays(int target, int[] coins) {

        // Sorts coins from the lowest value to highest
        Arrays.sort(coins);
        return tabulationSolution(target, coins);
    }

    // Uses memoization to find the number of combinations
    private static long memoizationSolution(int target, int[] coins) {

        // vals stores the number of paths to a given value while at a certain coin
        long[][] vals = new long[coins.length][target + 1];
        memoizationRecurse(target, coins, coins.length- 1, vals);
        return vals[coins.length - 1][target];
    }

    // Uses tabulation to find the number of combinations
    private static long tabulationSolution(int target, int[] coins) {

        // vals stores the number of paths to a given value while at a certain coin
        long[][] vals = new long[coins.length][target + 1];
        tabulationIteration(target, coins, vals);
        return vals[coins.length - 1][target];
    }

    // Iterates through vals in a row major order and finds the number of paths to each index
    private static void tabulationIteration(int target, int[] coins, long[][] vals) {

        // Iterates through all the coins
        for (int i = 0; i < coins.length; i++) {

            // Base case -- target has been reached with this coin
            vals[i][0] = 1;

            // Iterates through every column and adds up the paths to reach that point from target
            for (int j = 1; j <= target; j++) {
                long currentPaths = 0;

                // Adds paths that move on to a smaller coin
                if (i - 1 >= 0) {
                    currentPaths += vals[i - 1][j];
                }
                // Adds paths that use the current coin
                if (j - coins[i] >= 0) {
                    currentPaths += vals[i][j - coins[i]];
                }
                vals[i][j] = currentPaths;
            }
        }
    }

    // Uses recursion to find paths to target
    private static void memoizationRecurse(int currVal, int[] coins, int currentCoin, long[][] vals) {

        // Base case -- the target value has been reached
        if (currVal == 0) {
            vals[currentCoin][0] = 1;
            return;
        }

        long paths = 0;

        // Adds all paths that include using the current coin
        if ((currVal - coins[currentCoin]) >= 0) {

            // If the number of paths hasn't been calculated, there is a recursive call
            if (vals[currentCoin][currVal - coins[currentCoin]] == 0) {
                memoizationRecurse(currVal - coins[currentCoin], coins, currentCoin, vals);
            }
            paths += vals[currentCoin][currVal - coins[currentCoin]];
        }

        // Adds all paths that exclude the current coin
        if (currentCoin > 0) {

            // If the number of paths hasn't been calculated, there is a recursive call
            if (vals[currentCoin - 1][currVal] == 0) {
                memoizationRecurse(currVal, coins, currentCoin - 1, vals);
            }
            paths += vals[currentCoin - 1][currVal];
        }
        vals[currentCoin][currVal] = paths;
    }
}
