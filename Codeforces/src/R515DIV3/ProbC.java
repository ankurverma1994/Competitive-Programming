package R515DIV3;
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
        int n = 0;
        BIT left = new BIT();
        BIT right = new BIT();
        for (int tc = ii(); tc > 0; tc--) {
            char c = ic();
            int ID = ii();
            switch (c) {
                case 'L':
                    id[ID] = n;
                    if (n != 0) {
                        left.update(0, n - 1, MAX, 1);
                        right.update(n, n, MAX, n);
                    }
                    n++;
                    break;
                case 'R':
                    id[ID] = n;
                    if (n != 0) {
                        right.update(0, n - 1, MAX, 1);
                        left.update(n, n, MAX, n);
                    }
                    n++;
                    break;
                case '?':
                    ID = id[ID];
                    out.println(Math.min(left.getSum(ID), right.getSum(ID)));
                    break;
            }
        }
    }


    final int MAX = 200005;
    int id[] = new int[MAX];

    class BIT {
        // Max tree size
        int BITree[];

        BIT() {
            BITree = new int[MAX];
        }

        // Updates a node in Binary Index
        // Tree (BITree) at given index
        // in BITree. The given value 'val'
        // is added to BITree[i] and
        // all of its ancestors in tree.
        public void updateBIT(int n,
                              int index,
                              int val) {
            // index in BITree[] is 1
            // more than the index in arr[]
            index = index + 1;

            // Traverse all ancestors
            // and add 'val'
            while (index <= n) {
                // Add 'val' to current
                // node of BITree
                BITree[index] += val;

                // Update index to that
                // of parent in update View
                index += index & (-index);
            }
        }

        // SERVES THE PURPOSE OF getElement()
        // Returns sum of arr[0..index]. This
        // function assumes that the array is
        // preprocessed and partial sums of
        // array elements are stored in BITree[]
        public int getSum(int index) {
            int sum = 0; //Initialize result

            // index in BITree[] is 1 more
            // than the index in arr[]
            index = index + 1;

            // Traverse ancestors
            // of BITree[index]
            while (index > 0) {

                // Add current element
                // of BITree to sum
                sum += BITree[index];

                // Move index to parent
                // node in getSum View
                index -= index & (-index);
            }

            // Return the sum
            return sum;
        }

        // Updates such that getElement()
        // gets an increased value when
        // queried from l to r.
        public void update(int l, int r,
                           int n, int val) {
            // Increase value at
            // 'l' by 'val'
            updateBIT(n, l, val);

            // Decrease value at
            // 'r+1' by 'val'
            updateBIT(n, r + 1, -val);
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
