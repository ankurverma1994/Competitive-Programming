package LOC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Roses {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(obj.readLine());
        for (int T = 0; T < t; T++) {
            int n = Integer.parseInt(obj.readLine());
            String s = obj.readLine();
            int a[] = new int[n];
            String in[] = obj.readLine().split(" ");
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(in[i]);

            long ans = 0;
            if (s.compareTo("Happy") == 0) {
                for (int counter = 0; counter < (1 << n); counter++) {
                    long sum = 0;
                    for (int j = 0; j < n; j++) {
                        if ((counter & (1 << j)) > 0) {
                            sum += a[j];
                        }
                    }
                    if (sum % 2 == 1)
                        ans = Math.max(ans, sum);
                }
            } else if (s.compareTo("Sad") == 0) {
                for (int counter = 0; counter < (1 << n); counter++) {
                    long sum = 0;
                    for (int j = 0; j < n; j++) {
                        if ((counter & (1 << j)) > 0) {
                            sum += a[j];
                        }
                    }
                    if (sum % 2 == 0)
                        ans = Math.max(ans, sum);
                }
            }
            out.println(ans == 0 ? "Sad" : ans);
        }

        out.flush();
        out.close();
    }
}