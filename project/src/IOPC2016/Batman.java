package IOPC2016;
/**
 * Created by ankurverma1994
 */


import java.io.*;
import java.util.*;

class Batman {
    int n;
    int dis[];
    int cache[][];
    int W[];

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            int m = ii(), d = ii();
            int from[] = new int[m];
            int to[] = new int[m];
            int w[] = new int[m];
            for (int i = 0; i < m; i++) {
                from[i] = ii();
                to[i] = ii();
                w[i] = 2 * ii();
            }
            int g[][][] = packWU(n, from, to, w);
            W = new int[n];
            for (int i = 1; i < n; i++) W[i] = ii();
            dis = Dijkstra(0, g);
//            out.println(Arrays.toString(dis));
            pre(n, d);
            out.println(solve(0, d));
        }
    }

    // for undirected weighted graph
    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
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

    void pre(int n, int d) {
        cache = new int[n][d + 10];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < d + 10; j++)
                cache[i][j] = -1;
    }

    int solve(int i, int rem) {
        if (rem < 0 || i >= n)
            return 0;
        if (cache[i][rem] != -1)
            return cache[i][rem];
        if (dis[i] > rem)
            return cache[i][rem] = solve(i + 1, rem);
        return cache[i][rem] = Math.max(W[i] + solve(i + 1, rem - dis[i]), solve(i + 1, rem));
    }

    static public int[] Dijkstra(int s, int g[][][]) {
        int inf = Integer.MAX_VALUE - 10;
        int n = g.length;
        int dis[] = new int[n];
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
                    int w = g[u][i][1];
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
        int dist;

        public Node(int i, int j) {
            x = i;
            dist = j;
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Batman().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
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