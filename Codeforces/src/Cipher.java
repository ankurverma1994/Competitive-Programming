import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Cipher {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            char a[] = br.readLine().toCharArray();
            char b[] = br.readLine().toCharArray();
            int diff = (b[0] - a[0] + 26) % 26;
            boolean ans = true;
            for (int i = 1; i < a.length; i++)
                if (diff != ((b[i] - a[i] + 26) % 26)) {
                    ans = false;
                    break;
                }
            System.out.println(ans ? diff : -1);
        }
    }
}