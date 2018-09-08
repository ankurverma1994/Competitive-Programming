import java.io.*;
import java.math.*;

class STRGAME {
    public static void main(String args[]) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader obj=new BufferedReader(new FileReader("/home/ankurverma1994/fac.in"));
        PrintWriter out = new PrintWriter(System.out);
        BigInteger fac[] = new BigInteger[201];
        fac[1] = BigInteger.ONE;
        for (int i = 2; i <= 200; i++)
            fac[i] = fac[i - 1].multiply(BigInteger.valueOf(i));
        for (int tc = Integer.parseInt(obj.readLine()); tc > 0; tc--) {
            int n = obj.readLine().length();
            out.println(fac[n].subtract(BigInteger.ONE));
            out.flush();
        }
    }
}