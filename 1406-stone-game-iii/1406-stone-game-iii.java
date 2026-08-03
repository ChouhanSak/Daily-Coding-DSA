class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

    
        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int currentTakeSum = 0;

            
            for (int k = 0; k < 3 && i + k < n; k++) {
                currentTakeSum += stoneValue[i + k];
                int nextDiff = dp[i + k + 1];
                maxDiff = Math.max(maxDiff, currentTakeSum - nextDiff);
            }

            dp[i] = maxDiff;
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}