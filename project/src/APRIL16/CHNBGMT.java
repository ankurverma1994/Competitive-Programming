package APRIL16;

import java.io.*;
import java.util.*;
import java.math.*;

class CHNBGMT {
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            m = ii();
//            c = ii();
//            d = ii();
//            mod = ii();
            c = 0;
            d = 20;
            mod = (int) 1e9 + 7;
            a = new boolean[n][m];
            for (int i = 0; i < c; i++) {
                a[ii() - 1][ii() - 1] = true;
            }
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++)
//                    out.print(a[i][j] + " ");
//                out.println();
//            }
//            out.println((solve(0, 0, 0, 0, 0) * inverse(2, mod)) % mod);
            out.println((solve(0, 0, 0, 0, 0) / 2) % mod);
//            out.println(solve(1, 2, 2, 1));
        }
    }

    boolean a[][];
    int n, m, c, d, mod;

    long solve(int ax, int ay, int bx, int by, int carrots) {
//        out.print(ax + " " + ay + "     " + bx + " " + by + "    ");
        if (ax >= n || bx >= n || ay >= m || by >= m) {
//            out.println("0 a");
            return 0;
        }
        if (ax == bx && ay == by) { //ending
            if (ax == n - 1 && ay == m - 1) {
//                out.println("1  b");
                return 1;
            }
            if (!(ax == 0 && ay == 0)) { //not starting
//                out.println("0 c");
                return 0;
            }
        }
        if (a[ax][ay])
            carrots++;
        if (a[bx][by])
            carrots++;
        if (carrots > d) {
//            out.println("0  d");
            return 0;
        }
//        if (ax == bx && ay == by)
//            return (solve(ax + 1, ay, bx, by + 1, carrots) + solve(ax, ay + 1, bx + 1, by, carrots)) % mod;
        long ans = (solve(ax + 1, ay, bx + 1, by, carrots) +
                solve(ax + 1, ay, bx, by + 1, carrots) +
                solve(ax, ay + 1, bx + 1, by, carrots) +
                solve(ax, ay + 1, bx, by + 1, carrots));
//        out.println(ans + " e");
        return ans;
    }

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CHNBGMT().main1();
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
