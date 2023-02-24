package com.yiseul.spring.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String displayWelcome(Model model) {
		model.addAttribute("msg", "Welcome to COMP367");
		return "index";
	}
}
