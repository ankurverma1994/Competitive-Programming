package Round413DIV1and2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        c = ii();
        d = ii();
        int b[] = new int[n];
        int co[] = new int[n];
        char ty[] = new char[n];
        int di = 0;
        for (int i = 0; i < n; i++) {
            b[i] = ii();
            co[i] = ii();
            ty[i] = ic();
            if (ty[i] == 'D') di++;
        }
        int coi = n - di;
        diamond = new int[di][2];
        coins = new int[coi][2];
        int j = 0, k = 0;
        for (int i = 0; i < n; i++) {
            if (ty[i] == 'D') {
                diamond[j][0] = co[i];
                diamond[j][1] = b[i];
                j++;
            } else {
                coins[k][0] = co[i];
                coins[k][1] = b[i];
                k++;
            }
        }
        Arrays.sort(coins, (int aa[], int bb[]) ->
        {
            if (aa[0] < bb[0])
                return -1;
            return 1;
        });
        Arrays.sort(diamond, (int aa[], int bb[]) ->
        {
            if (aa[0] < bb[0])
                return -1;
            return 1;
        });
        int bc[] = new int[coins.length];
        for (int i = 0; i < coins.length; i++) bc[i] = coins[i][1];
        int bd[] = new int[diamond.length];
        for (int i = 0; i < diamond.length; i++) bd[i] = diamond[i][1];

        SegmentTree beautyCoins = new SegmentTree(bc, coins.length);
        SegmentTree beautyDiamonds = new SegmentTree(bd, diamond.length);
        int ans = 0;
        // CC
        for (int i = 0; i < coins.length; i++) {
            int local = 0;
            int remaining = c - coins[i][0];
            int index = BinarySearchUpperBound(coins, remaining);
            boolean valid = false;
            if (index > i) {
                if (index - (i + 1) + 1 > 0) {
                    valid = true;
                    local = Math.max(local, beautyCoins.query(i + 1, index));
                }
                if (i - 1 >= 0) {
                    valid = true;
                    local = Math.max(local, beautyCoins.query(0, i - 1));
                }
            } else {
                if (index == i) index--;
                if (index >= 0) {
                    valid = true;
                    local = Math.max(local, beautyCoins.query(0, index));
                }
            }
            if (valid)
                ans = Math.max(ans, local + coins[i][1]);
        }

        //DD
        for (int i = 0; i < diamond.length; i++) {
            int local = 0;
            int remaining = d - diamond[i][0];
            int index = BinarySearchUpperBound(diamond, remaining);
            boolean valid = false;
            if (index > i) {
                if (index - (i + 1) + 1 > 0) {
                    valid = true;
                    local = Math.max(local, beautyDiamonds.query(i + 1, index));
                }
                if (i - 1 >= 0) {
                    valid = true;
                    local = Math.max(local, beautyDiamonds.query(0, i - 1));
                }
            } else {
                if (index == i) index--;
                if (index >= 0) {
                    valid = true;
                    local = Math.max(local, beautyDiamonds.query(0, index));
                }
            }
            if (valid)
                ans = Math.max(ans, local + diamond[i][1]);
        }

        //CD
        int local = 0;
        int count = 0;
        int index = BinarySearchUpperBound(coins, c);
        if (index >= 0) {
            count++;
            local += beautyCoins.query(0, index);
        }
        index = BinarySearchUpperBound(diamond, d);
        if (index >= 0) {
            count++;
            local += beautyDiamonds.query(0, index);
        }
        if (count == 2) {
            ans = Math.max(ans, local);
        }
        out.println(ans);
    }

    public int BinarySearchUpperBound(int[][] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h][0] <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

    int diamond[][], coins[][];

    int n, c, d;

    class SegmentTree {
        int tree[];
        int a[];
        int n;

        int operation(int a, int b) {
            return Math.max(a, b);
        }

        SegmentTree(int a[], int n) {
            tree = new int[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        //  TODO check this function
        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        void update(int x, int v) {
            put(0, n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, int v) {
            if (s == e) {
                tree[c] = a[s] = v;
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) put(s, m, 2 * c + 1, x, v);
            else put(m + 1, e, 2 * c + 2, x, v);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        int query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        int get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbC().main1();
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
