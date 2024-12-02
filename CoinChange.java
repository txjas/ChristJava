import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class CoinChange {
    private static final int NUM_THREADS = 4;

    static class CoinChangeTask implements Runnable {
        private int[] dp;
        private int coin;
        private int sum;

        public CoinChangeTask(int[] dp, int coin, int sum) {
            this.dp = dp;
            this.coin = coin;
            this.sum = sum;
        }

        @Override
        public void run() {
            for (int i = coin; i <= sum; i++) {
                dp[i] += dp[i - coin];
            }
        }
    }

    public static int findWays(int[] coins, int n, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1; // Base case: There's 1 way to make sum 0 (use no coins)

        // ExecutorService for multithreading
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int coin : coins) {
            // Submit a task for each coin
            executor.submit(new CoinChangeTask(dp, coin, sum));
        }

        // Shutdown the executor and wait for tasks to finish
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all tasks to finish
        }

        return dp[sum];
    }

    public static void main(String[] args) {
        int[] coins = {2,5,3,6}; // Example denominations
        int sum = 10; // Example target sum

        int ways = findWays(coins, coins.length, sum);
        System.out.println("Number of ways to make sum " + sum + " is: " + ways);
    }
}
