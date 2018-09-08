package PACICPC;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class SimonSays {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String simon = "simon says";
        int l = simon.length();
        for (int tc = Integer.parseInt(obj.readLine()); tc > 0; tc--) {
            String s = obj.readLine();
            int in = s.indexOf(simon);
            if (in != -1)
                out.println(s.substring(Math.min(in + 1 + l, s.length())));
            else
                out.println();
        }
        out.close();
    }
}
