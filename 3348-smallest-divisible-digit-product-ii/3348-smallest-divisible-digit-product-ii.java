class Solution {
    private int[][] dp = new int[60][60];

    public String smallestNumber(String num, long t) {
        // Step 1: Factorize t into prime factors 2, 3, 5, 7
        long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }
        if (temp > 1) return "-1"; // Invalid prime factor found

        // Step 2: Properly initialize DP table to infinity
        for (int i = 0; i < 60; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }
        dp[0][0] = 0;

        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 60; j++) {
                if (i == 0 && j == 0) continue;
                int val = 1_000_000;
                
                // Transitions only from strictly smaller states
                if (i > 0) val = Math.min(val, 1 + dp[Math.max(0, i - 1)][j]);                         // digit 2
                if (j > 0) val = Math.min(val, 1 + dp[i][Math.max(0, j - 1)]);                         // digit 3
                if (i > 0) val = Math.min(val, 1 + dp[Math.max(0, i - 2)][j]);                         // digit 4
                if (i > 0 || j > 0) val = Math.min(val, 1 + dp[Math.max(0, i - 1)][Math.max(0, j - 1)]); // digit 6
                if (i > 0) val = Math.min(val, 1 + dp[Math.max(0, i - 3)][j]);                         // digit 8
                if (j > 0) val = Math.min(val, 1 + dp[i][Math.max(0, j - 2)]);                         // digit 9
                
                dp[i][j] = val;
            }
        }

        int n = num.length();
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                firstZero = i;
                break;
            }
        }

        // Prefix factor counts
        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            pref2[i + 1] = pref2[i] + count2(ch - '0');
            pref3[i + 1] = pref3[i] + count3(ch - '0');
            pref5[i + 1] = pref5[i] + count5(ch - '0');
            pref7[i + 1] = pref7[i] + count7(ch - '0');
        }

        // Check if num itself is valid
        if (firstZero == n) {
            if (pref2[n] >= c2 && pref3[n] >= c3 && pref5[n] >= c5 && pref7[n] >= c7) {
                return num;
            }
        }

        // Step 3: Search for the longest matching prefix
        int maxP = Math.min(n - 1, firstZero);
        for (int p = maxP; p >= 0; p--) {
            int startDigit = (p == firstZero) ? 1 : (num.charAt(p) - '0' + 1);
            for (int d = startDigit; d <= 9; d++) {
                int rem2 = c2 - pref2[p] - count2(d);
                int rem3 = c3 - pref3[p] - count3(d);
                int rem5 = c5 - pref5[p] - count5(d);
                int rem7 = c7 - pref7[p] - count7(d);
                int remLen = n - 1 - p;

                if (remLen >= getMinDigits(rem2, rem3, rem5, rem7)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, p));
                    sb.append(d);
                    sb.append(constructSuffix(remLen, rem2, rem3, rem5, rem7));
                    return sb.toString();
                }
            }
        }

        // Step 4: Fallback to length > n if no valid length-n number exists
        int minLenNeeded = getMinDigits(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, minLenNeeded);
        return constructSuffix(targetLen, c2, c3, c5, c7);
    }

    private int getMinDigits(int r2, int r3, int r5, int r7) {
        r2 = Math.max(0, r2);
        r3 = Math.max(0, r3);
        r5 = Math.max(0, r5);
        r7 = Math.max(0, r7);
        return r5 + r7 + dp[r2][r3];
    }

    private String constructSuffix(int remLen, int r2, int r3, int r5, int r7) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < remLen; i++) {
            for (int d = 1; d <= 9; d++) {
                int nr2 = r2 - count2(d);
                int nr3 = r3 - count3(d);
                int nr5 = r5 - count5(d);
                int nr7 = r7 - count7(d);
                int remPos = remLen - 1 - i;
                if (remPos >= getMinDigits(nr2, nr3, nr5, nr7)) {
                    sb.append(d);
                    r2 = nr2;
                    r3 = nr3;
                    r5 = nr5;
                    r7 = nr7;
                    break;
                }
            }
        }
        return sb.toString();
    }

    private int count2(int d) {
        if (d == 2 || d == 6) return 1;
        if (d == 4) return 2;
        if (d == 8) return 3;
        return 0;
    }

    private int count3(int d) {
        if (d == 3 || d == 6) return 1;
        if (d == 9) return 2;
        return 0;
    }

    private int count5(int d) {
        return d == 5 ? 1 : 0;
    }

    private int count7(int d) {
        return d == 7 ? 1 : 0;
    }
}