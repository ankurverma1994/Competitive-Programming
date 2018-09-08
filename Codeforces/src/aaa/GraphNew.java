package aaa;

import java.util.*;

class GraphNew {
    //```````````````````````````````````````````````````````````````````````
    // for undirected graph, max is length of from or to array
    public static int[][] packU(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < max; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    //```````````````````````````````````````````````````````````````````````
    // for undirected weighted graph
    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
            --p[to[i]];
            g[to[i]][p[to[i]]][0] = from[i];
            g[to[i]][p[to[i]]][1] = w[i];
        }
        return g;
    }

    public static int[][] parentToChildren(int[] par) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = par.length;
        int[] ct = new int[n];
        for (int i = 0; i < n; i++) {
            if (par[i] >= 0) {
                ct[par[i]]++;
            }
        }
        int[][] g = new int[n][];
        for (int i = 0; i < n; i++) {
            g[i] = new int[ct[i]];
        }
        for (int i = 0; i < n; i++) {
            if (par[i] >= 0) {
                g[par[i]][--ct[par[i]]] = i;
            }
        }
        return g;
    }

    //`````````````````````````````````````````````````````````````````````````````
    // for directed graph, max is length of from or to array
    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    //````````````````````````````````````````````````````````````````````````````
    // for directed weighted graph
    public static int[][][] packWD(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
        }
        return g;
    }

    public int[][] parents3(int[][] g, int root) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] levelTraversal = new int[n];
        levelTraversal[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = levelTraversal[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    levelTraversal[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, levelTraversal, depth};
    }

    //`````````````````````````````````````````````````````````````````````````````

    int dfs_timer = 0, time_in[], time_out[];

    void dfs2(int graph[][], int v) {
        time_in[v] = dfs_timer++;
        color[v] = 1;
        for (int i = 0; i < graph[v].length; i++) {
            int u = graph[v][i];
            if (color[u] == 0)
                dfs2(graph, u);
        }
        color[v] = 2;
        time_out[v] = dfs_timer++;
    }
    //`````````````````````````````````````````````````````````````````````````````
    //iterative DFS
//    boolean visit[];

    void dfs1(int graph[][], int s) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                size++;
                for (int i = 0; i < graph[v].length; i++) {
                    int w = graph[v][i];
                    st.push(w);
                }
            }
        }
    }

    //````````````````````````````````````````````````````````````````````````````````
    void bfs(int v, int graph[][]) {
        int d[] = new int[graph.length];
        int parent[] = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < graph[u].length; w++) {
                if (d[graph[u][w]] == Integer.MAX_VALUE / 2) {
                    d[graph[u][w]] = d[u] + 1;
                    parent[graph[u][w]] = u;
                    q.add(graph[u][w]);
                }
            }
        }
    }

    //````````````````````````````````````````````````````````````````````````````````````````
    // returns sum of size of each connected component
    int size;

    long SizeOfEachConnComp(int graph[][], int n) {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                size = 0;
                dfs1(graph, i);
                ans = ans + size;
            }
        }
        return ans;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````
    public static int[] sortTopologically(int[][][] g) {
        int n = g.length;
        int[] ec = new int[n];
        for (int i = 0; i < n; i++) {
            for (int[] to : g[i]) ec[to[0]]++;
        }
        int[] ret = new int[n];
        int q = 0;

        // sources
        for (int i = 0; i < n; i++) {
            if (ec[i] == 0) ret[q++] = i;
        }

        for (int p = 0; p < q; p++) {
            for (int[] to : g[ret[p]]) {
                if (--ec[to[0]] == 0) ret[q++] = to[0];
            }
        }
        // loop
        for (int i = 0; i < n; i++) {
            if (ec[i] > 0) return null;
        }
        return ret;
    }

    //```````````````````````````````````````````````````````````````````````````````````````````
    // Floyd Warshal Algorithm O(n^3)
    //@param n number of vertices
    boolean negativeCycle = false;
    long inf = Long.MAX_VALUE / 2;

    void floydWarshall(int g[][][], int n) {
        /*
        for (int k = 0; k < n; k++)
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
         */
        long d[][] = new long[n][n];
        for (int u = 0; u < n; u++)
            for (int v = 0; v < n; v++)
                d[u][v] = inf;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].length; j++) {
                d[i][g[i][j][0]] = g[i][j][1];
            }
            // in case of self loops
            if (d[i][i] >= 0) {
                d[i][i] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            // compute shortest paths using only 0, 1, ..., i as intermediate vertices
            for (int v = 0; v < n; v++) {
                for (int w = 0; w < n; w++) {
                    if (d[v][w] > d[v][i] + d[i][w]) {
                        d[v][w] = d[v][i] + d[i][w];
                    }
                }
                if (d[v][v] < 0) {
                    negativeCycle = true;
                    return;
                }
            }
        }
    }

    //``````````````````````````````````````````````````````````````````````````````````````````
    // Dijikstra Algo
    // @param s-source
    public long[] Dij(int s, int g[][][]) {
        int n = g.length;
        long dis[] = new long[n];
        boolean visit[] = new boolean[n];
        int parent[] = new int[n];
        Arrays.fill(dis, inf);
        Arrays.fill(parent, -1);
        dis[s] = 0;
        Comparator<Node> comp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.dist > o2.dist) return 1;
                if (o1.dist < o2.dist) return -1;
                return 0;
            }
        };
        PriorityQueue<Node> q = new PriorityQueue<>(2 * n, comp);
        q.add(new Node(s, 0));
        while (!q.isEmpty()) {
            Node now = q.poll();
            int u = now.x;
            if (!visit[u]) {
                visit[u] = true;
                for (int i = 0; i < g[u].length; i++) {
                    int curr = g[u][i][0];
                    long w = g[u][i][1];
                    if (dis[u] + w < dis[curr]) {
                        dis[curr] = dis[u] + w;
                        parent[curr] = u;
                        q.add(new Node(curr, dis[curr]));
                    }
                }
            }
        }
        return dis;
    }

    static public class Node {
        int x;
        long dist;

        public Node(int i, long j) {
            x = i;
            dist = j;
        }
    }

    //``````````````````````````````````````````````````````````````````````````````````````````
    // To check graph is Bipartite or not and also to confirm that there doesn't
    // exist odd length cycle.
    boolean visit[];
    int color[];

    boolean isBipartitie(int g[][], int n) {
        visit = new boolean[n];
        color = new int[n];
        for (int i = 0; i < n; i++)
            if (!visit[i] && !bfsTwoColoring(g, i))
                return false;
        return true;
    }

    boolean bfsTwoColoring(int g[][], int source) {
        /* does not contain odd length cycle */
        color[source] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visit[source] = true;
        color[source] = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < g[u].length; w++) {
                int v = g[u][w];
                if (color[u] == color[v]) return false;
                else if (!visit[v]) {
                    visit[v] = true;
                    color[v] = 3 - color[u];
                    q.add(v);
                }
            }
        }
        return true;
    }

    //````````````````````````````````````````````````````````````````````````````
    int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};// trick to explore an implicit 2D grid
    int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};// S,SE,E,NE,N,NW,W,SW neighbors

    char grid[][];
    int R, C;

    int floodfill(int r, int c, char c1, char c2) {// returns the size of CC
        if (r < 0 || r >= R || c < 0 || c >= C) return 0;// outside grid
        if (grid[r][c] != c1) return 0;// does not have color c1
        int ans = 1;// adds 1 to ans because vertex (r, c) has c1 as its color
        grid[r][c] = c2;// now recolors vertex (r, c) to c2 to avoid cycling!
        for (int d = 0; d < 8; d++)
            ans += floodfill(r + dr[d], c + dc[d], c1, c2);
        return ans;// the code is neat due to dr[] and dc[]
    }

    //``````````````````````````````````````````````````````````````````````````````
    public static void main(String[] args) {
    }
}
