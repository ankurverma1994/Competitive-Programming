package NOV16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class SEAPERM3 {
    final int mod = 2000000011;

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            m = ii();
            rule = new Rule[m];
            for (int i = 0; i < m; i++) {
                int x = ii() - 1, v = ii();
                rule[i] = new Rule(x, v);
            }
            p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i + 1;
            ans = 0;
            while (nextPermutation()) ;
            out.println(ans);
        }
    }

    int n, m, p[];
    Rule rule[];
    long ans;

    public boolean nextPermutation() {
        boolean valid = true;
        for (int i = 0; i < m; i++) {
            if (p[rule[i].x] != rule[i].v) {
                valid = false;
                break;
            }
        }
        if (valid) {
            for (int i = 0; i < p.length; i++) {
                for (int j = i + 1; j < p.length; j++) {
                    if (p[i] > (j + 1) && p[j] > (i + 1)) {
                        ans++;
                        ans %= mod;
                        valid = false;
                        break;
                    }
                }
                if (!valid) break;
            }
        }
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1; ; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }


    class Rule {
        int x, v;

        Rule(int x, int v) {
            this.x = x;
            this.v = v;
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new SEAPERM3().main1();
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
