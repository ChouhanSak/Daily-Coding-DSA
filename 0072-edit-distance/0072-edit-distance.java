class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
       
        int[] dp = new int[n + 1];
        
        
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        
       
        for (int i = 1; i <= m; i++) {
           
            int pre = dp[0]; 
            dp[0] = i; 
            
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                   
                    dp[j] = Math.min(pre, Math.min(dp[j], dp[j - 1])) + 1;
                }
                pre = temp; 
            }
        }
        
        return dp[n];
    }
}