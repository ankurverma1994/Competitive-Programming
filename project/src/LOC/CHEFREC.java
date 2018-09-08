package LOC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by ankurverma1994 on 12/3/16.
 */
class CHEFREC {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(obj.readLine());
        for (int tc = 1; tc <= t; tc++) {
            String line[] = obj.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);

            int a[][] = new int[n][m];

            for (int i = 0; i < n; i++) {
                line = obj.readLine().split(" ");
                for (int j = 0; j < line.length; j++)
                    a[i][j] = Integer.parseInt(line[j]);
            }

            for (int sum = 0; sum <= (n + m - 2); sum++) {
                for (int i = 0; i < n; i++) {
                    int j = sum - i;
                    if (0 <= j && j < m)
                        out.print(a[i][j] + " ");
                }
            }
            out.println();
        }
        out.close();
    }
}
