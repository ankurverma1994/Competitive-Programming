import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class LeetCode {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(obj.readLine());
        for (int T = 0; T < t; T++) {
            args = obj.readLine().split(" ");
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]) - 1;
            args = obj.readLine().split(" ");
            long a[] = new long[n];
            for (int i = 0; i < n; i++) a[i] = Long.parseLong(args[i]);
            Arrays.sort(a);
            long ans = Long.MAX_VALUE;
            for (int i = 0; i + k < n; i++) ans = Math.min(ans, a[i + k] - a[i]);
            System.out.println(ans);
        }
    }
}