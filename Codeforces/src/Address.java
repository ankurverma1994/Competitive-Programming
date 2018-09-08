import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Address {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String a[] = s.split("\\.");
        boolean ans = true;
        if (a.length != 4) ans = false;
        else {
            for (int i = 0; i < 4; i++) {
                int x = Integer.parseInt(a[i]);
                if (x < 0 || x > 255) ans = false;
            }
        }
        System.out.println(ans ? "YES" : "NO");
    }
}