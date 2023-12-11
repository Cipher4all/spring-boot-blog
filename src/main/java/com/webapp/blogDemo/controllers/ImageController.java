package com.webapp.blogDemo.controllers;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.webapp.blogDemo.services.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ImageController {

	private final FileService fileService;
	
	@GetMapping("images/{id}")
	public Resource getImage(@PathVariable("id") String imageUri) {
		return fileService.load(imageUri);
	}
}
