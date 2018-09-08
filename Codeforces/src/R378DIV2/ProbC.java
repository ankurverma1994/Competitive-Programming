package R378DIV2;
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
        int n = ii(), a[] = iia(n);
        int k = ii(), b[] = iia(k);
        if(Arrays.stream(a).asLongStream().sum()!=Arrays.stream(b).asLongStream().sum()){
            out.println("NO");
            return;
        }
        int start = 0, end = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            int sum = 0, count = 0;
            while (end < n) {
                sum += a[end];
                count++;
                if (sum > b[j] || sum < 0) {
//                    out.println(sum + " " + b[j]);
                    out.println("NO");
                    return;
                }
                if (sum == b[j]) {
//                    out.println(sum + " " + b[j] + " " + count);
                    for (int i = start; i <= end; i++) temp.add(a[i]);
                    while (count != 1) {
                        int max = 0;
                        for (int i = temp.size() - count; i < temp.size(); i++) {
                            max = Math.max(max, temp.get(i));
                        }
//                        out.println(start + " " + end + " " + count + " " + temp.toString());
//                        out.println(ans.toString() + " " + max);
                        boolean delete = false;
                        for (int i = temp.size() - count; i < temp.size(); i++) {
                            if (max == temp.get(i)) {
                                if (i - 1 >= temp.size() - count && temp.get(i) > temp.get(i - 1)) {
                                    int val = temp.get(i - 1) + temp.get(i);
                                    temp.remove(i - 1);
                                    temp.remove(i - 1);
                                    temp.add(i - 1, val);
                                    ans.add((i + 1) + " lambai");
                                    count--;
                                    delete = true;
                                    break;
                                }
                                if (i + 1 < temp.size() && temp.get(i) > temp.get(i + 1)) {
                                    int val = temp.get(i + 1) + temp.get(i);
                                    temp.remove(i);
                                    temp.remove(i);
                                    temp.add(i, val);
                                    ans.add((i + 1) + " R");
                                    count--;
                                    delete = true;
                                    break;
                                }
                            }
                        }
                        if (!delete) {
                            out.println("NO");
                            return;
                        }
                    }
                    start = end + 1;
                    end++;
                    break;
                }
                end++;
            }
        }

        out.println("YES");
        for (String x : ans) out.println(x);
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
