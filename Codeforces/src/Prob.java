import java.util.*;
import java.io.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s[] = br.readLine().split(" ");
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(s[i]);
        Arrays.sort(a);
        long pre[] = new long[n];
        long sum =0;
        for (int i = 0; i < n; i++)
        {
            int x = a[i];
            sum += x;
            if (i > 0)
                pre[i] += (pre[i - 1] + x);
            else
                pre[i] = x;
        }
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++)
        {
            long k = Long.parseLong(br.readLine());
            if (k > sum)
            {
                System.out.println("-1");
                continue;
            }
            int val = (int) Math.ceil((double) k / n);
            while (true)
            {
                int idx = Arrays.binarySearch(a, val);
//                System.out.println(val+" "+idx);
                if (idx < 0)
                    idx = -idx - 1;
                if (idx >= n)
                {
                    System.out.println(-1);
                    break;
                }
                long sum1 = (n - idx) * val;
                sum1 += ((idx > 0) ? pre[idx - 1] : 0);
                if (sum1 >= k)
                {
                    System.out.println(val);
                    break;
                }
                else{
                    val++;
                }
            }
        }
    }
}
