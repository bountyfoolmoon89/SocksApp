package com.example.socksapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Socks {

    private double size;

    private String color;

    private String sostav;

    private boolean nalichie;

    public Socks(double size, String color, String sostav, boolean nalichie) {
        this.size = size;
        this.color = color;
        this.sostav = sostav;
        this.nalichie = nalichie;
    }

    public double getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getSostav() {
        return sostav;
    }

    public boolean isNalichie() {
        return nalichie;
    }

    public void setSize(double size) {
        if (size > 36 && size < 49){
            this.size = size;
        }
    }

    public void setColor(String color) {
        if (color.isEmpty() | color.isBlank()){
            throw new RuntimeException("Укажите цвет");
        } else {
            this.color = color;
        }
    }

    public void setSostav(String sostav) {
        if (sostav.isBlank() | sostav.isEmpty()){
            throw new RuntimeException("Укажите состав");
        } else {
            this.sostav = sostav;
        }
    }
}
