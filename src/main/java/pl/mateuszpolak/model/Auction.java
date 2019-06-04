package pl.mateuszpolak.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime start;

    private LocalDateTime promo;

    private LocalDateTime sold;

    private String status;
    private String name;
    private String descriptions;
    @Length(max = 500)
    private String image_link;
    @Length(max = 500)
    private String otomoto_link;
    private String price;
    @OneToOne
    private Parameters parameters;

    public Auction() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start.toLocalDate();
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getPromo() {
        return promo;
    }

    public void setPromo(LocalDateTime promo) {
        this.promo = promo;
    }

    public LocalDate getSold() {

        return sold.toLocalDate();
    }

    public void setSold(LocalDateTime sold) {
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getOtomoto_link() {
        return otomoto_link;
    }

    public void setOtomoto_link(String otomoto_link) {
        this.otomoto_link = otomoto_link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}