class Solution 
{
    class Edge 
    {
        int to;
        int cost;
        Edge(int to, int cost) 
        {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) 
    {
        int n = online.length;
        
       
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) 
        {
            graph.add(new ArrayList<>());
        }
        
        int maxEdgeCost = 0;
        for (int[] edge : edges) 
        {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            if (!online[u] || !online[v]) continue;
            
            graph.get(u).add(new Edge(v, cost));
            maxEdgeCost = Math.max(maxEdgeCost, cost);
        }

        
        int low = 0;
        int high = maxEdgeCost;
        int result = -1;

        while (low <= high) 
        {
            int mid = low + (high - low) / 2;
            
            
            if (isValidPathPossible(graph, n, mid, k)) 
            {
                result = mid;       
                low = mid + 1;      
            } else 
            {
                high = mid - 1;     
            }
        }

        return result;
    }

    
    private boolean isValidPathPossible(List<List<Edge>> graph, int n, int minEdgeVal, long maxBudget) 
    {
       
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) 
        {
            long[] current = pq.poll();
            int u = (int) current[0];
            long currentCost = current[1];

            
            if (u == n - 1) return true;

           
            if (currentCost > minCost[u]) continue;

            for (Edge edge : graph.get(u)) 
            {
                
                if (edge.cost < minEdgeVal) continue;

                long nextCost = currentCost + edge.cost;

                
                if (nextCost <= maxBudget && nextCost < minCost[edge.to]) {
                    minCost[edge.to] = nextCost;
                    pq.add(new long[]{edge.to, nextCost});
                }
            }
        }

        return minCost[n - 1] <= maxBudget;
    }
}