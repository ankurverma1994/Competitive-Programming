//package CodeforcesTrainingsSeason3Episode2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Pair {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int N = ii() + 1;
        ArrayList divsors[] = new ArrayList[N];
//        double t = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
            divsors[i] = new ArrayList();
        for (int i = 1; i < N; i++) {
            for (int j = i + i; j < N; j += i)
                divsors[j].add(i);
        }
//        out.println(System.currentTimeMillis() - t);
        int k = ii(), a[] = iia(k);
      /*  int f[] = new int[N];
        for (int i = 0; i < k; i++) {
            if (f[a[i]] != 0) {
                out.println(f[a[i]] + " " + (i + 1));
                return;
            }
            f[a[i]] = i + 1;
        }
        */
        int number[] = new int[N];
        int div[] = new int[N];
        int index = -1;
        for (int i = 0; i < k; i++) {
            number[a[i]] = (i + 1);
            if (div[a[i]] != 0) {
                index = a[i];
                break;
            }
            for (int j = 0; j < divsors[a[i]].size(); j++) {
                div[divsors[a[i]].get(j)] = (i + 1);
                if (number[divsors[a[i]].get(j)] != 0) {
                    index = divsors[a[i]].get(j);
                    break;
                }
            }
            if (index != -1)
                break;
        }
        if (index == -1) out.println("0 0");
        else {
//            out.println(Arrays.toString(number));
//            out.println(Arrays.toString(div));
//            out.println(index);
            out.println(div[index] + " " + number[index]);
        }
    }

    class ArrayList {

        private int[] myStore;
        private int actSize = 0;

        public ArrayList() {
            myStore = new int[2];
        }

        public int get(int index) {
            if (index < actSize)
                return myStore[index];
            else
                throw new ArrayIndexOutOfBoundsException();
        }

        public void add(int obj) {
            if (myStore.length - actSize <= 1)
                increaseListSize();
            myStore[actSize++] = obj;
        }

        public int size() {
            return actSize;
        }

        public void clear() {
            actSize = 0;
        }

        private void increaseListSize() {
            myStore = Arrays.copyOf(myStore, myStore.length * 2);
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Pair().main1();
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
