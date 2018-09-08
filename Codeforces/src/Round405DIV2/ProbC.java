package Round405DIV2;
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
        String dic[] = new String[52];
        char x = 'a';
        for (int i = 0; i < 26; i++) {
            dic[i] = String.valueOf(x);
            x++;
        }
        x = 'a';
        for (int i = 26; i < 52; i++) {
            dic[i] = 'a' + String.valueOf(x);
            x++;
        }
        int n = ii(), k = ii();
        boolean res[] = new boolean[n - k + 1];
        String name[] = new String[n];
        Arrays.fill(name, "A");
        int index = 0;
        boolean flag = false;
        int first = -1;
        for (int i = 0; i < (n - k + 1); i++) res[i] = is().equals("YES");
        for (int i = 0; i < (n - k + 1); i++) {
            if (res[i]) {
                if (!flag) {
                    flag = true;
                    first = i;
                }
                for (int c = 0, j = i; c < k; c++, j++) {
                    if (name[j].equals("A")) {
                        name[j] = dic[index];
                        index++;
                    }
                }
            }
        }

        if (first == -1) {
            first = 0;
            name[0] = "a";
        }
        for (int i = first + 1; i < n; i++) {
            if (name[i].equals("A")) name[i] = name[i - 1];
        }
        for (int i = first - 1; i >= 0; i--) {
            if (name[i].equals("A")) name[i] = name[i + 1];
        }
        for (int i = 0; i < (n - k + 1); i++) {
            if (!res[i]) {
                HashSet<String> set = new HashSet<>();
                boolean ans = false;
                for (int c = 0, j = i; c < k; c++, j++) {
                    if (set.contains(name[j])) {
                        ans = true;
                        break;
                    }
                    set.add(name[j]);
                }
                if (!ans) {
                    name[i + k - 1] = name[i];
                }
            }
        }
        for (int i = 0; i < n; i++)
            out.print("A" + name[i] + " ");
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbC().main1();
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
