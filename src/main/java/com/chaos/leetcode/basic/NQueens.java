package com.chaos.leetcode.basic;

/**
 * Created by SUNAL4 on 2016-07-26.
 */
public class NQueens {

    private int[] occupied; //occupied[1] = 2 means (1, 2) is occupied. 0 is not used.

    private long numOfSolution;

    private boolean needPrint;

    public NQueens() {
    }

    public void setNumberOfQueens(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must >= 1");
        }
        occupied = new int[n];
    }

    private boolean available(int x, int y) {
        for (int i = 0; i < x; i++) {
            if (occupied[i] == y) {
                return false;
            }

            if (x - i == y - occupied[i] || i - x == y - occupied[i]) {
                return false;
            }
        }

        return true;
    }

    public void backtracking(int i) {
        if (i == occupied.length) {
            //find one solution.
            numOfSolution++;
            if (needPrint) {
                printSolution();
            }
            return;
        }

        for (int j = 0; j < occupied.length; j++) {
            if (!available(i, j)) {
                continue;
            }
            occupied[i] = j;
            backtracking(i + 1);
        }

    }

    private void printSolution() {
        System.out.println("Solution " + numOfSolution + ":");

        for (int y = 0; y < occupied.length; y++) {
            for (int x = 0; x < occupied.length; x++) {
                System.out.print(occupied[x] == y ? "Q " : "_ ");
            }
            System.out.println();
        }

        for (int y = 0; y < occupied.length; y++) {
            System.out.print("==");
        }
        System.out.println();
    }

    public long getNumOfSolution() {
        return numOfSolution;
    }

    public void setNeedPrint(boolean needPrint) {
        this.needPrint = needPrint;
    }

    public static void main(String[] args) {
        NQueens queen = new NQueens();
        queen.setNumberOfQueens(15);
        queen.setNeedPrint(true);
        queen.backtracking(0);
        System.out.println("total solution number:" + queen.getNumOfSolution());
    }

}
