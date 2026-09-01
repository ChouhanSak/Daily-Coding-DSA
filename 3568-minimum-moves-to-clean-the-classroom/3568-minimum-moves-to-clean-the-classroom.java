class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Store which bit corresponds to which litter cell
        int[][] litterIndex = new int[m][n];

        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        // Find starting position and index every litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char cell = classroom[i].charAt(j);

                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (cell == 'L') {
                    litterIndex[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;
        int targetMask = totalMasks - 1;

        /*
         * bestEnergy[mask][row][col]
         *
         * Maximum energy with which we have reached
         * (row, col) after collecting the litter in mask.
         */
        int[][][] bestEnergy = new int[totalMasks][m][n];

        for (int mask = 0; mask < totalMasks; mask++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(bestEnergy[mask][i], -1);
            }
        }

        // State:
        // {row, col, collectedMask, remainingEnergy}
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
                startRow,
                startCol,
                0,
                energy
        });

        bestEnergy[0][startRow][startCol] = energy;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // All states currently in queue are at same distance
            for (int s = 0; s < size; s++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];
                int mask = current[2];
                int currentEnergy = current[3];

                // Cannot make another move
                if (currentEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int newRow = row + dr[d];
                    int newCol = col + dc[d];

                    // Boundary check
                    if (newRow < 0 || newRow >= m ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    char cell = classroom[newRow].charAt(newCol);

                    // Obstacle
                    if (cell == 'X') {
                        continue;
                    }

                    // One move costs one energy
                    int newEnergy = currentEnergy - 1;

                    // Copy current collected litter
                    int newMask = mask;

                    // Reset energy if on R
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    // Collect litter if present
                    if (cell == 'L') {
                        int index = litterIndex[newRow][newCol];

                        newMask = newMask | (1 << index);
                    }

                    // We found a path collecting all litter
                    if (newMask == targetMask) {
                        return moves + 1;
                    }

                    /*
                     * Dominance check:
                     * If we already reached this same
                     * (mask, row, col)
                     * with equal or more energy,
                     * current state is useless.
                     */
                    if (bestEnergy[newMask][newRow][newCol] >= newEnergy) {
                        continue;
                    }

                    bestEnergy[newMask][newRow][newCol] = newEnergy;

                    queue.offer(new int[]{
                            newRow,
                            newCol,
                            newMask,
                            newEnergy
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}
    