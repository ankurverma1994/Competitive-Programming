package LTIME32;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class EXPALIN {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int pr[] = sieveEratosthenes(500000 + 10);
        boolean mark[]=new boolean[500000 + 10];
        Arrays.fill(mark,true);
        for(int i=0;i<pr.length;i++){
            for (long m=1L*pr[i]*pr[i];m<mark.length;m=m*pr[i])
                mark[(int)m]=false;
        }
        for (int tc = ii(); tc > 0; tc--) {
            char s[] = is().toCharArray();
            long ans = 0;
//            int count = 0;
            for (int p=2;p<=s.length;p++) {
                if (mark[p]) {
                    if (p > s.length) break;
//                count++;
                    char a[] = new char[s.length / 2 + 10];
                    int len = 0;
                    for (int i = 0; i < s.length; ) {
                        a[len++] = s[i];
                        i = (i + 1) * p - 1;
                    }
                    char b[] = Arrays.copyOf(a, len);
                out.println(Arrays.toString(b) + " " + count2(b));
                    ans += count2(b);
                }
            }
//            if (count > 1)
//                ans = ans - (count - 1);
            out.println(ans+s.length);
        }
    }
    long count2(char str[]){
        long c=0;
        for(int g=1;g<=str.length;g++) {
            for (int i = 0; i < str.length; i++)
                for (int j = i + g; j < str.length; j+=g)
                    if (palindrome(str, i, j,g)) {
                        out.println(i+" "+j+" "+g);
                        c++;
                    }
        }
        return c;
    }

//    long count1(char str[]) {
//        // code picked from https://www.codechef.com/viewsolution/567126
//        long n = str.length;
//
//        long count[][] = new long[(int)n][(int)n];
//        for (int i = (int)(n - 1); i >= 0; i--) {
//            int c = 1;
//            count[i][i] = 1;
//            if (i != n - 1) {
//                if (str[i] == str[i + 1]) {
//                    count[i][i + 1] = 3;
//                    c++;
//                } else
//                    count[i][i + 1] = 2;
//            }
//            for (int j = i + 2; j < n; j++) {
//                if (str[j] == str[i])
//                    c += count[i + 1][j - 1] + 1;
//                count[i][j] = count[i + 1][j] + c;
//            }
//        }
//        return count[0][(int)(n - 1)]-str.length;
//    }
    boolean palindrome(char str[], int a, int b, int g){
        int i=a,j=b;
        while (i <= j) {
            if (str[i] != str[j]) {
                return false;
            }
            i+=g;
            j-=g;
        }
        return true;
    }

    int countPalindrome(char[] str) {
        int i, j, k, count = 0;
        for (i = 0; i < str.length; i++) {
            k = i - 1;
            j = i + 1;  //count odd length palindromes
            while (k >= 0 && j < str.length && str[k] == str[j]) {
                ++count;
                k--;
                j++;
            }

            k = i;
            j = i + 1; //count even length palindrome
            while (k >= 0 && j < str.length && str[k] == str[j]) {
                ++count;
                k--;
                j++;
            }
        }
        i = 0;
        j = str.length - 1;
        boolean palindrome = true;
        while (i <= j) {
            if (str[i] != str[j]) {
                palindrome = false;
                break;
            }
            i++;
            j--;
        }
        return count + (palindrome ? 1 : 0);
    }

    public static int[] sieveEratosthenes(int n) {
        /*  Code picked up from "uwi" submissions */
        if (n <= 32) {
            int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int i = 0; i < primes.length; i++) {
                if (n < primes[i]) {
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }
        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;
        int[] isnp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;
        int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int tp : tprimes) {
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for (int j = 0; j < sup; j += tp) {
                for (int i = 0; i < tp && i + j < sup; i++) {
                    isnp[j + i] |= ptn[i];
                }
            }
        }
        int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
        int h = n / 2;
        for (int i = 0; i < sup; i++) {
            for (int j = ~isnp[i]; j != 0; j &= j - 1) {
                int pp = i << 5 | magic[(j & -j) * 0x76be629 >>> 27];
                int p = 2 * pp + 3;
                if (p > n)
                    break;
                ret[pos++] = p;
                if ((long) p * p > n)
                    continue;
                for (int q = (p * p - 3) / 2; q <= h; q += p)
                    isnp[q >> 5] |= 1 << q;
            }
        }
        return Arrays.copyOf(ret, pos);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new EXPALIN().main1();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
        return inbuffer[ptrbuffer++];
    }

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int ii() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long il() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}