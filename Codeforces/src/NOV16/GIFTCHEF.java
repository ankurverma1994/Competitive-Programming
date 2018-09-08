package NOV16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class GIFTCHEF {
    final int mod = (int) 1e9 + 7;

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            String S = is(), F = is();
            int z[] = Z((F + "$" + S).toCharArray());
            int n = S.length(), m = F.length();
            this.n = n;
            this.m = m;
            good = new boolean[n];
            for (int i = m + 1, j = 0; i < z.length; i++, j++)
                if (z[i] == m)
                    good[j] = true;
            dp = new long[2][n];
            Arrays.fill(dp[0], -1);
            Arrays.fill(dp[1], -1);
            out.println(solve(0, 0));
        }
    }

    int n, m;
    long dp[][];

    long solve(int taken, int i) {
        if (i == n) {
            if (taken == 1) return 1;
            return 0;
        }
        if (i > n) return 0;
        if (dp[taken][i] != -1)
            return dp[taken][i];
        long ans = solve(taken, i + 1);
        if (good[i])
            ans += solve(1, i + m);
        ans %= mod;
        return dp[taken][i] = ans;
    }

    boolean good[];

    public static int[] Z(char[] str) {
        int n = str.length;
        int[] z = new int[n];
        if (n == 0) return z;
        z[0] = n;
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = r = i;
                while (r < n && str[r - l] == str[r]) r++;
                z[i] = r - l;
                r--;
            } else {
                int k = i - l;
                if (z[k] < r - i + 1) {
                    z[i] = z[k];
                } else {
                    l = i;
                    while (r < n && str[r - l] == str[r]) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new GIFTCHEF().main1();
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
//        obj = check.isEmpty() ? new/ FileInputStream("/home/ankurverma1994/Downloads/testcases.txt") : new ByteArrayInputStream(check.getBytes());
//        out = new PrintWriter("/home/ankurverma1994/output.txt");
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}
