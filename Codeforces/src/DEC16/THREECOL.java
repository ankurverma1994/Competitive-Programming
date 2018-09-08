package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class THREECOL {
    ArrayList<String> ans;
    int n;

    //------------> Solution starts here!!
    void solve() {
        n = ii();
        ans = new ArrayList<>();
        Random rnd = new Random();
        if (n == 4) make4same(1, n);
        if (n == 8) make8same(1, n);
        if (n == 16) make16same(1, n);
        else {
            while (ans.size() <= (100000 - 400) / 2) {
                add(rnd.nextInt(2));
            }
            while (ans.size() <= 100000 - 400) {
                add(rnd.nextInt(2) + 2);
            }
        }
        out.println(ans.size());
        for (String x : ans) out.println(x);
    }

    void make16same(int start, int n) {
        make8same(start, n);
        make8same(start + 8, n);
        make8same(start + 4, n);
        make8same(start, n);
        make8same(start + 4, n);
        make8same(start, n);
        make8same(start + 8, n);
    }

    void make8same(int start, int n) {
        make4same(start, n);
        make4same(start + 4, n);
        make4same(start + 2, n);
        make4same(start, n);
        make4same(start + 2, n);
        make4same(start, n);
        make4same(start + 4, n);
    }

    void make4same(int start, int n) {
        for (int i = 1; i <= n; i++) {
            ans.add(i + " " + start + " " + i + " " + (start + 1));
            ans.add(i + " " + (start + 2) + " " + i + " " + (start + 3));
            ans.add(i + " " + (start + 1) + " " + i + " " + (start + 2));
            ans.add(i + " " + (start) + " " + i + " " + (start + 1));
            ans.add(i + " " + (start + 1) + " " + i + " " + (start + 2));
            ans.add(i + " " + start + " " + i + " " + (start + 1));
            ans.add(i + " " + (start + 2) + " " + i + " " + (start + 3));
        }
        for (int i = 1; i <= n; i++) {
            ans.add(start + " " + i + " " + (start + 1) + " " + i);
            ans.add((start + 2) + " " + i + " " + (start + 3) + " " + i);
            ans.add((start + 1) + " " + i + " " + (start + 2) + " " + i);
            ans.add(start + " " + i + " " + (start + 1) + " " + i);
            ans.add((start + 1) + " " + i + " " + (start + 2) + " " + i);
            ans.add(start + " " + i + " " + (start + 1) + " " + i);
            ans.add((start + 2) + " " + i + " " + (start + 3) + " " + i);
        }
    }

    void add(int i) {
        switch (i) {
            case 0:
                horizontalGo(1, n);
                break;
            case 1:
                horizontalAntiGo(1, n);
                break;
            case 2:
                verticalGo(1, n);
                break;
            case 3:
                verticalAntiGo(1, n);
                break;
        }
    }


    void horizontalGo(int left, int len) {
        for (int row = left, k = 0; k < len; k++, row++) {
            for (int i = left, j = 0; j < len - 1; j++, i++) {
                ans.add(row + " " + i + " " + row + " " + (i + 1));
            }
        }
    }

    void horizontalAntiGo(int left, int len) {
        for (int row = left, k = 0; k < len; k++, row++) {
            for (int i = left + len - 1, j = 0; j < len - 1; j++, i--) {
                ans.add(row + " " + i + " " + row + " " + (i - 1));
            }
        }
    }

    void verticalGo(int left, int len) {
        for (int col = left, k = 0; k < len; k++, col++) {
            for (int i = left, j = 0; j < len - 1; j++, i++) {
                ans.add(i + " " + col + " " + (i + 1) + " " + col);
            }
        }
    }

    void verticalAntiGo(int left, int len) {
        for (int col = left, k = 0; k < len; k++, col++) {
            for (int i = left + len - 1, j = 0; j < len - 1; j++, i--) {
                ans.add(i + " " + col + " " + (i - 1) + " " + col);
            }
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new THREECOL().main1();
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