package SPOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class JAVAC {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String s = obj.readLine();
        while (s != null) {
            char c[] = s.toCharArray();
            boolean cpp = false, java = false, error = false;
            for (char i : c)
                if (!(('a' <= i && i <= 'z') || i == '_' || ('A' <= i && i <= 'Z'))) {
                    error = true;
                    break;
                }
            for (char i : c)
                if (i == '_') {
                    cpp = true;
                    break;
                }
            if (cpp) {
                for (char i : c)
                    if (!(('a' <= i && i <= 'z') || i == '_')) {
                        cpp = false;
                        error = true;
                        break;
                    }
            }
            for (char i : c)
                if ('A' <= i && i <= 'Z') {
                    java = true;
                    break;
                }
            if (java) {
                for (char i : c)
                    if (!(('a' <= i && i <= 'z') || ('A' <= i && i <= 'Z'))) {
                        java = false;
                        error = true;
                        break;
                    }
            }
            if (!('a' <= c[0] && c[0] <= 'z'))
                error = true;
            if (s.contains("__"))
                error = true;
            if (error)
                out.println("Error!");
            else if (cpp) {
                String a[] = s.split("_");
                out.print(a[0]);
                for (int i = 1; i < a.length; i++)
                    out.print(((char) (a[i].charAt(0) - 32)) + a[i].substring(1));
                out.println();
            } else if (java) {
                for (char i : c) {
                    if ('A' <= i && i <= 'Z') {
                        out.print("_" + (char) (i + 32));
                    } else
                        out.print(i);
                }
                out.println();
            } else {
                out.println(s);
            }
            s = obj.readLine();
        }
        out.close();
    }
}