//package CODEVITA;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Stack;
//import java.io.FileInputStream;
/* *
 *
 * @author ankurverma1994
 */
class class1 {
    //------------> Solution starts here!!
    void solve(){
        for(int tc=ii();tc>0;tc--){
            String S[]=is().split(" ");
            char l[]=new char[S.length];
            for(int i=0;i<S.length;i++)
                l[i]=S[i].charAt(0);
            Stack<Character> st=new Stack<>();
            Stack<Character> stmain=new Stack<>();
            boolean ans=true;
            if(l[0]!='{' && l[S.length-1]!='}')
                ans=false;
            int inst=0,countm=0,endm=0,funs=0,fune=0;
            if(ans)
            for(int i=1;i<S.length-1;i++)
            {
                if(l[i]=='<')
                {
                    if(countm==0)
                    {
                        if(st.isEmpty())
                        {
                            st.push(l[i]);
                            countm++;
                        }
                        else{
                            ans=false;
                            break;
                        }
                    }
                    else{
                        ans=false;
                        break;
                    }
                }
                else if(l[i]=='>')
                {
                    if(endm==0)
                    {
                        if(!st.isEmpty())
                        {
                            if(st.pop()=='<')
                            {
                                endm++;
                            }
                            else
                            {
                                ans=false;
                                break;
                            }
                        }
                        else
                        {
                            ans=false;
                            break;
                        }
                    }
                    else{
                        ans=false;
                        break;
                    }
                }
                else if(l[i]=='{')
                {
                    if(st.isEmpty())
                    {
                        ans=false;
                        break;
                    }
                    st.push(l[i]);
                }
                else if(l[i]=='}')
                {
                    if(st.isEmpty())
                    {
                        ans=false;
                        break;
                    }
                    if(st.pop()!='{')
                    {
                        ans=false;
                        break;
                    }
                }
                else if(l[i]=='(')
                {
                    if(funs==0)
                    {
                        if(st.isEmpty())
                        {
                            st.push(l[i]);
                            funs++;
                            fune=0;
                        }
                        else{
                            ans=false;
                            break;
                        }
                    }
                    else{
                        ans=false;
                        break;
                    }
                }
                else if(l[i]==')')
                {
                    if(fune==0)
                    {
                        if(!st.isEmpty())
                        {
                            if(st.pop()=='(')
                            {
                                fune++;
                                funs=0;
                                inst=0;
                            }
                            else
                            {
                                ans=false;
                                break;
                            }
                        }
                        else
                        {
                            ans=false;
                            break;
                        }
                    }
                    else{
                        ans=false;
                        break;
                    }
                }
                else if(l[i]=='P')
                {
                    if(funs==1)
                    {
                        inst++;
                        if(inst>100)
                        {
                            ans=false;
                            break;
                        }
                    }
                }
            }
            if(!ans)
                out.println("Compilation Errors");
            else
                out.println("No Compilation Errors");
        }
    }
    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new class1().main1();
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
