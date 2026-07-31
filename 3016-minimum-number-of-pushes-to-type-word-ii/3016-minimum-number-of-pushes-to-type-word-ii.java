class Solution {
    public int minimumPushes(String word) {
       
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Sort frequencies in ascending order
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int distinctLettersCount = 0;
        
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) {
                break;
            }
            
            
            int pushCost = (distinctLettersCount / 8) + 1;
            
            totalPushes += freq[i] * pushCost;
            distinctLettersCount++;
        }
        
        return totalPushes;
    }
}