package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class ANKTRAIN {

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii();
            int x = (n - 1) % 8 + 1;
            if (x == 3 || x == 2 || x == 1) n += 3;
            else if (x == 4 || x == 5 || x == 6) n -= 3;
            else if (x == 7) n++;
            else n--;
            out.println(n + neighbour(x));
        }
    }

    String neighbour(int n) {
        String ans = "";
        switch (n) {
            case 1:
            case 4:
                ans = "LB";
                break;
            case 2:
            case 5:
                ans = "MB";
                break;
            case 3:
            case 6:
                ans = "UB";
                break;
            case 7:
                ans = "SU";
                break;
            case 8:
                ans = "SL";
        }
        return ans;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ANKTRAIN().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
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
