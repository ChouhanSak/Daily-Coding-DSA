class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        
        int[][] dp = new int[maxNum + 1][maxNum + 1];
        
        
        dp[0][0] = 1;

        
        int[][] gcdTable = new int[maxNum + 1][maxNum + 1];
        for (int i = 0; i <= maxNum; i++) {
            for (int j = 0; j <= maxNum; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        for (int num : nums) {
            
            int[][] nextDp = new int[maxNum + 1][maxNum + 1];

            for (int x = 0; x <= maxNum; x++) {
                for (int y = 0; y <= maxNum; y++) {
                    if (dp[x][y] == 0) continue;

                    long currentWays = dp[x][y];

                    
                    nextDp[x][y] = (int) ((nextDp[x][y] + currentWays) % MOD);

                   
                    int newX = gcdTable[x][num];
                    nextDp[newX][y] = (int) ((nextDp[newX][y] + currentWays) % MOD);

                   
                    int newY = gcdTable[x][num];
                    int actualNewY = gcdTable[y][num];
                    nextDp[x][actualNewY] = (int) ((nextDp[x][actualNewY] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        
        long totalPairs = 0;
        for (int g = 1; g <= maxNum; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}