class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        
        int[] compId = new int[n];
        int currentComp = 0;
        compId[0] = currentComp;
        
        for (int i = 1; i < n; i++) {
            
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComp++;
            }
            compId[i] = currentComp;
        }
        
        
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            answer[i] = (compId[u] == compId[v]);
        }
        
        return answer;
    }
}