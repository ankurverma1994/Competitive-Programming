package Technocup2017;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        char s[] = is().toCharArray();
        double sum = 0;
        ArrayList<Character> sb = new ArrayList<>();
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < s.length; i++) {
            if ('a' <= s[i] && s[i] <= 'z') {
//                out.println(sb.toString() + " " + count);
                int point = sb.size() + 10;
                if (count == 2 && flag) point = sb.size() - 2;
                String num = "";
                for (int j = 0; j < sb.size(); j++) {
                    if (j == point) num = num + ".";
                    num = num + sb.get(j);
                }
                if (num.length() > 0)
                    sum += Double.parseDouble(num);
//                out.println(sum);
                sb = new ArrayList<>();
                count = 0;
                flag = false;
                continue;
            }
            if (s[i] == '.') {
                count = 0;
                flag = true;
                continue;
            }
            sb.add(s[i]);
            count++;
        }
//        out.println(sb.toString() + " " + count);
        int point = sb.size() + 10;
        if (count == 2 && flag) point = sb.size() - 2;
        String num = "";
        for (int j = 0; j < sb.size(); j++) {
            if (j == point) num = num + ".";
            num = num + sb.get(j);
        }
        if (num.length() > 0)
            sum += Double.parseDouble(num);
//        out.println(sum);
        sb = new ArrayList<>();
        count = 0;
        flag = false;
//        out.println(sum);
//        out.println("sum= "+sum);
        num = String.format("%.2f", sum);
//        out.println(num);
        if (num.contains(".")) {
            int index = num.indexOf(".");
            if (index == num.length() - 2) {
                if (num.charAt(index + 1) == '0')
                    num = num.substring(0, index);
                else
                    num = num + "0";
            }
        }
//        out.println(num);

        int end = num.length() - 1;
        if (num.contains(".")) end = num.indexOf(".") - 1;
        ArrayList<Character> ans = new ArrayList<>();
        for (int i = num.length() - 1; i > end; i--) ans.add(num.charAt(i));
        count = 0;
        if (num.contains(".")) {
            int i = num.indexOf(".");
            if (num.charAt(i + 1) == '0' && num.charAt(i + 2) == '0')
                end = i - 1;
        }
        for (int i = end; i >= 0; i--) {
            ans.add(num.charAt(i));
            count++;
            if (count == 3 && i != 0) ans.add('.');
        }
        for (int i = ans.size() - 1; i >= 0; i--) out.print(ans.get(i));
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbB().main1();
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
