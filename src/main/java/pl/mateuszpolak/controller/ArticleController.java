package pl.mateuszpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.mateuszpolak.model.Article;
import pl.mateuszpolak.model.UploadImage;
import pl.mateuszpolak.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/update/{id:[1234]}")
    public String save(@PathVariable Long id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        Article article = articleService.findOne(id);
        article.setDescription(request.getParameter("description").replace("\"", "").replace("\'", ""));

        articleService.save(article);
        model.addAttribute("article", article);
        model.addAttribute("error", "Zmiany zostały zapisane.");
        return "admin/article";
    }

    @RequestMapping("/image/delete/{id:[1234]}")
    public String delete(@PathVariable Long id, Model model) {
        Article article = articleService.findOne(id);
        String delete = article.getImg();

        UploadImage.deleteUnused(delete);
        article.setImg(null);
        articleService.save(article);
        model.addAttribute("article", article);
        return "admin/article";
    }

    @PostMapping("/image/{id:[1234]}")
    public String image(@RequestParam("image") MultipartFile image, Model model, @PathVariable Long id) {
        Article article = articleService.findOne(id);
        String src;

        try {
            src = UploadImage.upload(image);
            String delete = article.getImg();
            article.setImg(src);
            articleService.save(article);
            UploadImage.deleteUnused(delete);
            model.addAttribute("error", "Zdjęcie zostało zmienione");
            model.addAttribute("article", article);
        } catch (IOException e) {
            model.addAttribute("error", "Wyjebało błąd o treści: " + e);
            model.addAttribute("article", article);
        }
        return "/admin/article";
    }

    @PostMapping("/link/{id:[0-9]}")
    public String link(Model model, @PathVariable Long id, @RequestParam String link) {
        Article article = articleService.findOne(id);

        if (link.matches("(.*).img") || link.matches("(.*).png") || link.matches("(.*).jpg") || link.matches("(.*).gif")) {
            String delete = article.getImg();
            article.setImg(link);
            articleService.save(article);
            UploadImage.deleteUnused(delete);
            model.addAttribute("error", "Zdjęcie zostało zmienione");
        } else {
            model.addAttribute("error", "Nie rozpoznano obrazu");
        }
        model.addAttribute("article", article);
        return "admin/article";
    }
}