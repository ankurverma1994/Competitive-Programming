//package CODEVITA;


import java.io.*;
import java.util.*;
/* *
 *
 * @author ankurverma1994
 */
class xyz {
    //------------> Solution starts here!!
    int h=0,w=0;
    boolean visit[][];
    char M[][];
  //  ArrayList<Integer> num=new ArrayList<Integer>();
    int num[];
    int index;
    void solve(){
        for(int tc=ii();tc>0;tc--){
            h=ii();w=ii();
            visit=new boolean[h][w];
            M=new char[h][w];
            num=new int[1000000];
            index=0;
            for(int i=0;i<h;i++)
            {
                char d[]=is().toCharArray();
                for(int j=0;j<w;j++)
                    M[i][j]=d[j];
            }
            out.println(CountBlobs());
            Arrays.sort(num);
            for(int i=0;i<num.length;i++)
                if(num[i]==0)
                    continue;
            else
                    out.print(num[i]+" ");
            out.println();
        }
    }
    int CountBlobs()
    {
        int count=0;
        for(int i=0;i<h;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(M[i][j]=='#' && !visit[i][j])
                {
                    size=0;
                    dfs(i,j);
                    num[index++]=size;
                  //  System.out.println(size);
                    count++;
                }
                
            }
         
        }
        return count;
    }
    int size=0;
    void dfs(int row, int col)
    {
            int hNbr[] = {-1, -1, -1,  0, 0,  1, 1, 1};
            int wNbr[] = {-1,  0,  1, -1, 1, -1, 0, 1};
    
           visit[row][col] = true;
           size++;
        for (int k = 0; k < 8; ++k)
            if (valid( row + hNbr[k], col + wNbr[k]) )
            dfs( row + hNbr[k], col + wNbr[k]);
        
    }
    boolean valid(int row, int col)
    {
        return (row >= 0) && (row < h) &&  (col >= 0) && (col < w) &&   (M[row][col]=='#' && !visit[row][col]);
    }
    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new xyz().main1();
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