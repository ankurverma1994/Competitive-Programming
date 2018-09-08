package IPC15P1A;/**
 * Created by ankurverma1994 on 25/11/15.
 */

import java.io.*;
import java.util.*;

class BCD {
    //------------> Solution starts here!!
    void solve() {
        int n = ii(),k=ii(), a[] = iia(n);
        height = (int)(Math.ceil(Math.log(n) / Math.log(2)));
        maxsize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxsize];
        ENDINDEX = n - 1;
        constructSegmentTree(a);
        int i=0;
        boolean found=false;
        for(;i<maxsize;i++) {
            if(tree[i]>=k) {
                found=true;
                break;
            }
        }
        if(!found)
            out.println("0");
        else {
            System.out.println(i);
            int left=i,right=i;
            while(left<maxsize)
                left=2*left+1;
            if(left>=maxsize)
                left=left/2;

            while (right<maxsize)
            {
                right=2*right+2;
//                System.out.println(right);
            }
            if(right>=maxsize)
                right=right/2-1;
            int zero=0,count=0;
            System.out.println(Arrays.toString(tree));
            System.out.println(left+"  "+right);
            for(int j=left;j<=right;j++)
                if(tree[j]==0)
                    zero++;
                else
                    count++;
            out.println(count+zero/2);
        }
    }

    int[] tree;
    int maxsize;
    int height;
    int STARTINDEX = 0;
    int ENDINDEX;
    int ROOT = 0;

    private int leftchild(int pos) {
        return 2 * pos + 1;
    }

    private int rightchild(int pos) {
        return 2 * pos + 2;
    }

    private int mid(int start, int end) {
        return (start + (end - start) / 2);
    }

    private int constructSegmentTreeUtil(int[] elements, int startIndex,

                                         int endIndex, int current) {
        if (startIndex == endIndex) {
            tree[current] = elements[startIndex];
            return tree[current];
        }
        int mid = mid(startIndex, endIndex);
        tree[current] = gcd(constructSegmentTreeUtil(elements, startIndex,

                mid, leftchild(current))
                , constructSegmentTreeUtil(elements, mid + 1,

                        endIndex, rightchild(current)));
        return tree[current];
    }

    public void constructSegmentTree(int[] elements) {
        constructSegmentTreeUtil(elements, STARTINDEX, ENDINDEX, ROOT);
    }
    int gcd(int a, int b) {
        while (b > 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new BCD().main1();
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
