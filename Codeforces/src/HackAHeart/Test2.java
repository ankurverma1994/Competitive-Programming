package HackAHeart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Test2 {

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    String args[];
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    int n = Integer.parseInt(br.readLine());
                    args = br.readLine().split(" ");
                    int arr[] = new int[n];
                    for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(args[i]);
                    ArrayList<Integer> g[] = new ArrayList[n];
                    for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
                    args = br.readLine().split(" ");
                    bada = new int[n];
                    for (int i = 0; i < n - 1; i++) {
                        int x = Integer.parseInt(args[i]) - 1;
                        int y = i + 1;
                        g[x].add(y);
                        g[y].add(x);
                    }
                    searchHackerEarth(0, -1, arr[0], g, arr, 0);
                    int q = Integer.parseInt(br.readLine());
                    LcaSparseTable lca = new LcaSparseTable(g, 0);
                    while (q-- > 0) {
                        args = br.readLine().split(" ");
                        int x = Integer.parseInt(args[0]);
                        int y = Integer.parseInt(args[1]);
                        System.out.println(bada[lca.lca(x - 1, y - 1)]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 20).start();
    }

    public static int bada[];

    public static void searchHackerEarth(int node, int prev, int badhiya, ArrayList<Integer> g[], int arr[], int index) {
        bada[node] = index + 1;
        for (int i = 0; i < g[node].size(); i++) {
            int v = g[node].get(i);
            if (v != prev) {
                if (arr[v] > badhiya) {
                    badhiya = arr[v];
                    index = v;
                }
                searchHackerEarth(v, node, badhiya, g, arr, index);
            }
        }
    }

    public static class LcaSparseTable {

        int len;
        int[][] up;
        int[] tin;
        int[] tout;
        int time;

        void dfs(ArrayList<Integer>[] tree, int u, int p) {
            tin[u] = time++;
            up[0][u] = p;
            for (int i = 1; i < len; i++)
                up[i][u] = up[i - 1][up[i - 1][u]];
            for (int v : tree[u])
                if (v != p)
                    dfs(tree, v, u);
            tout[u] = time++;
        }

        public LcaSparseTable(ArrayList<Integer>[] tree, int root) {
            int n = tree.length;
            len = 1;
            while ((1 << len) <= n) ++len;
            up = new int[len][n];
            tin = new int[n];
            tout = new int[n];
            dfs(tree, root, root);
        }

        boolean isParent(int parent, int child) {
            return tin[parent] <= tin[child] && tout[child] <= tout[parent];
        }

        public int lca(int a, int b) {
            if (isParent(a, b))
                return a;
            if (isParent(b, a))
                return b;
            for (int i = len - 1; i >= 0; i--)
                if (!isParent(up[i][a], b))
                    a = up[i][a];
            return up[0][a];
        }


    }
}