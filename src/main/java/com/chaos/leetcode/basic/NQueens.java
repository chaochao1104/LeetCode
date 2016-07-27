package com.chaos.leetcode.basic;

/**
 * Created by SUNAL4 on 2016-07-26.
 */
public class NQueens {

    private int[] occupied; //occupied[1] = 2 means (1, 2) is occupied. 0 is not used.

    private int numOfSolution;

    private boolean needPrint = true;

    public NQueens(int n) {
        occupied = new int[n + 1];
    }

    private boolean available(int x, int y) {
        for (int i = 1; i < x; i++) {
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

        for (int j = 1; j < occupied.length; j++) {
            if (!available(i, j)) {
                continue;
            }
            occupied[i] = j;
            backtracking(i + 1);
        }

    }

    private void printSolution() {
        System.out.println("Solution " + numOfSolution + ":");

        for (int y = 1; y < occupied.length; y++) {
            for (int x = 1; x < occupied.length; x++) {
                System.out.print(occupied[x] == y ? "Q " : "_ ");
            }
            System.out.println();
        }

        for (int y = 1; y < occupied.length; y++) {
            System.out.print("==");
        }
        System.out.println();
    }

    public int getNumOfSolution() {
        return numOfSolution;
    }

    public void setNeedPrint(boolean needPrint) {
        this.needPrint = needPrint;
    }

    public static void main(String[] args) {
        NQueens queen = new NQueens(13);
        queen.setNeedPrint(false);
        queen.backtracking(1);
        System.out.println("total solution:" + queen.getNumOfSolution());
    }

}
