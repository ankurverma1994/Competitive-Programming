package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 * 1
 * 8 4
 * 1 1 2 3 5 5 6 7
 * ans = 4 not 5
 */

import java.io.*;
import java.util.*;

class ALEXROSE {
    final int max = (int) 1e6;

    //------------> Solution starts here!!
    void solve() {
        for (int tc = 1; tc > 0; tc--) {
            int n = ii(), k = ii();
//            Random rnd = new Random();
//            int n = rnd.nextInt(12) + 1;
//            int k;
//            do {
//                k = rnd.nextInt(n) + 1;
//            } while (n % k != 0);
//            out.println(n + " " + k);
//            long b[] = new long[n];
//            for (int i = 0; i < n; i++) {
//                b[i] = rnd.nextInt(50) + 1;
////                out.print(b[i] + " ");
//            }
//            out.println();
            long b[] = ila(n);
            int a[] = shrink(b);
            Arrays.sort(b);
            out.println(Arrays.toString(b));
            int f[] = new int[max + 1];
            for (int i : a) f[i]++;
            for (int i = 0; i <= max; i++) f[i] %= k;
            int start = 0, end = max;
            int ans = 0;
            while (start < end) {
                while (start < max && f[start] == 0) start++;
                while (end > 0 && f[end] == 0) end--;
                if (start == max || end == 0) break;
//                System.out.println(start + " " + end);
                f[start]++;
                f[start] %= k;
                f[end]--;
                f[end] %= k;
                ans++;
            }
            out.println(ans);
        }
    }

    public static int[] shrink(long[] a) {
        /*  Code picked up from "uwi" submissions */
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++)
            b[i] = (long) a[i] << 32 | i;
        Arrays.sort(b);
        int[] ret = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (b[i] ^ b[i - 1]) >> 32 != 0)
                p++;
            ret[(int) b[i]] = p;
        }
        return ret;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ALEXROSE().main1();
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
