package Round367DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

public class ProbE {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii(), q = ii();
        Node[][] a = new Node[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                a[i][j] = new Node();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j].val = ii();
            }
        }
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                if (i - 1 >= 0) {
                    a[i][j].up = a[i - 1][j];
                    a[i - 1][j].down = a[i][j];
                }
                if (j - 1 >= 0) {
                    a[i][j].left = a[i][j - 1];
                    a[i][j - 1].right = a[i][j];
                }
            }
        }
        for (int Q = 0; Q < q; Q++) {
            int r1 = ii();
            int c1 = ii();
            int r2 = ii();
            int c2 = ii();
            int h = ii();
            int w = ii();
            Node cur1 = getPos(a, r1, c1);
            Node cur2 = getPos(a, r2, c2);
            Node cur3 = getPos(a, r1, c1 + w - 1);
            Node cur4 = getPos(a, r2, c2 + w - 1);
            for (int i = 0; i < h; i++) {
                Node R1 = cur1;
                Node R2 = cur2;
                Node L1 = R1.left;
                Node L2 = R2.left;
                R1.left = L2;
                R2.left = L1;
                L1.right = R2;
                L2.right = R1;

                L1 = cur3;
                L2 = cur4;
                R1 = L1.right;
                R2 = L2.right;
                R1.left = L2;
                R2.left = L1;
                L1.right = R2;
                L2.right = R1;

                cur1 = cur1.down;
                cur2 = cur2.down;
                cur3 = cur3.down;
                cur4 = cur4.down;
            }

            cur1 = getPos(a, r1, c1);
            cur2 = getPos(a, r2, c2);
            cur3 = getPos(a, r1 + h - 1, c1);
            cur4 = getPos(a, r2 + h - 1, c2);
            for (int i = 0; i < w; i++) {
                Node D1 = cur1;
                Node D2 = cur2;
                Node U1 = D1.up;
                Node U2 = D2.up;
                U1.down = D2;
                U2.down = D1;
                D1.up = U2;
                D2.up = U1;

                U1 = cur3;
                U2 = cur4;
                D1 = U1.down;
                D2 = U2.down;
                U1.down = D2;
                U2.down = D1;
                D1.up = U2;
                D2.up = U1;

                cur1 = cur1.right;
                cur2 = cur2.right;
                cur3 = cur3.right;
                cur4 = cur4.right;
            }
        }

        for (int i = 1; i <= n; i++) {
            Node cur = a[i][0];
            for (int j = 1; j <= m; j++) {
                cur = cur.right;
                out.print(cur.val + " ");
            }
            out.println();
        }
    }

    private Node getPos(Node[][] a, int r, int c) {
        Node res = a[0][0];
        for (int i = 0; i < r; i++) {
            res = res.down;
        }
        for (int i = 0; i < c; i++) {
            res = res.right;
        }
        return res;
    }

    class Node {
        Node up;
        Node left;
        Node down;
        Node right;
        int val;

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
