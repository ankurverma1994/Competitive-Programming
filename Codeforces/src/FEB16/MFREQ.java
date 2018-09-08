package FEB16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class MFREQ {

    //------------> Solution starts here!!
    void solve() {
        int n = ii(), m = ii(), a[] = iia(n);
        int N = 0;
        for (int i = 1; i < n; i++)
            if (a[i] == a[i - 1]) continue;
            else N++;
        N++;
        frequent A[] = new frequent[N];
        int count = 1, l = 0;
        for (int i = 1, j = 0; i < n; i++) {
            if (a[i] == a[i - 1]) count++;
            else {
                A[j++] = new frequent(l, i - 1, a[i - 1], count);
                count = 1;
                l = i;
            }
        }
        A[N - 1] = new frequent(l, n - 1, a[n - 1], count);
        Arrays.sort(A);
//        for (frequent x : A)
//            System.out.println(x.toString());
        outer:
        for (int j = 0; j < m; j++) {
            int x = ii() - 1, y = ii() - 1, k = ii();
            int index = BinarySearchLowerBound(A, k);
//            System.out.println(index);
            for (int i = index; i < N; i++) {
                int left = Math.max(A[i].left, x);
                int right = Math.min(A[i].right, y);
                int len = right - left + 1;
                if (len >= k) {
                    out.println(A[i].element);
                    continue outer;
                }
//                if (A[i].k > (y - x + 1)) break;
            }
            out.println(-1);
        }
    }

    public int BinarySearchLowerBound(frequent[] A, int v) {
        int low = -1, high = A.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (A[h].k >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    class frequent implements Comparable<frequent> {
        int left, right, element, k;

        frequent(int left, int right, int element, int k) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.k = k;
        }

        @Override
        public int compareTo(frequent o) {
            return Integer.compare(k, o.k);
        }

        public String toString() {
            return "left= " + left + " right= " + right + " element= " + element + " k= " + k;
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new MFREQ().main1();
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
