package com.chaos.leetcode.medium;


import junit.framework.Assert;
import org.junit.Test;

import java.util.Stack;

public class LCA {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {

        public void preorder(TreeNode root) {
            if (root != null) {
                System.out.print(root.val);
                System.out.print(" ");
                preorder(root.left);
                preorder(root.right);
            }
        }

        public void inorder(TreeNode root) {
            if (root != null) {
                inorder(root.left);
                System.out.print(root.val);
                System.out.print(" ");
                inorder(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q)
                return root;

            TreeNode lLca = lowestCommonAncestor(root.left, p, q);
            TreeNode rLca = lowestCommonAncestor(root.right, p, q);

            if (lLca != null && rLca != null) {
                return root;
            }

            return lLca == null ? rLca : lLca;
        }

        public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
            if (exists(p, q))
                return p;

            if (exists(q, p))
                return q;

            while (true) {
                if (p != root)
                    p = ancestorOfNode(root, p, null);

                if (q != root)
                    q = ancestorOfNode(root, q, null);

                return lowestCommonAncestor3(root, p, q);
            }
        }

        private TreeNode ancestorOfNode(TreeNode root, TreeNode node, TreeNode parent) {
            if (root == null) {
                return null;
            } else if (root != node) {
                parent = ancestorOfNode(root.left, node, root);
                if (parent == null) {
                    parent = ancestorOfNode(root.right, node, root);
                }
            }
            return parent;
        }

        private boolean exists(TreeNode root, TreeNode node) {
            if (root == null)
                return false;
            if (root.val == node.val)
                return true;

            return exists(root.left, node) || exists(root.right, node);
        }

        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            Stack<TreeNode> stackTraceOfP = new Stack<TreeNode>();
            Stack<TreeNode> stackTraceOfQ = new Stack<TreeNode>();

            stackTraceOf(root, p, stackTraceOfP);
            stackTraceOf(root, q, stackTraceOfQ);

            int diff = stackTraceOfP.size() - stackTraceOfQ.size();
            Stack<TreeNode> deeper = diff > 0 ? stackTraceOfP : stackTraceOfQ;
            diff = Math.abs(diff);
            while (diff-- > 0)
                deeper.pop();

            while (!stackTraceOfP.isEmpty()) {
                TreeNode nodeOfP = stackTraceOfP.pop();
                TreeNode nodeOfQ = stackTraceOfQ.pop();
                if (nodeOfP == nodeOfQ)
                    return nodeOfP;
            }

            return null;
        }

        private boolean stackTraceOf(TreeNode root, TreeNode node, Stack<TreeNode> stackTrace) {
            stackTrace.push(root);
            if (root.val == node.val) {
                return true;
            }

            if (root.left != null) {
                boolean found = stackTraceOf(root.left, node, stackTrace);
                if (found)
                    return true;
            }

            if (root.right != null) {
                boolean found = stackTraceOf(root.right, node, stackTrace);
                if (found)
                    return true;
            }

            stackTrace.pop();
            return false;
        }

    }

    private TreeNode buildTestCase1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        return root;
    }

    private TreeNode buildTestCase27() {
        TreeNode root = new TreeNode(37);
        root.left = new TreeNode(-34);
        root.left.right = new TreeNode(-100);
        root.right = new TreeNode(-48);
        root.right.left = new TreeNode(-100);
        root.right.right = new TreeNode(48);
        root.right.right.left = new TreeNode(-54);
        root.right.right.left.left = new TreeNode(-71);
        root.right.right.left.right = new TreeNode(-22);
        root.right.right.left.right.right = new TreeNode(8);

        return root;
    }

    @Test
    public void test() {
        TreeNode root = buildTestCase1();
        Solution solution = new Solution();

        TreeNode lca = solution.lowestCommonAncestor(root, root.left, root.left.right);
        Assert.assertEquals(5, lca.val);
        lca = solution.lowestCommonAncestor(root, root.left, root.left.right);
        Assert.assertEquals(5, lca.val);

        lca = solution.lowestCommonAncestor(root, root.left, root.right.right);
        Assert.assertEquals(3, lca.val);
        solution.lowestCommonAncestor2(root, root.left, root.right.right);
        Assert.assertEquals(3, lca.val);

        root = buildTestCase27();
        solution.preorder(root);
        System.out.println();
        solution.inorder(root);
        lca = solution.lowestCommonAncestor(root, root.left.right, root.right.right.left.left);
        Assert.assertEquals(37, lca.val);
        lca = solution.lowestCommonAncestor(root, root.right.left, root.right.right.left.left);
        Assert.assertEquals(-48, lca.val);
    }
}
