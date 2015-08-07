package com.chaos.leetcode.medium;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DifferentWays2AddParentheses {

    public static final char MULTIPLY = '*';

    public static final char PLUS = '+';

    public static final char MINUS = '-';

    public class Solution {
        public List<Integer> diffWaysToCompute(String input) {
            List<Integer> numbers = new ArrayList<Integer>();
            List<Character> operators = new ArrayList<Character>();

            int lastOperatorIdx = -1;
            for (int i = 0; i < input.length(); i++) {
                char theChar = input.charAt(i);
                if (theChar == MULTIPLY || theChar == MINUS || theChar == PLUS) {
                    operators.add(theChar);
                    String number = input.substring(lastOperatorIdx + 1, i);
                    numbers.add(Integer.valueOf(number));
                    lastOperatorIdx = i;
                }
            }
            String number = input.substring(lastOperatorIdx + 1);
            numbers.add(Integer.valueOf(number));

            return null;
        }

        private void diffWaysToCompute(List<String> numbers, int numberStart, int numberRange,
                                       List<Character> operators, int operatorStart, int operatorRange,
                                       List<Integer> outcome) {

        }

    }

    @Test
    public void testDiffWaysToCompute() {
        Solution solution = new Solution();
        solution.diffWaysToCompute("2*3-4*5");
    }

}
