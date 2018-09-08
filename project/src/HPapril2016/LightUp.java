package HPapril2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;

class LightUp {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii();
            Building a[] = new Building[n];
            for (int i = 0; i < n; i++) a[i] = new Building(ii(), ii(), ii());
            Arrays.sort(a);
            int light1[] = new int[n];
            int light2[] = new int[n];
            int illuminated1[] = new int[n];
            int illuminated2[] = new int[n];
            for (int i = 1; i < n; i++) {
                int ptr = i - 1;
                for (int j = i - 2; j >= 0; --j) {
                    if (area(a[j].r, a[j].h, a[ptr].r, a[ptr].h, a[i].l, 0) >= 0)
                        ptr = j;
                }
                if (ptr != i - 1) {
                    illuminated1[i] = 1;
                    light2[ptr] = 1;
                }
            }
            for (int i = 0; i + 1 < n; i++) {
                int ptr = i + 1;
                for (int j = i + 2; j < n; j++) {
                    if (area(a[j].l, a[j].h, a[ptr].l, a[ptr].h, a[i].r, 0) <= 0)
                        ptr = j;
                }
                if (ptr != i + 1) {
                    illuminated2[i] = 1;
                    light1[ptr] = 1;
                }
            }
            for (int i = 0; i + 1 < n; i++) {
                if (illuminated2[i] > 0 && illuminated1[i + 1] > 0)
                    continue;
                if (light2[i] == 0 && light1[i + 1] == 0) {
                    light2[i] = 1;
                }
            }
            int ans = 2;
            for (int i = 0; i < n; i++) {
                ans += light1[i] + light2[i];
            }
            out.println(ans);
        }
    }

    class Building implements Comparable<Building> {
        int l, r, h;

        Building(int L, int R, int H) {
            l = L;
            r = R;
            h = H;
        }

        @Override
        public int compareTo(Building o) {
            return l - o.l;
        }
    }

    int area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new LightUp().main1();
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
