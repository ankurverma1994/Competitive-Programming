package aaa;

import java.util.Scanner;

class SquareRootDecposition {
    int s[], a[], k, n;

    int operation(int a, int b) {
        return a + b;
    }

    int inverse(int a, int b) {
        return a - b;
    }

    //a[i]=x;
    void update(int i, int x) {
        s[i / k] = operation(inverse(s[i / k], a[i]), x);
        a[i] = x;
//        System.out.println("UPDATE DONE");
    }

    int query(int lo, int hi) {
        int ans = 0;
        int i = lo;
        while ((i + 1) % k != 0 && i <= hi) {
            ans = operation(ans, a[i]);
            i++;
        }
        while (i + k <= hi) {
            ans = operation(ans, s[i / k]);
            i += k;
        }
        while (i <= hi) {
            ans = operation(ans, a[i]);
            i++;
        }
//        System.out.println("QUERY DONE");
        return ans;
    }

    //k=ceil(sqrt(n))
    SquareRootDecposition(int n, int a[], int k) {
        this.n = n;
        this.k = k;
        this.a = new int[n];
        this.s = new int[k];
        for (int i = 0; i < n; i++) this.a[i] = a[i];
        for (int i = 0, j = 0; i < n; j++) {
            int count = 0;
            while (count < k && i < n) {
                count++;
                s[j] = operation(s[j], a[i]);
                i++;
            }
        }
//        System.out.println("OKAY");
    }
}

class check {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        int k = (int) Math.ceil(Math.sqrt(n));
        SquareRootDecposition sqrt = new SquareRootDecposition(n, a, k);
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            if (type == 1) {
                System.out.println(sqrt.query(in.nextInt() - 1, in.nextInt() - 1));
            }
            if (type == 2) {
                sqrt.update(in.nextInt() - 1, in.nextInt());
            }
        }
    }
}