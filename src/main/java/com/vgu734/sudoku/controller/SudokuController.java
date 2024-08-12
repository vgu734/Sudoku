package com.vgu734.sudoku.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.ArrayList;

@Controller
public class SudokuController {

	private static final Logger logger = LoggerFactory.getLogger(SudokuController.class);
	
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/")
    public String getBoard(Model model) {
    	logger.info("HERE!!!!!!!!!!!!!!!!!!!!!!!!");
        return "index";
    }
}