class Solution {
    public int[] arrayRankTransform(int[] arr) {
        
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        for (int num : sortedArr) {
           
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }
        
        // Step 3: Replace each element in the original array with its rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}