package com.vgu734.sudoku.model;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard {
    private Cell[][] board;
    private static final int SIZE = 9;
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);

    public SudokuBoard() {
        this.board = new Cell[SIZE][SIZE];
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public void initializeBlankBoard() {
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                this.board[i][j] = new Cell(null, false);
            }
        }
    }

    public void initializeBoard(Difficulty diff) {
        initializeBlankBoard();
        generateSudoku();
        removeCells(diff);
        logger.info("Initialized " + diff.toString() + " board:\n" + this.toString());
    }

    private void generateSudoku() {
        fillDiagonalBlocks();
        fillRemaining(0, 0);
    }

    private void fillDiagonalBlocks() {
        for (int i = 0; i < SIZE; i += 3) {
            fillBlock(i, i);
        }
    }

    private void fillBlock(int row, int col) {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            int num;
            do {
                num = random.nextInt(SIZE) + 1;
            } while (!isValid(row, col, num));
            this.board[row + i / 3][col + i % 3] = new Cell(num, true);
        }
    }

    private boolean fillRemaining(int row, int col) {
        if (row >= SIZE - 1 && col >= SIZE) {
            return true;
        }
        if (col >= SIZE) {
            row++;
            col = 0;
        }
        if (this.board[row][col].getValue() != null) {
            return fillRemaining(row, col + 1);
        }
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                this.board[row][col] = new Cell(num, true);
                if (fillRemaining(row, col + 1)) {
                    return true;
                }
                this.board[row][col] = new Cell(null, false);
            }
        }
        return false;
    }

    private void removeCells(Difficulty diff) {
        Random random = new Random();
        int numCellsToRemove;
        switch (diff) {
            case EASY:
                numCellsToRemove = 40;
                break;
            case MEDIUM:
                numCellsToRemove = 57;
                break;
            case HARD:
                numCellsToRemove = 63;
                break;
            case EXTREME:
                numCellsToRemove = 80;
                break;
            default:
                numCellsToRemove = 50;
        }

        int cellsRemoved = 0;
        while (cellsRemoved < numCellsToRemove) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (this.board[row][col].getValue() != null) {
                this.board[row][col] = new Cell(null, false);
                cellsRemoved++;
            }
        }
    }

    private boolean isValid(int row, int col, int val) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (this.board[row][i].getValue() != null && this.board[row][i].getValue() == val) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (this.board[i][col].getValue() != null && this.board[i][col].getValue() == val) {
                return false;
            }
        }

        // Check 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (this.board[i][j].getValue() != null && this.board[i][j].getValue() == val) {
                    return false;
                }
            }
        }

        return true;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        if (this.board == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append("[");
            for (int j = 0; j < SIZE; j++) {
                if (j > 0) {
                    sb.append(" ");
                }
                sb.append(this.board[i][j].getValue() != null ? this.board[i][j].getValue() : " ");
            }
            sb.append("]");
        }

        return sb.toString();
    }
}
