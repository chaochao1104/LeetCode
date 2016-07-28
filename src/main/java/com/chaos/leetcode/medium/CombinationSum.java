package com.chaos.leetcode.medium;

import java.util.*;

/**
 * Created by SUNAL4 on 2016-07-27.
 */

/*
  No.39
 */
public class CombinationSum {

    private Stack<Integer> chosenNos = new Stack<Integer>();

    private List<List<Integer>> outcome = new ArrayList<List<Integer>>();

    private void backtrack(int[] candidates, int start, int target) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            recordOutcome();
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            chosenNos.push(candidates[i]);
            backtrack(candidates, i, target - candidates[i]);
            chosenNos.pop();
        }
    }

    private void recordOutcome() {
        List<Integer> aSolution = new ArrayList<Integer>(chosenNos);
        outcome.add(aSolution);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return outcome;
    }


    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] testcase = new int[] {2, 3, 7};
        List<List<Integer>> comb = cs.combinationSum(testcase, 7);
        System.out.println(comb.toString());
    }

}
