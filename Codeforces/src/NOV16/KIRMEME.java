package NOV16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class KIRMEME {

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            l = ii();
            r = ii();
            a = iia(n);
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = ii() - 1;
                to[i] = ii() - 1;
            }
            g = packU(n, from, to, n - 1);
            ans = 0;
            for (int i = 0; i < n; i++)
                dfs(i, -1, Integer.MAX_VALUE / 2, 0);
            out.println(ans / 2);
        }
    }

    int n, l, r, a[], g[][], ans;

    void dfs(int curr, int par, int last, int count) {
        int temp;
        for (int next : g[curr]) {
            if (next == par) continue;
            if (last < a[curr] && a[curr] > a[next]) temp = count + 1;
            else temp = count;
            if (l <= temp && temp <= r) ans++;
            dfs(next, curr, a[curr], temp);
        }
    }

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


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new KIRMEME().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
