package ACM15AMR;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class AMGIT {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            HashMap<String, Integer> map = new HashMap<>();
            int n = ii();
            String inp[] = new String[n];
            boolean a[] = new boolean[n];
            boolean found = false;
            for (int i = 0; i < n; i++) {
                a[i] = is().equals("stage");
                found = found | a[i];
                inp[i] = is();
            }
            if (!found) {
                out.println("0");
                continue;
            }
            int curr = 1;
            for (int i = 0; i < n; i++) {
                String k[] = inp[i].split("/");
                for (String x : k) if (!x.equals("") && !map.containsKey(x)) map.put(x, curr++);
            }
            g = new ArrayList[curr];
            set = new int[curr];
            Arrays.fill(set, 1);
            for (int i = 0; i < curr; i++) g[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int last = 0;
                String k[] = inp[i].split("/");
                for (String x : k) {
                    if (!x.equals("")) {
                        int next = map.get(x);
                        if (!g[last].contains(next))
                            g[last].add(next);
                        last = next;
                    }
                }
                set[last] = a[i] ? 1 : 0;
            }
            dfs0(0);
//            out.println(map.toString());
//            out.println(Arrays.deepToString(g));
//            out.println(Arrays.toString(set));
            ans = 0;
            dfs(0, 0);
            int ANS = ans;
//            out.print(ans + " ");
            ans = 1;
            dfs(0, 1);
//            out.println(ans);
            ANS = Math.min(ANS, ans);
            out.println(ANS);
        }
    }

    int set[];
    ArrayList<Integer> g[];
    int ans;

    void dfs0(int curr) {
        if (g[curr].size() == 0)
            return;
        boolean ans = false;
        for (int next : g[curr]) {
            dfs0(next);
            ans = ans | set[next] == 1;
        }
        if (!ans) set[curr] = 0;
    }

    // type 1 set
    void dfs(int curr, int type) {
        if (set[curr] != type) {
            ans++;
            type = 1 - type;
        }
        for (int next : g[curr])
            dfs(next, type);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new AMGIT().main1();
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
