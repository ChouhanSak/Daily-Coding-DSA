class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list for the bidirectional graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int weight = road[2];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});
        }
        
        // Step 2: Initialize BFS variables
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS from city 1
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            
            // If the node has no outgoing connections, skip it
            if (!graph.containsKey(currentNode)) continue;
            
            for (int[] neighbor : graph.get(currentNode)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                
                // Track the absolute minimum edge weight seen in this component
                minScore = Math.min(minScore, weight);
                
                // If the neighbor hasn't been visited, add it to the queue
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return minScore;
    }
}