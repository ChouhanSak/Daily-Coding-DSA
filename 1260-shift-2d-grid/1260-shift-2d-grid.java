class Solution 
{
    public List<List<Integer>> shiftGrid(int[][] grid, int k) 
    {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k = k % total;

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < m; i++)
        {
            result.add(new ArrayList<>());
        }
        for(int i = 0; i < total; i++)
        {
            int oldIndex = (i - k + total) % total;

            int oldRow = oldIndex / n;
            int oldCol = oldIndex % n;

            int currentRow = i / n;

            result.get(currentRow).add(grid[oldRow][oldCol]);
        }
        return result;
    }
}