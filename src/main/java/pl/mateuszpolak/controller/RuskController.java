package pl.mateuszpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.mateuszpolak.model.Rusk;
import pl.mateuszpolak.model.UploadImage;
import pl.mateuszpolak.service.RuskService;

import java.io.IOException;

@Controller
@RequestMapping("/admin/rusks")
public class RuskController {

    @Autowired
    RuskService ruskService;

    @RequestMapping("/delete/{id:[0-9]+}")
    public String delete(@PathVariable Long id) {
        Rusk rusk = ruskService.findOne(id);
        String delete = rusk.getImg();
        UploadImage.deleteUnused(delete);
        ruskService.delete(rusk);
        return "redirect:/admin/rusks";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String image, Model model) {
        if (image.matches("(.*).img") || image.matches("(.*).png") || image.matches("(.*).jpg") || image.matches("(.*).gif")) {
            Rusk rusk = new Rusk();
            rusk.setImg(image);
            ruskService.save(rusk);
            model.addAttribute("error", "Pomyślnie dodano suchara");
        } else {
            model.addAttribute("error", "Nie rozpoznano obrazu");
        }
        model.addAttribute("rusks", ruskService.findAll());
        return "/admin/rusks";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("image") MultipartFile image, Model model) {
        String src;

        try {
            src = UploadImage.upload(image);
            Rusk rusk = new Rusk();
            rusk.setImg(src);
            ruskService.save(rusk);
            model.addAttribute("error", "Pomyślnie dodano suchara");
            model.addAttribute("rusks", ruskService.findAll());
        } catch (IOException e) {
            model.addAttribute("error", "Wyjebało błąd o treści: " + e);
            model.addAttribute("rusks", ruskService.findAll());
        }
        return "/admin/rusks";
    }
}
