package demo.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

	@GetMapping(path = "/demoPage")
	public String domePage() {
		return "/demoPage/demo2";
	}
	
	@GetMapping(path = "/homePage")
	public String homePage() {
		return "/hemoPage/demo";
	}
	
}
