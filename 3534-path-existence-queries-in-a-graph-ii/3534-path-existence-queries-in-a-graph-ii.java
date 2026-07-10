class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
       
        TreeSet<Integer> uniqueSet = new TreeSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }
        
        int k = uniqueSet.size();
        int[] A = new int[k];
        int idx = 0;
        for (int num : uniqueSet) {
            A[idx++] = num;
        }
        
        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < k; i++) {
            valToIdx.put(A[i], i);
        }
        
        int[] next = new int[k];
        int right = 0;
        for (int i = 0; i < k; i++) {
            while (right < k && A[right] - A[i] <= maxDiff) {
                right++;
            }
            next[i] = right - 1; 
        }
        
        // --- FIX ADDED HERE ---
        int LOG = 18; 
        int[][] up = new int[k][LOG];
        // ----------------------
        
        for (int i = 0; i < k; i++) {
            up[i][0] = next[i];
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < k; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            if (nums[u] == nums[v]) {
                ans[q] = 1;
                continue;
            }
            
            int idx1 = valToIdx.get(nums[u]);
            int idx2 = valToIdx.get(nums[v]);
            
            int startIdx = Math.min(idx1, idx2);
            int targetIdx = Math.max(idx1, idx2);
            
            int steps = 0;
            int curr = startIdx;
            
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < targetIdx) {
                    curr = up[curr][j];
                    steps += (1 << j);
                }
            }
            
            steps++;
            curr = up[curr][0];
            
            if (curr < targetIdx) {
                ans[q] = -1;
            } else {
                ans[q] = steps;
            }
        }
        
        return ans;
    }
}