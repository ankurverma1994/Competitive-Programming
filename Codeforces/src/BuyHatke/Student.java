package BuyHatke;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;

class Student {
    //------------> Solution starts here!!
    void solve() {
        int n = ii(), m = ii(), k = ii(), a[] = iia(n);
        TreeMap<Integer, Integer> set = new TreeMap<>();
        for (int i = 1; i <= m; i++) set.put(i, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (set.isEmpty()) {
                ans += (n - i);
                break;
            }
            int row = a[i];
            Integer next = set.ceilingKey(row);
            if (next == null) {
                next = set.firstKey();
            }
            if (row != next) ans++;
            int value = set.get(next) + 1;
            if (value == k) set.remove(next);
            else set.put(next, value);
        }
        out.println(ans);
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Student().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
