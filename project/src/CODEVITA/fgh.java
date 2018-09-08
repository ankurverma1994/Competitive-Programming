import java.io.*;
import java.util.*;
import javax.swing.plaf.synth.Region;
/* *
 *
 * @author ankurverma1994
 */
class fgh {
    //------------> Solution starts here!!
    void solve(){
        int t=ii();
        int a=ii(),b=ii();
        for(int tc=0;tc<t;tc++)
        {
            String s=is();
            if(s.charAt(0)=='A')
            {
                char num=s.charAt(1);
                int A=0;
                for(int i=a;i<=b;i++)
                {
                    char c[]=String.valueOf(i).toCharArray();
                    if(c.length>2)
                    {
                    boolean ans=true;
                    if(c[0]==num)
                    {
                        for(int j=2;j<c.length;j++)
                            if(c[j]!=num)
                            {
                                ans=false;
                                break;
                            }
                    }
                    else if(c[0]!=num || !ans)
                    {
                        ans=false;
                        for(int j=1;j<c.length;j++)
                            if(c[j]!=num)
                            {
                                ans=false;
                                break;
                            }
                    }
                    if(ans)
                        A++;
                }
                }
                out.println(A);
            }
            else if(s.charAt(0)=='C')
            {
                int num=Integer.parseInt(s.substring(s.length()-1));
                int rep=Integer.parseInt(s.substring(1, s.length()-1));
                int v=0;
                for(int i=a;i<=b;i++)
                {
                    String km=String.valueOf(i);
                    String pat=String.valueOf(num);
                    for(int ii=0;ii<rep;ii++)
                        pat=pat.concat(pat);
                    if(km.contains(pat))
                        v++;
                }
                out.println(v);
            }
            else
            {
                int count=0;
                for(int i=0;i<s.length();i++)
                    if(s.charAt(i)=='W')
                        count++;
                out.println((long)Math.pow(10, count));
            }
        }
    }
    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new fgh().main1();
    }
    void main1()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
       // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1)
            throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer)
        {
            ptrbuffer=0;
            try {
                lenbuffer=obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if(lenbuffer<=0)
            return -1;
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c)
    {
        return (!(c>=33 && c<=126));
    }
    int skip()
    {
        int b;
        while((b=readByte())!=-1 && isSpaceChar(b));
        return b;
    }
    String is()
    {
        int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b=readByte();
        }
        return sb.toString();
    }
    int ii()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    long il()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    float nf()
    {
        return Float.parseFloat(is());
    }
    double id()
    {
        return Double.parseDouble(is());
    }
    char ic()
    {
        return (char)skip();
    }
    int[] iia(int n)
    {
        int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=ii();
        return a;
    }
    long[] ila(int n)
    {
        long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=il();
        return a;
    }
    String[] isa(int n)
    {
        String a[]=new String[n];
        for(int i=0;i<n;i++)
            a[i]=is();
        return a;
    }
    double[][] idm(int n, int m)
    {
        double a[][]=new double[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                a[i][j]=id();
        return a;
    }
    int[][] iim(int n, int m)
    {
        int a[][]=new int[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                a[i][j]=ii();
        return a;
    }
}