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
class PartitionNumberNsuchThatEachNumberIsGreaterThanK {
    InputStream obj;
    PrintWriter out;
    String check="";
    //------------> Solution starts here!!
    void solve(){
        for(int tc=ii();tc>0;tc--){
            int n=ii(),k=ii();
            out.println(solve1(n,k));
        }
    }
    long dp[][]=new long[401][401];
    /**
     * dp[n][k]---> number of ways to divide an integer n such that each element
     *              is at-least k.
     */
    long solve1(int n, int k)
    {
        if(n==0)
            return 1;
        if(k>n)
            return 0;
        if(k==n)
            return 1;
        if(dp[n][k]!=0)
            return dp[n][k];
        
        return dp[n][k]=solve1(n, k+1) + solve1(n-k, k);
    }
    //------------> Solution ends here!!
    public static void main(String[] args)throws IOException{
        new PartitionNumberNsuchThatEachNumberIsGreaterThanK().main1();
    }
    void main1()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
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
}
