package aaa;

class SqrtDecTree {

    static class SqrtLCA {
        // O(N) precomputation
        // O(sqrt(H)) query.. H--> max height of tree
        // nr = sqrt(N), N--> no. of nodes in tree
        // P--> uss substree ka root find karega
        // T--> father/parent of node precompute
        //root --> 1
        // L --> level of tree precompute
        int P[], L[], T[];

        void dfs1(int g[][], int node, int level) {
            L[node] = level;
            for (int i = 0; i < g[node].length; i++) {
                int v = g[node][i];
                if (v != node) {
                    T[v] = node;
                    dfs1(g, v, level + 1);
                }
            }
        }

        void dfs2(int node, int nr, int g[][]) {

            if (L[node] < nr)
                P[node] = 1;
            else if (L[node] % nr == 0)
                P[node] = T[node];
            else
                P[node] = P[T[node]];
            for (int i = 0; i < g[node].length; i++) {
                int v = g[node][i];
                if (v != node)
                    dfs2(v, nr, g);
            }
        }

        int lca(int x, int y) {
            while (P[x] != P[y]) {
                if (L[x] > L[y])
                    x = P[x];
                else
                    y = P[y];
            }
            while (x != y) {
                if (L[x] > L[y])
                    x = T[x];
                else
                    y = T[y];
            }
            return x;
        }
    }
}
