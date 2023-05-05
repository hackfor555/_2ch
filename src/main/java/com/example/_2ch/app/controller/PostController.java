package com.example._2ch.app.controller;

import com.example._2ch.app.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example._2ch.app.repository.PostRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/new")
	public String newPost() {
		return "new-post";
	}

	@GetMapping
	@ResponseBody
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@PostMapping
	public String createPost(@ModelAttribute Post post,
	                         RedirectAttributes redirectAttributes) {
		Post savedPost = postRepository.save(post);
		redirectAttributes.addFlashAttribute("message",
				"The post was successfully created.");
		return "redirect:/api/posts/new";
	}



}
