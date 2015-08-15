package com.chaos.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Chaos23 on 2015/8/15.
 */
public class Search2DMatrixII {

    public class Solution {
        //O(m+n) solution
        //We start search the matrix from top right corner, initialize the current position to top right corner,
        // if the target is greater than the value in current position,
        // then the target can not be in entire row of current position because the row is sorted,
        // if the target is less than the value in current position,
        // then the target can not in the entire column because the column is sorted too.
        // We can rule out one row or one column each time, so the time complexity is O(m+n).
        public boolean searchMatrix0(int[][] matrix, int target)  {
            if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
                return false;
            }
            int col = matrix[0].length-1;
            int row = 0;
            while(col >= 0 && row <= matrix.length-1) {
                if(target == matrix[row][col]) {
                    return true;
                } else if(target < matrix[row][col]) {
                    col--;
                } else if(target > matrix[row][col]) {
                    row++;
                }
            }
            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            return searchMatrixRange(matrix, 0, 0, target);
        }

        private boolean binarySearchHorizontally(int[] matrix, int target) {
            return Arrays.binarySearch(matrix, target) > -1;
        }

        private boolean binarySearchVertically(int[][] martix, int y, int target) {
            int end = martix.length - 1;
            int start = 0;

            while (start <= end) {
                int mid = (start + end) >>> 1;
                int midVal = martix[mid][y];
                if (midVal == target) {
                    return true;
                } else if (midVal < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return false;
        }

        private boolean searchMatrixRange(int[][] matrix, int fromX, int fromY, int target) {
            int matrixWidth = matrix[0].length - fromY;
            if (matrixWidth == 0) {
                return binarySearchHorizontally(matrix[fromX], target);
            }
            int matrixHight = matrix.length - fromX;
            if (matrixHight == 0) {
                return binarySearchVertically(matrix, fromY, target);
            }

            boolean wider = matrixWidth > matrixHight;

            final int shorter = wider ? matrixHight : matrixWidth;
            int copyOfFromX = fromX, copyOfFromY = fromY;

            int endX = copyOfFromX + shorter - 1;
            int endY = copyOfFromY + shorter - 1;

            if (matrix[endX][endY] < target) {
                int nextFromX, nextFromY;
                if (wider) {
                    nextFromX = 0;
                    nextFromY = endY + 1;
                } else {
                    nextFromX = endX + 1;
                    nextFromY = 0;
                }

                return searchMatrixRange(matrix, nextFromX, nextFromY, target);
            } else if (matrix[endX][endY] == target) {
                return true;
            }

            int midX, midY;
            while (copyOfFromX <= endX) {
                midX = (copyOfFromX + endX) >>> 1;
                midY = (copyOfFromY + endY) >>> 1;

                int midVal = matrix[midX][midY];
                if (midVal < target) {
                    copyOfFromX = midX + 1;
                    copyOfFromY = midY + 1;
                } else if (midVal == target) {
                    return true;
                } else {
                    endX = midX - 1;
                    endY = midY - 1;
                }
            }

            for (int i = copyOfFromX; i < matrix.length; i++)
                if (binarySearchHorizontally(matrix[i], target))
                    return true;

            for (int i = copyOfFromY; i < matrix[0].length; i++)
                if (binarySearchVertically(matrix, i, target))
                    return true;

            return false;
        }
    }

    private int[][] buildTestCase(int x, int y) {
        int[][] testcase = new int[x][y];
        int counter = 1;
        for (int ix = 0; ix < x; ix++) {
            for (int iy = 0; iy < y; iy++) {
                testcase[ix][iy] = counter;
                counter = counter + 2;
            }
        }

        return testcase;
    }

    @Test
    public void testSearchMatrix() {
        Solution solution = new Solution();
        int[][] testcase1 = buildTestCase(3, 3);
        Assert.assertTrue(solution.searchMatrix(testcase1, 11));
        Assert.assertTrue(solution.searchMatrix(testcase1, 17));
        Assert.assertFalse(solution.searchMatrix(testcase1, 16));

        int[][] testcase2 = new int[][] {
                { -5 }
        };
        Assert.assertFalse(solution.searchMatrix(testcase2, -2));

        int[][] testcase3 = new int[][] {
                {1, 1}
        };
        Assert.assertFalse(solution.searchMatrix(testcase3, 2));

        int[][] testcase4 = new int[][] {
                { 1,   2,  3,  4,  5 },
                { 6,   7,  8,  9, 10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 }
        };
        Assert.assertTrue(solution.searchMatrix(testcase4, 15));
    }
}
