class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] halfFreq = new int[26];
        char midChar = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
            halfFreq[i] = freq[i] / 2;
            halfLen += halfFreq[i];
        }

        long cap = k + 1; 

        long totalPermutations = countPermutations(halfFreq, halfLen, cap);
        if (k > totalPermutations) {
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();
        int remLen = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (halfFreq[c] == 0) continue;

                halfFreq[c]--;
                long count = countPermutations(halfFreq, remLen - 1, cap);

                if (k <= count) {
                    firstHalf.append((char) ('a' + c));
                    remLen--;
                    break;
                } else {
                    k -= count;
                    halfFreq[c]++; 
                }
            }
        }

        String left = firstHalf.toString();
        String right = new StringBuilder(left).reverse().toString();

        if (midChar != 0) {
            return left + midChar + right;
        } else {
            return left + right;
        }
    }

    
    private long countPermutations(int[] counts, int len, long cap) {
        long res = 1;
        int currentLen = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= counts[i]; j++) {
                currentLen++;
                res = res * currentLen / j;
                if (res > cap) {
                    return cap;
                }
            }
        }
        return res;
    }
}