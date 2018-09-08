import java.io.*;
import java.util.*;

class Palindrome {
    public static void main(String[] args) {
        int n = 11;
        ArrayList<Integer> g[] = new ArrayList[n];
//        ArrayList<Integer> a=new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        g[1].add(2);
    }
    void solve(){
        Tree root=new Tree(1);
        root.left=new Tree(2);
//        root.left.left=new Tree(4);
        root=root.left;
        root.left=new Tree(4);
    }

    class Tree {
        Tree left, right;
        int val;

        Tree(int k) {
            val = k;
            left = right = null;
        }
    }
}