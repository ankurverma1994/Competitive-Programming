package GEEKS_IET;
//package WorldCodeSprint2016;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CrissCross {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int t = ii(); t > 0; t--) {
            n = 15;
            m = ii();
            grid = new char[n][m];
            char original[][] = new char[n][m];
            for (int i = 0; i < n; i++)
                original[i] = is().toCharArray();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (original[i][j] == 'G') {
                        for (int l = 1; l <= 15; l += 2) {
                            copy(original);
//                        out.println("fuck " + i + "  " + j);
                            if (validity(i, j, l)) {
//                            out.println("1st found " +l+"  "+i+" "+j);
                                copy(original);
                                place(i, j, l);
                                for (int p = 0; p < n; p++) {
                                    for (int q = 0; q < m; q++) {
                                        if (grid[p][q] == 'G') {
                                            for (int le = 1; le <= 15; le += 2) {
                                                if (validity(p, q, le)) {
//                                                out.println(area(l)+"  "+area(le));
                                                    ans = Math.max(ans, 1L * area(l) * area(le));
                                                }
//                                            out.println("1St area= "+i+" "+j+"   "+l+
//                                            "\n2nd area= "+p+" "+q+"   "+le);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            out.println(ans);
        }
    }

    int area(int l) {
        switch (l) {
            case 1:
                return 1;
            case 3:
                return 5;
            case 5:
                return 9;
            case 7:
                return 13;
            case 9:
                return 17;
            case 11:
                return 21;
            case 13:
                return 25;
            case 15:
                return 29;
        }
        return 0;
    }

    boolean validity(int i, int j, int l) {
        if (l == 1) {
            if (grid[i][j] == 'G')
                return true;
            else return false;
        }
        if (0 <= i && i + l - 1 < n && 0 <= (j - l / 2) && (j + l / 2) < m) {
            for (int x = i; x < i + l; x++)
                if (grid[x][j] != 'G')
                    return false;

            for (int x = j - l / 2; x <= (j + l / 2); x++)
                if (grid[i + l / 2][x] != 'G')
                    return false;

            return true;
        }
        return false;
    }

    void place(int i, int j, int l) {
        if (l == 1) {
            grid[i][j] = 'B';
            return;
        }
        for (int x = i; x < i + l; x++) grid[x][j] = 'B';

        for (int x = j - l / 2; x <= (j + l / 2); x++) grid[i + l / 2][x] = 'B';
    }

    void copy(char a[][]) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = a[i][j];
    }

    char grid[][];
    int n, m;

    // right up left down
    int x[] = {0, -1, 0, 1};
    int y[] = {1, 0, -1, 0};


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CrissCross().main1();
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