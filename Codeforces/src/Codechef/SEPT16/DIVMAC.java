package Codechef.SEPT16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */
/*
    Ismei lazy use nhi hua hai. Har ek update mei number ko uske lowest prime factor se divide karna hai.
    Matlab kuch steps k baad number 1 ho jayega. Uske baad divide karne par koi change nhi aayega
    Toh fir jahan 1 milega update karne ko toh utni segment hm skip kar sakte hai :-)
     */

import java.io.*;
import java.util.*;
import java.math.*;

public class DIVMAC {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        smallDivisor = enumLowestPrimeFactors(1000000);
        for (int i = 0; i < smallDivisor.length; i++) {
            if (smallDivisor[i] == 0)
                smallDivisor[i] = 1;
        }
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), a[] = iia(n);
            SegmentTree tree = new SegmentTree(a, n);

            for (int Q = 0; Q < m; Q++) {
                int type = ii(), l = ii() - 1, r = ii() - 1;

                if (type == 1) out.print(tree.query(l, r).divisor + " ");
                else tree.update(l, r);

            }

            out.println();
        }
    }


    int smallDivisor[];

    class Data {
        int num, divisor;
    }

    class SegmentTree {
        Data tree[];
        int a[];
        int n;

        Data operation(Data l, Data r) {
            Data res = new Data();
            res.divisor = Math.max(l.divisor, r.divisor);
            if (l.divisor > r.divisor) {
                res.num = l.num;
            } else {
                res.num = r.num;
            }
            return res;
        }

        Data makeData(int v) {
            Data res = new Data();
            res.num = v;
            res.divisor = smallDivisor[v];
            return res;
        }

        SegmentTree(int a[], int n) {
            tree = new Data[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = makeData(a[s]);
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        Data query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        Data get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }

        void update(int l, int r) {
            put(0, n - 1, 0, l, r);
        }

        void put(int s, int e, int c, int l, int r) {
            if (tree[c].num == 1) return;
            if (s == e) {
                tree[c].num = tree[c].num / tree[c].divisor;
                tree[c].divisor = smallDivisor[tree[c].num];
                return;
            }
            int m = (s + e) >> 1;
            if (r <= m) put(s, m, 2 * c + 1, l, r);
            else if (l > m) put(m + 1, e, 2 * c + 2, l, r);
            else {
                put(s, m, 2 * c + 1, l, m);
                put(m + 1, e, 2 * c + 2, m + 1, r);
            }
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }
    }

    public static int[] enumLowestPrimeFactors(int n) {
        /*  Code picked up from "uwi" submissions */
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++) lpf[i] = i;
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p) primes[tot++] = p;
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p] && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new DIVMAC().main1();
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
