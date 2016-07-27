package com.chaos.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class DifferentWays2AddParentheses {

    public static final char MULTIPLY = '*';

    public static final char MINUS = '-';

    public static final char PLUS = '+';

    public static final char LEFT_PARENTHESE = '(';

    public static final char RIGHT_PARENTHESE = ')';

    private Set<String> exprs = new HashSet<String>();

    private void swap(int[] str, int x, int y) {
        int buf = str[x];
        str[x] = str[y];
        str[y] = buf;
    }

    public void permutation(String input, int[] priorities, int start) {
        if (start == priorities.length - 1) {
            String expr = addParentheses(input, priorities);
            exprs.add(expr);
        }

        for (int i = start; i < priorities.length; i++) {
            swap(priorities, i, start);
            permutation(input, priorities, start + 1);
            swap(priorities, i, start);
        }

    }

    private String addParentheses(String input, int[] priorities) {
        StringBuilder expr = new StringBuilder(input);
        for (int priority : priorities) {
            int idxOfOperator = findNthOfOperator(expr, priority);
            int leftBoundary = findLeftBoundary(expr, idxOfOperator);
            expr.insert(leftBoundary, LEFT_PARENTHESE);
            int rightBoundary = findRightBoundary(expr, idxOfOperator + 1);
            expr.insert(rightBoundary, RIGHT_PARENTHESE);
        }

        return expr.toString();
    }

    private int findLeftBoundary(StringBuilder expr, int operatorIdx) {
        int parentheseBalance = 0;
        for (int i = operatorIdx - 1; i > -1; i--) {
            char ch = expr.charAt(i);
            if (ch == RIGHT_PARENTHESE) {
                parentheseBalance++;
            } else if (ch == LEFT_PARENTHESE) {
                parentheseBalance--;
            } else if (isOperator(ch)) {
                if (parentheseBalance == 0) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    private int findRightBoundary(StringBuilder expr, int operatorIdx) {
        int parentheseBalance = 0;
        for (int i = operatorIdx + 1; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == RIGHT_PARENTHESE) {
                parentheseBalance++;
            } else if (ch == LEFT_PARENTHESE) {
                parentheseBalance--;
            } else if (isOperator(ch)) {
                if (parentheseBalance == 0) {
                    return i;
                }
            }
        }
        return expr.length();
    }

    private int findNthOfOperator(StringBuilder expr, int nth) {
        int occurrence = 0;
        for (int i = 0; i < expr.length(); i++) {
            char operator = expr.charAt(i);
            if (isOperator(operator)) {
                if (occurrence++ == nth) {
                    return i;
                }
            }
        }

        return expr.length();
    }

    private boolean isOperator(char ch) {
        return ch == MULTIPLY || ch == MINUS || ch == PLUS;
    }

    private int findLowestPriorityOpt(String expr) {
        int parentheseBalance = 0;
        int lowestPbSoFar = Integer.MIN_VALUE;
        int lowestPriorityOptIdx = 0;
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == LEFT_PARENTHESE) {
                parentheseBalance--;
            } else if (ch == RIGHT_PARENTHESE) {
                parentheseBalance++;
            } else if (isOperator(ch)) {
                if (parentheseBalance > lowestPbSoFar) {
                    lowestPbSoFar = parentheseBalance;
                    lowestPriorityOptIdx = i;
                }
            }
        }

        return lowestPriorityOptIdx;
    }

    private Integer compute(String expr) {
        int lowestPriorityOpt = findLowestPriorityOpt(expr);

        if (lowestPriorityOpt == 0) {
            return Integer.valueOf(expr);
        }

        char operator = expr.charAt(lowestPriorityOpt);
        String leftExpr = expr.substring(1, lowestPriorityOpt);
        String rightExpr = expr.substring(lowestPriorityOpt + 1, expr.length() - 1);

        if (operator == MULTIPLY)
            return compute(leftExpr) * compute(rightExpr);
        else if (operator == PLUS)
            return compute(leftExpr) + compute(rightExpr);
        else if (operator == MINUS)
            return compute(leftExpr) - compute(rightExpr);

        throw new IllegalAccessError("operator: " + operator + " at " + lowestPriorityOpt);
    }

    public List<Integer> diffWaysToCompute2(String input) {
        int operatorNum = 0;
        for (int i = 0; i < input.length(); i++) {
            char operator = input.charAt(i);
            if (isOperator(operator)) {
                operatorNum++;
            }
        }

        if (operatorNum == 0) {
            return Arrays.asList(Integer.valueOf(input));
        }

        int[] priorities = new int[operatorNum];
        for (int i = 0; i < operatorNum; i++) {
            priorities[i] = i;
        }

        permutation(input, priorities, 0);
        List<Integer> results = new ArrayList<Integer>();

        for (String expr : exprs) {
            Integer result = compute(expr);
            results.add(result);
        }

        return results;
    }

    private boolean isSimpleExpr(String expr) {
        int operatorCount = 0;
        for (int i = 0; i <expr.length(); i++) {
            if (isOperator(expr.charAt(i)))
                operatorCount++;

            if (operatorCount > 1)
                return false;
        }

        return true;
    }

    private Map<String, List<Integer>> cache = new HashMap<String, List<Integer>>();

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> results = null;

        results = cache.get(input);
        if (results != null)
            return results;

        results = new ArrayList<Integer>();
        for (int i = 0; i <input.length(); i++) {
            char ch = input.charAt(i);
            if (isOperator(ch)) {
                List<Integer> leftResults = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightResults = diffWaysToCompute(input.substring(i + 1));
                for (Integer leftRes : leftResults) {
                    for (Integer rightRes : rightResults) {
                        if (ch == MULTIPLY)
                            results.add(leftRes * rightRes);
                        else if (ch == MINUS)
                            results.add(leftRes - rightRes);
                        else if (ch == PLUS)
                            results.add(leftRes + rightRes);
                    }
                }
            }
        }

        if (results.isEmpty()) {
            results.add(Integer.valueOf(input));
        }

        cache.put(input, results);
        return results;
    }

    @Test
    public void testPermutation() {
       DifferentWays2AddParentheses d = new DifferentWays2AddParentheses();
        List<Integer> results = d.diffWaysToCompute("2*3-4*5");

        List<Integer> expected = Arrays.asList(-34, -14, -10, -10, 10);
        List<Integer> resultsCopy = new ArrayList<Integer>(results);
        List<Integer> expectedCopy = new ArrayList<Integer>(expected);

        resultsCopy.removeAll(expected);
        Assert.assertEquals(0, resultsCopy.size());
        expectedCopy.removeAll(results);
        Assert.assertEquals(0, expectedCopy.size());
    }

}
