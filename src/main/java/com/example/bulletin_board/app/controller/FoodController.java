package com.example.bulletin_board.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FoodController {
	@GetMapping("/food")
	public String food() {
		return "food";


	}
}
