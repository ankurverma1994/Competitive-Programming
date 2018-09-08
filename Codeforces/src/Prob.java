import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        args = br.readLine().split(" ");
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(args[i]);
        long vol = Long.parseLong(br.readLine());
        for (int r = 0; r < n; r++) {
            for (int h = r + 1; h < n; h++) {
                if (vol * 7 == 22L * a[r] * a[r] * a[h]) {
                    System.out.println(a[r] + " " + a[h]);
                }
            }
        }
    }
}