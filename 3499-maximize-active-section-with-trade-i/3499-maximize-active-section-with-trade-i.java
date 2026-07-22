import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int n = t.length();
        
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                initialOnes++;
            }
        }
        
        List<Integer> onesLengths = new ArrayList<>();
        List<Integer> zerosLengths = new ArrayList<>();
        
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && t.charAt(j) == t.charAt(i)) {
                j++;
            }
            int len = j - i;
            if (t.charAt(i) == '1') {
                onesLengths.add(len);
            } else {
                zerosLengths.add(len);
            }
            i = j;
        }
        
        int maxGain = 0;
        for (int k = 1; k < onesLengths.size() - 1; k++) {
            int gain = zerosLengths.get(k - 1) + zerosLengths.get(k);
            if (gain > maxGain) {
                maxGain = gain;
            }
        }
        
        return initialOnes + maxGain;
    }
}