package Codevita2016;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class WhereisMycar {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            parking = new HashMap<>();
            ans = new HashMap<>();
            int X = ii(), Y = ii(), I = ii(), C = ii(), N = ii();
            long total = 1L * (X / I + 1) * (Y / I + 1);
            out.println(total);
            for (int i = 0; i < N; i++) {
                char c = ic();
                if (c == 'P') {
                    int x = ii(), y = ii();
                    String number = is();
                    solve(X, Y, I, C, number, x, y);
                } else if (c == 'R') {
                    String number = is();
                    ArrayList<Integer> ans1 = ans.get(number);
                    out.print(ans1.get(0) + " " + ans1.get(1) + " ");
                    out.println(ans1.get(2) % 10 + " " + ans1.get(3) % 10);
                }
            }
        }
    }

    HashMap<String, ArrayList<String>> parking;
    HashMap<String, ArrayList<Integer>> ans;

    void solve(int X, int Y, int I, int C, String number, int x, int y) {
        int[] co = parkingCo(x, y, I);
//        out.println(Arrays.toString(co));
        int a = co[0], b = co[1];
        String key = String.valueOf(a) + " " + String.valueOf(b);
        ArrayList<String> list = new ArrayList<>();
        if (parking.containsKey(key)) {
            list = parking.get(key);
        }
        list.add(number);
        int length = list.size();
        ArrayList value = new ArrayList();
        value.add(a);
        value.add(b);
        int floor = length / C;
        if (length % C == 0)
            floor--;
        value.add(floor);
        int slot = length % C;
        if (slot == 0)
            slot = C;
        value.add(slot);
        parking.put(key, list);
        ans.put(number, value);
    }

    int[] parkingCo(int x, int y, int I) {
        int x2 = (int) Math.ceil(x / (double) I);
        int x1 = x2 - 1;
        int y2 = (int) Math.ceil(y / (double) I);
        int y1 = y2 - 1;
        x1 *= I;
        y1 *= I;
        x2 *= I;
        y2 *= I;
        long D = dist(x, y, x1, y1);
        int ansx = x1, ansy = y1;
        if (dist(x, y, x1, y2) < D) {
            D = dist(x, y, x1, y2);
            ansx = x1;
            ansy = y2;
        }
        if (dist(x, y, x2, y1) < D) {
            D = dist(x, y, x2, y1);
            ansx = x2;
            ansy = y1;
        }
        if (dist(x, y, x2, y2) < D) {
            D = dist(x, y, x2, y2);
            ansx = x2;
            ansy = y2;
        }
        return new int[]{ansx, ansy};
    }

    long dist(int x1, int y1, int x2, int y2) {
        return (long) Math.abs(x1 - x2) + (long) Math.abs(y1 - y2);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new WhereisMycar().main1();
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
