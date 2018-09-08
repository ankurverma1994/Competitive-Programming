package FacebookHackerCup2016.FirstRound;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CodingContest {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            int n = ii(), a[] = iia(n);
            int count = 1, i = 0;
            long ans = 0;
            for (; i < n - 1; i++) {
//                out.println(a[i]+" "+ans);
//                if (count == 4) count = 1;
//                if (a[i] >= a[i + 1]) {
//                    ans += (4 - count);
//                    count = 1;
//                    continue;
//                }
//                if (a[i + 1] - a[i] <= 10) {
//                    count++;
//                    continue;
//                }
//                int d = (a[i + 1] - a[i]) / 10;
//                if ((a[i + 1] - a[i]) % 10 == 0) d--;
//                if ((4 - count) <= d) {
//                    ans += (4 - count);
//                    count = 1;
//                    continue;
//                }
//                ans += d;
//                out.println("last " + a[i] + " " + ans);
//                count = 1;
//                i++;
//                out.println(a[i]+" "+ans);
                if (a[i + 1] > a[i] && ((a[i + 1] - a[i]) <= 10)) {
                    count++;
                    if (count == 4) {
                        i++;
                        count = 1;
                    }
                    continue;
                }
                if (a[i] >= a[i + 1]) {
                    ans += (4 - count);
                    count = 1;
                    continue;
                }
                int d = (a[i+1] - a[i]) / 10;
//                out.println(tc + " " + d + " " + a[i]);
                if ((a[i + 1] - a[i]) % 10 == 0) d--;
                if ((4 - count) <= d) {
                    ans += (4 - count);
//                    out.println(tc + " " + ans);
                    count = 1;
                    continue;
                }
                ans += d;
                count = d+2;
                if(count==4) count=1;
                if(count==5) count=2;
                if(count==6) count=3;
                if(count==7) count=1;
                i++;
            }
                ans += (4 - count);
            out.print("Case #" + tc + ": " + ans);
            if (tc != test)
                out.println();
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new CodingContest().main1();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/inputCoding1.txt") : new ByteArrayInputStream(check.getBytes());
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