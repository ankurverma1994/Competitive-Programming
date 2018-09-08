package aaa;
/**
 * @author ankurverma1994
 *  Mo's algo implementation
 */

import java.io.*;
import java.util.*;

class STDPRGS {
    //------------> Solution starts here!!
    TreeSet<Integer> AVL;
    long current_answer = 0;
    int counts[];

    void solve() {
        int n = ii(), arr[] = iia(n);
        int BLOCK_SIZE = (int) (2*Math.floor(Math.sqrt(n)));
        int Q = ii();
        AVL = new TreeSet<>();
        counts = new int[1000010];
        int queryl[][] = new int[Q][2];
        int queryr[] = new int[Q];
        long mo_answer[] = new long[Q];
        for (int i = 0; i < Q; i++) {
            queryl[i][0] = ii() - 1;
            queryl[i][1] = i;
            queryr[i] = ii() - 1;
        }
        Arrays.sort(queryl, (int a[], int b[]) -> {
            int block_a = a[0] / BLOCK_SIZE;
            int block_b = b[0] / BLOCK_SIZE;
            if (block_a != block_b) {
                if (block_a < block_b)
                    return 1;
                return -1;
            }
            if (queryr[a[1]] < queryr[b[1]])
                return 1;
            return -1;
        });
        int mo_left = 0, mo_right = -1;
        for (int i = 0; i < Q; i++) {
            int left = queryl[i][0];
            int right = queryr[queryl[i][1]];
            while (mo_right < right) {
                mo_right++;
                add(arr[mo_right]);
            }
            while (mo_right > right) {
                remove(arr[mo_right]);
                mo_right--;
            }
            while (mo_left < left) {
                remove(arr[mo_left]);
                mo_left++;
            }
            while (mo_left > left) {
                mo_left--;
                add(arr[mo_left]);
            }
            mo_answer[queryl[i][1]] = current_answer;
        }
        for (int i = 0; i < Q; i++)
            out.println(mo_answer[i]);
    }

    void add(int x) {
        if (counts[x] > 0) {
            counts[x]++;
            return;
        }
        counts[x]++;
        Integer prev = AVL.lower(x), next = AVL.higher(x);
        AVL.add(x);
        if (prev != null)
            current_answer += (x - prev) * (long) (x - prev);
        if (next != null)
            current_answer += (next - x) * (long) (next - x);
        if (next != null && prev != null)
            current_answer -= (long) (next - prev) * (long) (next - prev);
    }

    void remove(int x) {
        counts[x]--;
        if (counts[x] == 0) {
            AVL.remove(x);
            Integer prev = AVL.lower(x), next = AVL.higher(x);
            if (prev != null)
                current_answer = current_answer - (x - prev) * (long) (x - prev);
            if (next != null)
                current_answer = current_answer - (next - x) * (long) (next - x);
            if (next != null && prev != null)
                current_answer += (long) (next - prev) * (next - prev);
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new STDPRGS().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1)
            throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0)
            return -1;
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
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
        for (int i = 0; i < n; i++)
            a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = ii();
        return a;
    }
}
