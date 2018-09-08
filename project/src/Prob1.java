import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Prob1 {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(obj.readLine());
        String name[] = obj.readLine().split(" ");
        ArrayList<String> male = new ArrayList<>();
        ArrayList<String> female = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = name[i];
            if (s.endsWith("ki"))
                female.add(s);
            if (s.endsWith("ka"))
                male.add(s);
        }
        int count = 0;
        for (int i = 0; i < male.size(); i++) {
            String s = male.get(i);
            int l = s.length();
            s = s.substring(0, l - 2) + "ki";
            if (female.contains(s)) {
                count++;
                female.remove(s);
            }
        }
        System.out.println(count);
    }
}