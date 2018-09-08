package aaa;

import java.util.Arrays;

class Cycles {
    public static void main(String[] args) {
        GraphResult rc = cluster(new int[]{1, 2, 0, 4, 5, 6, 3, 8, 9, 10, 11, 10});
        for (int cy : rc.componentNumber) System.out.println(cy);
        for (int cy[] : rc.cycles) System.out.println(Arrays.toString(cy));
    }

    public static GraphResult cluster(int[] f) {
        /* This part of code is picked up from "uwi" previous submission */
        //directed graph from--> i to--> f[i]
        int n = f.length;
        DJSet ds = new DJSet(n);
        int[] red = new int[n + 1];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (ds.union(i, f[i])) red[p++] = f[i];
        }
        int[] compon = new int[n];
        Arrays.fill(compon, -1);
        int compIDGenerator = 0;
        for (int i = 0; i < n; i++) if (ds.upper[i] < 0) compon[i] = compIDGenerator++;
        for (int i = 0; i < n; i++) compon[i] = compon[ds.root(i)];

        int[][] cycles = new int[p][];
        int[] temp = new int[n + 1];
        for (int i = 0; i < p; i++) {
            temp[0] = red[i];
            int j = 1;
            for (; ; j++) {
                temp[j] = f[temp[j - 1]];
                if (temp[j] == temp[0]) break;
            }
            cycles[compon[red[i]]] = Arrays.copyOf(temp, j);
        }
        GraphResult rc = new GraphResult();
        rc.componentNumber = compon;
        rc.cycles = cycles;
        return rc;
    }

    public static class GraphResult {
        int[] componentNumber;
        int[][] cycles;
        // cycles[0]--> list of all vertices in this cycle
    }

    public static class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }

        public int count() {
            int ct = 0;
            for (int u : upper)
                if (u < 0)
                    ct++;
            return ct;
        }
    }
}