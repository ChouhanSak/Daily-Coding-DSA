class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. Syntax: Creating a HashMap. 
        // Key will store the 'number', Value will store its 'index'.
        Map<Integer, Integer> map = new HashMap<>();
        
        // 2. Syntax: A standard 'for' loop to traverse the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // 3. Syntax: map.containsKey() checks if the number exists in our notebook
            if (map.containsKey(complement)) {
                // map.get() retrieves the value (index) of that complement
                return new int[] { map.get(complement), i };
            }
            
            // 4. Syntax: map.put() adds the current number and its index to the notebook
            map.put(nums[i], i);
        }
        
        // Return an empty array if no solution is found (though constraints say one always exists)
        return new int[] {};
    }
}