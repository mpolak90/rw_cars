package pl.mateuszpolak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String production_year;

    private String mileage;

    private String fuel_type;

    private String capacity;

    private String power;

    public Parameters() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduction_year() {
        return production_year;
    }

    public void setProduction_year(String production_year) {
        this.production_year = production_year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}