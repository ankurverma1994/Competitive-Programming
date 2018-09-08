package R392DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbE {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        out.println(Mathematician(3, new String[]{"11#3#44", "12#26#13", "21#33#21"}, new String[]{"33#44#11", "3#13#12", "21#26#21"}));
        out.println(Mathematician(2, new String[]{"1#1", "1#1"}, new String[]{"1#1", "1#1"}));
    }
    /*
    11 3  44
    12 26 13
    21 33 21
     */

    public static String Mathematician(int input1, String[] input2, String[] input3) {
        //Write code here
        String invalid = "invalid";
        long a[][] = new long[input1][input1];
        if (input2.length != input1) return invalid;
        for (int row = 0; row < input1; row++) {
            String split[] = input2[row].split("#");
            if (split.length != input1) return invalid;
            for (int col = 0; col < input1; col++) a[row][col] = Integer.parseInt(split[col]);
        }
        long b[][] = new long[input1][input1];
        if (input3.length != input1) return invalid;
        for (int row = 0; row < input1; row++) {
            String split[] = input3[row].split("#");
            if (split.length != input1) return invalid;
            for (int col = 0; col < input1; col++) b[row][col] = Integer.parseInt(split[col]);
        }
        for (int down = 0; down < input1; down++) {
            long copy[][] = new long[input1][input1];
            for (int row = 0; row < input1; row++)
                for (int col = 0; col < input1; col++)
                    if (down == col) copy[(row + 1) % input1][col] = a[row][col];
                    else copy[row][col] = a[row][col];
            boolean valid = true;
            for (int row = 0; row < input1; row++) {
                boolean found = false;
                if (matching(input1, row, copy, b)) continue;
                for (int steps = 0; steps < input1; steps++) {
                    long temp = copy[row][0];
                    for (int col = 0; col < input1; col++) copy[row][col] = copy[row][(col + 1) % input1];
                    copy[row][input1 - 1] = temp;
                    if (matching(input1, row, copy, b)) {
                        found = true;
                        break;
                    }
                }
                if (found) continue;
                else {
                    valid = false;
                    break;
                }
            }
            if (valid) return "yes";
        }
        return "no";
    }

    public static boolean matching(int n, int row, long original[][], long copy[][]) {
        for (int col = 0; col < n; col++)
            if (original[row][col] != copy[row][col])
                return false;
        return true;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbE().main1();
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
