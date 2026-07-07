class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }
        
        long x = 0;
        long digitSum = 0;
        long multiplier = 1;
        
        // Process digits from right to left
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                x += (long) digit * multiplier;
                digitSum += digit;
                multiplier *= 10;
            }
            n /= 10;
        }
        
        return x * digitSum;
    }
}