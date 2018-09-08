package JAN16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CHEFTMA {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), k = ii(), m = ii(), a[] = iia(n), b[] = iia(n), c[] = iia(k), d[] = iia(m);
            int rem[] = new int[n];
            for (int i = 0; i < n; i++) rem[i] = a[i] - b[i];
            Arrays.sort(rem);
            Arrays.sort(c);
            Arrays.sort(d);
            Stack<Integer> stack = new Stack<>();
            int x = 0, y = 0;
            while (x < k && y < m) {
                if (c[x] <= d[y]) {
                    stack.push(c[x]);
                    x++;
                } else {
                    stack.push(d[y]);
                    y++;
                }
            }
            while (x < k) stack.push(c[x++]);
            while (y < m) stack.push(d[y++]);
            int i = n - 1;
            while (!stack.isEmpty() && i >= 0) {
                int temp = stack.pop();
                if (rem[i] >= temp) {
                    rem[i] -= temp;
                    i--;
                }
            }
            long ans = 0;
            for (int z : rem) ans += z;
            out.println(ans);
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new CHEFTMA().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}