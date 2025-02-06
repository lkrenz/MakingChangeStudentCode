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
        return memoizationSolution(target, coins);
    }

    private static long memoizationSolution(int target, int[] coins) {
        long[][] vals = new long[coins.length][target + 1];
        memoizationRecurse(target, coins, coins.length- 1, vals);
        return vals[coins.length - 1][target];
    }

    private static long tabulationSolution(int target, int[] coins) {
        long[][] vals = new long[coins.length][target + 1];
        tabulatoi
    }

    private static void tabulationRecurse

    private static void memoizationRecurse(int currval, int[] coins, int currentCoin, long[][] vals) {
        if (currval == 0) {
            vals[currentCoin][0] = 1;
            return;
        }

        long paths = 0;

        if ((currval - coins[currentCoin]) >= 0) {
            if (vals[currentCoin][currval - coins[currentCoin]] == 0) {
                memoizationRecurse(currval - coins[currentCoin], coins, currentCoin, vals);
            }
            paths += vals[currentCoin][currval - coins[currentCoin]];
        }
        if (currentCoin > 0) {
            if (vals[currentCoin - 1][currval] == 0) {
                memoizationRecurse(currval, coins, currentCoin - 1, vals);
            }
            paths += vals[currentCoin - 1][currval];
        }

        vals[currentCoin][currval] = paths;
    }
}
