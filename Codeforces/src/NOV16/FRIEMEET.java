package NOV16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class FRIEMEET {

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            this.n = n;
            this.m = m;
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int w[] = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = ii() - 1;
                to[i] = ii() - 1;
                w[i] = ii();
            }
            g = packWU(n, from, to, w);
            can = new boolean[n];
            subtree = new int[n];
            ans = 0;
            for (int i = 0; i < m; i++) can[ii() - 1] = true;
            dfs1(0, -1);
            dfs2(0, -1);
            long num = 2 * ans, den = 1L * m * m;
            long gcd = gcd(num, den);
            num /= gcd;
            den /= gcd;
            out.println(num + " " + den);
        }
    }

    int g[][][], subtree[], n, m;
    boolean can[];
    long ans;

    int dfs1(int curr, int par) {
        int sum = can[curr] ? 1 : 0;
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            if (next == par) continue;
            sum += dfs1(next, curr);
        }
        return subtree[curr] = sum;
    }

    void dfs2(int curr, int par) {
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0], w = g[curr][i][1];
            if (next == par) continue;
            ans += (1L * subtree[next] * (m - subtree[next]) * w);
            dfs2(next, curr);
        }
    }

    long gcd(long a, long b) {
        while (b > 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

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


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new FRIEMEET().main1();
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
