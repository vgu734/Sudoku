package com.vgu734.sudoku.model;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard {
	private Integer[][] board;
	private static final int SIZE = 9;
	
	private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
	
	public SudokuBoard() {
		this.board = new Integer[SIZE][SIZE];
	}
	
	public Integer[][] getBoard() {
		return this.board;
	}
	
	public void initializeBlankBoard() {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.board[i][j] = null;
			}
		}
	}
	
	public void initializeBoard(Difficulty diff) {
		initializeBlankBoard();
		
		switch (diff) {
		case EASY:
			fillBoard(50);
			break;
		case MEDIUM:
			fillBoard(40);
			break;
		case HARD:
			fillBoard(30);
			break;
		case EXTREME:
			fillBoard(20);
			break;
		}
		logger.info("Initialized " + diff.toString() +" board:\n" + this.toString());
	}
	
	private void fillBoard(int n) {
		Random random = new Random();
		int count = 0;
		
		while(count < n) {
			int row = random.nextInt(SIZE);
			int col = random.nextInt(SIZE);
			int val = random.nextInt(SIZE) + 1;
			
			if(isValid(row, col, val)) {
				this.board[row][col] = val;
				count++;
			}
		}
	}
	
	private boolean isValid(int row, int col, int val) {
		// Check row
        for(int i=0; i<SIZE; i++) {
            if(this.board[row][i] != null && this.board[row][i] == val) {
                return false;
            }
        }

        // Check column
        for(int i=0; i<SIZE; i++) {
            if(this.board[i][col] != null && this.board[i][col] == val) {
                return false;
            }
        }

        // Check 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for(int i=startRow; i < startRow+3; i++) {
            for(int j=startCol; j < startCol+3; j++) {
                if(this.board[i][j] != null && this.board[i][j] == val) {
                    return false;
                }
            }
        }

        return true;
	}
	
	public void setBoard(Integer[][] board) {
		this.board = board;
	}
	
	@Override
	public String toString() {
		if (this.board == null) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<SIZE; i++) {
			if(i>0) {
				sb.append(",\n");
			}
			sb.append("[");
			for(int j=0; j<SIZE; j++) {
				if(j>0) {
					sb.append(" ");
				}
				sb.append(this.board[i][j] != null ? this.board[i][j] : " ");
			}
			sb.append("]");
		}
		
		return sb.toString();
	}
}
