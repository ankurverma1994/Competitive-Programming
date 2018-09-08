package Zenefits2015;

/**
 * Created by ankurverma1994 on 21/11/15.
 */
import java.io.*;
import java.util.*;
 class EliteTeam {
     static boolean visit[];
     static long connComp=0;
     static PrintWriter out;
     public static void main(String[] args)throws IOException {
         BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
         out=new PrintWriter(System.out);
         int N,M,Q;
         String in[]=obj.readLine().split(" ");
         N=Integer.parseInt(in[0]);
         M=Integer.parseInt(in[1]);
         Q=Integer.parseInt(in[2]);
         ArrayList<Integer> friends[]=new ArrayList[N];
         for(int i=0;i<N;i++)
             friends[i]=new ArrayList<>();
         for(int i=0;i<M;i++){
             in=obj.readLine().split(" ");
             int u=Integer.parseInt(in[0])-1;
             int v=Integer.parseInt(in[1])-1;
             friends[u].add(v);
             friends[v].add(u);
         }
         for(int q=0;q<Q;q++){
             in=obj.readLine().split(" ");
             int a=Integer.parseInt(in[0])-1;
             int b=Integer.parseInt(in[1])-1;
             solve1(friends,a,b);
         }
         out.flush();
         out.close();
     }
     static void solve1(ArrayList<Integer> friends[], int a, int b)
     {
         ArrayList<Integer> graph[]=new ArrayList[b-a+1];
         for(int i=0;i<b-a+1;i++)
             graph[i]=new ArrayList<>();
         for(int i=a;i<=b;i++){
             for(int j=0;j<friends[i].size();j++){
                 int x=friends[i].get(j);
                 if(a<=x && x<=b){
                     graph[i-a].add(x-a);
                 }
             }
         }
         out.println(sqConnectedComp(graph,b-a+1));
     }
     static void dfs1(ArrayList<Integer> graph[],int s)
     {
         Stack<Integer> st=new Stack<Integer>();
         st.push(s);
         while(!st.isEmpty()){
             int v=st.pop();
             if(!visit[v])
             {
                 visit[v]=true;
                 connComp++;
                 for(int i=0;i<graph[v].size();i++){
                     int w=graph[v].get(i);
                     st.push(w);
                 }
             }
         }
     }
     static long sqConnectedComp(ArrayList<Integer> graph[], int n)
     {
         visit=new boolean[n];
         long ans=0;
         for(int i=0;i<n;i++)
         {
             if(!visit[i])
             {
                 connComp=0;
                 dfs1(graph, i);
                 ans=ans + connComp*connComp;
             }
         }
         return ans;
     }
}
