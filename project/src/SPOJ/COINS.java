package SPOJ;

import java.io.*;

class COINS {
    static long mem[] = new long[10000000];

    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String s;
        for (int i = 0; i < 12; i++)
            mem[i] = i;
        while ((s = obj.readLine()) != null) {
            int n = Integer.parseInt(s);
            out.println(coins(n));
        }
        out.close();
    }

    static long coins(int n) {
        if (n < 12)
            return n;
        if (n < 10000000) {

            if (mem[n] != 0)
                return mem[n];
        }
        if (n < 10000000) {
            mem[n] = (coins(n / 2) + coins(n / 3) + coins(n / 4));
            return mem[n];
        }
        return (coins(n / 2) + coins(n / 3) + coins(n / 4));
    }
}