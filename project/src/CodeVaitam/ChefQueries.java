package CodeVaitam;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ChefQueries {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int l = ii(), r = ii(), k = ii();
        char a[][] = icm(l, r);
        int male = MaximumSizeSquareSubMatrix(newMatrix(a, 'M'));
        int female = MaximumSizeSquareSubMatrix(newMatrix(a, 'F'));
//        out.println(male + " " + female);
        for (int Q = 0; Q < k; Q++) {
            int n = ii();
            char c = ic();
            if (c == 'M')
                out.println(n <= male ? "yes" : "no");
            else
                out.println(n <= female ? "yes" : "no");
        }
    }

    int[][] newMatrix(char a[][], char ch) {
        int b[][] = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                b[i][j] = (a[i][j] == ch ? 1 : 0);
        return b;
    }

    int MaximumSizeSquareSubMatrix(int a[][]) {
        int n = a.length, m = a[0].length;
        int s[][] = new int[n][m];

        for (int i = 0; i < n; i++)
            s[i][0] = a[i][0];
        for (int i = 0; i < m; i++)
            s[0][i] = a[0][i];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i][j] == 1)
                    s[i][j] = Math.min(Math.min(s[i][j - 1], s[i - 1][j]), s[i - 1][j - 1]) + 1;
                else
                    s[i][j] = 0;
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max = Math.max(max, s[i][j]);
        return max;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ChefQueries().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    char ic() {
        return (char) skip();
    }

    char[][] icm(int n, int m) {
        char a[][] = new char[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = ic();
        return a;
    }
}