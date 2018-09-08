package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class KTHMAX {

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), a[] = iia(n);
            int c[][] = new int[n][2];
            for (int i = 0; i < n; i++) {
                c[i][0] = a[i];
                c[i][1] = i;
            }
            Arrays.sort(c, (int aa[], int bb[]) -> Integer.compare(bb[0], aa[0]));
            int l[] = leftIndices(a);
            int r[] = rightIndices(a);
            long ans[] = new long[n];
            for (int i = 0; i < n; i++) {
                int index = c[i][1];
                long total = r[index] - l[index];
                long x = Math.max(0, index - l[index]);
                long y = Math.max(0, r[index] - index - 1);
                ans[i] += (total * (total + 1) / 2);
                ans[i] -= (x * (x + 1) / 2);
                ans[i] -= (y * (y + 1) / 2);
            }
            for (int i = 1; i < n; i++) ans[i] += ans[i - 1];
            for (int Q = 0; Q < m; Q++) {
                long x = il();
                out.println(c[BinarySearchLowerBound(ans, x)][0]);
            }
        }
    }

    /* Starting indices in array where a[i] dominates i.e. it is maximum */
    public int[] leftIndices(int a[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int j = 0, n = a.length;
        int L[] = new int[n];
        for (int i = 0; i < n; i++) {
            while (stack.peek() >= 0 && a[i] >= a[stack.peek()])
                stack.pop();
            L[j++] = stack.peek() + 1;
            stack.push(i);
        }
        return L;
    }

    /* Ending indices in array till a[i] dominates i.e. it is maximum */
    public int[] rightIndices(int a[]) {
        Stack<Integer> stack = new Stack<>();
        int n = a.length, j = n - 1;
        int R[] = new int[n];
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            while (stack.peek() < n && a[i] > a[stack.peek()])
                stack.pop();
            R[j--] = stack.peek();
            stack.push(i);
        }
        return R;
    }

    public int BinarySearchLowerBound(long[] a, long v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new KTHMAX().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}