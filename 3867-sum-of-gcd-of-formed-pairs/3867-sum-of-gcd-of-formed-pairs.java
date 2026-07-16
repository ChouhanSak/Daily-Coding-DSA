class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixGcd = new long[n];
        long maxVal = 0;

        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, nums[i]);
            prefixGcd[i] = gcd(nums[i], maxVal);
        }

    
        Arrays.sort(prefixGcd);

        
        long totalSum = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }

        return totalSum;
    }

    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}