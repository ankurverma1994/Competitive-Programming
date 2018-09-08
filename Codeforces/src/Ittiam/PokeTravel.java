package Ittiam;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class PokeTravel {
    final static long inf = Long.MAX_VALUE / 2;
    static int g[][][];

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int des = ii(), k = ii();
        boolean used[] = new boolean[1000001];
        used[0] = used[des] = true;
        HashMap<Edge, Integer> graph = new HashMap<>();
        for (int i = 0; i < k; i++) {
            int u = ii(), v = ii(), w = ii();
            if (u > des || v > des) continue;
            used[u] = used[v] = true;
            Edge e = new Edge(u, v);
            if (graph.containsKey(e)) graph.put(e, Math.min(graph.get(e), w));
            else graph.put(e, w);
        }
        int V[] = vertices(used);
        int n = 0;
        for (int v : V) n = Math.max(n, v + 1);
        for (int i = 0; i < V.length; i++) {
            for (int j = i + 1; j < V.length; j++) {
                int u = V[i], v = V[j], w = Math.abs(V[j] - V[i]);
//                out.println(u + " " + v + " " + w);
                Edge e = new Edge(u, v);
                if (graph.containsKey(e)) graph.put(e, Math.min(graph.get(e), w));
                else graph.put(e, w);
            }
        }
        int m = graph.size();
        int from[] = new int[m];
        int to[] = new int[m];
        int w[] = new int[m];
        int c = 0;
        for (Map.Entry<Edge, Integer> es : graph.entrySet()) {
            Edge e = es.getKey();
            int we = es.getValue();
            int u = e.from, v = e.to;
            from[c] = u;
            to[c] = v;
            w[c++] = we;
        }
//        for (int i = 0; i < m; i++) {
//            out.println(from[i] + " " + to[i] + " " + w[i]);
//        }
        g = packWD(n, from, to, w);
        long d[] = Dij(0, g);
        out.println(d[des]);
    }

    public static long[] Dij(int s, int g[][][]) {
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

    public static int[] vertices(boolean visit[]) {
        int a[] = new int[visit.length];
        int len = 0;
        for (int i = 0; i < visit.length; i++)
            if (visit[i])
                a[len++] = i;
        return Arrays.copyOf(a, len);
    }

    class Edge {
        int from, to;

        Edge(int x, int y) {
            from = x;
            to = y;
        }

        @Override
        public boolean equals(Object o) {
            Edge obj = (Edge) o;
            if (obj.from == from && obj.to == to)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return new Integer(from).hashCode() * 31 + new Integer(to).hashCode();
        }
    }

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

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new PokeTravel().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
        return inbuffer[ptrbuffer++];
    }

    int ii() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
}
