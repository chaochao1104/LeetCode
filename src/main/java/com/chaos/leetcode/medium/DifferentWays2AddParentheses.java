package com.chaos.leetcode.medium;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DifferentWays2AddParentheses {

    public static final char MULTIPLY = '*';

    public static final char MINUS = '-';

    public static final char PLUS = '+';


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

    public void convert2Array(String expr, List<Integer> numbers, List<Character> operators) {
        int lastOperatorIdx = -1;
        for (int i = 0; i < expr.length(); i++) {
            char theChar = expr.charAt(i);
            if (theChar == MULTIPLY || theChar == MINUS || theChar == PLUS) {
                operators.add(theChar);
                String number = expr.substring(lastOperatorIdx + 1, i);
                numbers.add(Integer.valueOf(number));
                lastOperatorIdx = i;
            }
        }
        String number = expr.substring(lastOperatorIdx + 1);
        numbers.add(Integer.valueOf(number));
    }

    static abstract class Node {

        Node left;

        Node right;

    }

    static class OperatorNode extends Node {
        char operator;
    }

    static class NumberNode extends Node {
        int number;
    }

    public void buildExprTree(List<Integer> numbers, List<Character> operators) {
        left(null, null, numbers, operators, 0, operators.size() - 1);

    }

    private void left(Node root, Node parent, List<Integer> numbers, List<Character> operators, int optStart, int optEnd) {
        if (optStart == optEnd) {
            NumberNode numNode = new NumberNode();
            numNode.number = numbers.get(optStart);
            parent.left = numNode;
            return;
        }

        for (int i = optStart; i <= optEnd; i++) {
            OperatorNode optNode = new OperatorNode();
            optNode.operator = operators.get(i);
            if (root == null) {
                root = optNode;
            }

            if (parent != null) {
                parent.left = optNode;
            }

            left(root, optNode, numbers, operators, optStart, i);
            right(root, optNode, numbers, operators, i + 1, optEnd);
        }

    }

    private void right(Node root, Node parent, List<Integer> numbers, List<Character> operators, int optStart, int optEnd) {
        if (optStart == optEnd) {
            NumberNode numNode = new NumberNode();
            numNode.number = numbers.get(optStart + 1);
            parent.right = numNode;
            return;
        }

        for (int i = optStart; i <= optEnd; i++) {
            OperatorNode optNode = new OperatorNode();
            optNode.operator = operators.get(i);
            if (root == null) {
                root = optNode;
            }

            if (parent != null) {
                parent.right = optNode;
            }

            if (optStart == i) {

                optNode.left =
            } else {
                left(root, optNode, numbers, operators, optStart, i);
            }
            right(root, optNode, numbers, operators, i + 1, optEnd);
        }

    }

    @Test
    public void testPermutation() {
       DifferentWays2AddParentheses d = new DifferentWays2AddParentheses();
//        d.permutation("abcdefg".toCharArray(), 0);

        List<Integer> numbers = new ArrayList<Integer>();
        List<Character> operators = new ArrayList<Character>();
        d.convert2Array("2*3-4*5", numbers, operators);

        System.out.println(numbers);
        System.out.println(operators);

        d.buildExprTree(numbers, operators);
    }

}
