package aaa;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs=new BinarySearch();
        int a[]={1,2,2,3,5,5};
        System.out.println(bs.BinarySearchLowerBound(a,2));
    }

    class Pair {
        int x, y;

        Pair(int m, int n) {
            x = m;
            y = n;
        }
    }
   /*
   class Ratio {
        int x, y;

        Ratio(int a, int b) {
            int gcd = gcd(a, b);
            x = a / gcd;
            y = b / gcd;
        }

        @Override
        public boolean equals(Object o) {
            Ratio obj=(Ratio) o;
            if (obj.x == x && obj.y == y)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return new Integer(x).hashCode() * 31 + new Integer(y).hashCode();
        }
    }
    */

/*	static class Pair implements Comparable<Pair>{
        int idx;
		long val;
		Pair(int i,long v){
			idx = i;
			val = v;
		}
		public int compareTo(Pair o){
			if(val != o.val)
				return Long.compare(val, o.val);
			return Integer.compare(idx, o.idx);
		}
		public String toString(){
			return idx + " " + val;
		}
	}
*/

    /* Tells the first occurence of element.
    *  If element is not found then tells the position
    *  where it should be inserted :)*/
    public int BinarySearchLowerBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public int BinarySearchUpperBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }
}

