package com.chaos.leetcode.medium;


import java.util.ArrayList;
import java.util.List;

//78. Subsets
public class Subsets {

    private Integer[] subset;

    private int lastIdx;

    private List<List<Integer>> outcome;

    public List<List<Integer>> subsets(int[] nums) {
        outcome = new ArrayList<List<Integer>>();
        subset = new Integer[nums.length];
        combine(nums, 0);
        return outcome;
    }

    private void combine(int[] nums, int start) {
        //outcome.add(Arrays.asList(Arrays.copyOf(subset, lastIdx)));
        List<Integer> aSolution = new ArrayList<Integer>(lastIdx);
        for (int i = 0; i < lastIdx; i++) {
            aSolution.add(subset[i]);
        }
        outcome.add(aSolution);

        for (int i = start; i < nums.length; i++) {
            subset[lastIdx++] = nums[i];
            combine(nums, i + 1);
            --lastIdx;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        Subsets subsets = new Subsets();
        List<List<Integer>> outcome = subsets.subsets(nums);
        System.out.println(outcome);
    }
}
