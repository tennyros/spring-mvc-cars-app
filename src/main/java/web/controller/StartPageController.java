package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartPageController {

	@GetMapping(value = "/")
	public String printStartMessage(ModelMap model) {
		List<String> strings = new ArrayList<>();
		strings.add("Starting page of");
		strings.add("spring MVC application");
		model.put("messages", strings);
		return "index";
	}
}