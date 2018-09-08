package LTIME32;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class EXPALIN1 {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int a[] = get((int) 5e5);

        for (int tc = ii(); tc > 0; tc--) {
            char s[] = is().toCharArray();
            int n = s.length;
            long ans = n;

            for (int i = 0; i < a.length; i++) {

                if (a[i] > n) break;

                int indices[] = indexes(a[i], n);
                indices = decreaseByOne(indices);

                ans += countPalindromicSubsequences(s, indices);
            }

            out.println(ans);
        }
    }

    boolean palindrome(char s[], int a, int b, int g, int indices[]) {
        int i = a, j = b;
        while (indices[i] <= indices[j]) {
            if (s[indices[i]] != s[indices[j]])
                return false;
            i += g;
            j -= g;
        }
        return true;
    }

    long countPalindromicSubsequences(char s[], int indices[]) {
//        out.println(Arrays.toString(indices));
        long count = 0;
        for (int g = 1; g < indices.length; g++) {
            for (int i = 0; i < indices.length; i += g) {
                for (int j = i + g; j < indices.length; j += g) {
                    if (palindrome(s, i, j, g, indices)) {
//                        out.println(indices[i] + " " + indices[j] + " " + g);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    int[] decreaseByOne(int a[]) {
        for (int i = 0; i < a.length; i++)
            a[i]--;
        return a;
    }

    int[] indexes(int p, int len) {
        int a[] = new int[len + 1];
        int x = 0;
        for (long i = 1; i <= len; i *= p)
            a[x++] = (int) i;
        return Arrays.copyOf(a, x);
    }

    int[] get(int n) {
        boolean mark[] = new boolean[n + 1];
        int p[] = new int[n + 1];
        int len = 0;
        for (int i = 2; i <= n; i++) {
            if (!mark[i]) {
                p[len++] = i;
                for (long j = 1L * i * i; j <= n; j *= i)
                    mark[(int) j] = true;
            }
        }
        return Arrays.copyOf(p, len);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new EXPALIN1().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}