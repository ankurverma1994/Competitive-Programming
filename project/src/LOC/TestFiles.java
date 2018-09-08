package LOC;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by ankurverma1994 on 12/3/16.
 */
public class TestFiles {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/ankurverma1994/in2.txt");
        out.println(10);
        for (int tc = 1; tc <= 10; tc++) {
            int n = (int) (Math.random() * 100000) + 1;
            out.println(n);
            long sum = 0;
            for (int i = 0; i < n - 1; i++) {
                int c = (int) (Math.random() * 1000000) + 1;
                sum += c;
                out.print(c + " ");
            }
            int c = (int) (Math.random() * 1000000) + 1;
            int random = (int) (Math.random() * 2);
            if (random == 0)
                out.println(c);
            else
                out.println(n - sum % n);
        }
        out.close();
    }
}
