package CDQU1601;
/**
 * Created by ankurverma1994
 */

import java.io.*;

class CDQU09 {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = Integer.parseInt(obj.readLine()); tc > 0; tc--) {
            char s[] = obj.readLine().toCharArray();
            for (int i = 0; i < s.length; i++) {
                char c = s[i];
                if (c == ' ')
                    out.print("$");
                else {
                    int k = c - 'a';
                    out.print(k * k);
                    if (i == s.length - 1) {
                        break;
                    }
                    if(s[i+1]==' ') continue;
                    out.print("-");
                }
            }
            out.println();
        }
        out.flush();
    }
}