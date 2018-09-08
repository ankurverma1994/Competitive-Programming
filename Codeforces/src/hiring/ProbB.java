package hiring;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {
    //------------> Solution starts here!!
    void solve() {
        num = new ArrayList<>();
        num.add(BigInteger.valueOf(4));
        for (int len = 2; len <= 100; len++)
            generate("4", 1, len, false);
        Collections.sort(num);
        for (int tc = ii(); tc > 0; tc--) {
            long x = il();
            BigInteger X = BigInteger.valueOf(x);
            for (int i = 0; i < num.size(); i++) {
                if (num.get(i).mod(X).equals(BigInteger.ZERO)) {
                    String z = num.get(i).toString();
                    int end = z.lastIndexOf('4');
                    int len = z.length();
                    int a = 2 * (end+1);
                    int b = len - end;
                    out.println(a + b);
                    break;
                }
            }
        }
    }

    ArrayList<BigInteger> num;

    void generate(String a, int i, int len, boolean fourDONE) {
        if (i == len) {
            num.add(new BigInteger(a));
            return;
        }
        generate(a + "0", i + 1, len, true);
        if (!fourDONE)
            generate(a + "4", i + 1, len, fourDONE);
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
}
