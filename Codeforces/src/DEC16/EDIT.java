package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class EDIT {
    Random rnd;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            s = is().toCharArray();
            sf = is().toCharArray();
            insert = ii();
            delete = ii();
            swap = ii();
            replace = iim(26, 26);
            rnd = new Random();
            if (sf.length <= 10000) {
                long ans = 0;
                for (int i = 0; i < sf.length; i++) {
                    if (i >= s.length) break;
                    if (s[i] != sf[i]) {
                        for (int j = 0; j < s.length; j++) {
                            if (s[j] == sf[i]) {
                                ans += swap;
                                char temp = s[i];
                                s[i] = s[j];
                                s[j] = temp;
                                break;
                            }
                        }
                    }
                }
                dp = new long[s.length][sf.length];
                for (int i = 0; i < s.length; i++) Arrays.fill(dp[i], -1);
                out.println(solve(s.length - 1, sf.length - 1) + ans);
            } else {
                long ans = 1L * delete * s.length + 1L * insert * sf.length;
                long ans1 = 0;
                for (int i = 0; i < s.length; i++) {

                }
            }
        }
    }

    long dp[][];

    long solve(int i, int j) {
        if (i >= s.length) {
            return 1L * (sf.length - j) * insert;
        }
        if (j >= sf.length) {
            return 1L * (s.length - i) * delete;
        }
        if (dp[i][j] != -1) return dp[i][j];
        long ans = 922337203685477580L;
        if (s[i] == sf[j]) {
            parent[i][j] = i;
            ans = Math.min(ans, solve(i + 1, j + 1));
        } else {
            ans = Math.min(ans, insert + solve(i, j + 1));
            ans = Math.min(ans, delete + solve(i + 1, j));
            ans = Math.min(ans, replace[s[i] - 'A'][sf[j] - 'A'] + solve(i + 1, j + 1));
        }
        return dp[i][j] = ans;
    }

    int insert, delete, swap, replace[][], parent[][];

    char s[], sf[];


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new EDIT().main1();
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
