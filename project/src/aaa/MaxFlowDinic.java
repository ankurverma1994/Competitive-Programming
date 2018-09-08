package aaa;

import java.util.Arrays;
import java.util.List;

class MaxFlowDinic {

// complexity O(V^2.E)
    public static class Edge {
        /* This part of code is picked up from "uwi" previous submission */
        public int from, to;
        public int capacity;
        public int flow;
        public Edge complement;
        // public int iniflow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    public static Edge[][] compileWD(int n, List<Edge> edges) {
        /* This part of code is picked up from "uwi" previous submission */
        Edge[][] g = new Edge[n][];
        // cloning
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity);
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            edges.add(clone);
        }

        int[] p = new int[n];
        for (Edge e : edges) p[e.from]++;
        for (int i = 0; i < n; i++) g[i] = new Edge[p[i]];
        for (Edge e : edges) g[e.from][--p[e.from]] = e;
        return g;
    }

    public static Edge[][] compileWU(int n, List<Edge> edges) {
        /* This part of code is picked up from "uwi" previous submission */
        Edge[][] g = new Edge[n][];
        // cloning
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity * 2);
            origin.flow = origin.capacity;
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            origin.capacity *= 2;
            edges.add(clone);
        }

        int[] p = new int[n];
        for (Edge e : edges) p[e.from]++;
        for (int i = 0; i < n; i++) g[i] = new Edge[p[i]];
        for (Edge e : edges) g[e.from][--p[e.from]] = e;
        return g;
    }

    public static long maximumFlowDinic(Edge[][] g, int source, int sink) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] d = new int[n];
        int[] q = new int[n];
        long ret = 0;
        while (true) {
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;

            for (int v = 0; v < r; v++) {
                int cur = q[v];
                for (Edge ne : g[cur]) {
                    if (d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if (d[sink] == -1) break;
            int[] sp = new int[n];
            for (int i = 0; i < n; i++) sp[i] = g[i].length - 1;
            ret += dfsDinic(d, g, sp, source, sink, Long.MAX_VALUE);
        }

        return ret;
    }

    private static long dfsDinic(int[] d, Edge[][] g, int[] sp, int cur, int sink, long min) {
        /* This part of code is picked up from "uwi" previous submission */
        if (cur == sink) return min;
        long left = min;
        for (int i = sp[cur]; i >= 0; i--) {
            Edge ne = g[cur][i];
            if (d[ne.to] == d[cur] + 1 && ne.capacity - ne.flow > 0) {
                long fl = dfsDinic(d, g, sp, ne.to, sink, Math.min(left, ne.capacity - ne.flow));
                if (fl > 0) {
                    left -= fl;
                    ne.flow += fl;
                    ne.complement.flow -= fl;
                    if (left == 0) {
                        sp[cur] = i;
                        return min;
                    }
                }
            }
        }
        sp[cur] = -1;
        return min - left;
    }

    public static long maximumFlowDinicNoRec(Edge[][] g, int source, int sink) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] d = new int[n]; // distance
        int[] q = new int[n]; // queue for BFS
        long ret = 0;
        int[] stack = new int[n];
        long[] lefts = new long[n]; // left to flow
        long[] parflow = new long[n];
        int[] probe = new int[n]; // search pointer
        while (true) {
            // BFS
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;
            for (int v = 0; v < r; v++) {
                int cur = q[v];
                for (Edge ne : g[cur]) {
                    if (d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if (d[sink] == -1) break;

            // DFS
            for (int i = 0; i < n; i++) probe[i] = g[i].length;
            int sp = 0;
            stack[sp] = source;
            lefts[sp] = Long.MAX_VALUE;
            parflow[sp] = 0;
            sp++;
            long delta = 0;
            boolean down = true;
            while (sp > 0) {
                int cur = stack[sp - 1];
                long fl = lefts[sp - 1];
                if (cur == sink) {
                    delta += fl;
                    parflow[sp - 1] = fl;
                    sp--;
                    down = false;
                    continue;
                }
                if (!down && parflow[sp] > 0) {
                    lefts[sp - 1] -= parflow[sp];
                    fl = lefts[sp - 1];
                    Edge ne = g[cur][probe[cur]];
                    ne.flow += parflow[sp];
                    ne.complement.flow -= parflow[sp];
                    parflow[sp - 1] += parflow[sp];
                }
                if (fl > 0 && probe[cur] > 0) {
                    down = true;
                    Edge ne = g[cur][--probe[cur]];
                    if (d[ne.to] == d[cur] + 1 && ne.capacity - ne.flow > 0) {
                        lefts[sp] = Math.min(lefts[sp - 1], ne.capacity - ne.flow);
                        stack[sp] = ne.to;
                        parflow[sp] = 0;
                        sp++;
                    }
                } else {
                    down = false;
                    sp--;
                }
            }
            ret += delta;
        }
        return ret;
    }
}