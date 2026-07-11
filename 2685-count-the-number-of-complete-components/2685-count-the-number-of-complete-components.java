class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        
        List<List<Integer>> adj = new ArrayList<>();
        int[] degree = new int[n];
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        
        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;
        
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> componentNodes = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                
                
                queue.offer(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    componentNodes.add(curr);
                    
                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
                
               
                int numVertices = componentNodes.size();
                boolean isComplete = true;
                
                for (int node : componentNodes) {
                    if (degree[node] != numVertices - 1) {
                        isComplete = false;
                        break;
                    }
                }
                
                if (isComplete) {
                    completeComponentsCount++;
                }
            }
        }
        
        return completeComponentsCount;
    }
}