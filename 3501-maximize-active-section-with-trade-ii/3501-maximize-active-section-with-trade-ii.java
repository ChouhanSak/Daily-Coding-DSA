class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<int[]> zeroGroups = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                zeroGroups.add(new int[]{start, i - 1, i - start});
            } else {
                i++;
            }
        }

        int m = zeroGroups.size();
        if (m < 2) {
            List<Integer> ans = new ArrayList<>(queries.length);
            for (int k = 0; k < queries.length; k++) {
                ans.add(totalOnes);
            }
            return ans;
        }

        int[] pairSum = new int[m - 1];
        for (int j = 0; j < m - 1; j++) {
            pairSum[j] = zeroGroups.get(j)[2] + zeroGroups.get(j + 1)[2];
        }

        int K = 32 - Integer.numberOfLeadingZeros(m - 1);
        int[][] st = new int[K][m - 1];
        for (int j = 0; j < m - 1; j++) {
            st[0][j] = pairSum[j];
        }
        for (int k = 1; k < K; k++) {
            for (int j = 0; j + (1 << k) <= m - 1; j++) {
                st[k][j] = Math.max(st[k - 1][j], st[k - 1][j + (1 << (k - 1))]);
            }
        }

        int[] logTable = new int[m + 1];
        for (int j = 2; j <= m; j++) {
            logTable[j] = logTable[j >> 1] + 1;
        }

        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];

            int a = binarySearchFirst(zeroGroups, l);
            int b = binarySearchLast(zeroGroups, r);

            if (a == -1 || b == -1 || a >= b) {
                ans.add(totalOnes);
                continue;
            }

            int lenA = zeroGroups.get(a)[1] - Math.max(l, zeroGroups.get(a)[0]) + 1;
            int lenB = Math.min(r, zeroGroups.get(b)[1]) - zeroGroups.get(b)[0] + 1;

            if (b - a == 1) {
                ans.add(totalOnes + lenA + lenB);
                continue;
            }

            int firstPair = lenA + zeroGroups.get(a + 1)[2];
            int lastPair = zeroGroups.get(b - 1)[2] + lenB;
            int maxGain = Math.max(firstPair, lastPair);

            int low = a + 1;
            int high = b - 2;
            if (low <= high) {
                int len = high - low + 1;
                int k = logTable[len];
                int midMax = Math.max(st[k][low], st[k][high - (1 << k) + 1]);
                maxGain = Math.max(maxGain, midMax);
            }

            ans.add(totalOnes + maxGain);
        }

        return ans;
    }

    private int binarySearchFirst(List<int[]> zeroGroups, int l) {
        int low = 0, high = zeroGroups.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (zeroGroups.get(mid)[1] >= l) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int binarySearchLast(List<int[]> zeroGroups, int r) {
        int low = 0, high = zeroGroups.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (zeroGroups.get(mid)[0] <= r) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}