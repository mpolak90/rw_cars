package pl.mateuszpolak.controller;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.mateuszpolak.model.Auction;
import pl.mateuszpolak.model.Parameters;
import pl.mateuszpolak.model.UploadImage;
import pl.mateuszpolak.service.AuctionService;
import pl.mateuszpolak.service.ParametersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/logged")
public class LoggedController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    ParametersService parametersService;

    @RequestMapping("/articles")
    public String articles() {
        return "admin/articles";
    }

    @RequestMapping("/auctions")
    public String auctions(Model model) {
        model.addAttribute("title", "spis ofert");
        model.addAttribute("offers", auctionService.getNoSold());
        return "admin/auctions";
    }

    @RequestMapping("/auctions/sold")
    public String sold(Model model) {
        model.addAttribute("title", "sprzedane");
        model.addAttribute("offers", auctionService.getSold());
        return "admin/auctions";
    }

    @GetMapping("/auctions/sold/{id:[0-9]+}")
    public String soldOne(@PathVariable Long id) {
        Auction auction = auctionService.findOne(id);
        auction.setSold(LocalDateTime.now());
        auction.setStatus("sold");
        auctionService.save(auction);
        return "redirect:/logged/auctions/sold";
    }

    @GetMapping("/auctions/new")
    public String newAuction(HttpSession session) {
        session.removeAttribute("car");
        return "admin/new";
    }

    @RequestMapping("/auctions/details/{id:[0-9]+}")
    public String details(Model model, @PathVariable Long id) {
        model.addAttribute("car", auctionService.findOne(id));
        return "admin/details";
    }

    @PostMapping("/auctions/edit")
    public String edit(HttpServletRequest request, @RequestParam("image_upload") MultipartFile file) throws Exception {

        String id = request.getParameter("id");
        Auction auction = auctionService.findOne(Long.valueOf(id));
        Parameters parameters = auction.getParameters();

        parameters.setProduction_year(request.getParameter("production_year"));
        parameters.setPower(request.getParameter("power"));
        parameters.setCapacity(request.getParameter("capacity"));
        parameters.setMileage(request.getParameter("mileage"));
        parameters.setFuel_type(request.getParameter("fuel_type"));

        String image_link = request.getParameter("image_link");

        parametersService.save(parameters);

        if (!file.isEmpty()) {
            image_link = UploadImage.upload(file);

        } else {
            System.out.println("Empty file");
        }

        auction.setParameters(parameters);
        auction.setName(request.getParameter("name"));
        auction.setPrice(request.getParameter("price"));
        auction.setStatus(request.getParameter("status"));
        auction.setImage_link(image_link);

        auctionService.save(auction);

        return "redirect:/logged/auctions";
    }

    @PostMapping("/auctions/add")
    public String addAuction(HttpServletRequest request, HttpSession session, @RequestParam("image_upload") MultipartFile file) throws Exception {
        session.removeAttribute("car");

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String production_year = request.getParameter("production_year");
        String mileage = request.getParameter("mileage");
        String capacity = request.getParameter("capacity");
        String fuel_type = request.getParameter("fuel_type");
        String power = request.getParameter("power");
        String otomoto_link = request.getParameter("otomoto_link");
        String status = request.getParameter("status");
        String image_link = request.getParameter("image_link");

//------------------------------------------------------------------------

        if (!file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(file.getSize());

            image_link = UploadImage.upload(file);

        } else {
            System.out.println("Uploaded file is empty");
        }

//------------------------------------------------------------------------

        Map<String, String> errors = new HashMap<>();

        if (name.isEmpty()) {
            errors.put("name", "wprowadź nazwę");
        } else {
            errors.remove("name");
        }

        if (price.isEmpty() || !StringUtil.isNumeric(price)) {
            errors.put("price", "wprowadź cenę");
        } else {
            errors.remove("price");
        }

        if (!production_year.matches("^([0-9]{4})$")) {
            errors.put("production_year", "wprowadź prawidłowy rok");
        } else {
            errors.remove("production_year");
        }

        if (mileage.isEmpty()) {
            errors.put("mileage", "wprowadź przebieg pojazdu");
        } else {
            errors.remove("mileage");
        }

        if (capacity.isEmpty() || !StringUtil.isNumeric(capacity)) {
            errors.put("capacity", "wprowadź pojemność silnika");
        } else {
            errors.remove("capacity");
        }

        if (fuel_type.isEmpty()) {
            errors.put("fuel_type", "wybierz rodzaj paliwa");
        } else {
            errors.remove("fuel_type");
        }

        if (power.isEmpty()) {
            errors.put("power", "podaj moc silnika");
        } else {
            errors.remove("power");
        }

        if (otomoto_link.isEmpty()) {
            errors.put("otomoto_link", "wpowadź link od oferty");
        } else {
            errors.remove("otomoto_link");
        }

        if (image_link != null) {
            if (image_link.isEmpty()) {
                errors.put("image_link", "wprowadź link do obrazu");
            } else {
                errors.remove("image-link");
            }
        }

        if (errors.isEmpty()) {
            Auction auction = new Auction();
            Parameters parameters = new Parameters();

            parameters.setProduction_year(production_year);
            parameters.setPower(power);
            parameters.setCapacity(capacity);
            parameters.setMileage(mileage);
            parameters.setFuel_type(fuel_type);

            parametersService.save(parameters);

            auction.setParameters(parameters);
            auction.setName(name);
            auction.setPrice(price);
            auction.setStatus(status);
            auction.setOtomoto_link(otomoto_link);
            auction.setImage_link(image_link);
            auction.setStart(LocalDateTime.now());

            auctionService.save(auction);

            return "redirect:/logged/auctions";
        } else {
            session.setAttribute("errors", errors);
            return "admin/new";
        }

    }

    @RequestMapping("/auctions/add/byOtomoto")
    public String add(HttpServletRequest request, HttpSession session, Model model) {
        String link = request.getParameter("otomoto");


        if (link.matches("^(https://www\\.otomoto\\.pl/oferta/)([.\\S]*)$")) {
            Auction auction = auctionService.auctionByOtomoto(link);
            session.removeAttribute("upload_error");
            model.addAttribute("car", auction);
            return "admin/new";
        } else {
            session.removeAttribute("car");
            model.addAttribute("upload_error", "adres nieprawidłowy");
            return "/admin/new";
        }
    }

    @RequestMapping("/auctions/delete/{id:[0-9]+}")
    public String delete(@PathVariable Long id) {
        Auction auction = auctionService.findOne(id);
        String image = auction.getImage_link();
        System.out.println(image.substring(0, 9));
        UploadImage.deleteUnused(image);
        auctionService.delete(id);
        return "redirect:/logged/auctions";
    }
}