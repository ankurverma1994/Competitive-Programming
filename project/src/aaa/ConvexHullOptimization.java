package aaa;

import java.util.Arrays;
import java.util.Comparator;

public class ConvexHullOptimization {

    long[] A = new long[1000000];
    long[] B = new long[1000000];
    int len;
    int ptr;

    // a descends
    public void addLine(long a, long b) {
        // intersection of (A[len-2],B[len-2]) with (A[len-1],B[len-1]) must lie to the left of intersection of (A[len-1],B[len-1]) with (a,b)
        while (len >= 2 && (B[len - 2] - B[len - 1]) * (a - A[len - 1]) >= (B[len - 1] - b) * (A[len - 1] - A[len - 2])) {
            --len;
        }
        A[len] = a;
        B[len] = b;
        ++len;
    }

    // x ascends
    public long minValue(long x) {
        ptr = Math.min(ptr, len - 1);
        while (ptr + 1 < len && A[ptr + 1] * x + B[ptr + 1] <= A[ptr] * x + B[ptr]) {
            ++ptr;
        }
        return A[ptr] * x + B[ptr];
    }

    // Usage example
    public static void main(String[] args) {
        ConvexHullOptimization h = new ConvexHullOptimization();
        h.addLine(3, 0);
        h.addLine(2, 1);
        h.addLine(3, 2);
        h.addLine(0, 6);
        System.out.println(h.minValue(0));
        System.out.println(h.minValue(1));
        System.out.println(h.minValue(2));
        System.out.println(h.minValue(3));
        System.out.println();
    }


    public static long[][] convexHull(long[][] co) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = co.length;
        if (n <= 1) return co;
        Arrays.sort(co, new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                return Long.compare(a[1], b[1]);
            }
        });

        int[] inds = new int[n + 1];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (p >= 1 && co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1]) continue;
            while (p >= 2 && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) >= 0) p--; // if you need point on line
            inds[p++] = i;
        }

        int inf = p + 1;
        for (int i = n - 2; i >= 0; i--) {
            if (co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1]) continue;
            while (p >= inf && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) >= 0) p--; // if you need point on line
            inds[p++] = i;
        }

        long[][] ret = new long[p - 1][];
        for (int i = 0; i < p - 1; i++) ret[i] = co[inds[i]];
        return ret;
    }

    public static int ccw(long ax, long ay, long bx, long by, long tx, long ty) {
        return Long.signum((tx - ax) * (by - ay) - (bx - ax) * (ty - ay));
    }

    public static int ccw(long[] a, long[] b, long[] t) {
        return Long.signum((t[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (t[1] - a[1]));
    }

    public static int ccw(int[] a, int[] b, int[] t) {
        return Long.signum((long) (t[0] - a[0]) * (b[1] - a[1]) - (long) (b[0] - a[0]) * (t[1] - a[1]));
    }

}