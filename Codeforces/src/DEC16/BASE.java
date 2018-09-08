package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 * http://oeis.org/A216411
 */

import java.io.*;
import java.util.*;

class BASE {

    //------------> Solution starts here!!
    void solve() {
        int T = ii();
        long n[] = ila(T);
        long max = 0;
        for (long x : n) {
            max = Math.max(max, x);
        }
        TreeMap<Long, Long> cum = new TreeMap<>();
        for (int i = 3; i <= 1000000; i++) {
            long lower = 1, upper;
            while (true) {
                lower *= i;
                upper = 2 * lower - 1;
                if (lower > max) break;
                if (upper > max) upper = max + 1;
                if (!cum.containsKey(lower)) cum.put(lower, 0L);
                if (!cum.containsKey(upper + 1)) cum.put((upper + 1), 0L);

                cum.put(lower, cum.get(lower) + 1);
                cum.put((upper + 1), cum.get((upper + 1)) - 1);
            }
        }
        long key[] = cum.keySet().stream().mapToLong(l -> l).toArray();
        long val[] = cum.values().stream().mapToLong(l -> l).toArray();
        for (int i = 1; i < val.length; i++) {
            val[i] += val[i - 1];
        }
        for (long x : n) {
            if (x == 0) {
                out.println(0);
                continue;
            }
            if (x == 1) {
                out.println("INFINITY");
                continue;
            }
            if (x == 2) {
                out.println(1);
                continue;
            }
            long ret = 1;// 1 for base 2
            int i = BinarySearchUpperBound(key, x);

            if (x <= 1000000)
                out.println(ret + val[i]);
            else {
                out.println(ret + val[i] + (x + 1) / 2 - Math.max(0, 1000000 - x / 2));
            }
        }
    }

    public int BinarySearchUpperBound(long[] a, long v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new BASE().main1();
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

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }
}
