class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1_000_000_007L;

        // 1. Build prefix sum for digits to calculate 'sum' in O(1)
        int[] prefSum = new int[m + 1];
        for (int i = 0; i < m; i++) {
            prefSum[i + 1] = prefSum[i] + (s.charAt(i) - '0');
        }

        // 2. Extract non-zero digits and their positions
        List<Integer> D = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                D.add(ch - '0');
            }
        }

        int k = D.size();

        // 3. Build prefix values for the non-zero digits array `D`
        long[] prefX = new long[k + 1];
        long[] power10 = new long[k + 1];
        power10[0] = 1;

        for (int i = 0; i < k; i++) {
            prefX[i + 1] = (prefX[i] * 10 + D.get(i)) % MOD;
            power10[i + 1] = (power10[i] * 10) % MOD;
        }

        // 4. Precompute mappings from string indices to D indices
        int[] nxt = new int[m];
        int[] last = new int[m];
        
        int currentNxt = k;
        for (int i = m - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                currentNxt--;
            }
            nxt[i] = currentNxt;
        }

        int currentLast = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                currentLast++;
            }
            last[i] = currentLast;
        }

        // 5. Process each query in O(1)
        int numQueries = queries.length;
        int[] answer = new int[numQueries];

        for (int i = 0; i < numQueries; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Get the segment range within the non-zero array D
            int start = nxt[l];
            int end = last[r];

            // If start > end, there are no non-zero digits in s[l..r]
            if (start > end) {
                answer[i] = 0;
                continue;
            }

            // O(1) sum of digits
            long sum = prefSum[r + 1] - prefSum[l];

            // O(1) extraction of x % MOD
            int len = end - start + 1;
            long x = (prefX[end + 1] - (prefX[start] * power10[len]) % MOD + MOD) % MOD;

            // Final answer calculation
            answer[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return answer;
    }
}