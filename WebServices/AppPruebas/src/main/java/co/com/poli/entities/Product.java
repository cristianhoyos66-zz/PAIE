package co.com.poli.entities;

import java.io.Serializable;

public class Product implements Serializable{

    private String code;
    private String name;
    private Double cost;

    public Product() {
    }
    
    public Product(String code, String name, Double cost) {
        this.code = code;
        this.name = name;
        this.cost = cost;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" + "code=" + code + ", name=" + name + ", cost=" + cost + '}';
    }
    
}
