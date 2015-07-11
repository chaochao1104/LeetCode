package com.chaos.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;

public class PowerOfTwo {

    public class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 2 || n == 1) return true;

            if (n == 0) return false;

            if (n % 16 == 0)
                return isPowerOfTwo(n / 16);
            else if (n % 8 == 0)
                return isPowerOfTwo(n / 8);
            else if (n % 4 == 0)
                return isPowerOfTwo(n / 4);
            else if (n % 2 == 0)
                return isPowerOfTwo(n / 2);
            else
                return false;
        }

        public boolean isPowerOfTwoOptimized(int n) {
            if (n<=0)  return false;
            return (n & (n-1)) == 0;
        }
    }

    @Test
    public void testIsPowerOfTwo() {
        Solution solution = new Solution();
        Assert.assertFalse(solution.isPowerOfTwo(0));
        Assert.assertTrue(solution.isPowerOfTwo(1));
        Assert.assertTrue(solution.isPowerOfTwo(2));
        Assert.assertFalse(solution.isPowerOfTwo(3));
        Assert.assertTrue(solution.isPowerOfTwo(4));
        Assert.assertTrue(solution.isPowerOfTwo(8));
        Assert.assertTrue(solution.isPowerOfTwo(16));
    }

    @Test
    public void testIsPowerOfTwoOptimized() {
        Solution solution = new Solution();
        Assert.assertFalse(solution.isPowerOfTwoOptimized(0));
        Assert.assertTrue(solution.isPowerOfTwoOptimized(1));
        Assert.assertTrue(solution.isPowerOfTwoOptimized(2));
        Assert.assertFalse(solution.isPowerOfTwoOptimized(3));
        Assert.assertTrue(solution.isPowerOfTwoOptimized(4));
        Assert.assertTrue(solution.isPowerOfTwoOptimized(8));
        Assert.assertTrue(solution.isPowerOfTwoOptimized(16));
    }

}
