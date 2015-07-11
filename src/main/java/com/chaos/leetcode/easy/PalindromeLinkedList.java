package com.chaos.leetcode.easy;


import org.junit.Assert;
import org.junit.Test;

public class PalindromeLinkedList {


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        public boolean isPalindrome(ListNode head) {
            if (head == null) return true;

            ListNode copyOfHead = copy(head);
            ListNode reversedCopyOfHead = reverse(copyOfHead);

            while (head != null) {
                if (reversedCopyOfHead.val != head.val)
                    return false;

                reversedCopyOfHead = reversedCopyOfHead.next;
                head = head.next;
            }

            return true;
        }

        private ListNode copy(ListNode head) {
            ListNode copyOfHead = new ListNode(head.val);
            ListNode copyNode = copyOfHead;
            while (head.next != null) {
                copyNode.next = new ListNode(head.next.val);
                copyNode = copyNode.next;
                head = head.next;
            }

            return copyOfHead;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode current = head;

            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            return prev;
        }
    }

    private ListNode preparePalindrome1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        return head;
    }

    private ListNode preparePalindrome2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(1);
        return head;
    }

    private ListNode prepareNotPalindrome1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(0);
        return head;
    }

    @Test
    public void testIsPalindrome() {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isPalindrome(preparePalindrome1()));
        Assert.assertTrue(solution.isPalindrome(preparePalindrome2()));
        Assert.assertTrue(solution.isPalindrome(null));
        Assert.assertFalse(solution.isPalindrome(prepareNotPalindrome1()));
    }

}
