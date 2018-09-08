package JAN16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CHMKTPRS {
    public static void main(String[] args) throws IOException {
        double t1=System.currentTimeMillis();
        BufferedReader obj = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(obj.readLine());
        long a[][] = new long[3 * n][2];
        String line[] = obj.readLine().split(" ");
        long sum=0;
        for (int i = 0; i < 3 * n; i++) {
            a[i][0] = Integer.parseInt(line[i]);
            a[i][1] = i + 1;
            sum+=a[i][0];
        }
        sum/=n;
        Arrays.sort(a, (aa, bb) -> Long.compare(aa[0], bb[0]));
        ArrayList<triple> triples = new ArrayList<>();
        boolean mark[] = new boolean[3 * n];
        double t2=System.currentTimeMillis();
        while (t2-t1<=9000){
            int i=(int)(Math.random()*n*3);
            int j=(int)(Math.random()*n*3);
//            out.println(i+" "+j);
            long key = sum - a[i][0] - a[j][0];
            int index = BinarySearchLowerBound(a, key);
            if (i == j || i == index || j == index || index>=n) {
                t2=System.currentTimeMillis();
                continue;
            }
            if (a[index][0] != key) {
                t2=System.currentTimeMillis();
                continue;
            }
            if (mark[index] || mark[i] || mark[j]) {
                t2=System.currentTimeMillis();
                continue;
            }
            triples.add(new triple(a[i][1], a[index][1], a[j][1]));
            mark[index] = mark[i] = mark[j] = true;
            t2=System.currentTimeMillis();
        }
        out.println(triples.size());
        Iterator<triple> i = triples.iterator();
        while (i.hasNext()) {
            triple temp = i.next();
            out.print(temp.a + " " + temp.b + " " + temp.c + " ");
        }
        out.close();
    }

    static class triple {
        long a, b, c;

        triple(long x, long y, long z) {
            a = x;
            b = y;
            c = z;
        }
    }

    public static int BinarySearchLowerBound(long[][] a, long v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h][0] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }
}