package com.vgu734.sudoku.service;

import com.vgu734.sudoku.model.Difficulty;
import com.vgu734.sudoku.model.SudokuBoard;

import org.springframework.stereotype.Service;

@Service
public class SudokuService {
	
	public SudokuBoard createBlankBoard() {
		SudokuBoard board = new SudokuBoard();
		board.initializeBlankBoard();
		return board;
	}
	
	public SudokuBoard createNewBoard(Difficulty diff) {
		SudokuBoard board = new SudokuBoard();
		board.initializeBoard(diff);
		return board;
	}
	
	public boolean[][] retrieveConflicts(SudokuBoard board) {
		return board.getConflicts();
	}
}