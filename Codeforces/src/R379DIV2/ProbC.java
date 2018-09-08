////package R379DIV2;
///**
// * Created by ankurverma1994
// * My code is awesome!
// */
//
//import java.io.*;
//import java.util.*;
//import java.math.*;
//
//public class ProbC {
//    final int mod = (int) 1e9 + 7;
//    final double eps = 1e-6;
//    final double pi = Math.PI;
//    final long inf = Long.MAX_VALUE / 2;
//
//    //------------> Solution starts here!!
//    @SuppressWarnings("Main Logic")
//    void solve() {
//        int n = ii(), m = ii(), k = ii(), x = ii(), s = ii();
//        int a[] = iia(m), b[] = iia(m), c[] = iia(k), d[] = iia(k);
//        int remaining[] = new int[k];
//        int count[] = new int[k];
//        for (int i = 0; i < k; i++) {
//            count[i] = n - c[i];
//            remaining[i] = s - d[i];
//        }
//        Pair pair[] = new Pair[m];
//        for (int i = 0; i < m; i++) pair[i] = new Pair(a[i], b[i]);
//        Arrays.sort(pair);
////        for (int i = 0; i < m; i++) {
////            out.print(pair[i].a + " ");
////        }
////        out.println();
////        for (int i = 0; i < m; i++) {
////            out.print(pair[i].b + " ");
////        }
////        out.println();
////        out.println(Arrays.toString(count));
////        out.println(Arrays.toString(remaining));
//        int min[] = new int[m];
//        min[0] = pair[0].a;
//        for (int i = 1; i < m; i++) min[i] = Math.min(min[i - 1], pair[i].a);
//        long ans = 1L * n * x;
////        out.println(Arrays.toString(min));
//        for (int i = 0; i < k; i++) {
//            if (remaining[i] <= 0) continue;
//            int index = BinarySearchUpperBound(pair, remaining[i]);
////            out.println("FUCK");
////            out.println(remaining[i] + " " + index);
//            if (index < 0) continue;
////            out.println(remaining[i] + " " + index);
//            ans = Math.min(ans, 1L * count[i] * min[index]);
//        }
//        for (int i = 0; i < m; i++)
//            if (b[i] <= s)
//                ans = Math.min(ans, 1L * n * a[i]);
//        for (int i = 0; i < k; i++)
//            if (d[i] <= s)
//                ans = Math.min(ans, 1L * (n - c[i]) * s);
//        out.println(ans);
//    }
//
//    public int BinarySearchUpperBound(Pair[] a, int v) {
//        int low = -1, high = a.length;
//        while (high - low > 1) {
//            int h = high + low >>> 1;
//            if (a[h].b <= v) {
//                low = h;
//            } else {
//                high = h;
//            }
//        }
//        return low;
//    }
//
//    class Pair implements Comparable<Pair> {
//        int a, b;
//
//        Pair(int a, int b) {
//            this.a = a;
//            this.b = b;
//        }
//
//        @Override
//        public int compareTo(Pair o) {
//            return Integer.compare(b, o.b);
//        }
//    }
//
//    //------------> Solution ends here!!
//    InputStream obj;
//    PrintWriter out;
//    String check = "";
//
//    public static void main(String[] args) throws IOException {
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
//                    new ProbC().main1();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (StackOverflowError e) {
//                    System.out.println("RTE");
//                }
//            }
//        }, "1", 1 << 26).start();
//    }
//
//    void main1() throws IOException {
//        out = new PrintWriter(System.out);
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
//        solve();
//        out.flush();
//        out.close();
//    }
//
//    byte inbuffer[] = new byte[1024];
//    int lenbuffer = 0, ptrbuffer = 0;
//
//    int readByte() {
//        if (lenbuffer == -1) throw new InputMismatchException();
//        if (ptrbuffer >= lenbuffer) {
//            ptrbuffer = 0;
//            try {
//                lenbuffer = obj.read(inbuffer);
//            } catch (IOException e) {
//                throw new InputMismatchException();
//            }
//        }
//        if (lenbuffer <= 0) return -1;
//        return inbuffer[ptrbuffer++];
//    }
//
//    int ii() {
//        int num = 0, b;
//        boolean minus = false;
//        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
//        if (b == '-') {
//            minus = true;
//            b = readByte();
//        }
//        while (true) {
//            if (b >= '0' && b <= '9') {
//                num = num * 10 + (b - '0');
//            } else {
//                return minus ? -num : num;
//            }
//            b = readByte();
//        }
//    }
//
//    int[] iia(int n) {
//        int a[] = new int[n];
//        for (int i = 0; i < n; i++) a[i] = ii();
//        return a;
//    }
//}
