package Zenefits2015;

/**
 * Created by ankurverma1994 on 21/11/15.
 */
import java.io.*;
import java.util.*;
class KnightorKnave {
    public static void main(String[] args)throws IOException {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int T=Integer.parseInt(obj.readLine());
        for(int tc=0;tc<T;tc++){
            int n=Integer.parseInt(obj.readLine());
            int a[]=new int[n+1];
            char b[]=new char[n+1];
            char c[]=new char[n+1];
            String s[]=obj.readLine().split(" ");
            for(int i=1;i<=n;i++){
                a[i]=Integer.parseInt(s[i-1]);
            }
            Arrays.fill(b,'A');
            for(int i=1;i<=n;i++){
                if(b[i]=='A'){
                    if(a[i]<0)
                        b[-a[i]]='B';
                    else
                        b[a[i]]='A';
                }
                else{
                    if(a[i]<0)
                        b[-a[i]]='A';
                    else
                        b[a[i]]='B';
                }
            }
            Arrays.fill(c,'B');
            for(int i=1;i<=n;i++){
                if(c[i]=='A'){
                    if(a[i]<0)
                        c[-a[i]]='B';
                    else
                        c[a[i]]='A';
                }
                else{
                    if(a[i]<0)
                        c[-a[i]]='A';
                    else
                        c[a[i]]='B';
                }
            }
            int ba=0,ca=0;
            for(char i:b)
            if(i=='A')
                ba++;
            for(char i:c)
            if(i=='A')
                ca++;
            if(ba>=ca)
            for(int i=1;i<=n;i++)
                out.print(b[i]);
            else
                for(int i=1;i<=n;i++)
                    out.print(c[i]);
            out.println();
        }
        out.flush();
        out.close();
    }
}
