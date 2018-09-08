package HPapril2016;/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;

class Square {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-10;
    final double pi2 = Math.PI / 2;
    double xmin, xmax, ymin, ymax, x, y, dx, dy, mid, ans;
    double u[], r[];
    int n;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        u = new double[n + 1];
        r = new double[n + 1];
        xmin = ymin = 1e30;
        xmax = ymax = -1e30;
        for (int i = 1; i <= n; i++) {
            x = id();
            y = id();
            u[i] = Math.atan2(y, x);
            r[i] = Math.sqrt(x * x + y * y);
            xmin = Math.min(xmin, x);
            xmax = Math.max(xmax, x);
            ymin = Math.min(ymin, y);
            ymax = Math.max(ymax, y);
        }
        dx = xmax - xmin;
        dy = ymax - ymin;
        ans = Math.max(dx, dy);
        for (int step = 0; step < 128; step++)
            BinarySearch(step * pi2 / 128, (step + 1) * pi2 / 128);
        out.printf("%.4f\n", ans);
    }

    void BinarySearch(double low, double high) {
        while (Math.abs(high - low) > eps) {
            mid = (low + high) / 2;
            double f1 = place(mid), f2 = place(mid + eps);
            ans = Math.min(ans, f1);
            ans = Math.min(ans, f2);
            if (f1 <= f2) high = mid;
            else low = mid;
        }
    }

    double place(double value) {
        xmin = ymin = 1e30;
        xmax = ymax = -1e30;
        for (int i = 1; i <= n; i++) {
            x = r[i] * Math.cos(u[i] + value);
            y = r[i] * Math.sin(u[i] + value);
            xmin = Math.min(xmin, x);
            xmax = Math.max(xmax, x);
            ymin = Math.min(ymin, y);
            ymax = Math.max(ymax, y);
        }
        dx = xmax - xmin;
        dy = ymax - ymin;
        if (dx >= dy) return dx;
        return dy;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Square().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    double id() {
        return Double.parseDouble(is());
    }
}
