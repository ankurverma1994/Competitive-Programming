package JULY16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;

class CHSGMNTS {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), a[] = shrink(iia(n));
            int ans = 0;
            ArrayList<Integer> index[] = new ArrayList[n];
            for (int i = 0; i < n; i++) index[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) index[a[i]].add(i);


            for (int x = 0; x < n; x++) {

                boolean flag[] = new boolean[n];
                TreeSet<Integer> set = new TreeSet<>();
                set.add(n);
                int l = n - x;
                int prevAns = l * (l + 1) / 2;
                for (int i = x; i < n; i++) {
                    // one subarray fixed

                    set.add(i); //lower
                    int curr = prevAns;
                    if (!flag[i]) {
                        int upper = set.higher(i);
                        l = upper - i;
                        curr -= (l * (l + 1) / 2);
                        l = upper - (i + 1);
                        curr += (l * (l + 1) / 2);
                        flag[i] = true;

                        for (int j = 0; j < index[a[i]].size(); j++) {

                            int k = index[a[i]].get(j);
                            if (k <= i) continue;
                            set.add(k);
                            flag[k] = true;
                            upper = set.higher(k);
                            int lower = set.lower(k);

                            l = upper - lower - 1;
                            curr -= (l * (l + 1) / 2);

                            l = k - lower - 1;
                            curr += (l * (l + 1) / 2);

                            l = upper - k - 1;
                            curr += (l * (l + 1) / 2);
                        }
                    }
                    prevAns = curr;
                    ans += curr;
                }
            }
            out.println(ans);
        }
    }

    public static int[] shrink(int[] a) {
        /*  Code picked up from "uwi" submissions */
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++)
            b[i] = (long) a[i] << 32 | i;
        Arrays.sort(b);
        int[] ret = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (b[i] ^ b[i - 1]) >> 32 != 0)
                p++;
            ret[(int) b[i]] = p;
        }
        return ret;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHSGMNTS().main1();
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
