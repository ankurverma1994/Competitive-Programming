package SPOJ;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ArraySub {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = 1; tc > 0; tc--) {
            int n = ii(), a[] = iia(n), k = ii();
//            1st method
//            SegmentTree tree = new SegmentTree(0, n - 1, a);
//            for (int i = 0; i < n - k + 1; i++)
//                out.print(tree.query(i, i + k - 1) + " ");
//            2nd method
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                // For very element, the previous smaller elements are useless so
                // remove them from Qi
                while ((!deque.isEmpty()) && a[i] >= a[deque.getLast()])
                    deque.removeLast();
                deque.addLast(i);
            }
            for (int i = k; i < n; i++) {
                out.print(a[deque.getFirst()] + " ");
                // Remove the elements which are out of this window
                while ((!deque.isEmpty()) && deque.getFirst() <= (i - k))
                    deque.removeFirst();
                // Remove all elements smaller than the currently
                // being added element (remove useless elements)
                while ((!deque.isEmpty()) && a[i] >= a[deque.getLast()])
                    deque.removeLast();
                deque.addLast(i);
            }
            out.print(a[deque.getFirst()]);
        }
    }

    static class SegmentTree {

        int from;
        int to;
        long sum;
        SegmentTree left;
        SegmentTree right;

        SegmentTree(int from, int to, int A[]) {
            this.from = from;
            this.to = to;
            if (from == to) {
                sum = A[from];
            } else {
                int mid = (from + to) / 2;
                left = new SegmentTree(from, mid, A);
                right = new SegmentTree(mid + 1, to, A);
                sum = Math.max(left.sum, right.sum);
            }
        }

        int query(int L, int R) {
            if (L <= from && to <= R) {
                return (int) sum;
            } else if (to < L || R < from) {
                return Integer.MIN_VALUE;
            } else {
                long sumL = left.query(L, R);
                long sumR = right.query(L, R);
                return (int) Math.max(sumL, sumR);
            }
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
                    new ArraySub().main1();
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