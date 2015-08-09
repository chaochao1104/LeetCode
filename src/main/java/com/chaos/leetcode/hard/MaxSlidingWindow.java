package com.chaos.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;


public class MaxSlidingWindow {

    public class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int retLen = nums.length - k + 1;
            int[] ret = new int[retLen];

            int maxSoFar = Integer.MIN_VALUE;
            for (int i = 0; i < k; i++) {
                maxSoFar = Math.max(maxSoFar, nums[i]);
            }

            ret[0] = maxSoFar;
            for (int i = 0; i < retLen - 1; i++) {
                ret[i + 1] = Math.max(maxSoFar, nums[i + k]);
            }

            return ret;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        Assert.assertArrayEquals(
                new int[] {3, 3, 5, 5, 6, 7},
                solution.maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3)
        );
    }

}
