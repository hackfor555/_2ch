package com.example._2ch.app.controller;

import com.example._2ch.app.entity.User;
import com.example._2ch.app.service.UserService;
import com.example._2ch.app.Dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PostMapping;import java.util.List;

@Controller
public class AuthController {
  @Autowired
  private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String users(Model model){
    List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/index")
    public String home(Model model) {
        return "index";
    }

    //handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        /*
        create model attribute to bind form data
         */
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    /*
    handler method to handle user registration form request and data
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
    /*
    check if user exists
     */
        User existing = userService.findByUserEmail(userDto.getEmail());

        if(existing != null && existing.getEmail() != null && !existing.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            return "register";
        }

        /*
        save user in database if existing user is not found
         */
        userService.saveUser(userDto);
        model.addAttribute("user", new UserDto());

        return "redirect:/register?success";
    }

}
