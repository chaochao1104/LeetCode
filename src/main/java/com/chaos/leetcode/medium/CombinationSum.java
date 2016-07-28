package com.chaos.leetcode.medium;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.*;

/**
 * Created by SUNAL4 on 2016-07-27.
 */

/*
  No.39
 */
public class CombinationSum {

    private Stack<Integer> chosenNos = new Stack<Integer>();

    private Set<List<Integer>> outcome = new HashSet<List<Integer>>();

    private int minCand;

    private void backtracking(int[] candidates, int target) {
        if (target < minCand) {
            return;
        } else if (target == minCand) {
            chosenNos.push(minCand);
            recordOutcome();
            chosenNos.pop();
        }

        for (int i = 0; i < candidates.length; i++) {
            int newTarget = target - candidates[i];
            if (newTarget > 0) {
                chosenNos.push(candidates[i]);
                backtracking(candidates, newTarget);
                chosenNos.pop();
            } else if (newTarget == 0) {
                chosenNos.push(candidates[i]);
                recordOutcome();
                chosenNos.pop();
            }
        }
    }

    private void recordOutcome() {
        List<Integer> aSolution = new ArrayList<Integer>(chosenNos);
        Collections.sort(aSolution);
        outcome.add(aSolution);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        for (int cand : candidates) {
            if (minCand > cand) {
                minCand = cand;
            }
        }
        backtracking(candidates, target);
        return new ArrayList<List<Integer>>(outcome);
    }


    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] testcase = new int[] {2, 3, 7};
        List<List<Integer>> comb = cs.combinationSum(testcase, 7);
        System.out.println(comb.toString());
    }

}
