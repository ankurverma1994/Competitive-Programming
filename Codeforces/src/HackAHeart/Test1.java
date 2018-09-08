package HackAHeart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Test1 {
    public static double ret = 100000000000.0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        args = br.readLine().split(" ");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(args[i]);
        recurse(0, arr, n, 0, 0, 0, 0);
        System.out.printf("%.6f",ret);
    }

    public static void recurse(int x, int arr[], int n, int a, int b, int c1, int c2) {
        if (x == n) {
            if (c1 > 0 && c2 > 0) {
                double ratio = (double) a / b;
                double q = 1 - ratio;
                ret = Math.min(ret, q);
                return;
            }
            return;
        } else {
            recurse(x + 1, arr, n, a + arr[x], b, c1 + 1, c2);
            recurse(x + 1, arr, n, a, b, c1, c2);
            recurse(x + 1, arr, n, a, b + arr[x], c1, c2 + 1);
        }
    }
}