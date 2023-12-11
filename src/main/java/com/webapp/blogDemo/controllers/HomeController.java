package com.webapp.blogDemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webapp.blogDemo.models.Post;
import com.webapp.blogDemo.services.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@Autowired
	private final PostService postService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Post> posts = postService.getAll();
		model.addAttribute("posts", posts);
		return "home";
	}
}
