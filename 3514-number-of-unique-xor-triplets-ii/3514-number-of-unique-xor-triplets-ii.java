class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAXV = 2048; 

       
        boolean[] present = new boolean[MAXV];
        for (int x : nums) present[x] = true;

        int[] distinct = new int[MAXV];
        int m = 0;
        for (int i = 0; i < MAXV; i++) {
            if (present[i]) distinct[m++] = i;
        }

        
        boolean[] pairXor = new boolean[MAXV];
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                pairXor[distinct[i] ^ distinct[j]] = true;
            }
        }

       
        boolean[] tripleXor = new boolean[MAXV];
        for (int p = 0; p < MAXV; p++) {
            if (!pairXor[p]) continue;
            for (int j = 0; j < m; j++) {
                tripleXor[p ^ distinct[j]] = true;
            }
        }

        
        int count = 0;
        for (boolean b : tripleXor) if (b) count++;
        return count;
    }
}