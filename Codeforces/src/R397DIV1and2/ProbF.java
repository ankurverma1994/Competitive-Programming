//package R397DIV1and2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbF {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), arr[] = iia(n);
        int BLOCK_SIZE = (int) (2 * Math.floor(Math.sqrt(n)));
        int Q = ii();
        minGap = new TreeMap<>();
        set = new TreeMap<>();
        int queryl[][] = new int[Q][2];
        int queryr[] = new int[Q];
        long mo_answer[] = new long[Q];
        for (int i = 0; i < Q; i++) {
            queryl[i][0] = ii() - 1;
            queryl[i][1] = i;
            queryr[i] = ii() - 1;
        }
        Arrays.sort(queryl, (int aa[], int bb[]) -> {
            int block_a = aa[0] / BLOCK_SIZE;
            int block_b = bb[0] / BLOCK_SIZE;
            if (block_a != block_b) {
                if (block_a < block_b)
                    return 1;
                return -1;
            }
            if (queryr[aa[1]] < queryr[bb[1]])
                return 1;
            return -1;
        });
        int mo_left = 0, mo_right = -1;
        for (int i = 0; i < Q; i++) {
            int left = queryl[i][0];
            int right = queryr[queryl[i][1]];
//            System.out.println((left + 1) + " " + (right + 1));
            while (mo_right < right) {
                mo_right++;
                add(arr[mo_right]);
//                System.out.println(1);
            }
            while (mo_right > right) {
                remove(arr[mo_right]);
                mo_right--;
//                System.out.println(2);
            }
            while (mo_left < left) {
                remove(arr[mo_left]);
                mo_left++;
//                System.out.println(3);
            }
            while (mo_left > left) {
                mo_left--;
                add(arr[mo_left]);
//                System.out.println(4);
            }
            mo_answer[queryl[i][1]] = minGap.isEmpty() ? 0 : minGap.firstKey();
//            System.out.println(set.toString());
//            System.out.println(minGap.toString());
        }
        for (int i = 0; i < Q; i++)
            out.println(mo_answer[i]);
    }

    TreeMap<Integer, Integer> minGap;
    TreeMap<Integer, Integer> set;

    void add(int x) {
        if (set.containsKey(x)) {
            set.put(x, set.get(x) + 1);
            if (!minGap.containsKey(0)) minGap.put(0, 0);
            minGap.put(0, minGap.get(0) + 1);
            return;
        }
        set.put(x, 1);
        Integer low = set.lowerKey(x);
        Integer high = set.higherKey(x);
        if (low != null && high != null) {
            int gap = Math.abs(high - low);
            if(minGap.containsKey(gap)) {
                int value = minGap.get(gap) - 1;
                if (value == 0) minGap.remove(gap);
                else minGap.put(gap, value);
            }
        }
        if (low != null) {
            int gap = Math.abs(x - low);
            if (!minGap.containsKey(gap)) minGap.put(gap, 0);
            minGap.put(gap, minGap.get(gap) + 1);
        }
        if (high != null) {
            int gap = Math.abs(x - high);
            if (!minGap.containsKey(gap)) minGap.put(gap, 0);
            minGap.put(gap, minGap.get(gap) + 1);
        }
//        System.out.println(set.toString());
//        System.out.println(minGap.toString());
    }

    void remove(int x) {
//        System.out.println(set.toString());
//        System.out.println(minGap.toString());
//        System.out.println(x);
        int value = set.get(x) - 1;
        if (value > 0) {
            set.put(x, value);
            if(minGap.containsKey(0)) {
                value = minGap.get(0) - 1;
                if (value == 0) minGap.remove(0);
                else minGap.put(0, value);
            }
            return;
        }
        set.remove(x);
        Integer low = set.lowerKey(x);
        Integer high = set.higherKey(x);
        if (low != null) {
            int gap = Math.abs(low - x);
            if(minGap.containsKey(gap)) {
                value = minGap.get(gap) - 1;
                if (value == 0) minGap.remove(gap);
                else minGap.put(gap, value);
            }
        }
        if (high != null) {
            int gap = Math.abs(x - high);
            if(minGap.containsKey(gap)) {
                value = minGap.get(gap) - 1;
                if (value == 0) minGap.remove(gap);
                else minGap.put(gap, value);
            }
        }
        if (low != null && high != null) {
            int gap = Math.abs(low - high);
            if (!minGap.containsKey(gap)) minGap.put(gap, 0);
            minGap.put(gap, minGap.get(gap) + 1);
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbF().main1();
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
