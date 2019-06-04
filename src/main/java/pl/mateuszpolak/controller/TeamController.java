package pl.mateuszpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.mateuszpolak.model.Team;
import pl.mateuszpolak.model.UploadImage;
import pl.mateuszpolak.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/admin/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @RequestMapping("/edit/{id:[0-9]+}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("title", "edytuj członka");
        model.addAttribute("member", teamService.findOne(id));
        return "/admin/team-edit";
    }

    @RequestMapping("/delete/{id:[0-9]+}")
    public String delete(@PathVariable Long id, Model model) {
        Team team = teamService.findOne(id);
        String delete = team.getImg();
        UploadImage.deleteUnused(delete);
        teamService.delete(team);
        model.addAttribute("error", "członek został usunięty");
        model.addAttribute("team", teamService.findAll());
        return "/admin/articles";
    }

    @RequestMapping("/show/{id:[0-9]+}")
    public String show(@PathVariable Long id, Model model) {
        Team team = teamService.findOne(id);
        if (team.isActive()) {
            team.setActive(false);
            teamService.save(team);
            model.addAttribute("team", teamService.findAll());
        } else {
            team.setActive(true);
            teamService.save(team);
            model.addAttribute("team", teamService.findAll());
        }
        return "/admin/articles";
    }

    @RequestMapping("/new")
    public String newMember(Model model) {
        Team team = new Team();
        model.addAttribute("member", team);
        model.addAttribute("title", "Nowy członek");
        return "admin/team-edit";
    }

    @PostMapping("/step")
    public String step(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Team team;

        if (request.getParameter("id").equals("")) {
            team = new Team();
        } else {
            team = teamService.findOne(Long.valueOf(request.getParameter("id")));
        }

        team.setName(request.getParameter("name"));
        team.setDescription(request.getParameter("description"));

        if (!team.isActive()) {
            team.setActive(false);
        } else {
            team.setActive(true);
        }

        if (team.getImg() == null) {
            team.setImg("https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png");
        }
        teamService.save(team);
        model.addAttribute("member", team);
        return "admin/team-image";
    }

    @PostMapping("/image/link")
    public String link(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String image = request.getParameter("image");
        Long id = Long.valueOf(request.getParameter("id"));
        Team team = teamService.findOne(id);

        String delete = "";
        if (image.matches("(.*).img") || image.matches("(.*).png") || image.matches("(.*).jpg") || image.matches("(.*).gif")) {
            if (team.getImg() != null) {
                delete = team.getImg();
            }
            team.setImg(image);
            teamService.save(team);

            if (delete.matches("/images/auctions/(.*)")) {
                UploadImage.deleteUnused(delete);
            }

            model.addAttribute("error", "Pomyślnie dodano zdjęcie");
        } else {
            model.addAttribute("error", "Nie rozpoznano obrazu");
            model.addAttribute("member", team);
            return "admin/team-image";
        }
        model.addAttribute("team", teamService.findAll());
        return "/admin/articles";

    }

    @PostMapping("/image/upload")
    public String upload(@RequestParam("image") MultipartFile image, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        String src;
        Team team = teamService.findOne(id);

        try {
            src = UploadImage.upload(image);
            String delete = team.getImg();
            team.setImg(src);
            teamService.save(team);
            if (delete.matches("/images/auctions/(.*)")) {
                UploadImage.deleteUnused(delete);
            }
            model.addAttribute("error", "Pomyślnie dodano suchara");
        } catch (IOException e) {
            model.addAttribute("error", "Wyjebało błąd o treści: " + e);
        }
        model.addAttribute("team", teamService.findAll());
        return "/admin/rusks";
    }
}