package com.chaos.leetcode.medium;

import java.util.*;

/**
 * Created by SUNAL4 on 2016-07-27.
 */

/*
  No.39
 */
public class CombinationSum {

    //private Stack<Integer> chosenNos = new Stack<Integer>();

    private Integer[] chosenNos;

    private int lastChosenNoIdx;

    private List<List<Integer>> outcome = new ArrayList<List<Integer>>();

    private void backtrack(int[] candidates, int start, int target) {
        for (int i = start; i < candidates.length; i++) {
            int newTarget = target - candidates[i];
            if (newTarget > 0) {
                chosenNos[lastChosenNoIdx++] = candidates[i];
                backtrack(candidates, i, target - candidates[i]);
                lastChosenNoIdx--;
            } else if (newTarget < 0) {
                return;
            } else {
                chosenNos[lastChosenNoIdx++] = candidates[i];
                recordOutcome();
                lastChosenNoIdx--;
            }
        }
    }

    private void recordOutcome() {
        List<Integer> aSolution = new ArrayList<Integer>(lastChosenNoIdx);
        for (int i = 0; i < lastChosenNoIdx; i++) {
            aSolution.add(chosenNos[i]);
        }
        outcome.add(aSolution);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int maxOutComeLen = target / candidates[0];
        chosenNos = new Integer[maxOutComeLen];
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
