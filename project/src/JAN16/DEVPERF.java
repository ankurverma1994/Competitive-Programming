package JAN16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class DEVPERF {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            char town[][] = new char[n][m];
            for (int i = 0; i < n; i++) town[i] = is().toCharArray();
            int top = -1, bot = 0, left = 0, right = 0;
            for (int i = 0; i < n; i++) {
                boolean found = false;
                for (int j = 0; j < m; j++)
                    if (town[i][j] == '*') {
                        top = i;
                        found = true;
                        break;
                    }
                if (found) break;
            }
            if (top == -1) {
                out.println("0");
                continue;
            }
            for (int i = n - 1; i >= 0; i--) {
                boolean found = false;
                for (int j = 0; j < m; j++)
                    if (town[i][j] == '*') {
                        bot = i;
                        found = true;
                        break;
                    }
                if (found) break;
            }
            for (int i = 0; i < m; i++) {
                boolean found = false;
                for (int j = 0; j < n; j++)
                    if (town[j][i] == '*') {
                        left = i;
                        found = true;
                        break;
                    }
                if (found) break;
            }
            for (int i = m - 1; i >= 0; i--) {
                boolean found = false;
                for (int j = 0; j < n; j++)
                    if (town[j][i] == '*') {
                        right = i;
                        found = true;
                        break;
                    }
                if (found) break;
            }
//            out.println(top+" "+bot+" "+" "+left+" "+right);
            out.println((long) (Math.ceil(Math.max(bot - top, right - left) / 2.0) + 1));
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new DEVPERF().main1();
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

}