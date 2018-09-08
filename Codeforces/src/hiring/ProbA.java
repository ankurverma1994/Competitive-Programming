package hiring;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class ProbA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp = br.readLine();
        PrintWriter out = new PrintWriter("records_" + inp);
        BufferedReader obj = new BufferedReader(new FileReader(inp));
        String line;
        HashMap<String, Integer> hash = new HashMap<>();
        while ((line = obj.readLine()) != null) {
            String a[] = line.split(" ");
            if (!hash.containsKey(a[0])) hash.put(a[0], 0);
            hash.put(a[0], hash.get(a[0]) + 1);
        }
        for (Map.Entry<String, Integer> x : hash.entrySet()) {
            out.println(x.getKey() + " " + x.getValue());
        }
        out.flush();
    }
}