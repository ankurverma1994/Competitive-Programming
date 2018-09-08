package R386DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbE {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii(), a[] = iia(n);
        int ans[] = new int[n];
        HashSet<Integer> set = new HashSet<>();
        int even = 0, odd = 0;
        for (int x : a) {
            if (x % 2 == 0) {
                if (!set.contains(x)) {
                    set.add(x);
                    even++;
                }
            } else {
                if (x % 2 == 1) {
                    if (!set.contains(x)) {
                        set.add(x);
                        odd++;
                    }
                }
            }
        }
        int max = n / 2;
        int leastEven = 2, leastOdd = 1;
        HashSet<Integer> map = new HashSet<>();
        while (set.contains(leastEven)) leastEven += 2;
        while (set.contains(leastOdd)) leastOdd += 2;
        int count = 0;
        int e = 0, o = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            if (!map.contains(x)) {
                if (x % 2 == 0) {
                    if (e < max) {
                        e++;
                        ans[i] = x;
                    } else {
                        o++;
                        odd++;
                        while (set.contains(leastOdd)) leastOdd += 2;
                        if(leastOdd>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastOdd;
                        count++;
                    }
                } else { // odd pehle se nahi hai
                    if (o < max) {
                        o++;
                        ans[i] = x;
                    } else {
                        e++;
                        even++;
                        while (set.contains(leastEven)) leastEven += 2;
                        if(leastEven>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastEven;
                        count++;
                    }
                }
            } else { // set mei hai
                if (x % 2 == 0) {
                    if (even < max) {
                        e++;
                        even++;
                        while (set.contains(leastEven)) leastEven += 2;
                        if(leastEven>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastEven;
                        count++;
                    } else {
                        o++;
                        odd++;
                        while (set.contains(leastOdd)) leastOdd += 2;
                        if(leastOdd>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastOdd;
                        count++;
                    }
                } else {
                    // odd map mei pehle se hai
                    if (odd < max) {
                        o++;
                        odd++;
                        count++;
                        while (set.contains(leastOdd)) leastOdd += 2;
                        if(leastOdd>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastOdd;
                    } else {
                        e++;
                        even++;
                        count++;
                        while (set.contains(leastEven)) leastEven += 2;
                        if(leastEven>m){
                            out.println("-1");
                            return;
                        }
                        ans[i] = leastEven;
                    }
                }
            }
            map.add(ans[i]);
            set.add(ans[i]);
        }
        out.println(count);
        for (int i = 0; i < n; i++) out.print(ans[i] + " ");
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbE().main1();
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
