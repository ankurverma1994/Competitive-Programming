package LunchTimeFEB16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ProbB {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), k = ii();
            char a[][] = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = ic();
            }

            boolean ans = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == '.') {
                        char b[][] = copy(a);
                        b[i][j] = 'X';
                        ans = win(b, n, k);
                        if (ans) break;
                    }
                    if (ans) break;
                }
                if (ans) break;
            }
            out.println(ans ? "YES" : "NO");
        }
//        int n = ii(), k = ii();
//        for (int line = 1; line <= (n + n - 1); line++) {
//            int start_col = Math.max(0, line - n);
//            int count = Math.min(line, Math.min((n - start_col), n));
//            for (int j = 0; j < count; j++)
//                out.print((Math.min(n, line) - j - 1) + " " + (start_col + j) + "  ");
//            out.println();
//        }
//        win(ii(),ii());
    }

    boolean win(char a[][], int n, int k) {
        // row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'X') {
                    count++;
                    if (count >= k) return true;
                } else {
                    count = 0;
                }
            }
            if (count >= k) return true;//                out.println();
        }
        // column
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (a[i][j] == 'X') {
                    count++;
                    if (count >= k) return true;
                } else {
                    count = 0;
                }
            }
            if (count >= k) return true;
        }
        // left diag
        for (int line = 0; line <= 2 * n - 1; line++) {
            int i = 0;
            int count = 0;
            for (int j = line; j >= 0; j--) {
                if (i < n && j < n) {
                    if (a[i][j] == 'X') {
                        count++;
                        if (count >= k) return true;
                    } else {
                        count = 0;
                    }
                }
                i++;
            }
            if (count >= k) return true;
        }
        // right diag
        for (int line = 0; line <= 2 * n - 1; line++) {
            int j = n - 1, count = 0;
            for (int i = line; i >= 0; i--) {
                if (i >= 0 && j >= 0 && i < n && j < n) {
                    if (a[i][j] == 'X') {
                        count++;
                        if (count >= k) return true;
                    } else {
                        count = 0;
                    }
                }
                j--;
            }
            if (count >= k) return true;
        }

        return false;
    }

    char[][] copy(char a[][]) {
        int n = a.length;
        char b[][] = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                b[i][j] = a[i][j];
        return b;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbB().main1();
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