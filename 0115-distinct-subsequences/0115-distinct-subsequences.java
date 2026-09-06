class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Edge case: target is longer than source
        if (m < n) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: matching empty string

        for (int i = 1; i <= m; i++) {
            char sChar = s.charAt(i - 1);
            // Traverse backwards to use values from the previous row
            for (int j = n; j >= 1; j--) {
                if (sChar == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}