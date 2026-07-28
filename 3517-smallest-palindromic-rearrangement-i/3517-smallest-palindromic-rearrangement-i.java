class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char midChar = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                midChar = (char) ('a' + i); 
            }
            
           
            for (int j = 0; j < count[i] / 2; j++) {
                firstHalf.append((char) ('a' + i));
            }
        }

        
        StringBuilder result = new StringBuilder(firstHalf);
        if (midChar != 0) {
            result.append(midChar);
        }
        result.append(firstHalf.reverse());

        return result.toString();
    }
}