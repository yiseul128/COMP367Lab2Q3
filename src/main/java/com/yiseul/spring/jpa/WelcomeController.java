package com.yiseul.spring.jpa;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String displayWelcome(Model model) {
		String msg = "Good morning";
		
		LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current system date/time is : "
                + currentTime);
		
        // check if hour in afternoon
        int h =Integer.parseInt(currentTime.toString().substring(11, 13));
        System.out.println("h: " + h);
        if(h>=12) {
        	msg = "Good afternoon";
        }
        
		model.addAttribute("msg", msg);
		return "index";
	}
}
