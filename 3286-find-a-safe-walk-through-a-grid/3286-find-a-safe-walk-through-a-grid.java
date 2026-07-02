
class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        
        Deque<int[]> deque = new ArrayDeque<>();
  
        minCost[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});
        
        
        int[] dirs = {-1, 0, 1, 0, -1};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            

            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i + 1];
                
            
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int edgeWeight = grid.get(nr).get(nc);
                    
            
                    if (minCost[r][c] + edgeWeight < minCost[nr][nc]) {
                        minCost[nr][nc] = minCost[r][c] + edgeWeight;
                        
            
                        if (edgeWeight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
    
        return minCost[m - 1][n - 1] < health;
    }
}