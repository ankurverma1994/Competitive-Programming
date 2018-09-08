package CodeJam2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class CoinJam {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii();
            j = ii();
            solve(n);
        }
    }

    void solve(int n) {
        int sb[] = new int[n];
        sb[n - 1] = 1;
        solve(sb, n - 1);
    }

    int count = 0;
    int j;
    int set[] = {0, 1};

    void solve(int sb[], int n) {
        if (n == 1) {
            sb[n - 1] = 1;
            int b[] = reverse(sb);
            ArrayList<Long> list = new ArrayList<>();
            for (int i = 2; i <= 10; i++) {
//                long num = 0;
                BigInteger num = BigInteger.ZERO;
                for (int j = 0; j < b.length; j++) {
                    num = num.add(BigInteger.valueOf(b[j]).multiply(BigInteger.valueOf(i).pow(j)));
//                    num = num + (b[j]) * (long) Math.pow(i, j);
                }
                long a = isPrime(num);
                if (a != -1) {
                    list.add(a);
                }
            }
            if (list.size() == 9) {
                for (int i : sb) out.print(i);
                out.print(" ");
                for (int i = 0; i < list.size(); i++)
                    out.print(list.get(i) + " ");
                out.println();
                count++;
            }
            return;
        }
        if (count == j) return;
        sb[n - 1] = 0;
        solve(sb, n - 1);
        if (count == j) return;
        sb[n - 1] = 1;
        solve(sb, n - 1);
        if (count == j) return;
    }

    long isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) == 0)
            return 1;
        if (number.compareTo(BigInteger.valueOf(2)) == 0 || number.compareTo(BigInteger.valueOf(3)) == 0 || number.compareTo(BigInteger.valueOf(5)) == 0)
            return -1;
        for (long i = 2; i * i <= 1000; i++) {
            if (number.mod(BigInteger.valueOf(i)).compareTo(BigInteger.ZERO) == 0)
                return i;
        }
        return -1;
    }

    int[] reverse(int a[]) {
        int b[] = new int[a.length];
        for (int i = 0; i < a.length; i++)
            b[a.length - 1 - i] = a[i];
        return b;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CoinJam().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
//        out = new PrintWriter(System.out);
        out = new PrintWriter("/home/ankurverma1994/CodeJam/output1.txt");
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/CodeJaminput.txt") : new ByteArrayInputStream(check.getBytes());
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
}
