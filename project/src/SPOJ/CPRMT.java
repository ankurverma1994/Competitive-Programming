package SPOJ;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class CPRMT {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        String s = reader.readLine();
        while (s != null) {
            char a[] = s.toCharArray();
            char b[] = reader.readLine().toCharArray();
            int fa[] = new int[26];
            int fb[] = new int[26];
            int f[] = new int[26];
            for (char c : a) fa[c - 'a']++;
            for (char c : b) fb[c - 'a']++;
            for (int i = 0; i < 26; i++) f[i] = Math.min(fa[i], fb[i]);
            for (int i = 0; i < 26; i++)
                for (int j = 0; j < f[i]; j++)
                    writer.print((char) (i + 'a'));
            writer.println();
            s = reader.readLine();
        }
        writer.flush();
        writer.close();
    }
}