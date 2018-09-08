package hackerearthCollegiateCupSecondElimination;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Birthday {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = n - 1;
        int from[] = new int[m];
        int to[] = new int[m];
        int w[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
            w[i] = ii();
        }
        g = packWU(n, from, to, w);
        d = new long[n];
        visit = new boolean[n];
        Arrays.fill(d, 1);
        dfs(0, 1);
        out.println(Arrays.toString(d));
        cum = new long[n];
        visit = new boolean[n];
        dfs1(0);
        out.println(Arrays.toString(cum));
        visit = new boolean[n];
        dfs2(0);
        out.println(ANS);
    }

    void dfs(int curr, long dis) {
        visit[curr] = true;
        d[curr] = dis;
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            int w = g[curr][i][1];
            if (!visit[next]) {
                dfs(next, (dis * w) % mod);
            }
        }
    }

    long dfs1(int curr) {
        visit[curr] = true;
        long ans = d[curr];
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            if (!visit[next]) {
                ans = (ans + dfs1(next)) % mod;
            }
        }
        cum[curr] = ans;
        return ans;
    }

    long ANS = 0;

    void dfs2(int curr) {
        visit[curr] = true;
        long S = cum[curr] - d[curr];
        if (S < 0) S += mod;
//        out.println(S);
        long ans = 0;
        for (int i = 0; i < g[curr].length; i++) {
            if (S == 0) continue;
            int next = g[curr][i][0];
            S = (S - cum[next]) % mod;
            out.println("S= " + curr + " " + S);
            if (S < 0) S += mod;
            long temp = ((S + d[curr]) % mod) * (cum[next] + d[curr]) % mod;
            temp = (temp - (d[curr] * d[curr]) % temp) % temp;
            temp %= mod;
            out.println(curr + " " + temp);
            ans += temp;
            ans %= mod;
        }
        long in = inversemodp(d[curr], mod);
        ans = (ans * in) % mod;
        ans = (ans * in) % mod;
        ANS = (ANS + ans) % mod;
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            if (!visit[next])
                dfs2(next);
        }
    }

    // Complexity O(log n)
    public static long modpow(long base, int exp, int mod) {
        base %= mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // inverse modulo prime number
    // a^(-1) mod pie = a^(pie-2) mod pie
    public static long inversemodp(long base, int mod) {
        return modpow(base, mod - 2, mod);
    }


    int g[][][];
    long d[];
    long cum[];
    boolean visit[];

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


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Birthday().main1();
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

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
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

    long il() {
        long num = 0;
        int b;
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
