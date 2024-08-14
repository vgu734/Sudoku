package com.vgu734.sudoku.controller;

import com.vgu734.sudoku.model.Difficulty;
import com.vgu734.sudoku.model.SudokuBoard;
import com.vgu734.sudoku.service.SudokuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ArrayList;

@Controller
public class SudokuController {

	private static final Logger logger = LoggerFactory.getLogger(SudokuController.class);
	
	@Autowired
	private SudokuService sudokuService;

    @GetMapping("/")
    public String getBoard(Model model) {
    	logger.info("Hit get endpoint: '/'");
    	SudokuBoard board = sudokuService.createBlankBoard();
    	model.addAttribute("board", board);
        return "index";
    }
    
    @PostMapping("/start")
    public String getBoard(@RequestParam("diff") Difficulty diff, Model model) {
    	logger.info("Hit post endpoint: '/start'");
    	SudokuBoard board = sudokuService.createNewBoard(diff);
    	model.addAttribute("board", board);
    	model.addAttribute("diff", diff);
        return "index";
    }
}