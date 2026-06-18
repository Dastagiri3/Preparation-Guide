class Solution {
    boolean graphColoring(int[][] edges, int m, int n) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] color = new int[n];
        return backtrack(graph, color, 0, m, n);
    }

    private boolean backtrack(List<Integer>[] graph, int[] color, int vertex, int m, int n) {
        if (vertex == n)
            return true; // all vertices colored

        // Try each color from 1 to m
        for (int c = 1; c <= m; c++) {
            if (isSafe(graph, color, vertex, c)) {
                color[vertex] = c;
                if (backtrack(graph, color, vertex + 1, m, n))
                    return true;
                color[vertex] = 0; // backtrack
            }
        }
        return false;
    }

    private boolean isSafe(List<Integer>[] graph, int[] color, int vertex, int c) {
        for (int neighbor : graph[vertex]) {
            if (color[neighbor] == c)
                return false;
        }
        return true;
    }
}