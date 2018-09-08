package Round253Div1;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class BoryaAndHanabi {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        String COLOR = "RGBYW";
        int n = ii();
        boolean cards[][] = new boolean[5][5];
        for (int i = 0; i < n; i++) {
            char s[] = is().toCharArray();
            cards[COLOR.indexOf(s[0])][s[1] - '1'] = true; //yeh cards hai
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << 10); i++) {
            int c = 0;
            boolean hint[] = new boolean[10];
            for (int j = 0; j < 10; j++) {
                if ((i & (1 << j)) > 0) {
                    hint[j] = true;
                    c++;
                }
            }
            boolean identify[][][] = new boolean[5][5][2];
            // 0 number
            // 1 color
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    identify[x][y][0] = identify[x][y][1] = !cards[x][y];
                }
            }
            // false matab abhi tak identify nhi huye hai
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (hint[x])
                        identify[x][y][0] = true;
                    if (hint[y + 5])
                        identify[x][y][1] = true;
                }
            }
            int left = 0;
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (!identify[x][y][0] && !identify[x][y][1])
                        left++;
                    if (identify[x][y][0] && !identify[x][y][1]) {
                        for (int k = 0; k < 5; k++)
                            if (identify[x][k][0] && !identify[x][k][1] && k != y)
                                left++;
                    }
                    if (!identify[x][y][0] && identify[x][y][1]) {
                        for (int k = 0; k < 5; k++)
                            if (!identify[k][y][0] && identify[k][y][1] && k != x)
                                left++;
                    }
                }
            }
            if (left <= 1)
                ans = Math.min(ans, c);
        }
        out.println(ans);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new BoryaAndHanabi().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}
