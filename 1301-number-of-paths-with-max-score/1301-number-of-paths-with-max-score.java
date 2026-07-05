class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // dpSum[i][j] stores the max score to reach (i, j) from 'S'
        int[][] dpSum = new int[n][n];
        // dpCount[i][j] stores the number of paths achieving that max score
        int[][] dpCount = new int[n][n];

        // Initialize the starting position 'S'
        dpCount[n - 1][n - 1] = 1; 

        // Directions we can look back to: Down, Right, Diagonal Down-Right
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

        // Traverse from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip the starting point initialization logic and obstacles
                if ((i == n - 1 && j == n - 1) || board.get(i).charAt(j) == 'X') {
                    continue;
                }

                int maxScore = -1;
                int paths = 0;

                // Look at the 3 possible previous cells
                for (int[] dir : dirs) {
                    int prevI = i + dir[0];
                    int prevJ = j + dir[1];

                    // Check bounds and ensure the neighbor is reachable
                    if (prevI < n && prevJ < n && dpCount[prevI][prevJ] > 0) {
                        if (dpSum[prevI][prevJ] > maxScore) {
                            maxScore = dpSum[prevI][prevJ];
                            paths = dpCount[prevI][prevJ];
                        } else if (dpSum[prevI][prevJ] == maxScore) {
                            paths = (paths + dpCount[prevI][prevJ]) % MOD;
                        }
                    }
                }

                // If maxScore is still -1, this cell is unreachable
                if (maxScore != -1) {
                    char c = board.get(i).charAt(j);
                    // 'E' adds 0 to the sum, numeric characters add their value
                    int currentVal = (c == 'E') ? 0 : (c - '0');
                    dpSum[i][j] = maxScore + currentVal;
                    dpCount[i][j] = paths;
                }
            }
        }

        // Return result at the destination 'E' (0, 0)
        return new int[]{dpSum[0][0], dpCount[0][0]};
    }
}