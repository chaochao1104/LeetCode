package com.chaos.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;

public class LCA {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            if (p.val < root.val && q.val < root.val)
                return lowestCommonAncestor(root.left, p, q);

            if (p.val > root.val && q.val > root.val)
                return lowestCommonAncestor(root.right, p, q);

            return root;
        }
    }

    private TreeNode prepareTestData() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        return root;
    }

    @Test
    public void testLowestCommonAncestor() {
        TreeNode root = prepareTestData();

        Solution solution = new Solution();
        TreeNode lca = solution.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8));
        Assert.assertEquals(6, lca.val);

        lca = solution.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4));
        Assert.assertEquals(2, lca.val);
    }

}
