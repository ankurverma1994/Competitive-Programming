package LOC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

class VSQ {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(obj.readLine());
        int a[] = new int[n];
        String in[] = obj.readLine().split(" ");
        if (n != in.length) {
            // throwing exception
            System.out.println(a[-1]);
        }
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(in[i]);

        Arrays.sort(a);
        int qu = Integer.parseInt(obj.readLine());
        for (int Q = 0; Q < qu; Q++) {
            in = obj.readLine().split(" ");
            int x = Integer.parseInt(in[0]), y = Integer.parseInt(in[1]);
            int p = BinarySearchLowerBound(a, x), q = BinarySearchUpperBound(a, y);
            out.println(q - p + 1);
        }

        out.flush();
        out.close();
    }

    /* Tells the first occurence of element.
    *  If element is not found then tells the position
    *  where it should be inserted :)*/
    public static int BinarySearchLowerBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public static int BinarySearchUpperBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }
}