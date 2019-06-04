package pl.mateuszpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszpolak.model.SendMail;
import pl.mateuszpolak.service.ArticleService;
import pl.mateuszpolak.service.AuctionService;
import pl.mateuszpolak.service.RuskService;
import pl.mateuszpolak.service.TeamService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Controller
@Transactional
public class MainController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    RuskService ruskService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TeamService teamService;

    @RequestMapping("")
    public String main(Model model) {
        model.addAttribute("cars", auctionService.getNewest());
        return "index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("team", teamService.findAllActive());
        return "about";
    }

    @RequestMapping("/order")
    public String order(Model model) {
        model.addAttribute("article",articleService.findOne(3L));
        return "order";
    }

    @RequestMapping("/rusks")
    public String rusks(Model model) {
        model.addAttribute("rusks", ruskService.findAll());
        return "rusks";
    }

    @RequestMapping("/admin")
    public String admin() {

        return "admin";
    }

    @PostMapping("/admin")
    public String login(Model model) {
        model.addAttribute("offers", auctionService.getNoSold());
        return "admin/auctions";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("article", articleService.findOne(4L));
        return "contact";
    }

    @PostMapping("/contact")
    public String mail(HttpServletRequest request, ServletRequest servletRequest) throws UnsupportedEncodingException {

        servletRequest.setCharacterEncoding("UTF-8");
        request.getCharacterEncoding();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("content");

        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();

        title.append("Wiadomość od ");
        title.append(name);

        content.append("Wiadomość od ").append(name).append(": ");
        content.append("<br><br>");
        content.append(message);
        content.append("<hr>");
        content.append(name).append(" czeka na odpowiedź na e-mail: ");
        content.append("<br>");
        content.append(email);
        content.append("<br>");
        content.append("Strona ClassicCarsDoc.pl");

        SendMail.execute(title.toString(), content.toString());

        request.setAttribute("response", "OK");
        return "contact";
    }

    @PostMapping("/tel")
    public String tel(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        char[] chars = phone.toCharArray();
        String formatted = "";
        for (int i = 0; i < chars.length; i++) {
            if (i == 2 || i == 5) {
                formatted = formatted + chars[i] + " ";
            } else {
                formatted = formatted + chars[i];
            }
        }

        String title = "Kontakt +48 " + formatted;
        String content = "Dzień dobry, <br><br> Osoba o numerze telefonu <b>+48 " + formatted + "</b> prosi o kontakt telefoniczny. <br><br>Strona ClassicCarsDoc.pl";

        SendMail.execute(title, content);

        request.setAttribute("info", "OK");
        return "index";
    }
}