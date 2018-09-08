package LTIME50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by ankurverma1994
 * My code is awesome!
 */
class ProbA {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = Integer.parseInt(bufferedReader.readLine()); tc > 0; tc--) {
            String s[] = bufferedReader.readLine().split(" ");
            int n = s.length - 1;
            boolean valid[] = new boolean[n + 1];
            Arrays.fill(valid, true);
            for (int i = 0; i <= n; i++) {
                if (Integer.parseInt(s[i]) == n) {
                    valid[i] = false;
                    break;
                }
            }
            int max = 0;
            for (int i = 0; i <= n; i++) {
                if (valid[i]) {
                    max = Math.max(max, Integer.parseInt(s[i]));
                }
            }
            out.println(max);
        }
        out.flush();
        out.close();
    }
}