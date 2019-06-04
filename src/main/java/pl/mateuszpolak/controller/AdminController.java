package pl.mateuszpolak.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszpolak.model.User;
import pl.mateuszpolak.service.ArticleService;
import pl.mateuszpolak.service.RuskService;
import pl.mateuszpolak.service.TeamService;
import pl.mateuszpolak.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RuskService ruskService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @RequestMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("team", teamService.findAll());
        return "admin/articles";
    }

    @RequestMapping("/rusks")
    public String rusks(Model model) {
        model.addAttribute("rusks", ruskService.findAll());
        return "admin/rusks";
    }

    @RequestMapping("/articles/{id:[1234]}")
    public String article(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findOne(id));
        return "admin/article";
    }

    @RequestMapping("/instruction")
    public String instruction() {
        return "admin/instruction";
    }
}