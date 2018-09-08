package FacebookHackerCup2016;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ThePriceIsCorrect {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            int n = ii(), p = ii(), a[] = iia(n);
            long cum[] = new long[n];
            cum[0] = a[0];
            for (int i = 1; i < n; i++)
                cum[i] = cum[i - 1] + a[i];
            long ans = 0;
            for (int i = 0; i < n; i++) {
                if (p < a[i])
                    continue;
                long key = cum[i] + p - a[i];
                int j = BinarySearchUpperBound(cum, key);
                ans += (j < 0 ? 0 : (j - i + 1));
            }
            out.print("Case #" + tc + ": " + ans);
            if (tc != test)
                out.println();
        }
    }

    public static int BinarySearchUpperBound(long[] a, long v) {
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
        new Thread(null, () -> {
            try {
                new ThePriceIsCorrect().main1();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter("/home/ankurverma1994/output1.txt");
//        out=new PrintWriter(System.out);
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input1.txt") : new ByteArrayInputStream(check.getBytes());
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