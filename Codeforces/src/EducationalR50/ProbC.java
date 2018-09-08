package EducationalR50;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            long start = il();
            long num = il();
            if (start == 1)
                out.println(final_count(num));
            else
                out.println(final_count(num) - final_count(start - 1));
//            out.println(brute_force(start, num));
        }
    }

    long brute_force(long start, long num) {
        int c = 0;
        for (long i = start; i <= num; i++) {
            char x[] = String.valueOf(i).toCharArray();
            int non_zero = 0;
            for (char z : x) {
                if (z != '0')
                    non_zero++;
            }
            if (non_zero <= 3)
                c++;
        }
        return c;
    }

    long final_count(long num) {
        String s = String.valueOf(num);
        int N = s.length();
        long ans = 0;
        for (int i = 1; i < N; i++) {
            n = i;
            ans += (9 * count(1, 2));
        }
        n = N;
        x = s.toCharArray();
        ans += (((x[0] - '0') - 1) * count(1, 2));
//        out.println(ans);
        ans += countN(1, 2, true); // fixed first digit
        return ans;
    }

    int n;

    long count(int i, int non_zero) {
        if (i >= n)
            return 1;
        if (non_zero > 0) {
            return 9 * count(i + 1, non_zero - 1) + count(i + 1, non_zero);
        } else {
            return count(i + 1, non_zero);
        }
    }

    char x[];

    long countN(int i, int non_zero, boolean last) {
        if (i >= n)
            return 1;
        if (non_zero > 0) {
            long ans = 0;
            if (last) {
                if (x[i] == '0')
                    ans += countN(i + 1, non_zero, last);
                else {
                    ans += countN(i + 1, non_zero, false); // 0
                    ans += (x[i] - '0' - 1) * countN(i + 1, non_zero - 1, false); //0 to x[i]-1
                    ans += countN(i + 1, non_zero - 1, last); // x[i]
                }
            } else {
                ans += 9 * countN(i + 1, non_zero - 1, last);
                ans += countN(i + 1, non_zero, last);
            }
            return ans;
        } else {
            return countN(i + 1, non_zero, last);
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
                    new ProbC().main1();
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
