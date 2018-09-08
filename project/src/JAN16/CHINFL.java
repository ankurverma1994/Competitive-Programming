package JAN16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CHINFL {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        double t1=System.currentTimeMillis();
        n = ii();
        m = ii();
        long d = il();
        a = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j][0] = ii();
                a[i][j][1] = ii();
            }
        }
        dp=new double[n+10][m+10][2];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++)
//                out.print(a[i][j][0] + " ");
//            out.println();
//        }
        double ans = d;
        for (int i = 0; i < n; i++) {
//            out.println(solve1(i, 0, 0, d));
            ans = Math.max(ans, solve1(i, 0, 0, d));
        }
        if(overflow)
            out.println("Quintillionnaire");
        else
            out.println(ans);
        double t2=System.currentTimeMillis();
//        out.println(t2-t1);
    }

    // solve1(
    int n, m, a[][][];
    boolean overflow=false;
    double dp[][][];

    double solve1(int i, int j, int c, double val) {
        if(val>1e18){
            overflow=true;
            return val;
        }
//        out.println(val);
        if (i < 0 || i >= n)
            return val;
        if (j == m - 2) {
            if (c == 0)
                return dp[n][m][c]=Math.max(val, val / a[i][j][0] * a[i][j + 1][1]);
            double temp = a[i][j][c];
            temp = Math.max(temp, a[i][j + 1][c]);
            if (i > 0)
                temp = Math.max(temp, a[i - 1][j + 1][c]);
            if (i < n - 1)
                temp = Math.max(temp, a[i + 1][j + 1][c]);
            return dp[n][m][c]=temp * val;
        }
        if(dp[n][m][c]!=0)
            return dp[n][m][c];
        if (c == 0)
            return dp[n][m][c]=Math.max(Math.max(Math.max(solve1(i , j+1, 1, val / a[i][j][c]), solve1(i, j + 1, c, val)),
                    solve1(i - 1, j + 1, c, val)), solve1(i + 1, j + 1, c, val));

        return dp[n][m][c]=Math.max(Math.max(Math.max(solve1(i , j+1, 0, val * a[i][j][c]), solve1(i, j + 1, c, val)),
                solve1(i - 1, j + 1, c, val)), solve1(i + 1, j + 1, c, val));
    }
//    double ans[][][];
//    void solve2(double val){
//        ans=new double[n][m][2];
//        for(int i=0;i<n;i++){
//            ans[i][0][0]=val;
//            ans[i][0][1]=0;
//        }
//        for(int j=1;j<m;j++){
//            for(int i=0;i<n;i++){
//                double temp=ans[i][j-1][0];
//                if(i>0)
//                    temp=Math.min(temp,)
//            }
//        }
//    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new CHINFL().main1();
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
//         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/inputCHINFL.txt") : new ByteArrayInputStream(check.getBytes());
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