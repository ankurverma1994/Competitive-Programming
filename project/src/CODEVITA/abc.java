/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CODEVITA;

/**
 *
 * @author ankurverma1994
 */
import java.io.*;
import java.util.*;
class abc {
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int tc=0;tc<t;tc++)
        {
            String text[]=obj.readLine().split(" ");
            int l=text.length/2;
            int a[]=new int[l];
            String b[]=new String[l];
            for(int i=0,j=0;i<text.length;i++,j++)
            {
                b[j]=text[i].toLowerCase();
                i++;
                a[j]=Integer.parseInt(text[i]);
            }
            int steps=Integer.parseInt(obj.readLine());
            int iword=0,inum=0;
            for(int i=0;i<steps;i++)
            {
                //words
                if(i%2==0)
                {
                    int min_idx = iword;
                    for (int j = iword+1; j < l; j++)
                        if (b[j].compareTo(b[min_idx])<0)
                            min_idx = j;
                            
                    String temp=b[min_idx];
                    b[min_idx]=b[iword];
                    b[iword]=temp;
                    iword++;
                }
                else
                {
                    int min_idx=inum;
                    for(int j=inum+1; j<l; j++)
                        if(a[j]<a[min_idx])
                            min_idx=j;
                    
                    int tep=a[min_idx];
                    a[min_idx]=a[inum];
                    a[inum]=tep;
                    inum++;
                }
            }
            for(int i=0;i<l;i++)
                System.out.print(b[i]+" "+a[i]+" ");
            System.out.println("");
        }
    }
}
