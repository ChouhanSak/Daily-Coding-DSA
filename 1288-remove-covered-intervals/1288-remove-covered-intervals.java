class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Step 1: Sort by start ascending, then by end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Descending by end
            }
            return a[0] - b[0]; // Ascending by start
        });
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        // Step 2: Traverse and count non-covered intervals
        for (int[] interval : intervals) {
            int currentEnd = interval[1];
            
            // If the current end extends past the max end seen so far,
            // it is not covered!
            if (currentEnd > maxEnd) {
                remainingCount++;
                maxEnd = currentEnd; // Update the boundary
            }
        }
        
        return remainingCount;
    }
}