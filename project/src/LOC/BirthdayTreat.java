package LOC;

import java.io.*;

/**
 * Created by ankurverma1994 on 12/3/16.
 */
class BirthdayTreat {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new FileReader("/home/ankurverma1994/in3.txt"));
        PrintWriter out = new PrintWriter("/home/ankurverma1994/out3.txt");
//        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(obj.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(obj.readLine());
            String line[] = obj.readLine().split(" ");
            int c[] = new int[n];
            for (int i = 0; i < n; i++)
                c[i] = Integer.parseInt(line[i]);


            long sum = 0;
            for (int i : c)
                sum += i;

            if (sum % n == 0) {

                long avg = sum / n;
                long moves = 0;
                for (int i = 0; i < n; i++) {
                    if (avg > c[i]) {
                        moves += (avg - c[i]);
                    }
                }
                out.println(moves);

            } else {
                out.println("No Treat");
            }
        }
        out.flush();
    }
}
