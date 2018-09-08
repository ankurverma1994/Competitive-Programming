package CodeJam2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class CountingSheep {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int t = ii();
        for (int tc = 1; tc <= t; tc++) {
            out.printf("Case #%d: ", tc);
            solve(ii());
        }
    }

    void solve(int n) {
        if (n == 0) {
            out.println("INSOMNIA");
            return;
        }
        boolean a[] = new boolean[10];
        boolean ans = false;
        for (int i = 1; i <= 100000000; i++) {
            long num = 1L * n * i;
            char s[] = String.valueOf(num).toCharArray();
            for (char c : s) {
                a[c - '0'] = true;
            }
            boolean found = true;
            for (boolean k : a) {
                if (!k) {
                    found = false;
                    break;
                }
            }
            if (found) {
                ans = true;
                out.println(num);
                break;
            }
        }
        if (!ans)
            out.println("INSOMNIA");
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CountingSheep().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
//        out = new PrintWriter(System.out);
        out = new PrintWriter("/home/ankurverma1994/CodeJam/output.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/CodeJam/input.in") : new ByteArrayInputStream(check.getBytes());
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
}
