class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String sample = "123456789";
        
       
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        
        
        for (int len = minLen; len <= maxLen; len++) {
           
            for (int start = 0; start <= 9 - len; start++) {
                String sub = sample.substring(start, start + len);
                int num = Integer.parseInt(sub);
                
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}