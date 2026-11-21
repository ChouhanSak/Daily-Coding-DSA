class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = parent.clone();

        // Store closest ancestor having the same character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, adj, s, last, newParent);

        // Build the modified tree
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        int[] ans = new int[n];

        calculateSize(0, tree, ans);

        return ans;
    }

    void dfs(int node, List<Integer>[] adj, String s,
             int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        if (node != 0 && old != -1) {
            newParent[node] = old;
        }

        // Update closest ancestor
        last[ch] = node;

        for (int child : adj[node]) {
            dfs(child, adj, s, last, newParent);
        }

        // Restore previous ancestor
        last[ch] = old;
    }

    int calculateSize(int node, List<Integer>[] tree, int[] ans) {
        ans[node] = 1;

        for (int child : tree[node]) {
            ans[node] += calculateSize(child, tree, ans);
        }

        return ans[node];
    }
}