package VKCup;/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), d = ii(), h = ii();
        if (d == h) {
            if (d == 1) {
                if (n > 2) {
                    out.println("-1");
                    return;
                } else {
                    out.println("1 2");
                    return;
                }
            }
            int node = 1;
            for (int i = 1; i <= d; i++) {
                out.println(node + " " + (node + 1));
                node++;
            }
            node++;
            for (int i = node; i <= n; i++)
                out.println("2 " + i);
            return;
        } else {
            if (d > 2 * h) {
                out.println("-1");
                return;
            } else {
                if (h == 1 && d == 2 && n != 3) {
//                    out.println("-1");
                    for (int i = 2; i <= n; i++)
                        out.println("1 " + i);
                    return;
                }
                int node = 1;
                for (int i = 1; i <= h; i++) {
                    out.println(node + " " + (node + 1));
                    node++;
                }
                node++;
                d = d - h;
                out.println("1 " + node);
                d--;
                for (int i = 1; i <= d; i++) {
                    out.println(node + " " + (node + 1));
                    node++;
                }
                node++;
                for (int i = node; i <= n; i++)
                    out.println("2 " + i);
            }
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
