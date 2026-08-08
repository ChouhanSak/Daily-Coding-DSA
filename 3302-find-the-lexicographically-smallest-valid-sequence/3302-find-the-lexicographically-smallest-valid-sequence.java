import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
       
        List<Integer>[] charIndices = new ArrayList[26];
        for (int c = 0; c < 26; c++) {
            charIndices[c] = new ArrayList<>();
        }
        for (int idx = 0; idx < n; idx++) {
            charIndices[word1.charAt(idx) - 'a'].add(idx);
        }
        
        int[] lastPos0 = new int[m + 1];
        int[] lastPos1 = new int[m + 1];
        lastPos0[m] = n;
        lastPos1[m] = n;
        
        
        int p0 = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (p0 >= 0 && word1.charAt(p0) != word2.charAt(i)) {
                p0--;
            }
            lastPos0[i] = p0;
            if (p0 >= 0) {
                p0--;
            }
        }
        
        
        for (int i = m - 1; i >= 0; i--) {
     
            int choiceA = lastPos0[i + 1] - 1; 
            
            
            int limit = lastPos1[i + 1];
            int choiceB = -1;
            if (limit > 0) {
                List<Integer> list = charIndices[word2.charAt(i) - 'a'];
                choiceB = findLargestLessThan(list, limit);
            }
            
            lastPos1[i] = Math.max(choiceA, choiceB);
        }
        
       
        int[] ans = new int[m];
        boolean changed = false;
        int prevIdx = -1;
        int j = 0;
        
        for (int i = 0; i < m; i++) {
            boolean found = false;
            while (j < n) {
                if (j <= prevIdx) {
                    j++;
                    continue;
                }
                
                boolean isMatch = (word1.charAt(j) == word2.charAt(i));
                boolean isValid = false;
                boolean nextChanged = changed;
                
                if (isMatch) {
                    if (changed) {
                        isValid = (j < lastPos0[i + 1]);
                    } else {
                        isValid = (j < lastPos1[i + 1]);
                    }
                } else {
                    if (!changed) {
                        isValid = (j < lastPos0[i + 1]);
                        nextChanged = true;
                    }
                }
                
                if (isValid) {
                    ans[i] = j;
                    changed = nextChanged;
                    prevIdx = j;
                    found = true;
                    j++;
                    break;
                }
                j++;
            }
            
            if (!found) {
                return new int[0];
            }
        }
        
        return ans;
    }
    
    private int findLargestLessThan(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) < target) {
                res = list.get(mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }
}