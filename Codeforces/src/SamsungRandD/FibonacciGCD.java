package SamsungRandD;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class FibonacciGCD {
    final int mod = (int) 1e9 + 7;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), q = ii(), a[] = iia(n);
        SegmentTree tree = new SegmentTree(a, n);
        for (int Q = 0; Q < q; Q++) {
            int l = ii() - 1, r = ii() - 1;
            int g = tree.query(l, r);
            out.println(fib(g));
        }
    }

    long fib(int x) {
        if (x == 1 || x == 2) return 1;
        long M[][] = {
                {1, 1},
                {1, 0}
        };
        M = ModPow(M, x - 2);
        return (M[0][0] + M[0][1]) % mod;
    }

    // Nth power of Matrix with mod in O(log n)
    long[][] ModPow(long M[][], int exp) {
        long result[][] = {
                {1, 0},
                {0, 1}
        };
        long pow[][] = M;
        while (exp != 0) {
            if ((exp & 1) == 1)
                result = MulMatrix(result, pow);
            exp >>= 1;
            pow = MulMatrix(pow, pow);
        }
        return result;
    }

    // Multiplication of Matrix in O(k^3) where k is size of matrix
    long[][] MulMatrix(long A[][], long B[][]) {
        long C[][] = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                long value = 0;
                for (int k = 0; k < 2; k++) {
                    value += A[i][k] * B[k][j];
                }
                C[i][j] = value % mod;
            }
        }
        return C;
    }

    class SegmentTree {
        int tree[];
        int a[];
        int n;

        int operation(int a, int b) {
            return gcd(a, b);
        }

        int gcd(int a, int b) {
            while (b > 0) {
                int c = a;
                a = b;
                b = c % b;
            }
            return a;
        }

        SegmentTree(int a[], int n) {
            tree = new int[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        //  TODO check this function
        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        int query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        int get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
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
                    new FibonacciGCD().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
