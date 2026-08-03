class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
    
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    dist[r][c] = 0;
                }
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxHeap.offer(new int[]{dist[0][0], 0, 0});

        int[][] maxSafeness = new int[n][n];
        for (int[] row : maxSafeness) {
            Arrays.fill(row, -1);
        }
        maxSafeness[0][0] = dist[0][0];

        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int safe = current[0];
            int r = current[1];
            int c = current[2];

        
            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    int newSafeness = Math.min(safe, dist[nr][nc]);

                    if (newSafeness > maxSafeness[nr][nc]) {
                        maxSafeness[nr][nc] = newSafeness;
                        maxHeap.offer(new int[]{newSafeness, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}