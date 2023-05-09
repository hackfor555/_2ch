package com.example.bulletin_board.app.controller;

import com.example.bulletin_board.app.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.bulletin_board.app.repository.PostRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	/*
	 * 新規作成
	 */
	@GetMapping("/new")
	public String newPost() {
		return "new-post";
	}


	@PostMapping
	public String createPost(@ModelAttribute Post post,
	                         RedirectAttributes redirectAttributes) {
		Post savedPost = postRepository.save(post);
		redirectAttributes.addFlashAttribute("message",
				"The post was successfully created.");
		return "redirect:/api/posts/new";
	}

	/*
 * 一覧表示
	 */
	@GetMapping("/list")
	public String listPosts(Model model) {
		List<Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);
		return "posts-list";
	}



}
