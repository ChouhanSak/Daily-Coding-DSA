class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        // Store coordinates of all 1s in img1
        List<int[]> ones1 = new ArrayList<>();

        // Store coordinates of all 1s in img2
        List<int[]> ones2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        // Store frequency of every possible translation
        Map<String, Integer> map = new HashMap<>();

        int answer = 0;

        // Try every 1 from img1 with every 1 from img2
        for (int[] p1 : ones1) {

            for (int[] p2 : ones2) {

                int rowShift = p2[0] - p1[0];
                int colShift = p2[1] - p1[1];

                String shift = rowShift + "," + colShift;

                int count = map.getOrDefault(shift, 0) + 1;

                map.put(shift, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}