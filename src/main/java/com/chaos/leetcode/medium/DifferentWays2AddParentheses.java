package com.chaos.leetcode.medium;


import org.junit.Test;

public class DifferentWays2AddParentheses {

    private void swap(char[] str, int x, int y) {
        char buf = str[x];
        str[x] = str[y];
        str[y] = buf;
    }

    public void permutation(char[] str, int start) {
        if (start == str.length - 1) {
            System.out.println(str);
        }

        for (int i = start; i < str.length; i++) {
            swap(str, i, start);
            permutation(str, start + 1);
            swap(str, i, start);
        }

    }

    @Test
    public void testPermutation() {
        DifferentWays2AddParentheses d = new DifferentWays2AddParentheses();
        d.permutation("abcde".toCharArray(), 0);
    }

}
