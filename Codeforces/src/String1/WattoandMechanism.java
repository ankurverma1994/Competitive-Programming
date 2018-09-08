package String1;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

public class WattoandMechanism {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        root = new Trie();
        for (int i = 0; i < n; i++)
            add(root, is().toCharArray(), 0);
        for (int i = 0; i < m; i++) {
            char s[] = is().toCharArray();
            boolean ans = findExactlyoneDifference(root, s, 0, 0);
            out.println(ans ? "YES" : "NO");
        }
    }

    Trie root;
    int unique = 0;

    class Trie {
        Trie child[];
        boolean isleaf;

        Trie() {
            child = new Trie[3];
            isleaf = false;
        }
    }

    public void add(Trie curr, char a[], int pos) {
        if (pos == a.length) {
            if (!curr.isleaf) unique++;
            curr.isleaf = true;
            return;
        }
        if (curr.child[a[pos] - 'a'] == null)
            curr.child[a[pos] - 'a'] = new Trie();
        add(curr.child[a[pos] - 'a'], a, pos + 1);
    }

    boolean findExactlyoneDifference(Trie curr, char a[], int pos, int diff) {
        if (diff > 1)
            return false;
        boolean ret = false;
        if (pos == a.length) return curr.isleaf && diff == 1;
        for (int i = 0; i < 3; i++) {
            if (curr.child[i] != null) {
                if (i == a[pos] - 'a')
                    ret = ret | findExactlyoneDifference(curr.child[i], a, pos + 1, diff);
                else
                    ret = ret | findExactlyoneDifference(curr.child[i], a, pos + 1, diff + 1);
            }
        }
        return ret;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new WattoandMechanism().main1();
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
