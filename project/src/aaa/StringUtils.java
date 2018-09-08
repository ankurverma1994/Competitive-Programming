package aaa;

import java.util.*;

class StringUtils {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(suffixArray("34521")));
    }

    public static int[] suffixArray(CharSequence s) {
        int length = s.length();
        int[] position = new int[length];
        int[] count = new int[Math.max(256, length)];
        int[] order = new int[length];
        for (int i = 0; i < length; ++i) {
            count[s.charAt(i)]++;
        }
        for (int i = 1; i < 256; ++i) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < length; ++i) {
            position[--count[s.charAt(i)]] = i;
        }
        order[position[0]] = 0;
        int currentClass = 0;
        for (int i = 1; i < length; ++i) {
            if (s.charAt(position[i]) != s.charAt(position[i - 1])) {
                currentClass++;
            }
            order[position[i]] = currentClass;
        }
        int[] nextPosition = new int[length];
        int[] nextOrder = new int[length];
        for (int h = 0; (1 << h) < length; h++) {
            for (int i = 0; i < length; ++i) {
                nextPosition[i] = position[i] - (1 << h);
                if (nextPosition[i] < 0) {
                    nextPosition[i] += length;
                }
            }
            Arrays.fill(count, 0);
            for (int i = 0; i < length; ++i) {
                count[order[nextPosition[i]]]++;
            }
            for (int i = 1; i <= currentClass; ++i) {
                count[i] += count[i - 1];
            }
            for (int i = length - 1; i >= 0; --i) {
                position[--count[order[nextPosition[i]]]] = nextPosition[i];
            }
            nextOrder[position[0]] = 0;
            currentClass = 0;
            for (int i = 1; i < length; ++i) {
                int mid1 = (position[i] + (1 << h));
                if (mid1 >= length) {
                    mid1 -= length;
                }
                int mid2 = (position[i - 1] + (1 << h));
                if (mid2 >= length) {
                    mid2 -= length;
                }
                if (order[position[i]] != order[position[i - 1]] || order[mid1] != order[mid2]) {
                    currentClass++;
                }
                nextOrder[position[i]] = currentClass;
            }
            System.arraycopy(nextOrder, 0, order, 0, length);
        }
        return order;
    }
}