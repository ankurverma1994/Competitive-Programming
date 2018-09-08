package DirectI;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class MINMAXTF {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            m = ii();
            c = iia(n);
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += c[i];
            }
            out.println(min_time(c, n, m));
        }
    }

    int min_time(int A[], int size, int k) {
        int sum = 0;
        for (int i = 0; i < size; i++)
            sum += A[i];
        int lo = 1;
        int hi = sum;
        boolean ans = false;
        while (lo < hi) {
            int t = 1;
            int mid = (lo + hi) / 2;
            sum = 0;
            for (int i = 0; i < size; i++) {
                sum += A[i];
                if (sum > mid) {
                    i--;
                    t++;
                    sum = 0;
                }
            }
            if (t > k)
                lo = mid + 1;
            else if (t < k)
                hi = mid;
            else if (t == k) {
                hi = mid;
                ans = true;
                break;
            }
        }
        if (ans)
            return hi;
        return -1;
    }

    int c[], n, m;

//    public static int BinarySearchUpperBound(int[] a, long v) {
//        long low = 1, high = v;
//        while (high - low > 1) {
//            long h = high + low >>> 1;
//            if (a[h] <= v) {
//                low = h;
//            } else {
//                high = h;
//            }
//        }
//        return low;
//    }

//    public static int BinarySearchLowerBound(int[] a, long v) {
//        long low = 1, high = v;
//        while (high - low > 1) {
//            long h = high + low >>> 1;
//            if (a[h] >= v) {
//                high = h;
//            } else {
//                low = h;
//            }
//        }
//        return high;
//    }

//    boolean place(long mid) {
//        long sum = 0;
//        int t = 1;
//        for (int i = 0; i < n; i++) {
//            sum += c[i];
//            if (sum > mid) {
//                i--;
//                t++;
//                sum = 0;
//            }
//        }
//        return t;
//    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new MINMAXTF().main1();
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
