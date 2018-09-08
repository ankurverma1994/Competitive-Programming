package aaa;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
/* *
 *
 * @author ankurverma1994
 */
class maxSubArray {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new maxSubArray().main1();
    }
    void main1()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }
    //------------> Solution starts here!!
    void solve(){
        int n=ii();
        int a[]=iia(n);
        long maxarray_sofar=0,maxarray_ending=0;
        for(int i=0;i<n;i++){
            maxarray_ending+=a[i];
            if(maxarray_ending<0)
                maxarray_ending=0;
            if(maxarray_ending>maxarray_sofar)
                maxarray_sofar=maxarray_ending;
        }
        out.println(maxarray_sofar);
    }
    //------------> Solution ends here!!
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
}
