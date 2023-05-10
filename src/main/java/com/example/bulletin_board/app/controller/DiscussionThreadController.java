package com.example.bulletin_board.app.controller;

import com.example.bulletin_board.app.entity.DiscussionThread;
import com.example.bulletin_board.app.repository.DiscussionThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/threads")
public class DiscussionThreadController {
	@Autowired
	private DiscussionThreadRepository discussionThreadRepository;

	@GetMapping("/new")
	public String showCreateThreadForm(Model model) {
		model.addAttribute("discussionThread", new DiscussionThread());
		return "new-thread";
	}

	@PostMapping
	public String createThread(@ModelAttribute DiscussionThread discussionThread, RedirectAttributes redirectAttributes) {
		discussionThread.setCreatedAt(LocalDateTime.now());
		discussionThreadRepository.save(discussionThread);
		redirectAttributes.addFlashAttribute("message", "The thread was successfully created.");
		return "redirect:/api/threads/list";
	}

	@GetMapping("/list")
	public String showThreadList(Model model) {
		model.addAttribute("threads", discussionThreadRepository.findAll());
		return "threads-list";
	}
}
