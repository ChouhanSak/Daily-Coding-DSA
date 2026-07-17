class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int[] freq = new int[maxVal + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] countMultiples = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            for (int m = g; m <= maxVal; m += g) {
                countMultiples[g] += freq[m];
            }
        }

        
        long[] exactGcdCnt = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long c = countMultiples[g];
            exactGcdCnt[g] = c * (c - 1) / 2; 

            for (int m = 2 * g; m <= maxVal; m += g) {
                exactGcdCnt[g] -= exactGcdCnt[m];
            }
        }

        long[] pref = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            pref[g] = pref[g - 1] + exactGcdCnt[g];
        }

       
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = binarySearch(pref, queries[i]);
        }

        return ans;
    }

    private int binarySearch(long[] pref, long target) {
        int low = 1, high = pref.length - 1;
        int res = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (pref[mid] > target) {
                res = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;
            }
        }

        return res;
    }
}