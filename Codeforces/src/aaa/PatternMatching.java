package aaa;
class PatternMatching
{
    public static void main(String[] args) {

    }
    public static int[] Z(char[] str)
    {
        int n = str.length;
        int[] z = new int[n];
        if(n == 0)return z;
        z[0] = n;
        int l = 0, r = 0;
        for(int i = 1;i < n;i++){
            if(i > r){
                l = r = i;
                while(r < n && str[r-l] == str[r])r++;
                z[i] = r-l; r--;
            }else{
                int k = i-l;
                if(z[k] < r-i+1){
                    z[i] = z[k];
                }else{
                    l = i;
                    while(r < n && str[r-l] == str[r])r++;
                    z[i] = r-l; r--;
                }
            }
        }
        return z;
    }
}