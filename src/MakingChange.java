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
        Arrays.sort(coins);
        return tabulationSolution(target, coins);
    }

    private static long memoizationSolution(int target, int[] coins) {
        long[][] vals = new long[coins.length][target + 1];
        memoizationRecurse(target, coins, coins.length- 1, vals);
        return vals[coins.length - 1][target];
    }

    private static long tabulationSolution(int target, int[] coins) {
        long[][] vals = new long[coins.length][target + 1];
        tabulationIteration(target, coins, vals);
        System.out.println(Arrays.deepToString(vals));
        return vals[coins.length - 1][target];
    }

    private static void tabulationIteration(int target, int[] coins, long[][] vals) {
        for (int i = 0; i < coins.length; i++) {
            vals[i][0] = 1;
            for (int j = 1; j <= target; j++) {
                long currentPaths = 0;
                if (i - 1 >= 0) {
                    currentPaths += vals[i - 1][j];
                }
                if (j - coins[i] >= 0) {
                    currentPaths += vals[i][j - coins[i]];
                }
                vals[i][j] = currentPaths;
            }
        }
    }

    private static void memoizationRecurse(int currVal, int[] coins, int currentCoin, long[][] vals) {
        if (currVal == 0) {
            vals[currentCoin][0] = 1;
            return;
        }

        long paths = 0;

        if ((currVal - coins[currentCoin]) >= 0) {
            if (vals[currentCoin][currVal - coins[currentCoin]] == 0) {
                memoizationRecurse(currVal - coins[currentCoin], coins, currentCoin, vals);
            }
            paths += vals[currentCoin][currVal - coins[currentCoin]];
        }
        if (currentCoin > 0) {
            if (vals[currentCoin - 1][currVal] == 0) {
                memoizationRecurse(currVal, coins, currentCoin - 1, vals);
            }
            paths += vals[currentCoin - 1][currVal];
        }

        vals[currentCoin][currVal] = paths;
    }
}
