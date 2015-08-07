package com.chaos.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;

public class ProductOfArrayExceptSelf {

    public class Solution {

        public int[] productExceptSelf(int[] nums) {
            int[] ret = new int[nums.length];

            ret[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                ret[i] = nums[i - 1] * ret[i - 1];
            }

            int productOfRightSide = 1;
            for (int i = nums.length - 1; i > -1; i--) {
                int tempProduct = productOfRightSide;
                productOfRightSide *= nums[i];
                ret[i] *= tempProduct;
            }

            return ret;
        }

    }

    @Test
    public void test() {
        Solution solution = new Solution();
        Assert.assertArrayEquals(
                new int[]{24, 12, 8, 6},
                solution.productExceptSelf(new int[] {1, 2, 3, 4})
        );

        Assert.assertArrayEquals(
                new int[]{270, 54, 180, 60},
                solution.productExceptSelf(new int[] {2, 10, 3, 9})
        );
    }

}
