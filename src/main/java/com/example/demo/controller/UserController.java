package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);

        return "listUser";
    }

    @RequestMapping(value = "add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "find")
    public String findUser(Model model) {
        model.addAttribute("user", new User());
        return "findUser";
    }

    @RequestMapping(value = "loginUser")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model) {
        Optional<User> userEdit = userService.findUserById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }

        @RequestMapping(value = "save", method = RequestMethod.POST)
        public String save(User user) {
            System.out.println("Saving user: " + user);
            userService.saveUser(user);
            return "redirect:/";
        }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long userId, Model model) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public String findUser(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<User> users = userService.searchUser(searchTerm);
        model.addAttribute("users", users);
        return "findUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            // Lưu thông tin user vào session
            session.setAttribute("currentUser", user);
            List<User> users = userService.getAllUser();
            users.remove(user);
            model.addAttribute("users", users);

            return "listUser";
        } else {
            model.addAttribute("errorMessage", "true");
            return "redirect:/loginUser";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // Xóa thông tin người dùng khỏi session
        session.invalidate();
        return "redirect:/loginUser";
    }
}
