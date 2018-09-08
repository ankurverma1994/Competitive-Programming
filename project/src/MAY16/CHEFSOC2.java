package MAY16;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class CHEFSOC2 {
    final int mod = (int) 1e9 + 7;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), s = ii(), a[] = iia(m);
            Queue<Integer> q = new LinkedList<>();
            q.add(s);
            int ways[] = new int[n];
            for (int i = 1; i < m; i++) {
                Collection<Integer> list = new ArrayList<>();
                while (!q.isEmpty()) {
                    int u = q.poll();
                    boolean nfs = false;
                    if (1 <= u + a[i] && u + a[i] <= n) {
                        nfs = true;
                        list.add(u + a[i]);
                    }
                    if (1 <= u - a[i] && u - a[i] <= n) {
                        nfs = true;
                        list.add(u - a[i]);
                    }
                    if (!nfs)
                        ways[u - 1] = (ways[u - 1] + 1) % mod;
                }
                q.addAll(list);
            }
            while (!q.isEmpty()) {
                int u = q.poll() - 1;
                ways[u] = (ways[u] + 1) % mod;
            }
            out.println(Arrays.toString(ways));
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHEFSOC2().main1();
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
