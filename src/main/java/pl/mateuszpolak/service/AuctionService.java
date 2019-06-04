package pl.mateuszpolak.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.Auction;
import pl.mateuszpolak.model.Parameters;
import pl.mateuszpolak.repository.AuctionRepository;
import pl.mateuszpolak.repository.ParametersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    ParametersRepository parametersRepository;

    public void delete(Long id) {
        Long paramId = auctionRepository.findOne(id).getParameters().getId();
        auctionRepository.delete(id);
        parametersRepository.delete(paramId);
    }

    public void save(Auction auction) {
        auctionRepository.save(auction);
    }

    public List<Auction> getNewest() {
        return auctionRepository.findAllByStatusOrderByStartDesc("active");
    }

    public List<Auction> getPreview() {
        return auctionRepository.findAllByStatusOrderByStartDesc("preview");
    }

    public List<Auction> getPromo() {
        return auctionRepository.findAllByStatusOrderByPriceAsc("promo");
    }

    public List<Auction> getSold() {
        return auctionRepository.findAllByStatusOrderBySoldDesc("sold");
    }

    public List<Auction> getRetails() {return auctionRepository.findAllByStatusOrderByPriceAsc("retail"); }

    public List<Auction> getNoSold() {
        List<Auction> noSold = new ArrayList<>();
        noSold.addAll(getNewest());
        noSold.addAll(getPromo());
        noSold.addAll(getPreview());
        return noSold;
    }

    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    public Auction findOne(Long id) {
        return auctionRepository.findOne(id);
    }

    public Auction auctionByOtomoto(String urlOtomoto) {

        Auction auction = new Auction();

        try {
            Connection connection = Jsoup.connect(urlOtomoto);
            Document document = connection.get();

            Elements params = document.select(".offer-params__value");
            Elements images = document.select("img.bigImage");
            String price = document.selectFirst(".offer-price__number").text().replace(" PLN", "").replace(" ", "");

            String imageLink = images.first().absUrl("data-lazy");

            List<String> parameters = new ArrayList<>();

            String name;
            String productionYear = "";
            String milage = "";
            String capacity = "";
            String fuelType = "";
            String force = "";

            for (Element element : params) {
                parameters.add(element.text());
            }

            for (String string : parameters) {
                if (string.matches("^([0-9]{4})$")) {
                    productionYear = string;
                } else if (string.matches("^[0-9]+\\sKM$")) {
                    force = string.replace(" KM", "").replace(" ", "");
                } else if (string.matches("^[0-9\\s]+\\skm$")) {
                    milage = string.replace(" km", "").replace(" ", "");
                } else if (string.matches("^[0-9\\s]+\\scm3$")) {
                    capacity = string.replace(" cm3", "").replace(" ", "");
                } else if (string.matches("Benzyna|Diesel|Benzyna\\+LPG")) {
                    if (string.matches("Benzyna\\+LPG")) {
                        fuelType = "benzyna+LPG";
                    } else {
                        fuelType = string.toLowerCase();
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < parameters.size() - 2; i++) {
                sb.append(parameters.get(i));
                String test = parameters.get(i + 1);
                if (test.matches("^([0-9]{4})$") || test.matches("^[0-9\\s]+\\skm$") || test.matches("^[0-9\\s]+\\scm3$") || test.matches("Benzyna|Diesel|Benzyna\\+LPG")) {
                    break;
                }
                sb.append(" ");
            }

            name = sb.toString();

            Parameters parameters1 = new Parameters();

            parameters1.setProduction_year(productionYear);
            parameters1.setPower(force);
            parameters1.setMileage(milage);
            parameters1.setCapacity(capacity);
            parameters1.setFuel_type(fuelType);


            auction.setParameters(parameters1);
            auction.setName(name);
            auction.setPrice(price);
            auction.setImage_link(imageLink);
            auction.setOtomoto_link(urlOtomoto);
            auction.setStart(LocalDateTime.now());


        } catch (Exception e) {
            System.out.println("Chuj strzelił: " + e);
        }

        return auction;
    }
}
