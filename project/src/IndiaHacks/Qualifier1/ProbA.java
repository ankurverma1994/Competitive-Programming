package IndiaHacks.Qualifier1;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class ProbA {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        outer:
        for (int tc = ii(); tc > 0; tc--) {
            String ss = is();
            char s[] = ss.toCharArray();
            int n = s.length;
            int a[] = new int[26];
            for (char c : s)
                a[c - 'a']++;
            int count = 0;
            for (int i : a)
                if (i > 0)
                    count++;
            if (count >= 4) {
                out.println("YES");
                continue;
            }
            if (count == 3) {
                if (n > 4) {
                    out.println("YES");
                    continue;
                }
                out.println("NO");
                continue;
            }
            if (count == 1) {
                if (n >= 10) {
                    out.println("YES");
                    continue;
                }
                out.println("NO");
                continue;
            }
            if (count == 2) {
                for (int i = 0; i < n; i++) {
                    Set<String> strings = new HashSet<>();
                    for (int j = i + 1; j < n; j++) {
                        strings.add(ss.substring(i, j));
                        for (int k = j + 1; k < n; k++) {
                            strings.add(ss.substring(j, k));
                            if (strings.size() == 2)
                                for (int l = k + 1; l < n; l++) {
                                    strings.add(ss.substring(k, l));
                                    if (strings.size() == 3)
                                        for (int m = l + 1; m < n; m++) {
                                            strings.add(ss.substring(l, m));
                                            if (strings.size() == 4) {
                                                out.println("YES");
                                                continue outer;
                                            }
                                        }
                                }
                        }
                    }
                }
                out.println("NO");
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
                    new ProbA().main1();
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