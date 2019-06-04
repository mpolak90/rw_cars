package pl.mateuszpolak.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszpolak.model.User;
import pl.mateuszpolak.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String users(Model model) {
        model.addAttribute("title", "Aktywni użytkownicy");
        model.addAttribute("users", userService.findAllActive());
        return "admin/users";
    }

    @RequestMapping("/delete/{id:[0-9]+}")
    public String delete(@PathVariable Long id) {
        User user = userService.findOne(id);
        userService.delete(user);
        return "redirect:/admin/users/blocked";
    }

    @RequestMapping("/blocked")
    public String blocked(Model model) {
        model.addAttribute("title", "Użytkownicy zablokowani");
        model.addAttribute("users", userService.findAllBlock());
        return "admin/users";
    }

    @RequestMapping("/password/{id:[0-9]+}")
    public String password(Model model, @PathVariable Long id) {
        model.addAttribute("title", "nowe hasło");
        model.addAttribute("user", userService.findOne(id));
        return "admin/user-password";
    }

    @RequestMapping("/admin/{id:[0-9]+}")
    public String admin(@PathVariable Long id) {
        User user = userService.findOne(id);

        if (user.isAdmin()) {
            user.setAdmin(false);
            userService.save(user);
            return "redirect:/admin/users";
        } else {
            user.setAdmin(true);
            userService.save(user);
            return "redirect:/admin/users";
        }
    }

    @RequestMapping("/block/{id:[0-9]+}")
    public String block(@PathVariable Long id) {
        User user = userService.findOne(id);

        if (user.isActive()) {
            user.setActive(false);
            userService.save(user);
            return "redirect:/admin/users/blocked";
        } else {
            user.setActive(true);
            userService.save(user);
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/password/save")
    public String newPassword(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String password = request.getParameter("password");
        String passwordSec = request.getParameter("password-sec");
        User user = userService.findOne(Long.valueOf(request.getParameter("id")));

        if (password.equals(passwordSec)) {
            if (password.length() > 5) {
                user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                userService.save(user);
                model.addAttribute("title", "nowe hasło");
                model.addAttribute("info", "hasło zostało zmienione");
                model.addAttribute("user", user);
                return "admin/user-password";
            } else {
                model.addAttribute("info", "hasło za krótkie");
                model.addAttribute("title", "nowe hasło");
            }
        } else {
            model.addAttribute("title", "nowe hasło");
            model.addAttribute("info", "hasła nie są jednakowe");
        }
        model.addAttribute("user", user);
        return "admin/user-password";
    }

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("title", "nowy użytkownik");
        return "admin/user-editor";
    }

    @PostMapping("/save")
    public String save(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordSec = request.getParameter("password-sec");
        String adminStr = request.getParameter("admin");
        boolean admin = true;

        if (adminStr == null) {
            admin = false;
        }

        User user = new User();
        user.setLogin(login);
        user.setAdmin(admin);

        if (userService.checkAvailable(login)) {
            if (password.equals(passwordSec)) {
                if (password.length() > 5) {
                    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user.setActive(true);
                    userService.save(user);
                    model.addAttribute("info", "dodano użytkownika" + login);
                    model.addAttribute("users", userService.findAllActive());
                    return "/admin/users";
                } else {
                    model.addAttribute("info", "hasło za krótkie");
                }
            } else {
                model.addAttribute("info", "hasła różnią się od siebie");
            }
        } else {
            model.addAttribute("info", "nazwa użytkownika zajęta");
        }

        model.addAttribute("user", user);
        return "admin/user-editor";
    }
}
