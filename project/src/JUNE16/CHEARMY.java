package JUNE16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class CHEARMY {
    //    https://oeis.org/A014263
    final double log5 = Math.log(5);
    long pow5[], pow10[];

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
     /*   for (int i = 0; i <= 200; i++) {
            boolean b = false;
            if(i>=100)
                b = magic(3, new int[]{Integer.parseInt(String.valueOf(i).substring(0, 1)), Integer.parseInt(String.valueOf(i).substring(1,2)),Integer.parseInt(String.valueOf(i).substring(2))});
            else if (i >= 10)
                b = magic(2, new int[]{Integer.parseInt(String.valueOf(i).substring(0, 1)), Integer.parseInt(String.valueOf(i).substring(1))});
            else b = magic(1, new int[]{i});
            if (b)
                out.println(i);
        }
        */
        pow5 = new long[25];
        pow10 = new long[20];
        pow5[0] = 1;
        pow10[0] = 1;
        for (int i = 1; i <= 24; i++)
            pow5[i] = 5L * pow5[i - 1];
        for (int i = 1; i <= 19; i++) {
            pow10[i] = 10L * pow10[i - 1];
        }
        for (int tc = ii(); tc > 0; tc--) {
//        for (long n = 2; n <= 20; n++) {
            long n = il();
            if (n == 1) {
                out.println("0");
                continue;
            }
            long m = (long) Math.floor(Math.log(n - 1) / log5);
            long ans = ((2 * b(m, n, m)) % 8 + 2) * pow10[(int) m];
            for (int j = 0; j < m; j++)
                ans += ((2 * b(j, n, m) % 10) * pow10[j]);
            out.println(ans);
        }
    }

    long b(long j, long n, long m) {
        long ans = (long) Math.floor((n - 1 - pow5[(int) m]) / (double) pow5[(int) j]);
        return ans;
    }

    boolean magic(int n, int c[]) {
        long sum = 0;
        for (int counter = 0; counter < (1 << n); counter++) {
            int ans = 1;
            boolean notempty = false;
            for (int j = 0; j < n; j++) {
                if ((counter & (1 << j)) > 0) {
                    notempty = true;
                    ans = ans * c[j];
                }
            }
            if (notempty)
                sum += ans;
        }
        return sum % 2 == 0;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CHEARMY().main1();
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
