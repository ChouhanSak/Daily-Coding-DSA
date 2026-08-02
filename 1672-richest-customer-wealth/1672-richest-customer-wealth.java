class Solution {
    public int maximumWealth(int[][] accounts) 
    {
        int max_wealth=0;
        for(int i=0; i<accounts.length; i++){
            int wealth=0;
            for(int j=0; j<accounts[i].length; j++){
               
                wealth= wealth+accounts[i][j];
            }
            if(wealth>max_wealth){
                max_wealth=wealth;

            }
        }
        return max_wealth;

    }
}