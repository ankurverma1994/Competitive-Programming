package LunchTimeFEB16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), a[] = iia(n);
        SegmentTreeRSQPP rsqpp = new SegmentTreeRSQPP(a);
        // [l,r)
        out.println(rsqpp.sum(3, 4));
        for (int i=0;i<=6;i++)
        out.println(rsqpp.leanedsum(0,i));
    }

    public static class SegmentTreeRSQPP {
        public int M, H, N;
        public long[] st;
        public long[] lst;
        public long[] plus;
        private long[] lenh = new long[30];

        public SegmentTreeRSQPP(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new long[M];
            lst = new long[M];
            plus = new long[H];
            for (int i = 0; i <= 29; i++) {
                long len = 1L << i;
                lenh[i] = (long) len * (len - 1) / 2;
            }
        }

        public SegmentTreeRSQPP(int[] a) {
            N = a.length;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new long[M];
            lst = new long[M];
            plus = new long[H];
            for (int i = 0; i <= 29; i++) {
                long len = 1L << i;
                lenh[i] = (long) len * (len - 1) / 2;
            }
            for (int i = 0; i < N; i++) {
                st[H + i] = a[i];
            }
            for (int i = (M >> 1) - 1; i >= 1; i--) {
                propagate(i);
            }
        }

        private void propagate(int i) {
            int len = H / Integer.highestOneBit(i);
            int ind = Integer.numberOfTrailingZeros(len);
            st[i] = (st[2 * i] + st[2 * i + 1] + plus[i] * (long) len);
            lst[i] = (lst[2 * i] + lst[2 * i + 1] + st[2 * i + 1] * len / 2 + plus[i] * lenh[ind]);
        }

        public void add(int l, int r, int v) {
            if (l < r) add(l, r, v, 0, H, 1);
        }

        private void add(int l, int r, int v, int cl, int cr, int cur) {
            if (cur >= H) {
                st[cur] += v;
            } else if (l <= cl && cr <= r) {
                plus[cur] += v;
                propagate(cur);
            } else {
                int mid = cl + cr >>> 1;
                if (cl < r && l < mid) {
                    add(l, r, v, cl, mid, 2 * cur);
                }
                if (mid < r && l < cr) {
                    add(l, r, v, mid, cr, 2 * cur + 1);
                }
                propagate(cur);
            }
        }

        public long gsum = 0;

        public long sum(int l, int r) {
            gsum = 0;
            sum(l, r, 0, H, 1);
            return gsum;
        }

        private void sum(int l, int r, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                gsum += st[cur];
            } else {
                int mid = cl + cr >>> 1;
                if (cl < r && l < mid) {
                    sum(l, r, cl, mid, 2 * cur);
                }
                if (mid < r && l < cr) {
                    sum(l, r, mid, cr, 2 * cur + 1);
                }
                gsum += plus[cur] * (Math.min(cr, r) - Math.max(cl, l));
            }
        }

        public long leanedsum(int l, int r) {
            gsum = 0;
            leanedsum(l, r, 0, H, 1);
            return gsum;
        }

        private void leanedsum(int l, int r, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                gsum += lst[cur] + st[cur] * (cl - l);
            } else {
                int mid = cl + cr >>> 1;
                if (cl < r && l < mid) {
                    leanedsum(l, r, cl, mid, 2 * cur);
                }
                if (mid < r && l < cr) {
                    leanedsum(l, r, mid, cr, 2 * cur + 1);
                }
                long sup = Math.min(cr, r) - l;
                long inf = Math.max(cl, l) - l;
                long l2 = ((sup - 0) * (sup - 1) / 2 - inf * (inf - 1) / 2);
                gsum += plus[cur] * l2;
            }
        }

        public long[] toArray() {
            return toArray(1, 0, H, new long[H]);
        }

        private long[] toArray(int cur, int l, int r, long[] ret) {
            if (r - l == 1) {
                ret[cur - H] = st[cur];
            } else {
                toArray(2 * cur, l, l + r >>> 1, ret);
                toArray(2 * cur + 1, l + r >>> 1, r, ret);
                for (int i = l; i < r; i++) ret[i] += plus[cur];
            }
            return ret;
        }
    }

    // reduces elements in array like
    // 1000000000, 100000, 25550, 0, 1, 2, 1531333,0 ---> [6, 4, 3, 0, 1, 2, 5, 0]
    public int[] shrink(int[] array) {
        /*  Code picked up from "uwi" submissions */
        int n = array.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++)
            b[i] = (long) array[i] << 32 | i;
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

    public static int[][] makeBuckets(int[] a, int sup) {
        int n = a.length;
        int[][] bucket = new int[sup + 1][];
        int[] bp = new int[sup + 1];
        for (int i = 0; i < n; i++) bp[a[i]]++;
        for (int i = 0; i <= sup; i++) bucket[i] = new int[bp[i]];
        for (int i = n - 1; i >= 0; i--) bucket[a[i]][--bp[a[i]]] = i;
        return bucket;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbD().main1();
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

    long il() {
        long num = 0;
        int b;
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

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}