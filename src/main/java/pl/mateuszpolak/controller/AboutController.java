package pl.mateuszpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszpolak.service.ArticleService;
import pl.mateuszpolak.service.AuctionService;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequestMapping("/about")
public class AboutController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/philosophy")
    public String philosophy(Model model) {
        model.addAttribute("article", articleService.findOne(2L));
        return "about/philosophy";
    }

    @RequestMapping("/sold")
    public String team(Model model) {
        model.addAttribute("article", articleService.findOne(1L));
        model.addAttribute("cars", auctionService.getSold());
        return "about/sold";
    }
}