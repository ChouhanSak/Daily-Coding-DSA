class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] memo = new int[n][n + 1];
        int[] suffixSum = new int[n];
        
        
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return getRemainingStones(0, 1, piles, suffixSum, memo);
    }
    
    private int getRemainingStones(int i, int m, int[] piles, int[] suffixSum, int[][] memo) {
        if (i >= piles.length) return 0;
        
       
        if (i + 2 * m >= piles.length) {
            return suffixSum[i];
        }
        
        if (memo[i][m] > 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int nextM = Math.max(m, x);
            
            int currentScore = suffixSum[i] - getRemainingStones(i + x, nextM, piles, suffixSum, memo);
            maxStones = Math.max(maxStones, currentScore);
        }
        
        memo[i][m] = maxStones;
        return maxStones;
    }
}
    
